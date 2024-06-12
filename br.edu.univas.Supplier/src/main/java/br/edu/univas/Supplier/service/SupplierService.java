package br.edu.univas.Supplier.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.univas.Supplier.dtos.SupplierDTO;
import br.edu.univas.Supplier.entites.SupplierEntity;
import br.edu.univas.Supplier.repositories.SupplierRepository;
import br.edu.univas.Supplier.suporte.ObjectNotFoundException;
import br.edu.univas.Supplier.suporte.SupplierException;


@Service
public class SupplierService {
	
	private SupplierRepository repo;
	
	@Autowired
	private SupplierEntityConverter converter;
	
	@Autowired
	public SupplierService(SupplierRepository repo) {
		this.repo = repo;
	}
	
	public List<SupplierDTO> findAll(){
		return repo.findAll().stream().map(SupplierEntityConverter::toDTO).collect(Collectors.toList());
	}
	
	
	public SupplierEntity findById(Integer id) {
		Optional<SupplierEntity> obj = repo.findById(id);
		SupplierEntity entity = obj.orElseThrow(() -> new ObjectNotFoundException("Object not foun: " + id));
		return entity;
	}
	
	public List<SupplierDTO> findByActive(boolean b) {
		return repo.findByActive(true).stream().map(SupplierEntityConverter::toDTO).collect(Collectors.toList());
	}
	
	public void createSupplier(SupplierDTO supplier) {
		repo.save(converter.toEntity(supplier));
	}
	
	public void updateSupplier(SupplierDTO suppliers, Integer id) {
		if (id == null || suppliers == null || !id.equals(suppliers.getCnpj())) {
			throw new SupplierException("Invalid Supplier ID.");
		}
		SupplierEntity existingObj = findById(id);
		updateData(existingObj, suppliers);
		repo.save(existingObj);
	}
	
	private void updateData(SupplierEntity existingObj, SupplierDTO newObj) {
		existingObj.setName(newObj.getName());
	}
	
	public void deleteSupplier(Integer id) {
		if (id == null) {
			throw new SupplierException("Supplier ID can not be null.");
		}
		SupplierEntity obj = findById(id);
		try {
			repo.delete(obj);
		} catch (DataIntegrityViolationException e) {
			throw new SupplierException("Can not delete a Supplier with dependencies constraints.");
		}
	}
	
	

}



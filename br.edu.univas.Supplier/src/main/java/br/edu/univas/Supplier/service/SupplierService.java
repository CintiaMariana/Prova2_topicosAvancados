package br.edu.univas.Supplier.service;

import java.util.List;
import java.util.Optional;

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
	
	private SupplierEntityConverter converter = new SupplierEntityConverter();
	
	@Autowired
	public SupplierService(SupplierRepository repo) {
		this.repo = repo;
	}
	
	public List<SupplierEntity> findAll(){
		return repo.findAll();
	}
	
	public SupplierEntity findById(Integer cnpj) {
		Optional<SupplierEntity> obj = repo.findById(cnpj);
		return obj.orElseThrow(() -> new ObjectNotFoundException("No Supplier found with CNPJ: " + cnpj));
		
	}
	
	public void createSupplier(SupplierDTO supplier) {
		repo.save(converter.toEntity(supplier));
	}
	
	public void updateSupplier(SupplierDTO suppliers, Integer cnpj) {
		if (cnpj == null || suppliers == null || !cnpj.equals(suppliers.getName())) {
			throw new SupplierException("Invalid Supplier CNPJ.");
		}
		SupplierEntity existingObj = findById(cnpj);
		repo.save(existingObj);
	}
	
	public void deleteSupplier(Integer cnpj) {
		if (cnpj == null) {
			throw new SupplierException("Supplier CNPJ can not be null.");
		}
		SupplierEntity obj = findById(cnpj);
		try {
			repo.delete(obj);
		} catch (DataIntegrityViolationException e) {
			throw new SupplierException("Can not delete a Product with dependencies constraints.");
		}
	}
	
	

}



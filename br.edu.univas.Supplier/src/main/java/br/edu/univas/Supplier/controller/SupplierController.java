package br.edu.univas.Supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.Supplier.dtos.SupplierDTO;
import br.edu.univas.Supplier.entites.SupplierEntity;
import br.edu.univas.Supplier.service.SupplierService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/supplier")

public class SupplierController {
	@Autowired
	private SupplierService service;
	
	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<SupplierDTO> getAllSuppliers(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public SupplierEntity findById(@PathVariable int id) {
		return service.findById(id);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSupplier(@RequestBody SupplierDTO dto, @PathVariable  Integer id) {
		service.updateSupplier(dto, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSupplier(@PathVariable Integer id) {
		service.deleteSupplier(id);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createSupplier(@RequestBody SupplierDTO supplier) {
		service.createSupplier(supplier);
	}
}

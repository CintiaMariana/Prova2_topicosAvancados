package br.edu.univas.Supplier.controller;

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
	
	@GetMapping("/{cnpj}")
	public SupplierEntity findById(@PathVariable Integer cnpj) {
		return service.findById(cnpj);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSupplier(@RequestBody SupplierDTO dto, @PathVariable  Integer cnpj) {
		service.updateSupplier(dto, cnpj);
	}
	
	@DeleteMapping("/{cnpj}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSupplier(@PathVariable Integer cnpj) {
		service.deleteSupplier(cnpj);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createSupplier(@RequestBody SupplierDTO supplier) {
		service.createSupplier(supplier);
	}
}

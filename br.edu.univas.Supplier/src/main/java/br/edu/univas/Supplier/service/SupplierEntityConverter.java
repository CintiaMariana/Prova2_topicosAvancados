package br.edu.univas.Supplier.service;

import org.springframework.stereotype.Component;

import br.edu.univas.Supplier.dtos.SupplierDTO;
import br.edu.univas.Supplier.entites.SupplierEntity;


@Component
public class SupplierEntityConverter {
	
	public static SupplierDTO toDTO(SupplierEntity suppliers) {
		return new SupplierDTO(suppliers.getId(),suppliers.getCnpj(), suppliers.getName(), suppliers.getLastBuyDate(), suppliers.getQualityRate(), suppliers.getProbabilityNewDeals(), false);
	}
	
	public SupplierEntity toEntity(SupplierDTO sup) {
		System.out.println("toEntity: " + sup);
		return new SupplierEntity(sup.getId(), sup.getCnpj(), sup.getName(), sup.getLastBuyDate(), sup.getQualityRate(), 
				sup.getProbabilityNewDeals(), false);
	}

}

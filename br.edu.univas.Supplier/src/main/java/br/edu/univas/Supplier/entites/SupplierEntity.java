package br.edu.univas.Supplier.entites;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class SupplierEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cnpj;
	private String name;
	private Date lastBuyDate;
	private float qualityRate;
	private float probabilityNewDeals;
	private boolean active;
	

}

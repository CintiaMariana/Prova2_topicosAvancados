package br.edu.univas.Supplier.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SupplierNewDTO {
	
	private Integer id;
	private long cnpj;
	private String name;
	private Date lastBuyDate;
	private float qualityRate;
	private float probabilityNewDeals;
	private boolean active;

	}



package br.edu.univas.Supplier.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class SupplierDTO {
	private Integer cnpj;
	private String name;
	private Date lastBuyDate;
	private float qualityRate;
	private float probabilityNewDeals;

}

package br.edu.univas.Supplier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.univas.Supplier.entites.SupplierEntity;
import br.edu.univas.Supplier.repositories.SupplierRepository;

@SpringBootApplication
public class CintiaMarianaTestApplication implements CommandLineRunner{
	
	@Autowired
	private SupplierRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(CintiaMarianaTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SupplierEntity s1 = new SupplierEntity();
		
		s1.setId(1);
		s1.setCnpj(20415295003785l);
		s1.setName("Dom Pedro");
		s1.setLastBuyDate(new Date());
		s1.setQualityRate(10f);
		s1.setProbabilityNewDeals(10f);
		s1.setActive(true);
        
		repo.save(s1);
		
		
		
		List<SupplierEntity> suppliers = repo.findAll();
		System.out.println(suppliers);
		
	}
	
	

}

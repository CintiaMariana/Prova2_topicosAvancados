package br.edu.univas.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		// TODO Auto-generated method stub
		
	}

}

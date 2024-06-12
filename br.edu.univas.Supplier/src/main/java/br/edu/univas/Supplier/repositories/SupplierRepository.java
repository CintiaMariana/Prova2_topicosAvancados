package br.edu.univas.Supplier.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.edu.univas.Supplier.entites.SupplierEntity;


@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {
	
	public List<SupplierEntity> findByActive(Boolean active);

}

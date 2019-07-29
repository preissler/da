package com.da.persistance.repository;


import com.da.persistance.model.db.Product;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;


public interface ProductRepository extends CrudRepository<Product, UUID> {}

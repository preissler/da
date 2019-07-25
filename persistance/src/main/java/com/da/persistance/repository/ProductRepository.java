package com.da.persistance.repository;

import com.da.persistance.model.db.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, String> {
}

package com.da.persistance.common.repository;

import com.da.persistance.common.model.db.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, String> {
}

package com.da.persistence.common.repository;

import com.da.persistence.common.model.db.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, String> {
}

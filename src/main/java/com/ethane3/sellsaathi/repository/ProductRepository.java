package com.ethane3.sellsaathi.repository;

import com.ethane3.sellsaathi.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Products,Long> {
}

package com.ethane3.sellsaathi.service;

import com.ethane3.sellsaathi.entity.Products;
import com.ethane3.sellsaathi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Products> findAll(){
        return productRepository.findAll();
    }

    public Products findById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public void save(Products products){
        productRepository.save(products);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }
}

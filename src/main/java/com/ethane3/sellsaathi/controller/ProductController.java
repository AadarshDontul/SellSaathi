package com.ethane3.sellsaathi.controller;

import com.ethane3.sellsaathi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    public String listProducts(Model model){
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }
}

package com.ethane3.sellsaathi.controller;

import com.ethane3.sellsaathi.entity.Products;
import com.ethane3.sellsaathi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public String listProducts(Model model){
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    @GetMapping("/create")
    public String createProductForm(Model model) {
        model.addAttribute("newProduct", new Products()); //binding Products Entity object in view template
        return "product/create"; // Make sure this path matches the file location
    }

    @PostMapping("/create") // To pass product object and add to Database
    public String createProduct(
            @ModelAttribute Products product,
            @RequestParam("file") MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            byte[] imageBytes = file.getBytes();
            product.setImages(imageBytes);

        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/image/{id}") // To show Images on List Webpage
    @ResponseBody
    public byte[] getImage(@PathVariable Long id){
        Products product = productService.findById(id);
        return product.getImages();
    }

    @GetMapping("/delete/{id}") // To delete the Product from List Webpage
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}") //Rendering the Edit Product Web page and, we pass Product object for current ID in model attribute
    public String editProductForm(
            @PathVariable Long id,
            Model model){
        Products products = productService.findById(id);
        model.addAttribute("editProduct", products);
        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(
            @ModelAttribute Products product,
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Products existingProduct = productService.findById(id);

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setQuantity(product.getQuantity());

        if(!file.isEmpty()){
            existingProduct.setImages(file.getBytes());
        }

        productService.save(existingProduct);
        return "redirect:/products";
    }




}









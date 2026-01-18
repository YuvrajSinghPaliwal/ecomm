package com.app.ecomm.controller;

import java.util.List;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.dto.ProductDto;
import com.app.ecomm.entity.Product;
import com.app.ecomm.entity.Seller;
import com.app.ecomm.service.ProductService;
import com.app.ecomm.service.SellerService;

@RestController
@RequestMapping("/sellers/products")
public class SellerProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SellerService sellerService;
	
	 // ✅ Fix: Specific path for create
    @PostMapping("/create")  // This is correct ✅
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto product, Authentication auth){
        Seller seller = (Seller) auth.getPrincipal();
        return productService.createProduct(product, seller);
    }
    
    // ✅ Fix: Use specific path to avoid conflict
    @GetMapping("/seller/{sellerId}")  // Changed from /{id}
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> getProductsBySellerId(@PathVariable Long sellerId){
        return productService.getProductsbySellerId(sellerId);
    }

	
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable Long id){
		productService.deleteProduct(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){
		return productService.updateProduct(id,product);
	}
	
	
	
}

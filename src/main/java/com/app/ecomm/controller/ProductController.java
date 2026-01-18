package com.app.ecomm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.entity.Product;
import com.app.ecomm.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired 
	private ProductService productService;
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId){
		return productService.findProductById(productId);
	}
	@GetMapping("/")
	public ResponseEntity<List<Product>> getProducts(){
		return productService.getProducts();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> search(@RequestParam(required = false) String query){
		return productService.searchProducts(query);
	}
	
	@GetMapping("/findProducts")
	public Page<Product> findProducts(
			@RequestParam(required = false) String Category,
			@RequestParam(required = false) String brand,
			@RequestParam(required = false) String colors,
			@RequestParam(required = false) String sizes,
			@RequestParam(required = false) Integer minPrice,
			@RequestParam(required = false) Integer maxPrice,
			@RequestParam(required = false) Integer minProduct,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String stock,
			@RequestParam(defaultValue = "0") Integer pageNumber
			){
		return productService.getAllProducts(Category, brand, colors, sizes, minPrice, maxPrice, minProduct, sort, stock, pageNumber);
	}
	
}

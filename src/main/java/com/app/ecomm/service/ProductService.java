package com.app.ecomm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.ecomm.dto.ProductDto;
import com.app.ecomm.entity.Category;
import com.app.ecomm.entity.Product;
import com.app.ecomm.entity.Seller;
import com.app.ecomm.repo.CategoryRepo;
import com.app.ecomm.repo.ProductRepo;

import jakarta.persistence.criteria.*;



@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	public ResponseEntity<Product> createProduct(ProductDto productDto, Seller seller) {
	    Product p = new Product();
	    
	    // 🔥 Fix Category Logic
	    Optional<Category> categoryOpt = categoryRepo.findByName(productDto.getCategory());
	    Category category;
	    if (categoryOpt.isPresent()) {
	        category = categoryOpt.get();
	    } else {
	        category = new Category();
	        category.setName(productDto.getCategory());
	        category = categoryRepo.save(category);
	    }
	    
	    p.setTitle(productDto.getTitle());  // 🔥 MISSING!
	    p.setCategory(category);
	    p.setSeller(seller);
	    p.setDescription(productDto.getDescription());
	    p.setCreatedAt(LocalDate.now());
	    p.setColor(productDto.getColor());
	    p.setMrpPrice(productDto.getMrpPrice());
	    p.setSellingPrice(productDto.getSellingPrice());
	    p.setImages(productDto.getImages());
	    p.setSizes(productDto.getSizes());
	    
	    // 🔥 Fix discount calculation
	    if (productDto.getMrpPrice() > 0) {
	        p.setDiscountPercentage((int) ((productDto.getMrpPrice() - productDto.getSellingPrice()) 
	            * 100.0 / productDto.getMrpPrice()));
	    }
	    
	    Product savedProduct = productRepo.save(p);
	    return ResponseEntity.ok(savedProduct);  // 🔥 Return saved product!
	}

	
	public void deleteProduct(Long productId) {
		Optional<Product> product=productRepo.findById(productId);
		productRepo.delete(product.get());
	}
	
    public ResponseEntity<Product> updateProduct(Long productId,Product product){
		productRepo.findProductById(productId);
		product.setId(productId);
		return new ResponseEntity<Product>(productRepo.save(product),HttpStatus.OK);
	}
    
    public ResponseEntity<Product> findProductById(Long productId){
    	Optional<Product> product=productRepo.findById(productId);
    	if(product.isEmpty()) {
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity(product.get(),HttpStatus.OK);
    }
    public ResponseEntity<List<Product>> getProducts(){
    	return new ResponseEntity(productRepo.findAll(),HttpStatus.OK);
    }
    public Page<Product> getAllProducts(String category,
    		String brand, String colors, String sizes,
    		Integer minPrice,Integer maxPrice,Integer minDiscount ,
    		String sort, String stock ,Integer pageNumber){
    	
    	Specification<Product> spec=(root,query,criteriaBuilder)->{
    		List<Predicate> predicates=new ArrayList<>();
    		
    		if(category!=null) {
    			Join<Product,Category> categoryJoin=root.join("category");
    			predicates.add(criteriaBuilder.equal(categoryJoin.get("id") , category));
    			
    		}
    		if(colors!=null) {
    			predicates.add(criteriaBuilder.equal(root.get("color"), colors));
    		}
    		if(sizes!=null) {
    			predicates.add(criteriaBuilder.equal(root.get("sizes")  , sizes));
    		}
    		if(minPrice!=null) {
    			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
    		}
    		if(maxPrice!=null) {
    			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
    		}
    		if(minDiscount!=null) {
    			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercentage"), minDiscount));
    		}
    		if(stock!=null) {
    			predicates.add(criteriaBuilder.equal(root.get("quantity"), stock));
    		}
    		
    		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    	};
    	
    	Pageable pageable;
    	
    	if(sort!=null) {
    		pageable=switch(sort) {
    		case "price_low" ->PageRequest.of(pageNumber!=null? pageNumber:0, 10, Sort.by("sellingPrice").ascending());
    			
    			
    		case "price_high" ->PageRequest.of(pageNumber!=null? pageNumber:0, 10, Sort.by("sellingPrice").descending());
    			
    			default -> PageRequest.of(pageNumber!=null? pageNumber:0, 10, Sort.unsorted());
    			
    		};
    	}
    		
    		else {
    			pageable = PageRequest.of(pageNumber!=null? pageNumber:0, 10, Sort.unsorted());
    		}
    	    
		
    	
    	return productRepo.findAll(spec,pageable);
    }
    
    public ResponseEntity<List<Product>> getProductsbySellerId(Long sellerId){
    	return new ResponseEntity(productRepo.findBySellerUserId(sellerId),HttpStatus.OK);
    }
    
    public ResponseEntity<List<Product>> searchProducts(String query){
    	return new ResponseEntity(productRepo.searchProduct(query),HttpStatus.OK);
    }
    
}

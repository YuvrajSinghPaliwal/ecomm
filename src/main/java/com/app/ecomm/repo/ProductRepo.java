package com.app.ecomm.repo;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.ecomm.entity.Address;
import com.app.ecomm.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product>{

    Product findProductById(Long productId);

    @Query("SELECT p FROM Product p WHERE p.seller.id = :sellerId")  // ✅ Changed userId to id
    List<Product> findBySellerUserId(@Param("sellerId") Long sellerId);
	
	@Query("SELECT p FROM Product p WHERE " +
		       "(:query IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
		       "(:query IS NULL OR LOWER(p.category.name) LIKE LOWER(CONCAT('%', :query, '%')))")
		List<Product> searchProduct(@Param("query") String query);


}

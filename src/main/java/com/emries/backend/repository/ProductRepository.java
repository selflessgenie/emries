package com.emries.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emries.backend.modal.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("from Product where category_id = :categoryId")
	List<Product> findByCategoryId(@Param("categoryId") Long categoryId);
	
	@Query("from Product where product_id = :productId and category_id = :categoryId")
	Product findByProductIdAndCategoryId(@Param("productId") Long productId, @Param("categoryId") Long categoryId);
	
	@Modifying
	@Query(value = "insert into Product (count, is_blocked, marked_price, name, selling_price, time_created, category_id) "
			+ "values (:count, :isBlocked, :marketPrice, :name, :sellingPrice, :timeCreated, :categoryId)", nativeQuery = true)
	void saveProduct(@Param("count") Long count, @Param("isBlocked") Boolean isBlocked, @Param("marketPrice") Double marketPrice,
			@Param("name") String name, @Param("sellingPrice") Double sellingPrice, @Param("timeCreated") Long timeCreated, @Param("categoryId") Long categoryId);
}

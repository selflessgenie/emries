package com.emries.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emries.backend.modal.Category;
import com.emries.backend.modal.Product;
import com.emries.backend.repository.CategoryRepository;
import com.emries.backend.repository.ProductRepository;

@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/emries/products")
public class HomePageAPIs {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getCategoryList(){
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	@GetMapping("/category")
	public ResponseEntity<Optional<Category>> getCategory(@RequestParam Long categoryId){
		return ResponseEntity.ok(categoryRepository.findById(categoryId));
	}
	
	@PostMapping("/category")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		category.setTimeCreated(System.currentTimeMillis());
		return ResponseEntity.ok(categoryRepository.save(category));
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<List<Product>> getProductList(@PathVariable Long categoryId){
		return ResponseEntity.ok(productRepository.findByCategoryId(categoryId));
	}
	
	@GetMapping("/{categoryId}/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Long categoryId, @PathVariable Long productId){
		return ResponseEntity.ok(productRepository.findByProductIdAndCategoryId(productId, categoryId));
	}
	
	@PostMapping("/{categoryId}")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Long count = product.getCount();
		Boolean isBlocked = product.getIsBlocked();
		Double marketPrice = product.getMarkedPrice();
		String name = product.getName();
		Double sellingPrice = product.getSellingPrice();
		Long timeCreated = System.currentTimeMillis();
		Long categoryId = product.getCategoryId();
		
		try {
			productRepository.saveProduct(count, isBlocked, marketPrice, name, sellingPrice, timeCreated, categoryId);
		}
		catch(Exception e) {
			return new ResponseEntity<Product>(product, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
	}
	
}

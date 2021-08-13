package com.emries.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emries.backend.modal.Category;
import com.emries.backend.repository.CategoryRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/emries/homepage")
public class HomePageAPIs {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getCategoryList(){
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		category.setTimeCreated(System.currentTimeMillis());
		return ResponseEntity.ok(categoryRepository.save(category));
	}
}

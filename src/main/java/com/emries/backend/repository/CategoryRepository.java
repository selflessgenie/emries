package com.emries.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emries.backend.modal.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	//public List<Category> findAllBy();
}

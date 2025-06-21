package com.java.business.HeadOffice.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.business.HeadOffice.entity.Category;
import com.java.business.HeadOffice.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Iterable<Category> listAll(){
		return this.repo.findAllByOrderByDesc();	}
	
	public void Save(Category category) {
		repo.save(category);
	}


	public Category getUserById(long id) {
		return repo.findById(id).get();
	}

	public void update(Category category, long productid) {
		repo.save(category);
	}


////////delete
	public void deleteMemberById(Long id) {
		repo.deleteById(id);
		
	}

	
	///////edit
	public void save(Category existingCategory) {
		repo.save(existingCategory);
	}

	public Category findById(Long id) {
		return repo.findById(id).get();
	}

	public List<Map<String, Object>> findAllProductsByCategory(){
		return repo.getAllProductsByCategory();
	}
	
	public List<Map<String, Object>> findAllProductsByCategory1(){
		return repo.getAllProductsByCategory1();
	}

	
}
	


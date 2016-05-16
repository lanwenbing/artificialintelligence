package com.artificialintelligence.machinelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.CategoryDao;
import com.artificialintelligence.machinelearning.model.CategoryModel;
import com.artificialintelligence.machinelearning.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryModel> getCategory() {
		
		List<CategoryModel> categoryList = categoryDao.getCategory();
		return categoryList;
	}
	
	public CategoryModel queryCategory(Integer id) {
		
		CategoryModel category = categoryDao.queryCategory(id);
		return category;
	}


}

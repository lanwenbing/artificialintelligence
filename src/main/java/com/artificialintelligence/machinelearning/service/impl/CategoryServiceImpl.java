package com.artificialintelligence.machinelearning.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.CategoryDao;
import com.artificialintelligence.machinelearning.service.CategoryService;
import com.artificialintelligence.model.machinelearning.CategoryModel;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryModel> getCategory(String lang) {
		
		List<CategoryModel> categoryList = categoryDao.getCategory(lang);
		return categoryList;
	}
	
	public CategoryModel queryCategory(Integer id, String lang) {
		
		CategoryModel category = categoryDao.queryCategory(id, lang);
		return category;
	}


}

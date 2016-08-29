package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.model.machinelearning.CategoryModel;

public interface CategoryService {

	public List<CategoryModel> getCategory(String lang);
	public CategoryModel queryCategory(Integer id, String lang);
}

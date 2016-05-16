package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.machinelearning.model.CategoryModel;

public interface CategoryService {

	public List<CategoryModel> getCategory();
	public CategoryModel queryCategory(Integer id);
}

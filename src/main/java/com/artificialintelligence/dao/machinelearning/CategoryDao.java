package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.machinelearning.model.CategoryModel;

public interface CategoryDao extends BaseDao{

	public List<CategoryModel> getCategory();
	public CategoryModel queryCategory(int id);
	
}

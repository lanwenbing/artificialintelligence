package com.artificialintelligence.dao.core;

import java.util.List;

import com.artificialintelligence.dao.machinelearning.BaseDao;
import com.artificialintelligence.model.core.CommentModel;

public interface CommentDao extends BaseDao{

	public List<CommentModel> queryComments(int commentType, int commentTypeId);
	public boolean insertComment(String comment, int commentType, int commentTypeId);
}

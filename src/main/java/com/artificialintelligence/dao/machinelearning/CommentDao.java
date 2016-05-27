package com.artificialintelligence.dao.machinelearning;

import java.util.List;

import com.artificialintelligence.machinelearning.model.CommentModel;

public interface CommentDao extends BaseDao{

	public List<CommentModel> queryComments(int commentType, int commentTypeId);
	public boolean insertComment(String comment, int commentType, int commentTypeId);
}
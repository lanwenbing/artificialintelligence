package com.artificialintelligence.machinelearning.service;

import java.util.List;

import com.artificialintelligence.machinelearning.model.CommentModel;

public interface CommentService {

	public List<CommentModel> queryComments(int commentType, int commentTypeId);
	public boolean insertComment(String comment, int commentType, int commentTypeId);

}

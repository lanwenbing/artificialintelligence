package com.artificialintelligence.core.service;

import java.util.List;

import com.artificialintelligence.model.core.CommentModel;

public interface CommentService {

	public List<CommentModel> queryComments(int commentType, int commentTypeId);
	public boolean insertComment(String comment, int commentType, int commentTypeId);

}

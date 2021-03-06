package com.artificialintelligence.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.core.service.CommentService;
import com.artificialintelligence.dao.core.CommentDao;
import com.artificialintelligence.model.core.CommentModel;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDao commentDao;

	public List<CommentModel> queryComments(int commentType, int commentTypeId) {
		
		List<CommentModel> list = new ArrayList<CommentModel>();
		list = commentDao.queryComments(commentType, commentTypeId);
		
		return list;
	}

	public boolean insertComment(String comment, int commentType, int commentTypeId) {
		
		commentDao.insertComment(comment, commentType, commentTypeId);
		
		return true;
	}

}

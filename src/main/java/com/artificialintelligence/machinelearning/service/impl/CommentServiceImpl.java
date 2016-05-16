package com.artificialintelligence.machinelearning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificialintelligence.dao.machinelearning.CommentDao;
import com.artificialintelligence.machinelearning.model.CommentModel;
import com.artificialintelligence.machinelearning.service.CommentService;

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

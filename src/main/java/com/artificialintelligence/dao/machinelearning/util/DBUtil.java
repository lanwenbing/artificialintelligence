package com.artificialintelligence.dao.machinelearning.util;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DBUtil {
    
	
    private static SqlSession sqlSession; 
    
    private static final Log log = LogFactory.getLog(DBUtil.class);
	
	public static Connection getConnection(){    
        Connection conn = null;    
        try {   
        	ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
    		sqlSession = (SqlSession)context.getBean("sqlSession");
            conn =    sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();    
            log.info("===This Connection isClosed ? "+conn.isClosed());    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        return conn;    
    } 
	
	
}

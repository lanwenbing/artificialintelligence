package com.artificialintelligence.dao.machinelearning.util;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.artificialintelligence.report.util.ReportUtil;

public class DBUtil {

    @Autowired    
    private static SqlSession sqlSession; 
    
    private static final Log log = LogFactory.getLog(ReportUtil.class);
	
	public static Connection getConnection(){    
        Connection conn = null;    
        try {    
            conn =  sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();    
            log.info("===This Connection isClosed ? "+conn.isClosed());    
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        return conn;    
    }  
}

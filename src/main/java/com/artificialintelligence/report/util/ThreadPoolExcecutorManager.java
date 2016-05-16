package com.artificialintelligence.report.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExcecutorManager extends java.util.concurrent.ThreadPoolExecutor {
	static final int poolSize = 5;
	static final int maxPoolSize = 5;
	static final long keepAliveTime = 10;
	public static final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
	private static ThreadPoolExcecutorManager instance;

    private ThreadPoolExcecutorManager() {
    	super(poolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, queue);
	}
    
    public synchronized static ThreadPoolExcecutorManager getInstance() {
    	if (instance == null) {
    		instance = new ThreadPoolExcecutorManager();
    	}
    	return instance;
    }

	public void runTask(Runnable task) {
		 instance.execute(task);
	}

	public void shutDown() {
		instance.shutdown();
	}

}

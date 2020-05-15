package com.leaf.plugin.util;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author siufung
 *
 */
public class ThreadPoolUtil {
	
	private  static ExecutorService executor ;
	
	public static ExecutorService getThreadPool(){
		if(executor == null) {
			synchronized (ThreadPoolUtil.class) {
				if(executor == null) {
					executor = Executors.newFixedThreadPool(1000);
				}
			}
		}
		return  executor;
	}
	
}

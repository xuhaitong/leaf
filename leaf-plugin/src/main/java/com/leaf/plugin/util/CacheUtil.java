package com.leaf.plugin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

public class CacheUtil {
	
	public static final String USER_CACHE = "userCache";
	public static final String MENU_CACHE = "menuCache"; 
	public static final String DICT_CACHE = "dictCache";
	public static final String PARAM_CACHE = "paramCache";
	public static final String CLICKRATE_CACHE = "clickRateCache";

	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * get
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Object get(String cacheName,String key) {
		return cacheManager.getCache(cacheName).get(key).get();
	}
	
	/**
	 * 往缓存中放
	 * @param cacheName
	 * @param key
	 * @param object
	 */
	public void put(String cacheName,String key,Object value) {
		cacheManager.getCache(cacheName).put(key, value);
	}
	
	/**
	 * remove
	 * @param cacheName
	 * @param key
	 */
	public void remove(String cacheName,String key) {
		cacheManager.getCache(cacheName).put(key, null);
	}

	/**
	 * get
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Object getValue(String cacheName,String key) {

		if(cacheManager.getCache(cacheName).get(key) != null){
			cacheManager.getCache(cacheName).get(key);
			cacheManager.getCache(cacheName).get(key).get();
			return cacheManager.getCache(cacheName).get(key).get();
		}else{
			return null;
		}
	}
	
}

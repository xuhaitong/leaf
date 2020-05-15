package com.leaf.plugin.util;

public class ReflectionUtils {

	/**
	 * 判断一个类是否是基础类和包装类
	 * @param cls
	 * @return
	 */
	public static boolean isPrimitive(Class<? extends Object> cls){
		if(cls.isPrimitive()){
			return true;
		}
		if(cls.equals(Long.class)
		 ||cls.equals(Byte.class)
		 ||cls.equals(Integer.class)
		 ||cls.equals(Boolean.class)
		 ||cls.equals(String.class)
		 ||cls.equals(Double.class)
		 ||cls.equals(Short.class)
		 ||cls.equals(Character.class)){
			return true;
		}
		return false;
	}
}

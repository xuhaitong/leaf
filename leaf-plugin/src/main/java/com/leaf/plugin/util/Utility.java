package com.leaf.plugin.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
	/**
	 *  转换成有效的字符串
	* @Title: toSafeString
	* @Description: TODO
	* @param @param obj
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	 public static String toSafeString(Object obj) {
	    try {
	      if ((obj == null) || (obj.equals(null))) {
	        return "";
	      }
	        return obj.toString().trim().replace(">", "").replace("<", "");
	    }
	    catch (Exception e) {}
	    return "";
	 }
	 
	/**
	 * 是否包含  
	* @Title: contains
	* @Description: TODO
	* @param @param a
	* @param @param b
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean contains(String a, String b) {
	    try {
	      a = "," + toSafeString(a) + ",";
	      b = "," + toSafeString(b) + ",";
	      return a.contains(b);
	    }
	    catch (Exception e) {}
	    return false;
	}
	
	/**
	 * 是否是int类型的
	* @Title: isInteger
	* @Description: TODO
	* @param @param str
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean isInteger(Object str) {
	    try {
	      Integer.parseInt(toSafeString(str));
	      return true;
	    }
	    catch (Exception err) {}
	    return false;
	}
	
	/**
	 * 转换成安全的int 不是int返回-1
	* @Title: toSafeInt
	* @Description: TODO
	* @param @param obj
	* @param @return    参数
	* @return int    返回类型
	* @throws
	 */
	public static int toSafeInt(Object obj) {
	    if (isInteger(obj)) {
	      return Integer.parseInt(toSafeString(obj));
	    }
	    return -1;
	}
	
	/**
	 * 转换成安全的int 不是int返回-1
	* @Title: toSafeInt
	* @Description: TODO
	* @param @param obj
	* @param @param nInitValue
	* @param @return    参数
	* @return int    返回类型
	* @throws
	 */
	public static int toSafeInt(Object obj, int nInitValue) {
	    int nResult = toSafeInt(obj);
	    if (nResult == -1) {
	      nResult = nInitValue;
	    }
	    return nResult;
	}
	
	/**
	 * 转换成安全的long
	* @Title: toSafeLong
	* @Description: TODO
	* @param @param obj
	* @param @return    参数
	* @return long    返回类型
	* @throws
	 */
	public static long toSafeLong(Object obj){
	    long num = 0L;
	    try {
	      num = Long.parseLong(toSafeString(obj));
	    }
	    catch (Exception localException) {}
	    return num;
	}
	
	/**
	 * 转换成安全的short
	* @Title: toSafeShort
	* @Description: TODO
	* @param @param obj
	* @param @return    参数
	* @return long    返回类型
	* @throws
	 */
	public static long toSafeShort(Object obj) {
	    short num = 0;
	    try {
	      num = Short.parseShort(toSafeString(obj));
	    }
	    catch (Exception localException) {}
	    return num;
	}
	
	/**
	 * 转换成安全的float
	* @Title: toSafeFloat
	* @Description: TODO
	* @param @param obj
	* @param @return    参数
	* @return float    返回类型
	* @throws
	 */
	public static float toSafeFloat(Object obj) {
	    float num = 0.0F;
	    try {
	      num = Float.parseFloat(toSafeString(obj));
	    }
	    catch (Exception localException) {}
	    return num;
	}
	
	/**
	 * 转换成安全的double
	* @Title: toSafeDouble
	* @Description: TODO
	* @param @param obj
	* @param @return    参数
	* @return double    返回类型
	* @throws
	 */
	public static double toSafeDouble(Object obj) {
	    double num = 0.0D;
	    try {
	      num = Double.parseDouble(toSafeString(obj));
	    }
	    catch (Exception localException) {}
	    return num;
	}
	
	/**
	 * 转换成安全的时间格式
	* @Title: toSafeDateTime
	* @Description: TODO
	* @param @param objDate
	* @param @return    参数
	* @return Date    返回类型
	* @throws
	 */
	public static Date toSafeDateTime(Object objDate) {
	    Date date = new Date();
	    try {
	      String strDate = toSafeString(objDate);
	      if (strDate.indexOf(":") > 0) {
	        SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        date = formatDateTime.parse(strDate);
	      } else {
	        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	        date = formatDate.parse(strDate);
	      }
	    }
	    catch (Exception localException) {}
	    return date;
	}
	
	/**
	 * 转换成安全的boolean
	* @Title: toSafeBool
	* @Description: TODO
	* @param @param obj
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean toSafeBool(Object obj) {
	    return toSafeString(obj).equalsIgnoreCase("true");
	}
	
	/**
	 * 替换
	* @Title: replace
	* @Description: TODO
	* @param @param a
	* @param @param b
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	public static String replace(String a, String b) {
	    if (StringUtils.isEmpty(a)) {
	      return a;
	    }
	    a = "," + a + ",";
	    b = "," + b + ",";
	    a = a.replace(b, ",");
	    if (",".equals(a)) {
	      return "";
	    }
	    a = a.substring(1);
	    a = a.substring(0, a.length() - 1);
	    return a;
	}
	 
	
	/**
	 *  toUtf8 
	* @Title: toUtf8
	* @Description: TODO
	* @param @param str
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	public static String toUtf8(String str) {
	    str = toSafeString(str);
	    try {
	      String encodType = getEncoding(str);
	      if (!encodType.equalsIgnoreCase("GB2312")) {
	        str = new String(str.getBytes("ISO8859-1"), "UTF-8");
	      }
	    }
	    catch (UnsupportedEncodingException e)
	    {
	      return str;
	    }
	    return str;
	}
	  
	  /**
	   * 获取编码
	  * @Title: getEncoding
	  * @Description: TODO
	  * @param @param str
	  * @param @return    参数
	  * @return String    返回类型
	  * @throws
	   */
	public static String getEncoding(String str) {
	    String encode = "GB2312";
	    try {
	      if (str.equals(new String(str.getBytes(encode), encode))) {
	        return encode;
	      }
	    }
	    catch (Exception localException3) {
	      encode = "ISO-8859-1";
	      try {
	        if (str.equals(new String(str.getBytes(encode), encode))) {
	          return encode;
	        }
	      } catch (Exception localException) {
	        encode = "UTF-8";
	        try {
	          if (str.equals(new String(str.getBytes(encode), encode))) {
	            return encode;
	          }
	        } catch (Exception localException1) {
	          encode = "GBK";
	          try {
	            if (str.equals(new String(str.getBytes(encode), encode))) {
	              return encode;
	            }
	          } catch (Exception localException2) {}
	        }
	      }
	    }
	    return "";
	}
	  
	  
}

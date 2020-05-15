package com.leaf.plugin.util;


import java.math.BigDecimal;

/**
 * BigDecimal工具类
* @ClassName: DecimalUtils
* @Description:
* @author chenjianfeng
* @date 2018年5月30日
 */
public class DecimalUtils {
	
	private static final int DEF_DIV_SCALE = 6;
	
	/**
	 * 减法
	* @Title: sub
	* @Description: TODO
	* @param @param v1
	* @param @param v2
	* @param @return    参数
	* @return double    返回类型
	* @throws
	 */
	public static double sub(double v1, double v2) {
	    BigDecimal b1 = new BigDecimal(Double.toString(v1));
	    BigDecimal b2 = new BigDecimal(Double.toString(v2));
	    return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 加法
	* @Title: add
	* @Description: TODO
	* @param @param v1
	* @param @param v2
	* @param @return    参数
	* @return double    返回类型
	* @throws
	 */
	public static double add(double v1, double v2) {
	    BigDecimal b1 = new BigDecimal(Double.toString(v1));
	    BigDecimal b2 = new BigDecimal(Double.toString(v2));
	    return b1.add(b2).doubleValue();
	}
	
	/**
	 * 乘法
	* @Title: mul
	* @Description: TODO
	* @param @param v1
	* @param @param v2
	* @param @return    参数
	* @return double    返回类型
	* @throws
	 */
	public static double mul(double v1, double v2) {
	    BigDecimal b1 = new BigDecimal(Double.toString(v1));
	    BigDecimal b2 = new BigDecimal(Double.toString(v2));
	    return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 除法
	* @Title: div
	* @Description: TODO
	* @param @param v1
	* @param @param v2
	* @param @return    参数
	* @return double    返回类型
	* @throws
	 */
	public static double div(double v1, double v2) {
	    return div(v1, v2, DEF_DIV_SCALE);
	}
	
	/**
	 * 出发 保留小数点
	* @Title: div
	* @Description: TODO
	* @param @param v1
	* @param @param v2
	* @param @param scale
	* @param @return    参数
	* @return double    返回类型
	* @throws
	 */
	public static double div(double v1, double v2, int scale) {
	    if (scale < 0) {
	      throw new IllegalArgumentException("The scale must be a positive integer or zero");
	    }
	    BigDecimal b1 = new BigDecimal(Double.toString(v1));
	    BigDecimal b2 = new BigDecimal(Double.toString(v2));
	    return b1.divide(b2, scale, 4).doubleValue();
	}
	
}

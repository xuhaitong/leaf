package com.leaf.log.constant;

/**
 * 语义化日志常量类
 * @author Leaf Xu
 *
 */
public class LeafLogConstant {
	
	/**
	 * 操作状态 -成功 1 ，失败 0 ，异常 2 
	 */
	public static final String OPERATE_STATUS_SUCCESS = "1";
	public static final String OPERATE_STATUS_FAIL = "0";
	public static final String OPERATE_STATUS_ERROR = "2";
	
	public static final String OPERATE_STATUS_MSG_FAIL = "失败：";
	public static final String OPERATE_STATUS_MSG_SUCCESS = "成功";
	public static final String OPERATE_STATUS_MSG_ERROR = "异常：";

	public static final String OPERATE_TITLE_FORMAT = "用户 [{1}({0})] 执行了 {2} 操作";
	
	public static final String OPERATE_TYPE_INDEX_ZERO = "0";
}

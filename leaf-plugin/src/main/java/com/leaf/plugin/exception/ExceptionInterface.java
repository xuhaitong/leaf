package com.leaf.plugin.exception;

/**
 * 错误码接口
 *
 * Created by siufung on 13/03/2017.
 */
public interface ExceptionInterface {
	
	/**
	 * 
	* @Title: getCode
	* @return    参数
	* @return String    返回类型
	* @throws
	 */
    String getCode();
    
    /**
     * 
    * @Title: getMessage
    * @return    参数
    * @return String    返回类型
    * @throws
     */
    String getMessage();
}

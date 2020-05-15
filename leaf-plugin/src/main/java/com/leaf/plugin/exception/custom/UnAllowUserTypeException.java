package com.leaf.plugin.exception.custom;

/**
 * 不允许登录的用户类型
 * @author Leaf Xu
 *
 */
public class UnAllowUserTypeException extends CustomException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnAllowUserTypeException(String message) {
		super(message);
	}
}

package com.leaf.plugin.exception;

/**
 * 统一错误码异常
 * Created by siufung on 14/03/2017.
 */
public class GlobalErrorException extends Exception {
	
	/**
	 * 错误对象信息
	 */
    private ExceptionInterface errorInfo;
    
    
    public GlobalErrorException (ExceptionInterface errorInfo) {
        this.errorInfo = errorInfo;
    }
    
    public ExceptionInterface getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ExceptionInterface errorInfo) {
        this.errorInfo = errorInfo;
    }
}

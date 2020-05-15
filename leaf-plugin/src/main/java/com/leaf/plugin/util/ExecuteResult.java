package com.leaf.plugin.util;

import java.io.Serializable;

import com.leaf.plugin.exception.Exceptions;

/**
 * 
 * @author siufung
 *
 * @param <T>
 */
public class ExecuteResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4398792451408794749L;

	/**
	 * success
	 */
	private boolean success;

	/**
	 * T <>
	 */
	private T data;

	/**
	 * message
	 */
	private String message;

	/**
	 * code 错误码
	 */
	private String code;

	/**
	 * url
	 * 
	 * @return
	 */
	private String url;

	/**
	 * errorStackTrack
	 */
	private String errorStackTrack;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getErrorStackTrack() {
		return errorStackTrack;
	}

	public void setErrorStackTrack(String errorStackTrack) {
		this.errorStackTrack = errorStackTrack;
	}

	/**
	 * 
	 * @Title: ok @param @param data @param @return @return ExecuteResult<T> @throws
	 */
	public static <T> ExecuteResult<T> ok(T data) {
		ExecuteResult<T> result = new ExecuteResult<T>();
		result.setData(data);
		result.setSuccess(true);
		result.setCode("200");
		result.setMessage("");
		return result;
	}

	/**
	 * 
	 * @param message
	 * @return
	 */
	public static <T> ExecuteResult<T> ok(String message) {
		ExecuteResult<T> result = new ExecuteResult<T>();
		result.setData(null);
		result.setSuccess(true);
		result.setCode("200");
		result.setMessage(message);
		return result;
	}

	/**
	 * 
	 * @Title: ok @param @param data @param @param message @param @return @return
	 * ExecuteResult<T> @throws
	 */
	public static <T> ExecuteResult<T> ok(T data, String message) {
		ExecuteResult<T> result = new ExecuteResult<T>();
		result.setData(data);
		result.setSuccess(true);
		result.setCode("200");
		result.setMessage(message);
		return result;
	}

	/**
	 * 
	 * @Title: ok @param @return @return ExecuteResult<T> @throws
	 */
	public static <T> ExecuteResult<T> ok() {
		return ok(null);
	}

	/**
	 * 
	 * @Title: failure @param @return @return ExecuteResult<T> @throws
	 */
	public static <T> ExecuteResult<T> fail() {
		return fail(null);
	}

	/**
	 * 
	 * @Title: failure @param @param data @param @return @return
	 * ExecuteResult<T> @throws
	 */
	public static <T> ExecuteResult<T> fail(T data) {
		ExecuteResult<T> result = new ExecuteResult<T>();
		result.setData(data);
		result.setSuccess(false);
		return result;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static <T> ExecuteResult<T> fail(String message) {
		ExecuteResult<T> result = new ExecuteResult<T>();
		result.setData(null);
		result.setSuccess(false);
		result.setMessage(message);
		return result;
	}

	/**
	 * 
	 * @Title: failure @param @param message @param @param
	 * data @param @return @return ExecuteResult<T> @throws
	 */
	public static <T> ExecuteResult<T> fail(String errorcode, String message) {
		ExecuteResult<T> result = new ExecuteResult<T>();
		result.setMessage(message);
		result.setCode(errorcode);
		result.setSuccess(false);
		return result;
	}

	public static <T> ExecuteResult<T> fail(String errorcode, String message, Exception e) {
		ExecuteResult<T> result = new ExecuteResult<T>();
		result.setMessage(message);
		result.setSuccess(false);
		result.setCode(errorcode);
		result.setErrorStackTrack(Exceptions.getStackTraceAsString(e));
		return result;
	}

	public static <T> ExecuteResult<T> fail(String errorcode, String message, Exception e, String url) {
		ExecuteResult<T> result = new ExecuteResult<T>();
		result.setMessage(message);
		result.setSuccess(false);
		result.setCode(errorcode);
		result.setErrorStackTrack(Exceptions.getStackTraceAsString(e));
		result.setUrl(url);
		return result;
	}

	/**
	 * 
	 * @Title: failure @param @param errorCode @param @param message @param @param
	 * data @param @return @return ExecuteResult<T> @throws
	 */
	public static <T> ExecuteResult<T> fail(String errorCode, String message, T data) {
		ExecuteResult<T> result = new ExecuteResult<T>();
		result.setCode(errorCode);
		result.setMessage(message);
		result.setData(data);
		result.setSuccess(false);
		return result;
	}

	public void setError(String string) {
		// TODO Auto-generated method stub
		setMessage(string);
		setSuccess(false);
	}

}

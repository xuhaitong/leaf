package com.leaf.plugin.exception;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.leaf.plugin.exception.custom.ForbidenUserException;
import com.leaf.plugin.exception.custom.UnAllowUserTypeException;
import com.leaf.plugin.util.ExecuteResult;

/**
 * GlobalExceptionHandler
 * @author siufung
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
		
	/*@Autowired
	private MessageSource messageSource;*/
	
	/**
	 * 
	 * @param request
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public ExecuteResult<String> defaultRestErrorHandler(HttpServletRequest request,Exception ex) throws Exception {
		ExecuteResult<String> result=new ExecuteResult<String>();
		result.setCode(GlobalExceptionEnum.RUNTIME_EXCEPTION.getCode());
        result.setMessage(GlobalExceptionEnum.RUNTIME_EXCEPTION.getMessage());
        result.setSuccess(false);
        result.setUrl(request.getRequestURI());
        result.setData(Exceptions.getStackTraceAsString(ex));
        return result;
	}
	
	/**
	 * 
	 * @param request
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = GlobalErrorException.class)
    public ExecuteResult<String> serviceServiceExceptionHandler(HttpServletRequest request, GlobalErrorException ex) throws Exception {
		ExecuteResult<String> result=new ExecuteResult<String>();
        result.setCode(GlobalExceptionEnum.RUNTIME_EXCEPTION.getCode());
        result.setMessage(GlobalExceptionEnum.RUNTIME_EXCEPTION.getMessage());
        result.setSuccess(false);
        result.setUrl(request.getRequestURI());
        result.setData(Exceptions.getStackTraceAsString(ex));
        return result;
	}
	
	//运行时异常
	@ExceptionHandler(value=RuntimeException.class)  
	public ExecuteResult<String> runtimeExceptionHandler(RuntimeException ex,Locale locale) {
	    return ExecuteResult.fail(GlobalExceptionEnum.RUNTIME_EXCEPTION.getCode(), GlobalExceptionEnum.RUNTIME_EXCEPTION.getMessage(),ex);  
	} 
	
	//空指针异常
    @ExceptionHandler(NullPointerException.class)
    public ExecuteResult<String> nullPointerExceptionHandler(NullPointerException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.NULL_POINTER_EXCEPTION.getCode(), GlobalExceptionEnum.NULL_POINTER_EXCEPTION.getMessage(), ex);
    }
    
    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public ExecuteResult<String> classCastExceptionHandler(ClassCastException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.CLASS_CAST_EXCEPTION.getCode(), GlobalExceptionEnum.CLASS_CAST_EXCEPTION.getMessage(), ex);
    }
    
    //IO异常
    @ExceptionHandler(IOException.class)
    public ExecuteResult<String> iOExceptionHandler(IOException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.IO_EXCEPTION.getCode(), GlobalExceptionEnum.IO_EXCEPTION.getMessage(), ex);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public ExecuteResult<String> noSuchMethodExceptionHandler(NoSuchMethodException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.NO_SUCH_METHOD_EXCEPTION.getCode(), GlobalExceptionEnum.NO_SUCH_METHOD_EXCEPTION.getMessage(), ex);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ExecuteResult<String> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.INDEX_OUT_OF_BOUNDS_EXCEPTION.getCode(), GlobalExceptionEnum.INDEX_OUT_OF_BOUNDS_EXCEPTION.getMessage(), ex);
    }

    //网络异常
    @ExceptionHandler(ConnectException.class)
    public ExecuteResult<String> connectException(ConnectException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.CONNECT_EXCEPTION.getCode(), GlobalExceptionEnum.CONNECT_EXCEPTION.getMessage(), ex);
    }
    
  //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ExecuteResult<String> requestNotReadable(HttpMessageNotReadableException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.BAD_REQUEST.getCode(), GlobalExceptionEnum.BAD_REQUEST.getMessage(), ex);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public ExecuteResult<String> requestTypeMismatch(TypeMismatchException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.BAD_REQUEST.getCode(), GlobalExceptionEnum.BAD_REQUEST.getMessage(), ex);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ExecuteResult<String> requestMissingServletRequest(MissingServletRequestParameterException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.BAD_REQUEST.getCode(), GlobalExceptionEnum.BAD_REQUEST.getMessage(), ex);
    }

    @ExceptionHandler({ ServletException.class })
    public ExecuteResult<String> http404(ServletException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.NOT_FOUND_REQUEST.getCode(), GlobalExceptionEnum.NOT_FOUND_REQUEST.getMessage(), ex);
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ExecuteResult<String> request405(HttpRequestMethodNotSupportedException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.METHOD_NOT_ALLOWED.getCode(), GlobalExceptionEnum.METHOD_NOT_ALLOWED.getMessage(), ex);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ExecuteResult<String> request406(HttpMediaTypeNotAcceptableException ex,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.NOT_ACCEPTABLE.getCode(), GlobalExceptionEnum.NOT_ACCEPTABLE.getMessage(), ex);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public ExecuteResult<String> server500(RuntimeException runtimeException,Locale locale) {
        return ExecuteResult.fail(GlobalExceptionEnum.INTERNAL_SERVER_ERROR.getCode(), GlobalExceptionEnum.INTERNAL_SERVER_ERROR.getMessage(), runtimeException);
    } 

    
		
	/**
	 * 401 未授权
	 * @param request
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = AccessDeniedException.class)
    public ExecuteResult<Object> serviceAccessDeniedExceptionHandler(AccessDeniedException ex,Locale locale){
        return ExecuteResult.fail(
        		GlobalExceptionEnum.NOT_AUTHORIZATION.getCode(),
        		GlobalExceptionEnum.NOT_AUTHORIZATION.getMessage(),
        		ex
		);
	}
	
	
	/**
	 * 4011 用户名密码错误
	 * @param ex
	 * @param locale
	 * @return
	 */
	@ExceptionHandler(value = BadCredentialsException.class)
    public ExecuteResult<Object> serviceBadCredentialsExceptionHandler(BadCredentialsException ex,Locale locale){
        return ExecuteResult.fail(
        		GlobalExceptionEnum.WRONG_USERNAME_PASSWORD.getCode(),
        		GlobalExceptionEnum.WRONG_USERNAME_PASSWORD.getMessage(),
        		ex
		);
	}
	
	/**
	 * 4012 不被允许的用户类型
	 * @param ex
	 * @param locale
	 * @return
	 */
	@ExceptionHandler(value = UnAllowUserTypeException.class)
    public ExecuteResult<Object> serviceUnAllowUserTypeExceptionHandler(UnAllowUserTypeException ex,Locale locale){
        return ExecuteResult.fail(
        		GlobalExceptionEnum.UN_ALLOW_USER_TYPE.getCode(),
        		GlobalExceptionEnum.UN_ALLOW_USER_TYPE.getMessage(),
        		ex
		);
	}
	
	/**
	 * 4015 用户禁用
	 * @param ex
	 * @param locale
	 * @return
	 */
	@ExceptionHandler(value = ForbidenUserException.class)
    public ExecuteResult<Object> serviceForbidenUserExceptionHandler(ForbidenUserException ex,Locale locale){
        return ExecuteResult.fail(
        		GlobalExceptionEnum.FORBIDEN_USER.getCode(),
        		GlobalExceptionEnum.FORBIDEN_USER.getMessage(),
        		ex
		);
	}


}

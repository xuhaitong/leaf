package com.leaf.plugin.exception;

/**
 * 错误对找码
 * @author siufung
 *
 */
public enum ExceptionEnum {
	
    BAD_REQUEST("400", "Bad Request!"),
    NOT_AUTHORIZATION("401", "[服务器]没有访问权限"),
    NOT_FOUND_REQUEST("404", "[服务器]未找到资源"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
    NOT_ACCEPTABLE("406", "Not Acceptable"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),

    LOGIN_FIRST("999", "[服务器]请登录"),
    

    RUNTIME_EXCEPTION("1000", "[服务器]运行时异常"),
    NULL_POINTER_EXCEPTION("1001", "[服务器]空值异常"),
    CLASS_CAST_EXCEPTION("1002", "[服务器]数据类型转换异常"),
    IO_EXCEPTION("1003", "[服务器]IO异常"),
    NO_SUCH_METHOD_EXCEPTION("1004", "[服务器]未知方法异常"),
    INDEX_OUT_OF_BOUNDS_EXCEPTION("1005", "[服务器]数组越界异常"),
    CONNECT_EXCEPTION("1006", "[服务器]网络异常"),
    ERROR_MEDIA_TYPE("1007", "[服务器]Content-type错误，请使用application/json"),
    EMPTY_REQUEST_BOYD("1008", "[服务器]request请求body不能为空"),
    ERROR_REQUEST_BOYD("1009", "[服务器]request请求body非json对象"),
    ERROR_VERSION("2000", "[服务器]版本号错误"),
    ERROR_FORMAT_PARAMETER("2001", "[服务器]参数格式错误");



    private final String code;
    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getNameByValue(String val) {
        if (val != null) {
            String value = val;
            for (ExceptionEnum constant : ExceptionEnum.values()) {
                if (constant.code == value) {
                    return constant.msg;
                }
            }
        }
        return "";
    }

    public ExceptionEnum getTypeByValue(String value) {
        for (ExceptionEnum constant : ExceptionEnum.values()) {
            if (constant.code == value) {
                return constant;
            }
        }
        return null;
    }

}

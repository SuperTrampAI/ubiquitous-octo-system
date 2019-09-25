package com.supertrampai.myutils.util;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = -3032015199552656978L;
    private String code;
    private String msg;
    private T data;

	public Result(String code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}


	public static <T> Result<T> create(String code, String message) {
        return new Result(code, message, (Object)null);
    }

    public static <T> Result<T> create(String code, String message, T data) {
        return new Result(code, message, data);
    }

    public static <T> Result<T> successData(String message, T data) {
        return create("0", message, data);
    }

    public static <T> Result<T> successMessage(String message) {
        return (Result<T>) successData(message, (Object)null);
    }

    public static <T> Result<T> successData(T data) {
        return successData((String)null, data);
    }

    public static <T> Result<T> success() {
        return successMessage((String)null);
    }

    public static <T> Result<T> errorData(String message, T data) {
        return create("1", message, data);
    }

    public static <T> Result<T> error() {
        return errorMessage((String)null);
    }

    public static <T> Result<T> errorData(T data) {
        return errorData((String)null, data);
    }

    public static <T> Result<T> errorMessage(String message) {
        return (Result<T>) errorData(message, (Object)null);
    }

    public static <T> Result<T> selectiveMessage(boolean success, String successMessage, String errorMessage) {
        return success ? successMessage(successMessage) : errorMessage(errorMessage);
    }
    
 
}

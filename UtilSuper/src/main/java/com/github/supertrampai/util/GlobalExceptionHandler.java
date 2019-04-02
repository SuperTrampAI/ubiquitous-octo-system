package com.github.supertrampai.util;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

 

@ControllerAdvice
@Component
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Resp handle(ConstraintViolationException exception) {
    	StringBuffer msg=new StringBuffer();
        if(exception instanceof ConstraintViolationException){
        	for(ConstraintViolation<?> e:exception.getConstraintViolations()) {
        		msg.append(e.getPropertyPath()).append(e.getMessage()).append(";");
        	}
            return new Resp(1,msg.toString()) ;
        }
        return new Resp(1,"出错了") ;
    }
    
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Resp handle(MissingServletRequestParameterException exception) {
        return new Resp(1,exception.getMessage()) ;
    }
    
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Resp methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getAllErrors().stream().map(objectError -> {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                return errorMessage(fieldError);
            }
            return objectError.getDefaultMessage();
        }).reduce((a, b) -> a + "; " + b).orElse("");
        return new Resp(1,errorMsg);
    }


    private String errorMessage(FieldError fieldError) {
        String message = fieldError.getDefaultMessage();
        if ("不能为空".equals(message)) {
            message = fieldError.getField() + message;
        }
        if ("不能为null".equals(message)) {
            message = fieldError.getField() + message;
        }
        if(message.startsWith("Failed to convert")) {
            message = "无效参数"+fieldError.getField()  ;
        }
        
        return message;
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public Resp httpMessageNotReadableExceptionHandler(MethodArgumentTypeMismatchException e) {
        return new Resp(1, "请求参数有误" );
    }

	@ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public Resp bindExceptionHandler(HttpMessageNotReadableException e) {
        return new Resp(1, "请求参数有误" );}
    
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public Resp<?> httpMessageNotReadableExceptionHandler(ServiceException e) {
		logger.error("Service调用异常", e);
		return new Resp<>(1, e.getMessage());
	}
	
	@ExceptionHandler({BindException.class})
    @ResponseBody
    public Resp bindExceptionHandler(BindException e) {
        String errorMsg = e.getBindingResult().getAllErrors().stream().map(objectError -> {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                return errorMessage(fieldError);
            }
            return objectError.getDefaultMessage();
        }).reduce((a, b) -> a + "; " + b).orElse("");
        return new Resp(1,errorMsg);
    }

	@ExceptionHandler(value = Exception.class)
    public Resp defaultErrorHandler( Exception e) throws Exception {
        return new Resp(1, "系统异常：" + e.getMessage());
    }
}
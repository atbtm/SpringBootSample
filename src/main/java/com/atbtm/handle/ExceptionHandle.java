package com.atbtm.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atbtm.domain.HttpResult;
import com.atbtm.enums.HttpResultEnum;
import com.atbtm.exception.PersonAgeException;
import com.atbtm.utils.HttpResultUtil;

@ControllerAdvice
public class ExceptionHandle {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public HttpResult handle(Exception e) {
		if(e instanceof PersonAgeException) {
			PersonAgeException pae = (PersonAgeException)e;
			return HttpResultUtil.failResult(pae.getMessage(), pae.getCode());
		}else {
			logger.error(" 【系统异常】{} ", e.toString());
			logger.error("Stacktrace: {}", e.getStackTrace());
			return HttpResultUtil.failResult(
					HttpResultEnum.UNKOWN_ERROR.getMsg(), 
					HttpResultEnum.UNKOWN_ERROR.getCode());
		}
	}
}

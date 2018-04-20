package com.atbtm.exception;

import com.atbtm.enums.HttpResultEnum;

public class PersonAgeException extends RuntimeException{
	private Integer code;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public PersonAgeException(HttpResultEnum hre) {
		super(hre.getMsg());
		this.code = hre.getCode();
	}
}

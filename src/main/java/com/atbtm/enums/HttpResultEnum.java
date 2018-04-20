package com.atbtm.enums;

public enum HttpResultEnum {
	UNKOWN_ERROR(-1, "未知错误"),
	SUCCESS(0, "成功"),
	PRIM_SCHOOL(99, "在上小学"),
	MID_SCHOOL(100, "在初中");
	
	private Integer code;
	private String msg;
	HttpResultEnum(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
}

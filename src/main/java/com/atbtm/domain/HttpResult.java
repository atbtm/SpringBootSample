package com.atbtm.domain;

import org.springframework.stereotype.Component;

@Component
public class HttpResult<T> {
	private String msg;
	private Integer returnCode;
	private T data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}

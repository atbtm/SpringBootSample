package com.atbtm.utils;

import com.atbtm.domain.HttpResult;

public class HttpResultUtil {
	public static HttpResult successResult(Object data) {
		HttpResult hr = new HttpResult();
		hr.setData(data);
		hr.setMsg("Http result success");
		hr.setReturnCode(0);
		return hr;
	}
	public static HttpResult failResult(String msg, Integer returnCode) {
		HttpResult hr = new HttpResult();
		//hr.setData(null);
		hr.setMsg(msg);
		hr.setReturnCode(returnCode);
		return hr;
	}
}

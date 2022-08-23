package com.ddip.common.enums;

public enum CommonEnum {

	SUCCESS("00","成功"),
	FAILED("01","系统繁忙，请稍后再试"),
	REQUEST_PARAM_ERROR("02","请求参数不能为空"),
	TOKEN_ERROR("03","token授权错误"),
	QUERY_FAIL("04","查询数据不能为空"),
	PERMISSION_DEFINE("05","权限不足"),
	NOT_LOGINED("06","请先登录"),
	LOGIN_ERROR("07","登录失败"),
	REQUEST_ERROR("08","非法请求");
	
	private String code;
	
	private String msg;

	private CommonEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

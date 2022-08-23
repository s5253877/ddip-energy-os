package com.ddip.common.response;

import com.ddip.common.context.ThreadContext;
import com.ddip.common.enums.CommonEnum;

import lombok.Data;

@Data
public class ResultModel<T> {

	/** 请求ID */
	private String requestId;
	
	/** 返回码 */
	private String code;
	
	/** 返回信息 */
	private String msg;
	
	/** 数据 */
	private T data;
 

	public static <T> ResultModel<T> success(){
		ResultModel<T> response = new ResultModel<T>();
		response.setRequestId(ThreadContext.getRequestId());
		response.setCode(CommonEnum.SUCCESS.getCode());
		response.setMsg(CommonEnum.SUCCESS.getMsg());
		return response;
	}
	
	public static <T> ResultModel<T> success(T data){
		ResultModel<T> response = new ResultModel<T>();
		response.setRequestId(ThreadContext.getRequestId());
		response.setCode(CommonEnum.SUCCESS.getCode());
		response.setMsg(CommonEnum.SUCCESS.getMsg());
		response.setData(data);
		return response;
	}
	
	public static <T> ResultModel<T> error(){
		ResultModel<T> response = new ResultModel<T>();
		response.setRequestId(ThreadContext.getRequestId());
		response.setCode(CommonEnum.FAILED.getCode());
		response.setMsg(CommonEnum.FAILED.getMsg());
		return response;
	}
	
	public static <T> ResultModel<T> error(String errorCode,String errorMsg){
		ResultModel<T> response = new ResultModel<T>();
		response.setRequestId(ThreadContext.getRequestId());
		response.setCode(errorCode);
		response.setMsg(errorMsg);
		return response;
	}
	
}

package com.ddip.common.context;

import com.ddip.pojo.ao.ApiAO;
import com.ddip.pojo.ao.UserInfoAO;

public class ThreadContext {

	/** 登录用户信息 */
	private static ThreadLocal<UserInfoAO> userLocal = new ThreadLocal<UserInfoAO>();
	
	/** api信息 */
	private static ThreadLocal<ApiAO> apiLocal = new ThreadLocal<ApiAO>();
	
	/** 授权token */
	private static ThreadLocal<String> tokenLocal = new ThreadLocal<String>();
	
	/** 请求ID */
	private static ThreadLocal<String> requestIdLocal = new ThreadLocal<String>();
	
	public static String getToken() {
		return tokenLocal.get();
	}
	
	public static void setToken(String token) {
		tokenLocal.set(token);
	}
	
	public static void removeToken() {
		tokenLocal.remove();
	} 
	
	public static String getRequestId() {
		return requestIdLocal.get();
	}
	
	public static void setRequestId(String requestId) {
		requestIdLocal.set(requestId);
	}
	
	public static void removeRequestId() {
		requestIdLocal.remove();
	} 	
	
	public static UserInfoAO getUserInfo() {
		return userLocal.get();
	}
	
	public static void setUserInfo(UserInfoAO userInfoAO) {
		userLocal.set(userInfoAO);
	}
	
	public static void removeUserInfo() {
		userLocal.remove();
	} 	
	
	public static void login(UserInfoAO userInfoAO,String token) {
		setUserInfo(userInfoAO);
		setToken(token);
	}
	
	public static void login(ApiAO apiAO,String token) {
		setApiAO(apiAO);
		setToken(token);
	}

	private static void setApiAO(ApiAO apiAO) {
		apiLocal.set(apiAO);		
	}
	
	private static ApiAO getApiAO() {
		return apiLocal.get();		
	}
}

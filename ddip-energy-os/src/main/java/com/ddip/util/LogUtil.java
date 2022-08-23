package com.ddip.util;

import org.springframework.util.StringUtils;

import com.ddip.common.context.ThreadContext;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class LogUtil {

	public static void info(String msg) {
		if(StringUtils.isEmpty(ThreadContext.getRequestId())) {
			log.info(msg);
		}else {
			log.info("["+ThreadContext.getRequestId()+"]"+msg);
		}
	}
	
	public static void info(String msg,Object... obj) {
		if(StringUtils.isEmpty(ThreadContext.getRequestId())) {
			log.info(msg,obj);
		}else {
			log.info("["+ThreadContext.getRequestId()+"]"+msg,obj);
		}
	}
	
	public static void error(String msg) {
		if(StringUtils.isEmpty(ThreadContext.getRequestId())) {
			log.error(msg);
		}else {
			log.error("["+ThreadContext.getRequestId()+"]"+msg);
		}
	}
	
	public static void error(String msg,Object... obj) {
		if(StringUtils.isEmpty(ThreadContext.getRequestId())) {
			log.error(msg,obj);
		}else {
			log.error("["+ThreadContext.getRequestId()+"]"+msg,obj);
		}
	}
}

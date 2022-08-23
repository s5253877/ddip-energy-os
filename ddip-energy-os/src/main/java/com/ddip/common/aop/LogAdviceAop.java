package com.ddip.common.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.ddip.util.LogUtil;

@Aspect
@Component
public class LogAdviceAop {
	
	ThreadLocal<Long> currentTime = new ThreadLocal<Long>();
	
	@Pointcut("execution(public * com.ddip.controller..*.*(..))")
	public void logAdvice() {

	}
	
	@Before("logAdvice()")
	public void doBefore(JoinPoint joinPoint) {
		currentTime.set(System.currentTimeMillis());
		LogUtil.info("####### controller begin #######");
		ServletRequestAttributes servletRequest = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequest.getRequest();
		LogUtil.info("======= request url :" + request.getRequestURI().toString());
		LogUtil.info("======= class method:" + joinPoint.getSignature().getDeclaringTypeName());
		LogUtil.info("======= request params:" + Arrays.toString(joinPoint.getArgs()));
	}
	
	@AfterReturning(pointcut="logAdvice()",returning = "rvt" )
	public void doAfterReturning(JoinPoint joinPoint,Object rvt) {
		LogUtil.info("======= response  :" + JSON.toJSONString(rvt));
		LogUtil.info("======= 接口耗时 :" + (System.currentTimeMillis()-currentTime.get())+"ms");
		LogUtil.info("####### controller end #######");
	}
}

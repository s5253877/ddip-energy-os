package com.ddip.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.ddip.common.constant.ParamKeys;
import com.ddip.common.constant.RedisKey;
import com.ddip.common.context.ThreadContext;
import com.ddip.common.enums.CommonEnum;
import com.ddip.common.response.ResultModel;
import com.ddip.pojo.ao.ApiAO;
import com.ddip.pojo.ao.UserInfoAO;
import com.ddip.util.LogUtil;

@Component
public class AuthFilter implements Filter{

	/** 登录URL */
	private String loginUrl = "/web/login";
	
	/** 内部接口URL */
	private String webUrl = "/web/";
	
	/** 外部接口URL */
	private String apiUrl = "/api/";
	
//	@Autowired
//	RedisTemplate<String, Object>  redisTemplate;
//	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		ThreadContext.setRequestId(UUID.randomUUID().toString());
		String url = request.getRequestURI();
		String method = request.getMethod();
		LogUtil.info("======= request url = {} ,method:{}",url,method);
		if(url.contains(loginUrl)) {
			filterChain.doFilter(request, response);
			return;	
		}else if(url.contains(webUrl)){
			validateLogin(request,response,filterChain);
			return;
		}else if(url.contains(apiUrl)){
			validateApi(request,response,filterChain);
			return;
		}else {
			printWrite(response,CommonEnum.REQUEST_ERROR);
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	@Override
	public void destroy() {
		
	}
	
	private void printWrite(ServletResponse response,CommonEnum commonEnum) throws IOException {
		PrintWriter writer = null;
		try {
			response.setContentType("text/json;charset=utf-8");
			writer = response.getWriter();
			writer.write(JSON.toJSONString(ResultModel.error(commonEnum.getCode(), commonEnum.getMsg())));
			writer.flush();
		} catch (Exception e) {
			LogUtil.error("loginFilter error:", e);
		}finally {
			if(writer!=null) {
				writer.close();
			}
		}
	}
	
	private void validateLogin(HttpServletRequest request,ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
		String token = request.getHeader(ParamKeys.TOKEN);
		if(StringUtils.isEmpty(token)) {
			printWrite(response, CommonEnum.TOKEN_ERROR);
			return;
		}
		// 获取登录用户信息
		UserInfoAO userInfoAO = null;
		// (UserInfoAO) redisTemplate.opsForValue().get(RedisKey.LOGIN_USER_INFO+token);
		if(userInfoAO==null) {
			printWrite(response, CommonEnum.NOT_LOGINED);
			return;
		}
		ThreadContext.login(userInfoAO, token);
		filterChain.doFilter(request, response);
	}

	private void validateApi(HttpServletRequest request,ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
		String token = request.getParameter(ParamKeys.TOKEN);
		if(StringUtils.isEmpty(token)) {
			printWrite(response, CommonEnum.TOKEN_ERROR);
			return;
		}
		//  redis中取
		ApiAO apiAO = null;
		// ApiAO apiAO =  (ApiAO) redisTemplate.opsForValue().get(RedisKey.API_INFO+token);
		if(apiAO==null) {
			printWrite(response, CommonEnum.TOKEN_ERROR);
			return;
		}
		ThreadContext.login(apiAO, token);
		filterChain.doFilter(request, response);
	}
}

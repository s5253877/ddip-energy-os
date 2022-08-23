//package com.ddip.common.config;
//
//import javax.annotation.Resource;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.ddip.common.filter.AuthFilter;
//
//@Configuration
//public class FilterConfig {
//
//	@Resource
//	private AuthFilter authFilter;
//	
//	@Bean
//	public FilterRegistrationBean<AuthFilter> registerCorsFilter(){
//		FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<AuthFilter>();
//		registration.setFilter(authFilter);
//		registration.addUrlPatterns("/*");
//		registration.setName("authFilter");
//		registration.setOrder(2);
//		return registration;
//	}
//}

package com.ddip.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	
	 /** 线程池最大值 */
	 private static final int MAX_POOL_SIZE = 50;
	 
	 /** 核心线程池最大值 */
	 private static final int CORE_POOL_SIZE = 20;
	 
	 @Bean("asyncTaskExecutor")
	 public AsyncTaskExecutor asyncTaskExecutor() {
		 ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
		 asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
		 asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
		 asyncTaskExecutor.setThreadNamePrefix("async-task-thread-pool-");
		 asyncTaskExecutor.initialize();
		 return asyncTaskExecutor;
	 }
}

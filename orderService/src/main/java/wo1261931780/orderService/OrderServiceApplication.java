/*
 * Author: junw 45444154+wo1261931780@users.noreply.github.com
 * Date: 2023-03-29 09:23:57
 * LastEditors: junw 45444154+wo1261931780@users.noreply.github.com
 * LastEditTime: 2023-04-01 01:48:01
 * FilePath: \st-springCloud\orderService\src\main\java\wo1261931780\orderService\OrderServiceApplication.java
 * Description: 1111
 *
 * Copyright (c) 2023 by ${git_name_email}, All Rights Reserved.
 */
package wo1261931780.orderService;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients(clients = FeignClient.class, defaultConfiguration = FeignClientConfig.class)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	// 一个服务，既可以是提供者，也可以是消费者
	// 如果是消费者，就需要调用其他服务的接口
	// 如果是提供者，就需要暴露接口
	// 消费者和提供者都是相对的概念

	/**
	 * 创建RestTemplate并注入Spring容器
	 * <p>
	 * 发送http请求的工具类
	 *
	 * @return RestTemplate
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		// 这个就是模拟前端，发送http请求的工具类
		// 使用这个工具类，来调用其他服务的接口
		// LoadBalanced注解，表示启动负载均衡
		return new RestTemplate();
	}

	@Bean
	public IRule iRules() {
		// 这个是自定义的负载均衡算法
		// 这个算法是随机的
		return new RandomRule();
	}

	// EnableFeignClients注解，表示开启Feign的功能
	// 首先要引入Feign的依赖
}

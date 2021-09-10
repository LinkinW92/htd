package com.skeqi.htd.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置
 *
 * @author linkin
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Configuration implements WebMvcConfigurer {

	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(buildApiInfo())
			.select()
			// 要扫描的API(Controller)基础包
			.apis(RequestHandlerSelectors.basePackage("com.skeqi.htd.controller"))
			.paths(PathSelectors.any())
			.build();
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder()
			.title("高驱进销存")
			.description("平台管理服务api")
			.contact("linkin")
			.version("1.0.0").build();
	}
}

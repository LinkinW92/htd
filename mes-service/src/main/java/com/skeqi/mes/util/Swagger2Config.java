//package com.skeqi.mes.util;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import io.swagger.annotations.ApiOperation;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration      //让Spring来加载该类配置
//@EnableWebMvc       //启用Mvc，非springboot框架需要引入注解@EnableWebMvc
//@EnableSwagger2     //启用Swagger2
//public class Swagger2Config {
//
//	  @Bean
//	    public Docket createRestApi() {
//	        return new Docket(DocumentationType.SWAGGER_2)
////	                .apiInfo(apiInfo()).select()
////	                //扫描指定包中的swagger注解
////	                .apis(RequestHandlerSelectors.basePackage("com.skeqi.mes.controller.project"))
////	                //扫描所有有注解的api，用这种方式更灵活
////	                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
////	                .paths(PathSelectors.any())
////	                .build();
//	        		.groupName("1")
//	        		.apiInfo(apiInfo("1", "1.0"))
//	        		.useDefaultResponseMessages(true)
//	        		.forCodeGeneration(false)
//	        		.select()
//	        		.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//	        		.paths(PathSelectors.any())
//	        		.build();
//	    }
//
//
//	    private ApiInfo apiInfo(String title,String version) {
//	        return new ApiInfoBuilder()
//	                .title(title)
//	                .description("基础平台 RESTful 风格的接口文档，内容详细，极大的减少了前后端的沟通成本，同时确保代码与文档保持高度一致，极大的减少维护文档的时间。")
//	                .version(version)
//	                .build();
//	    }
//
//}

package com.skeqi.framecore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

	@Value(value = "${fileName.pdfFile}")
	private String locationPath;
//	private String locationPath = "D:\\image\\"; // 3.文件本地路径
	private static final String netPath = "/image/**"; // 映射路径

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 目前在本地Win系统测试需要在本地路径前添加 "file:"
		// 有待确认Linux系统是否需要添加（已确认）
		// Linux系统也可以添加 "file:"
		registry.addResourceHandler(netPath).addResourceLocations("file:"+locationPath);
	}

}

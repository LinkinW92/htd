package com.skeqi.wms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/aaa")
public class TestController {
	
	@GetMapping("/asd")
	public String asd(HttpServletRequest request) {
		return request.getParameter("name");
	}

}

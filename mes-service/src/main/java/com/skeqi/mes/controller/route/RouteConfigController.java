package com.skeqi.mes.controller.route;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

@Controller
@RequestMapping("/")
public class RouteConfigController {

	@RequestMapping({"/menuConfig","menu"})
	public String menuConfig(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			String pwd = EqualsUtil.string(request, "pwd", "密码", true);
			if("skq123".equals(pwd)) {
				return "/menuConfig/index";
			}else {
				return "redirect:/menuConfig/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return "redirect:/menuConfig/error";
		}

	}

	@RequestMapping("/menuConfig/error")
	@ResponseBody
	public Rjson error() {
		return new Rjson().error("密码错误");
	}

	@RequestMapping("/menuConfig/error500")
	@ResponseBody
	public Rjson error500() {
		return new Rjson().error("系统异常");
	}

}

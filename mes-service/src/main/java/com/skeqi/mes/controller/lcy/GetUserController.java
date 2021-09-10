//package com.skeqi.mes.controller.lcy;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.alibaba.fastjson.JSONObject;
//import com.skeqi.mes.common.lcy.Encryption;
//import com.skeqi.mes.pojo.CMesUserT;
//import com.skeqi.mes.util.InsertRegistry;
//import com.skeqi.mes.util.Version;
//
//@RequestMapping("skq")
//@Controller
//public class GetUserController {
//
//	public static CMesUserT GetUser() {
//		// 获取user
//		Subject subject = SecurityUtils.getSubject();
//		CMesUserT user = (CMesUserT) subject.getSession().getAttribute("user");
//		return user;
//	}
//
//	@RequestMapping("sessionConversation")
//	@ResponseBody
//	public JSONObject getSessionConversation() {
//		Subject subject = SecurityUtils.getSubject();
//		JSONObject jo = new JSONObject();
//		if (!subject.isAuthenticated()) {
//			jo.put("value", true);
//		} else {
//			jo.put("menuData", subject.getSession().getAttribute("menuData"));
//			jo.put("menuModuleData", subject.getSession().getAttribute("menuModuleData"));
//			jo.put("value", false);
//		}
//		Version version = new Version();
//		jo.put("version", version.getVersion());
//		return jo;
//	}
//
//	@RequestMapping("setMenuData")
//	@ResponseBody
//	public JSONObject setMenuData(HttpServletRequest request) {
//		Subject subject = SecurityUtils.getSubject();
//		JSONObject jo = new JSONObject();
//		String menuData = request.getParameter("menuData");
//		String menuModuleData = request.getParameter("menuModuleData");
//
//		subject.getSession().setAttribute("menuData", menuData);
//		subject.getSession().setAttribute("menuModuleData", menuModuleData);
//		if (!subject.isAuthenticated()) {
//			jo.put("value", true);
//		} else {
//			jo.put("value", false);
//		}
//		Version version = new Version();
//		jo.put("version", version.getVersion());
//		return jo;
//	}
//
//	@RequestMapping("getLogoutSomeValue")
//	@ResponseBody
//	public JSONObject getLogoutSomeValue() {
//		Subject subject = SecurityUtils.getSubject();
//		JSONObject jo = new JSONObject();
//		if (subject.isAuthenticated()) {
//			subject.logout();
//			jo.put("value", true);
//		} else {
//			jo.put("value", false);
//		}
//		return jo;
//	}
//
//	@RequestMapping("getUserId")
//	@ResponseBody
//	public JSONObject getUserId() {
//		Subject subject = SecurityUtils.getSubject();
//		JSONObject jo = new JSONObject();
//
//		jo.put("user", subject.getSession().getAttribute("user"));
//		return jo;
//	}
//
//	@ResponseBody
//	@RequestMapping("getLoginSomeValue")
//	public JSONObject getLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		String userName = request.getParameter("username");
//		String passWord = request.getParameter("password");
//		JSONObject jo = new JSONObject();
//		if (userName == "" || userName == null) {
//			jo.put("value", "用户名为空");
//		}
//		if (passWord == "" || passWord == null) {
//			jo.put("value", "密码为空");
//		}
//
////		JSONObject registry = InsertRegistry.findRegistry();
////		Object object = registry.get("code");
////		Object object1 = registry.get("msg");
////		if (Integer.parseInt(object.toString()) == 1) {
////			jo.put("flag", 5);
////			jo.put("msg", object1.toString());
////			return jo;
////		}
//
//		// 获取shiro中的subject对象，该对象封装了登陆
//		Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()) {
//			// 调用token
//			UsernamePasswordToken token = new UsernamePasswordToken(userName,
//					Encryption.getPassWord(passWord + userName + 666666 + passWord, 555));
//			// 缓存登陆
//			token.setRememberMe(true);
//			try {
//				// 登陆的方法
//				subject.login(token);
//				jo.put("flag", subject.isAuthenticated());
//				jo.put("value", "登录成功");
//			} catch (UnknownAccountException e) {
//				// 账号不存在
//				jo.put("flag", subject.isAuthenticated());
//				jo.put("value", "用户账号不存在");
//			} catch (IncorrectCredentialsException e) {
//				// 密码不匹配
//				jo.put("flag", subject.isAuthenticated());
//				jo.put("value", "用户密码输入不正确");
//			} catch (AuthenticationException e) {
//				jo.put("flag", subject.isAuthenticated());
//				jo.put("value", "验证失败");
//			}
//		}
//		return jo;
//	}
//}

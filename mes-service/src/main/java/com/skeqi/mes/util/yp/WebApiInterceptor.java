package com.skeqi.mes.util.yp;//package com.skeqi.mes.util.yp;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//import java.lang.reflect.Method;
//
//public class WebApiInterceptor extends HandlerInterceptorAdapter {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		if (!(handler instanceof HandlerMethod)) {
//			return true;
//		}
//		HandlerMethod handlerMethod = (HandlerMethod) handler;
//		Method method = handlerMethod.getMethod();
//
//		String sessionId = request.getSession().getId();
//		if (sessionId == null) {
//
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("application/json; charset=utf-8");
//			PrintWriter out = null;
//			JSONObject res = new JSONObject();
//			res.put("resultCode", 302);
//			res.put("message", "sessionId 已失效");
//			out = response.getWriter();
//			out.append(res.toString());
//			return false;
//		}
//
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
//			ModelAndView modelAndView) throws Exception {
//
//	}
//
//	// 方法执行之后拦截
//	@Override
//	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//			Object o, Exception e) throws Exception {
//		System.out.println("========方法执行之后 开始调用===============");
//	}
//}

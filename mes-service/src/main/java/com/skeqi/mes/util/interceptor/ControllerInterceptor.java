package com.skeqi.mes.util.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.chenj.CMesApiDictionariesService;
import com.skeqi.mes.util.SimpleDateFormatCache;
import com.skeqi.mes.util.StringUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class ControllerInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private CMesApiDictionariesService cMesApiDictionariesService;

	/**
	 * 在Controller方法前进行拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);

			sb.append("-----------------------").append(SimpleDateFormatCache.getYmdhms().format(new Date()))
				.append("-------------------------------------\n");
			HandlerMethod h = (HandlerMethod) handler;
			sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
			sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
			String params = getParamString(request.getParameterMap());
//            System.err.println("请求参数:"+params);
//			if(params == null || "".equals(params)) {
//				params = getRawString(request);
//			}
			HttpSession session = request.getSession();
			session.setAttribute("commitType", request.getMethod().toUpperCase());
//			session.setAttribute("params", getFormatInfo(request, session));
//			String sessionParams = String.valueOf(session.getAttribute("params"));
//            System.err.println("params解析后存储的参数"+sessionParams);
			String paramsType = String.valueOf(session.getAttribute("paramsType"));
			sb.append("Params    : ").append(params).append("\n");
			sb.append("Url       : ").append(request.getRequestURI()).append("\n");
			sb.append("请求类型       : ").append(request.getContentType()).append("\n");
			System.out.println(sb.toString());
		}

		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);
			sb.append("CostTime  : ").append(executeTime).append("ms").append("\n");
			sb.append("-------------------------------------------------------------------------------");
			System.out.println(sb.toString());
		}
//		String params = getParamString(request.getParameterMap());

	}

	/**
	 * 在Controller方法后进行拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
		throws Exception {
		// System.out.println("ControllerInterceptor.afterCompletion()");

	}


	private String getParamString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> e : map.entrySet()) {
			sb.append(e.getKey()).append("=");
			String[] value = e.getValue();
			if (value != null && value.length == 1) {
				sb.append(value[0]).append("\t");
			} else {
				sb.append(Arrays.toString(value)).append("   ");
			}
		}
		return sb.toString();
	}

	/**
	 * 表单参数数据拼接=》(form)
	 *
	 * @param map
	 * @return
	 */
	private String getParamStringAppendForm(Map<String, String[]> map) {
		if (map.size() < 1) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> e : map.entrySet()) {
			sb.append(e.getKey()).append("=");
			String[] value = e.getValue();
			if (value != null && value.length == 1) {
				sb.append("".equals(value[0]) ? "null" : '"' + value[0] + '"').append(" ");
			} else {
				sb.append(Arrays.toString(value)).append("   ");
			}


		}
		return sb.substring(0, sb.lastIndexOf(" "));
	}


	/**
	 * 表单参数数据拼接=》(json)
	 *
	 * @param request
	 * @return
	 */
//	private JSONObject getParamStringAppendJson(HttpServletRequest request) throws IOException {
//		JSONObject requestJsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
//		return requestJsonObject;
//	}


	/**
	 * 格式转换
	 * text/html： HTML格式
	 * text/plain：纯文本格式
	 * image/jpeg：jpg图片格式
	 * application/json： JSON数据格式
	 * application/x-www-form-urlencoded： form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据格式）
	 * multipart/form-data： 在表单中进行文件上传时使用
	 *
	 * @param request
	 * @return
	 */
//	private String getFormatInfo(HttpServletRequest request, HttpSession session) throws Exception {
//		String contentType = request.getContentType();
//		Map<String, String[]> map = request.getParameterMap();
//		String params = getParamString(map);
//		// 导出功能未指定类型，所以默认null
//		if (null == contentType || contentType.contains("application/x-www-form-urlencoded")) {
//			session.setAttribute("paramsType", "FORM");
//			String apiAnnotation = request.getParameter("apiAnnotation");
//			request.removeAttribute("apiAnnotation");
//			session.setAttribute("apiAnnotation", apiAnnotation);
//			return getParamStringAppendForm(map);
//
//		} else if (contentType.contains("application/json")) {
//			// 预防Swagger接口文档方式请求数据，这里进行二次校验
//			if (!"".equals(params)) {
//				session.setAttribute("paramsType", "FORM");
//				String apiAnnotation = request.getParameter("apiAnnotation");
//				request.removeAttribute("apiAnnotation");
//				session.setAttribute("apiAnnotation", apiAnnotation);
//				return getParamStringAppendForm(map);
//			} else {
//				session.setAttribute("paramsType", "JSON");
//				JSONObject paramStringAppendJson = getParamStringAppendJson(request);
//				// 获取body请求体中数据为[{}]直接返回存储
//				if (null != paramStringAppendJson.get("list")) {
//					return paramStringAppendJson.get("list").toString();
//				} else if (null != paramStringAppendJson.get("params")) {
//					return paramStringAppendJson.get("params").toString();
//				}
//
//				if (!StringUtil.eqNu(paramStringAppendJson)) {
//					return null;
//				}
//				session.setAttribute("apiAnnotation", paramStringAppendJson.get("apiAnnotation"));
//				paramStringAppendJson.remove("apiAnnotation");
//				return paramStringAppendJson.toJSONString();
//			}
//
//		} else if (contentType.contains("image/jpeg")) {
//			return "";
//
//		} else if (contentType.contains("text/plain")) {
//			return "";
//
//
//		} else if (contentType.contains("text/html")) {
//			return "";
//
//		} else if (contentType.contains("multipart/form-data")) {
//			session.setAttribute("paramsType", "FORM");
//			String apiAnnotation = request.getParameter("apiAnnotation");
//			session.setAttribute("apiAnnotation", apiAnnotation);
//			return getParamStringAppendForm(map);
//		}
//		return "未知";
//	}


	private String getRawString(HttpServletRequest request) throws Exception {
		InputStream ris = request.getInputStream();
		Reader reader = new InputStreamReader(ris, "UTF-8");
		BufferedReader streamReader = new BufferedReader(reader);
		StringBuilder responseStrBuilder = new StringBuilder();
		String inputStr;
		while ((inputStr = streamReader.readLine()) != null)
			responseStrBuilder.append(inputStr);

		JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Object> e : jsonObject.entrySet()) {
			sb.append(e.getKey()).append("=");
			Object value = e.getValue();
			if (value != null) {
				sb.append(value.toString()).append("   ");
			}
		}
//		streamReader.close();
//		reader.close();
//		streamReader = null;
//		reader = null;
		return sb.toString();
//		return IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());
	}
}

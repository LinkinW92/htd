//package com.skeqi.mes.util.yp;
//
//import java.io.IOException;
//import java.util.Objects;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import com.skeqi.mes.util.interceptor.BodyReaderHttpServletRequestWrapper;
//import org.springframework.stereotype.Component;
//
//import com.skeqi.mes.mapper.qh.CQhAuthorityDao;
//import com.skeqi.mes.pojo.qh.CQhRoleT;
//
//@Component
//public class CheckFilter implements Filter {
//
//    CQhAuthorityDao dao = (CQhAuthorityDao) ApplicationContextUtil.getApplicationContext().getBean("cQhAuthorityDao");
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("加载拦截器");
//    }
//
//    /**
//     * 过滤后的处理操作
//     */
//    @Override
//    public void doFilter(ServletRequest r, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
////		System.out.println("进入拦截器");
//        HttpServletRequest request = (HttpServletRequest) r;
//        if ("application/json".equals(request.getContentType()) || "application/json;charset=UTF-8".equals(request.getContentType())) {
//            // 重写request.getInputStream()和getReader()，实现重复读取body参数
//            request = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) r);
//        }
//
////
////		String servletPath = request.getServletPath().toString();
////
////		try {
////
////			// 判断请求是否是不拦截的请求
////			if (!checkOpenInterface(servletPath)) {
////				String token = request.getHeader("token");
////				// 校验token
////				checkToken(token, servletPath);
////			}
////
////			System.out.println("结束拦截");
////			// 对请求进行处理
//        chain.doFilter(request, response);
////			System.out.println("结束拦截============================");
////
////		} catch (Exception e) {
////			response.setCharacterEncoding("UTF-8");
////			response.setContentType("application/json; charset=utf-8");
////			PrintWriter out = response.getWriter();
////			e.printStackTrace();
////			System.out.println("结束拦截");
////			out.append(new Rjson().error(500,e.getMessage()).toString());
////		}
//
//    }
//
//    /**
//     * 校验token及权限 通过正常返回 未通过抛异常
//     *
//     * @param token
//     * @throws ServletException
//     */
//    private void checkToken(String token, String path) throws Exception {
//        if (Objects.isNull(token)) {
//            throw new Exception("未登录");
//        }
//        // 通过token查询用户角色
//        CQhRoleT role = dao.queryRoleByToken(token);
//        if (Objects.isNull(role)) {
//            throw new Exception("用户token失效");
//        }
//        // 按角色id和路径查询是否有权限
//        Integer count = dao.queryPermissionByRoleIdAndPath(role.getId(), path);
//        if (count == 0) {
//            throw new Exception("对不起,您无操作权限");
//        }
//    }
//
//    /**
//     * 判断请求是否是不拦截的请求 是返回true 否返回false
//     *
//     * @param servletPath
//     * @return
//     */
//    private boolean checkOpenInterface(String servletPath) {
//        for (String path : OpenInterface.getOPEN_INTERFACE_URL()) {
//            if (path.equals(servletPath)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("销毁拦截器");
//    }
//}

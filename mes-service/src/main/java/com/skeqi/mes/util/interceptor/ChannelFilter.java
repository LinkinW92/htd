package com.skeqi.mes.util.interceptor;//package com.skeqi.mes.util.interceptor;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @author ChenJian
// * @version 1.0
// * @date 2021/3/29
// * @Classname ChannelFilter
// * @Description 拦截所有请求并将流进行复制处理
// * @since
// */
//
//@WebFilter(urlPatterns = "/*",filterName = "channelFilter")
//public class ChannelFilter  implements Filter{
//    private Logger logger = LoggerFactory.getLogger(ChannelFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        logger.info("================进入过滤器======================");
//        // 防止流读取一次后就没有了, 所以需要将流继续写出去
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
//        filterChain.doFilter(requestWrapper, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
//

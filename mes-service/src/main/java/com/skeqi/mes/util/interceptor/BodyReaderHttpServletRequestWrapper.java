//package com.skeqi.mes.util.interceptor;
//
//import org.springframework.util.StreamUtils;
//
//import javax.servlet.ReadListener;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.io.*;
//
///**
// * @author ChenJian
// * @version 1.0
// * @date 2021/3/29
// * @Classname BodyReaderHttpServletRequestWrapper
// * @Description 重写request.getInputStream()
// * @since
// */
//public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
//    //保存流
//    private byte[] requestBody = null;
//
//    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
//        super(request);
//        requestBody = StreamUtils.copyToByteArray(request.getInputStream());
//    }
//
//
//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//
//        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
//
//        return new ServletInputStream() {
//
////            @Override
//            public int read() throws IOException {
//                return bais.read();
//            }
//
////            @Override
//            public boolean isFinished() {
//                return false;
//            }
//
////            @Override
//            public boolean isReady() {
//                return false;
//            }
//
////            @Override
//            public void setReadListener(ReadListener readListener) {
//
//            }
//
//        };
//    }
//
//
//    @Override
//    public BufferedReader getReader() throws IOException {
//        return new BufferedReader(new InputStreamReader(getInputStream()));
//    }
//}

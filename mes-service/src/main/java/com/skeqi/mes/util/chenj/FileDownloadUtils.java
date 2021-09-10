package com.skeqi.mes.util.chenj;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @Description TODO 下载文件工具类
 * @Author yitianRen
 * @Date 2019/10/9 10:03
 * @Version 1.0
 **/
public class FileDownloadUtils {
    public static void downloadStream(HttpServletResponse response, String filePath) throws Exception {
        // 必要地清除response中的缓存信息
        response.reset();
        OutputStream out = response.getOutputStream();
//         filePath = filePath.substring(0,filePath.length()-1);
        //建立文件的输入流,读取本地的图片资源
        FileInputStream fileInputStream = new FileInputStream(filePath);
        //建立缓冲字节数组读取
        byte[] buf = new byte[1024];
        int length = 0;
        //不断的读取了图片的资源
        while ((length = fileInputStream.read(buf)) != -1) {
            //向浏览器输出
            out.write(buf, 0, length);
        }
//        System.err.println("下载文件名"+URLEncoder.encode(new File(filePath).getName(), "UTF-8"));
        System.err.println("下载文件名："+ new File(filePath).getName());
        //设置content-disposition响应头，通知浏览区以附件的形式下载处理。
//        if(("1").equals(openMode)){
//            response.setHeader("Content-Disposition", "inline;filename="+ URLEncoder.encode(new File(filePath).getName(), "UTF-8"));
//            response.setContentType("text/html; charset=utf-8");
//        }else{
//            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(new File(filePath).getName(), "UTF-8"));
//            response.setContentType("application/octet-stream; charset=utf-8");
//        }
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(new File(filePath).getName(), "UTF-8"));
        response.setContentType("application/octet-stream; charset=utf-8");
        //关闭 资源
        fileInputStream.close();
    }


    public static void downloadStreamExtend(HttpServletResponse response, String filePath, String openMode) throws Exception {
        // 必要地清除response中的缓存信息
        response.reset();
        OutputStream out = response.getOutputStream();
//         filePath = filePath.substring(0,filePath.length()-1);
        //建立文件的输入流,读取本地的图片资源
        FileInputStream fileInputStream = new FileInputStream(filePath);
        //建立缓冲字节数组读取
        byte[] buf = new byte[1024];
        int length = 0;
        //不断的读取了图片的资源
        while ((length = fileInputStream.read(buf)) != -1) {
            //向浏览器输出
            out.write(buf, 0, length);
        }
        System.err.println("下载文件名：" + URLEncoder.encode(new File(filePath).getName(), "UTF-8"));
        //设置content-disposition响应头，通知浏览区以附件的形式下载处理。
        if (("1").equals(openMode)) {
            response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(new File(filePath).getName(), "UTF-8"));
            response.setContentType("text/html; charset=utf-8");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(new File(filePath).getName(), "UTF-8"));
            response.setContentType("application/octet-stream; charset=utf-8");
        }
        //关闭 资源
        fileInputStream.close();
    }

}

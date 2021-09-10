package com.skeqi.mes.controller.yp.oa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import com.skeqi.mes.util.yp.FileReading;

/**
 * 单据信息文件
 *
 * @author yinp
 * @data 2021年5月21日
 *
 */
@RestController
@RequestMapping("/api/oa/SinglePageFile")
public class SinglePageFileController {


	@Value(value = "${fileName.ApprovalFilePath}")
	private String PATH;

	/**
	 * 加载图片
	 *
	 * @param response
	 * @param nian
	 * @param yue
	 * @param ri
	 * @param name
	 */
	@RequestMapping("/imgs/{nian}/{yue}/{ri}/{name:.+}")
	public void find(HttpServletResponse response, @PathVariable("nian") String nian, @PathVariable("yue") String yue,
			@PathVariable("ri") String ri, @PathVariable("name") String name) {

		FileInputStream fis = null;
		response.setContentType("image/gif");
		try {
			OutputStream out = response.getOutputStream();
			File file = new File(PATH + "/" + nian + "/" + yue + "/" + ri + "/" + name);
			fis = new FileInputStream(file);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 下载文件
	 *
	 * @param response
	 * @param nian
	 * @param yue
	 * @param ri
	 * @param name
	 */
	@OptionalLog(module = "OA", module2 = "单据信息文件", method= "下载文件")
	@RequestMapping("/downloadFile/{nian}/{yue}/{ri}/{name:.+}/{oldName:.+}")
	public void downloadFile(HttpServletResponse response, @PathVariable("nian") String nian,
			@PathVariable("yue") String yue, @PathVariable("ri") String ri, @PathVariable("name") String name,
			@PathVariable("oldName") String oldName) {

		try {
			String path = PATH + "/" + nian + "/" + yue + "/" + ri + "/" + name;

			// 读到流中
			InputStream inStream = new FileInputStream(path);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(oldName, "UTF-8") + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len;
			try {
				while ((len = inStream.read(b)) > 0)
					response.getOutputStream().write(b, 0, len);
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Value(value = "${fileName.OAFilePath}")
	public String path;
	/**
	 * 下载关联文件
	 *
	 * @param response
	 * @param request
	 */
	@OptionalLog(module = "OA", module2 = "单据信息文件", method= "下载关联文件")
	@RequestMapping("/downloadAssociatedFiles")
	public void downloadAssociatedFiles(HttpServletResponse response, HttpServletRequest request) {
		try {
			
			String value = EqualsUtil.string(request, "value", "关联文件地址", true);

			path = path + value;

			// 读到流中
			InputStream inStream = new FileInputStream(path);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(path.substring(path.lastIndexOf("/") + 1, path.length()), "UTF-8") + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len;
			try {
				while ((len = inStream.read(b)) > 0)
					response.getOutputStream().write(b, 0, len);
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

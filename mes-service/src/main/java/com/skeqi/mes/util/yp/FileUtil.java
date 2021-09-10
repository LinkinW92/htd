package com.skeqi.mes.util.yp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具类
 *
 * @author yinp
 *
 */
public class FileUtil {

	// 文件名
	private String fileName;
	// 带单位的文件大小
	private String fileSizeCompany;
	// 文件大小
	private long fileSize;
	// 文件后缀
	private String suffix;
	// 重命名
	private String rename;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSizeCompany() {
		return fileSizeCompany;
	}

	public void setFileSizeCompany(String fileSizeCompany) {
		this.fileSizeCompany = fileSizeCompany;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getRename() {
		return rename;
	}

	public void setRename(String rename) {
		this.rename = rename;
	}

	/**
	 * 上传文件
	 *
	 * @param multipartFile
	 * @param path
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static FileUtil uploadFile(MultipartFile multipartFile, String path, String fileName) throws Exception {

		FileUtil fileUtil = new FileUtil();

		// 获取文件名
		String name = "";

		if (multipartFile != null) {

			//给文件重命名一下
			if(fileName!=null) {
				name = fileName;
			}else {
				name = multipartFile.getOriginalFilename();// 直接返回文件的名字
			}

			String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());// 我这里取得文件后缀

			File file = new File(path);
			if (!file.exists()) {// 目录不存在就创建
				file.mkdirs();
			}

			fileUtil.setFileName(name);
			fileUtil.setFileSize(multipartFile.getSize());
			fileUtil.setFileSizeCompany(getPrintSize(multipartFile.getSize()));
			fileUtil.setSuffix(subffix);
			
			multipartFile.transferTo(new File(path + "/" + name));// 保存文件
		}

		return fileUtil;
	}

	/**
	 * 文件下载
	 *
	 * @param response
	 * @param path
	 * @param fileName
	 * @throws Exception
	 */
	public static void downloadFile(HttpServletResponse response, String path, String fileName) throws Exception {
		// 读到流中
		InputStream inStream = new FileInputStream(path + fileName);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("text/html;charset=UTF-8");
		response.addHeader("Content-Disposition",
				"attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
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

	}

	/**
	 * 删除文件
	 *
	 * @param path
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		delFiles(file);
	}

	/**
	 * 字节转kb/mb/gb
	 *
	 * @param size
	 * @return
	 */
	public static String getPrintSize(long size) {
		// 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		if (size < 1024) {
			return String.valueOf(size) + "B";
		} else {
			size = size / 1024;
		}
		// 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		// 因为还没有到达要使用另一个单位的时候
		// 接下去以此类推
		if (size < 1024) {
			return String.valueOf(size) + "KB";
		} else {
			size = size / 1024;
		}
		if (size < 1024) {
			// 因为如果以MB为单位的话，要保留最后1位小数，
			// 因此，把此数乘以100之后再取余
			size = size * 100;
			return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
		} else {
			// 否则如果要以GB为单位的，先除于1024再作同样的处理
			size = size * 100 / 1024;
			return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
		}
	}

	/**
	 * 递归删除 删除某个目录及目录下的所有子目录和文件
	 *
	 * @param file 文件或目录
	 * @return 删除结果
	 */
	public static boolean delFiles(File file) {
		boolean result = false;
		// 目录
		if (file.isDirectory()) {
			File[] childrenFiles = file.listFiles();
			for (File childFile : childrenFiles) {
				result = delFiles(childFile);
				if (!result) {
					return result;
				}
			}
		}
		// 删除 文件、空目录
		result = file.delete();
		return result;
	}

	/**
	 * 重命名文件
	 *
	 * @param path1
	 * @param path2
	 */
	public static void renameFile(String path1, String path2) {
		File file1 = new File(path1);
		// 将原文件夹更改为A，其中路径是必要的。注意
		file1.renameTo(new File(path2));

	}

	public static void main(String[] args) {
		renameFile("D:/MES_file/OAFile/1/1.zip", "D:/MES_file/OAFile/1/activiti01_LFhnYXccPN.zip");
	}

}

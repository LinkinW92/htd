package com.skeqi.mes.util.chenj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.chenj.srm.CSrmFileUploading;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * @Description TODO  文件上传工具类
 * @Author yitianRen
 * @Date 2019/10/10 10:51
 * @Version 1.0
 **/
public class FileUploadUtils {


	/**
	 * @Author: chenj
	 * @Description:
	 * @Date: 16:00 2019/10/10
	 * @Param: file:文件 rootDir：上传跟路径
	 * @Return:
	 */
	public static JSONObject upLoadFile(MultipartFile[] file, String fileName, String orderNumber, String lineNumber, int flag, String supplierCode,String pathFile) throws IOException {
		// 采购文件、供应商文件转换
		String flagName = "";
		if (1 == flag) {
			flagName = "采购文件";
		} else if (2 == flag) {
			flagName = "供应商文件";
		}
		// 使用fileupload组件完成文件上传
//        Properties pps = new Properties();
//        pps.load(BusinessInfoFileController.class.getClassLoader().getResourceAsStream("/config.properties"));
//        String pathFile = pps.getProperty("SRMFile");


		// 存储linux路径或window
		String linuxOrWindowPath = "";
		// true：Linux,false：Window
		if (isOSLinux()) {
			linuxOrWindowPath = pathFile + "/" + fileName + "/" + orderNumber + "/" + flagName + "/";
		} else {
			linuxOrWindowPath = pathFile + "\\" + fileName + "\\" + orderNumber + "\\" + flagName + "\\";
		}

		// 判断，该路径是否存在
		File file1 = new File(linuxOrWindowPath);
		if (!file1.exists()) {
			// 创建该文件夹
			file1.mkdirs();
		}
		// 说明上传文件项
		// 获取上传文件的名称
		// 新增数据
		List<CSrmFileUploading> addList = new ArrayList<>();
		// 更新数据
		List<CSrmFileUploading> updateList = new ArrayList<>();
		for (MultipartFile filD : file) {        // SRMFile/采购订单/订单号/采购文件|供应商编码/文件名
			File fileName1 = new File(linuxOrWindowPath + filD.getOriginalFilename());
			if (!fileName1.exists()) {  //要删除的文件不存在
				filD.transferTo(fileName1);//保存当前文件
				// 新增文件上传表
				CSrmFileUploading cSrmFileUploading = new CSrmFileUploading();
				cSrmFileUploading.setFilePath(fileName1.getPath());
				cSrmFileUploading.setFileName(filD.getOriginalFilename());
				cSrmFileUploading.setFlag(flag);
				cSrmFileUploading.setOrderNumber(orderNumber);
				cSrmFileUploading.setLineNumber(lineNumber);
				// flag标识为供应商文件时有供应商代码
				if (2 == flag) {
					cSrmFileUploading.setSupplierCode(supplierCode);
				}
				addList.add(cSrmFileUploading);
			} else { //要删除的文件已经存在
				if (fileName1.isFile()) { //如果目标文件是文件

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					Date date = new Date();
					String beforeName = sdf.format(date);
//                    File oldFilePath = new File(pathFile + fileName + "\\" + orderNumber+"\\");
//                    if (!oldFilePath.exists()) {
//                        oldFilePath.mkdirs();
//                    }

					// 存储linux路径或window
					String coverLinuxOrWindowPath = "";
					// true：Linux,false：Window
					if (isOSLinux()) {
						coverLinuxOrWindowPath = pathFile + fileName + "/" + orderNumber + "/(覆盖文件)" + beforeName + "-" + filD.getOriginalFilename();
					} else {
						coverLinuxOrWindowPath = pathFile + fileName + "\\" + orderNumber + "\\(覆盖文件)" + beforeName + "-" + filD.getOriginalFilename();
					}

					File oldFile = new File(coverLinuxOrWindowPath);
					fileName1.renameTo(oldFile);
					fileName1.delete();//删除文件
					filD.transferTo(fileName1);//保存新文件
					// 更新文件上传表
					CSrmFileUploading cSrmFileUploading = new CSrmFileUploading();
					cSrmFileUploading.setFlag(flag);
					cSrmFileUploading.setOrderNumber(orderNumber);
					cSrmFileUploading.setLineNumber(lineNumber);
					// flag标识为供应商文件时有供应商代码
					if (2 == flag) {
						cSrmFileUploading.setSupplierCode(supplierCode);
					}
					updateList.add(cSrmFileUploading);

				}
			}
		}
		JSONObject jsonObject = new JSONObject();
		if (!CollectionUtils.isEmpty(addList)) {
			jsonObject.put("addList", JSON.toJSONString(addList));
		}
		if (!CollectionUtils.isEmpty(updateList)) {
			jsonObject.put("updateList", JSON.toJSONString(updateList));
		}
		System.err.println("文件上传成功...");
		return jsonObject;
	}

	/**
	 * 复制整个文件夹内容
	 *
	 * @param oldFilePath String 原文件路径 如：c:/fqf
	 * @param newFilePath String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldFilePath, String newFilePath) throws IOException {

		File filePath = new File(oldFilePath);
		DataInputStream read;
		DataOutputStream write;
		if (filePath.isDirectory()) {
			File[] list = filePath.listFiles();
			for (int i = 0; i < list.length; i++) {
				String newPath = oldFilePath + File.separator + list[i].getName();
				String newCopyPath = newFilePath + File.separator + list[i].getName();
				File newFile = new File(newFilePath);
				if (!newFile.exists()) {
					newFile.mkdir();
				}
				copyFolder(newPath, newCopyPath);
			}
		} else if (filePath.isFile()) {
			read = new DataInputStream(
				new BufferedInputStream(new FileInputStream(oldFilePath)));
			write = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(newFilePath)));
			byte[] buf = new byte[1024 * 512];
			while (read.read(buf) != -1) {
				write.write(buf);
			}
			read.close();
			write.close();
		} else {
			System.err.println("请输入正确的文件名或路径名");
		}
	}

	/**
	 * 判断当前系统为Window或者Linux
	 *
	 * @return
	 */
	public static boolean isOSLinux() {
		Properties prop = System.getProperties();

		String os = prop.getProperty("os.name");
		if (os != null && os.toLowerCase().indexOf("linux") > -1) {
			return true;
		} else {
			return false;
		}
	}

}

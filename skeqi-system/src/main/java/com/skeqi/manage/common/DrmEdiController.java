package com.skeqi.manage.common;

import cn.net.drm.edi.DrmEdiClient;
import com.skeqi.common.config.SqConfig;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.utils.file.FileUploadUtils;
import com.skeqi.framecore.config.ServerConfig;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DrmEdiController {

	@Autowired
	private ServerConfig serverConfig;

	private static URI fileUrl=null;
	/**
	 * 通用上传请求
	 */
	@PostMapping("drm/decryption")
	public AjaxResult decryption(MultipartFile file) throws Exception
	{
		try
		{
			File decFile=getFile(file);
			if(!DrmEdiClient.getInstance().isEncrypted(decFile)){
				return AjaxResult.error("当前文件不是加密文件，无需解密");
			}
			DrmEdiClient.getInstance().decrypt(decFile);
			// 上传文件路径
			String filePath = SqConfig.getUploadPath();
			MultipartFile newFile=getMultipartFile(decFile);
			// 上传并返回新文件名称
			String fileName = FileUploadUtils.upload(filePath, newFile);
			String url = serverConfig.getUrl() + fileName;
			Map<String,Object> ajax = new HashMap<>();
			ajax.put("fileName", fileName);
			ajax.put("url", url);
			return AjaxResult.success(ajax);
		}
		catch (Exception e)
		{
			return AjaxResult.error(e.getMessage());
		}finally {
			File f = new File(fileUrl);
			if (f.delete()){
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
			}
		}
	}

	private File getFile(MultipartFile multipartFile){
		String fileName = multipartFile.getOriginalFilename();
		File file = new File(fileName);
		OutputStream out = null;
		try{
			//获取文件流，以文件流的方式输出到新文件
//    InputStream in = multipartFile.getInputStream();
			out = new FileOutputStream(file);
			byte[] ss = multipartFile.getBytes();
			for(int i = 0; i < ss.length; i++){
				out.write(ss[i]);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			if (out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 操作完上的文件 需要删除在根目录下生成的文件
		fileUrl=file.toURI();
		return file;
	}

	private MultipartFile getMultipartFile(File file){
		FileInputStream fileInputStream = null;
		MultipartFile multipartFile = null;
		try {
			fileInputStream = new FileInputStream(file);
			multipartFile = new MockMultipartFile(file.getName(),file.getName(),
				ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return multipartFile;
	}

}

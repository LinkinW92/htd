package com.skeqi.mes.service.yp.oa;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.util.Rjson;

/**
 * 文件管理
 *
 * @author yinp
 * @data 2021年5月21日
 *
 */
public interface OAFileService {

	/**
	 * 查询
	 *
	 * @param path
	 * @return
	 */
	public Rjson list(String path, int userId, int roleId) throws Exception;

	/**
	 * 创建文件夹
	 *
	 * @param path
	 * @param name
	 * @throws Exception
	 */
	public void createFolder(JSONObject json) throws Exception;

	/**
	 * 上传文件
	 *
	 * @param path
	 * @param userId
	 * @param fileList
	 * @throws Exception
	 */
	public void uploadFile(String path, int userId, List<MultipartFile> fileList) throws Exception;

	/**
	 * 下载文件
	 *
	 * @param id
	 * @throws Exception
	 */
	public JSONObject downloadFile(int id) throws Exception;

	/**
	 * 删除文件
	 *
	 * @param id
	 */
	public void deleteFile(int id) throws Exception;

	/**
	 * 修改文件名
	 *
	 * @param id
	 * @param name
	 * @throws Exception
	 */
	public void updateFileName(int id, String name) throws Exception;

	/**
	 * 查询文件权限
	 *
	 * @return
	 */
	public List<JSONObject> findFileAuthority(String path);

	/**
	 * 配置权限
	 *
	 * @param roleId
	 * @param catalogue
	 */
	public void configurePermissions(String path, String authority) throws Exception;

	/**
	 * 查询用户权限
	 *
	 * @param userId
	 * @return
	 */
	public List<JSONObject> findAuthorityByUserId(int userId);

}

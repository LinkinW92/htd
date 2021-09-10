package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.oa.OAFileService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import com.skeqi.mes.util.yp.FileReading;
import com.skeqi.mes.util.yp.FileUtil;

/**
 * 文件管理
 *
 * @author yinp
 * @data 2021年5月21日
 *
 */
@RestController
@RequestMapping("/api/oa/file")
public class FileController {

	@Autowired
	OAFileService service;

	/**
	 * 文件上传路径
	 */
	@Value(value = "${fileName.OAFilePath}")
	private static String PATH;

	/**
	 * 查询
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			String path = EqualsUtil.string(request, "path", "路径", true);
			int userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			int roleId = EqualsUtil.integer(request, "roleId", "角色ID", true);

			return service.list(path, userId, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 创建文件夹
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "文件管理", method= "创建文件夹")
	@Transactional
	@RequestMapping("/createFolder")
	public Rjson createFolder(HttpServletRequest request) {
		try {
			String path = EqualsUtil.string(request, "path", "路径", true);
			String name = EqualsUtil.string(request, "name", "文件名", true);
			int userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			int roleId = EqualsUtil.integer(request, "roleId", "角色ID", true);

			JSONObject json = new JSONObject();
			json.put("path", path);
			json.put("name", name);
			json.put("userId", userId);
			json.put("roleId", roleId);

			service.createFolder(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 上传文件
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "文件管理", method= "上传文件")
	@Transactional
	@RequestMapping("/uploadFile")
	public Rjson uploadFile(HttpServletRequest request) {
		try {
			String path = EqualsUtil.string(request, "path", "路径", true);
			int userId = EqualsUtil.integer(request, "userId", "用户ID", true);

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> fileList = multipartRequest.getFiles("file");

			service.uploadFile(path, userId, fileList);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 下载文件
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "文件管理", method= "下载文件")
	@RequestMapping("/downloadFile")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);

			JSONObject json = service.downloadFile(id);

			FileUtil.downloadFile(response, this.PATH + json.getString("path"), json.getString("name"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "文件管理", method= "删除文件")
	@Transactional
	@RequestMapping("/deleteFile")
	public Rjson deleteFile(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteFile(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改文件名
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "文件管理", method= "修改文件名")
	@Transactional
	@RequestMapping("/updateFileName")
	public Rjson updateFileName(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			String name = EqualsUtil.string(request, "name", "文件名", true);

			service.updateFileName(id, name);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询文件权限
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findFileAuthority")
	public Rjson findFileAuthority(HttpServletRequest request) {
		try {
			String path = EqualsUtil.string(request, "path", "路径", true);

			List<JSONObject> list = service.findFileAuthority(path);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 配置权限
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "文件管理", method= "配置权限")
	@Transactional
	@RequestMapping("configurePermissions")
	public Rjson configurePermissions(HttpServletRequest request) {
		try {
			String path = EqualsUtil.string(request, "path", "目录", true);
			String authority = EqualsUtil.string(request, "authority", "权限", true);

			service.configurePermissions(path, authority);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}
}

package com.skeqi.mes.service.yp.oa.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.OAFileDao;
import com.skeqi.mes.service.yp.oa.OAFileService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.FileReading;
import com.skeqi.mes.util.yp.FileUtil;

/**
 * 文件管理
 *
 * @author yinp
 * @data 2021年5月21日
 *
 */
@Service
public class OAFileServiceImpl implements OAFileService {

	@Autowired
	OAFileDao dao;

	@Value(value = "${fileName.OAFilePath}")
	private String PATH;

	/**
	 * 查询
	 *
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@Override
	public Rjson list(String path, int userId, int roleId) throws Exception {

		String shangJiName = "";
		String shangJiPath = "";
		String b = path.substring(0, path.indexOf("/", 2) + 1);
		if (!path.equals("/")) {
			String a = path;
			a = a.substring(0, a.length() - 1);
			shangJiName = a.substring(a.lastIndexOf("/") + 1, a.length());
			shangJiPath = a.substring(0, a.lastIndexOf("/") + 1);
			if (a.lastIndexOf("/") == 0) {
				shangJiPath = "/";
			}

			if (!b.equals("/user/") && !b.equals("/share/")) {
				// 通过路径跟文件名查询count数量
				if (dao.findFileCountByPathAndName(shangJiPath, shangJiName, null) == 0) {
					throw new Exception("文件路径不存在");
				}
			}
		}

		JSONObject json = new JSONObject();

		if (!b.equals("/user/")) {
			// 判断是否有访问权限
			List<JSONObject> permissionList = getPermissionList(path, userId, roleId);

			if (permissionList.size() == 0) {
				throw new Exception("无访问权限");
			}

			json.put("permissionList", permissionList);
		}

		List<JSONObject> list = dao.list(path);

		json.put("list", list);

		// 查询
		return Rjson.success(json);
	}

	/**
	 * 判断是否有访问权限
	 *
	 * @param path
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<JSONObject> getPermissionList(String path, Integer userId, Integer roleId) throws Exception {
		List<JSONObject> permissionList = new ArrayList<JSONObject>();
		// 查询权限清单
		List<JSONObject> list = dao.findPermissionList(path);
		if (list.size() == 0) {
			if (path.equals("/")) {
				throw new Exception("系统必须配置一位有的根目录权限的用户");
			}

			path = path.substring(0, path.lastIndexOf("/"));
			path = path.substring(0, path.lastIndexOf("/") + 1);

			return getPermissionList(path, userId, roleId);
		} else {

			for (JSONObject jsonObject : list) {
				if (jsonObject.getString("userOrRole").equals("user")) {
					if (jsonObject.getInteger("keyId").equals(userId)) {
						permissionList.add(jsonObject);
					}

				}
				if (jsonObject.getString("userOrRole").equals("role")) {

					if (jsonObject.getInteger("keyId").equals(roleId)) {
						permissionList.add(jsonObject);
					}
				}

			}
		}
		return permissionList;
	}

	/**
	 * 创建文件夹
	 *
	 * @param path
	 * @param name
	 * @throws Exception
	 */
	@Override
	public void createFolder(JSONObject json) throws Exception {
		String path = json.getString("path");
		String name = json.getString("name");
		String b = path.substring(0, path.indexOf("/", 2) + 1);
		if (!path.equals("/")) {
			String a = path;
			a = a.substring(0, a.length() - 1);
			String shangJiName = a.substring(a.lastIndexOf("/") + 1, a.length());
			String shangJiPath = a.substring(0, a.lastIndexOf("/") + 1);
			if (a.lastIndexOf("/") == 0) {
				shangJiPath = "/";
			}

			if (!b.equals("/user/") && !b.equals("/share/")) {
				// 通过路径跟文件名查询count数量
				if (dao.findFileCountByPathAndName(shangJiPath, shangJiName, null) == 0) {
					throw new Exception("文件路径不存在");
				}
			}

		}

		json.put("type", "文件夹");

		if (dao.findFileCountName(path, name, null) > 0) {
			throw new Exception("该文件名称已存在");
		}

		if (dao.add(json) != 1) {
			throw new Exception("创建失败");
		}

	}

	/**
	 * 上传文件
	 *
	 * @param path
	 * @param userId
	 * @param fileList
	 * @throws Exception
	 */
	@Override
	public void uploadFile(String path, int userId, List<MultipartFile> fileList) throws Exception {

		for (MultipartFile multipartFile : fileList) {
			if (!path.equals("/")) {
				String a = path;
				a = a.substring(0, a.length() - 1);
				String shangJiName = a.substring(a.lastIndexOf("/") + 1, a.length());
				String shangJiPath = a.substring(0, a.lastIndexOf("/") + 1);
				if (a.lastIndexOf("/") == 0) {
					shangJiPath = "/";
				}

				if (!shangJiPath.equals("/user/") && !shangJiPath.equals("/share/")) {
					// 通过路径跟文件名查询count数量
					if (dao.findFileCountByPathAndName(shangJiPath, shangJiName, null) == 0) {
						throw new Exception("上传路径不存在");
					}
				}
			}

			JSONObject json = new JSONObject();

			String name = multipartFile.getOriginalFilename();
			String subffix = "";

			// 通过路径跟文件名查询count数量
			if (dao.findFileCountByPathAndName(path, name, null) > 0) {
				if (name.lastIndexOf(".") >= 0) {
					name = name.substring(0, name.lastIndexOf("."));
					subffix = name.substring(name.lastIndexOf(".") + 1, name.length());// 我这里取得文件后缀
				}
				name = name + "(1)";
				if (!subffix.equals("")) {
					name = name + "." + subffix;
				}

			}

			json.put("name", name);
			json.put("path", path);
			json.put("userId", userId);

			FileUtil fileUtil = FileUtil.uploadFile(multipartFile, this.PATH + path, name);

			String type = "";
			if (name.lastIndexOf(".") >= 0) {
				type = fileUtil.getSuffix();
			} else {
				type = "文件";
			}

			json.put("type", type);
			json.put("size", fileUtil.getFileSizeCompany());

			// 新增
			dao.add(json);
		}

	}

	/**
	 * 下载文件
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public JSONObject downloadFile(int id) throws Exception {
		return dao.findFileById(id);
	}

	/**
	 * 删除文件
	 *
	 * @param id
	 */
	@Override
	public void deleteFile(int id) throws Exception {

		// 通过id查询
		JSONObject json = dao.findFileById(id);

		if (json == null || json.getInteger("id").equals("")) {
			throw new Exception("文件不存在");
		}

		// 删除文件夹
		dao.deleteFileById(id);

		// 删除文件夹下的文件
		dao.deleteFileByPath(json.getString("path") + json.getString("name") + "/");

		// 删除硬盘文件
		FileUtil.deleteFile(this.PATH + json.getString("path") + json.getString("name") + "/");

	}

	/**
	 * 修改文件名
	 *
	 * @param id
	 * @param name
	 * @throws Exception
	 */
	@Override
	public void updateFileName(int id, String name) throws Exception {

		// 通过id查询
		JSONObject json = dao.findFileById(id);

		if (json == null) {
			throw new Exception("操作的文件不存在");
		}

		String laoPath = json.getString("path") + json.getString("name") + "/";

		String path = json.getString("path");

		if (dao.findFileCountByPathAndName(path, name, id) > 0) {
			throw new Exception("文件名已存在");
		}

		// 修改前路径
		String path1 = this.PATH + path + json.getString("name");
		// 修改后路径
		String path2 = this.PATH + path + name;

		// 重命名文件
		FileUtil.renameFile(path1, path2);

		// 更新文件名称
		dao.updateFileName(id, name);

		if (json.getString("type").equals("文件夹")) {

			path1 = path + json.getString("name") + "/";
			path2 = path + name + "/";

			// 更新文件路径
			dao.updateFilePath(path1, path2);
		} else {
			String type = "";
			if (name.lastIndexOf(".") >= 0) {
				type = name.substring(name.lastIndexOf(".") + 1, name.length());
			} else {
				type = "文件";
			}
			// 修改文件类型
			dao.updateFileType(id, type);
		}

		// 通过路径查询路径前部分权限
		List<JSONObject> fileAuthorityList = dao.findFileAuthorityByLikePath(laoPath);
		for (JSONObject jsonObject : fileAuthorityList) {
			// 如果路径一样，直接改掉
			if (jsonObject.getString("path").equals(laoPath)) {
				// 新的地址
				jsonObject.put("path", json.getString("path") + name + "/");
				jsonObject.put("name", name);
			} else {
				// 如果路径不一样，替换前面的

				// 新的路径
				String xinde = json.getString("path") + name + "/";

				// 清单的路径
				String qingdang = jsonObject.getString("path");

				// 新的地址
				jsonObject.put("path", xinde + qingdang.substring(laoPath.length(), qingdang.length()));
			}

			// 更新权限清单路径
			dao.updateFileAuthorityPath(jsonObject);
		}

	}

	/**
	 * 查询文件权限
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findFileAuthority(String path) {
		List<JSONObject> list = new ArrayList<JSONObject>();

		List<JSONObject> roleList = dao.findFileAuthorityRole(path);

		for (JSONObject jsonObject : roleList) {
			jsonObject.put("userOrRole", "role");
			List<JSONObject> stateList = JSONObject.parseArray(jsonObject.getString("state"), JSONObject.class);

			for (JSONObject stateJson : stateList) {
				if (stateJson.getString("type").equals("只读")) {
					jsonObject.put("read", true);
				}
				if (stateJson.getString("type").equals("读写")) {
					jsonObject.put("write", true);
				}
			}

			list.add(jsonObject);
		}

		List<JSONObject> userList = dao.findFileAuthorityUser(path);

		for (JSONObject jsonObject : userList) {
			jsonObject.put("userOrRole", "user");
			List<JSONObject> stateList = JSONObject.parseArray(jsonObject.getString("state"), JSONObject.class);

			for (JSONObject stateJson : stateList) {
				if (stateJson.getString("type").equals("只读")) {
					jsonObject.put("read", true);
				}
				if (stateJson.getString("type").equals("读写")) {
					jsonObject.put("write", true);
				}
			}

			list.add(jsonObject);
		}

		return list;
	}

	/**
	 * 配置权限
	 *
	 * @param roleId
	 * @param catalogue
	 */
	@Override
	public void configurePermissions(String path, String authority) throws Exception {
		List<JSONObject> list = null;
		try {
			list = JSONObject.parseArray(authority, JSONObject.class);
		} catch (Exception e) {
			throw new Exception("权限格式有误");
		}

		// 删除文件权限
		dao.deleteFileAuthority(path);

		for (JSONObject jsonObject : list) {

			String a = path;
			if (!a.equals("/")) {
				a = a.substring(0, a.lastIndexOf("/"));
				a = a.substring(a.lastIndexOf("/") + 1, a.length());
			}

			jsonObject.put("path", path);
			jsonObject.put("name", a);

			List<JSONObject> typeList = JSONObject.parseArray(jsonObject.getString("state"), JSONObject.class);
			for (JSONObject typeJson : typeList) {
				jsonObject.put("type", typeJson.getString("type"));
				// 新增文件权限
				dao.addFileAuthority(jsonObject);
			}
		}

	}

	/**
	 * 查询用户权限
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public List<JSONObject> findAuthorityByUserId(int userId) {
		return dao.findAuthorityByUserId(userId);
	}

}

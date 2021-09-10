package com.skeqi.mes.service.yp.oa.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.DraftsDao;
import com.skeqi.mes.service.yp.oa.DraftsService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.yp.EqualsUtil;
import com.skeqi.mes.util.yp.FileReading;
import com.skeqi.mes.util.yp.FileUtil;
import com.skeqi.mes.util.yp.TokenUtil;

/**
 * 草稿箱
 *
 * @author yinp
 * @data 2021年6月2日
 *
 */
@Service
public class DraftsServiceImpl implements DraftsService {

	@Value(value = "${fileName.ApprovalFilePath}")
	private static String PATH;

	@Autowired
	DraftsDao dao;

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		return dao.list(json);
	}

	/**
	 * 查询现有的单据类型
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findType() {
		return dao.findType();
	}

	/**
	 * 查询单据
	 *
	 * @param listNo
	 * @return
	 */
	@Override
	public JSONObject findApprovalRecordDrafts(String listNo) {
		return dao.findApprovalRecordDrafts(listNo);
	}

	/**
	 * 查询单据详情
	 *
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findApprovalRecordDetailedDrafts(String listNo) {
		return dao.findApprovalRecordDetailedDrafts(listNo);
	}

	/**
	 * 查询公司
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findCompany() {
		return dao.findCompany();
	}

	/**
	 * 通过公司ID查询部门
	 *
	 * @param companyId
	 * @return
	 */
	@Override
	public List<JSONObject> findDepartmentByCompanyId(int companyId) {
		return dao.findDepartmentByCompanyId(companyId);
	}

	/**
	 * 通过部门ID查询用户
	 *
	 * @param departmentId
	 * @return
	 */
	@Override
	public List<JSONObject> findUserByDepartmentId(int departmentId) {
		return dao.findUserByDepartmentId(departmentId);
	}

	/**
	 * 通过id查询用户
	 *
	 * @param departmentId
	 * @return
	 */
	@Override
	public JSONObject findUserById(int id) {
		return dao.findUserById(id);
	}

	/**
	 * 保存草稿
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void saveDraft(JSONObject json, HttpServletRequest request) throws Exception {
		int userId = json.getInteger("userId");
		int formTemplateId = json.getInteger("formTemplateId");
		String relationListNo = json.getString("relationListNo");

		String applyUserName = json.getString("applyUserName");
		String applyDepartmentName = json.getString("applyDepartmentName");
		String applyCompanyName = json.getString("applyCompanyName");

		// 删除的图片
		List<JSONObject> deleteImg = JSONObject.parseArray(json.getString("deleteImg"), JSONObject.class);
		for (JSONObject jsonObject : deleteImg) {
			String url = jsonObject.getString("url");
			FileUtil.deleteFile(PATH + url.split("imgs")[1]);
		}

		// 删除的文件
		List<JSONObject> deleteFile = JSONObject.parseArray(json.getString("deleteFile"), JSONObject.class);
		for (JSONObject jsonObject : deleteFile) {
			String url = jsonObject.getString("url");
			FileUtil.deleteFile(PATH + url.split("downloadFile")[1]);
		}

		String listNo = json.getString("listNo");

		// 查询草稿
		JSONObject draftsJson = dao.findDraftsByListNo(listNo);
		if (draftsJson == null) {
			throw new Exception("草稿不存在");
		}

		// 删除草稿
		if (dao.deleteDraftsByListNo(listNo) != 1) {
			throw new Exception("草稿保存失败");
		}

		// 删除草稿详情
		dao.deleteDraftsDetailedByApprovalRecordId(draftsJson.getInteger("id"));

		// 删除草稿表格
		dao.deleteDraftsTableByApprovalRecordId(draftsJson.getInteger("id"));

		List<JSONObject> detailedJsonList = null;
		try {
			detailedJsonList = JSONObject.parseArray(json.getString("detailed"), JSONObject.class);
		} catch (Exception e) {
			throw new Exception("参数格式不正确");
		}

		List<JSONObject> tableJsonList = null;
		try {
			tableJsonList = JSONObject.parseArray(json.getString("table"), JSONObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("参数格式不正确");
		}

		json.put("state", "草稿");

		// 新增申请
		if (dao.addDraft(json) != 1) {
			throw new Exception("提交失败");
		}

		// 查询模板表格
		List<JSONObject> tableList = dao.findTemplateTable(formTemplateId);

		if (tableList.size() > 0) {
			JSONObject tableJson = new JSONObject();
			tableJson.put("key", tableList.toString());
			tableJson.put("value", tableJsonList.toString());
			tableJson.put("approvalRecordId", json.getInteger("id"));

			// 新增审批记录表格
			dao.addApprovalRecordTableDraft(tableJson);
		}

		/**
		 * ===========================================================================
		 * ===========================================================================
		 */
		JSONObject jsonDetailed = new JSONObject();
		jsonDetailed.put("key", "applyUserName");
		jsonDetailed.put("value", applyUserName);
		jsonDetailed.put("approvalRecordId", json.getInteger("id"));
		jsonDetailed.put("type", "parameter");

		// 新增明细
		if (dao.addDetailedDraft(jsonDetailed) != 1) {
			throw new Exception("提交失败");
		}

		jsonDetailed.put("key", "applyDepartmentName");
		jsonDetailed.put("value", applyDepartmentName);

		// 新增明细
		if (dao.addDetailedDraft(jsonDetailed) != 1) {
			throw new Exception("提交失败");
		}

		jsonDetailed.put("key", "applyCompanyName");
		jsonDetailed.put("value", applyCompanyName);

		// 新增明细
		if (dao.addDetailedDraft(jsonDetailed) != 1) {
			throw new Exception("提交失败");
		}
		/**
		 * ===========================================================================
		 * ===========================================================================
		 */

		for (JSONObject detailedJson : detailedJsonList) {

			if (detailedJson.getString("type").equals("图片上传")) {
				try {

					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
					List<MultipartFile> fileList = multipartRequest.getFiles(detailedJson.getString("key"));

					// 保存源文件名跟路径
					List<JSONObject> fileResult = uploadFile(fileList);

					// 已保存的图片
					List<JSONObject> fileJsonList = JSONObject.parseArray(detailedJson.getString("value"),
							JSONObject.class);

					// 删除的图片
					for (int i = 0; i < fileJsonList.size(); i++) {
						for (int j = 0; j < deleteImg.size(); j++) {
							if (fileJsonList.get(i).getString("relativePath")
									.equals(deleteImg.get(j).getString("url").split("imgs/")[1])) {
								fileJsonList.remove(i);
								i--;
								break;
							}
						}
					}

					for (JSONObject jsonObject : fileResult) {
						fileJsonList.add(jsonObject);
					}

					detailedJson.put("value", fileJsonList.toString());

				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("文件上传出错了");
				}

			} else if (detailedJson.getString("type").equals("附件")) {
				try {

					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
					List<MultipartFile> fileList = multipartRequest.getFiles(detailedJson.getString("key"));

					// 保存源文件名跟路径
					List<JSONObject> fileResult = uploadFile(fileList);

					// 已保存文件
					List<JSONObject> fileJsonList = JSONObject.parseArray(detailedJson.getString("value"),
							JSONObject.class);

					// 删除的文件
					for (int i = 0; i < fileJsonList.size(); i++) {
						for (int j = 0; j < deleteFile.size(); j++) {
							if (fileJsonList.get(i).getString("relativePath")
									.equals(deleteFile.get(j).getString("url").split("downloadFile/")[1])) {
								fileJsonList.remove(i);
								i--;
								break;
							}
						}
					}

					for (JSONObject jsonObject : fileResult) {
						fileJsonList.add(jsonObject);
					}

					detailedJson.put("value", fileJsonList.toString());

				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("文件上传出错了");
				}
			} else if (detailedJson.getString("type").equals("关联单据")) {

				detailedJson.put("value", relationListNo);

			} else {
				if (detailedJson.getString("key") == null) {
					throw new Exception("参数缺失");
				}

				if (detailedJson.getBoolean("check") != null && detailedJson.getBoolean("check")) {
					if (detailedJson.getString("value") == null) {
						throw new Exception(detailedJson.getString("key") + "不能为空");
					}
				}

			}
			detailedJson.put("approvalRecordId", json.getInteger("id"));

			// 新增明细
			if (dao.addDetailedDraft(detailedJson) != 1) {
				throw new Exception("提交失败");
			}
		}

	}

	public List<JSONObject> uploadFile(List<MultipartFile> fileList) throws Exception {
		List<JSONObject> jsonList = new ArrayList<JSONObject>();

		for (MultipartFile multipartFile : fileList) {

			String realpath = "";
			// 获取文件名
			String name = "";

			if (multipartFile != null) {

				name = multipartFile.getOriginalFilename();// 直接返回文件的名字
				String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());// 我这里取得文件后缀
				String fileName = TokenUtil.randomToken(10);
				fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());// 文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。
//				String filepath = request.getServletContext().getRealPath("/") + "files\\";// 获取项目路径到webapp
				String filepath = "D:\\MES_file\\" + DateUtil.getNian() + "\\" + DateUtil.getYue() + "\\"
						+ DateUtil.getRi();
				File file = new File(filepath);
				if (!file.exists()) {// 目录不存在就创建
					file.mkdirs();
				}
				multipartFile.transferTo(new File(filepath + "\\" + fileName + "." + subffix));// 保存文件
				realpath = file + "\\" + fileName + "." + subffix;

				String relativePath = DateUtil.getNian() + "\\" + DateUtil.getYue() + "\\" + DateUtil.getRi() + "\\"
						+ fileName + "." + subffix;

				JSONObject json = new JSONObject();
				json.put("oldName", name);
				// 绝对路径
				json.put("absolutePath", realpath);
				// 相对路径
				json.put("relativePath", relativePath);
				// 文件大小
				json.put("fileSize", getPrintSize(multipartFile.getSize()));

				jsonList.add(json);
			}
		}
		return jsonList;
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
	 * 发布
	 *
	 * @param json
	 * @param request
	 * @throws Exception
	 */
	@Override
	public void release(JSONObject json, HttpServletRequest request) throws Exception {
		int userId = json.getInteger("userId");
		int formTemplateId = json.getInteger("formTemplateId");
		String relationListNo = json.getString("relationListNo");
		String key = json.getString("key");

		String applyUserName = json.getString("applyUserName");
		String applyDepartmentName = json.getString("applyDepartmentName");
		String applyCompanyName = json.getString("applyCompanyName");

		// 删除的图片
		List<JSONObject> deleteImg = JSONObject.parseArray(json.getString("deleteImg"), JSONObject.class);
		for (JSONObject jsonObject : deleteImg) {
			String url = jsonObject.getString("url");
			FileUtil.deleteFile(PATH + url.split("imgs")[1]);
		}

		// 删除的文件
		List<JSONObject> deleteFile = JSONObject.parseArray(json.getString("deleteFile"), JSONObject.class);
		for (JSONObject jsonObject : deleteFile) {
			String url = jsonObject.getString("url");
			FileUtil.deleteFile(PATH + url.split("downloadFile")[1]);
		}

		String listNo = json.getString("listNo");

		// 查询草稿
		JSONObject draftsJson = dao.findDraftsByListNo(listNo);
		if (draftsJson == null) {
			throw new Exception("草稿不存在");
		}

		// 删除草稿
		if (dao.deleteDraftsByListNo(listNo) != 1) {
			throw new Exception("草稿保存失败");
		}

		// 删除草稿详情
		dao.deleteDraftsDetailedByApprovalRecordId(draftsJson.getInteger("id"));

		// 删除草稿表格
		dao.deleteDraftsTableByApprovalRecordId(draftsJson.getInteger("id"));

		List<JSONObject> detailedJsonList = null;
		try {
			detailedJsonList = JSONObject.parseArray(json.getString("detailed"), JSONObject.class);
		} catch (Exception e) {
			throw new Exception("参数格式不正确");
		}

		List<JSONObject> tableJsonList = null;
		try {
			tableJsonList = JSONObject.parseArray(json.getString("table"), JSONObject.class);
		} catch (Exception e) {
			throw new Exception("参数格式不正确");
		}

		json.put("state", "待审核");

		// 新增申请
		if (dao.add(json) != 1) {
			throw new Exception("提交失败");
		}

		// 查询模板表格
		List<JSONObject> tableList = dao.findTemplateTable(formTemplateId);

		if (tableList.size() > 0) {
			JSONObject tableJson = new JSONObject();
			tableJson.put("key", tableList.toString());
			tableJson.put("value", tableJsonList.toString());
			tableJson.put("approvalRecordId", json.getInteger("id"));

			// 新增审批记录表格
			dao.addApprovalRecordTable(tableJson);
		}

		/**
		 * ===========================================================================
		 * ===========================================================================
		 */
		JSONObject jsonDetailed = new JSONObject();
		jsonDetailed.put("key", "applyUserName");
		jsonDetailed.put("value", applyUserName);
		jsonDetailed.put("approvalRecordId", json.getInteger("id"));
		jsonDetailed.put("type", "parameter");

		// 新增明细
		if (dao.addDetailed(jsonDetailed) != 1) {
			throw new Exception("提交失败");
		}

		jsonDetailed.put("key", "applyDepartmentName");
		jsonDetailed.put("value", applyDepartmentName);

		// 新增明细
		if (dao.addDetailed(jsonDetailed) != 1) {
			throw new Exception("提交失败");
		}

		jsonDetailed.put("key", "applyCompanyName");
		jsonDetailed.put("value", applyCompanyName);

		// 新增明细
		if (dao.addDetailed(jsonDetailed) != 1) {
			throw new Exception("提交失败");
		}
		/**
		 * ===========================================================================
		 * ===========================================================================
		 */

		for (JSONObject detailedJson : detailedJsonList) {

			if (detailedJson.getString("type").equals("图片上传")) {
				try {

					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
					List<MultipartFile> fileList = multipartRequest.getFiles(detailedJson.getString("key"));

					// 保存源文件名跟路径
					List<JSONObject> fileResult = uploadFile(fileList);

					// 已保存的图片
					List<JSONObject> fileJsonList = JSONObject.parseArray(detailedJson.getString("value"),
							JSONObject.class);

					// 删除的图片
					for (int i = 0; i < fileJsonList.size(); i++) {
						for (int j = 0; j < deleteImg.size(); j++) {
							if (fileJsonList.get(i).getString("relativePath")
									.equals(deleteImg.get(j).getString("url").split("imgs/")[1])) {
								fileJsonList.remove(i);
								if (i == 0) {
									break;
								}
								i--;
							}
						}
					}

					for (JSONObject jsonObject : fileResult) {
						fileJsonList.add(jsonObject);
					}

					detailedJson.put("value", fileJsonList.toString());

				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("文件上传出错了");
				}

			} else if (detailedJson.getString("type").equals("附件")) {
				try {

					MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
					List<MultipartFile> fileList = multipartRequest.getFiles(detailedJson.getString("key"));

					// 保存源文件名跟路径
					List<JSONObject> fileResult = uploadFile(fileList);

					// 已保存文件
					List<JSONObject> fileJsonList = JSONObject.parseArray(detailedJson.getString("value"),
							JSONObject.class);

					// 删除的文件
					for (int i = 0; i < fileJsonList.size(); i++) {
						for (int j = 0; j < deleteFile.size(); j++) {
							if (fileJsonList.get(i).getString("relativePath")
									.equals(deleteFile.get(j).getString("url").split("downloadFile/")[1])) {
								fileJsonList.remove(i);
								if (i == 0) {
									break;
								}
								i--;
							}
						}
					}

					for (JSONObject jsonObject : fileResult) {
						fileJsonList.add(jsonObject);
					}

					detailedJson.put("value", fileJsonList.toString());

				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("文件上传出错了");
				}
			} else if (detailedJson.getString("type").equals("关联单据")) {

				detailedJson.put("value", relationListNo);

			} else {
				if (detailedJson.getString("key") == null) {
					throw new Exception("参数缺失");
				}

				if (detailedJson.getBoolean("check") != null && detailedJson.getBoolean("check")) {
					if (detailedJson.getString("value") == null) {
						throw new Exception(detailedJson.getString("key") + "不能为空");
					}
				}

			}
			detailedJson.put("approvalRecordId", json.getInteger("id"));

			// 新增明细
			if (dao.addDetailed(detailedJson) != 1) {
				throw new Exception("提交失败");
			}
		}

		JSONObject noteJson = new JSONObject();
		noteJson.put("approvalRecordId", json.getInteger("id"));
		noteJson.put("dis", "发起申请");
		noteJson.put("userId", userId);
		noteJson.put("state", "通过");
		// 新增审批备注
		dao.addApprovalRecordNote(noteJson);

		Map<String, Object> variables = new HashMap<>();
		variables.put("user", userId);

		// 通过key查询表单模板与流程关联表
		JSONObject activitiFormTemplateJson = dao.findActivitiFormTemplateByActivitiKey(key);

//		if (activitiFormTemplateJson.getString("organizationalPath") != null
//				&& !activitiFormTemplateJson.getString("organizationalPath").equals("")) {
		// 通过用户id查询用户部门信息
		JSONObject departmentJson = dao.findDepartmentByUserId(userId);

		if (departmentJson.getString("organizationalPath") == null) {
			throw new Exception("用户未绑定部门");
		}

		variables.put("organizationalPath", departmentJson.getString("organizationalPath"));
//		}

		for (JSONObject detailedJson : detailedJsonList) {
			variables.put(detailedJson.getString("key"), detailedJson.getString("value"));
		}

		// 查询用户职级
		Integer rankID = dao.findUserRankID(userId);
		if (rankID != null) {
			variables.put("职级", rankID);
		} else {
			throw new Exception("用户无职级");
		}

		if (activitiFormTemplateJson.getString("positionId") != null
				&& !activitiFormTemplateJson.getString("positionId").equals("")) {

			// 获取职位ID_xxx
			// 获取职级
			List<JSONObject> positionIdJsonList = JSONObject
					.parseArray(activitiFormTemplateJson.getString("positionId"), JSONObject.class);

			String organizationalPath = departmentJson.getString("organizationalPath");
			Integer departmentId = departmentJson.getInteger("ID");
			Integer superiorId = departmentJson.getInteger("superiorId");

			for (JSONObject positionIdJson : positionIdJsonList) {
				JSONObject userJson = null;
				switch (positionIdJson.getString("type")) {
				case "主管":
					userJson = dao.queryRankByDepartment(departmentId, 10);
					break;
				case "副主管":
					userJson = dao.queryRankByDepartment(departmentId, 20);
					break;
				case "上级主管":
					userJson = dao.queryRankByDepartment(superiorId, 10);
					break;
				case "上级副主管":
					userJson = dao.queryRankByDepartment(superiorId, 20);
					break;
				default:
					break;
				}
				try {
					String ID_ = positionIdJson.getString("ID_");
//					// 查询上级用户
//					JSONObject superiorUserJson = mapper.findSuperiorUser(ID_, userId);
					variables.put(ID_, userJson.getInteger("ID"));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

		String businessKey = json.getString("id");
		initiateApplication(key, businessKey, variables);

	}

	/**
	 * activiti 发起申请
	 *
	 * @param processDefinitionKey
	 * @param variables
	 * @throws Exception
	 */
	public void initiateApplication(String processDefinitionKey, String businessKey, Map<String, Object> variables)
			throws Exception {

	}

	/**
	 * 查询表格明细
	 *
	 * @param listNo
	 * @return
	 */
	@Override
	public JSONObject findTableDrafts(String listNo) {
		return dao.findTableDrafts(listNo);
	}

	/**
	 * 直接发布
	 *
	 * @param listNo
	 * @throws Exception
	 */
	@Override
	public void directRelease(String listNo) throws Exception {

		// 查询草稿
		JSONObject draftsJson = dao.findDraftsByListNo(listNo);
		if (draftsJson == null) {
			throw new Exception("草稿不存在");
		}

		// 查询详情
		List<JSONObject> detailedDraftsList = dao.findDetailedDraftsByListNo(listNo);

		// 查询表格详情
		JSONObject tableDrafts = dao.findTableDraftsByListNo(listNo);

		// 删除草稿
		if (dao.deleteDraftsByListNo(listNo) != 1) {
			throw new Exception("发布失败");
		}

		// 删除草稿详情
		dao.deleteDraftsDetailedByApprovalRecordId(draftsJson.getInteger("id"));

		// 删除草稿表格
		dao.deleteDraftsTableByApprovalRecordId(draftsJson.getInteger("id"));

		draftsJson.put("name", draftsJson.getString("type"));
		draftsJson.put("state", "待审核");
		draftsJson.put("key", draftsJson.getString("actKey"));
		// 新增申请
		dao.add(draftsJson);

		// 新增明细
		for (JSONObject jsonObject : detailedDraftsList) {
			jsonObject.put("approvalRecordId", draftsJson.getInteger("id"));
			dao.addDetailed(jsonObject);
		}

		List<JSONObject> tableJsonList = JSONObject.parseArray(tableDrafts.getString("value"), JSONObject.class);

		for (int i = 0; i < tableJsonList.size(); i++) {
			JSONObject value = JSONObject.parseObject(tableJsonList.get(i).getString("value"));
			JSONObject key = JSONObject.parseObject(tableJsonList.get(i).getString("key"));
			value.put("tableList", JSONObject.parseArray(key.getString("tableData"), JSONObject.class));
			tableJsonList.get(i).put("value", JSONObject.parse(value.toJSONString()));
			tableJsonList.get(i).put("key", JSONObject.parseArray(key.getString("list"), JSONObject.class));
		}

		// 查询模板表格
		List<JSONObject> tableList = dao.findTemplateTable(draftsJson.getInteger("formTemplateId"));

		if (tableList.size() > 0) {
			JSONObject tableJson = new JSONObject();
			tableJson.put("key", tableList.toString());
			tableJson.put("value", tableJsonList.toString());
			tableJson.put("approvalRecordId", draftsJson.getInteger("id"));

			// 新增审批记录表格
			dao.addApprovalRecordTable(tableJson);
		}

		JSONObject noteJson = new JSONObject();
		noteJson.put("approvalRecordId", draftsJson.getInteger("id"));
		noteJson.put("dis", "发起申请");
		noteJson.put("userId", draftsJson.getInteger("userId"));
		noteJson.put("state", "通过");
		// 新增审批备注
		dao.addApprovalRecordNote(noteJson);

		Map<String, Object> variables = new HashMap<>();
		variables.put("user", draftsJson.getInteger("userId"));

		// 通过key查询表单模板与流程关联表
		JSONObject activitiFormTemplateJson = dao.findActivitiFormTemplateByActivitiKey(draftsJson.getString("actKey"));

//		if (activitiFormTemplateJson.getString("organizationalPath") != null
//				&& !activitiFormTemplateJson.getString("organizationalPath").equals("")) {
		// 通过用户id查询用户部门信息
		JSONObject departmentJson = dao.findDepartmentByUserId(draftsJson.getInteger("userId"));

		if (departmentJson.getString("organizationalPath") == null) {
			throw new Exception("用户未绑定部门");
		}

		variables.put("organizationalPath", departmentJson.getString("organizationalPath"));
//		}

		// 查询用户职级
		Integer rankID = dao.findUserRankID(draftsJson.getInteger("userId"));
		if (rankID != null) {
			variables.put("职级", rankID);
		} else {
			throw new Exception("用户无职级");
		}

		if (activitiFormTemplateJson.getString("positionId") != null
				&& !activitiFormTemplateJson.getString("positionId").equals("")) {

			// 获取职位ID_xxx
			// 获取职级
			List<JSONObject> positionIdJsonList = JSONObject
					.parseArray(activitiFormTemplateJson.getString("positionId"), JSONObject.class);

			String organizationalPath = departmentJson.getString("organizationalPath");
			Integer departmentId = departmentJson.getInteger("ID");
			Integer superiorId = departmentJson.getInteger("superiorId");

			for (JSONObject positionIdJson : positionIdJsonList) {
				JSONObject userJson = null;
				switch (positionIdJson.getString("type")) {
				case "主管":
					userJson = dao.queryRankByDepartment(departmentId, 10);
					break;
				case "副主管":
					userJson = dao.queryRankByDepartment(departmentId, 20);
					break;
				case "上级主管":
					userJson = dao.queryRankByDepartment(superiorId, 10);
					break;
				case "上级副主管":
					userJson = dao.queryRankByDepartment(superiorId, 20);
					break;
				default:
					break;
				}
				try {
					String ID_ = positionIdJson.getString("ID_");
//					// 查询上级用户
//					JSONObject superiorUserJson = mapper.findSuperiorUser(ID_, userId);
					variables.put(ID_, userJson.getInteger("ID"));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

		String businessKey = draftsJson.getString("id");
		initiateApplication(draftsJson.getString("actKey"), businessKey, variables);

	}

}

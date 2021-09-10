package com.skeqi.mes.service.yp.oa.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.InitiateApplicationDao;
import com.skeqi.mes.service.yp.oa.ApprovalRelatedService;
import com.skeqi.mes.service.yp.oa.InitiateApplicationService;
import com.skeqi.mes.service.yp.system.SystemNewsService;
import com.skeqi.mes.util.yp.oa.Copy;
import com.skeqi.mes.util.yp.oa.ProcessUtil;

/**
 * 发起审批
 *
 * @author yinp
 * @data 2021年5月10日
 */
@Service
public class InitiateApplicationServiceImpl implements InitiateApplicationService {

	@Autowired
	InitiateApplicationDao dao;

	@Autowired
	SystemNewsService systemNewsService;

	@Autowired
	ApprovalRelatedService approvalRelatedService;

	/**
	 * 查询审批流程
	 *
	 * @return
	 */
	public List<JSONObject> queryApprovalProcess() {
		return dao.queryApprovalProcess();
	}

	/**
	 * 通过Id查询表单
	 *
	 * @param id
	 * @return
	 */
	@Override
	public JSONObject findFormById(Integer id, Integer userId) {
		// 通过Id查询表单
		JSONObject json = dao.findFormById(id);

		// 查询草稿
		JSONObject draft = dao.findDraft(userId, id);

		// 表单对象
		JSONObject detailed = json.getJSONObject("detailed");

		// 如果没有草稿说明是第一次进来
		if (draft != null) {
			detailed = draft.getJSONObject("details");
		} else {
			draft = new JSONObject();
			draft.put("userId", userId);
			draft.put("formTemplateId", id);
			draft.put("details", detailed.toString());
			// 保存草稿
			dao.saveDraft(draft);
		}

		json.put("detailed", detailed);
		json.put("draftId", draft.getInteger("id"));
		return json;
	}

	/**
	 * 查询部门
	 *
	 * @param superiorDepartmentId
	 * @return
	 */
	@Override
	public List<JSONObject> findDepartment(Integer superiorDepartmentId) {
		if (superiorDepartmentId == null) {
			superiorDepartmentId = 0;
		}
		return dao.findDepartment(superiorDepartmentId);
	}

	/**
	 * 查询部门跟用户
	 *
	 * @param superiorDepartmentId
	 * @return
	 */
	@Override
	public List<JSONObject> findDepartmentAndUser(Integer superiorDepartmentId) {
		if (superiorDepartmentId == null) {
			superiorDepartmentId = 0;
		}
		List<JSONObject> list = new ArrayList<JSONObject>();
		List<JSONObject> departmentList = dao.findDepartment(superiorDepartmentId);
		for (JSONObject jsonObject : departmentList) {
			jsonObject.put("type", "部门");
			list.add(jsonObject);
		}
		List<JSONObject> userList = dao.findUserByDepartmentId(superiorDepartmentId);
		for (JSONObject jsonObject : userList) {
			jsonObject.put("type", "用户");
			list.add(jsonObject);
		}

		return list;
	}

	/**
	 * 获取流程
	 *
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<JSONObject> acquisitionProcess(JSONObject json) throws Exception {

		String details = json.getString("details");
		String process = json.getString("process");
		Integer userId = json.getInteger("userId");

		JSONObject user = dao.findUserById(userId);

		// 表单内容集合
		List<JSONObject> detailsList = JSONObject.parseArray(details, JSONObject.class);
		// 流程json
		JSONObject processJson = JSONObject.parseObject(process);

		// 流程步序
		List<JSONObject> stepList = ProcessUtil.accessSteps(user, processJson, detailsList, dao);

		for (int i = 1; i < stepList.size(); i++) {
			if (stepList.get(i).getString("type").equals("empty")) {
				stepList.remove(i);
				i--;
				continue;
			}
			// 非自选审批人
			if (!stepList.get(i).getString("content").equals("发起人自选")) {
				// 审批
				if (stepList.get(i).getString("type").equals("approver")) {
					// 非已选用户
					if (!stepList.get(i).getJSONObject("properties").getString("assigneeType").equals("user")) {
						JSONObject properties = stepList.get(i).getJSONObject("properties");
						// 获取审批用户
						List<JSONObject> approversList = getApprovalUser(properties, userId, detailsList);

						// 审批人为空
						if (approversList.size() == 0) {
							String noApproved = properties.getString("noApproved");
							// 自动转交管理员
							if (noApproved.equals("2")) {
								JSONObject json1 = new JSONObject();
								json1.put("id", 1);
								json1.put("name", "admin");
								json1.put("fullName", "系统管理员");
								json1.put("order", approversList.size() + 1);
								approversList.add(json1);
							} else {
								stepList.remove(i);
								i--;
							}

						}
						stepList.get(i).getJSONObject("properties").put("approvers", approversList);
					}

				} else {
					// 抄送
				}
			}
		}

		return stepList;
	}

	// 获取审批用户
	public List<JSONObject> getApprovalUser(JSONObject properties, Integer userId, List<JSONObject> detailsList)
			throws Exception {
		List<JSONObject> list = new ArrayList<JSONObject>();

		// 审批人类型
		String assigneeType = properties.getString("assigneeType");

		switch (assigneeType) {
		case "directors":
			// 连续多级主管
			list = directors(userId, properties);
			break;
		case "director":
			// 部门主管
			list = director(userId, properties);
			break;
		case "zhisdirector":
			// 直属主管
			list = zhisdirector(userId, properties);
			break;
		case "formDirector":
			// 表单部门主管
			list = formDirector(userId, properties, detailsList);
			break;
		case "myself":
			// 发起人自己
			list = myself(userId);
			break;
		case "formContacts":
			// 表单联系人
			list = formContacts(userId, properties, detailsList);
			break;
		case "role":
			// 角色
			list = role(userId, properties);
			break;
		case "formRole":
			// 表单部门角色
			list = formRole(userId, properties, detailsList);
			break;
		default:
			break;
		}

		return list;
	}

	/**
	 * 表单部门角色
	 *
	 * @param userId
	 * @param properties
	 * @param detailsList
	 * @return
	 */
	public List<JSONObject> formRole(Integer userId, JSONObject properties, List<JSONObject> detailsList) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		// 表单部门角色
		JSONObject directorLevel = properties.getJSONObject("directorLevel");
		// 角色id
		Integer roleId = directorLevel.getInteger("id");

		// 表单id
		String formId = directorLevel.getString("formId");

		// 表单选择的部门集合
		List<JSONObject> selectDepartmentList = new ArrayList<JSONObject>();

		// 表单
		for (JSONObject form : detailsList) {
			// 找到表单
			if (form.getString("formId").equals(formId)) {
				// 获取到选择的部门集合
				selectDepartmentList = JSONObject.parseArray(form.getJSONObject("attribute").getString("value"),
						JSONObject.class);
			}
		}

		if (selectDepartmentList.size() == 0) {
			return list;
		}

		// 循环选择的部门集合
		for (JSONObject department : selectDepartmentList) {
			// 部门ID
			Integer departmentId = department.getInteger("id");

			JSONObject json = new JSONObject();
			json.put("roleId", roleId);
			json.put("departmentId", departmentId);

			// 通过部门and角色查询的用户
			List<JSONObject> userList = dao.findUser(json);

			for (JSONObject jsonObject : userList) {
				JSONObject user = new JSONObject();
				user.put("id", jsonObject.getString("id"));
				user.put("name", jsonObject.getString("userName"));
				user.put("fullName", jsonObject.getString("fullName"));
				user.put("order", list.size() + 1);
				list.add(user);
			}

		}

		return list;
	}

	/**
	 * 角色
	 *
	 * @param userId
	 * @param properties
	 * @return
	 */
	public List<JSONObject> role(Integer userId, JSONObject properties) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		// 角色
		JSONObject directorLevel = properties.getJSONObject("directorLevel");
		Integer roleId = directorLevel.getInteger("id");
		// 通过角色查询用户
		List<JSONObject> userList = dao.findUserByRole(roleId);

		for (JSONObject user : userList) {
			JSONObject json = new JSONObject();
			json.put("id", user.getInteger("id"));
			json.put("name", user.getString("userName"));
			user.put("fullName", user.getString("fullName"));
			json.put("order", list.size() + 1);
			list.add(json);
		}

		return list;
	}

	/**
	 * 表单联系人
	 *
	 * @param userId
	 * @param properties
	 * @param detailsList
	 * @return
	 */
	public List<JSONObject> formContacts(Integer userId, JSONObject properties, List<JSONObject> detailsList) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		// 表单联系人
		JSONObject directorLevel = properties.getJSONObject("directorLevel");

		// 表单id
		String formId = directorLevel.getString("formId");

		// 表单
		for (JSONObject form : detailsList) {
			// 找到表单
			if (form.getString("formId").equals(formId)) {
				// 获取到选择的联系人集合
				List<JSONObject> contactsList = JSONObject
						.parseArray(form.getJSONObject("attribute").getString("value"), JSONObject.class);

				if (contactsList.size() == 0) {
					return list;
				}

				for (JSONObject contacts : contactsList) {
					JSONObject json = new JSONObject();
					json.put("id", contacts.getInteger("id"));
					json.put("name", contacts.getString("name"));
					json.put("fullName", contacts.getString("fullName"));
					json.put("order", list.size() + 1);
					list.add(json);
				}

			}
		}

		return list;
	}

	/**
	 * 发起人自己
	 *
	 * @param userId
	 * @return
	 */
	public List<JSONObject> myself(Integer userId) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONObject user = dao.findUserById(userId);
		JSONObject json = new JSONObject();
		json.put("id", user.getString("id"));
		json.put("name", user.getString("userName"));
		json.put("fullName", user.getString("fullName"));
		json.put("order", list.size() + 1);
		list.add(json);
		return list;
	}

	/**
	 * 表单部门主管
	 *
	 * @param userId
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public List<JSONObject> formDirector(Integer userId, JSONObject properties, List<JSONObject> detailsList)
			throws Exception {
		List<JSONObject> list = new ArrayList<JSONObject>();
		// 表单主管
		JSONObject directorLevel = properties.getJSONObject("directorLevel");

		// 第几级主管
		Integer supervisorLevel = directorLevel.getInteger("supervisorLevel");

		// 表单id
		String formId = directorLevel.getString("formId");

		// 部门id
		Integer superiorDepartmentId = null;

		// 表单选择的部门集合
		List<JSONObject> selectDepartmentList = new ArrayList<JSONObject>();

		// 表单
		i: for (JSONObject form : detailsList) {

			if (form.getString("typeY").equals("表格") || form.getString("typeY").equals("分栏")) {
				String children = form.getString("children");
				List<JSONObject> childrenList = JSONObject.parseArray(children, JSONObject.class);
				for (JSONObject jsonObject : childrenList) {
					// 找到表单
					if (jsonObject.getString("formId").equals(formId)) {
						// 获取到选择的部门集合
						selectDepartmentList = JSONObject
								.parseArray(jsonObject.getJSONObject("attribute").getString("value"), JSONObject.class);
						break i;
					}
				}
			} else {
				// 找到表单
				if (form.getString("formId").equals(formId)) {
					// 获取到选择的部门集合
					selectDepartmentList = JSONObject.parseArray(form.getJSONObject("attribute").getString("value"),
							JSONObject.class);
					break i;
				}
			}
		}

		if (selectDepartmentList.size() == 0) {
//			throw new Exception("必填信息填写后，流程将自动显示");
			return list;
		}

		// 循环选择的部门集合
		i: for (JSONObject department : selectDepartmentList) {
			// 获取到即将要查询的部门主管的部门ID
			superiorDepartmentId = department.getInteger("id");
			b: for (int i = 0; i < supervisorLevel; i++) {
				// 找到规定层级
				if (i == supervisorLevel - 1) {
					// 查询部门主管信息
					JSONObject headOfDepartment = dao.findHeadOfDepartment(superiorDepartmentId, null);
					if (headOfDepartment != null) {
						JSONObject json = new JSONObject();
						json.put("id", headOfDepartment.getInteger("userId"));
						json.put("name", headOfDepartment.getString("userName"));
						json.put("fullName", headOfDepartment.getString("fullName"));
						json.put("order", list.size() + 1);
						list.add(json);
					}
				} else {
					// 通过部门id查询部门
					JSONObject departmentJson = dao.findDepartmentById(superiorDepartmentId);
					if (departmentJson == null || departmentJson.getString("superiorId").equals("0")) {
						continue i;
					}
					superiorDepartmentId = departmentJson.getInteger("superiorId");
				}

			}

		}
		return list;
	}

	/**
	 * 连续多级主管
	 *
	 * @param userId
	 * @param properties
	 * @return
	 */
	public List<JSONObject> directors(Integer userId, JSONObject properties) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		// 审批终点类型
		String directorLevelType = properties.getJSONObject("directorLevel").getString("type");
		// 向上部门的主管层级数
		Integer directorLevelNumber = properties.getJSONObject("directorLevel").getInteger("supervisorLevel");

		// 通过用户id查询用户
		JSONObject user = dao.findUserById(userId);

		// 指定角色
		JSONObject role = properties.getJSONObject("directorLevel").getJSONObject("roleList");
		Integer roleId = null;
		if (role != null && role.getInteger("id") != null) {
			roleId = role.getInteger("id");
		}

		// 上级部门id
		Integer superiorDepartmentId = null;

		for (int j = 0; j < directorLevelNumber; j++) {

			if (superiorDepartmentId == null) {
				superiorDepartmentId = user.getInteger("department");
			} else if (superiorDepartmentId == 0) {
				break;
			}

			// 查询部门主管信息
			JSONObject headOfDepartment = dao.findHeadOfDepartment(superiorDepartmentId, roleId);
			superiorDepartmentId = headOfDepartment.getInteger("superiorId");
			JSONObject json = new JSONObject();
			json.put("id", headOfDepartment.getInteger("userId"));
			json.put("name", headOfDepartment.getString("userName"));
			json.put("fullName", headOfDepartment.getString("fullName"));
			json.put("order", list.size() + 1);
			list.add(json);
		}
		return list;
	}

	/**
	 * 直属主管
	 *
	 * @param userId
	 * @param properties
	 * @return
	 */
	public List<JSONObject> zhisdirector(Integer userId, JSONObject properties) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		// 直属主管
		JSONObject directorLevel = properties.getJSONObject("directorLevel");
		// 用户信息
		JSONObject user = dao.findUserById(userId);
		// 直属主管用户id
		Integer reportsToUserId = user.getInteger("reportsTo");
		// 第几级主管
		Integer supervisorLevel = directorLevel.getInteger("supervisorLevel");

		if (reportsToUserId != null) {
			for (int i = 0; i < supervisorLevel; i++) {
				// 直属主管用户信息
				JSONObject reportsToUser = dao.findUserById(reportsToUserId);
				if (reportsToUser != null) {
					JSONObject json = new JSONObject();
					json.put("id", reportsToUser.getInteger("id"));
					json.put("name", reportsToUser.getString("userName"));
					json.put("fullName", reportsToUser.getString("fullName"));
					json.put("order", list.size() + 1);
					list.add(json);
					reportsToUserId = reportsToUser.getInteger("reportsTo");
				}
			}
		}

		return list;
	}

	/**
	 * 部门主管
	 *
	 * @param userId
	 * @param properties
	 * @return
	 */
	public List<JSONObject> director(Integer userId, JSONObject properties) {
		List<JSONObject> list = new ArrayList<JSONObject>();

		// 部门主管
		JSONObject directorLevel = properties.getJSONObject("directorLevel");
		// 第几级主管
		Integer supervisorLevel = directorLevel.getInteger("supervisorLevel");
		// 用户信息
		JSONObject user = dao.findUserById(userId);

		Integer superiorDepartmentId = user.getInteger("department");

		// 找不到主管时，由上级主管代审批
		Boolean useDirectorProxy = directorLevel.getBoolean("useDirectorProxy");

		i: for (int i = 0; i < supervisorLevel; i++) {

			if (i == supervisorLevel - 1) {
				// 查询部门主管信息
				JSONObject headOfDepartment = dao.findHeadOfDepartment(superiorDepartmentId, null);
				if (headOfDepartment != null) {
					JSONObject json = new JSONObject();
					json.put("id", headOfDepartment.getInteger("userId"));
					json.put("name", headOfDepartment.getString("userName"));
					json.put("fullName", headOfDepartment.getString("fullName"));
					json.put("order", list.size() + 1);
					list.add(json);
				} else {
					// 查询部门
					JSONObject department = dao.findDepartmentById(superiorDepartmentId);
					superiorDepartmentId = department.getInteger("superiorId");
					if (superiorDepartmentId == 0) {
						break i;
					}
				}
			} else {
				// 查询部门
				JSONObject department = dao.findDepartmentById(superiorDepartmentId);
				superiorDepartmentId = department.getInteger("superiorId");
				if (superiorDepartmentId == 0) {
					break i;
				}
			}
		}

		if (list.size() == 0 && useDirectorProxy) {

			// 查询部门主管信息
			JSONObject headOfDepartment = dao.findHeadOfDepartment(superiorDepartmentId, null);
			if (headOfDepartment.size() > 0) {
				JSONObject json = new JSONObject();
				json.put("id", headOfDepartment.getInteger("userId"));
				json.put("name", headOfDepartment.getString("userName"));
				json.put("fullName", headOfDepartment.getString("fullName"));
				json.put("order", list.size() + 1);
				list.add(json);
			}
		}

		return list;

	}

	/**
	 * 获取地点
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findPlaces() {
		return dao.findPlaces();
	}

	/**
	 * 获取外部联系人
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findExternalContacts() {
		return dao.findExternalContacts();
	}

	/**
	 * 更新草稿
	 *
	 * @param id
	 * @param details
	 */
	@Override
	public void updateDraft(Integer id, String details) {
		// 更新草稿
		dao.updateDraft(id, details);

	}

	/**
	 * 发布
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public String release(JSONObject json) throws Exception {
		// 发布用户
		Integer userId = json.getInteger("userId");
		// 表单id
		Integer formTemplateId = json.getInteger("formTemplateId");
		// 审批步骤
		String step = json.getString("step");
		// 表单数据
		String formData = json.getString("formData");
		// 审批名称
		String name = json.getString("name");
		// 审批步骤json
		List<JSONObject> stepList = JSONObject.parseArray(step, JSONObject.class);
		if (stepList == null || stepList.size() == 0) {
			throw new Exception("无审批步序");
		}

		// 通过Id查询表单
		JSONObject formTemplate = dao.findFormById(formTemplateId);
		json.put("callbackUrl", formTemplate.getString("tokenUrl"));

		// 查询今天表单发布的数量
		Integer number = 10000 + dao.queryTheNumberOfFormsPublishedToday() + 1;

		// 生成单据号
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyyMMddHHmmss");
		Date date = new Date();//  获取当前时间

		// 单据号
		String listNo = sdf.format(date) + number.toString();

		// 新增相关审批
		for (JSONObject jsonObject : stepList) {
			List<JSONObject> userList = null;

			JSONObject approvalRelated = new JSONObject();
			approvalRelated.put("listNo", listNo);
			if (jsonObject.getString("type").equals("approver")) {
				approvalRelated.put("type", "审批");
				// 审批参数
				JSONObject properties = jsonObject.getJSONObject("properties");
				// 审批人列表
				userList = JSONObject.parseArray(properties.getString("approvers"), JSONObject.class);

			} else if (jsonObject.getString("type").equals("copy")) {
				approvalRelated.put("type", "抄送");
				// 审批参数
				JSONObject properties = jsonObject.getJSONObject("properties");
				// 审批人列表
				userList = JSONObject.parseArray(properties.getString("menbers"), JSONObject.class);
			}

			if (userList != null) {
				for (int i = 0; i < userList.size(); i++) {
					approvalRelated.put("userId", userList.get(i).getString("id"));
					// 新增
					approvalRelatedService.add(approvalRelated);
				}
			}

		}

		// 遍历审批人 给每个审批人对象添加一个审批标识
		for (int i = 0; i < stepList.size(); i++) {

			// 如果是审批
			if (stepList.get(i).getString("type").equals("approver")) {
				// 审批参数
				JSONObject properties = stepList.get(i).getJSONObject("properties");
				// 审批人列表
				List<JSONObject> approversList = JSONObject.parseArray(properties.getString("approvers"),
						JSONObject.class);
				if (i > 0) {
					for (int j = 0; j < approversList.size(); j++) {
						approversList.get(j).put("approvalOrNot", "未批");
					}
				}
				properties.put("approvers", approversList);
				stepList.get(i).put("properties", properties.toString());
			}
		}

		// 新增审批记录表
		JSONObject approvalNote = new JSONObject();
		approvalNote.put("dis", "发起申请");
		approvalNote.put("listNo", listNo);
		approvalNote.put("userId", userId);
		approvalNote.put("state", "通过");
		approvalNote.put("type", "approver");
		approvalNote.put("step", 1);
		if (dao.addApprovalNote(approvalNote) != 1) {
			System.out.println("新增审批记录表出错了");
			System.out.println(approvalNote);
			throw new Exception("发布失败");
		}

		// 新增待审批流程记录表
		JSONObject withApproval = null;

		for (int i = 0; i < stepList.size(); i++) {
			// 审批
			if (stepList.get(i).getString("type").equals("approver")) {
				// 新增待审批流程记录表
				withApproval = stepList.get(i);
				break;
			}
			// 抄送
			if (stepList.get(i).getString("type").equals("copy")) {
				// 获取抄送人
				String menbers = stepList.get(i).getJSONObject("properties").getString("menbers");
				List<JSONObject> menbersList = JSONObject.parseArray(menbers, JSONObject.class);

				if (menbersList.size() == 0) {
					stepList.remove(i);
					i--;
				} else {
					// 抄送
					Copy.Copy(userId, listNo, name, stepList.get(i).getInteger("step"), menbersList, dao,
							systemNewsService);
				}

			}
		}

		for (int i = 0; i < stepList.size(); i++) {
			stepList.get(i).put("stepState", "未完成");
		}

		stepList.get(0).put("stepState", "完成");

		// 新增审批记录详情表
		JSONObject approvalRecordDetailed = new JSONObject();
		approvalRecordDetailed.put("step", stepList.toString());
		approvalRecordDetailed.put("formData", formData);
		approvalRecordDetailed.put("listNo", listNo);
		if (dao.addApprovalRecordDetailed(approvalRecordDetailed) != 1) {
			System.out.println("新增审批记录详情表出错了");
			System.out.println(approvalRecordDetailed);
			throw new Exception("发布失败");
		}

		for (JSONObject approvalRecordStep : stepList) {
			// 新增审批流程步序表

			try {
				JSONObject properties = new JSONObject();
				// 审批方式
				properties.put("counterSign", approvalRecordStep.getJSONObject("properties").getString("counterSign"));
				// 审批人为空时的审批方式
				properties.put("noApproved", approvalRecordStep.getJSONObject("properties").getString("noApproved"));
				// 审批人
				properties.put("approvers", approvalRecordStep.getJSONObject("properties").getString("approvers"));
				// 超时时间
				properties.put("overtime", approvalRecordStep.getJSONObject("properties").getString("overtime"));
				// 超时类型
				properties.put("overtimeType",
						approvalRecordStep.getJSONObject("properties").getString("overtimeType"));
				// 表单权限
				properties.put("formOperates",
						approvalRecordStep.getJSONObject("properties").getString("formOperates"));

				approvalRecordStep.put("listNo", listNo);
				approvalRecordStep.put("properties", properties.toString());

			} catch (Exception e) {
				approvalRecordStep.put("properties", approvalRecordStep.getString("properties"));
			}
			approvalRecordStep.put("listNo", listNo);
			if (dao.addApprovalRecordStep(approvalRecordStep) != 1) {
				System.out.println("新增审批流程记录表出错了");
				System.out.println(approvalRecordStep);
				throw new Exception("发布失败");
			}
		}

		String state = "审核中";

		// 无需审批
		if (withApproval != null) {
			// 流程参数
			JSONObject properties = withApproval.getJSONObject("properties");
			// 审批人集合
			List<JSONObject> approversList = JSONObject.parseArray(properties.getString("approvers"), JSONObject.class);
			// 多人审批时采用的审批方式
			// 1：依次审批
			// 2：会签（须所有审批人同意）
			// 3：或签（一名审批人同意或拒绝即可）
			String counterSign = properties.getString("counterSign");
			if (counterSign.equals("1")) {
				JSONObject approvalRecordWith = new JSONObject();
				approvalRecordWith.put("userId", approversList.get(0).getInteger("id"));
				approvalRecordWith.put("listNo", listNo);
				approvalRecordWith.put("overtime", properties.getString("overtime"));
				approvalRecordWith.put("timedOut", "false");
				approvalRecordWith.put("overtimeType", properties.getString("overtimeType"));
				if (dao.addApprovalRecordWith(approvalRecordWith) != 1) {
					System.out.println("新增待审批流程记录表出错了");
					System.out.println(approvalRecordWith);
					throw new Exception("发布失败");
				}

				// 新增系统通知
				JSONObject news = new JSONObject();
				news.put("title", "审批通知");
				news.put("msg", "您有一条待审批记录需要处理，请及时处理。");
				news.put("userId", approversList.get(0).getInteger("id"));
				news.put("state", "未读");
				news.put("expandData", "");
				news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);

				// 发起通知
				systemNewsService.launch(news);
			} else {
				for (JSONObject jsonObject : approversList) {
					JSONObject approvalRecordWith = new JSONObject();
					approvalRecordWith.put("userId", jsonObject.getInteger("id"));
					approvalRecordWith.put("listNo", listNo);
					approvalRecordWith.put("overtime", properties.getString("overtime"));
					approvalRecordWith.put("timedOut", "false");
					approvalRecordWith.put("overtimeType", properties.getString("overtimeType"));
					if (dao.addApprovalRecordWith(approvalRecordWith) != 1) {
						System.out.println("新增待审批流程记录表出错了");
						System.out.println(approvalRecordWith);
						throw new Exception("发布失败");
					}

					// 新增系统通知
					JSONObject news = new JSONObject();
					news.put("title", "审批通知");
					news.put("msg", "您有一条待审批记录需要处理，请及时处理。");
					news.put("userId", jsonObject.getInteger("id"));
					news.put("state", "未读");
					news.put("expandData", "");
					news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
					// 发起通知
					systemNewsService.launch(news);
				}
			}
		} else {
			state = "通过";
		}

		// 新增审批记录表
		JSONObject approvalRecord = new JSONObject();
		approvalRecord.put("userId", userId);
		approvalRecord.put("name", name);
		approvalRecord.put("state", state);
		approvalRecord.put("listNo", listNo);
		approvalRecord.put("url", json.getString("url"));
		approvalRecord.put("parameter", json.getString("parameter"));
		approvalRecord.put("callbackUrl", json.getString("callbackUrl"));
		approvalRecord.put("urgent", json.getString("urgent"));
		approvalRecord.put("overtime", formTemplate.getString("overtime"));
		approvalRecord.put("timedOut", "false");
		approvalRecord.put("overtimeType", formTemplate.getString("overtimeType"));
		if (dao.addApprovalRecord(approvalRecord) != 1) {
			System.out.println("新增审批记录表出错了");
			System.out.println(approvalRecord);
			throw new Exception("发布失败");
		}

		// 删除草稿
		dao.deleteDraft(userId, formTemplateId);

		return listNo;
	}

}

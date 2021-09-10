package com.skeqi.mes.util.yp.oa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.WaitingForMyApprovalDao;
import com.skeqi.mes.service.yp.system.SystemNewsService;
import com.skeqi.mes.util.yp.NetworkInterfaceConfig;
import com.skeqi.mes.util.yp.NewHttpClientUtil;

/**
 * 审批同意
 *
 * @author yinp
 * @data 2021年6月26日
 */
public class ApprovalAndConsent {

	/**
	 * 通过
	 *
	 * @param json
	 * @throws Exception
	 */
	public static void adopt(String userId, String listNo, String dis, WaitingForMyApprovalDao dao,
			SystemNewsService systemNewsService) throws Exception {

		// 查询单据
		JSONObject approvalRecord = dao.findApprovalRecord(listNo);

		// 查询审批人
		List<JSONObject> approvalRecordWithList = dao.findApprovalRecordWith(listNo);

		// 判断当前步骤是否是本用户审批
		for (int i = 0; i < approvalRecordWithList.size(); i++) {
			if (approvalRecordWithList.get(i).getString("userId").equals(userId)) {
				break;
			}
			if (approvalRecordWithList.size() - 1 == i) {
				throw new Exception("您无需审批");
			}
		}

		// 查询详情
		JSONObject detailed = dao.findApprovalRecordDetailed(listNo);
		// 所有步骤
		List<JSONObject> stepList = JSONObject.parseArray(detailed.getString("step"), JSONObject.class);
		// 找到当前步骤
		JSONObject nowStep = null;
		// 下一步
		JSONObject nextStep = null;
		int index = 0;
		i: for (int i = 1; i < stepList.size(); i++) {

			if (stepList.get(i).getString("type").equals("approver")) {
				// 审批人
				List<JSONObject> approvers = JSONObject.parseArray(
						stepList.get(i).getJSONObject("properties").getString("approvers"), JSONObject.class);
				for (JSONObject jsonObject : approvers) {
					if (jsonObject.getString("approvalOrNot").equals("未批")) {
						nowStep = stepList.get(i);
						index = i;
						break i;
					}
				}
			}

		}

		if (nowStep == null) {
			throw new Exception("无需审批");
		}

		// 新增审批记录表
		JSONObject approvalRecordNote = new JSONObject();
		approvalRecordNote.put("dis", dis);
		approvalRecordNote.put("listNo", listNo);
		approvalRecordNote.put("userId", userId);
		approvalRecordNote.put("state", "通过");
		approvalRecordNote.put("type", "approver");
		approvalRecordNote.put("step", nowStep.getString("step"));
		approvalRecordNote.put("list", "[]");
		if (dao.addApprovalNote(approvalRecordNote) != 1) {
			System.out.println("新增审批记录表出错了");
			throw new Exception("审批失败");
		}

		// 返回审批的结果
		JSONObject result = null;

		// 多人审批时采用的审批方式
		// 1、依次审批
		// 2、会签（须所有审批人同意）
		// 3、或签（一名审批人同意或拒绝即可）
		String counterSign = nowStep.getJSONObject("properties").getString("counterSign");
		switch (counterSign) {
		case "1":
			result = approvalInTurn(nowStep, userId);
			break;
		case "2":
			result = jointlySign(nowStep, userId);
			break;
		case "3":
			result = orSign(nowStep, userId);
			break;
		}

		// 操作结果
		nowStep = result.getJSONObject("nowStep");

		// 当前步骤是否完成
		boolean complete = result.getBoolean("complete");
		// 下一个步骤的审批人
		List<JSONObject> nextUser = new ArrayList<JSONObject>();

		if (complete) {

			nowStep.put("stepState", "完成");
		}

		// 修改原本对象的值
		stepList.set(index, nowStep);
		detailed.put("step", stepList.toString());

		// 修改详情
		if (dao.updateApprovalRecordDetailed(detailed) != 1) {
			System.out.println("修改审批详情出错了");
			throw new Exception("审批失败");
		}

		if (complete) {

			// 或签同意了
			// 该步骤其他人也就不需要审批了
			if (counterSign.equals("3")) {
				// 删除待审批流程记录表
				if (dao.deleteApprovalRecordWith(null, listNo) == 0) {
					System.out.println("删除待审批流程记录表出错了");
					throw new Exception("审批失败");
				}
			}

			// 完成
			// 判断是否走到最后一个步序了
			if (index == stepList.size() - 1) {
				// 已经最后一个步骤了
				// 修改单据状态
				if (dao.updateApprovalRecordState(listNo, "通过") != 1) {
					System.out.println("修改单据状态出错了");
					throw new Exception("审批失败");
				}

				huidiao(approvalRecord);

				// 发布系统通知
				JSONObject news = new JSONObject();
				news.put("title", "审批通知");
				news.put("msg", "您有一条待审批已通过。");
				news.put("userId", approvalRecord.getInteger("userId"));
				news.put("state", "未读");
				news.put("expandData", "");
				news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
				systemNewsService.launch(news);
			} else {

				while (true) {

					// 现在的步骤下标
					index = index + 1;
					// 目前不是最后一个步骤
					nextStep = stepList.get(index);
					// 获取下一步需要审批的人
					nextUser = getNextUser(nextStep);

					/**
					 * 抄送
					 */
					if (nextStep.getString("type").equals("copy")) {

						JSONObject user = dao.findUserById(approvalRecord.getInteger("userId"));

						for (JSONObject jsonObject : nextUser) {
							// 新增系统通知
							JSONObject news = new JSONObject();
							news.put("title", "审批消息");
							news.put("msg", user.getString("userName") + "(" + user.getString("fullName") + ")"
									+ "发起了一条" + approvalRecord.getString("name") + "审批");
							news.put("userId", jsonObject.getString("id"));
							news.put("state", "未读");
							news.put("expandData", "");
							news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
							systemNewsService.launch(news);

						}

						if (nextUser.size() > 0) {
							// 新增审批记录表
							JSONObject approvalNote = new JSONObject();
							approvalNote.put("dis", "抄送");
							approvalNote.put("listNo", listNo);
							approvalNote.put("userId", userId);
							approvalNote.put("state", "通过");
							approvalNote.put("type", "copy");
							approvalNote.put("step", nextStep.getString("step"));
							approvalNote.put("list", nextUser.toString());
							if (dao.addApprovalNote(approvalNote) != 1) {
								System.out.println("新增审批记录表出错了");
								System.out.println(approvalNote);
								throw new Exception("发布失败");
							}
						}

						// 最后一个步骤
						if (index == stepList.size() - 1) {
							// 已经没有步骤了
							// 单据审批完成
							if (dao.updateApprovalRecordState(listNo, "通过") != 1) {
								System.out.println("修改单据状态出错了");
								throw new Exception("审批失败");
							}

							huidiao(approvalRecord);

							// 发布系统通知
							JSONObject news = new JSONObject();
							news.put("title", "审批通知");
							news.put("msg", "您有一条待审批已通过。");
							news.put("userId", approvalRecord.getInteger("userId"));
							news.put("state", "未读");
							news.put("expandData", "");
							news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
							systemNewsService.launch(news);

							nextUser = new ArrayList<JSONObject>();

							break;
						}

					} else {
						break;
					}
				}
			}
		} else {
			// 未完成
			if (counterSign.equals("1")) {
				// 如果是依次审批就吧下一个审批人找到
				// 获取下一步需要审批的人
				nextUser = getNextUser(nowStep);
			}

		}

		// 删除待审批流程记录表
		dao.deleteApprovalRecordWith(userId, listNo);

		// 循环下一个需要审批的用户
		for (JSONObject user : nextUser) {
			// 发布系统通知
			JSONObject news = new JSONObject();
			news.put("title", "审批通知");
			news.put("msg", "您有一条待审批记录需要处理，请及时处理。");
			news.put("userId", user.getInteger("id"));
			news.put("state", "未读");
			news.put("expandData", "");
			news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
			systemNewsService.launch(news);

			// 新增待审批流程记录表
			JSONObject approvalRecordWith = new JSONObject();
			approvalRecordWith.put("userId", user.getInteger("id"));
			approvalRecordWith.put("listNo", listNo);
			approvalRecordWith.put("overtime", nextStep.getJSONObject("properties").getString("overtime"));
			approvalRecordWith.put("timedOut", "false");
			approvalRecordWith.put("overtimeType", nextStep.getJSONObject("properties").getString("overtimeType"));
			if (dao.addApprovalRecordWith(approvalRecordWith) != 1) {
				System.out.println("新增待审批流程记录表出错了");
				throw new Exception("审批失败");
			}
		}

	}

	// 获取下一步需要审批的人
	public static List<JSONObject> getNextUser(JSONObject nowStep) {
		List<JSONObject> nextUserList = new ArrayList<JSONObject>();
		if (nowStep.getString("type").equals("approver")) {

			// 多人审批时采用的审批方式
			// 1、依次审批
			// 2、会签（须所有审批人同意）
			// 3、或签（一名审批人同意或拒绝即可）
			String counterSign = nowStep.getJSONObject("properties").getString("counterSign");
			// 获取审批人
			List<JSONObject> approversList = JSONObject
					.parseArray(nowStep.getJSONObject("properties").getString("approvers"), JSONObject.class);

			// 找到下一步审批人
			if (counterSign.equals("1")) {
				for (JSONObject jsonObject : approversList) {
					if (jsonObject.getString("approvalOrNot").equals("未批")) {
						nextUserList.add(jsonObject);
						break;
					}
				}

			} else if (counterSign.equals("2")) {
				nextUserList = approversList;
			} else {
				nextUserList = approversList;
			}
		}
		// 抄送
		if (nowStep.getString("type").equals("copy")) {
			// 抄送人
			String menbers = nowStep.getJSONObject("properties").getString("menbers");
			nextUserList = JSONObject.parseArray(menbers, JSONObject.class);

		}

		return nextUserList;
	}

	// 或签
	public static JSONObject orSign(JSONObject nowStep, String userId) throws Exception {
		JSONObject result = new JSONObject();
		// 该步骤是否已经全部审批完成了
		result.put("complete", false);

		// 获取审批人
		List<JSONObject> approversList = JSONObject
				.parseArray(nowStep.getJSONObject("properties").getString("approvers"), JSONObject.class);
		// 找到审批的人 修改审批状态
		for (int i = 0; i < approversList.size(); i++) {
			// 找到现在能审批的人
			// 判断现在要审批的人是不是审批的人
			if (!approversList.get(i).getString("id").equals(userId)) {

				// 如果找到最后一个未批的人还不是现在审批的人
				// 那么就表示当前审批的人无需审批
				if (i == approversList.size() - 1) {
					// 如果不是就抛出异常
					throw new Exception("您无需审批");
				}

			} else {
				// 找到了需要审批的人
				approversList.get(i).put("approvalOrNot", "已批");
				// 该步骤是否已经全部审批完成了
				result.put("complete", true);
				break;
			}
		}

		// 修改审批详情
		JSONObject properties = nowStep.getJSONObject("properties");
		properties.put("approvers", approversList);
		nowStep.put("properties", properties);

		result.put("nowStep", nowStep);

		return result;
	}

	// 会签
	public static JSONObject jointlySign(JSONObject nowStep, String userId) throws Exception {
		JSONObject result = new JSONObject();
		// 该步骤是否已经全部审批完成了
		result.put("complete", true);

		// 获取审批人
		List<JSONObject> approversList = JSONObject
				.parseArray(nowStep.getJSONObject("properties").getString("approvers"), JSONObject.class);
		// 找到审批的人 修改审批状态
		for (int i = 0; i < approversList.size(); i++) {
			// 找到现在能审批的人
			if (approversList.get(i).getString("approvalOrNot").equals("未批")) {
				// 判断现在要审批的人是不是审批的人
				if (!approversList.get(i).getString("id").equals(userId)) {

					// 如果找到最后一个未批的人还不是现在审批的人
					// 那么就表示当前审批的人无需审批
					if (i == approversList.size() - 1) {
						// 如果不是就抛出异常
						throw new Exception("您无需审批");
					}

				} else {
					// 找到了需要审批的人
					approversList.get(i).put("approvalOrNot", "已批");
					break;
				}
			}
		}

		for (int i = 0; i < approversList.size(); i++) {
			// 找到未审批的人
			if (approversList.get(i).getString("approvalOrNot").equals("未批")) {
				// 找到一个还未审批的人
				// 表示该步骤还未走完
				// 该步骤是否已经全部审批完成了
				result.put("complete", false);
			}
		}

		// 修改审批详情
		JSONObject properties = nowStep.getJSONObject("properties");
		properties.put("approvers", approversList);
		nowStep.put("properties", properties);

		result.put("nowStep", nowStep);

		return result;
	}

	// 依次审批
	public static JSONObject approvalInTurn(JSONObject nowStep, String userId) throws Exception {
		JSONObject result = new JSONObject();
		// 该步骤是否已经全部审批完成了
		result.put("complete", false);

		// 获取审批人
		List<JSONObject> approversList = JSONObject
				.parseArray(nowStep.getJSONObject("properties").getString("approvers"), JSONObject.class);
		for (int i = 0; i < approversList.size(); i++) {
			// 找到现在能审批的人
			if (approversList.get(i).getString("approvalOrNot").equals("未批")) {
				// 判断现在要审批的人是不是审批的人
				if (!approversList.get(i).getString("id").equals(userId)) {
					// 如果不是就抛出异常
					throw new Exception("您无需审批");
				} else {
					approversList.get(i).put("approvalOrNot", "已批");
					if (i == approversList.size() - 1) {
						// 当前步骤已经全部完成
						result.put("complete", true);
					}
					break;
				}
			}
		}

		// 修改审批详情
		JSONObject properties = nowStep.getJSONObject("properties");
		properties.put("approvers", approversList);
		nowStep.put("properties", properties);

		result.put("nowStep", nowStep);

		return result;
	}

	public void copy() {

	}

	// 回调
	static void huidiao(JSONObject approvalRecord) throws Exception {
		// 回调地址
		String callbackUrl = approvalRecord.getString("callbackUrl");
		if (callbackUrl == null || callbackUrl.equals("")) {
			return;
		}
		// 回调参数
		JSONObject parameter = approvalRecord.getJSONObject("parameter");
		if (parameter == null) {
			throw new Exception("回调参数为空");
		}
		parameter.put("name", "通过");

		// 获取当前机器端口号
		String port = NetworkInterfaceConfig.getLocalPort();
		JSONObject result1 = null;
		try {
			String url = "http://127.0.0.1:" + port + "/" + callbackUrl;
			result1 = NewHttpClientUtil.deviceRequest(url, parameter);
		} catch (Exception e) {
			throw new Exception("回调失败");
		}
		if (!result1.getString("code").equals("200")) {
			throw new Exception("回调失败," + result1.getString("msg"));
		}
	}

}

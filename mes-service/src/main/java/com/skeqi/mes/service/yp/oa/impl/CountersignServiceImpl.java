package com.skeqi.mes.service.yp.oa.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.CountersignDao;
import com.skeqi.mes.service.yp.oa.CountersignService;
import com.skeqi.mes.service.yp.system.SystemNewsService;
import com.skeqi.mes.util.yp.oa.Check;

/**
 * 加签
 *
 * @author yinp
 * @data 2021年6月29日
 */
@Service
public class CountersignServiceImpl implements CountersignService {

	@Autowired
	CountersignDao dao;

	@Autowired
	SystemNewsService systemNewsService;

	/**
	 * 加签提交
	 *
	 * @param listNo
	 * @param userId
	 * @param countersignUser
	 * @param dis
	 * @throws Exception
	 */
	@Override
	public void sub(String listNo, Integer userId, String countersignUser, String signatureMethod, String dis)
			throws Exception {

		// 加签用户集合
		List<JSONObject> countersignUserList = JSONObject.parseArray(countersignUser, JSONObject.class);
		for (JSONObject jsonObject : countersignUserList) {
			if (jsonObject.getString("id").equals(userId.toString())) {
				throw new Exception("加签人列表不能有当前操作人");
			}
		}

		// 查询审批详情
		JSONObject approvalDetailed = dao.findApprovalDetailed(listNo);
		// 步骤
		String step = approvalDetailed.getString("step");
		List<JSONObject> stepList = JSONObject.parseArray(step, JSONObject.class);

		// 检查是否需要审批
		Check check = new Check();
		check.runCheck(step, userId);

		// 当前步骤
		JSONObject nowStep = new JSONObject();
		for (int i = 0; i < stepList.size(); i++) {
			if (stepList.get(i).getString("type").equals("approver")) {
				if (stepList.get(i).getString("stepState").equals("未完成")) {
					nowStep = stepList.get(i);
					break;
				}
			}
		}

		// 审批参数
		JSONObject properties = nowStep.getJSONObject("properties");
		// 审批类型
		String counterSign = properties.getString("counterSign");
		// 审批人
		String approvers = properties.getString("approvers");
		List<JSONObject> approversList = JSONObject.parseArray(approvers, JSONObject.class);

		for (JSONObject jsonObject : approversList) {
			for (JSONObject jsonObject2 : countersignUserList) {
				if (jsonObject.getString("id").equals(jsonObject2.getString("id"))) {
					throw new Exception("加签审批人已存在，请检查后再提交");
				}
			}
		}

		i: for (int i = 0; i < stepList.size(); i++) {
			if (stepList.get(i).getString("type").equals("approver")) {

				// 审批参数
				properties = stepList.get(i).getJSONObject("properties");
				// 审批类型
				counterSign = properties.getString("counterSign");
				// 审批人
				approvers = properties.getString("approvers");
				approversList = JSONObject.parseArray(approvers, JSONObject.class);
				if (counterSign.equals("1")) {
					// 依次审批
					for (int j = 0; j < approversList.size(); j++) {
						if (approversList.get(j).getString("approvalOrNot").equals("未批")) {
							if (approversList.get(j).getString("id").equals(userId.toString())) {
								List<JSONObject> userList = new ArrayList<JSONObject>();
								if (signatureMethod.equals("在我之前")) {
									for (JSONObject jsonObject : countersignUserList) {
										jsonObject.put("approvalOrNot", "未批");
										userList.add(jsonObject);
									}
									for (JSONObject jsonObject : approversList) {
										userList.add(jsonObject);
									}

								} else {
									for (int k = 0; k <= j; k++) {
										userList.add(approversList.get(k));
									}
									for (JSONObject jsonObject : countersignUserList) {
										jsonObject.put("approvalOrNot", "未批");
										userList.add(jsonObject);
									}
									for (int k = j + 1; k < approversList.size(); k++) {
										userList.add(approversList.get(k));
									}
								}
								properties.put("approvers", userList.toString());
								stepList.get(i).put("properties", properties);
								break i;
							} else {
								throw new Exception("您无需操作");
							}
						}
					}
				} else {
					// 会签、或签
					if (stepList.get(i).getString("stepState").equals("未完成")) {
						List<JSONObject> userList = new ArrayList<JSONObject>();
						for (JSONObject jsonObject : approversList) {
							userList.add(jsonObject);
						}
						for (JSONObject jsonObject : countersignUserList) {
							jsonObject.put("approvalOrNot", "未批");
							userList.add(jsonObject);
						}
						properties.put("approvers", userList.toString());
						stepList.get(i).put("properties", properties);
						break i;
					}
				}
			}
		}

		// 更新审批详情步骤
		dao.updateApprovalDetailedStep(listNo, stepList.toString());

		// 新增审批记录
		JSONObject aapprovalNote = new JSONObject();
		aapprovalNote.put("dis", dis);
		aapprovalNote.put("listNo", listNo);
		aapprovalNote.put("userId", userId);
		aapprovalNote.put("state", "countersign");
		aapprovalNote.put("type", "countersign");
		aapprovalNote.put("step", 0);
		aapprovalNote.put("list", countersignUser);
		dao.addAapprovalNote(aapprovalNote);

		// 审批参数
		properties = nowStep.getJSONObject("properties");
		// 审批类型
		counterSign = properties.getString("counterSign");

		for (JSONObject userJosn : countersignUserList) {

			if (counterSign.equals("1")) {

				if (signatureMethod.equals("在我之前")) {
					// 删除待审批记录
					dao.deleteApprovalWith(userId, listNo);

					// 新增待审批记录
					JSONObject approvalWith = new JSONObject();
					approvalWith.put("userId", userJosn.getInteger("id"));
					approvalWith.put("listNo", listNo);
					dao.addApprovalWith(approvalWith);

					// 发布系统通知
					JSONObject news = new JSONObject();
					news.put("title", "审批通知");
					news.put("msg", "您有一条待审批记录需要处理，请及时处理。");
					news.put("userId", userJosn.getString("id"));
					news.put("state", "未读");
					news.put("expandData", "");
					news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
					systemNewsService.launch(news);
				}

				break;
			} else {
				// 新增待审批记录
				JSONObject approvalWith = new JSONObject();
				approvalWith.put("userId", userJosn.getInteger("id"));
				approvalWith.put("listNo", listNo);
				dao.addApprovalWith(approvalWith);

				// 发布系统通知
				JSONObject news = new JSONObject();
				news.put("title", "审批通知");
				news.put("msg", "您有一条待审批记录需要处理，请及时处理。");
				news.put("userId", userJosn.getString("id"));
				news.put("state", "未读");
				news.put("expandData", "");
				news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
				systemNewsService.launch(news);
			}

		}

	}

}

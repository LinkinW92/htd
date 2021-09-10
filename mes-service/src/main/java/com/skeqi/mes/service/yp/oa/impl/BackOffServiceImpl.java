package com.skeqi.mes.service.yp.oa.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.BackOffDao;
import com.skeqi.mes.service.yp.oa.BackOffService;
import com.skeqi.mes.service.yp.system.SystemNewsService;

/**
 * 回退
 *
 * @author yinp
 * @data 2021年6月29日
 */
@Service
public class BackOffServiceImpl implements BackOffService {

	@Autowired
	BackOffDao dao;

	@Autowired
	SystemNewsService systemNewsService;

	/**
	 * 查询已批准的步骤
	 *
	 * @param listNo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<JSONObject> findAlreadyStep(String listNo, Integer userId) throws Exception {

		// 查询审批详情
		JSONObject approvalDetailed = dao.findApprovalDetailed(listNo);
		// 步骤
		String step = approvalDetailed.getString("step");
		List<JSONObject> stepList = JSONObject.parseArray(step, JSONObject.class);

		// 只保留审批的
		for (int i = 0; i < stepList.size(); i++) {
			if (!stepList.get(i).getString("type").equals("approver")) {
				stepList.remove(i);
				i--;
			}
		}

		int num = 0;
		// 只保留已经全部审批的
		i: for (int i = 0; i < stepList.size(); i++) {
			// 审批人
			String approvers = stepList.get(i).getJSONObject("properties").getString("approvers");
			List<JSONObject> approversList = JSONObject.parseArray(approvers, JSONObject.class);

			for (int j = 0; j < approversList.size(); j++) {
				if (approversList.get(j).getString("approvalOrNot").equals("未批")) {
					if (!approversList.get(j).getString("id").equals(userId.toString())) {
						throw new Exception("您无需操作");
					}
					num = i;
					break i;
				}
			}
		}

		List<JSONObject> list = new ArrayList<JSONObject>();
		for (int i = 0; i < num; i++) {
			list.add(stepList.get(i));
		}

		return list;
	}

	/**
	 * 查询已批准的步骤
	 *
	 * @param listNo
	 * @param userId
	 * @param step
	 * @return
	 */
	@Override
	public void sub(String listNo, Integer userId, Integer step, String dis) throws Exception {
		// 查询已批准的步骤
		List<JSONObject> stepList = findAlreadyStep(listNo, userId);

		// 回退的步序
		JSONObject backOffStep = new JSONObject();

		for (int i = 0; i < stepList.size(); i++) {
			if (stepList.get(i).getString("step").equals(step.toString())) {
				backOffStep = stepList.get(i);
				break;
			}
			if (i == stepList.size() - 1) {
				throw new Exception("步序有误");
			}
		}

		// 查询审批详情
		JSONObject approvalDetailed = dao.findApprovalDetailed(listNo);
		// 步骤
		String stepString = approvalDetailed.getString("step");
		stepList = JSONObject.parseArray(stepString, JSONObject.class);

		for (int j = 0; j < stepList.size(); j++) {
			if (stepList.get(j).getInteger("step") >= step) {
				stepList.get(j).put("stepState", "未完成");
			}
		}

		for (int i = 0; i < stepList.size(); i++) {
			if (stepList.get(i).getString("type").equals("approver")) {
				if (stepList.get(i).getInteger("step") >= step) {
					JSONObject properties = stepList.get(i).getJSONObject("properties");
					// 审批人
					String approvers = properties.getString("approvers");
					List<JSONObject> approversList = JSONObject.parseArray(approvers, JSONObject.class);
					for (int j = 0; j < approversList.size(); j++) {
						approversList.get(j).put("approvalOrNot", "未批");
					}

					approvers = approversList.toString();
					properties.put("approvers", approversList.toString());

					stepList.get(i).put("properties", properties);

				}
			}
		}

		// 更新审批详情步骤
		dao.updateApprovalDetailedStep(listNo, stepList.toString());

		// 通过id查询用户
		JSONObject user = dao.findUserById(userId);

		// 新增审批记录
		JSONObject aapprovalNote = new JSONObject();
		aapprovalNote.put("dis", dis);
		aapprovalNote.put("listNo", listNo);
		aapprovalNote.put("userId", userId);
		aapprovalNote.put("state", "backOff");
		aapprovalNote.put("type", "backOff");
		aapprovalNote.put("step", 0);
		aapprovalNote.put("list", backOffStep.toString());
		dao.addAapprovalNote(aapprovalNote);

		// 删除待审批记录
		dao.deleteApprovalWith(null, listNo);

		JSONObject properties = backOffStep.getJSONObject("properties");

		// 审批类型
		String counterSign = properties.getString("counterSign");

		// 审批人
		String approversString = properties.getString("approvers");
		List<JSONObject> approvers = JSONObject.parseArray(approversString, JSONObject.class);

		if (counterSign.equals("1")) {
			// 新增待审批记录
			JSONObject approvalWith = new JSONObject();
			approvalWith.put("userId", approvers.get(0).getString("id"));
			approvalWith.put("listNo", listNo);
			dao.addApprovalWith(approvalWith);

			// 发布系统通知
			JSONObject news = new JSONObject();
			news.put("title", "审批通知");
			news.put("msg", user.getString("userName") + "(" + user.getString("fullName") + ")" + "回退至"
					+ backOffStep.getString("title") + "请及时处理。");
			news.put("userId", approvers.get(0).getString("id"));
			news.put("state", "未读");
			news.put("expandData", "");
			news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
			systemNewsService.launch(news);

		} else {
			for (JSONObject jsonObject : approvers) {
				// 新增待审批记录
				JSONObject approvalWith = new JSONObject();
				approvalWith.put("userId", jsonObject.getString("id"));
				approvalWith.put("listNo", listNo);
				dao.addApprovalWith(approvalWith);

				// 发布系统通知
				JSONObject news = new JSONObject();
				news.put("title", "审批通知");
				news.put("msg", user.getString("userName") + "(" + user.getString("fullName") + ")" + "回退至"
						+ backOffStep.getString("title") + "请及时处理。");
				news.put("userId", jsonObject.getString("id"));
				news.put("state", "未读");
				news.put("expandData", "");
				news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
				systemNewsService.launch(news);
			}
		}
	}

}

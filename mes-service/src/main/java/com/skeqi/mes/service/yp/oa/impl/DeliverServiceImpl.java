package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.DeliverDao;
import com.skeqi.mes.service.yp.oa.DeliverService;
import com.skeqi.mes.service.yp.system.SystemNewsService;
import com.skeqi.mes.util.yp.oa.Check;

/**
 * 转交
 *
 * @author yinp
 * @data 2021年6月29日
 */
@Service
public class DeliverServiceImpl implements DeliverService {

	@Autowired
	DeliverDao dao;

	@Autowired
	SystemNewsService systemNewsService;

	/**
	 * 提交
	 *
	 * @param listNo
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public void sub(String listNo, Integer userId, Integer deliverUserId, String dis) throws Exception {

		// 查询审批详情
		JSONObject approvalDetailed = dao.findApprovalDetailed(listNo);

		JSONObject deliverUser = dao.findUserById(deliverUserId);
		JSONObject user = dao.findUserById(userId);

		// 步骤
		String step = approvalDetailed.getString("step");
		List<JSONObject> stepList = JSONObject.parseArray(step, JSONObject.class);

		// 检查是否需要审批
		Check check = new Check();
		check.runCheck(step, userId);

		i: for (int i = 0; i < stepList.size(); i++) {
			JSONObject properties = stepList.get(i).getJSONObject("properties");
			// 审批
			if (stepList.get(i).getString("type").equals("approver")) {
				// 审批方式
				String counterSign = properties.getString("counterSign");

				// 审批人
				String approvers = properties.getString("approvers");
				List<JSONObject> approversList = JSONObject.parseArray(approvers, JSONObject.class);

				if (counterSign.equals("1")) {
					// 依次审批
					for (int j = 0; j < approversList.size(); j++) {
						if (approversList.get(j).getString("approvalOrNot").equals("未批")) {

							if (approversList.get(j).getString("id").equals(userId.toString())) {
								approversList.get(j).put("id", deliverUserId);
								approversList.get(j).put("name", deliverUser.getString("userName"));
								approversList.get(j).put("fullName", deliverUser.getString("fullName"));

								properties.put("approvers", approversList.toString());
								stepList.get(i).put("properties", properties);
								break i;
							} else {
								throw new Exception("您无需操作");
							}

						}
					}
				} else {
					// 会签、或签
					// 依次审批
					for (int j = 0; j < approversList.size(); j++) {
						if (approversList.get(j).getString("approvalOrNot").equals("未批")) {
							if (approversList.get(j).getString("id").equals(userId.toString())) {
								approversList.get(j).put("id", deliverUserId);
								approversList.get(j).put("name", deliverUser.getString("userName"));

								properties.put("approvers", approversList.toString());
								stepList.get(i).put("properties", properties);
								break i;
							}

							if (j == approversList.size() - 1) {
								throw new Exception("您无需操作");
							}
						}
					}
				}

			}
		}

		// 更新审批详情
		dao.updateApprovalDetailed(listNo, stepList.toString());

		// 删除待审批记录
		dao.deleteApprovalWith(listNo, userId);

		// 新增待审批记录
		JSONObject approvalWith = new JSONObject();
		approvalWith.put("userId", deliverUserId);
		approvalWith.put("listNo", listNo);
		dao.addApprovalWith(approvalWith);

		// 新增审批记录
		JSONObject approvalNote = new JSONObject();
		approvalNote.put("dis", dis);
		approvalNote.put("listNo", listNo);
		approvalNote.put("userId", userId);
		approvalNote.put("state", "Deliver");
		approvalNote.put("type", "deliver");
		approvalNote.put("step", 0);
		approvalNote.put("list", deliverUser.toString());
		dao.addApprovalNote(approvalNote);

		// 发布系统通知
		JSONObject news = new JSONObject();
		news.put("title", "审批通知");
		news.put("msg", user.getString("userName") + "(" + user.getString("fullName") + ")" + "转交给您" + "请及时处理。");
		news.put("userId", deliverUser.getString("id"));
		news.put("state", "未读");
		news.put("expandData", "");
		news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
		systemNewsService.launch(news);

	}

}

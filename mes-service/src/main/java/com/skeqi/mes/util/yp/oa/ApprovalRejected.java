package com.skeqi.mes.util.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.WaitingForMyApprovalDao;
import com.skeqi.mes.service.yp.system.SystemNewsService;

/**
 * 审批拒绝
 *
 * @author yinp
 * @data 2021年6月26日
 */
public class ApprovalRejected {

	/**
	 * 审批拒绝
	 *
	 * @param userId
	 * @param listNo
	 * @param dis
	 * @param dao
	 * @param systemNewsService
	 * @throws Exception
	 */
	public static void reject(String userId, String listNo, String dis, WaitingForMyApprovalDao dao,
			SystemNewsService systemNewsService) throws Exception {
		//查询单据
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
		int index = 0;
		i: for (int i = 1; i < stepList.size(); i++) {
			if(stepList.get(i).getString("type").equals("approver")) {
				// 审批人
				List<JSONObject> approvers = JSONObject
						.parseArray(stepList.get(i).getJSONObject("properties").getString("approvers"), JSONObject.class);
				for (JSONObject jsonObject : approvers) {
					if (jsonObject.getString("approvalOrNot").equals("未批")) {
						nowStep = stepList.get(i);
						index = i;
						break i;
					}
				}
			}
		}

		// 删除待审批流程记录表
		dao.deleteApprovalRecordWith(null, listNo);

		// 新增审批记录表
		JSONObject approvalRecordNote = new JSONObject();
		approvalRecordNote.put("dis", dis);
		approvalRecordNote.put("listNo", listNo);
		approvalRecordNote.put("userId", userId);
		approvalRecordNote.put("state", "驳回");
		approvalRecordNote.put("type", "approver");
		approvalRecordNote.put("step", nowStep.getString("step"));
		approvalRecordNote.put("list", "[]");
		if (dao.addApprovalNote(approvalRecordNote) != 1) {
			System.out.println("新增审批记录表出错了");
			throw new Exception("审批失败");
		}

		// 单据审批完成
		if (dao.updateApprovalRecordState(listNo, "未通过") != 1) {
			System.out.println("修改单据状态出错了");
			throw new Exception("审批失败");
		}

	}

}

package com.skeqi.mes.util.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 检查是否需要审批
 *
 * @author yinp
 *
 */
public class Check {

	public void runCheck(String step, Integer userId) throws Exception {
		List<JSONObject> stepList = JSONObject.parseArray(step, JSONObject.class);

		// 只保留审批
		for (int i = 0; i < stepList.size(); i++) {
			if (!stepList.get(i).getString("type").equals("approver")) {
				stepList.remove(i);
				i--;
			}
		}

		for (int i = 0; i < stepList.size(); i++) {
			JSONObject properties = stepList.get(i).getJSONObject("properties");
			// 审批方式
			String counterSign = properties.getString("counterSign");

			// 审批人
			String approvers = properties.getString("approvers");
			List<JSONObject> approversList = JSONObject.parseArray(approvers, JSONObject.class);

			if (counterSign.equals("1")) {
				// 依次审批
				if (stepList.get(i).getString("stepState").equals("未完成")) {
					for (int j = 0; j < approversList.size(); j++) {
						if (approversList.get(j).getString("approvalOrNot").equals("未批")) {
							if (approversList.get(j).getString("id").equals(userId.toString())) {
								return;
							}
							throw new Exception("您无需操作");
						}
						if (j == approversList.size() - 1) {
							throw new Exception("您无需操作");
						}
					}
				}

			} else {
				// 会签、或签
				if (stepList.get(i).getString("stepState").equals("未完成")) {
					for (int j = 0; j < approversList.size(); j++) {
						if (approversList.get(j).getString("id").equals(userId.toString())) {
							return;
						}
						if (j == approversList.size() - 1) {
							throw new Exception("您无需操作");
						}
					}
				}
			}
		}
	}

}

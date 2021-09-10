package com.skeqi.mes.util.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.InitiateApplicationDao;
import com.skeqi.mes.mapper.yp.oa.WaitingForMyApprovalDao;
import com.skeqi.mes.service.yp.system.SystemNewsService;

/**
 * 抄送
 *
 * @author yinp
 *
 */
public class Copy {

	/**
	 * 抄送
	 *
	 * @param userId
	 * @param listNo
	 * @param dao
	 * @param systemNewsService
	 * @throws Exception
	 */
	public static void Copy(Integer userId, String listNo, String name, Integer step, List<JSONObject> copyUserList,
			InitiateApplicationDao dao, SystemNewsService systemNewsService) throws Exception {

		// 通过用户id查询用户
		JSONObject user = dao.findUserById(userId);

		for (JSONObject jsonObject : copyUserList) {
			// 新增系统通知
			JSONObject news = new JSONObject();
			news.put("title", "审批消息");
			news.put("msg",
					user.getString("userName") + "(" + user.getString("fullName") + ")" + "发起了一条" + name + "审批");
			news.put("userId", jsonObject.getString("id"));
			news.put("state", "未读");
			news.put("expandData", "");
			news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
			systemNewsService.launch(news);

		}
		// 新增审批记录表
		JSONObject approvalNote = new JSONObject();
		approvalNote.put("dis", "抄送");
		approvalNote.put("listNo", listNo);
		approvalNote.put("userId", userId);
		approvalNote.put("state", "通过");
		approvalNote.put("type", "copy");
		approvalNote.put("step", step);
		approvalNote.put("list", copyUserList.toString());
		if (dao.addApprovalNote(approvalNote) != 1) {
			System.out.println("新增审批记录表出错了");
			System.out.println(approvalNote);
			throw new Exception("发布失败");
		}

	}

}

package com.skeqi.mes.service.yp.oa.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.skeqi.mes.util.yp.HttpRequestUtil;
import com.skeqi.mes.util.yp.NetworkInterfaceConfig;
import com.skeqi.mes.util.yp.NewHttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.skeqi.mes.mapper.yp.oa.WaitingForMyApprovalDao;
import com.skeqi.mes.service.yp.oa.WaitingForMyApprovalService;
import com.skeqi.mes.service.yp.system.SystemNewsService;
import com.skeqi.mes.util.yp.oa.ApprovalAndConsent;
import com.skeqi.mes.util.yp.oa.ApprovalRejected;

/**
 * 待我审批
 *
 * @author yinp
 * @data 2021年5月10日
 */
@Service
public class WaitingForMyApprovalServiceImpl implements WaitingForMyApprovalService {

	@Autowired
	WaitingForMyApprovalDao dao;

	@Autowired
	SystemNewsService systemNewsService;

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
	 * 查询明细
	 *
	 * @param listNo
	 * @param userId
	 * @return
	 */
	public JSONObject queryDetails(String listNo, Integer userId) {
		JSONObject json = dao.queryDetails(listNo);
		json.put("formData", JSONObject.parseArray(json.getString("formData"), JSONObject.class));

		List<JSONObject> stepList = JSONObject.parseArray(json.getString("step"), JSONObject.class);
		List<JSONObject> asd = JSONObject.parseArray(json.getString("step"), JSONObject.class);
		stepList.remove(0);

		JSONObject nowStep = new JSONObject();

		i: for (int i = 0; i < stepList.size(); i++) {
			String type = stepList.get(i).getString("type");
			if (type.equals("copy")) {
				stepList.remove(i);
				i--;
				continue;
			}
			if (type.equals("approver")) {
				String approvers = stepList.get(i).getJSONObject("properties").getString("approvers");
				List<JSONObject> approversList = JSONObject.parseArray(approvers, JSONObject.class);
				for (int j = 0; j < approversList.size(); j++) {

					if (approversList.get(j).getString("approvalOrNot").equals("未批")) {
						nowStep = stepList.get(i);
						break i;
					}

					if (j == approversList.size() - 1) {
						stepList.remove(i);
						i--;
					}
				}
			}

		}

		for (int i = 0; i < stepList.size(); i++) {
			if (stepList.get(i).getString("type").equals("copy")) {
				String menbers = stepList.get(i).getJSONObject("properties").getString("menbers");
				List<JSONObject> menbersList = JSONObject.parseArray(menbers, JSONObject.class);
				if (menbersList.size() == 0) {
					stepList.remove(i);
					i--;
				}
			}
			JSONObject properties = stepList.get(i).getJSONObject("properties");
			properties.put("approvers", JSONObject.parseArray(properties.getString("approvers"), JSONObject.class));
			stepList.get(i).put("properties", properties);
		}

		json.put("step", stepList);

		// 查询审批记录
		List<JSONObject> noteList = dao.findApprovalRecordNote(listNo);

		// 确认该用户是否可以操作
		// 是否可批
		json.put("canItBeApproved", false);

		JSONObject properties = nowStep.getJSONObject("properties");
		if (properties != null) {

			String approvers = properties.getString("approvers");
			String counterSign = properties.getString("counterSign");
			List<JSONObject> approverss = JSONObject.parseArray(approvers, JSONObject.class);
			for (JSONObject jsonObject : approverss) {
				if (counterSign.equals("1")) {
					if (jsonObject.getString("approvalOrNot").equals("未批")) {
						if (jsonObject.getString("id").equals(userId.toString())) {
							json.put("canItBeApproved", true);
							break;
						} else {
							break;
						}
					}
				} else {
					if (jsonObject.getString("id").equals(userId.toString())) {
						json.put("canItBeApproved", true);
						break;
					}
				}
			}
		}

		i: for (JSONObject step : asd) {

			if (step.getString("type").equals("approver")) {
				JSONObject properties1 = step.getJSONObject("properties");
				List<JSONObject> approversList = JSONObject.parseArray(properties1.getString("approvers"),
						JSONObject.class);
				for (JSONObject approvers : approversList) {
					if (approvers.getString("id").equals(userId.toString())) {
						List<JSONObject> formOperatesList = JSONObject.parseArray(properties1.getString("formOperates"),
								JSONObject.class);
						json.put("formOperatesList", formOperatesList);
						break i;
					}
				}
			}

		}

		json.put("note", noteList);
		json.put("nowStep", nowStep);
		return json;
	}

	/**
	 * 审批
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void Approved(JSONObject json) throws Exception {
		String type = json.getString("type");
		String formData = json.getString("formData");
		if (formData != null && !formData.equals("")) {
			try {
				List<JSONObject> form = JSONObject.parseArray(formData, JSONObject.class);
				json.put("form", formData);
				// 修改表单
				updateForm(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (type.equals("同意")) {
			adopt(json);
		}

		if (type.equals("拒绝")) {
			reject(json);
		}
	}

	/**
	 * 通过
	 *
	 * @param json
	 * @throws Exception
	 */
	public void adopt(JSONObject json) throws Exception {
		String userId = json.getString("userId");
		String listNo = json.getString("listNo");
		String dis = json.getString("dis");

		// 通过
		ApprovalAndConsent.adopt(userId, listNo, dis, dao, systemNewsService);

		// 新增我的已批记录
		JSONObject approvalBatchRecords = new JSONObject();
		approvalBatchRecords.put("listNo", listNo);
		approvalBatchRecords.put("userId", userId);
		approvalBatchRecords.put("state", "通过");
		dao.addApprovalBatchRecords(approvalBatchRecords);

	}

	/**
	 * 驳回
	 *
	 * @throws Exception
	 */
	public void reject(JSONObject json) throws Exception {

		String userId = json.getString("userId");
		String listNo = json.getString("listNo");
		String dis = json.getString("dis");

		// 驳回
		ApprovalRejected.reject(userId, listNo, dis, dao, systemNewsService);

		// 查询单据
		JSONObject approvalRecord = dao.findApprovalRecord(listNo);
		if (approvalRecord == null) {
			throw new Exception("单据不存在");
		}

		// 新增我的已批记录
		JSONObject approvalBatchRecords = new JSONObject();
		approvalBatchRecords.put("listNo", listNo);
		approvalBatchRecords.put("userId", userId);
		approvalBatchRecords.put("state", "驳回");
		dao.addApprovalBatchRecords(approvalBatchRecords);

		// 回调地址
		String callbackUrl = approvalRecord.getString("callbackUrl");
		if (callbackUrl != null && !callbackUrl.equals("")) {

			// 回调参数
			JSONObject parameter = approvalRecord.getJSONObject("parameter");
			if (parameter == null) {
				throw new Exception("回调参数为空");
			}
			parameter.put("name", "驳回");

			// 获取当前机器端口号
			String port = NetworkInterfaceConfig.getLocalPort();
			JSONObject result = null;

			try {
				String url = "http://127.0.0.1:" + port + "/" + callbackUrl;
				result = NewHttpClientUtil.deviceRequest(url, parameter);
			} catch (Exception e) {
				throw new Exception("回调失败");
			}
			if (!result.getString("code").equals("200")) {
				throw new Exception("回调失败," + result.getString("msg"));
			}
		}

		// 发布系统通知
		JSONObject news = new JSONObject();
		news.put("title", "审批通知");
		news.put("msg", "您有一条待审批被驳回。");
		news.put("userId", approvalRecord.getInteger("userId"));
		news.put("state", "未读");
		news.put("expandData", "");
		news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
		systemNewsService.launch(news);

	}

	/**
	 * 查询所有用户
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findUserAll() {
		return dao.findUserAll();
	}

	/**
	 * 修改表单
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void updateForm(JSONObject json) throws Exception {
		String listNo = json.getString("listNo");
		Integer userId = json.getInteger("userId");

		JSONObject user = dao.findUserById(userId);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间

		// 查询出单据详情
		JSONObject details = dao.queryDetails(listNo);
		if (details == null) {
			throw new Exception("单据不存在");
		}

		// 老表单
		List<JSONObject> oldFormDataList = JSONObject.parseArray(details.getString("formData"), JSONObject.class);
		// 新表单
		List<JSONObject> newFormDataList = JSONObject.parseArray(json.getString("form"), JSONObject.class);

		for (int i1 = 0; i1 < oldFormDataList.size(); i1++) {
			for (int j1 = 0; j1 < newFormDataList.size(); j1++) {
				if (oldFormDataList.get(i1).getString("typeY").equals("分栏")
						&& newFormDataList.get(j1).getString("typeY").equals("分栏")) {

					// 老的子级
					List<JSONObject> oldChildrenList = JSONObject
							.parseArray(oldFormDataList.get(i1).getString("children"), JSONObject.class);
					// 新的子级
					List<JSONObject> newChildrenList = JSONObject
							.parseArray(newFormDataList.get(i1).getString("children"), JSONObject.class);
					for (int i2 = 0; i2 < oldChildrenList.size(); i2++) {
						for (int j2 = 0; j2 < newChildrenList.size(); j2++) {
							if (oldChildrenList.get(i2).getString("formId")
									.equals(newChildrenList.get(j2).getString("formId"))) {

								// 判断内容是否被修改
								if (!oldChildrenList.get(i2).getString("attribute")
										.equals(newChildrenList.get(j2).getString("attribute"))) {
									// 历史修改
									List<JSONObject> historyList = JSONObject.parseArray(
											oldChildrenList.get(i2).getString("historyList"), JSONObject.class);
									if (historyList == null) {
										historyList = new ArrayList<JSONObject>();
									}
									JSONObject history = new JSONObject();
									history.put("time", time);
									history.put("userId", userId);
									history.put("userName", user.getString("userName"));
									history.put("fullName", user.getString("fullName"));
									String oldAttribute = oldChildrenList.get(i2).getString("attribute");
									history.put("oldAttribute", JSONObject.parse(oldAttribute));
									String newAttribute = newChildrenList.get(j2).getString("attribute");
									history.put("newAttribute", JSONObject.parse(newAttribute));
									historyList.add(history);
									oldChildrenList.get(i2).put("historyList", historyList);

									// 如果修改了就替换老表单数据
									oldChildrenList.get(i2).put("attribute",
											newChildrenList.get(j2).getJSONObject("attribute"));

									break;

								}
							}
						}
					}
					oldFormDataList.get(i1).put("children", oldChildrenList);
				} else if (oldFormDataList.get(i1).getString("typeY").equals("表格")
						&& newFormDataList.get(j1).getString("typeY").equals("表格")) {

					String find1 = oldFormDataList.get(i1).getString("find");
					String find2 = newFormDataList.get(j1).getString("find");
					List<String> a1 = JSONObject.parseArray(find1, String.class);
					List<String> a2 = JSONObject.parseArray(find2, String.class);

					boolean bool = judge(a1, a2);

					if (bool) {
						// 找到同一个formId
						if (oldFormDataList.get(i1).getString("formId")
								.equals(newFormDataList.get(j1).getString("formId"))) {

							// 历史修改
							List<JSONObject> historyList = JSONObject
									.parseArray(oldFormDataList.get(i1).getString("historyList"), JSONObject.class);
							if (historyList == null) {
								historyList = new ArrayList<JSONObject>();
							}
							JSONObject history = new JSONObject();
							history.put("time", time);
							history.put("userId", userId);
							history.put("userName", user.getString("userName"));
							history.put("fullName", user.getString("fullName"));
							String old = oldFormDataList.get(i1).toString();
							history.put("oldAttribute", JSONObject.parse(old));
							String news = newFormDataList.get(j1).toString();
							history.put("newAttribute", JSONObject.parse(news));
							historyList.add(history);
							newFormDataList.get(i1).put("historyList", historyList);
							oldFormDataList.set(i1, newFormDataList.get(j1));

						}
					}

				} else {
					// 找到同一个formId
					if (oldFormDataList.get(i1).getString("formId")
							.equals(newFormDataList.get(j1).getString("formId"))) {
						// 判断内容是否被修改
						if (!oldFormDataList.get(i1).getString("attribute")
								.equals(newFormDataList.get(j1).getString("attribute"))) {

							// 历史修改
							List<JSONObject> historyList = JSONObject
									.parseArray(oldFormDataList.get(i1).getString("historyList"), JSONObject.class);
							if (historyList == null) {
								historyList = new ArrayList<JSONObject>();
							}
							JSONObject history = new JSONObject();
							history.put("time", time);
							history.put("userId", userId);
							history.put("userName", user.getString("userName"));
							history.put("fullName", user.getString("fullName"));
							String oldAttribute = oldFormDataList.get(i1).getString("attribute");
							history.put("oldAttribute", JSONObject.parse(oldAttribute));
							String newAttribute = newFormDataList.get(j1).getString("attribute");
							history.put("newAttribute", JSONObject.parse(newAttribute));
							historyList.add(history);
							oldFormDataList.get(i1).put("historyList", historyList);

							// 如果修改了就替换老表单数据
							oldFormDataList.get(i1).put("attribute",
									newFormDataList.get(j1).getJSONObject("attribute"));
						}
					}
				}

			}
		}

		details.put("formData", oldFormDataList.toString());
		dao.updateApprovalRecordDetailed(details);

	}

	public boolean judge(List<String> oldFormDataList, List<String> newFormDataList) {
		if (oldFormDataList.size() != newFormDataList.size()) {
			return true;
		}

		for (int i = 0; i < oldFormDataList.size(); i++) {
			for (int j = 0; j < newFormDataList.size(); j++) {
				if (!oldFormDataList.get(i).equals(newFormDataList.get(j))) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) {

		String a = "[[{\"formId\":2},{\"formId\":3},{\"formId\":4},{\"formId\":5}],[{\"formId\":2},{\"formId\":3},{\"formId\":4},{\"formId\":5}]]";
		List<String> a1 = JSONObject.parseArray(a, String.class);
		for (String string : a1) {
			List<JSONObject> a2 = JSONObject.parseArray(string, JSONObject.class);
			System.out.println(a2);
		}
	}

}

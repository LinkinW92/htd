package com.skeqi.mes.service.yp.oa.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.MyApplicationInquiryDao;
import com.skeqi.mes.service.yp.oa.MyApplicationInquiryService;
import com.skeqi.mes.util.yp.NetworkInterfaceConfig;
import com.skeqi.mes.util.yp.NewHttpClientUtil;

/**
 * 我的申请
 *
 * @author yinp
 * @data 2021年5月10日
 */
@Service
public class MyApplicationInquiryServiceImpl implements MyApplicationInquiryService {

	@Autowired
	MyApplicationInquiryDao dao;

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
	 * @return
	 */
	@Override
	public List<JSONObject> queryDetails(JSONObject json) {
		return dao.queryDetails(json);
	}

	/**
	 * 查询审批备注
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<JSONObject> findApprovalRecordNote(int id) {
		return dao.findApprovalRecordNote(id);
	}

	/**
	 * 重新发起申请
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void reInitiateApplication(JSONObject json) throws Exception {
		String key = json.getString("key");
		int userId = json.getInteger("userId");
		String userName = json.getString("userName");
		List<JSONObject> detailedJsonList = null;
		try {
			detailedJsonList = JSONObject.parseArray(json.getString("detailed"), JSONObject.class);
		} catch (Exception e) {
			throw new Exception("参数格式不正确");
		}

		json.put("state", "待审核");

		// 新增申请
		if (dao.addApprovalRecord(json) != 1) {
			throw new Exception("提交失败");
		}

		for (JSONObject detailedJson : detailedJsonList) {
			if (detailedJson.getString("key") == null) {
				throw new Exception("参数缺失");
			}
			if (detailedJson.getString("value") == null) {
				throw new Exception("参数缺失");
			}

			detailedJson.put("approvalRecordId", json.getInteger("id"));
			// 新增明细
			if (dao.addDetailed(detailedJson) != 1) {
				throw new Exception("提交失败");
			}
		}

		JSONObject noteJson = new JSONObject();
		noteJson.put("approvalRecordId", json.getInteger("id"));
		noteJson.put("dis", "重新发起申请");
		noteJson.put("userId", userId);
		noteJson.put("state", "");
		// 新增审批备注
		dao.addApprovalRecordNote(noteJson);

		Map<String, Object> variables = new HashMap<>();
		variables.put("user", userName);

		String businessKey = json.getString("id");
		initiateApplication(key, businessKey, variables);

	}

	/**
	 * activiti 发起申请
	 *
	 * @param processDefinitionKey
	 * @param variables
	 */
	public void initiateApplication(String processDefinitionKey, String businessKey, Map<String, Object> variables) {

	}

	/**
	 * 查询审批记录表表格
	 *
	 * @param approvalRecordId
	 * @return
	 */
	@Override
	public JSONObject findApprovalRecordTable(int approvalRecordId) {
		JSONObject json = dao.findApprovalRecordTable(approvalRecordId);

		if (json != null && json.getString("key") != null) {
			String key = json.getString("key");
			String value = json.getString("value");
			System.out.println(key);
			System.out.println(value);
		}

		return json;
	}

	/**
	 * 撤销
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void revoke(String listNo, Integer userId) throws Exception {

		// 查询单据信息
		JSONObject approvalRecord = dao.findApprovalRecordByListNo(listNo);

		// 判断单据是否是该用户申请
		if (!approvalRecord.getString("userId").equals(userId.toString())) {
			throw new Exception("您不能操作该单据");
		}

		// 更新业务表状态
		dao.updateApprovalRecordState(approvalRecord.getInteger("id"), "撤销");

		JSONObject noteJSon = new JSONObject();
		noteJSon.put("dis", "用户撤销");
		noteJSon.put("listNo", listNo);
		noteJSon.put("userId", userId);
		noteJSon.put("state", "撤销");
		noteJSon.put("type", "revoke");
		noteJSon.put("step", 999);
		noteJSon.put("list", "[]");

		// 新增审批备注
		dao.addApprovalRecordNote(noteJSon);

		// 删除待审批记录
		dao.deleteApprovalRecordWithByListNo(listNo);

		// 回调地址
		String callbackUrl = approvalRecord.getString("callbackUrl");
		if (callbackUrl != null && !callbackUrl.equals("")) {
			// 回调参数
			JSONObject parameter = approvalRecord.getJSONObject("parameter");
			if (parameter == null) {
				throw new Exception("回调参数为空");
			}
			parameter.put("name", "撤销");

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
	 * 查询现有的单据类型
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findType() {
		return dao.findType();
	}

	/**
	 * 加急
	 * @param listNo
	 * @return
	 */
	@Override
	public void urgent(String listNo) {
		dao.urgent(listNo);
	}

}

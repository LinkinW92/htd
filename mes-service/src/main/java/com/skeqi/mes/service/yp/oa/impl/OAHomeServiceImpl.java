package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.OAHomeDao;
import com.skeqi.mes.service.yp.oa.OAHomeService;

/**
 * OA首页
 *
 * @author yinp
 * @data 2021年7月09日
 */
@Service
public class OAHomeServiceImpl implements OAHomeService {

	@Autowired
	OAHomeDao dao;

	@Override
	public JSONObject findNumber(Integer userId, String date) {
		//按月查询与我相关的审批单数量
		Integer relevantNumber = dao.findItSAboutMeNumber(userId, date);
		//按月查询我发起的申请数量
		Integer applyNumber = dao.findMyApprovalNumber(userId, date);
		//按月查询我审批的单据
		Integer approvedNumber = dao.findMyApprovalRecordNumber(userId, date);
		//按月查询待我审批的单据数量
		Integer pendingApprovalNumber = dao.findApprovalWithNumber(userId, date);

		JSONObject json = new JSONObject();
		json.put("relevantNumber", relevantNumber);
		json.put("applyNumber", applyNumber);
		json.put("approvedNumber", approvedNumber);
		json.put("pendingApprovalNumber", pendingApprovalNumber);

		return json;
	}

	/**
	 * 查询代办
	 * @param userId
	 * @param date
	 * @return
	 */
	@Override
	public List<JSONObject> findDaiBan(Integer userId, String date) {
		return dao.findDaiBan(userId, date);
	}

	/**
	 * 查询已办
	 * @param userId
	 * @param date
	 * @return
	 */
	@Override
	public List<JSONObject> findDone(Integer userId, String date) {
		return dao.findDone(userId, date);
	}

	/**
	 * 查询办结
	 * @param userId
	 * @param date
	 * @return
	 */
	@Override
	public List<JSONObject> findToConclude(Integer userId, String date) {
		return dao.findToConclude(userId, date);
	}

}

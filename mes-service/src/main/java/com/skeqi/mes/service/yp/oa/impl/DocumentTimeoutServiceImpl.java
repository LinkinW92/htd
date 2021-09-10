package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.DocumentTimeoutDao;
import com.skeqi.mes.service.yp.oa.DocumentTimeoutService;

/**
 * 单据超时
 * @author yinp
 * @date 2021年8月12日
 */
@Service
public class DocumentTimeoutServiceImpl implements DocumentTimeoutService {

	@Autowired
	DocumentTimeoutDao dao;

	/**
	 * 查询审批单据
	 */
	@Override
	public List<JSONObject> findApprovalRecord() {
		return dao.findApprovalRecord();
	}

	/**
	 * 查询待审批人
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> queryApprover(String listNo) {
		return dao.queryApprover(listNo);
	}

	/**
	 * 更新已超时列
	 * @param listNo
	 * @return
	 */
	@Override
	public void updateTimedOut(String listNo) {
		dao.updateTimedOut(listNo);
	}

	/**
	 * 查询待审批数据
	 * @return
	 */
	@Override
	public List<JSONObject> findPendingApproval() {
		return dao.findPendingApproval();
	}

	/**
	 * 更新待审批已超时列
	 * @param id
	 * @return
	 */
	@Override
	public void updateTimedOutWith(Integer id) {
		dao.updateTimedOutWith(id);
	}

}

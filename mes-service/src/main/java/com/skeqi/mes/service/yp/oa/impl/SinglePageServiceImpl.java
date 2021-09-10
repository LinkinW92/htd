package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.SinglePageDao;
import com.skeqi.mes.service.yp.oa.SinglePageService;

/**
 * 单据信息
 * @author yinp
 * @data 2021年5月21日
 *
 */
@Service
public class SinglePageServiceImpl implements SinglePageService {

	@Autowired
	SinglePageDao dao;

	/**
	 * 查询
	 * @return
	 */
	@Override
	public JSONObject query(String listNo) {
		return dao.query(listNo);
	}

	/**
	 * 查询明细
	 * @return
	 */
	@Override
	public List<JSONObject> queryDetails(String listNo) {
		return dao.queryDetails(listNo);
	}

	/**
	 * 查询审批备注
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<JSONObject> findApprovalRecordNote(String listNo) {
		return dao.findApprovalRecordNote(listNo);
	}

	/**
	 * 查询审批记录表表格
	 *
	 * @param approvalRecordId
	 * @return
	 */
	@Override
	public JSONObject findApprovalRecordTable(String listNo) {
		return dao.findApprovalRecordTable(listNo);
	}

}

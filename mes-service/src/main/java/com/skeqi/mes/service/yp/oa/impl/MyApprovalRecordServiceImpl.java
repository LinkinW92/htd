package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.MyApprovalRecordDao;
import com.skeqi.mes.service.yp.oa.MyApprovalRecordService;

/**
 * 我的审批记录
 *
 * @author yinp
 * @data 2021年5月13日
 *
 */
@Service
public class MyApprovalRecordServiceImpl implements MyApprovalRecordService {

	@Autowired
	MyApprovalRecordDao dao;

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		return dao.list(json);
	}

	/**
	 * 查询明细
	 * @param json
	 * @return
	 */
	public List<JSONObject> queryDetails(JSONObject json) {
		return dao.queryDetails(json);
	}

	/**
	 * 查询审批备注
	 * @param id
	 * @return
	 */
	@Override
	public List<JSONObject> findApprovalRecordNote(int id) {
		return dao.findApprovalRecordNote(id);
	}

	/**
	 * 查询所有用户
	 * @return
	 */
	@Override
	public List<JSONObject> findUserAll() {
		return dao.findUserAll();
	}

	/**
	 * 查询现有的单据类型
	 * @return
	 */
	@Override
	public List<JSONObject> findType() {
		return dao.findType();
	}

}

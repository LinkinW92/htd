package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.ApprovalRelatedDao;
import com.skeqi.mes.service.yp.oa.ApprovalRelatedService;

@Service
public class ApprovalRelatedServiceImpl implements ApprovalRelatedService {

	@Autowired
	ApprovalRelatedDao dao;

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
	 * 新增
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) {
		dao.add(json);
	}

	/**
	 * 查询模板
	 * @return
	 */
	@Override
	public List<JSONObject> findTemplateAll() {
		return dao.findTemplateAll();
	}

}

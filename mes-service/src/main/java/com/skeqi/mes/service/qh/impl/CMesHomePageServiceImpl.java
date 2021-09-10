package com.skeqi.mes.service.qh.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.CMesHomePageDAO;
import com.skeqi.mes.service.qh.CMesHomePageService;

@Service
public class CMesHomePageServiceImpl implements CMesHomePageService {

	@Autowired
	CMesHomePageDAO dao;

	@Override
	public JSONArray getPassRate() {
		Integer passNum = dao.getPassCount();
		Integer unPassNum = dao.getUnPassCount();

		JSONArray jsonArray = new JSONArray();
		JSONObject pass = new JSONObject();
		JSONObject unPass = new JSONObject();
		pass.put("name", "合格率");
		pass.put("value", passNum);
		unPass.put("name", "未合格率");
		unPass.put("value", unPassNum);
		jsonArray.add(pass);
		jsonArray.add(unPass);
		return jsonArray;
	}

}

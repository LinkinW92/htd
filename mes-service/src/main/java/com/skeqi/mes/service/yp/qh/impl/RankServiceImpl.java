package com.skeqi.mes.service.yp.qh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.qh.RankDao;
import com.skeqi.mes.service.yp.qh.RankService;

/**
 * 职级
 *
 * @author yinp
 * @data 2021年7月13日
 */
@Service
public class RankServiceImpl implements RankService {

	@Autowired
	RankDao dao;

	/**
	 * 查询
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> list() {
		return dao.list();
	}

	/**
	 * 新增
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		if (dao.findCountByName(json.getString("rankName"), null) > 0) {
			throw new Exception("职级名称已存在");
		}
		dao.add(json);
	}

	/**
	 * 更新
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		if (dao.findCountByName(json.getString("rankName"), json.getInteger("id")) > 0) {
			throw new Exception("职级名称已存在");
		}
		dao.update(json);
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

}

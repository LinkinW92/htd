package com.skeqi.mes.service.qh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.PositionDao;
import com.skeqi.mes.service.qh.PositionService;
import com.skeqi.mes.util.yp.TokenUtil;

/**
 * 职位
 *
 * @author yinp
 * @date 2021年5月20日
 *
 */
@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionDao dao;

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
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String name = json.getString("name");
		json.put("ID_", TokenUtil.randomToken(5) + System.currentTimeMillis());

		// 查询名称得数量
		if (dao.findCountByName(name, null) > 0) {
			throw new Exception("该职位已存在");
		}

		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}
	}

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		Integer id = json.getInteger("id");
		String number = json.getString("number");

		// 查询名称得数量
		if (dao.findCountByName(number, id) > 0) {
			throw new Exception("该职位已存在");
		}

		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void delete(int id) throws Exception {
		if (dao.findUserPositionCount(id) > 0) {
			throw new Exception("该职位还有用户使用，不可删除");
		}
		if (dao.delete(id) != 1) {
			throw new Exception("删除失败");
		}
	}

}

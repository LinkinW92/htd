package com.skeqi.mes.service.yp.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.system.SystemNewsDao;
import com.skeqi.mes.service.yp.system.MailService;
import com.skeqi.mes.service.yp.system.SystemNewsService;

/**
 * 系统通知
 *
 * @author yinp
 * @date 2021年6月23日
 *
 */
@Service
public class SystemNewsServiceImpl implements SystemNewsService {

	@Autowired
	SystemNewsDao dao;

	@Autowired
	MailService mailService;

	/**
	 * 查询通知
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		return dao.list(json);
	}

	/**
	 * 发起通知
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void launch(JSONObject json) throws Exception {
		Integer userId = json.getInteger("userId");
		JSONObject user = dao.findUserById(userId);
		if (user.getString("email") != null && !user.getString("email").equals("")) {
			mailService.sendOut(user.getString("email"), json.getString("title"), json.getString("msg"));
		}
		if (dao.launch(json) != 1) {
			throw new Exception("发起通知失败");
		}
	}

	/**
	 * 修改标记
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public void batch(Integer userId) throws Exception {
		dao.batch(userId);
	}

	/**
	 * 全部编辑已读
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public void updateState(Integer id, String state) throws Exception {
		if (dao.updateState(id, state) != 1) {
			throw new Exception("修改标记失败");
		}
	}

	/**
	 * 删除通知
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void delete(Integer id) throws Exception {
		if (dao.delete(id) != 1) {
			throw new Exception("删除通知失败");
		}
	}

}

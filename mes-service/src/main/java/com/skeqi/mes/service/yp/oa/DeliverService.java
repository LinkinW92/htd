package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 转交
 *
 * @author yinp
 * @data 2021年6月29日
 */
public interface DeliverService {

	/**
	 * 查询已批准的步骤
	 *
	 * @param listNo
	 * @param userId
	 * @return
	 */
	public void sub(String listNo, Integer userId, Integer deliverUserId, String dis) throws Exception;

}

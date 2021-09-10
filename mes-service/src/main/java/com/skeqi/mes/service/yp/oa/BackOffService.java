package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 回退
 *
 * @author yinp
 * @data 2021年6月29日
 */
public interface BackOffService {

	/**
	 * 查询已批准的步骤
	 *
	 * @param listNo
	 * @param userId
	 * @return
	 */
	public List<JSONObject> findAlreadyStep(String listNo, Integer userId) throws Exception;

	/**
	 * 查询已批准的步骤
	 *
	 * @param listNo
	 * @param userId
	 * @param step
	 * @return
	 */
	public void sub(String listNo, Integer userId, Integer step,String dis) throws Exception;

}

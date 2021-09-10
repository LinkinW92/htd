package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * OA首页
 *
 * @author yinp
 * @data 2021年7月09日
 */
public interface OAHomeService {

	/**
	 * 查询数量
	 * @param userId
	 * @param date
	 * @return
	 */
	public JSONObject findNumber(Integer userId,String date);

	/**
	 * 查询代办
	 * @param userId
	 * @param date
	 * @return
	 */
	public List<JSONObject> findDaiBan(Integer userId,String date);

	/**
	 * 查询已办
	 * @param userId
	 * @param date
	 * @return
	 */
	public List<JSONObject> findDone(Integer userId,String date);

	/**
	 * 查询办结
	 * @param userId
	 * @param date
	 * @return
	 */
	public List<JSONObject> findToConclude(Integer userId,String date);

}

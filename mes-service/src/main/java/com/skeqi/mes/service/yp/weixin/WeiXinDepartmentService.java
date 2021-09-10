package com.skeqi.mes.service.yp.weixin;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信部门
 *
 * @author yinp
 * @date 2021年6月11日
 */
public interface WeiXinDepartmentService {

	/**
	 * 获取部门列表
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> list(Integer id) throws Exception;

	/**
	 * 创建部门
	 *
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 删除部门
	 * @param id
	 * @throws Exception
	 */
	public void delete(Integer id)throws Exception;

	/**
	 * 修改部门
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

}

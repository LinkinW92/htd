package com.skeqi.mes.service.yp.system;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 系统通知
 * @author yinp
 * @date 2021年6月23日
 *
 */
public interface SystemNewsService {

	/**
	 * 查询通知
	 * @param id
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 发起通知
	 * @param json
	 * @return
	 */
	public void launch(JSONObject json) throws Exception;

	/**
	 * 修改标记
	 * @param id
	 * @return
	 */
	public void updateState(Integer id,String state) throws Exception;

	/**
	 * 全部编辑已读
	 * @param userId
	 * @return
	 */
	public void batch(Integer userId) throws Exception;

	/**
	 * 删除通知
	 * @param id
	 * @return
	 */
	public void delete(Integer id) throws Exception;

}

package com.skeqi.mes.service.yp.wms;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 来料检验
 * @date 2021-07-16
 */
public interface WmsIncomingInspectionService {

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询行
	 * @param json
	 * @return
	 */
	public List<JSONObject> listRow(JSONObject json);

	/**
	 * 查询详情
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);

	/**
	 * 更新详情
	 * @param json
	 * @return
	 */
	public void updateD(JSONObject json) throws Exception;

	/**
	 * 校验完成
	 * @param id
	 * @param listNo
	 */
	public void verificationComplete(Integer id,String listNo,Integer checkUserId) throws Exception;

	/**
	 * 查询R跟D表数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(String listNo);

}

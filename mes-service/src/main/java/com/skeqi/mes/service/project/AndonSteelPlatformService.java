package com.skeqi.mes.service.project;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.project.AndonSteelPlatform;

/**
 * 钢平台数据
 */
public interface AndonSteelPlatformService {

	/**R表
	 * 查询
	 * @return
	 */
	public List<AndonSteelPlatform> findRAndonSteelPlatform();

	/**
	 * 查询P表
	 * @return
	 */
	public List<AndonSteelPlatform> findPAndonSteelPlatform(JSONObject json);

	/**
	 * 扫码
	 * @param sn
	 */
	public void scanCode(String sn) throws Exception;


}

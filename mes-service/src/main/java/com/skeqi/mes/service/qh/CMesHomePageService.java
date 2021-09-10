package com.skeqi.mes.service.qh;

import com.alibaba.fastjson.JSONArray;

public interface CMesHomePageService {

	/**
	 * 获取今日合格 非合格数量
	 * @return
	 */
	JSONArray getPassRate();

}

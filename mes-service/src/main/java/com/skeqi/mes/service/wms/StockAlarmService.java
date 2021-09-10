package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

/**
 * 库存报警
 * @author yinp
 *
 */
public interface StockAlarmService {

	/**
	 * 查询
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> findList(Map<String,Object> map);

}

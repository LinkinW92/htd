package com.skeqi.mes.service.wms;
import com.skeqi.mes.util.Rjson;

/**
 * 模组Controller
 * @author 73414
 */
public interface ModuleService {

	/**
	 * 上线
	 * @param sn 总成号
	 * @return
	 */
	public Rjson goOnline(String sn);

	/**
	 * 下线
	 * @param sn 总成号
	 * @param result 下线结果(OK or NG)
	 * @return
	 */
	public Rjson offline(String sn,String result);

}

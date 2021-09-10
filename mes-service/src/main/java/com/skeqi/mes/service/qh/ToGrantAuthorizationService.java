package com.skeqi.mes.service.qh;

/**
 * @author yinp
 * @explain 授权
 * @date 2020-10-28
 */
public interface ToGrantAuthorizationService {

	/**
	 * @explain 读取注册表
	 * @param key
	 * @return
	 */
	String getValue(String key);

	/**
	 * @explain 获取mac地址
	 * @return
	 */
	String getLocalMac() throws Exception;

	/**
	 * @explain 校验
	 * @return
	 */
	String check() throws Exception;

}

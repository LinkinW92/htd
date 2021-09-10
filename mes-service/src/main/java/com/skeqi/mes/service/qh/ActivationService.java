package com.skeqi.mes.service.qh;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 激活
 * @date 2020-10-28
 */
public interface ActivationService {

	/**
	 * @explain 激活记录查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> findList(JSONObject json);

	/**
	 * @explain加密算法
	 * @param str
	 * @param n
	 * @return
	 */
	char[] jiami(char[] str, int n);

	/**
	 * @explain解密算法
	 * @param str
	 * @param n
	 * @return
	 */
	char[] jiemi(char[] str, int n);

	/**
	 * @explain 加密
	 * @param effectiveActivationTime
	 * @param termOfValidity
	 * @param mac
	 * @param replacementNumber
	 * @return
	 */
	String encryption(String effectiveActivationTime, String termOfValidity, String mac, int replacementNumber);

	/**
	 * @explain 解密
	 * @param ciphertext
	 * @param replacementNumber
	 * @return
	 */
	String decrypt(String ciphertext, int replacementNumber);

	/**
	 * @explain 写入注册表
	 * @param code
	 */
	void writeValue(String code);

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
	String check(String activationCode) throws Exception;

}

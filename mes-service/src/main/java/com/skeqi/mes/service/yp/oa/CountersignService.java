package com.skeqi.mes.service.yp.oa;

/**
 * 加签
 *
 * @author yinp
 * @data 2021年6月29日
 */
public interface CountersignService {

	/**
	 * 加签提交
	 *
	 * @param listNo
	 * @param userId
	 * @param countersignUser
	 * @param dis
	 */
	public void sub(String listNo, Integer userId, String countersignUser,String signatureMethod, String dis) throws Exception;

}

package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 表单权限
 *
 * @author yinp
 * @data 2021年6月10日
 *
 */
public interface FormPermissionsService {

	/**
	 * 查询公司
	 * @return
	 */
	public List<JSONObject> findCompany();

	/**
	 * 查询角色
	 * @return
	 */
	public List<JSONObject> findRole();

	/**
	 * 查询下级
	 * @return
	 */
	public List<JSONObject> findSubordinate(Integer companyId,Integer departmentId);

	/**
	 * 提交修改
	 * @param id
	 * @param selectData
	 * @return
	 */
	public void submitUpdate(int id,String selectData) throws Exception;

}

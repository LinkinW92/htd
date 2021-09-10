package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesRoleT;
import com.skeqi.mes.pojo.wms.CWmsDepartmentT;
import com.skeqi.mes.pojo.wms.ProcessApproval;

/**
 * @package com.skeqi.mes.service.wms
 * @author Yinp
 * @date 2020年2月15日
 * 审批流程
 *
 */
public interface ProcessApprovalService {

	/**
	 * 查询
	 * @param processApproval
	 * @return
	 */
	public List<ProcessApproval> findProcessApproval(ProcessApproval processApproval);

	/**
	 * 通过id查询
	 * @param processApprovalId
	 * @return
	 */
	public ProcessApproval findProcessApprovalById(Integer processApprovalId);

	/**
	 * 新增
	 * @param processApproval
	 * @return
	 */
	public boolean addProcessApproval(Map<String,Object> map)throws Exception;

	/**
	 * 更新
	 * @param processApproval
	 * @return
	 */
	public boolean updateProcessApproval(Map<String,Object> map)throws Exception;

	/**
	 * 删除
	 * @param processApprovalId
	 * @return
	 */
	public boolean deleteProcessApproval(Integer processApprovalId);

	/**
	 * 查询所有角色id+name
	 * @return
	 */
	public List<JSONObject> findRoleIdAndName();

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<JSONObject> findDepartmentAll();

	/**
	 * 查询所有审批类型
	 * @return
	 */
	public List<JSONObject>findProcessTypeAll();

	/**
	 * 通过部门个跟角色查询用户
	 * @param departmentId
	 * @param roleId
	 * @return
	 */
	public List<JSONObject> findUserAll(Integer departmentId,Integer roleId);

	/**
	 * 通过userid查询user
	 * @param userId
	 * @return
	 */
	public JSONObject findUserById(Integer userId);

}

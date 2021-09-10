package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesRoleT;
import com.skeqi.mes.pojo.wms.CWmsDepartmentT;
import com.skeqi.mes.pojo.wms.ProcessApproval;

/**
 * @package com.skeqi.mapper.wms
 * @author Yinp
 * @date 2020年2月15日
 * 审批流程
 *
 */
public interface ProcessApprovalDao {

	/**
	 * 查询
	 * @param processApproval
	 * @return
	 */
	public List<ProcessApproval> findProcessApproval(ProcessApproval processApproval);

	/**
	 * 新增
	 * @param processApproval
	 * @return
	 */
	public Integer addProcessApproval(ProcessApproval processApproval);

	/**
	 * 更新
	 * @param processApproval
	 * @return
	 */
	public Integer updateProcessApproval(ProcessApproval processApproval);

	/**
	 * 删除
	 * @param processApprovalId
	 * @return
	 */
	public Integer deleteProcessApproval(Integer processApprovalId);

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
	public List<JSONObject> findProcessTypeAll();

	/**
	 * 通过部门个跟角色查询用户
	 * @param departmentId
	 * @param roleId
	 * @return
	 */
	public List<JSONObject> findUserAll(@Param("departmentId")Integer departmentId,@Param("roleId")Integer roleId);

	/**
	 * 通过userid查询user
	 * @param userId
	 * @return
	 */
	public JSONObject findUserById(@Param("userId")Integer userId);

	/**
	 * 查询审批详情是否存在
	 * @param json
	 * @return
	 */
	public int findDoesTheApprovalDetailsExist(@Param("userId")int userId, @Param("processId")int processId, @Param("id")Integer id);

}

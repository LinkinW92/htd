package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.ProcessApprovalDao;
import com.skeqi.mes.mapper.wms.ProcessApprovalDetailDao;
import com.skeqi.mes.pojo.CMesRoleT;
import com.skeqi.mes.pojo.wms.CWmsDepartmentT;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;


/**
 * @package com.skeqi.mes.service.wms
 * @author Yinp
 * @date 2020年2月15日 审批流程
 *
 */
@Service
public class ProcessApprovalServiceImpl implements ProcessApprovalService {

	// public ProcessApprovalServiceImpl() {
	// ApplicationContext ap = new
	// ClassPathXmlApplicationContext("spring-config.xml");
	// mapper = (ProcessApprovalDao) ap.getBean("processApprovalDao");
	// }

	@Autowired
	ProcessApprovalDao dao;

	@Autowired
	ProcessApprovalDetailDao dDao;

	@Override
	public List<ProcessApproval> findProcessApproval(ProcessApproval processApproval) {
		List<ProcessApproval> processApprovalList = dao.findProcessApproval(processApproval);
		return processApprovalList;
	}

	@Override
	public ProcessApproval findProcessApprovalById(Integer processApprovalId) {
		ProcessApproval processApproval = new ProcessApproval();
		processApproval.setId(processApprovalId);

		List<ProcessApproval> processApprovalList = dao.findProcessApproval(processApproval);
		if (processApprovalList.size() == 1) {
			processApproval = processApprovalList.get(0);
			return processApproval;
		} else {
			return null;
		}
	}

	@Override
	public boolean addProcessApproval(Map<String,Object> map) throws Exception {

		try {

			//部门id
			Integer deptId = Integer.parseInt(map.get("dmId").toString());
			//角色id
			Integer roleId = Integer.parseInt(map.get("roleId").toString());
			//审批类型id
			Integer typeId = Integer.parseInt(map.get("typeId").toString());

			//用户id
			Integer userId = Integer.parseInt(map.get("userId").toString());
			//优先级
			Integer priorityLevel = Integer.parseInt(map.get("priorityLevel").toString());

			ProcessApproval processApproval = new ProcessApproval();
			processApproval.setDeptId(deptId);
			processApproval.setRoleId(roleId);
			processApproval.setTypeId(typeId);

			// 查询审批流程
			List<ProcessApproval> list = findProcessApproval(processApproval);

			// 判断是否有审批流程
			if (list.size()<=0) {
				//新增审批流程
				dao.addProcessApproval(processApproval);
				//查询审批流程
				list = findProcessApproval(processApproval);
			}

			processApproval = list.get(0);

			ProcessApprovalDetail processApprovalDetail = new ProcessApprovalDetail();

			processApprovalDetail.setUserId(userId);
			processApprovalDetail.setPriorityLevel(priorityLevel);
			processApprovalDetail.setProcessId(processApproval.getId());

			//查询审批详情是否存在
			int result = dao.findDoesTheApprovalDetailsExist(userId, processApproval.getId(), null);
			if(result >= 1) {
				throw new Exception("用户已经存在该审批类型中的审批流程");
			}

			//更新审批详情的优先级
			dDao.updateApprovaDetailsPriorityLevel(processApprovalDetail.getPriorityLevel(), processApprovalDetail.getProcessId(),1);

			//新增审批流程详情
			dDao.addProcessApprovalDetail(processApprovalDetail);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean updateProcessApproval(Map<String,Object> map) throws Exception {
		try {
			//部门id
			Integer id = Integer.parseInt(map.get("id").toString());
			//部门id
			Integer deptId = Integer.parseInt(map.get("dmId").toString());
			//角色id
			Integer roleId = Integer.parseInt(map.get("roleId").toString());
			//审批类型id
			Integer typeId = Integer.parseInt(map.get("typeId").toString());

			//用户id
			Integer userId = Integer.parseInt(map.get("userId").toString());
			//优先级
			Integer priorityLevel = Integer.parseInt(map.get("priorityLevel").toString());
			Integer qpriorityLevel = Integer.parseInt(map.get("qpriorityLevel").toString());

			ProcessApproval processApproval = new ProcessApproval();
			processApproval.setDeptId(deptId);
			processApproval.setRoleId(roleId);
			processApproval.setTypeId(typeId);

			// 查询审批流程
			List<ProcessApproval> list = findProcessApproval(processApproval);

			// 判断是否有审批流程
			if (list.size()<=0) {
				//新增审批流程
				dao.addProcessApproval(processApproval);
				//查询审批流程
				list = findProcessApproval(processApproval);
			}

			processApproval = list.get(0);

			ProcessApprovalDetail processApprovalDetail = new ProcessApprovalDetail();

			processApprovalDetail.setId(id);
			processApprovalDetail.setUserId(userId);
			processApprovalDetail.setPriorityLevel(priorityLevel);
			processApprovalDetail.setProcessId(processApproval.getId());

			//查询审批详情是否存在
			int result = dao.findDoesTheApprovalDetailsExist(userId, processApproval.getId(),  id);
			if(result >= 1) {
				throw new Exception("用户已经存在该审批类型中的审批流程");
			}

			//更新审批详情的优先级
//			dDao.updateApprovaDetailsPriorityLevel(processApprovalDetail.getPriorityLevel(), processApprovalDetail.getProcessId(),1);
			if(priorityLevel<qpriorityLevel) {
				dDao.updateUpdateApprovaDetailsPriorityLevel(qpriorityLevel,processApprovalDetail.getPriorityLevel(), processApprovalDetail.getProcessId(), 1);
			}else if(priorityLevel>qpriorityLevel) {
				dDao.updateUpdateApprovaDetailsPriorityLevel(qpriorityLevel,processApprovalDetail.getPriorityLevel(), processApprovalDetail.getProcessId(), 2);
			}

			//新增审批流程详情
			dDao.updateProcessApprovalDetail(processApprovalDetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean deleteProcessApproval(Integer processApprovalId) {
		Integer result = dao.deleteProcessApproval(processApprovalId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<JSONObject> findRoleIdAndName() {
		// TODO Auto-generated method stub
		return dao.findRoleIdAndName();
	}

	@Override
	public List<JSONObject> findDepartmentAll() {
		// TODO Auto-generated method stub
		return dao.findDepartmentAll();
	}

	@Override
	public List<JSONObject> findProcessTypeAll() {
		// TODO Auto-generated method stub
		return dao.findProcessTypeAll();
	}

	@Override
	public List<JSONObject> findUserAll(Integer departmentId, Integer roleId) {
		// TODO Auto-generated method stub
		return dao.findUserAll(departmentId, roleId);
	}

	@Override
	public JSONObject findUserById(Integer userId) {
		// TODO Auto-generated method stub
		return dao.findUserById(userId);
	}

}

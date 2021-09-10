package com.skeqi.mes.service.dzb;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.OrgStructureDao;
import com.skeqi.mes.pojo.chenj.srm.req.CWmsDepartmentTReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CWmsDepartmentTRsp;
import com.skeqi.mes.util.Rjson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/27 20:38
 * @Description TODO
 */
@Service("OrgStructureService")
public class OrgStructureService {

	@SuppressWarnings("all")
	@Autowired
	private OrgStructureDao dao;

	public JSONObject data() {
		JSONObject out = new JSONObject();
		//查询所有公司
		List data = new ArrayList();
		List<Map> listCompany = dao.listCompany();
		for (Map company : listCompany) {
			Map myCompany = new HashMap();
			Integer companyId = (Integer) company.get("id");
			myCompany.put("orgId", companyId);
			myCompany.put("orgName", company.get("name"));
			myCompany.put("type", 1);
			//获取公司下边的部门
			List<Map> listDepartment = dao.listDepartment(companyId);
			List childDepartment = new ArrayList();
			if (listDepartment.size() != 0) {
				myCompany.put("childList", childDepartment);
				getChildDepartment(listDepartment, childDepartment, companyId);
			}
			data.add(myCompany);
		}
		//查询用户
		out.put("data", data);
		return out;
	}

	private void getChildDepartment(List<Map> listDepartment, List childDepartment, Integer parentId) {
		for (Map department : listDepartment) {
			Map myDepartment = new HashMap();
			Integer departmentId = (Integer) department.get("id");
			myDepartment.put("orgId", departmentId);
			myDepartment.put("orgName", department.get("name"));
			myDepartment.put("type", 2);
			//获取部门管理人
			Map leader = dao.getLeader(departmentId);
			if (leader != null && leader.keySet().size() != 0) {
				myDepartment.put("orgManagerName", leader.get("name"));
				myDepartment.put("orgManagerPositionName", leader.get("positionName"));
			}
			//获取子部门
			List childDepartment2 = new ArrayList();
			List<Map> listDepartment2 = dao.listDepartment2(departmentId);
			if (listDepartment2.size() != 0) {
				myDepartment.put("childList", childDepartment2);
				getChildDepartment(listDepartment2, childDepartment2, departmentId);
			}
			//获取所有用户
			List<Map> listUser = dao.listUser(departmentId);
			for (Map user : listUser) {
				Map myUser = new HashMap();
				myUser.put("orgId", user.get("id"));
				myUser.put("orgName", user.get("name"));
				myDepartment.put("type", 3);
				myUser.put("orgManagerName", user.get("name"));
				myUser.put("orgManagerPositionName", user.get("positionName"));
				myUser.put("orgParentId", departmentId);
				childDepartment2.add(myUser);
			}
			myDepartment.put("orgParentId", parentId);
			childDepartment.add(myDepartment);
		}
	}


	public JSONObject data2() {
		JSONObject out = new JSONObject();
		//查询所有公司
		List data = new ArrayList();
		List<Map> listCompany = dao.listCompany();
		for (Map company : listCompany) {
			Map myCompany = new HashMap();
			Integer companyId = (Integer) company.get("id");
			myCompany.put("id", companyId);
			myCompany.put("name", company.get("name"));
			myCompany.put("type", 1);
			//获取公司下边的部门
			List<Map> listDepartment = dao.listDepartment(companyId);
			List childDepartment = new ArrayList();
			if (listDepartment.size() != 0) {
				myCompany.put("children", childDepartment);
				getChildDepartment2(listDepartment, childDepartment, companyId);
			}
			data.add(myCompany);
		}
		//查询用户
		out.put("data", data);
		return out;
	}

	private void getChildDepartment2(List<Map> listDepartment, List childDepartment, Integer parentId) {
		for (Map department : listDepartment) {
			Map myDepartment = new HashMap();
			Integer departmentId = (Integer) department.get("id");
			myDepartment.put("id", departmentId);
			myDepartment.put("name", department.get("name"));
			myDepartment.put("type", 2);
			//获取部门管理人
//            List<Map> listLeader = mapper.listLeader(departmentId);
//            List<Map> listManage = new ArrayList<>();
//            for (Map leader : listLeader) {
//                Map manage = new HashMap();
//                manage.put("orgManagerName", leader.get("name"));
//                manage.put("orgManagerPositionName", leader.get("positionName"));
//                listManage.add(manage);
//            }
//            myDepartment.put("listManage", listManage);
			//获取子部门
			List childDepartment2 = new ArrayList();
			List<Map> listDepartment2 = dao.listDepartment2(departmentId);
			if (listDepartment2.size() != 0) {
				myDepartment.put("children", childDepartment2);
				getChildDepartment2(listDepartment2, childDepartment2, departmentId);
			}
			myDepartment.put("orgParentId", parentId);
			childDepartment.add(myDepartment);
			//获取所有用户
			List<Map> listUser = dao.listUser(departmentId);
			if (listUser.size() > 0) {
				Map userFamily = new HashMap();
				userFamily.put("id", 2);
				userFamily.put("name", "员工大家庭");
				userFamily.put("type", 3);
				List userFamilyList = new ArrayList();
				userFamily.put("listUser", userFamilyList);
				for (Map user : listUser) {
					Map myUser = new HashMap();
					myUser.put("orgManagerName", user.get("name"));
					myUser.put("orgManagerPositionName", "y");
					userFamilyList.add(myUser);
				}
				childDepartment.add(userFamily);
			}
		}
	}


}

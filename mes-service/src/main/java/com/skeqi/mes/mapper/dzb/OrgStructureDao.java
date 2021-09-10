package com.skeqi.mes.mapper.dzb;

import com.skeqi.mes.pojo.chenj.srm.req.CWmsDepartmentTReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CWmsDepartmentTRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/27 20:39
 * @Description TODO
 */
public interface OrgStructureDao {

    //查询所有公司
    List<Map> listCompany();

    //查询所有第一级部门
    List<Map> listDepartment(@Param("companyId") Integer companyId);

    //查询下级部门
    List<Map> listDepartment2(@Param("superiorId") Integer superiorId);

    //查询部门管理人
    Map getLeader(@Param("departmentId") Integer departmentId);
    //查询部门管理人
    List<Map> listLeader(@Param("departmentId") Integer departmentId);

    //查询部门所有人
    List<Map> listUser(@Param("departmentId") Integer departmentId);
}

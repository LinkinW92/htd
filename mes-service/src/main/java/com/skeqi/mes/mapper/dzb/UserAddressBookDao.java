package com.skeqi.mes.mapper.dzb;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/27 22:21
 * @Description TODO
 */
public interface UserAddressBookDao {
    //查询所有公司
    List<Map> listCompany();
    //查询所有第一级部门
    List<Map> listDepartment(@Param("companyId") Integer companyId);
    //查询下级部门
    List<Map> listDepartment2(@Param("superiorId") Integer superiorId);
    //查询用户通讯录
    List<Map> listUserByDepartmentId(@Param("departmentId") Integer DepartmentId,@Param("name")String name);
    //查询用户通讯录
    List<Map> listUserByCompanyIdId(@Param("companyId") Integer companyId,@Param("name")String name);
}

package com.skeqi.mes.service.dzb;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.dzb.UserAddressBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/27 22:20
 * @Description TODO
 */
@Service("UserAddressBookService")
public class UserAddressBookService {

    @SuppressWarnings("all")
    @Autowired
    public UserAddressBookDao dao;

    //查询组织架构
    public JSONObject listOrg() {
        JSONObject out = new JSONObject();
        //查询所有公司
        List data = new ArrayList();
        List<Map> listCompany = dao.listCompany();
        for (Map company : listCompany) {
            Map myCompany = new HashMap();
            Integer companyId = (Integer) company.get("id");
            myCompany.put("id", companyId);
            myCompany.put("type", "C");
            myCompany.put("label", company.get("name"));
            //获取公司下边的部门
            List<Map> listDepartment = dao.listDepartment(companyId);
            List childDepartment = new ArrayList();
            if (listDepartment.size() != 0) {
                myCompany.put("children", childDepartment);
                getChildDepartment(listDepartment, childDepartment);
            }
            data.add(myCompany);
        }
        //查询用户
        out.put("data", data);
        return out;
    }

    //查询用户
    public JSONObject listUser(String type, Integer value, String name, Integer pageSize, Integer pageNum) {
        JSONObject out = new JSONObject();
        //查询所有公司
        PageHelper.startPage(pageNum, pageSize);
        List<Map> listUser = null;
        if (type != null && type.equals("C")) {
            listUser = dao.listUserByCompanyIdId(value, name);
        } else {
            listUser = dao.listUserByDepartmentId(value, name);
        }
        PageInfo pageInfo = new PageInfo<>(listUser);
        out.put("data", pageInfo);
        return out;
    }

    private void getChildDepartment(List<Map> listDepartment, List childDepartment) {
        for (Map department : listDepartment) {
            Map myDepartment = new HashMap();
            Integer departmentId = (Integer) department.get("id");
            myDepartment.put("id", departmentId);
            myDepartment.put("type", "D");
            myDepartment.put("label", department.get("name"));

            //获取子部门
            List childDepartment2 = new ArrayList();
            List<Map> listDepartment2 = dao.listDepartment2(departmentId);
            if (listDepartment2.size() != 0) {
                myDepartment.put("childList", childDepartment2);
                getChildDepartment(listDepartment2, childDepartment2);
            }

            childDepartment.add(myDepartment);
        }
    }

    //查询用户
    public JSONObject listUser() {
        JSONObject out = new JSONObject();
        //查询所有公司
        List data = new ArrayList();
        List<Map> listCompany = dao.listCompany();
        for (Map company : listCompany) {
            Map myCompany = new HashMap();
            Integer companyId = (Integer) company.get("id");
            myCompany.put("id", companyId);
            myCompany.put("type", 1);
            myCompany.put("label", company.get("name"));
            //获取公司下边的部门
            List<Map> listDepartment = dao.listDepartment(companyId);
            List childDepartment = new ArrayList();
            if (listDepartment.size() != 0) {
                myCompany.put("childList", childDepartment);
                getChildDepartment2(listDepartment, childDepartment);
            }
            data.add(myCompany);
        }
        //查询用户
        out.put("data", data);
        return out;
    }

    private void getChildDepartment2(List<Map> listDepartment, List childDepartment) {
        for (Map department : listDepartment) {
            Map myDepartment = new HashMap();
            Integer departmentId = (Integer) department.get("id");
            myDepartment.put("id", departmentId);
            myDepartment.put("type", 2);
            myDepartment.put("label", department.get("name"));
            //获取子部门
            List childDepartment2 = new ArrayList();
            List<Map> listDepartment2 = dao.listDepartment2(departmentId);
            if (listDepartment2.size() != 0) {
                myDepartment.put("childList", childDepartment2);
                getChildDepartment2(listDepartment2, childDepartment2);
            }
            List<Map> listUser = dao.listUserByDepartmentId(departmentId, null);
            if (listUser.size() != 0) {
                List userList = new ArrayList();
                for (Map user : listUser) {
                    Map myUser = new HashMap();
                    myUser.put("id", user.get("id"));
                    myUser.put("type", 3);
                    myUser.put("email", user.get("email"));
                    myUser.put("userName", user.get("userName"));
                    myUser.put("mobile", user.get("mobile"));
                    userList.add(myUser);
                }
                myDepartment.put("userList", userList);
            }

            childDepartment.add(myDepartment);
        }
    }

}

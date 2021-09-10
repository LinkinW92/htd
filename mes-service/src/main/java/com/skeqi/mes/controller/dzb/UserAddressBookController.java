package com.skeqi.mes.controller.dzb;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.UserAddressBookService;
import com.skeqi.mes.util.ToolUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Created by DZB
 * @Date 2021/5/27 22:19
 * @Description TODO
 */
@RestController
@RequestMapping("skq/userAddressBook")
public class UserAddressBookController {

    @Resource(name = "UserAddressBookService")
    private UserAddressBookService service;

    @RequestMapping(value = "listOrg",method = RequestMethod.POST)
    public Object listOrg(HttpServletRequest request){
        JSONObject out =new JSONObject();
        try {
            out = service.listOrg();
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }

    @RequestMapping(value = "listUser",method = RequestMethod.POST)
    public Object listUser(HttpServletRequest request,
                           @RequestParam(required = true,value = "type")String type,
                           @RequestParam(required = true,value = "value")Integer value,
                           @RequestParam(required = true,value = "name")String name,
                           @RequestParam(required = true,value = "pageSize")Integer pageSize,
                           @RequestParam(required = true,value = "pageNum")Integer pageNum){
        JSONObject out =new JSONObject();
        try {
            out = service.listUser(type,value,name,pageSize,pageNum);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }

    @RequestMapping(value = "listUser2",method = RequestMethod.POST)
    public Object listUser(HttpServletRequest request){
        JSONObject out =new JSONObject();
        try {
            out = service.listUser();
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }
}

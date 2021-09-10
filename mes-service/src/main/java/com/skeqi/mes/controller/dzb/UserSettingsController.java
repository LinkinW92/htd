package com.skeqi.mes.controller.dzb;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.UserSettingsService;
import com.skeqi.mes.util.ToolUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/28 10:37
 * @Description TODO
 */
@RestController
@RequestMapping("skq/userSettings")
public class UserSettingsController {

    @Resource(name = "UserSettingsService")
    private UserSettingsService service;

    @RequestMapping(value = "getUserById",method = RequestMethod.POST)
    public Object getUserById(HttpServletRequest request,Integer id){
        JSONObject out =new JSONObject();
        try {
            out = service.getUserById(id);
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }
    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    public Object updateUser(HttpServletRequest request,
        int id,String userName,String userPwd,String passNew,String mobile,String email){
        JSONObject out =new JSONObject();
        try {
            Map map = new HashMap();
            map.put("id",id);
            map.put("userName",userName);
            map.put("userPwd",userPwd);
            map.put("passNew",passNew);
            map.put("mobile",mobile);
            map.put("email",email);
            out.put("code",200);
            out = service.updateUser(map);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }
}

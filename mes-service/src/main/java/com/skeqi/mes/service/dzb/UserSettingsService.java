package com.skeqi.mes.service.dzb;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.Encryption;
import com.skeqi.mes.mapper.all.CMesUserTDAO;
import com.skeqi.mes.mapper.dzb.UserSettingsDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.util.Rjson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/28 10:39
 * @Description TODO
 */
@Service("UserSettingsService")
public class UserSettingsService {

    @SuppressWarnings("all")
    @Autowired
    private UserSettingsDao dao;
    @SuppressWarnings("all")
    @Autowired
    private CMesUserTDAO userDao;

    //获取用户信息
    public JSONObject getUserById(Integer id) {
        JSONObject out = new JSONObject();
        Map user = dao.getUserById(id);
        out.put("data",user);
        return out;
    }


    //修改用户信息
    public JSONObject updateUser(Map map) {
        JSONObject out = new JSONObject();
        CMesUserT user  = new CMesUserT();
        String userName = map.get("userName").toString();
        user.setUserName(userName);
        List<CMesUserT> userList = userDao.findUserByName(user);
        if(userList == null || userList.size() <= 0) {
            out.put("msg","用户不存在！");
            out.put("code",201);
            return out;
        }
        user = userList.get(0);
        if((!map.get("userPwd").toString().equals("")) || (!map.get("passNew").toString().equals(""))) {
            if (!user.getUserPwd().equals(Encryption.getPassWord(userName, map.get("userPwd").toString()))) {
                out.put("code",201);
                out.put("msg", "原密码不正确！");
                return out;
            }
        }
        //
        Map updateMap = new HashMap();
        updateMap.put("id",map.get("id"));
        Object passNew = map.get("passNew");
        if(passNew!= null && !passNew.toString().equals("")) {
            updateMap.put("pwd",Encryption.getPassWord(userName,passNew.toString()));
        }
        Object mobile = map.get("mobile");
        if(mobile!= null && !mobile.toString().equals("")){
            updateMap.put("mobile",mobile.toString());

        }

        Object email = map.get("email");
        if(email!= null && !email.toString().equals("")){
            updateMap.put("email",email.toString());

        }
        dao.updateUser(updateMap);
        return out;
    }

}

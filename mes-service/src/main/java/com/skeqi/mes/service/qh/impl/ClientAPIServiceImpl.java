package com.skeqi.mes.service.qh.impl;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.qh.ClientAPIDAO;
import com.skeqi.mes.service.qh.ClientAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/21 15:46
 */
@Service
public class ClientAPIServiceImpl implements ClientAPIService {

    @Autowired
    ClientAPIDAO dao;

    @Override
    public JSONObject findUserStation(String userName, String password) throws ServicesException {
        JSONObject json = new JSONObject();
        Integer integer = dao.UserLogin(userName, password);
        if(integer==0){
            throw new ServicesException(201,"用户或密码输入错误");
        }
        Map<String, Object> userStation = dao.findUserStation(userName);
        if(userStation.get("isWhole").toString().equals("1")){ //全职
            json.put("code",1);
            return json;
        }
        List<String> list = new ArrayList<>();
        String string = userStation.get("stationId").toString();
        String[] split = string.split(",");
        for(String str : split){
            String stationName = dao.findStationName(str);
            list.add(stationName);
        }
        json.put("code",0);
        json.put("station",list);
        return json;
    }
}

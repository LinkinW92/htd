package com.skeqi.mes.controller.dzb;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.OrgStructureService;
import com.skeqi.mes.util.ToolUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Created by DZB
 * @Date 2021/5/27 20:36
 * @Description TODO
 */
@RestController
@RequestMapping("skq/OrgStructure")
public class OrgStructureController {

    @Resource(name = "OrgStructureService")
    private OrgStructureService service;

    @RequestMapping(value = "data",method = RequestMethod.POST)
    public Object data(HttpServletRequest request){
        JSONObject out =new JSONObject();
        try {
            out = service.data();
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }

    @RequestMapping(value = "data2",method = RequestMethod.POST)
    public Object data2(HttpServletRequest request){
        JSONObject out =new JSONObject();
        try {
            out = service.data2();
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return out;

    }
}

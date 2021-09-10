package com.skeqi.mes.controller.dzb.monitor;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.monitor.AndonMonService;
import com.skeqi.mes.service.dzb.monitor.AndonMonServiceI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Created by DZB
 * @Date 2021/4/20 16:12
 * @Description TODO
 */
@RestController
@RequestMapping("/mon/Andon")
public class AndonMonController {

    @Resource(name = "AndonMonService")
    private AndonMonService service;

    //设备异常监控
    @RequestMapping(value = "data1",method = RequestMethod.POST)
    public Object data1() throws Exception{
        JSONObject out =new JSONObject();
        try {
            out = service.data1();
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
        }
        return out;
    }

    //质检
    @RequestMapping(value = "data2",method = RequestMethod.POST)
    public Object data2() throws Exception{
        JSONObject out =new JSONObject();
        try {
            out = service.data2();
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
        }
        return out;
    }

    //类别统计
    @RequestMapping(value = "data3",method = RequestMethod.POST)
    public Object data3() throws Exception{
        JSONObject out =new JSONObject();
        try {
            out = service.data3();
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
        }
        return out;
    }

    //设备状态
    @RequestMapping(value = "data4",method = RequestMethod.POST)
    public Object data4() throws Exception{
        JSONObject out =new JSONObject();
        try {
            out = service.data4();
            out.put("code",200);
        } catch (Exception e) {
            out.put("code",201);
        }
        return out;
    }
}

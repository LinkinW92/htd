package com.skeqi.mes.controller.qh.clientAPI;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.qh.APIResult;
import com.skeqi.mes.service.qh.ClientAPIService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/21 15:44
 */
@RestController
@RequestMapping(value = "api/client", produces = MediaType.APPLICATION_JSON)
@Api(value = "客户端调用API", description = "客户端调用API", produces = MediaType.APPLICATION_JSON)
public class ClientAPIController {

    @Autowired
    ClientAPIService service;

    @RequestMapping(value = "/ClientLogin", method = RequestMethod.POST)
    @ApiOperation(value = "客户端登录接口调用", notes = "客户端登录接口调用")
    @ApiImplicitParams({
                @ApiImplicitParam(paramType = "query", name = "userName", required = true, dataType = "String"),
                @ApiImplicitParam(paramType = "query", name = "passWord",  required = true, dataType = "String"),
                @ApiImplicitParam(paramType = "query", name = "stationName", required = true, dataType = "String"),})
    @ResponseBody
    public APIResult ClientLogin(HttpServletRequest request, String userName,String passWord,String stationName){
        try {
            JSONObject userStation = service.findUserStation(userName, passWord);
            if(Integer.parseInt(userStation.get("code").toString())==1){  //全职
                return new APIResult("100","登录成功","true");
            }
            List<String> list = (List<String>) userStation.get("station");
            if(!list.contains(stationName)){
                return new APIResult("101","登录失败!此用户没有在此工位登录的权限","true");
            }
            return new APIResult("100","登录成功","true");
        }catch (ServicesException e){
            e.getMessage();
            return new APIResult("300",e.getMessage(),"false");
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return new APIResult("300","登陆失败!请检查员工信息是否正常","false");
        }
    }
}

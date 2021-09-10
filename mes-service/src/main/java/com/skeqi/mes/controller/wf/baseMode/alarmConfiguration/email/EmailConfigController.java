package com.skeqi.mes.controller.wf.baseMode.alarmConfiguration.email;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.email.AlarmEmailConfig;
import com.skeqi.mes.service.wf.baseMode.alarmConfiguration.email.AlarmEmailConfigService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "alarmEmail", produces = MediaType.APPLICATION_JSON)
@Api(value = "邮箱发件人配置", description = "邮箱发件人配置", produces = MediaType.APPLICATION_JSON)
public class EmailConfigController {

    @Resource
    private AlarmEmailConfigService emailConfigService;

    /**
     * 查询邮箱发件人
     * @param request
     * @return
     */
    @ApiOperation(value = "查询邮箱发件人", notes = "查询邮箱发件人")
    @RequestMapping(value = "findEmailSender",method = RequestMethod.POST)
    public Rjson findEmailConfigList(HttpServletRequest request) {
        try {
            Integer pageNumber = EqualsUtil.pageNumber(request);
            Integer pageSize = EqualsUtil.pageSize(request);
            PageHelper.startPage(pageNumber, pageSize);
            List<AlarmEmailConfig> list =  emailConfigService.selectAll();
            PageInfo<AlarmEmailConfig> pageInfo = new PageInfo<AlarmEmailConfig>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增邮箱发件人", notes = "新增邮箱发件人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "senderEmail", value = "发件人", required = true, paramType = "query"),
            @ApiImplicitParam(name = "theServer", value = "服务器", required = true, paramType = "query"),
            @ApiImplicitParam(name = "authorizationCode", value = "授权码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "ifEnable", value = "是否开启", required = true, paramType = "query")
    })
    @RequestMapping(value = "addEmailConfig", method = RequestMethod.POST)
    public Rjson addEmailConfig (HttpServletRequest request){
        try {
            String senderEmail = EqualsUtil.string(request,"senderEmail","发件人",true);
            String theServer = EqualsUtil.string(request,"theServer","服务器",true);
            String authorizationCode = EqualsUtil.string(request,"authorizationCode","授权码",true);
            Integer ifEnable = EqualsUtil.integer(request,"ifEnable","是否开启",true);
            AlarmEmailConfig alarmEmailConfig = new AlarmEmailConfig();
            alarmEmailConfig.setSenderEmail(senderEmail);
            alarmEmailConfig.setTheServer(theServer);
            alarmEmailConfig.setAuthorizationCode(authorizationCode);
            alarmEmailConfig.setIfEnable(ifEnable);
            alarmEmailConfig.setCdt(new Date());
            alarmEmailConfig.setUdt(new Date());
            Integer integer = emailConfigService.insertSelective(alarmEmailConfig);
            if (integer<1){
                return Rjson.error("新增邮箱发件人失败!");
            }
            return Rjson.success("新增邮箱发件人成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "编辑邮箱发件人", notes = "编辑邮箱发件人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "senderEmail", value = "发件人", required = true, paramType = "query"),
            @ApiImplicitParam(name = "theServer", value = "服务器", required = true, paramType = "query"),
            @ApiImplicitParam(name = "authorizationCode", value = "授权码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "ifEnable", value = "是否开启", required = true, paramType = "query")
    })
    @RequestMapping(value = "editEmailConfig", method = RequestMethod.POST)
    public Rjson editEmailConfig (HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request,"id","id",true);
            String senderEmail = EqualsUtil.string(request,"senderEmail","发件人",true);
            String theServer = EqualsUtil.string(request,"theServer","服务器",true);
            String authorizationCode = EqualsUtil.string(request,"authorizationCode","授权码",true);
            Integer ifEnable = EqualsUtil.integer(request,"ifEnable","是否开启",true);
            AlarmEmailConfig alarmEmailConfig = new AlarmEmailConfig();
            alarmEmailConfig.setId(id);
            alarmEmailConfig.setSenderEmail(senderEmail);
            alarmEmailConfig.setTheServer(theServer);
            alarmEmailConfig.setAuthorizationCode(authorizationCode);
            alarmEmailConfig.setIfEnable(ifEnable);
            alarmEmailConfig.setUdt(new Date());
            Integer integer = emailConfigService.updateByPrimaryKeySelective(alarmEmailConfig);
            if (integer<1){
                return Rjson.error("编辑邮箱发件人失败!");
            }
            return Rjson.success("编辑邮箱发件人成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "删除邮箱发件人", notes = "删除邮箱发件人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query")
    })
    @RequestMapping(value = "delEmailConfig", method = RequestMethod.POST)
    public Rjson delEmailConfig (HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request,"id","id",true);
            Integer integer = emailConfigService.deleteByPrimaryKey(id);
            if (integer<1){
                return Rjson.error("删除邮箱发件人失败!");
            }
            return Rjson.success("删除邮箱发件人成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

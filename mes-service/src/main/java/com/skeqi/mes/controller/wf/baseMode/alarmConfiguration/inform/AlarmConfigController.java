package com.skeqi.mes.controller.wf.baseMode.alarmConfiguration.inform;

import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.inform.CMesAlarmConfigT;
import com.skeqi.mes.service.wf.baseMode.alarmConfiguration.inform.CMesAlarmConfigTService;
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
import java.util.List;

/**
 * 告警配置控制器
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "alarmConfig", produces = MediaType.APPLICATION_JSON)
@Api(value = "告警配置", description = "告警配置", produces = MediaType.APPLICATION_JSON)
public class AlarmConfigController {
    @Resource
    private CMesAlarmConfigTService configTService;


    @ApiOperation(value = "查询告警配置", notes = "查询告警配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "配置编号", required = false, paramType = "query"),
    })
    @RequestMapping(value = "findAlarmConfigData", method = RequestMethod.POST)
    public Rjson findTimerConfig (HttpServletRequest request){
        try {
            String code = EqualsUtil.string(request,"code","类型编码",false);
            List<CMesAlarmConfigT> alarmConfigTS =  configTService.selectAll();
            return Rjson.success(alarmConfigTS);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "新增告警配置", notes = "新增告警配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "配置编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sql", value = "执行sql", required = true, paramType = "query"),
            @ApiImplicitParam(name = "way", value = "告警方式", required = true, paramType = "query"),
            @ApiImplicitParam(name = "receptionStaff", value = "接收人员", required = true, paramType = "query"),
            @ApiImplicitParam(name = "dataFormat", value = "数据格式", required = true, paramType = "query"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query"),
            @ApiImplicitParam(name = "content", value = "正文", required = true, paramType = "query")
    })
    @RequestMapping(value = "addAlarmConfig", method = RequestMethod.POST)
    public Rjson addAlarmConfig (HttpServletRequest request){
        try {
            String code = EqualsUtil.string(request,"code","配置编号",true);
            String sql = EqualsUtil.string(request,"sql","执行sql",true);
            String way = EqualsUtil.string(request,"way","告警方式",true);
            String receptionStaff = EqualsUtil.string(request,"receptionStaff","接收人员",true);
            String dataFormat = EqualsUtil.string(request,"dataFormat","数据格式",true);
            String title = EqualsUtil.string(request,"title","标题",true);
            String content = EqualsUtil.string(request,"content","正文",true);
            CMesAlarmConfigT configT = new CMesAlarmConfigT();
            configT.setCode(code);
            configT.setSql(sql);
            configT.setWay(way);
            configT.setReceptionStaff(receptionStaff);
            configT.setDataFormat(dataFormat);
            configT.setTitle(title);
            configT.setContent(content);
            Integer integer = configTService.insertSelective(configT);
            if (integer<1){
                return Rjson.error("添加告警配置信息失败!");
            }
            return Rjson.success("添加告警配置信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "编辑告警配置", notes = "编辑告警配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "配置编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sql", value = "执行sql", required = true, paramType = "query"),
            @ApiImplicitParam(name = "way", value = "告警方式", required = true, paramType = "query"),
            @ApiImplicitParam(name = "receptionStaff", value = "接收人员", required = true, paramType = "query"),
            @ApiImplicitParam(name = "dataFormat", value = "数据格式", required = true, paramType = "query"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query"),
            @ApiImplicitParam(name = "content", value = "正文", required = true, paramType = "query")
    })
    @RequestMapping(value = "editAlarmConfig", method = RequestMethod.POST)
    public Rjson editAlarmConfig (HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request,"id","id",true);
            String code = EqualsUtil.string(request,"code","配置编号",true);
            String sql = EqualsUtil.string(request,"sql","执行sql",true);
            String way = EqualsUtil.string(request,"way","告警方式",true);
            String receptionStaff = EqualsUtil.string(request,"receptionStaff","接收人员",true);
            String dataFormat = EqualsUtil.string(request,"dataFormat","数据格式",true);
            String title = EqualsUtil.string(request,"title","标题",true);
            String content = EqualsUtil.string(request,"content","正文",true);
            CMesAlarmConfigT configT = new CMesAlarmConfigT();
            configT.setId(id);
            configT.setCode(code);
            configT.setSql(sql);
            configT.setWay(way);
            configT.setReceptionStaff(receptionStaff);
            configT.setDataFormat(dataFormat);
            configT.setTitle(title);
            configT.setContent(content);
            Integer integer = configTService.updateByPrimaryKeySelective(configT);
            if (integer<1){
                return Rjson.error("编辑告警配置信息失败!");
            }
            return Rjson.success("编辑告警配置信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "删除告警配置", notes = "删除告警配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query")
    })
    @RequestMapping(value = "deleteAlarmConfig", method = RequestMethod.POST)
    public Rjson deleteAlarmConfig (HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request,"id","id",true);
            Integer integer = configTService.deleteByPrimaryKey(id);
            if (integer<1){
                return Rjson.error("删除告警配置信息失败!");
            }
            return Rjson.success("删除告警配置信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

package com.skeqi.mes.controller.wf.timer;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.config.StartTimer;
import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;
import com.skeqi.mes.service.wf.timer.CMesTimerConfigTService;
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
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "alarmMonitoring", produces = MediaType.APPLICATION_JSON)
@Api(value = "轮询定时任务监控", description = "轮询定时任务监控", produces = MediaType.APPLICATION_JSON)
public class AlarmMonitoringController {

    /**
     *轮询定时任务类
     */
    @Resource
    private StartTimer startTimer;

    /**
     * 定时器配置类
     */
    @Resource
    private CMesTimerConfigTService timerConfigTService;

    @ApiOperation(value = "轮询任务停止", notes = "轮询任务停止")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "类型编码", required = true, paramType = "query"),
    })
    @RequestMapping(value = "stopCron", method = RequestMethod.POST)
    public Rjson stopCron (HttpServletRequest request){
        try {
            String code = EqualsUtil.string(request,"code","类型编码",true);
            String result = "关闭定时任务失败";
            if (startTimer.stopCron(code)){
                result = "关闭定时任务成功";
            }
            return Rjson.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "轮询任务开始", notes = "轮询任务开始")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "类型编码", required = true, paramType = "query"),
    })
    @RequestMapping(value = "startCron", method = RequestMethod.POST)
    public Rjson startCron (HttpServletRequest request){
        try {
            String code = EqualsUtil.string(request,"code","类型编码",true);
            String result = "开启定时任务失败";
            if (startTimer.startCron(code)){
                result = "开启定时任务成功";
            }
            return Rjson.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "查询轮询定时器配置", notes = "查询轮询定时器配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "类型编码", required = false, paramType = "query"),
    })
    @RequestMapping(value = "findTimerConfig", method = RequestMethod.POST)
    public Rjson findTimerConfig (HttpServletRequest request){
        try {
            String code = EqualsUtil.string(request,"code","类型编码",false);
            List<CMesTimerConfigT> cMesTimerConfigT = timerConfigTService.selectByCode(code);
            return Rjson.success(cMesTimerConfigT);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增轮询定时器配置", notes = "新增轮询定时器配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "类型编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "params", value = "参数", required = false, paramType = "query"),
            @ApiImplicitParam(name = "cron", value = "cron表达式", required = true, paramType = "query"),
            @ApiImplicitParam(name = "cronExplain", value = "cron表达式说明", required = true, paramType = "query"),
    })
    @RequestMapping(value = "addTimerConfig", method = RequestMethod.POST)
    public Rjson addTimerConfig (HttpServletRequest request){
        try {
            String code = EqualsUtil.string(request,"code","类型编码",true);
            String name = EqualsUtil.string(request,"name","名称",true);
            String params = EqualsUtil.string(request,"params","参数",false);
            String cron = EqualsUtil.string(request,"cron","cron表达式",true);
            String cronExplain = EqualsUtil.string(request,"cronExplain","cron表达式说明",true);
            CMesTimerConfigT timerConfigT = new CMesTimerConfigT();
            timerConfigT.setCode(code);
            timerConfigT.setName(name);
            timerConfigT.setParams(params);
            timerConfigT.setCron(cron);
            timerConfigT.setCronExplain(cronExplain);
            timerConfigT.setStatus(0);
            Integer integer = timerConfigTService.addTimerConfig(timerConfigT);
            if (integer<1){
                return Rjson.error("添加失败");
            }
            return Rjson.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "编辑轮询定时器配置", notes = "编辑轮询定时器配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "类型编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "params", value = "参数", required = false, paramType = "query"),
            @ApiImplicitParam(name = "cron", value = "cron表达式", required = true, paramType = "query"),
    })
    @RequestMapping(value = "editTimerConfig", method = RequestMethod.POST)
    public Rjson editTimerConfig (HttpServletRequest request){
        try {
            String code = EqualsUtil.string(request,"code","类型编码",true);
            String name = EqualsUtil.string(request,"name","名称",true);
            String params = EqualsUtil.string(request,"params","参数",false);
            String cron = EqualsUtil.string(request,"cron","cron表达式",true);
            String cronExplain = EqualsUtil.string(request,"cronExplain","cron表达式说明",true);
            CMesTimerConfigT timerConfigT = new CMesTimerConfigT();
            timerConfigT.setCode(code);
            timerConfigT.setName(name);
            timerConfigT.setParams(params);
            timerConfigT.setCron(cron);
            timerConfigT.setCronExplain(cronExplain);
            Integer integer = timerConfigTService.editTimerConfig(timerConfigT);
            if (integer<1){
                return Rjson.error("编辑失败");
            }
            return Rjson.success("编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "初始化定时器", notes = "初始化定时器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "类型编码", required = false, paramType = "query")
    })
    @RequestMapping(value = "initTimer", method = RequestMethod.POST)
    public Rjson initTimer (HttpServletRequest request){
        try {
            String code = EqualsUtil.string(request,"code","类型编码",false);
            JSONArray jsonArray = JSONArray.parseArray(code);
            List<CMesTimerConfigT> cMesTimerConfigTS = jsonArray.toJavaList(CMesTimerConfigT.class);
            for (CMesTimerConfigT cMesTimerConfigT : cMesTimerConfigTS) {
                startTimer.initTimer(cMesTimerConfigT.getCode());
            }
            return Rjson.success();
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除定时配置", notes = "删除定时配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "类型编码", required = false, paramType = "query")
    })
    @RequestMapping(value = "deleteTimerTack", method = RequestMethod.POST)
    public Rjson deleteTimerTack (HttpServletRequest request){
        try {
            String code = EqualsUtil.string(request,"code","类型编码",false);
            Integer integer = timerConfigTService.deleteTimerTack(code);
            if (integer<1){
                return Rjson.error("删除失败");
            }
            startTimer.stopCron(code);
            return Rjson.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


}

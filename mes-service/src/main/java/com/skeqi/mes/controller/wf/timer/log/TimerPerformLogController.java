package com.skeqi.mes.controller.wf.timer.log;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wf.baseMode.coderRule.CMesCodeRuleT;
import com.skeqi.mes.pojo.wf.timer.CMesTimerConfigT;
import com.skeqi.mes.pojo.wf.timer.log.CMesTimerPerformLogT;
import com.skeqi.mes.service.wf.timer.CMesTimerConfigTService;
import com.skeqi.mes.service.wf.timer.log.CMesTimerPerformLogTService;
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
@RequestMapping(value = "timerPerformLog", produces = MediaType.APPLICATION_JSON)
@Api(value = "定时任务执行记录", description = "定时任务执行记录", produces = MediaType.APPLICATION_JSON)
public class TimerPerformLogController {
    @Resource
    private CMesTimerPerformLogTService performLogTService;

    @ApiOperation(value = "查询定时任务执行记录", notes = "查询定时任务执行记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
    })
    @RequestMapping(value = "findTimerPerformLog", method = RequestMethod.POST)
    public Rjson findTimerConfig (HttpServletRequest request){
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            PageHelper.startPage(pageNum,pageSize);
            List<CMesTimerPerformLogT> performLogTList =  performLogTService.selectAll();
            PageInfo<CMesTimerPerformLogT> pageInfo = new PageInfo<>(performLogTList);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

package com.skeqi.mes.controller.wf;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.wf.GroupModelingPageService;
import com.skeqi.mes.service.wf.SchemePageService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lenovo
 * 集团建模首页
 */
@RestController
@RequestMapping(value = "groupModelingPage", produces = MediaType.APPLICATION_JSON)
@Api(value = "集团建模首页", description = "集团建模首页", produces = MediaType.APPLICATION_JSON)
public class GroupModelingPageController {
    @Autowired
    private GroupModelingPageService service;

    @RequestMapping(value = "findProductionLine ", method = RequestMethod.POST)
    @ApiOperation(value = "查询产线", notes = "查询产线")
    public Rjson findProductionLine(HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        try {
            list = service.findProductionLine();
            return Rjson.success(list);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "findStation ", method = RequestMethod.POST)
    @ApiOperation(value = "查询工位", notes = "查询工位")
    public Rjson findStation(HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        try {
            list = service.findStation();
            return Rjson.success(list);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }
}

package com.skeqi.mes.controller.qh;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "工位管理", description = "工位管理", produces = MediaType.APPLICATION_JSON)
public class CMesStationControllerl {

    @Autowired
    CMesStationTService stationService;

    @Autowired
    CMesLineTService cMesLineService;
    @Autowired
    CMesStationTService cMesStationTService;
    // 工位列表
    @RequestMapping(value = "/station/findAll", method = RequestMethod.POST)
    @ApiOperation(value = "工位列表", notes = "工位列表")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lineId", value = "产线Id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int")})
    public JSONObject lineManager(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize, Integer lineId) throws ServicesException {
        PageHelper.startPage(pageNum, pageSize);
        System.err.println(lineId);
        CMesStationT cMesStationT = new CMesStationT();
        if (lineId == null) {
            lineId = 0;
        }
        cMesStationT.setLineId(lineId);
        List<CMesStationT> lineList = stationService.findAllStation(cMesStationT);
        JSONObject json = new JSONObject();
        try {
            PageInfo<CMesStationT> pageInfo = new PageInfo<>(lineList);
            json.put("code", 0);
            json.put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            // TODO: handle exception
            json.put("code", 1);
            json.put("msg", "未知错误");
        }
        return json;
    }

    // 产线信息
    @RequestMapping(value = "/station/findByLine", method = RequestMethod.POST)
    @ApiOperation(value = "产线列表 ", notes = "产线列表 ")
    @ResponseBody
    public JSONObject findByLine(HttpServletRequest request) throws ServicesException {

        JSONObject json = new JSONObject();
        try {
            List<CMesLineT> cme = cMesLineService.findAllLine(null);
            json.put("code", 0);
            json.put("msg", cme);
        } catch (ServicesException e) {
            // TODO: handle exception
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            // TODO: handle exception
            json.put("code", 2);
            json.put("msg", "未知错误");
        }
        return json;
    }

    // 新增产线
    @ResponseBody
    @RequestMapping(value = "/station/addStation", method = RequestMethod.POST)
    @ApiOperation(value = "添加工位", notes = "添加工位")
    @OptionalLog(module = "基础建模", module2 = "工位管理", method = "添加工位")
    public JSONObject addLine(HttpServletRequest request, @ModelAttribute CMesStationT cMesStationT) throws ServicesException {
        JSONObject json = new JSONObject();
        try {
            CMesLineT line =null;
            // 产线Code
            if (StringUtil.eqNu(cMesStationT.getLineCode())) {
                line= new CMesLineT();
                line.setName(cMesStationT.getLineCode());
                Map<String, Object> lineMap = cMesLineService.getLineByName(line);
                if(lineMap.size()>0){
                    cMesStationT.setLineId((Integer) lineMap.get("ID"));
                    HttpSession session = request.getSession();
                    session.setAttribute("lineCode", cMesStationT.getLineCode());
                }else{
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }
            } else {
                line= new CMesLineT();
                line.setId(cMesStationT.getLineId());
                List<CMesLineT> allLine = cMesLineService.findAllLine(line);
                if(allLine.size()>0){
                    HttpSession session = request.getSession();
                    session.setAttribute("lineCode", allLine.get(0).getName());
                }else{
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }
            }
            stationService.addStation(cMesStationT);
            json.put("code", 0);
            json.put("msg", "成功");
        } catch (ServicesException e) {
            // TODO: handle exception
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            // TODO: handle exception
            json.put("code", 1);
            json.put("msg", "未知错误");
        }
        return json;
    }

    // 根据id查询工位
    @RequestMapping(value = "/station/toEditStation", method = RequestMethod.POST)
    @ApiOperation(value = "查询id工位信息", notes = "根据id查询工位")
    @ApiImplicitParam(paramType = "query", name = "id", value = "工位ID", required = true, dataType = "Integer")
    @ResponseBody
    public JSONObject toEditStation(HttpServletRequest request, Integer id) throws ServicesException {

        JSONObject json = new JSONObject();
        try {
            CMesStationT cMesStationT = stationService.findStationByid(id);
            json.put("code", 0);
            json.put("msg", cMesStationT);
        } catch (ServicesException e) {
            // TODO: handle exception
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            // TODO: handle exception
            json.put("code", 2);
            json.put("msg", "未知错误");
        }
        return json;

    }

    // 修改工位信息
    @RequestMapping(value = "/station/editStation", method = RequestMethod.POST)
    @ApiOperation(value = "修改工位信息", notes = "根据前端传来的数据修改")
    @ResponseBody
    @OptionalLog(module = "基础建模", module2 = "工位管理", method = "修改工位信息")
    public JSONObject editLineById(HttpServletRequest request, @ModelAttribute CMesStationT cMesStationT) throws ServicesException {
        JSONObject json = new JSONObject();


        try {

            Integer id = EqualsUtil.integer(request, "id", "Id", true);
            Integer lineId = EqualsUtil.integer(request, "lineId", "产线Id", true);
            Integer stationIndex = EqualsUtil.integer(request, "stationIndex", "工位下标", true);
            String stationName = EqualsUtil.string(request, "stationName", "工位名称", true);
            Integer stationIndex1 = EqualsUtil.integer(request, "stationIndex1", "工位下标", true);
            String stationName1 = EqualsUtil.string(request, "stationName1", "工位名称", true);
            Integer stationTime = EqualsUtil.integer(request, "stationTime", "工位节拍", true);
            Integer stationAutoornot = EqualsUtil.integer(request, "stationAutoornot", "站业务属性", true);
            String ip = EqualsUtil.string(request, "ip", "工控机ip", false);
            String userName = EqualsUtil.string(request, "userName", "工控机系统登录名", false);
            String password = EqualsUtil.string(request, "password", "工控机系统登录密码", false);

            JSONObject dx = new JSONObject();
            dx.put("id", id);
            // 产线Code
            CMesLineT line =null;
            if (StringUtil.eqNu(cMesStationT.getLineCode())&& !"null".equals(cMesStationT.getLineCode())) {
                line= new CMesLineT();
                line.setName(cMesStationT.getLineCode());
                Map<String, Object> lineMap = cMesLineService.getLineByName(line);
                if(lineMap.size()>0){
                    cMesStationT.setLineId(Integer.parseInt((String)lineMap.get("ID")));
                    HttpSession session = request.getSession();
                    session.setAttribute("lineCode", cMesStationT.getLineCode());
                }else {
                        json.put("code", 1);
                        json.put("msg", "产线不存在");
                        return json;
                }

            } else {
                line= new CMesLineT();
                line.setId(cMesStationT.getLineId());
                List<CMesLineT> allLine = cMesLineService.findAllLine(line);
                if(allLine.size()>0){
                    HttpSession session = request.getSession();
                    session.setAttribute("lineCode", allLine.get(0).getName());
                }else {
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            }


            dx.put("lineId", lineId);
            dx.put("stationIndex", stationIndex);
            dx.put("stationName", stationName);
            dx.put("stationIndex1", stationIndex1);
            dx.put("stationName1", stationName1);
            dx.put("stationTime", stationTime);
            dx.put("stationAutoornot", stationAutoornot);
            dx.put("ip", ip);
            dx.put("userName", userName);
            dx.put("password", password);

            stationService.updateStation(cMesStationT);
            json.put("code", 0);
            json.put("msg", "成功");
        } catch (ServicesException e) {
            // TODO: handle exception
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            // TODO: handle exception
            json.put("code", 2);
            json.put("msg", "未知错误");
        }
        return json;

    }

    @RequestMapping(value = "/station/delStation", method = RequestMethod.POST)
    @ApiOperation(value = "根据id删除工位", notes = "根据id删除工位")
    @ApiImplicitParam(paramType = "query", name = "id", value = "工位ID", required = true, dataType = "Integer")
    @ResponseBody
    @OptionalLog(module = "基础建模", module2 = "工位管理", method = "删除工位")
    public JSONObject delStation(HttpServletRequest request, Integer id) throws ServicesException {

        JSONObject json = new JSONObject();
        try {
            HttpSession session=request.getSession();
            String stationCode=request.getParameter("stationCode");
            String lineCode=request.getParameter("lineCode");
            String stationIndex=request.getParameter("stationIndex");
            // 获取工位外部ID
            if (StringUtil.eqNu(stationCode)&&StringUtil.eqNu(lineCode)&&StringUtil.eqNu(stationIndex)) {
                CMesStationT cMesStationT = new CMesStationT();
                cMesStationT.setStationName(stationCode);
                cMesStationT.setStationIndex(Integer.parseInt(stationIndex));
                CMesLineT cMesLineT = new CMesLineT();
                cMesLineT.setName(lineCode);
                // 获取产线ID
                Map<String, Object> lineByName = cMesLineService.getLineByName(cMesLineT);
                if(lineByName.size()>0){
                    cMesStationT.setLineId((Integer)lineByName.get("ID"));
                    List<CMesStationT> stationNameAndId = cMesStationTService.findStationNameAndId(cMesStationT);
                    id=stationNameAndId.get(0).getId();
                    CMesLineT lineById = cMesLineService.findLineByid(stationNameAndId.get(0).getLineId());
                    session.setAttribute("lineCode",lineById.getName());
                    session.setAttribute("stationCode", stationCode);
                    session.setAttribute("stationIndex", stationNameAndId.get(0).getStationIndex());
                }else {
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            } else {
                CMesStationT cMesStationT = new CMesStationT();
                cMesStationT.setId(id);
                List<CMesStationT> stationNameAndId = cMesStationTService.findStationNameAndId(cMesStationT);
                if(stationNameAndId.size()>0){
                    CMesLineT lineById = cMesLineService.findLineByid(stationNameAndId.get(0).getLineId());
                    session.setAttribute("lineCode",lineById.getName());
                    session.setAttribute("stationCode", stationNameAndId.get(0).getStationName());
                    session.setAttribute("stationIndex", stationNameAndId.get(0).getStationIndex());
                }else {
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            }
            stationService.delStation(id);
            json.put("code", 0);
            json.put("msg", "成功");
        } catch (ServicesException e) {
            // TODO: handle exception
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            // TODO: handle exception
            json.put("code", 2);
            json.put("msg", "未知错误");
        }
        return json;

    }

}

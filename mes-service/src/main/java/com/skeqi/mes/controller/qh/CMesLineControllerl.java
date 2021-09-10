package com.skeqi.mes.controller.qh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesPlan;
import com.skeqi.mes.pojo.project.CMesSchedulingL;
import com.skeqi.mes.pojo.qh.CMesEndStocks;
import com.skeqi.mes.pojo.qh.CPlimsMaterialTypeT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.yin.UsersService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "产线管理", description = "产线管理", produces = MediaType.APPLICATION_JSON)
public class CMesLineControllerl {

    @Autowired
    CMesLineTService cMesLineTService;

    @Autowired
    UsersService usersService;

    // 修改产线状态
    @RequestMapping(value = "/line/toeditstatus", method = RequestMethod.POST)
    @ApiOperation(value = "修改产线状态", notes = "根据id修改产线状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "产线ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "status", value = "产线状态", required = true, dataType = "Integer")})
    @ResponseBody
    @OptionalLog(module = "基础建模", module2 = "产线管理", method = "修改产线状态")
    public JSONObject toeditstatus(HttpServletRequest request, Integer id, Integer status) throws ServicesException {
        Integer status2 = cMesLineTService.updateStatus(id, status);
        JSONObject json = new JSONObject();
        try {
            cMesLineTService.updateStatus(id, status);
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

    // 根据id删除产线
    @RequestMapping(value = "/line/delLine", method = RequestMethod.POST)
    @ApiOperation(value = "删除产线信息", notes = "根据id删除产线")
    @ApiImplicitParam(paramType = "query", name = "id", value = "产线ID", required = true, dataType = "Integer")
    @ResponseBody
    @OptionalLog(module = "基础建模", module2 = "产线管理", method = "删除产线信息")
    public JSONObject deleteLineById(HttpServletRequest request, Integer id) throws ServicesException {

        JSONObject json = new JSONObject();
        try {
            HttpSession session = request.getSession();
            String lineCode = request.getParameter("lineCode");
            // 获取外部产线id
            if (StringUtil.eqNu(lineCode)) {
                CMesLineT line = new CMesLineT();
                line.setName(lineCode);
                Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                if(lineMap.size()>0){
                    id = (Integer) lineMap.get("ID");
                    session.setAttribute("lineCode", lineCode);
                }else{
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            } else {
                CMesLineT line = new CMesLineT();
                line.setId(id);
                List<CMesLineT> allLine = cMesLineTService.findAllLine(line);
                if(allLine.size()>0){
                    session.setAttribute("lineCode", allLine.get(0).getName());
                }else{
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            }
            cMesLineTService.delLine(id);
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

    // 根据id查询产线
    @RequestMapping(value = "/line/toEditLine", method = RequestMethod.POST)
    @ApiOperation(value = "查询产线信息", notes = "根据id查询产线")
    @ApiImplicitParam(paramType = "query", name = "id", value = "产线ID", required = true, dataType = "Integer")
    @ResponseBody
    public JSONObject findLineById(HttpServletRequest request, Integer id) throws ServicesException {

        JSONObject json = new JSONObject();
        try {
            CMesLineT cMesLineT = cMesLineTService.findLineByid(id);
            json.put("code", 0);
            json.put("msg", cMesLineT);
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

    // 修改产线信息
    @RequestMapping(value = "/line/editLine", method = RequestMethod.POST)
    @ApiOperation(value = "修改产线信息", notes = "根据前端传来的数据修改")
    @ResponseBody
    @OptionalLog(module = "基础建模", module2 = "产线管理", method = "修改产线信息")
    public JSONObject editLineById(HttpServletRequest request, CMesLineT cMesLineT) throws ServicesException {

        JSONObject json = new JSONObject();
        try {
            HttpSession session = request.getSession();
            String lineCode = request.getParameter("lineCode");
            // 获取产线外部ID
            if (StringUtil.eqNu(lineCode)) {
                CMesLineT line = new CMesLineT();
                line.setName(lineCode);
                Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                if(lineMap.size()>0){
                    cMesLineT.setId((Integer) lineMap.get("ID"));
                    session.setAttribute("lineCode", lineCode);
                }else{
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }
            } else {
                CMesLineT line = new CMesLineT();
                line.setId(cMesLineT.getId());
                List<CMesLineT> allLine = cMesLineTService.findAllLine(line);
                if(allLine.size()>0){
                    session.setAttribute("lineCode", allLine.get(0).getName());
                }else{
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            }

            //自定义属性所需参数
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            cMesLineT.setCustom(jsonArray);
            cMesLineTService.updateLine(cMesLineT);
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

    // 产线列表
    @RequestMapping(value = "/line/lineManager", method = RequestMethod.POST)
    @ApiOperation(value = "产线列表", notes = "产线列表")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "companyCode", value = "所属公司编码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "factoryCode", value = "所属工厂编码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaCode", value = "所属区域编码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "plantCode", value = "所属车间编码", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lineName", value = "产线名称", required = false, paramType = "query", dataType = "String"),
    })
    public JSONObject lineManager(HttpServletRequest request) throws Exception {
        Integer pageNum = EqualsUtil.integer(request, "pageNum", "页码", false);
        Integer pageSize = EqualsUtil.integer(request, "pageSize", "每页记录数", false);
        PageHelper.startPage(null == pageNum ? 1 : pageNum, null == pageSize ? 10 : pageSize);
        String companyCode = EqualsUtil.string(request, "companyCode", "所属公司编码", false);
        String factoryCode = EqualsUtil.string(request, "factoryCode", "所属工厂编码", false);
        String areaCode = EqualsUtil.string(request, "areaCode", "所属区域编码", false);
        String plantCode = EqualsUtil.string(request, "plantCode", "所属车间编码", false);
        String lineName = EqualsUtil.string(request, "lineName", "产线名称", false);
        CMesLineT cMesLineT = new CMesLineT();
        cMesLineT.setCompanyCode(companyCode);
        cMesLineT.setFactoryCode(factoryCode);
        cMesLineT.setAreaCode(areaCode);
        cMesLineT.setPlantCode(plantCode);
        cMesLineT.setName(lineName);
        List<CMesLineT> lineList = cMesLineTService.findAllLine(cMesLineT);
        PageInfo<CMesLineT> pageInfo = new PageInfo<>(lineList);
        JSONObject json = new JSONObject();
        try {

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

    // 新增产线
    @ResponseBody
    @RequestMapping(value = "/line/addLine", method = RequestMethod.POST)
    @ApiOperation("添加产线")
    @OptionalLog(module = "基础建模", module2 = "产线管理", method = "添加产线")
    public JSONObject addLine(HttpServletRequest request, @ModelAttribute @Valid CMesLineT cMesLineT) throws ServicesException {
        JSONObject json = new JSONObject();
        try {
            //自定义属性值（内容）
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            cMesLineT.setCustom(jsonArray);
            String lineCode = request.getParameter("lineCode");
            if (StringUtil.eqNu(lineCode) && !StringUtil.eqNu(cMesLineT.getCode())) {
                cMesLineT.setCode(lineCode);
            }
            cMesLineTService.addLine(cMesLineT);
            json.put("code", 0);
            json.put("msg", "添加成功");
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

    // 结存量
    @ResponseBody
    @RequestMapping(value = "/endStock", method = RequestMethod.POST)
    @ApiOperation("结存量")
    public JSONObject endStock() throws Exception {
        JSONObject json = new JSONObject();
        List<CMesEndStocks> list = new ArrayList<>();
        String proName = "311jss";// 产品名称
        String lineName = "CB311焊接线";// 产线名称
        String stationName = "";// 工位名称
        Integer lineRegion = 1;// 产线区域
        CMesEndStocks sum = cMesLineTService.findByName(proName, lineName, stationName, lineRegion);
        sum.setProName(proName);
        sum.setLineName(lineName);
        sum.setLineRegion(lineRegion);
        list.add(sum);

        String proName1 = "311jss";// 产品名称
        String lineName1 = "CB318焊接线";// 产线名称
        String stationName1 = "";// 工位名称
        Integer lineRegion1 = 6;// 产线区域
        CMesEndStocks sum1 = cMesLineTService.findByName(proName1, lineName1, stationName1, lineRegion1);
        sum1.setProName(proName1);
        sum1.setLineName(lineName1);
        sum1.setLineRegion(lineRegion1);
        list.add(sum1);

        String proName2 = "311jss";// 产品名称外协涂装线
        String lineName2 = "电泳生产线";// 产线名称
        String stationName2 = "";// 工位名称
        Integer lineRegion2 = 5;// 产线区域
        CMesEndStocks sum2 = cMesLineTService.findByName(proName2, lineName2, stationName2, lineRegion2);
        sum2.setProName(proName2);
        sum2.setLineName(lineName2);
        sum2.setLineRegion(lineRegion2);
        list.add(sum2);

        String proName3 = "311jss";// 产品名称
        String lineName3 = "外协涂装产线";// 产线名称
        String stationName3 = "";// 工位名称
        Integer lineRegion3 = 5;// 产线区域
        CMesEndStocks sum3 = cMesLineTService.findByName(proName3, lineName3, stationName3, lineRegion3);
        sum3.setProName(proName3);
        sum3.setLineName(lineName3);
        sum3.setLineRegion(lineRegion3);
        list.add(sum3);

        String proName4 = "311jss";// 产品名称
        String lineName4 = "重卡装配线";// 产线名称
        String stationName4 = "";// 工位名称
        Integer lineRegion4 = 7;// 产线区域
        CMesEndStocks sum4 = cMesLineTService.findByName(proName4, lineName4, stationName4, lineRegion4);
        sum4.setProName(proName4);
        sum4.setLineName(lineName4);
        sum4.setLineRegion(lineRegion4);
        list.add(sum4);

        String proName5 = "318pd";// 产品名称
        String lineName5 = "CB318焊接线";// 产线名称
        String stationName5 = "";// 工位名称
        Integer lineRegion5 = 6;// 产线区域
        CMesEndStocks sum5 = cMesLineTService.findByName(proName5, lineName5, stationName5, lineRegion5);
        sum5.setProName(proName5);
        sum5.setLineName(lineName5);
        sum5.setLineRegion(lineRegion5);
        list.add(sum5);

        String proName6 = "318pd";// 产品名称
        String lineName6 = "电泳生产线";// 产线名称
        String stationName6 = "";// 工位名称
        Integer lineRegion6 = 5;// 产线区域
        CMesEndStocks sum6 = cMesLineTService.findByName(proName6, lineName6, stationName6, lineRegion6);
        sum6.setProName(proName6);
        sum6.setLineName(lineName6);
        sum6.setLineRegion(lineRegion6);
        list.add(sum6);

        String proName7 = "318pd";// 产品名称
        String lineName7 = "外协涂装产线";// 产线名称
        String stationName7 = "";// 工位名称
        Integer lineRegion7 = 5;// 产线区域
        CMesEndStocks sum7 = cMesLineTService.findByName(proName7, lineName7, stationName7, lineRegion7);
        sum7.setProName(proName7);
        sum7.setLineName(lineName7);
        sum7.setLineRegion(lineRegion7);
        list.add(sum7);

        String proName8 = "318pd";// 产品名称
        String lineName8 = "重卡装配线";// 产线名称
        String stationName8 = "";// 工位名称
        Integer lineRegion8 = 7;// 产线区域
        CMesEndStocks sum8 = cMesLineTService.findByName(proName8, lineName8, stationName8, lineRegion8);
        sum8.setProName(proName8);
        sum8.setLineName(lineName8);
        sum8.setLineRegion(lineRegion8);
        list.add(sum8);

        String proName9 = "318zgd";// 产品名称
        String lineName9 = "CB318焊接线";// 产线名称
        String stationName9 = "";// 工位名称
        Integer lineRegion9 = 6;// 产线区域
        CMesEndStocks sum9 = cMesLineTService.findByName(proName9, lineName9, stationName9, lineRegion9);
        sum9.setProName(proName9);
        sum9.setLineName(lineName9);
        sum9.setLineRegion(lineRegion9);
        list.add(sum9);

        String proName11 = "318zgd";// 产品名称
        String lineName11 = "电泳生产线";// 产线名称
        String stationName11 = "";// 工位名称
        Integer lineRegion11 = 5;// 产线区域
        CMesEndStocks sum11 = cMesLineTService.findByName(proName11, lineName11, stationName11, lineRegion11);
        sum11.setProName(proName11);
        sum11.setLineName(lineName11);
        sum11.setLineRegion(lineRegion11);
        list.add(sum11);

        String proName12 = "318zgd";// 产品名称
        String lineName12 = "外协涂装产线";// 产线名称
        String stationName12 = "";// 工位名称
        Integer lineRegion12 = 5;// 产线区域
        CMesEndStocks sum12 = cMesLineTService.findByName(proName12, lineName12, stationName12, lineRegion12);
        sum12.setProName(proName12);
        sum12.setLineName(lineName12);
        sum12.setLineRegion(lineRegion12);
        list.add(sum12);

        String proName13 = "318zgd";// 产品名称
        String lineName13 = "重卡装配线";// 产线名称
        String stationName13 = "";// 工位名称
        Integer lineRegion13 = 7;// 产线区域
        CMesEndStocks sum13 = cMesLineTService.findByName(proName13, lineName13, stationName13, lineRegion13);
        sum13.setProName(proName13);
        sum13.setLineName(lineName13);
        sum13.setLineRegion(lineRegion13);
        list.add(sum13);

        json.put("list", list);
        return json;
    }

/*	@ResponseBody
	@RequestMapping(value = "/operationExport", method = RequestMethod.POST)
	@ApiOperation("sssa")
	public JSONObject operationExport(@RequestBody List<CPlimsMaterialTypeT> cMaterialTypeList) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		json.put("cMaterialTypeList", cMaterialTypeList);
		return json;
	}*/
}

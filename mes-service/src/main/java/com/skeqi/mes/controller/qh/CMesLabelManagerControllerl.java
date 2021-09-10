/**
 *
 */
package com.skeqi.mes.controller.qh;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.pojo.CMesLabelType;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.zch.NextBarcodeService;
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
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.TechnologyService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author: zhangr
 * @版本: 1.0
 * @创建日期: 2020年4月27日 下午5:00:14
 * @ClassName CMesLabelManagerControllerl
 * @类描述-Description: TODO
 * @修改记录:
 */

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "标签管理", description = "标签管理", produces = MediaType.APPLICATION_JSON)
public class CMesLabelManagerControllerl {

    @Autowired
    TechnologyService service;

    @Autowired
    CMesLineTService cMesLineTService;
    @Autowired
    TechnologyService technologyService;
    @Autowired
    NextBarcodeService nextBarcodeService;

    // 产线列表
    @RequestMapping(value = "/labelManager", method = RequestMethod.POST)
    @ApiOperation(value = "标签列表", notes = "标签列表")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int")})
    public JSONObject labelManager(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) throws ServicesException {
        PageHelper.startPage(pageNum, pageSize);
        List<CMesLabelManagerT> lineList = service.findAllLabelManager(null);
        PageInfo<CMesLabelManagerT> pageInfo = new PageInfo<>(lineList);
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

    /**
     * 通过ID查询标签信息
     */
    @RequestMapping(value = "findLabelManagerById", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据ID查询标签信息", notes = "根据ID查询标签信息")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    public JSONObject findLabelManagerById(Integer id) {
        JSONObject json = new JSONObject();
        try {
            CMesLabelManagerT labelManager = service.findLabelManagerByid(id);
            json.put("labelManager", labelManager);
            json.put("code", 0);
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", "查询失败");
        }
        return json;
    }

    /**
     * 添加标签
     */
    @RequestMapping(value = "addLabelManager", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加标签")
    @OptionalLog(module = "生产", module2 = "标签管理", method = "新增标签")
    public JSONObject addLabelManager(@ModelAttribute CMesLabelManagerT cMesLabelManagerT, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        try {
            if (StringUtil.eqNu(cMesLabelManagerT.getLabelTypeCode())) {
                // 标签id
                CMesLabelType cMesLabelType = new CMesLabelType();
                cMesLabelType.setName(cMesLabelManagerT.getLabelTypeCode());
                List<CMesLabelType> allLabelType = technologyService.findAllLabelType(cMesLabelType);
                if (allLabelType.size() > 0) {
                    cMesLabelManagerT.setLabelTypeId(allLabelType.get(0).getId());
                    session.setAttribute("labelTypeCode", cMesLabelManagerT.getLabelTypeCode());
                } else {
                    json.put("code", 1);
                    json.put("msg", "标签不存在");
                    return json;
                }
            } else {
                CMesLabelType cMesLabelType = new CMesLabelType();
                cMesLabelType.setId(cMesLabelManagerT.getLabelTypeId());
                List<CMesLabelType> allLabelType = technologyService.findAllLabelType(cMesLabelType);
                if(allLabelType.size()>0){
                    session.setAttribute("labelTypeCode", allLabelType.get(0).getName());
                }else {
                    json.put("code", 1);
                    json.put("msg", "标签不存在");
                    return json;
                }

            }
            // 产线id
            if (StringUtil.eqNu(cMesLabelManagerT.getLineCode())) {
                CMesLineT line = new CMesLineT();
                line.setName(cMesLabelManagerT.getLineCode());
                Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                if (lineMap.size() > 0) {
                    cMesLabelManagerT.setLineId((Integer) lineMap.get("ID"));
                    session.setAttribute("lineCode", cMesLabelManagerT.getLineCode());
                } else {
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            } else {
                CMesLineT line = new CMesLineT();
                line.setId(cMesLabelManagerT.getLineId());
                List<CMesLineT> allLine = cMesLineTService.findAllLine(line);
                if(allLine.size()>0){
                    session.setAttribute("lineCode", allLine.get(0).getName());
                }else {
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            }
            service.addLabelManager(cMesLabelManagerT);
            json.put("code", 0);
            json.put("msg", "添加成功");
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", "添加失败");
        }
        return json;
    }

    /**
     * 修改标签信息
     */
    @RequestMapping(value = "editLabelManager", method = RequestMethod.POST)
    @ApiOperation(value = "修改标签")
    @ResponseBody
    @OptionalLog(module = "生产", module2 = "标签管理", method = "编辑标签")
    public JSONObject editLabelManager(@ModelAttribute CMesLabelManagerT cMesLabelManagerT, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        System.err.println("标签名====" + cMesLabelManagerT.getLabelName());
        try {
            HttpSession session = request.getSession();
            if (StringUtil.eqNu(cMesLabelManagerT.getLabelTypeCode())) {
                // 标签id
                CMesLabelType cMesLabelType = new CMesLabelType();
                cMesLabelType.setName(cMesLabelManagerT.getLabelTypeCode());
                List<CMesLabelType> allLabelType = technologyService.findAllLabelType(cMesLabelType);
                if (allLabelType.size() > 0) {
                    cMesLabelType.setId(allLabelType.get(0).getId());
                    session.setAttribute("labelTypeCode", cMesLabelManagerT.getLabelTypeCode());
                } else {
                    json.put("code", 1);
                    json.put("msg", "标签不存在");
                    return json;
                }

            } else {
                CMesLabelType cMesLabelType = new CMesLabelType();
                cMesLabelType.setId(cMesLabelManagerT.getLabelTypeId());
                List<CMesLabelType> allLabelType = technologyService.findAllLabelType(cMesLabelType);
                if(allLabelType.size()>0){
                    session.setAttribute("labelTypeCode", allLabelType.get(0).getName());
                }else {
                    json.put("code", 1);
                    json.put("msg", "标签不存在");
                    return json;
                }

            }
            // 产线id
            if (StringUtil.eqNu(cMesLabelManagerT.getLineCode())) {
                CMesLineT line = new CMesLineT();
                line.setName(cMesLabelManagerT.getLineCode());
                Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                if (lineMap.size() > 0) {
                    cMesLabelManagerT.setLineId((Integer) lineMap.get("ID"));
                    session.setAttribute("lineCode", cMesLabelManagerT.getLineCode());
                } else {
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            } else {
                CMesLineT line = new CMesLineT();
                line.setId(cMesLabelManagerT.getLineId());
                List<CMesLineT> allLine = cMesLineTService.findAllLine(line);
                if(allLine.size()>0){
                    session.setAttribute("lineCode", allLine.get(0).getName());
                }else {
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }
            }

            service.updateLabelManager(cMesLabelManagerT);
            json.put("code", 0);
            json.put("msg", "修改成功");
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", "修改失败");
        }
        return json;
    }

    /**
     * 删除标签信息
     */
    @RequestMapping(value = "removeLabelManager", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除标签信息")
    @ApiImplicitParam(name = "id", value = "标签id", required = true, paramType = "query", dataType = "int")
    @OptionalLog(module = "生产", module2 = "标签管理", method = "删除标签")
    public JSONObject removeLabelManager(Integer id, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            HttpSession session = request.getSession();
            CMesLineT line = null;
            String labelCode = request.getParameter("labelCode");
            String lineCode = request.getParameter("lineCode");
            // 获取标签外部ID
            if (StringUtil.eqNu(labelCode) && StringUtil.eqNu(lineCode)) {
                line = new CMesLineT();
                line.setName(lineCode);
                Map<String, Object> lineMap = cMesLineTService.getLineByName(line);
                if (lineMap.size() > 0) {
                    Map<String, Object> idAndNameAndLineId = nextBarcodeService.getLabelByIDAndNameAndLineId(null, labelCode, (Integer) lineMap.get("ID"));
                    id = (Integer) idAndNameAndLineId.get("id");
                    session.setAttribute("labelCode", labelCode);
                    session.setAttribute("lineCode", lineCode);
                } else {
                    json.put("code", 1);
                    json.put("msg", "标签不存在");
                    return json;
                }
            } else {
                Map<String, Object> idAndNameAndLineId = nextBarcodeService.getLabelByIDAndNameAndLineId(id, null, null);
                if(idAndNameAndLineId.size()>0){
                    CMesLineT lineT = cMesLineTService.findLineByid((Integer) idAndNameAndLineId.get("lineId"));
                    session.setAttribute("labelCode", idAndNameAndLineId.get("labelName"));
                    session.setAttribute("lineCode", lineT.getName());
                }else {
                    json.put("code", 1);
                    json.put("msg", "标签不存在");
                    return json;
                }

            }

            service.delLabelManager(id);
            json.put("code", 0);
            json.put("msg", "删除成功");
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", "删除错误");
        }
        return json;
    }
}

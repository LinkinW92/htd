/**
 *
 */
package com.skeqi.mes.controller.qh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.skeqi.mes.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLabelType;
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
 * @创建日期: 2020年4月28日 上午8:49:20
 * @ClassName CMesRuleTypeManagerController
 * @类描述-Description: TODO
 * @修改记录:
 */
@RestController
@Api(value = "规则管理", description = "规则管理")
@RequestMapping("/api")
public class CMesRuleTypeManagerControllerl {

    @Autowired
    TechnologyService service;

    @Autowired
    CMesLineTService cMesLineTService;
    @Autowired
    TechnologyService technologyService;

    /**
     * 规则类型列表
     */
    @RequestMapping(value = "ruleTypeManager", method = RequestMethod.POST)
    @ApiOperation(value = "规则类型列表", notes = "规则类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int")})
    public JSONObject lineManager(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize) throws ServicesException {
        PageHelper.startPage(pageNum, pageSize);
        List<CMesLabelType> ruleTypeManagerList = service.findAllLabelType(null);
        PageInfo<CMesLabelType> pageInfo = new PageInfo<>(ruleTypeManagerList);
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
     * 添加规则类型
     */
    @RequestMapping(value = "addRuleTypeManager", method = RequestMethod.POST)
    @ApiOperation(value = "添加规则类型", notes = "添加规则类型")
    @OptionalLog(module = "生产", module2 = "规则管理", method = "新增规则")
    public JSONObject addRuleTypeManager(@ModelAttribute CMesLabelType cMesLabelType) {
        JSONObject json = new JSONObject();
        try {
            service.addLabelType(cMesLabelType);
            json.put("code", 0);
            json.put("msg", "添加成功");
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        }
        return json;
    }

    /**
     * 通过ID查询规则类型信息
     */
    @RequestMapping(value = "/findRuleTypeManagerById", method = RequestMethod.POST)
    @ApiOperation(value = "通过ID查询规则类型信息", notes = "通过ID查询规则类型信息")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    public JSONObject findRuleTypeManagerById(Integer id) {
        JSONObject json = new JSONObject();
        try {
            CMesLabelType ruleTypeManager = service.findLabelTypeByid(id);
            json.put("ruleTypeManager", ruleTypeManager);
            json.put("code", 0);
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        }
        return json;
    }

    /**
     * 修改规则类型
     */
    @RequestMapping(value = "editRuleTypeManager", method = RequestMethod.POST)
    @ApiOperation(value = "修改规则类型", notes = "修改规则类型")
    @OptionalLog(module = "生产", module2 = "规则管理", method = "编辑规则")
    public JSONObject editRuleTypeManager(@ModelAttribute CMesLabelType cMesLabelType,HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            CMesLabelType cMesLabelTypes=null;
            HttpSession session = request.getSession();
            String ruleCode = request.getParameter("ruleCode");
            if (StringUtil.eqNu(ruleCode)) {
                cMesLabelTypes=new CMesLabelType();
                cMesLabelTypes.setName(ruleCode);
                List<CMesLabelType> allLabelType = technologyService.findAllLabelType(cMesLabelType);
                if(allLabelType.size()>0){
                    cMesLabelType.setId(allLabelType.get(0).getId());
                    session.setAttribute("ruleCode", ruleCode);
                }else {
                    json.put("code", 1);
                    json.put("msg", "规则不存在");
                    return json;
                }
            } else {
                cMesLabelTypes=new CMesLabelType();
                cMesLabelTypes.setId(cMesLabelType.getId());
                List<CMesLabelType> allLabelType = technologyService.findAllLabelType(cMesLabelType);
                if(allLabelType.size()>0){
                    session.setAttribute("ruleCode", allLabelType.get(0).getName());
                }else {
                    // 取当前填入修改的名称
                    session.setAttribute("ruleCode", cMesLabelType.getName());
                }
            }
            service.updateLabelType(cMesLabelType);
            json.put("code", 0);
            json.put("msg", "修改成功");
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        }
        return json;
    }

    /**
     * 删除规则类型
     */
    @RequestMapping(value = "/removeRuleTypeManager", method = RequestMethod.POST)
    @ApiOperation(value = "删除规则类型", notes = "根据删除规则类型")
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "int")
    @OptionalLog(module = "生产", module2 = "规则管理", method = "删除规则")
    public JSONObject removeRuleTypeManager(Integer id, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            HttpSession session = request.getSession();
            String ruleCode = request.getParameter("ruleCode");
            if (StringUtil.eqNu(ruleCode)) {
				CMesLabelType cMesLabelType=new CMesLabelType();
				cMesLabelType.setName(ruleCode);
				List<CMesLabelType> allLabelType = technologyService.findAllLabelType(cMesLabelType);
				if(allLabelType.size()>0){
                    id=allLabelType.get(0).getId();
                    session.setAttribute("ruleCode", ruleCode);
                }else {
                    json.put("code", 1);
                    json.put("msg", "规则不存在");
                    return json;
                }

            } else {
				CMesLabelType cMesLabelType=new CMesLabelType();
				cMesLabelType.setId(id);
				List<CMesLabelType> allLabelType = technologyService.findAllLabelType(cMesLabelType);
				session.setAttribute("ruleCode", allLabelType.get(0).getName());
            }
            service.delLabelType(id);
            json.put("code", 0);
            json.put("msg", "删除成功");
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
        }
        return json;
    }
}

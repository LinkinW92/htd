package com.skeqi.mes.controller.qh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesRecipeService;
import com.skeqi.mes.service.all.CMesRecipeTService;
import com.skeqi.mes.service.lcy.UpdataBoltViewSomeDataService;
import com.skeqi.mes.service.wjp.AllotmentManagementService;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "配方明细管理", description = "配方明细管理", produces = MediaType.APPLICATION_JSON)
public class CMesRecipeDetailControllerl {

    @Autowired
    CMesRecipeTService tservice;

    @Autowired
    CMesProductionTService pservice;

    @Autowired
    CMesMaterialService mservice;

    @Autowired
    UpdataBoltViewSomeDataService ubv;
    @Autowired
    CMesRecipeService cMesRecipeService;

    @Autowired
    AllotmentManagementService allotmentManagementService;

    @SuppressWarnings("restriction")
    static BASE64Encoder encoder = new BASE64Encoder();

    // 配方明细列表
    @RequestMapping(value = "/recipeDetailAll", method = RequestMethod.POST)
    @ApiOperation(value = "配方明细列表", notes = "配方明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "tid", value = "配方id", required = false, paramType = "query", dataType = "int")})
    public JSONObject lineManager(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize, Integer tid) throws ServicesException {
        PageHelper.startPage(pageNum, pageSize);
        CMesRecipeDatilT t = new CMesRecipeDatilT();
        if (tid != null && !tid.equals("")) {
            t.setRecipeId(tid);
        }

        List<CMesRecipeDatilT> findAllRecipeDatil = tservice.findAllRecipeDatil(t);   //配方明细列表
        PageInfo<CMesRecipeDatilT> pageInfo = new PageInfo<>(findAllRecipeDatil);
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
     * 添加
     */
    @RequestMapping(value = "insertrecipeDetail", method = RequestMethod.POST)
    @ApiOperation(value = "添加配方明细", notes = "添加配方明细")
    @OptionalLog(module = "生产", module2 = "配方明细管理", method = "添加配方明细")
    public Map<String, Object> insertrecipeDetail(HttpServletRequest request, @RequestBody CMesRecipeDatilT recipe) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //	CMesRecipeDatilT recipe = new CMesRecipeDatilT();
        //	String stepCategory = recipe.getStepCategory();
//		System.err.println("stepCategory=="+recipe);
        try {
            CMesRecipeT cMesRecipeT = null;
            HttpSession session = request.getSession();
            // 配方Code   recipeCode+totalRecipeCode定位到具体的recipeId,stepno+recipeId定位到具体的ID
            if (StringUtil.eqNu(recipe.getRecipeCode()) && StringUtil.eqNu(recipe.getTotalRecipeCode())) {
                //根据配方名称、总配方名称获取配方id
                cMesRecipeT = new CMesRecipeT();
                cMesRecipeT.setRecipeName(recipe.getRecipeCode());
                cMesRecipeT.setTotalRecipeName(recipe.getTotalRecipeCode());
                CMesRecipeT byIdToAndR = allotmentManagementService.findByIdToAndR(cMesRecipeT);
                if (null != byIdToAndR) {
                    recipe.setRecipeId(byIdToAndR.getId());
                    session.setAttribute("recipeCode", byIdToAndR.getRecipeName());
                    session.setAttribute("totalRecipeCode", byIdToAndR.getTotalRecipeName());
                }else{
                    map.put("code", 1);
                    map.put("msg", "配方不存在");
                    return map;
                }
            } else {
                //根据配方id获取配方名称和总配方名称
                cMesRecipeT = new CMesRecipeT();
                cMesRecipeT.setId(recipe.getRecipeId());
                CMesRecipeT byIdToAndR = allotmentManagementService.findByIdToAndR(cMesRecipeT);
                if (null != byIdToAndR) {
                    session.setAttribute("recipeCode", byIdToAndR.getRecipeName());
                    session.setAttribute("totalRecipeCode", byIdToAndR.getTotalRecipeName());
                }else {
                    map.put("code", 1);
                    map.put("msg", "配方不存在");
                    return map;
                }
            }
            if(recipe.getWorkorderId() != null) {
                recipe = tservice.copyRecipe(recipe);
            }
            tservice.addRecipeDetail(recipe);
            map.put("code", 0);
            map.put("msg", "添加成功");
        } catch (ServicesException e) {
            // TODO: handle exception
            map.put("code", 1);
            map.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            map.put("code", 1);
            map.put("msg", "添加失败，请刷新页面重试");
        }
        return map;
    }

    @RequestMapping(value = "test111", method = RequestMethod.POST)
    public void test111(HttpServletRequest request) {
    	CMesRecipeDatilT recipe = new CMesRecipeDatilT();
    	recipe.setId(1767);
    	CMesRecipeDatilT recipe1 = tservice.copyRecipe(recipe);
    	System.out.println("recipe: " + recipe1.toString());
    }

    /**
     * 修改
     */
    @RequestMapping(value = "updaterecipedetails", method = RequestMethod.POST)
    @ApiOperation(value = "修改配方明细", notes = "修改配方明细")
    @OptionalLog(module = "生产", module2 = "配方明细管理", method = "修改配方明细")
    public Map<String, Object> updaterecipedetails(HttpServletRequest request, @RequestBody CMesRecipeDatilT recipe) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //	CMesRecipeDatilT recipe = new CMesRecipeDatilT();
        //	String stepCategory = recipe.getStepCategory();
        try {
            CMesRecipeT cMesRecipeT = null;
            HttpSession session = request.getSession();
            // 配方Code   recipeCode+totalRecipeCode定位到具体的recipeId,stepno+recipeId定位到具体的ID
            if (StringUtil.eqNu(recipe.getRecipeCode()) && StringUtil.eqNu(recipe.getTotalRecipeCode())) {
                //根据总配方名称获取配方id
                cMesRecipeT = new CMesRecipeT();
                cMesRecipeT.setRecipeName(recipe.getRecipeCode());
                cMesRecipeT.setTotalRecipeName(recipe.getTotalRecipeCode());
                CMesRecipeT byIdToAndR = allotmentManagementService.findByIdToAndR(cMesRecipeT);
                if (null != byIdToAndR) {
                    recipe.setRecipeId(byIdToAndR.getId());
                }else {
                    map.put("code", 1);
                    map.put("msg", "配方不存在");
                    return map;
                }
                // 获取ID
                CMesRecipeDatilT cMesRecipeDatilT = new CMesRecipeDatilT();
                cMesRecipeDatilT.setStepno(recipe.getStepno());
                cMesRecipeDatilT.setRecipeId(recipe.getRecipeId());
                List<CMesRecipeDatilT> recipeDetailId = tservice.findRecipeDetailId(cMesRecipeDatilT);
                if (recipeDetailId.size() > 0) {
                    recipe.setRecipeId(recipeDetailId.get(0).getId());
                    session.setAttribute("recipeCode", recipe.getRecipeCode());
                    session.setAttribute("totalRecipeCode", recipe.getTotalRecipeCode());
                }else{
                    map.put("code", 1);
                    map.put("msg", "配方明细不存在");
                    return map;
                }
            } else {
                //根据配方id获取配方名称和总配方名称
                cMesRecipeT = new CMesRecipeT();
                cMesRecipeT.setId(recipe.getRecipeId());
                CMesRecipeT byIdToAndR = allotmentManagementService.findByIdToAndR(cMesRecipeT);
                if (null != byIdToAndR) {
                    session.setAttribute("recipeCode", byIdToAndR.getRecipeName());
                    session.setAttribute("totalRecipeCode", byIdToAndR.getTotalRecipeName());
                    session.setAttribute("stepNo", recipe.getStepno());
                }else {
                    map.put("code", 1);
                    map.put("msg", "配方明细不存在");
                    return map;
                }
            }
            if(recipe.getWorkorderId() != null) {
            	recipe = tservice.copyRecipe(recipe);
            }
            tservice.updateRecipeDetail(recipe);
            map.put("code", 0);
            map.put("msg", "修改成功");
        } catch (ServicesException e) {
            // TODO: handle exception
            map.put("code", 1);
            map.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            map.put("code", 1);
            map.put("msg", "未知错误");
        }
        return map;
    }

    @RequestMapping(value = "findAllRecipeType", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有类别", notes = "查询所有类别")
    public Map<String, Object> findAllRecipeType(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<CMesRecipeTypeT> type = tservice.findAllRecipeType();
            map.put("code", 0);
            map.put("msg", type);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            map.put("code", 1);
            map.put("msg", "未知错误");
        }
        return map;
    }

    @RequestMapping(value = "findAllMaterial", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有物料", notes = "查询所有物料")
    public Map<String, Object> findAllMaterial(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<CMesJlMaterialT> findAllMaterial = tservice.findAllJLMaterial();  //物料列表
            map.put("code", 0);
            map.put("msg", findAllMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            map.put("code", 1);
            map.put("msg", "未知错误");
        }
        return map;
    }

    //删除
    @RequestMapping(value = "delRecipeDetail", method = RequestMethod.POST)
    @ApiOperation(value = "删除配方明细", notes = "删除配方明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配方明细id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "stepNo", value = "步序", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "recipeId", value = "配方id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "workorderId", value = "工单id", required = false, paramType = "query", dataType = "int"),
            })
    @OptionalLog(module = "生产", module2 = "配方明细管理", method = "删除配方明细")
    public Map<String, Object> delRecipeDetail(HttpServletRequest request, Integer id, String stepNo, String recipeId, Integer workorderId) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.err.println("删除配方详情");
        try {
            CMesRecipeDatilT c = new CMesRecipeDatilT();
            CMesRecipeT cMesRecipeT = null;
            HttpSession session = request.getSession();
            String recipeCode = request.getParameter("recipeCode");
            String totalRecipeCode = request.getParameter("totalRecipeCode");
            if (StringUtil.eqNu(recipeCode) && StringUtil.eqNu(totalRecipeCode)) {
                //根据总配方名称获取配方id
                cMesRecipeT = new CMesRecipeT();
                cMesRecipeT.setRecipeName(recipeCode);
                cMesRecipeT.setTotalRecipeName(totalRecipeCode);
                CMesRecipeT byIdToAndR = allotmentManagementService.findByIdToAndR(cMesRecipeT);
                if (null != byIdToAndR) {
                    c.setRecipeId(byIdToAndR.getId());
                    recipeId=String.valueOf(byIdToAndR.getId());
                }else {
                    map.put("code", 1);
                    map.put("msg", "配方不存在");
                    return map;
                }
                // 获取ID
                CMesRecipeDatilT cMesRecipeDatilT = new CMesRecipeDatilT();
                cMesRecipeDatilT.setRecipeId(byIdToAndR.getId());
                cMesRecipeDatilT.setStepno("1");
                stepNo="1";
                List<CMesRecipeDatilT> recipeDetailId = tservice.findRecipeDetailId(cMesRecipeDatilT);
                if (recipeDetailId.size() > 0) {
                    id=recipeDetailId.get(0).getId();
                    session.setAttribute("recipeCode", recipeCode);
                    session.setAttribute("totalRecipeCode", totalRecipeCode);
                }else{
                    map.put("code", 1);
                    map.put("msg", "配方明细不存在");
                    return map;
                }
            } else {
                //根据配方id获取配方名称和总配方名称
                cMesRecipeT = new CMesRecipeT();
                cMesRecipeT.setId(Integer.parseInt(recipeId));
                CMesRecipeT byIdToAndR = allotmentManagementService.findByIdToAndR(cMesRecipeT);
                if (null != byIdToAndR) {
                    session.setAttribute("recipeCode", byIdToAndR.getRecipeName());
                    session.setAttribute("totalRecipeCode", byIdToAndR.getTotalRecipeName());
                }else{
                    map.put("code", 1);
                    map.put("msg", "配方明细不存在");
                    return map;
                }

            }
            c.setId(id);
            c.setStepno(stepNo);
            c.setRecipeId(Integer.parseInt(recipeId));
            if(workorderId != null) {
            	c.setWorkorderId(workorderId);
            	c = tservice.copyRecipe(c);
            }
            tservice.delRecipeDatil(c);
            map.put("code", 0);
        } catch (ServicesException e) {
            map.put("code", 1);
            map.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            map.put("code", 2);
            map.put("msg", "删除失败，请刷新页面重试");
        }
        return map;
    }


    @RequestMapping(value = "updataViewBoltData", method = RequestMethod.POST)
    @ApiOperation(value = "配置螺栓位置", notes = "配置螺栓位置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "配方明细id", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "jsonStr", value = "json字符", required = false, paramType = "query", dataType = "int")})
    @OptionalLog(module = "生产", module2 = "配方明细管理", method = "配置螺栓位置")
    public JSONObject updataViewBoltData(HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        String id = request.getParameter("id");
        String jsonStr = request.getParameter("jsonStr");

        ubv.updataViewBoltData(id, jsonStr);
        jo.put("code", 0);
        jo.put("msg", "保存成功");
        return jo;
    }

    // 查询配方明细列表
    @RequestMapping(value = "findFormulaDetailById", method = RequestMethod.POST)
    @ApiOperation(value = "根据id查询配方明细", notes = "根据id查询配方明细")
    public Map<String, Object> findFormulaDetailById(HttpServletRequest request, Integer id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CMesRecipeDatilT cdt = tservice.findRecipeDetailByid(id);
            map.put("code", 0);
            map.put("msg", "");
            map.put("result", cdt);
        } catch (ServicesException e) {
            // TODO: handle exception
            map.put("code", 1);
            map.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            map.put("code", 1);
            map.put("msg", "未知错误");
        }
        return map;
    }

}


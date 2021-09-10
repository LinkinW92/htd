/**
 *
 */
package com.skeqi.mes.controller.qh;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.service.project.CMesProductionService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
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
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.yin.DeviceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author: zhangr
 * @版本: 1.0
 * @创建日期: 2020年4月27日 下午2:00:48
 * @ClassName CMesProductionController
 * @类描述-Description: TODO
 * @修改记录:
 */

@Controller
@RequestMapping(value = "api/Production", produces = MediaType.APPLICATION_JSON)
@Api(value = "产品管理(琦航)", description = "产品管理(琦航)", produces = MediaType.APPLICATION_JSON)
public class CMesProductionControllerl {

    @Autowired
    DeviceService deviceService;
    @Autowired
    CMesProductionTService productionService;
    @Autowired
    CMesProductionService cMesProductionService;

    // 产品列表
    @RequestMapping(value = "/finAll", method = RequestMethod.POST)
    @ApiOperation(value = "产品列表", notes = "产品列表")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int")})
    public JSONObject finAll(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize) throws ServicesException {
        PageHelper.startPage(pageNum, pageSize);
        List<CMesProductionT> lineList = productionService.findAllProductionL();
        PageInfo<CMesProductionT> pageInfo = new PageInfo<>(lineList);
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

    // 新增产品
    @ResponseBody
    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    @ApiOperation(value = "添加产品 ", notes = "添加数据")
    @OptionalLog(module = "生产", module2 = "产品管理", method = "添加产品")
    public JSONObject addAll(HttpServletRequest request) throws IOException {
        JSONObject json = new JSONObject();
        try {
            String productionName = EqualsUtil.string(request, "productionName", "产品名称", true);
            String productionType = EqualsUtil.string(request, "productionType", "产品型号", true);
            String productionSn = EqualsUtil.string(request, "productionSn", "产品条码", true);
            String materialCode = EqualsUtil.string(request, "materialCode", "物料编码", true);
            String productionTrademark = EqualsUtil.string(request, "productionTrademark", "产品商标", false);
            String productionVr = EqualsUtil.string(request, "productionVr", "产品检验规则", false);
            String productionSte = EqualsUtil.string(request, "productionSte", "产品状态", false);
            String productionDiscription = EqualsUtil.string(request, "productionDiscription", "产品详情", false);
            String path = EqualsUtil.string(request, "path", "产品图片", false);

            CMesProductionT cMesProductionT = new CMesProductionT();
            cMesProductionT.setProductionName(productionName);
            cMesProductionT.setProductionType(productionType);
            cMesProductionT.setProductionSn(productionSn);
            cMesProductionT.setMaterialCode(materialCode);
            cMesProductionT.setProductionTrademark(productionTrademark);
            cMesProductionT.setProductionVr(productionVr);
            cMesProductionT.setProductionSte(productionSte);
            cMesProductionT.setProductionDiscription(productionDiscription);
            cMesProductionT.setPath(path);
            productionService.addProductionL(cMesProductionT);
            json.put("code", 0);
            json.put("msg", "添加成功");
        } catch (ServicesException e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", 1);
            json.put("msg", "未知错误");
        }
        return json;
    }

    // 删除产品
    @RequestMapping(value = "/deletePro", method = RequestMethod.POST)
    @ApiOperation(value = "删除产品", notes = "删除产品")
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "产品id", required = true, paramType = "query"),})
    @OptionalLog(module = "生产", module2 = "产品管理", method = "删除产品")
    public JSONObject deletePro(HttpServletRequest request, Integer id) {
        JSONObject json = new JSONObject();
        System.err.println("id===" + id);
        try {
            HttpSession session = request.getSession();
            String proCode = request.getParameter("proCode");
            // 获取产线外部ID
            if (StringUtil.eqNu(proCode)) {
                List<CMesProductionT> productionByNameAndId = productionService.findProductionByNameAndId(proCode, null);
                if (productionByNameAndId.size() > 0) {
                    id = productionByNameAndId.get(0).getId();
                    session.setAttribute("proCode", proCode);
                } else {
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }
            } else {
                List<CMesProductionT> productionByNameAndId = productionService.findProductionByNameAndId(null, id);
                if(productionByNameAndId.size()>0){
                    session.setAttribute("proCode", productionByNameAndId.get(0).getProductionName());
                }else {
                    json.put("code", 1);
                    json.put("msg", "产线不存在");
                    return json;
                }

            }
            productionService.delProductionL(id);
            json.put("code", 0);
            json.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            // TODO: handle exception
            json.put("code", 1);
            json.put("msg", "未知错误");
        }
        return json;
    }

    @RequestMapping(value = "findProductById", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据ID或型号查询产品", notes = "根据ID或型号查询产品")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "productType", value = "productType", required = true, paramType = "query"),
    })
    public JSONObject findProductById(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            CMesProductionT cMesProductionT;
            Integer id = EqualsUtil.integer(request, "id", "id", false);
            String productionType = EqualsUtil.string(request, "productionType", "productionType", false);
            cMesProductionT = productionService.findProductionByidL(id, productionType);
            json.put("cMesProductionT", cMesProductionT);
            json.put("code", 0);
        } catch (Exception e) {
            json.put("code", 1);
            json.put("msg", "未知错误");
        }
        return json;
    }

    // 修改产品
    @ResponseBody
    @RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
    @ApiOperation(value = "修改产品 ", notes = "修改数据")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "productionName", value = "产品名称", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "productionType", value = "产品类型", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "productionTrademark", value = "产品商标", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "productionSeries", value = "序列号", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "productionVr", value = "产品规则", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "productionDiscription", value = "产品描述", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "productionSte", value = "在线/离线（0/1）", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "path", value = "图片", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query"),
//
//})
    @OptionalLog(module = "生产", module2 = "产品管理", method = "修改产品")
    public JSONObject updateProduct(HttpServletRequest request) throws IOException {
        JSONObject json = new JSONObject();
        try {
            Integer id = EqualsUtil.integer(request, "id", "产品id", true);
            String productionName = EqualsUtil.string(request, "productionName", "产品名称", true);
            String productionType = EqualsUtil.string(request, "productionType", "产品型号", true);
            String productionSn = EqualsUtil.string(request, "productionSn", "产品条码", true);
            String materialCode = EqualsUtil.string(request, "materialCode", "物料编码", true);
            String productionTrademark = EqualsUtil.string(request, "productionTrademark", "产品商标", false);
            String productionVr = EqualsUtil.string(request, "productionVr", "产品检验规则", false);
            String productionSte = EqualsUtil.string(request, "productionSte", "产品状态", false);
            String productionDiscription = EqualsUtil.string(request, "productionDiscription", "产品详情", false);
            String path = EqualsUtil.string(request, "path", "产品图片", false);

            CMesProductionT cMesProductionT = new CMesProductionT();
            cMesProductionT.setId(id);
            cMesProductionT.setProductionName(productionName);
            cMesProductionT.setProductionType(productionType);
            cMesProductionT.setProductionSn(productionSn);
            cMesProductionT.setMaterialCode(materialCode);

            cMesProductionT.setProductionTrademark(productionTrademark == null ? "" : productionTrademark);
            cMesProductionT.setProductionVr(productionVr == null ? "" : productionVr);
            cMesProductionT.setProductionSte(productionSte == null ? "" : productionSte);
            cMesProductionT.setProductionDiscription(productionDiscription == null ? "" : productionDiscription);
            cMesProductionT.setPath(path == null ? "" : path);

            HttpSession session = request.getSession();

            // 产品Code
            String proCode = request.getParameter("productionName");
            if (StringUtil.eqNu(proCode)) {
                CMesProductionT productionT = cMesProductionService.findProIdAndName(null, proCode);
                if (null != productionT) {
                    cMesProductionT.setId(productionT.getId());
                    session.setAttribute("productionCode", proCode);
                } else {
					json.put("code", 1);
					json.put("msg", "产品不存在");
					return json;
                }
            } else {
                CMesProductionT productionT = cMesProductionService.findProIdAndName(cMesProductionT.getId(), null);
                if(null!=productionT){
                    session.setAttribute("productionCode", productionT.getProductionName());
                }else {
                    json.put("code", 1);
                    json.put("msg", "产品不存在");
                    return json;
                }

            }

            productionService.updateProductionL(cMesProductionT);
            json.put("code", 0);
            json.put("msg", "修改成功");
        } catch (ServicesException e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", 1);
            json.put("msg", "未知错误");
        }
        return json;
    }


}

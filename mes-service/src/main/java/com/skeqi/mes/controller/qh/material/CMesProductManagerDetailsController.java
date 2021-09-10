package com.skeqi.mes.controller.qh.material;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.qh.CMesMaterialInstanceTService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 产品详情管理
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "api/productManagerDetails", produces = MediaType.APPLICATION_JSON)
@Api(value = "产品详情管理", description = "产品详情管理", produces = MediaType.APPLICATION_JSON)
public class CMesProductManagerDetailsController {
    @Resource
    private CMesMaterialService service;
    @Autowired
    private CMesBomService bomService;
    @Autowired
    private CMesProductionTService productionTService;
    @ApiOperation(value = "按物料编码查询物料列表", notes = "按物料编码查询物料列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query") })
    @RequestMapping(value = "findProductMaterialList", method = RequestMethod.POST)
    public Rjson findProductMaterialList(HttpServletRequest request){
        try{
            String materialNo = EqualsUtil.string(request, "materialCode", "物料编码", false);
            Integer pageNumber = EqualsUtil.pageNumber(request);

            CMesMaterialInstanceT cMesMaterialInstanceT = new CMesMaterialInstanceT();
            cMesMaterialInstanceT.setMaterialCode(materialNo);

            PageHelper.startPage(pageNumber, 10);
            List<CMesJlMaterialT> allMaterialInstance = service.findProductMaterialList(cMesMaterialInstanceT);
            PageInfo<CMesJlMaterialT> pageInfo = new PageInfo<>(allMaterialInstance, 5);
            return Rjson.success("查询成功",pageInfo);
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按产品型号查询Bom料单列表", notes = "按产品型号查询Bom料单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productType", value = "产品型号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query") })
    @RequestMapping(value = "findBOMMaterialList", method = RequestMethod.POST)
    public Rjson findBOMMaterialList(HttpServletRequest request){
        try{
            String productType = EqualsUtil.string(request, "productType", "产品型号", false);
            Integer pageNumber = EqualsUtil.pageNumber(request);
            Integer pageSize  = 10;
            if(!StringUtils.isEmpty(request.getParameter("pageSize"))){
                pageSize = EqualsUtil.pageSize(request);
            }
            CMesMaterialListT material = new CMesMaterialListT();
            material.setProductType(productType);

            PageHelper.startPage(pageNumber, pageSize);
            List<CMesMaterialListT> allMaterialInstance = bomService.findBOMMaterialList(material);
            PageInfo<CMesMaterialListT> pageInfo = new PageInfo<>(allMaterialInstance, 5);
            return Rjson.success("查询成功",pageInfo);
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按物料编码查询产品列表", notes = "按物料编码查询产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query") })
    @RequestMapping(value = "findProductByMaterialCode", method = RequestMethod.POST)
    public Rjson findProductByMaterialCode(HttpServletRequest request){
        try{
            String materialCode = EqualsUtil.string(request, "materialCode", "物料编码", true);
            Integer pageNumber = EqualsUtil.pageNumber(request);

            CMesProductionT cMesProductionT = new CMesProductionT();
            cMesProductionT.setMaterialCode(materialCode);

            PageHelper.startPage(pageNumber, 10);
            List<CMesProductionT> allMaterialInstance = productionTService.findProductionByCode(cMesProductionT);
            PageInfo<CMesProductionT> pageInfo = new PageInfo<>(allMaterialInstance, 5);
            return Rjson.success("查询成功",pageInfo);
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

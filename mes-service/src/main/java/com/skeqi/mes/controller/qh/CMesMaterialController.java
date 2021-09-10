package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 物料管理
 *
 * @author 73414
 */
@RestController
@RequestMapping(value = "api/material", produces = MediaType.APPLICATION_JSON)
@Api(value = "物料管理", description = "物料管理", produces = MediaType.APPLICATION_JSON)
public class CMesMaterialController {

    @Autowired
    CMesMaterialService service;

    @Resource
    private CodeRuleService codeRuleService;

    @ApiOperation(value = "删除物料", notes = "删除物料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物料id", required = true, paramType = "query")
    })
    @RequestMapping(value = "deleteMaterial", method = RequestMethod.POST)
    @OptionalLog(module = "物料", module2 = "物料管理", method = "删除物料")
    public Rjson deleteMaterial(HttpServletRequest request) {
        try {
//            Integer id = EqualsUtil.integer(request, "id", "物料编码", true);
            Integer id = 0;
            HttpSession session = request.getSession();
            String materialCode = request.getParameter("materialCode");
            // 获取物料外部ID
            if (StringUtil.eqNu(materialCode)) {
                CMesJlMaterialT materialByIdAndMaterialName = service.findMaterialByidAndMaterialName(null, materialCode);
                if(null!=materialByIdAndMaterialName){
                    id = materialByIdAndMaterialName.getId();
                    session.setAttribute("materialCode", materialCode);
                }else {
                    return new Rjson().error("物料不存在");
                }

            } else {
                id = EqualsUtil.integer(request, "id", "物料编码", true);
                CMesJlMaterialT materialByIdAndMaterialName = service.findMaterialByidAndMaterialName(id, null);
                if(null!=materialByIdAndMaterialName){
                    session.setAttribute("materialCode", materialByIdAndMaterialName.getMaterialName());
                }else {
                    return new Rjson().error("物料不存在");
                }

            }
            boolean boo = service.deleteMaterial(id);
            return new Rjson().success(boo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return new Rjson().error(e.getMessage());
        }
    }

    @ApiOperation(value = "更新物料", notes = "更新物料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "bomId", value = "物料编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialName", value = "物料名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "描述", required = false, paramType = "query"),
            @ApiImplicitParam(name = "specifications", value = "规格", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialGroup", value = "物料组", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialType", value = "物料类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tracesType", value = "追溯方式", required = true, paramType = "query"),
            @ApiImplicitParam(name = "stockUnit", value = "库存单位", required = false, paramType = "query"),
            @ApiImplicitParam(name = "inventoryModelGroup", value = "库存模型组", required = false, paramType = "query"),
            @ApiImplicitParam(name = "inventoryDimensionGroup", value = "库存维组", required = false, paramType = "query"),
            @ApiImplicitParam(name = "release", value = "发放方式（y:自动发送，n:工单发送", required = true, paramType = "query"),
            @ApiImplicitParam(name = "inspection", value = "检验等级", required = false, paramType = "query"),
            @ApiImplicitParam(name = "fictitious", value = "虚拟", required = false, paramType = "query"),
            @ApiImplicitParam(name = "salesUnit", value = "销售单位", required = false, paramType = "query"),
            @ApiImplicitParam(name = "secrecy", value = "保密否", required = true, paramType = "query"),
            @ApiImplicitParam(name = "purchasingUnit", value = "采购单位", required = false, paramType = "query"),
            @ApiImplicitParam(name = "productionTeam", value = "生产组", required = false, paramType = "query"),
            @ApiImplicitParam(name = "mininumberOfPackages", value = "最小包装数量", required = false, paramType = "query"),
            @ApiImplicitParam(name = "termOfValidity", value = "有效期", required = false, paramType = "query"),
            @ApiImplicitParam(name = "typenum", value = "型号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "voltage", value = "电压容量", required = false, paramType = "query"),
            @ApiImplicitParam(name = "partCounts", value = "子件数", required = false, paramType = "query"),
            @ApiImplicitParam(name = "cellCapacity", value = "电芯容量", required = false, paramType = "query"),
            @ApiImplicitParam(name = "scan", value = "是否扫描(Y:是,N:否)", required = true, paramType = "query"),
            @ApiImplicitParam(name = "cellSpecification", value = "电芯规格", required = false, paramType = "query"),
            @ApiImplicitParam(name = "customerPartCode", value = "客户零件编码（SD新增字段）", required = false, paramType = "query")
    })
    @RequestMapping(value = "updateMaterial", method = RequestMethod.POST)
    @OptionalLog(module = "物料", module2 = "物料管理", method = "编辑物料")
    public Rjson updateMaterial(HttpServletRequest request) {
        try {

            Integer result = service.updateMaterial(request);
            return new Rjson().success(result <= 0 ? "更新失败" : "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return new Rjson().error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增物料", notes = "新增物料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bomId", value = "物料编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialName", value = "物料名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "描述", required = true, paramType = "query"),
            @ApiImplicitParam(name = "specifications", value = "规格", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialGroup", value = "物料组", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialType", value = "物料类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tracesType", value = "追溯方式", required = true, paramType = "query"),
            @ApiImplicitParam(name = "stockUnit", value = "库存单位", required = true, paramType = "query"),
            @ApiImplicitParam(name = "inventoryModelGroup", value = "库存模型组", required = true, paramType = "query"),
            @ApiImplicitParam(name = "inventoryDimensionGroup", value = "库存维组", required = true, paramType = "query"),
            @ApiImplicitParam(name = "release", value = "发放方式（y:自动发送，n:工单发送", required = true, paramType = "query"),
            @ApiImplicitParam(name = "inspection", value = "检验等级", required = true, paramType = "query"),
            @ApiImplicitParam(name = "fictitious", value = "虚拟", required = true, paramType = "query"),
            @ApiImplicitParam(name = "salesUnit", value = "销售单位", required = true, paramType = "query"),
            @ApiImplicitParam(name = "secrecy", value = "保密否", required = true, paramType = "query"),
            @ApiImplicitParam(name = "purchasingUnit", value = "采购单位", required = true, paramType = "query"),
            @ApiImplicitParam(name = "productionTeam", value = "生产组", required = true, paramType = "query"),
            @ApiImplicitParam(name = "mininumberOfPackages", value = "最小包装数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "termOfValidity", value = "有效期", required = true, paramType = "query"),
            @ApiImplicitParam(name = "typenum", value = "型号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "voltage", value = "电压容量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "partCounts", value = "子件数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "cellCapacity", value = "电芯容量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "scan", value = "是否扫描(Y:是,N:否)", required = true, paramType = "query"),
            @ApiImplicitParam(name = "cellSpecification", value = "电芯规格", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "materialLength", value = "物料长度，单位m", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "materialWidth", value = "物料宽度，单位m", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "materialHight", value = "物料高度，单位m", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "materialVolume", value = "物料体积。单位m3", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "materialWeight", value = "物料重量，单位KG", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "materialLt", value = "存放库位类型，0：立库；1：平库；2：other", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "materialIncomType", value = "来料方式，0：单个；1：批次", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "materialLowLimitmaterial", value = "物料库存下限", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "materialBatch", value = "物料批次数量", required = true, paramType = "query"),
//		@ApiImplicitParam(name = "daysOfFailure", value = "失效天数", required = true, paramType = "query"),
    })
    @RequestMapping(value = "addMaterial", method = RequestMethod.POST)
    @OptionalLog(module = "物料", module2 = "物料管理", method = "新增物料")
    public Rjson addMaterial(HttpServletRequest request) {
        try {
            String bomId = EqualsUtil.string(request, "bomId", "物料编码", true);
            String materialName = EqualsUtil.string(request, "materialName", "物料名称", true);
            String description = EqualsUtil.string(request, "description", "描述", false);
            String specifications = EqualsUtil.string(request, "specifications", "规格", false);
            String materialGroup = EqualsUtil.string(request, "materialGroup", "物料组", false);
            String materialType = EqualsUtil.string(request, "materialType", "物料类型", true);
            Integer tracesType = EqualsUtil.integer(request, "tracesType", "追溯方式", true);
            String stockUnit = EqualsUtil.string(request, "stockUnit", "库存单位", false);
            String inventoryModelGroup = EqualsUtil.string(request, "inventoryModelGroup", "库存模型组", false);
            String inventoryDimensionGroup = EqualsUtil.string(request, "inventoryDimensionGroup", "库存维组", false);
            String release = EqualsUtil.string(request, "release", "发放方式", true);
            String inspection = EqualsUtil.string(request, "inspection", "检验等级", false);
            String fictitious = EqualsUtil.string(request, "fictitious", "虚拟", false);
            String salesUnit = EqualsUtil.string(request, "salesUnit", "销售单位", false);
            String secrecy = EqualsUtil.string(request, "secrecy", "是否保密", false);
            String purchasingUnit = EqualsUtil.string(request, "purchasingUnit", "采购单位", false);
            String productionTeam = EqualsUtil.string(request, "productionTeam", "生产组", false);
            String mininumberOfPackages = EqualsUtil.string(request, "mininumberOfPackages", "最小包装数量", false);
            String termOfValidity = EqualsUtil.string(request, "termOfValidity", "有效期", false);
            String typenum = EqualsUtil.string(request, "typenum", "型号", false);
            String voltage = EqualsUtil.string(request, "voltage", "电压容量", false);
            String partCounts = EqualsUtil.string(request, "partCounts", "子件数", false);
            String cellCapacity = EqualsUtil.string(request, "cellCapacity", "电芯容量", false);
            String scan = EqualsUtil.string(request, "scan", "是否扫描", true);
            String cellSpecification = EqualsUtil.string(request, "cellSpecification", "电芯规格", false);
            String customerPartCode = EqualsUtil.string(request, "customerPartCode", "客户零件编码", false);
//			String materialLength = EqualsUtil.string(request, "materialLength", "物料长度", false);
//			String materialWidth = EqualsUtil.string(request, "materialWidth", "物料宽度", false);
//			String materialHight = EqualsUtil.string(request, "materialHight", "物料高度", false);
//			String materialVolume = EqualsUtil.string(request, "materialVolume", "物料体积", false);
//			String materialWeight = EqualsUtil.string(request, "materialWeight", "物料重量", false);
//			String materialLt = EqualsUtil.string(request, "materialLt", "存放库位类型", true);
//			String materialIncomType = EqualsUtil.string(request, "materialIncomType", "来料方式", true);
//			String materialLowLimitmaterial = EqualsUtil.string(request, "materialLowLimitmaterial", "物料库存下限", false);
//			String materialBatch = EqualsUtil.string(request, "materialBatch", "物料批次数量", false);
//			String daysOfFailure = EqualsUtil.string(request, "daysOfFailure", "失效天数", false);
            //自定义属性所需参数
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));

            CMesJlMaterialT dx = new CMesJlMaterialT();
            dx.setBomId(bomId);
            // API外部ID标识
            HttpSession session = request.getSession();
            session.setAttribute("materialCode", materialName);
            dx.setMaterialName(materialName);
            dx.setDaysOfFailure(description);
            dx.setSpecifications(specifications);
            dx.setMaterialGroup(materialGroup);
            dx.setMaterialType(materialType);
            dx.setTracesType(tracesType);
            dx.setStockUnit(stockUnit);
            dx.setInventoryModelGroup(inventoryModelGroup);
            dx.setInventoryDimensionGroup(inventoryDimensionGroup);
            dx.setRelease(release);
            dx.setInspection(inspection);
            dx.setFictitious(fictitious);
            dx.setSalesUnit(salesUnit);
            dx.setSecrecy(secrecy);
            dx.setPurchasingUnit(purchasingUnit);
            dx.setProductionTeam(productionTeam);
            dx.setMininumberOfPackages(mininumberOfPackages);
            dx.setTermOfValidity(termOfValidity);
            dx.setTypenum(typenum);
            dx.setVoltage(voltage);
            dx.setPartCounts(partCounts);
            dx.setCellCapacity(cellCapacity);
            dx.setScan(scan);
            dx.setCellSpecification(cellSpecification);
            dx.setCustomerPartCode(customerPartCode);
            dx.setCustom(jsonArray);

            Thread.sleep(500);
            Integer result = service.addMaterial(dx);

            return new Rjson().success(result <= 0 ? "新增失败" : "新增成功");
        } catch (Exception e) {
            e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
            return new Rjson().error(e.getMessage());
        }
    }

    @ApiOperation(value = "验证物料名称", notes = "验证物料名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialName", value = "物料名称", required = true, paramType = "query")
    })
    @RequestMapping(value = "checkMaterialName", method = RequestMethod.POST)
    public Rjson checkMaterialName(HttpServletRequest request) {
        try {
            String materialName = EqualsUtil.string(request, "materialName", "物料编码", true);
            boolean boo = service.checkMaterialName(materialName);
            return new Rjson().success(boo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return new Rjson().error(e.getMessage());
        }
    }

    @ApiOperation(value = "验证物料编码", notes = "验证物料编码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialNo", value = "物料编码", required = true, paramType = "query")
    })
    @RequestMapping(value = "checkMaterialNo", method = RequestMethod.POST)
    public Rjson checkMaterialNo(HttpServletRequest request) {
        try {
            String materialNo = EqualsUtil.string(request, "materialNo", "物料编码", true);
            boolean boo = service.checkMaterialNo(materialNo);
            return new Rjson().success(boo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return new Rjson().error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询物料类型", notes = "查询物料类型")
    @ApiImplicitParams({})
    @RequestMapping(value = "findMaterialTypeList", method = RequestMethod.POST)
    public Rjson findMaterialTypeList() {
        try {
            List<CMesMaterialTypeT> list = service.findAllMaterialType(null);
            return new Rjson().success(list);
        } catch (Exception e) {
            return new Rjson().error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询物料列表", notes = "查询物料列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialNo", value = "物料编号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialTypeId", value = "物料类型Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小 ", required = false, paramType = "query")
    })
    @RequestMapping(value = "findMaterialList", method = RequestMethod.POST)
    public Rjson findMaterialList(HttpServletRequest request) {
        try {
            String materialName = EqualsUtil.string(request, "materialName", "物料名称", false);
            String materialNo = EqualsUtil.string(request, "materialNo", "物料编号", false);
            Integer materialTypeId = EqualsUtil.integer(request, "materialTypeId", "物料类型Id", false);
            Integer id = EqualsUtil.integer(request, "id", "物料内部id", false);
            Integer pageSize = EqualsUtil.pageSize(request);
            Integer pageNumber = EqualsUtil.pageNumber(request);

            CMesJlMaterialT dx = new CMesJlMaterialT();
            dx.setId(id);
            dx.setMaterialName(materialName);
            dx.setBomId(materialNo);
            dx.setMaterialType(materialTypeId == null ? null : materialTypeId.toString());

            PageHelper.startPage(pageNumber, pageSize);
            List<CMesJlMaterialT> list = service.findAllMaterial(dx);
            PageInfo<CMesJlMaterialT> pageInfo = new PageInfo<CMesJlMaterialT>(list, 5);

            return Rjson.success("查询成功", pageInfo);

        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询物料编码列表", notes = "查询物料编码列表")
    @RequestMapping(value = "findMaterialCodeList", method = RequestMethod.POST)
    public Rjson findMaterialCodeList(HttpServletRequest request) {
        try {
            List<CMesJlMaterialT> list = service.findMaterialCodeList();
            return Rjson.success("查询成功", list);

        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

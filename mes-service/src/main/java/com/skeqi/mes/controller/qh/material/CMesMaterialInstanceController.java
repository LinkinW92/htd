package com.skeqi.mes.controller.qh.material;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import com.skeqi.mes.service.qh.CMesMaterialInstanceTService;
import com.skeqi.mes.service.qh.PMesTrackingTService;
import com.skeqi.mes.service.qh.RMesTrackingTService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 物料实例管理
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "api/materialInstance", produces = MediaType.APPLICATION_JSON)
@Api(value = "物料实例管理", description = "物料实例管理", produces = MediaType.APPLICATION_JSON)
public class CMesMaterialInstanceController {

    @Resource
    private CMesMaterialInstanceTService service;

    @Resource
    private RMesTrackingTService rMesTrackingTService;

    @ApiOperation(value = "查询物料实例列表", notes = "查询物料实例列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialBatch", value = "物料批次", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialSn", value = "物料序列号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialTypeId", value = "物料类型Id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小 ", required = false, paramType = "query")
    })
    @RequestMapping(value = "findMaterialInstanceList", method = RequestMethod.POST)
    public Rjson findMaterialInstanceList(HttpServletRequest request){
        try{
            String  materialBatch = EqualsUtil.string(request, "materialBatch", "物料批次", false);
            String materialCode = EqualsUtil.string(request, "materialCode", "物料编码", false);
            String  materialSn = EqualsUtil.string(request, "materialSn", "物料序列号", false);
            Integer materialType = EqualsUtil.integer(request, "materialType", "物料类型", false);

            Integer pageNumber = EqualsUtil.pageNumber(request);
            Integer pageSize = EqualsUtil.pageSize(request);

            CMesMaterialInstanceT cMesMaterialInstanceT = new CMesMaterialInstanceT();
            cMesMaterialInstanceT.setMaterialBatch(materialBatch);
            cMesMaterialInstanceT.setMaterialCode(materialCode);
            cMesMaterialInstanceT.setMaterialSn(materialSn);
            cMesMaterialInstanceT.setMaterialType(materialType);

            PageHelper.startPage(pageNumber, pageSize);
            List<CMesMaterialInstanceT> allMaterialInstance = service.findAllMaterialInstance(cMesMaterialInstanceT);
            PageInfo<CMesMaterialInstanceT> pageInfo = new PageInfo<>(allMaterialInstance, 5);
            return Rjson.success("查询成功",pageInfo);
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按id或编码查询物料实例", notes = "按id或编码查询物料实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物料实例id", required = false, paramType = "query"),
            @ApiImplicitParam(name = "sn", value = "物料实例总成号SN", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = false, paramType = "query")})
    @RequestMapping(value = "findMaterialInstanceByCodeOrId", method = RequestMethod.POST)
    public Rjson findMaterialInstanceByCodeOrId(HttpServletRequest request){
        try{
            Integer  id = EqualsUtil.integer(request, "id", "物料实例id", false);
            String  sn = EqualsUtil.string(request, "sn", "物料实例总成号SN", false);
            String materialCode = EqualsUtil.string(request, "materialCode", "物料编码", false);

            CMesMaterialInstanceT cMesMaterialInstanceT = new CMesMaterialInstanceT();
            cMesMaterialInstanceT.setId(id);
            cMesMaterialInstanceT.setMaterialCode(materialCode);
            cMesMaterialInstanceT.setMaterialSn(sn);
            List<CMesMaterialInstanceT> allMaterialInstance = service.findMaterialInstanceByCodeOrId(cMesMaterialInstanceT);
            return Rjson.success("查询成功",allMaterialInstance);
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按工单编号和产品id查询物料实例", notes = "按工单编号和产品id查询物料实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "产品id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "workOrderId", value = "工单编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "当前页码记录数 ", required = false, paramType = "query")
    })
    @RequestMapping(value = "findMaterialByProductIDAndWorkOrderId", method = RequestMethod.POST)
    public Rjson findMaterialByProductIDAndWorkOrderId(HttpServletRequest request){
        try{
            Integer  productId = EqualsUtil.integer(request, "productId", "产品id", true);
            String workOrderId = EqualsUtil.string(request, "workOrderId", "工单编号", true);
            Integer pageNumber = EqualsUtil.pageNumber(request);
            Integer pageSize = EqualsUtil.pageSize(request);
            PageHelper.startPage(pageNumber, pageSize);

            List<CMesMaterialInstanceT> allMaterialInstance = service.findMaterialByProductIDAndWorkOrderId(productId,workOrderId);
            PageInfo<CMesMaterialInstanceT> pageInfo = new PageInfo<>(allMaterialInstance, 5);
            return Rjson.success("查询成功",pageInfo);
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按工单编号和产品id查询相关产品在线明细", notes = "按工单编号和产品id查询相关产品在线明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "产品id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "workOrderId", value = "工单编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "当前页码记录数 ", required = false, paramType = "query")
    })
    @RequestMapping(value = "findOnlineDetailsByProductIDAndWorkOrderId", method = RequestMethod.POST)
    public Rjson findOnlineDetailsByProductIDAndWorkOrderId(HttpServletRequest request){
        try{
            Integer  productId = EqualsUtil.integer(request, "productId", "产品id", true);
            String workOrderId = EqualsUtil.string(request, "workOrderId", "工单编号", true);
            Integer pageNumber = EqualsUtil.pageNumber(request);
            Integer pageSize = EqualsUtil.pageSize(request);
            PageHelper.startPage(pageNumber, pageSize);

            List<Map<String, Object>> allMaterialInstance = rMesTrackingTService.findPMesTrackingByProductIDAndWorkOrderId(productId,workOrderId);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(allMaterialInstance, 5);
            return Rjson.success("查询成功",pageInfo);
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增物料实例", notes = "新增物料实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialBatch", value = "物料批次", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialSn", value = "物料SN", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialType", value = "物料类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "instanceValidity", value = "有效期", required = true, paramType = "query"),
            @ApiImplicitParam(name = "instanceDescription", value = "实例描述", required = true, paramType = "query"),
            @ApiImplicitParam(name = "wearState", value = "耗损状态", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialNumber", value = "批次数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "用户姓名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "locationId", value = "库位Id", required = true, paramType = "query")
    })
    @RequestMapping(value = "addMaterialInstance", method = RequestMethod.POST)
	@OptionalLog(module="物料", module2="物料实例管理", method="新增物料实例")
    public Rjson addMaterialInstance(HttpServletRequest request){
        try {
            String  materialCode = EqualsUtil.string(request, "materialCode", "物料编码", true);
            String  materialBatch = EqualsUtil.string(request, "materialBatch", "物料批次", false);
            String  materialSn = EqualsUtil.string(request, "materialSn", "物料SN", false);
            String  materialType = EqualsUtil.string(request, "materialType", "物料类型", true);
            String  instanceValidity = EqualsUtil.string(request, "instanceValidity", "有效期", true);
            String  instanceDescription = EqualsUtil.string(request, "instanceDescription", "实例描述", false);
            String  wearState = EqualsUtil.string(request, "wearState", "耗损状态", false);
            String  materialNumber = EqualsUtil.string(request, "materialNumber", "批次数量", false);
            String  userName = EqualsUtil.string(request, "userName", "用户姓名", true);
            Integer  locationId = EqualsUtil.integer(request, "locationId", "库位Id", true);
            CMesMaterialInstanceT c = new CMesMaterialInstanceT();
            c.setMaterialCode(materialCode);
            c.setMaterialBatch(materialBatch);
            c.setMaterialSn(materialSn);
            c.setMaterialType(materialType==null?null:Integer.valueOf(materialType));
            c.setInstanceValidity(instanceValidity);
            c.setInstanceDescription(instanceDescription);
            c.setWearState(wearState==null?"0":wearState);
            c.setMaterialNumber(materialNumber==null||materialNumber==""?1:Integer.valueOf(materialNumber));
            c.setDt(new Date());
            c.setAlterDt(new Date());
            c.setLocationId(locationId);

            return service.addMaterialInstance(c,userName);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "修改物料实例", notes = "修改物料实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物料实例id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialBatch", value = "物料批次", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialSn", value = "物料SN", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialType", value = "物料类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "instanceValidity", value = "有效期", required = true, paramType = "query"),
            @ApiImplicitParam(name = "instanceDescription", value = "实例描述", required = true, paramType = "query"),
            @ApiImplicitParam(name = "wearState", value = "耗损状态", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialNumber", value = "批次数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "用户姓名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "locationId", value = "库位Id", required = true, paramType = "query")
    })
    @RequestMapping(value = "updateMaterialInstance", method = RequestMethod.POST)
	@OptionalLog(module="物料", module2="物料实例管理", method="编辑物料实例")
    public Rjson updateMaterialInstance(HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request, "id", "物料实例id", true);
            String  materialCode = EqualsUtil.string(request, "materialCode", "物料编码", true);
            String  materialBatch = EqualsUtil.string(request, "materialBatch", "物料批次", false);
            String  materialSn = EqualsUtil.string(request, "materialSn", "物料SN", false);
            String  instanceValidity = EqualsUtil.string(request, "instanceValidity", "有效期", true);
            String  instanceDescription = EqualsUtil.string(request, "instanceDescription", "实例描述", false);
            String  wearState = EqualsUtil.string(request, "wearState", "耗损状态", false);
            String  materialNumber = EqualsUtil.string(request, "materialNumber", "批次数量", false);
            String  userName = EqualsUtil.string(request, "userName", "用户姓名", true);
            Integer  locationId = EqualsUtil.integer(request, "locationId", "库位Id", true);

            CMesMaterialInstanceT c = new CMesMaterialInstanceT();
            c.setId(id);
            c.setMaterialCode(materialCode);
            c.setMaterialBatch(materialBatch);
            c.setMaterialSn(materialSn);
            c.setInstanceValidity(instanceValidity);
            c.setInstanceDescription(instanceDescription);
            c.setWearState(wearState==null?"0":wearState);
            c.setMaterialNumber(materialNumber==null||materialNumber==""?1:Integer.valueOf(materialNumber));
            c.setAlterDt(new Date());
            c.setLocationId(locationId);

            return service.updateMaterialInstance(c,userName);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除物料实例", notes = "删除物料实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物料实例id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialCode", value = "物料编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialBatch", value = "物料批次", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialSn", value = "物料SN", required = true, paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "用户姓名", required = true, paramType = "query")
    })
    @RequestMapping(value = "deleteMaterialInstance", method = RequestMethod.POST)
	@OptionalLog(module="物料", module2="物料实例管理", method="删除物料实例")
    public Rjson deleteMaterialInstance(HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request, "id", "物料实例id", true);
            String  materialCode = EqualsUtil.string(request, "materialCode", "物料编码", true);
            String  materialBatch = EqualsUtil.string(request, "materialBatch", "物料批次", false);
            String  materialSn = EqualsUtil.string(request, "materialSn", "物料SN", false);
            String  userName = EqualsUtil.string(request, "userName", "用户姓名", true);

            CMesMaterialInstanceT c = new CMesMaterialInstanceT();
            c.setId(id);
            c.setMaterialCode(materialCode);
            c.setMaterialBatch(materialBatch);
            c.setMaterialSn(materialSn);

            return service.deleteMaterialInstance(c,userName);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "冻结物料实例", notes = "冻结物料实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物料实例id", required = true, paramType = "query"),

            @ApiImplicitParam(name = "numberRemaining", value = "剩余数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "frozenNumber", value = "质量冻结数", required = true, paramType = "query")
    })
    @RequestMapping(value = "freezeInventory", method = RequestMethod.POST)
    @OptionalLog(module="物料", module2="物料实例管理", method="删除物料实例")
    public Rjson freezeInventory(HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request, "id", "物料实例id", true);
            Integer  numberRemaining = EqualsUtil.integer(request, "numberRemaining", "剩余数量", true);
            Integer  frozenNumber = EqualsUtil.integer(request, "frozenNumber", "质量冻结数", false);

            CMesMaterialInstanceT c = new CMesMaterialInstanceT();
            c.setId(id);
            c.setNumberRemaining(numberRemaining);
            c.setFrozenNumber(frozenNumber);
            Integer integer = service.freezeInventory(c);
            if (integer<1){
                return Rjson.error("操作失败");
            }
            return Rjson.success();
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


}

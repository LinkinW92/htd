package com.skeqi.mes.controller.wf.linesidelibrary.productOffline;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wf.linesidelibrary.pack.LslPackT;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedT;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineT;
import com.skeqi.mes.service.wf.linesidelibrary.pack.LslPackTService;
import com.skeqi.mes.service.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailTService;
import com.skeqi.mes.service.wf.linesidelibrary.productOffline.LslProductOfflineDetailedTService;
import com.skeqi.mes.service.wf.linesidelibrary.productOffline.LslProductOfflineTService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "productOffline", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库下线管理", description = "线边库下线管理", produces = MediaType.APPLICATION_JSON)
public class ProductOfflineController {

    @Resource
    private LslProductOfflineTService productOfflineTService;

    @Resource
    private LslProductOfflineDetailedTService detailedTService;

    @Resource
    private LslProductOfflineDetailedDetailTService detailedDetailTService;

    @Resource
    private LslPackTService packTService;

    @RequestMapping(value = "findList", method = RequestMethod.POST)
    @ApiOperation(value = "查询下线管理列表", notes = "查询下线管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "number", value = "下线单", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creator", value = "创建者", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="下线管理", method="查询下线管理列表")
    public Rjson findList(HttpServletRequest request) throws ServicesException {
        List<LslProductOfflineT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            Integer status = EqualsUtil.integer(request, "status", "状态", false);
            String number = EqualsUtil.string(request, "number", "下线单", false);
            String creator = EqualsUtil.string(request, "creator", "创建者", false);
            LslProductOfflineT offlineT = new LslProductOfflineT();
            offlineT.setStatus(status);
            offlineT.setNumber(number);
            offlineT.setCreator(creator);
            PageHelper.startPage(pageNum,pageSize);
            list = productOfflineTService.selectAll(offlineT);
            PageInfo<LslProductOfflineT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }


    @RequestMapping(value = "findDetailedList", method = RequestMethod.POST)
    @ApiOperation(value = "查询下线管理详情", notes = "查询下线管理详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "offlineNumber", value = "下线单", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="下线管理", method="查询下线管理详情")
    public Rjson findDetailedList(HttpServletRequest request) throws ServicesException {
        List<LslProductOfflineDetailedT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String offlineNumber = EqualsUtil.string(request, "offlineNumber", "下线单", true);
            LslProductOfflineDetailedT detailedT = new LslProductOfflineDetailedT();
            detailedT.setOfflineNumber(offlineNumber);
            PageHelper.startPage(pageNum,pageSize);
            list = detailedTService.selectAll(detailedT);
            PageInfo<LslProductOfflineDetailedT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "findDetailedDetailList", method = RequestMethod.POST)
    @ApiOperation(value = "查询下线管理详情明细", notes = "查询下线管理详情明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "detailedNumber", value = "详情单号", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="下线管理", method="查询下线管理详情")
    public Rjson findDetailedDetailList(HttpServletRequest request) throws ServicesException {
        List<LslProductOfflineDetailedDetailT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String detailedNumber = EqualsUtil.string(request, "detailedNumber", "详情单号", true);
            LslProductOfflineDetailedDetailT detailedDetailT = new LslProductOfflineDetailedDetailT();
            detailedDetailT.setDetailedNumber(detailedNumber);
            PageHelper.startPage(pageNum,pageSize);
            list = detailedDetailTService.selectAll(detailedDetailT);
            PageInfo<LslProductOfflineDetailedDetailT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "ifPack", method = RequestMethod.POST)
    @ApiOperation(value = "是否包装", notes = "是否包装")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sn", value = "总成号", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="下线管理", method="是否包装")
    public Rjson ifPack(HttpServletRequest request) throws ServicesException {
        try {
            String sn = EqualsUtil.string(request, "sn", "总成号", true);
            LslPackT packT = new LslPackT();
            packT = packTService.selectBySn(sn);
            return Rjson.success(packT);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @RequestMapping(value = "addProductOffline", method = RequestMethod.POST)
    @ApiOperation(value = "新增下线", notes = "新增下线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "下线单号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "下线类型", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "creator", value = "创建者", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "batch", value = "批次号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "offlineLine", value = "下线产线", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "list", value = "明细集合", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="下线管理", method="新增下线")
    public Rjson addProductOffline(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "下线单号", true);
            Integer type = EqualsUtil.integer(request, "type", "下线类型", true);
            String creator = EqualsUtil.string(request, "creator", "创建者", true);
            String batch = EqualsUtil.string(request, "batch", "批次号", true);
            String offlineLine =  EqualsUtil.string(request, "offlineLine", "下线产线", true);
            String remarks = EqualsUtil.string(request, "remarks", "备注", true);
            String list = EqualsUtil.string(request, "list", "明细集合", true);
            List<LslProductOfflineDetailedDetailT> detailTList = JSONArray.parseArray(list).toJavaList(LslProductOfflineDetailedDetailT.class);
            LslProductOfflineT offlineT = new LslProductOfflineT();
            offlineT.setNumber(number);
            offlineT.setType(type);
            offlineT.setStatus(1);
            offlineT.setCreator(creator);
            offlineT.setMender(creator);
            offlineT.setBatch(batch);
            offlineT.setOfflineLine(offlineLine);
            offlineT.setRemarks(remarks);
            offlineT.setCdt(new Date());
            offlineT.setUdt(new Date());
            Integer i = productOfflineTService.insertSelective(offlineT, detailTList);
            if (i<1){
                return Rjson.error("新增失败");
            }
            return Rjson.success("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @RequestMapping(value = "delProductOffline", method = RequestMethod.POST)
    @ApiOperation(value = "删除下线", notes = "删除下线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "下线单号", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="下线管理", method="删除下线")
    public Rjson delProductOffline(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "下线单号", true);
            Integer i = productOfflineTService.deleteByNumber(number);
            if (i<1){
                return Rjson.error("删除失败");
            }
            return Rjson.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

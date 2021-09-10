package com.skeqi.mes.controller.wf.linesidelibrary;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.controller.wf.timer.instantiationTimer.MarginLibraryTimer;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;
import com.skeqi.mes.service.wf.linesidelibrary.RLslMaterialRequestTService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "materialRequest", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库要料请求管理", description = "线边库要料请求管理", produces = MediaType.APPLICATION_JSON)
public class MaterialRequestController {

    @Resource
    private RLslMaterialRequestTService requestTService;

    /**
     *要料轮询定时任务类
     */
    @Resource
    private MarginLibraryTimer libraryTimer;

    @ApiOperation(value = "查询线边库请求记录信息", notes = "查询线边库请求记录信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态", required = true, paramType = "query"),
    })
    @RequestMapping(value = "findMaterialRequest", method = RequestMethod.POST)
    public Rjson findMaterialRequest (HttpServletRequest request){
        try {
            //0 待处理、1 拣货中、2 已出库、3 已确认
            String status = EqualsUtil.string(request,"status","状态",true);
            String[] stringStatus = status.split(",");
            List<Integer> statusList = new ArrayList<>();
            for (int i = 0; i < stringStatus.length; i++) {
                statusList.add(Integer.valueOf(stringStatus[i]));
            }
            List<RLslMaterialRequestT> requestTS = requestTService.findMaterialRequestByStatus(statusList);
            return Rjson.success(requestTS);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按条件查询线边库请求记录信息", notes = "按条件查询线边库请求记录信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query"),
            @ApiImplicitParam(name = "billNo", value = "单据号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "creator", value = "创建者", required = false, paramType = "query"),
            @ApiImplicitParam(name = "picker", value = "拣料人员", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pickTime", value = "拣料时间", required = false, paramType = "query"),
            @ApiImplicitParam(name = "startPickTime", value = "开始拣料时间", required = false, paramType = "query"),
            @ApiImplicitParam(name = "collector", value = "收料人员", required = false, paramType = "query"),
            @ApiImplicitParam(name = "rejecter", value = "拒收人员", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小 ", required = false, paramType = "query")
    })
    @RequestMapping(value = "findMaterialRequestAll", method = RequestMethod.POST)
    public Rjson findMaterialRequestAll (HttpServletRequest request){
        try {

            Integer  status = EqualsUtil.integer(request, "status", "状态", false);
            String billNo = EqualsUtil.string(request, "billNo", "单据号", false);
            String  creator = EqualsUtil.string(request, "creator", "创建者", false);
            String  picker = EqualsUtil.string(request, "picker", "拣料人员", false);
            String  pickTime = EqualsUtil.string(request, "pickTime", "拣料时间", false);
            String  startPickTime = EqualsUtil.string(request, "startPickTime", "开始拣料时间", false);
            String collector = EqualsUtil.string(request, "collector", "收料人员", false);
            String rejecter = EqualsUtil.string(request, "rejecter", "拒收人员", false);

            Integer pageNumber = EqualsUtil.pageNumber(request);
            Integer pageSize = EqualsUtil.pageSize(request);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            RLslMaterialRequestT requestT = new RLslMaterialRequestT();
            requestT.setStatus(status);
            requestT.setBillNo(billNo);
            requestT.setCreator(creator);
            requestT.setPicker(picker);
            requestT.setPickTime(pickTime);
            requestT.setStartPickTime(startPickTime);
            requestT.setCollector(collector);
            requestT.setRejecter(rejecter);
            PageHelper.startPage(pageNumber, pageSize);
            List<RLslMaterialRequestT> requestTS = requestTService.findMaterialRequestAll(requestT);
            PageInfo<RLslMaterialRequestT> pageInfo = new PageInfo<>(requestTS, 5);
            return Rjson.success("查询成功",pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }



    @ApiOperation(value = "线边库物料要料请求确认收货", notes = "线边库物料要料请求确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "billNo", value = "单据号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "collector", value = "收料员", required = true, paramType = "query")
    })
    @RequestMapping(value = "materialRequestConfirmReceipt", method = RequestMethod.POST)
    public Rjson materialRequestConfirmReceipt (HttpServletRequest request){
        try {
            String billNo = EqualsUtil.string(request,"billNo","单据号",true);
            String collector = EqualsUtil.string(request,"collector","收料员",true);
            Map<String, Object> map = new HashMap<>();
            map.put("billNo",billNo);
            map.put("emp",collector);
            Integer integer = requestTService.materialRequestConfirmReceipt(map);
            if (integer>0){
                libraryTimer.udpStop(billNo);
            }
            return Rjson.success("确认收料成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "取消线边库物料要料请求", notes = "取消线边库物料要料请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "billNo", value = "单据号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "collector", value = "取消人员", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "取消描述", required = true, paramType = "query")
    })
    @RequestMapping(value = "updateMaterialRequestCancelTask", method = RequestMethod.POST)
    public Rjson updateMaterialRequestCancelTask (HttpServletRequest request){
        try {
            RLslMaterialRequestT rLslMaterialRequestT = new RLslMaterialRequestT();
            String billNo = EqualsUtil.string(request,"billNo","单据号",true);
            String picker = EqualsUtil.string(request,"picker","取消人员",true);
            String description = EqualsUtil.string(request,"description","取消描述",true);
            rLslMaterialRequestT.setBillNo(billNo);
            rLslMaterialRequestT.setCollector(picker);
            rLslMaterialRequestT.setDescription(description);
            Integer integer = requestTService.updateMaterialRequestCancelTask(rLslMaterialRequestT);
            if (integer>0){
                libraryTimer.udpStop(billNo);
            }
            return Rjson.success("取消任务成功!");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "拒收线边库物料要料请求", notes = "拒收线边库物料要料请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "billNo", value = "单据号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "rejecter", value = "拒收人员", required = true, paramType = "query"),
            @ApiImplicitParam(name = "refuseDescribe", value = "拒收描述", required = true, paramType = "query")
    })
    @RequestMapping(value = "rejectMaterialRequest", method = RequestMethod.POST)
    public Rjson rejectMaterialRequest (HttpServletRequest request){
        try {
            RLslMaterialRequestT rLslMaterialRequestT = new RLslMaterialRequestT();
            String billNo = EqualsUtil.string(request,"billNo","单据号",true);
            String rejecter = EqualsUtil.string(request,"rejecter","拒收人员",true);
            String refuseDescribe = EqualsUtil.string(request,"refuseDescribe","拒收描述",true);
            rLslMaterialRequestT.setBillNo(billNo);
            rLslMaterialRequestT.setRejecter(rejecter);
            rLslMaterialRequestT.setRefuseDescribe(refuseDescribe);
            Integer integer = requestTService.rejectMaterialRequest(rLslMaterialRequestT);
            if (integer>0){
                libraryTimer.udpStop(billNo);
            }
            return Rjson.success("拒收任务成功!");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

}

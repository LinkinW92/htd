package com.skeqi.mes.controller.wf.productionQuality.dataCollection.record;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.params.MesDataCollectionParamsT;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record.MesDataCollectionRecordT;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.params.MesDataCollectionParamsTService;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.record.MesDataCollectionRecordTService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.wf.UniversalUtil;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据收集组参数清单
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "dataCollection/record", produces = MediaType.APPLICATION_JSON)
@Api(value = "数据收集记录", description = "数据收集记录", produces = MediaType.APPLICATION_JSON)
public class DataCollectionRecordController {
    @Resource
    private MesDataCollectionRecordTService recordTService;

    @Resource
    private MesDataCollectionParamsTService paramsTService;


    @RequestMapping(value = "findList", method = RequestMethod.POST)
    @ApiOperation(value = "查询数据收集组参数清单列表", notes = "查询数据收集组参数清单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "number", value = "编号", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="质量", module2="数据收集管理", method="查询数据收集记录")
    public Rjson findList(HttpServletRequest request) throws ServicesException {
        List<MesDataCollectionRecordT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String number = EqualsUtil.string(request, "number", "编号", false);
            MesDataCollectionRecordT paramsT = new MesDataCollectionRecordT();
            paramsT.setNumber(number);
            PageHelper.startPage(pageNum,pageSize);
            list = recordTService.selectAll(paramsT);
            PageInfo<MesDataCollectionRecordT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }


    @RequestMapping(value = "addRecord", method = RequestMethod.POST)
    @ApiOperation(value = "新增数据收集记录", notes = "新增数据收集记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramsData", value = "参数集合", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupNumber", value = "所属组号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "staff", value = "记录人员", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "process", value = "工序", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "equipment", value = "设备", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sn", value = "总成号", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="质量", module2="数据收集管理", method="新增数据收集记录")
    public Rjson addRecord(HttpServletRequest request) throws ServicesException {
        try {
            String paramsData = EqualsUtil.string(request, "paramsData", "参数集合", true);
            String groupNumber = EqualsUtil.string(request, "groupNumber", "所属组号", true);
            String staff = EqualsUtil.string(request, "staff", "记录人员", true);
            String process = EqualsUtil.string(request, "process", "工序", true);
            String equipment = EqualsUtil.string(request, "equipment", "设备", true);
            String sn = EqualsUtil.string(request, "sn", "总成号", false);


            MesDataCollectionParamsT paramsT = new MesDataCollectionParamsT();
            paramsT.setGroupNumber(groupNumber);
            List<MesDataCollectionParamsT> paramsTList = paramsTService.selectAll(paramsT);

            List<MesDataCollectionParamsT> paramsDataList = JSONArray.parseArray(paramsData).toJavaList(MesDataCollectionParamsT.class);
            List<MesDataCollectionParamsT> paramsDataList1 = new ArrayList<>();
            for (MesDataCollectionParamsT params : paramsTList) {
                for (MesDataCollectionParamsT o : paramsDataList) {
                        if (params.getGroupNumber().equals(o.getGroupNumber())&&params.getNumber().equals(o.getNumber())){
                            if (params.getNecessary()==0&&o.getValue()==null){
                                return Rjson.error(params.getName()+"为必选项!!!");
                            }
                            paramsDataList1.add(o);
                        }
                }
            }

            if (paramsDataList1.size()!=paramsTList.size()){
                return Rjson.error("参数数量请核对!!!");
            }

            MesDataCollectionRecordT recordT = new MesDataCollectionRecordT();
            //调用工具类生成编号
            String number = UniversalUtil.generateNumber();
            Boolean flag = true;
            while (flag){
                MesDataCollectionRecordT record = recordTService.selectByPrimaryKey(number);
                if (!StringUtils.isEmpty(record)){
                    number = UniversalUtil.generateNumber();
                    continue;
                }
                flag=false;
            }
            recordT.setNumber(number);
            recordT.setGrouNumber(groupNumber);
            recordT.setStaff(staff);
            recordT.setProcess(process);
            recordT.setEquipment(equipment);
            recordT.setSn(sn);
            recordT.setCdt(new Date());
            recordT.setUdt(new Date());
            Integer  integer = recordTService.insertSelective(recordT,paramsDataList1);
            if (integer<1){
                return Rjson.error("添加失败");
            }
            return Rjson.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @RequestMapping(value = "findRecordList", method = RequestMethod.POST)
    @ApiOperation(value = "查询数据收集组参数清单列表", notes = "查询数据收集组参数清单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "sn", value = "总成", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="质量", module2="数据收集管理", method="查询数据收集记录")
    public Rjson findRecordList(HttpServletRequest request) throws ServicesException {
        List<MesDataCollectionRecordT> list = null;
        try {
            Map<String, Object> map = ToolUtils.getParameterMap(request);
            Integer pageNum = 1;
            Integer pageSize = 10;
            if (map.get("pageNum") != null) {
                pageNum = Integer.parseInt(map.get("pageNum").toString());
            }
            if (map.get("pageSize") != null) {
                pageSize = Integer.parseInt(map.get("pageSize").toString());
            }

            PageHelper.startPage(pageNum,pageSize);
            list = recordTService.findRecordList(map);
            PageInfo<MesDataCollectionRecordT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }
}

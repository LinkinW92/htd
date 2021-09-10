package com.skeqi.mes.controller.wf.productionQuality.ncCodeManager.record;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.record.MesNcCodeRecordT;
import com.skeqi.mes.service.wf.productionQuality.ncCodeManager.record.MesNcCodeRecordTService;
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
import java.util.Date;
import java.util.List;

/**
 * 不合格记录
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "ncCode/record", produces = MediaType.APPLICATION_JSON)
@Api(value = "不合格记录", description = "不合格记录", produces = MediaType.APPLICATION_JSON)
public class NcCodeRecordController {
    @Resource
    private MesNcCodeRecordTService mesNcCodeRecordTService;


    @RequestMapping(value = "findNcCodeRecordList", method = RequestMethod.POST)
    @ApiOperation(value = "查询不合格记录列表", notes = "查询不合格记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "sn", value = "总成号", required = false, paramType = "query", dataType = "Integer")
    })
    @OptionalLog(module="质量", module2="不合格记录", method="查询不合格记录列表")
    public Rjson findNcCodeRecordList(HttpServletRequest request) throws ServicesException {
        List<MesNcCodeRecordT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String sn = EqualsUtil.string(request, "sn", "总成号", false);
            MesNcCodeRecordT mesNcCodeRecordT = new MesNcCodeRecordT();
            mesNcCodeRecordT.setSn(sn);
            PageHelper.startPage(pageNum,pageSize);
            list = mesNcCodeRecordTService.selectAll(mesNcCodeRecordT);
            PageInfo<MesNcCodeRecordT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @RequestMapping(value = "addNcCodeRecord", method = RequestMethod.POST)
    @ApiOperation(value = "新增不合格记录", notes = "新增不合格记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sn", value = "总成号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "ncCode", value = "不合格编码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "staff", value = "记录人员", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, paramType = "query", dataType = "int")
    })
    @OptionalLog(module="质量", module2="不合格记录", method="新增不合格记录")
    public Rjson addNcCodeRecord(HttpServletRequest request) throws ServicesException {
        try {
            String sn = EqualsUtil.string(request, "sn", "总成号", true);
            String ncCode = EqualsUtil.string(request, "ncCode", "不合格编码", true);
            String staff = EqualsUtil.string(request, "staff", "记录人员", true);
            Integer status = EqualsUtil.integer(request, "status", "状态", true);

            //调用工具类生成编号
            String number = UniversalUtil.generateNumber();
            Boolean flag = true;
            while (flag){
                MesNcCodeRecordT record = mesNcCodeRecordTService.selectByPrimaryKey(number);
                if (!StringUtils.isEmpty(record)){
                    number = UniversalUtil.generateNumber();
                    continue;
                }
                flag=false;
            }
            MesNcCodeRecordT mesNcCodeRecordT = new MesNcCodeRecordT();
            mesNcCodeRecordT.setNumber(number);
            mesNcCodeRecordT.setSn(sn);
            mesNcCodeRecordT.setNcCode(ncCode);
            mesNcCodeRecordT.setStaff(staff);
            mesNcCodeRecordT.setStatus(status);
            mesNcCodeRecordT.setCdt(new Date());
            mesNcCodeRecordT.setUdt(new Date());
            return mesNcCodeRecordTService.insertSelective(mesNcCodeRecordT);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "关闭不合格记录", notes = "关闭不合格记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "编号", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "updateByStatus", method = RequestMethod.POST)
    @OptionalLog(module="质量", module2="不合格记录", method="关闭不合格记录")
    public Rjson updateByStatus(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "编号", true);
            MesNcCodeRecordT mesNcCodeRecordT = new MesNcCodeRecordT();
            mesNcCodeRecordT.setNumber(number);
            mesNcCodeRecordT.setStatus(1);
            return mesNcCodeRecordTService.updateByStatus(mesNcCodeRecordT);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "删除不合格记录", notes = "删除不合格记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "编号", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "delNcCodeRecordById", method = RequestMethod.POST)
    @OptionalLog(module="质量", module2="不合格记录", method="删除不合格记录")
    public Rjson delNcCodeRecordById(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "编号", true);
            return mesNcCodeRecordTService.deleteByPrimaryKey(number);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

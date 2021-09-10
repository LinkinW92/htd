package com.skeqi.mes.controller.wf.linesidelibrary.checkInventory;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wf.baseMode.coderRule.CMesCodeRuleT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryT;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedT;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.wf.linesidelibrary.CLslMaterialInventoryTService;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailTService;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedTService;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryTService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.wf.CheckInventoryExcelUtil;
import com.skeqi.mes.util.wf.baseMode.codeRule.CodeRuleConstant;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "checkInventory", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库盘点管理", description = "线边库盘点管理", produces = MediaType.APPLICATION_JSON)
public class CheckInventoryController {
    @Resource
    private LslCheckInventoryTService checkInventoryTService;

    @Resource
    private LslCheckInventoryDetailedTService detailedTService;

    @Resource
    private LslCheckInventoryDetailedDetailTService detailedDetailTService;

    @Resource
    private CLslMaterialInventoryTService inventoryTService;

    @Resource
    private CodeRuleService codeRuleService;

    @RequestMapping(value = "findCheckInventoryList", method = RequestMethod.POST)
    @ApiOperation(value = "查询盘点管理列表", notes = "查询盘点管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "number", value = "盘点单", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creator", value = "创建者", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="盘点管理", method="查询盘点管理列表")
    public Rjson findCheckInventoryList(HttpServletRequest request) throws ServicesException {
        List<LslCheckInventoryT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            Integer status = EqualsUtil.integer(request, "status", "状态", false);
            String number = EqualsUtil.string(request, "number", "盘点单", false);
            String creator = EqualsUtil.string(request, "creator", "创建者", false);
            LslCheckInventoryT checkInventoryT = new LslCheckInventoryT();
            checkInventoryT.setStatus(status);
            checkInventoryT.setNumber(number);
            checkInventoryT.setCreator(creator);
            PageHelper.startPage(pageNum,pageSize);
            list = checkInventoryTService.selectAll(checkInventoryT);
            PageInfo<LslCheckInventoryT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }


    @RequestMapping(value = "findCheckInventoryDetailedList", method = RequestMethod.POST)
    @ApiOperation(value = "查询盘点管理详情", notes = "查询盘点管理详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "checkNumber", value = "盘点单", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="盘点管理", method="查询盘点管理详情")
    public Rjson findCheckInventoryDetailedList(HttpServletRequest request) throws ServicesException {
        List<LslCheckInventoryDetailedT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String checkNumber = EqualsUtil.string(request, "checkNumber", "盘点单", false);
            LslCheckInventoryDetailedT detailedT = new LslCheckInventoryDetailedT();
            detailedT.setCheckNumber(checkNumber);
            PageHelper.startPage(pageNum,pageSize);
            list = detailedTService.selectAll(detailedT);
            PageInfo<LslCheckInventoryDetailedT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "findCheckInventoryDetailedDetailList", method = RequestMethod.POST)
    @ApiOperation(value = "查询盘点管理详情明细", notes = "查询盘点管理详情明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "detailedNumber", value = "详情行号", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="盘点管理", method="查询盘点管理详情明细")
    public Rjson findCheckInventoryDetailedDetailList(HttpServletRequest request) throws ServicesException {
        List<LslCheckInventoryDetailedDetailT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String detailedNumber = EqualsUtil.string(request, "detailedNumber", "详情行号", false);
            LslCheckInventoryDetailedDetailT detailedDetailT = new LslCheckInventoryDetailedDetailT();
            detailedDetailT.setDetailedNumber(detailedNumber);
            PageHelper.startPage(pageNum,pageSize);
            list = detailedDetailTService.selectAll(detailedDetailT);
            PageInfo<LslCheckInventoryDetailedDetailT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "addCheckInventory", method = RequestMethod.POST)
    @ApiOperation(value = "新增盘点管理", notes = "新增盘点管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creator", value = "操作人员", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "data", value = "数据集合", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="盘点管理", method="新增盘点管理")
    public Rjson addCheckInventory(HttpServletRequest request) throws ServicesException {
        try {
            Integer status = EqualsUtil.integer(request, "status", "状态", true);
            String creator = EqualsUtil.string(request, "creator", "操作人员", true);
            String remarks = EqualsUtil.string(request, "remarks", "备注", true);
            String list = EqualsUtil.string(request, "list", "数据集合", true);
            List<LslCheckInventoryDetailedT> detailedTList = Objects.requireNonNull(JSONArray.parseArray(list)).toJavaList(LslCheckInventoryDetailedT.class);

            // 获取条码规则编号并更新最新编号
            String latestCode = codeRuleService.getLatestCode(CodeRuleConstant.LINE_SIDE_LIBRARY_INVENTORY);
            LslCheckInventoryT checkInventoryT = new LslCheckInventoryT();
            checkInventoryT.setNumber(latestCode);
            checkInventoryT.setCreator(creator);
            checkInventoryT.setStatus(status);
            checkInventoryT.setCdt(new Date());
            checkInventoryT.setUdt(new Date());
            checkInventoryT.setRemarks(remarks);
            Integer i = checkInventoryTService.insertSelective(checkInventoryT,detailedTList);
            if (i<1){
                return Rjson.error("添加失败");
            }
            return Rjson.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "Excel导入", notes = "Excel导入")
    @OptionalLog(module="线边库", module2="盘点管理", method="Excel导入")
    @RequestMapping(value = "importExcel",method = RequestMethod.POST)
    public Rjson importExcel(@RequestParam(value = "file") MultipartFile excelFile, HttpServletRequest request){
        try {
            //判断文件是否存在
            if(null == excelFile){
                throw new FileNotFoundException("文件不存在！");
            }
            //获得文件名
            String fileName = excelFile.getOriginalFilename();
            //获得后缀
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //输入流
            InputStream fis = excelFile.getInputStream();

            //解析到的数据
            List<Map<String,Object>>  data = CheckInventoryExcelUtil.importExcel(fis,suffix,1);
            if (data.size()<1){
                throw new FileNotFoundException("文件内容为空！");
            }
            // 返回数据
            List<LslCheckInventoryDetailedT> checkInventory = this.getCheckInventory(data);
            return Rjson.success(checkInventory);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }


    /**
     * 生成盘点信息集合
     * @param data excel数据
     * @return detailedTList 盘点信息集合
     * @throws Exception
     */
    private List<LslCheckInventoryDetailedT> getCheckInventory(List<Map<String,Object>> data) throws Exception {
        // 临时数据验证
        List<Map<String,Object>> objects = new ArrayList<>();
        objects.addAll(data);
        for (Map<String, Object> map : data) {
            String dataMaterialCode = map.get("materialSn").toString();
            // 移除当前条码
            objects.removeIf(s -> s.get("materialSn").equals(map.get("materialSn")));
            // 遍历临时数据
            for (Map<String, Object> map1 : objects) {
                // excel条码重复
                if (map.get("materialSn").equals(map1.get("materialSn"))){
                    throw new Exception(dataMaterialCode+"条码在excel中重复,请验证后重试!");
                }
            }
        }
        // 查询数据库库存并比较
        List<Map<String,Object>>  libraryApiServiceAll = inventoryTService.findAll(data);
        for (Map<String, Object> stringObjectMap : data) {
            Boolean flag = false;
            String materialSn = stringObjectMap.get("materialSn").toString();
            for (Map<String, Object> map : libraryApiServiceAll) {
                String materialCode = map.get("materialCode").toString();
                if (materialCode.equals(materialSn)){
                    map.put("realQuantity",stringObjectMap.get("realQuantity"));
                    flag = true;
                }
            }
            if (!flag){
                throw new Exception(materialSn+"条码库存中不存在,请验证后重试!");
            }
        }
        // 去重
        List<Map<String,Object>> distinctClass =  libraryApiServiceAll.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<>(
                                        Comparator.comparing(o -> o.get("materialNo") + ";")
                                )
                        ), ArrayList::new
                )
        );
        // 返回集合
        // 详情信息
        List<LslCheckInventoryDetailedT> detailedTList = new ArrayList<>();
        for (Map<String, Object> map : distinctClass) {// 详情明细集合
            List<LslCheckInventoryDetailedDetailT> detailedDetailTList = new ArrayList<>();
            // 详情实体类
            LslCheckInventoryDetailedT detailedT = new LslCheckInventoryDetailedT();
            String materialCode = map.get("materialNo").toString();
            for (Map<String, Object> map1 : libraryApiServiceAll) {
                String materialNo = map1.get("materialNo").toString();
                if (materialCode.equals(materialNo)) {
                    // 详情信息
                    // 物料编号
                    detailedT.setMaterialCode(materialCode);
                    // 实际数量
                    Integer r = detailedT.getRealQuantity() == null ? 0 : detailedT.getRealQuantity();
                    Integer r1 = map1.get("realQuantity") == null ? 0 : Integer.parseInt(map1.get("realQuantity").toString());
                    detailedT.setRealQuantity( r + r1);
                    // 系统数量
                    Integer w = detailedT.getWarehouseQuantity() == null ? 0 : detailedT.getWarehouseQuantity();
                    Integer w1 = map1.get("quantity") == null ? 0 : Integer.parseInt(map1.get("quantity").toString());
                    detailedT.setWarehouseQuantity(w + w1);

                    // 详情明细信息
                    LslCheckInventoryDetailedDetailT detailedDetailT = new LslCheckInventoryDetailedDetailT();
                    // 实际数量
                    detailedDetailT.setRealQuantity(r1);
                    // 系统数量
                    detailedDetailT.setWarehouseQuantity(w1);
                    // 差异数量
                    detailedDetailT.setDiscrepancyQuantity(detailedDetailT.getRealQuantity() - detailedDetailT.getWarehouseQuantity());
                    // 物料总成号
                    detailedDetailT.setMaterialSn(map1.get("materialCode").toString());
                    detailedDetailTList.add(detailedDetailT);
                }
            }
            // 差异数量
            detailedT.setDiscrepancyQuantity(detailedT.getRealQuantity() - detailedT.getWarehouseQuantity());
            // 详情明细集合
            detailedT.setDetailedDetailTList(detailedDetailTList);
            // 详情集合
            detailedTList.add(detailedT);
        }
        return detailedTList;
    }

    @RequestMapping(value = "delCheckInventory", method = RequestMethod.POST)
    @ApiOperation(value = "删除盘点", notes = "删除盘点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "盘点单号", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="盘点管理", method="删除盘点")
    public Rjson delCheckInventory(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "盘点单号", true);
            Integer i = checkInventoryTService.deleteByNumber(number);
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

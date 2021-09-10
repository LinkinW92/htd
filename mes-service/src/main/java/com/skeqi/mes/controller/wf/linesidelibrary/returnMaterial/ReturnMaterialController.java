package com.skeqi.mes.controller.wf.linesidelibrary.returnMaterial;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wf.baseMode.coderRule.CMesCodeRuleT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryT;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedT;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnT;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.wf.linesidelibrary.CLslMaterialInventoryTService;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailTService;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedTService;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryTService;
import com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedDetailTService;
import com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedTService;
import com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.LslMaterialReturnTService;
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
import org.apache.poi.ss.formula.functions.T;
import org.neo4j.cypher.internal.compiler.v2_2.functions.Str;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "returnMaterial", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库退料管理", description = "线边库退料管理", produces = MediaType.APPLICATION_JSON)
public class ReturnMaterialController {
    @Resource
    private LslMaterialReturnTService materialReturnTService;

    @Resource
    private LslMaterialReturnDetailedTService detailedTService;

    @Resource
    private LslMaterialReturnDetailedDetailTService detailedDetailTService;

    @Resource
    private CodeRuleService codeRuleService;

    @Resource
    private CLslMaterialInventoryTService inventoryTService;

    @RequestMapping(value = "findReturnMaterialList", method = RequestMethod.POST)
    @ApiOperation(value = "查询退料管理", notes = "查询退料管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "number", value = "退料单", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creator", value = "创建者", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="退料管理", method="查询退料管理")
    public Rjson findReturnMaterialList(HttpServletRequest request) throws ServicesException {
        List<LslMaterialReturnT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            Integer status = EqualsUtil.integer(request, "status", "状态", false);
            String number = EqualsUtil.string(request, "number", "退料单", false);
            String creator = EqualsUtil.string(request, "creator", "创建者", false);
            LslMaterialReturnT materialReturnT = new LslMaterialReturnT();
            materialReturnT.setStatus(status);
            materialReturnT.setNumber(number);
            materialReturnT.setCreator(creator);
            PageHelper.startPage(pageNum,pageSize);
            list = materialReturnTService.selectAll(materialReturnT);
            PageInfo<LslMaterialReturnT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }


    @RequestMapping(value = "findReturnMaterialDetailedList", method = RequestMethod.POST)
    @ApiOperation(value = "查询退料详情", notes = "查询退料详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "returnNumber", value = "退料单", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="退料管理", method="查询退料详情")
    public Rjson findReturnMaterialDetailedList(HttpServletRequest request) throws ServicesException {
        List<LslMaterialReturnDetailedT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String returnNumber = EqualsUtil.string(request, "returnNumber", "退料单", false);
            LslMaterialReturnDetailedT detailedT = new LslMaterialReturnDetailedT();
            detailedT.setReturnNumber(returnNumber);
            PageHelper.startPage(pageNum,pageSize);
            list = detailedTService.selectAll(detailedT);
            PageInfo<LslMaterialReturnDetailedT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "findReturnMaterialDetailedDetailList", method = RequestMethod.POST)
    @ApiOperation(value = "查询退料详情明细", notes = "查询退料详情明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "detailedNumber", value = "详情行号", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="退料管理", method="查询退料详情明细")
    public Rjson findReturnMaterialDetailedDetailList(HttpServletRequest request) throws ServicesException {
        List<LslMaterialReturnDetailedDetailT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String detailedNumber = EqualsUtil.string(request, "detailedNumber", "详情行号", false);
            LslMaterialReturnDetailedDetailT detailedDetailT = new LslMaterialReturnDetailedDetailT();
            detailedDetailT.setDetailedNumber(detailedNumber);
            PageHelper.startPage(pageNum,pageSize);
            list = detailedDetailTService.selectAll(detailedDetailT);
            PageInfo<LslMaterialReturnDetailedDetailT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "addReturnMaterial", method = RequestMethod.POST)
    @ApiOperation(value = "新增退料申请", notes = "新增退料申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "退料类型", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "creator", value = "操作人员", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "data", value = "数据集合", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="退料管理", method="新增退料申请")
    public Rjson addReturnMaterial(HttpServletRequest request) throws ServicesException {
        try {
            Integer type = EqualsUtil.integer(request, "type", "退料类型", true);
            String creator = EqualsUtil.string(request, "creator", "操作人员", true);
            String remarks = EqualsUtil.string(request, "remarks", "备注", true);
            String list = EqualsUtil.string(request, "list", "数据集合", true);
            List<LslMaterialReturnDetailedT> detailedTList = Objects.requireNonNull(JSONArray.parseArray(list)).toJavaList(LslMaterialReturnDetailedT.class);

            // 获取条码规则编号并更新最新编号
            String latestCode = codeRuleService.getLatestCode(CodeRuleConstant.LINE_SIDE_LIBRARY_RETURN_MATERIAL);
            LslMaterialReturnT materialReturnT = new LslMaterialReturnT();
            materialReturnT.setNumber(latestCode);
            materialReturnT.setType(type);
            materialReturnT.setCreator(creator);
            materialReturnT.setStatus(0);
            materialReturnT.setCdt(new Date());
            materialReturnT.setUdt(new Date());
            materialReturnT.setRemarks(remarks);


            Integer i = materialReturnTService.insertSelective(materialReturnT,detailedTList);
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

    @ApiOperation(value = "TXT导入", notes = "TXT导入")
    @OptionalLog(module="线边库", module2="退料管理", method="TXT导入")
    @RequestMapping(value = "importTxt",method = RequestMethod.POST)
    public Rjson importExcel(@RequestParam(value = "file") MultipartFile excelFile, HttpServletRequest request){
        try {
            //判断文件是否存在
            if(null == excelFile){
                throw new FileNotFoundException("文件不存在！");
            }
            InputStreamReader read = new InputStreamReader(excelFile.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(read);
            List<Map<String,Object>> snList = new ArrayList<>();
            String sd = "";
            while((sd = bufferedReader.readLine()) != null){
                Map<String, Object> stringObjectHashMap = new HashMap<>();
                stringObjectHashMap.put("materialSn",sd);
                snList.add(stringObjectHashMap);
            }
            read.close();
            if (snList.size()<1){
                throw new FileNotFoundException("文件内容为空！");
            }
            // 生成退料信息集合
            List<LslMaterialReturnDetailedT> materialReturn = this.getMaterialReturn(snList);
            return Rjson.success(materialReturn);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增退料单", notes = "新增退料单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "list", value = "总成号集合", required = true, paramType = "query", dataType = "String"),
    })
    @OptionalLog(module="线边库", module2="退料管理", method="新增退料单")
    @RequestMapping(value = "addMaterialReturnByList",method = RequestMethod.POST)
    public Rjson addMaterialReturnByList(HttpServletRequest request){
        try {
            String list = EqualsUtil.string(request, "list", "总成号集合", true);
            List<Map<String,Object>> snList = Objects.requireNonNull(JSONArray.parseArray(list)).stream().map(o -> (Map<String, Object>) o).collect(Collectors.toList());
            // 生成退料信息集合
            List<LslMaterialReturnDetailedT> materialReturn = this.getMaterialReturn(snList);
            return Rjson.success(materialReturn);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    /**
     * 生成退料信息集合
     * @param snList sn总成号集合
     * @return detailedTList 退料信息集合
     * @throws Exception
     */
    private List<LslMaterialReturnDetailedT> getMaterialReturn(List<Map<String,Object>> snList) throws Exception {
        // 临时数据验证
        List<Map<String,Object>> objects = new ArrayList<>();
        objects.addAll(snList);
        for (Map<String, Object> map : snList) {
            String dataMaterialCode = map.get("materialSn").toString();
            // 移除当前条码
            objects.removeIf(s -> s.get("materialSn").equals(map.get("materialSn")));
            // 遍历临时数据
            for (Map<String, Object> map1 : objects) {
                // 条码重复
                if (map.get("materialSn").equals(map1.get("materialSn"))){
                    throw new Exception(dataMaterialCode+"条码重复,请验证后重试!");
                }
            }
        }
        // 查询数据库库存并比较
        List<Map<String,Object>>  libraryApiServiceAll = inventoryTService.findAll(snList);
        for (Map<String, Object> stringObjectMap : snList) {
            Boolean flag = false;
            String materialSn = stringObjectMap.get("materialSn").toString();
            for (Map<String, Object> map : libraryApiServiceAll) {
                String materialCode = map.get("materialCode").toString();
                if (materialCode.equals(materialSn)){
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
        List<LslMaterialReturnDetailedT> detailedTList = new ArrayList<>();
        for (Map<String, Object> map : distinctClass) {
            // 详情明细集合
            List<LslMaterialReturnDetailedDetailT> detailedDetailTList = new ArrayList<>();
            // 详情实体类
            LslMaterialReturnDetailedT detailedT = new LslMaterialReturnDetailedT();
            String materialCode = map.get("materialNo").toString();
            for (Map<String, Object> map1 : libraryApiServiceAll) {
                String materialNo = map1.get("materialNo").toString();
                if (materialCode.equals(materialNo)) {
                    // 详情信息
                    // 物料编号
                    detailedT.setMaterialCode(materialCode);
                    // 库存数量
                    Integer oldStockQuantity = detailedT.getStockQuantity() == null ? 0 :detailedT.getStockQuantity();
                    Integer newStockQuantity = map1.get("quantity") == null ? 0 : Integer.parseInt(map1.get("quantity").toString());
                    detailedT.setStockQuantity(oldStockQuantity + newStockQuantity);

                    // 详情明细信息
                    LslMaterialReturnDetailedDetailT detailedDetailT = new LslMaterialReturnDetailedDetailT();
                    // 库存数量
                    detailedDetailT.setStockQuantity(newStockQuantity);
                    // 物料总成号
                    detailedDetailT.setMaterialSn(map1.get("materialCode").toString());

                    detailedDetailTList.add(detailedDetailT);
                }
            }
            // 详情明细集合
            detailedT.setDetailedDetailTList(detailedDetailTList);
            // 详情集合
            detailedTList.add(detailedT);
        }
        return detailedTList;
    }

    @RequestMapping(value = "delReturnMaterial", method = RequestMethod.POST)
    @ApiOperation(value = "删除退料", notes = "删除退料")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "退料单号", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="退料管理", method="删除退料")
    public Rjson delReturnMaterial(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "退料单号", true);
            Integer i = materialReturnTService.deleteByNumber(number);
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

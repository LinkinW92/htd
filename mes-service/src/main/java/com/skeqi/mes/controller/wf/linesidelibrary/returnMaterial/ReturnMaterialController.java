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
@Api(value = "?????????????????????", description = "?????????????????????", produces = MediaType.APPLICATION_JSON)
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
    @ApiOperation(value = "??????????????????", notes = "??????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "??????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "???????????????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "??????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "number", value = "?????????", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creator", value = "?????????", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="??????????????????")
    public Rjson findReturnMaterialList(HttpServletRequest request) throws ServicesException {
        List<LslMaterialReturnT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            Integer status = EqualsUtil.integer(request, "status", "??????", false);
            String number = EqualsUtil.string(request, "number", "?????????", false);
            String creator = EqualsUtil.string(request, "creator", "?????????", false);
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
    @ApiOperation(value = "??????????????????", notes = "??????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "??????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "???????????????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "returnNumber", value = "?????????", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="??????????????????")
    public Rjson findReturnMaterialDetailedList(HttpServletRequest request) throws ServicesException {
        List<LslMaterialReturnDetailedT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String returnNumber = EqualsUtil.string(request, "returnNumber", "?????????", false);
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
    @ApiOperation(value = "????????????????????????", notes = "????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "??????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "???????????????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "detailedNumber", value = "????????????", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="????????????????????????")
    public Rjson findReturnMaterialDetailedDetailList(HttpServletRequest request) throws ServicesException {
        List<LslMaterialReturnDetailedDetailT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String detailedNumber = EqualsUtil.string(request, "detailedNumber", "????????????", false);
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
    @ApiOperation(value = "??????????????????", notes = "??????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "????????????", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "creator", value = "????????????", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "??????", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "data", value = "????????????", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="??????????????????")
    public Rjson addReturnMaterial(HttpServletRequest request) throws ServicesException {
        try {
            Integer type = EqualsUtil.integer(request, "type", "????????????", true);
            String creator = EqualsUtil.string(request, "creator", "????????????", true);
            String remarks = EqualsUtil.string(request, "remarks", "??????", true);
            String list = EqualsUtil.string(request, "list", "????????????", true);
            List<LslMaterialReturnDetailedT> detailedTList = Objects.requireNonNull(JSONArray.parseArray(list)).toJavaList(LslMaterialReturnDetailedT.class);

            // ?????????????????????????????????????????????
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
                return Rjson.error("????????????");
            }
            return Rjson.success("????????????");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "TXT??????", notes = "TXT??????")
    @OptionalLog(module="?????????", module2="????????????", method="TXT??????")
    @RequestMapping(value = "importTxt",method = RequestMethod.POST)
    public Rjson importExcel(@RequestParam(value = "file") MultipartFile excelFile, HttpServletRequest request){
        try {
            //????????????????????????
            if(null == excelFile){
                throw new FileNotFoundException("??????????????????");
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
                throw new FileNotFoundException("?????????????????????");
            }
            // ????????????????????????
            List<LslMaterialReturnDetailedT> materialReturn = this.getMaterialReturn(snList);
            return Rjson.success(materialReturn);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "???????????????", notes = "???????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "list", value = "???????????????", required = true, paramType = "query", dataType = "String"),
    })
    @OptionalLog(module="?????????", module2="????????????", method="???????????????")
    @RequestMapping(value = "addMaterialReturnByList",method = RequestMethod.POST)
    public Rjson addMaterialReturnByList(HttpServletRequest request){
        try {
            String list = EqualsUtil.string(request, "list", "???????????????", true);
            List<Map<String,Object>> snList = Objects.requireNonNull(JSONArray.parseArray(list)).stream().map(o -> (Map<String, Object>) o).collect(Collectors.toList());
            // ????????????????????????
            List<LslMaterialReturnDetailedT> materialReturn = this.getMaterialReturn(snList);
            return Rjson.success(materialReturn);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    /**
     * ????????????????????????
     * @param snList sn???????????????
     * @return detailedTList ??????????????????
     * @throws Exception
     */
    private List<LslMaterialReturnDetailedT> getMaterialReturn(List<Map<String,Object>> snList) throws Exception {
        // ??????????????????
        List<Map<String,Object>> objects = new ArrayList<>();
        objects.addAll(snList);
        for (Map<String, Object> map : snList) {
            String dataMaterialCode = map.get("materialSn").toString();
            // ??????????????????
            objects.removeIf(s -> s.get("materialSn").equals(map.get("materialSn")));
            // ??????????????????
            for (Map<String, Object> map1 : objects) {
                // ????????????
                if (map.get("materialSn").equals(map1.get("materialSn"))){
                    throw new Exception(dataMaterialCode+"????????????,??????????????????!");
                }
            }
        }
        // ??????????????????????????????
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
                throw new Exception(materialSn+"????????????????????????,??????????????????!");
            }
        }

        // ??????
        List<Map<String,Object>> distinctClass =  libraryApiServiceAll.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<>(
                                        Comparator.comparing(o -> o.get("materialNo") + ";")
                                )
                        ), ArrayList::new
                )
        );

        // ????????????
        // ????????????
        List<LslMaterialReturnDetailedT> detailedTList = new ArrayList<>();
        for (Map<String, Object> map : distinctClass) {
            // ??????????????????
            List<LslMaterialReturnDetailedDetailT> detailedDetailTList = new ArrayList<>();
            // ???????????????
            LslMaterialReturnDetailedT detailedT = new LslMaterialReturnDetailedT();
            String materialCode = map.get("materialNo").toString();
            for (Map<String, Object> map1 : libraryApiServiceAll) {
                String materialNo = map1.get("materialNo").toString();
                if (materialCode.equals(materialNo)) {
                    // ????????????
                    // ????????????
                    detailedT.setMaterialCode(materialCode);
                    // ????????????
                    Integer oldStockQuantity = detailedT.getStockQuantity() == null ? 0 :detailedT.getStockQuantity();
                    Integer newStockQuantity = map1.get("quantity") == null ? 0 : Integer.parseInt(map1.get("quantity").toString());
                    detailedT.setStockQuantity(oldStockQuantity + newStockQuantity);

                    // ??????????????????
                    LslMaterialReturnDetailedDetailT detailedDetailT = new LslMaterialReturnDetailedDetailT();
                    // ????????????
                    detailedDetailT.setStockQuantity(newStockQuantity);
                    // ???????????????
                    detailedDetailT.setMaterialSn(map1.get("materialCode").toString());

                    detailedDetailTList.add(detailedDetailT);
                }
            }
            // ??????????????????
            detailedT.setDetailedDetailTList(detailedDetailTList);
            // ????????????
            detailedTList.add(detailedT);
        }
        return detailedTList;
    }

    @RequestMapping(value = "delReturnMaterial", method = RequestMethod.POST)
    @ApiOperation(value = "????????????", notes = "????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "????????????", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="????????????")
    public Rjson delReturnMaterial(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "????????????", true);
            Integer i = materialReturnTService.deleteByNumber(number);
            if (i<1){
                return Rjson.error("????????????");
            }
            return Rjson.success("????????????");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

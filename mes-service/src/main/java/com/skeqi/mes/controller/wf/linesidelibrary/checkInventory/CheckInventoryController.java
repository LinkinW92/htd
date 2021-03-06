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
@Api(value = "?????????????????????", description = "?????????????????????", produces = MediaType.APPLICATION_JSON)
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
    @ApiOperation(value = "????????????????????????", notes = "????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "??????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "???????????????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "??????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "number", value = "?????????", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creator", value = "?????????", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="????????????????????????")
    public Rjson findCheckInventoryList(HttpServletRequest request) throws ServicesException {
        List<LslCheckInventoryT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            Integer status = EqualsUtil.integer(request, "status", "??????", false);
            String number = EqualsUtil.string(request, "number", "?????????", false);
            String creator = EqualsUtil.string(request, "creator", "?????????", false);
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
    @ApiOperation(value = "????????????????????????", notes = "????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "??????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "???????????????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "checkNumber", value = "?????????", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="????????????????????????")
    public Rjson findCheckInventoryDetailedList(HttpServletRequest request) throws ServicesException {
        List<LslCheckInventoryDetailedT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String checkNumber = EqualsUtil.string(request, "checkNumber", "?????????", false);
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
    @ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "??????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "???????????????", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "detailedNumber", value = "????????????", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="??????????????????????????????")
    public Rjson findCheckInventoryDetailedDetailList(HttpServletRequest request) throws ServicesException {
        List<LslCheckInventoryDetailedDetailT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String detailedNumber = EqualsUtil.string(request, "detailedNumber", "????????????", false);
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
    @ApiOperation(value = "??????????????????", notes = "??????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "??????", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "creator", value = "????????????", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "??????", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "data", value = "????????????", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="??????????????????")
    public Rjson addCheckInventory(HttpServletRequest request) throws ServicesException {
        try {
            Integer status = EqualsUtil.integer(request, "status", "??????", true);
            String creator = EqualsUtil.string(request, "creator", "????????????", true);
            String remarks = EqualsUtil.string(request, "remarks", "??????", true);
            String list = EqualsUtil.string(request, "list", "????????????", true);
            List<LslCheckInventoryDetailedT> detailedTList = Objects.requireNonNull(JSONArray.parseArray(list)).toJavaList(LslCheckInventoryDetailedT.class);

            // ?????????????????????????????????????????????
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
                return Rjson.error("????????????");
            }
            return Rjson.success("????????????");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "Excel??????", notes = "Excel??????")
    @OptionalLog(module="?????????", module2="????????????", method="Excel??????")
    @RequestMapping(value = "importExcel",method = RequestMethod.POST)
    public Rjson importExcel(@RequestParam(value = "file") MultipartFile excelFile, HttpServletRequest request){
        try {
            //????????????????????????
            if(null == excelFile){
                throw new FileNotFoundException("??????????????????");
            }
            //???????????????
            String fileName = excelFile.getOriginalFilename();
            //????????????
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //?????????
            InputStream fis = excelFile.getInputStream();

            //??????????????????
            List<Map<String,Object>>  data = CheckInventoryExcelUtil.importExcel(fis,suffix,1);
            if (data.size()<1){
                throw new FileNotFoundException("?????????????????????");
            }
            // ????????????
            List<LslCheckInventoryDetailedT> checkInventory = this.getCheckInventory(data);
            return Rjson.success(checkInventory);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }


    /**
     * ????????????????????????
     * @param data excel??????
     * @return detailedTList ??????????????????
     * @throws Exception
     */
    private List<LslCheckInventoryDetailedT> getCheckInventory(List<Map<String,Object>> data) throws Exception {
        // ??????????????????
        List<Map<String,Object>> objects = new ArrayList<>();
        objects.addAll(data);
        for (Map<String, Object> map : data) {
            String dataMaterialCode = map.get("materialSn").toString();
            // ??????????????????
            objects.removeIf(s -> s.get("materialSn").equals(map.get("materialSn")));
            // ??????????????????
            for (Map<String, Object> map1 : objects) {
                // excel????????????
                if (map.get("materialSn").equals(map1.get("materialSn"))){
                    throw new Exception(dataMaterialCode+"?????????excel?????????,??????????????????!");
                }
            }
        }
        // ??????????????????????????????
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
        List<LslCheckInventoryDetailedT> detailedTList = new ArrayList<>();
        for (Map<String, Object> map : distinctClass) {// ??????????????????
            List<LslCheckInventoryDetailedDetailT> detailedDetailTList = new ArrayList<>();
            // ???????????????
            LslCheckInventoryDetailedT detailedT = new LslCheckInventoryDetailedT();
            String materialCode = map.get("materialNo").toString();
            for (Map<String, Object> map1 : libraryApiServiceAll) {
                String materialNo = map1.get("materialNo").toString();
                if (materialCode.equals(materialNo)) {
                    // ????????????
                    // ????????????
                    detailedT.setMaterialCode(materialCode);
                    // ????????????
                    Integer r = detailedT.getRealQuantity() == null ? 0 : detailedT.getRealQuantity();
                    Integer r1 = map1.get("realQuantity") == null ? 0 : Integer.parseInt(map1.get("realQuantity").toString());
                    detailedT.setRealQuantity( r + r1);
                    // ????????????
                    Integer w = detailedT.getWarehouseQuantity() == null ? 0 : detailedT.getWarehouseQuantity();
                    Integer w1 = map1.get("quantity") == null ? 0 : Integer.parseInt(map1.get("quantity").toString());
                    detailedT.setWarehouseQuantity(w + w1);

                    // ??????????????????
                    LslCheckInventoryDetailedDetailT detailedDetailT = new LslCheckInventoryDetailedDetailT();
                    // ????????????
                    detailedDetailT.setRealQuantity(r1);
                    // ????????????
                    detailedDetailT.setWarehouseQuantity(w1);
                    // ????????????
                    detailedDetailT.setDiscrepancyQuantity(detailedDetailT.getRealQuantity() - detailedDetailT.getWarehouseQuantity());
                    // ???????????????
                    detailedDetailT.setMaterialSn(map1.get("materialCode").toString());
                    detailedDetailTList.add(detailedDetailT);
                }
            }
            // ????????????
            detailedT.setDiscrepancyQuantity(detailedT.getRealQuantity() - detailedT.getWarehouseQuantity());
            // ??????????????????
            detailedT.setDetailedDetailTList(detailedDetailTList);
            // ????????????
            detailedTList.add(detailedT);
        }
        return detailedTList;
    }

    @RequestMapping(value = "delCheckInventory", method = RequestMethod.POST)
    @ApiOperation(value = "????????????", notes = "????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "????????????", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="?????????", module2="????????????", method="????????????")
    public Rjson delCheckInventory(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "????????????", true);
            Integer i = checkInventoryTService.deleteByNumber(number);
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

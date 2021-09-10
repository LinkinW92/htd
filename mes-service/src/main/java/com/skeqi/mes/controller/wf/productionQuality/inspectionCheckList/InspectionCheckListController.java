package com.skeqi.mes.controller.wf.productionQuality.inspectionCheckList;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.wf.productionQuality.inspectionCheckList.CMesInspectionCheckListService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.wf.CheckListExcelImportUtil;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * 清单管理
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "checkList", produces = MediaType.APPLICATION_JSON)
@Api(value = "质检清单管理", description = "质检清单管理", produces = MediaType.APPLICATION_JSON)
public class InspectionCheckListController {

    @Resource
    private CMesInspectionCheckListService cMesInspectionChecklistService;

    @ApiOperation(value = "查询清单", notes = "查询清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "清单名称", required = false, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "清单编号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "produceType", value = "产品类型", required = false, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "清单类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页码大小", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="查询清单")
    @RequestMapping(value = "findCheckList", method = RequestMethod.POST)
    public Rjson findCheckList(HttpServletRequest request){
        try {
            String name = EqualsUtil.string(request, "name", "清单名称", false);
            String code = EqualsUtil.string(request, "code", "清单编号", false);
            String produceType = EqualsUtil.string(request, "produceType", "产品类型", false);
            String type = EqualsUtil.string(request, "type", "清单类型", false);
            Integer pageNum = EqualsUtil.integer(request, "pageNum", "当前页码", false);
            Integer pageSize = EqualsUtil.integer(request, "pageSize", "页码大小", false);
            Map<String, Object> map = new HashMap<>();
            map.put("name",name);
            map.put("code",code);
            map.put("produceType",produceType);
            map.put("type",type);
            PageHelper.startPage(pageNum, pageSize);
            List<Map<String,Object>> list = cMesInspectionChecklistService.findCheckList(map);
            PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list, 5);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }
    @ApiOperation(value = "查询清单详情", notes = "查询清单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkListCode", value = "质检清单ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页码大小", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="查询清单详情")
    @RequestMapping(value = "findCheckListDetail", method = RequestMethod.POST)
    public Rjson findCheckListDetail(HttpServletRequest request){
        try {
            String checkListCode = EqualsUtil.string(request, "checkListCode", "质检清单编号", false);
            Integer pageNum = EqualsUtil.integer(request, "pageNum", "当前页码", false);
            Integer pageSize = EqualsUtil.integer(request, "pageSize", "页码大小", false);
            PageHelper.startPage(pageNum, pageSize);

            List<Map<String,Object>> list = cMesInspectionChecklistService.findCheckListDetail(checkListCode);

            PageInfo<Map<String,Object>> pageInfo= new PageInfo<>(list, 5);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }



    @ApiOperation(value = "查询所有清单详情", notes = "查询所有清单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "checkListCode", value = "质检清单ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页码大小", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="查询清单详情")
    @RequestMapping(value = "findCheckListDetailAll", method = RequestMethod.POST)
    public Rjson findCheckListDetailAll(HttpServletRequest request){
        try {
            String codeArray = request.getParameter("codeArray");
            String[] split = codeArray.split(",");
            List<String> strings = Arrays.asList(split);
            List<Map<String,Object>> list = cMesInspectionChecklistService.findCheckListDetailAll(strings);
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "获取清单下一个编号", notes = "获取清单下一个编号")
    @OptionalLog(module="质量", module2="清单管理", method="获取清单下一个编号")
    @RequestMapping(value = "getNextCheckListCode", method = RequestMethod.POST)
    public Rjson getNextCheckListCode(HttpServletRequest request){
        try {
            Integer id = cMesInspectionChecklistService.getNextCheckListCode();
            Long second = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            String ckCode = "CK"+second.toString()+id.toString();
            return Rjson.success(ckCode);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增清单", notes = "新增清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "NAME", value = "清单名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "CODE", value = "清单编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "PRODUCE_TYPE", value = "产品型号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "TYPE", value = "清单类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "VERSIONS", value = "版本号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "CONTENT", value = "检查内容", required = false, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="新增清单")
    @RequestMapping(value = "addCheckList", method = RequestMethod.POST)
    public Rjson addCheckList(HttpServletRequest request){
        try {
            String NAME = EqualsUtil.string(request, "NAME", "清单名称", true);
            String CODE = EqualsUtil.string(request, "CODE", "清单编号", true);
            String PRODUCE_TYPE = EqualsUtil.string(request, "PRODUCE_TYPE", "产品型号", true);
            String TYPE = EqualsUtil.string(request,"TYPE","清单类型",true);
            String VERSIONS = EqualsUtil.string(request, "VERSIONS", "版本号", true);
            String CONTENT = EqualsUtil.string(request,"CONTENT","检查内容",false);

            //清单信息
            Map<String,Object> map = new HashMap<>();
            map.put("name", NAME);
            map.put("code", CODE);
            map.put("produceType", PRODUCE_TYPE);
            map.put("dt",LocalDateTime.now());
            map.put("type",TYPE);
            //清单明细信息
            map.put("versions", VERSIONS);
            //1是开启,0是关闭
            map.put("start",1);
            //检查内容
            map.put("content", CONTENT);
            Rjson rjson = cMesInspectionChecklistService.addCheckList(map);

            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按产品型号查询清单是否存在", notes = "按产品型号查询清单是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PRODUCE_TYPE", value = "产品型号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "TYPE", value = "清单类型", required = true, paramType = "query"),
    })
    @OptionalLog(module="质量", module2="清单管理", method="按产品型号查询清单是否存在")
    @RequestMapping(value = "findCheckListByProduceType", method = RequestMethod.POST)
    public Rjson findCheckListByProduceType(HttpServletRequest request){
        try {
            String PRODUCE_TYPE = EqualsUtil.string(request, "PRODUCE_TYPE", "产品型号", true);
            String TYPE = EqualsUtil.string(request,"TYPE","清单类型",true);

            //清单信息
            Map<String,Object> map = new HashMap<>();
            map.put("produceType", PRODUCE_TYPE);
            map.put("type",TYPE);
            List<Map<String,Object>> list = cMesInspectionChecklistService.findCheckListByProduceType(map);

            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "按编号和版本查询清单版本", notes = "按编号和版本查询清单版本")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CODE", value = "清单编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "VERSIONS", value = "版本", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="按编号和版本查询清单版本")
    @RequestMapping(value = "selectCheckListDetailByVersions", method = RequestMethod.POST)
    public Rjson selectCheckListDetailByVersions(HttpServletRequest request){
        try {

            String CODE = EqualsUtil.string(request, "CODE", "清单编号", true);
            String VERSIONS = EqualsUtil.string(request, "VERSIONS", "版本", true);
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("code",CODE);
            hashMap.put("versions",VERSIONS);
            List<Map<String, Object>> list = cMesInspectionChecklistService.selectCheckListDetailByVersions(hashMap);
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }





    @ApiOperation(value = "编辑清单", notes = "编辑清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "清单ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "NAME", value = "清单名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "CODE", value = "清单编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "PRODUCE_TYPE", value = "产品型号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "TYPE", value = "清单类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "VERSIONS", value = "版本号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "CONTENT", value = "检查内容", required = false, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="编辑清单")
    @RequestMapping(value = "editCheckList", method = RequestMethod.POST)
    public Rjson editCheckList(HttpServletRequest request){
        try {
            Integer ID = EqualsUtil.integer(request, "ID", "清单ID", true);
            String NAME = EqualsUtil.string(request, "NAME", "清单名称", true);
            String CODE = EqualsUtil.string(request, "CODE", "清单编号", true);
            String PRODUCE_TYPE = EqualsUtil.string(request, "PRODUCE_TYPE", "产品型号", true);
            String TYPE = EqualsUtil.string(request,"TYPE","清单类型",true);
            String VERSIONS = EqualsUtil.string(request, "VERSIONS", "版本号", true);
            String CONTENT = EqualsUtil.string(request,"CONTENT","检查内容",false);
            //清单信息
            Map<String,Object> map = new HashMap<>();
            map.put("id",ID);
            map.put("name", NAME);
            map.put("code", CODE);
            map.put("produceType", PRODUCE_TYPE);
            map.put("dt",LocalDateTime.now());
            map.put("type",TYPE);
            //清单明细信息
            map.put("versions", VERSIONS);
            //1是开启,0是关闭
            map.put("start",1);
            //检查内容
            map.put("content", CONTENT);
            Rjson rjson = cMesInspectionChecklistService.editCheckList(map);

            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除清单", notes = "删除清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CODE", value = "清单编号", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="删除清单")
    @RequestMapping(value = "deleteCheckList", method = RequestMethod.POST)
    public Rjson deleteCheckList(HttpServletRequest request){
        try {
            String CODE = EqualsUtil.string(request, "CODE", "清单编号", true);
            //清单信息
            Map<String,Object> map = new HashMap<>();
            map.put("code", CODE);
            Rjson rjson = cMesInspectionChecklistService.deleteCheckList(map);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增清单详情", notes = "新增清单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CHECK_LIST_CODE", value = "所属清单编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "VERSIONS", value = "版本号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "START", value = "状态", required = true, paramType = "query"),
            @ApiImplicitParam(name = "CONTENT", value = "检查内容", required = false, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="新增清单详情")
    @RequestMapping(value = "addCheckListDetail", method = RequestMethod.POST)
    public Rjson addCheckListDetail(HttpServletRequest request){
        try {
            String CHECK_LIST_CODE = EqualsUtil.string(request, "CHECK_LIST_CODE", "所属清单编号", true);
            String VERSIONS = EqualsUtil.string(request, "VERSIONS", "版本号", true);
            String START = EqualsUtil.string(request, "START", "状态", true);
            String CONTENT = EqualsUtil.string(request,"CONTENT","检查内容",false);
            //清单明细信息
            Map<String,Object> map = new HashMap<>();
            map.put("dt",LocalDateTime.now());
            map.put("versions", VERSIONS);
            //1是开启,0是关闭
            map.put("start",START);
            map.put("code",CHECK_LIST_CODE);
            //检查内容
            map.put("content", CONTENT);
            Rjson rjson = cMesInspectionChecklistService.addCheckListDetail(map);

            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "编辑清单详情", notes = "编辑清单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "FLAG", value = "新增清单版本重复覆盖版本标记", required = false, paramType = "query"),
            @ApiImplicitParam(name = "CHECK_LIST_CODE", value = "所属清单编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "ID", value = "清单详情编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "VERSIONS", value = "版本号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "START", value = "状态", required = true, paramType = "query"),
            @ApiImplicitParam(name = "CONTENT", value = "检查内容", required = false, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="编辑清单详情")
    @RequestMapping(value = "editCheckListDetail", method = RequestMethod.POST)
    public Rjson editCheckListDetail(HttpServletRequest request){
        try {
            //新增清单版本重复覆盖版本标记
            String FLAG = EqualsUtil.string(request, "FLAG", "新增清单版本重复覆盖版本标记", false);
            String CHECK_LIST_CODE = EqualsUtil.string(request, "CHECK_LIST_CODE", "所属清单编号", true);
            String ID = EqualsUtil.string(request, "ID", "清单详情编号", true);
            String VERSIONS = EqualsUtil.string(request, "VERSIONS", "版本号", true);
            String START = EqualsUtil.string(request, "START", "状态", true);
            String CONTENT = EqualsUtil.string(request,"CONTENT","检查内容",false);
            //清单明细信息
            Map<String,Object> map = new HashMap<>();
            //新增清单版本重复覆盖版本标记
            map.put("flag",FLAG);
            map.put("code",CHECK_LIST_CODE);
            map.put("id",ID);
            map.put("versions", VERSIONS);
            //1是开启,0是关闭
            map.put("start",START);
            //检查内容
            map.put("content", CONTENT);
            Rjson rjson = cMesInspectionChecklistService.editCheckListDetail(map);

            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "删除清单详情", notes = "删除清单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "清单详情编号", required = true, paramType = "query")
    })
    @OptionalLog(module="质量", module2="清单管理", method="删除清单详情")
    @RequestMapping(value = "deleteCheckListDetail", method = RequestMethod.POST)
    public Rjson deleteCheckListDetail(HttpServletRequest request){
        try {
            Integer ID = EqualsUtil.integer(request, "ID", "清单详情编号", true);
            //清单信息
            Map<String,Object> map = new HashMap<>();
            map.put("id", ID);
            Rjson rjson = cMesInspectionChecklistService.deleteCheckListDetail(map);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "修改清单详情状态", notes = "修改清单详情状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "清单详情编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "CHECK_LIST_CODE", value = "所属清单编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "VERSIONS", value = "版本号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "START", value = "状态", required = true, paramType = "query"),
    })
    @OptionalLog(module="质量", module2="清单管理", method="修改清单详情状态")
    @RequestMapping(value = "editCheckListDetailByStart", method = RequestMethod.POST)
    public Rjson editCheckListDetailByStart(HttpServletRequest request){
        try {
            Integer ID = EqualsUtil.integer(request, "ID", "清单详情编号", true);
            String CHECK_LIST_CODE = EqualsUtil.string(request, "CHECK_LIST_CODE", "所属清单编号", true);
            String VERSIONS = EqualsUtil.string(request, "VERSIONS", "版本号", true);
            String START = EqualsUtil.string(request, "START", "状态", true);
            //清单信息
            Map<String,Object> map = new HashMap<>();
            map.put("id", ID);
            map.put("code",CHECK_LIST_CODE);
            map.put("versions", VERSIONS);
            map.put("start",START);
            Rjson rjson = cMesInspectionChecklistService.editCheckListDetailByStart(map);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "导入清单信息", notes = "导入清单信息")
    @OptionalLog(module="质量", module2="清单管理", method="导入清单信息")
    @RequestMapping(value = "importChecklistExcel",method = RequestMethod.POST)
    public Rjson importChecklistExcel(@RequestParam(value = "file") MultipartFile excelFile, HttpServletRequest request){
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
            List<Map<String,Object>>  data = CheckListExcelImportUtil.importExcel(fis,suffix,1,"importChecklistExcel");
            if (data.size()<1){
                throw new FileNotFoundException("文件内容为空！");
            }
            List<Map<String,Object>> list1 = new ArrayList<>();
            list1.addAll(data);
            //过滤已存在内容
            list1.forEach(objectMap -> {
                Integer count = cMesInspectionChecklistService.findCheckListByCodeAndType(objectMap);
                if (count>0){
                    //移除当前数据
                    data.removeIf(s -> s.get("CODE").equals(objectMap.get("CODE"))&&s.get("TYPE").equals(objectMap.get("TYPE")));
                }
            });

            if (data.size()<1){
                throw new FileNotFoundException("文件内容已存在！");
            }
            Rjson rjson = cMesInspectionChecklistService.addCheckListBatch(data);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "导入清单详情信息", notes = "导入清单详情信息")
    @OptionalLog(module="质量", module2="清单管理", method="导入清单详情信息")
    @RequestMapping(value = "importChecklistDetailExcel",method = RequestMethod.POST)
    public Rjson importChecklistDetailExcel(@RequestParam(value = "file") MultipartFile excelFile, HttpServletRequest request){
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
            List<Map<String,Object>>  list = CheckListExcelImportUtil.importExcel(fis,suffix,1,"importChecklistDetailExcel");
            if (list.size()<1){
                throw new FileNotFoundException("文件内容为空！");
            }

            List<Map<String,Object>> list1 = new ArrayList<>();
            list1.addAll(list);
            //过滤已存在内容
            for (Map<String, Object> objectMap : list1) {
                Integer count = cMesInspectionChecklistService.findCheckListDetailByCodeAndVersions(objectMap);
                if (count > 0) {
                    //移除当前数据
                    list.removeIf(s -> s.get("VERSIONS").equals(objectMap.get("VERSIONS")) && s.get("CHECK_LIST_CODE").equals(objectMap.get("CHECK_LIST_CODE")));
                }
            }

            if (list.size()<1){
                throw new FileNotFoundException("文件内容已存在！");
            }
            Rjson rjson = cMesInspectionChecklistService.addCheckListDetailBatch(list);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }
}

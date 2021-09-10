package com.skeqi.mes.controller.wf;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wf.CMesMaterialMapping;
import com.skeqi.mes.pojo.wf.CMesMaterialCodeMappingT;
import com.skeqi.mes.service.wf.CMesMaterialMappingService;
import com.skeqi.mes.service.wf.CMesMaterialCodeMappingTService;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.wf.MaterialMappingExcelUtil;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 物料映射管理
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "materialMapping", produces = MediaType.APPLICATION_JSON)
@Api(value = "物料映射管理", description = "物料映射管理", produces = MediaType.APPLICATION_JSON)
public class MaterialMappingController {
    @Resource
    private CMesMaterialCodeMappingTService codeMappingService;

    @Resource
    private CMesMaterialMappingService mappingService;

    @Autowired
    CodeRuleService codeRuleService;

    @ApiOperation(value = "查询物料编号映射", notes = "查询物料编号映射")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierName", value = "供应商名称", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码 ", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小 ", required = false, paramType = "query")
    })
    @RequestMapping(value = "findMaterialCodeMapping", method = RequestMethod.POST)
    public Rjson findMaterialCodeMapping(HttpServletRequest request){
        try{
            String  supplierName = EqualsUtil.string(request, "supplierName", "供应商名称", false);
            Integer pageNum = EqualsUtil.pageNum(request);
            Integer pageSize = EqualsUtil.pageSize(request);

            PageHelper.startPage(pageNum, pageSize);
            List<CMesMaterialCodeMappingT> list = codeMappingService.findMaterialCodeMapping(supplierName);
            PageInfo<CMesMaterialCodeMappingT> pageInfo = new PageInfo<>(list);
            return Rjson.success("查询成功",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
    @ApiOperation(value = "查询物料批次映射", notes = "查询物料批次映射")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierName", value = "供应商名称", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码 ", required = false, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小 ", required = false, paramType = "query")
    })
    @RequestMapping(value = "findMaterialBatchMapping", method = RequestMethod.POST)
    public Rjson findMaterialBatchMapping(HttpServletRequest request){
        try{
            String  supplierName = EqualsUtil.string(request, "supplierName", "供应商名称", false);
            Integer pageNum = EqualsUtil.pageNum(request);
            Integer pageSize = EqualsUtil.pageSize(request);

            PageHelper.startPage(pageNum, pageSize);
            List<CMesMaterialMapping> list = mappingService.findMaterialMapping(supplierName);
            PageInfo<CMesMaterialMapping> pageInfo = new PageInfo<>(list);
            return Rjson.success("查询成功",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "添加批次映射信息", notes = "添加批次映射信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierName", value = "供应商名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "supplierMaterialCode", value = "供应商物料编码 ", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialCode", value = "物料编码 ", required = true, paramType = "query"),
            @ApiImplicitParam(name = "supplierBatch", value = "供应商批次号 ", required = true, paramType = "query"),
            @ApiImplicitParam(name = "batch", value = "批次号 ", required = false, paramType = "query")
    })
    @RequestMapping(value = "addBatchMapping", method = RequestMethod.POST)
    public Rjson addBatchMapping(HttpServletRequest request){
        try{
            String  supplierName = EqualsUtil.string(request, "supplierName", "供应商名称", true);
            String supplierMaterialCode = EqualsUtil.string(request, "supplierMaterialCode", "供应商物料编码", true);
            String materialCode = EqualsUtil.string(request, "materialCode", "物料编码", true);
            String supplierBatch = EqualsUtil.string(request, "supplierBatch", "供应商批次号", true);
            String batch = EqualsUtil.string(request, "batch", "批次号", false);
            CMesMaterialMapping mapping = new CMesMaterialMapping();
            mapping.setSupplierName(supplierName);
            mapping.setSupplierMaterialCode(supplierMaterialCode);
            mapping.setMaterialCode(materialCode);
            mapping.setSupplierBatch(supplierBatch);
            mapping.setBatch(batch);
            return mappingService.addMapping(mapping);
        }catch (Exception e){
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "添加编号映射信息", notes = "添加编号映射信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierName", value = "供应商名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "supplierMaterialCode", value = "供应商物料编码 ", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialCode", value = "物料编码 ", required = true, paramType = "query")
    })
    @RequestMapping(value = "addMaterialCodeMapping", method = RequestMethod.POST)
    public Rjson addMaterialCodeMapping(HttpServletRequest request){
        try{
            String  supplierName = EqualsUtil.string(request, "supplierName", "供应商名称", true);
            String supplierMaterialCode = EqualsUtil.string(request, "supplierMaterialCode", "供应商物料编码", true);
            String materialCode = EqualsUtil.string(request, "materialCode", "物料编码", true);
            CMesMaterialCodeMappingT mapping = new CMesMaterialCodeMappingT();
            mapping.setSupplierName(supplierName);
            mapping.setSupplierMaterialCode(supplierMaterialCode);
            mapping.setMaterialCode(materialCode);
            return codeMappingService.addMaterialCodeMapping(mapping);
        }catch (Exception e){
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "导入批次映射数据", notes = "导入批次映射数据")
    @OptionalLog(module="物料", module2="物料映射管理", method="导入批次映射数据")
    @RequestMapping(value = "importBatchExcel",method = RequestMethod.POST)
    public Rjson importBatchExcel(@RequestParam(value = "file") MultipartFile excelFile, HttpServletRequest request){
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
            List<Map<String, Object>> data = MaterialMappingExcelUtil.importExcel(fis,suffix,1,"batch");
            if (data.size()<1){
                throw new FileNotFoundException("文件内容为空！");
            }

            List<Map<String, Object>> list1 = new ArrayList<>();
            list1.addAll(data);
            //过滤已存在内容
            for (Map<String, Object> objectMap : list1) {
                CMesMaterialMapping mapping = new CMesMaterialMapping();
                if (!StringUtils.isEmpty(objectMap.get("batch"))) {
                    mapping.setBatch(objectMap.get("batch").toString());
                }
                if (!StringUtils.isEmpty(objectMap.get("supplier_batch"))) {
                    mapping.setSupplierBatch(objectMap.get("supplier_batch").toString());
                }
                if (!StringUtils.isEmpty(objectMap.get("supplier_material_code"))) {
                    mapping.setSupplierBatch(objectMap.get("supplier_material_code").toString());
                }
                List<CMesMaterialMapping> count = mappingService.selectMapping(mapping);
                if (count.size() > 0) {
                    boolean batchFlag = true;
                    if (StringUtils.isEmpty(objectMap.get("batch"))){
                        batchFlag = false;
                    }
                    boolean supplierBatchFlag = true;
                    if (StringUtils.isEmpty(objectMap.get("supplier_batch"))){
                        supplierBatchFlag = false;
                    }
                    boolean supplierMaterialCodeFlag = true;
                    if (StringUtils.isEmpty(objectMap.get("supplier_batch"))){
                        supplierMaterialCodeFlag = false;
                    }

                    //移除当前数据
                    boolean finalBatchFlag = batchFlag;
                    boolean finalSupplierBatchFlag = supplierBatchFlag;
                    boolean finalSupplierMaterialCodeFlag = supplierMaterialCodeFlag;
                    data.removeIf(s -> finalSupplierBatchFlag &&s.get("supplier_batch").equals(objectMap.get("supplier_batch")) || finalBatchFlag &&s.get("batch").equals(objectMap.get("batch")) || finalSupplierMaterialCodeFlag &&s.get("supplier_material_code").equals(objectMap.get("supplier_material_code")));
                }
            }

            if (data.size()<1){
                throw new FileNotFoundException("文件内容已存在！");
            }

            Rjson rjson = mappingService.addsMapping(data);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "导入编码映射数据", notes = "导入编码映射数据")
    @OptionalLog(module="物料", module2="物料映射管理", method="导入编码映射数据")
    @RequestMapping(value = "importMaterialCodeExcel",method = RequestMethod.POST)
    public Rjson importMaterialCodeExcel(@RequestParam(value = "file") MultipartFile excelFile, HttpServletRequest request){
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
            List<Map<String,Object>>  data = MaterialMappingExcelUtil.importExcel(fis,suffix,1,"code");
            if (data.size()<1){
                throw new FileNotFoundException("文件内容为空！");
            }
            List<Map<String,Object>> list1 = new ArrayList<>();
            list1.addAll(data);
            //过滤已存在内容
            list1.forEach(objectMap -> {
                CMesMaterialCodeMappingT mapping = new CMesMaterialCodeMappingT();
                if (!StringUtils.isEmpty(objectMap.get("material_code"))){
                    mapping.setMaterialCode(objectMap.get("material_code").toString());
                }
                if (!StringUtils.isEmpty(objectMap.get("supplier_material_code"))){
                    mapping.setSupplierMaterialCode(objectMap.get("supplier_material_code").toString());
                }
                List<CMesMaterialCodeMappingT> count = codeMappingService.selectCodeMapping(mapping);
                if (count.size()>0){
                    //移除当前数据
                    data.removeIf(s -> s.get("supplier_material_code").equals(objectMap.get("supplier_material_code"))&&s.get("material_code").equals(objectMap.get("material_code")));
                }
            });

            if (data.size()<1){
                throw new FileNotFoundException("文件内容已存在！");
            }
            Rjson rjson = codeMappingService.addMaterialCodesMapping(data);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this,e,request);
            return Rjson.error(e.getMessage());
        }
    }

}

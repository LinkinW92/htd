package com.skeqi.mes.controller.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.CLslDictionaryT;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;
import com.skeqi.mes.service.wf.linesidelibrary.CLslDictionaryTService;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "dictionary", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库喇叭配置", description = "线边库喇叭配置", produces = MediaType.APPLICATION_JSON)
public class DictionaryController {
    @Resource
    private CLslDictionaryTService cLslDictionaryTService;

    @ApiOperation(value = "查询线边库喇叭配置", notes = "查询线边库喇叭配置")
    @RequestMapping(value = "findDictionaryList", method = RequestMethod.POST)
    public Rjson findDictionaryList (HttpServletRequest request){
        try {
            List<CLslDictionaryT> requestTS = cLslDictionaryTService.selectAll();
            return Rjson.success(requestTS);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
    @ApiOperation(value = "新增线边库喇叭配置", notes = "新增线边库喇叭配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "属性名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "value", value = "属性内容", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "字段描述", required = true, paramType = "query")
    })
    @RequestMapping(value = "insertDictionary", method = RequestMethod.POST)
    public Rjson insertDictionary (HttpServletRequest request){
        try {
            String key = EqualsUtil.string(request,"key","属性名称",true);
            String value = EqualsUtil.string(request,"value","属性内容",true);
            String describe = EqualsUtil.string(request,"describe","字段描述",true);
            CLslDictionaryT cLslDictionaryT = new CLslDictionaryT();
            cLslDictionaryT.setKey(key);
            cLslDictionaryT.setValue(value);
            cLslDictionaryT.setDescribe(describe);
            Rjson rjson = cLslDictionaryTService.insertDictionary(cLslDictionaryT);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "编辑线边库喇叭配置", notes = "编辑线边库喇叭配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "key", value = "属性名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "value", value = "属性内容", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "字段描述", required = true, paramType = "query")
    })
    @RequestMapping(value = "updateDictionary", method = RequestMethod.POST)
    public Rjson updateDictionary (HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request,"id","编号",true);
            String key = EqualsUtil.string(request,"key","属性名称",true);
            String value = EqualsUtil.string(request,"value","属性内容",true);
            String describe = EqualsUtil.string(request,"describe","字段描述",true);
            CLslDictionaryT cLslDictionaryT = new CLslDictionaryT();
            cLslDictionaryT.setId(id);
            cLslDictionaryT.setKey(key);
            cLslDictionaryT.setValue(value);
            cLslDictionaryT.setDescribe(describe);
            Rjson rjson = cLslDictionaryTService.updateDictionary(cLslDictionaryT);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除线边库喇叭配置", notes = "删除线边库喇叭配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query")
    })
    @RequestMapping(value = "deleteDictionary", method = RequestMethod.POST)
    public Rjson deleteDictionary (HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request,"id","编号",true);
            Rjson rjson = cLslDictionaryTService.deleteDictionaryById(id);
            return rjson;
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

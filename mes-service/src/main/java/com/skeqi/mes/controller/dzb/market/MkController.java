package com.skeqi.mes.controller.dzb.market;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.qh.CQhRoleT;
import com.skeqi.mes.service.dzb.market.MkService;
import com.skeqi.mes.service.dzb.monitor.MonitorService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import org.neo4j.cypher.internal.compiler.v2_2.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/mk")
public class MkController {


    @Resource(name = "MkService")
    private MkService service;

    /**
     * 表信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "listTable", method = RequestMethod.POST)
    public Object listTable(HttpServletRequest request) {
        JSONObject out = new JSONObject();
        try {
            out = service.listTable();
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 获取数据
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "data", method = RequestMethod.POST)
    public Object data(HttpServletRequest request, Integer roleId) {
        JSONObject out = new JSONObject();
        try {
            out = service.data(roleId);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 获取数据
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "deleteData", method = RequestMethod.POST)
    @OptionalLog(module = "营销", module2 = "营销报表", method = "删除属性")
    public Object deleteData(HttpServletRequest request, String pjNo) {
        JSONObject out = new JSONObject();
        try {
            out = service.deleteData(pjNo);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }


    /**
     * 修改表数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateMkData", method = RequestMethod.POST)
    @OptionalLog(module = "营销", module2 = "营销报表", method = "修改表数据")
    public Object updateMkData(
            HttpServletRequest request,
            @RequestParam(value = "tableId", required = true) Integer tableId,
            @RequestParam(value = "colId", required = true) Integer colId,
            @RequestParam(value = "pjNo", required = true) String pjNo,
            @RequestParam(value = "value", required = true) String value
    ) {
        JSONObject out = new JSONObject();
        try {
            out = service.updateMkData(tableId, colId, pjNo, value);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 添加表数据
     *
     * @param request
     * @param tableId 表格id
     * @param colId   列id
     * @param value   值
     * @return
     */
    @RequestMapping(value = "saveMkData", method = RequestMethod.POST)
    @OptionalLog(module = "营销", module2 = "营销报表", method = "添加表数据")
    public Object saveMkData(
            HttpServletRequest request,
            @RequestParam(value = "tableId", required = true) Integer tableId,
            @RequestParam(value = "colId", required = true) Integer colId,
            @RequestParam(value = "value", required = true) String value
    ) {
        JSONObject out = new JSONObject();
        try {
            out = service.saveMkData(tableId, colId, value);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 角色资源信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "authInfo", method = RequestMethod.POST)
    public Object authInfo(HttpServletRequest request) {
        JSONObject out = new JSONObject();
        try {
            System.out.println("test12");
            out = service.authInfo();
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 修改角色资源
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateAuth", method = RequestMethod.POST)
    @OptionalLog(module = "营销", module2 = "营销报表", method = "修改角色资源")
    public Object updateAuth(HttpServletRequest request
            , String roleChangJson) {
        JSONObject out = new JSONObject();
        try {
            List<Map> roleChang = (List<Map>) JSONObject.parse(roleChangJson);
            out = service.updateAuth(roleChang);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 导入数据
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "importData", method = RequestMethod.POST)
    @OptionalLog(module = "营销", module2 = "营销报表", method = "导入数据")
    public Object importData(HttpServletRequest request,
        @RequestBody JSONArray importData) {
        JSONObject out = new JSONObject();
        try {
            List<Map> list = importData.toJavaList(Map.class);
            out = service.importData(list);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }
}

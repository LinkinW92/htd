package com.skeqi.mes.controller.dzb.market;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.controller.dzb.reqobj.TableColParam;
import com.skeqi.mes.service.dzb.market.TableColService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Created by DZB
 * @Date 2021/5/19 14:26
 * @Description TODO
 */
@RestController
@RequestMapping("/mk/tableCol")
public class TableColController {

    @Resource(name = "TableColService")
    private TableColService service;


    /**
     * 查询所有列
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "listTableCol", method = RequestMethod.POST)
    public Object listTableCol(
            HttpServletRequest request,
            @RequestParam(value = "tableId") Integer tableId,
            @RequestParam(value = "colName") String showName
    ) {
        JSONObject out = new JSONObject();
        try {
            out = service.listTableCol(tableId, showName);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 添加自定义属性
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "saveCol", method = RequestMethod.POST)
    @OptionalLog(module = "营销", module2 = "属性管理", method = "添加自定义属性")
    public Object saveCol(
            HttpServletRequest request,
            @RequestBody TableColParam tableColParam
    ) {
        JSONObject out = new JSONObject();
        try {
            out = service.saveCol(tableColParam);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 修改一列
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updateCol", method = RequestMethod.POST)
    @OptionalLog(module = "营销", module2 = "属性管理", method = "修改列名")
    public Object updateCol(
            HttpServletRequest request,
            @RequestBody TableColParam tableColParam
    ) {
        JSONObject out = new JSONObject();
        try {
            out = service.updateCol(tableColParam);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }

    /**
     * 删除一列
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "deleteCol", method = RequestMethod.POST)
    @OptionalLog(module = "营销", module2 = "属性管理", method = "删除列名")
    public Object deleteCol(
            HttpServletRequest request,
            @RequestParam(value = "colId") Integer colId
    ) {
        JSONObject out = new JSONObject();
        try {
            out = service.deleteCol(colId);
            out.put("code", 200);
        } catch (Exception e) {
            out.put("code", 201);
            out.put("msg", e.getMessage());
            ToolUtils.errorLog(this, e, request);
        }
        return out;
    }


}

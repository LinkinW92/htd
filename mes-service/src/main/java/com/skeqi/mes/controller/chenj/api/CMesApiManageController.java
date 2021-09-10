package com.skeqi.mes.controller.chenj.api;

import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.chenj.CMesApiDictionariesService;
import com.skeqi.mes.service.zch.LogService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.ExcelExportUtil;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.*;

/**
 * @author chenj
 * @version 6.15
 * @date 2021/3/20 10:04
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON)
@Api(value = "API管理", description = "API管理", produces = MediaType.APPLICATION_JSON)
public class CMesApiManageController {

    @Autowired
    private CMesApiDictionariesService cMesApiDictionariesService;
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/findApiLogList ", method = RequestMethod.POST)
    @ApiOperation(value = "查询API列表", notes = "查询API列表")
    public Rjson findApiLogList(HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("module", request.getParameter("module"));
            map.put("username", request.getParameter("username"));
            map.put("params", request.getParameter("params"));
            map.put("commitType", request.getParameter("commitType"));
            map.put("paramsType", request.getParameter("paramsType"));
            map.put("beginTime", request.getParameter("beginTime"));
            map.put("endTime", request.getParameter("endTime"));

            // 获取前一个月的今天
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar c = Calendar.getInstance();
            // 目前时间减1个月
            c.add(MONTH, -1);
            map.put("beginTime", df.format(c.getTime()));
            list = cMesApiDictionariesService.findApiOperationList(map);
            List<Map<String, Object>> apiDictionariesList = cMesApiDictionariesService.findApiDictionariesList(null);
            // 保留第一条数据用和存储条件变量
            final int[] num = {0};
            String id = "";
            for (Map<String, Object> item : list) {
                try {
                    // 处理请求路径格式
                    item.put("actionurl", String.valueOf(item.get("actionurl")).replaceAll("/MES_System/", ""));
                    // 将脚本的请求格式存储并返回给前端
                    apiDictionariesList.forEach(itemDict->{
                        if(String.valueOf(item.get("actionurl")).equals(itemDict.get("actionurl"))){
                            item.put("paramsType",itemDict.get("paramsType"));
                        }
                    });
                    DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
                    // 获取数据库中时间
                    Date dtj = dfs.parse(item.get("dt").toString());
                    Calendar cData = getInstance();
                    cData.setTime(dtj);
                    int day = cData.get(DAY_OF_MONTH);
                    // 第一条数据不清除
                    if (num[0] > 0) {
                        // 第一条和第二条比较  相同则清除  不相同则赋值给下一轮继续后续清除操作
                        if (num[0] == day) {
                            item.put("dt", "");
                            item.put("parentId", id);
                        } else {
                            // 将日期格式转换为yyyy-MM-dd 返回给前端
                            item.put("dt", dfs.format(dtj));
                            num[0] = day;
                            id = String.valueOf(item.get("id"));
                        }
                    } else {
                        // 将日期格式转换为yyyy-MM-dd 返回给前端
                        item.put("dt", dfs.format(dtj));
                        num[0] = day;
                        id = String.valueOf(item.get("id"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);



            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }


    @RequestMapping(value = "/findApiDictionariesList ", method = RequestMethod.POST)
    @ApiOperation(value = "查询API字典列表", notes = "查询API字典列表")
    public Rjson findApiDictionariesList(HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("actionUrl", request.getParameter("actionUrl"));
            list = cMesApiDictionariesService.findApiDictionariesList(map);
            PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);

            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }


    @RequestMapping(value = "/exportApiLogList", method = RequestMethod.GET)
    @ApiOperation(value = "导出API列表", notes = "导出API列表")
    @ResponseBody
    public void exportApiLogList(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        List<Map<String, Object>> list = null;
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("module", request.getParameter("module"));
            map.put("username", request.getParameter("username"));
            map.put("params", request.getParameter("params"));
            map.put("commitType", request.getParameter("commitType"));
            map.put("paramsType", request.getParameter("paramsType"));
            map.put("beginTime", request.getParameter("beginTime"));
            map.put("endTime", request.getParameter("endTime"));


            list = cMesApiDictionariesService.findApiOperationList(map);

            //excel标题
            String[] title = {"操作时间", "请求路径", "请求方式", "请求参数", "参数类型"};

            //excel文件名
            String fileName = "Api脚本表" + System.currentTimeMillis() + ".xls";

            //sheet名
            String sheetName = "Api脚本表";

            String[][] content = new String[list.size()][];

            for (int i = 0; i < list.size(); i++) {
                content[i] = new String[title.length];
                Map<String, Object> obj = list.get(i);
                content[i][0] = obj.get("dt").toString();
                content[i][1] = obj.get("actionurl").toString();
                content[i][2] = obj.get("commitType").toString();
                content[i][3] = obj.get("params").toString();
                content[i][4] = obj.get("paramsType").toString();
            }

            //创建HSSFWorkbook
            HSSFWorkbook wb = ExcelExportUtil.getHSSFWorkbook(sheetName, title, content, null);

            //响应到客户端
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");

            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
    }

    @RequestMapping(value = "/delApiOperationData", method = RequestMethod.POST)
    @ApiOperation(value = "删除API操作日志数据", notes = "删除API操作日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "编号数组", required = true, paramType = "query")
    })
    public Rjson delApiOperationData(HttpServletRequest request) {
        try {
            String ids = EqualsUtil.string(request, "ids", "脚本编号", true);
            // 批量删除
            String[] idsItem = ids.split(",");
            Integer[] idsData = (Integer[]) ConvertUtils.convert(idsItem, Integer.class);
            return cMesApiDictionariesService.delApiOperationData(1, idsData)>0?Rjson.success("删除成功"):Rjson.error("未找到此编号");
        } catch (Exception e) {
        e.printStackTrace();
        ToolUtils.errorLog(this, e, request);
        return Rjson.error(e.getMessage());
    }

   }
}

























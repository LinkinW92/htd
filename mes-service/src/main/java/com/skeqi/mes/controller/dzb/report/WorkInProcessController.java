package com.skeqi.mes.controller.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.controller.dzb.reqobj.WorkInProcessParam;
import com.skeqi.mes.service.dzb.report.WorkInProcessService;
import com.skeqi.mes.util.ToolUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/11 23:54
 * @Description TODO
 */
@RestController
@RequestMapping("report/WorkInProcess")
public class WorkInProcessController {

    @Resource(name = "WorkInProcessService")
    private WorkInProcessService service;
//
//    @RequestMapping(value = "data", method = RequestMethod.POST)
//    public Object data(HttpServletRequest request,
//                       @RequestBody JSONObject jsonObject) {
//        JSONObject out = new JSONObject();
//        try {
//            WorkInProcessParam param = jsonObject.toJavaObject(WorkInProcessParam.class);
//            out = service.data(param.getGroup(), param.getWhere(), param.getStart(), param.getEnd(), param.getPageSize(), param.getPageNum());
//            out.put("code", 200);
//        } catch (Exception e) {
//            out.put("code", 201);
//            out.put("msg", e.getMessage());
//            e.printStackTrace();
//            ToolUtils.errorLog(this, e, request);
//        }
//        return out;
//
//    }
}

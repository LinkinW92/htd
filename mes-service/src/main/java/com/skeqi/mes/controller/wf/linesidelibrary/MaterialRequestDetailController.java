package com.skeqi.mes.controller.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.CLslMaterialRequestDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;
import com.skeqi.mes.service.wf.linesidelibrary.CLslMaterialRequestDetailTService;
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
import java.util.List;

/**
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "materialRequestDetail", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库要料请求明细管理", description = "线边库要料请求明细管理", produces = MediaType.APPLICATION_JSON)
public class MaterialRequestDetailController {
    @Resource
    private CLslMaterialRequestDetailTService materialRequestDetailTService;

    @ApiOperation(value = "查询线边库要料请求记录明细信息", notes = "查询线边库要料请求记录明细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "billNo", value = "要料请求单据号", required = true, paramType = "query"),
    })
    @RequestMapping(value = "findMaterialRequestDetail", method = RequestMethod.POST)
    public Rjson findMaterialRequestDetail (HttpServletRequest request){
        try {
            String billNo = EqualsUtil.string(request,"billNo","要料请求单据号",true);
            List<CLslMaterialRequestDetailT> requestDetailTS = materialRequestDetailTService.findMaterialRequestDetailByRequestId(billNo);
            return Rjson.success(requestDetailTS);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}

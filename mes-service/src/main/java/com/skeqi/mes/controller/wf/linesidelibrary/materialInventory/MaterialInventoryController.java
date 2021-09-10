package com.skeqi.mes.controller.wf.linesidelibrary.materialInventory;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wf.linesidelibrary.LslMaterialInventoryT;
import com.skeqi.mes.service.wf.linesidelibrary.CLslMaterialInventoryTService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
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

@RestController
@RequestMapping(value = "materialInventory", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库实时库存管理", description = "线边库实时库存管理", produces = MediaType.APPLICATION_JSON)
public class MaterialInventoryController {

    @Resource
    private CLslMaterialInventoryTService inventoryTService;

    @RequestMapping(value = "findMaterialInventoryBySn", method = RequestMethod.POST)
    @ApiOperation(value = "查询实时库存", notes = "查询实时库存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sn", value = "总成号", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="线边库", module2="实时库存管理", method="查询实时库存")
    public Rjson findCheckInventoryList(HttpServletRequest request) throws ServicesException {
        try {
            String sn = EqualsUtil.string(request, "sn", "总成号", true);
            LslMaterialInventoryT inventoryT = inventoryTService.selectByMaterialCode(sn);
            return Rjson.success(inventoryT);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }
}

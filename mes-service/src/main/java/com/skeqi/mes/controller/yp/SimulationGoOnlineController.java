package com.skeqi.mes.controller.yp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skeqi.mes.service.yp.SimulationGoOnlineService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 产品上线
 * @date 2020-12-24
 */
@RestController
@RequestMapping("/api/SimulationGoOnline")
public class SimulationGoOnlineController {

	@Autowired
	SimulationGoOnlineService service;

	/**
	 * @explain 打印条码
	 * @param request
	 * @return
	 */
	@RequestMapping("/printBarcode")
	public Rjson printBarcode(HttpServletRequest request) {
		try {
			String sn = EqualsUtil.string(request, "sn", "sn", true);
			service.updatePrintFlag(sn);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 产品上线
	 * @param request
	 * @return
	 */
	@RequestMapping("/addRStep")
	public Rjson addRStep(HttpServletRequest request) {
		try {
			String lineName = EqualsUtil.string(request, "lineName", "产线", true);
			String stationName = EqualsUtil.string(request, "stationName", "工位sn", true);
			String sn = EqualsUtil.string(request, "sn", "sn", true);

			service.addRStep(lineName, stationName, sn);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}

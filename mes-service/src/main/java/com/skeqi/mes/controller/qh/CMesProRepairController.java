package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesReturnRepairT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;
import com.skeqi.mes.service.all.CMesProRepairService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/***
 *
 * @author ENS  产品维修
 *
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "产品维修", description = "产品维修", produces = MediaType.APPLICATION_JSON)
public class CMesProRepairController {

	@Autowired
	CMesProRepairService service;



	@RequestMapping(value = "/technology/ProRepair", method = RequestMethod.POST)
	@ApiOperation(value = "产品维修列表", notes = "查询此SN是否可以返工")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "sn", value = "SN", required = false, paramType = "query", dataType = "String"),
	})
	@ResponseBody
	public JSONObject ProRepair(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6")Integer pageSize,String sn)throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		JSONObject json = new JSONObject();
		try {
			String sn2 = sn;
			RTrackingT findBySn = service.findBySn(sn);   //查询此sn是否可以返工
			if(findBySn==null) {
				json.put("code", 1);
				json.put("msg", "此SN不存在或者没有报废");
			}else {
				List<RMesBolt> findBoltBySn = service.findBoltBySn(sn);  //查询螺栓信息
				PageInfo<RMesBolt> pageinfo1 = new PageInfo<RMesBolt>(findBoltBySn,5);
				pageinfo1.setList(findBoltBySn);

				List<RMesKeypart> findKeyPartBySn = service.findKeyPartBySn(sn);   //查询物料信息
				PageInfo<RMesKeypart> pageinfo2 = new PageInfo<RMesKeypart>(findKeyPartBySn,5);
				pageinfo2.setList(findKeyPartBySn);

				List<RMesLeakage> findLeakageBySn = service.findLeakageBySn(sn);  //查询气密信息
				PageInfo<RMesLeakage> pageinfo3 = new PageInfo<RMesLeakage>(findLeakageBySn,5);
				pageinfo3.setList(findLeakageBySn);

				String findProType = service.findProType(findBySn.getProductionId());   //产品型号

				Integer findRouting = service.findRouting(sn, findBySn.getProductionId(), Integer.parseInt(findBySn.getLineId()));  //工艺路线id

				if(findRouting!=0 && findRouting!=null) {   //如果工艺路线存在则查询工艺路线下面的工位
					List<CMesStationT> findStation = service.findStation(findRouting);
					json.put("staList", findStation);
				}else {
					json.put("staList", null);
				}
				json.put("code", 0);
				json.put("msg", "查询成功");
				json.put("boltList",pageinfo1);
				json.put("keypartList", pageinfo2);
				json.put("leakageList",pageinfo3);
				json.put("proType",findProType);   //产品型号
				json.put("station",findBySn.getST());  //当前工位
				json.put("status","NG");  //状态
				json.put("pid",findBySn.getProductionId());  //产品id
				json.put("lineId",findBySn.getLineId());  //产线id
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}


	@RequestMapping(value = "/technology/save", method = RequestMethod.POST)
	@ApiOperation(value = "保存返修路线", notes = "保存返修路线")
	@Transactional(rollbackFor = { Exception.class })
	@ResponseBody
	@OptionalLog(module="生产", module2="维修", method="保存返修路线")
	public JSONObject saveTechno(HttpServletRequest request, @RequestBody JSONObject info) throws ServicesException {
		JSONObject json = new JSONObject();
		Object pid = info.get("pid");  //产品id
		Object reason = info.get("reason"); //原因
		Object sn = info.get("sn");  //sn
		Object lineId = info.get("lineId");  //产线id
		CMesReturnRepairT repair = new CMesReturnRepairT();
		if(String.valueOf(pid)!=null) {
			repair.setProductionId(Integer.parseInt(String.valueOf(pid)));
		}
		if(String.valueOf(reason)!=null) {
			repair.setReason(String.valueOf(reason));
		}
		if(String.valueOf(sn)!=null) {
			repair.setSn(String.valueOf(sn));
		}
		if(String.valueOf(lineId)!=null) {
			repair.setLineId(Integer.parseInt(String.valueOf(lineId)));
		}
		JSONArray array  = info.getJSONArray("list");  //返修路线
		try {
			service.insertReturnRepair(repair);
			for (int i = 0; i < array.size(); i++) {
				ReworkWayT way = new ReworkWayT();
				way.setSn(String.valueOf(sn));
				way.setStName(service.findStationById(Integer.parseInt(String.valueOf(array.getJSONObject(i).get("stId")))));
//				way.setStName(String.valueOf(array.getJSONObject(i).get("stName")));
				way.setStId(Integer.parseInt(String.valueOf(array.getJSONObject(i).get("stId"))));
				way.setSertalNo(Integer.parseInt(String.valueOf(array.getJSONObject(i).get("sertalNo"))));
				service.insertReworkWay(way);
			}
			service.updateRTracking(String.valueOf(sn));
			json.put("code",0);
			json.put("msg","添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code",0);
			json.put("msg","添加失败");
		}
		return json;
	}
}

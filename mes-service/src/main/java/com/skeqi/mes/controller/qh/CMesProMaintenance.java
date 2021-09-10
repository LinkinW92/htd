package com.skeqi.mes.controller.qh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.CMesLeakageInfoT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesDefectReasonT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.RMesTrackingT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesBomServiceImpl;
import com.skeqi.mes.service.lcy.GetReworkRouteService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "产品维修", description = "产品维修", produces = MediaType.APPLICATION_JSON)
public class CMesProMaintenance {

	@Autowired
	CMesBomService cMesBomService;
	@Autowired
	GetReworkRouteService rrs;




	// 物料列表
	@RequestMapping(value = "/findAllMaterialMsg", method = RequestMethod.POST)
	@ApiOperation(value = "物料列表", notes = "物料列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "sn", value = "sn", required = false, paramType = "query", dataType = "string") })
	public JSONObject findAllMaterialMsg(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize,String sn) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		List<PMesKeypartT> keypartList = rrs.queryKeypartList(sn);
		PageInfo<PMesKeypartT> pageInfo = new PageInfo<>(keypartList);
		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 螺栓列表
	@RequestMapping(value = "/findAllBolt", method = RequestMethod.POST)
	@ApiOperation(value = "螺栓列表", notes = "物料列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "sn", value = "sn", required = false, paramType = "query", dataType = "string") })
	public JSONObject findAllBolt(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize,String sn) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		List<PMesBoltT> boltList = rrs.queryBoltList(sn);
		PageInfo<PMesBoltT> pageInfo = new PageInfo<>(boltList);
		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 气密性信息
	@RequestMapping(value = "/findAllLeakage", method = RequestMethod.POST)
	@ApiOperation(value = "气密性信息", notes = "气密性信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "sn", value = "sn", required = false, paramType = "query", dataType = "string") })
	public JSONObject findAllLeakage(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize,String sn) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		List<PMesLeakageT> leakageList = rrs.queryLeakageList(sn);
		PageInfo<PMesLeakageT> pageInfo = new PageInfo<>(leakageList);
		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 产品维修下线
	@RequestMapping(value = "/productRepairDownLine", method = RequestMethod.POST)
	@ApiOperation(value = "产品维修下线", notes = "产品维修下线")
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public JSONObject productRepairDownLine(@ModelAttribute PMesDefectReasonT reasonT) {
		JSONObject json = new JSONObject();
		String sn = reasonT.getSn();
		reasonT.setDefectType("1");
		// 插入数据
		rrs.addDefectReasonl(reasonT);
		try {
			// 获取这个产品再运行表的信息
			RMesTrackingT rt = rrs.getRTracking(sn);
			// 把产品运行表的信息插入到产品下线表中
			rrs.addPTracking(rt.getDt(), rt.getSt(), rt.getBst(), rt.getSn(), "1", rt.getGearboxsn(), rt.getTypename(),
					rt.getTraynum(), rt.getProductnum(), "OK", rt.getDis(), rt.getPlanId(), rt.getReworkFlag(),
					rt.getProductionId(), rt.getLineId());
			// 删除产品运行表的信息
			rrs.deleteRTracking(sn);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "失败");
		}
		return json;
	}

	// 产品报废
	@RequestMapping(value = "/productScrap", method = RequestMethod.POST)
	@ApiOperation(value = "产品报废", notes = "产品报废")
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public JSONObject productScrap(@ModelAttribute PMesDefectReasonT reasonT) {
		JSONObject json = new JSONObject();
		String sn = reasonT.getSn();
		reasonT.setDefectType("2");
		// 插入数据
		rrs.addDefectReasonl(reasonT);
		try {
			// 获取这个产品再运行表的信息
			RMesTrackingT rt = rrs.getRTracking(sn);
			// 把产品运行表的信息插入到产品下线表中
			rrs.addPTracking(rt.getDt(), rt.getSt(), rt.getBst(), rt.getSn(), "2", rt.getGearboxsn(), rt.getTypename(),
					rt.getTraynum(), rt.getProductnum(), "OK", rt.getDis(), rt.getPlanId(), rt.getReworkFlag(),
					rt.getProductionId(), rt.getLineId());
			// 删除产品运行表的信息
			rrs.deleteRTracking(sn);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "失败");
		}
		return json;
	}
	// 返修路径
	@RequestMapping(value = "/productRepair", method = RequestMethod.POST)
	@ApiOperation(value = "返修路径", notes = "返修路径")
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public JSONObject productRepair(@ModelAttribute PMesDefectReasonT reasonT) {
		JSONObject json = new JSONObject();
		String sn = reasonT.getSn();
		String menuIds = reasonT.getMenuIds();
		rrs.removeBackWay(sn);
		String[] getRework = menuIds.split(";");
		for (int i = 0; i < getRework.length; i++) {
			String[] getReworkWay = getRework[i].split(",");
			rrs.addReworkWay(new Date(), sn, getReworkWay[1], Integer.parseInt(getReworkWay[0]), (i + 1));
		}
		try {
			reasonT.setDefectType("3");
			// 插入原因
			rrs.addDefectReasonl(reasonT);
			// 将这个sn的运行表数据改为返工
			rrs.updatRTracking(sn);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "失败");
		}
		return json;
	}
}

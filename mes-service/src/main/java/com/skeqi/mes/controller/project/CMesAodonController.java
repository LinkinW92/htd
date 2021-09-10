package com.skeqi.mes.controller.project;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.project.CMesAndonFault;
import com.skeqi.mes.pojo.project.CMesAndonInfo;
import com.skeqi.mes.pojo.project.CMesLossReasonT;
import com.skeqi.mes.pojo.project.CMesLossTypeT;
import com.skeqi.mes.pojo.project.CMesParetoT;
import com.skeqi.mes.pojo.project.InsertAndonT;
import com.skeqi.mes.pojo.project.InsertInfo;
import com.skeqi.mes.pojo.project.ResponbodtAndonT;
import com.skeqi.mes.pojo.project.UpdateAndon;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.project.CMesAndonService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "安灯管理", description = "安灯管理", produces = MediaType.APPLICATION_JSON)
public class CMesAodonController {

	@Autowired
	private CMesAndonService service;

	@Autowired
	CMesLineTService cMesLineTService;

	/**
	 * 710重写版
	 *
	 * @param andon
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "findStationStatus710", method = RequestMethod.POST)
	@ApiOperation(value = "当前故障列表", notes = "当前故障列表")
	public Rjson findStationStatus710(HttpServletRequest request) throws Exception {
		String lineName =  EqualsUtil.string(request, "lineName", "产线名称", false);
		List<JSONObject> list = service.findStationStatus710(lineName);
		return new Rjson().success(list);
	}

	// 创建故障
	@RequestMapping(value = "InsertFault", method = RequestMethod.POST)
	@ApiOperation(value = "创建故障", notes = "创建故障")
	public JSONObject addFault(HttpServletRequest request, @RequestBody InsertAndonT andon) {
		JSONObject json = new JSONObject();
		try {
			service.addFault(andon.getLineName(), andon.getStationName(), andon.getFaultType());
			json.put("code", 0);
			json.put("msg", "创建成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "创建失败，未知错误");
		}
		return json;
	}

	// 响应故障
	@RequestMapping(value = "responseFault", method = RequestMethod.POST)
	@ApiOperation(value = "响应故障", notes = "响应故障")
	public JSONObject responseFault(@RequestBody ResponbodtAndonT andon) {
		JSONObject json = new JSONObject();
		try {
			Integer updateFault = service.responseFault(andon.getLineName(), andon.getStationName());
			if (updateFault == 0) {
				json.put("code", 1);
				json.put("msg", "响应失败，没有创建此产线和工位的故障");
				return json;
			}
			json.put("code", 0);
			json.put("msg", "响应成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "响应失败，未知错误");
		}
		return json;
	}

	// 解决故障
	@RequestMapping(value = "SolveFault", method = RequestMethod.POST)
	@ApiOperation(value = "解决故障", notes = "解决故障")
	public JSONObject SolveFault(HttpServletRequest request, @RequestBody ResponbodtAndonT andon) {
		JSONObject json = new JSONObject();
		try {
			Integer updateFault = service.SolveFault(andon.getLineName(), andon.getStationName());
			if (updateFault == 0) {
				json.put("code", 1);
				json.put("msg", "解决失败，没有创建此产线和工位的故障");
				return json;
			}
			json.put("code", 0);
			json.put("msg", "解决成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "解决失败，未知错误");
		}
		return json;
	}

	// 当前故障列表
	@RequestMapping(value = "findNowAndon", method = RequestMethod.POST)
	@ApiOperation(value = "当前故障列表", notes = "当前故障列表")
	public JSONObject findAllNowAndon(HttpServletRequest request, @RequestBody ResponbodtAndonT andon) throws Exception {
		JSONObject json = new JSONObject();
		String id = null;
		if (!andon.getLineName().equals("")) {
			id = andon.getLineName();
			CMesLineT line = cMesLineTService.findLineByid(Integer.parseInt(id));
			andon.setLineName(line.getName());
		} else {
			andon.setLineName(null);
		}
		try {
			List<CMesAndonFault> findAllAndon = null;
			if (andon.getPages() == 0 || andon.getPages() == null) {
				findAllAndon = service.findNowAndon(andon.getLineName(), andon.getStationName(), andon.getLossType(),
						andon.getStartDate(), andon.getEndDate());
			} else {
				PageHelper.startPage(andon.getPages(), 10);
				findAllAndon = service.findNowAndon(andon.getLineName(), andon.getStationName(), andon.getLossType(),
						andon.getStartDate(), andon.getEndDate());
				PageInfo<CMesAndonFault> pageInfo = new PageInfo<CMesAndonFault>(findAllAndon, 5);
				findAllAndon = pageInfo.getList();
				json.put("pageNum", pageInfo.getTotal());
			}
			json.put("info", findAllAndon);
			json.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", "1");
		}
		return json;
	}

	// 故障列表
	@RequestMapping(value = "findAllAndon", method = RequestMethod.POST)
	@ApiOperation(value = "历史故障列表", notes = "历史故障列表")
	public JSONObject findAllAndon(HttpServletRequest request, @RequestBody ResponbodtAndonT andon) throws Exception {
		JSONObject json = new JSONObject();
		String id = null;
		if (!andon.getLineName().equals("")) {
			id = andon.getLineName();
			CMesLineT line = cMesLineTService.findLineByid(Integer.parseInt(id));
			andon.setLineName(line.getName());
		} else {
			andon.setLineName(null);
		}
		try {
			List<CMesAndonFault> findAllAndon = null;
			if (andon.getPages() == null || andon.getPages() == 0) {
				andon.setPages(1);
			}
			PageHelper.startPage(andon.getPages(), 10);
			findAllAndon = service.findAllAndon(andon.getLineName(), andon.getStationName(), 2, andon.getLossType(),
					andon.getStartDate(), andon.getEndDate());
			PageInfo<CMesAndonFault> pageInfo = new PageInfo<CMesAndonFault>(findAllAndon, 5);
			findAllAndon = pageInfo.getList();
			json.put("pageNum", pageInfo.getTotal());

			json.put("info", findAllAndon);
			json.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", "1");
		}
		return json;
	}

	// 故障列表导出
	@RequestMapping(value = "findAllAndonDaochu", method = RequestMethod.POST)
	@ApiOperation(value = "历史故障列表", notes = "历史故障列表")
	public JSONObject findAllAndonDaochu(HttpServletRequest request, @RequestBody ResponbodtAndonT andon) throws Exception {
		JSONObject json = new JSONObject();
		String id = null;
		if (!andon.getLineName().equals("")) {
			id = andon.getLineName();
			CMesLineT line = cMesLineTService.findLineByid(Integer.parseInt(id));
			andon.setLineName(line.getName());
		} else {
			andon.setLineName(null);
		}
		try {
			List<CMesAndonFault> findAllAndon = null;
			if (andon.getPages() == null || andon.getPages() == 0) {
				andon.setPages(1);
			}
			findAllAndon = service.findAllAndon(andon.getLineName(), andon.getStationName(), 2, andon.getLossType(),
					andon.getStartDate(), andon.getEndDate());

			json.put("info", findAllAndon);
			json.put("code", "0");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", "1");
		}
		return json;
	}

	// 更新故障
	@RequestMapping(value = "updateFault", method = RequestMethod.POST)
	@ApiOperation(value = "更新故障信息", notes = "更新故障信息")
	public JSONObject updateFault(HttpServletRequest request, @RequestBody UpdateAndon andon) {
		JSONObject json = new JSONObject();
		try {
			CMesAndonFault c = new CMesAndonFault();
			c.setToolId(andon.getToolId());
			c.setId(andon.getId());
			c.setLossType(andon.getLossName());
			c.setEmp(andon.getEmp());
			c.setNote(andon.getNote());
			c.setReason(andon.getReason());
			service.updateFault(c);
			json.put("code", 0);
			json.put("msg", "更新成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "更新失败，未知错误");
		}
		return json;
	}

	@RequestMapping(value = "findReasonByid", method = RequestMethod.POST)
	@ApiOperation(value = "根据损失类型查询损失原因", notes = "根据损失类型查询损失原因")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query"), })
	public JSONObject findReasonByid(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			List<CMesLossReasonT> findLossReason = service.findLossReason(id);
			json.put("code", 0);
			json.put("info", findLossReason);
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 生产计数
	@RequestMapping(value = "InsertAndonInfo", method = RequestMethod.POST)
	@ApiOperation(value = "安灯生产计数", notes = "安灯生产计数")
	@Transactional
	public JSONObject InsertAndonInfo(HttpServletRequest request, @RequestBody InsertInfo andon) {
		JSONObject json = new JSONObject();
		try {
			Integer insertInfo = service.insertInfo(andon);
			if (insertInfo == 0) {
				json.put("code", 1);
				json.put("msg", "添加失败");
				return json;
			}
			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	// 当天生产数据
	@RequestMapping(value = "findNowInfo", method = RequestMethod.POST)
	@ApiOperation(value = "当天生产数据", notes = "当天生产数据")
	public JSONObject findNowInfo(HttpServletRequest request, @RequestBody CMesAndonInfo andon) {
		JSONObject json = new JSONObject();
		Integer num = 0;
		try {
			List<InsertInfo> findAllInfo = service.findNowInfo(andon.getLineName(), andon.getStationName());

			num = findAllInfo.size();
			json.put("num", num);
			json.put("info", findAllInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	// 生产信息
	@RequestMapping(value = "findInfo", method = RequestMethod.POST)
	@ApiOperation(value = "历史生产数据", notes = "历史生产数据")
	public JSONObject findAllInfo(HttpServletRequest request, @RequestBody CMesAndonInfo andon) {
		JSONObject json = new JSONObject();
		Integer num = 0;
		try {
			List<InsertInfo> findAllInfo = service.findInfo(andon.getLineName(), andon.getStationName());
			num = findAllInfo.size();
			json.put("num", num);
			json.put("info", findAllInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	/*
	 * //生产计划
	 *
	 * @RequestMapping(value="findPlan",method=RequestMethod.POST)
	 *
	 * @ApiOperation(value = "获取生产计划", notes = "获取生产计划") public JSONObject
	 * findPlan(@RequestBody CMesAndonInfo andon){ JSONObject json = new
	 * JSONObject(); json.put("planName", "测试计划"); return json; }
	 */

	// Pareto统计
	@RequestMapping(value = "findPareto", method = RequestMethod.POST)
	@ApiOperation(value = "Pareto报表", notes = "Pareto报表")
	public JSONArray findPareto(HttpServletRequest request, @RequestBody CMesParetoT andon) throws Exception, ServicesException {

		String id = null;
		if (!andon.getLineName().equals("")) {
			id = andon.getLineName();
			CMesLineT line = cMesLineTService.findLineByid(Integer.parseInt(id));
			andon.setLineName(line.getName());
		} else {
			andon.setLineName(null);
		}

		JSONArray json = new JSONArray();
		try {
			List<Map<String, Object>> findPareto = service.findPareto(andon.getLineName(), andon.getStationName(),
					andon.getStartTime(), andon.getEndTime());

			for (Map<String, Object> map : findPareto) {
				JSONObject js = new JSONObject();
				js.put("lossType", map.get("lossType"));
				js.put("lossName", map.get("lossName"));
				js.put("num", map.get("num"));
				js.put("dt", map.get("dt"));
				json.add(js);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	// Pareto统计
	@RequestMapping(value = "findParetor", method = RequestMethod.POST)
	@ApiOperation(value = "Paretor报表", notes = "Paretor报表")
	public JSONArray findParetor(HttpServletRequest request, @RequestBody CMesParetoT andon) throws Exception, Exception {
		JSONArray json = new JSONArray();
		String id = null;
		if (!andon.getLineName().equals("")) {
			id = andon.getLineName();
			CMesLineT line = cMesLineTService.findLineByid(Integer.parseInt(id));
			andon.setLineName(line.getName());
		} else {
			andon.setLineName(null);
		}
		try {
			List<Map<String, Object>> findPareto = service.findParetors(andon.getLineName(), andon.getStationName(),
					andon.getStartTime(), andon.getEndTime(), andon.getLossType());
			for (Map<String, Object> map : findPareto) {
				JSONObject js = new JSONObject();
				js.put("lossType", map.get("lossType"));
				js.put("reason", map.get("reason"));
				js.put("lossName", map.get("lossName"));
				js.put("num", map.get("num"));
				js.put("dt", map.get("dt"));
				json.add(js);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	// 损失类型列表
	@RequestMapping(value = "findAllLoss", method = RequestMethod.POST)
	@ApiOperation(value = "损失类型列表", notes = "损失类型列表")
	public List<CMesLossTypeT> findPareto(HttpServletRequest request) {
		List<CMesLossTypeT> findAllLoss = null;
		try {
			findAllLoss = service.findAllLoss();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return findAllLoss;
	}

	@RequestMapping(value = "findEmp", method = RequestMethod.POST)
	@ApiOperation(value = "员工列表", notes = "员工列表")
	public List<Map<String, Object>> findEmp(HttpServletRequest request) {
		List<Map<String, Object>> findEmp = null;
		try {
			findEmp = service.findEmp();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return findEmp;
	}

	@RequestMapping(value = "delAndon", method = RequestMethod.POST)
	@ApiOperation(value = "删除安灯", notes = "删除安灯")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"), })
	public JSONObject delAndon(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			service.delAndon(id);
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "findStationStatus", method = RequestMethod.POST)
	@ApiOperation(value = "当前工位状态", notes = "当前工位状态")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineName", value = "lineName", required = false, paramType = "query"), })
	public JSONObject findStationStatus(HttpServletRequest request, String lineName) {
		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("line", service.findStationStatus710(lineName));
		}  catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "findStatusCount", method = RequestMethod.POST)
	@ApiOperation(value = "产线故障分类统计", notes = "产线故障分类统计")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineName", value = "lineName", required = false, paramType = "query"), })
	public JSONObject findStatusCount(HttpServletRequest request, String lineName) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			JSONArray findCount = service.findCount(lineName);
			json.put("code", 0);
			json.put("line", findCount);
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "ShiftNowNumber", method = RequestMethod.POST)
	@ApiOperation(value = "当前班次的产量", notes = "当前班次的产量")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineName", value = "lineName", required = false, paramType = "query"), })
	public JSONObject ShiftNowNumber(HttpServletRequest request, String lineName) {
		JSONObject json = new JSONObject();
		try {
		//String lineName1 = new String(lineName.getBytes("ISO-8859-1"),"utf-8");
			json = service.findNowNumber(lineName);
			json.put("code", 0);
			json.put("msg", "查询成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "stationResponse", method = RequestMethod.POST)
	@ApiOperation(value = "自动站响应", notes = "自动站响应")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineName", value = "lineName", required = false, paramType = "query"), })
	public JSONObject stationResponse(HttpServletRequest request, String lineName) {
		JSONObject json = new JSONObject();
		try {
			service.updateStationResponse(lineName);
			json.put("code", 0);
			json.put("msg", "响应成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}
}

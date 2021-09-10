package com.skeqi.mes.controller.qh;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesAlarmCodeT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesDeviceUpkeep;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.all.CMesAlarmCodeService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * 报警信息管理
 * @ClassName: CMesAlarmController
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "报警信息管理", description = "报警信息管理", produces = MediaType.APPLICATION_JSON)
public class CMesAlarmMessageController {

	@Autowired
	private CMesAlarmCodeService alarmCodeService;


	@RequestMapping(value = "/alarm/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询报警信息", notes = "可根据报警代码查询报警信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "alarmCode", value = "报警代码", required = false, paramType = "query", dataType = "Integer")
			 })
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6") Integer pageSize,Integer alarmCode) throws ServicesException {

		CMesAlarmCodeT alarm = new CMesAlarmCodeT();
		List<CMesAlarmCodeT> list = null;

		JSONObject json = new JSONObject();

		try {

			alarm.setAlarmCode(alarmCode);
			PageHelper.startPage(pageNum, pageSize);
			list=alarmCodeService.findAllAlarm(alarm);
			PageInfo pageInfo=new PageInfo<>(list);

			json.put("code", 0);
			json.put("msg", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "/alarm/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除报警信息", notes = "根据id删除报警信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "责任id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject delete(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			alarmCodeService.delAlarm(id);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

//	//报警查询
//	@RequestMapping("selAlarm")
//	public Object selAlarm(HttpServletRequest request,Model model,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//		PageHelper.startPage(page,5);
//		CMesAlarmCodeT alarm = new CMesAlarmCodeT();
//		String alarmCode=request.getParameter("alarmCode3");
//		if(alarmCode!=null && alarmCode!=""){
//			alarm.setAlarmCode(Integer.parseInt(alarmCode));
//			try {
//			List<CMesAlarmCodeT> list  = alarmCodeService.findAllAlarm(alarm);
//
//				if(list.size()>0){
//					PageInfo pageInfo=new PageInfo<>(list, 5);
//					model.addAttribute("pageInfo", pageInfo);
//					return "deviceManager_control/alarmInfoManager";
//				}else{
//					model.addAttribute("pageInfo", null);
//					return "deviceManager_control/alarmInfoManager";
//				}
//
//			} catch (ServicesException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}else{
//			return "redirect:/Alarm/findAll";
//		}
//		return "redirect:/Alarm/findAll";
//
//
//	}

	@ResponseBody
	@RequestMapping(value = "/alarm/add", method = RequestMethod.POST)
	@ApiOperation("新增报警信息")
	public JSONObject add(@ModelAttribute CMesAlarmCodeT alarm) {
		JSONObject json = new JSONObject();

		try {
			alarmCodeService.addAlarm(alarm);
			json.put("code", 0);
		}  catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}


	@RequestMapping(value = "/alarm/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询报警信息", notes = "根据id查询报警信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "报警id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			CMesAlarmCodeT alarm = alarmCodeService.findAlarmByid(id);
			json.put("msg", alarm);
			json.put("code", 0);
		}  catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}

	@RequestMapping(value = "/alarm/alter", method = RequestMethod.POST)
	@ApiOperation(value = "修改报警信息", notes = "修改报警信息")
	@ResponseBody
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "修改成功"), @ApiResponse(code = 202, message = "修改失败") })
	public JSONObject alter(@ModelAttribute CMesAlarmCodeT alarm) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			alarmCodeService.updateAlarm(alarm);
			json.put("code", 0);
		}  catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}



}

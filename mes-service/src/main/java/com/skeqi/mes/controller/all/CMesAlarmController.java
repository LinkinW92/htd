package com.skeqi.mes.controller.all;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesAlarmCodeT;
import com.skeqi.mes.service.all.CMesAlarmCodeService;
import com.skeqi.mes.util.ToolUtils;

@Controller
@RequestMapping("Alarm")

/***
 *
 * @author ENS  报警信息管理  1
 *
 */
public class CMesAlarmController {

	@Autowired
	private CMesAlarmCodeService alarmCodeService;


	//报警列表
	@RequestMapping("findAll")
	public String findAllAlarm(Model model,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page,HttpServletRequest request,HttpServletResponse response){

		CMesAlarmCodeT alarm = new CMesAlarmCodeT();
		String alarmCode = request.getParameter("alarmCode3");
		List<CMesAlarmCodeT> list = null;
		try {
			if(alarmCode==null||alarmCode=="") {
				PageHelper.startPage(page,5);
				list=alarmCodeService.findAllAlarm(alarm);

			}else {
				int code =  Integer.parseInt(alarmCode);
				alarm.setAlarmCode(code);
				PageHelper.startPage(page,5);
				list=alarmCodeService.findAllAlarm(alarm);
			}
		} catch (ServicesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		PageInfo pageInfo=new PageInfo<>(list, 5);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("alarmCode", alarmCode);
		return "deviceManager_control/alarmInfoManager";
	}

	//报警删除
	@RequestMapping("delAlarm")
	@ResponseBody
	public  JSONObject delAlarm(Integer id){
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

	//报警查询
	@RequestMapping("selAlarm")
	public Object selAlarm(HttpServletRequest request,Model model,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		PageHelper.startPage(page,5);
		CMesAlarmCodeT alarm = new CMesAlarmCodeT();
		String alarmCode=request.getParameter("alarmCode3");
		if(alarmCode!=null && alarmCode!=""){
			alarm.setAlarmCode(Integer.parseInt(alarmCode));
			try {
			List<CMesAlarmCodeT> list  = alarmCodeService.findAllAlarm(alarm);

				if(list.size()>0){
					PageInfo pageInfo=new PageInfo<>(list, 5);
					model.addAttribute("pageInfo", pageInfo);
					return "deviceManager_control/alarmInfoManager";
				}else{
					model.addAttribute("pageInfo", null);
					return "deviceManager_control/alarmInfoManager";
				}

			} catch (ServicesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			}
		}else{
			return "redirect:/Alarm/findAll";
		}
		return "redirect:/Alarm/findAll";


	}

	//报警信息新增
	@RequestMapping("addAlarm")
	@ResponseBody
	public  JSONObject addAlarm(HttpServletRequest request,Model mode){
		JSONObject json = new JSONObject();
		String alarmCode=request.getParameter("alarmCode").trim();
		String alarmText=request.getParameter("alarmText").trim();
		String alarmEnglish=request.getParameter("alarmEnglish").trim();
		int alarmGrade=Integer.parseInt(request.getParameter("alarmGrade").trim());


		CMesAlarmCodeT alarm = new CMesAlarmCodeT();
		alarm.setAlarmCode(Integer.parseInt(alarmCode));
		alarm.setAlarmEnglish(alarmEnglish);
		alarm.setAlarmGrade(alarmGrade);
		alarm.setAlarmText(alarmText);

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

	//根据id查询
	@RequestMapping("findById")
	@ResponseBody
	public  JSONObject findById(HttpServletRequest request){
		JSONObject json = new JSONObject();
		int id=Integer.parseInt(request.getParameter("id"));

		try {
			CMesAlarmCodeT alarm = alarmCodeService.findAlarmByid(id);
			json.put("alarm", alarm);
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

	//修改报警信息
	@RequestMapping("updateAlarm")
	@ResponseBody
	public  JSONObject updateAlarm(HttpServletRequest request){
		JSONObject json = new JSONObject();
		int id=Integer.parseInt(request.getParameter("id"));
		int alarmCode=Integer.parseInt(request.getParameter("alarmCode").trim());
		String alarmText=request.getParameter("alarmText").trim();
		String alarmEnglish=request.getParameter("alarmEnglish").trim();
		String alarmGrade=request.getParameter("alarmGrade").trim();

		CMesAlarmCodeT alarm = new CMesAlarmCodeT();
		alarm.setId(id);
		alarm.setAlarmCode(alarmCode);
		alarm.setAlarmEnglish(alarmEnglish);
		alarm.setAlarmGrade(Integer.parseInt(alarmGrade));
		alarm.setAlarmText(alarmText);

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

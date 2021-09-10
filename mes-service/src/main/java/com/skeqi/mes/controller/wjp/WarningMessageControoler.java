package com.skeqi.mes.controller.wjp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesAlarmCodeT;
import com.skeqi.mes.service.wjp.WarningMessageService;

@Controller
@RequestMapping("Alarm")

public class WarningMessageControoler {
	@Autowired
	private WarningMessageService warningMessageService;

	/*
	 * //报警列表
	 *
	 * @SuppressWarnings({ "rawtypes"})
	 *
	 * @RequestMapping("findAll") public String findAll(Model
	 * model,@RequestParam(required = false,defaultValue = "1",value =
	 * "page")Integer page,HttpServletRequest request,HttpServletResponse response){
	 * Map<String,Object> map=new HashMap<>(); String alarmCode =
	 * request.getParameter("alarmCode3"); map.put("alarmCode", alarmCode);
	 * PageHelper.startPage(page,5); List<CMesAlarmCodeT>
	 * list=warningMessageService.findAll(map); PageInfo pageInfo=new
	 * PageInfo<>(list, 5); model.addAttribute("pageInfo", pageInfo);
	 * model.addAttribute("alarmCode", alarmCode); return
	 * "deviceManager_control/alarmInfoManager"; }
	 *
	 * //报警删除
	 *
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("delAlarm") public @ResponseBody Map delAlarm(Integer id){
	 * Map<String, Object> map = new HashMap<>(); try {
	 * warningMessageService.delAlarm(id); map.put("msg", 1); } catch (Exception e)
	 * { e.printStackTrace(); } return map; }
	 *
	 * //报警查询
	 *
	 * @SuppressWarnings({ "rawtypes", "unchecked"})
	 *
	 * @RequestMapping("selAlarm") public Object selAlarm(HttpServletRequest
	 * request,Model model,@RequestParam(required = false,defaultValue = "1",value =
	 * "page")Integer page){ PageHelper.startPage(page,5); Map map=new HashMap<>();
	 * String alarmCode=request.getParameter("alarmCode3"); if(alarmCode!=null &&
	 * alarmCode!=""){ map.put("alarmCode", Integer.parseInt(alarmCode));
	 * List<CMesAlarmCodeT> list=warningMessageService.selAlarm(map); try {
	 * if(list.size()>0){ PageInfo pageInfo=new PageInfo<>(list, 5);
	 * model.addAttribute("pageInfo", pageInfo); return
	 * "deviceManager_control/alarmInfoManager"; }else{
	 * model.addAttribute("pageInfo", null); return
	 * "deviceManager_control/alarmInfoManager"; } } catch (Exception e) {
	 * e.printStackTrace(); } }else{ return "redirect:/Alarm/findAll"; } return
	 * "redirect:/Alarm/findAll"; }
	 *
	 * //报警信息新增
	 *
	 * @SuppressWarnings({ "rawtypes", "unchecked" })
	 *
	 * @RequestMapping("addAlarm") public @ResponseBody Map
	 * addAlarm(HttpServletRequest request,Model mode){ Map map=new HashMap<>();
	 * String alarmCode=request.getParameter("alarmCode").trim();
	 * map.put("alarmCode", alarmCode); List<CMesAlarmCodeT>
	 * list=warningMessageService.selAlarm(map); if(list.size()>0){
	 * //存在alarmCode,给一个1 map.put("msg", 1); }else{ map.put("msg", 0); String
	 * alarmText=request.getParameter("alarmText").trim(); String
	 * alarmEnglish=request.getParameter("alarmEnglish").trim(); int
	 * alarmGrade=Integer.parseInt(request.getParameter("alarmGrade").trim());
	 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); Date date = null;
	 * try { date = new SimpleDateFormat("yyyy-MM-dd").parse(df.format(new Date()));
	 * } catch (ParseException e) { e.printStackTrace(); } map.put("alarmCode",
	 * alarmCode); map.put("alarmText", alarmText); map.put("alarmEnglish",
	 * alarmEnglish); map.put("dt", date); map.put("alarmGrade", alarmGrade);
	 * warningMessageService.addAlarm(map); map.put("msg", 0); } return map; }
	 *
	 * //根据id查询
	 *
	 * @SuppressWarnings({ "rawtypes", "unchecked" })
	 *
	 * @RequestMapping("findById") public @ResponseBody Map
	 * findById(HttpServletRequest request){ Map map=new HashMap<>(); int
	 * id=Integer.parseInt(request.getParameter("id")); map.put("id", id);
	 * CMesAlarmCodeT list=warningMessageService.findById(map); if(list!=null){
	 * map.put("ByAlarm", list); map.put("msg", 1); } return map; }
	 *
	 * //修改报警信息
	 *
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("updateAlarm") public @ResponseBody Map
	 * updateAlarm(HttpServletRequest request){ Map<String,Object> map=new
	 * HashMap<>(); int id=Integer.parseInt(request.getParameter("id")); int
	 * alarmCode=Integer.parseInt(request.getParameter("alarmCode").trim()); String
	 * alarmText=request.getParameter("alarmText").trim(); String
	 * alarmEnglish=request.getParameter("alarmEnglish").trim(); String
	 * alarmGrade=request.getParameter("alarmGrade").trim(); map.put("id", id);
	 * map.put("alarmCode", alarmCode); map.put("alarmText", alarmText);
	 * map.put("alarmEnglish", alarmEnglish); map.put("alarmGrade", alarmGrade);
	 * warningMessageService.updateAlarm(map); return map; }
	 */

}

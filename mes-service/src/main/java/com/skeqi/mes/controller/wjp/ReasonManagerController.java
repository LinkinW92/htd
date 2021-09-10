package com.skeqi.mes.controller.wjp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.service.wjp.ReasonManagerService;

@Controller
@RequestMapping("Reason")
public class ReasonManagerController {

	@Autowired
	private ReasonManagerService reasonManagerService;

//	//原因列表
//	@SuppressWarnings({"rawtypes" })
//	@RequestMapping("findAll")
//	public String findAll(Model model,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//		PageHelper.startPage(page,6);
//		List<CMesDefectResionT> list=reasonManagerService.findAll();
//		PageInfo pageInfo = new PageInfo<>(list,3);
//		model.addAttribute("ReasonList", list);
//		model.addAttribute("pageInfo", pageInfo);
//		return "quality_control/reasonManager";
//	}
//
//	//原因新增
//	@SuppressWarnings("rawtypes")
//	@RequestMapping("addReason")
//	public @ResponseBody Map addReason(Model model,HttpServletRequest request){
//		String resionNumber=request.getParameter("resionNumber").trim();
//		String dis=request.getParameter("dis").trim();
//		Map<String, Object> map=new HashMap<>();
//		map.put("resionNumber", resionNumber);
//		map.put("dis", dis);
//		int i=reasonManagerService.noRepeat(resionNumber);
//		if(i>0){
//			map.put("msg", 1);
//		}else{
//			reasonManagerService.addReason(map);
//		}
//		return map;
//	}
//
//	//原因删除
//	@SuppressWarnings("rawtypes")
//	@RequestMapping("removeReason")
//	public @ResponseBody Map removeReason(Model model,HttpServletRequest request){
//		Map<String, Integer> map=new HashMap<>();
//		int id=Integer.parseInt(request.getParameter("id"));
//		reasonManagerService.removeReason(id);
//		map.put("msg", 1);
//		return map;
//	}
//
//	//根据id查询
//	@SuppressWarnings({ "rawtypes"})
//	@RequestMapping("findById")
//	public @ResponseBody Map findById(HttpServletRequest request){
//		Map<String, Object> map=new HashMap<>();
//		int id=Integer.parseInt(request.getParameter("id"));
//		map.put("id", id);
//		CMesDefectResionT list=reasonManagerService.findById(id);
//		if(list!=null){
//			map.put("ByReason", list);
//		}
//		return map;
//	}
//
//	//原因修改
//	@SuppressWarnings("rawtypes")
//	@RequestMapping("updateReason")
//	public @ResponseBody Map updateReason(HttpServletRequest request){
//		Map<String, Object> map=new HashMap<>();
//		String dis=request.getParameter("dis").trim();
//		int id=Integer.parseInt(request.getParameter("id"));
//		String resionNumber=request.getParameter("resionNumber").trim();
//		Date date=new Date();
//		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time=format.format(date);
//		//System.out.println(time);
//		map.put("sysdate", time);
//		map.put("id", id);
//		map.put("dis", dis);
//		map.put("resionNumber", resionNumber);
//		CMesDefectResionT list=reasonManagerService.findById(id);
//		String number=list.getResionNumber();
//		if(!number.equals(resionNumber)){
//			int i=reasonManagerService.noRepeat(resionNumber);
//			if(i>0){
//				map.put("msg", 1);
//			}else{
//				reasonManagerService.updateReason(map);
//			}
//		}else{
//			reasonManagerService.updateReason(map);
//		}
//		return map;
//	}

}

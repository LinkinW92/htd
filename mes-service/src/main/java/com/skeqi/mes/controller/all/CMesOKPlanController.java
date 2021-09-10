package com.skeqi.mes.controller.all;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.PMesPlanT;
import com.skeqi.mes.service.all.RMesPlanTService;

/***
 *
 * @author ENS 完成工单
 *
 */

@Controller
@RequestMapping("/skq")
public class CMesOKPlanController {

//	@Autowired
//	RMesPlanTService planService;
//
//
//	@RequestMapping("okList")
//	public Object okList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page)throws Exception{
//		String planSerialno = request.getParameter("planSerialno");
//		String act_start_time = request.getParameter("act_start_time");
//		String act_stop_time = request.getParameter("act_stop_time");
//		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
//		Map<String, Object> map = new HashMap<>();
//		Date date1=null;
//		Date date2=null;
//
//		if (act_start_time!=null&&act_start_time!="") {
//			date1   =   formatter.parse(act_start_time);
//		}
//		if (act_stop_time!=null&&act_stop_time!="") {
//			date2   =   formatter.parse(act_stop_time);
//		}
//		map.put("planSerialno", planSerialno);
//		map.put("date1", date1);
//		map.put("date2", date2);
//		map.put("flag", 4);
//
//		//寮曞叆鍒嗛〉鏌ヨ锛屼娇鐢≒ageHelper鍒嗛〉鍔熻兘
//		//鍦ㄦ煡璇箣鍓嶄紶鍏ュ綋鍓嶉〉锛岀劧鍚庡灏戣褰�
//		PageHelper.startPage(page,8);
//		//startPage鍚庣揣璺熺殑杩欎釜鏌ヨ灏辨槸鍒嗛〉鏌ヨ
//		List<PMesPlanT> planList = planService.findOKWorkOrder(map);
//		//浣跨敤PageInfo鍖呰鏌ヨ缁撴灉锛屽彧闇�瑕佸皢pageInfo浜ょ粰椤甸潰灏卞彲浠�
//		PageInfo<PMesPlanT> pageInfo = new PageInfo<>(planList,5);
//		//pageINfo灏佽浜嗗垎椤电殑璇︾粏淇℃伅锛屼篃鍙互鎸囧畾杩炵画鏄剧ず鐨勯〉鏁�
//		request.setAttribute("planSerialno", planSerialno);
//		request.setAttribute("act_start_time", act_start_time);
//		request.setAttribute("act_stop_time", act_stop_time);
//		request.setAttribute("pageInfo", pageInfo);
//		return "plan_control/okList";
//	}

}

package com.skeqi.mes.controller.fqz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.RMesNewSn;
import com.skeqi.mes.pojo.RMesPlanPrintT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;
import com.skeqi.mes.service.fqz.HeavyService;

@RequestMapping("/skq")
@Controller
public class HeavyController {

	@Autowired
	private HeavyService heavyservice;

	@RequestMapping("/getheavy")
	public String getheavy(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		PageHelper.startPage(page,8);
		List<RMesNewSn> findAll = heavyservice.findAll();
		PageInfo<RMesNewSn> pageInfo = new PageInfo<>(findAll,5);
		request.setAttribute("pageInfo", pageInfo);
		List<RMesPlanT> findRmesPlanT = heavyservice.findRmesPlanT();
		request.setAttribute("first",findRmesPlanT.get(0).getId());
		request.setAttribute("info",findRmesPlanT);
		return "plan_control/heavys";
	}

	@RequestMapping("/getPlant")
	@ResponseBody
	public List<RMesWorkorderDetailT> getPlant(String id){
		List<RMesWorkorderDetailT> findDetil = heavyservice.findDetil(Integer.parseInt(id));
		return findDetil;
	}

	@RequestMapping("getsn")
	@ResponseBody
	public List<RMesPlanPrintT> getsn(String id){
		List<RMesPlanPrintT> findSn = heavyservice.findSn(Integer.parseInt(id));
		return findSn;

	}
	@RequestMapping(value="jugdeSN",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String jugde(String id){
		String flag = "存在";
		Integer jugdeSN = heavyservice.jugdeSN(id);
		if(jugdeSN==0){
			flag = "不存在";
		}
		return flag;
	}

/*	@RequestMapping(value="getbar",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getbar(String id,String oldsn){
		System.out.println("就总成"+oldsn);
		String flag = "存在";
		Integer findPrint = heavyservice.findPrint(Integer.parseInt(id));
		if(findPrint==0){
			flag = "不存在";
			return flag;
		}else{
			return flag;
		}
	}*/
	@RequestMapping(value="insertsn",produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String insert(String newsn,String oldsn){
		String flag = "yes";
		Integer jugdeoldSN = heavyservice.jugdeoldSN(oldsn);
		String findNewSN = heavyservice.findNewSN(Integer.parseInt(newsn));
		Integer jugdenewSN = heavyservice.jugdenewSN(findNewSN);
		Integer jugdeoldSN_New = heavyservice.jugdeoldSN_New(oldsn);
		if(jugdeoldSN==0 && jugdenewSN==0 && jugdeoldSN_New==0){
			heavyservice.insertnewsn(findNewSN, oldsn);
		}else{
			flag = "no";
		}
		return flag;
	}
}

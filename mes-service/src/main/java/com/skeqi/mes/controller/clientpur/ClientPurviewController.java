package com.skeqi.mes.controller.clientpur;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.neo4j.cypher.internal.compiler.v2_2.pipes.NiceHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesClientPurviewT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.service.clientpur.ClientPurviewService;
import com.skeqi.mes.util.ToolUtils;

@Controller
@RequestMapping("clientpur")
public class ClientPurviewController {

	@Autowired
	ClientPurviewService clientPurviewService;

	@RequestMapping("ClientPurvierList")
	public String clientpurvier(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, 8);
		List<CMesClientPurviewT> clientpurList = clientPurviewService.clientPurviewList(map);
		PageInfo<CMesClientPurviewT> pageInfo = new PageInfo<>(clientpurList, 5);
		request.setAttribute("pageInfo", pageInfo);
		return "base_control/clientPasswordManagement";
	}

	@ResponseBody
	@RequestMapping("addClientPurview")
	public Map<String, Object> addClientPurview(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String funNo = request.getParameter("funNo").trim();
		String funName = request.getParameter("funName").trim();
		String ownPassword = request.getParameter("ownPassword").trim();
		String dis = request.getParameter("dis");
		map.put("dt", new Date());
		map.put("funNo", funNo);
		map.put("funName", funName);
		map.put("ownPassword", ownPassword);
		map.put("dis", dis);
		List<CMesClientPurviewT> clientpurList = clientPurviewService.clientPurviewList(map);
		if (clientpurList.size() > 0) {
			map.put("msg", -1);
			return map;
		}
		try {
			clientPurviewService.addClientPurview(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	@RequestMapping("findById")
	public @ResponseBody Map<String, Object> ClientPurfindById(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer ClientPurId = Integer.parseInt(request.getParameter("id"));
		map.put("id", ClientPurId);
		List<CMesClientPurviewT> clientpurList = clientPurviewService.clientPurviewList(map);
		map.put("clientpur", clientpurList.get(0));
		return map;
	}

	@RequestMapping("updateClientPur")
	public @ResponseBody Map<String, Object> updateClientPur(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer ClientPurId = Integer.parseInt(request.getParameter("id"));
		String funNo1 = request.getParameter("funNo1");
		String funName1 = request.getParameter("funName1");
		String ownPassword1 = request.getParameter("ownPassword1");
		String dis1 = request.getParameter("dis1");
		map.put("id", ClientPurId);
		map.put("dt", new Date());
		map.put("funNo", funNo1);
		map.put("funName", funName1);
		map.put("ownPassword", ownPassword1);
		map.put("dis", dis1);
		List<CMesClientPurviewT> clientpurList = clientPurviewService.clientPurviewList(map);
		try {

			if(!clientpurList.isEmpty()){
				if(funNo1.equals(clientpurList.get(0).getFunNo())&&clientpurList.get(0).getId()!=ClientPurId&&clientpurList.size()>=1){
					map.put("msg", 1);
				}else{
					clientPurviewService.updateClientPur(map);
					map.put("msg", 0);
				}
			}else{
				clientPurviewService.updateClientPur(map);
				map.put("msg", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	@RequestMapping("delClientPur")
	public @ResponseBody Map<String, Object> delClientPur(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		try {
			map.put("id",id );
			clientPurviewService.delClientPur(map);
			map.put("msg", "ok");
		} catch (Exception e) {
			map.put("msg", 0);
		}
		return map;
	}

}

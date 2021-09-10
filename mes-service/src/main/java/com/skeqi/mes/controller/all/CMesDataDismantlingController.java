package com.skeqi.mes.controller.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.PTrackingT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.service.all.QualityService;

/***
 *
 * @author ENS 数据拆解
 *
 */

@Controller
@RequestMapping("skq")
public class CMesDataDismantlingController {

	@Autowired
	QualityService qualityService;

	/**
	 * 数据拆解
	 */
	@RequestMapping("dataDismantling")
	public String dataDismantling(HttpServletRequest request,
			@RequestParam(required = false,defaultValue = "1",value = "page")Integer page,
			@RequestParam(required = false,defaultValue = "1",value = "page2")Integer page2,
			@RequestParam(required = false,defaultValue = "1",value = "page3")Integer page3,
			HttpServletResponse response)throws Exception {
		String sn = request.getParameter("sn");
		if(sn==null){
			sn=" ";
		}
			List<PTrackingT> ptrackingList = qualityService.findPTrack(sn);
			request.setAttribute("sn", sn);
			if (ptrackingList.size()>0) {
				request.setAttribute("startDate",ptrackingList.get(0).getDt());//上线时间
				request.setAttribute("endDate", ptrackingList.get(0).getOfflineDt());//下线时间
				request.setAttribute("typeName",ptrackingList.get(0).getTypename());//产品类型

				PageHelper.startPage(page,8);
				List<PMesBoltT> boltListR = qualityService.findPBolt(sn);
				PageInfo<PMesBoltT> pageInfo = new PageInfo<>(boltListR,5);

				PageHelper.startPage(page2,8);
				List<PMesKeypartT> keypartListR = qualityService.findPKeypart(sn);
				PageInfo<PMesKeypartT> pageInfo2 = new PageInfo<>(keypartListR,5);

				PageHelper.startPage(page3,8);
				List<PMesLeakageT> leakageListR =qualityService.findPLeakage(sn);
				PageInfo<PMesLeakageT> pageInfo3 = new PageInfo<>(leakageListR,5);

			}else {
				List<RTrackingT> rtrackingList = qualityService.findRTrack(sn);
				if (rtrackingList.size()>0) {
					request.setAttribute("startDate",rtrackingList.get(0).getDT());//上线时间
					request.setAttribute("typeName",rtrackingList.get(0).getTypeName());//产品类型

					PageHelper.startPage(page,8);
					List<RMesBolt> boltListR = qualityService.findRbolt(sn);
					PageInfo<RMesBolt> pageInfo = new PageInfo<>(boltListR,5);

					PageHelper.startPage(page2,8);
					List<RMesKeypart> keypartListR = qualityService.findRKeypart(sn);
					PageInfo<RMesKeypart> pageInfo2 = new PageInfo<>(keypartListR,5);

					PageHelper.startPage(page3,8);
					List<RMesLeakage> leakageListR =qualityService.findRLeakage(sn);
					PageInfo<RMesLeakage> pageInfo3 = new PageInfo<>(leakageListR,5);
					request.setAttribute("pageInfo", pageInfo);
					request.setAttribute("pageInfo2", pageInfo2);
					request.setAttribute("pageInfo3", pageInfo3);

				}else {
					List<RMesBolt> boltListR = qualityService.findRbolt(null);
					PageInfo<RMesBolt> pageInfo = new PageInfo<>(boltListR,5);

					PageHelper.startPage(page2,8);
					List<RMesKeypart> keypartListR = qualityService.findRKeypart(null);
					PageInfo<RMesKeypart> pageInfo2 = new PageInfo<>(keypartListR,5);

					PageHelper.startPage(page3,8);
					List<RMesLeakage> leakageListR =qualityService.findRLeakage(null);
					PageInfo<RMesLeakage> pageInfo3 = new PageInfo<>(leakageListR,5);
					request.setAttribute("pageInfo", pageInfo);
					request.setAttribute("pageInfo2", pageInfo2);
					request.setAttribute("pageInfo3", pageInfo3);
				}
			}



		return "deviceManager_control/dataDismantling";
	}

	/**
	 * 拆解数据（修改bolt、keypart、leakage状态）
	 */
	@RequestMapping("relieveData")
	@Transactional
	@ResponseBody
	public JSONObject relieveData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String mode = request.getParameter("mode");
		String sn = request.getParameter("sn");
		String reasons = request.getParameter("reasons");
		String account = request.getParameter("account");

		try {
			List<PTrackingT> ptrackingList = qualityService.findPTrack(sn);

			if (ptrackingList.size() > 0) {
				if ("1".equals(mode)) {
					qualityService.updateBoltP(Integer.parseInt(id), reasons);
				} else if (mode.equals("2")) {
					qualityService.updateKeypartP(Integer.parseInt(id), reasons);
				} else if (mode.equals("3")) {
					qualityService.updateLeakageP(Integer.parseInt(id), reasons);
				}

			} else {

				List<RTrackingT> rtrackingList = qualityService.findRTrack(sn);
				if (rtrackingList.size() > 0) {

					if ("1".equals(mode)) {
						qualityService.updateBoltR(Integer.parseInt(id), reasons);
					} else if (mode.equals("2")) {
						qualityService.updateKeypartR(Integer.parseInt(id), reasons);
					} else if (mode.equals("3")) {
						qualityService.updateLeakageR(Integer.parseInt(id), reasons);
					}
				}

			}

			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

}

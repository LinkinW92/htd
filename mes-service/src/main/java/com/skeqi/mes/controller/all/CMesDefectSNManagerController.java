package com.skeqi.mes.controller.all;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;
import com.skeqi.mes.service.all.QualityService;

/***
 *
 * @author ENS 缺陷总成管理
 *
 */
@Controller
@RequestMapping("skq")
public class CMesDefectSNManagerController {

	@Autowired
	QualityService qualityService;

//
//	//缺陷总成的查询列表
//	@RequestMapping("defectSNManager")
//	public String queryDutyTypeManagerList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//			PageHelper.startPage(page,13);
//			CMesDutyTypeManagerT dutyType = new CMesDutyTypeManagerT();
//			try {
//				//缺陷管理的查询
//				CMesDefectManager defect = new CMesDefectManager();
//				List<CMesDefectManager> defectList = qualityService.findAllDefectManager(defect);
//				//责任类型
//				CMesDutyManagerT dm = new CMesDutyManagerT();
//				List<CMesDutyManagerT> dutyList = qualityService.findAllDutyManager(dm);
//				//原因类型
//				CMesDefectResionT dr = new CMesDefectResionT();
//				List<CMesDefectResionT> list=qualityService.findAllReason(dr);
//
//			//	qualityService
//				//PageInfo<CMesDutyTypeManagerT> pageInfo = new PageInfo<>(dutyTypeList,8);
//				//request.setAttribute("pageInfo", pageInfo);
//			}catch (Exception e) {
//				// TODO: handle exception
//			}
//			return "quality_control/dutyTypeManager";
//	}
//
//
//
//
//

}

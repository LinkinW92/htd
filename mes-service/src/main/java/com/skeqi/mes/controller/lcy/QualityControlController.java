package com.skeqi.mes.controller.lcy;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;
import com.skeqi.mes.service.lcy.QualityControlService;

//质量管理
@Controller
@RequestMapping("skq")
public class QualityControlController {

	@Autowired
	private QualityControlService qcs;
//	/**
//	 *
//	 * 缺陷等级管理
//	 */
//	//缺陷等级管理的删除方法
//	@RequestMapping("removeDefectGradeManager")
//	@ResponseBody
//	public JSONObject removeDefectGradeManager(HttpServletRequest request){
//		JSONObject jo = new JSONObject();
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//		//判断等级管理中是否有这个缺陷管理的使用，若有客户不能删除，若没有，则删除
//		int index = qcs.getDefectManagerNumber(id);
//		if(index==0){//index 为0,则说明数据库中没有这个类型的调用，可以直接删除
//			qcs.removeDefectGradeManager(id);
//			jo.put("flag", true);
//			jo.put("value","删除成功");
//		}else{
//			jo.put("flag", false);
//			jo.put("value","缺陷管理中有该数据的使用,若要删除,请先删除缺陷管理中的使用再删除");
//		}
//		return jo;
//	}
//
//	/*
//	@RequestMapping("removeAllDefectManager")
//	@ResponseBody
//	public void removeAllDefectManager(HttpServletRequest request){
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//		qcs.removeAllDefectManager(id);
//		qcs.removeDefectGradeManager(id);
//	}*/
//
//
//
//
//	//缺陷等级管理的查找
//	@RequestMapping("findDefectGradeManagerById")
//	@ResponseBody
//	public JSONObject findDefectGradeManagerById(HttpServletRequest request){
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//		CMesDefectGradeManagerT defectGradeManage=qcs.findDefectGradeManagerById(id);
//		JSONObject jo = new JSONObject();
//		jo.put("defectGradeManage", defectGradeManage);
//		return jo;
//	}
//	//缺陷等级管理的添加
//	@RequestMapping("addDefectGradeManager")
//	@ResponseBody
//	public JSONObject addDefectGradeManager(HttpServletRequest request){
//	String defectGradeId=request.getParameter("defectGradeId");
//	String defectGradeName =request.getParameter("defectGradeName");
//	String defectGradeDis = request.getParameter("defectGradeDis");
//	String getDate = GetDate.getDateforSimple(new Date());
//	Integer getValue=qcs.findEqualDefectGradeManager(defectGradeId,null);
//	JSONObject jo = new JSONObject();
//	if(getValue==0){
//		qcs.addDefectGradeManager(defectGradeId,defectGradeName,defectGradeDis,getDate);
//		jo.put("getResult", 0);
//	}else{
//		jo.put("getResult", 1);
//	}
//		return jo;
//	}
//	//缺陷等级管理的修改
//	@RequestMapping("updataDefectGradeManager")
//	@ResponseBody
//	public JSONObject updataDefectGradeManager(HttpServletRequest request){
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//		String defectGradeId=request.getParameter("defectGradeId");
//		String defectGradeName =request.getParameter("defectGradeName");
//		String getDate = GetDate.getDateforSimple(new Date());
//		Integer getValue=qcs.findEqualDefectGradeManager(defectGradeId,null);//说明这个数据库里面没有
//		Integer getValue2=qcs.findEqualDefectGradeManager(defectGradeId,id);//说明现在的就是  没有进行修改
//		JSONObject jo = new JSONObject();
//		if(getValue==0||getValue2==1){
//			qcs.updataDefectGradeManager(id,defectGradeId,defectGradeName,getDate);
//			jo.put("getResult", 0);
//		}else{
//
//			jo.put("getResult", 1);
//		}
//			return jo;
//
//	}
//	//缺陷等级管理的列表
//		@RequestMapping("queryDefectGradeManagerList")
//		public String queryDefectGradeManagerList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//				PageHelper.startPage(page,13);
//				List<CMesDefectGradeManagerT> defectGradeList = qcs.queryDefectGradeManagerList();
//				PageInfo<CMesDefectGradeManagerT> pageInfo = new PageInfo<>(defectGradeList,8);
//				request.setAttribute("pageInfo", pageInfo);
//				return "quality_control/defectGradeManager";
//		}
//
//


//	/**
//	 *
//	 * 缺陷管理
//	 */
//	//初始化下拉选
//	@RequestMapping("getInitDefectGradeManagerList")
//	@ResponseBody
//	public JSONObject getInitDefectGradeManagerList(){
//		JSONObject jo = new JSONObject();
//		List<CMesDefectGradeManagerT> list = qcs.getInitDefectGradeManagerList();
//		jo.put("getInit",list);
//		return jo;
//	}
//
//
//	@RequestMapping("removeAllDefectManager")
//	@ResponseBody
//	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
//	public JSONObject  removeAllDefectManager(HttpServletRequest request){
//		JSONObject jo = new JSONObject();
//		Integer id=Integer.parseInt(request.getParameter("id"));
//		qcs.updataAllDefectManager(id);
//		qcs.removeDefectManager(id);
//		jo.put("flag",true);
//		return jo;
//	}
//
//
//
//	//缺陷管理的删除方法
//	@RequestMapping("removeDefectManager")
//	@ResponseBody
//	public JSONObject removeDefectManager(HttpServletRequest request){
//		JSONObject jo = new JSONObject();
//		String id=request.getParameter("id");//获取id
//		int index = qcs.queryUseDefectManagerNumber(Integer.parseInt(id));
//		if(index==0){
//			qcs.removeDefectManager(Integer.parseInt(id));
//			jo.put("flag",true);
//			jo.put("value", "删除成功，2秒后自动刷新页面…");
//		}else{
//			jo.put("flag",false);
//			jo.put("value", "删除失败，缺陷产品原因中有此引用");
//		}
//		return jo;
//	}
//	//缺陷管理的查找
//		@RequestMapping("findDefectManagerById")
//		@ResponseBody
//		public JSONObject findDefectManagerById(HttpServletRequest request){
//			String id=request.getParameter("id");//获取id
//			CMesDefectManager defectManager=qcs.findDefectManager(Integer.parseInt(id));
//			JSONObject jo = new JSONObject();
//			jo.put("defectManager", defectManager);
//			return jo;
//
//		}
//
//	//缺陷管理的添加
//	@RequestMapping("addDefectManager")
//	@ResponseBody
//	public JSONObject addDefectManager(HttpServletRequest request){
//		String defectId=request.getParameter("defectId");
//		String defectName =request.getParameter("defectName");
//		String defectDis = request.getParameter("defectDis");
//		Integer defectGrade=Integer.parseInt(request.getParameter("defectGrade"));
//		String getDate = GetDate.getDateforSimple(new Date());
//
//		JSONObject jo = new JSONObject();
//		Integer getValue=qcs.findEqualDefectManager(defectId,null);//说明数据库里没有这条数据
//		if(getValue==0){
//			qcs.addDefectManager(getDate,defectGrade,defectId,defectName,defectDis);
//			jo.put("getResult", 0);
//		}else{
//			jo.put("getResult", 1);
//		}
//		return jo;
//	}
//
//	//缺陷管理的修改
//	@RequestMapping("updateDefectManager")
//	@ResponseBody
//	public JSONObject updataDefectManager(HttpServletRequest request){
//		int id=Integer.parseInt(request.getParameter("id"));
//		String defectId=request.getParameter("defectId");
//		String defectName=request.getParameter("defectName");
//		Integer defectGrade=Integer.parseInt(request.getParameter("defectGrade"));
//		String getDate = GetDate.getDateforSimple(new Date());
//		JSONObject jo = new JSONObject();
//
//		Integer getValue=qcs.findEqualDefectManager(defectId,null);//说明现在的就是  没有进行修改
//		Integer getValue2=qcs.findEqualDefectManager(defectId,id);//说明现在的就是  没有进行修改
//		if(getValue==0||getValue2==1){
//			qcs.updataDefectManager(id,getDate,defectId,defectName,defectGrade);
//			jo.put("getResult", 0);
//		}else{
//			jo.put("getResult", 1);
//		}
//
//		return jo;
//	}
//
//	//缺陷管理的列表
//	@RequestMapping("queryDefectManagerList")
//	public String queryDefectManagerList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//			PageHelper.startPage(page,13);
//			List<CMesDefectManager> defectList = qcs.queryDefectManagerList();
//			PageInfo<CMesDefectManager> pageInfo = new PageInfo<>(defectList,8);
//			request.setAttribute("pageInfo", pageInfo);
//
//			return "quality_control/defectManager";
//	}
//

	/**
	 * 原因管理
	 */
	/*
	//原因管理的删除方法
	@RequestMapping("removeReasonManager")
	@ResponseBody
	public void removeReasonManager(HttpServletRequest request){
		String id=request.getParameter("id");//获取id


	}

	//原因管理的查找
	@RequestMapping("findReasonManagerById")
	@ResponseBody
	public void findReasonManagerById(HttpServletRequest request){
		String id=request.getParameter("id");//获取id


	}
	//原因管理的添加
	@RequestMapping("addReasonManager")
	@ResponseBody
	public void addReasonManager(HttpServletRequest request){



	}
	//原因管理的修改
	@RequestMapping("updataReasonManager")
	@ResponseBody
	public void updataReasonManager(HttpServletRequest request){



	}*/



//	/**
//	 *
//	 * 责任管理
//	 */
//	//初始化下拉选
//		@RequestMapping("getInitDutyTypeManagerList")
//		@ResponseBody
//		public JSONObject getInitDutyTypeManagerList(){
//			JSONObject jo = new JSONObject();
//			List<CMesDutyTypeManagerT> list = qcs.getInitDutyTypeManagerList();
//			jo.put("getInit",list);
//			return jo;
//
//		}
//
//	//缺陷管理的列表
//		@RequestMapping("queryDutyManagerList")
//		public String queryDutyManagerList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//				PageHelper.startPage(page,13);
//				List<CMesDutyManagerT> defectList = qcs.queryDutyManagerList();
//				PageInfo<CMesDutyManagerT> pageInfo = new PageInfo<>(defectList,8);
//				request.setAttribute("pageInfo", pageInfo);
//
//				return "quality_control/dutyManager";
//		}
//
//
//	//责任管理的删除方法
//	@RequestMapping("removeDutyManager")
//	@ResponseBody
//	public JSONObject removeDutyManager(HttpServletRequest request){
//		JSONObject jo = new JSONObject();
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//		int index = qcs.queryUseDutyManagerNumber(id);
//		if(index==0){
//			qcs.removeDutyManager(id);
//			jo.put("flag",true);
//			jo.put("value", "删除成功");
//		}else{
//			jo.put("flag",false);
//			jo.put("value", "删除失败，缺陷产品原因中有此引用");
//		}
//		return jo;
//	}
//
//	@RequestMapping("removeAllDutyManager")
//	@ResponseBody
//	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
//	public JSONObject  removeAllDutyManager(HttpServletRequest request){
//		JSONObject jo = new JSONObject();
//		Integer id=Integer.parseInt(request.getParameter("id"));
//		qcs.updataAllDutyManager(id);
//		qcs.removeDutyManager(id);
//		jo.put("flag",true);
//		return jo;
//	}
//
//	//责任管理的查找
//	@RequestMapping("findDutyManagerById")
//	@ResponseBody
//	public JSONObject findDutyManagerById(HttpServletRequest request){
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//		CMesDutyManagerT dutyManager=qcs.findDutyManagerById(id);
//		JSONObject jo = new JSONObject();
//		jo.put("dutyManager", dutyManager);
//		return jo;
//	}
//	//责任管理的添加
//	@RequestMapping("addDutyManager")
//	@ResponseBody
//	public JSONObject addDutyManager(HttpServletRequest request){
//
//		String dutyId=request.getParameter("dutyId");
//		String dutyName =request.getParameter("dutyName");
//		String dutyDis = request.getParameter("dutyDis");
//		Integer dutyType=Integer.parseInt(request.getParameter("dutyType"));
//		String getDate = GetDate.getDateforSimple(new Date());
//
//		JSONObject jo = new JSONObject();
//		Integer getValue=qcs.findEqualDutyManager(dutyId,null);//说明数据库里没有这条数据
//		if(getValue==0){
//			qcs.addDutyManager(getDate,dutyId,dutyName,dutyDis,dutyType);
//			jo.put("getResult", 0);
//		}else{
//			jo.put("getResult", 1);
//		}
//		return jo;
//
//
//	}
//	//责任管理的修改
//	@RequestMapping("updateDutyManager")
//	@ResponseBody
//	public JSONObject updataDutyManager(HttpServletRequest request){
//		Integer id = Integer.parseInt(request.getParameter("id"));
//		String dutyId=request.getParameter("dutyId");
//		String dutyName =request.getParameter("dutyName");
//		Integer dutyType=Integer.parseInt(request.getParameter("dutyType"));
//		String getDate = GetDate.getDateforSimple(new Date());
//		JSONObject jo = new JSONObject();
//		Integer getValue=qcs.findEqualDutyManager(dutyId,null);//说明现在的就是  没有进行修改
//		Integer getValue2=qcs.findEqualDutyManager(dutyId,id);//说明现在的就是  没有进行修改
//		if(getValue==0||getValue2==1){
//			qcs.updataDutyManager(id,getDate,dutyId,dutyName,dutyType);
//			jo.put("getResult", 0);
//		}else{
//			jo.put("getResult", 1);
//		}
//		return jo;
//	}
//	/**
//	 *
//	 * 责任类型管理
//	 */
//	//责任类型管理的删除方法
//	@RequestMapping("removeDutyTypeManager")
//	@ResponseBody
//	public JSONObject removeDutyTypeManager(HttpServletRequest request){
//		JSONObject jo = new JSONObject();
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//		int index = qcs.getDutyManagerNumber(id);
//		if(index==0){//index 为0,则说明数据库中没有这个类型的调用，可以直接删除
//			qcs.removeDutyTypeManager(id);
//			jo.put("flag", true);
//			jo.put("value","删除成功");
//		}else{
//			jo.put("flag", false);
//			jo.put("value","责任管理中有该数据的使用,若要删除,请先删除责任管理中的使用再删除");
//		}
//		return jo;
//	}
//	/*//删除责任管理中的所有关于这个id的数据
//	@RequestMapping("removeAllDutyManager")
//	@ResponseBody
//	public void removeAllDutyManager(HttpServletRequest request){
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//			qcs.removeAllDutyManager(id);
//			qcs.removeDutyTypeManager(id);
//	}*/
//
//
//	//责任类型管理的查找
//	@RequestMapping("findDutyTypeManagerById")
//	@ResponseBody
//	public JSONObject findDutyTypeManagerById(HttpServletRequest request){
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//		CMesDutyTypeManagerT dutyTypeManager=qcs.findDutyTypeManagerById(id);
//		JSONObject jo = new JSONObject();
//		jo.put("dutyTypeManager", dutyTypeManager);
//		return jo;
//	}
//
//	//责任类型管理的添加
//	@RequestMapping("addDutyTypeManager")
//	@ResponseBody
//	public JSONObject addDutyTypeManager(HttpServletRequest request){
//		String dutyTypeId=request.getParameter("dutyTypeId");
//		String dutyTypeName =request.getParameter("dutyTypeName");
//		String dutyTypeDis = request.getParameter("dutyTypeDis");
//		String getDate = GetDate.getDateforSimple(new Date());
//		Integer getValue=qcs.findEqualDutyTypeManager(dutyTypeId,null);
//		JSONObject jo = new JSONObject();
//		if(getValue==0){
//			qcs.addDutyTypeManager(getDate,dutyTypeId,dutyTypeName,dutyTypeDis);
//			jo.put("getResult", 0);
//		}else{
//			jo.put("getResult", 1);
//		}
//		return jo;
//
//
//	}
//
//
//	//责任类型管理的修改
//	@RequestMapping("updateDutyTypeManager")
//	@ResponseBody
//	public JSONObject updataDutyTypeManager(HttpServletRequest request){
//		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
//		String dutyTypeId=request.getParameter("dutyTypeId");
//		String dutyTypeName =request.getParameter("dutyTypeName");
//		String getDate = GetDate.getDateforSimple(new Date());
//		Integer getValue=qcs.findEqualDutyTypeManager(dutyTypeId,null);//说明这个数据库里面没有
//		Integer getValue2=qcs.findEqualDutyTypeManager(dutyTypeId,id);//说明现在的就是  没有进行修改
//		JSONObject jo = new JSONObject();
//		if(getValue==0||getValue2==1){
//			qcs.updataDutyTypeManager(id,dutyTypeId,dutyTypeName,getDate);
//			jo.put("getResult", 0);
//		}else{
//
//			jo.put("getResult", 1);
//		}
//			return jo;
//	}
//		//责任类型管理的列表
//			@RequestMapping("queryDutyTypeManagerList")
//			public String queryDutyTypeManagerList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//					PageHelper.startPage(page,13);
//					List<CMesDutyTypeManagerT> dutyTypeList = qcs.queryDutyTypeManagerList();
//					PageInfo<CMesDutyTypeManagerT> pageInfo = new PageInfo<>(dutyTypeList,8);
//					request.setAttribute("pageInfo", pageInfo);
//					return "quality_control/dutyTypeManager";
//			}
//
//



}

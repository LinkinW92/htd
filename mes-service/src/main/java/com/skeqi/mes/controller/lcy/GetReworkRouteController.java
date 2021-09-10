package com.skeqi.mes.controller.lcy;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesDefectReasonT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.RMesTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;
import com.skeqi.mes.service.lcy.GetReworkRouteService;

@Controller
@RequestMapping("skq")
public class GetReworkRouteController {

	@Autowired
	GetReworkRouteService rrs;

	//产品查询
	@RequestMapping("queryReworkRoute")
	@ResponseBody
	public JSONObject queryReworkRoute(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String sn=request.getParameter("sn");
		Integer queryPage2=Integer.parseInt(request.getParameter("queryPage2"));
		Integer queryPage1=Integer.parseInt(request.getParameter("queryPage1"));
		Integer queryPage3=Integer.parseInt(request.getParameter("queryPage3"));
		//根据总成查询产品
		RMesTrackingT product=rrs.getReworkRoute(sn);
		//若产品为空 则说明 要么没有改产品  要么产品是合格品
		if(product!=null){
			jo.put("keyFlag",true);
			jo.put("product",product);//产品

			PageHelper.startPage(queryPage1,8);
			//螺栓
			List<PMesBoltT> boltList = rrs.queryBoltList(sn);
			PageInfo<PMesBoltT> pageInfo1 = new PageInfo<>(boltList,5);

			StringBuffer sb1 = new StringBuffer();
			for(int i=0;i<pageInfo1.getSize();i++){
				sb1.append("<tr>");
				if(GetDate.getDateforSimple(pageInfo1.getList().get(i).getDt())==null
						||"null".equals(GetDate.getDateforSimple(pageInfo1.getList().get(i).getDt()))){

					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+GetDate.getDateforSimple(pageInfo1.getList().get(i).getDt())+"</td>");

				}

				if(pageInfo1.getList().get(i).getBoltName()==null
						||"null".equals(pageInfo1.getList().get(i).getBoltName())){

					sb1.append("<td scope=\"row\"></td>");
				}else{

					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getBoltName()+"</td>");
				}
				if(pageInfo1.getList().get(i).getA()==null
						||"null".equals(pageInfo1.getList().get(i).getA())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getA()+"</td>");
				}
				if(pageInfo1.getList().get(i).getT()==null
						||"null".equals(pageInfo1.getList().get(i).getT())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getT()+"</td>");
				}
				if(pageInfo1.getList().get(i).getR()==null
						||"null".equals(pageInfo1.getList().get(i).getR())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getR()+"</td>");
				}
				if(pageInfo1.getList().get(i).getWid()==null
						||"null".equals(pageInfo1.getList().get(i).getWid())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getWid()+"</td>");
				}
				if(pageInfo1.getList().get(i).getSt()==null
						||"null".equals(pageInfo1.getList().get(i).getSt())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getSt()+"</td>");
				}
				if(pageInfo1.getList().get(i).getaLimit()!=null){
					sb1.append("<td><a onclick=\"disassembly("+pageInfo1.getList().get(i).getId()+",1); \" href=\"javascript:;\" class=\"btn btn-default\" style=\"padding-top:2px;background-color: #d9534f;color: white;\">拆解</a></td>");
				}else{
					sb1.append("<td><a href='#' class=\"btn btn-default\" style=\"padding-top:2px;background-color: #d9534f;color: white;\">已拆解</a></td>");
				}

				sb1.append("</tr>");
			}


			PageHelper.startPage(queryPage2,8);
			//对应物料信息
			List<PMesKeypartT> keypartList = rrs.queryKeypartList(sn);
			PageInfo<PMesKeypartT> pageInfo2 = new PageInfo<>(keypartList,5);
			StringBuffer sb2 = new StringBuffer();

			for(int i=0;i<pageInfo2.getSize();i++){
				sb2.append("<tr>");
				if(GetDate.getDateforSimple(pageInfo2.getList().get(i).getDt())==null
						||"null".equals(GetDate.getDateforSimple(pageInfo2.getList().get(i).getDt()))){

					sb2.append("<td scope=\"row\"></td>");
				}else{
					sb2.append("<td scope=\"row\">"+GetDate.getDateforSimple(pageInfo2.getList().get(i).getDt())+"</td>");

				}
				if(pageInfo2.getList().get(i).getKeypartName()==null
						||"null".equals(pageInfo2.getList().get(i).getKeypartName())){

					sb2.append("<td scope=\"row\"></td>");
				}else{

					sb2.append("<td scope=\"row\">"+pageInfo2.getList().get(i).getKeypartName()+"</td>");
				}
				if(pageInfo2.getList().get(i).getKeypartNum()==null
						||"null".equals(pageInfo2.getList().get(i).getKeypartNum())){
					sb2.append("<td scope=\"row\"></td>");
				}else{
					sb2.append("<td scope=\"row\">"+pageInfo2.getList().get(i).getKeypartNum()+"</td>");
				}
				if(pageInfo2.getList().get(i).getWid()==null
						||"null".equals(pageInfo2.getList().get(i).getWid())){
					sb2.append("<td scope=\"row\"></td>");
				}else{
					sb2.append("<td scope=\"row\">"+pageInfo2.getList().get(i).getWid()+"</td>");
				}
				if(pageInfo2.getList().get(i).getSt()==null
						||"null".equals(pageInfo2.getList().get(i).getSt())){
					sb2.append("<td scope=\"row\"></td>");
				}else{
					sb2.append("<td scope=\"row\">"+pageInfo2.getList().get(i).getSt()+"</td>");
				}

				if(pageInfo2.getList().get(i).getKeypartNum()!=null){
				sb2.append("<td><a onclick=\"disassembly("+pageInfo2.getList().get(i).getId()+",2); \" href=\"javascript:;\" class=\"btn btn-default\" style=\"padding-top:2px;background-color: #d9534f;color: white;\">拆解</a></td>");
				}else{
					sb2.append("<td><a href='#' class=\"btn btn-default\" style=\"padding-top:2px;background-color: #d9534f;color: white;\">已拆解</a></td>");
				}

				sb2.append("</tr>");
			}




			PageHelper.startPage(queryPage3,8);
			//对应气密性表
			List<PMesLeakageT> leakageList = rrs.queryLeakageList(sn);
			PageInfo<PMesLeakageT> pageInfo3 = new PageInfo<>(leakageList,5);
			StringBuffer sb3 = new StringBuffer();
			for(int i=0;i<pageInfo3.getSize();i++){
				sb3.append("<tr>");

				if(GetDate.getDateforSimple(pageInfo3.getList().get(i).getDt())==null
						||"null".equals(GetDate.getDateforSimple(pageInfo3.getList().get(i).getDt()))){

					sb3.append("<td scope=\"row\"></td>");
				}else{
					sb3.append("<td scope=\"row\">"+GetDate.getDateforSimple(pageInfo3.getList().get(i).getDt())+"</td>");

				}

				if(pageInfo3.getList().get(i).getLeakageName()==null
						||"null".equals(pageInfo3.getList().get(i).getLeakageName())){

					sb3.append("<td scope=\"row\"></td>");
				}else{
					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getLeakageName()+"</td>");

				}



				if(pageInfo3.getList().get(i).getLeakagePv()==null
						||"null".equals(pageInfo3.getList().get(i).getLeakagePv())){
					sb3.append("<td scope=\"row\"></td>");
				}else{

					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getLeakagePv()+"</td>");
				}
				if(pageInfo3.getList().get(i).getLeakageLv()==null
						||"null".equals(pageInfo3.getList().get(i).getLeakageLv())){
					sb3.append("<td scope=\"row\"></td>");
				}else{

					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getLeakageLv()+"</td>");
				}
				if(pageInfo3.getList().get(i).getLeakageR()==null
						||"null".equals(pageInfo3.getList().get(i).getLeakageR())){
					sb3.append("<td scope=\"row\"></td>");
				}else{

					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getLeakageR()+"</td>");
				}
				if(pageInfo3.getList().get(i).getWid()==null
						||"null".equals(pageInfo3.getList().get(i).getWid())){
					sb3.append("<td scope=\"row\"></td>");
				}else{
					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getWid()+"</td>");
				}
				if(pageInfo3.getList().get(i).getSt()==null
						||"null".equals(pageInfo3.getList().get(i).getSt())){
					sb3.append("<td scope=\"row\"></td>");
				}else{
					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getSt()+"</td>");
				}
				if(pageInfo3.getList().get(i).getLeakageR()!=null){
					sb3.append("<td><a onclick=\"disassembly("+pageInfo3.getList().get(i).getId()+",3); \" href=\"javascript:;\" class=\"btn btn-default\" style=\"padding-top:2px;background-color: #d9534f;color: white;\">拆解</a></td>");

				}else{
					sb3.append("<td><a href='#' class=\"btn btn-default\" style=\"padding-top:2px;background-color: #d9534f;color: white;\">已拆解</a></td>");
				}
				sb3.append("</tr>");
			}
			jo.put("sn", sn);
			jo.put("sb1", sb1);
			jo.put("sb2", sb2);
			jo.put("sb3", sb3);
			jo.put("pageInfo1", pageInfo1);
			jo.put("pageInfo2", pageInfo2);
			jo.put("pageInfo3", pageInfo3);
			/*//查询产品的原因
			PMesDefectReasonT defectReason=rrs.queryProductRepairDownLine(sn);
			*/

			//根据产线id 查询工位
			List<CMesStationT> stList = rrs.queryStList(product.getLineId());
			List<ReworkWayT> reworkWayList = rrs.queryReworkStList(sn);
			List<Integer> getReworkWayListId = new ArrayList<>();


			if(stList.size()==reworkWayList.size()){
				stList.clear();

			}else if(reworkWayList.size()!=0){
				for (int j = 0; j < reworkWayList.size(); j++) {
					getReworkWayListId.add(reworkWayList.get(j).getStId());
				}
				for (int j = 0; j < stList.size(); j++) {
					for (int k = 0; k <getReworkWayListId.size(); k++) {
						 if(stList.get(j).getId()==getReworkWayListId.get(k)){
							stList.remove(j);
						}
					}
				}
			}

			jo.put("leftStList",stList);
			jo.put("reworkWayList",reworkWayList);
		}else{
			jo.put("keyFlag",false);//说明为空
		}
		return jo;

	}

	//获取初始化产品
	@RequestMapping("getDefectInItList")
	@ResponseBody
	public JSONObject getInItList(){
		JSONObject jo = new JSONObject();
		List<CMesDefectManager> getDefectManagerList = rrs.getDefectManagerList();
		List<CMesDutyManagerT> getDutyManagerList = rrs.getDutyManagerList();
		List<CMesDefectResionT> getDefectResionList = rrs.getDefectResionList();
		jo.put("defectManager", getDefectManagerList);
		jo.put("dutyManager", getDutyManagerList);
		jo.put("defectRsion", getDefectResionList);
		return jo;
	}





	//产品维修下线
	@RequestMapping("productRepairDownLine")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public JSONObject productRepairDownLine(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String sn=request.getParameter("sn");
		Integer repairDownLineDefectManager=Integer.parseInt(request.getParameter("repairDownLineDefectManager"));
		Integer repairDownLineDutyManager=Integer.parseInt(request.getParameter("repairDownLineDutyManager"));
		Integer repairDownLineReasonManager=Integer.parseInt(request.getParameter("repairDownLineReasonManager"));
		String repairDownLineReasonDetail=request.getParameter("repairDownLineReasonDetail");

			//插入数据
			rrs.addDefectReason(sn,new Date(),"1",repairDownLineDefectManager,repairDownLineDutyManager,repairDownLineReasonManager,repairDownLineReasonDetail);

			//获取这个产品再运行表的信息
			RMesTrackingT rt=rrs.getRTracking(sn);
			//把产品运行表的信息插入到产品下线表中
			rrs.addPTracking(rt.getDt(),rt.getSt(),rt.getBst(),rt.getSn(),"1",rt.getGearboxsn(),
					rt.getTypename(),rt.getTraynum(),rt.getProductnum(),"OK",rt.getDis(),rt.getPlanId(),
					rt.getReworkFlag(),rt.getProductionId(),rt.getLineId());
			//删除产品运行表的信息
			rrs.deleteRTracking(sn);
			jo.put("flag",true);
			return jo;
	}

	//产品报废
	@RequestMapping("productScrap")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public JSONObject productScrap(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String sn=request.getParameter("sn");
		Integer scrapDefectManager=Integer.parseInt(request.getParameter("scrapDefectManager"));
		Integer scrapDutyManager=Integer.parseInt(request.getParameter("scrapDutyManager"));
		Integer scrapReasonManager=Integer.parseInt(request.getParameter("scrapReasonManager"));
		String scrapReasonDetail=request.getParameter("scrapReasonDetail");
		//插入数据
		rrs.addDefectReason(sn,new Date(),"2",scrapDefectManager,scrapDutyManager,scrapReasonManager,scrapReasonDetail);
		//获取这个产品再运行表的信息
		RMesTrackingT rt=rrs.getRTracking(sn);
		//把产品运行表的信息插入到产品下线表中
		rrs.addPTracking(rt.getDt(),rt.getSt(),rt.getBst(),rt.getSn(),"2",rt.getGearboxsn(),
				rt.getTypename(),rt.getTraynum(),rt.getProductnum(),"NG",rt.getDis(),rt.getPlanId(),
				rt.getReworkFlag(),rt.getProductionId(),rt.getLineId());
		//删除产品运行表的信息
		rrs.deleteRTracking(sn);
		jo.put("flag", true);
		return jo;
	}


	//返修路径
	@RequestMapping("productRepair")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public JSONObject productRepair(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String sn =request.getParameter("sn");
		Integer repairDefectManager = Integer.parseInt(request.getParameter("repairDefectManager"));
		Integer repairDutyManager = Integer.parseInt(request.getParameter("repairDutyManager"));
		Integer repairReasonManager = Integer.parseInt(request.getParameter("repairReasonManager"));
		String repairReasonDetail = request.getParameter("repairReasonDetail");
		String menuIds=request.getParameter("menuIds");
		rrs.removeBackWay(sn);

		String[] getRework = menuIds.split(";");
		for (int i = 0; i < getRework.length; i++) {
			String[] getReworkWay = getRework[i].split(",");
			rrs.addReworkWay(new Date(),sn,getReworkWay[1],Integer.parseInt(getReworkWay[0]),(i+1));

		}
		//插入原因
		rrs.addDefectReason(sn,new Date(),"3",repairDefectManager,repairDutyManager,repairReasonManager,repairReasonDetail);
		//将这个sn的运行表数据改为返工
		rrs.updatRTracking(sn);
		jo.put("flag", true);
		return jo;
	}
	@RequestMapping("queryDefectSNManager")
	@ResponseBody
	public JSONObject queryDefectSNManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		JSONObject jo = new JSONObject();
		String getPage=request.getParameter("queryPage");//查询第几页
		String sn=request.getParameter("sn");//总成
		String getStartTime=request.getParameter("getStartTime");//开始时间
		String getEndTime=request.getParameter("getEndTime");//结束时间
		String getLine=request.getParameter("getLine");//产线
		Integer queryPage = 1;
		if(getPage==""||getPage==null){
			queryPage = 1;
		}else{
			queryPage = Integer.parseInt(getPage);
		}

		PageHelper.startPage(queryPage,10);
		List<PMesDefectReasonT> getDefectList=rrs.queryDefectResionList(sn,getStartTime,getEndTime,getLine);
		PageInfo<PMesDefectReasonT> pageInfo = new PageInfo<>(getDefectList,8);
		jo.put("pageInfo",pageInfo);
		request.setAttribute("pageInfo", pageInfo);
		List<PMesDefectReasonT> getList = pageInfo.getList();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <getList.size(); i++) {
			sb.append("<tr>");
			sb.append("<td>"+getList.get(i).getSn()+"</td>");
			if(getList.get(i).getDt()!=null){
				sb.append("<td>"+GetDate.getDateforSimple(getList.get(i).getDt())+"</td>");

			}
			if("1".equals(getList.get(i).getDefectType())){
				sb.append("<td>维修下线</td>");
			}else if("2".equals(getList.get(i).getDefectType())){
				sb.append("<td>报废产品</td>");
			}else{
				sb.append("<td></td>");
			}
			if(getList.get(i).getDefectName()!=null&&getList.get(i).getDefectName()!=""){
				sb.append("<td>"+getList.get(i).getDefectName()+"</td>");

			}else{
				sb.append("<td></td>");
			}
			if(getList.get(i).getDutyName()!=null&&getList.get(i).getDutyName()!=""){
				sb.append("<td>"+getList.get(i).getDutyName()+"</td>");
			}else{
				sb.append("<td></td>");
			}
			if(getList.get(i).getResionDis()!=null&&getList.get(i).getResionDis()!=""){
				sb.append("<td>"+getList.get(i).getResionDis()+"</td>");
			}else{
				sb.append("<td></td>");
			}
			sb.append("<td>"+getList.get(i).getReasonDetail()+"</td>");
			sb.append("</tr>");
		}
		jo.put("sb",sb);
		return jo;
	}


}

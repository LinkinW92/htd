//package com.skeqi.mes.controller.yin;
//
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.skeqi.mes.pojo.CMesEmpTeamT;
//import com.skeqi.mes.pojo.CMesLineT;
//import com.skeqi.mes.pojo.CMesProductionRecipeT;
//import com.skeqi.mes.pojo.CMesProductionT;
//import com.skeqi.mes.pojo.CMesReasonsBolt;
//import com.skeqi.mes.pojo.CMesReasonsKeypart;
//import com.skeqi.mes.pojo.CMesReasonsLeakage;
//import com.skeqi.mes.pojo.CMesRecipeT;
//import com.skeqi.mes.pojo.CMesRecipeTypeT;
//import com.skeqi.mes.pojo.CMesRecipeVersion;
//import com.skeqi.mes.pojo.CMesRecipeVersionDetail;
//import com.skeqi.mes.pojo.CMesStationT;
//import com.skeqi.mes.pojo.CMesXXT;
//import com.skeqi.mes.pojo.PChargedischarge;
//import com.skeqi.mes.pojo.PMesBoltT;
//import com.skeqi.mes.pojo.PMesDischargeT;
//import com.skeqi.mes.pojo.PMesEolT;
//import com.skeqi.mes.pojo.PMesKeypartT;
//import com.skeqi.mes.pojo.PMesLeakageT;
//import com.skeqi.mes.pojo.PTrackingT;
//import com.skeqi.mes.pojo.RAsmElectricDetection;
//import com.skeqi.mes.pojo.RAsmEol;
//import com.skeqi.mes.pojo.RMesBolt;
//import com.skeqi.mes.pojo.RMesKeypart;
//import com.skeqi.mes.pojo.RMesLeakage;
//import com.skeqi.mes.pojo.RTrackingT;
//import com.skeqi.mes.pojo.RUploadDataT;
//import com.skeqi.mes.service.fqz.RecipeVersionService;
//import com.skeqi.mes.service.yin.BomService;
//import com.skeqi.mes.service.yin.ProductionService;
//import com.skeqi.mes.service.yin.ReportService;
//import com.skeqi.mes.service.yin.UsersService;
//import com.skeqi.mes.util.ToolUtils;
//
//
//
//@SuppressWarnings("deprecation")
//@Controller
//@Transactional
//@RequestMapping("skq")
//public class ReportController {
//	@Autowired
//	ReportService reportService;
//	@Autowired
//	UsersService usersService;
//	@Autowired
//	BomService bomService;
//	@Autowired
//	RecipeVersionService recipeversionService;
//	@Autowired
//	ProductionService productionService;
//
//	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//	/**
//	 * 电子报表列表
//	 */
//	@RequestMapping("electronicReport")
//	public String electronicReport(HttpServletRequest request,
//			@RequestParam(required = false,defaultValue = "1",value = "page")Integer page,
//			@RequestParam(required = false,defaultValue = "1",value = "page2")Integer page2,
//			@RequestParam(required = false,defaultValue = "1",value = "page3")Integer page3,
//			@RequestParam(required = false,defaultValue = "1",value = "page4")Integer page4,
//			@RequestParam(required = false,defaultValue = "1",value = "page5")Integer page5,
//			@RequestParam(required = false,defaultValue = "1",value = "page6")Integer page6,
//			HttpServletResponse response)throws Exception {
//		Map<String, Object> map = new HashMap<>();
//		String sn = request.getParameter("sn");
//		if (sn==null) {
//			sn="";
//		}
///*		String materialName = request.getParameter("materialName");
//		if(materialName!=null && materialName!=""){
//			String sn2 ="";
//			sn2= reportService.getSn(materialName);
//			if(sn2==null || sn2==""){
//				sn2 = reportService.getPSn(materialName);
//			}
//			if(sn==""){
//				sn=sn2;
//			}else{
//				if(!sn.equals(sn2)){
//					sn="";
//				}
//			}
//		}*/
//		map.put("sn", sn);
//		map.put("status", 0);
//		List<PTrackingT> ptrackingList = reportService.ptrackingList(map);
//		if (ptrackingList.size()>0) {
//			request.setAttribute("startDate",ptrackingList.get(0).getDt());//上线时间
//			request.setAttribute("endDate", ptrackingList.get(0).getOfflineDt());//下线时间
//			request.setAttribute("typeName",ptrackingList.get(0).getProductionName());//产品类型
//		}else {
//			List<RTrackingT> rtrackingList = reportService.rtrackingList(map);
//			if (rtrackingList.size()>0) {
//				request.setAttribute("startDate",rtrackingList.get(0).getDT());//上线时间
//				request.setAttribute("typeName",rtrackingList.get(0).getProductionName());//产品类型
//			}
//		}
//
//		PageHelper.startPage(page3,10);
//		List<PMesLeakageT> leakageList = reportService.leakageList(map);
//		if(leakageList.size()>0){
//			//气密性信息
//			PageInfo<PMesLeakageT> pageInfo3 = new PageInfo<>(leakageList,5);
//			request.setAttribute("pageInfo3",pageInfo3);
//		}else{
//			//气密性信息
//			PageHelper.startPage(page3,10);
//			List<RMesLeakage> rleakageList = reportService.rleakageList(map);
//			PageInfo<RMesLeakage> pageInfo4 = new PageInfo<RMesLeakage>(rleakageList,5);
//			request.setAttribute("pageInfo3",pageInfo4);
//		}
//
//		PageHelper.startPage(page2,10);
//		List<PMesKeypartT> keypartList = reportService.keypartList(map);
//		if(keypartList.size()>0){
//			//物料信息
//			PageInfo<PMesKeypartT> pageInfo2 = new PageInfo<>(keypartList,5);
//			request.setAttribute("pageInfo2", pageInfo2);
//		}else{
//			//物料信息
//			PageHelper.startPage(page2,10);
//			List<RMesKeypart> rkeypartList = reportService.rkeypartList(map);
//			PageInfo<RMesKeypart> pageInfo5 = new PageInfo<RMesKeypart>(rkeypartList,5);
//			request.setAttribute("pageInfo2",pageInfo5);
//		}
//
//		PageHelper.startPage(page,10);
//		List<PMesBoltT> boltList = reportService.boltList(map);
//		if(boltList.size()>0){
//			//螺栓信息
//			PageInfo<PMesBoltT> pageInfo = new PageInfo<>(boltList,5);
//			request.setAttribute("pageInfo", pageInfo);
//
//		}else{
//			//螺栓信息
//			PageHelper.startPage(page,10);
//			List<RMesBolt> rboltList = reportService.rboltList(map);
//			PageInfo<RMesBolt> pageInfos = new PageInfo<RMesBolt>(rboltList,5);
//			request.setAttribute("pageInfo", pageInfos);
//		}
//
//
//		//产线电检测
//		PageHelper.startPage(page4,10);
//		List<RAsmElectricDetection> rAsmElectricDetectionList = reportService.rAsmElectricDetectionList(map);
//		PageInfo<RAsmElectricDetection> pageInfo4 = new PageInfo<>(rAsmElectricDetectionList,5);
//
//		//充放电检测
//		PageHelper.startPage(page5,10);
//		List<PMesDischargeT> findAllDischargeT = reportService.findAllDischargeT(sn);
//		PageInfo<PMesDischargeT> pageInfo5 = new PageInfo<>(findAllDischargeT,5);
//		//EOL
//		PageHelper.startPage(page6,10);
//		List<PMesEolT> findAllEol = reportService.findAllEol(sn);
//		PageInfo<PMesEolT> pageInfo6 = new PageInfo<>(findAllEol,5);
//
//
//		request.setAttribute("sn", sn);
///*		request.setAttribute("materialName", materialName);*/
//		request.setAttribute("pageInfo4", pageInfo4);
//		request.setAttribute("pageInfo5", pageInfo5);
//		request.setAttribute("pageInfo6", pageInfo6);
//		return "report_control/electronicReport";
//	}
////	/**
////	 * 数据拆解
////	 */
////	@RequestMapping(""
////			+ ""
////			+ ""
////			+ "")
////	public String dataDismantling(HttpServletRequest request,
////			@RequestParam(required = false,defaultValue = "1",value = "page")Integer page,
////			@RequestParam(required = false,defaultValue = "1",value = "page2")Integer page2,
////			@RequestParam(required = false,defaultValue = "1",value = "page3")Integer page3,
////			HttpServletResponse response)throws Exception {
////		Map<String, Object> map = new HashMap<>();
////		String sn = request.getParameter("sn");
////		if (sn==null) {
////			sn="";
////		}
////		map.put("sn", sn);
////		//		List<CMesXXT> xxtList = reportService.getXXT(map);
////		//		if (xxtList.size()>0) {
////		//			map.put("sn", xxtList.get(0).getSn());
////		//		}
////		map.put("status", 0);
////		List<PTrackingT> ptrackingList = reportService.ptrackingList(map);
////		if (ptrackingList.size()>0) {
////			request.setAttribute("startDate",ptrackingList.get(0).getDt());//上线时间
////			request.setAttribute("endDate", ptrackingList.get(0).getOfflineDt());//下线时间
////			request.setAttribute("typeName",ptrackingList.get(0).getTypename());//产品类型
////		}else {
////			List<RTrackingT> rtrackingList = reportService.rtrackingList(map);
////			if (rtrackingList.size()>0) {
////				request.setAttribute("startDate",rtrackingList.get(0).getDT());//上线时间
////				request.setAttribute("typeName",rtrackingList.get(0).getTypeName());//产品类型
////			}
////		}
////		PageHelper.startPage(page,8);
////		List<RMesBolt> boltListR = reportService.boltListR(map);
////		PageInfo<RMesBolt> pageInfo = new PageInfo<>(boltListR,5);
////
////		PageHelper.startPage(page2,8);
////		List<RMesKeypart> keypartListR = reportService.keypartListR(map);
////		PageInfo<RMesKeypart> pageInfo2 = new PageInfo<>(keypartListR,5);
////
////		PageHelper.startPage(page3,8);
////		List<RMesLeakage> leakageListR = reportService.leakageListR(map);
////		PageInfo<RMesLeakage> pageInfo3 = new PageInfo<>(leakageListR,5);
////		request.setAttribute("sn", sn);
////		request.setAttribute("pageInfo", pageInfo);
////		request.setAttribute("pageInfo2", pageInfo2);
////		request.setAttribute("pageInfo3", pageInfo3);
////		return "deviceManager_control/dataDismantling";
////	}
////
////	/**
////	 * 拆解数据（修改bolt、keypart、leakage状态）
////	 */
////	@RequestMapping("relieveData")
////	@Transactional
////	public @ResponseBody Map<String, Object> relieveData(HttpServletRequest request){
////		String id = request.getParameter("id");
////		String mode = request.getParameter("mode");
////		String sn = request.getParameter("sn");
////		String reasons = request.getParameter("reasons");
////		String account = request.getParameter("account");
////		//		String endTime = request.getParameter("endTime");
////		Map<String, Object> map = new HashMap<>();
////		map.put("status", "0");
////		map.put("id", id);
////		map.put("msgId", id);
////		map.put("reasons", reasons);
////		map.put("flag", mode);
////		map.put("account", account);
////		try {
////			if ("1".equals(mode)) {
////				usersService.upbolt(map);
////				/*List<RMesBolt> getBoltById = usersService.getBoltById(map);
////				RMesBolt bolt = getBoltById.get(0);
////				CMesReasonsBolt reasonsBolt = new CMesReasonsBolt(0, bolt.getDt(), bolt.getTransfer(), bolt.getBoltMode(), bolt.getSn(), bolt.getSt(), bolt.getT(), bolt.getA(), bolt.getP(), bolt.getC(), bolt.getR(), bolt.getEqs(), bolt.gettLimit(), bolt.getaLimit(), bolt.getWid(), bolt.getTid(), bolt.getBoltNum(), bolt.getBoltName(), bolt.getReworkFlag(), bolt.getReworkSt(), bolt.getGunNo(), reasons);
////				Date dt = getBoltById.get(0).getDt();
////				String boltName = getBoltById.get(0).getBoltName();
////				String st = getBoltById.get(0).getSt();
////				map.put("dt", dt);
////				map.put("boltName", boltName);
////				map.put("st", st);
////				map.put("sn", sn);
////				map.put("boltId",getBoltById.get(0).getId());
////				usersService.addBolt(map);
////				usersService.delBolt(map);
////				map.put("reasonsBolt", reasonsBolt);
////				usersService.addToReasonsBolt(map);*/
////			}else if (mode.equals("2")) {
////				/*List<RMesKeypart> getKeypartById = usersService.getKeypartById(map);
////				RMesKeypart keyPart = getKeypartById.get(0);
////				CMesReasonsKeypart reasonsKeypart = new CMesReasonsKeypart(0, reasons, keyPart.getDt(), keyPart.getTransfer(), keyPart.getKeypartMode(), keyPart.getSt(), keyPart.getSn(), keyPart.getWid(), keyPart.getTid(), keyPart.getSecondNum(), keyPart.getKeypartId(), keyPart.getKeypartName(), keyPart.getKeypartPn(), keyPart.getKeypartNum(), keyPart.getKeypartModule(), keyPart.getKeypartReworkFg(), keyPart.getKeypartReworkSt(), keyPart.getMaterialId(), keyPart.getMaterialNumber());
////				map.put("dt", getKeypartById.get(0).getDt());
////				map.put("keypartName", getKeypartById.get(0).getKeypartName());
////				map.put("st", getKeypartById.get(0).getSt());
////				map.put("sn", getKeypartById.get(0).getSn());
////				map.put("keyPartId", getKeypartById.get(0).getId());
////				map.put("reasonsKeypart", reasonsKeypart);
////				usersService.addKeypart(map);
////				usersService.delKeypart(map);
////				usersService.addToReasonsKeypart(map);*/
////				usersService.upkeypart(map);
////			}else if (mode.equals("3")) {
////				/*List<RMesLeakage> getLeakageById = usersService.getLeakageById(map);
////				RMesLeakage leakage = getLeakageById.get(0);
////				CMesReasonsLeakage reasonsLeakage = new CMesReasonsLeakage(0, leakage.getDt(), leakage.getSt(), leakage.getSn(), leakage.getLeakageName(), leakage.getLeakagePv(), leakage.getLeakageLv(), leakage.getLeakageR(), leakage.getWid(), leakage.getTransfer(), leakage.getLeakageMode(), leakage.getReworkFlag(), reasons);
////				map.put("dt", getLeakageById.get(0).getDt());
////				map.put("leakageName", getLeakageById.get(0).getLeakageName());
////				map.put("st", getLeakageById.get(0).getSt());
////				map.put("sn", getLeakageById.get(0).getSn());
////				map.put("leakageId", getLeakageById.get(0).getId());
////				map.put("reasonsLeakage", reasonsLeakage);
////				usersService.addLeakage(map);
////				usersService.delLeakage(map);
////				usersService.addToReasonsLeakage(map);
////				*/
////				usersService.upleakage(map);
////			}
////			map.put("msg", 0);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		return map;
////	}
//	public boolean export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,String filename,Integer flag){
//		OutputStream os =null;
//		try {
//			//清空buffer,设置页面不缓存
//			response.reset();
//			os = response.getOutputStream();
//			response.setCharacterEncoding("utf-8");
//			response.reset();
//			response.setContentType("application/x-msdownload");
//			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "utf-8"));
//			// 导出当前页 、选中项
//			if (flag==0) {
//				excelout(os, map);
//			}else if (flag==1) {
//				exceloutRecipeDatil(os,map);
//			}
//			os.flush();
//			os.close();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//			return false;
//		}finally {
//			try {
//				if(null!=os){
//					os.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				ToolUtils.errorLog(this, e, request);
//			}
//		}
//	}
//	/**
//	 * Excel样式格式
//	 */
//	@SuppressWarnings("unchecked")
//	public Object excelout(OutputStream os,Map<String, Object> map) throws Exception{
//		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd  HH:mm:ss" );
//		int boltSize = 0;
//		int leakageSize = 0;
//		int keypartSize = 0;
//		List<PTrackingT> ptrackingLists = reportService.ptrackingList(map);
//		if(ptrackingLists.size()>0){
//			List<PMesKeypartT> keypartList = reportService.keypartList(map);
//			List<PMesBoltT> boltList = reportService.boltList(map);
//			List<PMesLeakageT> leakageList = reportService.leakageList(map);
//			 boltSize = boltList.size();
//			 leakageSize = leakageList.size();
//			 keypartSize = keypartList.size();
//		}else{
//			List<RMesBolt> boltList = reportService.boltListR(map);
//			List<RMesKeypart> keypartList = reportService.keypartListR(map);
//			List<RMesLeakage> leakageList = reportService.leakageListR(map);
//			boltSize = boltList.size();
//			leakageSize = leakageList.size();
//			keypartSize = keypartList.size();
//		}
//
//		List<RAsmElectricDetection> rAsmElectricDetectionList = (List<RAsmElectricDetection>) map.get("rAsmElectricDetectionList");
//		List<PMesDischargeT> pChargedischargeList = (List<PMesDischargeT>) map.get("discharge");
//		List<PMesEolT> rAsmEolList = (List<PMesEolT>) map.get("rAsmEolList");
//
//		WritableWorkbook wwb = Workbook.createWorkbook(os);// 创建可写工作薄
//		WritableSheet ws = wwb.createSheet("总成信息", 0);
//		WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
//				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
//		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 18, WritableFont.NO_BOLD, false,
//				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
//		WritableCellFormat cellFormat2 = new WritableCellFormat(font);
//		cellFormat2.setBackground(Colour.AQUA);
//		cellFormat2.setWrap(false);
//		cellFormat2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.MEDIUM, jxl.format.Colour.DARK_BLUE);
//		cellFormat2.setAlignment(Alignment.CENTRE);
//		WritableCellFormat cellFormat = new WritableCellFormat(font);
//		cellFormat.setWrap(false);
//		cellFormat.setAlignment(Alignment.CENTRE);
//		cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.DARK_BLUE);
//		WritableCellFormat cellFormat3 = new WritableCellFormat(font2);
//		cellFormat3.setWrap(false);
//		cellFormat3.setAlignment(Alignment.CENTRE);
//		cellFormat3.setBackground(Colour.GREEN);
//		cellFormat3.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.DARK_BLUE);
//
//		WritableCellFormat cellFormat4 = new WritableCellFormat(font2);
//		cellFormat4.setWrap(false);
//		cellFormat4.setBackground(Colour.YELLOW);
//		cellFormat4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.DARK_BLUE);
//
//		int rAsmElectricDetectionSize = rAsmElectricDetectionList.size();
//		int pChargedischargeSize = pChargedischargeList.size();
//		WritableCellFormat cellFormat5 = new WritableCellFormat(font2);
//		cellFormat5.setWrap(false);
//		cellFormat5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.DARK_BLUE);
//
//		ws.addCell(new Label(0, 5, "气密性信息", cellFormat3));
//		ws.mergeCells(0, 5, 7, 5); // 合并单元格
//
//		ws.addCell(new Label(0, 8+leakageSize, "螺栓信息", cellFormat3));
//		ws.mergeCells(0, 8+leakageSize, 7, 8+leakageSize); // 合并单元格
//
//		ws.addCell(new Label(0, 11+leakageSize+boltSize, "物料信息", cellFormat3));
//		ws.mergeCells(0, 11+leakageSize+boltSize, 5, 11+leakageSize+boltSize); // 合并单元格
//
//
//		ws.addCell(new Label(0, 14+leakageSize+boltSize+keypartSize, "产线电解测", cellFormat3));
//		ws.mergeCells(0, 14+leakageSize+boltSize+keypartSize, 19, 14+leakageSize+boltSize+keypartSize); // 合并单元格
//
//		ws.addCell(new Label(0, 17+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "充放电检测", cellFormat3));
//		ws.mergeCells(0, 17+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, 20, 17+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize); // 合并单元格
//
//		ws.addCell(new Label(0, 20+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, "绝缘耐压", cellFormat3));
//		ws.mergeCells(0, 20+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, 7, 20+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize); // 合并单元格
//
//
//		ws.setColumnView(0, 20);
//		ws.setColumnView(1, 20);
//		ws.setColumnView(2, 20);
//		ws.setColumnView(3, 20);
//		ws.setColumnView(4, 20);
//		ws.setColumnView(5, 20);
//		ws.setColumnView(6, 20);
//		ws.setColumnView(7, 20);
//		ws.setColumnView(8, 20);
//		ws.setColumnView(9, 20);
//		ws.setColumnView(10, 20);
//		ws.setColumnView(11, 20);
//		ws.setColumnView(12, 20);
//		ws.setColumnView(13, 20);
//		ws.setColumnView(14, 20);
//		ws.setColumnView(15, 20);
//		ws.setColumnView(16, 20);
//		ws.setColumnView(17, 20);
//		ws.setColumnView(18, 20);
//		ws.setColumnView(19, 20);
//		ws.setColumnView(20, 20);
//		ws.addCell(new Label(0, 6, "编号", cellFormat2));
//		ws.addCell(new Label(1, 6, "日期", cellFormat2));
//		ws.addCell(new Label(2, 6, "名称", cellFormat2));
//		ws.addCell(new Label(3, 6, "气压值", cellFormat2));
//		ws.addCell(new Label(4, 6, "泄露值", cellFormat2));
//		ws.addCell(new Label(5, 6, "结果", cellFormat2));
//		ws.addCell(new Label(6, 6, "员工号", cellFormat2));
//		ws.addCell(new Label(7, 6, "工位", cellFormat2));
//
//
//		ws.addCell(new Label(0, 9+leakageSize, "编号", cellFormat2));
//		ws.addCell(new Label(1, 9+leakageSize, "日期", cellFormat2));
//		ws.addCell(new Label(2, 9+leakageSize, "名称", cellFormat2));
//		ws.addCell(new Label(3, 9+leakageSize, "角度值°", cellFormat2));
//		ws.addCell(new Label(4, 9+leakageSize, "扭矩值N/M", cellFormat2));
//		ws.addCell(new Label(5, 9+leakageSize, "结果", cellFormat2));
//		ws.addCell(new Label(6, 9+leakageSize, "员工号", cellFormat2));
//		ws.addCell(new Label(7, 9+leakageSize, "工位", cellFormat2));
//
//		ws.addCell(new Label(0, 12+leakageSize+boltSize, "编号", cellFormat2));
//		ws.addCell(new Label(1, 12+leakageSize+boltSize, "日期", cellFormat2));
//		ws.addCell(new Label(2, 12+leakageSize+boltSize, "名称", cellFormat2));
//		ws.addCell(new Label(3, 12+leakageSize+boltSize, "批次号", cellFormat2));
//		ws.addCell(new Label(4, 12+leakageSize+boltSize, "员工号", cellFormat2));
//		ws.addCell(new Label(5, 12+leakageSize+boltSize, "工位", cellFormat2));
//
//
//		ws.addCell(new Label(0, 15+leakageSize+boltSize+keypartSize, "编号", cellFormat2));
//		ws.addCell(new Label(1, 15+leakageSize+boltSize+keypartSize, "日期", cellFormat2));
//		ws.addCell(new Label(2, 15+leakageSize+boltSize+keypartSize, "程序烧录结果", cellFormat2));
//		ws.addCell(new Label(3, 15+leakageSize+boltSize+keypartSize, "通讯检测结果", cellFormat2));
//		ws.addCell(new Label(4, 15+leakageSize+boltSize+keypartSize, "总电压值检测结果", cellFormat2));
//		ws.addCell(new Label(5, 15+leakageSize+boltSize+keypartSize, "单体电压总和", cellFormat2));
//		ws.addCell(new Label(6, 15+leakageSize+boltSize+keypartSize, "单体电压检测", cellFormat2));
//		ws.addCell(new Label(7, 15+leakageSize+boltSize+keypartSize, "单体电压最大值", cellFormat2));
//		ws.addCell(new Label(8, 15+leakageSize+boltSize+keypartSize, "单体电压最小值", cellFormat2));
//		ws.addCell(new Label(9, 15+leakageSize+boltSize+keypartSize, "最大单体电压差", cellFormat2));
//		ws.addCell(new Label(10, 15+leakageSize+boltSize+keypartSize, "单体温度检测", cellFormat2));
//		ws.addCell(new Label(11, 15+leakageSize+boltSize+keypartSize, "最大单体温度", cellFormat2));
//		ws.addCell(new Label(12, 15+leakageSize+boltSize+keypartSize, "最小单体温度", cellFormat2));
//		ws.addCell(new Label(13, 15+leakageSize+boltSize+keypartSize, "最大单体温度差", cellFormat2));
//		ws.addCell(new Label(14, 15+leakageSize+boltSize+keypartSize, "总正总负继电器检测", cellFormat2));
//		ws.addCell(new Label(15, 15+leakageSize+boltSize+keypartSize, "预充继电器检测", cellFormat2));
//		ws.addCell(new Label(16, 15+leakageSize+boltSize+keypartSize, "慢充继电器检测", cellFormat2));
//		ws.addCell(new Label(17, 15+leakageSize+boltSize+keypartSize, "高压通路导通测试", cellFormat2));
//		ws.addCell(new Label(18, 15+leakageSize+boltSize+keypartSize, "保险丝状态检测", cellFormat2));
//		ws.addCell(new Label(19, 15+leakageSize+boltSize+keypartSize, "高压板检测", cellFormat2));
//
//		ws.addCell(new Label(0, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "编号", cellFormat2));
//		ws.addCell(new Label(1, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "SN", cellFormat2));
//		ws.addCell(new Label(2, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "mcNum", cellFormat2));
//		ws.addCell(new Label(3, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "工位", cellFormat2));
//		ws.addCell(new Label(4, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "调试员工", cellFormat2));
//		ws.addCell(new Label(5, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "步序", cellFormat2));
//		ws.addCell(new Label(6, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "测试项", cellFormat2));
//		ws.addCell(new Label(7, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "测试值", cellFormat2));
//		ws.addCell(new Label(8, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "单位", cellFormat2));
//		ws.addCell(new Label(9, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "结果", cellFormat2));
//		ws.addCell(new Label(10, 18+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, "时间", cellFormat2));
//
//		ws.addCell(new Label(0, 21+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, "编号", cellFormat2));
//		ws.addCell(new Label(1, 21+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, "日期", cellFormat2));
//		ws.addCell(new Label(2, 21+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, "开路电压测试", cellFormat2));
//		ws.addCell(new Label(3, 21+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, "正极对壳体绝缘阻抗测试", cellFormat2));
//		ws.addCell(new Label(4, 21+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, "负极对壳体绝缘阻抗测试", cellFormat2));
//		ws.addCell(new Label(5, 21+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, "正极对壳体直流耐压测试", cellFormat2));
//		ws.addCell(new Label(6, 21+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, "负极对壳体直流耐压测试", cellFormat2));
//		ws.addCell(new Label(7, 21+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, "结果", cellFormat2));
//
//
//		List<PTrackingT> ptrackingList = reportService.ptrackingList(map);
//		List<RTrackingT> rtrackingList = reportService.rtrackingList(map);
//		String typeName = "";
//		String sn = "";
//		Date startDate = null;
//		Date endDate = null;
//		String endDate2 = "";
//		if (ptrackingList.size()>0) {
//			sn = ptrackingList.get(0).getSn();
//			typeName = ptrackingList.get(0).getProductionName();
//			startDate = ptrackingList.get(0).getDt();
//			endDate = ptrackingList.get(0).getOfflineDt();
//		}else if(rtrackingList.size()>0){
//			sn = rtrackingList.get(0).getSN();
//			typeName = rtrackingList.get(0).getProductionName();
//			startDate = rtrackingList.get(0).getDT();
//		}
//		if (endDate!=null) {
//			endDate2 = sdf.format(endDate);
//		}
//		ws.addCell(new Label(0, 0, "总成号：", cellFormat4));
//		ws.addCell(new Label(1, 0, sn, cellFormat4));
//		ws.mergeCells(1, 0, 20, 0); // 合并单元格
//
//		ws.addCell(new Label(0, 1, "产品名称：", cellFormat4));
//		ws.addCell(new Label(1, 1, typeName, cellFormat4));
//		ws.mergeCells(1, 1, 20, 1); // 合并单元格
//
//		ws.addCell(new Label(0, 2, "上线时间：", cellFormat4));
//		ws.addCell(new Label(1, 2, null, cellFormat4));
//		ws.mergeCells(1, 2, 20, 2); // 合并单元格
//
//		ws.addCell(new Label(0, 3, "下线时间：", cellFormat4));
//		ws.addCell(new Label(1, 3, null, cellFormat4));
//		ws.mergeCells(1, 3, 20, 3); // 合并单元格
//
//		ws.setRowView(4, 800);
//		ws.setRowView(7+leakageSize, 800);
//		ws.setRowView(10+leakageSize+boltSize, 800);
//		ws.setRowView(13+leakageSize+boltSize+keypartSize, 800);
//		ws.setRowView(16+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, 800);
//		ws.setRowView(19+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, 800);
//		ws.mergeCells(0, 4, 10, 4); // 合并单元格
//		ws.mergeCells(0, 7+leakageSize, 20, 7+leakageSize); // 合并单元格
//		ws.mergeCells(0, 10+leakageSize+boltSize, 20, 10+leakageSize+boltSize); // 合并单元格
//		ws.mergeCells(0, 13+leakageSize+boltSize+keypartSize, 20, 13+leakageSize+boltSize+keypartSize); // 合并单元格
//		ws.mergeCells(0, 16+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize, 20, 16+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize); // 合并单元格
//		ws.mergeCells(0, 19+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, 20, 19+leakageSize+boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize); // 合并单元格
//
//		if(ptrackingLists.size()>0){
//			List<PMesKeypartT> keypartList = reportService.keypartList(map);
//			List<PMesBoltT> boltList = reportService.boltList(map);
//			List<PMesLeakageT> leakageList = reportService.leakageList(map);
//			for (int i = 0; i < leakageList.size(); i++) {
//				ws.addCell(new Label(0, 7 + i, 1+i+"", cellFormat));
//				ws.addCell(new Label(1, 7 + i, sdf.format(leakageList.get(i).getDt()), cellFormat));
//				ws.addCell(new Label(2, 7 + i, leakageList.get(i).getLeakageName(),cellFormat));
//				ws.addCell(new Label(3, 7 + i, leakageList.get(i).getLeakageLv(),cellFormat));
//				ws.addCell(new Label(4, 7 + i, leakageList.get(i).getLeakagePv(),cellFormat));
//				ws.addCell(new Label(5, 7 + i, leakageList.get(i).getLeakageR(), cellFormat));
//				ws.addCell(new Label(6, 7 + i, leakageList.get(i).getWid(), cellFormat));
//				ws.addCell(new Label(7, 7 + i, leakageList.get(i).getSt(), cellFormat));
//			}
//			for (int i = 0; i < boltList.size(); i++) {
//				ws.addCell(new Label(0, 10 + i+leakageSize, 1+i+"", cellFormat));
//				ws.addCell(new Label(1, 10 + i+leakageSize, sdf.format(boltList.get(i).getDt()), cellFormat));
//				ws.addCell(new Label(2, 10 + i+leakageSize, boltList.get(i).getBoltName(),cellFormat));
//				ws.addCell(new Label(3, 10 + i+leakageSize, boltList.get(i).getA(),cellFormat));
//				ws.addCell(new Label(4, 10 + i+leakageSize, boltList.get(i).getT(), cellFormat));
//				ws.addCell(new Label(5, 10 + i+leakageSize, boltList.get(i).getR(), cellFormat));
//				ws.addCell(new Label(6, 10 + i+leakageSize, boltList.get(i).getWid(), cellFormat));
//				ws.addCell(new Label(7, 10 + i+leakageSize, boltList.get(i).getSt(), cellFormat));
//			}
//
//			for (int i = 0; i < keypartList.size(); i++) {
//				ws.addCell(new Label(0, 13 + i+leakageSize + boltSize, 1+i+"", cellFormat));
//				ws.addCell(new Label(1, 13 + i+leakageSize + boltSize, sdf.format(keypartList.get(i).getDt()), cellFormat));
//				ws.addCell(new Label(2, 13 + i+leakageSize + boltSize, keypartList.get(i).getKeypartName(),cellFormat));
//				ws.addCell(new Label(3, 13 + i+leakageSize + boltSize, keypartList.get(i).getKeypartNum(),cellFormat));
//				ws.addCell(new Label(4, 13 + i+leakageSize + boltSize, keypartList.get(i).getWid(), cellFormat));
//				ws.addCell(new Label(5, 13 + i+leakageSize + boltSize, keypartList.get(i).getSt(), cellFormat));
//			}
//
//		}else{
//			List<RMesBolt> boltList = reportService.boltListR(map);
//			List<RMesKeypart> keypartList = reportService.keypartListR(map);
//			List<RMesLeakage> leakageList = reportService.leakageListR(map);
//			for (int i = 0; i < leakageList.size(); i++) {
//				ws.addCell(new Label(0, 7 + i, 1+i+"", cellFormat));
//				ws.addCell(new Label(1, 7 + i, sdf.format(leakageList.get(i).getDt()), cellFormat));
//				ws.addCell(new Label(2, 7 + i, leakageList.get(i).getLeakageName(),cellFormat));
//				ws.addCell(new Label(3, 7 + i, leakageList.get(i).getLeakageLv(),cellFormat));
//				ws.addCell(new Label(4, 7 + i, leakageList.get(i).getLeakagePv(),cellFormat));
//				ws.addCell(new Label(5, 7 + i, leakageList.get(i).getLeakageR(), cellFormat));
//				ws.addCell(new Label(6, 7 + i, leakageList.get(i).getWid(), cellFormat));
//				ws.addCell(new Label(7, 7 + i, leakageList.get(i).getSt(), cellFormat));
//			}
//			for (int i = 0; i < boltList.size(); i++) {
//				ws.addCell(new Label(0, 10 + i+leakageSize, 1+i+"", cellFormat));
//				ws.addCell(new Label(1, 10 + i+leakageSize, sdf.format(boltList.get(i).getDt()), cellFormat));
//				ws.addCell(new Label(2, 10 + i+leakageSize, boltList.get(i).getBoltName(),cellFormat));
//				ws.addCell(new Label(3, 10 + i+leakageSize, boltList.get(i).getA(),cellFormat));
//				ws.addCell(new Label(4, 10 + i+leakageSize, boltList.get(i).getT(), cellFormat));
//				ws.addCell(new Label(5, 10 + i+leakageSize, boltList.get(i).getR(), cellFormat));
//				ws.addCell(new Label(6, 10 + i+leakageSize, boltList.get(i).getWid(), cellFormat));
//				ws.addCell(new Label(7, 10 + i+leakageSize, boltList.get(i).getSt(), cellFormat));
//			}
//
//			for (int i = 0; i < keypartList.size(); i++) {
//				ws.addCell(new Label(0, 13 + i+leakageSize + boltSize, 1+i+"", cellFormat));
//				ws.addCell(new Label(1, 13 + i+leakageSize + boltSize, sdf.format(keypartList.get(i).getDt()), cellFormat));
//				ws.addCell(new Label(2, 13 + i+leakageSize + boltSize, keypartList.get(i).getKeypartName(),cellFormat));
//				ws.addCell(new Label(3, 13 + i+leakageSize + boltSize, keypartList.get(i).getKeypartNum(),cellFormat));
//				ws.addCell(new Label(4, 13 + i+leakageSize + boltSize, keypartList.get(i).getWid(), cellFormat));
//				ws.addCell(new Label(5, 13 + i+leakageSize + boltSize, keypartList.get(i).getSt(), cellFormat));
//			}
//
//		}
//
//		for (int i = 0; i < rAsmElectricDetectionList.size(); i++) {
//			ws.addCell(new Label(0, 16 + i+leakageSize + boltSize+keypartSize, 1+i+"", cellFormat));
//			ws.addCell(new Label(1, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getDt(), cellFormat));
//			ws.addCell(new Label(2, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckBurmer(),cellFormat));
//			ws.addCell(new Label(3, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckCommunication(),cellFormat));
//			ws.addCell(new Label(4, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckAllvoltage(), cellFormat));
//			ws.addCell(new Label(5, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getValueAllvoltage(), cellFormat));
//			ws.addCell(new Label(6, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckSinglevoltage(), cellFormat));
//			ws.addCell(new Label(7, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getValueCellvmax(), cellFormat));
//			ws.addCell(new Label(8, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getValueCellvmin(), cellFormat));
//			ws.addCell(new Label(9, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getValueCellvdif(), cellFormat));
//			ws.addCell(new Label(10, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckTemperature(), cellFormat));
//			ws.addCell(new Label(11, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getValueCelltmax(), cellFormat));
//			ws.addCell(new Label(12, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getValueCelltmin(), cellFormat));
//			ws.addCell(new Label(13, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getValueCelltdif(), cellFormat));
//			ws.addCell(new Label(14, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckPlusMinus(), cellFormat));
//			ws.addCell(new Label(15, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckPriming(), cellFormat));
//			ws.addCell(new Label(16, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckTricklecharge(), cellFormat));
//			ws.addCell(new Label(17, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckHighhanded(), cellFormat));
//			ws.addCell(new Label(18, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckFause(), cellFormat));
//			ws.addCell(new Label(19, 16 + i+leakageSize + boltSize+keypartSize, rAsmElectricDetectionList.get(i).getCheckHardbord(), cellFormat));
//		}
//
//		for (int i = 0; i < pChargedischargeList.size(); i++) {
//			ws.addCell(new Label(0, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, 1+i+"", cellFormat));
//			ws.addCell(new Label(1, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getSn(), cellFormat));
//			ws.addCell(new Label(2, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getMcNum(),cellFormat));
//			ws.addCell(new Label(3, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getStCode(),cellFormat));
//			ws.addCell(new Label(4, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getOperator(), cellFormat));
//			ws.addCell(new Label(5, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getStepNo(), cellFormat));
//			ws.addCell(new Label(6, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getInStructions(), cellFormat));
//			ws.addCell(new Label(7, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getTestValue(), cellFormat));
//			ws.addCell(new Label(8, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getUnit(), cellFormat));
//			ws.addCell(new Label(9, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getResult(), cellFormat));
//			ws.addCell(new Label(10, 19 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize, pChargedischargeList.get(i).getOptime(), cellFormat));
//		}
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		for (int i = 0; i < rAsmEolList.size(); i++) {
//			ws.addCell(new Label(0, 22 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, 1+i+"", cellFormat));
//			ws.addCell(new Label(1, 22 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, format.format(rAsmEolList.get(i).getDt()).toString(), cellFormat));
//			ws.addCell(new Label(2, 22 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, rAsmEolList.get(i).getT1(),cellFormat));
//			ws.addCell(new Label(3, 22 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, rAsmEolList.get(i).getT2(),cellFormat));
//			ws.addCell(new Label(4, 22 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, rAsmEolList.get(i).getT3(), cellFormat));
//			ws.addCell(new Label(5, 22 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, rAsmEolList.get(i).getT4(), cellFormat));
//			ws.addCell(new Label(6, 22 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, rAsmEolList.get(i).getT5(), cellFormat));
//			ws.addCell(new Label(7, 22 + i+leakageSize + boltSize+keypartSize+rAsmElectricDetectionSize+pChargedischargeSize, rAsmEolList.get(i).getR(), cellFormat));
//		}
//		wwb.write();
//		wwb.close();
//		return os;
//	}
//	/**
//	 * 电子报表导出Excel
//	 */
//	@RequestMapping("exportExcle")
//	public @ResponseBody Object exportExcle(HttpServletRequest request,HttpServletResponse response) {
//		Map<String, Object> map = new HashMap<>();
//		String sn = request.getParameter("sn");
//		if (sn==null||sn=="") {
//			map.put("msg", 1);
//			return map;
//		}
//		map.put("sn", sn);
//		List<CMesXXT> xxtList = reportService.getXXT(map);
//		if (xxtList.size()>0) {
//			map.put("sn", xxtList.get(0).getSn());
//		}
//		List<PTrackingT> ptrackingList = reportService.ptrackingList(map);
//		List<RTrackingT> rtrackingList = reportService.rtrackingList(map);
//		List<PMesBoltT> boltList = reportService.boltList(map);
//		List<PMesKeypartT> keypartList = reportService.keypartList(map);
//		List<PMesLeakageT> leakageList = reportService.leakageList(map);
//		if ((ptrackingList.size()>0||rtrackingList.size()>0)) {
//			map.put("msg", 0);
//			return map;
//		}
//		if (boltList.size()>0||keypartList.size()>0||leakageList.size()>0) {
//			map.put("msg", 0);
//		}else{
//			map.put("msg", 2);
//		}
//		return map;
//
//	}
//	@RequestMapping("export")
//	public void export(HttpServletRequest request,HttpServletResponse response) {
//		Map<String, Object> map = new HashMap<>();
//		String sn = request.getParameter("sn");
//		map.put("sn", sn);
//		List<CMesXXT> xxtList = reportService.getXXT(map);
//		if (xxtList.size()>0) {
//			map.put("sn", xxtList.get(0).getSn());
//		}
//		List<PTrackingT> ptrackingList = reportService.ptrackingList(map);
//		if(ptrackingList.size()>0){
//			List<PMesKeypartT> keypartList = reportService.keypartList(map);
//			List<PMesBoltT> boltList = reportService.boltList(map);
//			List<PMesLeakageT> leakageList = reportService.leakageList(map);
//			map.put("d", boltList);
//			map.put("keypartList", keypartList);
//			map.put("leakageList", leakageList);
//		}else{
//			List<RMesBolt> boltList = reportService.boltListR(map);
//			List<RMesKeypart> keypartList = reportService.keypartListR(map);
//			List<RMesLeakage> leakageList = reportService.leakageListR(map);
//			map.put("boltList", boltList);
//			map.put("keypartList", keypartList);
//			map.put("leakageList", leakageList);
//		}
//		List<RAsmElectricDetection> rAsmElectricDetectionList = reportService.rAsmElectricDetectionList(map);
//		List<PMesDischargeT> discharge = reportService.findAllDischargeT(map.get("sn").toString());
//		List<PMesEolT> findPEol = reportService.findAllEol(map.get("sn").toString());
//		map.put("rAsmElectricDetectionList", rAsmElectricDetectionList);
//		map.put("discharge", discharge);
//		map.put("rAsmEolList", findPEol);
//		export(request, response, map,sn+".xls",0);
//	}
//	/**
//	 * 配方详细信息
//	 */
//	@RequestMapping("recipeDatilLists")
//	public String recipeDatilLists(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
//		List<CMesRecipeVersion> findPVersion=new ArrayList<CMesRecipeVersion>();
//		Integer id = 0;  //产品ID
//		Double PVersion=0.0;
//		List<CMesProductionT> productionVrList = reportService.getProductionVr();
//		String productionVr = request.getParameter("packPn"); //产品规则
//		if (productionVr==null&&productionVrList.size()>0) {
//			productionVr = productionVrList.get(0).getProductionVr();
//			id= productionVrList.get(0).getId();
//			findPVersion = recipeversionService.findPVersion(productionVr);
//			if(findPVersion.size()>0){
//				PVersion= findPVersion.get(0).getRecipeName();
//				request.setAttribute("findPVersion", findPVersion);
//			}
//		}
//		request.setAttribute("PVersion", PVersion);
//		request.setAttribute("productionVr", productionVr);
//		PageHelper.startPage(page,13);
//		List<CMesRecipeVersionDetail> recipeDatilListsTwo = recipeversionService.recipeDatilListsTwo(id, PVersion);
//		PageInfo<CMesRecipeVersionDetail> pageInfo = new PageInfo<>(recipeDatilListsTwo,8);
//		request.setAttribute("productionVrList", productionVrList);
//		request.setAttribute("pageInfo", pageInfo);
//		return "tool_control/importRecipe";
//	}
//	/**
//	 * 导出配方
//	 */
//	@RequestMapping("exportRecipeDatil")
//	public void exportRecipeDatil(HttpServletRequest request,HttpServletResponse response) {
//		SimpleDateFormat sdf =   new SimpleDateFormat("yyyyMMddHHmm");
//		Map<String, Object> map = new HashMap<>();
//		String pid = request.getParameter("packPn");
//		String vid = request.getParameter("verid");
//		String findId = recipeversionService.findId(vid);
//		/*		map.put("productionVr", productionVr);
//		List<CMesRecipeDatilT> recipeDatilLists = reportService.recipeDatilLists(map);*/
//		List<CMesRecipeVersionDetail> recipeDatilListsTwo = recipeversionService.recipeDatilListsTwo(Integer.parseInt(pid),Double.parseDouble(findId));
//		map.put("recipeDatilListsTwo", recipeDatilListsTwo);
//		export(request, response, map,sdf.format(new Date())+".xls",1);
//	}
//	@SuppressWarnings("unchecked")
//	public Object exceloutRecipeDatil(OutputStream os,Map<String, Object> map) throws Exception{
//		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd  HH:mm:ss" );
//		List<CMesRecipeVersionDetail> recipeDatilLists = (List<CMesRecipeVersionDetail>) map.get("recipeDatilListsTwo");
//		WritableWorkbook wwb = Workbook.createWorkbook(os);// 创建可写工作薄
//		WritableSheet ws = wwb.createSheet("配方明细", 0);
//		WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.NO_BOLD, false,
//				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
//		WritableCellFormat cellFormat = new WritableCellFormat(font);
//		cellFormat.setWrap(false);
//		cellFormat.setAlignment(Alignment.CENTRE);
//		cellFormat.setBackground(Colour.AQUA);
//		cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.DARK_BLUE);
//		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
//				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
//		WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
//		cellFormat2.setWrap(false);
//		cellFormat2.setAlignment(Alignment.CENTRE);
//		cellFormat2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.DARK_BLUE);
//		ws.setColumnView(0, 14);
//		ws.setRowView(0, 600);
//		ws.addCell(new Label(0, 0, "配方明细", cellFormat));
//		ws.setColumnView(1, 14);
//		ws.addCell(new Label(1, 0, "", cellFormat));
//		ws.setColumnView(2, 14);
//		ws.addCell(new Label(2, 0, "", cellFormat));
//		ws.setColumnView(3, 14);
//		ws.addCell(new Label(3, 0, "", cellFormat));
//		ws.setColumnView(4, 14);
//		ws.addCell(new Label(4, 0, "", cellFormat));
//		ws.setColumnView(5, 14);
//		ws.addCell(new Label(5, 0, "", cellFormat));
//		ws.setColumnView(6, 14);
//		ws.addCell(new Label(6, 0, "", cellFormat));
//		ws.setColumnView(7, 14);
//		ws.addCell(new Label(7, 0, "导出数据时间:"+sdf.format(new Date()), cellFormat));
//		ws.setColumnView(8, 14);
//		ws.addCell(new Label(8, 0, "", cellFormat));
//		ws.setColumnView(9, 14);
//		ws.addCell(new Label(9, 0, "", cellFormat));
//		ws.setColumnView(10, 14);
//		ws.addCell(new Label(10, 0, "", cellFormat));
//		ws.setColumnView(11, 14);
//		ws.addCell(new Label(11, 0, "", cellFormat));
//		ws.setColumnView(12, 14);
//		ws.addCell(new Label(12, 0, "", cellFormat));
//		ws.setColumnView(13, 14);
//		ws.addCell(new Label(13, 0, "", cellFormat));
//		ws.setColumnView(14, 14);
//		ws.addCell(new Label(14, 0, "", cellFormat));
//
//		ws.setColumnView(15, 14);
//		ws.addCell(new Label(15, 0, "", cellFormat));
//		ws.setColumnView(16, 14);
//		ws.addCell(new Label(16, 0, "", cellFormat));
//		ws.setColumnView(17, 14);
//		ws.addCell(new Label(17, 0, "", cellFormat));
//		ws.setColumnView(18, 14);
//		ws.addCell(new Label(18, 0, "", cellFormat));
//		ws.setColumnView(19, 14);
//		ws.addCell(new Label(19, 0, "", cellFormat));
//		ws.setColumnView(20, 14);
//		ws.addCell(new Label(20, 0, "", cellFormat));
//		ws.setColumnView(21, 14);
//		ws.addCell(new Label(21, 0, "", cellFormat));
//		ws.setColumnView(22, 14);
//		ws.addCell(new Label(22, 0, "", cellFormat));
//		ws.setColumnView(23, 14);
//		ws.addCell(new Label(23, 0, "", cellFormat));
//		ws.mergeCells(0, 0, 6, 0); // 合并单元格
//		ws.mergeCells(7, 0, 14, 0); // 合并单元格
//		ws.addCell(new Label(0, 1, "编号", cellFormat));
//		ws.addCell(new Label(1, 1, "操作类别", cellFormat));
//		ws.addCell(new Label(2, 1, "工序名称", cellFormat));
//		ws.addCell(new Label(3, 1, "数量",cellFormat));
//		ws.addCell(new Label(4, 1, "枪号",cellFormat));
//		ws.addCell(new Label(5, 1, "程序号", cellFormat));
//		ws.addCell(new Label(6, 1, "物料PN", cellFormat));
//		ws.addCell(new Label(7, 1, "套筒号", cellFormat));
//		ws.addCell(new Label(8, 1, "工序", cellFormat));
//		ws.addCell(new Label(9, 1, "上传代码", cellFormat));
//		ws.addCell(new Label(10, 1, "条码规则", cellFormat));
//		ws.addCell(new Label(11, 1, "工位节拍", cellFormat));
//		ws.addCell(new Label(12, 1, "工位名称", cellFormat));
//		ws.addCell(new Label(13, 1, "产品名称", cellFormat));
//		ws.addCell(new Label(14, 1, "产品规则", cellFormat));
//		ws.addCell(new Label(15, 1, "相机号", cellFormat));
//		ws.addCell(new Label(16, 1, "返工次数", cellFormat));
//		ws.addCell(new Label(17, 1, "是否校验", cellFormat));
//		ws.addCell(new Label(18, 1, "是否追溯", cellFormat));
//		ws.addCell(new Label(19, 1, "物料类别", cellFormat));
//		ws.addCell(new Label(20, 1, "角度上限", cellFormat));
//		ws.addCell(new Label(21, 1, "扭矩上限", cellFormat));
//		ws.addCell(new Label(22, 1, "图片路径", cellFormat));
//		ws.addCell(new Label(23, 1, "螺栓json数据", cellFormat));
//		for (int i = 0; i < recipeDatilLists.size(); i++) {
//			ws.addCell(new Label(0, i+2, 1+i+"", cellFormat2));
//			ws.addCell(new Label(1, i+2, recipeDatilLists.get(i).getStepCategory(), cellFormat2));
//			ws.addCell(new Label(2, i+2, recipeDatilLists.get(i).getMaterialName(), cellFormat2));
//			ws.addCell(new Label(3, i+2, recipeDatilLists.get(i).getNumbers(), cellFormat2));
//			ws.addCell(new Label(4, i+2, recipeDatilLists.get(i).getGunNo(), cellFormat2));
//			ws.addCell(new Label(5, i+2, recipeDatilLists.get(i).getProgramNo(), cellFormat2));
//			ws.addCell(new Label(6, i+2, recipeDatilLists.get(i).getMaterialpn(), cellFormat2));
//			ws.addCell(new Label(7, i+2, recipeDatilLists.get(i).getSleeveNo(), cellFormat2));
//			ws.addCell(new Label(8, i+2, String.valueOf(recipeDatilLists.get(i).getStepno()), cellFormat2));
//			ws.addCell(new Label(9, i+2, recipeDatilLists.get(i).getUploadCode(), cellFormat2));
//			ws.addCell(new Label(10, i+2, recipeDatilLists.get(i).getFeacode(), cellFormat2));
//			ws.addCell(new Label(11, i+2, recipeDatilLists.get(i).getBolteqs(), cellFormat2));
//			ws.addCell(new Label(12, i+2, recipeDatilLists.get(i).getStationName(), cellFormat2));
//			ws.addCell(new Label(13, i+2, recipeDatilLists.get(i).getProductionName(), cellFormat2));
//			ws.addCell(new Label(14, i+2, recipeDatilLists.get(i).getProductionVr(), cellFormat2));
//			ws.addCell(new Label(15, i+2, recipeDatilLists.get(i).getPhotoNo()));
//			ws.addCell(new Label(16, i+2, recipeDatilLists.get(i).getReworktimes()));
//			ws.addCell(new Label(17, i+2, recipeDatilLists.get(i).getChekorno()));
//			ws.addCell(new Label(18, i+2, recipeDatilLists.get(i).getRevieworno()));
//			ws.addCell(new Label(19, i+2, recipeDatilLists.get(i).getExactorno()));
//			ws.addCell(new Label(20, i+2, recipeDatilLists.get(i).getaLimit()));
//			ws.addCell(new Label(21, i+2, recipeDatilLists.get(i).gettLimit()));
//			ws.addCell(new Label(22, i+2, recipeDatilLists.get(i).getPicturnPath()));
//			ws.addCell(new Label(23, i+2, recipeDatilLists.get(i).getBoltjson()));
//		}
//		wwb.write();
//		wwb.close();
//		return os;
//	}
//	/**
//	 * 解析文件方法
//	 *
//	 * @param fileName
//	 * @return map
//	 */
//
//
//
//	@RequestMapping("importExcel")//支持 excel 2003 (*.xls)。
//	public @ResponseBody Map<String, Object> importUser(HttpServletRequest request,HttpServletResponse response,MultipartFile file){
//		Map<String, Object> returnMap = new HashMap<>();
//		Map<String, Object> map = new HashMap<>();
//		try {
//			//也可以用request获取上传文件
//			//MultipartFile  fileFile = request.getFile("file"); //这里是页面的name属性
//			//转换成输入流
//			InputStream is = file.getInputStream();
//			//得到excel
//			Workbook workbook = Workbook.getWorkbook(is);
//			//得到sheet
//			Sheet sheet = workbook.getSheet(0);
//			//得到列数
//			//			int colsNum = sheet.getColumns();
//			//得到行数
//			int rowsNum = sheet.getRows();
//			//单元格
//			//			Cell cell;
//			Set<String> set = new HashSet<>();
//			Set<String> set1 = new HashSet<>();
//			Set<String> set2 = new HashSet<>();
//			Set<String> set3 = new HashSet<>();
//			Set<String> set4 = new HashSet<>();
//			int count = 0;
//			int count1 = 0;
//			int count2 = 0;
//			int count3 = 0;
//			for (int i = 1; i < rowsNum-1; i++) {//我的excel第一行是标题,所以 i从1开始
//				String stepCategory = sheet.getCell(1, i+1).getContents(); //操作类别
//				String stationName = sheet.getCell(12, i+1).getContents(); //工位名称
//				String productionName = sheet.getCell(13, i+1).getContents(); //产品名称
//				String productionVr = sheet.getCell(14, i+1).getContents(); //产品规则
//				String numbers = sheet.getCell(3, i+1).getContents(); //数量
//				String stepno = sheet.getCell(8, i+1).getContents();  //工序
//				String gunNo = sheet.getCell(4, i+1).getContents();  //枪号
//				String programNo = sheet.getCell(5, i+1).getContents(); //程序号
//				String bolteqs = sheet.getCell(11, i+1).getContents();	//工位节拍
//				String sleeveNo = sheet.getCell(7, i+1).getContents(); //套筒号
//				set.add(stationName);  //工位表
//				set1.add(stepCategory);	//类别表
//				set2.add(productionName);  //产品表、名称
//				set3.add(productionVr);	//产品表、条码规则
//				set4.add(numbers);
//				set4.add(stepno);
//				set4.add(gunNo);
//				set4.add(programNo);
//				set4.add(bolteqs);
//				set4.add(sleeveNo);
//			}
//			for (String str : set) {
//				if (reportService.countStation(str)>0) {count++;} //查看工位表中有几个名称一样的工位
//			}
//			for (String str : set1) {
//				if (!str.equals("扫描总成")&&!str.equals("扫描员工号")&&!str.equals("扫描物料")&&!str.equals("扫描物料(唯一编码)")&&
//						!str.equals("扫描模组")&&!str.equals("扫描电芯")&&!str.equals(" 拧紧")&&!str.equals("气密性测试")
//						&&!str.equals("通断测试")&&!str.equals("拍照")&&!str.equals("打印")
//						&&!str.equals("用户录入")&&!str.equals("电性能测试")&&!str.equals("结束")) {
//					count1++;
//				}
//			}
//			for (String str : set2) {
//				if (reportService.countProductionName(str)>0) {
//					count2++;   //查询产品表中是否有相同的产品
//				}
//			}
//			for (String str : set3) {
//				if (reportService.countPackPn(str)>0) {count3++;}  //查询产品表中是否有相同的条码规则
//			}
//			for (String string : set4) {
//				try {
//					if (string!=null && string.trim().length()<1 && !string.equals("")) {
//						Long.parseLong(string);  //string类型转Long
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					ToolUtils.errorLog(this, e, request);
//					returnMap.put("msg", 3);  //数量、工序不是数字类型
//					return returnMap;
//				}
//			}
//			if (set3.size()>1) {
//				returnMap.put("msg", 6);  //excle中条码规则不一致
//				return returnMap;
//			}
//			if (count<set.size()) {  //Excel中的工位数量大于工位表中的数量
//				returnMap.put("msg", 5);//返回错误信息：导入数据‘工位名称’格式错误
//				return returnMap;
//			}
//			if (count1>0) {   //类别表中没有此类别
//				returnMap.put("msg", 4);//返回错误信息：导入数据‘操作类别’格式错误
//				return returnMap;
//			}
//			if (set2.size()>1) {  //excle中有两个产品
//				returnMap.put("msg", 9);//返回错误信息：导入数据‘产品信息’格式错误
//				return returnMap;
//			}
//			if (count2<set2.size()&&count3>=set3.size()) {
//				returnMap.put("msg", 7);//返回错误信息：导入数据‘PACK PN’格式错误
//				return returnMap;
//			}else if (count2>=set2.size()&&count3>=set3.size()) {
//				Set<Integer> recipeIdList = new HashSet<>();
//				for (int i = 1; i < rowsNum-1; i++) {
//					String stationName = sheet.getCell(12, i+1).getContents();
//					String productionName = sheet.getCell(13, i+1).getContents();
//					map.put("productionName", productionName);
//					map.put("stationName", stationName);
//					List<CMesProductionT> production = reportService.getProductionByName(map);
//					List<CMesStationT> station = reportService.getStationByName(map);
//					if (production.size()>0&&station.size()>0) {
//						map.put("productionId", production.get(0).getId());
//						map.put("stationId", station.get(0).getId());
//						List<CMesProductionRecipeT> productionRecipeList = reportService.productionRecipe(map);
//						if (productionRecipeList.size()>0) {
//							recipeIdList.add(productionRecipeList.get(0).getRecipeId());
//						}
//					}
//				}
//				int counts = recipeIdList.size();
//				if (counts>0) {
//					for (Integer recipeId : recipeIdList) {//通过配方ID移除原有的配方明细
//						map.put("recipeId",recipeId);
//						reportService.removeRecipeDatilByRecipeId(map);
//						counts--;
//					}
//				}
//				for (int i = 1; i < rowsNum-1; i++) {//我的excel第一行是标题,所以 i从1开始
//					String stepCategory = sheet.getCell(1, i+1).getContents();
//					String materialName = sheet.getCell(2, i+1).getContents();
//					String materialpn = sheet.getCell(6, i+1).getContents();
//					String sleeveNo = sheet.getCell(7, i+1).getContents();
//					String stepno = sheet.getCell(8, i+1).getContents();
//					String uploadCode = sheet.getCell(9, i+1).getContents();
//					String feacode = sheet.getCell(10, i+1).getContents();
//					String numbers = sheet.getCell(3, i+1).getContents();
//					String gunNo = sheet.getCell(4, i+1).getContents();
//					String programNo = sheet.getCell(5, i+1).getContents();
//					String bolteqs = sheet.getCell(11, i+1).getContents();
//					String stationName = sheet.getCell(12, i+1).getContents();
//					String productionName = sheet.getCell(13, i+1).getContents();
//					map.put("typeName", stepCategory);
//					List<CMesRecipeTypeT> getRecipeTypeNameByStepCategory = reportService.getRecipeTypeNameByStepCategory(map);
//					map.put("productionName", productionName);
//					map.put("stationName", stationName);
//					List<CMesProductionT> production = reportService.getProductionByName(map);
//					List<CMesStationT> station = reportService.getStationByName(map);
//					map.put("productionId", production.get(0).getId());
//					map.put("stationId", station.get(0).getId());
//					List<CMesProductionRecipeT> productionRecipeList = reportService.productionRecipe(map);
//					if (productionRecipeList.size()<1) {//关联表 工位和产品无关联
//						//1.自动生成配方（配方名称：工位名称-产品名称-AUTO）
//						map.put("recipeName", stationName+"-"+productionName+"-AUTO");
//						map.put("recipeDis", "AutoDis");
//						reportService.createRecipe(map);
//						//2.获取生成的配方ID
//						List<CMesRecipeT> recipe = reportService.getRecipeIdByName(map);
//						//3.将工位ID、产品ID、配方ID加入关联表
//						map.put("recipeId", recipe.get(0).getId());
//						reportService.saveProductionRecipe(map);
//						map.put("stepCategory", getRecipeTypeNameByStepCategory.get(0).getId());
//						map.put("materialName", materialName);
//						map.put("gunNo", gunNo);
//						map.put("programNo", programNo);
//						map.put("sleeveNo", sleeveNo);
//						map.put("feacode", feacode);
//						map.put("materialpn", materialpn);
//						map.put("bolteqs", bolteqs);
//						map.put("uploadCode", uploadCode);
//						map.put("stepno", stepno);
//						map.put("numbers", numbers);
//						//4.将数据导入 配方明细
//						reportService.saveRecipeDatil(map);
//					}else {//关联表 工位和产品有关联
//						map.put("stepCategory", getRecipeTypeNameByStepCategory.get(0).getId());
//						map.put("materialName", materialName);
//						map.put("gunNo", gunNo);
//						map.put("programNo", programNo);
//						map.put("sleeveNo", sleeveNo);
//						map.put("feacode", feacode);
//						map.put("materialpn", materialpn);
//						map.put("bolteqs", bolteqs);
//						map.put("uploadCode", uploadCode);
//						map.put("stepno", stepno);
//						map.put("numbers", numbers);
//						map.put("recipeId", productionRecipeList.get(0).getRecipeId());
//						//将数据导入 配方明细
//						reportService.saveRecipeDatil(map);
//					}
//				}
//			}
//			for (int i = 1; i < rowsNum-1; i++) {//我的excel第一行是标题,所以 i从1开始
//				String stepCategory = sheet.getCell(1, i+1).getContents();
//				String materialName = sheet.getCell(2, i+1).getContents();
//				String numbers = sheet.getCell(3, i+1).getContents();
//				String gunNo = sheet.getCell(4, i+1).getContents();
//				String programNo = sheet.getCell(5, i+1).getContents();
//				String materialpn = sheet.getCell(6, i+1).getContents();
//				String sleeveNo = sheet.getCell(7, i+1).getContents();
//				String stepno = sheet.getCell(8, i+1).getContents();
//				String uploadCode = sheet.getCell(9, i+1).getContents();
//				String feacode = sheet.getCell(10, i+1).getContents();
//				String bolteqs = sheet.getCell(11, i+1).getContents();
//				String stationName = sheet.getCell(12, i+1).getContents();
//				String productionName = sheet.getCell(13, i+1).getContents();
//				String productionVr = sheet.getCell(14, i+1).getContents();
//				map.put("typeName", stepCategory);
//				List<CMesRecipeTypeT> getRecipeTypeNameByStepCategory = reportService.getRecipeTypeNameByStepCategory(map);
//				int countProduction = reportService.countProductionName(productionName);
//				map.put("stationName", stationName);
//				map.put("productionName", productionName);
//				map.put("productionVr", productionVr);
//				map.put("productionType", "AutoType");
//				map.put("stepCategory", getRecipeTypeNameByStepCategory.get(0).getId());
//				map.put("materialName", materialName);
//				map.put("gunNo", gunNo);
//				map.put("programNo", programNo);
//				map.put("sleeveNo", sleeveNo);
//				map.put("feacode", feacode);
//				map.put("materialpn", materialpn);
//				map.put("bolteqs", bolteqs);
//				map.put("uploadCode", uploadCode);
//				map.put("stepno", stepno);
//				map.put("numbers", numbers);
//				int countPackPn = reportService.countPackPn(productionVr);
//				if (countProduction<1&&countPackPn<1) {
//					reportService.createProduction(map);//生成一个产品产品
//					//自动生成配方（配方名称：工位名称-产品名称-AUTO）
//					map.put("recipeName", stationName+"-"+productionName+"-AUTO");
//					map.put("recipeDis", "AutoDis");
//					reportService.createRecipe(map);
//					List<CMesRecipeT> recipe = reportService.getRecipeIdByName(map);
//					List<CMesProductionT> production = reportService.getProductionByName(map);
//					List<CMesStationT> station = reportService.getStationByName(map);
//					map.put("productionId", production.get(0).getId());
//					map.put("stationId", station.get(0).getId());
//					map.put("recipeId", recipe.get(0).getId());
//					reportService.saveProductionRecipe(map);
//					reportService.saveRecipeDatil(map);
//				}
//			}
//			returnMap.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//			returnMap.put("msg", 1);
//		}
//		return returnMap;
//	}
//	/**
//	 * 数据报表
//	 */
//	@RequestMapping("dataReport")
//	public String dataReport(){
//
//		return "report_control/dataReport";
//	}
//	/**
//	 * 月产量统计
//	 */
//	@RequestMapping("monOutputStatistics")
//	public String monOutputStatistics(){
//		return "report_control/monOutputStatistics";
//	}
//	/**
//	 * 日下线统计
//	 */
//	@RequestMapping("downlineStatistics")
//	public String downlineStatistics(){
//		return "report_control/downlineStatistics";
//	}
//	/**
//	 * 月合格量统计
//	 */
//	@RequestMapping("monQualiStatistics")
//	public String monQualiStatistics(){
//		return "report_control/monQualiStatistics";
//	}
//	/**
//	 * 月气密性统计
//	 */
//	@RequestMapping("monAirtightnesstTStatistics")
//	public String monAirtightnesstTStatistics(){
//		return "report_control/monAirtightnesstTStatistics";
//	}
//	/**
//	 * 日产量统计
//	 */
//	@RequestMapping("dailyOutputStatistics")
//	public String dailyOutputStatistics(){
//		return "report_control/dailyOutputStatistics";
//	}
//	/**
//	 * 设备使用率
//	 */
//	@RequestMapping("equipmentUsageRate")
//	public String equipmentUsageRate(){
//		return "report_control/equipmentUsageRate";
//	}
//	/**
//	 * 设备OEE
//	 */
//	@RequestMapping("equipmentOEE")
//	public String equipmentOEE(){
//		return "report_control/equipmentOEE";
//	}
//	/**
//	 * 拧紧合格率
//	 */
//	@RequestMapping("tightenQualifiedRate")
//	public String tightenQualifiedRate(){
//		return "report_control/tightenQualifiedRate";
//	}
//	/**
//	 * 工位完成数量
//	 */
//	@RequestMapping("stationNumberStatistics")
//	public String stationNumberStatistics(){
//		return "report_control/stationNumberStatistics";
//	}
//	/**
//	 * 班次数量统计
//	 */
//	@RequestMapping("shiftNumberStatistics")
//	public String shiftNumberStatistics(){
//		return "report_control/shiftNumberStatistics";
//	}
//	/**
//	 * 整体合格率统计
//	 */
//	@RequestMapping("wholeQualiRateStatistics")
//	public String wholeQualiRateStatistics(){
//		return "report_control/wholeQualiRateStatistics";
//	}
//	/**
//	 * 工位时间统计
//	 */
//	@RequestMapping("stationTimeStatistics")
//	public String stationTimeStatistics(){
//		return "report_control/stationTimeStatistics";
//	}
//	/**
//	 * 一次通过率统计
//	 */
//	@RequestMapping("onePassRateStatistics")
//	public String onePassRateStatistics(){
//		return "report_control/onePassRateStatistics";
//	}
//	/**
//	 * 废品率统计
//	 */
//	@RequestMapping("wasteRateStatistics")
//	public String wasteRateStatistics(){
//		return "report_control/wasteRateStatistics";
//	}
//	/**
//	 * 缺陷总成
//	 */
//	@RequestMapping("defectSNManager")
//	public String defectSNManager(){
//		return "quality_control/defectSNManager";
//	}
//	/**
//	 * 节拍统计
//	 */
//	@RequestMapping("beatStatistic")
//	public String beatStatistics(){
//		return "report_control/beatStatistics";
//	}
//	/**
//	 * SPC统计
//	 */
//	@RequestMapping("spcStatistics")
//	public String spcStatistics(){
//		return "report_control/spcStatistics";
//	}
//	/**
//	 * 手动上传
//	 * @return
//	 */
//	@RequestMapping("manuallyUploading")
//	public String manuallyUploading(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//
//		Map<String, Object> map = new HashMap<>();
//		PageHelper.startPage(page,5);
//		List<RUploadDataT> uploadDataList = reportService.uploadDataList(map);
//		PageInfo<RUploadDataT> pageInfo = new PageInfo<>(uploadDataList,5);
//		request.setAttribute("pageInfo", pageInfo);
//		return "tool_control/manuallyUploading";
//	}
//	/**
//	 * 导入员工信息
//	 */
//
//	@RequestMapping("importEmp")//支持 excel 2003 (*.xls)。
//	public @ResponseBody Map<String, Object> importEmp(HttpServletRequest request,HttpServletResponse response,MultipartFile file){
//		Map<String, Object> returnMap = new HashMap<>();
//		Map<String, Object> map = new HashMap<>();
//		Map<String, Object> map2 = new HashMap<>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			//也可以用request获取上传文件
//			//MultipartFile  fileFile = request.getFile("file"); //这里是页面的name属性
//			//转换成输入流
//			InputStream is = file.getInputStream();
//			//得到excel
//			Workbook workbook = Workbook.getWorkbook(is);
//			//得到sheet
//			Sheet sheet = workbook.getSheet(0);
//			//得到列数
//			//			int colsNum = sheet.getColumns();
//			//得到行数
//			int rowsNum = sheet.getRows();
//			//单元格
//			//			List<CMesEmpT> empList = productionService.empList(map);
//			for (int i = 0; i < rowsNum-1; i++) {
//				//员工编号
//				String empNo = sheet.getCell(0, i+1).getContents().trim();
//				//员工姓名
//				String empName = sheet.getCell(1, i+1).getContents().trim();
//				//密码
//				String empVr = sheet.getCell(2, i+1).getContents().trim();
//				//性别
//				String empSex = sheet.getCell(3, i+1).getContents().trim();
//				//职位
//				String empType = sheet.getCell(4, i+1).getContents().trim();
//				//电话
//				String empTp = sheet.getCell(5, i+1).getContents().trim();
//				//所属部门
//				String empDepartment = sheet.getCell(6, i+1).getContents().trim();
//				//邮箱
//				String empMail = sheet.getCell(7, i+1).getContents().trim();
//				//所属班组
//				String empTeamName = sheet.getCell(8, i+1).getContents().trim();
//				//所属产线
//				String lineName = sheet.getCell(9, i+1).getContents().trim();
//				//所属工位
//				String stationName = sheet.getCell(10, i+1).getContents().trim();
//				if (empSex.equals("男")) {
//					map.put("empSex", 0);
//				}else if (empSex.equals("女")) {
//					map.put("empSex", 1);
//				}else {
//					returnMap.put("msg", 5);
//					return returnMap;
//				}
//				map2.put("empNo", empNo);
//				//验证员工编号是否存在
//				Integer count = productionService.countEmpByEmpNo(map2);
//				if (count>0) {
//					returnMap.put("msg", 6);
//					return returnMap;
//				}
//				map2.put("empTeamName", empTeamName);
//				List<CMesEmpTeamT> empTeamList = productionService.empTeamList(map2);
//				if (empTeamList.size()>0) {
//					map.put("empTeamId", empTeamList.get(0).getId());
//				}else {
//					//返回错误：没有此班组名称
//					returnMap.put("msg", 2);
//					return returnMap;
//				}
//				map2.put("lineNames", lineName);
//				List<CMesLineT> lineList = usersService.lineList(map2);
//				if (lineList.size()>0) {
//					map.put("lineId", lineList.get(0).getId());
//				}else {
//					//返回错误：没有此产线名称
//					returnMap.put("msg", 3);
//					return returnMap;
//				}
//				map2.put("stationNames", stationName);
//				List<CMesStationT> stationList = usersService.stationList(map2);
//				if (stationList.size()>0) {
//					map.put("stationId", stationList.get(0).getId());
//				}else {
//					//返回错误：没有此工位名称
//					returnMap.put("msg", 4);
//					return returnMap;
//				}
//				map.put("empNo", empNo);
//				map.put("dt", sdf.format(new Date()));
//				map.put("empName", empName);
//				map.put("empVr", empVr);
//				map.put("empType", empType);
//				map.put("empTp", empTp);
//				map.put("empDepartment", empDepartment);
//				map.put("empMail", empMail);
//
//				productionService.addEmp(map);
//			}
//			returnMap.put("msg", 0);
//		}catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//			returnMap.put("msg", 1);
//		}
//		return returnMap;
//	}
//}

//package com.skeqi.mes.controller.fqz;
//
//import java.io.InputStream;
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
//import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.CellRangeAddress;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.util.RegionUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.alibaba.fastjson.JSON;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.skeqi.mes.pojo.CMesProductionRecipeT;
//import com.skeqi.mes.pojo.CMesProductionT;
//import com.skeqi.mes.pojo.CMesRecipeDatilT;
//import com.skeqi.mes.pojo.CMesRecipeT;
//import com.skeqi.mes.pojo.CMesRecipeTypeT;
//import com.skeqi.mes.pojo.CMesRecipeVersion;
//import com.skeqi.mes.pojo.CMesRecipeVersionDetail;
//import com.skeqi.mes.service.fqz.RecipeVersionService;
//import com.skeqi.mes.service.yin.ReportService;
//import com.skeqi.mes.service.yin.UsersService;
//import com.skeqi.mes.util.ExcelUtil;
//import com.skeqi.mes.util.ToolUtils;
//
//import jxl.Sheet;
//import jxl.Workbook;
//
//@Controller
//@RequestMapping("/skq")
//public class RecipeVersionController {
//
//	@Autowired
//	private RecipeVersionService recipeversionservice;
//
//	@Autowired
//	private ReportService reportService;
//
//	@Autowired
//	private UsersService usersService;
//
//	//???????????????????????????
//	@RequestMapping("/addRecipe")
//	@SuppressWarnings("all")
//	public @ResponseBody Map<String, Object> addRecipe(HttpServletRequest request,HttpServletResponse response,MultipartFile file){
//		Map<String, Object> returnMap = new HashMap<>();
//		Map<String, Object> map = new HashMap<>();
//		try {
//			//??????????????????request???????????????????????????
//			//MultipartFile  fileFile = request.getFile("file"); //???????????????????????????name????????????
//			//???????????????????????????
//			InputStream is = file.getInputStream();
//			//?????????excel
//			Workbook workbook = Workbook.getWorkbook(is);
//			//?????????sheet
//			Sheet sheet = workbook.getSheet(0);
//			//??????????????????
//			//			int colsNum = sheet.getColumns();
//			//??????????????????
//			int rowsNum = sheet.getRows();
//			//???????????????
//			//			Cell cell;
//			Set<String> set = new HashSet<>();
//			Set<String> set1 = new HashSet<>();
//			Set<String> set2 = new HashSet<>();
//			Set<String> set3 = new HashSet<>();
//			Set<String> set4 = new HashSet<>();
//			Set<String> set5 = new HashSet<>();
//			int count = 0;
//			int count1 = 0;
//			int count2 = 0;
//			int count3 = 0;
//			for (int i = 1; i < rowsNum-1; i++) {//?????????excel???????????????????????????,???????????? i??????1????????????
//				String stepCategory = sheet.getCell(1, i+1).getContents(); //??????????????????
//				String stationName = sheet.getCell(12, i+1).getContents(); //?????????????????
//				String productionName = sheet.getCell(13, i+1).getContents(); //????????????????
//				String productionVr = sheet.getCell(14, i+1).getContents(); //?????????????????
//				String numbers = sheet.getCell(3, i+1).getContents(); //?????????
//				String stepno = sheet.getCell(8, i+1).getContents();  //?????????
//				String gunNo = sheet.getCell(4, i+1).getContents();  //?????????
//				String programNo = sheet.getCell(5, i+1).getContents(); //???????????????
//				String bolteqs = sheet.getCell(11, i+1).getContents();	//??????????????????
//				String sleeveNo = sheet.getCell(7, i+1).getContents(); //???????????????
//
//				String photoNo = sheet.getCell(15, i+1).getContents(); //???????????????
//				String reworktimes = sheet.getCell(16, i+1).getContents(); //??????????????????
//				String chekorno = sheet.getCell(17, i+1).getContents(); //??????????????????
//				String revieworno = sheet.getCell(18, i+1).getContents(); //??????????????????
//				String exactorno = sheet.getCell(19, i+1).getContents(); //??????????????????
//				String aLimit = sheet.getCell(20, i+1).getContents(); //??????????????????
//				String tLimit = sheet.getCell(21, i+1).getContents(); //??????????????????
//				String picturnPath = sheet.getCell(22, i+1).getContents(); //??????????????????
//				String boltJson = sheet.getCell(23, i+1).getContents(); //?????????json?????????
//
///*				if(chekorno!="" && chekorno!=null ){
//					if(Integer.parseInt(chekorno)!=0 && Integer.parseInt(chekorno)!=1){
//						returnMap.put("msg", 10);//?????????????????????????????????"??????????????????"??????????????????0??????1
//						return returnMap;
//					}
//				}
//				if(revieworno!=""){
//					if(Integer.parseInt(revieworno)!=0 && Integer.parseInt(revieworno)!=1){
//						returnMap.put("msg", 11);//?????????????????????????????????"??????????????????"??????????????????0??????1
//						return returnMap;
//					}
//				}
//				if(exactorno!=""){
//					if(Integer.parseInt(exactorno)!=0 && Integer.parseInt(exactorno)!=1){
//						returnMap.put("msg", 12);//?????????????????????????????????"??????????????????"??????????????????0??????1
//						return returnMap;
//					}
//				}*/
//
//
//				set.add(stationName);  //???????????????
//				set1.add(stepCategory);	//???????????????
//				set2.add(productionName);  //?????????????????????????????
//				set3.add(productionVr);	//??????????????????????????????????????
//				set4.add(numbers);
//				set4.add(stepno);
//				set4.add(gunNo);
//				set4.add(programNo);
//				set4.add(bolteqs);
//				set4.add(sleeveNo);
//			}
//			for (String str : set) {
//				if (reportService.countStation(str)>0) {count++;} //????????????????????????????????????????????????????????????????????????
//			}
//			for (String str : set1) {
//				if (!str.equals("??????????????????")&&!str.equals("?????????????????????")&&!str.equals("????????????")&&!str.equals("????????????")&&
//						!str.equals("????????????(????????????)")&&!str.equals("????????????")&&!str.equals("????????????")&&!str.equals("??????")
//						&&!str.equals("???????????????")&&!str.equals("????????????")&&!str.equals("??????")
//						&&!str.equals("??????")&&!str.equals("????????????")&&!str.equals("???????????????")
//						&&!str.equals("??????")&&!str.equals("???????????????")&&!str.equals("??????")) {
//					count1++;
//				}
//			}
//			for (String str : set2) {
//				if (reportService.countProductionName(str)>0) {
//					count2++;   //?????????????????????????????????????????????????????????????
//				}
//			}
//			for (String str : set3) {
//				if (reportService.countPackPn(str)>0) {count3++;}  //???????????????????????????????????????????????????????????????????????
//			}
//			for (String string : set4) {
//				try {
//					if (string!=null && string.trim().length()<1 && !string.equals("")) {
//						Long.parseLong(string);  //string???????????????ong
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					ToolUtils.errorLog(this, e, request);
//					returnMap.put("msg", 3);  //???????????????????????????????????????????????????
//					return returnMap;
//				}
//			}
//			if (set3.size()>1) {
//				returnMap.put("msg", 6);  //excle???????????????????????????????????????
//				return returnMap;
//			}
//			if (count<set.size()) {  //Excel?????????????????????????????????????????????????????????????????????
//				returnMap.put("msg", 5);//???????????????????????????????????????????????????????????????????????????????????????????????????
//				return returnMap;
//			}
//			if (count1>0) {   //??????????????????????????????????????????
//				returnMap.put("msg", 4);//???????????????????????????????????????????????????????????????????????????????????????????????????
//				return returnMap;
//			}
//			if (set2.size()>1) {  //excle??????????????????????????
//				returnMap.put("msg", 9);//???????????????????????????????????????????????????????????????????????????????????????????????????
//				return returnMap;
//			}
//			if (count2<set2.size()&&count3>=set3.size()) {
//				returnMap.put("msg", 7);//?????????????????????????????????????????????????????????ACK PN????????????????????????
//				return returnMap;
//			}
//			for (int i = 1; i < rowsNum-1; i++) {//?????????excel???????????????????????????,???????????? i??????1????????????
//				CMesRecipeVersionDetail reci = new CMesRecipeVersionDetail();
//				String stepCategory = sheet.getCell(1, i+1).getContents(); //??????????????????
//				reci.setStepCategory(stepCategory);
//				String materialName = sheet.getCell(2, i+1).getContents(); //?????????????????
//				reci.setMaterialName(materialName);
//				String numbers = sheet.getCell(3, i+1).getContents(); //?????????
//				reci.setNumbers(numbers);
//				String gunNo = sheet.getCell(4, i+1).getContents(); //?????????
//				reci.setGunNo(gunNo);
//				String programNo = sheet.getCell(5, i+1).getContents(); //???????????????
//				reci.setProgramNo(programNo);
//				String materialpn = sheet.getCell(6, i+1).getContents(); //?????????PN
//				reci.setMaterialpn(materialpn);
//				String sleeveNo = sheet.getCell(7, i+1).getContents(); //???????????????
//				reci.setSleeveNo(sleeveNo);
//				String stepno = sheet.getCell(8, i+1).getContents(); //?????????
//				reci.setStepno(Integer.parseInt(stepno));
//				String uploadCode = sheet.getCell(9, i+1).getContents(); //??????????????????
//				reci.setUploadCode(uploadCode);
//				String feacode = sheet.getCell(10, i+1).getContents(); //??????????????????
//				reci.setFeacode(feacode);
//				String bolteqs = sheet.getCell(11, i+1).getContents(); //??????????????????
//				reci.setBolteqs(bolteqs);
//				String stationName = sheet.getCell(12, i+1).getContents(); //?????????????????
//				reci.setStationName(stationName);
//				String productionName = sheet.getCell(13, i+1).getContents(); //????????????????
//				reci.setProductionName(productionName);
//				String productionVr = sheet.getCell(14, i+1).getContents(); //?????????????????
//				reci.setProductionVr(productionVr);
//
//				String photoNo = sheet.getCell(15, i+1).getContents(); //???????????????
//				reci.setPhotoNo(photoNo);
//				String reworktimes = sheet.getCell(16, i+1).getContents(); //??????????????????
//				reci.setReworktimes(reworktimes);
//				String chekorno = sheet.getCell(17, i+1).getContents(); //??????????????????
//				reci.setChekorno(chekorno);
//				String revieworno = sheet.getCell(18, i+1).getContents(); //??????????????????
//				reci.setRevieworno(revieworno);
//				String exactorno = sheet.getCell(19, i+1).getContents(); //??????????????????
//				reci.setExactorno(exactorno);
//				String aLimit = sheet.getCell(20, i+1).getContents(); //??????????????????
//				reci.setaLimit(aLimit);
//				String tLimit = sheet.getCell(21, i+1).getContents(); //??????????????????
//				reci.settLimit(tLimit);
//				String picturnPath = sheet.getCell(22, i+1).getContents(); //??????????????????
//				reci.setPicturnPath(picturnPath);
//				String boltJson = sheet.getCell(23, i+1).getContents(); //?????????json?????????
//				reci.setBoltjson(boltJson);
//				if(i==1){
//					CMesProductionT pro = recipeversionservice.findPID(productionVr); //???????????????????????????????????id??????ame
//					int p_id = pro.getId();
//					String p_name = pro.getProductionName();
//					String recipeName = "";
//					Double listName = recipeversionservice.listName(p_id); //?????????????????????????????????
//					Map<String,Object> maps = new HashMap<String,Object>();
//					maps.put("productionId", p_id);
//					maps.put("productionName", p_name);
//					maps.put("productionVr", productionVr);
//					if(listName==null){
//						maps.put("recipeName", 1.0);
//						recipeversionservice.addVersion(maps);
//					}else{
//						recipeName=String.format("%1f", listName+1);
//						maps.put("recipeName",recipeName);
//						recipeversionservice.addVersion(maps); //????????????????????????
//					}
//				}
//				Integer listId = recipeversionservice.listId(); //????????????????????????????????????id
//				recipeversionservice.addVersionDetail(listId, reci);
//				}
//			returnMap.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//			returnMap.put("msg", 1);
//		}
//		return returnMap;
//	}
//
//	//??????????????????????????????????????????????????
//	@RequestMapping("/findVersion")
//	@ResponseBody
//	@SuppressWarnings("all")
//	public List<Map<String,Object>> findVersion(String vr){
//		if(vr!=null||vr!=""){
//			List<Map<String, Object>> findVersion = recipeversionservice.findVersion(vr);
//			return findVersion;
//		}else{
//			return null;
//		}
//	}
//
//	//???????????????????????????
//	@RequestMapping("/recipeDatilListsTwo")
//	public String  recipeDatilLists(HttpServletRequest request,String packPn,String verid,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
//		List<CMesProductionT> productionVrList = reportService.getProductionVr(); //?????????????????????
//		request.setAttribute("productionVrList", productionVrList);
//		String findPName = recipeversionservice.findPName(packPn);
//		request.setAttribute("productionVr", findPName); //?????????????????
//		List<CMesRecipeVersion> findPVersion =recipeversionservice.findPVersion(findPName); //??????????????????????????
//		request.setAttribute("findPVersion", findPVersion);
//		String finds = recipeversionservice.findId(verid);
//		request.setAttribute("PVersion",finds); //??????????????????
//		PageHelper.startPage(page,13);
//		List<CMesRecipeVersionDetail> recipeDatilListsTwo = recipeversionservice.recipeDatilListsTwo(Integer.parseInt(packPn), Double.parseDouble(finds));
//		PageInfo<CMesRecipeVersionDetail> pageInfo = new PageInfo<>(recipeDatilListsTwo,8);
//		request.setAttribute("pageInfo", pageInfo);
//		return "tool_control/importRecipe";
//	}
//
//	//??????????????????
//	@RequestMapping(value="updates",produces = "text/plain;charset=utf-8")
//	@ResponseBody
//	@SuppressWarnings("all")
//	public String updates(HttpServletRequest request, String packPn,String verid){
//		String flag ="????????????";
//		List<Integer> list = new ArrayList<>();
//		String findId = recipeversionservice.findId(verid);//ID???????????????ame
//		Integer findMaxId = recipeversionservice.findMaxId(); //??????????????id
//		try {
//			List<CMesRecipeVersionDetail> recipeDatilListsTwo = recipeversionservice.recipeDatilListsTwo(Integer.parseInt(packPn),Double.parseDouble(findId));
//			for (CMesRecipeVersionDetail cMesRecipeVersionDetail : recipeDatilListsTwo) {
//				Integer findProductName = recipeversionservice.findProductName(cMesRecipeVersionDetail.getProductionName()); //????????ID
//				Integer findStationName = recipeversionservice.findStationName(cMesRecipeVersionDetail.getStationName()); //?????????id
//				if(findProductName==0||findStationName==0){
//					flag = "????????????";
//					break;
//				}
//				Integer findPCId = recipeversionservice.findPCId(findProductName, findStationName); //?????????id
//				Integer findTId = recipeversionservice.findTId(cMesRecipeVersionDetail.getStepCategory()); //??????????????????id
//				List<Integer> findRId = recipeversionservice.findRId(findPCId);
//				for (Integer integer : findRId) {
//					list.add(integer);
//				}
//				Map<String,Object> map = new HashMap<>();
//				map.put("recipe_id", String.valueOf(findPCId)); //?????????id
//				map.put("step_category",String.valueOf(findTId));  //??????????????????
//				map.put("material_name",cMesRecipeVersionDetail.getMaterialName()); //?????????????????
//				map.put("numbers", cMesRecipeVersionDetail.getNumbers());  //?????????
//				map.put("GUN_NO",cMesRecipeVersionDetail.getGunNo());  //?????????
//				map.put("PROGRAM_NO", cMesRecipeVersionDetail.getProgramNo()); //???????????????
//				map.put("MATERIALPN", cMesRecipeVersionDetail.getMaterialpn());  //?????????PN
//				map.put("SLEEVE_NO", cMesRecipeVersionDetail.getSleeveNo());  //???????????????
//				map.put("STEPNO",String.valueOf(cMesRecipeVersionDetail.getStepno()));  //?????????
//				map.put("UPLOAD_CODE",cMesRecipeVersionDetail.getUploadCode());  //??????????????????
//				map.put("FEACODE", cMesRecipeVersionDetail.getFeacode());  //??????????????????
//				map.put("BOLTEQS", cMesRecipeVersionDetail.getBolteqs());//??????????????????
//				map.put("photoNo",cMesRecipeVersionDetail.getPhotoNo());  //???????????????
//				map.put("reworktimes", cMesRecipeVersionDetail.getReworktimes()); //??????????????????
//				map.put("chekorno", cMesRecipeVersionDetail.getChekorno()); //??????????????????
//				map.put("revieworno", cMesRecipeVersionDetail.getRevieworno());  //??????????????????
//				map.put("exactorno", cMesRecipeVersionDetail.getExactorno());  //??????????????????
//				map.put("aLimit", cMesRecipeVersionDetail.getaLimit());  //??????????????????
//				map.put("tLimit", cMesRecipeVersionDetail.gettLimit()); //??????????????????
//				map.put("picturnPath", cMesRecipeVersionDetail.getPicturnPath()); //??????????????????
//				map.put("boltJson", cMesRecipeVersionDetail.getBoltjson()); //?????????json?????????
//				recipeversionservice.addrecipe(map); //????????????????????????
//			}
//			for (Integer in : list) {
//				if(in<=findMaxId){
//					recipeversionservice.deleterecipe(in);
//				}//????????????????????????????????
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//			flag = "????????????";
//			return flag;
//		}
//		return flag;
//	}
//
//	@RequestMapping(value="deleteVersion",produces = "text/plain;charset=utf-8")
//	@ResponseBody
//	public String delete(HttpServletRequest request, String verid){
//		String flag = "????????????";
//		try {
//			recipeversionservice.ddelteVersionDetil(Integer.parseInt(verid));
//			recipeversionservice.deleteVersion(Integer.parseInt(verid));
//		} catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//			flag = "????????????";
//			return flag;
//		}
//		return flag;
//	}
//
//	/*
//	 * ????????????
//	 */
//	@RequestMapping("/excelRecipe")
//	@ResponseBody
//	public void excelRecipe(HttpServletRequest request, HttpServletResponse response){
//		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
//		try {
//			HSSFWorkbook  book = new HSSFWorkbook();  //?????????????????????
//			String headers[] = {"??????","????????????","????????????","??????","??????","?????????","??????PN","?????????","??????","????????????",
//					"????????????","????????????","????????????","????????????","????????????","?????????","????????????","????????????",
//					"????????????","????????????","????????????","????????????","????????????","??????json??????"};
//			HSSFSheet sheet = book.createSheet("Sheet1"); // ????????????sheet??????
//			sheet.setColumnWidth(0, 100 * 60); // ???????????????
//			sheet.setColumnWidth(1, 100 * 60);
//			sheet.setColumnWidth(2, 100 * 60);
//			sheet.setColumnWidth(3, 100 * 60);
//			sheet.setColumnWidth(4, 100 * 60);
//			sheet.setColumnWidth(5, 100 * 60);
//			sheet.setColumnWidth(6, 100 * 60);
//			sheet.setColumnWidth(7, 100 * 60);
//			sheet.setColumnWidth(8, 100 * 60);
//			sheet.setColumnWidth(9, 100 * 60);
//			sheet.setColumnWidth(10, 100 * 60);
//			sheet.setColumnWidth(11, 100 * 60);
//			sheet.setColumnWidth(12, 100 * 60);
//			sheet.setColumnWidth(13, 100 * 60);
//			sheet.setColumnWidth(14, 100 * 60);
//			sheet.setColumnWidth(15, 100 * 60);
//			sheet.setColumnWidth(16, 100 * 60);
//			sheet.setColumnWidth(17, 100 * 60);
//			sheet.setColumnWidth(18, 100 * 60);
//			sheet.setColumnWidth(19, 100 * 60);
//			sheet.setColumnWidth(20, 100 * 60);
//			sheet.setColumnWidth(21, 100 * 60);
//			sheet.setColumnWidth(22, 100 * 60);
//			sheet.setColumnWidth(23, 100 * 60);
//			sheet.setColumnWidth(24, 100 * 60);
//			sheet.setColumnWidth(25, 100 * 60);
//			HSSFFont font = book.createFont();
//
//			font.setColor(IndexedColors.BLACK.index); // ????????????
//			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // ??????
//			font.setFontName("??????"); // ????????????
//			font.setFontHeightInPoints((short) 17); // ????????????
//
//			HSSFCellStyle cellStyle = book.createCellStyle();
//			cellStyle.setFont(font);
//			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ??????
//			cellStyle.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
//			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//			cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
//			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//?????????
//			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//?????????
//			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//?????????
//			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//?????????
//
//			HSSFCellStyle cellStyle1 = book.createCellStyle();
//			cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//?????????
//			cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//?????????
//			cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//?????????
//			cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//?????????
//
//			CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 23);
//			sheet.addMergedRegion(cra);
//			RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, book);
//
//			HSSFRow createRowbt = sheet.createRow(0); // ?????????????????????
//			HSSFCell createCellbt = createRowbt.createCell(0);
//			createRowbt.setHeight((short) 480);
//			createCellbt.setCellValue("????????????");
//			createCellbt.setCellStyle(cellStyle);
//
//			HSSFRow createRow = sheet.createRow(1); // ?????????????????????
//			createRow.setHeight((short) 380);
//			for (int i = 0; i < headers.length; i++) {
//				HSSFCell createCell = createRow.createCell(i); // ?????????????????????
//				createCell.setCellValue(headers[i]);
//				createCell.setCellStyle(cellStyle);
//			}
//
//			HSSFRow createRow2 = sheet.createRow(2);  //?????????
//			createRow2.setHeight((short) 280);
//			for(int i=0;i<22;i++){
//				HSSFCell createCellbt0 = createRow2.createCell(i);
//				createCellbt0.setCellValue("");
//				createCellbt0.setCellStyle(cellStyle1);
//			}
//			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
//		} catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//		}
//	}
//
//	@RequestMapping("/exportrecipe")
//	@ResponseBody
//	public Map<String,Object> exportrecipe(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<String,Object>();
//		String id = request.getParameter("pid");
//		try {
//			List<CMesRecipeDatilT> findRecipe = recipeversionservice.findRecipe(id);
//			if(findRecipe.size()>0){
//				map.put("msg",1);
//			}else{
//				map.put("msg", 2);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//				map.put("msg",3);
//		}
//		return map;
//	}
//
//	@RequestMapping("/exportDeil")
//	@ResponseBody
//	public void exportDeil(HttpServletRequest request,HttpServletResponse response){
//		String id = request.getParameter("id");
//		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
//		try {
//			List<CMesRecipeDatilT> findRecipe = recipeversionservice.findRecipe(id);
//			HSSFWorkbook  book = new HSSFWorkbook();
//			String headers[] = {"??????","????????????","????????????","??????","??????","?????????","??????PN","?????????","??????","????????????",
//					"????????????","????????????","????????????","????????????","????????????","?????????","????????????","????????????",
//					"????????????","????????????","????????????","????????????","????????????","??????json??????"};
//	/*		ExcelUtil.RecipeExcel(findRecipe,book,headers);*/
//			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//}

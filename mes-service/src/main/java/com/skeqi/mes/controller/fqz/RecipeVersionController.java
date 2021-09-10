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
//	//淇濆瓨鍒扮増鏈〃
//	@RequestMapping("/addRecipe")
//	@SuppressWarnings("all")
//	public @ResponseBody Map<String, Object> addRecipe(HttpServletRequest request,HttpServletResponse response,MultipartFile file){
//		Map<String, Object> returnMap = new HashMap<>();
//		Map<String, Object> map = new HashMap<>();
//		try {
//			//涔熷彲浠ョ敤request鑾峰彇涓婁紶鏂囦欢
//			//MultipartFile  fileFile = request.getFile("file"); //杩欓噷鏄〉闈㈢殑name灞炴��
//			//杞崲鎴愯緭鍏ユ祦
//			InputStream is = file.getInputStream();
//			//寰楀埌excel
//			Workbook workbook = Workbook.getWorkbook(is);
//			//寰楀埌sheet
//			Sheet sheet = workbook.getSheet(0);
//			//寰楀埌鍒楁暟
//			//			int colsNum = sheet.getColumns();
//			//寰楀埌琛屾暟
//			int rowsNum = sheet.getRows();
//			//鍗曞厓鏍�
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
//			for (int i = 1; i < rowsNum-1; i++) {//鎴戠殑excel绗竴琛屾槸鏍囬,鎵�浠� i浠�1寮�濮�
//				String stepCategory = sheet.getCell(1, i+1).getContents(); //鎿嶄綔绫诲埆
//				String stationName = sheet.getCell(12, i+1).getContents(); //宸ヤ綅鍚嶇О
//				String productionName = sheet.getCell(13, i+1).getContents(); //浜у搧鍚嶇О
//				String productionVr = sheet.getCell(14, i+1).getContents(); //浜у搧瑙勫垯
//				String numbers = sheet.getCell(3, i+1).getContents(); //鏁伴噺
//				String stepno = sheet.getCell(8, i+1).getContents();  //宸ュ簭
//				String gunNo = sheet.getCell(4, i+1).getContents();  //鏋彿
//				String programNo = sheet.getCell(5, i+1).getContents(); //绋嬪簭鍙�
//				String bolteqs = sheet.getCell(11, i+1).getContents();	//宸ヤ綅鑺傛媿
//				String sleeveNo = sheet.getCell(7, i+1).getContents(); //濂楃瓛鍙�
//
//				String photoNo = sheet.getCell(15, i+1).getContents(); //鐩告満鍙�
//				String reworktimes = sheet.getCell(16, i+1).getContents(); //杩斿伐娆℃暟
//				String chekorno = sheet.getCell(17, i+1).getContents(); //鏄惁鏍￠獙
//				String revieworno = sheet.getCell(18, i+1).getContents(); //鏄惁杩芥函
//				String exactorno = sheet.getCell(19, i+1).getContents(); //鐗╂枡绫诲埆
//				String aLimit = sheet.getCell(20, i+1).getContents(); //瑙掑害涓婇檺
//				String tLimit = sheet.getCell(21, i+1).getContents(); //瑙勭煩涓婇檺
//				String picturnPath = sheet.getCell(22, i+1).getContents(); //鍥剧墖璺緞
//				String boltJson = sheet.getCell(23, i+1).getContents(); //铻烘爴json鏁版嵁
//
///*				if(chekorno!="" && chekorno!=null ){
//					if(Integer.parseInt(chekorno)!=0 && Integer.parseInt(chekorno)!=1){
//						returnMap.put("msg", 10);//杩斿洖閿欒淇℃伅锛�"鏄惁鏍￠獙"鍙兘杈撳叆0鍜�1
//						return returnMap;
//					}
//				}
//				if(revieworno!=""){
//					if(Integer.parseInt(revieworno)!=0 && Integer.parseInt(revieworno)!=1){
//						returnMap.put("msg", 11);//杩斿洖閿欒淇℃伅锛�"鏄惁杩芥函"鍙兘杈撳叆0鍜�1
//						return returnMap;
//					}
//				}
//				if(exactorno!=""){
//					if(Integer.parseInt(exactorno)!=0 && Integer.parseInt(exactorno)!=1){
//						returnMap.put("msg", 12);//杩斿洖閿欒淇℃伅锛�"鐗╂枡绫诲埆"鍙兘杈撳叆0鍜�1
//						return returnMap;
//					}
//				}*/
//
//
//				set.add(stationName);  //宸ヤ綅琛�
//				set1.add(stepCategory);	//绫诲埆琛�
//				set2.add(productionName);  //浜у搧琛ㄣ�佸悕绉�
//				set3.add(productionVr);	//浜у搧琛ㄣ�佹潯鐮佽鍒�
//				set4.add(numbers);
//				set4.add(stepno);
//				set4.add(gunNo);
//				set4.add(programNo);
//				set4.add(bolteqs);
//				set4.add(sleeveNo);
//			}
//			for (String str : set) {
//				if (reportService.countStation(str)>0) {count++;} //鏌ョ湅宸ヤ綅琛ㄤ腑鏈夊嚑涓悕绉颁竴鏍风殑宸ヤ綅
//			}
//			for (String str : set1) {
//				if (!str.equals("扫描二级总成")&&!str.equals("扫描总成码确认")&&!str.equals("扫描总成")&&!str.equals("扫描物料")&&
//						!str.equals("扫描物料(唯一编码)")&&!str.equals("扫描模组")&&!str.equals("扫描电芯")&&!str.equals("拧紧")
//						&&!str.equals("气密性测试")&&!str.equals("通断测试")&&!str.equals("拍照")
//						&&!str.equals("打印")&&!str.equals("用户录入")&&!str.equals("电性能测试")
//						&&!str.equals("结束")&&!str.equals("扫描员工号")&&!str.equals("称重")) {
//					count1++;
//				}
//			}
//			for (String str : set2) {
//				if (reportService.countProductionName(str)>0) {
//					count2++;   //鏌ヨ浜у搧琛ㄤ腑鏄惁鏈夌浉鍚岀殑浜у搧
//				}
//			}
//			for (String str : set3) {
//				if (reportService.countPackPn(str)>0) {count3++;}  //鏌ヨ浜у搧琛ㄤ腑鏄惁鏈夌浉鍚岀殑鏉＄爜瑙勫垯
//			}
//			for (String string : set4) {
//				try {
//					if (string!=null && string.trim().length()<1 && !string.equals("")) {
//						Long.parseLong(string);  //string绫诲瀷杞琇ong
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					ToolUtils.errorLog(this, e, request);
//					returnMap.put("msg", 3);  //鏁伴噺銆佸伐搴忎笉鏄暟瀛楃被鍨�
//					return returnMap;
//				}
//			}
//			if (set3.size()>1) {
//				returnMap.put("msg", 6);  //excle涓潯鐮佽鍒欎笉涓�鑷�
//				return returnMap;
//			}
//			if (count<set.size()) {  //Excel涓殑宸ヤ綅鏁伴噺灏忎簬宸ヤ綅琛ㄤ腑鐨勬暟閲�
//				returnMap.put("msg", 5);//杩斿洖閿欒淇℃伅锛氬鍏ユ暟鎹�樺伐浣嶅悕绉扳�欐牸寮忛敊璇�
//				return returnMap;
//			}
//			if (count1>0) {   //绫诲埆琛ㄤ腑娌℃湁姝ょ被鍒�
//				returnMap.put("msg", 4);//杩斿洖閿欒淇℃伅锛氬鍏ユ暟鎹�樻搷浣滅被鍒�欐牸寮忛敊璇�
//				return returnMap;
//			}
//			if (set2.size()>1) {  //excle涓湁涓や釜浜у搧
//				returnMap.put("msg", 9);//杩斿洖閿欒淇℃伅锛氬鍏ユ暟鎹�樹骇鍝佷俊鎭�欐牸寮忛敊璇�
//				return returnMap;
//			}
//			if (count2<set2.size()&&count3>=set3.size()) {
//				returnMap.put("msg", 7);//杩斿洖閿欒淇℃伅锛氬鍏ユ暟鎹�楶ACK PN鈥欐牸寮忛敊璇�
//				return returnMap;
//			}
//			for (int i = 1; i < rowsNum-1; i++) {//鎴戠殑excel绗竴琛屾槸鏍囬,鎵�浠� i浠�1寮�濮�
//				CMesRecipeVersionDetail reci = new CMesRecipeVersionDetail();
//				String stepCategory = sheet.getCell(1, i+1).getContents(); //鎿嶄綔绫诲埆
//				reci.setStepCategory(stepCategory);
//				String materialName = sheet.getCell(2, i+1).getContents(); //宸ュ簭鍚嶇О
//				reci.setMaterialName(materialName);
//				String numbers = sheet.getCell(3, i+1).getContents(); //鏁伴噺
//				reci.setNumbers(numbers);
//				String gunNo = sheet.getCell(4, i+1).getContents(); //鏋彿
//				reci.setGunNo(gunNo);
//				String programNo = sheet.getCell(5, i+1).getContents(); //绋嬪簭鍙�
//				reci.setProgramNo(programNo);
//				String materialpn = sheet.getCell(6, i+1).getContents(); //鐗╂枡PN
//				reci.setMaterialpn(materialpn);
//				String sleeveNo = sheet.getCell(7, i+1).getContents(); //濂楃瓛鍙�
//				reci.setSleeveNo(sleeveNo);
//				String stepno = sheet.getCell(8, i+1).getContents(); //宸ュ簭
//				reci.setStepno(Integer.parseInt(stepno));
//				String uploadCode = sheet.getCell(9, i+1).getContents(); //涓婁紶浠ｇ爜
//				reci.setUploadCode(uploadCode);
//				String feacode = sheet.getCell(10, i+1).getContents(); //鏍￠獙瑙勫垯
//				reci.setFeacode(feacode);
//				String bolteqs = sheet.getCell(11, i+1).getContents(); //宸ヤ綅鑺傛媿
//				reci.setBolteqs(bolteqs);
//				String stationName = sheet.getCell(12, i+1).getContents(); //宸ヤ綅鍚嶇О
//				reci.setStationName(stationName);
//				String productionName = sheet.getCell(13, i+1).getContents(); //浜у搧鍚嶇О
//				reci.setProductionName(productionName);
//				String productionVr = sheet.getCell(14, i+1).getContents(); //浜у搧瑙勫垯
//				reci.setProductionVr(productionVr);
//
//				String photoNo = sheet.getCell(15, i+1).getContents(); //鐩告満鍙�
//				reci.setPhotoNo(photoNo);
//				String reworktimes = sheet.getCell(16, i+1).getContents(); //杩斿伐娆℃暟
//				reci.setReworktimes(reworktimes);
//				String chekorno = sheet.getCell(17, i+1).getContents(); //鏄惁鏍￠獙
//				reci.setChekorno(chekorno);
//				String revieworno = sheet.getCell(18, i+1).getContents(); //鏄惁杩芥函
//				reci.setRevieworno(revieworno);
//				String exactorno = sheet.getCell(19, i+1).getContents(); //鐗╂枡绫诲埆
//				reci.setExactorno(exactorno);
//				String aLimit = sheet.getCell(20, i+1).getContents(); //瑙掑害涓婇檺
//				reci.setaLimit(aLimit);
//				String tLimit = sheet.getCell(21, i+1).getContents(); //瑙勭煩涓婇檺
//				reci.settLimit(tLimit);
//				String picturnPath = sheet.getCell(22, i+1).getContents(); //鍥剧墖璺緞
//				reci.setPicturnPath(picturnPath);
//				String boltJson = sheet.getCell(23, i+1).getContents(); //铻烘爴json鏁版嵁
//				reci.setBoltjson(boltJson);
//				if(i==1){
//					CMesProductionT pro = recipeversionservice.findPID(productionVr); //鏍规嵁浜у搧瑙勫垯鏌ヨid鍜宯ame
//					int p_id = pro.getId();
//					String p_name = pro.getProductionName();
//					String recipeName = "";
//					Double listName = recipeversionservice.listName(p_id); //濡傛灉琛ㄤ腑鏃犳暟鎹�
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
//						recipeversionservice.addVersion(maps); //娣诲姞鐗堟湰琛�
//					}
//				}
//				Integer listId = recipeversionservice.listId(); //鏌ヨ鍒氭坊鍔犵殑鐗堟湰id
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
//	//鏍规嵁浜у搧瑙勫垯鏌ヨ鐗堟湰鍙�
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
//	//鏌ヨ鐗堟湰鏄庣粏
//	@RequestMapping("/recipeDatilListsTwo")
//	public String  recipeDatilLists(HttpServletRequest request,String packPn,String verid,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
//		List<CMesProductionT> productionVrList = reportService.getProductionVr(); //鎵�鏈変骇鍝�
//		request.setAttribute("productionVrList", productionVrList);
//		String findPName = recipeversionservice.findPName(packPn);
//		request.setAttribute("productionVr", findPName); //褰撳墠浜у搧
//		List<CMesRecipeVersion> findPVersion =recipeversionservice.findPVersion(findPName); //浜у搧涓嬬殑鐗堟湰
//		request.setAttribute("findPVersion", findPVersion);
//		String finds = recipeversionservice.findId(verid);
//		request.setAttribute("PVersion",finds); //褰撳墠鐗堟湰
//		PageHelper.startPage(page,13);
//		List<CMesRecipeVersionDetail> recipeDatilListsTwo = recipeversionservice.recipeDatilListsTwo(Integer.parseInt(packPn), Double.parseDouble(finds));
//		PageInfo<CMesRecipeVersionDetail> pageInfo = new PageInfo<>(recipeDatilListsTwo,8);
//		request.setAttribute("pageInfo", pageInfo);
//		return "tool_control/importRecipe";
//	}
//
//	//鏇存崲鐗堟湰
//	@RequestMapping(value="updates",produces = "text/plain;charset=utf-8")
//	@ResponseBody
//	@SuppressWarnings("all")
//	public String updates(HttpServletRequest request, String packPn,String verid){
//		String flag ="使用成功";
//		List<Integer> list = new ArrayList<>();
//		String findId = recipeversionservice.findId(verid);//ID杞崲涓簄ame
//		Integer findMaxId = recipeversionservice.findMaxId(); //鏈�澶х殑id
//		try {
//			List<CMesRecipeVersionDetail> recipeDatilListsTwo = recipeversionservice.recipeDatilListsTwo(Integer.parseInt(packPn),Double.parseDouble(findId));
//			for (CMesRecipeVersionDetail cMesRecipeVersionDetail : recipeDatilListsTwo) {
//				Integer findProductName = recipeversionservice.findProductName(cMesRecipeVersionDetail.getProductionName()); //浜у搧ID
//				Integer findStationName = recipeversionservice.findStationName(cMesRecipeVersionDetail.getStationName()); //宸ヤ綅id
//				if(findProductName==0||findStationName==0){
//					flag = "使用成功";
//					break;
//				}
//				Integer findPCId = recipeversionservice.findPCId(findProductName, findStationName); //閰嶆柟id
//				Integer findTId = recipeversionservice.findTId(cMesRecipeVersionDetail.getStepCategory()); //鎿嶄綔绫诲埆id
//				List<Integer> findRId = recipeversionservice.findRId(findPCId);
//				for (Integer integer : findRId) {
//					list.add(integer);
//				}
//				Map<String,Object> map = new HashMap<>();
//				map.put("recipe_id", String.valueOf(findPCId)); //閰嶆柟id
//				map.put("step_category",String.valueOf(findTId));  //鎿嶄綔绫诲埆
//				map.put("material_name",cMesRecipeVersionDetail.getMaterialName()); //宸ュ簭鍚嶇О
//				map.put("numbers", cMesRecipeVersionDetail.getNumbers());  //鏁伴噺
//				map.put("GUN_NO",cMesRecipeVersionDetail.getGunNo());  //鏋彿
//				map.put("PROGRAM_NO", cMesRecipeVersionDetail.getProgramNo()); //绋嬪簭鍙�
//				map.put("MATERIALPN", cMesRecipeVersionDetail.getMaterialpn());  //鐗╂枡PN
//				map.put("SLEEVE_NO", cMesRecipeVersionDetail.getSleeveNo());  //濂楃瓛鍙�
//				map.put("STEPNO",String.valueOf(cMesRecipeVersionDetail.getStepno()));  //宸ュ簭
//				map.put("UPLOAD_CODE",cMesRecipeVersionDetail.getUploadCode());  //涓婁紶浠ｇ爜
//				map.put("FEACODE", cMesRecipeVersionDetail.getFeacode());  //鏍￠獙瑙勫垯
//				map.put("BOLTEQS", cMesRecipeVersionDetail.getBolteqs());//宸ヤ綅鑺傛媿
//				map.put("photoNo",cMesRecipeVersionDetail.getPhotoNo());  //鐩告満鍙�
//				map.put("reworktimes", cMesRecipeVersionDetail.getReworktimes()); //杩斿伐娆℃暟
//				map.put("chekorno", cMesRecipeVersionDetail.getChekorno()); //鏄惁鏍￠獙
//				map.put("revieworno", cMesRecipeVersionDetail.getRevieworno());  //鏄惁杩芥函
//				map.put("exactorno", cMesRecipeVersionDetail.getExactorno());  //鐗╂枡绫诲埆
//				map.put("aLimit", cMesRecipeVersionDetail.getaLimit());  //瑙掑害涓婇檺
//				map.put("tLimit", cMesRecipeVersionDetail.gettLimit()); //鎵煩涓婇檺
//				map.put("picturnPath", cMesRecipeVersionDetail.getPicturnPath()); //鍥剧墖璺緞
//				map.put("boltJson", cMesRecipeVersionDetail.getBoltjson()); //铻烘爴json鏁版嵁
//				recipeversionservice.addrecipe(map); //娣诲姞鏂伴厤鏂�
//			}
//			for (Integer in : list) {
//				if(in<=findMaxId){
//					recipeversionservice.deleterecipe(in);
//				}//鍒犻櫎鏃ч厤鏂规槑缁�
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//			flag = "使用失败";
//			return flag;
//		}
//		return flag;
//	}
//
//	@RequestMapping(value="deleteVersion",produces = "text/plain;charset=utf-8")
//	@ResponseBody
//	public String delete(HttpServletRequest request, String verid){
//		String flag = "删除成功";
//		try {
//			recipeversionservice.ddelteVersionDetil(Integer.parseInt(verid));
//			recipeversionservice.deleteVersion(Integer.parseInt(verid));
//		} catch (Exception e) {
//			e.printStackTrace();
//			ToolUtils.errorLog(this, e, request);
//			flag = "删除失败";
//			return flag;
//		}
//		return flag;
//	}
//
//	/*
//	 * 下载模板
//	 */
//	@RequestMapping("/excelRecipe")
//	@ResponseBody
//	public void excelRecipe(HttpServletRequest request, HttpServletResponse response){
//		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
//		try {
//			HSSFWorkbook  book = new HSSFWorkbook();  //创建一个工作簿
//			String headers[] = {"编号","操作类别","工序名称","数量","枪号","程序号","物料PN","套筒号","工序","上传代码",
//					"条码规则","工位节拍","工位名称","产品名称","产品规则","相机号","返工次数","是否校验",
//					"是否追溯","物料类别","角度上限","扭矩上限","图片路径","螺栓json数据"};
//			HSSFSheet sheet = book.createSheet("Sheet1"); // 创建一个sheet页、
//			sheet.setColumnWidth(0, 100 * 60); // 设置列宽度
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
//			font.setColor(IndexedColors.BLACK.index); // 字体颜色
//			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
//			font.setFontName("宋体"); // 字体样式
//			font.setFontHeightInPoints((short) 17); // 字体大小
//
//			HSSFCellStyle cellStyle = book.createCellStyle();
//			cellStyle.setFont(font);
//			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
//			cellStyle.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
//			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//			cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
//			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
//			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//
//			HSSFCellStyle cellStyle1 = book.createCellStyle();
//			cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
//			cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
//			cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
//			cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//
//			CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 23);
//			sheet.addMergedRegion(cra);
//			RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, book);
//
//			HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
//			HSSFCell createCellbt = createRowbt.createCell(0);
//			createRowbt.setHeight((short) 480);
//			createCellbt.setCellValue("配方模板");
//			createCellbt.setCellStyle(cellStyle);
//
//			HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
//			createRow.setHeight((short) 380);
//			for (int i = 0; i < headers.length; i++) {
//				HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
//				createCell.setCellValue(headers[i]);
//				createCell.setCellStyle(cellStyle);
//			}
//
//			HSSFRow createRow2 = sheet.createRow(2);  //第二行
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
//			String headers[] = {"编号","操作类别","工序名称","数量","枪号","程序号","物料PN","套筒号","工序","上传代码",
//					"条码规则","工位节拍","工位名称","产品名称","产品规则","相机号","返工次数","是否校验",
//					"是否追溯","物料类别","角度上限","扭矩上限","图片路径","螺栓json数据"};
//	/*		ExcelUtil.RecipeExcel(findRecipe,book,headers);*/
//			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//}

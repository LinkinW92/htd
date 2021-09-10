package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesRecipeVersion;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesImportRecipeService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.ImportExcel;
import com.skeqi.mes.util.IsNumber;
import com.skeqi.mes.util.PageInfoUtil;
import com.skeqi.mes.util.ToolUtils;

import sun.misc.BASE64Encoder;

/**
 * 导入配方
 * @author : FQZ
 * @Package: com.skeqi.mes.controller.all
 * @date   : 2020年3月16日 上午9:17:50
 */
@Controller
@RequestMapping("skq")
public class CMesImportRecipeController {

	@Autowired
	private CMesImportRecipeService service;

	static BASE64Encoder encoder = new BASE64Encoder();


	@RequestMapping("/importRecipe")
	public String recipeDatilLists(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) throws Exception{
		PageHelper.startPage(page,20);
		String pvr = request.getParameter("pvr");   //产品规则
		String vid = request.getParameter("vid");   //版本ID
		List<CMesProductionT> findAllProduction = service.findAllProduction(null);   //产品列表
		CMesRecipeVersion version = new CMesRecipeVersion();
		if(pvr!=null && pvr!=""){
			version.setProductionId(Integer.parseInt(pvr));
		}else{
			if(findAllProduction.size()>0){
				version.setProductionId(findAllProduction.get(0).getId());
			}else{
				version.setProductionId(null);
			}
		}
		List<CMesRecipeVersion> findAllRecipeVersion = service.findAllRecipeVersion(version);  //版本列表
		if(vid!=null && vid!="" && !"000".toString().equals(vid)){   //根据配方版本查询
			List<CMesRecipeVersionDetail> findAllRecipeVersionDetail = service.findAllRecipeVersionDetail(Integer.parseInt(vid));   //配方版本明细列表
			PageInfoUtil<CMesRecipeVersionDetail> pageInfo = new PageInfoUtil<>(findAllRecipeVersionDetail,5,20,page);
			request.setAttribute("pageInfo", pageInfo);

		}else{    //查询配方明细
			CMesProductionT pro = new CMesProductionT();
			if(pvr!=null && pvr!=""){
				pro.setId(Integer.parseInt(pvr));
			}
			List<CMesProductionT> findAllProductions = service.findAllProduction(pro);   //查询当前产品ID
			List<CMesRecipeDatilT> findAllRecipeDetail = null;
			if(findAllProductions.size()>0){
				findAllRecipeDetail = service.findAllRecipeDetail(findAllProductions.get(0).getId());  //查询配方明细
			}else{
				findAllRecipeDetail = service.findAllRecipeDetail(null);  //查询配方明细
			}
			PageInfoUtil<CMesRecipeDatilT> pageInfo = new PageInfoUtil<>(findAllRecipeDetail,5,20,page);
			request.setAttribute("pageInfo", pageInfo);
		}

		request.setAttribute("findAllRecipeVersion",findAllRecipeVersion);
		request.setAttribute("findAllProduction",findAllProduction);
		request.setAttribute("pvr",pvr);
		request.setAttribute("vid",vid);

		return "tool_control/importRecipe";
	}

	/**
	 * 导出配方
	* @author FQZ
	* @date 2020年3月23日下午12:24:41
	 */
	@RequestMapping("/downloadMoudle")
	public void downloadMoudle(HttpServletRequest request, HttpServletResponse response){
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			HSSFWorkbook  book = new HSSFWorkbook();  //创建一个工作簿
			String headers[] = {"编号","操作类别","工序名称","数量","枪号","程序号","物料PN","套筒号","工序","上传代码",
					"条码规则","工位节拍","工位名称","产品名称","产品规则","相机号","返工次数","是否校验",
					"是否追溯","物料类别","角度上限","扭矩上限","螺栓json数据","图片"};
			HSSFSheet sheet = book.createSheet("Sheet1"); // 创建一个sheet页、
			sheet.setColumnWidth(0, 100 * 60); // 设置列宽度
			sheet.setColumnWidth(1, 100 * 60);
			sheet.setColumnWidth(2, 100 * 60);
			sheet.setColumnWidth(3, 100 * 60);
			sheet.setColumnWidth(4, 100 * 60);
			sheet.setColumnWidth(5, 100 * 60);
			sheet.setColumnWidth(6, 100 * 60);
			sheet.setColumnWidth(7, 100 * 60);
			sheet.setColumnWidth(8, 100 * 60);
			sheet.setColumnWidth(9, 100 * 60);
			sheet.setColumnWidth(10, 100 * 60);
			sheet.setColumnWidth(11, 100 * 60);
			sheet.setColumnWidth(12, 100 * 60);
			sheet.setColumnWidth(13, 100 * 60);
			sheet.setColumnWidth(14, 100 * 60);
			sheet.setColumnWidth(15, 100 * 60);
			sheet.setColumnWidth(16, 100 * 60);
			sheet.setColumnWidth(17, 100 * 60);
			sheet.setColumnWidth(18, 100 * 60);
			sheet.setColumnWidth(19, 100 * 60);
			sheet.setColumnWidth(20, 100 * 60);
			sheet.setColumnWidth(21, 100 * 60);
			sheet.setColumnWidth(22, 100 * 60);
			sheet.setColumnWidth(23, 100 * 60);
			HSSFFont font = book.createFont();

			font.setColor(IndexedColors.BLACK.index); // 字体颜色
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
			font.setFontName("宋体"); // 字体样式
			font.setFontHeightInPoints((short) 17); // 字体大小

			HSSFCellStyle cellStyle = book.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
			cellStyle.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

			HSSFCellStyle cellStyle1 = book.createCellStyle();
			cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
			cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
			cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
			cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

			CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 23);
			sheet.addMergedRegion(cra);
			RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, book);

			HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
			HSSFCell createCellbt = createRowbt.createCell(0);
			createRowbt.setHeight((short) 480);
			createCellbt.setCellValue("配方模板");
			createCellbt.setCellStyle(cellStyle);

			HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
			createRow.setHeight((short) 380);
			for (int i = 0; i < headers.length; i++) {
				HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
				createCell.setCellValue(headers[i]);
				createCell.setCellStyle(cellStyle);
			}

			HSSFRow createRow2 = sheet.createRow(2);  //第二行
			createRow2.setHeight((short) 280);
			for(int i=0;i<=23;i++){
				HSSFCell createCellbt0 = createRow2.createCell(i);
				createCellbt0.setCellValue("");
				createCellbt0.setCellStyle(cellStyle1);
			}
			ExcelUtil.export(response, book, "配方模板" + ".xls");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
	}

	/**
	 * 删除配方
	* @author FQZ
	* @date 2020年3月23日下午2:23:53
	 */
	@RequestMapping("/deleteVersion")
	@ResponseBody
	@Transactional
	public JSONObject deleteVersion(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String pvr = request.getParameter("pvr");   //产品规则
		String vid = request.getParameter("vid");   //版本ID
		try {
			service.deleteVersion(Integer.parseInt(vid));   //删除
			service.deleteVersionDetail(Integer.parseInt(vid));
			json.put("code",0);
			return json;
		} catch (Exception e) {
			json.put("code", 1);
			return json;
			// TODO: handle exception
		}
	}


	/**
	 * 导出配方
	* @author FQZ
	* @date 2020年3月20日下午2:39:01
	 */
	@RequestMapping("/exportRecipeVersion")
	public void exportRecipeDatil(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pvr = request.getParameter("pvr");   //产品ID
		String vid = request.getParameter("vid");   //版本ID
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		if(!"000".toString().equals(vid)){   //如果有版本号
			List<CMesRecipeVersionDetail> findAllRecipeVersionDetail = service.findAllRecipeVersionDetail(Integer.parseInt(vid));   //配方版本明细列表
			HSSFWorkbook  book = new HSSFWorkbook();
			String headers[] = {"编号","操作类别","工序名称","数量","枪号","程序号","物料PN","套筒号","工序","上传代码",
					"条码规则","工位节拍","工位名称","产品名称","产品规则","相机号","返工次数","是否校验",
					"是否追溯","物料类别","角度上限","扭矩上限","螺栓json数据"};
			ExcelUtil.RecipeVersionExport(findAllRecipeVersionDetail,book,headers);
			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
		}else{   //如果没有版本号则查询配方明细
			List<CMesRecipeDatilT> findAllRecipeDetail = service.findAllRecipeDetail(Integer.parseInt(pvr));  //查询配方明细
			HSSFWorkbook  book = new HSSFWorkbook();
			String headers[] = {"编号","操作类别","工序名称","数量","枪号","程序号","物料PN","套筒号","工序","上传代码",
					"条码规则","工位节拍","工位名称","产品名称","产品规则","相机号","返工次数","是否校验",
					"是否追溯","物料类别","角度上限","扭矩上限","螺栓json数据","图片"};
			ExcelUtil.RecipeExcel(response,findAllRecipeDetail,book,headers,s.format(new Date()) + ".xls");
/*			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");*/
		}
	}

	/**
	 * 使用配方
	* @author FQZ
	* @date 2020年3月23日下午3:01:24
	 */
	@RequestMapping("/userRecipe")
	@ResponseBody
	@Transactional
	public JSONObject userRecipe(HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("code",0);
		String pvr = request.getParameter("pvr");   //产品规则
		String vid = request.getParameter("vid");   //版本ID
		try {
			//保存配方ID
			HashSet<Integer> set = new HashSet<>();
			//根据版本ID获取版本详情
			List<CMesRecipeVersionDetail> findAllRecipeVersionDetail = service.findAllRecipeVersionDetail(Integer.parseInt(vid));
			//添加配方明细
			List<CMesRecipeDatilT> list = new ArrayList<CMesRecipeDatilT>();
			//循环添加到配方明细实体类中
			for (CMesRecipeVersionDetail cMesRecipeVersionDetail : findAllRecipeVersionDetail) {
				//根据产品和工位查询是否有此配方
				List<CMesProductionRecipeT> findProductionRecipe = service.findProductionRecipe(cMesRecipeVersionDetail.getProductionName(), cMesRecipeVersionDetail.getStationName());
				if(findProductionRecipe.size()==0){
					json.put("msg","没有["+cMesRecipeVersionDetail.getProductionName()+"]产品和["+cMesRecipeVersionDetail.getStationName()+"]工位的配方，请先配置配方");
					return json;
				}else if(findProductionRecipe.size()>1){
					json.put("msg", "配方数量大于1,请删除多余的配方！");
					return json;
				}
				set.add(findProductionRecipe.get(0).getId());
				CMesRecipeDatilT recipe = new CMesRecipeDatilT();
				List<CMesRecipeTypeT> findRecipeType = service.findRecipeType(cMesRecipeVersionDetail.getStepCategory());  //配方类别
				if(findRecipeType.size()>0){
					recipe.setStepCategory(findRecipeType.get(0).getId().toString());
				}else{
					json.put("msg","没有["+cMesRecipeVersionDetail.getStepCategory()+"]的配方类别");
				}
				recipe.setMaterialName(cMesRecipeVersionDetail.getMaterialName());   //步骤名称
				recipe.setGunNo(cMesRecipeVersionDetail.getGunNo());   //枪号
				recipe.setProgramNo(cMesRecipeVersionDetail.getProgramNo());  //程序号
				recipe.setPhotoNo(cMesRecipeVersionDetail.getPhotoNo());   //相机号
				recipe.setSleeveNo(cMesRecipeVersionDetail.getSleeveNo());   //套筒号
				recipe.setReworktimes(cMesRecipeVersionDetail.getReworktimes());   //返工次数
				recipe.setFeacode(cMesRecipeVersionDetail.getFeacode());   //校验规则
				recipe.setChekorno(cMesRecipeVersionDetail.getChekorno());    //是否校验
				recipe.setRevieworno(cMesRecipeVersionDetail.getRevieworno());   //是否追溯
				recipe.setexactorno(cMesRecipeVersionDetail.getExactorno());    //精追批追
				recipe.setMaterialpn(cMesRecipeVersionDetail.getMaterialpn());    //物料PN
				recipe.setBolteqs(cMesRecipeVersionDetail.getBolteqs());   //工位节拍
				recipe.setaLimit(cMesRecipeVersionDetail.getaLimit());   //角度上线
				recipe.settLimit(cMesRecipeVersionDetail.gettLimit());   //扭矩上线
				recipe.setRecipeId(findProductionRecipe.get(0).getId());   //配方ID
				recipe.setUploadCode(cMesRecipeVersionDetail.getUploadCode());   //上传代码
				recipe.setStepno(cMesRecipeVersionDetail.getStepno().toString());   //步序
				recipe.setNumbers(cMesRecipeVersionDetail.getNumbers());   //数量
				recipe.setPicturnPath(cMesRecipeVersionDetail.getPicturnPath());   //图片路径
				recipe.setBoltjson(cMesRecipeVersionDetail.getBoltjson());  //螺栓json数据
				recipe.setPathBinary(cMesRecipeVersionDetail.getPathBinary());  //图片二进制
				list.add(recipe);
			}
			//删除配方明细
			for (Integer i : set) {
				service.delRecipeDetail(i);
			}
			//循环添加
			for(CMesRecipeDatilT recipe : list){
				service.addRecipeDetail(recipe);
			}
			json.put("code", 1);
 		} catch (Exception e) {
 			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
 			json.put("msg","未知错误");
 			return json;
			// TODO: handle exception
		}
		return json;
	}

	/**
	 * 导入配方
	* @author FQZ
	 * @throws Exception
	 * @date 2020年3月23日下午12:24:52
	 */
	@RequestMapping("/importRecipeVersion")
	@ResponseBody
	@Transactional
	public JSONObject importRecipeVersion(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile excelFile) throws Exception{
		JSONObject json = new JSONObject();
		json.put("code",0);
		String fileName = excelFile.getOriginalFilename(); // 获取文件名
		InputStream in = null;
		try {
			in = excelFile.getInputStream();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		Map<Integer, List<Object>> map = new HashMap<Integer, List<Object>>();
		Map<String,String> maps = new HashMap<>();
		HSSFWorkbook  workbook = new HSSFWorkbook(in);
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		Map<String, PictureData> pictures1 = getPictures1(sheetAt);
		if(pictures1!=null){
			 maps= new HashMap<>();
			Set<String> keySet = pictures1.keySet();
			for (String string : keySet) {
				String[] split = string.split("-");
				PictureData pictureData = pictures1.get(string);
				byte[] data = pictureData.getData();
				String path = encoder.encodeBuffer(data).trim();
				maps.put(split[0],path);
			}
			try {
				if(in!=null){
					in.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			}
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			InputStream ins = excelFile.getInputStream();
			map = ImportExcel.getIoValue(ins, fileName); // 调用封装的方法获取数据
			List<Object> list = map.get(2);
			if(!list.get(0).toString().trim().equals("编号")){
				json.put("msg","第一列名称错误");
				return json;
			}
			if(!list.get(1).toString().trim().equals("操作类别")){
				json.put("msg","第二列名称错误");
				return json;
			}
			if(!list.get(2).toString().trim().equals("工序名称")){
				json.put("msg","第三列名称错误");
				return json;
			}
			if(!list.get(3).toString().trim().equals("数量")){
				json.put("msg","第四列名称错误");
				return json;
			}
			if(!list.get(4).toString().trim().equals("枪号")){
				json.put("msg","第五列名称错误");
				return json;
			}
			if(!list.get(5).toString().trim().equals("程序号")){
				json.put("msg","第六列名称错误");
				return json;
			}
			if(!list.get(6).toString().trim().equals("物料PN")){
				json.put("msg","第七列名称错误");
				return json;
			}
			if(!list.get(7).toString().trim().equals("套筒号")){
				json.put("msg","第八列名称错误");
				return json;
			}
			if(!list.get(8).toString().trim().equals("工序")){
				json.put("msg","第九列名称错误");
				return json;
			}
			if(!list.get(9).toString().trim().equals("上传代码")){
				json.put("msg","第十列名称错误");
				return json;
			}
			if(!list.get(10).toString().trim().equals("条码规则")){
				json.put("msg","第十一列名称错误");
				return json;
			}
			if(!list.get(11).toString().trim().equals("工位节拍")){
				json.put("msg","第十二列名称错误");
				return json;
			}
			if(!list.get(12).toString().trim().equals("工位名称")){
				json.put("msg","第十三列名称错误");
				return json;
			}
			if(!list.get(13).toString().trim().equals("产品名称")){
				json.put("msg","第十四列名称错误");
				return json;
			}
			if(!list.get(14).toString().trim().equals("产品规则")){
				json.put("msg","第十五列名称错误");
				return json;
			}
			if(!list.get(15).toString().trim().equals("相机号")){
				json.put("msg","第十六列名称错误");
				return json;
			}
			if(!list.get(16).toString().trim().equals("返工次数")){
				json.put("msg","第十七列名称错误");
				return json;
			}
			if(!list.get(17).toString().trim().equals("是否校验")){
				json.put("msg","第十八列名称错误");
				return json;
			}
			if(!list.get(18).toString().trim().equals("是否追溯")){
				json.put("msg","第十九列名称错误");
				return json;
			}
			if(!list.get(19).toString().trim().equals("物料类别")){
				json.put("msg","第二十列名称错误");
				return json;
			}
			if(!list.get(20).toString().trim().equals("角度上限")){
				json.put("msg","第二十一列名称错误");
				return json;
			}
			if(!list.get(21).toString().trim().equals("扭矩上限")){
				json.put("msg","第二十二列名称错误");
				return json;
			}
			if(!list.get(22).toString().trim().equals("螺栓json数据")){
				json.put("msg","第二十三列名称错误");
				return json;
			}
			List<CMesRecipeVersionDetail> li = new ArrayList<CMesRecipeVersionDetail>();
			List<String> pname = new ArrayList<String>();  //存放产品名称
			List<String> pvr = new ArrayList<String>();  //存放产品规则
			String productionName = "";
			String productionVr = "";
			Integer id = null;  //产品ID
			for (int i = 3; i <= map.size(); i++) {
				List<Object> list2 = map.get(i);
				CMesRecipeVersionDetail versionDetail = new CMesRecipeVersionDetail();
				for (int j = 1; j <= list2.size(); j++) {
					if(j==1){  //操作类别
						if(!list2.get(j).equals("") && list2.get(j)!=null){
							List<CMesRecipeTypeT> findRecipeType = service.findRecipeType(list2.get(j).toString().trim());
							if(findRecipeType.size()==0){
								json.put("msg","第"+i+"行操作类别不存在");
								return json;
							}else{
								versionDetail.setStepCategory(list2.get(j).toString());
							}
						}else{
							json.put("msg","第"+i+"行操作类别不能为空");
							return json;
						}
					}
					if(j==2){  //工序名称
						if(!list2.get(j).equals("") && list2.get(j)!=null){
							versionDetail.setMaterialName(list2.get(j).toString());
						}else{
							json.put("msg","第"+i+"行工序名称不能为空");
							return json;
						}
					}
					if(j==8){   //工序
						if(!list2.get(j).equals("") && list2.get(j)!=null){
							boolean numeric = IsNumber.isNumeric(list2.get(j).toString());
							if(numeric){
								versionDetail.setStepno(Integer.parseInt(list2.get(j).toString()));
							}else{
								json.put("msg","第"+i+"行工序必须为数字");
								return json;
							}
						}else{
							json.put("msg","第"+i+"行工序不能为空");
							return json;
						}
					}
					if(j==12){   //工位名称
						if(!list2.get(j).equals("") && list2.get(j)!=null){
							CMesStationT findStation = service.findStation(list2.get(j).toString());
							if(findStation==null){
								json.put("msg","第"+i+"行工位名称不存在");
								return json;
							}else{
								versionDetail.setStationName(list2.get(j).toString());
							}
						}else{
							json.put("msg","第"+i+"行工位名称不能为空");
							return json;
						}
					}
					if(j==13){  //产品名称
						if(!list2.get(j).equals("") && list2.get(j)!=null && !list2.get(j+1).equals("") && list2.get(j+1)!=null){

							CMesProductionT t = new CMesProductionT();
							t.setProductionName(list2.get(j).toString());
							t.setProductionVr(list2.get(j+1).toString());
							CMesProductionT findProduction = service.findProduction(t);


							if(findProduction==null){
								json.put("msg","第"+i+"行产品名称和产品规则存在错误");
								return json;
							}else{
								versionDetail.setProductionName(list2.get(j).toString());
								versionDetail.setProductionVr(list2.get(j+1).toString());
								pname.add(list2.get(j).toString());
								pvr.add(list2.get(j+1).toString());
								if(i==3){
									productionName = list2.get(j).toString();
									productionVr = list2.get(j+1).toString();
									id=findProduction.getId();
								}
							}
						}else{
							json.put("msg","第"+i+"行产品名称/产品规则不能为空");
							return json;
						}
					}
					if(j==3){  //数量
						versionDetail.setNumbers(list2.get(j).toString());
					}
					if(j==4){  //枪号
						versionDetail.setGunNo(list2.get(j).toString());
					}
					if(j==5){  //程序号
						versionDetail.setProgramNo(list2.get(j).toString());
					}
					if(j==6){  //物料Pn
						versionDetail.setMaterialpn(list2.get(j).toString());
					}
					if(j==7){  //套筒号
						versionDetail.setSleeveNo(list2.get(j).toString());
					}
					if(j==9){  //上传代码
						versionDetail.setUploadCode(list2.get(j).toString());
					}
					if(j==10){  //条码规则
						versionDetail.setFeacode(list2.get(j).toString());
					}
					if(j==11){  //工位节拍
						versionDetail.setBolteqs(list2.get(j).toString());
					}
					if(j==15){  //相机号
						versionDetail.setPhotoNo(list2.get(j).toString());
					}
					if(j==16){  //返工次数
						versionDetail.setReworktimes(list2.get(j).toString());
					}
					if(j==17){  //是否校验
						if(!"0".toString().equals(list2.get(j).toString()) && !"1".toString().equals(list2.get(j).toString())){
							json.put("msg","是否校验只能是0或1");
							return json;
						}else{
							versionDetail.setChekorno(list2.get(j).toString());
						}
					}
					if(j==18){  //是否追溯
						if(!"0".toString().equals(list2.get(j).toString()) && !"1".toString().equals(list2.get(j).toString())){
							json.put("msg","是否追溯只能是0或1");
							return json;
						}else{
							versionDetail.setRevieworno(list2.get(j).toString());
						}
					}
					if(j==19){  //物料类别
						if(!"0".toString().equals(list2.get(j).toString()) && !"1".toString().equals(list2.get(j).toString())){
							json.put("msg","物料类别只能是0或1");
							return json;
						}else{
							versionDetail.setExactorno(list2.get(j).toString());
						}
					}
					if(j==20){  //角度上线
						versionDetail.setaLimit(list2.get(i).toString());
					}
					if(j==21){   //扭矩上线
						versionDetail.settLimit(list2.get(j).toString());
					}
					if(j==22){   //螺栓json数据
						versionDetail.setBoltjson(list2.get(j).toString());
					}
					if(maps!=null){
						Set<String> keySet2 = maps.keySet();
						for (String string : keySet2) {
							if(Integer.parseInt(string)==i-1){
								versionDetail.setPathBinary(maps.get(string));
							}
						}
					}
				}
				li.add(versionDetail);
			}
			for (String string : pvr) {
				if(!string.equals(productionVr)){
					json.put("msg","产品规则不一样");
					return json;
				}
			}
			for (String string : pname) {
				if(!string.equals(productionName)){
					json.put("msg","产品名称不一样");
					return json;
				}
			}
			CMesRecipeVersion version = new CMesRecipeVersion();
			CMesRecipeVersion findMaxVersion = service.findMaxVersion(id);   //查询该产品最大的版本号
			if(findMaxVersion==null){
				version.setRecipeName(1.0);
			}else{
				Double recipeName = findMaxVersion.getRecipeName();
				version.setRecipeName(recipeName+1);
			}
			version.setProductionId(id);
			version.setProductionName(productionName);
			version.setProductionVr(productionVr);

			service.addRecipeVersion(version);  //添加配方版本
			List<CMesRecipeVersion> findAllRecipeVersion = service.findAllRecipeVersion(null);
			for (CMesRecipeVersionDetail cMesRecipeVersionDetail : li) {
				cMesRecipeVersionDetail.setVersionId(findAllRecipeVersion.get(0).getRecipeId());
				service.addRecipeVersionDetail(cMesRecipeVersionDetail);
			}
			json.put("msg","导入成功");
			json.put("code",1);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("msg","未知错误");
			return json;
		}
		return json;
	}

	  public static Map<String, PictureData> getPictures1 (HSSFSheet sheet) throws IOException {
		    Map<String, PictureData> map = new HashMap<String, PictureData>();
		    if(sheet.getDrawingPatriarch()!=null){
			    List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
			    for (HSSFShape shape : list) {
			        if (shape instanceof HSSFPicture) {
			            HSSFPicture picture = (HSSFPicture) shape;
			            HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
			            PictureData pdata = picture.getPictureData();
			            String key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
			            map.put(key, pdata);
			        }
			    }
			    return map;
		    }
		    return null;
	  	}
}

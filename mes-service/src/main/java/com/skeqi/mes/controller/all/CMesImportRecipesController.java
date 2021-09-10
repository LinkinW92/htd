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
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;
import com.skeqi.mes.service.all.CMesImportRecipesService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.ImportExcel;
import com.skeqi.mes.util.PageInfoUtil;
import com.skeqi.mes.util.ToolUtils;

import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("skq")
public class CMesImportRecipesController {

	@Autowired
	public  CMesImportRecipesService service;

	static BASE64Encoder encoder = new BASE64Encoder();

	/**
	 * 进入页面
	* @author FQZ
	* @date 2020年4月6日下午1:12:12
	 */
	@RequestMapping("/recipeDetailList")
	public String recipeDetailList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) throws Exception{
		PageHelper.startPage(page,20);
		List<CMesTotalRecipeT> findAllTotalRecipe = service.findAllTotalRecipe();
		String tid = request.getParameter("tid");  //总配方名称
		if(tid==null || tid==""){
			if(findAllTotalRecipe.size()>0){
				tid =findAllTotalRecipe.get(0).getId().toString();
			}else{
				tid = "0";
			}
		}
		List<Map<String, Object>> findAllRecipeDetail = service.findAllRecipeDetail(Integer.parseInt(tid));
		PageInfoUtil pageInfo = new PageInfoUtil(findAllRecipeDetail,5,20,page);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("findAllTotalRecipe",findAllTotalRecipe);
		request.setAttribute("tid", tid);
		return "tool_control/importRecipe2";
	}

	/**
	 * 下载模板
	* @author FQZ
	* @date 2020年4月6日下午1:12:20
	 */
	@RequestMapping("/downloadMoudles")
	public void downloadMoudle(HttpServletRequest request, HttpServletResponse response){
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			HSSFWorkbook  book = new HSSFWorkbook();  //创建一个工作簿
			String headers[] = {"编号","总配方","产品名称","产线名称","工位配方","工位名称","操作类别","工序名称","数量","枪号","程序号","物料PN","套筒号","工序",
					"条码规则","工位节拍","相机号","返工次数","是否校验",
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
			sheet.setColumnWidth(24, 100 * 60);
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

			CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 24);
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
			for(int i=0;i<=24;i++){
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
	 * 导出配方
	* @author FQZ
	* @date 2020年4月6日下午1:18:40
	 */
	@RequestMapping("/exportRecipes")
	public void exportRecipe(HttpServletRequest request,HttpServletResponse response){
		String tid = request.getParameter("tid");   //总配方
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			List<Map<String, Object>> findAllRecipeDetail = service.findAllRecipeDetail(Integer.parseInt(tid));
			HSSFWorkbook  book = new HSSFWorkbook();
			String headers[] = {"编号","总配方","产品名称","产线名称","工位配方","所属工位","操作类别","工序名称","数量","枪号","程序号","物料PN","套筒号","工序",
					"条码规则","工位节拍","相机号","返工次数","是否校验",
					"是否追溯","物料类别","角度上限","扭矩上限","螺栓json数据","图片"};
			ExcelUtil.TotalRecipeExport(findAllRecipeDetail,book,headers);
			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
	}

	/**
	 * 导入配方
	* @author FQZ
	* @date 2020年4月6日下午2:13:17
	 */
	@RequestMapping("/importTotalRecipe")
	@ResponseBody
	@Transactional
	public JSONObject importTotalRecipe(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile excelFile) throws Exception{
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
		System.err.println("配方列表");
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
			if(!list.get(1).toString().trim().equals("总配方")){
				json.put("msg","第二列名称错误");
				return json;
			}
			if(!list.get(2).toString().trim().equals("产品名称")){
				json.put("msg","第三列名称错误");
				return json;
			}
			if(!list.get(3).toString().trim().equals("产线名称")){
				json.put("msg","第四列名称错误");
				return json;
			}
			if(!list.get(4).toString().trim().equals("工位配方")){
				json.put("msg","第五列名称错误");
				return json;
			}
			if(!list.get(5).toString().trim().equals("所属工位")){
				json.put("msg","第六列名称错误");
				return json;
			}
			if(!list.get(6).toString().trim().equals("操作类别")){
				json.put("msg","第七列名称错误");
				return json;
			}
			if(!list.get(7).toString().trim().equals("工序名称")){
				json.put("msg","第八列名称错误");
				return json;
			}
			if(!list.get(8).toString().trim().equals("数量")){
				json.put("msg","第九列名称错误");
				return json;
			}
			if(!list.get(9).toString().trim().equals("枪号")){
				json.put("msg","第十列名称错误");
				return json;
			}
			if(!list.get(10).toString().trim().equals("程序号")){
				json.put("msg","第十一列名称错误");
				return json;
			}
			if(!list.get(11).toString().trim().equals("物料PN")){
				json.put("msg","第十二列名称错误");
				return json;
			}
			if(!list.get(12).toString().trim().equals("套筒号")){
				json.put("msg","第十三列名称错误");
				return json;
			}
			if(!list.get(13).toString().trim().equals("工序")){
				json.put("msg","第十四列名称错误");
				return json;
			}
			if(!list.get(14).toString().trim().equals("条码规则")){
				json.put("msg","第十五列名称错误");
				return json;
			}
			if(!list.get(15).toString().trim().equals("工位节拍")){
				json.put("msg","第十六列名称错误");
				return json;
			}
			if(!list.get(16).toString().trim().equals("相机号")){
				json.put("msg","第十七列名称错误");
				return json;
			}
			if(!list.get(17).toString().trim().equals("返工次数")){
				json.put("msg","第十八列名称错误");
				return json;
			}
			if(!list.get(18).toString().trim().equals("是否校验")){
				json.put("msg","第十九列名称错误");
				return json;
			}
			if(!list.get(19).toString().trim().equals("是否追溯")){
				json.put("msg","第二十列名称错误");
				return json;
			}
			if(!list.get(20).toString().trim().equals("物料类别")){
				json.put("msg","第二十一列名称错误");
				return json;
			}
			if(!list.get(21).toString().trim().equals("角度上限")){
				json.put("msg","第二十二列名称错误");
				return json;
			}
			if(!list.get(22).toString().trim().equals("扭矩上限")){
				json.put("msg","第二十三列名称错误");
				return json;
			}
			if(!list.get(23).toString().trim().equals("螺栓json数据")){
				json.put("msg","第二十四列名称错误");
				return json;
			}
			if(!list.get(24).toString().trim().equals("图片")){
				json.put("msg","第二十五列名称错误");
				return json;
			}
			List<String> list1 = new ArrayList<String>();   //存放总配方名称
			List<String> list2 = new ArrayList<String>();    //存放产品名称
			List<String> list3 = new ArrayList<String>();    //存放产线名称


			List<CMesTotalRecipeT> totalList = new ArrayList<CMesTotalRecipeT>();   //存放总配方名称
			List<CMesRecipe> recipeList = new ArrayList<CMesRecipe>();   //存放工位配方
			List<CMesRecipeDatilT> recipeDatilList = new ArrayList<CMesRecipeDatilT>();   //存放配方明细
			String totalName = null;  //记录第一条总配方
			String productionName = null;   //记录第一条产品名称
			String lineName =null ;   //记录第一条产线名
			Integer findLineId = null;
			for (int i = 3; i <= map.size(); i++) {
				List<Object> lists = map.get(i);
				CMesTotalRecipeT total =  new CMesTotalRecipeT();
				CMesRecipe recipe = new CMesRecipe();
				CMesRecipeDatilT datil = new CMesRecipeDatilT();
				for (int j = 1; j < lists.size(); j++) {
					//总配方
					if(j==1){
						if(lists.get(j)==null){
							json.put("msg","[总配方] 不能为空");
							return json;
						}else{
							if(i==3){
								totalName = lists.get(j).toString();
							}
							list1.add(lists.get(j).toString());
							total.setTotalRecipeName(lists.get(j).toString());
						}
					}

					//产品名称
					if(j==2){
						if(lists.get(j)==null){
							json.put("msg","[产品名称] 不能为空");
							return json;
						}else{
							Integer findProductionId = service.findProductionId(lists.get(j).toString());
							if(findProductionId==null || findProductionId==0){
								json.put("msg","不存在["+lists.get(i).toString()+"]产品");
								return json;
							}
							if(i==3){
								productionName = lists.get(j).toString();
							}
							list2.add(lists.get(j).toString());
							total.setProductionId(findProductionId);
						}
					}

					//产线名称
					if(j==3){
						if(lists.get(j)==null){
							json.put("msg","[产线名称] 不能为空");
							return json;
						}else{
							 findLineId = service.findLineId(lists.get(j).toString());
							if(findLineId==null || findLineId==0){
								json.put("msg","不存在["+lists.get(i).toString()+"]产线");
								return json;
							}
							if(i==3){
								lineName = lists.get(j).toString();
							}
							list3.add(lists.get(j).toString());
							total.setLineId(findLineId);
						}
					}


					//工位配方
					if(j==4){
						if(lists.get(j)==null){
							json.put("msg","[工位配方] 不能为空");
							return json;
						}else{
							System.out.println("工位配方"+lists.get(j).toString());
							recipe.setRecipeName(lists.get(j).toString());
							datil.setRecipeName(lists.get(j).toString());
						}
					}
					//所属工位
					if(j==5){
						if(lists.get(j)==null){
							json.put("msg","[所属工位] 不能为空");
							return json;
						}else{
							Integer findByNameStation = service.findByNameStation(lists.get(j).toString(),findLineId);
							if(findByNameStation==null || findByNameStation==0){
								json.put("msg","不存在 ["+lists.get(j)+"] 工位");
								return json;
							}else{
								System.out.println("工位ID"+findByNameStation);
								recipe.setStationId(findByNameStation);
							}
						}
					}
					//操作类别
					if(j==6){
						if(lists.get(j)==null){
							json.put("msg","[操作类别] 不能为空");
							return json;
						}else{
							Integer findByNameType = service.findByNameType(lists.get(j).toString());
							if(findByNameType==0){
								json.put("msg","不存在 ["+lists.get(j)+"} 操作类别");
								return json;
							}else{
								datil.setStepCategory(findByNameType.toString());
							}
						}
					}
					//工序名称
					if(j==7){
						if(lists.get(j)==null){
							json.put("msg","[工序名称] 不能为空");
							return json;
						}else{
							datil.setMaterialName(lists.get(j).toString());
						}
					}
					//数量
					if(j==8){
						if(lists.get(j)!=null){
							datil.setNumbers(lists.get(j).toString());
						}
					}
					//枪号
					if(j==9){
						if(lists.get(j)!=null){
							datil.setGunNo(lists.get(j).toString());
						}
					}
					//程序号
					if(j==10){
						if(lists.get(j)!=null){
							datil.setProgramNo(lists.get(j).toString());
						}
					}
					//物料PN
					if(j==11){
						if(lists.get(j)!=null){
							datil.setMaterialpn(lists.get(j).toString());
						}
					}
					//套筒号
					if(j==12){
						if(lists.get(j)!=null){
							datil.setSleeveNo(lists.get(j).toString());
						}
					}
					//工序
					if(j==13){
						if(lists.get(j)==null){
							json.put("msg","[工序] 不能为空");
							return json;
						}else{
							datil.setStepno(lists.get(j).toString());
						}
					}
					//条码规则
					if(j==14){
						if(lists.get(j)!=null){
							datil.setFeacode(lists.get(j).toString());
						}
					}
					//工位节拍
					if(j==15){
						if(lists.get(j)!=null){
							datil.setBolteqs(lists.get(j).toString());
						}
					}
					//相机号
					if(j==16){
						if(lists.get(j)!=null){
							datil.setPhotoNo(lists.get(j).toString());
						}
					}
					//返工次数
					if(j==17){
						if(lists.get(j)!=null){
							datil.setReworktimes(lists.get(j).toString());
						}
					}
					//是否校验
					if(j==18){
						if(lists.get(j)!=null){
							datil.setChekorno(lists.get(j).toString());
						}else{
							datil.setChekorno("0");
						}
					}
					//是否追溯
					if(j==19){
						if(lists.get(j)!=null){
							datil.setRevieworno(lists.get(j).toString());
						}else{
							datil.setRevieworno("0");
						}
					}
					//物料类别
					if(j==20){
						if(lists.get(j)!=null){
							datil.setexactorno(lists.get(j).toString());
						}else{
							datil.setexactorno("0");
						}
					}
					//角度上线
					if(j==21){
						if(lists.get(j)!=null){
							datil.setaLimit(lists.get(j).toString());
						}
					}
					//扭矩上线
					if(j==22){
						if(lists.get(j)!=null){
							datil.settLimit(lists.get(j).toString());
						}
					}
					//螺栓json数据
					if(j==23){
						if(lists.get(j)!=null){
							datil.setBoltjson(lists.get(j).toString());
						}
					}

					//图片
					if(maps!=null){
						Set<String> keySet2 = maps.keySet();
						for (String string : keySet2) {
							if(Integer.parseInt(string)==i-1){
								datil.setPathBinary(maps.get(string));
							}
						}
					}
				}
				totalList.add(total);
				recipeList.add(recipe);
				recipeDatilList.add(datil);
			}
			Set<String> sets = new HashSet<>();
			List<CMesRecipe> recipes = new ArrayList<>();
			for (CMesRecipe cMesRecipe : recipeList) {
				if(!sets.contains(cMesRecipe.getRecipeName())){
					sets.add(cMesRecipe.getRecipeName());
					recipes.add(cMesRecipe);
				}
			}


			//查看总配方是否一致
			for (String string : list1) {
				if(!string.equals(totalName)){
					json.put("msg","总配方不一致");
					return json;
				}
			}

			//查询产品 名称是否一致
			for (String string : list2) {
				if(!string.equals(productionName)){
					json.put("msg","产品名称不一致");
					return json;
				}
			}

			//查询产产线名称是否一致
			for (String string : list3) {
				if(!string.equals(lineName)){
					json.put("msg","产线名称不一致");
					return json;
				}
			}
			service.addTotalRecipe(totalList.get(0));   //添加总配方
			Integer findTotalId = service.findTotalId();   //查询最大的总配方ID
			for (CMesRecipe recipe : recipes) {
				recipe.setTotalId(findTotalId);
				service.addRecipe(recipe);   ///循环添加配方
				Integer findRecipeId = service.findRecipeId();
				for (CMesRecipeDatilT datil : recipeDatilList) {
					if(datil.getRecipeName().equals(recipe.getRecipeName())){
						datil.setRecipeId(findRecipeId);
						service.addRecipeDatil(datil);
					}
				}
			}
			json.put("msg","导入成功");
			json.put("code",0);
		} catch (ServicesException e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("msg",e.getMessage());
			return json;
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

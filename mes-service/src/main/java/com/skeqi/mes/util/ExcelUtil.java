package com.skeqi.mes.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.RegionUtil;

import com.skeqi.mes.pojo.CMesCheckoutListT;
import com.skeqi.mes.pojo.CMesDefectEntryT;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesPatrolT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;
import com.skeqi.mes.pojo.CMesReturnRepairT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

import sun.misc.BASE64Decoder;
public class ExcelUtil {

	 static BASE64Decoder decoder = new BASE64Decoder();


	//IQC、SQE
	public static void fileExcel(List<CMesIqcCheckT> list, HSSFWorkbook wr, String[] headers) throws Exception {

		//总行数
		int pages = 0;
		//list长度
		int size = list.size();
		//需要多少页
		int sheets =size/50000+1;
		//达到多少条重建sheet页
		int count = 49997;
		HSSFFont font = wr.createFont();
		font.setColor(IndexedColors.BLACK.index); // 字体颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		font.setFontName("宋体"); // 字体样式
		font.setFontHeightInPoints((short) 13); // 字体大小

		HSSFCellStyle cellStyle = wr.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index); // 设置背景颜色
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		HSSFCellStyle cellStyle1 = wr.createCellStyle();
		cellStyle1.setFont(font);
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index); // 设置红色背景颜色
		cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		HSSFCellStyle cellStyle2 = wr.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


		try {
			while(true){
				for(int m=1;m<=sheets;m++){
					HSSFSheet sheet = wr.createSheet("Sheet"+m); // 创建一个sheet页、
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



					// 合并单元格：参数：起始行, 终止行, 起始列, 终止列
					CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 17);
					sheet.addMergedRegion(cra);
					RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, wr);

					HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
					HSSFCell createCellbt = createRowbt.createCell(0);
					createRowbt.setHeight((short) 480);
					createCellbt.setCellValue("出库统计");
					createCellbt.setCellStyle(cellStyle1);

					HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
					createRow.setHeight((short) 380);
					for (int i = 0; i < headers.length; i++) {
						HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
						createCell.setCellValue(headers[i]);
						createCell.setCellStyle(cellStyle);
					}

					int	num = 0;
					if(m!=1){
						num=(m-1)*49997+m-1;
					}
					for (int i = 0; i < list.size(); i++) {
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date dtid = f.parse(list.get(i+num).getDt()) ;
					String dt = null;
					String dts = null;
					if(dtid!=null){
						 dt = f.format(dtid);
					}
					Date dtss = f.parse(list.get(i+num).getCheckDt());
					if(dtss!=null){
						 dts = f.format(dtss);
					}
					HSSFRow createRow2 = sheet.createRow(i + 2);
					createRow2.setHeight((short) 280);

					HSSFCell createCellbt0 = createRow2.createCell(0);  // 工厂编号
					createCellbt0.setCellValue(list.get(i+num).getFactoryNo());
					createCellbt0.setCellStyle(cellStyle2);

					HSSFCell createCellbt17 = createRow2.createCell(1);  // 物料凭证号
					createCellbt17.setCellValue(list.get(i+num).getMaterialName());
					createCellbt17.setCellStyle(cellStyle2);

					HSSFCell createCellbt1 = createRow2.createCell(2);  // 物料凭证号
					createCellbt1.setCellValue(list.get(i+num).getMaterialVoucher());
					createCellbt1.setCellStyle(cellStyle2);

					HSSFCell createCellbt2 = createRow2.createCell(3);  // 校验批次
					createCellbt2.setCellValue(list.get(i+num).getCheckBatch());
					createCellbt2.setCellStyle(cellStyle2);

					HSSFCell createCellbt3 = createRow2.createCell(4); // 校验批来源
					createCellbt3.setCellValue(list.get(i+num).getOtigin());
					createCellbt3.setCellStyle(cellStyle2);

					HSSFCell createCellbt4 = createRow2.createCell(5);  // 物料编号
					createCellbt4.setCellValue(list.get(i+num).getMaterialNo());
					createCellbt4.setCellStyle(cellStyle2);

					HSSFCell createCellbt5= createRow2.createCell(6); // 物料描述
					createCellbt5.setCellValue(list.get(i+num).getMaterialDescribe());
					createCellbt5.setCellStyle(cellStyle2);

					HSSFCell createCellbt6 = createRow2.createCell(7); // 校验批数量
					createCellbt6.setCellValue(list.get(i+num).getCheckNum());
					createCellbt6.setCellStyle(cellStyle2);

					HSSFCell createCellbt7= createRow2.createCell(8); // 物料描述
					createCellbt7.setCellValue(list.get(i+num).getNgNum());
					createCellbt7.setCellStyle(cellStyle2);

					HSSFCell createCellbt8 = createRow2.createCell(9); //SQE不合格数量
					createCellbt8.setCellValue(list.get(i+num).getSeqNgNum());
					createCellbt8.setCellStyle(cellStyle2);

					HSSFCell createCellbt9= createRow2.createCell(10); // 计量单位
					createCellbt9.setCellValue(list.get(i+num).getCalculateUnit());
					createCellbt9.setCellStyle(cellStyle2);

					HSSFCell createCellbt10 = createRow2.createCell(11); // 供应商名称
					createCellbt10.setCellValue(list.get(i+num).getSupplierName());
					createCellbt10.setCellStyle(cellStyle2);

					HSSFCell createCellbt11 = createRow2.createCell(12);// 创建人
					createCellbt11.setCellValue(list.get(i+num).getEmp());
					createCellbt11.setCellStyle(cellStyle2);

					HSSFCell createCellbt12 = createRow2.createCell(13); // 检验时间
					createCellbt12.setCellValue(dt);
					createCellbt12.setCellStyle(cellStyle2);

					HSSFCell createCellbt13 = createRow2.createCell(14); // 复验时间
					createCellbt13.setCellValue(dts);
					createCellbt13.setCellStyle(cellStyle2);

					HSSFCell createCellbt14 = createRow2.createCell(15);// 创建人
					createCellbt14.setCellValue(list.get(i+num).getCheckPerson());
					createCellbt14.setCellStyle(cellStyle2);

					HSSFCell createCellbt15 = createRow2.createCell(16); // 供应商名称
					createCellbt15.setCellStyle(cellStyle2);
					if(list.get(i+num).getProductionHandie()=="1"){
						createCellbt15.setCellValue("接受"); // 产品处置
					}else if(list.get(i+num).getProductionHandie()=="2"){
						createCellbt15.setCellValue("退货"); // 产品处置
					}else{
						createCellbt15.setCellValue("入库"); // 产品处置
					}

					HSSFCell createCellbt16 = createRow2.createCell(17); // 供应商名称
					createCellbt16.setCellStyle(cellStyle2);
					if(list.get(i+num).getStatus()==1){
						createCellbt16.setCellValue("已检验"); // 产品处置
					}else{
						createCellbt16.setCellValue("已复验"); // 产品处置
					}
					if(i+num==size-1){
						return ;
					}
					if(i!=0 && i%count==0){
						break;
					}

				}
			}
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}

	//缺陷录入
	public static void entryExcel(List<CMesDefectEntryT> list, HSSFWorkbook wr, String[] headers) throws Exception {
		//总行数
		int pages = 0;
		//list长度
		int size = list.size();
		//需要多少页
		int sheets =size/50000+1;
		//达到多少条重建sheet页
		int count = 49997;

		HSSFFont font = wr.createFont();
		font.setColor(IndexedColors.BLACK.index); // 字体颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		font.setFontName("宋体"); // 字体样式
		font.setFontHeightInPoints((short) 13); // 字体大小

		HSSFCellStyle cellStyle = wr.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index); // 设置背景颜色
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		HSSFCellStyle cellStyle1 = wr.createCellStyle();
		cellStyle1.setFont(font);
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index); // 设置红色背景颜色
		cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		HSSFCellStyle cellStyle2 = wr.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		try {
				while(true){
					for(int m=1;m<=sheets;m++){
						HSSFSheet sheet = wr.createSheet("Sheet"+m); // 创建一个sheet页、
						sheet.setColumnWidth(0, 100 * 60); // 设置列宽度
						sheet.setColumnWidth(1, 100 * 60);
						sheet.setColumnWidth(2, 100 * 60);
						sheet.setColumnWidth(3, 100 * 60);
						sheet.setColumnWidth(4, 100 * 60);


						// 合并单元格：参数：起始行, 终止行, 起始列, 终止列
						CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 4);
						sheet.addMergedRegion(cra);
						RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, wr);

						HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
						HSSFCell createCellbt = createRowbt.createCell(0);
						createRowbt.setHeight((short) 480);
						createCellbt.setCellValue("缺陷录入");
						createCellbt.setCellStyle(cellStyle1);

						HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
						createRow.setHeight((short) 380);
						for (int j = 0; j < headers.length; j++) {
							HSSFCell createCell = createRow.createCell(j); // 循环添加头声明
							createCell.setCellValue(headers[j]);
							createCell.setCellStyle(cellStyle);
						}
						int	num = 0;
						if(m!=1){
							num=(m-1)*49997+m-1;
						}
						for (int i=0; i <size; i++) {
							SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							Date dtid = f.parse(list.get(i+num).getDt()) ;
							String dt = null;
							if(dtid!=null){
								 dt = f.format(dtid);
							}
							HSSFRow createRow2 = sheet.createRow(i + 2);
							createRow2.setHeight((short) 280);

							HSSFCell createCellbt0 = createRow2.createCell(0);
							createCellbt0.setCellValue(list.get(i+num).getSn());
							createCellbt0.setCellStyle(cellStyle2);


							HSSFCell createCellbt1 = createRow2.createCell(1);
							createCellbt1.setCellValue(dt);
							createCellbt1.setCellStyle(cellStyle2);

							HSSFCell createCellbt2 = createRow2.createCell(2);
							createCellbt2.setCellValue(list.get(i+num).getProductionName());
							createCellbt2.setCellStyle(cellStyle2);

							HSSFCell createCellbt3 = createRow2.createCell(3);
							createCellbt3.setCellValue(list.get(i+num).getReason());
							createCellbt3.setCellStyle(cellStyle2);

							HSSFCell createCellbt4 = createRow2.createCell(4);
							createCellbt4.setCellValue(list.get(i+num).getEmp());
							createCellbt4.setCellStyle(cellStyle2);
							if(i+num==size-1){
								return ;
							}
							if(i!=0 && i%count==0){
								break;
							}

						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}


	/**
	 * 巡检记录
	* @author FQZ
	* @date 2019年10月23日上午11:24:42
	 */
	public static void patrolExcel(List<CMesPatrolT> list, HSSFWorkbook wr, String[] headers) throws Exception {
		//总行数
		int pages = 0;
		//list长度
		int size = list.size();
		//需要多少页
		int sheets =size/50000+1;
		//达到多少条重建sheet页
		int count = 49997;

		HSSFFont font = wr.createFont();
		font.setColor(IndexedColors.BLACK.index); // 字体颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		font.setFontName("宋体"); // 字体样式
		font.setFontHeightInPoints((short) 13); // 字体大小

		HSSFCellStyle cellStyle = wr.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index); // 设置背景颜色
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		HSSFCellStyle cellStyle1 = wr.createCellStyle();
		cellStyle1.setFont(font);
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index); // 设置红色背景颜色
		cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


		HSSFCellStyle cellStyle2 = wr.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		try {
				while(true){
					for(int m=1;m<=sheets;m++){
					HSSFSheet sheet = wr.createSheet("Sheet"+m); // 创建一个sheet页、
					sheet.setColumnWidth(0, 100 * 60); // 设置列宽度
					sheet.setColumnWidth(1, 100 * 60);
					sheet.setColumnWidth(2, 100 * 60);
					sheet.setColumnWidth(3, 100 * 60);
					sheet.setColumnWidth(4, 100 * 60);
					sheet.setColumnWidth(5, 100 * 60);



					// 合并单元格：参数：起始行, 终止行, 起始列, 终止列
					CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 5);
					sheet.addMergedRegion(cra);
					RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, wr);

					HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
					HSSFCell createCellbt = createRowbt.createCell(0);
					createRowbt.setHeight((short) 480);
					createCellbt.setCellValue("巡检记录");
					createCellbt.setCellStyle(cellStyle1);

					HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
					createRow.setHeight((short) 380);
					for (int i = 0; i < headers.length; i++) {
						HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
						createCell.setCellValue(headers[i]);
						createCell.setCellStyle(cellStyle);
					}
					int	num = 0;
					if(m!=1){
						num=(m-1)*49997+m-1;
					}
					for (int i = 0; i < list.size(); i++) {
						SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String dtid = list.get(i+num).getDt();
						String dt = null;
						String dts = null;
						if(dtid!=null){
							 dt = f.format(dtid);
						}

						HSSFRow createRow2 = sheet.createRow(i + 2);
						createRow2.setHeight((short) 280);

						HSSFCell createCellbt0 = createRow2.createCell(0);
						createCellbt0.setCellValue(list.get(i+num).getSn());
						createCellbt0.setCellStyle(cellStyle2);

						HSSFCell createCellbt1 = createRow2.createCell(1);
						createCellbt1.setCellValue(dt);
						createCellbt1.setCellStyle(cellStyle2);

						HSSFCell createCellbt2 = createRow2.createCell(2);
						createCellbt2.setCellValue(list.get(i+num).getProductionName());
						createCellbt2.setCellStyle(cellStyle2);

						HSSFCell createCellbt3 = createRow2.createCell(3);
						createCellbt3.setCellValue(list.get(i+num).getStationName());
						createCellbt3.setCellStyle(cellStyle2);

						HSSFCell createCellbt4 = createRow2.createCell(4);
						createCellbt4.setCellValue(list.get(i+num).getEmp());
						createCellbt4.setCellStyle(cellStyle2);

						HSSFCell createCellbt5 = createRow2.createCell(5);
						createCellbt5.setCellValue(list.get(i+num).getReason());
						createCellbt5.setCellStyle(cellStyle2);
						if(i+num==size-1){
							return ;
						}
						if(i!=0 && i%count==0){
							break;
						}

					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 返厂维修
	* @author FQZ
	* @date 2019年10月23日上午11:24:49
	 */
	public static void reapairExcel(List<CMesReturnRepairT> list, HSSFWorkbook wr, String[] headers) throws Exception {

		//总行数
		int pages = 0;
		//list长度
		int size = list.size();
		//需要多少页
		int sheets =size/50000+1;
		//达到多少条重建sheet页
		int count = 49997;
		HSSFFont font = wr.createFont();
		font.setColor(IndexedColors.BLACK.index); // 字体颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		font.setFontName("宋体"); // 字体样式
		font.setFontHeightInPoints((short) 13); // 字体大小

		HSSFCellStyle cellStyle = wr.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index); // 设置背景颜色
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		HSSFCellStyle cellStyle1 = wr.createCellStyle();
		cellStyle1.setFont(font);
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index); // 设置红色背景颜色
		cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		HSSFCellStyle cellStyle2 = wr.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		try {
			while(true){
				for(int m=1;m<=sheets;m++){
					HSSFSheet sheet = wr.createSheet("Sheet"+m); // 创建一个sheet页、
					sheet.setColumnWidth(0, 100 * 60); // 设置列宽度
					sheet.setColumnWidth(1, 100 * 60);
					sheet.setColumnWidth(2, 100 * 60);
					sheet.setColumnWidth(3, 100 * 60);
					sheet.setColumnWidth(4, 100 * 60);
					sheet.setColumnWidth(5, 100 * 60);



					// 合并单元格：参数：起始行, 终止行, 起始列, 终止列
					CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 5);
					sheet.addMergedRegion(cra);
					RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, wr);

					HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
					HSSFCell createCellbt = createRowbt.createCell(0);
					createRowbt.setHeight((short) 480);
					createCellbt.setCellValue("返厂维修");
					createCellbt.setCellStyle(cellStyle1);

					HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
					createRow.setHeight((short) 380);
					for (int i = 0; i < headers.length; i++) {
						HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
						createCell.setCellValue(headers[i]);
						createCell.setCellStyle(cellStyle);
					}

					int	num = 0;
					if(m!=1){
						num=(m-1)*49997+m-1;
					}

					for (int i = 0; i < list.size(); i++) {
						SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						Date dtid = list.get(i+num).getDt();
						Date dtids = list.get(i+num).getEndTime();
						String dt = null;
						String dts = null;
						if(dtid!=null){
							 dt = f.format(dtid);
						}
						if(dtids!=null){
							dts=f.format(dtids);
						}


						HSSFRow createRow2 = sheet.createRow(i + 2);
						createRow2.setHeight((short) 280);

						HSSFCell createCellbt0 = createRow2.createCell(0);
						createCellbt0.setCellValue(list.get(i+num).getSn());
						createCellbt0.setCellStyle(cellStyle2);


						HSSFCell createCellbt1 = createRow2.createCell(1);
						createCellbt1.setCellValue(dt);
						createCellbt1.setCellStyle(cellStyle2);

						HSSFCell createCellbt2 = createRow2.createCell(2);
						createCellbt2.setCellValue(list.get(i+num).getProductionName());
						createCellbt2.setCellStyle(cellStyle2);

						HSSFCell createCellbt3 = createRow2.createCell(3);
						createCellbt3.setCellValue(list.get(i+num).getLineName());
						createCellbt3.setCellStyle(cellStyle2);

						HSSFCell createCellbt4 = createRow2.createCell(4);
						createCellbt4.setCellValue(dts);
						createCellbt4.setCellStyle(cellStyle2);

						HSSFCell createCellbt5 = createRow2.createCell(5);
						createCellbt5.setCellValue(list.get(i+num).getReason());
						createCellbt5.setCellStyle(cellStyle2);
						if(i+num==size-1){
							return ;
						}
						if(i!=0 && i%count==0){
							break;
						}

					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 入库检验项
	* @author FQZ
	* @date 2019年10月23日上午11:24:49
	 */
	public static void checkoutExcel(List<CMesCheckoutListT> list, HSSFWorkbook wr, String[] headers) throws Exception {
		//总行数
		int pages = 0;
		//list长度
		int size = list.size();
		//需要多少页
		int sheets =size/50000+1;
		//达到多少条重建sheet页
		int count = 49997;

		HSSFFont font = wr.createFont();
		font.setColor(IndexedColors.BLACK.index); // 字体颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		font.setFontName("宋体"); // 字体样式
		font.setFontHeightInPoints((short) 13); // 字体大小

		HSSFCellStyle cellStyle = wr.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillBackgroundColor(HSSFColor.AQUA.index); // 设置背景颜色
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		HSSFCellStyle cellStyle1 = wr.createCellStyle();
		cellStyle1.setFont(font);
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index);
		cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle1.setFillForegroundColor(HSSFColor.AQUA.index); // 设置红色背景颜色
		cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		HSSFCellStyle cellStyle2 = wr.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

		try {
			while(true){
				for(int m=1;m<=sheets;m++){
					HSSFSheet sheet = wr.createSheet("Sheet"+m); // 创建一个sheet页、
					sheet.setColumnWidth(0, 100 * 60); // 设置列宽度
					sheet.setColumnWidth(1, 100 * 60);
					sheet.setColumnWidth(2, 100 * 60);
					sheet.setColumnWidth(3, 100 * 60);
					sheet.setColumnWidth(4, 100 * 60);

					// 合并单元格：参数：起始行, 终止行, 起始列, 终止列
					CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 4);
					sheet.addMergedRegion(cra);
					RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, wr);

					HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
					HSSFCell createCellbt = createRowbt.createCell(0);
					createRowbt.setHeight((short) 480);
					createCellbt.setCellValue("入库检验项");
					createCellbt.setCellStyle(cellStyle1);

					HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
					createRow.setHeight((short) 380);
					for (int i = 0; i < headers.length; i++) {
						HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
						createCell.setCellValue(headers[i]);
						createCell.setCellStyle(cellStyle);
					}

					int	num = 0;
					if(m!=1){
						num=(m-1)*49997+m-1;
					}
					for (int i = 0; i < list.size(); i++) {
						SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


						HSSFRow createRow2 = sheet.createRow(i + 2);
						createRow2.setHeight((short) 280);

						HSSFCell createCellbt0 = createRow2.createCell(0);
						createCellbt0.setCellValue(list.get(i+num).getOrderNumber());
						createCellbt0.setCellStyle(cellStyle2);


						HSSFCell createCellbt1 = createRow2.createCell(1);
						createCellbt1.setCellValue(list.get(i+num).getProjectName());
						createCellbt1.setCellStyle(cellStyle2);

						HSSFCell createCellbt2 = createRow2.createCell(2);
						createCellbt2.setCellValue(list.get(i+num).getQuailty());
						createCellbt2.setCellStyle(cellStyle2);

						HSSFCell createCellbt3 = createRow2.createCell(3);
						createCellbt3.setCellValue(list.get(i+num).getMethodName());
						createCellbt3.setCellStyle(cellStyle2);

						HSSFCell createCellbt4 = createRow2.createCell(4);
						createCellbt4.setCellValue(list.get(i+num).getProductionName());
						createCellbt4.setCellStyle(cellStyle2);
						if(i+num==size-1){
							return ;
						}
						if(i!=0 && i%count==0){
							break;
						}

					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 导出配方
	* @author FQZ
	* @date 2019年11月13日下午2:33:06
	 */
	public static void RecipeExcel(HttpServletResponse response,List<CMesRecipeDatilT> list, HSSFWorkbook book, String[] headers,String filename) throws Exception {
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
		createCellbt.setCellValue("配方明细");
		createCellbt.setCellStyle(cellStyle);

		HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
		createRow.setHeight((short) 380);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
			createCell.setCellValue(headers[i]);
			createCell.setCellStyle(cellStyle);
		}

		HSSFCellStyle cellStyle2 = book.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		for (int i = 0; i < list.size(); i++) {
			HSSFRow createRow2 = sheet.createRow(i + 2);
			createRow2.setHeight((short) 280);


			HSSFCell createCellbt0 = createRow2.createCell(0);
			createCellbt0.setCellValue(i+1);
			createCellbt0.setCellStyle(cellStyle2);


			HSSFCell createCellbt1 = createRow2.createCell(1);
			createCellbt1.setCellValue(list.get(i).getStepCategory());
			createCellbt1.setCellStyle(cellStyle2);

			HSSFCell createCellbt2 = createRow2.createCell(2);
			createCellbt2.setCellValue(list.get(i).getMaterialName());
			createCellbt2.setCellStyle(cellStyle2);

			HSSFCell createCellbt3 = createRow2.createCell(3);
			createCellbt3.setCellValue(list.get(i).getNumbers());
			createCellbt3.setCellStyle(cellStyle2);

			HSSFCell createCellbt4 = createRow2.createCell(4);
			createCellbt4.setCellValue(list.get(i).getGunNo());
			createCellbt4.setCellStyle(cellStyle2);

			HSSFCell createCellbt5 = createRow2.createCell(5);
			createCellbt5.setCellValue(list.get(i).getProgramNo());
			createCellbt5.setCellStyle(cellStyle2);

			HSSFCell createCellbt6= createRow2.createCell(6);
			createCellbt6.setCellValue(list.get(i).getMaterialpn());
			createCellbt6.setCellStyle(cellStyle2);

			HSSFCell createCellbt7 = createRow2.createCell(7);
			createCellbt7.setCellValue(list.get(i).getSleeveNo());
			createCellbt7.setCellStyle(cellStyle2);

			HSSFCell createCellbt8= createRow2.createCell(8);
			createCellbt8.setCellValue(list.get(i).getStepno());
			createCellbt8.setCellStyle(cellStyle2);

			HSSFCell createCellbt9 = createRow2.createCell(9);
			createCellbt9.setCellValue(list.get(i).getUploadCode());
			createCellbt9.setCellStyle(cellStyle2);

			HSSFCell createCellbt10= createRow2.createCell(10);
			createCellbt10.setCellValue(list.get(i).getFeacode());
			createCellbt10.setCellStyle(cellStyle2);

			HSSFCell createCellbt11 = createRow2.createCell(11);
			createCellbt11.setCellValue(list.get(i).getBolteqs());
			createCellbt11.setCellStyle(cellStyle2);

			HSSFCell createCellbt12 = createRow2.createCell(12);
			createCellbt12.setCellValue(list.get(i).getStationName());
			createCellbt12.setCellStyle(cellStyle2);

			HSSFCell createCellbt13 = createRow2.createCell(13);
			createCellbt13.setCellValue(list.get(i).getProductionName());
			createCellbt13.setCellStyle(cellStyle2);

			HSSFCell createCellbt14 = createRow2.createCell(14);
			createCellbt14.setCellValue(list.get(i).getProductionVr());
			createCellbt14.setCellStyle(cellStyle2);

			HSSFCell createCellbt15 = createRow2.createCell(15);
			createCellbt15.setCellValue(list.get(i).getPhotoNo());
			createCellbt15.setCellStyle(cellStyle2);

			HSSFCell createCellbt16 = createRow2.createCell(16);
			createCellbt16.setCellValue(list.get(i).getReworktimes());
			createCellbt16.setCellStyle(cellStyle2);

			HSSFCell createCellbt17 = createRow2.createCell(17);
			createCellbt17.setCellValue(list.get(i).getChekorno());
			createCellbt17.setCellStyle(cellStyle2);

			HSSFCell createCellbt18 = createRow2.createCell(18);
			createCellbt18.setCellValue(list.get(i).getRevieworno());
			createCellbt18.setCellStyle(cellStyle2);


			HSSFCell createCellbt19 = createRow2.createCell(19);
			createCellbt19.setCellValue(list.get(i).getexactorno());
			createCellbt19.setCellStyle(cellStyle2);

			HSSFCell createCellbt20 = createRow2.createCell(20);
			createCellbt20.setCellValue(list.get(i).getaLimit());
			createCellbt20.setCellStyle(cellStyle2);

			HSSFCell createCellbt21 = createRow2.createCell(21);
			createCellbt21.setCellValue(list.get(i).gettLimit());
			createCellbt21.setCellStyle(cellStyle2);


			HSSFCell createCellbt23 = createRow2.createCell(22);
			createCellbt23.setCellValue(list.get(i).getBoltjson());
			createCellbt23.setCellStyle(cellStyle2);

			HSSFCell createCellbt24 = createRow2.createCell(23);
			createCellbt24.setCellValue("");
			createCellbt24.setCellStyle(cellStyle2);


			if(list.get(i).getPathBinary()!=null){
				BufferedImage bufferImg = null;
				try {
					byte[] bytes1 = decoder.decodeBuffer(list.get(i).getPathBinary());
				    ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
			        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			        bufferImg = ImageIO.read(bais);
			        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			        ImageIO.write(bufferImg, "jpg", byteArrayOut);
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
		                     (short) 23,i+2, (short) 24,i+3);
		            patriarch.createPicture(anchor, book.addPicture(byteArrayOut
		                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("utf-8"), "iso8859-1"));
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		book.write(out);
		out.flush();
		out.close();
	}

	/**
	 * 导出配方版本明细
	* @author FQZ
	* @date 2020年3月25日上午9:32:53
	 */
	public static void RecipeVersionExport(List<CMesRecipeVersionDetail> list,HSSFWorkbook book, String[] headers) throws Exception {
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
		createCellbt.setCellValue("配方明细");
		createCellbt.setCellStyle(cellStyle);

		HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
		createRow.setHeight((short) 380);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
			createCell.setCellValue(headers[i]);
			createCell.setCellStyle(cellStyle);
		}

		HSSFCellStyle cellStyle2 = book.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		for (int i = 0; i < list.size(); i++) {
			HSSFRow createRow2 = sheet.createRow(i + 2);
			createRow2.setHeight((short) 280);


			HSSFCell createCellbt0 = createRow2.createCell(0);
			createCellbt0.setCellValue(i+1);
			createCellbt0.setCellStyle(cellStyle2);


			HSSFCell createCellbt1 = createRow2.createCell(1);
			createCellbt1.setCellValue(list.get(i).getStepCategory());
			createCellbt1.setCellStyle(cellStyle2);

			HSSFCell createCellbt2 = createRow2.createCell(2);
			createCellbt2.setCellValue(list.get(i).getMaterialName());
			createCellbt2.setCellStyle(cellStyle2);

			HSSFCell createCellbt3 = createRow2.createCell(3);
			createCellbt3.setCellValue(list.get(i).getNumbers());
			createCellbt3.setCellStyle(cellStyle2);

			HSSFCell createCellbt4 = createRow2.createCell(4);
			createCellbt4.setCellValue(list.get(i).getGunNo());
			createCellbt4.setCellStyle(cellStyle2);

			HSSFCell createCellbt5 = createRow2.createCell(5);
			createCellbt5.setCellValue(list.get(i).getProgramNo());
			createCellbt5.setCellStyle(cellStyle2);

			HSSFCell createCellbt6= createRow2.createCell(6);
			createCellbt6.setCellValue(list.get(i).getMaterialpn());
			createCellbt6.setCellStyle(cellStyle2);

			HSSFCell createCellbt7 = createRow2.createCell(7);
			createCellbt7.setCellValue(list.get(i).getSleeveNo());
			createCellbt7.setCellStyle(cellStyle2);

			HSSFCell createCellbt8= createRow2.createCell(8);
			createCellbt8.setCellValue(list.get(i).getStepno());
			createCellbt8.setCellStyle(cellStyle2);

			HSSFCell createCellbt9 = createRow2.createCell(9);
			createCellbt9.setCellValue(list.get(i).getUploadCode());
			createCellbt9.setCellStyle(cellStyle2);

			HSSFCell createCellbt10= createRow2.createCell(10);
			createCellbt10.setCellValue(list.get(i).getFeacode());
			createCellbt10.setCellStyle(cellStyle2);

			HSSFCell createCellbt11 = createRow2.createCell(11);
			createCellbt11.setCellValue(list.get(i).getBolteqs());
			createCellbt11.setCellStyle(cellStyle2);

			HSSFCell createCellbt12 = createRow2.createCell(12);
			createCellbt12.setCellValue(list.get(i).getStationName());
			createCellbt12.setCellStyle(cellStyle2);

			HSSFCell createCellbt13 = createRow2.createCell(13);
			createCellbt13.setCellValue(list.get(i).getProductionName());
			createCellbt13.setCellStyle(cellStyle2);

			HSSFCell createCellbt14 = createRow2.createCell(14);
			createCellbt14.setCellValue(list.get(i).getProductionVr());
			createCellbt14.setCellStyle(cellStyle2);

			HSSFCell createCellbt15 = createRow2.createCell(15);
			createCellbt15.setCellValue(list.get(i).getPhotoNo());
			createCellbt15.setCellStyle(cellStyle2);

			HSSFCell createCellbt16 = createRow2.createCell(16);
			createCellbt16.setCellValue(list.get(i).getReworktimes());
			createCellbt16.setCellStyle(cellStyle2);

			HSSFCell createCellbt17 = createRow2.createCell(17);
			createCellbt17.setCellValue(list.get(i).getChekorno());
			createCellbt17.setCellStyle(cellStyle2);

			HSSFCell createCellbt18 = createRow2.createCell(18);
			createCellbt18.setCellValue(list.get(i).getRevieworno());
			createCellbt18.setCellStyle(cellStyle2);


			HSSFCell createCellbt19 = createRow2.createCell(19);
			createCellbt19.setCellValue(list.get(i).getExactorno());
			createCellbt19.setCellStyle(cellStyle2);

			HSSFCell createCellbt20 = createRow2.createCell(20);
			createCellbt20.setCellValue(list.get(i).getaLimit());
			createCellbt20.setCellStyle(cellStyle2);

			HSSFCell createCellbt21 = createRow2.createCell(21);
			createCellbt21.setCellValue(list.get(i).gettLimit());
			createCellbt21.setCellStyle(cellStyle2);


			HSSFCell createCellbt23 = createRow2.createCell(22);
			createCellbt23.setCellValue(list.get(i).getBoltjson());
			createCellbt23.setCellStyle(cellStyle2);

			HSSFCell createCellbt24 = createRow2.createCell(23);
			createCellbt24.setCellValue("");
			createCellbt24.setCellStyle(cellStyle2);

			if(list.get(i).getPathBinary()!=null){
				BufferedImage bufferImg = null;
				try {
					byte[] bytes1 = decoder.decodeBuffer(list.get(i).getPathBinary());
				    ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
			        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			        bufferImg = ImageIO.read(bais);
			        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			        ImageIO.write(bufferImg, "jpg", byteArrayOut);
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
		                     (short) 23,i+2, (short) 24,i+3);
		            patriarch.createPicture(anchor, book.addPicture(byteArrayOut
		                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	/**
	 * 导出配方版本明细
	* @author FQZ
	* @date 2020年3月25日上午9:32:53
	 */
	public static void TotalRecipeExport(List<Map<String,Object>> list,HSSFWorkbook book, String[] headers) throws Exception {
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

		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 24);
		sheet.addMergedRegion(cra);
		RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, book);

		HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
		HSSFCell createCellbt = createRowbt.createCell(0);
		createRowbt.setHeight((short) 480);
		createCellbt.setCellValue("配方明细");
		createCellbt.setCellStyle(cellStyle);

		HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
		createRow.setHeight((short) 380);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
			createCell.setCellValue(headers[i]);
			createCell.setCellStyle(cellStyle);
		}

		HSSFCellStyle cellStyle2 = book.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
		cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		for (int i = 0; i < list.size(); i++) {
			HSSFRow createRow2 = sheet.createRow(i + 2);
			createRow2.setHeight((short) 280);


			HSSFCell createCellbt0 = createRow2.createCell(0);
			createCellbt0.setCellValue(i+1);
			createCellbt0.setCellStyle(cellStyle2);


			HSSFCell createCellbt1 = createRow2.createCell(1);
			createCellbt1.setCellValue(getObject(list.get(i).get("totalName")));
			createCellbt1.setCellStyle(cellStyle2);

			HSSFCell createCellbt2 = createRow2.createCell(2);
			createCellbt2.setCellValue(getObject(list.get(i).get("productionName")));
			createCellbt2.setCellStyle(cellStyle2);

			HSSFCell createCellbt3 = createRow2.createCell(3);
			createCellbt3.setCellValue(getObject(list.get(i).get("lineName")));
			createCellbt3.setCellStyle(cellStyle2);

			HSSFCell createCellbt4 = createRow2.createCell(4);
			createCellbt4.setCellValue(getObject(list.get(i).get("recipeName")));
			createCellbt4.setCellStyle(cellStyle2);

			HSSFCell createCellbt5 = createRow2.createCell(5);
			createCellbt5.setCellValue(getObject(list.get(i).get("stationName")));
			createCellbt5.setCellStyle(cellStyle2);

			HSSFCell createCellbt6= createRow2.createCell(6);
			createCellbt6.setCellValue(getObject(list.get(i).get("stepCategory")));
			createCellbt6.setCellStyle(cellStyle2);


			HSSFCell createCellbt7 = createRow2.createCell(7);
			createCellbt7.setCellValue(getObject(list.get(i).get("materialName")));
			createCellbt7.setCellStyle(cellStyle2);

			HSSFCell createCellbt8= createRow2.createCell(8);
			createCellbt8.setCellValue(getObject(list.get(i).get("numbers")));
			createCellbt8.setCellStyle(cellStyle2);

			HSSFCell createCellbt9 = createRow2.createCell(9);
			createCellbt9.setCellValue(getObject(list.get(i).get("gunNo")));
			createCellbt9.setCellStyle(cellStyle2);

			HSSFCell createCellbt10= createRow2.createCell(10);
			createCellbt10.setCellValue(getObject(list.get(i).get("programNo")));
			createCellbt10.setCellStyle(cellStyle2);

			HSSFCell createCellbt11 = createRow2.createCell(11);
			createCellbt11.setCellValue(getObject(list.get(i).get("materialpn")));
			createCellbt11.setCellStyle(cellStyle2);

			HSSFCell createCellbt12 = createRow2.createCell(12);
			createCellbt12.setCellValue(getObject(list.get(i).get("sleeveNo")));
			createCellbt12.setCellStyle(cellStyle2);

			HSSFCell createCellbt13 = createRow2.createCell(13);
			createCellbt13.setCellValue(getObject(list.get(i).get("stepno")));
			createCellbt13.setCellStyle(cellStyle2);

			HSSFCell createCellbt14 = createRow2.createCell(14);
			createCellbt14.setCellValue(getObject(list.get(i).get("feacode")));
			createCellbt14.setCellStyle(cellStyle2);

			HSSFCell createCellbt15 = createRow2.createCell(15);
			createCellbt15.setCellValue(getObject(list.get(i).get("bolteqs")));
			createCellbt15.setCellStyle(cellStyle2);

			HSSFCell createCellbt16 = createRow2.createCell(16);
			createCellbt16.setCellValue(getObject(list.get(i).get("photoNo")));
			createCellbt16.setCellStyle(cellStyle2);

			HSSFCell createCellbt17 = createRow2.createCell(17);
			createCellbt17.setCellValue(getObject(list.get(i).get("reworktimes")));
			createCellbt17.setCellStyle(cellStyle2);

			HSSFCell createCellbt18 = createRow2.createCell(18);
			createCellbt18.setCellValue(getObject(list.get(i).get("chekorno")));
			createCellbt18.setCellStyle(cellStyle2);


			HSSFCell createCellbt19 = createRow2.createCell(19);
			createCellbt19.setCellValue(getObject(list.get(i).get("revieworno")));
			createCellbt19.setCellStyle(cellStyle2);

			HSSFCell createCellbt20 = createRow2.createCell(20);
			createCellbt20.setCellValue(getObject(list.get(i).get("exactorno")));
			createCellbt20.setCellStyle(cellStyle2);

			HSSFCell createCellbt21 = createRow2.createCell(21);
			createCellbt21.setCellValue(getObject(list.get(i).get("aLimit")));
			createCellbt21.setCellStyle(cellStyle2);

			HSSFCell createCellbt22 = createRow2.createCell(22);
			createCellbt22.setCellValue(getObject(list.get(i).get("tLimit")));
			createCellbt22.setCellStyle(cellStyle2);

			HSSFCell createCellbt23 = createRow2.createCell(23);
			createCellbt23.setCellValue(getObject(list.get(i).get("boltjson")));
			createCellbt23.setCellStyle(cellStyle2);

			HSSFCell createCellbt24 = createRow2.createCell(24);
			createCellbt24.setCellValue("");
			createCellbt24.setCellStyle(cellStyle2);



			if(getObject(list.get(i).get("pathBinary"))!=null){
				BufferedImage bufferImg = null;
				try {
					byte[] bytes1 = decoder.decodeBuffer(getObject(list.get(i).get("pathBinary")));
				    ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
			        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			        bufferImg = ImageIO.read(bais);
			        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			        ImageIO.write(bufferImg, "jpg", byteArrayOut);
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
		                     (short) 24,i+2, (short) 25,i+3);
		            patriarch.createPicture(anchor, book.addPicture(byteArrayOut
		                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}


	public static void export(HttpServletResponse response, Workbook wb, String fileName) throws Exception {
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso8859-1"));

		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
	}

	public  static String getObject(Object ob){
		if(ob==null){
			return "";
		}else{
			return ob.toString();
		}
	}

	/**
	 * 物料库存导出
	 * @param list
	 * @param wr
	 * @param headers
	 * @throws Exception
	 */
	public static void materialNumberExcel(List<CWmsMaterialNumberT> list, HSSFWorkbook wr, String[] headers) throws Exception {
		HSSFSheet sheet = wr.createSheet("Sheet1"); // 创建一个sheet页、
		sheet.setColumnWidth(0, 100 * 60); // 设置列宽度
		sheet.setColumnWidth(1, 100 * 60);
		sheet.setColumnWidth(2, 100 * 30);
		sheet.setColumnWidth(3, 100 * 30);
		sheet.setColumnWidth(4, 100 * 30);
		sheet.setColumnWidth(5, 100 * 30);
		sheet.setColumnWidth(6, 100 * 30);
		sheet.setColumnWidth(7, 100 * 30);
		sheet.setColumnWidth(8, 100 * 30);
		sheet.setColumnWidth(9, 100 * 30);
		sheet.setColumnWidth(10, 100 * 30);
		sheet.setColumnWidth(11, 100 * 30);
		sheet.setColumnWidth(12, 100 * 30);
		sheet.setColumnWidth(13, 100 * 30);
		sheet.setColumnWidth(14, 100 * 60);
		sheet.setColumnWidth(15, 100 * 60);

		HSSFFont font = wr.createFont();
		font.setColor(IndexedColors.BLACK.index); // 字体颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		font.setFontName("宋体"); // 字体样式
		font.setFontHeightInPoints((short) 13); // 字体大小

		HSSFCellStyle cellStyle = wr.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index); // 设置背景颜色

		HSSFCellStyle cellStyle1 = wr.createCellStyle();
		cellStyle1.setFont(font);
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle1.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index); // 设置黄色背景颜色

		// 合并单元格：参数：起始行, 终止行, 起始列, 终止列
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 9);
		sheet.addMergedRegion(cra);
		RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, wr);

		HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
		HSSFCell createCellbt = createRowbt.createCell(0);
		createRowbt.setHeight((short) 480);
		createCellbt.setCellValue("物料库存");
		createCellbt.setCellStyle(cellStyle1);

		HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
		createRow.setHeight((short) 380);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
			createCell.setCellValue(headers[i]);
			createCell.setCellStyle(cellStyle);
		}
		HSSFCellStyle cellStyle2 = wr.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
			HSSFRow createRow2 = sheet.createRow(i + 2);
			createRow2.setHeight((short) 280);

			createRow2.createCell(0).setCellValue(list.get(i).getMaterial().getBomId()); //物料编号
			createRow2.createCell(1).setCellValue(list.get(i).getMaterial().getMaterialName()); // 物料名称
			createRow2.createCell(2).setCellValue(list.get(i).getMaterialNumber()); // 物料库存
			createRow2.createCell(3).setCellValue(list.get(i).getProject().getProjectName()); // 物料库存
			createRow2.createCell(4).setCellValue(list.get(i).getWarehouse().getName()); // 所属仓库
			createRow2.createCell(5).setCellValue(list.get(i).getArea().getAreaName()); // 所属区域
			createRow2.createCell(6).setCellValue(list.get(i).getReservoirArea().getRaName()); // 所属库区
			createRow2.createCell(7).setCellValue(list.get(i).getLocation().getLocationName()); // 所属库位
			createRow2.createCell(8).setCellValue(list.get(i).getDt()); // 入库时间
			createRow2.createCell(9).setCellValue(list.get(i).getTray()); // 托盘码
		}
	}

	/**
	 * 物料导出
	 * @param list
	 * @param wr
	 * @param headers
	 * @throws Exception
	 */
	public static void materialExcel(List<CMesJlMaterialT> list, HSSFWorkbook wr, String[] headers) throws Exception {
		HSSFSheet sheet = wr.createSheet("Sheet1"); // 创建一个sheet页、
		sheet.setColumnWidth(0, 100 * 60); // 设置列宽度
		sheet.setColumnWidth(1, 100 * 60);
		sheet.setColumnWidth(2, 100 * 30);
		sheet.setColumnWidth(3, 100 * 30);
		sheet.setColumnWidth(4, 100 * 30);
		sheet.setColumnWidth(5, 100 * 30);
		sheet.setColumnWidth(6, 100 * 30);
		sheet.setColumnWidth(7, 100 * 30);
		sheet.setColumnWidth(8, 100 * 30);
		sheet.setColumnWidth(9, 100 * 30);
		sheet.setColumnWidth(10, 100 * 30);
		sheet.setColumnWidth(11, 100 * 30);
		sheet.setColumnWidth(12, 100 * 30);
		sheet.setColumnWidth(13, 100 * 30);
		sheet.setColumnWidth(14, 100 * 60);
		sheet.setColumnWidth(15, 100 * 60);

		HSSFFont font = wr.createFont();
		font.setColor(IndexedColors.BLACK.index); // 字体颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		font.setFontName("宋体"); // 字体样式
		font.setFontHeightInPoints((short) 13); // 字体大小

		HSSFCellStyle cellStyle = wr.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index); // 设置背景颜色

		HSSFCellStyle cellStyle1 = wr.createCellStyle();
		cellStyle1.setFont(font);
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle1.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index); // 设置黄色背景颜色

		// 合并单元格：参数：起始行, 终止行, 起始列, 终止列
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 9);
		sheet.addMergedRegion(cra);
		RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, wr);

		HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
		HSSFCell createCellbt = createRowbt.createCell(0);
		createRowbt.setHeight((short) 480);
		createCellbt.setCellValue("物料库存");
		createCellbt.setCellStyle(cellStyle1);

		HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
		createRow.setHeight((short) 380);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
			createCell.setCellValue(headers[i]);
			createCell.setCellStyle(cellStyle);
		}

		HSSFCellStyle cellStyle2 = wr.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
			HSSFRow createRow2 = sheet.createRow(i + 2);
			createRow2.setHeight((short) 280);


			createRow2.createCell(0).setCellValue(list.get(i).getBomId()); //物料编号
			createRow2.createCell(1).setCellValue(list.get(i).getMaterialName()); // 物料名称
			createRow2.createCell(2).setCellValue(list.get(i).getMaterialLength()); // 物料长度
			createRow2.createCell(3).setCellValue(list.get(i).getMaterialWidth()); // 物料宽度
			createRow2.createCell(4).setCellValue(list.get(i).getMaterialHight()); // 物料高度
			createRow2.createCell(5).setCellValue(list.get(i).getMaterialVolume()); // 物料体积
			createRow2.createCell(6).setCellValue(list.get(i).getMaterialWeight()); // 物料重量
			String materialLt = "";
			switch (list.get(i).getMaterialLt()) {
			case "0":
				materialLt = "立库";
				break;
			case "1":
				materialLt = "平库";
				break;
			case "2":
				materialLt = "other";
				break;
			}
			createRow2.createCell(7).setCellValue(materialLt); // 存放库位类型
			createRow2.createCell(8).setCellValue(list.get(i).getDaysOfFailure()); // 失效天数
		}
	}

	/**
	 * 物料库存导出
	 * @param list
	 * @param wr
	 * @param headers
	 * @throws Exception
	 */
	public static void StorageDetailExcel(List<CWmsStorageDetailT> list, HSSFWorkbook wr, String[] headers) throws Exception {
		HSSFSheet sheet = wr.createSheet("Sheet1"); // 创建一个sheet页、
		sheet.setColumnWidth(0, 100 * 60); // 设置列宽度
		sheet.setColumnWidth(1, 100 * 60);
		sheet.setColumnWidth(2, 100 * 30);
		sheet.setColumnWidth(3, 100 * 30);
		sheet.setColumnWidth(4, 100 * 30);
		sheet.setColumnWidth(5, 100 * 30);
		sheet.setColumnWidth(6, 100 * 30);
		sheet.setColumnWidth(7, 100 * 30);
		sheet.setColumnWidth(8, 100 * 30);
		sheet.setColumnWidth(9, 100 * 30);
		sheet.setColumnWidth(10, 100 * 30);
		sheet.setColumnWidth(11, 100 * 30);
		sheet.setColumnWidth(12, 100 * 30);
		sheet.setColumnWidth(13, 100 * 30);
		sheet.setColumnWidth(14, 100 * 60);
		sheet.setColumnWidth(15, 100 * 60);

		HSSFFont font = wr.createFont();
		font.setColor(IndexedColors.BLACK.index); // 字体颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 加粗
		font.setFontName("宋体"); // 字体样式
		font.setFontHeightInPoints((short) 13); // 字体大小

		HSSFCellStyle cellStyle = wr.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index); // 设置背景颜色

		HSSFCellStyle cellStyle1 = wr.createCellStyle();
		cellStyle1.setFont(font);
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle1.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index); // 设置黄色背景颜色

		// 合并单元格：参数：起始行, 终止行, 起始列, 终止列
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 9);
		sheet.addMergedRegion(cra);
		RegionUtil.setBorderTop(HSSFBorderFormatting.BORDER_THICK, cra, sheet, wr);

		HSSFRow createRowbt = sheet.createRow(0); // 创建第一行标题
		HSSFCell createCellbt = createRowbt.createCell(0);
		createRowbt.setHeight((short) 480);
		createCellbt.setCellValue("物料库存");
		createCellbt.setCellStyle(cellStyle1);

		HSSFRow createRow = sheet.createRow(1); // 创建第一行标题
		createRow.setHeight((short) 380);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell createCell = createRow.createCell(i); // 循环添加头声明
			createCell.setCellValue(headers[i]);
			createCell.setCellStyle(cellStyle);
		}
		HSSFCellStyle cellStyle2 = wr.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
			HSSFRow createRow2 = sheet.createRow(i + 2);
			createRow2.setHeight((short) 280);

			createRow2.createCell(0).setCellValue(list.get(i).getListNo()); //单据号
			createRow2.createCell(1).setCellValue(list.get(i).getMaterial().getMaterialName()); // 物料名称
			createRow2.createCell(2).setCellValue(list.get(i).getMaterialNumber()); // 物料数量
			createRow2.createCell(3).setCellValue(list.get(i).getProject().getProjectName()); // 所属项目
			createRow2.createCell(4).setCellValue(list.get(i).getWarehouse().getName()); // 所属仓库
			createRow2.createCell(5).setCellValue(list.get(i).getArea().getAreaName()); // 所属区域
			createRow2.createCell(6).setCellValue(list.get(i).getReservoirArea().getRaName()); // 所属库区
			createRow2.createCell(7).setCellValue(list.get(i).getLocation().getLocationName()); // 所属库位
			createRow2.createCell(8).setCellValue(list.get(i).getDt()); // 操作时间
			createRow2.createCell(9).setCellValue(list.get(i).getIssueOrReceipt()==0?"出库":"入库"); // 操作类型
		}
	}

}

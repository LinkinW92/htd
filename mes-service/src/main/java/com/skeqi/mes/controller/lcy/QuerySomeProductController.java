package com.skeqi.mes.controller.lcy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.common.lcy.ExportExcel;
import com.skeqi.mes.common.lcy.ListSplitSubList;
import com.skeqi.mes.pojo.CMesTableColumnsReportT;
import com.skeqi.mes.pojo.CMesTableReportT;
import com.skeqi.mes.pojo.QueryProduct;
import com.skeqi.mes.service.lcy.QuerySomeProductService;
@Controller
@RequestMapping("skq")
public class QuerySomeProductController {
	@Autowired
	private QuerySomeProductService qsps;
	@ResponseBody
	@RequestMapping("querySomeProductList")
	public JSONObject queryProduct(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String queryProduct = request.getParameter("queryProduct");//查询类型
		String queryStartDate=request.getParameter("queryStartDate");//开始时间
		String queryEndDate=request.getParameter("queryEndDate");//结束时间
		String queryValue = request.getParameter("queryValue");//查询总成号
		String queryNumber = request.getParameter("queryNumber");//查询表的数量
		String queryPage = request.getParameter("queryPage");//查询第几页
		Integer pId=Integer.parseInt(queryProduct);
		CMesTableReportT tableReport=qsps.queryTableName(pId);
		List<CMesTableColumnsReportT> nameList =qsps.queryAllReportList(pId);//查询出产品表
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		for (int i = 0; i < nameList.size(); i++) {
			sb.append(nameList.get(i).getTableColumnsName()+" strs"+i+",");
		}
		String str = sb.toString();
		str=sb.toString().substring(0,str.length()-1);
		sb = new StringBuffer(str);
		sb.append(" from "+tableReport.getTableName()+" WHERE 1=1 ");

		if("R_MES_KEYPART_T".equals(tableReport.getTableName().toUpperCase())||"P_MES_KEYPART_T".equals(tableReport.getTableName().toUpperCase())){

			/*<if test="queryValue != null and queryValue!=''">
        	AND sn = #{queryValue}
      	</if>*/
			if(!queryValue.isEmpty()){

				sb.append("and sn = '"+queryValue+"' or KEYPART_NUM = '" +queryValue +"' ");


			}


		}else{
			if(!queryValue.isEmpty()){

				sb.append("and sn = '"+queryValue+"' ");

			}



		}


		PageHelper.startPage(Integer.parseInt(queryPage),Integer.parseInt(queryNumber));
		List<QueryProduct> queryList = qsps.getQueryProductList(sb.toString(),queryStartDate,queryEndDate,queryValue);
		PageInfo<QueryProduct> pageInfo = new PageInfo<>(queryList,5);//5是页面显示5页
		jo.put("queryList", queryList);
		jo.put("pageInfo",pageInfo);
		StringBuffer getSBHeadValue = new StringBuffer();
		//获取表头名字
		for(int i=0;i<nameList.size();i++){
			if(nameList.get(i).getShowFlag()==1){
				getSBHeadValue.append("<th style=\"text-align: center;\" class=\"col-sm-1\">" +nameList.get(i).getShowColumnsName()+"</th>");
			}
		}
		jo.put("getSBHeadValue", getSBHeadValue);
		StringBuffer getSBBodyValue = new StringBuffer();
		@SuppressWarnings("unchecked")
		List<QueryProduct> getProductList = jo.getObject("queryList", List.class);
		jo.put("getProductListLength", getProductList.size());
		for(int i =0;i<getProductList.size();i++){

			if(i==nameList.size()-1){
				getSBBodyValue.append("<tr id=\"goToBottom\">");
			}else{
				getSBBodyValue.append("<tr>");
			}


			for (int j = 0; j < nameList.size(); j++) {
				if(nameList.get(j).getShowFlag()==1){

					try{
						Class<? extends QueryProduct> clazz = getProductList.get(i).getClass();
						Method getMethod = clazz.getMethod("getStrs"+j,  new Class[] {});
						Object value = getMethod.invoke(getProductList.get(i), new Object[] {});
						if(value==null){
							getSBBodyValue.append("<td scope=\"row\"></td>");
						}else{
							if(value.toString().length()>=50){
								value= value.toString().substring(0,50);
								value=value+"...";
							}
							if(pId==2 || pId==6){
								if(j==10){
									getSBBodyValue.append("<td scope=\"row\"><a href=\"materielManager?materialName="+value+"\">"+value+"</a></td>");
								}else{
									getSBBodyValue.append("<td scope=\"row\">"+value+"</td>");
								}
							}else{
								getSBBodyValue.append("<td scope=\"row\">"+value+"</td>");
							}
						}
					}catch(Exception e){
					}
				}
			}
			getSBBodyValue.append("<tr>");
		}
		jo.put("getSBBodyValue", getSBBodyValue);
		return jo;
	}
	//显示表名 初始化下拉选
	@ResponseBody
	@RequestMapping("queryAllReprot")
	public List<CMesTableReportT> queryAllReprot(){
		List<CMesTableReportT> list =qsps.queryAllReprot();
		return list;
	}
	@RequestMapping("getExport")
	public void getexportExcle(HttpServletRequest request,HttpServletResponse response){
		String queryProduct = request.getParameter("queryProduct");//查询产品类型
		String queryValue = request.getParameter("queryValue").trim();//查询总成号
		String queryEndDate = request.getParameter("queryEndDate");//查询结束时间
		String queryStartDate = request.getParameter("queryStartDate");//查询开始时间
		//String queryPage = request.getParameter("queryPage");//第几次
		String queryTotalNumber =request.getParameter("queryTotalNumber");//有多少条消息
		String everySheetNumber = request.getParameter("everySheetNumber");//每页打印多少条消息
		Integer pId=Integer.parseInt(queryProduct);
		CMesTableReportT tableReport=qsps.queryTableName(pId);
		if("0".equals(queryTotalNumber)||"".equals(queryTotalNumber)) {
			queryTotalNumber="0";//有多少条消息
			everySheetNumber="0";//每页显示多少数据
		}
		List<CMesTableColumnsReportT> nameList2 =qsps.queryAllReportList(pId);//获得产品列表
		List<CMesTableColumnsReportT> nameList =new ArrayList<>();//获得产品列表
		for (CMesTableColumnsReportT cMesTableColumnsReportT : nameList2) {
			if(cMesTableColumnsReportT.getShowFlag()==1){
				nameList.add(cMesTableColumnsReportT);
			}
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		sb.append("select ");
		for (int i = 0; i < nameList.size(); i++) {
			sb.append(nameList.get(i).getTableColumnsName()+" strs"+i+",");
			sb2.append("strs"+i+",");
		}
		String str = sb.toString();
		str=sb.toString().substring(0,str.length()-1);
		sb = new StringBuffer(str);
		sb.append(" from "+tableReport.getTableName()+" ");
		String str2 = sb2.toString();
		str2=sb2.toString().substring(0,str2.length()-1);
		CMesTableReportT someValue = qsps.queryTableName(pId);//获得产品数据
		String fileName =someValue.getShowName()+"产品";
		List<String> querySomeKey = new ArrayList<>();
		querySomeKey.add(("总成号="+queryValue));
		querySomeKey.add(("查询结束时间="+queryEndDate));
		querySomeKey.add(("查询开始时间="+queryStartDate));
		Integer queryTotal2 = Integer.parseInt(queryTotalNumber);//有多少条消息
		Integer SheetNumber = Integer.parseInt(everySheetNumber);//每页显示多少数据
		Integer queryTotal=(int) (Math.ceil((double)queryTotal2/SheetNumber));//需要查询多少次
		List<QueryProduct> queryList2 = new ArrayList<>(30000);
		if(queryTotal==1||queryTotal==0){
			ExportExcel<QueryProduct> ex= new ExportExcel<>();
			XSSFWorkbook wookBook=ex.createWookBook();//创建工作薄
			List<QueryProduct> queryList = qsps.getQueryProductList(sb.toString(),queryStartDate,queryEndDate,queryValue);
			 ex.getQueryKey(wookBook, querySomeKey, response);
			ex.exportExcel(nameList, queryList, fileName,response,wookBook,str2);
			ex.getExportedFile(wookBook, fileName,response);
		}else{
			ListSplitSubList lssl = new ListSplitSubList();
			ExportExcel<QueryProduct> ex= new ExportExcel<>();
			XSSFWorkbook wookBook=ex.createWookBook();//创建工作薄
			ex.getQueryKey(wookBook, querySomeKey, response);
			List<QueryProduct> queryList = qsps.getQueryProductList(sb.toString(),queryStartDate,queryEndDate,queryValue);
			for (int i = 0; i < queryTotal; i++) {

				PageHelper.startPage(i+1,Integer.parseInt(everySheetNumber));
				queryList2 = qsps.getQueryProductList(sb.toString(),queryStartDate,queryEndDate,queryValue);
				ex.getNewSheetFile(nameList, queryList2, fileName+i, response, wookBook,str2);
				queryList2.clear();
			}
			ex.getExportedFile(wookBook, fileName,response);
		}
	}
}

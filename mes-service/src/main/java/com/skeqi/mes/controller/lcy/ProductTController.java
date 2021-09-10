package com.skeqi.mes.controller.lcy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.lcy.ProductTService;
import com.skeqi.mes.service.wjp.ProductManagementService;
import com.skeqi.mes.util.ToolUtils;
@Controller
@RequestMapping("skq")
public class ProductTController {
	@Autowired
	private ProductTService pts;

	@Autowired
	private ProductManagementService productManagementService;

	@ResponseBody
	@RequestMapping(value="addAll",method = RequestMethod.POST, consumes = "multipart/form-data")
	public JSONObject addAll(@RequestParam(required=false)MultipartFile file,HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String path = null;
		String url = null;
		if(!file.isEmpty()){
			String pictures = file.getOriginalFilename();   //得到上传时的文件名
			String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());   //获取当前时间并转换为string类型
			String extension = FilenameUtils.getExtension(pictures);   //获取文件后缀
			path = name+"."+extension;
			if (!extension.equals("jpg")&&!extension.equals("png")&&!extension.equals("jpeg")) {
				jo.put("getTypeValue", 3);
				return jo;
			}
			url = "E:\\upload"; //要保存的路径
			File dir = new File(url);
			if(!dir.exists()) {   //判断这个路径不存在
				dir.mkdirs();  //如果不存在就创建
			}
		}
		String productionName =	request.getParameter("productionName");
		String productionType = request.getParameter("productionType");
		String productionSeries = request.getParameter("productionSeries");
		String productionVr = request.getParameter("productionVr");//验证
		String productionDiscription = request.getParameter("productionDiscription");
		String productionTrademark = request.getParameter("productionTrademark");
		String productionSte = request.getParameter("productionSte");
		Integer labelTypeId = Integer.parseInt(request.getParameter("labelTypeId"));
		Integer productionSystemId = Integer.parseInt(request.getParameter("productionSystemId"));
		Integer productionGroupId = Integer.parseInt(request.getParameter("productionGroupId"));
		Integer queryValue=pts.queryProductionVr(productionVr,null);//验证校验规则
		Integer queryName = pts.queryProductionName(productionName,null);//校验  名字
		Integer queryType = pts.queryProductionType(productionType,null);
		if(queryValue==0){//说明数据库中没有这个数据
			jo.put("getValue",0);

			if(queryName ==0){
				jo.put("getNameValue", 0);

				if(queryType ==0){
					try {
						pts.addProduction(productionName,productionType,productionSeries,productionVr,productionDiscription,productionTrademark,productionSte,labelTypeId,productionSystemId,productionGroupId,path);
						if(!file.isEmpty()){
							file.transferTo(new File(url+"/"+path));
						}
						jo.put("getTypeValue",0);
					} catch (Exception e) {
						jo.put("getTypeValue", 4);
					}
				}else{
					jo.put("getTypeValue", 1);
				}

			}else{
				jo.put("getNameValue", 1);
			}

		}else{//说明数据库里有这条数据
			jo.put("getValue",1);

		}
		return jo;
	}

	@ResponseBody
	@RequestMapping(value="updateProduct",method = RequestMethod.POST, consumes = "multipart/form-data")
	public JSONObject updateProduct(@RequestParam(required=false)MultipartFile files,HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String path = null;
		String url = null;
		if(!files.isEmpty()){
			String pictures = files.getOriginalFilename();   //得到上传时的文件名
			String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());   //获取当前时间并转换为string类型
			String extension = FilenameUtils.getExtension(pictures);   //获取文件后缀
			path = name+"."+extension;
			if (!extension.equals("jpg")&&!extension.equals("png")&&!extension.equals("jpeg")) {
				jo.put("getTypeValue", 3);
				return jo;
			}
			url = "E:\\upload"; //要保存的路径
			File dir = new File(url);
			if(!dir.exists()) {   //判断这个路径不存在
				dir.mkdirs();  //如果不存在就创建
			}
		}else{
			String id = request.getParameter("id");
			CMesProductionT cMesProductionT = productManagementService.findProductionById(Integer.parseInt(id));
			path = cMesProductionT.getPath();
		}
		String productionName =	request.getParameter("productionName");
		String productionType = request.getParameter("productionType");
		String productionSeries = request.getParameter("productionSeries");
		String productionVr = request.getParameter("productionVr");//验证
		String productionDiscription = request.getParameter("productionDiscription");
		String productionSte = request.getParameter("productionSte5");
		String productId = request.getParameter("id");
		Integer labelTypeId = Integer.parseInt(request.getParameter("labelTypeId"));
		Integer productionGroupId = Integer.parseInt(request.getParameter("productionGroupId"));
		Integer productionSystemId = Integer.parseInt(request.getParameter("productionSystemId"));
		Integer queryValue2 = pts.queryProductionVr(productionVr,productId);
		Integer queryName2 = pts.queryProductionName(productionName,Integer.parseInt(productId));//校验  名字
		Integer queryType2 = pts.queryProductionType(productionType,Integer.parseInt(productId));
		Integer queryValue=pts.queryProductionVr(productionVr,null);//验证校验规则
		Integer queryName = pts.queryProductionName(productionName,null);//校验  名字
		Integer queryType = pts.queryProductionType(productionType,null);
		if(queryValue==0||queryValue2==1){//说明数据库中没有这个数据
			jo.put("getValue",0);
			if(queryName ==0||queryName2==1){
				jo.put("getNameValue", 0);
				if(queryType ==0||queryType2==1){
					try {
						pts.updateProduct(productId,productionName,productionType,productionSeries,productionVr,productionDiscription,productionSte,labelTypeId,productionSystemId,productionGroupId,path);
						if(!files.isEmpty()){
							files.transferTo(new File(url+"/"+path));
						}
						jo.put("getTypeValue",0);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						ToolUtils.errorLog(this, e, request);
						jo.put("getTypeValue", 4);
					}
				}else{
					jo.put("getTypeValue", 1);
				}
			}else{
				jo.put("getNameValue", 1);
			}

		}else{//说明数据库里有这条数据
			jo.put("getValue",1);

		}
		return jo;

	}



}

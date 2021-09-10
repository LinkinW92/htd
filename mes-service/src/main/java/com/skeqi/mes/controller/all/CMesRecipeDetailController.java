package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesRecipeTService;
import com.skeqi.mes.util.ImageConverter;
import com.skeqi.mes.util.ToolUtils;

import sun.misc.BASE64Encoder;

/***
 *
 * @author ENS 配方明细管理
 *
 */
@Controller
@RequestMapping("/skq")
public class CMesRecipeDetailController {

	@Autowired
	CMesRecipeTService tservice;

	@Autowired
	CMesProductionTService pservice;

	@Autowired
	CMesMaterialService mservice;

	static BASE64Encoder encoder = new BASE64Encoder();

	/**
	 * 进入页面
	* @author FQZ
	 * @throws ServicesException
	* @date 2020年3月11日上午11:34:58
	 */
	@RequestMapping("recipeDetailAll")
	public String recipeDetailAll(HttpSession session,HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) throws ServicesException{
		String rid = request.getParameter("rid");   //配方ID
		String pid = request.getParameter("pid");   //产品ID
		List<CMesRecipeT> findAllRecipe;   //配方列表
		System.err.println("进入页面");
		CMesRecipeDatilT t = new CMesRecipeDatilT();
		if("0".toString().equals(pid)){
			pid=null;
		}
		if(pid!=null && pid!=""){
			findAllRecipe = tservice.findAllRecipeByPid(Integer.parseInt(pid));
			t.setProductionId(Integer.parseInt(pid));
		}else{
			findAllRecipe = tservice.findAllRecipe(null);
		}
		if(rid!=null && rid!=""){
			t.setRecipeId(Integer.parseInt(rid));
		}else{
			if(findAllRecipe.size()>0){
				t.setRecipeId(findAllRecipe.get(0).getId());
			}else{
				t.setRecipeId(null);
			}
		}
		PageHelper.startPage(page,10);
		List<CMesRecipeDatilT> findAllRecipeDatil = tservice.findAllRecipeDatil(t);   //配方明细列表
		PageInfo<CMesRecipeDatilT> pageInfo = new PageInfo<>(findAllRecipeDatil,5);
		List<CMesRecipeTypeT> findAllRecipeType = tservice.findAllRecipeType();   //配方类型列表
		List<CMesProductionT> findAllProduction = pservice.findAllProduction(null);;   //产品列表
		List<CMesJlMaterialT> findAllMaterial = tservice.findAllJLMaterial();  //物料列表
		request.setAttribute("findAllMaterial", findAllMaterial);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("findAllRecipe",findAllRecipe);
		request.setAttribute("findAllRecipeType",findAllRecipeType);
		request.setAttribute("findAllProduction", findAllProduction);
		request.setAttribute("rid",rid);
		request.setAttribute("pid",pid);
		return "recipe_control/recipeDetail";
	}

	/**
	 * 修改配方明细
	* @author FQZ
	* @date 2020年3月13日下午12:33:40
	 */
	@ResponseBody
	@RequestMapping(value="updaterecipedetails",method = RequestMethod.POST, consumes = "multipart/form-data")
	public Map<String,Object> updaterecipedetails(@RequestParam(required=false)MultipartFile file,HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		CMesRecipeDatilT recipe = new CMesRecipeDatilT();
		String path = null;
		System.err.println(" 修改配方明细");
		if(file!=null){
			byte[] bytes = file.getBytes();
			path = encoder.encodeBuffer(bytes).trim();
			recipe.setPathBinary(path);
		}
		String ids = request.getParameter("id");   //ID
		recipe.setId(Integer.parseInt(ids));
		String recipeId = request.getParameter("recipeId");  //配方ID


		recipe.setRecipeId(Integer.parseInt(recipeId));
		String types = request.getParameter("typeid");  //类型ID
		recipe.setStepCategory(types);
		Integer typeid = Integer.parseInt(types);
		String oldstepNo = request.getParameter("oldstepNo");  //旧的步序
		System.err.println("oldstepNo===="+oldstepNo);
		recipe.setOldstepNo(Integer.parseInt(oldstepNo));
		if(typeid==1 || typeid==16 || typeid==17){   //扫描总成
			String setpNos2 = request.getParameter("setpNos2");  //步序
			System.err.println("setpNos2===="+setpNos2);
			String materialNames2 = request.getParameter("materialNames2");  //名称
			String boltEQSs2 = request.getParameter("boltEQSs2");  //工位节拍
			String feaCodes2 = request.getParameter("feaCodes2");   //条码规则
			String chekornos2 = request.getParameter("chekornos2");   //是否校验
			recipe.setStepno(setpNos2);
			recipe.setMaterialName(materialNames2);
			recipe.setBolteqs(boltEQSs2);
			recipe.setFeacode(feaCodes2);
			recipe.setChekorno(chekornos2);
		}else if(typeid==2){   //扫描员工号
			String setpNos2 = request.getParameter("setpNos3");  //步序
			String materialNames2 = request.getParameter("materialNames3");  //名称
			String boltEQSs2 = request.getParameter("boltEQSs3");  //工位节拍
			String feaCodes2 = request.getParameter("feaCodes3");   //条码规则
			String chekornos2 = request.getParameter("chekornos3");   //是否校验
			String exactorNo3 = request.getParameter("exactorNos3");  //是否精追
			recipe.setStepno(setpNos2);
			recipe.setMaterialName(materialNames2);
			recipe.setBolteqs(boltEQSs2);
			recipe.setFeacode(feaCodes2);
			recipe.setChekorno(chekornos2);
			recipe.setexactorno(exactorNo3);
		}else if(typeid==3 || typeid==4){   //扫描物料
			String setpNos2 = request.getParameter("setpNos4");  //步序
			String boltEQSs2 = request.getParameter("boltEQSs4");  //工位节拍
			String feaCodes2 = request.getParameter("feaCodes4");   //条码规则
			String chekornos2 = request.getParameter("chekornos4");   //是否校验
			String exactorNo3 = request.getParameter("exactorNos4");  //是否精追
			String numberss4 = request.getParameter("numberss4");  //数量
			String materialPNs6 = request.getParameter("materialPNs6");  //物料pn
			String mids4 = request.getParameter("mids4");   //物料
			recipe.setStepno(setpNos2);
			recipe.setBolteqs(boltEQSs2);
			recipe.setFeacode(feaCodes2);
			recipe.setChekorno(chekornos2);
			recipe.setexactorno(exactorNo3);
			recipe.setNumbers(numberss4);
			recipe.setMaterialpn(materialPNs6);
			recipe.setMaterialName(mids4);
		}else if(typeid==5 || typeid==6){   //扫描模组   扫描电芯
			String setpNos2 = request.getParameter("setpNos5");  //步序
			String materialNames2 = request.getParameter("materialNames5");  //名称
			String boltEQSs2 = request.getParameter("boltEQSs5");  //工位节拍
			String feaCodes2 = request.getParameter("feaCodes5");   //条码规则
			String chekornos2 = request.getParameter("chekornos5");   //是否校验
			String exactorNo3 = request.getParameter("exactorNos5");  //是否精追
			String numberss4 = request.getParameter("numberss5");  //数量
			recipe.setStepno(setpNos2);
			recipe.setMaterialName(materialNames2);
			recipe.setBolteqs(boltEQSs2);
			recipe.setFeacode(feaCodes2);
			recipe.setChekorno(chekornos2);
			recipe.setexactorno(exactorNo3);
			recipe.setNumbers(numberss4);
		}else if(typeid==7){   //拧紧
			String setpNos2 = request.getParameter("setpNos6");  //步序
			String materialNames2 = request.getParameter("materialNames6");  //名称
			String numberss4 = request.getParameter("numberss6");  //数量
			String gunNos6 = request.getParameter("gunNos6");   //枪号
			String programNos6 = request.getParameter("programNos6");   //程序号
			String sleeveNos6 = request.getParameter("sleeveNos6");  //套筒号
			String aLimits6 = request.getParameter("aLimits6");   //角度范围
			String tLimits6 = request.getParameter("tLimits6");  //扭矩范围
			String boltEQSs6 = request.getParameter("boltEQSs6");   //工位节拍
			String reworktimes6 = request.getParameter("reworktimes6");  //返工次数
			recipe.setStepno(setpNos2);
			recipe.setMaterialName(materialNames2);
			recipe.setNumbers(numberss4);
			recipe.setGunNo(gunNos6);
			recipe.setProgramNo(programNos6);
			recipe.setSleeveNo(sleeveNos6);
			recipe.setaLimit(aLimits6);
			recipe.settLimit(tLimits6);
			recipe.setBolteqs(boltEQSs6);
			recipe.setReworktimes(reworktimes6);
		}else if(typeid==8  || typeid==9  ||typeid==13  || typeid==15){   //拧紧
			String setpNos2 = request.getParameter("setpNos7");  //步序
			String materialNames2 = request.getParameter("materialNames7");  //名称
			String boltEQSs2 = request.getParameter("boltEQSs7");  //工位节拍
			String numberss4 = request.getParameter("numberss7");  //数量
			String programNos6 = request.getParameter("programNos7");   //程序号
			recipe.setStepno(setpNos2);
			recipe.setMaterialName(materialNames2);
			recipe.setBolteqs(boltEQSs2);
			recipe.setNumbers(numberss4);
			recipe.setProgramNo(programNos6);
		}else if(typeid==10  || typeid==11  ||typeid==12){   //拧紧
			String setpNos2 = request.getParameter("setpNos8");  //步序
			String materialNames2 = request.getParameter("materialNames8");  //名称
			String boltEQSs2 = request.getParameter("boltEQSs8");  //工位节拍
			recipe.setStepno(setpNos2);
			recipe.setMaterialName(materialNames2);
			recipe.setBolteqs(boltEQSs2);
		}else if(typeid==14){   //结束
			String setpNos2 = request.getParameter("setpNos9");  //步序
			String materialNames2 = request.getParameter("materialNames9");  //名称
			recipe.setStepno(setpNos2);
			recipe.setMaterialName(materialNames2);
		}
		try {
			tservice.updateRecipeDetail(recipe);
			map.put("code", 0);
		} catch (ServicesException e) {
			// TODO: handle exception
			map.put("code",1);
			map.put("msg", e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			map.put("code",-1);
			map.put("msg","修改失败，请刷新页面重试");
		}
		return map;
	}

	/**
	 * 添加
	* @author FQZ
	* @date 2020年3月13日上午10:30:58
	 */
	@ResponseBody
	@RequestMapping(value="insertrecipeDetail",method = RequestMethod.POST, consumes = "multipart/form-data")
	public Map<String,Object> insertrecipeDetail(@RequestParam(required=false)MultipartFile file,HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		CMesRecipeDatilT recipe = new CMesRecipeDatilT();
		String stepCategory = request.getParameter("stepCategory");  //扫描类型
		String recipeId = request.getParameter("recipeId");//配方id
		String path = null;
		if(file!=null){
			byte[] bytes = file.getBytes();
			path = encoder.encodeBuffer(bytes).trim();
			recipe.setPathBinary(path);
		}
		recipe.setRecipeId(Integer.parseInt(recipeId));
		recipe.setStepCategory(stepCategory);
		if("1".toString().equals(stepCategory) || "16".toString().equals(stepCategory) || "17".toString().equals(stepCategory)){   //扫描总成
				String chekorno = request.getParameter("chekorno");  //是否校验
				String mid = request.getParameter("materialName");    //物料名称
				String feaCode = request.getParameter("feaCode");   //条码规则
				String boltEQS = request.getParameter("boltEQS");   //工位节拍
				recipe.setChekorno(chekorno);
				recipe.setMaterialName(mid);
				recipe.setFeacode(feaCode);
				recipe.setBolteqs(boltEQS);
		}else if("2".toString().equals(stepCategory)){   //扫描员工号
				String chekorno2 = request.getParameter("chekorno2");  //是否校验
				String materialName2 = request.getParameter("materialName2");   //名称
				String feaCode2 = request.getParameter("feaCode2");   //条码规则
				String boltEQS2 = request.getParameter("boltEQS2");   //工位节拍
				String exactorNo2 = request.getParameter("exactorNo2");   //精追、批追
				recipe.setChekorno(chekorno2);
				recipe.setMaterialName(materialName2);
				recipe.setFeacode(feaCode2);
				recipe.setBolteqs(boltEQS2);
				recipe.setexactorno(exactorNo2);
		}else if("3".toString().equals(stepCategory) || "4".toString().equals(stepCategory)){   //扫描物料、唯一物料
				String chekorno3 = request.getParameter("chekorno3");  //是否校验
				String mid = request.getParameter("mid");   //名称
				String feaCode3 = request.getParameter("feaCode3");   //条码规则
				String boltEQS3 = request.getParameter("boltEQS3");   //工位节拍
				String exactorNo3 = request.getParameter("exactorNo3");   //精追、批追
				String number3 = request.getParameter("number3");   //数量
				String materialPN6 = request.getParameter("materialPN6");   //物料pn
				System.err.println(exactorNo3);
				recipe.setChekorno(chekorno3);
				recipe.setMaterialName(mid);
				recipe.setFeacode(feaCode3);
				recipe.setBolteqs(boltEQS3);
				recipe.setexactorno(exactorNo3);
				recipe.setNumbers(number3);
				recipe.setMaterialpn(materialPN6);
		}else if("5".toString().equals(stepCategory) || "6".toString().equals(stepCategory)){   //扫描模组、扫描电芯
				String chekorno4 = request.getParameter("chekorno4");  //是否校验
				String mid = request.getParameter("materialName4");   //名称
				String feaCode4 = request.getParameter("feaCode4");   //条码规则
				String boltEQS4 = request.getParameter("boltEQS4");   //工位节拍
				String exactorNo4 = request.getParameter("exactorNo4");   //精追、批追
				String number4 = request.getParameter("number4");   //数量
				recipe.setChekorno(chekorno4);
				recipe.setMaterialName(mid);
				recipe.setFeacode(feaCode4);
				recipe.setBolteqs(boltEQS4);
				recipe.setexactorno(exactorNo4);
				recipe.setNumbers(number4);
		}else if("7".toString().equals(stepCategory)){   //拧紧
				String mid = request.getParameter("materialName5");   //名称
				String boltEQS5 = request.getParameter("boltEQS5");   //工位节拍
				String number5 = request.getParameter("number5");   //数量
				String gunNo5 = request.getParameter("gunNo5");   //枪号
				String programNo5 = request.getParameter("programNo5");   //程序号
				String sleeveNo5 = request.getParameter("sleeveNo5");   //套筒号
				String aLimit5 = request.getParameter("aLimit5");   //角度范围
				String tLimit5 = request.getParameter("tLimit5");   //扭矩范围
				String reworktimes5 = request.getParameter("reworktimes5");   //返工次数

				recipe.setMaterialName(mid);
				recipe.setBolteqs(boltEQS5);
				recipe.setNumbers(number5);
				recipe.setGunNo(gunNo5);
				recipe.setProgramNo(programNo5);
				recipe.setSleeveNo(sleeveNo5);
				recipe.setaLimit(aLimit5);
				recipe.settLimit(tLimit5);
				recipe.setReworktimes(reworktimes5);
		}else if("8".toString().equals(stepCategory) || "9".toString().equals(stepCategory) ||
				"13".toString().equals(stepCategory)  || "15".toString().equals(stepCategory)){   //气密性信息
				String mid = request.getParameter("materialName6");   //名称
				String boltEQS6 = request.getParameter("boltEQS6");   //工位节拍
				String number6 = request.getParameter("number6");   //数量
				String programNo6 = request.getParameter("programNo6");   //程序号

				recipe.setMaterialName(mid);
				recipe.setBolteqs(boltEQS6);
				recipe.setNumbers(number6);
				recipe.setProgramNo(programNo6);
		}else if("10".toString().equals(stepCategory) || "11".toString().equals(stepCategory) ||
				"12".toString().equals(stepCategory)){   //气密性信息
				String mid = request.getParameter("materialName7");   //名称
				String boltEQS7 = request.getParameter("boltEQS7");   //工位节拍

				recipe.setMaterialName(mid);
				recipe.setBolteqs(boltEQS7);
		}else if("14".toString().equals(stepCategory)){   //气密性信息
				String mid = request.getParameter("materialName8");   //名称
				recipe.setMaterialName(mid);

		}
			try {
				tservice.addRecipeDetail(recipe);
				map.put("code",0);
			}catch (ServicesException e) {
				// TODO: handle exception
				map.put("code",1);
				map.put("msg", e.getMessage());
			}catch (Exception e){
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				map.put("code",-1);
				map.put("msg","添加失败，请刷新页面重试");
			}
		return map;
	}

	/**
	 * 修改回显
	* @author FQZ
	* @date 2020年3月13日上午10:09:01
	 */
	@RequestMapping("updaterecipeDetail")
	@ResponseBody
	public Map<String,Object> updaterecipeDetail(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		System.err.println("修改回显");
		try {
			CMesRecipeDatilT findRecipeDetailByid = tservice.findRecipeDetailByid(Integer.parseInt(id));
			map.put("info",findRecipeDetailByid);
			map.put("code",0);
		} catch (ServicesException e) {
			// TODO: handle exception
			map.put("code",1);
			map.put("msg",e.getMessage());
		}catch(Exception e){
			map.put("code",2);
			map.put("msg",e.getMessage());
		}
		return map;
	}

	@RequestMapping("/delRecipeDetail")
	@ResponseBody
	public Map<String,Object> delRecipeDetail(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		String stepNo = request.getParameter("stepNo");
		String recipeId = request.getParameter("recipeId");
		System.err.println("删除配方详情");
		try {
			CMesRecipeDatilT c = new CMesRecipeDatilT();
			c.setId(Integer.parseInt(id));
			c.setStepno(stepNo);
			c.setRecipeId(Integer.parseInt(recipeId));
			tservice.delRecipeDatil(c);
			map.put("code", 0);
		} catch (ServicesException e) {
			map.put("code",1);
			map.put("msg",e.getMessage());
		} catch (Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			map.put("code",2);
			map.put("msg","删除失败，请刷新页面重试");
		}
		return map;
	}
}

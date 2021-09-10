package com.skeqi.mes.controller.qh;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesImportT;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;
import com.skeqi.mes.pojo.CMesTotalRecipeT;
import com.skeqi.mes.service.all.CMesImportRecipeService;
import com.skeqi.mes.service.all.CMesImportRecipesService;
import com.skeqi.mes.util.PageInfoUtil;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

@RestController
@SuppressWarnings("all")
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "导入配方", description = "导入配方", produces = MediaType.APPLICATION_JSON)
public class CMesImportRecipesControllerl {

	@Autowired
	public CMesImportRecipesService service;


	@Autowired
	private CMesImportRecipeService recipeService;
	static BASE64Encoder encoder = new BASE64Encoder();

	@RequestMapping(value = "/recipe/findAll", method = RequestMethod.POST)
	@ApiOperation(value = "导入配方列表", notes = "导入配方列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "tid", value = "总配方id", required = false, paramType = "query", dataType = "int"), })
	public JSONObject findAll(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "100") Integer pageSize, String tid) throws Exception {
		// PageHelper.startPage(pageSize,pageNum);
		JSONObject json = new JSONObject();
		List<CMesTotalRecipeT> findAllTotalRecipe = service.findAllTotalRecipe();
		if (tid == null || tid == "") {
			if (findAllTotalRecipe.size() > 0) {
				tid = findAllTotalRecipe.get(0).getId().toString();
			} else {
				tid = "0";
			}
		}
		List<CMesImportT> findAllRecipeDetailTwo = service.findAllRecipeDetailTwo(Integer.parseInt(tid));
		PageInfoUtil pageInfo = new PageInfoUtil(findAllRecipeDetailTwo, 0, pageSize, pageNum);
		json.put("pageInfo", pageInfo);
		return json;
	}



	/**
	 * 删除配方
	 *
	 */
	@RequestMapping(value = "/recipe/delete", method = RequestMethod.POST)
	@ApiOperation(value = "删除配方", notes = "删除配方")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vid", value = "版本ID", required = false, paramType = "query", dataType = "int"), })
	public JSONObject delete(Integer vid) {
		JSONObject json = new JSONObject();
		try {
			recipeService.deleteVersion(vid); // 删除
			recipeService.deleteVersionDetail(vid);
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "删除失败");
		}
		return json;
	}

	/**
	 * 使用配方
	 *
	 * @author FQZ
	 * @date 2020年3月23日下午3:01:24
	 */
	@RequestMapping(value = "/recipe/userRecipe", method = RequestMethod.POST)
	@ApiOperation(value = "使用配方", notes = "使用配方")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vid", value = "版本ID", required = false, paramType = "query", dataType = "int"), })
	public JSONObject userRecipe(HttpServletRequest request, Integer vid) {
		JSONObject json = new JSONObject();
		try {
			// 保存配方ID
			HashSet<Integer> set = new HashSet<>();
			// 根据版本ID获取版本详情
			List<CMesRecipeVersionDetail> findAllRecipeVersionDetail = recipeService.findAllRecipeVersionDetail(vid);
			// 添加配方明细
			List<CMesRecipeDatilT> list = new ArrayList<CMesRecipeDatilT>();
			// 循环添加到配方明细实体类中
			for (CMesRecipeVersionDetail cMesRecipeVersionDetail : findAllRecipeVersionDetail) {
				// 根据产品和工位查询是否有此配方
				List<CMesProductionRecipeT> findProductionRecipe = recipeService.findProductionRecipe(
						cMesRecipeVersionDetail.getProductionName(), cMesRecipeVersionDetail.getStationName());
				if (findProductionRecipe.size() == 0) {
					json.put("msg", "没有[" + cMesRecipeVersionDetail.getProductionName() + "]产品和["
							+ cMesRecipeVersionDetail.getStationName() + "]工位的配方，请先配置配方");
					return json;
				} else if (findProductionRecipe.size() > 1) {
					json.put("msg", "配方数量大于1,请删除多余的配方！");
					return json;
				}
				set.add(findProductionRecipe.get(0).getId());
				CMesRecipeDatilT recipe = new CMesRecipeDatilT();
				List<CMesRecipeTypeT> findRecipeType = recipeService
						.findRecipeType(cMesRecipeVersionDetail.getStepCategory()); // 配方类别
				if (findRecipeType.size() > 0) {
					recipe.setStepCategory(findRecipeType.get(0).getId().toString());
				} else {
					json.put("msg", "没有[" + cMesRecipeVersionDetail.getStepCategory() + "]的配方类别");
				}
				recipe.setMaterialName(cMesRecipeVersionDetail.getMaterialName()); // 步骤名称
				recipe.setGunNo(cMesRecipeVersionDetail.getGunNo()); // 枪号
				recipe.setProgramNo(cMesRecipeVersionDetail.getProgramNo()); // 程序号
				recipe.setPhotoNo(cMesRecipeVersionDetail.getPhotoNo()); // 相机号
				recipe.setSleeveNo(cMesRecipeVersionDetail.getSleeveNo()); // 套筒号
				recipe.setReworktimes(cMesRecipeVersionDetail.getReworktimes()); // 返工次数
				recipe.setFeacode(cMesRecipeVersionDetail.getFeacode()); // 校验规则
				recipe.setChekorno(cMesRecipeVersionDetail.getChekorno()); // 是否校验
				recipe.setRevieworno(cMesRecipeVersionDetail.getRevieworno()); // 是否追溯
				recipe.setexactorno(cMesRecipeVersionDetail.getExactorno()); // 精追批追
				recipe.setMaterialpn(cMesRecipeVersionDetail.getMaterialpn()); // 物料PN
				recipe.setBolteqs(cMesRecipeVersionDetail.getBolteqs()); // 工位节拍
				recipe.setaLimit(cMesRecipeVersionDetail.getaLimit()); // 角度上线
				recipe.settLimit(cMesRecipeVersionDetail.gettLimit()); // 扭矩上线
				recipe.setRecipeId(findProductionRecipe.get(0).getId()); // 配方ID
				recipe.setUploadCode(cMesRecipeVersionDetail.getUploadCode()); // 上传代码
				recipe.setStepno(cMesRecipeVersionDetail.getStepno().toString()); // 步序
				recipe.setNumbers(cMesRecipeVersionDetail.getNumbers()); // 数量
				recipe.setPicturnPath(cMesRecipeVersionDetail.getPicturnPath()); // 图片路径
				recipe.setBoltjson(cMesRecipeVersionDetail.getBoltjson()); // 螺栓json数据
				recipe.setPathBinary(cMesRecipeVersionDetail.getPathBinary()); // 图片二进制
				list.add(recipe);
			}
			// 删除配方明细
			for (Integer i : set) {
				recipeService.delRecipeDetail(i);
			}
			// 循环添加
			for (CMesRecipeDatilT recipe : list) {
				recipeService.addRecipeDetail(recipe);
			}
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}



	@RequestMapping(value = "/recipe/importTotalRecipe", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "excel文件导入配方信息 ", notes = "通过excel文件导入配方信息 ")
	@Transactional(rollbackFor = { Exception.class })
	@OptionalLog(module="生产", module2="导入配方", method="excel文件导入配方信息")
	public JSONObject importTotalRecipe(HttpServletRequest request, @RequestBody JSONArray info) throws Exception {
		List<CMesTotalRecipeT> totalList = new ArrayList<CMesTotalRecipeT>();   //存放总配方名称
		List<CMesRecipe> recipeList = new ArrayList<CMesRecipe>();   //存放工位配方
		List<CMesRecipeDatilT> recipeDatilList = new ArrayList<CMesRecipeDatilT>();   //存放配方明细

		JSONObject json = new JSONObject();

		json.put("code", 0);
		if (info.size() < 0) {
			json.put("code", 1);
			json.put("msg", "导入数据为空");
			return json;
		}
		Integer productId = 0;
		try {
			for (int i = 0; i < info.size(); i++) {
				CMesTotalRecipeT total =  new CMesTotalRecipeT();
				CMesRecipe recipe = new CMesRecipe();
				CMesRecipeDatilT datil = new CMesRecipeDatilT();

				JSONObject jsonObject = info.getJSONObject(i) ;
				//----------------------------------------------------------
				Object totalName1 = jsonObject.get("totalName");//总配方
				Object dt = jsonObject.get("dt");
				String date = null;
				if (!StringUtils.isEmpty(dt)) {
					date = dt.toString();
				}
				if (totalName1 == null || totalName1 == "") {
					json.put("code", 1);
					json.put("msg", "总配方名称不能为空");
					return json;
				}else {
					// 判断总配方名称是否重复
					Integer count = service.findCountByTotalName(totalName1.toString());
					if(count > 0){
						json.put("code", 1);
						json.put("msg", "总配方名称已存在");
						return json;
					}
				}
				String totalName = totalName1.toString();//总配方名称
				total.setTotalRecipeName(totalName);
				//----------------------------------------------------------
				Object productName1 = jsonObject.get("productName");//产品名称
				if (productName1 == null || productName1 == "") {
					json.put("code", 1);
					json.put("msg", "产品名称不能为空");
					return json;
				} else {
					productId = service.findProductionId(productName1.toString());//得到产品id
					if (productId == null) {
						json.put("code", 1);
						json.put("msg", "[" + productName1 + "]产品不存在");
						return json;
					}else{
						total.setProductionId(productId);
					}
				}
				//----------------------------------------------------------
				Object lineName1 = jsonObject.get("lineName");
				if (lineName1 == null || lineName1 == "") {
					json.put("code", 1);
					json.put("msg", "产线名称不能为空");
					return json;
				}
				Integer lineId = service.findLineId(lineName1.toString());
				if (lineId.equals(null)) {
					json.put("code", 1);
					json.put("msg", "当前产线不存在");
					return json;
				}else{
					total.setLineId(lineId);
				}
				//----------------------------------------------------------
				Object recipeName1 = jsonObject.get("recipeName");
				if (recipeName1 == null || recipeName1 == "") {
					json.put("code", 1);
					json.put("msg", "工位配方不能为空");
					return json;
				}else{
					recipe.setRecipeName(recipeName1.toString());
					datil.setRecipeName(recipeName1.toString());
				}
				//----------------------------------------------------------
				Object stationName1 = jsonObject.get("stationName");
				if (stationName1 == null || stationName1 == "") {
					json.put("code", 1);
					json.put("msg", "所属工位不能为空");
					return json;
				}
				Integer stationId = service.findByNameStation(stationName1.toString(),lineId);
				if (lineId.equals(null)) {
					json.put("code", 1);
					json.put("msg", "当前工位不存在");
					return json;
				}else{
					recipe.setStationId(stationId);
				}
				//----------------------------------------------------------
				Object stepCategoryName1 = jsonObject.get("stepCategoryName");//操作类别
				if (stepCategoryName1 == null || stepCategoryName1 == "") {
					json.put("code", 1);
					json.put("msg", "操作类别不能为空");
					return json;
				}
				Integer stepCategoryId = service.findByNameType(stepCategoryName1.toString());
				if (lineId.equals(null)) {
					json.put("code", 1);
					json.put("msg", "操作类别不存在");
					return json;
				}else{
					datil.setStepCategory(stepCategoryId.toString());
				}
				//----------------------------------------------------------
				Object materialName = jsonObject.get("materialName");//工序名称
				if (materialName == null || materialName == "") {
					json.put("code", 1);
					json.put("msg", "工序名称不能为空");
					return json;
				}else{
					datil.setMaterialName(materialName.toString());
				}
				//----------------------------------------------------------
				Object numbers = jsonObject.get("numbers");
				if(numbers==null) {
					datil.setNumbers(String.valueOf(""));
				}else {
					datil.setNumbers(String.valueOf(numbers));
				}

				Object gunNo = jsonObject.get("gunNo");
				if(gunNo == null) {
					datil.setGunNo(String.valueOf(""));
				}else {
					datil.setGunNo(String.valueOf(gunNo));
				}

				//----------------------------------------------------------
				Object programNo = jsonObject.get("programNo");
				if(programNo == null) {
					datil.setProgramNo(String.valueOf(""));
				} else {
					datil.setProgramNo(String.valueOf(programNo));
				}

				//----------------------------------------------------------
				Object materialPn = jsonObject.get("materialPn");
				if(materialPn == null) {
					datil.setMaterialpn(String.valueOf(""));
				}else {
					datil.setMaterialpn(String.valueOf(materialPn));
				}

				//----------------------------------------------------------
				Object sleeveNo = jsonObject.get("sleeveNo");
				if(sleeveNo == null) {
					datil.setSleeveNo(String.valueOf(""));
				}else {
					datil.setSleeveNo(String.valueOf(sleeveNo));
				}

				//----------------------------------------------------------
				Object stepNo = jsonObject.get("stepNo");
				if (stepNo == null || stepNo == "") {
					json.put("code", 1);
					json.put("msg", "工序不能为空");
					return json;
				}else{
					datil.setStepno(stepNo.toString());
				}
				//----------------------------------------------------------
				Object feacode = jsonObject.get("feacode");
				if(feacode == null) {
					datil.setFeacode(String.valueOf(""));
				}else {
					datil.setFeacode(String.valueOf(feacode));
				}

				//----------------------------------------------------------
				Object boltEqs = jsonObject.get("bolteqs");
				if(boltEqs == null) {
					datil.setBolteqs(String.valueOf(""));
				}else {
					datil.setBolteqs(String.valueOf(boltEqs));
				}
				//----------------------------------------------------------
				Object photoNo = jsonObject.get("photoNo");
				if(photoNo == null) {
					datil.setPhotoNo(String.valueOf(""));
				}else {
					datil.setPhotoNo(String.valueOf(photoNo));
				}

				//----------------------------------------------------------
				Object reworkTimes = jsonObject.get("reworktimes");
				if(reworkTimes == null) {
					datil.setReworktimes(String.valueOf(""));
				}else {
					datil.setReworktimes(String.valueOf(reworkTimes));
				}

				//----------------------------------------------------------
				Object chekorno = jsonObject.get("chekorno");//是否校验
				if (chekorno == null || chekorno == "") {
					datil.setChekorno("0");
				}else{
					datil.setChekorno(chekorno.toString());
				}
				//----------------------------------------------------------
				Object revieworno = jsonObject.get("revieworno");//是否追溯
				if (revieworno == null || revieworno == "") {
					datil.setRevieworno("0");
				}else{
					datil.setRevieworno(revieworno.toString());
				}
				//----------------------------------------------------------
				Object exactorno = jsonObject.get("exactorno");//物料类别
				if (exactorno == null || exactorno == "") {
					datil.setexactorno("0");
				}else{
					datil.setexactorno(exactorno.toString());
				}
				//----------------------------------------------------------
				Object aLimit = jsonObject.get("aLimit");
				if(aLimit == null) {
					datil.setaLimit(String.valueOf(""));
				}else {
					datil.setaLimit(String.valueOf(aLimit));
				}

				//----------------------------------------------------------
				Object tLimit = jsonObject.get("tLimit");
				if(tLimit == null) {
					datil.settLimit(String.valueOf(""));
				}else {
					datil.settLimit(String.valueOf(tLimit));
				}

				//----------------------------------------------------------
				Object boltJson = jsonObject.get("boltJson");
				if(boltJson == null) {
					datil.setBoltjson(String.valueOf(""));
				}else {
					datil.setBoltjson(String.valueOf(boltJson));
				}

				//----------------------------------------------------------

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
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}finally {
			return json;
		}

	}
}

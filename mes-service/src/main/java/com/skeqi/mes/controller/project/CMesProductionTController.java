package com.skeqi.mes.controller.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.service.project.CMesProLIneService;
import com.skeqi.mes.service.project.CMesProductionService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "产品管理", description = "产品管理", produces = MediaType.APPLICATION_JSON)
public class CMesProductionTController {

	@Autowired
	CMesProductionService service;

	@Autowired
	CMesProLIneService lineService;

	@RequestMapping(value = "findPro", method = RequestMethod.POST)
	@ApiOperation(value = "产品列表", notes = "产品列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productionName", value = "产品名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pages", value = "当前页", required = false, paramType = "query"), })
	public JSONObject findPro(HttpServletRequest request, String productionName, Integer pages) {
		JSONObject json = new JSONObject();
		try {
			List<CMesProductionT> findAllPro = null;
			if (pages == null || pages == 0) {
				findAllPro = service.findAllPro(productionName);
			} else {
				PageHelper.startPage(pages, 10);
				findAllPro = service.findAllPro(productionName);
				PageInfo<CMesProductionT> pageinfo = new PageInfo<CMesProductionT>(findAllPro, 5);
				json.put("pageNum", pageinfo.getTotal());
				findAllPro = pageinfo.getList();
			}

			json.put("code", 0);
			json.put("info", findAllPro);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}

	@RequestMapping(value = "addPro", method = RequestMethod.POST)
	@ApiOperation(value = "添加产品", notes = "添加产品")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productionName", value = "产品名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionType", value = "产品编码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionTrademark", value = "产品简称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionSeries", value = "产品型号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionVr", value = "产品规则", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionDiscription", value = "产品描述", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionSte", value = "在线/离线（0/1）", required = true, paramType = "query"),
			@ApiImplicitParam(name = "path", value = "图片", required = true, paramType = "query"), })
	public JSONObject addPro(HttpServletRequest request) {

		JSONObject json = new JSONObject();

		try {

			String productionName = EqualsUtil.string(request, "productionName", "产品名称", true);
			String productionType = EqualsUtil.string(request, "productionType", "产品编码", true);
			String productionTrademark = EqualsUtil.string(request, "productionTrademark", "产品简称", true);
			String productionSeries = EqualsUtil.string(request, "productionSeries", "产品型号", true);
			String productionVr = EqualsUtil.string(request, "productionVr", "产品标记", true);
			String productionDiscription = EqualsUtil.string(request, "productionDiscription", "产品描述", false);
			String productionSte = EqualsUtil.string(request, "productionSte", "状态", true);
			String path = EqualsUtil.string(request, "path", "图片", false);
//			Integer codeDigit = EqualsUtil.integer(request, "codeDigit", "产品条码位数", true);

			CMesProductionT dx = new CMesProductionT();
			dx.setProductionName(productionName);
			dx.setProductionType(productionType);
			dx.setProductionTrademark(productionTrademark);
			dx.setProductionSeries(productionSeries);
			dx.setProductionVr(productionVr);
			dx.setProductionDiscription(productionDiscription);
			dx.setProductionSte(productionSte);
			dx.setPath(path);
//			dx.setCodeDigit(codeDigit);

			Integer a = service.findByProductionType(productionType);
			if (a > 0) {
				throw new ServicesException("当前产品编码已存在");
			}
			service.addPro(dx);
			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "updatePro", method = RequestMethod.POST)
	@ApiOperation(value = "修改产品", notes = "修改产品")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productionName", value = "产品名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionType", value = "产品类型", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionTrademark", value = "产品商标", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionSeries", value = "产品系列", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionVr", value = "产品规则", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionDiscription", value = "产品描述", required = true, paramType = "query"),
			@ApiImplicitParam(name = "productionSte", value = "在线/离线（0/1）", required = true, paramType = "query"),
			@ApiImplicitParam(name = "path", value = "图片", required = true, paramType = "query"),
			@ApiImplicitParam(name = "id", value = "ID", required = true, paramType = "query"),

	})
	public JSONObject updatePro(HttpServletRequest request, CMesProductionT pro) {
		JSONObject json = new JSONObject();
		Integer a = service.findByProductionTypeL(pro.getProductionType(), pro.getId());

		if (a > 0) {
			json.put("code", 1);
			json.put("msg", "当前产品编码已存在");
		} else {
			try {
				service.updatePro(pro);
				json.put("code", 0);
				json.put("msg", "修改成功");
			} catch (ServicesException e) {
				// TODO: handle exception
				json.put("code", 1);
				json.put("msg", e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				// TODO: handle exception
				json.put("code", 1);
				json.put("msg", "未知错误");
			}
		}
		return json;
	}

	@RequestMapping(value = "deletePro", method = RequestMethod.POST)
	@ApiOperation(value = "删除产品", notes = "删除产品")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"), })
	public JSONObject deletePro(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			service.delPro(id);
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "importProduction", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导入产品 ", notes = "导入产品 ")
	@Transactional(rollbackFor = { Exception.class })
	public JSONObject importProduction(HttpServletRequest request, @RequestBody JSONArray info) throws Exception {
		JSONObject json = new JSONObject();
		Integer b = 0;
		int sum = info.size();
		if (info.size() < 0) {
			json.put("code", 1);
			json.put("msg", "导入数据为空");
			return json;
		}
		try {
			for (int i = 0; i < info.size(); i++) {
				JSONObject jsonObject = info.getJSONObject(i);
				Object planNames = jsonObject.get("productionSeries");
				if (planNames == null || planNames == "") {
					json.put("code", 1);
					json.put("msg", "型号不能为空");
					return json;
				}
				String productionSeries = planNames.toString();
				Object proName = jsonObject.get("proName");
				if (proName == null || proName == "") {
					json.put("code", 1);
					json.put("msg", "产品名称不能为空");
					return json;
				}
				String name = proName.toString();
				Object planNumbers = jsonObject.get("productionType");
				if (planNumbers == null || planNumbers == "") {
					json.put("code", 1);
					json.put("msg", "产品编码不能为空");
					return json;
				}
				String productionType = planNumbers.toString();
				Integer c = 0;
				if (!productionType.equals("无")) {
					c = service.findByProductionType(productionType);
				}
				if (c > 0) {
					b++;
				} else {
					Object s = jsonObject.get("productionDiscription");
					if (s == null || s == "") {
						json.put("code", 1);
						json.put("msg", "产品描述不能为空");
						return json;
					}
					String productionDiscription = s.toString();

					Object a = jsonObject.get("productionTrademark");
					if (a == null || a == "") {
						json.put("code", 1);
						json.put("msg", "产品简称不能为空");
						return json;
					}

					Object productionVr = jsonObject.get("productionVr");
					if (productionVr == null || productionVr == "") {
						json.put("code", 1);
						json.put("msg", "产品标记不能为空");
						return json;
					}
					String proVr = productionVr.toString();// 产品标记
					Integer lineT = lineService.findProVrByLine(proVr);// 查询产线是否有这个产品标记
					if (!StringUtils.isEmpty(lineT)) {
						// String productMark = lineT.getProductMark();//产品标记
						String productionTrademark = a.toString();
						CMesProductionT t = new CMesProductionT();
						t.setProductionType(productionType);
						t.setProductionDiscription(productionDiscription);
						t.setProductionSeries(productionSeries);
						t.setProductionName(name);
						t.setProductionTrademark(productionTrademark);
						t.setProductionVr(proVr);
//						t.setCodeDigit(jsonObject.getInteger("codeDigit"));
						service.addProL(t);
					}

				}
			}
			json.put("code", 0);
			json.put("sum", sum);
			json.put("error", b);
			json.put("msg", "导入成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

}

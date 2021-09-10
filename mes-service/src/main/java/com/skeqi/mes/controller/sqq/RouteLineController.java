package com.skeqi.mes.controller.sqq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.sqq.RouteLineService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

@RestController
@SuppressWarnings("all")
@RequestMapping(value = "api")
public class RouteLineController {

	@Autowired
	RouteLineService service;

	@RequestMapping(value = "/findAllStation", method = RequestMethod.POST)
	public Rjson findAllStation(HttpServletRequest request) {
		try {
			Integer lineId = EqualsUtil.integer(request, "lineId", "产线id", true);
			List<JSONObject> list = service.findAllStation(lineId);
			return new Rjson().success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}

	}

	@RequestMapping(value = "/findAllLine", method = RequestMethod.POST)
	public Rjson findAllLine(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findAllLine();
			return new Rjson().success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	@RequestMapping(value = "/findAllProduction", method = RequestMethod.POST)
	public Rjson findAllProduction(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findAllProduction();
			return new Rjson().success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	@RequestMapping(value = "/findAllRoute", method = RequestMethod.POST)
	public Rjson findAllRoute(HttpServletRequest request) {
		try {
			Integer lineId = null;
			Integer productionId = null;
			if(!StringUtils.isEmpty(request.getParameter("lineId")))
				lineId = Integer.parseInt(request.getParameter("lineId"));
			if(!StringUtils.isEmpty(request.getParameter("productionId")))
				productionId = Integer.parseInt(request.getParameter("productionId"));
			List<JSONObject> list = service.findAllRoute(lineId, productionId);
			return new Rjson().success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	@RequestMapping(value = "/findRouteById", method = RequestMethod.POST)
	public Rjson findRouteById(HttpServletRequest request) {
		try {
			Integer routeId = EqualsUtil.integer(request, "routeId", "路线id", true);
			List<JSONObject> list = service.findRouteById(routeId);
			return new Rjson().success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	// 查询工艺路线是否存在
	@RequestMapping(value = "/findRoute", method = RequestMethod.POST)
	public Rjson findRoute(HttpServletRequest request) {
		try {
			Integer lineId = EqualsUtil.integer(request, "lineId", "产线id", true);
			Integer productionId = EqualsUtil.integer(request, "productionId", "产品id", true);
			Integer routeId = EqualsUtil.integer(request, "routeId", "路线id", true);
			List<JSONObject> list = service.findRoute(lineId, productionId, routeId);
			JSONObject dx = new JSONObject();

			if (list == null || list.size() == 0) {
				dx.put("flag", 1);
			} else {
				dx.put("flag", 0);
			}
			dx.put("list", list);
			return new Rjson().success("查询成功", dx);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	// 添加工艺路线是否存在
	@RequestMapping(value = "/addRoute", method = RequestMethod.POST)
	public Rjson addRoute(HttpServletRequest request, String name, Integer lineId, String json, Integer status, Integer productionId) {
		try {

			JSONObject jo = new JSONObject();
			CMesRoutingT t = new CMesRoutingT();
			t.setLineId(lineId);
			t.setProductionId(productionId);
			t.setName(name);
			t.setJson(json);
			t.setStatus(status);
			try {
				if (status == 1) { // 取消其他默认路线
					service.updatestatus(lineId, productionId);
				}
				service.addRoute(t);
				List<JSONObject> jsonList = test(json);
				for (int j = 0; j < jsonList.size(); j++) {
					JSONObject jsonObject = jsonList.get(j);
					String id = (String) jsonObject.get("id");
					Integer a = service.findStationId(id);
					if (a > 0) {
						int sid = Integer.parseInt(id);// 工位id
						Integer rid = t.getId();// 工艺id
						service.addProductionWay(sid, rid, j);
					}
				}

				jo.put("flag", 0);
				jo.put("msg", "添加工艺路线成功");
				return new Rjson().success("查询成功", jo);
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				jo.put("flag", 1);
				jo.put("msg", "工艺路线失败");
				return new Rjson().success("添加工艺路线失败", jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	// 解析工艺路线
	public static List<JSONObject> test(String str) throws Exception {
		JSONObject json = JSONObject.parseObject(str);
		List<Object> lineList = JSONObject.parseArray(json.get("lineList").toString());
		List<Object> nodeList = JSONObject.parseArray(json.get("nodeList").toString());

		List<JSONObject> jsons = new ArrayList<JSONObject>();

		for (int i = 0; i < lineList.size(); i++) {
			JSONObject line = JSONObject.parseObject(lineList.get(i).toString());
			if (i == 0) {
				if (!line.getString("from").equals("start") || line.getString("to").equals("")) {
					throw new Exception("工艺路线有误：" + line);
				} else {
					for (int j = 0; j < nodeList.size(); j++) {
						JSONObject node = JSONObject.parseObject(nodeList.get(j).toString());
						if (node.getString("id").equals("start")) {
							jsons.add(node);
							nodeList.remove(j);
							break;
						} else if (j == nodeList.size() - 1) {
							throw new Exception("工艺路线有误：" + line);
						}
					}
				}
			} else if (i < lineList.size()) {
				for (int j = 0; j < nodeList.size(); j++) {
					JSONObject node = JSONObject.parseObject(nodeList.get(j).toString());
					if (node.getString("id").equals(line.getString("from"))) {
						jsons.add(node);
						nodeList.remove(j);
						break;
					} else if (j == nodeList.size() - 1) {
						throw new Exception("工艺路线有误：" + line);
					}
				}
				if (i == lineList.size() - 1) {
					if (!line.getString("to").equals("end") || line.getString("to").equals("")) {
						throw new Exception("工艺路线有误：" + line);
					} else {
						for (int j = 0; j < nodeList.size(); j++) {
							JSONObject node = JSONObject.parseObject(nodeList.get(j).toString());
							if (node.getString("id").equals("end")) {
								jsons.add(node);
								nodeList.remove(j);
								break;
							} else if (j == nodeList.size() - 1) {
								throw new Exception("工艺路线有误：" + line);
							}
						}
					}
				}

			}
		}

		for (JSONObject jsonObject : jsons) {
			jsonObject.remove("ico");
			jsonObject.remove("left");
			jsonObject.remove("top");
			jsonObject.remove("type");
		}

		return jsons;
	}

	// 删除工艺路线是否存在
	@RequestMapping(value = "/deleteRoute", method = RequestMethod.POST)
	public Rjson deleteRoute(HttpServletRequest request) {
		try {
			Integer routeId = EqualsUtil.integer(request, "routeId", "路线id", true);
			JSONObject jo = new JSONObject();
			try {
				service.deleteRoute(routeId);
				jo.put("flag", 0);
				jo.put("msg", "删除工艺路线成功");
				return new Rjson().success("查询成功", jo);
			} catch (Exception e) {
				jo.put("flag", 1);
				jo.put("msg", "删除工艺路线失败");
				return new Rjson().error(e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	// 修改工艺路线是否存在
	@RequestMapping(value = "/updateRoute", method = RequestMethod.POST)
	public Rjson updateRoute(HttpServletRequest request) {
		try {
			Integer routeId = EqualsUtil.integer(request, "routeId", "路线id", true);
			String json = EqualsUtil.string(request, "json", "工艺路线", true);
			JSONObject jo = new JSONObject();
			try {
				service.updateRoute(routeId, json);
				jo.put("flag", 0);
				jo.put("msg", "修改工艺路线成功");
				return new Rjson().success("查询成功", jo);
			} catch (Exception e) {
				jo.put("flag", 1);
				jo.put("msg", "工艺路线失败");
				return new Rjson().success("删除工艺路线失败", jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}

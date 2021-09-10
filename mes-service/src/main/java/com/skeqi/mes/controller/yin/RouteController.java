package com.skeqi.mes.controller.yin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.Routing;
import com.skeqi.mes.service.yin.RouteService;
import com.skeqi.mes.service.yin.UsersService;
import com.skeqi.mes.util.ToolUtils;

@Controller
@RequestMapping("skq")
public class RouteController {
	@Autowired
	UsersService usersService;
	@Autowired
	RouteService routeService;

	/*
	 * @RequestMapping("processRoute") public String processRoute(HttpServletRequest
	 * request, HttpSession session) {
	 *
	 * Map<String, Object> map_production = new HashMap<String, Object>();
	 * List<CMesProductionT> productionList =
	 * usersService.productionList(map_production); List<CMesLineT> list_line =
	 * usersService.findAllLine();
	 *
	 * String str_line = request.getParameter("line_"); if (str_line != null) {
	 * Map<String, Object> map = new HashMap<String, Object>(); map.put("lineName",
	 * str_line); List<CMesStationT> list_station =
	 * usersService.findStationByLine(map); session.removeAttribute("stationList");
	 * session.setAttribute("stationList", list_station); } else { Map<String,
	 * Object> map = new HashMap<String, Object>(); map.put("lineId",
	 * list_line.get(0).getId()); List<CMesStationT> stationList =
	 * usersService.stationList(map); session.removeAttribute("stationList");
	 * session.setAttribute("stationList", stationList); }
	 * session.setAttribute("lineLIst", list_line);
	 * session.setAttribute("productionList", productionList); return
	 * "processPlan_control/processRoute"; }
	 */

	@SuppressWarnings("rawtypes")
	@RequestMapping("staionLine")
	public @ResponseBody Map staionLine(HttpServletRequest request, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		String str_line = request.getParameter("line_");
		map.put("lineName", str_line);

		List<CMesStationT> list_station = usersService.findStationByLine(map);
		Map<String, List<CMesStationT>> map_line_station = new HashMap<String, List<CMesStationT>>();
		map_line_station.put("stationByLine", list_station);
		return map_line_station;
	}

	public List<CMesStationT> ChangeStation(List<CMesStationT> list) {
		List<CMesStationT> list_station = new ArrayList<CMesStationT>();
		for (int i = 0; i < list.size(); i++) {
			CMesStationT cms = list.get(i);
//			cms.setStationType(((cms.getStationType().equals("0")) ? "绾垮唴绔�" : "绾垮绔�"));
//			cms.setStationRecipeornot(((cms.getStationRecipeornot().equals("0")) ? "鏄�" : "鍚�"));
//			cms.setStationAgvornot(((cms.getStationAgvornot().equals("0")) ? "鏄�" : "鍚�"));
//			cms.setStationLightornot(((cms.getStationLightornot().equals("0")) ? "鏄�" : "鍚�"));
//			cms.setStationPrintornot(((cms.getStationPrintornot().equals("0")) ? "鏄�" : "鍚�"));
//			cms.setStationEndornot(((cms.getStationEndornot().equals("0")) ? "鏄�" : "鍚�"));
//			cms.setStationGunornot(((cms.getStationGunornot().equals("0")) ? "鏄�" : "鍚�"));
			list_station.add(cms);
		}
		return list_station;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("GetRoute")
	public @ResponseBody Map GetRoute(HttpServletRequest request, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		String str_product = request.getParameter("product");
		String str_line = request.getParameter("line");
		Map<String, Object> map_productName_ = new HashMap<String, Object>();
		map_productName_.put("productName", str_product);

		int a_ = usersService.selectIDByName(map_productName_);
		map.put("productID", a_);

		Map<String, Object> map_LINENAME_ = new HashMap<String, Object>();
		map_LINENAME_.put("str_line", str_line);
		int b_ = usersService.GetLineIDByName(map_LINENAME_);

		map.put("productLine", b_);
		List<Routing> list_route = usersService.GetRoute(map);
		if (list_route.size() > 0) {
			Map<String, Object> map_route = new HashMap<String, Object>();
			map_route.put("route", list_route.get(0).route);
			map_route.put("lineName", list_route.get(0).lineName);
			System.err.println(list_route.get(0).lineName);
			return map_route;
		} else {
			List<CMesLineT> list_line = usersService.findAllLine();
			Map<String, Object> map_route = new HashMap<String, Object>();
			map_route.put("route", "nohave");
			if (list_line.size() > 0) {
				map_route.put("lineName", list_line.get(0).getName());
			}
			System.err.println("nohave");
			return map_route;
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("DeleteRoute")
	public @ResponseBody Map DeleteRoute(HttpServletRequest request, HttpSession session) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			String str_product = request.getParameter("product");
			Map<String, Object> map_delete = new HashMap<String, Object>();
			map_delete.put("productionName", str_product);
			usersService.DeleteRoute_Routing_ByproductName(map_delete);

			Map<String, Object> map_delete_way = new HashMap<String, Object>();
			map_delete_way.put("productionName", str_product);
			usersService.DeleteRoute_Production_ByproductName(map_delete_way);
			result.put("flag", "true");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			result.put("flag", "false");
			return result;
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("SaveRoute")
	public @ResponseBody Map SaveRoute(HttpServletRequest request, HttpSession session) {
		@SuppressWarnings("unused")
		Map<Integer, Integer> map_route = new HashMap<Integer, Integer>();
		@SuppressWarnings("unchecked")
		List<Integer> list_route = new ArrayList();
		Map<String, String> result = new HashMap<String, String>();
		@SuppressWarnings("unused")
		boolean flag = false;
		try {

			String str_line = request.getParameter("line");
			String str_route = request.getParameter("para");
			String str_product = request.getParameter("product");
			String min_station = "";
			int min_station_ = 0;
			min_station = str_route.substring(13, 15);
			if (min_station.matches("[0-9]{1,}")) {
				min_station_ = Integer.parseInt(min_station.trim());
			} else {
				min_station_ = Integer.parseInt(min_station.replace(':', ' ').trim());
			}
			System.err.println(str_route);
			System.err.println("min_station" + min_station);
			Map<String, Object> map_productName = new HashMap<String, Object>();
			map_productName.put("productName", str_product);
			int a_ = usersService.selectIDByName(map_productName);
			Map<String, Object> map = new HashMap<String, Object>();

			Map<String, Object> map_LINENAME_ = new HashMap<String, Object>();
			map_LINENAME_.put("str_line", str_line);
			int b_ = usersService.GetLineIDByName(map_LINENAME_);

			map.put("productionName", str_product);
			map.put("route", str_route.toString());
			map.put("lineName", str_line);
			map.put("lineID", b_);
			map.put("productionID", a_);
			Map<String, Object> map_select = new HashMap<String, Object>();
			map_select.put("productID", a_);
			map_select.put("productLine", b_);
			List<Routing> list_route_sql = usersService.GetRoute(map_select);
			if (list_route_sql.size() > 0) {
				try {
					Map<String, Object> map_delete = new HashMap<String, Object>();
					map_delete.put("productionName", str_product);
					usersService.DeleteRoute_Routing_ByproductName(map_delete);

					Map<String, Object> map_delete_way = new HashMap<String, Object>();
					map_delete_way.put("productionName", str_product);
					usersService.DeleteRoute_Production_ByproductName(map_delete_way);
				} catch (Exception e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			str_route = str_route.replace('{', ' ');
			str_route = str_route.replace('}', ' ');
			str_route = str_route.replace('[', ' ');
			str_route = str_route.replace(']', ' ');
			str_route = str_route.replaceAll("type", "");
			str_route = str_route.replaceAll("text", "");
			str_route = str_route.replaceAll("attr", "");
			str_route = str_route.replaceAll("props", "");
			str_route = str_route.replaceAll("dots", "");
			str_route = str_route.replaceAll("props", "");
			str_route = str_route.replaceAll("textPos", "");
			str_route = str_route.replaceAll("value", "");
			str_route = str_route.replace(':', ' ');

			str_route = str_route.trim();
			int a = str_route.indexOf("paths");
			str_route = str_route.substring(a + 5, str_route.length() - 1);
			str_route = str_route.replaceAll(" ", "");
			int[] arr = new int[80];
			String str = str_route;

			String s = "from";
			@SuppressWarnings("unused")
			int vvv = str.length();

			arr[0] = 0;
			for (int i = 1; i < arr.length; i++) {
				if (str.indexOf(s, arr[i - 1] + 4) == -1) {
					break;
				} else {
					arr[i] = str.indexOf(s, arr[i - 1] + 4);
				}
			}

			int count = -1;
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] != 0) {
					count++;
				}
			}
			count = count + 2;
			for (int i = 1; i < count; i++) {

				if (i == count - 1) {
					String[] str_group_route = GetIndexOfStation(str.substring(arr[i], str.length()));
					list_route.add(Integer.parseInt(str_group_route[0]));
					list_route.add(Integer.parseInt(str_group_route[1]));
				} else if (count > 1 || count == 1) {
					if (i < count - 1) {
						String[] str_group_route = GetIndexOfStation(str.substring(arr[i], str.length()));
						list_route.add(Integer.parseInt(str_group_route[0]));
						list_route.add(Integer.parseInt(str_group_route[1]));
					}
				} else {
					String[] str_group_route = GetIndexOfStation(str.substring(arr[i], str.length()));
					list_route.add(Integer.parseInt(str_group_route[0]));
					list_route.add(Integer.parseInt(str_group_route[1]));
				}
			}
			Date date = new Date();
			@SuppressWarnings("unused")
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
			Map<String, Object> map_station = new HashMap<String, Object>();
			Map<String, Object> map_lineID = new HashMap<String, Object>();

			map_lineID.put("lineName", str_line);
			int ID_line = usersService.findIDByName(map_lineID);
			map_station.put("lineId", ID_line);
			List<CMesStationT> stationList = usersService.stationList(map_station);

			if (list_route.size() > 0) {

				routeService.insertRoute(map);
				int min = min_station_;
				for (int i = 1; i < list_route.size(); i++) {
					if (min > list_route.get(i)) {
						min = list_route.get(i);
						System.err.println("--" + list_route.get(i) + "--");
					}
				}
				List<Integer> list_route_new = new ArrayList<Integer>();
				for (int j = 0; j < list_route.size(); j++) {
					if (j > 1) {
						if (list_route.get((j - 1)) == (list_route.get(j))) {
							continue;
						} else {
							list_route_new.add(list_route.get(j) - min);
						}
					} else {
						list_route_new.add(list_route.get(j) - min);
					}
				}
				// productId
				Map<String, Object> map_productionID = new HashMap<String, Object>();
				map_productionID.put("productId", a_);
				int result_id = usersService.getCountByID(map_productionID);

				List<String> list_station_name = new ArrayList();
				int[] int_station_ID = new int[60];
				int c = 0;

				if (result_id > 0) {
					usersService.deleteByID(map_productionID);
					for (int i = 0; i < list_route_new.size(); i++) {
						for (int j = 0; j < stationList.size(); j++) {
							if (list_route_new.get(i) == j) {
								Map<String, Object> map_production_way = new HashMap<String, Object>();
								map_production_way.put("DT", date);
								map_production_way.put("productionName", str_product);
								map_production_way.put("productionID", a_);
								map_production_way.put("stationName", stationList.get(j).getStationName());
								map_production_way.put("stationID", stationList.get(j).getId());
								map_production_way.put("serialNo", i + 1);
								map_production_way.put("lineId", ID_line);
								routeService.insertProductionWay(map_production_way);
							}
						}
					}
				} else {
					for (int i = 0; i < list_route_new.size(); i++) {
						System.err.println("外层：" + list_route_new.get(i));
						for (int j = 0; j < stationList.size(); j++) {
							if (list_route_new.get(i) == j) {
								/*
								 * Map<String, Object> map_production_way = new HashMap<String, Object>();
								 * map_production_way.put("DT", date); map_production_way.put("productionName",
								 * str_product); map_production_way.put("productionID", a_);
								 * map_production_way.put("stationName", stationList.get(j).getStationName());
								 * map_production_way.put("stationID", stationList.get(j).getId());
								 * map_production_way.put("serialNo", i + 1); map_production_way.put("lineId",
								 * ID_line); routeService.insertProductionWay(map_production_way);
								 */
								list_station_name.add(stationList.get(j).getStationName());
								int_station_ID[c] = stationList.get(j).getId();
								c++;
								System.err.println(
										"内层：" + stationList.get(j).getStationName() + "--" + list_route_new.get(i));
							}
						}
					}
				}
				String first_station_name = list_station_name.get(0);
				int[] int_begin_in = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				int[] int_begin_out = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				int int_end = 0;
				int x = 0;
				for (int m = 0; m < list_station_name.size() - 1; m++) {
					System.err.println("站名：" + list_station_name.get(m));
					for (int k = m + 1; k < list_station_name.size(); k++) {
						if (list_station_name.get(m).equals(list_station_name.get(k))) {
							if (int_begin_in[x] == 0) {
								int_begin_in[x] = m;
								int_begin_out[x] = k;
								x++;
							} else {
								int_end = k;
							}
						}
					}
				}
				System.err.println("int_begin_in:" + int_begin_in[0] + "int_begin_out:" + int_begin_out[0]);
				if (int_begin_out[0] == 0) {
					for (int n = 0; n < list_station_name.size(); n++) {
						Map<String, Object> map_production_way = new HashMap<String, Object>();
						map_production_way.put("DT", date);
						map_production_way.put("productionName", str_product);
						map_production_way.put("productionID", a_);
						map_production_way.put("stationName", list_station_name.get(n));
						map_production_way.put("stationID", int_station_ID[n]);
						map_production_way.put("serialNo", n + 1);
						map_production_way.put("lineId", ID_line);
						routeService.insertProductionWay(map_production_way);
					}
				} else {
					int mm = 0;
					for (int n = 0; n < list_station_name.size(); n++) {
						if (n == int_begin_out[0]) {
							Map<String, Object> map_production_way = new HashMap<String, Object>();
							map_production_way.put("DT", date);
							map_production_way.put("productionName", str_product);
							map_production_way.put("productionID", a_);
							map_production_way.put("stationName", list_station_name.get(int_begin_out[0] + 1));
							map_production_way.put("stationID", int_station_ID[int_begin_out[0] + 1]);
							map_production_way.put("serialNo", int_begin_in[0] + 2);
							map_production_way.put("lineId", ID_line);
							routeService.insertProductionWay(map_production_way);
							/*
							 * if(int_begin_in[0]==0) n=n+3; else
							 */
							n = n + 2;
							mm++;
						} else {
							Map<String, Object> map_production_way = new HashMap<String, Object>();
							map_production_way.put("DT", date);
							map_production_way.put("productionName", str_product);
							map_production_way.put("productionID", a_);
							map_production_way.put("stationName", list_station_name.get(n));
							map_production_way.put("stationID", int_station_ID[n]);
							if (n == list_station_name.size() - 1)
								map_production_way.put("serialNo", mm);
							else
								map_production_way.put("serialNo", mm + 1);
							map_production_way.put("lineId", ID_line);
							routeService.insertProductionWay(map_production_way);
							mm++;
						}
					}
				}
				result.put("flag", "true");
				return result;
			} else {
				result.put("flag", "false");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			result.put("flag", "false");
			return result;
		}
	}

	public String[] GetIndexOfStation(String str_route) {
		String ss = str_route;
		ss = ss.replaceAll("from", "");
		ss = ss.replaceAll("to", "");
		int c = ss.indexOf(",");
		int d = ss.indexOf(",", c + 1);
		ss = ss.substring(0, d);
		ss = ss.replaceAll("rect", "");
		ss = ss.replaceAll("'", "");
		System.err.println("++" + ss + "++");
		String[] str_group_route = ss.split(",");
		return str_group_route;
	}
}

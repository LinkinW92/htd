package com.skeqi.mes.controller.yin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.service.yin.ProductionService;
import com.skeqi.mes.service.yin.UsersService;


@Controller
@RequestMapping("skq")
public class ProductionController {
	@Autowired
	ProductionService productionService;
	@Autowired
	UsersService usersService;
	/**
	 * 班次列表
	 */
//	@RequestMapping("shiftManager")
//	public String shiftManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
//		HashMap<String, Object> map = new HashMap<>();
//		PageHelper.startPage(page,8);
//		List<CMesShiftsTeamT> shiftsTeamList = productionService.shiftsTeamList(map);
//		PageInfo<CMesShiftsTeamT> pageInfo = new PageInfo<>(shiftsTeamList,5);
//		List<CMesLineT> lineList = productionService.lineList();
//		request.setAttribute("lineList", lineList);
//		request.setAttribute("pageInfo", pageInfo);
//		return "production_control/shiftManager";
//	}
//	/**
//	 * 班组列表
//	 */
//	@RequestMapping("teamManager")
//	public String teamManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
//		HashMap<String, Object> map = new HashMap<>();
//		PageHelper.startPage(page,8);
//		List<CMesEmpTeamT> empTeamList = productionService.empTeamList(map);
//		PageInfo<CMesEmpTeamT> pageInfo = new PageInfo<>(empTeamList,5);
//		List<CMesShiftsTeamT> shiftsTeamList = productionService.shiftsTeamList(map);
//
//		request.setAttribute("shiftsTeamList", shiftsTeamList);
//		request.setAttribute("pageInfo", pageInfo);
//		return "production_control/teamManager";
//	}
//	/**
//	 * 添加班次
//	 */
//	@RequestMapping("addShift")
//	public @ResponseBody Map<String, Object> addShiftsTeam(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String name = request.getParameter("name").trim();
//		String startTime = request.getParameter("startTime");
//		String endTime = request.getParameter("endTime");
//		String dis = request.getParameter("dis");
//		String lineId = request.getParameter("lineId");
//		map.put("lineId", lineId.trim());
//		map.put("name", name.trim());
//		Integer count = productionService.countShiftByName(map);
//		if (count>0) {
//			map.put("msg", 1);
//			return map;
//		}
//		map.put("startTime", startTime);
//		map.put("endTime", endTime);
//		map.put("dis", dis);
//		try {
//			productionService.addShiftsTeam(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//	/**
//	 * 添加班组
//	 */
//	@RequestMapping("addEmpTeam")
//	public @ResponseBody Map<String, Object> addEmpTeam(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String name = request.getParameter("name").trim();
//		String shiftsTeamId = request.getParameter("shiftsTeamId");
//		String dis = request.getParameter("dis");
//		String emps = request.getParameter("emps");
//		map.put("name", name);
//		map.put("shiftsTeamId", shiftsTeamId);
//		map.put("dis", dis);
//		map.put("emps", emps);
//		Integer count = productionService.countEmpTeamByName(map);
//		if (count>0) {
//			map.put("msg", 1);
//			return map;
//		}
//		try {
//			productionService.addEmpTeam(map);
//			map.put("shiftsTeamId", shiftsTeamId);
//			map.put("empTeamId", productionService.getMaxIdfromSET());
//			if ("0".equals(shiftsTeamId)) {
//				map.put("shiftsTeamId", "");
//			}
//			productionService.addShiftEmpsTeam(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//	/**
//	 * 通过ID查询班次信息
//	 */
//	@RequestMapping("toEditShift")
//	public @ResponseBody Map<String, Object> toEditShift(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		map.put("id", id);
//		List<CMesShiftsTeamT> shiftsTeamList = productionService.shiftsTeamList(map);
//		String startTime=shiftsTeamList.get(0).getStartTime();
//		String endTime=shiftsTeamList.get(0).getEndTime();
//		map.put("shiftsTeam", shiftsTeamList.get(0));
//		map.put("startTime", startTime);
//		map.put("endTime", endTime);
//		return map;
//	}
//	/**
//	 * 通过ID查询班组信息
//	 */
//	@RequestMapping("toEditEmpTeam")
//	public @ResponseBody Map<String, Object> toEditEmpTeam(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		map.put("id", id);
//		List<CMesEmpTeamT> empTeamList = productionService.empTeamList(map);
//		map.put("empTeam", empTeamList.get(0));
//		return map;
//	}
//	/**
//	 * 修改班次
//	 */
//	@RequestMapping("editShift")
//	public @ResponseBody Map<String, Object> editShift(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		String name = request.getParameter("name").trim();
//		String startTime = request.getParameter("startTime");
//		String endTime = request.getParameter("endTime");
//		String dis = request.getParameter("dis");
//		String lineId = request.getParameter("lineId");
//		map.put("id", id);
//		List<CMesShiftsTeamT> shiftsTeamList = productionService.shiftsTeamList(map);
//		map.put("lineId", lineId);
//		map.put("name", name);
//		map.put("startTime", startTime);
//		map.put("endTime", endTime);
//		map.put("dis", dis);
//		if (!shiftsTeamList.get(0).getName().equals(name)) {
//			int countShiftByName = productionService.countShiftByName(map);
//			if (countShiftByName>0) {
//				map.put("msg", 1);
//				return map;
//			}
//		}
//		try {
//			productionService.editShiftsTeam(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//		}
//		return map;
//	}
//	/**
//	 * 修改班组
//	 */
//	@RequestMapping("editEmpTeam")
//	public @ResponseBody Map<String, Object> editEmpTeam(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		String name = request.getParameter("name").trim();
//		String shiftsTeamId = request.getParameter("shiftsTeamId");
//		String dis = request.getParameter("dis");
//		String emps = request.getParameter("emps");
//		map.put("id", id);
//		List<CMesEmpTeamT> empTeamList = productionService.empTeamList(map);
//		map.put("name", name);
//		map.put("shiftsTeamId", shiftsTeamId);
//		map.put("dis", dis);
//		map.put("emps", emps);
//		map.put("empTeamId", id);
//		if (!empTeamList.get(0).getName().equals(name)) {
//			int countEmpTeamByName = productionService.countEmpTeamByName(map);
//			if (countEmpTeamByName>0) {
//				map.put("msg", 1);
//				return map;
//			}
//		}
//		if ("0".equals(shiftsTeamId)) {
//			map.put("shiftsTeamId", "");
//		}
//		productionService.editShiftsTeamByEmpTeamId(map);
//		try {
//			productionService.editEmpTeam(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//	/**
//	 * 删除班次
//	 */
//	@RequestMapping("delShift")
//	public @ResponseBody Map<String, Object> delShift(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		map.put("id", id);
//		try {
//			productionService.delShift(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//		}
//		return map;
//	}
//	/**
//	 * 删除班组
//	 */
//	@RequestMapping("delEmpTeam")
//	public @ResponseBody Map<String, Object> delEmpTeam(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		map.put("id", id);
//		try {
//			productionService.delEmpTeam(map);
//			productionService.delShiftEmpTeam(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//		}
//		return map;
//	}
//	/**
//	 * 员工列表
//	 */
//	@RequestMapping("employeeManager")
//	public String employeeManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
//		HashMap<String, Object> map = new HashMap<>();
//		Map<String, Object> map1 = null;
//		PageHelper.startPage(page,8);
//		List<CMesEmpT> empList = productionService.empList(map);
//		System.out.println(empList.size());
//
//		PageInfo<CMesEmpT> pageInfo = new PageInfo<>(empList,5);
//		List<CMesEmpTeamT> empTeamList = productionService.empTeamList(map);
//		List<CMesLineT> lineList = usersService.lineList(map);
//		List<CMesEmpTypeT> empTypeList = productionService.empTypeList(map);
//		if(lineList.size()>0){
//			map.put("lineId", lineList.get(0).getId());
//		}
//		List<CMesStationT> stationList = usersService.stationList(map);
//		List<RoleT> roleList = usersService.roleList(map1);
//		request.setAttribute("pageInfo", pageInfo);
//		request.setAttribute("stationList", stationList);
//		request.setAttribute("empTeamList", empTeamList);
//		request.setAttribute("lineList", lineList);
//		request.setAttribute("roleList", roleList);
//		request.setAttribute("empTypeList", empTypeList);
//		return "production_control/employeeManager";
//		//此注释掉程序不会报错，开始出
////		for (int i = 0; i < empList.size(); i++) {
////
////			String stationIdss = empList.get(i).getStationId();
////			if (stationIdss!=null) {
////				String stations[] = stationIdss.split(",");
////				String stationNames = "";
////				for (int j = 0; j < stations.length; j++) {
////					map1 = new HashMap<>();
////					map1.put("stationId", Integer.parseInt(stations[j]));
////					List<CMesStationT> stationLists = usersService.stationList(map1);
////					stationNames +=stationLists.get(0).getStationName()+",";
////					empList.get(i).setStationName(stationNames);
////				}
////			}
////
////		}
////
////		List<List<CMesStationT>> stationListList = new ArrayList<>();
////		String stationIds = empList.get(0).getStationId();
////		if (stationIds!=null) {
////
////			String station[] = stationIds.split(",");
////			for (String string : station) {
////				map1 = new HashMap<>();
////				map1.put("stationId", Integer.parseInt(string));
////				List<CMesStationT> stationLists = usersService.stationList(map1);
////				stationListList.add(stationLists);
////			}
////		}
//
//		//以此为结束
//
//
//		//		for (int i = 0; i < stationListList.size(); i++) {
//		//			for (int j = 0; j < stationListList.get(i).size(); j++) {
//		//				stationNames +=stationListList.get(i).get(j).getStationName()+",";
//		//			}
//		//		}
//
//
//	}
//
//	@RequestMapping("stationListsss")
//	public @ResponseBody List<CMesStationT> findlineById(Integer lineId) {
//		return productionService.stationList(lineId);
//	}
//
//	/**
//	 * 添加员工
//	 * @return
//	 */
//	@RequestMapping("addEmp")
//	public @ResponseBody Map<String, Object> addEmp(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String empNo = request.getParameter("empNo").trim();
//		String empName = request.getParameter("empName").trim();
//		String empSex = request.getParameter("empSex");
//		String empType = request.getParameter("empType");
//		String empTp = request.getParameter("empTp");
//		String dt = request.getParameter("dt");
//		String empDepartment = request.getParameter("empDepartment");
//		String empTeamId = request.getParameter("empTeamId");
//		String lineId = request.getParameter("lineId");
//		String empVr = request.getParameter("empVr");
//		String empMail = request.getParameter("empMail");
//		String stationId = request.getParameter("stationId");
//		String roleId = request.getParameter("roleId");
//		map.put("roleId", roleId);
//		map.put("empMail", empMail);
//		map.put("stationId", stationId);
//		map.put("empNo", empNo);
//		map.put("empName", empName);
//		map.put("empSex", Integer.parseInt(empSex));
//		map.put("empType", Integer.parseInt(empType));
//		map.put("empTp", empTp);
//		map.put("dt", dt);
//		map.put("empDepartment", empDepartment);
//		map.put("empTeamId", Integer.parseInt(empTeamId));
//		map.put("lineId", Integer.parseInt(lineId));
//		map.put("empVr", empVr);
//		Integer count = productionService.countEmpByEmpNo(map);
//		if (count>0) {
//			map.put("msg", 1);
//			return map;
//		}
//		try {
//			productionService.addEmp(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//	//回显
//	@RequestMapping("toUpdateEmp")
//	public @ResponseBody Map<String, Object> toUpdateEmp(HttpServletRequest request){
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String id = request.getParameter("id");
//		CMesEmpT c = productionService.findById(Integer.parseInt(id));
//		HashMap<String, Object> map = new HashMap<>();
//		map.put("emp", c);
//		return map;
//	}
//
//	/**
//	 * 修改员工信息
//	 */
//	@RequestMapping("editEmp")
//	public @ResponseBody Map<String, Object> editEmp(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String empNo = request.getParameter("empNo1").trim();
//		String empName = request.getParameter("empName1").trim();
//		String empSex = request.getParameter("empSex1");
//		String empType = request.getParameter("empType1");
//		String empTp = request.getParameter("empTp1");
//		String dt = request.getParameter("dt1");
//		String empDepartment = request.getParameter("empDepartment1");
//		String empTeamId = request.getParameter("empTeamId1");
//		String lineId = request.getParameter("lineId1");
//		String empVr = request.getParameter("empVr1");
//		String id = request.getParameter("id");
//		String empMail = request.getParameter("empMail1");
//		String stationId = request.getParameter("stationId1");
//		String roleId = request.getParameter("roleId1");
//		map.put("roleId", roleId);
//		map.put("empMail", empMail);
//		map.put("stationId", stationId);
//		map.put("id", id);
//		map.put("empNo", empNo);
//		map.put("empName", empName);
//		map.put("empSex", Integer.parseInt(empSex));
//		map.put("empType", Integer.parseInt(empType));
//		map.put("empTp", empTp);
//		map.put("dt", dt);
//		map.put("empDepartment", empDepartment);
//		map.put("empTeamId", Integer.parseInt(empTeamId));
//		map.put("lineId", Integer.parseInt(lineId));
//		map.put("empVr", empVr);
//		try {
//			productionService.editEmp(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//
//	@RequestMapping("delEmp")
//	public @ResponseBody Map<String, Object> delEmp(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		map.put("id", id);
//		try {
//			productionService.delEmp(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//		}
//		return map;
//	}

//	/**
//	 * 员工类型管理
//	 * @param request
//	 * @param page
//	 * @return
//	 */
//	@RequestMapping("empTypeList")
//	public String empTypeList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
//		Map<String, Object> map = new HashMap<>();
//		PageHelper.startPage(page,8);
//		List<CMesEmpTypeT> empTypeList = productionService.empTypeList(map);
//		PageInfo<CMesEmpTypeT> pageInfo = new PageInfo<>(empTypeList,5);
//		request.setAttribute("pageInfo", pageInfo);
//		return "production_control/employeeTypeManager";
//	}
//	/**
//	 * 添加员工类型
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("addEmpType")
//	public @ResponseBody Map<String, Object> addEmpType(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String empType = request.getParameter("empType").trim();
//		String dis = request.getParameter("dis").trim();
//		map.put("empType",empType);
//		map.put("dis", dis);
//		try {
//			productionService.addEmpType(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//
//	@RequestMapping("toUpdateEmpType")
//	public @ResponseBody Map<String, Object> toUpdateEmpType(HttpServletRequest request){
//		String id = request.getParameter("id");
//		Map<String, Object> map = new HashMap<>();
//		map.put("id", id);
//		List<CMesEmpTypeT> empTypeList = productionService.empTypeList(map);
//		map.put("empTypes", empTypeList.get(0));
//		return map;
//	}
//
//	@RequestMapping("editEmpType")
//	public @ResponseBody Map<String, Object> editEmpType(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<>();
//		String empType = request.getParameter("empType").trim();
//		String dis = request.getParameter("dis").trim();
//		String id = request.getParameter("id").trim();
//		map.put("id",id);
//		map.put("empType",empType);
//		map.put("dis", dis);
//		try {
//			productionService.editEmpType(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//
//	@RequestMapping("delEmpType")
//	public @ResponseBody Map<String, Object> delEmpType(HttpServletRequest request) {
//		HashMap<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		map.put("id", id);
//		try {
//			productionService.delEmpType(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//		}
//		return map;
//	}
}

package com.skeqi.mes.controller.qh;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesSystem;
import com.skeqi.mes.service.all.CMesSystemService;
import com.skeqi.mes.service.lcy.GetSomeYieldService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.Version;
import com.skeqi.mes.util.gmg.JDBCUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 节拍统计
 *
 * @ClassName: CMesBeatInfoController
 */
//@Controller
//@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
//@Api(value = "节拍统计", description = "节拍统计", produces = MediaType.APPLICATION_JSON)
public class CMesBeatInfoController {
	@Autowired
	private GetSomeYieldService gsys;
	@Autowired
	private CMesSystemService cSystemService;

	/**
	 * 整体合格率统计
	 *
	 * @throws ParseException
	 */

	@RequestMapping(value = "/beatInfo/findAll", method = RequestMethod.POST)
	@ApiOperation(value = "得到节拍统计", notes = "根据多条件得到节拍统计")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "time", value = "时间", required = false, paramType = "query", dataType = "String") })
	@ResponseBody
	public JSONObject findAll(Integer lineId, String time) throws ServicesException, ParseException {

		String str_time = time;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str_beginTime = "";
		String str_endTime = "";
		str_beginTime = new SimpleDateFormat("yyyy/MM/dd").format(sdf.parse(str_time)) + " 00:00:00";
		str_endTime = new SimpleDateFormat("yyyy/MM/dd").format(sdf.parse(str_time)) + " 23:59:59";
		String str_line = lineId.toString();

		Map<String, Object> map = new HashMap<>();
		map.put("str_line", str_line);

		int int_line = Integer.parseInt(str_line);

		String sql = "{call TRIPOD.p_mes_station_pass_t(?,?,?,?,?,?,?,?)}";
		JSONObject jo = new JSONObject();

		System.out.println("sql====="+sql);

		// 获取每个工位所用的时间
		String[] sk = testProcedure(int_line, str_beginTime, str_endTime, sql);

		Map<String, Object> map2 = new HashMap<String, Object>();
		System.err.println("sk.length:" + sk.length);

		if (sk[0] != null) {
			String[] group_alltime = sk[0].trim().split(",");
			String[] group_averagetime = sk[1].trim().split(",");
			String[] group_oknum = sk[2].trim().split(",");
			String[] group_line = sk[3].trim().split(",");
			String[] group_station = sk[4].trim().split(",");

			if (group_alltime.length > 1) {
				for (int i = 1; i < group_alltime.length; i++) {
					map2.put("line", group_line[i].trim());
					map2.put("station", group_station[i].trim());
					map2.put("alltime", group_alltime[i]);
					map2.put("oknum", group_oknum[i].trim());
					map2.put("averagetime", group_averagetime[i]);

				}
			}
			jo.put("msg", map2);
			System.out.println("map2==========" + map2);
		}

		return jo;
	}

	public String[] testProcedure(Integer str_lineID, String str_begintime, String str_endtime, String sql) {

		System.out.println("sql=====sdfdsdf============"+sql);
		String[] ss = new String[5];

		CallableStatement call = null;
		Connection conn = null;
		try {
			// 得到一个数据库连接
			conn = JDBCUtils.getConnection();
			// 通过连接创建出statement
			call = conn.prepareCall(sql);
			// 对于in参数，赋值
			call.setString(1, str_lineID + ""); // (第几个问号,要赋的值)
			call.setString(2, str_begintime);
			call.setString(3, str_endtime);
			System.err.println("str_begintime:" + str_begintime + "str_endtime:" + str_endtime);
			// 对out参数，声明
			call.registerOutParameter(4, java.sql.Types.VARCHAR);// (第几个问号，声明的类型)
			call.registerOutParameter(5, java.sql.Types.VARCHAR);
			call.registerOutParameter(6, java.sql.Types.VARCHAR);
			call.registerOutParameter(7, java.sql.Types.VARCHAR);
			call.registerOutParameter(8, java.sql.Types.VARCHAR);
			// 执行调用
			call.execute();
			// 取出结果
			System.err.println(call.getString(4));
			String str_addtime = call.getString(4);
			ss[0] = str_addtime;
			String str_averagetime = call.getString(5);
			ss[1] = str_averagetime;
			String str_qualified = call.getString(6);
			ss[2] = str_qualified;
			String str_empgroup = call.getString(7);
			ss[3] = str_empgroup;
			String str_station = call.getString(8);
			ss[4] = str_station;
			return ss;
		} catch (Exception e) {
			e.printStackTrace();
			return ss;
		} finally {
			// 关闭链接，释放资源
			JDBCUtils.release(conn, call, null);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/beatInfo/findLine", method = RequestMethod.POST)
	@ApiOperation("初始化产线")
	public JSONObject getLine(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		List<CMesLineT> list = gsys.getLine();
		jo.put("lineList", list);
		return jo;

	}

	@ResponseBody
	@RequestMapping(value = "/beatInfo/sessionConversation", method = RequestMethod.POST)
	@ApiOperation("版本时间控制")
	public JSONObject getSessionConversation() {
		//TODO
//		Subject subject = SecurityUtils.getSubject();
//		JSONObject jo = new JSONObject();
//		if (!subject.isAuthenticated()) {
//			jo.put("value", true);
//		} else {
//			jo.put("menuData", subject.getSession().getAttribute("menuData"));
//			jo.put("menuModuleData", subject.getSession().getAttribute("menuModuleData"));
//			jo.put("value", false);
//		}
//		Version version = new Version();
//		jo.put("version", version.getVersion());
		return null;
	}

	// 系统数据
	@RequestMapping(value = "/beatInfo/findByAll", method = RequestMethod.POST)
	@ApiOperation(value = "系统数据", notes = "系统数据")
	@ResponseBody
	public JSONObject findByAll(HttpServletRequest request) throws ServicesException {
		List<CMesSystem> cSystems = cSystemService.findByAll(null);
		JSONObject json = new JSONObject();
		try {
			json.put("code", 0);
			json.put("cSystems", cSystems);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}
}

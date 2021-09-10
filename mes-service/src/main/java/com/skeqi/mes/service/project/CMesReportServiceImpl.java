package com.skeqi.mes.service.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.mapper.project.CMesReportDAO;
import com.skeqi.mes.mapper.project.CMesSchedulingDAO;
import com.skeqi.mes.pojo.project.CMesAndonFault;


@Service
public class CMesReportServiceImpl implements CMesReportService{

	@Autowired
	CMesReportDAO dao;

	@Autowired
	CMesSchedulingDAO schedao;

	@Override
	public Map<Integer,Integer> Reportyield(String id, String lineName) throws Exception {
		// TODO Auto-generated method stub
		if(id==null || id==""){
			throw new ParameterNullException("时间不能为空",200);
		}else if(lineName==null || lineName==""){
			throw new ParameterNullException("产线不能为空",200);
		}
		String substring = id.substring(5,7);
		String year = id.substring(0,4);
		List<Map<String,Object>> reportyield = dao.Reportyield(substring,lineName,year);
		Map<Integer,Integer> maps = new HashMap<Integer,Integer>();
		for (Map<String, Object> map : reportyield) {
			maps.put(Integer.parseInt(map.get("day").toString()),Integer.parseInt(map.get("num").toString()));
		}
		for (int i = 1; i <= 31; i++) {
			if(maps.get(i)==null){
				maps.put(i, 0);
			}
		}
		return maps;
	}

	@Override
	public Map<Integer,Integer>ReportyieldTwo(String id, String lineName) throws Exception {
		// TODO Auto-generated method stub
		if(id==null || id==""){
			throw new ParameterNullException("时间不能为空",200);
		}else if(lineName==null || lineName==""){
			throw new ParameterNullException("产线不能为空",200);
		}

		List<Map<String, Object>> reportyieldTwo = dao.ReportyieldTwo(id, lineName);
		Map<Integer,Integer> maps = new HashMap<Integer,Integer>();
		for (Map<String, Object> map : reportyieldTwo) {
			maps.put((int)(Float.parseFloat(map.get("hour").toString())),Integer.parseInt(map.get("num").toString()));
		}
		for (int i = 1; i <= 24; i++) {
			if(maps.get(i)==null){
				maps.put(i, 0);
			}
		}
		return maps;
	}

	@Override
	public Integer findYield(String name) throws Exception {
		if(name==null || name==""){
			throw new ParameterNullException("id不能为空",200);
		}
		// TODO Auto-generated method stub
		return dao.findYield(name);
	}

	@Override
	public JSONArray findOEE(Integer lineId, String startTime, String endTime) throws Exception {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
		List<String> days = getDays(startTime, endTime);
		 SimpleDateFormat sim = new SimpleDateFormat("HH:mm");
		 SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (String string : days) {

			Set<Integer> sets = new HashSet<Integer>();
			JSONObject json = new JSONObject();
			Integer findSumTime = dao.findSumTime(string,lineId);  //计划时间（分钟）
			if(findSumTime!=null && findSumTime!=0){
				String findLineName = dao.findLineName(lineId);   //产线名称
				List<Integer> findId = dao.findId(findLineName, string);
//				long times = 0;   //故障总时长
				for (Integer integer : findId) {
					CMesAndonFault findFaultByid = dao.findFaultByid(integer);
//					times = times+getMinutes(findFaultByid.getEstablishDt(), findFaultByid.getSolveDt());
					List<String> minuteBetweenTwoDate = getMinuteBetweenTwoDate(findFaultByid.getEstablishDt(), findFaultByid.getSolveDt());
					for (String string2 : minuteBetweenTwoDate) {
						String format = sim.format(sim1.parse(string2));
						String[] split = format.split(":");
						int s = Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);
						sets.add(s);
					}
				}
//				float timess = times;
				if(string.equals("2020-05-16")){
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
				}
				float timess = sets.size();
				float findNumber = dao.findNumber(string, lineId);   //计划生产数量
				float findCount = Integer.parseInt(schedao.findCount(string));   //实际生产数量
				float  ti= (float ) ((findSumTime-timess)/findSumTime);  //时间开动率
				float  nums = (float ) (findCount/findNumber);   //性能开动率
				float  oee = ti*nums*100;
				json.put("dt", string);
				json.put("OEE", oee);
				json.put("TimeRate", ti);
				json.put("performance", nums);
				json.put("QualifiedRate", 100);
				array.add(json);
			}else{
				json.put("dt", string);
				json.put("OEE", 0);
				json.put("TimeRate", 0);
				json.put("performance", 0);
				json.put("QualifiedRate", 100);
				array.add(json);
			}
		}
		return array;
	}

	/**
	 * 获取两个日期的所有天数
	* @author FQZ
	* @date 2020年5月6日下午4:06:15
	 */
	 public static List<String> getDays(String startTime, String endTime) throws Exception {

	        // 返回的日期集合
	        List<String> days = new ArrayList<String>();

	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        if (!startTime.equals("") || !endTime.equals("")) {
	            Date start = dateFormat.parse(startTime);
				Date end = dateFormat.parse(endTime);

				Calendar tempStart = Calendar.getInstance();
				tempStart.setTime(start);

				Calendar tempEnd = Calendar.getInstance();
				tempEnd.setTime(end);
				tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
				while (tempStart.before(tempEnd)) {
				    days.add(dateFormat.format(tempStart.getTime()));
				    tempStart.add(Calendar.DAY_OF_YEAR, 1);
				}

			}

	        return days;
	  }

	 /**
	  * 获取两个时间内的分钟总数
	 * @author FQZ
	 * @date 2020年5月6日下午4:06:35
	  */
	 public static Long getMinutes(String startTime,String endTime) throws Exception{
		 SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 Date parse = sim.parse(startTime);
		 Date parse1 = sim.parse(endTime);
		 long s = parse1.getTime()-parse.getTime();
		 long minutes = s/60000;
		 return minutes;
	 }

	 /**
	  * 获取两个时间段内的所有时间  以yyyy-MM-dd HH:mm的格式
	 * @author FQZ
	 * @date 2020年5月6日下午4:08:18
	  */
	 public static List<String> getMinuteBetweenTwoDate(String beginDate,
				String endDate) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			List<String> lDate = new ArrayList<String>();
			lDate.add(beginDate );// 把开始时间加入集合
			Calendar cal = Calendar.getInstance();
			// 使用给定的 Date 设置此 Calendar 的时间
			cal.setTime(sdf.parse(beginDate));
			boolean bContinue = true;
			while (bContinue) {
				// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
				cal.add(Calendar.MINUTE, 1);
				// 测试此日期是否在指定日期之后
				if (sdf.parse(endDate).after(cal.getTime())) {
					lDate.add(sdf.format(cal.getTime()));
				} else {
					break;
				}
			}
			lDate.add(endDate);// 把结束时间加入集合l
			return lDate;
	 }

	 public static void main(String[] args) throws Exception{
		 List<String> minuteBetweenTwoDate = getMinuteBetweenTwoDate("2020-04-20 04:20","2020-04-21 04:30");
//		 SimpleDateFormat sim = new SimpleDateFormat("HH:mm");
//		 SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		 for (String string : minuteBetweenTwoDate) {
//			String format = sim.format(sim1.parse(string));
//			String[] split = format.split(":");
//			int s = Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);
//		}
		 for (String string : minuteBetweenTwoDate) {
			System.out.println(string);
		}
	}

	@Override
	public List<Map<String, Object>> ProductionNumberByLine(String startTime, String endTime) throws Exception {
		// TODO Auto-generated method stub

		return dao.ProductionNUmberByLine(startTime, endTime);
	}

	@Override
	public List<Map<String,Object>> ProNumByLineName(String lineName) {
		// TODO Auto-generated method stub
		return dao.ProNumByLineName(lineName);
	}
}

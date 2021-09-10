package com.skeqi.mes.service.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.alarm.CAlarmProblemsDao;
import com.skeqi.mes.mapper.project.CMesAndonDAO;
import com.skeqi.mes.mapper.project.CMesAndonPlanDAO;
import com.skeqi.mes.mapper.project.CMesProLineDAO;
import com.skeqi.mes.mapper.project.CMesSchedulingDAO;
import com.skeqi.mes.mapper.project.CMesShiftTeamDAO;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.alarm.CAlarmNoticeLogs;
import com.skeqi.mes.pojo.project.CMesAndonFault;
import com.skeqi.mes.pojo.project.CMesLossReasonT;
import com.skeqi.mes.pojo.project.CMesLossTypeT;
import com.skeqi.mes.pojo.project.CMesWorkorderT;
import com.skeqi.mes.pojo.project.InsertInfo;
import com.skeqi.mes.util.yp.EamilUtil;

@Service("cMesAndonService")
public class CMesAndonServiceImpl implements CMesAndonService {

	@Autowired
	CMesAndonDAO dao;

	@Autowired
	CAlarmProblemsDao alarmDao;

	@Autowired
	CMesProductionService proDAO;

	@Autowired
	CMesAndonPlanDAO planDAO;

	@Autowired
	CMesProLineDAO lineDAO;

	@Autowired
	CMesSchedulingDAO schedao;

	@Autowired
	CMesShiftTeamDAO shiftDao;

	@Override
	public Integer addFault(String lineName, String stationName, Integer faultType) throws Exception {
		// TODO Auto-generated method stub
		Integer findFault = dao.findFault(lineName, stationName);
		if (findFault > 0) {
			throw new ParameterNullException("此故障已存在", 200);
		}

//		if(lineName==null || lineName==""){
//			throw new ParameterNullException("产线不能为空",200);
//		}else if(stationName==null || stationName==""){
//			throw new ParameterNullException("工位不能为空",200);
//		}else if(!faultType.equals("1") && !faultType.equals("2") && !faultType.equals("3") && !faultType.equals("4")){
//			throw new ParameterNullException("损失类型不存在",200);
//		}
//
//		Integer findLineName = mapper.findLineName(lineName);
//		if(findLineName==0){
//			throw new ParameterNullException("不存在此产线",200);
//		}
//		Integer findStationName = mapper.findStationNameByName(stationName);
//		if(findStationName==0){
//			throw new ParameterNullException("不存在此工位",200);
//		}

		dao.addFault(lineName, stationName, faultType.toString());

		// 通过产线工位查询故障
		CMesAndonFault andonFault = alarmDao.findAndonFault(lineName, stationName);

		// 查询损失类型
		CMesLossTypeT lossType = alarmDao.findLossTypeById(faultType);

		// 需要发送的邮件
		List<JSONObject> emailJsons = alarmDao.findNnotificationMethod(faultType, 1, 1);
		for (JSONObject json : emailJsons) {
			// 需要发送的用户
			String[] notificationChannelsContents = json.getString("notificationChannelsContent").split(",");

			// 设置邮件参数
			EamilUtil.SENDER_EAMIL = json.getString("senderEmail");
			EamilUtil.THE_SERVER = json.getString("theServer");
			EamilUtil.AUTHORIZATION_CODE = json.getString("authorizationCode");

			for (String string : notificationChannelsContents) {
				EamilUtil.sendEamil(string, "发生："+lossType.getLossName(),
						lineName + stationName + "发生" + lossType.getLossName());
			}

			CAlarmNoticeLogs logs = new CAlarmNoticeLogs();
			logs.setFaultId(andonFault.getId());
			logs.setLossType(json.getString("lossType"));
			logs.setSendOut(json.getString("senderEmail"));
			logs.setReceive(json.getString("notificationChannelsContent"));
			logs.setChannels(json.getString("notificationChannels"));
			logs.setChannelsType(json.getString("notificationChannelsTypeName"));

			alarmDao.addNoticeLogs(logs);

		}

		return 1;
	}

	@Override
	public Integer responseFault(String lineName, String stationName) throws Exception {
		// TODO Auto-generated method stub
		Integer findFault = dao.findResFault(lineName, stationName);
		if (findFault > 0) {
			throw new ParameterNullException("此故障已存在", 200);
		}
		if (lineName == null || lineName == "") {
			throw new ParameterNullException("产线不能为空", 200);
		} else if (stationName == null || stationName == "") {
			throw new ParameterNullException("工位不能为空", 200);
		}

		dao.responseFault(lineName, stationName);

		CMesAndonFault andonFault = alarmDao.findAndonFault(lineName, stationName);

		// 需要发送的邮件
		List<JSONObject> emailJsons = alarmDao.findNnotificationMethod(Integer.parseInt(andonFault.getLossType()), 2,
				1);
		for (JSONObject json : emailJsons) {
			// 需要发送的用户
			String[] notificationChannelsContents = json.getString("notificationChannelsContent").split(",");

			// 设置邮件参数
			EamilUtil.SENDER_EAMIL = json.getString("senderEmail");
			EamilUtil.THE_SERVER = json.getString("theServer");
			EamilUtil.AUTHORIZATION_CODE = json.getString("authorizationCode");

			for (String string : notificationChannelsContents) {
				EamilUtil.sendEamil(string, "响应："+andonFault.getLossName(),
						lineName + stationName + "发生的" + andonFault.getLossName() + "已响应");
			}

			CAlarmNoticeLogs logs = new CAlarmNoticeLogs();
			logs.setFaultId(andonFault.getId());
			logs.setLossType(json.getString("lossType"));
			logs.setSendOut(json.getString("senderEmail"));
			logs.setReceive(json.getString("notificationChannelsContent"));
			logs.setChannels(json.getString("notificationChannels"));
			logs.setChannelsType(json.getString("notificationChannelsTypeName"));

			alarmDao.addNoticeLogs(logs);

		}

		return 1;
	}

	@Override
	public Integer SolveFault(String lineName, String stationName) throws Exception {
		// TODO Auto-generated method stub
		if (lineName == null || lineName == "") {
			throw new ParameterNullException("产线不能为空", 200);
		} else if (stationName == null || stationName == "") {
			throw new ParameterNullException("工位不能为空", 200);
		}
		dao.SolveFault(lineName, stationName);

		CMesAndonFault andonFault = alarmDao.findAndonFault(lineName, stationName);

		// 需要发送的邮件
		List<JSONObject> emailJsons = alarmDao.findNnotificationMethod(Integer.parseInt(andonFault.getLossType()), andonFault.getFaultLevelId(),
				3);
		for (JSONObject json : emailJsons) {
			// 需要发送的用户
			String[] notificationChannelsContents = json.getString("notificationChannelsContent").split(",");

			// 设置邮件参数
			EamilUtil.SENDER_EAMIL = json.getString("senderEmail");
			EamilUtil.THE_SERVER = json.getString("theServer");
			EamilUtil.AUTHORIZATION_CODE = json.getString("authorizationCode");

			for (String string : notificationChannelsContents) {
				EamilUtil.sendEamil(string, "解决："+andonFault.getLossName(),
						lineName + stationName + "发生的" + andonFault.getLossName() + "已解决");
			}

			CAlarmNoticeLogs logs = new CAlarmNoticeLogs();
			logs.setFaultId(andonFault.getId());
			logs.setLossType(json.getString("lossType"));
			logs.setSendOut(json.getString("senderEmail"));
			logs.setReceive(json.getString("notificationChannelsContent"));
			logs.setChannels(json.getString("notificationChannels"));
			logs.setChannelsType(json.getString("notificationChannelsTypeName"));

			alarmDao.addNoticeLogs(logs);

		}

		return 1;
	}

	@Override
	public List<CMesAndonFault> findAllAndon(String lineName, String stationName, Integer status, String lossType,
			String startDate, String endDate) throws ServicesException {
		// TODO Auto-generated method stub

		return dao.findAllAndon(lineName, stationName, status, lossType, startDate, endDate);
	}

	@Override
	public Integer updateFault(CMesAndonFault fault) throws ServicesException {
		// TODO Auto-generated method stub
//		Integer findFaultType = mapper.findLossType(fault.getLossName());
//		if(findFaultType==null){
//			throw new ParameterNullException("不存在此【"+fault.getLossName()+"】损失类型",200);
//		}
//		fault.setLossType(findFaultType.toString());
		return dao.updateFault(fault);
	}

	private String twoDaysBeforeAcquisition() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		date = calendar.getTime();
		return sdf.format(date);
	}

	@Override
	@Transactional
	public Integer insertInfo(InsertInfo info) throws Exception {
		// TODO Auto-generated method stub
		if (info.getLineName() == null || info.getLineName() == "") {
			throw new ParameterNullException("产线不能为空", 200);
		} else if (info.getStationName() == null || info.getStationName() == "") {
			throw new ParameterNullException("工位不能为空", 200);
		}
		Integer lineId = dao.findLineId(info.getLineName());
		Integer findRegion = dao.findRegion(lineId);
		if (lineId == null) {
			throw new ParameterNullException("产线不存在", 200);
		}
		Set<Map<String, Object>> findAllSchebuling = schedao.findAllSchebuling(lineId, twoDaysBeforeAcquisition());
		String findProVr = null;

//		if(codeDigit!=info.getSn().length()) {
//			throw new Exception("扫描到的条码位数与系统设置的不一致");
//		}

		int i = 0;
		outterLoop: for (Map<String, Object> map : findAllSchebuling) {
			Integer findMaxId = finScheId(lineId, 1); // 查询是否存在排班
			if (findMaxId != null && findMaxId != 0) { // 排班存在
//            	String materialNo = null;
				boolean snNull = false;
				if (info.getSn() != null && !info.getSn().equals("")) {
					String[] s = info.getSn().split("\\|");

					String materialNo = info.getSn().split("\\|")[0]; // 条码中的编码
					findProVr = schedao.findProVr(materialNo); // 根据编码查询标记（规则）
					Integer codeDigit = dao.findProductCodeDigit(materialNo);
					if (s.length != 3) {
						throw new Exception("扫描到无效的条码");
					}
//            		materialNo = info.getSn().split("\\|")[0];   //条码中的编码
//            		findProVr = schedao.findProVr(materialNo);  //根据编码查询标记（规则）
//            		Integer codeDigit =  mapper.findProductCodeDigit(materialNo);
//            		if(codeDigit==null || codeDigit==0 || codeDigit.equals("")) {
//            			throw new Exception("扫描到无效的条码");
//            		}
//            		if(codeDigit!=info.getSn().length()) {
//            			throw new Exception("扫描到的条码位数与系统设置的不一致");
//            		}
					info.setProductMark(findProVr);
				} else {
					snNull = true;
					info.setProductMark(info.getProductMark());
				}
				List<CMesWorkorderT> findAllWork = schedao.findAllWork(findMaxId, null); // 查询此排班下的所有工单
				for (CMesWorkorderT work : findAllWork) {
					if (work.getProductMark().equals(findProVr)) { // 查询此排班下的工单中的标记是否与传来的标记一致
						i = 1;
					} else if (snNull) { // sn为空，传感器计数
						if (work.getProductMark().equals(info.getProductMark())) {// 判断产品标记
							i = 1;
						}
					}
					info.setPattern(0);
					if (i == 1) {
						info.setPattern(1);
						info.setWorkId(work.getId());
						schedao.addPlaNumber(work.getPlanId(), 1); // 修改计划数量
						schedao.addWorkNumber(work.getId(), 1); // 修改工单数量
						schedao.addScheNumber(findMaxId, 1); // 修改排班数量
						break outterLoop;
					}
				}
				break;
			}
		}

//		if(i==0){
//    		throw new ParameterNullException("排班或产品标记不存在",200);
//    	}

//		if(info.getSn()!=null  && !info.getSn().equals("") && (findProVr==null || findProVr.equals(""))) {
//			findProVr = mapper.findProductSign(info.getSn());
//			if(findProVr==null || findProVr.equals("")) {
//				throw new ParameterNullException("无法根据sn匹配产品",200);
//			}else {
//				info.setProductMark(findProVr);
//			}
//		}

		Integer findLineType = dao.findLineType(info.getLineName());
		if (findLineType == 1) {
			dao.insertInfo(info);
		} else if (findLineType == 3) {
			Integer findInfoName = dao.findInfoName(info.getSn(), info.getLineName());
			if (findInfoName > 0) {
				throw new ParameterNullException("类型条码重复", 200);
			}
			dao.insertInfo(info);
		} else {
			dao.insertInfo(info);
		}
		return 1;
	}

	public Integer finScheId(Integer lineId, Integer num) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");// 设置日期格式
		String dt = df.format(new Date());// new Date()为获取当前系统时间

		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String dts = dfs.format(new Date());// new Date()为获取当前系统时间

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		String dt1 = dfs.format(calendar.getTime()); // 当前天数的前一天

		Integer findMaxId = schedao.findMaxId(dts, dt, lineId); // 根据年月日时分秒查询是否存在此排班
		if (findMaxId != null && findMaxId != 0) { // 不是全天班
			return findMaxId;
		} else { // 全天班
			Integer findMaxIdDay = schedao.findMaxIdDay(dts, lineId); // 查询年月日当天是否存在排班
			if (findMaxIdDay == null) { // 等于null，说明今天没有排班，那么就去查前一天的排班
				if (num == 0) {
					return 0;
				}
				Integer yesterday = schedao.findMaxIdDay(dt1, lineId); // 查询前一天的排班
				if (yesterday == null) { // 代表昨天也没有排班,直接返回
					return 0;
				} else { // 昨天有排班，查询下线时间是否大于排班结束时间
					String findStartTime = schedao.findEndTime(yesterday); // 查询到的昨天排班的结束时间
					Date parse = df.parse(findStartTime + ":00"); // 排班结束时间
					Date parse2 = df.parse(dt); // 当前时间
					if (parse2.before(parse)) { // 说明当前生产计数在昨天的跨天排班中
						return yesterday;
					} else { // 代表没有排班，返回0
						return 0;
					}
				}
			} else { // 代表今天有排班
				if (num == 0) { // 查询昨天的排班
					Integer yesterday = schedao.findMaxIdDay(dt1, lineId); // 查询前一天的排班
					if (yesterday == null) { // 代表昨天也没有排班,直接返回
						return 0;
					} else { // 昨天有排班，查询下线时间是否大于排班结束时间
						String findStartTime = schedao.findEndTime(yesterday); // 查询到的昨天排班的结束时间
						Date parse = df.parse(findStartTime + ":00"); // 排班结束时间
						Date parse2 = df.parse(dt); // 当前时间
						if (parse2.before(parse)) { // 说明当前生产计数在昨天的跨天排班中
							return yesterday;
						} else { // 代表没有排班，返回0
							return 0;
						}
					}
				}
				String findStartTime = schedao.findStartTime(findMaxIdDay); // 查询今天排班的开始时间
				Date parse = df.parse(findStartTime + ":00"); // 排班结束时间
				Date parse2 = df.parse(dt);
				if (parse.before(parse2)) { // 代表当前时间大于今天排班的开始时间
					return findMaxIdDay;
				} else {
					return finScheId(lineId, 0);
				}
			}
		}
	}

	@Override
	public List<InsertInfo> findNowInfo(String lineName, String stationName) throws ServicesException {
		// TODO Auto-generated method stub

		return dao.findNowInfo(lineName, stationName);
	}

	@Override
	public List<Map<String, Object>> findPareto(String lineName, String stationName, String startDt, String endtime)
			throws Exception {
		// TODO Auto-generated method stub
		/*
		 * if(!isValidDate(startDt) || !isValidDate(endtime)){ throw new
		 * ParameterNullException("["+startDt+"]不是日期格式的",200); }
		 */
		List<Map<String, Object>> findPareto = dao.findPareto(lineName, stationName, startDt, endtime);
		for (Map<String, Object> map : findPareto) {
			Integer lid = (Integer) map.get("lossType");
			List<CMesAndonFault> findDt = dao.findDt(lid);
			long times = 0;
			if (findDt.size() > 0) {
				for (CMesAndonFault cMesAndonFault : findDt) {
					times = times + getMinutes(cMesAndonFault.getEstablishDt(), cMesAndonFault.getSolveDt());
				}
				map.put("dt", times);
			}
		}
		return findPareto;
	}

	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		try {
			if (str != null && str != "") {
				format.setLenient(false);
				format.parse(str);
			}
		} catch (Exception e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}

	@Override
	public List<InsertInfo> findInfo(String lineName, String stationName) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findInfo(lineName, stationName);
	}

	@Override
	public List<CMesLossTypeT> findAllLoss() throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllLoss();
	}

	@Override
	public List<CMesAndonFault> findNowAndon(String lineName, String stationName, String lossType, String startDate,
			String endDate) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findNowAndon(lineName, stationName, lossType, startDate, endDate);
	}

	@Override
	public List<Map<String, Object>> findEmp() throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findEmp();
	}

	@Override
	public Integer delAndon(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if (id == null || id == 0) {
			throw new ParameterNullException("id不能为空", 200);
		}
		return dao.delAndon(id);
	}

	@Override
	public JSONArray findStationStatus(String lineName) throws ServicesException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
		if (lineName != null && lineName != "") {
			JSONObject jsons = new JSONObject();
			JSONArray array1 = new JSONArray();
			Integer findLineByName = planDAO.findLineByName(lineName); // 查询是否存在此产线
			if (findLineByName == null) {
				throw new ParameterNullException("不存在此产线", 200);
			}
			List<CMesStationT> findStation = dao.findStation(findLineByName);// 查询所有工位
			List<String> list = new ArrayList<String>();
			List<String> list1 = new ArrayList<String>();
//			List<CMesAndonFault> findNowAndon = mapper.findNowAndon(lineName, null,null,null,null);//根据产线名称查询当前故障列表
			/*
			 * for (CMesAndonFault cMesAndonFault : findNowAndon) { for (CMesStationT
			 * cMesStationT : findStation) { JSONObject json = new JSONObject();
			 * //还有0正常，1故障，2缺料，3响应，不是只有两种状态
			 * if(cMesStationT.getStationName().equals(cMesAndonFault.getStationName())){
			 * if(cMesAndonFault.getStatus()==1){
			 * json.put("name",cMesStationT.getStationName()); json.put("status",6);
			 * json.put("stIndex",cMesStationT.getStationIndex());//工位下标
			 * json.put("dt",mapper.findRDt(cMesAndonFault.getId()));
			 * json.put("stEvent",cMesAndonFault.getLossType()); array1.add(json); }else
			 * if(cMesAndonFault.getStatus()==0){
			 * json.put("name",cMesStationT.getStationName()); json.put("status",
			 * cMesAndonFault.getLossType());
			 * json.put("stIndex",cMesStationT.getStationIndex());//工位下标
			 * json.put("dt",mapper.findEDt(cMesAndonFault.getId())); array1.add(json); }
			 * list.add(cMesStationT.getStationName()); } } }
			 */
			for (CMesStationT cMesStationT : findStation) {
				JSONObject json = new JSONObject();
				List<CMesAndonFault> findNowAndon = dao.findNowAndon(lineName, cMesStationT.getStationName(), null,
						null, null);// 根据产线名称查询当前故障列表
				if (findNowAndon.size() > 0) {
					if (findNowAndon.get(0).getStatus() == 1) {
						json.put("name", cMesStationT.getStationName());
						json.put("status", 6);
						json.put("stIndex", cMesStationT.getStationIndex());// 工位下标
						json.put("dt", dao.findRDt(findNowAndon.get(0).getId()));
						json.put("stEvent", findNowAndon.get(0).getLossType());
						array1.add(json);
					} else if (findNowAndon.get(0).getStatus() == 0) {
						json.put("name", cMesStationT.getStationName());
						json.put("status", findNowAndon.get(0).getLossType());
						json.put("stIndex", cMesStationT.getStationIndex());// 工位下标
						json.put("dt", dao.findEDt(findNowAndon.get(0).getId()));
						array1.add(json);
					}
					list.add(cMesStationT.getStationName());
				}
			}

			// 查询由中间表插入的数据
			List<String> findStationName = dao.findStationName(lineName);// 查询由中间表添加的故障
			List<String> diffrent = getDiffrent(list, findStationName);
			for (String string : diffrent) {
				List<Integer> findDeviceStataus = dao.findDeviceStataus(lineName, string);
				if (findDeviceStataus.contains(0)) {
					JSONObject json = new JSONObject();
					json.put("name", string);
					json.put("status", 5);
					json.put("dt", "");
					Integer stIndex = dao.findByIndex(string);
					json.put("stIndex", stIndex);// 工位下标
					array1.add(json);
					list.add(string);
				}
			}

			if (list.size() == 0) {
				for (CMesStationT cMesStationT : findStation) {
					JSONObject json = new JSONObject();
					json.put("name", cMesStationT.getStationName());
					json.put("status", 0);
					json.put("stIndex", cMesStationT.getStationIndex());// 工位下标
					json.put("dt", dao.findSDt(lineName, cMesStationT.getStationName()));
					array1.add(json);
				}
			} else {
				for (CMesStationT cMesStationT : findStation) { // 四个工位
					list1.add(cMesStationT.getStationName());
				}
				List<String> list2 = getDiffrent(list1, list);
				for (String string : list2) {
					JSONObject jsonss = new JSONObject();
					jsonss.put("name", string);
					jsonss.put("status", 0);
					Integer stIndex = dao.findByIndex(string);
					jsonss.put("stIndex", stIndex);// 工位下标
					jsonss.put("dt", dao.findSDt(lineName, string));
					array1.add(jsonss);
				}
			}
			jsons.put("name", lineName);
			jsons.put("station", array1);
			array.add(jsons);
		} else {
			List<CMesLineT> findAllLine = lineDAO.findAllLine(null);
			for (CMesLineT cMesLineT : findAllLine) {
				if (cMesLineT.getName().equals("CB318焊接线")) {
					System.out.println("ss");
				}
				JSONObject jsons = new JSONObject();
				JSONArray array1 = new JSONArray();
				Integer findLineByName = planDAO.findLineByName(cMesLineT.getName());
				if (findLineByName == null) {
					throw new ParameterNullException("不存在此产线", 200);
				}
				List<CMesStationT> findStation = dao.findStation(findLineByName);
				List<String> list = new ArrayList<String>();
				List<String> list1 = new ArrayList<String>();
//				List<CMesAndonFault> findNowAndon = mapper.findNowAndon(cMesLineT.getName(), null,null,null,null);
//				for (CMesAndonFault cMesAndonFault : findNowAndon) {
//					for (CMesStationT cMesStationT : findStation) {
//						JSONObject json = new JSONObject();
//						if(cMesStationT.getStationName().equals(cMesAndonFault.getStationName())){
//							if(cMesAndonFault.getStatus()==1){
//								json.put("name",cMesStationT.getStationName());
//								json.put("status", 6);
//								json.put("stIndex",cMesStationT.getStationIndex());//工位下标
//								json.put("dt",mapper.findRDt(cMesAndonFault.getId()));
//								json.put("stEvent",cMesAndonFault.getLossType());
//								array1.add(json);
//							}else if(cMesAndonFault.getStatus()==0){
//								json.put("name",cMesStationT.getStationName());
//								json.put("status", cMesAndonFault.getLossType());
//								json.put("stIndex",cMesStationT.getStationIndex());//工位下标
//								json.put("dt",mapper.findEDt(cMesAndonFault.getId()));
//								array1.add(json);
//							}
////							else if(cMesAndonFault.getFaultType().equals("1")){
////								json.put("name",cMesStationT.getStationName());
////								json.put("status", 1);
////								json.put("stIndex",cMesStationT.getStationIndex());//工位下标
////								json.put("dt",mapper.findEDt(cMesAndonFault.getId()));
////								array1.add(json);
////							}else if(cMesAndonFault.getFaultType().equals("2")){
////								json.put("name",cMesStationT.getStationName());
////								json.put("status", 2);
////								json.put("stIndex",cMesStationT.getStationIndex());//工位下标
////								json.put("dt",mapper.findEDt(cMesAndonFault.getId()));
////								array1.add(json);
////							}
//							list.add(cMesStationT.getStationName());
//						}
//					}
//				}
				for (CMesStationT cMesStationT : findStation) {
					JSONObject json = new JSONObject();
					List<CMesAndonFault> findNowAndon = dao.findNowAndon(cMesLineT.getName(),
							cMesStationT.getStationName(), null, null, null);// 根据产线名称查询当前故障列表
					if (findNowAndon.size() > 0) {
						if (findNowAndon.get(0).getStatus() == 1) {
							json.put("name", cMesStationT.getStationName());
							json.put("status", 6);
							json.put("stIndex", cMesStationT.getStationIndex());// 工位下标
							json.put("dt", dao.findRDt(findNowAndon.get(0).getId()));
							json.put("stEvent", findNowAndon.get(0).getLossType());
							array1.add(json);
						} else if (findNowAndon.get(0).getStatus() == 0) {
							json.put("name", cMesStationT.getStationName());
							json.put("status", findNowAndon.get(0).getLossType());
							json.put("stIndex", cMesStationT.getStationIndex());// 工位下标
							json.put("dt", dao.findEDt(findNowAndon.get(0).getId()));
							array1.add(json);
						}
						list.add(cMesStationT.getStationName());
					}
				}
				// 查询由中间表插入的数据
				List<String> findStationName = dao.findStationName(cMesLineT.getName());
				List<String> diffrent = getDiffrent(list, findStationName);
				for (String string : diffrent) {
					List<Integer> findDeviceStataus = dao.findDeviceStataus(cMesLineT.getName(), string);
					if (findDeviceStataus.contains(0)) {
						JSONObject json = new JSONObject();
						json.put("name", string);
						Integer stIndex = dao.findByIndex(string);
						json.put("stIndex", stIndex);// 工位下标
						json.put("status", 5);
						array1.add(json);
						list.add(string);
					}
				}

				if (list.size() == 0) {
					for (CMesStationT cMesStationT : findStation) {
						JSONObject json = new JSONObject();
						json.put("name", cMesStationT.getStationName());
						json.put("status", 0);
						json.put("stIndex", cMesStationT.getStationIndex());// 工位下标
						json.put("dt", dao.findSDt(cMesLineT.getName(), cMesStationT.getStationName()));
						array1.add(json);
					}
				} else {
					for (CMesStationT cMesStationT : findStation) { // 四个工位

						list1.add(cMesStationT.getStationName());
					}
					List<String> list2 = getDiffrent(list1, list);

					for (String string : list2) {
						JSONObject jsonss = new JSONObject();
						jsonss.put("name", string);
						jsonss.put("status", 0);
						jsonss.put("dt", dao.findSDt(cMesLineT.getName(), string));
						Integer stIndex = dao.findByIndex(string);
						jsonss.put("stIndex", stIndex);// 工位下标
						array1.add(jsonss);
					}
				}

				jsons.put("name", cMesLineT.getName());
				jsons.put("region", cMesLineT.getRegion());
				jsons.put("station", array1);
				array.add(jsons);
			}
		}
		return array;
	}

	public static List<String> getDiffrent(List<String> list1, List<String> list2) {
		Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
		List<String> diff = new ArrayList<String>();
		List<String> maxList = list1;
		List<String> minList = list2;
		if (list2.size() > list1.size()) {
			maxList = list2;
			minList = list1;
		}

		for (String string : maxList) {
			map.put(string, 1);
		}

		for (String string : minList) {
			Integer cc = map.get(string);
			if (cc != null) {
				map.put(string, ++cc);
				continue;
			}
			map.put(string, 1);
		}

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				diff.add(entry.getKey());
			}
		}
		return diff;
	}

	@Override
	public JSONArray findCount(String lineName) throws ServicesException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
		if (lineName != null && lineName != "") {
			Integer findLineByName = planDAO.findLineByName(lineName);
			if (findLineByName == null) {
				throw new ParameterNullException("不存在此产线", 200);
			}
			JSONObject json = new JSONObject();
			JSONObject jo = new JSONObject();
			Integer findAndonInfo = dao.findAndonInfo(lineName, 0);
			Integer findAndonInfo2 = dao.findAndonInfo(lineName, 1);
			Integer findAndonInfo3 = dao.findAndonInfo(lineName, 2);
			jo.put("nosolve", findAndonInfo);
			jo.put("solving", findAndonInfo2);
			jo.put("solved", findAndonInfo3);
			json.put("name", lineName);
			json.put("status", jo);
			array.add(json);
		} else {
			List<CMesLineT> findAllLine = lineDAO.findAllLine(null);
			for (CMesLineT cMesLineT : findAllLine) {
				JSONObject json = new JSONObject();
				JSONObject jo = new JSONObject();
				Integer findAndonInfo = dao.findAndonInfo(cMesLineT.getName(), 0);
				Integer findAndonInfo2 = dao.findAndonInfo(cMesLineT.getName(), 1);
				Integer findAndonInfo3 = dao.findAndonInfo(cMesLineT.getName(), 2);
				jo.put("nosolve", findAndonInfo);
				jo.put("solving", findAndonInfo2);
				jo.put("solved", findAndonInfo3);
				json.put("name", cMesLineT.getName());
				json.put("status", jo);
				array.add(json);
			}
		}
		return array;
	}

	public static Long getMinutes(String startTime, String endTime) throws Exception {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parse = sim.parse(startTime);
		Date parse1 = sim.parse(endTime);
		long s = parse1.getTime() - parse.getTime();
		long minutes = s / 60000;
		return minutes;
	}

	@Override
	public List<Map<String, Object>> findParetors(String lineName, String stationName, String startTime, String endtime,
			Integer lossType) throws Exception {
		/// TODO Auto-generated method stub
		/*
		 * if(!isValidDate(startTime) || !isValidDate(endtime)){ throw new
		 * ParameterNullException("["+startTime+"]不是日期格式的",200); }
		 */
//		if (startTime.equals(" 00:00:00")) {
//			 startTime="2000-01-11 00:00:00";
//		}
//		System.err.println("开始时间===="+startTime+"=====+endtime=="+endtime);
		List<Map<String, Object>> findPareto = dao.findParetors(lineName, stationName, startTime, endtime, lossType);
		for (Map<String, Object> map : findPareto) {
			String reason = map.get("rid").toString();
			List<CMesAndonFault> findDt = dao.findDts(reason);
			long times = 0;
			if (findDt.size() > 0) {
				for (CMesAndonFault cMesAndonFault : findDt) {
					times = times + getMinutes(cMesAndonFault.getEstablishDt(), cMesAndonFault.getSolveDt());
				}
				map.put("dt", times);
			}
		}
		return findPareto;
	}

	@Override
	public List<CMesLossReasonT> findLossReason(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if (id == null || id == 0) {
			throw new ParameterNullException("Id不能 为空", 200);
		}
		return dao.findLossReason(id);
	}

	@Override
	public JSONObject findNowNumber(String lineName) throws ServicesException, Exception {
		// TODO Auto-generated method stub
		Integer findLineId = dao.findLineId(lineName);
		if (findLineId == null) {
			throw new ParameterNullException("此产线不存在", 200);
		}

		Integer schedulingId = finScheId(findLineId, 1);
		Integer sum = 0;
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		SimpleDateFormat simone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat simTwo = new SimpleDateFormat("yyyy-MM-dd HH");
		SimpleDateFormat simThree = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat simsFour = new SimpleDateFormat("HH:mm");
		SimpleDateFormat simfive = new SimpleDateFormat("HH");
		SimpleDateFormat simSix = new SimpleDateFormat("mm");
		String format3 = simSix.format(new Date()); // 当前时间(分钟)
		format3 = format3.substring(0, 1) + "0";
		String format = simsFour.format(new Date()); // 当前时间(小时+分钟)
		String format2 = simTwo.format(new Date());
		String hource = simfive.format(new Date()); // 当前时间(小时)
		Integer hous = Integer.parseInt(hource); // 当前时间（小时）
		String formats = simThree.format(new Date()); // 当前时间(年月日)
		CMesShiftsTeamT findNowNumber = dao.findNowNumber(schedulingId); // 查询在当前时间的班次
//		if(findNowNumber==null){  //如果没有此班次
//			for (int i = 0; i < 20; i++) {
//				if(hous==00){hous=24;}
//				findNowNumber = mapper.findNowNumber(hous-1+":00",findLineId);
//				hous=hous-1;
//				if(findNowNumber!=null){
//					break;
//				}
//			}
//		}
		json.put("shiftName", findNowNumber.getName());
		String startTime = formats + " " + findNowNumber.getStartTime() + ":00"; // 班次开始时间
		String endTime = formats + " " + findNowNumber.getEndTime() + ":00"; // 班次结束时间
		json.put("startTime", findNowNumber.getStartTime());
		if (!utilDate(findNowNumber.getStartTime(), findNowNumber.getEndTime())) {
			json.put("endTime", "23:59");
		} else {
			json.put("endTime", findNowNumber.getEndTime());
		}

		List<String> list = new ArrayList<String>();
		list.add(startTime);
		for (int i = 0; i < 100; i++) {
			startTime = dao.findNowAddMinute(startTime);
			list.add(startTime);

			if (startTime.equals(format2 + ":" + format3 + ":00")) {
				break;
			}
		}
		JSONObject jsons = new JSONObject();
		// 班次开始时间时数量是0
		jsons.put("dt", findNowNumber.getStartTime());
		jsons.put("number", "0");
		array.add(jsons);
		for (int i = 0; i < list.size(); i++) {
			if (i < list.size() - 1) {
				Integer findTenMinuteNumber = dao.findTenMinuteNumber(lineName, list.get(i), list.get(i + 1));
				JSONObject json1 = new JSONObject();
				Date parse = simone.parse(list.get(i + 1));
				json1.put("dt", simsFour.format(parse));
				sum = sum + findTenMinuteNumber;
				json1.put("number", sum);
				array.add(json1);
			}
		}
		json.put("info", array);
		return json;
	}

	public boolean utilDate(String str1, String str2) {
		String[] array1 = str1.split(":");
		int total1 = Integer.valueOf(array1[0]) * 3600 + Integer.valueOf(array1[1]) * 60 + Integer.valueOf(array1[1]);
		String[] array2 = str2.split(":");
		int total2 = Integer.valueOf(array2[0]) * 3600 + Integer.valueOf(array2[1]) * 60 + Integer.valueOf(array2[1]);
		return total1 - total2 > 0 ? true : false;
	}

	@Override
	public Integer updateStationResponse(String lineName) throws ServicesException {
		// TODO Auto-generated method stub
		Integer findLineName = dao.findLineName(lineName);
		if (findLineName == 0) {
			throw new ParameterNullException("不存在此产线", 200);
		}
		List<Integer> findStationResponse = dao.findStationResponse(lineName);
		if (findStationResponse.size() > 0) {
			for (Integer integer : findStationResponse) {
				dao.updateStationResponse(integer);
			}
		}
		return 1;
	}

	@Override
	public List<JSONObject> findStationStatus710(String lineName) throws ServicesException {
		List<JSONObject> list = planDAO.findStationStatus710(lineName);
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			JSONObject json = list.get(i);
			List<Object> stationList = json.getJSONArray("station");
			for (int j = 0; j < stationList.size(); j++) {
				JSONObject station = JSONObject.parseObject(stationList.get(j).toString());
				String status = station.getString("status");
				String lossType = station.getString("lossType");
				String toolId = station.getString("toolId");
				String stEvent = "";
				if (status.equals("1")) {
					status = "6";
					stEvent = lossType;
				} else if (status.equals("2")) {
					status = "0";
					sum++;
				} else if (status.equals("0")) {
					status = lossType;
				} else if (!toolId.equals("")) {
					status = "5";
				} else if (status.equals("")) {
					status = "0";
				}
				station.put("status", Integer.parseInt(status));
				station.put("stIndex", station.getInteger("stIndex"));
				station.put("lossType", lossType);
//				station.put("toolId", toolId);
				station.remove("lossType");
				station.remove("toolId");
				if (status.equals("6")) {
					station.put("stEvent", stEvent == null ? null : Integer.parseInt(stEvent));
				}
				stationList.set(j, station);
			}
			JSONObject dx = new JSONObject();
			dx.put("sum", sum);// 解决数量
			dx.put("name", json.getString("name"));
			dx.put("region", json.getString("region"));
			dx.put("station", stationList);

			list.set(i, dx);
		}
		return list;
	}

	@Override
	public void closePlan() {
		// TODO Auto-generated method stub
		dao.closePlan();
	}

}

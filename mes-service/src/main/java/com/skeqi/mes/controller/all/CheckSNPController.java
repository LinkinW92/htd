package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.api.UpdateReworkSnPT;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.all.CheckSnPService;
import com.skeqi.mes.service.all.UpdatePlanPService;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 总成上线
 * @author : FQZ
 * @Package: com.skeqi.mes.controller.all
 * @date   : 2020年4月8日 上午9:49:56
 */
@RestController
@RequestMapping(value="api",produces = MediaType.APPLICATION_JSON)
@Api(value = "总成上线", description = "用户信息", produces = MediaType.APPLICATION_JSON)
public class CheckSNPController {

	@Autowired
	CMesWebApiLogsService logsService;


	@Autowired
	UpdatePlanPService moveDataPService;

	@Autowired
	private CheckSnPService checkSnPService;

	@Autowired
	private EventService serviceEvent;

	/**
	 * 入参
	 */

	/**
	 * 出参
	 */
	Integer r;
	/**
	 * 临时变量
	 */
	Integer tempTrackingCount;
	Integer tempPTrackingCout;
	Integer tempTrackingId;
	String tempTrackingEnginesn;// 产品是否返工
	String tempTrackingStatus;// 产品状态
	Integer tempTrackingProductionId;// 产品id
	String tempTrackingGearbosxn;// 经过工位下标
	String tempTrackingStationName;// 上一个工位
	Integer tempStationId;// 工位id
	String tempStationName;// 工位名称
	String tempReworkFlag;
	String tempTrackingBst;
	Integer tempStationLineId;// 产线id
	Integer tempPlanId;
	String tempDt;
	Integer tempWorkOrderId;


	// 保存返回数据
	JSONObject jo = new JSONObject();

	// 保存返回数据
	JSONObject joz = new JSONObject();


	@RequestMapping(value="checkSN",method = RequestMethod.POST)
	public synchronized void checkSN(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		JSONObject jo = new JSONObject();
		JSONObject data = new JSONObject();
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("errMsg", "发送数据为空");
			data.put("code", "201");
			data.put("r", "201");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("checkSN");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);
			// 获取总称码
			String sn = (String) json.get("sn");
			// 获取工位名称
			String station = (String) json.get("station");
			// 获取产线名称
			String line = (String) json.get("line");

			if (sn.isEmpty() || station.isEmpty() || line.isEmpty()) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
				data.put("code", "18");
				data.put("r", "18");
				jo.put("result", data);
				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(checkSnPService.checkSN(sn, station, line, jo).toJSONString());
//				return checkSnPService.checkSN(snBarcode, station, line, jo);

				// 事件添加
				Map<String, Object> map = new HashMap<>();
				map.put("NAME", line);
				Map<String, Object> mapResult = serviceEvent.getLineCode(map);
				Map<String, Object> mapEvent = new HashMap<>();
				mapEvent.put("OBJECT_TYPE", "成品");
				mapEvent.put("OBJECT_ID", sn);
				mapEvent.put("EVENT", "扫码上线");
				if(mapResult != null){
					mapEvent.put("PARAMETER1", mapResult.get("code"));
				}
				mapEvent.put("PARAMETER2", station);
				serviceEvent.addEvent(mapEvent);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			jo.put("isSuccess", false);
			data.put("code", "999");
			jo.put("msg", "出现异常");
			data.put("r", "999");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());

		} finally {
			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

		}

	}

	@RequestMapping(value = "updateReworkSN", method = RequestMethod.POST)
	public void updateReworkSnP(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		String snBarconde;
		String stationname;
		String lineName;
		Integer reworkMod;// 表示拆除所有的零件 其他： 表示 部分拆除
		response.setContentType("application/json");

//		joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("errMsg", "发送数据为空");
			jo.put("result", false);
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("updateReworkSN");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {
			snBarconde = json.get("sn").toString();
			stationname = json.get("station").toString();
			lineName = json.get("line").toString();
			reworkMod = Integer.parseInt(json.get("mode").toString());
		} catch (NullPointerException e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "参数缺失！");
			jo.put("result", false);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				jo.put("errMsg", jo.getString("errMsg") + "生成日志出错了!");
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			main(snBarconde,stationname,lineName,reworkMod);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			jo.put("isSuccess", false);
			// TODO: handle exception
//			joz.put("code", "17");
			jo.put("errMsg", "更新数据失败！");
			jo.put("result", false);
		} finally {
//			joz.put("r", r);
//			joz.put("code", r);
//			jo.put("result", joz);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
			}

			response.getWriter().append(jo.toJSONString());
		}
	}

	@Transactional
	public  synchronized void main(String snBarcondes,String stationnames,String  lineNames,Integer reworkMods) {
		synchronized(this) {
			String snBarconde = snBarcondes;
			String stationname= stationnames;
			String lineName = lineNames;
			Integer reworkMod =reworkMods;
		//更新总成状态
		checkSnPService.updateRMesReworkSn(snBarconde);

		if (reworkMod==1) {
			// 表示全部拆除总成物料，更新总成的状态为全部拆除 然后注意物料校验的时候二次利用物料
			UpdateReworkSnPT a = checkSnPService.find1(snBarconde);
			// 查询是否已经上线
			tempTrackingCount = Integer.parseInt(a.getTempTrackingCount());
			if (tempTrackingCount > 0) {
				// 该总成正在线上 更新该总成，状态为全拆
				UpdateReworkSnPT dx = checkSnPService.find2(snBarconde);

				/**
				 * 调用MoveDataPController
				 */
				UpdatePlanPController move = new UpdatePlanPController();
				// 赋予参数
				move.snMove = snBarconde;
				// 执行方法
				move.mainData();


				dx.setSnBarconde(snBarconde);
				checkSnPService.update1(snBarconde);
				checkSnPService.insert1(dx);
				checkSnPService.delete1(snBarconde);
				// 删除临时运行时表
				checkSnPService.delete2(snBarconde);
				// 删除临时物料表
				checkSnPService.delete3(snBarconde);
				// 删除临时螺栓表
				checkSnPService.delete4(snBarconde);
				// 删除临时气密性表
				checkSnPService.delete5(snBarconde);
				jo.put("isSuccess", true);
				jo.put("errMsg", "异常");
				jo.put("result", true);
				return;
			} else {
				// 该总成已经下线 则查询下线表中是否有该总成
				UpdateReworkSnPT b = checkSnPService.find3(snBarconde);
				tempPTrackingCout = Integer.parseInt(b.getTempPTrackingCout());
				if (tempPTrackingCout > 0) {
					checkSnPService.update2(snBarconde);
					checkSnPService.delete6(snBarconde);
					// 删除临时运行时表
					checkSnPService.delete7(snBarconde);
					// 删除临时物料表
					checkSnPService.delete8(snBarconde);
					// 删除临时螺栓表
					checkSnPService.delete9(snBarconde);
					// 删除临时气密性表
					checkSnPService.delete10(snBarconde);
					jo.put("isSuccess", true);
					jo.put("errMsg", "");
					jo.put("result", true);
					return;
				} else {
					// 该总成不在此产线的系统中
					r = 72;
					jo.put("isSuccess", false);
					jo.put("errMsg", "该总成不在此产线的系统中");
					jo.put("result", false);
					return;
				}
			}
		} else {
			// 表示部分拆除，需要将下线所有的数据移动到r表中，再装配
			// 标记总成为5 表示翻修站返修
			UpdateReworkSnPT a = checkSnPService.find4(snBarconde);
			tempTrackingCount = Integer.parseInt(a.getTempTrackingCount());
			if (tempTrackingCount > 0) {
				jo.put("isSuccess", true);
				jo.put("errMsg", "");
				jo.put("result", true);
				return;
			} else {
				UpdateReworkSnPT b = checkSnPService.find5(snBarconde);

				/**
				 * 调用MoveDataPController
				 */
				// 执行方法
				moveDataPService.mainData(snBarconde);

				tempPTrackingCout = Integer.parseInt(b.getTempPTrackingCout());
				if (tempPTrackingCout > 0) {


					List<UpdateReworkSnPT> pt = checkSnPService.insert2Find(snBarconde);
					for (UpdateReworkSnPT dx : pt) {
						// 总成已经下线了， 部分拆除
						checkSnPService.insert2(dx);
					}

					pt = null;
					pt = checkSnPService.insert3Find(snBarconde);
					for (UpdateReworkSnPT dx : pt) {
						// 跟新螺栓信息
						checkSnPService.insert3(dx);
					}

					pt = null;
					pt = checkSnPService.insert4Find(snBarconde);
					for (UpdateReworkSnPT dx : pt) {
						// 跟新气密性信息
						checkSnPService.insert4(dx);
					}





					UpdateReworkSnPT dx = checkSnPService.find6(snBarconde);
					dx.setStationname(stationname);
					dx.setSnBarconde(snBarconde);
					checkSnPService.insert5(dx);
					checkSnPService.delete11(snBarconde);
					checkSnPService.delete12(snBarconde);
					checkSnPService.delete13(snBarconde);
					jo.put("isSuccess", true);
					jo.put("errMsg", "");
					jo.put("result", true);
					return;
				} else {
					//系统中没有找到此条码
					jo.put("isSuccess", false);
					jo.put("errMsg", "系统中没有找到此条码");
					jo.put("result", false);
					r = 72;
					return;
				}

			}
		}
		}

	}

}

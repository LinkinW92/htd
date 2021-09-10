package com.skeqi.mes.service.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.glassfish.gmbal.impl.trace.TraceRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.project.AndonMessageDao;
import com.skeqi.mes.pojo.project.AndonMessage;
import com.skeqi.mes.pojo.project.CAndonIssuedMessage;

@Service
public class AndonMessageServiceImpl implements AndonMessageService {

	@Autowired
	AndonMessageDao dao;

	@Override
	public List<AndonMessage> findAndonMessageList() {
		// TODO Auto-generated method stub
		return dao.findAndonMessageList();
	}

	@Override
	public AndonMessage findAndonMessage() {
		// TODO Auto-generated method stub
		return dao.findAndonMessage();
	}

	@Override
	public Integer addAndonMessage(AndonMessage dx) throws Exception {
		String phone = dx.getPhone();
		String[] phones = phone.split(",");
		for (String string : phones) {
			if (string.length() != 11) {
				throw new Exception(string + "手机号应为11位数");
			} else {
				Pattern pattern = Pattern.compile("^[1]\\d{10}$");
				if (!pattern.matcher(string).matches()) {
					throw new Exception(string + "手机号是错误格式");
				}
			}

		}
		return dao.addAndonMessage(dx);
	}

	@Override
	public Integer deleteAndonMessage(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteAndonMessage(id);
	}

	@Override
	public Integer updateAndonMessage(AndonMessage dx) throws Exception {
		// TODO Auto-generated method stub
		String phone = dx.getPhone();
		String[] phones = phone.split(",");
		for (String string : phones) {
			if (string.length() != 11) {
				throw new Exception(string + "手机号应为11位数");
			} else {
				Pattern pattern = Pattern.compile("^[1]\\d{10}$");
				if (!pattern.matcher(string).matches()) {
					throw new Exception(string + "手机号是错误格式");
				}
			}

		}
		return dao.updateAndonMessage(dx);
	}

	@Transactional
	//@Scheduled(cron = "0/5 * * * * ?")
	public void messages() throws ParseException {
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// 异常
			List<JSONObject> faultList = dao.findFaultList();
			// 已发
			List<CAndonIssuedMessage> messageLogs = dao.findIssuedMessage();
			// 需发
			List<AndonMessage> messageList = dao.findAndonMessageList();

			// 消息
			List<JSONObject> list = new ArrayList<JSONObject>();

			JSONObject json = new JSONObject();

			// 异常
			for (JSONObject fault : faultList) {

				// 获得异常时间到目前为止的分钟数
				Integer establishDt = dataTime(format,fault.getString("establishDt"));
				// 需发
				messageFrom: for (AndonMessage message : messageList) {
					// 判断时间相等对应的表示需要发送的用户
					if (message.getDt().equals(establishDt)) {
						// 已发
						for (CAndonIssuedMessage messageLog : messageLogs) {
							if (message.getId().equals(messageLog.getMessageId())) {
								continue messageFrom;
							}
						}
						json = new JSONObject();
						json.put("faultId", fault.getString("id"));
						json.put("stationName", fault.getString("stationName"));
						json.put("lineName", fault.getString("lineName"));
						json.put("lossType", fault.getString("lossType"));
						json.put("message", message);
						json.put("phone", message.getPhone());

						list.add(json);
					}
				}

			}
			System.out.println("开始输出需要发送的用户");
			for (JSONObject jsonObject : list) {
				System.out.println(jsonObject);

				CAndonIssuedMessage dx = new CAndonIssuedMessage();
				dx.setFaultId(jsonObject.getInteger("faultId"));
				dx.setMessageId(jsonObject.getJSONObject("message").getInteger("id"));
				// 新增已发记录（避免重复发送）
				dao.addIssuedMessage(dx);
				// 新增已发日志
				dao.addMessageLog(jsonObject);

			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
	}

	Integer dataTime(SimpleDateFormat format,String startDate) throws ParseException {
		Date date = format.parse(startDate);
		// 日期转时间戳（毫秒）
		long s = date.getTime();
		long a = new Date().getTime();
		return (int) ((a / 1000 - s / 1000) / 60);
	}

}

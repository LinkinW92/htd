package com.skeqi.mes.service.alarm.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.alarm.CAlarmTimingUgradeDao;
import com.skeqi.mes.pojo.alarm.CAlarmNoticeLogs;
import com.skeqi.mes.pojo.alarm.CAlarmProblemUpgrade;
import com.skeqi.mes.pojo.alarm.CAlarmProblemUpgradeLogs;
import com.skeqi.mes.pojo.alarm.CAlarmProblems;
import com.skeqi.mes.pojo.project.CMesAndonFault;
import com.skeqi.mes.service.alarm.CAlarmTimingUgradeService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EamilUtil;


@Service
public class CAlarmTimingUgradeServiceImpl implements CAlarmTimingUgradeService {

	@Autowired
	CAlarmTimingUgradeDao dao;

//	@Scheduled(cron = "0/10 * * * * ?")
	@Override
	@Transactional
	public void upgrade() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// 查询升级
			List<JSONObject> upgrades = dao.findProblemUpgrade();

			// 查询当前故障
			List<CMesAndonFault> faults = dao.findAndonFault();
			for (CMesAndonFault fault : faults) {

				// 获取触发时间过去多少分钟了
				Integer s = null;

				// 查询是否有升级记录
				CAlarmProblemUpgradeLogs upgradeLogs = dao.findProblemUpgradeLogs(fault.getId());
				if (upgradeLogs == null || upgradeLogs.getDt() == null || upgradeLogs.getDt().equals("")) {
					// 获取触发时间过去多少分钟了
					s = dataTime(format, fault.getEstablishDt());
				} else {
					// 获取升级时间过去多少分钟了
					s = dataTime(format, upgradeLogs.getDt());
				}

				for (JSONObject upgrade : upgrades) {
					// 判断相同事件
					if (fault.getLossType().equals(upgrade.getInteger("lossTypeId").toString())) {
						// 判断相同等级
						if (fault.getFaultLevelId().equals(upgrade.getInteger("problemLevelId"))) {
							// 如果问题触发的时间分钟大于或者等于需要升级的分钟数给予升级
							if (s >= upgrade.getInteger("triggerTime")) {
								dao.upgrade(fault.getId(), upgrade.getInteger("upgradeProblemLevelId"));

								// 新增升级记录
								upgradeLogs = new CAlarmProblemUpgradeLogs();
								upgradeLogs.setFaultId(fault.getId());
								upgradeLogs.setBeforeUpgradelevelId(fault.getFaultLevelId());
								upgradeLogs.setAfterUpgradelevelId(upgrade.getInteger("upgradeProblemLevelId"));
								dao.addProblemUpgradeLogs(upgradeLogs);

								// 升级后查询升级后的等级是否有对应的通知方式
								String msg = "'" + fault.getLineName() +"——"+ fault.getStationName()+"——"+ fault.getLossName() + "" + "'问题等级从'" + upgrade.getString("qLevel")
										+ "'升至'" + upgrade.getString("hLevel") + "',请迅速处理";

								// 需要发送的邮件
								List<JSONObject> emailJsons = dao.findNnotificationMethod(upgrade.getInteger("lossTypeId"),
										upgrade.getInteger("upgradeProblemLevelId"),1);
								for (JSONObject json : emailJsons) {
									// 需要发送的用户
									String[] notificationChannelsContents = json
											.getString("notificationChannelsContent").split(",");

									// 设置邮件参数
									EamilUtil.SENDER_EAMIL = json.getString("senderEmail");
									EamilUtil.THE_SERVER = json.getString("theServer");
									EamilUtil.AUTHORIZATION_CODE = json.getString("authorizationCode");

									for (String string : notificationChannelsContents) {
										EamilUtil.sendEamil(string, "故障升级", msg);
									}

									CAlarmNoticeLogs logs = new CAlarmNoticeLogs();
									logs.setFaultId(fault.getId());
									logs.setLossType(json.getString("lossType"));
									logs.setSendOut(json.getString("senderEmail"));
									logs.setReceive(json.getString("notificationChannelsContent"));
									logs.setChannels(json.getString("notificationChannels"));
									logs.setChannelsType(json.getString("notificationChannelsTypeName"));

									dao.addNoticeLogs(logs);

								}
							}
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	Integer dataTime(SimpleDateFormat format, String startDate) throws ParseException {
		Date date = format.parse(startDate);
		// 日期转时间戳（毫秒）
		long s = date.getTime();
		long a = new Date().getTime();
		return (int) ((a / 1000 - s / 1000) / 60);
	}

}

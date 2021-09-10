package com.skeqi.mes.service.yp.oa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.system.SystemNewsService;

/**
 * OA定时器
 *
 * @author yinp
 *
 */
@Service
@Component
public class OATask {

	@Autowired
	DocumentTimeoutService documentTimeoutService;

	@Autowired
	SystemNewsService systemNewsService;

	/**
	 * 审批节点超时
	 */
	@Scheduled(cron = "0/60 * * * * ? ")
	public void tongjiTask() {
		try {
			// 当前时间
			Long startTs = System.currentTimeMillis();

			// 查询待审批数据
			// 状态=审批中
			// 已超时=false
			List<JSONObject> list = documentTimeoutService.findPendingApproval();
			for (JSONObject json : list) {
				// 单据号
				String listNo = json.getString("listNo");
				// 用户id
				Integer userId = json.getInteger("userId");
				// 发布时间
				long dt = Long.parseLong(dateToStamp(json.getString("dt")));
				// 超时时间
				Integer overtime = json.getInteger("overtime");
				// 相差分钟
				long timeDifference = (startTs - dt) / (1000 * 60);
				// 超时类型
				String overtimeType = json.getString("overtimeType");
				if (overtimeType.equals("天")) {
					overtime = overtime * 24 * 60;
				} else if (overtimeType.equals("时")) {
					overtime = overtime * 60;
				}

				if (overtime > timeDifference) {
					// 未超时
					continue;
				}

				// 超时
				// 新增系统通知
				JSONObject news = new JSONObject();
				news.put("title", "审批通知");
				news.put("msg", "您有一条需要处理的审批流程已超时，请及时处理。");
				news.put("userId", json.getInteger("userId"));
				news.put("state", "未读");
				news.put("expandData", "");
				news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
				// 发起通知
				systemNewsService.launch(news);

				// 通知申请人
				// 新增系统通知
//				news = new JSONObject();
//				news.put("title", "审批通知");
//				news.put("msg", "您有一条需要处理的审批流程已超时，请及时处理。");
//				news.put("userId", userId);
//				news.put("state", "未读");
//				news.put("expandData", "");
//				news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);
//				// 发起通知
//				systemNewsService.launch(news);

				// 更新已超时列
				documentTimeoutService.updateTimedOutWith(json.getInteger("id"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 审批单据超时
	 */
	@Scheduled(cron = "0/60 * * * * ? ")
	public void tongjiTask1() {
		try {
			// 当前时间
			Long startTs = System.currentTimeMillis();

			// 查询审批单据
			// 状态=审批中
			// 已超时=false
			List<JSONObject> list = documentTimeoutService.findApprovalRecord();
			for (JSONObject json : list) {
				// 单据号
				String listNo = json.getString("listNo");
				// 用户id
				Integer userId = json.getInteger("userId");
				// 发布时间
				long dt = Long.parseLong(dateToStamp(json.getString("dt")));
				// 超时时间
				Integer overtime = json.getInteger("overtime");
				// 相差分钟
				long timeDifference = (startTs - dt) / (1000 * 60);
				// 超时类型
				String overtimeType = json.getString("overtimeType");
				if (overtimeType.equals("天")) {
					overtime = overtime * 24 * 60;
				} else if (overtimeType.equals("时")) {
					overtime = overtime * 60;
				}

				if (overtime > timeDifference) {
					// 未超时
					continue;
				}

				// 超时
				// 查询待审批人
				List<JSONObject> withList = documentTimeoutService.queryApprover(listNo);

				// 通知正在审批的人
				for (JSONObject with : withList) {
					// 新增系统通知
					JSONObject news = new JSONObject();
					news.put("title", "审批通知");
					news.put("msg", "您有一条需要处理的审批流程已超时，请及时处理。");
					news.put("userId", with.getInteger("userId"));
					news.put("state", "未读");
					news.put("expandData", "");
					news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);

					// 发起通知
					systemNewsService.launch(news);
				}

				// 通知申请人
				// 新增系统通知
				JSONObject news = new JSONObject();
				news.put("title", "审批通知");
				news.put("msg", "您有一条提交的审批流程已超时。");
				news.put("userId", userId);
				news.put("state", "未读");
				news.put("expandData", "");
				news.put("url", "/OA/ApprovalCirculation/WaitingForMyApproval/singlePage?listNo=" + listNo);

				// 发起通知
				systemNewsService.launch(news);

				// 更新已超时列
				documentTimeoutService.updateTimedOut(listNo);
				System.out.println(listNo + "超时");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 找到当前审批人
	 *
	 * @param stepList
	 * @throws Exception
	 */
	public void findCurrentApprover(String listNo) throws Exception {

	}

	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	public static void main(String[] args) throws ParseException {
		Long a = 1628730000000L;
		Long startTs = System.currentTimeMillis();
		System.out.println((startTs - a) / (1000 * 60) / 60);

		// 1628730000000
		// 1628733600000
//		String res;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = simpleDateFormat.parse("2021-08-12 10:00:00");
//        long ts = date.getTime();
//        res = String.valueOf(ts);
//        System.out.println(res);
	}

}

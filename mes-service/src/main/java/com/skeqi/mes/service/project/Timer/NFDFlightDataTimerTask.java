package com.skeqi.mes.service.project.Timer;

import java.util.TimerTask;

import com.skeqi.mes.mapper.project.CMesAndonDAO;
import com.skeqi.mes.service.project.CMesAndonServiceImpl;
import com.skeqi.mes.util.yp.ApplicationContextUtil;

public class NFDFlightDataTimerTask extends TimerTask {

	CMesAndonServiceImpl service = (CMesAndonServiceImpl)ApplicationContextUtil.getBean("cMesAndonService");

	@Override
	public void run() {
		try {
			// 在这里写你要执行的内容
			System.out.println("===============================================================");
			System.out.println("===============================================================");
			System.out.println("===============================================================");
			System.out.println("===============================================================");
			System.out.println("===============================================================");
			service.closePlan();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.skeqi.mes.controller.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.project.DeviceCollectService;

@Controller
public class DeviceConllectController {

	@Autowired
	DeviceCollectService service;

//	 @Scheduled(cron = "0/5 * * * * ?")
//	 public void task() throws ServicesException{
//		 try {
//			 service.findTool();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	 }
}

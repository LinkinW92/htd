package com.skeqi.mes.util.task;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.skeqi.mes.service.zch.OrderService;

@Component
public class MaterialInventoryTask {

	@Autowired
	OrderService service;

	@Scheduled(cron = "0 0 1 * * ? ") // 每天凌晨1点执行
    public void taskCycle() {
        service.materialInventory();
    }

}

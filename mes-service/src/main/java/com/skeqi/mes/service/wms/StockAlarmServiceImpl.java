package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.wms.StockAlarmDao;

/**
 * 库存报警
 * @author yinp
 *
 */
@Service
public class StockAlarmServiceImpl implements StockAlarmService {

	@Autowired
	StockAlarmDao dao;

	/**
	 * 查询物料库存
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String,Object>> findList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findList(map);
	}

//	@Test
//	public void test(){
//		try {
//			ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
//			mapper = (StockAlarmDao) ap.getBean("stockAlarmDao");
//			List<Map<String,Object>> list = mapper.findList(null);
//			System.out.println(list);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}

}

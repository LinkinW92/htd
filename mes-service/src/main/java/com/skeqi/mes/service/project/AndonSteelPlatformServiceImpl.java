package com.skeqi.mes.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.project.AndonSteelPlatformDao;
import com.skeqi.mes.pojo.project.AndonSteelPlatform;

@Service
public class AndonSteelPlatformServiceImpl implements AndonSteelPlatformService {


	@Autowired
	AndonSteelPlatformDao dao;

	@Override
	public List<AndonSteelPlatform> findRAndonSteelPlatform() {
		// TODO Auto-generated method stub
		return dao.findRAndonSteelPlatform(false);
	}

	@Override
	public List<AndonSteelPlatform> findPAndonSteelPlatform(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.findPAndonSteelPlatform(json);
	}

	@Override
	public void scanCode(String sn) throws Exception{
		//通过sn截取到产品编码
		String productNo = sn.substring(0,sn.indexOf("|"));
		//通过产品编码查询产品信息
		JSONObject productJson = dao.findProductByProductNo(productNo);
		if(productJson==null || productJson.getInteger("productId")==null || productJson.getInteger("productId").equals("")) {
			throw new Exception("系统内没有配置该产品");
		}

		AndonSteelPlatform dx = new AndonSteelPlatform(sn,productJson.getInteger("productId"),productJson.getString("productName"),productJson.getString("productNo"),productJson.getString("productModel"),productJson.getString("productAbbreviation"));

		Integer result = dao.addRAndonSteelPlatform(dx);
		if(result<=0) {
			throw new Exception("插入数据失败");
		}

		//查询需要移至历史数据表的数据
		List<AndonSteelPlatform> list = dao.findRAndonSteelPlatform(true);
		for (AndonSteelPlatform andonSteelPlatform : list) {
			result = dao.deletePAndonSteelPlatform(andonSteelPlatform.getId());
			if(result<=0) {
				throw new Exception("删除数据失败");
			}
			result = dao.addPAndonSteelPlatform(andonSteelPlatform);
			if(result<=0) {
				throw new Exception("移动数据失败");
			}
		}

	}

	public static void main(String[] args) {
		String sn = "123|456|789";
		System.out.println();
	}

}

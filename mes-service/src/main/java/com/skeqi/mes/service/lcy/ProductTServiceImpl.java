package com.skeqi.mes.service.lcy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lcy.ProductTMapper;

@Service
public class ProductTServiceImpl implements ProductTService{
	@Autowired
	private ProductTMapper ptm;


	@Override
	public void updateProduct(String productId, String productionName, String productionType, String productionSeries,
			String productionVr, String productionDiscription, String productionSte,Integer labelTypeId,Integer productionSystemId,Integer productionGroupId,String path) {
		ptm.updateProduct(productId,productionName,productionType,productionSeries,productionVr,productionDiscription,productionSte,labelTypeId,productionSystemId, productionGroupId,path);

	}

	@Override
	public Integer queryProductionVr(String productionVr, String productId) {
		// TODO Auto-generated method stub
		return ptm.queryProductionVr(productionVr,productId);
	}

	@Override
	public Integer queryProductionType(String productionType, Integer id) {
		// TODO Auto-generated method stub
		return ptm.queryProductionType(productionType,id);
	}

	@Override
	public Integer queryProductionName(String productionName, Integer id) {
		// TODO Auto-generated method stub
		return ptm.queryProductionName(productionName,id);
	}

	@Override
	public void addProduction(String productionName, String productionType, String productionSeries,
			String productionVr, String productionDiscription, String productionTrademark, String productionSte,
			Integer labelTypeId, Integer productionSystemId, Integer productionGroupId, String path) {
		// TODO Auto-generated method stub
		ptm.addProduction(productionName, productionType, productionSeries, productionVr, productionDiscription, productionTrademark, productionSte, productionSystemId, productionGroupId, labelTypeId, path);
	}


}

package com.skeqi.mes.service.lcy;

public interface ProductTService {

	public Integer queryProductionVr(String productionVr, String productId);


	//添加数据
	public void addProduction(String productionName, String productionType, String productionSeries,
			String productionVr, String productionDiscription, String productionTrademark, String productionSte,Integer labelTypeId,Integer productionSystemId,Integer productionGroupId,String path);

	//更改数据
	public void updateProduct(String productId, String productionName, String productionType, String productionSeries,
			String productionVr, String productionDiscription, String productionSte,Integer labelTypeId,Integer productionSystemId,Integer productionGroupId,String path);


	public Integer queryProductionType(String productionType, Integer id);


	public Integer queryProductionName(String productionName, Integer id);
}

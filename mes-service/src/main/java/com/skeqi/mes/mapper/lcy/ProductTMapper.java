package com.skeqi.mes.mapper.lcy;

import org.apache.ibatis.annotations.Param;

public interface ProductTMapper {
	//添加产品
	public void addProduction(@Param("productionName")String productionName, @Param("productionType")String productionType,@Param("productionSeries") String productionSeries,
			@Param("productionVr")String productionVr,@Param("productionDiscription") String productionDiscription, @Param("productionTrademark")String productionTrademark,@Param("productionSte") String productionSte,
			@Param("productionSystemId")Integer productionSystemId,@Param("productionGroupId")Integer productionGroupId,@Param("labelTypeId")Integer labelTypeId,@Param("path")String path);
	//更新数据
	public void updateProduct(@Param("productId")String productId,@Param("productionName")String productionName, @Param("productionType")String productionType,@Param("productionSeries") String productionSeries,
			@Param("productionVr")String productionVr,@Param("productionDiscription") String productionDiscription,@Param("productionSte") String productionSte,@Param("labelTypeId")Integer labelTypeId,
			@Param("productionSystemId")Integer productionSystemId,@Param("productionGroupId")Integer productionGroupId,@Param("path")String path);

	//查询数据
	public Integer queryProductionVr(@Param("productionVr")String productionVr, @Param("productId")String productId);
	public Integer queryProductionType(@Param("productionType")String productionType, @Param("id")Integer id);
	public Integer queryProductionName(@Param("productionName")String productionName, @Param("id")Integer id);


}

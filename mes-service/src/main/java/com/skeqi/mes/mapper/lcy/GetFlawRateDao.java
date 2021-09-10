package com.skeqi.mes.mapper.lcy;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
public interface GetFlawRateDao {

	int getFlawProductionNumber(@Param("startDate")Date startDate, @Param("endDate")Date endDate,@Param("lineId")int lineId);

	int getProductionNumber(@Param("startDate")Date startDate, @Param("endDate")Date endDate,@Param("lineId")int lineId);




}

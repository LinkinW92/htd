package com.skeqi.mes.mapper.lcy;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesRecipeDatilT;

public interface UpdataBoltViewSomeDataMapper {

	CMesRecipeDatilT queryRecipeDatil(@Param("id") int id);

	void updataViewBoltData(@Param("id")String id, @Param("jsonStr")String jsonStr);



}

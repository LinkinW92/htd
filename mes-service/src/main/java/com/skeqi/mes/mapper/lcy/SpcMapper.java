package com.skeqi.mes.mapper.lcy;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesSPCCoeffic;
import com.skeqi.mes.pojo.PMesBoltT;

public interface SpcMapper {

	List<String> querySPCSite(@Param("spcStation") String spcStation);

	List<String> querySPCStation();

	List<PMesBoltT> querySpcBoltList(@Param("station")String station,@Param("site") String site, @Param("startTime")String startTime,@Param("endTime") String endTime,
			@Param("sampleNumber")String sampleNumber);

	CMesSPCCoeffic querySPCCoeffic(@Param("sampleSize")String sampleSize);


}

package com.skeqi.mes.mapper.all;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.api.AssembleLeakagePT;

@Component
@MapperScan
public interface AssembleLeakagePDao {

	@Select("SELECT MAX(ID) FROM R_MES_LEAKAGE_T WHERE SN = #{sn} AND ST = #{stationName}")
	public Integer getLeakageMaxIdBySnAndSt(@Param("sn") String sn,@Param("stationName") String stationName);


	@Select("SELECT LEAKAGE_NAME as tempName,LEAKAGE_PV as tempP,"
			+ "LEAKAGE_LV as tempL,LEAKAGE_R as tempR FROM R_MES_LEAKAGE_T  WHERE ID = #{id}")
//	@Results(id="assembleKeypartMap",value = {
//			@Result(column="LEAKAGE_NAME",property="tempName",javaType = String.class),
//			@Result(column="LEAKAGE_PV",property="tempP",javaType = String.class),
//			@Result(column="LEAKAGE_LV",property="tempL",javaType = String.class),
//			@Result(column="LEAKAGE_R",property="tempR",javaType = String.class)
//	})
	public AssembleLeakagePT getAssembleKeypartPTById(@Param("id") Integer id);


	@Update("UPDATE R_MES_LEAKAGE_T SET DT = now(), LEAKAGE_PV = #{leakagePv},LEAKAGE_LV=#{leakageLv},LEAKAGE_R = #{leakageR}, WID = #{emp} WHERE ID = #{id}")
	public void updateAssembleKeypartPT(@Param("leakagePv")String leakagePv,@Param("leakageLv")String leakageLv,
			@Param("leakageR")String leakageR, @Param("emp")String emp,@Param("id")Integer id);


	@Insert("INSERT INTO R_MES_LEAKAGE_T(DT,ST,SN,LEAKAGE_NAME,LEAKAGE_PV,LEAKAGE_LV,LEAKAGE_R,WID,TRANSFER,LEAKAGE_MODE,REWORK_FLAG)" +
			" VALUES(now(),#{stationName},#{snBarcode},#{tempName},#{pValues},#{lValues},#{rValues},#{emp},1,0,'0')")
	public void inserteAssembleKeypartPT(@Param("stationName")String stationName, @Param("snBarcode")String snBarcode, @Param("tempName")String tempName,
			@Param("pValues")String pValues,@Param("lValues")String lValues, @Param("rValues") String rValues,@Param("emp") String emp);



	//称重

	@Select("SELECT ID FROM P_MES_WEIGH_T WHERE SN = #{snBarcode} AND ST = #{stationName}")
	public Integer queryAssembleWeighBySnAndStationName(@Param("snBarcode")String snBarcode,@Param("stationName")String stationName);


	@Update("UPDATE P_MES_WEIGH_T SET DT=NOW(),WEIGH=#{weighValues},WID=#{emp} WHERE ID = #{id}")
	void updateAssembleWeigh(@Param("weighValues")String weighValues,@Param("emp")String emp,@Param("id")Integer id);
}

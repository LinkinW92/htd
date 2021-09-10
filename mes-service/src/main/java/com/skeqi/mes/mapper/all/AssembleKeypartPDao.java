package com.skeqi.mes.mapper.all;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.api.AssembleKeypartPT;
import com.skeqi.mes.pojo.api.CheckMaterialPT;

@Component
@MapperScan
public interface AssembleKeypartPDao {


	//**根据sn查询总配方ID***//
	@Select("SELECT plan.TOTAL_RECIPE_ID "
			+ "FROM r_mes_plan_print_t print,"
			+ "r_mes_workorder_detail_t plan"
			+ " WHERE print.WORK_ORDER_ID = plan.ID "
			+ "AND print.sn = #{sn} limit 1")
	String  findAllPlan(@Param("sn")String sn);

	@Select("SELECT sta.STATION_TYPE as tempStationType FROM c_mes_station_t sta,c_mes_line_t line WHERE sta.LINE_ID=line.ID and sta.STATION_NAME=#{stationInName} and line.NAME=#{lineName}")
	String queryStationType(@Param("stationInName")String stationInName,@Param("lineName")String lineName);


	@Select(" SELECT CMRD.CHEKORNO as tempMaterialCheckFlag,"
			+ "CMRD.FEACODE as tempMaterialVr,"
			+ "CMRD.MATERIAL_NAME as tempMaterialName," +
			"CMRD.EXACTORNO as tempExactorno,"
			+ "CMRD.STEP_CATEGORY as tempMaterialScdType" +
			" FROM C_MES_RECIPE_T CMPR," +
			" C_MES_RECIPE_DATIL_T CMRD," +
			" R_MES_TRACKING_T RMT," +
			" C_MES_STATION_T CMS," +
			" C_MES_LINE_T CML" +
			" WHERE CMPR.TOTAL_ID = #{recipeId}" +
			"     AND CML.NAME = #{lineName}" +
			"     AND CMS.STATION_NAME = #{stationInName}" +
			"     AND CMS.LINE_ID = CML.ID" +
			"     AND RMT.SN = #{snMaterial}" +
			"     AND CMRD.RECIPE_ID = CMPR.ID" +
			"     AND CMPR.STATION_ID = CMS.ID" +
			"     AND CMRD.STEPNO = #{stepNo}")
//	@Results(id="checkPlanSnPTMap1",value = {
//			@Result(column="CHEKORNO",property="tempMaterialCheckFlag",javaType = String.class),
//			@Result(column="FEACODE",property="tempMaterialVr",javaType = String.class),
//			@Result(column="MATERIAL_NAME",property="tempMaterialName",javaType = String.class),
//			@Result(column="EXACTORNO",property="tempExactorno",javaType = String.class),
//			@Result(column="STEP_CATEGORY",property="tempMaterialScdType",javaType = String.class)
//	})
	CheckMaterialPT queryCheckMaterialPT1(@Param("recipeId")String recipeId,@Param("lineName")String lineName, @Param("stationInName")String stationInName,@Param("snMaterial") String snMaterial,@Param("stepNo") String stepNo);



	@Select("   SELECT distinct CMRD.CHEKORNO as tempMaterialCheckFlag," +
			"         CMRD.FEACODE as tempMaterialVr," +
			"         CMRD.MATERIAL_NAME as tempMaterialName," +
			"         CMRD.EXACTORNO as tempExactorno," +
			"         CMRD.STEP_CATEGORY as tempMaterialScdType" +
			"    FROM C_MES_RECIPE_T CMPR," +
			"         C_MES_RECIPE_DATIL_T      CMRD," +
			"        P_MES_TRACKING_T          RMT," +
			"         C_MES_STATION_T           CMS," +
			"         C_MES_LINE_T              CML" +
			"   WHERE CMPR.TOTAL_ID = #{recipeId}" +
			"     AND CML.NAME = #{lineName}" +
			"     AND CMS.STATION_NAME = #{stationInName}" +
			"     AND CMS.LINE_ID = CML.ID" +
			"     AND RMT.SN = #{snMaterial}" +
			"     AND CMRD.RECIPE_ID = CMPR.ID" +
			"     AND CMPR.STATION_ID = CMS.ID" +
			"     AND CMRD.STEPNO = #{stepNo}")
//	@Results(id="checkPlanSnPTMap1",value = {
//			@Result(column="CHEKORNO",property="tempMaterialCheckFlag",javaType = String.class),
//			@Result(column="FEACODE",property="tempMaterialVr",javaType = String.class),
//			@Result(column="MATERIAL_NAME",property="tempMaterialName",javaType = String.class),
//			@Result(column="EXACTORNO",property="tempExactorno",javaType = String.class),
//			@Result(column="STEP_CATEGORY",property="tempMaterialScdType",javaType = String.class)
//	})
	CheckMaterialPT queryCheckMaterialPT2(@Param("recipeId")String recipeId,@Param("lineName")String lineName, @Param("stationInName")String stationInName,@Param("snMaterial") String snMaterial,@Param("stepNo") String stepNo);


	@Select(" SELECT SECOND_NUM" +
			"      FROM R_MES_KEYPART_T" +
			"     WHERE ID = (SELECT MAX(ID)" +
			"     FROM R_MES_KEYPART_T" +
			"     WHERE SN = #{snMaterial}" +
			"     AND ST = #{stationInName}" +
			"     AND KEYPART_NAME = #{tempMaterialName})")
	String queryTempMaterialSecondNum(@Param("snMaterial")String snMaterial, @Param("stationInName")String stationInName,@Param("tempMaterialName") String tempMaterialName);

	@Select(" SELECT COUNT(ID)" +
			" FROM P_MES_KEYPART_T" +
			" WHERE ID = (SELECT MAX(ID)" +
			" FROM P_MES_KEYPART_T" +
			" WHERE KEYPART_NUM = #{materialBarcode}" +
			" AND TRANSFER = 0)")
	Integer queryTempKepartcountP(@Param("materialBarcode") String materialBarcode);


	@Select(" SELECT COUNT(ID)" +
			" FROM R_MES_KEYPART_T" +
			" WHERE ID = (SELECT MAX(ID)" +
			" FROM R_MES_KEYPART_T" +
			" WHERE KEYPART_NUM = #{materialBarcode}" +
			" AND TRANSFER = 0)")
	Integer queryTempKepartcountR(@Param("materialBarcode") String materialBarcode);


	@Select(" SELECT ENGINESN " +
			" FROM R_MES_TRACKING_T" +
			" WHERE SN=#{snMaterial}")
	String queryTempTrackingEnginesnR(@Param("snMaterial") String snMaterial);

	@Select(" SELECT ENGINESN " +
			" FROM P_MES_TRACKING_T" +
			" WHERE SN=#{snMaterial}")
	String queryTempTrackingEnginesnP(@Param("snMaterial") String snMaterial);


	@Select("SELECT ENGINESN " +
			" FROM R_MES_TRACKING_T" +
			" WHERE SN=#{SN_MATERIAL}")
	String queryEnginesnByTrackingR(@Param("snMaterial")String snMaterial);

	@Select(" SELECT ENGINESN" +
			" FROM P_MES_TRACKING_T" +
			" WHERE SN=#{SN_MATERIAL}")
	String queryEnginesnByTrackingP(@Param("snMaterial")String snMaterial);

	@Select(" SELECT KEYPART_NUM" +
			" FROM R_MES_KEYPART_T" +
			" WHERE ID = (SELECT MAX(ID)" +
			" FROM R_MES_KEYPART_T" +
			" WHERE SN = #{snMaterial}" +
			" AND ST = #{stationInName}" +
			" AND KEYPART_NAME = #{tempMaterialName});")
	String queryKeypartNum(@Param("snMaterial")String snMaterial, @Param("stationInName")String stationInName, @Param("tempMaterialName")String tempMaterialName);




	@Select("SELECT MAX(ID) FROM R_MES_KEYPART_T WHERE SN = #{sn} AND ST = #{stationName} AND KEYPART_NAME = #{keypartName}")
	public Integer getKeypartMaxIdBySnAndStAndKeypartName(@Param("sn") String sn,
			@Param("stationName") String stationName,@Param("keypartName") String keypartName);


	@Select("SELECT id as tempMaxId,KEYPART_NUM as tempKeypartNum, KEYPART_ID as tempKeypartType, SECOND_NUM as tempSecondNum FROM R_MES_KEYPART_T  WHERE ID = #{id}")
//	@Results(id="assembleKeypartMap",value = {
//			@Result(column="KEYPART_NUM",property="tempKeypartNum",javaType = String.class),
//			@Result(column="KEYPART_ID",property="tempKeypartType",javaType = String.class),
//			@Result(column="SECOND_NUM",property="tempSecondNum",javaType = String.class)
//	})
	public AssembleKeypartPT getAssembleKeypartPTById(@Param("id") Integer id);


	@Update("UPDATE R_MES_KEYPART_T SET DT = now(), KEYPART_NUM = #{materialBarcode}, WID = #{emp} WHERE ID = #{id}")
	public void updateAssembleKeypartPT(@Param("materialBarcode")String materialBarcode, @Param("emp")String emp,@Param("id")Integer id);


}

package com.skeqi.mes.mapper.all;

import java.util.List;

import com.skeqi.mes.controller.all.InitializeDataPController;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.api.InitializeCurrentstepPT;
import com.skeqi.mes.pojo.api.InitializeDataPT;
import com.skeqi.mes.pojo.api.InitializeReworkDataPT;

/**
 *
 * @name
 * @author Yinp
 * @date 2020年01月10日13:58:57
 *
 */
@Component
@MapperScan
public interface InitializeDataPDao {

	/**
	 *
	 * @param iniProductionId
	 * @param productionInit
	 * @return cStationsStId
	 */
	@Select("select st_id cStationsStId "
			+ "from c_mes_production_way_t way,"
			+ "c_mes_routing_t routing "
			+ "where way.ROUTING_ID=routing.ID "
			+ "and routing.ID=#{routingId}")
	public List<InitializeDataPT> find1(@Param("routingId")Integer routingId);

	/**
	 *
	 * @param tempStationRecipeId
	 * @return cRecipesStepCategory
	 * @return cRecipesMaterialName
	 * @return cRecipesMaterialpn
	 * @return cRecipesNumbers
	 * @return cRecipesTLimit
	 * @return cRecipesALimit
	 */
	@Select("select step_category cRecipesStepCategory,material_name cRecipesMaterialName,"
			+ "materialpn cRecipesMaterialpn,numbers cRecipesNumbers,"
			+ "t_limit cRecipesTLimit,a_limit cRecipesALimit "
			+ "from c_mes_recipe_datil_t "
			+ "where recipe_id=#{tempStationRecipeId} "
			+ "order by stepno asc")
	public List<InitializeDataPT> find2(@Param("tempStationRecipeId")String tempStationRecipeId);

	/**
	 *
	 * @param cStationsStId
	 * @param iniProductionId
	 * @return tempStationRecipeId
	 */
	@Select("select id tempStationRecipeId from c_mes_recipe_t "
			+ "where station_id=#{cStationsStId} "
			+ "and TOTAL_ID=#{totalRecipeId}")
	public InitializeDataPT find3(@Param("cStationsStId")String cStationsStId,@Param("totalRecipeId")Integer totalRecipeId);

	/**
	 *
	 * @param cStationsStId
	 * @return tempStationName
	 */
	@Select("select station_name tempStationName from c_mes_station_t "
			+ "where id=#{cStationsStId}")
	public InitializeDataPT find4(@Param("cStationsStId")String cStationsStId);

	/**
	 *
	 * @param tempStationName
	 * @param snIni
	 * @param cRecipesMaterialName
	 * @param cRecipesMaterialpn
	 */
	@Insert(" insert into r_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,keypart_name,"
			+ "keypart_pn,keypart_rework_fg,second_num,keypart_rework_st)"
			+ "values(now(),0,0,#{tempStationName},"
			+ "#{snIni},#{cRecipesMaterialName},"
			+ "#{cRecipesMaterialpn},'0',null,null)")
	public void insert1(@Param("tempStationName")String tempStationName,
			@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")String cRecipesMaterialName,
			@Param("cRecipesMaterialpn")String cRecipesMaterialpn);

	/**
	 *
	 * @param tempStationName
	 * @param snIni
	 * @param cRecipesMaterialName
	 * @param cRecipesMaterialpn
	 */
	@Insert(" insert into r_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,keypart_name,"
			+ "keypart_pn,keypart_rework_fg,keypart_rework_st)"
			+ "values(now(),0,0,#{tempStationName},"
			+ "#{snIni},#{cRecipesMaterialName},"
			+ "#{cRecipesMaterialpn},'0',null)")
	public void insert2(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")String cRecipesMaterialName,@Param("cRecipesMaterialpn")String cRecipesMaterialpn);

	@Insert(" insert into r_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,"
			+ "st,sn,keypart_name,keypart_pn,"
			+ "keypart_rework_fg,keypart_rework_st)"
			+ "values(now(),0,0,#{tempStationName},"
			+ "#{snIni},#{cRecipesMaterialName},"
			+ "#{cRecipesMaterialpn},'0',null)")
	public void insert3(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")String cRecipesMaterialName,@Param("cRecipesMaterialpn")String cRecipesMaterialpn);

	/**
	 *
	 * @param tempStationName
	 * @param snIni
	 * @param cRecipesMaterialName
	 * @param cRecipesMaterialpn
	 */
	@Insert("insert into r_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,"
			+ "keypart_name,keypart_pn,keypart_rework_fg,keypart_rework_st)"
			+ "values(now(),0,0,#{tempStationName},"
			+ "#{snIni},#{cRecipesMaterialName},"
			+ "#{cRecipesMaterialpn},'0',null)")
	public void insert4(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")String cRecipesMaterialName,@Param("cRecipesMaterialpn")String cRecipesMaterialpn);

	/**
	 *
	 * @param snIni
	 * @param tempStationName
	 * @param cRecipesTLimit
	 * @param cRecipesALimit
	 * @param tempParamartersName
	 * @param temp
	 */
	@Insert("insert into r_mes_bolt_t("
			+ "dt,transfer,bolt_mode,sn,st,t_limit,a_limit,bolt_name,bolt_num,rework_flag,MATERIAL_INSTANCE_ID)"
			+ "values(now(),0,0,#{snIni},#{tempStationName},"
			+ "#{cRecipesTLimit},#{cRecipesALimit},"
			+ "#{tempParamartersName},#{whileTemp},'0',#{MATERIAL_INSTANCE_ID})")
	public void insert5(@Param("snIni")String snIni,@Param("tempStationName")String tempStationName,
			@Param("cRecipesTLimit")String cRecipesTLimit,@Param("cRecipesALimit")String cRecipesALimit,
			@Param("tempParamartersName")String tempParamartersName,@Param("whileTemp")String whileTemp
			,@Param("MATERIAL_INSTANCE_ID")Integer MATERIAL_INSTANCE_ID);

	public void batchlyInsert(List<InitializeDataPController.BoltInfo> data);

	/**
	 *
	 * @param tempStationName
	 * @param snIni
	 * @param cRecipesMaterialName
	 */
	@Insert("insert into r_mes_leakage_t(dt,st,sn,leakage_name,transfer,leakage_mode,rework_flag)"
			+ "values(now(),#{tempStationName},#{snIni},"
			+ "#{cRecipesMaterialName},1,0,'0')")
	public void insert6(@Param("tempStationName")String tempStationName,
			@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")String cRecipesMaterialName);

	/**
	 *
	 * @param tempStationName
	 * @param snIni
	 */
	@Insert("insert into p_mes_weigh_t(dt,st,sn,transfer)"
			+ "values(now(),#{tempStationName},#{snIni},0)")
	public void insert7(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni);

	/**
	 *
	 * @param tempStationName
	 * @param snIni
	 * @param cRecipesMaterialName
	 * @param cRecipesMaterialpn
	 */
	@Insert("insert into r_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,"
			+ "keypart_name,keypart_num,keypart_id,"
			+ "keypart_rework_fg,second_num,keypart_rework_st,MATERIAL_INSTANCE_ID)"
			+ "values(now(),0,0,#{tempStationName},#{snIni},"
			+ "#{cRecipesMaterialName},#{cRecipesMaterialpn},4,'0',null,null,#{MATERIAL_INSTANCE_ID})")
	public void insert8(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")String cRecipesMaterialName,@Param("cRecipesMaterialpn")String cRecipesMaterialpn, @Param("MATERIAL_INSTANCE_ID")Integer MATERIAL_INSTANCE_ID);

	@Insert("insert into r_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,keypart_name,"
			+ "keypart_pn,keypart_rework_fg,second_num,keypart_rework_st,MATERIAL_INSTANCE_ID)"
			+ "values(now(),0,0,#{tempStationName},#{snIni},"
			+ "#{cRecipesMaterialName},#{cRecipesMaterialpn},'0',null,null,#{MATERIAL_INSTANCE_ID})")
	public void insert9(@Param("tempStationName")String tempStationName,@Param("snIni")String snIni,
			@Param("cRecipesMaterialName")String cRecipesMaterialName,@Param("cRecipesMaterialpn")String cRecipesMaterialpn, @Param("MATERIAL_INSTANCE_ID")Integer MATERIAL_INSTANCE_ID);

	//初始化步序
	//
	//
	//
	//
	//
	//
	/**
	 *
	 * @param serialnumber
	 * @param station
	 * @param line
	 * @return tempSteprecordCount
	 */
	@Select("select count(*) tempSteprecordCount from r_mes_step_t s "
			+ "where s.sn = #{serialnumber} "
			+ "and s.st = #{station} "
			+ "and s.line_name = #{line}")
	public InitializeCurrentstepPT find1s(String serialnumber,String station,String line);


	@Insert("insert into r_mes_step_t (dt,st,sn,step_no,emp,line_name)"
			+ "values (now(),#{station},#{serialnumber},1,'',#{line});")
	public void insert1s(String serialnumber,String station,String line);

	//
	//
	//
	//
	//返修站初始化配方
	/**
	 *
	 * @param snRework
	 * @return cStationsStName
	 */
	@Select("select ST_NAME cStationsStName from r_mes_rework_way_t "
			+ "where sn=#{snRework} "
			+ "order by serial_no desc")
	public List<InitializeReworkDataPT> finds1(String snRework);

	/**
	 *
	 * @param tempStationRecipeId
	 * @return cRecipesStepCategory
	 * @return cRecipesMaterialName
	 * @return cRecipesNumbers
	 * @return cRecipesTLimit
	 * @return cRecipesALimit
	 */
	@Select("select step_category cRecipesStepCategory,material_name cRecipesMaterialName,"
			+ "numbers cRecipesNumbers,t_limit cRecipesTLimit,a_limit cRecipesALimit "
			+ "from c_mes_recipe_datil_t "
			+ "where recipe_id=#{tempStationRecipeId} "
			+ "order by stepno desc")
	public List<InitializeReworkDataPT> finds2(String tempStationRecipeId);

	/**
	 *
	 * @param cStationsStName
	 * @param productionId
	 * @return tempStationRecipeId
	 */
	@Select("select recipe.ID tempStationRecipeId "
			+ "from c_mes_recipe_t recipe,c_mes_station_t sta "
			+ " where sta.ID=recipe.STATION_ID and "
			+ " sta.STATION_NAME=#{cStationsStName} "
			+ " and recipe.TOTAL_ID=#{totalId}")
	public InitializeReworkDataPT finds3(@Param("cStationsStName")String cStationsStName,@Param("totalId")String totalId);

	/**
	 *
	 * @param cStationsStName
	 * @param snRework
	 * @param cRecipesMaterialName
	 * @param cStationsStName
	 */
	@Insert(" insert into p_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,"
			+ "keypart_name,keypart_rework_fg,keypart_rework_st)"
			+ "values(now(),1,0,#{cStationsStName},#{snRework},"
			+ "#{cRecipesMaterialName},'1',#{cStationsStName})")
	public void inserts1(@Param("cStationsStName")String cStationsStName,@Param("snRework")String snRework,@Param("cRecipesMaterialName")String cRecipesMaterialName);

	/**
	 *
	 * @param cStationsStName
	 * @param snRework
	 * @param cRecipesMaterialName
	 */
	@Insert(" insert into p_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,"
			+ "keypart_name,keypart_rework_fg,keypart_rework_st)"
			+ "values(now(),1,0,#{cStationsStName},#{snRework},"
			+ "#{cRecipesMaterialName},'1',#{cStationsStName})")
	public void inserts2(@Param("cStationsStName")String cStationsStName,@Param("snRework")String snRework,@Param("cRecipesMaterialName")String cRecipesMaterialName);

	/**
	 *
	 * @param cStationsStName
	 * @param snRework
	 * @param cRecipesMaterialName
	 */
	@Insert(" insert into p_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,"
			+ "keypart_name,keypart_rework_fg,keypart_rework_st)"
			+ "values(now(),1,0,#{cStationsStName},#{snRework},"
			+ "#{cRecipesMaterialName},'1',#{cStationsStName})")
	public void inserts3(@Param("cStationsStName")String cStationsStName,@Param("snRework")String snRework,@Param("cRecipesMaterialName")String cRecipesMaterialName);

	/**
	 *
	 * @param cStationsStName
	 * @param snRework
	 * @param cRecipesMaterialName
	 */
	@Insert(" insert into p_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,"
			+ "keypart_name,keypart_rework_fg,keypart_rework_st)"
			+ "values(now(),1,0,#{cStationsStName},#{snRework},"
			+ "#{cRecipesMaterialName},'1',#{cStationsStName})")
	public void inserts4(@Param("cStationsStName")String cStationsStName,@Param("snRework")String snRework,@Param("cRecipesMaterialName")String cRecipesMaterialName);

	/**
	 *
	 * @param cStationsStName
	 * @param snRework
	 * @param cRecipesMaterialName
	 */
	@Insert(" insert into p_mes_keypart_t ("
			+ "dt,transfer,keypart_mode,st,sn,"
			+ "keypart_name,keypart_rework_fg,keypart_rework_st)"
			+ "values(now(),1,0,#{cStationsStName},#{snRework},"
			+ "#{cRecipesMaterialName},'1',#{cStationsStName})")
	public void inserts5(@Param("cStationsStName")String cStationsStName,@Param("snRework")String snRework,@Param("cRecipesMaterialName")String cRecipesMaterialName);

	/**
	 *
	 * @param snRework
	 * @param cStationsStName
	 * @param cRecipesTLimit
	 * @param cRecipesALimit
	 * @param tempParamartersName
	 */
	@Insert("insert into p_mes_bolt_t("
			+ "dt,transfer,bolt_mode,sn,st,t_limit,"
			+ "a_limit,bolt_name,rework_flag,rework_st)"
			+ "values(now(),1,0,#{snRework},#{cStationsStName},"
			+ "#{cRecipesTLimit},#{cRecipesALimit},#{tempParamartersName},'1',#{cStationsStName})")
	public void inserts6(@Param("snRework")String snRework,@Param("cStationsStName")String cStationsStName,@Param("cRecipesTLimit")String cRecipesTLimit,
			@Param("cRecipesALimit")String cRecipesALimit,@Param("tempParamartersName")String tempParamartersName);


	/**
	 *
	 * @param cStationsStName
	 * @param snRework
	 * @param cRecipesMaterialName
	 */
	@Insert("insert into p_mes_leakage_t("
			+ "dt,st,sn,leakage_name,transfer,"
			+ "leakage_mode，rework_flag)"
			+ "values(now(),#{cStationsStName},"
			+ "#{snRework},#{cRecipesMaterialName},1,0,'1')")
	public void inserts7(@Param("cStationsStName")String cStationsStName,@Param("snRework")String snRework,@Param("cRecipesMaterialName")String cRecipesMaterialName);

	@Select("select count(*) from r_mes_keypart_t where ST=#{staName} and SN=#{sn}")
	public Integer findKeypart(@Param("staName")String staName,@Param("sn")String sn);

	@Select("select count(*) from r_mes_bolt_t where ST=#{staName} and SN=#{sn}")
	public Integer findBolt(@Param("staName")String staName,@Param("sn")String sn);

	@Select("select count(*) from p_mes_weigh_t where ST=#{staName} and SN=#{sn}")
	public Integer findWeight(@Param("staName")String staName,@Param("sn")String sn);

	@Select("select count(*) from r_mes_leakage_t where ST=#{staName} and SN=#{sn}")
	public Integer findleakage(@Param("staName")String staName,@Param("sn")String sn);

	@Update("update r_mes_keypart_t set KEYPART_NUM=null,WID=null where SN=#{sn} and ST=#{stationName}")
	//修改keypart表数据
	public void updateRKeypart(@Param("sn")String sn,@Param("stationName")String stationName);

	@Update("update r_mes_bolt_t set T=null,A=null,R=null,WID=null,Y=0 where SN=#{sn} and ST=#{stationName}")
	//修改bolt表数据
	public void updateRBolt(@Param("sn")String sn,@Param("stationName")String stationName);

	@Delete("delete from r_mes_bolt_t where where SN=#{sn} and ST=#{stationName} and R='NG' ")
	//删除NG的拧紧数据
	public void updateRBoltNg(@Param("sn")String sn,@Param("stationName")String stationName);

	@Update("update r_mes_leakage_t set LEAKAGE_PV=null,LEAKAGE_LV=null,LEAKAGE_R=null,WID=null where SN=#{sn} and ST=#{stationName}")
	//修改气密数据
	public void updateRLeakage(@Param("sn")String sn,@Param("stationName")String stationName);

	@Delete("delete from r_mes_keypart_t where SN=#{sn} and ST=#{stationName}")
	//修改keypart表数据
	public void deleteRKeypart(@Param("sn")String sn,@Param("stationName")String stationName);

	@Delete("delete from r_mes_bolt_t where SN=#{sn} and ST=#{stationName}")
	//修改bolt表数据
	public void deleteRBolt(@Param("sn")String sn,@Param("stationName")String stationName);

	@Delete("delete from r_mes_leakage_t where SN=#{sn} and ST=#{stationName}")
	//修改气密数据
	public void deleteRLeakage(@Param("sn")String sn,@Param("stationName")String stationName);

	@Select("select ID from c_mes_material_instance_t where MATERIAL_NAME=#{MATERIAL_NAME} and WEAR_STATE = 0 limit 1")
	// 获取一个未消耗的物料
	public Integer getUnUsedMaterial(@Param("MATERIAL_NAME")String MATERIAL_NAME);

	@Update("update c_mes_material_instance_t set WEAR_STATE=1,ALTER_DT=NOW() where ID=#{ID}")
	public void updateStateByID(@Param("ID")Integer ID);
}

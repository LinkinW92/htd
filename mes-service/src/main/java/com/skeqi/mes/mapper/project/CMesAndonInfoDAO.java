package com.skeqi.mes.mapper.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.project.InsertInfo;

@Component
@MapperScan
public interface CMesAndonInfoDAO {

	//生产计数列表
	@Select("<script>"
			+  "SELECT" +
			"	an.ID id," +
			"	an.COUNT_TYPE countType," +
			"	an.DT dt," +
			"	an.LINE_NAME lineName," +
			"	an.SN sn," +
			"	an.STATION_NAME stationName," +
			"	ifnull(pr.PRODUCTION_NAME,prs.PRODUCTION_NAME) proName," +
			"	ifnull(pr.PRODUCTION_TYPE,prs.PRODUCTION_TYPE) proType," +
			"	ifnull(pr.PRODUCTION_SERIES,prs.PRODUCTION_SERIES) series," +
			"	ifnull(pr.PRODUCTION_TRADEMARK,prs.PRODUCTION_TRADEMARK) trademark," +
			"	ifnull(pr.PRODUCTION_DISCRIPTION,prs.PRODUCTION_DISCRIPTION) dis," +
			"	an.PRODUCT_MARK productMark " +
			" FROM" +
			"	c_mes_andon_info_t an" +
			"	LEFT JOIN c_mes_production_t pr ON SUBSTRING_INDEX( an.sn, '|', 1 ) = pr.PRODUCTION_TYPE" +
			"	LEFT JOIN c_mes_production_t prs ON an.PRODUCT_MARK = prs.PRODUCTION_TYPE"
			+ "  WHERE 1=1  "
			+ " <if test=\"workId!='' and workId!=null \"> and an.WORK_ID=#{workId} </if>"
			+ " <if test=\"startDate!='' and startDate!=null   and endDate!=null   and endDate!='' \" >  and   an.DT    between  #{startDate} and  #{endDate}</if>"
			+ " <if test=\"lineName!='' and lineName!=null \"> and an.LINE_NAME=#{lineName} </if>"
			+ " <if test=\"stationName!='' and stationName!=null \"> and an.STATION_NAME=#{stationName} </if>"
			+ " <if test=\"sn!='' and sn!=null \"> and an.SN=#{sn} </if>"
			+ " ORDER BY an.dt desc </script>")
	public List<InsertInfo> findAllInfo(@Param("workId")Integer workId,@Param("lineName")String lineName,
	@Param("stationName")String stationName,@Param("sn")String sn,@Param("startDate")String startDate,@Param("endDate")String endDate);

	//删除生产计数
	@Delete("delete from c_mes_andon_info_t where ID=#{id}")
	public Integer delAndonInfo(Integer id);

	//修改生产计数
	public Integer updateAndonInfo(InsertInfo info);

	//添加手动计数
	@Insert("insert into c_mes_andon_info_t(LINE_NAME,STATION_NAME,DT,SN,PRODUCT_MARK,COUNT_TYPE,WORK_ID) "
			+ "values(#{lineName},#{stationName},#{dt},#{sn,jdbcType=VARCHAR},#{productMark},#{countType},#{workId})")
	public Integer insertInfo(InsertInfo info);


	//根据排班id查询产线名称
	@Select("SELECT line.NAME as lineName FROM c_mes_scheduling_t sche left JOIN c_mes_line_t line on sche.LINE_ID=line.ID where sche.ID=#{id}")
	public String findLineName(Integer id);

	//根据生产信息查询计划、排班、工单id
	@Select("SELECT  works.ID as workId,works.PLAN_ID as planId,works.SCHE_ID as scheId "
			+ " FROM  c_mes_andon_info_t info left join c_mes_workorder_t works on works.ID=info.WORK_ID"
			+ " where info.id=#{id}")
	public Map<String,Integer> findAllId(Integer id);

	//工单完成数量-1
	@Update("update c_mes_workorder_t set COMPLETE_NUMBER = COMPLETE_NUMBER-1  where id=#{id}")
	public Integer updateWorkComplete(Integer id);

	//计划完成数量-1
	@Update("update r_mes_plan_t set COMPLETE_NUMBER = COMPLETE_NUMBER-1 where id=#{id}")
	public Integer updatePlanComplete(Integer id);

	//排班完成数量-1
	@Update("update c_mes_scheduling_t set ACTUAl_NUMBER = ACTUAl_NUMBER-1  where id=#{id}")
	public Integer updateScheComplete(Integer id);

    //根据产线ID查询产线名称
	@Select("select NAME from  c_mes_line_t where ID=#{lineId}")
	public String findByLineName(@Param("lineId")String lineId);

	//查询sn是否存在
	@Select("select count(*)  from c_mes_andon_info_t  where SN=#{sn}   ")
	public Integer findWorkBySn(@Param("sn")String sn);


}

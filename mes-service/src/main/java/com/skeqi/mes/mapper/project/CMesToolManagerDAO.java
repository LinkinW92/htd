package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.project.CMesToolManager;

@Component
@MapperScan
public interface CMesToolManagerDAO {


	@Select("<script>" +
			"select tool.*,line.NAME as lineName,sta.STATION_NAME as stationName from c_mes_tool_manage_t tool,c_mes_line_t line,c_mes_station_t sta where tool.LINE_ID=line.ID and sta.ID=STATION_ID"+
			  "<if test=\"name!='' and name!=null \"> and TOOL_NAME=#{name} </if>"
			  + "		ORDER BY tool.dt DESC    </script>")
	public List<CMesToolManager> findAllTool(@Param("name")String name);


//	@Insert("insert into c_mes_tool_manage_t(DT,TOOL_NO,TOOL_NAME,TOOL_DIS,ESTIMATE_LIFE,SURPLUS_LIFE,USEFUL_LIFE,MAINTAIN_CYCLE,"
//			+ "NEXT_MAINTAIN,SURPLUS_MAINTAIN,FIRST_USE,LINE_ID,STATION_ID)"
//			+ "values(now(),#{toolNo},#{toolName},#{toolDis},#{estimateLife},0,0,#{maintainCycle},date_add(now(), interval #{maintainCycle} day),"
//			+ "#{surplusMaintain},#{firstUse},#{lineId},#{stationId})")
	@Insert("insert into c_mes_tool_manage_t(DT,TOOL_NO,TOOL_NAME,TOOL_DIS,LINE_ID,STATION_ID) "
			+ "values(now(),#{toolNo},#{toolName},#{toolDis},#{lineId},#{stationId})")
	public Integer addTool(CMesToolManager tool);


//	@Update("update c_mes_tool_manage_t set TOOL_NO=#{toolNo},TOOL_NAME=#{toolName},TOOL_DIS=#{toolDis},ESTIMATE_LIFE=#{estimateLife},LINE_ID=#{lineId},STATION_ID=#{stationId}"
//			+ "MAINTAIN_CYCLE=#{maintainCycle},SURPLUS_LIFE=#{estimateLife}-USEFUL_LIFE,NEXT_MAINTAIN=date_add(now(), interval #{maintainCycle} day) where ID=#{id}")
	@Update("update c_mes_tool_manage_t set TOOL_NO=#{toolNo},TOOL_NAME=#{toolName},TOOL_DIS=#{toolDis},LINE_ID=#{lineId},STATION_ID=#{stationId} where ID=#{id} ")
	public Integer updateTool(CMesToolManager tool);


	@Update("update c_mes_tool_manage_t set LAST_MAINTAIN=now(),NEXT_MAINTAIN=date_add(now(), interval MAINTAIN_CYCLE day) where Id=#{id}; ")
	public Integer maintain(Integer id);


	@Delete("delete from c_mes_tool_manage_t where ID=#{id}")
	public Integer delTool(Integer id);

	@Update("update  c_mes_tool_manage_t set SURPLUS_LIFE=ESTIMATE_LIFE-USEFUL_LIFE-#{num},USEFUL_LIFE=USEFUL_LIFE+#{num} where ID=#{id}")
	public Integer UseTool(@Param("id")Integer id,@Param("num")Integer num);

	@Select("select * from c_mes_station_t where LINE_ID=#{lineId}")
	public List<CMesStationT> findStation(Integer lineId);

	@Select("select count(*) from c_mes_tool_manage_t where TOOL_NO=#{deviceNo}")
	public Integer findDeviceNo(@Param("deviceNo")String deviceNo);

	@Select("select count(*) from c_mes_tool_manage_t where TOOL_NO=#{deviceNo} and Id!=#{id}")
	public Integer findDeviceNoById(@Param("deviceNo")String deviceNo,@Param("id")Integer id);
}

package com.skeqi.mes.mapper.project;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.project.CMesToolManager;

@MapperScan
@Component
public interface DeviceDAO {

	//根据编号查询设备表
	@Select("select tool.*,line.NAME as lineName,sta.STATION_NAME as stationName from c_mes_tool_manage_t tool,c_mes_line_t line,c_mes_station_t sta"
			+ " where tool.LINE_ID=line.ID and sta.ID=tool.STATION_ID and tool.TOOL_NO=#{name}")
	public CMesToolManager findTool(@Param("name")String name);

	//添加到故障表
	@Insert("insert into c_mes_andon_fault_t(LINE_NAME,STATION_NAME,STATUS,TOOL_IDS,FAULT_TYPE,DT) values(#{lineName},#{staName},#{status},#{toolId},1,#{time})")
	public Integer insertAndonFault(@Param("lineName")String lineName,@Param("staName")String staName,@Param("status")Integer status,@Param("toolId")Integer toolId,@Param("time")String time);

	@Update("update c_mes_andon_fault_t set STATUS=#{status},DT=now() where ID=#{id}")
	public Integer updateAndonFault(@Param("id")Integer id,@Param("status")Integer status);


	//根据设备id查询该设备是否存在
	@Select("select  id  from c_mes_andon_fault_t where TOOL_IDS=#{id} and status=0")
	public Integer  findFaultByTid(Integer id);

	//查询此故障是否被响应
	@Select("select DT from c_mes_andon_fault_t where id = (select max(id) from c_mes_andon_fault_t where TOOL_IDS=#{id} and status=1)")
	public String findDt(Integer id);
}

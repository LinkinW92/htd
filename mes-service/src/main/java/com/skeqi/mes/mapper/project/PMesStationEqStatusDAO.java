package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.project.PMesStationEqStatusT;

@MapperScan
@Component
public interface PMesStationEqStatusDAO {


	@Select("<script>"
			+ "SELECT eq.*,line.NAME AS lineName,sta.STATION_NAME AS stationName FROM"
			+ " p_mes_station_eq_status_t eq LEFT JOIN c_mes_line_t line ON eq.LINE_ID = line.ID"
			+ " LEFT JOIN c_mes_station_t sta ON sta.ID = eq.ST where 1=1"
			+"<if test=\"name!='' and name!=null \"> and eq.NAME=#{name} </if>"
		    + "</script>")
	public List<PMesStationEqStatusT> findAllEq(@Param("name")String name);


	@Insert("insert into p_mes_station_eq_status_t(DT,ST,EQ_NAME,EQ_STATUS_TYPE,RESION,LINE_ID)"
			+ "values(now(),#{st},#{eqName},#{eqStatusType},#{resion},#{lineId})")
	public Integer addEq(PMesStationEqStatusT eq);


	@Update("update p_mes_station_eq_status_t set ST=#{st},EQ_NAME=#{eqName},EMP=#{emp},EQ_STATUS_TYPE=#{eqStatusType},RESION=#{resion},LINE_ID=#{lineId} where ID=#{id}")
	public Integer udpateEq(PMesStationEqStatusT eq);


	@Delete("delete from  p_mes_station_eq_status_t where ID=#{id}")
	public Integer delEq(Integer id);
}

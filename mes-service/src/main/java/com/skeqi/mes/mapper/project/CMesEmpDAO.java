package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesStationT;

@Component
@MapperScan
public interface CMesEmpDAO {

	@Select("<script>" +
			"SELECT emp.*,team.`NAME` AS teamName,LINE.`NAME` AS lineName,emp.STATION_ID AS stationId,etype.EMP_TYPE as empTypeName FROM "
			+ "c_mes_emp_t emp LEFT JOIN c_mes_team_t team ON emp.EMP_TEAM_ID = team.ID "
			+ "LEFT JOIN c_mes_line_t line ON line.ID = emp.LINE_ID "
			+ "LEFT JOIN c_mes_emp_type_t etype  ON etype.ID = emp.EMP_TYPE "
			+ "WHERE 1 =1"+
			 "<if test=\"name!='' and name!=null \"> and EMP_NAME = #{name} </if>"
			  + "</script>")
	public List<CMesEmpT> findAllEmp(@Param("name")String name);


	@Insert("insert into c_mes_emp_t(DT,EMP_NO,EMP_NAME,EMP_SEX,EMP_TYPE,EMP_TP,EMP_DEPARTMENT,EMP_TEAM_ID,LINE_ID,STATION_ID,IS_WHOLE,password) "
			+ "values(now(),#{empNo},#{empName},#{empSex},#{empType},#{empTp},#{empDepartment},#{empTeamId},#{lineId},#{stationId},#{isWhole},#{password}) ")
	public Integer addEmp(CMesEmpT emp);


	@Update("update c_mes_emp_t set EMP_NO=#{empNo},EMP_NAME=#{empName},EMP_SEX=#{empSex},EMP_TYPE=#{empType},EMP_TP=#{empTp},EMP_DEPARTMENT=#{empDepartment},"
			+ "EMP_TEAM_ID=#{empTeamId},LINE_ID=#{lineId},STATION_ID=#{stationId},IS_WHOLE=#{isWhole},password=#{password} where ID=#{id}")
	public Integer updateEmp(CMesEmpT emp);

	@Delete("delete from c_mes_emp_t where ID=#{id}")
	public Integer delEmp(Integer id);


	@Select("select max(id) from c_mes_emp_type_t where EMP_TYPE=#{name}")
	public Integer findEmpType(@Param("name")String name);

	@Select("select max(id) from c_mes_team_t where NAME=#{name}")
	public Integer findTeam(@Param("name")String name);

	@Select("select max(id) from  c_mes_line_t where NAME=#{name}")
	public Integer findLine(@Param("name")String name);

	@Select("select max(id) from c_mes_station_t where STATION_NAME=#{name}")
	public Integer findStataionId(@Param("name")String name);

	@Select("select * from c_mes_emp_type_t")
	public List<CMesEmpTypeT> findsEmpType();


	@Select("select * from c_mes_station_t")
	public List<CMesStationT> findStation();

	@Select("select id from c_mes_emp_type_t where EMP_TYPE=#{name}")
	public Integer findEmpTypeName(@Param("name")String name);

	@Select("select id from c_mes_team_t where NAME=#{name}")
	public Integer findTeamName(String name);

	@Select("select id from c_mes_station_t where STATION_NAME=#{name}")
	public Integer findStationName(String name);


	@Select("select count(*) from c_mes_emp_t where EMP_NO=#{empNos}")
	public Integer findEmpByName(@Param("empNos")String empNos);

	@Select("select STATION_NAME as stationName from c_mes_station_t where ID in (${stationId})")
	public List<CMesEmpT> findStationNameById(CMesEmpT cMesEmpT);
}

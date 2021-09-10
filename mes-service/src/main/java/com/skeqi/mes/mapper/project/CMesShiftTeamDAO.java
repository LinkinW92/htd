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

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
@Component
@MapperScan
public interface CMesShiftTeamDAO {

	//班次列表
	@Select("<script>" +
			"select team.*,line.NAME as lineName  from c_mes_shifts_team_t team,c_mes_line_t line where team.LINE_ID=line.ID and 1=1"+
			 "<if test=\"name!='' and name!=null \"> and team.NAME=#{name} </if>"
			 + "<if test=\"lineId!='' and lineId!=null \"> and team.LINE_ID=#{lineId} </if>"
			  + "</script>")
	public List<CMesShiftsTeamT> findAllShift(Map<String, Object> map);

	//添加
	@Insert("insert into c_mes_shifts_team_t(NAME,START_TIME,END_TIME,DIS,LINE_ID,PLAN_TIME,JUMP_TIME)  values(#{name},#{startTime},#{endTime},#{dis},#{lineId},#{planTime},#{jumpTime})")
	public Integer addShift(CMesShiftsTeamT shift);

	//删除
	@Delete("delete from c_mes_shifts_team_t where id=#{id}")
	public Integer delShift(@Param("id")Integer id);


	//修改
	@Update("update c_mes_shifts_team_t set NAME=#{name},START_TIME=#{startTime},END_TIME=#{endTime},LINE_ID=#{lineId},DIS=#{dis},PLAN_TIME=#{planTime},JUMP_TIME=#{jumpTime} where id=#{id}")
	public Integer updateShift(CMesShiftsTeamT shift);


	@Select("select id,NAME from c_mes_line_t  ")
	public List<CMesLineT> findAllLine();

	@Select("<script>" +
			"select id,NAME from c_mes_line_t   where 1=1"
			+ "<if test=\" paibanStatus!=null \"> and PAIBAN_STATUS=#{paibanStatus} </if>"
			+ "<if test=\" andengStatus!=null \"> and ANDENG_STATUS=#{andengStatus} </if>"
			 + " order by ID"+
			   "</script>")
	public List<CMesLineT> findAllLinel(@Param("paibanStatus")Integer paibanStatus,@Param("andengStatus") Integer andengStatus);


	@Select("select id from c_mes_line_t where NAME=#{name}")
	public Integer findLineName(@Param("name")String name);


	//根据班次名查询班次
	@Select("select count(*)   from c_mes_shifts_team_t where NAME=#{name}  and LINE_ID=#{lineId}")
	public Integer findByName(@Param("name")String name,@Param("lineId") Integer lineId);

	//根据产线名称查询产线ID
	@Select("select id from c_mes_line_t where NAME=#{lineName}")
	public Integer findByLineName(String lineName);

	//查询当前班次存在的产线
	@Select("SELECT  line.name AS name  FROM 	c_mes_shifts_team_t shift 	LEFT JOIN c_mes_line_t line 	ON line.id = shift.LINE_ID")
	public List<CMesLineT> findShiftLineName();
}

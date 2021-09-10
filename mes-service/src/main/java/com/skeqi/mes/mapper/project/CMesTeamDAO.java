package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.CMesTeamT;

@Component
@MapperScan
public interface CMesTeamDAO {

	//班组列表
	@Select("<script>" +
			"select t.*,(select NAME from c_mes_shifts_team_t s where s.ID=t.SHIFT_ID) as shiftName  from c_mes_team_t t where 1 = 1"+
			 "<if test=\"name!='' and name!=null \"> and NAME=#{name} </if>"
			  + "</script>")
	public List<CMesTeamT> findAllTeam(@Param("name")String name);

	@Insert("insert into c_mes_team_t(NAME,DIS,SHIFT_ID) values(#{name},#{dis},#{shiftId})")
	public Integer addTeam(CMesTeamT team);


	@Update("update c_mes_team_t set NAME=#{name},DIS=#{dis},SHIFT_ID=#{shiftId} where ID=#{id}")
	public Integer updateTeam(CMesTeamT team);


	@Delete("delete from c_mes_team_t where ID=#{id}")
	public Integer delTeam(@Param("id")Integer id);

	//根据班组名查询是否有班组
	@Select("select count(*) from  c_mes_team_t where NAME=#{name}")
	public Integer findByName(@Param("name")String name);
}

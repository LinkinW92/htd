package com.skeqi.mes.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesTeamT;

public interface CMesTeamService {

	public List<CMesTeamT> findAllTeam(@Param("name")String name) throws ServicesException;

	public Integer addTeam(CMesTeamT team) throws ServicesException;


	public Integer updateTeam(CMesTeamT team) throws ServicesException;


	public Integer delTeam(@Param("Integer id")Integer id) throws ServicesException;

	public Integer findByName(String name);
}

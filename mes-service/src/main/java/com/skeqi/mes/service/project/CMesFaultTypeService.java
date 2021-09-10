package com.skeqi.mes.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.project.CMesFaultTypeT;

public interface CMesFaultTypeService {
	public List<CMesFaultTypeT> findAllFault(@Param("name")String name) throws ServicesException;


	public Integer addFault(@Param("name")String name,@Param("note")String note) throws ServicesException;

	public Integer updateFault(@Param("name")String name,@Param("note")String note,@Param("id")Integer id) throws ServicesException;

	public Integer delFault(@Param("id")Integer id) throws ServicesException;
}

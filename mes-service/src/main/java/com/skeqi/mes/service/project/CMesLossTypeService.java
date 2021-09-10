package com.skeqi.mes.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.project.CMesLossReasonT;
import com.skeqi.mes.pojo.project.CMesLossTypeT;

public interface CMesLossTypeService {

	public List<CMesLossTypeT> findAllLoss(@Param("name")String name) throws ServicesException;


	public Integer addLoss(@Param("name")String name,@Param("note")String note) throws ServicesException;



	public Integer updateLoss(@Param("name")String name,@Param("note")String note,@Param("id")Integer id) throws ServicesException;


	public Integer delLoss(@Param("id")Integer id) throws ServicesException;


	public List<CMesLossReasonT> findReason() throws ServicesException;


	public Integer addReason(@Param("reasonNo")String reasonNo,@Param("lossId")Integer lossId,@Param("name")String name,@Param("note")String note) throws ServicesException;


	public Integer updateReason(@Param("reasonNo")String reasonNo,@Param("lossId")Integer lossId,@Param("name")String name,@Param("note")String note,@Param("id")Integer id) throws ServicesException;


	public Integer delReason(Integer id) throws ServicesException;

	public String findReasonNameByNO(String id) throws ServicesException;
}

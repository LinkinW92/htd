package com.skeqi.mes.service.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.RMesPlanT;

public interface CMesAndonPlanService {

	public List<RMesPlanT> findAndonPlan(@Param("name") String name, String lineName, String systemDate) throws ServicesException;

	public List<RMesPlanT> findcompleteAndonPlan(@Param("name") String name, String lineName, String systemDate);

	public Integer addAndonPlan(RMesPlanT plan) throws ServicesException;

	public Integer addAndonImportPlan(RMesPlanT plan) throws ServicesException;

	public Integer updateAndonPlan(RMesPlanT plan) throws ServicesException;

	public Integer delAndonPlan(Integer id) throws ServicesException;

	public Integer updateStatus(@Param("id") Integer id, @Param("status") Integer status) throws ServicesException;

	public Integer findLineByName(@Param("name") String name);

	public CMesProductionT findProByName(@Param("name") String name);

	public List<String> findProducMark(Integer id) throws ServicesException;

	public Integer findByName(String name);

	public Integer updateStatusl(RMesPlanT plan);

	public Integer findByNamel(String planName, Integer id);

	public String findByLineId(Integer lineId);

	public CMesLineT findLineByProType(Integer lineId);

	public Integer findLineDtByPro(Integer lineId, String dt, Integer pid);

	public JSONObject findKanBanYield(String lineName) throws Exception;
}

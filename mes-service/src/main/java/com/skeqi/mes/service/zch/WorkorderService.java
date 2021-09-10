package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.util.Rjson;

public interface WorkorderService {

	List<Map<String, Object>> findWorkorderList(Map<String, Object> map);

	List<Map<String, Object>> findPWorkorderList(Map<String, Object> map);

	Integer addWorkorder(Map<String, Object> map) throws Exception;

	Integer updateWorkorder(Map<String, Object> map) throws Exception;

	Integer deleteWorkorder(Map<String, Object> map) throws Exception;

	Map<String, Object> getByID(Map<String, Object> map);

	List<Map<String, Object>> findWorkorderByIdOrWID(Map<String, Object> map);

	Integer getCountByWorkorderId(Map<String, Object> map);

	//转移P表
	void moveWork(Integer id);

	//查询工单条码
	List<Map<String,Object>> findWorkSn(Integer id);

	List<Map<String, Object>> findListAll();

	Rjson splitWorkorder(Map<String, Object> map);

	Integer jointWorkorder(Map<String, Object> map);

	Integer updateRecipe(Map<String, Object> map);

	List<Map<String, Object>> findRecipeList(Map<String, Object> map);
}

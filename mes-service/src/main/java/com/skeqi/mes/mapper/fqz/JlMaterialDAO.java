package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesJlMaterialT;

public interface JlMaterialDAO {

	public List<CMesJlMaterialT> findAll(Map<String,Object> map);

	public void insertJlMaterial(CMesJlMaterialT c);

	public void delJlMaterial(Integer id);

	public CMesJlMaterialT findByid(Integer id);

	public void editJlMaterial(CMesJlMaterialT c);

	public String findBomId(@Param("name")String name);
}

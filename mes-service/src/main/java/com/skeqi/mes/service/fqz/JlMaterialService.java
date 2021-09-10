package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesJlMaterialT;

public interface JlMaterialService {

	public List<CMesJlMaterialT> findAll(Map<String,Object> map);

	public void insertJlMaterial(CMesJlMaterialT c);

	public void delJlMaterial(Integer id);

	public CMesJlMaterialT findByid(Integer id);

	public void editJlMaterial(CMesJlMaterialT c);

	public String findBomId(String name);
}

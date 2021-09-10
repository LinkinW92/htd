package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesMaterialT;

public interface IQCCheckService {

	public List<CMesIqcCheckT> findAll(Map<String,Object> map);

	public void insertIQC(CMesIqcCheckT c);

	public CMesIqcCheckT findByid(String id);
	public void updateIQC(CMesIqcCheckT c);

	//删除
	public void deleteIQC(String id);

	public void fuhe(String id,String name);

	public List<CMesMaterialT> findMaterial();

	public void freezes(String id,String status);
}

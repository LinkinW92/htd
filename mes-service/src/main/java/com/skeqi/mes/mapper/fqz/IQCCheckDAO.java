package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesMaterialT;

public interface IQCCheckDAO {

	//查询列表
	public List<CMesIqcCheckT> findAll(Map<String,Object> map);

	//添加
	public void insertIQC(CMesIqcCheckT c);

	//修改
	public void updateIQC(CMesIqcCheckT c);

	//删除
	public void deleteIQC(String id);

	//根据id获取数据
	public CMesIqcCheckT findByid(String id);

	public void fuhe(@Param("id")String id,@Param("name")String name);

	//查询物料列表
	public List<CMesMaterialT> findMaterial();

	//冻结、解冻
	public void freezes(@Param("id")String id,@Param("status")String status);
}

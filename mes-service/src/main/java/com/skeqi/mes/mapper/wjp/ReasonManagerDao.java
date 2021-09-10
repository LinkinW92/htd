package com.skeqi.mes.mapper.wjp;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesDefectResionT;

public interface ReasonManagerDao {

	//原因列表
	List<CMesDefectResionT> findAll();

	//原因新增
	@SuppressWarnings("rawtypes")
	void addReason(Map map);

	//删除原因
	void removeReason(int id);

	//根据Id查询
	CMesDefectResionT findById(int id);

	//编号不能重复
	int noRepeat(String string);

	//修改原因
	@SuppressWarnings("rawtypes")
	void updateReason(Map map);

}

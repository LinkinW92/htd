package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesLabelType;

public interface TechnologyService {

	//规则类型列表
	public List<CMesLabelType> findAllLabelType(CMesLabelType c) throws ServicesException;

	//根据id查询标签类型列表
	public CMesLabelType findLabelTypeByid(Integer id) throws ServicesException;

	//添加标签类型
	public Integer addLabelType(CMesLabelType c) throws ServicesException;

	//修改标签类型
	public Integer updateLabelType(CMesLabelType c) throws ServicesException;

	//删除标签类型
	public Integer delLabelType(Integer id) throws ServicesException;

	//标签列表
	public List<CMesLabelManagerT> findAllLabelManager(CMesLabelManagerT c) throws ServicesException;

	//根据id查询标签列表
	public CMesLabelManagerT findLabelManagerByid(Integer id) throws ServicesException;

	//添加标签
	public Integer addLabelManager(CMesLabelManagerT c) throws ServicesException;

	//修改标签
	public Integer updateLabelManager(CMesLabelManagerT c) throws ServicesException;

	//删除标签
	public Integer delLabelManager(Integer id) throws ServicesException;
}

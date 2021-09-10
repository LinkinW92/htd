package com.skeqi.mes.mapper.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesPlanTypeT;

public interface CMesPlanTypeDao {
	// 查询所有计划类型的表
	public List<CMesPlanTypeT> findAllPlanType();

	// 添加计划类型
	public void addPlanType(CMesPlanTypeT planType);

	// 修改此表展示的列
	public void updatePlanType(CMesPlanTypeT planType);

	// 删除
	public void deletePlanType(@Param("id") Integer id);

	// 根据id查询
	public CMesPlanTypeT findPlanTypeById(@Param("id") Integer id);
}

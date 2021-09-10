package com.skeqi.mes.service.qh;

import java.util.List;

import com.skeqi.mes.pojo.CMesPlanTypeT;

public interface CMesPlanTypeService {
		//查询所有计划类型的表
		public List<CMesPlanTypeT> findAllPlanType();

		//添加计划类型
		public void addPlanType(CMesPlanTypeT planType);

		//修改此表展示的列
		public void updatePlanType(CMesPlanTypeT planType);

		// 删除
		public void deletePlanType(Integer id);

		// 根据id查询
		public CMesPlanTypeT findPlanTypeById(Integer id);

}

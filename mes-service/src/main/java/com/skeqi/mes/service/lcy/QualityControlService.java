package com.skeqi.mes.service.lcy;

import java.util.List;

import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;

public interface QualityControlService {
	/**
	 *
	 * 缺陷管理
	 */

	//删除
	public void removeDefectManager(int id);
	//查询
	public CMesDefectManager findDefectManager(int id);
	//修改
	public void updataDefectManager(Integer id, String getDate, String defectId, String defectName, Integer defectGrade);
	//查询列表
	public List<CMesDefectManager> queryDefectManagerList();
	//添加
	public void addDefectManager(String getDate, Integer defectGrade, String defectId, String defectName,
			String defectDis);
	//得到缺陷管理的初始化下拉选
	public List<CMesDefectGradeManagerT> getInitDefectGradeManagerList();
	//查找相同的缺陷管理
	public Integer findEqualDefectManager(String defectId, Integer id);

	/**
	 *
	 * 缺陷等级管理
	 */
	//获取缺陷管理中的使用的数量
	public int getDefectManagerNumber(Integer id);
	//删除
	public void removeDefectGradeManager(int id);
	//查询
	public CMesDefectGradeManagerT findDefectGradeManagerById(int id);
	//修改
	public void updataDefectGradeManager(int id, String defectGradeId, String defectGradeName, String getDate);
	//添加
	public void addDefectGradeManager(String defectGradeId, String defectGradeName, String defectGradeDis,
			String getDate);
	//查询列表
	public List<CMesDefectGradeManagerT> queryDefectGradeManagerList();
	//查找相同的缺陷等级管理
	public Integer findEqualDefectGradeManager(String defectGradeId, Integer id);
	//删除所有缺陷管理的关于这个id的数据
	public void removeAllDefectManager(Integer id);
	/**
	 * 责任类型管理
	 * @return
	 */
	//责任类型管理的列表
	public List<CMesDutyTypeManagerT> queryDutyTypeManagerList();
	//删除
	public void removeDutyTypeManager(Integer id);
	//查询
	public CMesDutyTypeManagerT findDutyTypeManagerById(Integer id);
	//查找相同的责任类型
	public Integer findEqualDutyTypeManager(String dutyTypeId, Integer id);
	//修改责任类型数据
	public void updataDutyTypeManager(Integer id, String dutyTypeId, String dutyTypeName, String getDate);
	//添加责任类型数据
	public void addDutyTypeManager(String getDate, String dutyTypeId, String dutyTypeName, String dutyTypeDis);
	//获取责任管理中的数量
	public int getDutyManagerNumber(Integer id);
	//删除所有关于这个id的数据
	public void removeAllDutyManager(Integer id);

	/**
	 * 责任管理
	 * @return
	 */
	//获取初始化责任类型管理
	public List<CMesDutyTypeManagerT> getInitDutyTypeManagerList();
	//查询责任管理列表
	public List<CMesDutyManagerT> queryDutyManagerList();
	//查询
	public CMesDutyManagerT findDutyManagerById(Integer id);
	//查找相同的责任管理编号
	public Integer findEqualDutyManager(String dutyId, Integer id);
	//修改
	public void updataDutyManager(Integer id, String getDate, String dutyId, String dutyName, Integer dutyType);
	//添加
	public void addDutyManager(String getDate, String dutyId, String dutyName, String dutyDis, Integer dutyType);
	//删除
	public void removeDutyManager(Integer id);
	//查询使用缺陷管理的数据
	public int queryUseDefectManagerNumber(int parseInt);
	//查询使用责任管理的数据
	public int queryUseDutyManagerNumber(int parseInt);

	public void updataAllDutyManager(Integer id);

	public void updataAllDefectManager(Integer id);








}

package com.skeqi.mes.service.lcy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lcy.QualityControlMapper;
import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;

@Service
public class QualityControlServiceImpl implements QualityControlService{

	@Autowired
	private QualityControlMapper qcm;

	/**
	 *
	 * 缺陷管理
	 */
	//添加
	@Override
	public void addDefectManager(String getDate,Integer defectGrade, String defectId, String defectName,
			String defectDis) {
		qcm.addDefectManager(getDate,defectGrade,defectId,defectName,defectDis);

	}
	//移除
	@Override
	public void removeDefectManager(int id) {
		 qcm.removeDefectManager(id);

	}
	//查询
	@Override
	public CMesDefectManager findDefectManager(int id) {
		// TODO Auto-generated method stub
		return qcm.findDefectManager(id);
	}
	//更新
	@Override
	public void updataDefectManager(Integer id, String getDate, String defectId, String defectName,Integer defectGrade) {
		// TODO Auto-generated method stub
		qcm.updataDefectManager(id,getDate,defectId,defectName,defectGrade);
	}
	//查询列表
	@Override
	public List<CMesDefectManager> queryDefectManagerList() {
		// TODO Auto-generated method stub
		return qcm.queryDefectManagerList();
	}
	//得到初始化下拉选的列
	@Override
	public List<CMesDefectGradeManagerT> getInitDefectGradeManagerList() {
		// TODO Auto-generated method stub
		return  qcm.getInitDefectGradeManagerList();
	}
	//查找相同的缺陷管理
	@Override
	public Integer findEqualDefectManager(String defectId, Integer id) {
		// TODO Auto-generated method stub
		return qcm.findEqualDefectManager(defectId,id);
	}



	/**
	 *
	 * 缺陷等级管理
	 */
	//判断缺陷管理是否有这个的引用
	@Override
	public int getDefectManagerNumber(Integer id) {
		// TODO Auto-generated method stub
		return qcm.getDefectManagerNumber(id);
	}
	//删除关于这个id 的所有数据
	@Override
	public void removeAllDefectManager(Integer id) {
		// TODO Auto-generated method stub
		qcm.removeAllDefectManager(id);
	}


	//移除
	@Override
	public void removeDefectGradeManager(int id) {
		qcm.removeDefectGradeManager(id);

	}
	//通过id查询
	@Override
	public CMesDefectGradeManagerT findDefectGradeManagerById(int id) {
		// TODO Auto-generated method stub
		return qcm.findDefectGradeManagerById(id);
	}
	//修改
	@Override
	public void updataDefectGradeManager(int id, String defectGradeId, String defectGradeName, String getDate) {
		// TODO Auto-generated method stub
		qcm.updataDefectGradeManager(id,defectGradeId,defectGradeName,getDate);
	}
	//添加
	@Override
	public void addDefectGradeManager(String defectGradeId, String defectGradeName, String defectGradeDis,
			String getDate) {
		// TODO Auto-generated method stub
		qcm.addDefectGradeManager(defectGradeId,defectGradeName,defectGradeDis,getDate);
	}
	//查询列表
	@Override
	public List<CMesDefectGradeManagerT> queryDefectGradeManagerList() {
		// TODO Auto-generated method stub
		return qcm.queryDefectGradeManagerList();
	}
	//查找相同的缺陷等级管理
	@Override
	public Integer findEqualDefectGradeManager(String defectGradeId, Integer id) {
		// TODO Auto-generated method stub
		return qcm.findEqualDefectGradeManager(defectGradeId,id);
	}





	/**
	 * 责任类型管理
	 */
	//获取责任管理中使用的数量
	@Override
	public int getDutyManagerNumber(Integer id) {
		// TODO Auto-generated method stub
		return qcm.getDutyManagerNumber(id);
	}
	//删除所有关于这个id的数据
	@Override
	public void removeAllDutyManager(Integer id) {
		// TODO Auto-generated method stub
		qcm.removeAllDutyManager(id);
	}

	@Override
	public List<CMesDutyTypeManagerT> queryDutyTypeManagerList() {
		// TODO Auto-generated method stub
		return qcm.queryDutyTypeManagerList();
	}
	//移除
	@Override
	public void removeDutyTypeManager(Integer id) {
		// TODO Auto-generated method stub
		qcm.removeDutyTypeManager(id);
	}
	//查找
	@Override
	public CMesDutyTypeManagerT findDutyTypeManagerById(Integer id) {
		// TODO Auto-generated method stub
		return qcm.findDutyTypeManagerById(id);
	}
	//查找相同的责任类型
	@Override
	public Integer findEqualDutyTypeManager(String dutyTypeId, Integer id) {
		// TODO Auto-generated method stub
		return qcm.findEqualDutyTypeManager(dutyTypeId,id);
	}
	//修改
	@Override
	public void updataDutyTypeManager(Integer id, String dutyTypeId, String dutyTypeName, String getDate) {
		// TODO Auto-generated method stub
		qcm.updataDutyTypeManager(id, dutyTypeId,dutyTypeName,getDate);
	}
	//添加
	@Override
	public void addDutyTypeManager(String getDate, String dutyTypeId, String dutyTypeName, String dutyTypeDis) {
		// TODO Auto-generated method stub
		qcm.addDutyTypeManager(getDate,dutyTypeId,dutyTypeName,dutyTypeDis);
	}



	/**
	 * 责任管理
	 */
	//获取初始化责任类型
	@Override
	public List<CMesDutyTypeManagerT> getInitDutyTypeManagerList() {
		// TODO Auto-generated method stub
		return qcm.getInitDutyTypeManagerList();
	}
	//获取查询管理列表
	@Override
	public List<CMesDutyManagerT> queryDutyManagerList() {
		// TODO Auto-generated method stub
		return qcm.queryDutyManagerList();
	}
	//查找责任管理
	@Override
	public CMesDutyManagerT findDutyManagerById(Integer id) {
		// TODO Auto-generated method stub
		return qcm.findDutyManagerById(id);
	}
	//查找相同的责任管理编号
	@Override
	public Integer findEqualDutyManager(String dutyId, Integer id) {
		// TODO Auto-generated method stub
		return qcm.findEqualDutyManager(dutyId,id);
	}
	//修改
	@Override
	public void updataDutyManager(Integer id, String getDate, String dutyId, String dutyName, Integer dutyType) {
		// TODO Auto-generated method stub
		qcm.updataDutyManager(id,getDate,dutyId,dutyName,dutyType);
	}
	//添加
	@Override
	public void addDutyManager(String getDate, String dutyId, String dutyName, String dutyDis, Integer dutyType) {
		// TODO Auto-generated method stub
		qcm.addDutyManager(getDate,dutyId,dutyName,dutyDis,dutyType);
	}
	//删除
	@Override
	public void removeDutyManager(Integer id) {
		// TODO Auto-generated method stub
		qcm.removeDutyManager(id);
	}
	@Override
	public int queryUseDefectManagerNumber(int parseInt) {
		// TODO Auto-generated method stub
		return qcm.queryUseDefectManagerNumber(parseInt);
	}
	@Override
	public int queryUseDutyManagerNumber(int parseInt) {
		// TODO Auto-generated method stub
		return qcm.queryUseDutyManagerNumber(parseInt);
	}
	@Override
	public void updataAllDutyManager(Integer id) {
		// TODO Auto-generated method stub
		 qcm.updataAllDutyManager(id);
	}
	@Override
	public void updataAllDefectManager(Integer id) {
		// TODO Auto-generated method stub
		qcm.updataAllDefectManager(id);
	}









}

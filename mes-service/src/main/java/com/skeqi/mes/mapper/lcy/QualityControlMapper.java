package com.skeqi.mes.mapper.lcy;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;

public interface QualityControlMapper {


	/**
	 *
	 * 缺陷管理
	 */
	//添加
	public void addDefectManager(@Param("getDate")String getDate, @Param("defectGrade")Integer defectGrade,
			@Param("defectId")String defectId, @Param("defectName")String defectName,
			@Param("defectDis")String defectDis);
	//移除
	public void removeDefectManager(@Param("id")int id);
	//查询
	public CMesDefectManager findDefectManager(@Param("id")int id);
	//修改
	public void updataDefectManager(@Param("id")Integer id, @Param("getDate")String getDate,
			@Param("defectId")String defectId,@Param("defectName")String defectName,@Param("defectGrade")Integer defectGrade);
	//查询列表
	public List<CMesDefectManager> queryDefectManagerList();

	//初始化下拉选
	public List<CMesDefectGradeManagerT> getInitDefectGradeManagerList();
	//查找相同的缺陷管理
	public Integer findEqualDefectManager(@Param("defectId")String defectId,@Param("id")Integer id);

	/**
	 *
	 * 缺陷等级管理
	 */
	//判断缺陷管理中是否有此使用
	public int getDefectManagerNumber(@Param("id")Integer id);
	//移除
	public void removeDefectGradeManager(@Param("id")int id);
	//添加
	public void addDefectGradeManager(@Param("defectGradeId")String defectGradeId, @Param("defectGradeName")String defectGradeName,
			@Param("defectGradeDis")String defectGradeDis,@Param("getDate")String getDate);
	//查询
	public CMesDefectGradeManagerT findDefectGradeManagerById(@Param("id")int id);
	//修改
	public void updataDefectGradeManager(@Param("id")int id, @Param("defectGradeId")String defectGradeId, @Param("defectGradeName")String defectGradeName, @Param("getDate")String getDate);
	//查询列表
	public List<CMesDefectGradeManagerT> queryDefectGradeManagerList();
	//查找相同的缺陷等级管理
	public Integer findEqualDefectGradeManager(@Param("defectGradeId")String defectGradeId, @Param("id")Integer id);


	/**
	 * 责任类型管理
	 * @return
	 */
	//删除关于这个id 的所有数据
	public void removeAllDefectManager(Integer id);
	//删除所有关于责任类型的数据
	public void removeAllDutyManager(Integer id);
	//查询列表
	public List<CMesDutyTypeManagerT> queryDutyTypeManagerList();
	//移除
	public void removeDutyTypeManager(@Param("id")Integer id);
	//查询
	public CMesDutyTypeManagerT findDutyTypeManagerById(@Param("id")Integer id);
	//查找相同的责任类型
	public Integer findEqualDutyTypeManager(@Param("dutyTypeId")String dutyTypeId, @Param("id")Integer id);
	//修改
	public void updataDutyTypeManager(@Param("id")Integer id, @Param("dutyTypeId")String dutyTypeId, @Param("dutyTypeName")String dutyTypeName, @Param("getDate")String getDate);
	//添加
	public void addDutyTypeManager(@Param("getDate")String getDate, @Param("dutyTypeId")String dutyTypeId, @Param("dutyTypeName")String dutyTypeName, @Param("dutyTypeDis")String dutyTypeDis);
	//获取责任类型的数量
	public int getDutyManagerNumber(@Param("id")Integer id);
	/**
	 * 责任类型
	 * @return
	 */
	//获取责任类型初始化下拉选
	public List<CMesDutyTypeManagerT> getInitDutyTypeManagerList();
	//查询责任管理列表
	public List<CMesDutyManagerT> queryDutyManagerList();
	//寻找责任管理
	public CMesDutyManagerT findDutyManagerById(@Param("id")Integer id);
	//寻找相同的责任管理编号
	public Integer findEqualDutyManager(@Param("dutyId")String dutyId, @Param("id")Integer id);
	//修改责任管理
	public void updataDutyManager(@Param("id")Integer id, @Param("getDate")String getDate, @Param("dutyId")String dutyId, @Param("dutyName")String dutyName, @Param("dutyType")Integer dutyType);
	//添加责任管理
	public void addDutyManager(@Param("getDate")String getDate, @Param("dutyId")String dutyId, @Param("dutyName")String dutyName, @Param("dutyDis")String dutyDis, @Param("dutyType")Integer dutyType);
	//删除
	public void removeDutyManager(@Param("id")Integer id);
	//查询使用缺陷管理的数据
	public int queryUseDefectManagerNumber(@Param("id")int id);
	//查询使用责任管理的数据
	public int queryUseDutyManagerNumber(@Param("id")int id);

	public void updataAllDutyManager(@Param("id")Integer id);

	public void updataAllDefectManager(@Param("id")Integer id);





}

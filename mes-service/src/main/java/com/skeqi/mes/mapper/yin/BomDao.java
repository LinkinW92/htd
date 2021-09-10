package com.skeqi.mes.mapper.yin;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.CMesBomDetailT;
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesLeakageInfoT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.CMesOtherDataT;
import com.skeqi.mes.pojo.CMesStationT;

public interface BomDao {
	/**
	 * Bom列表
	 */
	List<CMesBomT> bomList(Map<String, Object> map);
	/**
	 * 添加Bom
	 */
	void addBom(Map<String, Object> map);
	/**
	 * 删除Bom
	 */
	void delBom(Map<String, Object> map);
	/**
	 * 修改Bom
	 */
	void editBom(Map<String, Object> map);
	/**
	 * 物料信息
	 */
	List<CMesMaterialMsgT> materialMsgList(Map<String, Object> map);
	/**
	 * 添加物料
	 */
	void addMaterialMsg(Map<String, Object> map);
	/**
	 * 删除物料信息
	 */
	void delMaterialMsg(Map<String, Object> map);
	/**
	 * 修改物料信息
	 */
	void editMaterialMsg(Map<String, Object> map);
	/**
	 * 螺栓信息
	 */
	List<CMesBoltInfomationT> boltList(Map<String, Object> map);
	/**
	 * 添加螺栓
	 */
	void addBolt(Map<String, Object> map);
	/**
	 * 删除螺栓信息
	 */
	void delBolt(Map<String, Object> map);
	/**
	 * 修改螺栓信息
	 */
	void editBolt(Map<String, Object> map);
	/**
	 * 气密性信息
	 */
	List<CMesLeakageInfoT> leakageList(Map<String, Object> map);
	/**
	 * 添加气密性信息
	 */
	void addAirtightness(Map<String, Object> map);
	/**
	 * 修改气密性信息
	 */
	void editAirtightness(Map<String, Object> map);
	/**
	 * 删除气密性信息
	 */
	void delAirtightness(Map<String, Object> map);
	/**
	 * 其他信息
	 */
	List<CMesOtherDataT> otherDataList(Map<String, Object> map);
	/**
	 * 添加其他信息
	 */
	void addOtherData(Map<String, Object> map);
	/**
	 * 删除其他信息
	 */
	void delOtherData(Map<String, Object> map);
	/**
	 * 修改其他信息
	 */
	void editOtherData(Map<String, Object> map);
	/**
	 * BOM明细
	 */
	List<CMesBomDetailT> bomDetailList(Map<String, Object> map);
	/**
	 * 添加BOM明细
	 */
	void addBomDetails(Map<String, Object> map);
	/**
	 * 删除BOM明细
	 */
	void delBomDetails(Map<String, Object> map);

	void editBomDetails(Map<String, Object> map);

	int countBolt(Map<String, Object> map);
	int countBomDetailsByBomId(Map<String, Object> map);
	int countBomByName(Map<String, Object> map);
	int countBoltByNo(Map<String, Object> map);
	int countBoltByName(Map<String, Object> map);
	int countOtherByName(Map<String, Object> map);

	List<CMesLineT> findLine();
	List<CMesStationT> findStation();

}

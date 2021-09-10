package com.skeqi.mes.service.yin;

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

public interface BomService {
	List<CMesBomT> bomList(Map<String, Object> map);
	void addBom(Map<String, Object> map);
	void delBom(Map<String, Object> map);
	void editBom(Map<String, Object> map);
	List<CMesMaterialMsgT> materialMsgList(Map<String, Object> map);
	void addMaterialMsg(Map<String, Object> map);
	void delMaterialMsg(Map<String, Object> map);
	void editMaterialMsg(Map<String, Object> map);
	List<CMesBoltInfomationT> boltList(Map<String, Object> map);
	void addBolt(Map<String, Object> map);
	void delBolt(Map<String, Object> map);
	void editBolt(Map<String, Object> map);
	List<CMesLeakageInfoT> leakageList(Map<String, Object> map);
	void addAirtightness(Map<String, Object> map);
	void editAirtightness(Map<String, Object> map);
	void delAirtightness(Map<String, Object> map);
	List<CMesOtherDataT> otherDataList(Map<String, Object> map);
	void addOtherData(Map<String, Object> map);
	void delOtherData(Map<String, Object> map);
	void editOtherData(Map<String, Object> map);
	List<CMesBomDetailT> bomDetailList(Map<String, Object> map);
	void addBomDetails(Map<String, Object> map);
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

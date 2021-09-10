package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesDefectEntryT;
import com.skeqi.mes.pojo.CMesProductionT;

public interface DefectEntryDAO {

	public List<CMesDefectEntryT> findAll(Map<String,Object> map);

	public void insertentry(CMesDefectEntryT c);


	public void updateentry(CMesDefectEntryT c);

	public void deleteentry(String id);

	public CMesDefectEntryT findByid(String id);

	public List<CMesProductionT> findProduction();
}

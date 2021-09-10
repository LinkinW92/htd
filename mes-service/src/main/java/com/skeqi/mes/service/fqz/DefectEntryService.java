package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesDefectEntryT;
import com.skeqi.mes.pojo.CMesProductionT;

public interface DefectEntryService {

	public List<CMesDefectEntryT> findAll(Map<String,Object> map);

	public void insertentry(CMesDefectEntryT c);


	public void updateentry(CMesDefectEntryT c);

	public void deleteentry(String id);

	public CMesDefectEntryT findByid(String id);

	public List<CMesProductionT> findProduction();
}

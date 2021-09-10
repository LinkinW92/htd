package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesWorkBarcodeT;
import com.skeqi.mes.pojo.PMesStationPassT;
import com.skeqi.mes.pojo.PMesTrackingT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesTrackingT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;

public interface WorkBarcodeService {
	public List<CMesWorkBarcodeT> findAll(Map<String,Object> map);

	public List<PMesStationPassT> sndail(String sn);

	public RMesTrackingT findR(String workId,String sn);

	public List<CMesWorkBarcodeT> findAllP(Map<String,Object> map);
	public PMesTrackingT findP(String workId,String sn);
	public List<CMesProductionT> findPro(Integer id);
	public List<RMesPlanT> findPlan(String id);
	public List<RMesWorkorderDetailT> findWorkor(String id);
}

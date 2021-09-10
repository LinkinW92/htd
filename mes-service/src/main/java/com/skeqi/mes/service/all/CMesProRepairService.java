package com.skeqi.mes.service.all;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.skeqi.mes.pojo.CMesReturnRepairT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;

public interface CMesProRepairService {


	public RTrackingT findBySn(String sn);

	public List<RMesKeypart> findKeyPartBySn(String sn);

	public List<RMesBolt> findBoltBySn(String sn);

	public List<RMesLeakage> findLeakageBySn(String sn);

	public String findProType(Integer id);

	public Integer findRecipeId(String sn,Integer pro,Integer line);

	public Integer findRouting(String sn,Integer pro,Integer line);

	public List<CMesStationT> findStation(Integer id);

	public Integer insertReturnRepair(CMesReturnRepairT repair);

	public Integer insertReworkWay(ReworkWayT reworkWay);

	public Integer updateRTracking(String sn);

	public String findStationById(Integer id);
}

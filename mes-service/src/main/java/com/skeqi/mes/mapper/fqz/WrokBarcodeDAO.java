package com.skeqi.mes.mapper.fqz;

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

/**
 * 工单条码dao
 * @author : FQZ
 * @Package: com.skeqi.mapper.fqz
 * @date   : 2019年10月9日 下午12:02:47
 */
public interface WrokBarcodeDAO {
	/**
	 * 根据条件查询列表
	* @author FQZ
	* @date 2019年10月9日下午12:05:09
	 */
	public List<CMesWorkBarcodeT> findAll(Map<String,Object> map);

	public List<CMesWorkBarcodeT> findAllP(Map<String,Object> map);

	/**
	 * 查询pack经过工位的状态
	* @author FQZ
	* @date 2019年10月10日上午10:03:03
	 */
	public List<PMesStationPassT> sndail(String sn);

	public RMesTrackingT findR(@Param("workId")String workId,@Param("sn")String sn);

	public PMesTrackingT findP(@Param("workId")String workId,@Param("sn")String sn);

	//查询正在做的产品
	public List<CMesProductionT> findPro(Integer id);

	//查询正在做的计划
	public List<RMesPlanT> findPlan(@Param("id")String id);

	//查询正在做的工单
	public List<RMesWorkorderDetailT> findWorkor(@Param("id")String id);
}

package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.CMesReturnRepairT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;

@Component
@MapperScan
public interface CMesProRepairDAO {

	/**
	 * 查询此sn是否可以返修
	 * @param sn
	 * @return
	 */
	@Select("select * from r_mes_tracking_t where SN=#{sn} and STATUS='NG'")
	public RTrackingT findBySn(@Param("sn")String sn);

	/**
	 * 查询产品类型
	 * @param id
	 * @return
	 */
	@Select("select PRODUCTION_TYPE from c_mes_production_t where ID=#{id}")
	public String findProType(Integer id);

	/**
	 * 查询此SN的物料信息
	 * @param sn
	 * @return
	 */
	@Select("select * from r_mes_keypart_t where SN=#{sn}")
	public List<RMesKeypart> findKeyPartBySn(@Param("sn")String sn);


	/**
	 * 查询此SN的螺栓信息
	 * @param sn
	 * @return
	 */
	@Select("select * from r_mes_bolt_t where SN=#{sn}")
	public List<RMesBolt> findBoltBySn(@Param("sn")String sn);


	/**
	 * 查询此SN的物料信息
	 * @param sn
	 * @return
	 */
	@Select("select * from r_mes_leakage_t where SN=#{sn}")
	public List<RMesLeakage> findLeakageBySn(@Param("sn")String sn);

	/**
	 * 根据工艺路线id查询所在的工位
	 * @param id
	 * @return
	 */
	/*
	 * @Select("select ST_ID from c_mes_production_way_t where ROUTING_ID=#{id}")
	 * public List<Integer> findStation(Integer id);
	 */

	@Select("select * from c_mes_station_t where id in (select ST_ID from c_mes_production_way_t where ROUTING_ID=#{id})")
	public List<CMesStationT> findStation(Integer id);

	/**
	 * 根据工位id查询工位详情
	 * @param id
	 * @return
	 */
	@Select("select * from c_mes_station_t where ID=#{id}")
	public CMesStationT findStationByid(Integer id);

	@Select("select station_name from c_mes_station_t where ID=#{id}")
	public String findStationById(Integer id);

	/**
	 * 添加到return_repair表
	 * @param repair
	 * @return
	 */
	@Insert("insert into c_mes_return_repair_t(DT,PRODUCTION_ID,REASON,SN,LINE_ID) values(now(),#{productionId},#{reason},#{sn},#{lineId})")
	public Integer insertReturnRepair(CMesReturnRepairT repair);

	/**
	 * 添加到返修路线表
	 * @param reworkWay
	 * @return
	 */
	@Insert("insert into r_mes_rework_way_t(DT,SN,ST_NAME,ST_ID,SERIAL_NO) values(now(),#{sn},#{stName},#{stId},#{sertalNo})")
	public Integer insertReworkWay(ReworkWayT reworkWay);

	/**
	 * 修改sn的状态为返修
	 * @param sn
	 * @return
	 */
	@Update("update r_mes_tracking_t  set ENGINESN=3 where SN=#{sn}")
	public Integer updateRTracking(@Param("sn")String sn);
}

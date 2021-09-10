package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesStationT;

/**
 * 工位dao
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date   : 2020年2月11日 下午2:18:52
 */
public interface CMesStationTDAO {

	public List<CMesStationT> findAllStation(CMesStationT c);

	public List<CMesStationT> findStationNameAndId(CMesStationT c);

	public String findLineName(Integer lineId);

	public List<CMesStationT> findStationByListId(Integer listId);

	/**
	 * 查询所有工位id、Name
	 * @return
	 */
	public List<CMesStationT> findStationAll();

	public CMesStationT findStationByid(Integer id);


	public Integer updateStations(CMesStationT c);

	public Integer addStation(CMesStationT c);

	public Integer updateStation(CMesStationT c);

	public Integer delStation(Integer id);

	//根据产线名称和工位下标验证工位下标是否存在
	public int findIndex(@Param("index")Integer index,@Param("lineName")String lineName);

	//查询该工位是否存在末站
	public List<CMesStationT> findEndornot(Integer lineId);
	public List<CMesStationT> findIndexs(@Param("index")Integer index,@Param("lineId")Integer lineId);
}

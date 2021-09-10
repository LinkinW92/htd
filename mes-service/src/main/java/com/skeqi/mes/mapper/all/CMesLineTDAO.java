package com.skeqi.mes.mapper.all;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesStationLine;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesScheduling;
import com.skeqi.mes.pojo.project.CMesSchedulingL;
import com.skeqi.mes.pojo.qh.CMesEndStocks;

/**
 * 产线service
 *
 * @author : FQZ
 * @Package: com.skeqi.mapper.all
 * @date : 2020年2月10日 下午2:43:20
 */
public interface CMesLineTDAO {

	public List<CMesLineT> findAllLine(CMesLineT line);

	public CMesLineT findLineByid(Integer id);

	public Integer addLine(CMesLineT line);

	public Integer updateLine(CMesLineT line);

	public Integer delLine(Integer id);

	public Integer updateStatus(@Param("id") Integer id, @Param("status") Integer status);

	public List<CMesStationLine> findStationByLid(Integer id);

	public Integer update(@Param("lineId") Integer lineId, @Param("shiftId") Integer shiftId, @Param("dt") String dt,
			@Param("teamId") Integer teamId, @Param("planNumber") Integer planNumber,
			@Param("planrealNumber") Integer planrealNumber);

	@Select("SELECT  count(*) FROM `c_mes_scheduling_t` where DATE_FORMAT(dt,'%Y-%m-%d')=#{dt} and SHIFT_ID=#{id}")
	public Integer findSchedulingByDt(String dt, Integer shiftId);

	public Integer addScheduling(CMesScheduling du);

	// 查询每个产品在每个区域的生产数
	public CMesEndStocks findByName(@Param("proName") String proName, @Param("lineName") String lineName,
			@Param("stationName") String stationName, @Param("lineRegion") Integer lineRegion) throws Exception;

	@Select("select id from c_mes_production_t")
	public List<Integer> findAllPro();


	//查询看板列表
	@Select("select * from c_mes_board_t")
	public List<Map<String, Object>> findAll();


	//删除看板
	@Delete("DELETE FROM c_mes_board_t WHERE id=#{id}")
	public Integer delBoard(@Param("id")Integer id);

	//添加看板
	@Insert("insert into c_mes_board_t(boardName,boardType,lineName) value(#{boardName},#{boardType},#{lineName})")
	public void addBoard(@Param("boardName")String boardName, @Param("boardType")String boardType, @Param("lineName")String lineName);

	//修改看板
	@Update("update c_mes_board_t set boardName=#{boardName},boardType=#{boardType},lineName=#{lineName}  where id=#{id}")
	public void updateBoard(@Param("boardName")String boardName, @Param("boardType")String boardType,@Param("lineName")String lineName, @Param("id")Integer id);

	public Map<String, Object> getLineByName(CMesLineT line);

	public CMesLineT getLineByLineIdAndName(CMesLineT line);

}

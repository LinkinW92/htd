package com.skeqi.mes.mapper.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
/**
 * 工序管理
 * @author Huangzs
 *
 */
@MapperScan
@Component("ProcessManagerDao")
public interface ProcessManagerDao {

//	展示工序列表
	@Select("select * from c_mes_process_station_t where ID!=409 order by ID DESC ")
	public List<Map<String,Object>> showProcess();

//	新增工序
	@Insert("insert into c_mes_process_station_t(STATION_NAME,LINE_ID,STATION_TYPES,UNIT_PRICE) values(#{processName},152,#{processType},#{unitPrice})")
	public Integer addProcess(@Param("processName")String processName,@Param("processType")String processType,@Param("unitPrice")String unitPrice);

//	获取最大的ID
	@Select("select max(ID) from  c_mes_process_station_t ")
	public String showProcessById();

//	编辑工序
	@Update("update c_mes_process_station_t set STATION_NAME=#{processName},STATION_TYPES=#{processType},UNIT_PRICE=#{unitPrice} where ID=#{id} ")
	public Integer updateProcess(@Param("processName")String processName,@Param("id")String id,@Param("processType")String processType,@Param("unitPrice")String unitPrice);

//	删除工序
	@Delete("delete from c_mes_process_station_t where ID=#{id}")
	public Integer delProcess(@Param("id")Integer id);

//	工序名称防重
	@Select("<script>select count(*) from  c_mes_process_station_t where STATION_NAME=#{processName} <if test=\"id!='' and id!=null\">and ID!=#{id}</if></script>")
	public Integer countProcessByName(@Param("processName")String processName,@Param("id")String id);

//	查询删除工序是否存在工艺路线
	@Select("select count(*) from c_mes_process_production_way_t where ST_ID=#{id}")
	public Integer countRoutes(@Param("id")Integer id);

//	查看编辑工序是否是任务在生产
	@Select("select count(*) from c_process_production_task t left join c_process_details_t d on t.ID=d.TASK_ID where d.PROCESS_ID=#{processId} and  t.STATUS_FLAGS!=0")
	public Integer showProductTaskByProcessId(@Param("processId")Integer processId);

	@Select("select STATION_NAME from  c_mes_process_station_t where ID=#{id}")
	public String showStationNameById(@Param("id")String id);


}

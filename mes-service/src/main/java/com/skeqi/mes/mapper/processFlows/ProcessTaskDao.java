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
 * 生产任务
 * @author Huangzs
 *
 */
@MapperScan
@Component("ProcessTaskDao")
public interface ProcessTaskDao {

//	测试批量导入pdf文件
	@Insert("insert into c_process_production_task set IMAGES=#{pdf}")
	public Integer addPDFFile(@Param("pdf")String pdf);

//	展示生产任务where t.STATUS_FLAGS!=3
	@Select("select * from c_process_production_task t left join c_mes_process_routing_t r on t.ROUTE_NAME=r.ID  order by t.ID desc ")
	public List<Map<String,Object>> showProcessTask();

//	新增生产任务
	@Insert("insert into c_process_production_task(PROJECT_NAME,PROJECT_NUM,SPECIFICATION_AND_MODEL,MATERIAL_CODE,ROUTE_NAME,PLAN_NUM,COMPLETE_NUM,NG_NUM,REWORK_NUM,OPERATION_DEPARTMENT,STATUS_FLAGS,DT,IMAGES,MATERIAL_NAME,MATERIAL_QUALITY,NUM_REMARKS,STATION_NUM) "
			+ "values(#{projectName},#{projectNum},#{specificationModel},#{materialCode},#{routeName},#{planNum},#{completeNum},#{NGNum},#{reworkNum},#{operationDepartment},#{status},now(),#{images},#{materialName},#{materialQuality},#{numRemarks},#{stationNum})")
	public Integer addProcessTask(@Param("projectName")String projectName,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("materialCode")String materialCode,@Param("routeName")String routeName,@Param("planNum")Integer planNum,@Param("completeNum")Integer completeNum,@Param("NGNum")Integer NGNum,@Param("reworkNum")Integer reworkNum,@Param("operationDepartment")String operationDepartment,@Param("status")Integer status,@Param("images")String images,@Param("materialName")String materialName,@Param("materialQuality")String materialQuality,@Param("numRemarks")String numRemarks,@Param("stationNum")String stationNum);

//	编辑生产任务
	@Update("update c_process_production_task set STATION_NUM=#{stationNum},MATERIAL_NAME=#{materialName},MATERIAL_QUALITY=#{materialQuality},NUM_REMARKS=#{numRemarks},IMAGES=#{images},PROJECT_NAME=#{projectName},PROJECT_NUM=#{projectNum},SPECIFICATION_AND_MODEL=#{specificationModel},MATERIAL_CODE=#{materialCode},ROUTE_NAME=#{routeName},PLAN_NUM=#{planNum},OPERATION_DEPARTMENT=#{operationDepartment} where ID=#{id}")
	public Integer editProcessTask(@Param("projectName")String projectName,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("materialCode")String materialCode,@Param("routeName")String routeName,@Param("planNum")Integer planNum,@Param("operationDepartment")String operationDepartment,@Param("id")Integer id,@Param("images")String images,@Param("materialName")String materialName,@Param("materialQuality")String materialQuality,@Param("numRemarks")String numRemarks,@Param("stationNum")String stationNum);


//	删除生产任务
	@Delete("delete from c_process_production_task where ID=#{id}")
	public Integer delProcessTask(@Param("id")Integer id);

//	查询工艺路线List
	@Select("select * from c_mes_process_routing_t")
	public List<Map<String,Object>> showRouteLists();

//	防止重复限制
	@Select("<script> select count(*) from c_process_production_task where SPECIFICATION_AND_MODEL=#{specificationModel} and PROJECT_NUM=#{projectNum} and  MATERIAL_CODE=#{meterialCode} and STATION_NUM=#{stationNum}"
			+ "<if test=\"id!=-1 and id!=null\"> and ID!=#{id} </if> </script>")
	public Integer countProcessTask(@Param("specificationModel")String specificationModel,@Param("projectNum")String projectNum,@Param("id")Integer id,@Param("meterialCode")String meterialCode,@Param("stationNum")String stationNum);

//	更新状态
	@Update("<script>update c_process_production_task set "
			+ "<if test=\"flags==0\"> STATUS_FLAGS=1</if>"
			+ "<if test=\"flags==1\"> STATUS_FLAGS=2</if>"
			+ "<if test=\"flags==2\"> STATUS_FLAGS=1</if> where ID=#{id}</script>")
	public Integer updateTaskStatus(@Param("id")Integer id,@Param("flags")String flags);

//	多条件查询
	@Select("<script>select * from c_process_production_task t left join c_mes_process_routing_t r on t.ROUTE_NAME=r.ID "
			+ "where 1=1 "
			+ "<if test=\"stationNum!=''\"> and STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"status!=''\"> and STATUS_FLAGS like '%${status}%'</if>"
			+ "<if test=\"materiCode!=''\"> and MATERIAL_CODE like '%${materiCode}%'</if>"
			+ "<if test=\"id!='' and id!=null\"> and t.ID =#{id}</if></script>")
	public List<Map<String,Object>> showProcessTaskByCondition(@Param("stationNum")String stationNum,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("status")String status,@Param("id")String id,@Param("materiCode")String materiCode);


//	限制工艺路线页面的编辑与修改
	@Select("select count(*) from c_process_production_task where ROUTE_NAME=#{routeId} and STATUS_FLAGS!=0")
	public Integer countEditRoute(@Param("routeId")String routeId);

//	限制工艺路线是删除关联属性
	@Select("select count(*) from c_process_production_task where ROUTE_NAME=#{routeId}")
	public Integer countDelRoute(@Param("routeId")String routeId);

//	查询关联的工艺路线的生产任务
	@Select("select ID from c_process_production_task where ROUTE_NAME=#{routeId}")
	public List<Integer> showRouteIds(@Param("routeId")String routeId);

//	不同的工序ID检索下推页面显示任务单
	@Select("select * from (select t.ID,PROJECT_NAME,PROJECT_NUM,SPECIFICATION_AND_MODEL,MATERIAL_CODE,ROUTE_NAME,PLAN_NUM,COMPLETE_NUM,NG_NUM,REWORK_NUM,OPERATION_DEPARTMENT,STATUS_FLAGS,t.DT from c_process_production_task t left join c_mes_process_routing_t r on t.ROUTE_NAME=r.ID)AS a left join c_process_details_t b on a.ID=b.TASK_ID where PROCESS_ID=#{processId} and b.USEABLE_NUM > 0 ")
	public List<Map<String,Object>> showAllProcessTaskPage(@Param("processId")Integer processId);

//  多条件查询下推相关工序的任务单
	@Select("<script>select * from (select t.ID,PROJECT_NAME,PROJECT_NUM,SPECIFICATION_AND_MODEL,MATERIAL_CODE,ROUTE_NAME,PLAN_NUM,COMPLETE_NUM,NG_NUM,REWORK_NUM,OPERATION_DEPARTMENT,STATUS_FLAGS,t.DT from c_process_production_task t left join c_mes_process_routing_t r on t.ROUTE_NAME=r.ID)AS a left join c_process_details_t b on a.ID=b.TASK_ID where PROCESS_ID=#{processId}"
			+ " and 1=1"
			+ "<if test=\"projectName!=''\"> and PROJECT_NAME like '%${projectName}%'</if>"
			+ "<if test=\"projectNum!=''\"> and PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"status!=''\"> and STATUS_FLAGS like '%${status}%'</if>"
			+ "<if test=\"id!='' and id!=null\"> and a.ID =#{id}</if></script>")
	public List<Map<String,Object>> showAllProcessTaskPageByCondition(@Param("processId")Integer processId,@Param("projectName")String projectName,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("status")String status,@Param("id")String id);

//	展示生产任务
	@Select("select * from c_process_production_task where ID=#{id}")
	public Map<String,Object> showTaskInfoById(@Param("id")Integer id);

//	查找唯一码是否已经存在
	@Select("select count(*) from c_process_production_task where SPECIFICATION_AND_MODEL=#{specification} and MATERIAL_CODE=#{meterialCode} and STATION_NUM=#{stationNum}")
	public Integer showAlreadyNum(@Param("specification")String specification,@Param("meterialCode")String meterialCode,@Param("stationNum")String stationNum);

//	一键开始所有任务
	@Update("update c_process_production_task set STATUS_FLAGS=1 where STATUS_FLAGS=0 ")
	public Integer updateSattusInfox();

//	展示工艺路线清单
	@Select("select ID,`NAME` from c_mes_process_routing_t ")
	public List<Map<String,Object>> showRouteList();

//	展示工艺路线清单
	@Select("<script> select ID,NAME from c_mes_process_routing_t where 1=1 <if test=\"processNum!=null and processNum!=''\"> and ID like '%${processNum}%'</if> <if test=\"processName!=null and processName!=''\">and NAME like '%${processName}%'</if> </script>")
	public List<Map<String,Object>> showRouteListxs(@Param("processNum")String processNum,@Param("processName")String processName);

//	查询工艺路线ID对应的工序ID
	@Select("select * from c_mes_process_production_way_t a left join c_mes_process_station_t b on a.ST_ID = b.ID where a.ROUTING_ID=#{id}")
	public List<Map<String,Object>> showAllProcessInfo(@Param("id")String id);

//	展示工艺路线名称
	@Select("select ID,NAME from c_mes_process_routing_t where ID=#{id} ")
	public List<Map<String,Object>> showRouteLineInfo(@Param("id")String id);

//	添加工艺路线备注
	@Insert("insert into c_process_routing_remarks(ROUTE_ID,PROCESS_ID,REMARKS) values(#{routeName},#{processName},#{remarksInfos})")
	public Integer addRoutListInfo(@Param("routeName")String routeName,@Param("processName")String processName,@Param("remarksInfos")String remarksInfos);

//	编辑工艺路线
	@Update("update c_process_routing_remarks set REMARKS=#{remarksInfos} where ROUTE_ID=#{routeName} and PROCESS_ID=#{processName}")
	public Integer updateRoutListInfo(@Param("remarksInfos")String remarksInfos,@Param("routeName")String routeName,@Param("processName")String processName);

//	备注查重
	@Select("select count(*) from c_process_routing_remarks where ROUTE_ID=#{routeName} and PROCESS_ID=#{processName}")
	public Integer countNum(@Param("routeName")String routeName,@Param("processName")String processName);

//	展示工艺备注
//	@Select("select * from (select c.SERIAL_NO,ST_ID,NAME,STATION_NAME,c.ID from (select a.SERIAL_NO,b.ID,ST_ID,NAME from c_mes_process_production_way_t a left join c_mes_process_routing_t b on a.ROUTING_ID=b.ID) c left join c_mes_process_station_t d on c.ST_ID=d.ID) e left join c_process_routing_remarks f on e.ST_ID=f.PROCESS_ID where e.ID=#{id}  and  (f.ROUTE_ID=#{id} or f.ROUTE_ID is null)  ORDER BY SERIAL_NO asc")
//	@Select("(select * from (select c.SERIAL_NO,ST_ID,NAME,STATION_NAME,c.ID from (select a.SERIAL_NO,b.ID,ST_ID,NAME from c_mes_process_production_way_t a left join c_mes_process_routing_t b on a.ROUTING_ID=b.ID) c left join c_mes_process_station_t d on c.ST_ID=d.ID ) e left join c_process_routing_remarks f on e.ID=f.ROUTE_ID WHERE e.ID=#{id} and e.ST_ID=f.PROCESS_ID and f.PROCESS_ID is not null) union ("
//			+ "select * from (select c.SERIAL_NO,ST_ID,NAME,STATION_NAME,c.ID from (select a.SERIAL_NO,b.ID,ST_ID,NAME from c_mes_process_production_way_t a left join c_mes_process_routing_t b on a.ROUTING_ID=b.ID) c left join c_mes_process_station_t d on c.ST_ID=d.ID ) e left join c_process_routing_remarks f on e.ID=f.ROUTE_ID WHERE e.ID=#{id} and f.PROCESS_ID is null) order by SERIAL_NO asc")
	@ Select("select * from (select c.SERIAL_NO,ST_ID,NAME,STATION_NAME,c.ID from (select a.SERIAL_NO,b.ID,ST_ID,NAME from c_mes_process_production_way_t a left join c_mes_process_routing_t b on a.ROUTING_ID=b.ID) c left join c_mes_process_station_t d on c.ST_ID=d.ID ) e left join c_process_routing_remarks f on e.ID=f.ROUTE_ID WHERE e.ID=#{id} and e.ST_ID=f.PROCESS_ID and f.PROCESS_ID is not null order by  e.SERIAL_NO")
	public List<Map<String,Object>> showAllRouteLists(@Param("id")String id);

//	检索当前的工艺路线列表
	@Select("select c.SERIAL_NO,ST_ID,NAME,STATION_NAME,c.ID from (select a.SERIAL_NO,b.ID,ST_ID,NAME from c_mes_process_production_way_t a left join c_mes_process_routing_t b on a.ROUTING_ID=b.ID) c left join c_mes_process_station_t d on c.ST_ID=d.ID where c.ID=#{id}")
	public List<Map<String,Object>> showAllProcessLists(@Param("id")String id);

//	检索工艺路线ID
	@Select("select ID from c_mes_process_routing_t where PROJECT_NUMX=#{projectNum} and STATION_NUMX=#{stationNum} and SPECIFICATION_AND_MODELX=#{specification}")
	public String showRouteId(@Param("projectNum")String projectNum,@Param("stationNum")String stationNum,@Param("specification")String specification);

}

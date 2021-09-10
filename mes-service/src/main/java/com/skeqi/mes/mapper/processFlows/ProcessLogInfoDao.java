package com.skeqi.mes.mapper.processFlows;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
@Component("ProcessLogInfoDao")
public interface ProcessLogInfoDao {

	@Select("select * from c_process_operation_log where TASK_ID=#{id} ")
	public List<Map<String,Object>> showAllProcessLogInfo(@Param("id")Integer id);

	@Insert("insert into c_process_operation_log(DT,TASK_ID,PROCESS_ID,LOG_INFO,LOG_TYPE) values(now(),#{taskId},#{processId},#{logInfo},#{logType})")
	public Integer addProcessLogInfo(@Param("taskId")String taskId, @Param("processId")String processId,@Param("logInfo")String logInfo,@Param("logType")String logType);

}

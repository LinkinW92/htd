package com.skeqi.mes.mapper.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@MapperScan
@Component("ProcessProblemDao")
public interface ProcessProblemDao {

	@Select("select * from process_problem_submit")
	public List<Map<String,Object>> showProblem();

	@Insert("insert into process_problem_submit(DEPARTMENT,DESCRIBES)values(#{department},#{describe})")
	public Integer addProblem(@Param("department")String department,@Param("describe")String describe);


}

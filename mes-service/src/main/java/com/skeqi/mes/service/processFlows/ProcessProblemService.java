package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProcessProblemService {


	public List<Map<String,Object>> showProblem();


	public Integer addProblem(String department,String describe);

}

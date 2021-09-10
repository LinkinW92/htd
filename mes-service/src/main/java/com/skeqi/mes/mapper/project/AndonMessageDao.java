package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.project.AndonMessage;
import com.skeqi.mes.pojo.project.CAndonIssuedMessage;

public interface AndonMessageDao {

	public List<AndonMessage> findAndonMessageList();

	public AndonMessage findAndonMessage();

	public Integer addAndonMessage(AndonMessage dx);

	public Integer deleteAndonMessage(@Param("id")Integer id);

	public Integer updateAndonMessage(AndonMessage dx);

	//查询异常状态的信息
	public List<JSONObject> findFaultList();

	public List<CAndonIssuedMessage> findIssuedMessage();

	//新增发送记录
	public Integer addIssuedMessage(CAndonIssuedMessage dx);

	//新增日志
	public Integer addMessageLog(JSONObject json);

}

package com.skeqi.mes.service.alarm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.alarm.CAlarmShortmessageConfig;

/**
 * 短信服务配置
 * @author yinp
 *
 */
public interface CAlarmShortmessageConfigService {
	// 查询短信服务配置集合
	public List<CAlarmShortmessageConfig> findShortmessageConfigList(@Param("userName") String userName);

	// 新增短信服务配置
	public Integer addShortmessageConfig(CAlarmShortmessageConfig dx,String userName)throws Exception;

	// 更新短信服务配置
	public Integer updateShortmessageConfig(CAlarmShortmessageConfig dx,String userName)throws Exception;

	// 删除短信服务配置
	public Integer deleteShortmessageConfig(@Param("id") Integer id);
}

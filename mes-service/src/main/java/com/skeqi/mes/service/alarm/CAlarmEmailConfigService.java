package com.skeqi.mes.service.alarm;

import java.util.List;

import com.skeqi.mes.pojo.alarm.CAlarmEmailConfig;

/**
 * 邮箱服务配置
 *
 * @author yinp
 *
 */
public interface CAlarmEmailConfigService {
	// 查询邮箱服务配置集合
	public List<CAlarmEmailConfig> findEmailConfigList(String userName);

	// 新增邮箱服务配置
	public Integer addEmailConfig(CAlarmEmailConfig dx, String userName) throws Exception;

	// 更新邮箱服务配置
	public Integer updateEmailConfig(CAlarmEmailConfig dx, String userName) throws Exception;

	// 删除邮箱服务配置
	public Integer deleteEmailConfig(Integer id);
}

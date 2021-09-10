package com.skeqi.mes.mapper.alarm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmEmailConfig;

/**
 * 邮箱服务配置
 *
 * @author yinp
 *
 */
public interface CAlarmEmailConfigDao {

	// 通过用户名查询用户
	public CMesUserT findUserByName(@Param("userName") String userName);

	// 查询邮箱服务配置集合
	public List<CAlarmEmailConfig> findEmailConfigList(@Param("userName") String userName);

	// 新增邮箱服务配置
	public Integer addEmailConfig(CAlarmEmailConfig dx);

	// 更新邮箱服务配置
	public Integer updateEmailConfig(CAlarmEmailConfig dx);

	// 删除邮箱服务配置
	public Integer deleteEmailConfig(@Param("id") Integer id);

}

package com.skeqi.mes.pojo.alarm;

import com.skeqi.mes.pojo.project.CMesLossTypeT;

/**
 * 通知方式
 *
 * @author yinp
 *
 */
public class CAlarmNotificationMethod {

	private Integer id;
	private Integer lossTypeId;// 损失类型
	private Integer notificationChannelsId;// 通知渠道
	private Integer userId;// 用户ID
	private Integer serviceId;// 服务id
	private Integer problemLevelId;// 问题等级
	private Integer problemState;// 问题状态(1:触发、2:响应、3:处理)
	private String dt;// 更新时间

	private CMesLossTypeT lossType;
	private CAlarmNotificationChannels notificationChannels;
	private CAlarmProblemLevel problemLevel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLossTypeId() {
		return lossTypeId;
	}

	public void setLossTypeId(Integer lossTypeId) {
		this.lossTypeId = lossTypeId;
	}

	public Integer getNotificationChannelsId() {
		return notificationChannelsId;
	}

	public void setNotificationChannelsId(Integer notificationChannelsId) {
		this.notificationChannelsId = notificationChannelsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getProblemLevelId() {
		return problemLevelId;
	}

	public void setProblemLevelId(Integer problemLevelId) {
		this.problemLevelId = problemLevelId;
	}

	public Integer getProblemState() {
		return problemState;
	}

	public void setProblemState(Integer problemState) {
		this.problemState = problemState;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public CMesLossTypeT getLossType() {
		return lossType;
	}

	public void setLossType(CMesLossTypeT lossType) {
		this.lossType = lossType;
	}

	public CAlarmNotificationChannels getNotificationChannels() {
		return notificationChannels;
	}

	public void setNotificationChannels(CAlarmNotificationChannels notificationChannels) {
		this.notificationChannels = notificationChannels;
	}

	public CAlarmProblemLevel getProblemLevel() {
		return problemLevel;
	}

	public void setProblemLevel(CAlarmProblemLevel problemLevel) {
		this.problemLevel = problemLevel;
	}

	@Override
	public String toString() {
		return "CAlarmNotificationMethod [id=" + id + ", lossTypeId=" + lossTypeId + ", notificationChannelsId="
				+ notificationChannelsId + ", userId=" + userId + ", serviceId=" + serviceId + ", problemLevelId="
				+ problemLevelId + ", problemState=" + problemState + ", dt=" + dt + ", lossType=" + lossType
				+ ", notificationChannels=" + notificationChannels + ", problemLevel=" + problemLevel + "]";
	}

	public CAlarmNotificationMethod() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CAlarmNotificationMethod(Integer id, Integer lossTypeId, Integer notificationChannelsId, Integer userId,
			Integer serviceId, Integer problemLevelId, Integer problemState, String dt, CMesLossTypeT lossType,
			CAlarmNotificationChannels notificationChannels, CAlarmProblemLevel problemLevel) {
		super();
		this.id = id;
		this.lossTypeId = lossTypeId;
		this.notificationChannelsId = notificationChannelsId;
		this.userId = userId;
		this.serviceId = serviceId;
		this.problemLevelId = problemLevelId;
		this.problemState = problemState;
		this.dt = dt;
		this.lossType = lossType;
		this.notificationChannels = notificationChannels;
		this.problemLevel = problemLevel;
	}

}

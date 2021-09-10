package com.skeqi.mes.pojo.alarm;

/**
 * 通知渠道
 *
 * @author yinp
 *
 */
public class CAlarmNotificationChannels {

	private Integer id;
	private Integer userId;// 用户id
	private Integer notificationChannelsTypeId;// 通知渠道类型id
	private String notificationChannels;// 通知渠道
	private String notificationChannelsContent;// 内容
	private String dt;// 更新时间

	private CAlarmNotificationChannelsType notificationChannelsType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNotificationChannelsTypeId() {
		return notificationChannelsTypeId;
	}

	public void setNotificationChannelsTypeId(Integer notificationChannelsTypeId) {
		this.notificationChannelsTypeId = notificationChannelsTypeId;
	}

	public String getNotificationChannels() {
		return notificationChannels;
	}

	public void setNotificationChannels(String notificationChannels) {
		this.notificationChannels = notificationChannels;
	}

	public String getNotificationChannelsContent() {
		return notificationChannelsContent;
	}

	public void setNotificationChannelsContent(String notificationChannelsContent) {
		this.notificationChannelsContent = notificationChannelsContent;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public CAlarmNotificationChannelsType getNotificationChannelsType() {
		return notificationChannelsType;
	}

	public void setNotificationChannelsType(CAlarmNotificationChannelsType notificationChannelsType) {
		this.notificationChannelsType = notificationChannelsType;
	}

	@Override
	public String toString() {
		return "CAlarmNotificationChannels [id=" + id + ", userId=" + userId + ", notificationChannelsTypeId="
				+ notificationChannelsTypeId + ", notificationChannels=" + notificationChannels
				+ ", notificationChannelsContent=" + notificationChannelsContent + ", dt=" + dt
				+ ", notificationChannelsType=" + notificationChannelsType + "]";
	}

	public CAlarmNotificationChannels(Integer id, Integer userId, Integer notificationChannelsTypeId,
			String notificationChannels, String notificationChannelsContent, String dt,
			CAlarmNotificationChannelsType notificationChannelsType) {
		super();
		this.id = id;
		this.userId = userId;
		this.notificationChannelsTypeId = notificationChannelsTypeId;
		this.notificationChannels = notificationChannels;
		this.notificationChannelsContent = notificationChannelsContent;
		this.dt = dt;
		this.notificationChannelsType = notificationChannelsType;
	}

	public CAlarmNotificationChannels() {
		super();
		// TODO Auto-generated constructor stub
	}

}

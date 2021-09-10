package com.skeqi.mes.pojo.alarm;

/**
 * 通知渠道类型
 *
 * @author yinp
 *
 */
public class CAlarmNotificationChannelsType {
	private Integer id;
	private String notificationChannelsTypeName;// 渠道类型名称
	private String note;// 说明

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotificationChannelsTypeName() {
		return notificationChannelsTypeName;
	}

	public void setNotificationChannelsTypeName(String notificationChannelsTypeName) {
		this.notificationChannelsTypeName = notificationChannelsTypeName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "CAlarmNotificationChannelsType [id=" + id + ", notificationChannelsTypeName="
				+ notificationChannelsTypeName + ", note=" + note + "]";
	}

	public CAlarmNotificationChannelsType(Integer id, String notificationChannelsTypeName, String note) {
		super();
		this.id = id;
		this.notificationChannelsTypeName = notificationChannelsTypeName;
		this.note = note;
	}

	public CAlarmNotificationChannelsType() {
		super();
		// TODO Auto-generated constructor stub
	}

}

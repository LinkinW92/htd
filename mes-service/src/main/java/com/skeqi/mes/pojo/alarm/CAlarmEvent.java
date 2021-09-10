package com.skeqi.mes.pojo.alarm;

/**
 * 事件
 *
 * @author yinp
 *
 */
public class CAlarmEvent {

	private Integer id;
	private String event;
	private Integer userId;
	private String dt;
	private String note;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public CAlarmEvent(Integer id, String event, Integer userId, String dt, String note) {
		super();
		this.id = id;
		this.event = event;
		this.userId = userId;
		this.dt = dt;
		this.note = note;
	}
	public CAlarmEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CAlarmEvent [id=" + id + ", event=" + event + ", userId=" + userId + ", dt=" + dt + ", note=" + note
				+ "]";
	}


}

package com.skeqi.mes.pojo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
/*通知管理表*/
public class CMesNoticeT {
	@ApiModelProperty(value="id",required=false)
	private Integer id;
	@ApiModelProperty(value="时间",required=false)
	private Date dt;
	@ApiModelProperty(value="标题",required=true)
	private String head;//标题
	@ApiModelProperty(value="内容",required=true)
	private String  noticeContent;//内容
	@ApiModelProperty(value="班组长",required=true)
	private String  contacts;//班组长
	@ApiModelProperty(value="班组id",required=false)
	private String  teamId;//班组id
	@ApiModelProperty(value="发布时间",required=false)
	private Date pullTime;//发布时间

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Date getPullTime() {
		return pullTime;
	}

	public void setPullTime(Date pullTime) {
		this.pullTime = pullTime;
	}


}

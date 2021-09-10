package com.skeqi.mes.pojo.project;

public class CAndonIssuedMessage {

	private Integer id;
	private Integer faultId;
	private Integer messageId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFaultId() {
		return faultId;
	}
	public void setFaultId(Integer faultId) {
		this.faultId = faultId;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	@Override
	public String toString() {
		return "CAndonMessageLogs [id=" + id + ", faultId=" + faultId + ", messageId=" + messageId + "]";
	}
	public CAndonIssuedMessage(Integer id, Integer faultId, Integer messageId) {
		this.id = id;
		this.faultId = faultId;
		this.messageId = messageId;
	}
	public CAndonIssuedMessage() {
		// TODO Auto-generated constructor stub
	}

}

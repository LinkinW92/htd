package com.skeqi.mes.pojo.alarm;

/**
 * 通知日志
 *
 * @author yinp
 *
 */
public class CAlarmNoticeLogs {

	private Integer id;
	private Integer faultId;// 故障id
	private String lossType;// 事件
	private String dt;// 时间
	private String sendOut;// 发送
	private String receive;// 接收
	private String channels;// 渠道
	private String channelsType;// 渠道类型

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

	public String getLossType() {
		return lossType;
	}

	public void setLossType(String lossType) {
		this.lossType = lossType;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getSendOut() {
		return sendOut;
	}

	public void setSendOut(String sendOut) {
		this.sendOut = sendOut;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String getChannelsType() {
		return channelsType;
	}

	public void setChannelsType(String channelsType) {
		this.channelsType = channelsType;
	}

}

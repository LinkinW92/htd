package com.skeqi.mes.pojo.alarm;

/**
 * ip白名单
 *
 * @author yinp
 *
 */
public class CAlarmIpWhiteList {

	private Integer id;
	private Integer userId;
	private String ip;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public CAlarmIpWhiteList(Integer id, Integer userId, String ip) {
		super();
		this.id = id;
		this.userId = userId;
		this.ip = ip;
	}

	public CAlarmIpWhiteList() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CAlarmIpWhiteList [id=" + id + ", userId=" + userId + ", ip=" + ip + "]";
	}

}

package com.skeqi.mes.pojo.alarm;

/**
 * 邮箱服务配置
 * @author yinp
 *
 */
public class CAlarmEmailConfig {

	private Integer id;
	private Integer userId;// 用户id
	private String senderEmail;// 发件人
	private String theServer;// 服务器
	private String authorizationCode;// 授权码
	private String dt;// 更新时间

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

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getTheServer() {
		return theServer;
	}

	public void setTheServer(String theServer) {
		this.theServer = theServer;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	@Override
	public String toString() {
		return "AlarmEmailConfig [id=" + id + ", userId=" + userId + ", senderEmail=" + senderEmail + ", theServer="
				+ theServer + ", authorizationCode=" + authorizationCode + ", dt=" + dt + "]";
	}

	public CAlarmEmailConfig(Integer id, Integer userId, String senderEmail, String theServer, String authorizationCode,
			String dt) {
		super();
		this.id = id;
		this.userId = userId;
		this.senderEmail = senderEmail;
		this.theServer = theServer;
		this.authorizationCode = authorizationCode;
		this.dt = dt;
	}

	public CAlarmEmailConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

}

package com.skeqi.mes.pojo.alarm;

/**
 * 短信服务配置
 *
 * @author yinp
 *
 */
public class CAlarmShortmessageConfig {

	private Integer id;
	private Integer userId;// 用户id
	private String header;// 请求头
	private String parameter;// 参数
	private String charset;// 字符集
	private String apiUrl;// 接口地址
	private String requestMethod;// 请求方式
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

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	@Override
	public String toString() {
		return "CAlarmShortmessageConfig [id=" + id + ", userId=" + userId + ", header=" + header + ", parameter="
				+ parameter + ", charset=" + charset + ", apiUrl=" + apiUrl + ", requestMethod=" + requestMethod
				+ ", dt=" + dt + "]";
	}

	public CAlarmShortmessageConfig(Integer id, Integer userId, String header, String parameter, String charset,
			String apiUrl, String requestMethod, String dt) {
		super();
		this.id = id;
		this.userId = userId;
		this.header = header;
		this.parameter = parameter;
		this.charset = charset;
		this.apiUrl = apiUrl;
		this.requestMethod = requestMethod;
		this.dt = dt;
	}

	public CAlarmShortmessageConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

}

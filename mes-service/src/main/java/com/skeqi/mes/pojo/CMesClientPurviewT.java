package com.skeqi.mes.pojo;

import java.util.Date;

/**
 * 客户端功能模块密码
 * @author Lenovo
 *
 */
public class CMesClientPurviewT {

	private Integer id;//主键
	private Date dt;//日期
	private String funNo;//模块编码
	private String ownPassword;//开启密码
	private String dis;//描述
	private String funName;//模块名称

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
	public String getFunNo() {
		return funNo;
	}
	public void setFunNo(String funNo) {
		this.funNo = funNo;
	}
	public String getOwnPassword() {
		return ownPassword;
	}
	public void setOwnPassword(String ownPassword) {
		this.ownPassword = ownPassword;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}

	public CMesClientPurviewT(Integer id, Date dt, String funNo, String ownPassword, String dis, String funName) {
		super();
		this.id = id;
		this.dt = dt;
		this.funNo = funNo;
		this.ownPassword = ownPassword;
		this.dis = dis;
		this.funName = funName;
	}
	public CMesClientPurviewT() {
		super();
	}


}

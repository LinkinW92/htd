package com.skeqi.mes.pojo.project;

public class AndonMessage {

	private Integer id;
	private Integer dt;
	private String phone;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDt() {
		return dt;
	}
	public void setDt(Integer dt) {
		this.dt = dt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "AndonMessage [id=" + id + ", dt=" + dt + ", phone=" + phone + "]";
	}
	public AndonMessage(Integer id, Integer dt, String phone) {
		super();
		this.id = id;
		this.dt = dt;
		this.phone = phone;
	}
	public AndonMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

}

package com.skeqi.mes.pojo;

import java.util.Date;

public class CMesCheckoutMethodT {

	private Integer id;
	private String methodNo;
	private String methodName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMethodNo() {
		return methodNo;
	}
	public void setMethodNo(String methodNo) {
		this.methodNo = methodNo;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	@Override
	public String toString() {
		return "CMesCheckoutMethodT [id=" + id + ", methodNo=" + methodNo + ", methodName=" + methodName + "]";
	}
	public CMesCheckoutMethodT(Integer id, String methodNo, String methodName) {
		super();
		this.id = id;
		this.methodNo = methodNo;
		this.methodName = methodName;
	}
	public CMesCheckoutMethodT() {
		super();
	}


}

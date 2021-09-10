package com.skeqi.mes.pojo;

import java.util.Date;

/**
 * eol
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月16日 下午12:44:19
 */
public class PMesEolT {

	private Integer id;
	private Date Dt;
	private String ST;
	private String tt;
	private String SN;
	private String t1;
	private String t2;
	private String t3;
	private String t4;
	private String t5;
	private String t6;
	private String R;

	public PMesEolT() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDt() {
		return Dt;
	}

	public void setDt(Date dt) {
		Dt = dt;
	}

	public String getST() {
		return ST;
	}

	public void setST(String sT) {
		ST = sT;
	}

	public String getTt() {
		return tt;
	}

	public void setTt(String tt) {
		this.tt = tt;
	}

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}

	public String getT1() {
		return t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getT3() {
		return t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}

	public String getT4() {
		return t4;
	}

	public void setT4(String t4) {
		this.t4 = t4;
	}

	public String getT5() {
		return t5;
	}

	public void setT5(String t5) {
		this.t5 = t5;
	}

	public String getT6() {
		return t6;
	}

	public void setT6(String t6) {
		this.t6 = t6;
	}

	public String getR() {
		return R;
	}

	public void setR(String r) {
		R = r;
	}

	public PMesEolT(Integer id, Date dt, String sT, String tt, String sN, String t1, String t2, String t3, String t4,
			String t5, String t6, String r) {
		super();
		this.id = id;
		Dt = dt;
		ST = sT;
		this.tt = tt;
		SN = sN;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.t4 = t4;
		this.t5 = t5;
		this.t6 = t6;
		R = r;
	}

	@Override
	public String toString() {
		return "PMesEolT [id=" + id + ", Dt=" + Dt + ", ST=" + ST + ", tt=" + tt + ", SN=" + SN + ", t1=" + t1 + ", t2="
				+ t2 + ", t3=" + t3 + ", t4=" + t4 + ", t5=" + t5 + ", t6=" + t6 + ", R=" + R + "]";
	}


}

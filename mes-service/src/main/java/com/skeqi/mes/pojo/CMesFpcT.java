package com.skeqi.mes.pojo;
/**
 * fpc通讯
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午11:04:14
 */
public class CMesFpcT {

	private Integer id;
	private String DT;	 //模组上传时间
	private String BARCODE;	//模组条码
	private String FPC_FF1;	//电芯电压测试
	private String FPC_FF2;  //ntc阻值测试
	private String FPC_FF3;	  //模组温度计算
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDT() {
		return DT;
	}
	public void setDT(String dT) {
		DT = dT;
	}
	public String getBARCODE() {
		return BARCODE;
	}
	public void setBARCODE(String bARCODE) {
		BARCODE = bARCODE;
	}
	public String getFPC_FF1() {
		return FPC_FF1;
	}
	public void setFPC_FF1(String fPC_FF1) {
		FPC_FF1 = fPC_FF1;
	}
	public String getFPC_FF2() {
		return FPC_FF2;
	}
	public void setFPC_FF2(String fPC_FF2) {
		FPC_FF2 = fPC_FF2;
	}
	public String getFPC_FF3() {
		return FPC_FF3;
	}
	public void setFPC_FF3(String fPC_FF3) {
		FPC_FF3 = fPC_FF3;
	}
	@Override
	public String toString() {
		return "CMesFpcT [id=" + id + ", DT=" + DT + ", BARCODE=" + BARCODE + ", FPC_FF1=" + FPC_FF1 + ", FPC_FF2="
				+ FPC_FF2 + ", FPC_FF3=" + FPC_FF3 + ", getId()=" + getId() + ", getDT()=" + getDT() + ", getBARCODE()="
				+ getBARCODE() + ", getFPC_FF1()=" + getFPC_FF1() + ", getFPC_FF2()=" + getFPC_FF2() + ", getFPC_FF3()="
				+ getFPC_FF3() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public CMesFpcT() {
		super();
	}



}

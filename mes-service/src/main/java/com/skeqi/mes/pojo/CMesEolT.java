package com.skeqi.mes.pojo;

/**
 * EOl
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午10:57:56
 */
public class CMesEolT {

	private Integer id;
	private String dt;
	private String BARCODE;  //模组条码
	private String EOL_FF1;  //侧板探针接触检测
	private String EOL_FF2;  //模组开路电压测试
	private String EOL_FF3;	 //	模组总内阻测试
	private String EOL_FF4;	 //	单串电压测试
	private String EOL_FF5;	 //	电压差值计算，内阻差值计算
	private String EOL_FF6;	 //	总正对壳体绝缘测试
	private String EOL_FF7;	 //	总负对壳体绝缘测试
	private String EOL_FF8;	 //	总正对壳体漏电流测试
	private String EOL_FF9;	 //	总负对壳体漏电流测试
	private String EOL_FF10; //	单串内阻测试
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getBARCODE() {
		return BARCODE;
	}
	public void setBARCODE(String bARCODE) {
		BARCODE = bARCODE;
	}
	public String getEOL_FF1() {
		return EOL_FF1;
	}
	public void setEOL_FF1(String eOL_FF1) {
		EOL_FF1 = eOL_FF1;
	}
	public String getEOL_FF2() {
		return EOL_FF2;
	}
	public void setEOL_FF2(String eOL_FF2) {
		EOL_FF2 = eOL_FF2;
	}
	public String getEOL_FF3() {
		return EOL_FF3;
	}
	public void setEOL_FF3(String eOL_FF3) {
		EOL_FF3 = eOL_FF3;
	}
	public String getEOL_FF4() {
		return EOL_FF4;
	}
	public void setEOL_FF4(String eOL_FF4) {
		EOL_FF4 = eOL_FF4;
	}
	public String getEOL_FF5() {
		return EOL_FF5;
	}
	public void setEOL_FF5(String eOL_FF5) {
		EOL_FF5 = eOL_FF5;
	}
	public String getEOL_FF6() {
		return EOL_FF6;
	}
	public void setEOL_FF6(String eOL_FF6) {
		EOL_FF6 = eOL_FF6;
	}
	public String getEOL_FF7() {
		return EOL_FF7;
	}
	public void setEOL_FF7(String eOL_FF7) {
		EOL_FF7 = eOL_FF7;
	}
	public String getEOL_FF8() {
		return EOL_FF8;
	}
	public void setEOL_FF8(String eOL_FF8) {
		EOL_FF8 = eOL_FF8;
	}
	public String getEOL_FF9() {
		return EOL_FF9;
	}
	public void setEOL_FF9(String eOL_FF9) {
		EOL_FF9 = eOL_FF9;
	}
	public String getEOL_FF10() {
		return EOL_FF10;
	}
	public void setEOL_FF10(String eOL_FF10) {
		EOL_FF10 = eOL_FF10;
	}
	@Override
	public String toString() {
		return "CMesEolT [id=" + id + ", dt=" + dt + ", BARCODE=" + BARCODE + ", EOL_FF1=" + EOL_FF1 + ", EOL_FF2="
				+ EOL_FF2 + ", EOL_FF3=" + EOL_FF3 + ", EOL_FF4=" + EOL_FF4 + ", EOL_FF5=" + EOL_FF5 + ", EOL_FF6="
				+ EOL_FF6 + ", EOL_FF7=" + EOL_FF7 + ", EOL_FF8=" + EOL_FF8 + ", EOL_FF9=" + EOL_FF9 + ", EOL_FF10="
				+ EOL_FF10 + "]";
	}
	public CMesEolT(Integer id, String dt, String bARCODE, String eOL_FF1, String eOL_FF2, String eOL_FF3,
			String eOL_FF4, String eOL_FF5, String eOL_FF6, String eOL_FF7, String eOL_FF8, String eOL_FF9,
			String eOL_FF10) {
		super();
		this.id = id;
		this.dt = dt;
		BARCODE = bARCODE;
		EOL_FF1 = eOL_FF1;
		EOL_FF2 = eOL_FF2;
		EOL_FF3 = eOL_FF3;
		EOL_FF4 = eOL_FF4;
		EOL_FF5 = eOL_FF5;
		EOL_FF6 = eOL_FF6;
		EOL_FF7 = eOL_FF7;
		EOL_FF8 = eOL_FF8;
		EOL_FF9 = eOL_FF9;
		EOL_FF10 = eOL_FF10;
	}
	public CMesEolT() {
		super();
	}



}

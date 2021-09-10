package com.skeqi.mes.pojo;
/**
 * ISO
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午10:53:58
 */
public class CMesIsoT {

	private Integer id;
	private String DT;		//	数据上传时间
	private String BARCODE;   //模组条码
	private String ISO_FF1;	  //侧板探针接触检测
	private String ISO_FF2;	   //单电芯电压测试
	private String ISO_FF3;	   //电芯电压测试
	private String ISO_FF4;	   //相邻两组正极间绝缘测试
	private String ISO_FF5;	   //模组所有电芯正极对模组壳体绝缘测试
	private String ISO_FF6;	   //模组所有电芯负极对模组壳体绝缘测试
	private String ISO_FF7;	   //模组所有电芯正极对模组壳体漏电流测试
	private String ISO_FF8;	   //模组所有电芯负极对模组壳体漏电流测试
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
	public String getISO_FF1() {
		return ISO_FF1;
	}
	public void setISO_FF1(String iSO_FF1) {
		ISO_FF1 = iSO_FF1;
	}
	public String getISO_FF2() {
		return ISO_FF2;
	}
	public void setISO_FF2(String iSO_FF2) {
		ISO_FF2 = iSO_FF2;
	}
	public String getISO_FF3() {
		return ISO_FF3;
	}
	public void setISO_FF3(String iSO_FF3) {
		ISO_FF3 = iSO_FF3;
	}
	public String getISO_FF4() {
		return ISO_FF4;
	}
	public void setISO_FF4(String iSO_FF4) {
		ISO_FF4 = iSO_FF4;
	}
	public String getISO_FF5() {
		return ISO_FF5;
	}
	public void setISO_FF5(String iSO_FF5) {
		ISO_FF5 = iSO_FF5;
	}
	public String getISO_FF6() {
		return ISO_FF6;
	}
	public void setISO_FF6(String iSO_FF6) {
		ISO_FF6 = iSO_FF6;
	}
	public String getISO_FF7() {
		return ISO_FF7;
	}
	public void setISO_FF7(String iSO_FF7) {
		ISO_FF7 = iSO_FF7;
	}
	public String getISO_FF8() {
		return ISO_FF8;
	}
	public void setISO_FF8(String iSO_FF8) {
		ISO_FF8 = iSO_FF8;
	}
	@Override
	public String toString() {
		return "CMesIsoT [id=" + id + ", DT=" + DT + ", BARCODE=" + BARCODE + ", ISO_FF1=" + ISO_FF1 + ", ISO_FF2="
				+ ISO_FF2 + ", ISO_FF3=" + ISO_FF3 + ", ISO_FF4=" + ISO_FF4 + ", ISO_FF5=" + ISO_FF5 + ", ISO_FF6="
				+ ISO_FF6 + ", ISO_FF7=" + ISO_FF7 + ", ISO_FF8=" + ISO_FF8 + "]";
	}
	public CMesIsoT(Integer id, String dT, String bARCODE, String iSO_FF1, String iSO_FF2, String iSO_FF3,
			String iSO_FF4, String iSO_FF5, String iSO_FF6, String iSO_FF7, String iSO_FF8) {
		super();
		this.id = id;
		DT = dT;
		BARCODE = bARCODE;
		ISO_FF1 = iSO_FF1;
		ISO_FF2 = iSO_FF2;
		ISO_FF3 = iSO_FF3;
		ISO_FF4 = iSO_FF4;
		ISO_FF5 = iSO_FF5;
		ISO_FF6 = iSO_FF6;
		ISO_FF7 = iSO_FF7;
		ISO_FF8 = iSO_FF8;
	}
	public CMesIsoT() {
		super();
	}



}

package com.skeqi.mes.pojo;
/**
 * 机器人搬运
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午10:34:13
 */
public class CMesRobotT {

	private Integer id;
	private String glue_spread;   //涂胶量
	private String dt;   //时间
	private String barcode;   //条码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGlue_spread() {
		return glue_spread;
	}
	public void setGlue_spread(String glue_spread) {
		this.glue_spread = glue_spread;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	@Override
	public String toString() {
		return "CMesRobotT [id=" + id + ", glue_spread=" + glue_spread + ", dt=" + dt + ", barcode=" + barcode + "]";
	}
	public CMesRobotT(Integer id, String glue_spread, String dt, String barcode) {
		super();
		this.id = id;
		this.glue_spread = glue_spread;
		this.dt = dt;
		this.barcode = barcode;
	}
	public CMesRobotT() {
		super();
	}


}

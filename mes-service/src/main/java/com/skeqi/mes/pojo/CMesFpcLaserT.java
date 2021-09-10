package com.skeqi.mes.pojo;
/*
 *
 * fpc激光焊接
 */
public class CMesFpcLaserT {

	private Integer id;
	private String fpcNum;		//编码
	private String weldingPower;	//焊接功率
	private String weldingSpeed;		//焊接速度
	private String protectiveGasFlwo;   //保护气流量
	private String defocusing;	 //离焦量
	private String columnId;	//极柱数量编号
	private String uploadTime; //时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFpcNum() {
		return fpcNum;
	}
	public void setFpcNum(String fpcNum) {
		this.fpcNum = fpcNum;
	}
	public String getWeldingPower() {
		return weldingPower;
	}
	public void setWeldingPower(String weldingPower) {
		this.weldingPower = weldingPower;
	}
	public String getWeldingSpeed() {
		return weldingSpeed;
	}
	public void setWeldingSpeed(String weldingSpeed) {
		this.weldingSpeed = weldingSpeed;
	}
	public String getProtectiveGasFlwo() {
		return protectiveGasFlwo;
	}
	public void setProtectiveGasFlwo(String protectiveGasFlwo) {
		this.protectiveGasFlwo = protectiveGasFlwo;
	}
	public String getDefocusing() {
		return defocusing;
	}
	public void setDefocusing(String defocusing) {
		this.defocusing = defocusing;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Override
	public String toString() {
		return "CMesFpcLaserT [id=" + id + ", fpcNum=" + fpcNum + ", weldingPower=" + weldingPower + ", weldingSpeed="
				+ weldingSpeed + ", protectiveGasFlwo=" + protectiveGasFlwo + ", defocusing=" + defocusing
				+ ", columnId=" + columnId + ", uploadTime=" + uploadTime + "]";
	}
	public CMesFpcLaserT(Integer id, String fpcNum, String weldingPower, String weldingSpeed, String protectiveGasFlwo,
			String defocusing, String columnId, String uploadTime) {
		super();
		this.id = id;
		this.fpcNum = fpcNum;
		this.weldingPower = weldingPower;
		this.weldingSpeed = weldingSpeed;
		this.protectiveGasFlwo = protectiveGasFlwo;
		this.defocusing = defocusing;
		this.columnId = columnId;
		this.uploadTime = uploadTime;
	}
	public CMesFpcLaserT() {
		super();
	}


}

package com.skeqi.mes.pojo;
/**
 * busbar激光焊
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午10:47:52
 */
public class CMesBusbarT {

	private Integer id;
	private String busbarNum;		//编码
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
	public String getBusbarNum() {
		return busbarNum;
	}
	public void setBusbarNum(String busbarNum) {
		this.busbarNum = busbarNum;
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
		return "CMesBusbarT [id=" + id + ", busbarNum=" + busbarNum + ", weldingPower=" + weldingPower
				+ ", weldingSpeed=" + weldingSpeed + ", protectiveGasFlwo=" + protectiveGasFlwo + ", defocusing="
				+ defocusing + ", columnId=" + columnId + ", uploadTime=" + uploadTime + "]";
	}
	public CMesBusbarT(Integer id, String busbarNum, String weldingPower, String weldingSpeed, String protectiveGasFlwo,
			String defocusing, String columnId, String uploadTime) {
		super();
		this.id = id;
		this.busbarNum = busbarNum;
		this.weldingPower = weldingPower;
		this.weldingSpeed = weldingSpeed;
		this.protectiveGasFlwo = protectiveGasFlwo;
		this.defocusing = defocusing;
		this.columnId = columnId;
		this.uploadTime = uploadTime;
	}
	public CMesBusbarT() {
		super();
	}


}

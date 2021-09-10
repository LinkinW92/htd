package com.skeqi.mes.pojo;
/**
 * CCD拍照
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午10:36:12
 */
public class CMesCcdPhotographT {

	private Integer id;
	private String ccdPhotographNum;   //ccd编码
	private String ccdPhotographHeight;   //高度
	private String ccdPhotographLocation;   //位置
	private String ccdPhotographpower_num;   //功率
	private String ccdphotographColumn_num;  //极柱数量编号
	private String uploadTime;   //上传时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCcdPhotographNum() {
		return ccdPhotographNum;
	}
	public void setCcdPhotographNum(String ccdPhotographNum) {
		this.ccdPhotographNum = ccdPhotographNum;
	}
	public String getCcdPhotographHeight() {
		return ccdPhotographHeight;
	}
	public void setCcdPhotographHeight(String ccdPhotographHeight) {
		this.ccdPhotographHeight = ccdPhotographHeight;
	}
	public String getCcdPhotographLocation() {
		return ccdPhotographLocation;
	}
	public void setCcdPhotographLocation(String ccdPhotographLocation) {
		this.ccdPhotographLocation = ccdPhotographLocation;
	}
	public String getCcdPhotographpower_num() {
		return ccdPhotographpower_num;
	}
	public void setCcdPhotographpower_num(String ccdPhotographpower_num) {
		this.ccdPhotographpower_num = ccdPhotographpower_num;
	}
	public String getCcdphotographColumn_num() {
		return ccdphotographColumn_num;
	}
	public void setCcdphotographColumn_num(String ccdphotographColumn_num) {
		this.ccdphotographColumn_num = ccdphotographColumn_num;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Override
	public String toString() {
		return "CMesCcdPhotographT [id=" + id + ", ccdPhotographNum=" + ccdPhotographNum + ", ccdPhotographHeight="
				+ ccdPhotographHeight + ", ccdPhotographLocation=" + ccdPhotographLocation + ", ccdPhotographpower_num="
				+ ccdPhotographpower_num + ", ccdphotographColumn_num=" + ccdphotographColumn_num + ", uploadTime="
				+ uploadTime + "]";
	}
	public CMesCcdPhotographT(Integer id, String ccdPhotographNum, String ccdPhotographHeight,
			String ccdPhotographLocation, String ccdPhotographpower_num, String ccdphotographColumn_num,
			String uploadTime) {
		super();
		this.id = id;
		this.ccdPhotographNum = ccdPhotographNum;
		this.ccdPhotographHeight = ccdPhotographHeight;
		this.ccdPhotographLocation = ccdPhotographLocation;
		this.ccdPhotographpower_num = ccdPhotographpower_num;
		this.ccdphotographColumn_num = ccdphotographColumn_num;
		this.uploadTime = uploadTime;
	}
	public CMesCcdPhotographT() {
		super();
	}


}

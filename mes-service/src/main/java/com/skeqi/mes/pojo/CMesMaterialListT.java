package com.skeqi.mes.pojo;

import java.util.Date;
import java.util.List;

public class CMesMaterialListT {
	private Integer id;//             INTEGER not null,
	private Date dt;//             DATE,
	private String listNo;//        VARCHAR2(100),
	private String listName;//      VARCHAR2(200),
	private String effectiveTime;// DATE,
	private String invalidTime;//   DATE,
	private String listVersion;//   INTEGER,
	private Integer lineId;//        INTEGER
	private String lineName;
	private Integer status;  //状态
	private String productType;//产品型号

	private String figureNo;
	private String materialNo;
	private String materialName;

	private Object objects;

	public Object getObjects() {
		return objects;
	}

	public void setObjects(Object objects) {
		this.objects = objects;
	}

	public String getFigureNo() {
		return figureNo;
	}

	public void setFigureNo(String figureNo) {
		this.figureNo = figureNo;
	}

	public String getMaterialNo() {
		return materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getListNo() {
		return listNo;
	}
	public void setListNo(String listNo) {
		this.listNo = listNo;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}


	public String getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public String getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getListVersion() {
		return listVersion;
	}
	public void setListVersion(String listVersion) {
		this.listVersion = listVersion;
	}

	public CMesMaterialListT() {
		super();
	}
	public CMesMaterialListT(Integer id, Date dt, String listNo, String listName, String effectiveTime,
			String invalidTime, String listVersion, Integer lineId, String lineName, Integer status) {
		super();
		this.id = id;
		this.dt = dt;
		this.listNo = listNo;
		this.listName = listName;
		this.effectiveTime = effectiveTime;
		this.invalidTime = invalidTime;
		this.listVersion = listVersion;
		this.lineId = lineId;
		this.lineName = lineName;
		this.status = status;
	}


}

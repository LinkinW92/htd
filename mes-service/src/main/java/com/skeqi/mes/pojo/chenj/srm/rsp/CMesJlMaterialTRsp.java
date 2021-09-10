package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * 物料表出参
 */
public class CMesJlMaterialTRsp {
	/**
     * 物料编码
	 */
	private String  bomId;
    /**
     * 物料名称
	 */
	private String  materialName;

	public String getBomId() {
		return bomId;
	}

	public void setBomId(String bomId) {
		this.bomId = bomId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	@Override
	public String toString() {
		return "CMesJlMaterialTReq{" +
				"bomId='" + bomId + '\'' +
				", materialName='" + materialName + '\'' +
				'}';
	}
}

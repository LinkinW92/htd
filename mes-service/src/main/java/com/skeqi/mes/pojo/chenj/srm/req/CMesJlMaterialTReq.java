package com.skeqi.mes.pojo.chenj.srm.req;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 物料表入参
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMesJlMaterialTReq {
	/**
	 * 物料编码
	 */
	private String  bomId;
	/**
	 * 物料名称
	 */
	private String  materialName;
	/**
	 * 当前页码
	 */
	private Integer  pageNum;
	/**
	 * 每页显示条数
	 */
	private Integer  pageSize;

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

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "CMesJlMaterialTReq{" +
				"bomId='" + bomId + '\'' +
				", materialName='" + materialName + '\'' +
				", pageNum=" + pageNum +
				", pageSize=" + pageSize +
				'}';
	}
}

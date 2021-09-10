package com.skeqi.mes.pojo;
/**
 * 模组报表记录表
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午11:40:10
 */
public class CMesModuleTableT {

	private Integer id;
	private String tableNo;
	private String tableName;
	private String dis;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTableNo() {
		return tableNo;
	}
	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	@Override
	public String toString() {
		return "CMesModuleTableT [id=" + id + ", tableNo=" + tableNo + ", tableName=" + tableName + ", dis=" + dis
				+ "]";
	}
	public CMesModuleTableT(Integer id, String tableNo, String tableName, String dis) {
		super();
		this.id = id;
		this.tableNo = tableNo;
		this.tableName = tableName;
		this.dis = dis;
	}
	public CMesModuleTableT() {
		super();
	}


}

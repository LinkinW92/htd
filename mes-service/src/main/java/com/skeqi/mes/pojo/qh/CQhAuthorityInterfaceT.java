package com.skeqi.mes.pojo.qh;

/**
 * @author yinp
 * @explain 权限接口
 * @date 2020-9-3
 */
public class CQhAuthorityInterfaceT {

	private Integer id;
	// 路径
	private String path;
	// 菜单id
	private Integer menuId;
	// 操作类型(1:增、2:删、3:改、4:查)
	private Integer operationType;
	// 描述
	private String describe;
	// 菜单
	private CQhMenuT menuT;

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public CQhMenuT getMenuT() {
		return menuT;
	}

	public void setMenuT(CQhMenuT menuT) {
		this.menuT = menuT;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

}

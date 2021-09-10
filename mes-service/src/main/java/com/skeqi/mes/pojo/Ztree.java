package com.skeqi.mes.pojo;

public class Ztree {

	private Integer id;
	private Integer pid;
	private String name;
	private String checked;
	private String open;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	@Override
	public String toString() {
		return "Ztree [id=" + id + ", pid=" + pid + ", name=" + name + ", checked=" + checked + ", open=" + open + "]";
	}
	public Ztree(Integer id, Integer pid, String name, String checked, String open) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.checked = checked;
		this.open = open;
	}
	public Ztree() {
		super();
	}


}

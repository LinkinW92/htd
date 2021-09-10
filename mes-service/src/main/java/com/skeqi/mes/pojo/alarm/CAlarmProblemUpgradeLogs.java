package com.skeqi.mes.pojo.alarm;

/**
 * 问题升级日志
 *
 * @author yinp
 *
 */
public class CAlarmProblemUpgradeLogs {

	private Integer id;
	private Integer faultId;// 问题编号
	private Integer beforeUpgradelevelId;// 升级前等级编号
	private Integer afterUpgradelevelId;// 升级后等级编号
	private String dt;// 升级时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFaultId() {
		return faultId;
	}

	public void setFaultId(Integer faultId) {
		this.faultId = faultId;
	}

	public Integer getBeforeUpgradelevelId() {
		return beforeUpgradelevelId;
	}

	public void setBeforeUpgradelevelId(Integer beforeUpgradelevelId) {
		this.beforeUpgradelevelId = beforeUpgradelevelId;
	}

	public Integer getAfterUpgradelevelId() {
		return afterUpgradelevelId;
	}

	public void setAfterUpgradelevelId(Integer afterUpgradelevelId) {
		this.afterUpgradelevelId = afterUpgradelevelId;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public CAlarmProblemUpgradeLogs() {
		super();
		// TODO Auto-generated constructor stub
	}

}

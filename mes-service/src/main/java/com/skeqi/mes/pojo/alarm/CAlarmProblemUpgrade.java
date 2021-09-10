package com.skeqi.mes.pojo.alarm;

import com.skeqi.mes.pojo.project.CMesLossTypeT;

/**
 * 问题升级
 *
 * @author yinp
 *
 */
public class CAlarmProblemUpgrade {

	private Integer id;
	private Integer problemLevelId;// 问题等级
	private Integer upgradeProblemLevelId;// 升级后的问题等级
	private Integer triggerTime;// 触发时间/分钟
	private Integer lossTypeId;// 事件id

	private CMesLossTypeT lossType;
	private CAlarmProblemLevel qLevel;
	private CAlarmProblemLevel hLevel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProblemLevelId() {
		return problemLevelId;
	}

	public void setProblemLevelId(Integer problemLevelId) {
		this.problemLevelId = problemLevelId;
	}

	public Integer getUpgradeProblemLevelId() {
		return upgradeProblemLevelId;
	}

	public void setUpgradeProblemLevelId(Integer upgradeProblemLevelId) {
		this.upgradeProblemLevelId = upgradeProblemLevelId;
	}

	public Integer getTriggerTime() {
		return triggerTime;
	}

	public void setTriggerTime(Integer triggerTime) {
		this.triggerTime = triggerTime;
	}

	public Integer getLossTypeId() {
		return lossTypeId;
	}

	public void setLossTypeId(Integer lossTypeId) {
		this.lossTypeId = lossTypeId;
	}

	public CMesLossTypeT getLossType() {
		return lossType;
	}

	public void setLossType(CMesLossTypeT lossType) {
		this.lossType = lossType;
	}

	public CAlarmProblemLevel getqLevel() {
		return qLevel;
	}

	public void setqLevel(CAlarmProblemLevel qLevel) {
		this.qLevel = qLevel;
	}

	public CAlarmProblemLevel gethLevel() {
		return hLevel;
	}

	public void sethLevel(CAlarmProblemLevel hLevel) {
		this.hLevel = hLevel;
	}

	@Override
	public String toString() {
		return "CAlarmProblemUpgrade [id=" + id + ", problemLevelId=" + problemLevelId + ", upgradeProblemLevelId="
				+ upgradeProblemLevelId + ", triggerTime=" + triggerTime + ", lossTypeId=" + lossTypeId + ", lossType="
				+ lossType + ", qLevel=" + qLevel + ", hLevel=" + hLevel + "]";
	}

	public CAlarmProblemUpgrade(Integer id, Integer problemLevelId, Integer upgradeProblemLevelId, Integer triggerTime,
			Integer lossTypeId, CMesLossTypeT lossType, CAlarmProblemLevel qLevel, CAlarmProblemLevel hLevel) {
		super();
		this.id = id;
		this.problemLevelId = problemLevelId;
		this.upgradeProblemLevelId = upgradeProblemLevelId;
		this.triggerTime = triggerTime;
		this.lossTypeId = lossTypeId;
		this.lossType = lossType;
		this.qLevel = qLevel;
		this.hLevel = hLevel;
	}

	public CAlarmProblemUpgrade() {
		super();
		// TODO Auto-generated constructor stub
	}

}

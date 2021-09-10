package com.skeqi.mes.pojo.alarm;

import com.skeqi.mes.pojo.CMesUserT;

/**
 * 问题
 *
 * @author yinp
 *
 */
public class CAlarmProblems {

	private Integer id;
	private String problem;//问题
	private String establishTime;//创建时间
	private String responseTime;//响应时间
	private String solveTime;//解决时间
	private Integer establishUserId;//创建用户id
	private Integer problemLevelId;//问题级别
	private Integer eventId;//事件

	private CMesUserT user;
	private CAlarmProblemLevel problemLevel;
	private CAlarmEvent event;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getEstablishTime() {
		return establishTime;
	}

	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getSolveTime() {
		return solveTime;
	}

	public void setSolveTime(String solveTime) {
		this.solveTime = solveTime;
	}

	public Integer getEstablishUserId() {
		return establishUserId;
	}

	public void setEstablishUserId(Integer establishUserId) {
		this.establishUserId = establishUserId;
	}

	public Integer getProblemLevelId() {
		return problemLevelId;
	}

	public void setProblemLevelId(Integer problemLevelId) {
		this.problemLevelId = problemLevelId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public CMesUserT getUser() {
		return user;
	}

	public void setUser(CMesUserT user) {
		this.user = user;
	}

	public CAlarmProblemLevel getProblemLevel() {
		return problemLevel;
	}

	public void setProblemLevel(CAlarmProblemLevel problemLevel) {
		this.problemLevel = problemLevel;
	}

	public CAlarmEvent getEvent() {
		return event;
	}

	public void setEvent(CAlarmEvent event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "CAlarmProblems [id=" + id + ", problem=" + problem + ", establishTime=" + establishTime
				+ ", responseTime=" + responseTime + ", solveTime=" + solveTime + ", establishUserId=" + establishUserId
				+ ", problemLevelId=" + problemLevelId + ", eventId=" + eventId + ", user=" + user + ", problemLevel="
				+ problemLevel + ", event=" + event + "]";
	}

	public CAlarmProblems(Integer id, String problem, String establishTime, String responseTime, String solveTime,
			Integer establishUserId, Integer problemLevelId, Integer eventId, CMesUserT user,
			CAlarmProblemLevel problemLevel, CAlarmEvent event) {
		super();
		this.id = id;
		this.problem = problem;
		this.establishTime = establishTime;
		this.responseTime = responseTime;
		this.solveTime = solveTime;
		this.establishUserId = establishUserId;
		this.problemLevelId = problemLevelId;
		this.eventId = eventId;
		this.user = user;
		this.problemLevel = problemLevel;
		this.event = event;
	}

	public CAlarmProblems() {
		super();
		// TODO Auto-generated constructor stub
	}

}

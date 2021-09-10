package com.skeqi.mes.controller.alarm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.pojo.alarm.CAlarmProblemUpgrade;
import com.skeqi.mes.pojo.project.CMesLossTypeT;
import com.skeqi.mes.service.alarm.CAlarmProblemUpgradeService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 问题升级
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping("api/AlarmProblemUpgrade")
public class CAlarmProblemUpgradeController {

	@Autowired
	CAlarmProblemUpgradeService service;

	/**
	 * 查询问题升级集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findProblemUpgradeList",method = RequestMethod.POST)
	public Rjson findProblemUpgradeList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<CAlarmProblemUpgrade> list = service.findProblemUpgradeList();
			PageInfo<CAlarmProblemUpgrade> pageInfo = new PageInfo<CAlarmProblemUpgrade>(list);

			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 新增问题升级集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addProblemUpgrade",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="策略", method="新增问题升级集合")
	public Rjson addProblemUpgrade(HttpServletRequest request) {
		try {
			Integer problemLevelId = EqualsUtil.integer(request, "problemLevelId", "问题等级", true);
			Integer upgradeProblemLevelId = EqualsUtil.integer(request, "upgradeProblemLevelId", "升级后的问题等级", true);
			Integer triggerTime = EqualsUtil.integer(request, "triggerTime", "触发时间", true);
			Integer lossTypeId = EqualsUtil.integer(request, "lossTypeId", "损失类型", true);

			if(problemLevelId>=upgradeProblemLevelId) {
				throw new Exception("升级后的等级一定要比升级前的等级高");
			}

			CAlarmProblemUpgrade dx = new CAlarmProblemUpgrade();
			dx.setProblemLevelId(problemLevelId);
			dx.setUpgradeProblemLevelId(upgradeProblemLevelId);
			dx.setTriggerTime(triggerTime);
			dx.setLossTypeId(lossTypeId);

			service.addProblemUpgrade(dx);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 更新新增问题升级集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateProblemUpgrade",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="策略", method="更新新增问题升级集合")
	public Rjson updateProblemUpgrade(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer problemLevelId = EqualsUtil.integer(request, "problemLevelId", "问题等级", true);
			Integer upgradeProblemLevelId = EqualsUtil.integer(request, "upgradeProblemLevelId", "升级后的问题等级", true);
			Integer triggerTime = EqualsUtil.integer(request, "triggerTime", "触发时间", true);
			Integer lossTypeId = EqualsUtil.integer(request, "lossTypeId", "损失类型", true);

			if(problemLevelId>=upgradeProblemLevelId) {
				throw new Exception("升级后的等级一定要比升级前的等级高");
			}

			CAlarmProblemUpgrade dx = new CAlarmProblemUpgrade();
			dx.setId(id);
			dx.setProblemLevelId(problemLevelId);
			dx.setUpgradeProblemLevelId(upgradeProblemLevelId);
			dx.setTriggerTime(triggerTime);
			dx.setLossTypeId(lossTypeId);

			service.updateProblemUpgrade(dx);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 删除新增问题升级集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteProblemUpgrade",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="策略", method="删除新增问题升级集合")
	public Rjson deleteProblemUpgrade(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteProblemUpgrade(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 查询所有 等级
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findProblemLevelAll",method = RequestMethod.POST)
	public Rjson findProblemLevelAll(HttpServletRequest request) {
		try {
			List<CAlarmProblemLevel> list = service.findProblemLevelAll();

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 查询所有损失类型
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findLossTypeAll",method = RequestMethod.POST)
	public Rjson findLossTypeAll(HttpServletRequest request) {
		try {
			List<CMesLossTypeT> list = service.findLossTypeAll();

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}

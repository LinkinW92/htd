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
import com.skeqi.mes.service.alarm.CAlarmProblemLevelService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 问题等级
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping("api/AlarmProblemLevel")
public class CAlarmProblemLevelController {

	@Autowired
	CAlarmProblemLevelService service;

	/**
	 * 查询问题等级
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findProblemLevel",method = RequestMethod.POST)
	public Rjson findProblemLevel(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<CAlarmProblemLevel> list = service.findProblemLevel();
			PageInfo<CAlarmProblemLevel> pageInfo = new PageInfo<CAlarmProblemLevel>(list);
			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 新增问题等级
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addProblemLevel",method = RequestMethod.POST)
	public Rjson addProblemLevel(HttpServletRequest request) {
		try {
			Integer level = EqualsUtil.integer(request, "level", "等级", true);
			String explain = EqualsUtil.string(request, "explain", "说明", true);

			CAlarmProblemLevel dx = new CAlarmProblemLevel();
			dx.setLevel(level);
			dx.setExplain(explain);

			service.addProblemLevel(dx);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 更新问题等级
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateProblemLevel",method = RequestMethod.POST)
	public Rjson updateProblemLevel(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer level = EqualsUtil.integer(request, "level", "等级", true);
			String explain = EqualsUtil.string(request, "explain", "说明", true);

			CAlarmProblemLevel dx = new CAlarmProblemLevel();
			dx.setId(id);
			dx.setLevel(level);
			dx.setExplain(explain);

			service.updateProblemLevel(dx);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 删除新增问题等级
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteProblemLevel",method = RequestMethod.POST)
	public Rjson deleteProblemLevel(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteProblemLevel(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}

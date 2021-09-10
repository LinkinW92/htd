package com.skeqi.mes.service.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesNoticeT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;

public interface ProduceService {

	//班次列表
	public List<CMesShiftsTeamT> findAllShift(CMesShiftsTeamT t) throws ServicesException;

	//根据id查询班次
	public CMesShiftsTeamT findShiftByid(Integer id) throws ServicesException;

	//添加
	public Integer addShift(CMesShiftsTeamT t) throws ServicesException;

	//修改
	public Integer updateShift(CMesShiftsTeamT t) throws ServicesException;

	//删除
	public Integer delShift(Integer id) throws ServicesException;

	//班组列表
	public List<CMesEmpTeamT> findAllTeam(CMesEmpTeamT t) throws ServicesException;

	//根据id查询班组信息
	public CMesEmpTeamT findTeamByid(Integer id) throws ServicesException;

	//添加班组
	public Integer addTeam(CMesEmpTeamT t) throws ServicesException;

	//修改班组
	public Integer updateTeam(CMesEmpTeamT t) throws ServicesException;

	//删除班组
	public Integer delTeam(Integer id) throws ServicesException;

	//通知管理列表
	public List<CMesNoticeT> findAllNotice(CMesNoticeT t) throws ServicesException;

	//根据id查询通知列表
	public CMesNoticeT findNoticeByid(Integer id) throws ServicesException;

	//根据班次查询班组长
	public List<String> findTeams(Integer id) throws ServicesException;

	//添加通知
	public Integer addNotice(CMesNoticeT t) throws ServicesException;

	//修改通知
	public Integer updateNotice(CMesNoticeT t) throws ServicesException;

	//删除通知
	public Integer delNotice(Integer id) throws ServicesException;

	//员工类型列表
	public List<CMesEmpTypeT> findAllEmpType(CMesEmpTypeT c) throws ServicesException;

	//根据id查询员工类型
	public CMesEmpTypeT findEmpTypeByid(Integer id) throws ServicesException;

	//添加
	public Integer addEmpType(CMesEmpTypeT t) throws ServicesException;

	//修改
	public Integer updateEmpType(CMesEmpTypeT t) throws ServicesException;

	//删除
	public Integer delEmpType(Integer id) throws ServicesException;

	//员工列表
	public List<CMesEmpT> findAllEmp(CMesEmpT c) throws ServicesException;

	//根据id查询员工列表
	public CMesEmpT findEmpByid(Integer id) throws ServicesException;

	//添加员工
	public Integer addEmp(CMesEmpT t) throws ServicesException;

	//修改员工
	public Integer updateEmp(CMesEmpT t) throws ServicesException;

	//删除员工
	public Integer delEmp(Integer id) throws ServicesException;

}

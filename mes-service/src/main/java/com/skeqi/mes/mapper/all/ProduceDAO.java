package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesNoticeT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;

public interface ProduceDAO {

	//班次列表
	public List<CMesShiftsTeamT> findAllShift(CMesShiftsTeamT t);

	//根据id查询班次
	public CMesShiftsTeamT findShiftByid(Integer id);

	//添加
	public Integer addShift(CMesShiftsTeamT t);

	//修改
	public Integer updateShift(CMesShiftsTeamT t);

	//删除
	public Integer delShift(Integer id);

	//班组列表
	public List<CMesEmpTeamT> findAllTeam(CMesEmpTeamT t);

	//根据id查询班组信息
	public CMesEmpTeamT findTeamByid(Integer id);

	//获取最大的班组id
	public Integer getMaxTeamId();

	//添加班组
	public Integer addTeam(CMesEmpTeamT t);

	//添加班组和班次中间表
	public Integer addShiftTeam(@Param("empTeamId")Integer empTeamId,@Param("shiftsTeamId")Integer shiftsTeamId);
	//修改班组
	public Integer updateTeam(CMesEmpTeamT t);

	//删除班组
	public Integer delTeam(Integer id);

	//删除班组班次中间表
	public Integer delShiftTeam(Integer id);

	//通知管理列表
	public List<CMesNoticeT> findAllNotice(CMesNoticeT t);

	//根据id查询通知列表
	public CMesNoticeT findNoticeByid(Integer id);

	//根据班次查询班组长
	public List<String> findTeams(Integer id);

	//添加通知
	public Integer addNotice(CMesNoticeT t);

	//修改通知
	public Integer updateNotice(CMesNoticeT t);

	//删除通知
	public Integer delNotice(Integer id);

	//员工类型列表
	public List<CMesEmpTypeT> findAllEmpType(CMesEmpTypeT c);

	//根据id查询员工类型
	public CMesEmpTypeT findEmpTypeByid(Integer id);

	//添加
	public Integer addEmpType(CMesEmpTypeT t);

	//修改
	public Integer updateEmpType(CMesEmpTypeT t);

	//删除
	public Integer delEmpType(Integer id);

	//员工列表
	public List<CMesEmpT> findAllEmp(CMesEmpT c);

	//根据id查询员工列表
	public CMesEmpT findEmpByid(Integer id);

	//添加员工
	public Integer addEmp(CMesEmpT t);

	//修改员工
	public Integer updateEmp(CMesEmpT t);

	//删除员工
	public Integer delEmp(Integer id);
}

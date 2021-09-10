package com.skeqi.mes.mapper.wll;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesNoticeT;


public interface NoticeTDao {
	List<CMesNoticeT> noticeList(Map<String, Object> map);

	//初始化通知人姓名
	List<String> getinItNoticeName(@Param("getShiftTeam")Integer getShiftTeam);
	//通过产线获取班组名
	List<CMesEmpTeamT> getShiftsNameList();

	void addNotice(@Param("head")String head, @Param("noticeContent")String noticeContent, @Param("s")String s,@Param("getDate")String getDate);

}

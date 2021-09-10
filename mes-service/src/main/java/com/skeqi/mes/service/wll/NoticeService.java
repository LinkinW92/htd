package com.skeqi.mes.service.wll;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesNoticeT;


public interface NoticeService {

	List<CMesNoticeT> noticeList(Map<String, Object> map);

	//初始化通知人姓名
	List<String> getinItNoticeName(Integer getShiftTeam);

	//通过产线获取班组名
	List<CMesEmpTeamT> getShiftsNameList();
	void addNotice(String head, String noticeContent, String s,String getDate);

}

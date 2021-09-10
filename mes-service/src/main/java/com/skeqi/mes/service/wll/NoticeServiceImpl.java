package com.skeqi.mes.service.wll;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.wll.NoticeTDao;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesNoticeT;

@Service
@Transactional
public class NoticeServiceImpl  implements NoticeService{

	@Autowired
	NoticeTDao NoticeDao;

	@Override
	public List<CMesNoticeT> noticeList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return NoticeDao.noticeList(map);
	}

	//初始化通知人姓名
	@Override
	public List<String> getinItNoticeName(Integer getShiftTeam) {
		// TODO Auto-generated method stub
		return NoticeDao.getinItNoticeName(getShiftTeam);
	}
	//通过产线获取班组名
	@Override
	public List<CMesEmpTeamT> getShiftsNameList() {
		// TODO Auto-generated method stub
		return NoticeDao.getShiftsNameList();
	}
	//添加
	@Override
	public void addNotice(String head, String noticeContent, String s,String getDate) {
		// TODO Auto-generated method stub
		NoticeDao.addNotice(head, noticeContent,s,getDate);
	}



}

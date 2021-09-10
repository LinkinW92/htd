package com.skeqi.mes.service.wjp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.wjp.ReasonManagerDao;
import com.skeqi.mes.pojo.CMesDefectResionT;

@Service
public class ReasonManagerServiceImpl implements ReasonManagerService{

	@Autowired
	private ReasonManagerDao reasonManagerDao;

	//原因列表
	@Transactional
	@Override
	public List<CMesDefectResionT> findAll() {
		return reasonManagerDao.findAll();
	}
	//原因添加
	@Transactional
	@Override
	public void addReason(@SuppressWarnings("rawtypes") Map map) {
		reasonManagerDao.addReason(map);
	}
	//原因删除
	@Transactional
	@Override
	public void removeReason(int id) {
		reasonManagerDao.removeReason(id);
	}
	//根据Id查询
	@Transactional
	@Override
	public CMesDefectResionT findById(int id) {
		return reasonManagerDao.findById(id);
	}
	//原因修改
	@Transactional
	@Override
	public void updateReason(Map map) {
		reasonManagerDao.updateReason(map);
	}
	//编号不能重复
	@Transactional
	@Override
	public int noRepeat(String string) {
		return reasonManagerDao.noRepeat(string);
	}

}

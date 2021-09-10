package com.skeqi.mes.service.fqz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.HeavyDAO;
import com.skeqi.mes.pojo.RMesNewSn;
import com.skeqi.mes.pojo.RMesPlanPrintT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;

@Service
public class HeavyServiceImpl implements HeavyService {

	@Autowired
	private HeavyDAO dao;
	@Override
	public List<RMesPlanT> findRmesPlanT() {
		// TODO Auto-generated method stub
		return dao.findRmesPlanT();
	}
	@Override
	public Integer jugdeSN(String sn) {
		// TODO Auto-generated method stub
		return dao.jugdeSN(sn);
	}
	@Override
	public List<RMesWorkorderDetailT> findDetil(Integer id) {
		// TODO Auto-generated method stub
		return dao.findDetil(id);
	}
	@Override
	public Integer findPrint(Integer id) {
		// TODO Auto-generated method stub
		return dao.findPrint(id);
	}
	@Override
	public List<RMesPlanPrintT> findSn(Integer id) {
		// TODO Auto-generated method stub
		return dao.findSn(id);
	}
	@Override
	public void insertnewsn(String newsn, String oldsn) {
		// TODO Auto-generated method stub
		dao.insertnewsn(newsn, oldsn);
	}
	@Override
	public String findNewSN(Integer id) {
		// TODO Auto-generated method stub
		return dao.findNewSN(id);
	}
	@Override
	public List<RMesNewSn> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	@Override
	public Integer jugdeoldSN(String sn) {
		// TODO Auto-generated method stub
		return dao.jugdeoldSN(sn);
	}
	@Override
	public Integer jugdenewSN(String sn) {
		// TODO Auto-generated method stub
		return dao.jugdenewSN(sn);
	}
	@Override
	public Integer jugdeoldSN_New(String sn) {
		// TODO Auto-generated method stub
		return dao.jugdeoldSN_New(sn);
	}

}

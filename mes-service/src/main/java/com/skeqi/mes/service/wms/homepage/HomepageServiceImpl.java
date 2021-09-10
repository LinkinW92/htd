package com.skeqi.mes.service.wms.homepage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.homepage.HomepageDAO;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsStorageListDetailT;


@Service
public class HomepageServiceImpl implements HomepageService {

	@Autowired
	private HomepageDAO homepageDAO;

	@Override
	public Integer outboundQuantityQuery(String ks,String js) {
		// TODO Auto-generated method stub
		return homepageDAO.outboundQuantityQuery(ks,js);
	}

	@Override
	public Integer inputVolumeQuery(String ks,String js) {
		// TODO Auto-generated method stub
		return homepageDAO.inputVolumeQuery(ks,js);
	}

	@Override
	public Integer inventoryMarginQuery() {
		// TODO Auto-generated method stub
		return homepageDAO.inventoryMarginQuery();
	}

	@Override
	public Integer messageNumberQuery() {
		// TODO Auto-generated method stub
		return homepageDAO.messageNumberQuery();
	}

	@Override
	public List<CWmsStorageListDetailT> rkfind(String ks,String js) {
		// TODO Auto-generated method stub
		return homepageDAO.rkfind(ks,js);
	}

	@Override
	public List<CWmsStorageListDetailT> ckfind(String ks,String js) {
		// TODO Auto-generated method stub
		return homepageDAO.ckfind(ks,js);
	}

	@Override
	public List<CWmsLocationT> cWmsLocationTQuery() {
		// TODO Auto-generated method stub
		return homepageDAO.cWmsLocationTQuery();
	}

}

package com.skeqi.mes.service.clientpur;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.clientpur.ClientPurviewDao;
import com.skeqi.mes.pojo.CMesClientPurviewT;

@Service
public class ClientPurviewServiceImpl implements ClientPurviewService {

	@Autowired
	private ClientPurviewDao cpdao;

	@Override
	public void addClientPurview(Map<String, Object> map) {
		// TODO Auto-generated method stub
		cpdao.addClientPurview(map);
	}

	@Override
	public List<CMesClientPurviewT> clientPurviewList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cpdao.clientPurviewList(map);
	}

	@Override
	public void updateClientPur(Map<String, Object> map) {
		// TODO Auto-generated method stub
		cpdao.updateClientPur(map);
	}

	@Override
	public void delClientPur(Map<String, Object> map) {
		// TODO Auto-generated method stub
		cpdao.delClientPur(map);
	}

}

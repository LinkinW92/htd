package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.zch.ModuleServerDao;

@Service
public class ModuleServerServiceImpl implements ModuleServerService {
	@Autowired
	ModuleServerDao dao;

	@Override
	public List<Map<String, Object>> findDataConfList(Map<String, Object> map) {
		return dao.findDataConfList(map);
	}

	@Override
	public Integer addDataConf(Map<String, Object> map) {
		return dao.addDataConf(map);
	}

	@Override
	public Integer editDataConf(Map<String, Object> map) {
		return dao.editDataConf(map);
	}

	@Override
	public Integer deleteDataConf(Map<String, Object> map) {
		return dao.deleteDataConf(map);
	}

	@Override
	public List<Map<String, Object>> findMesInterfaceTypeList(Map<String, Object> map) {
		return dao.findMesInterfaceTypeList(map);
	}

	@Override
	public List<Map<String, Object>> findMesInterfaceParaList(Map<String, Object> map) {
		return dao.findMesInterfaceParaList(map);
	}

	@Override
	public Integer addMesInterfacePara(Map<String, Object> map) {
		return dao.addMesInterfacePara(map);
	}

	@Override
	public Integer editMesInterfacePara(Map<String, Object> map) {
		return dao.editMesInterfacePara(map);
	}

	@Override
	public Integer deleteMesInterfacePara(Map<String, Object> map) {
		return dao.deleteMesInterfacePara(map);
	}

	@Override
	public List<Map<String, Object>> findDataCollectList(Map<String, Object> map) {
		return dao.findDataCollectList(map);
	}

	@Override
	public List<Map<String, Object>> findDataCollectParaList(Map<String, Object> map) {
		return dao.findDataCollectParaList(map);
	}

	@Override
	public List<Map<String, Object>> findStationList(Map<String, Object> map) {
		return dao.findStationList(map);
	}

	@Override
	public List<Map<String, Object>> exportDataCollectParaList(Map<String, Object> map) {
		return dao.exportDataCollectParaList(map);
	}
}

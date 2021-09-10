package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.zch.HookDao;
import com.skeqi.mes.service.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigTService;
import com.skeqi.mes.util.Rjson;

@Service
public class HookServiceImpl implements HookService {
	@Autowired
	HookDao dao;
	@Autowired
	MesNcCodeConfigTService ncCodeService;

	@Override
	public List<Map<String, Object>> findJobList(Map<String, Object> map) {
		return dao.findJobList(map);
	}

	@Override
	public Integer addJobBinding(Map<String, Object> map) {
		Map<String, Object> jobBindingMap = dao.getJobBindingRepetition(map);
		if(jobBindingMap != null) {
			return -1;
		}
		return dao.addJobBinding(map);
	}

	@Override
	public Integer editJobBindingStatus(Map<String, Object> map) {
		return dao.editJobBindingStatus(map);
	}

	@Override
	public Integer deleteJobBinding(Map<String, Object> map) {
		return dao.deleteJobBinding(map);
	}

	@Override
	public List<Map<String, Object>> findJobBindingList(Map<String, Object> map) {
		return dao.findJobBindingList(map);
	}

	@Override
	public Boolean checkJob(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> jobList = dao.findJobBindingListByStationId(map);
		for (Map<String, Object> job : jobList) {
			String jobName = job.get("name").toString();
			switch (jobName) {
			case "ncCode":
				// 检验sn
				Rjson rjson = ncCodeService.verifySn(map.get("sn").toString());
				if(rjson.getCode() != 200) {
					return false;
				}
				break;

			default:
				break;
			}
		}
		return true;
	}

}

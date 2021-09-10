package com.skeqi.mes.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesAndonInfoDAO;
import com.skeqi.mes.mapper.project.CMesSchedulingDAO;
import com.skeqi.mes.pojo.project.InsertInfo;

@Service
public class CMesAndonInfoServiceImpl implements CMesAndonInfoService {

	@Autowired
	CMesAndonInfoDAO dao;

	@Autowired
	CMesSchedulingDAO schedao;

	@Override
	public List<InsertInfo> findAllInfo(Integer workId, String lineName, String stationName, String sn,
			String startDate, String endDate) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllInfo(workId, lineName, stationName, sn, startDate, endDate);
	}

	@Override
	public Integer delAndonInfo(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if (id == null || id == 0) {
			throw new ParameterNullException("id不能为空", 200);
		}
	  System.err.println("id==="+id);
		Map<String, Integer> findAllId = dao.findAllId(id); // 获取工单、计划、排班id
		Integer planId = 0;
		Integer scheId = 0;
		Integer workId = 0;
		System.err.println("2222=="+findAllId.size());
		if (findAllId != null) {
			if (findAllId.get("planId") != null) {
				planId = Integer.parseInt(findAllId.get("planId").toString());
			}
			if (findAllId.get("scheId") != null) {
				scheId = Integer.parseInt(findAllId.get("scheId").toString());
			}
			if (findAllId.get("workId") != null) {
				workId = Integer.parseInt(findAllId.get("workId").toString());
			}
			dao.updatePlanComplete(planId); // 计划完成数量-1
			dao.updateScheComplete(scheId); // 排班完成数量-1
			dao.updateWorkComplete(workId); // 工单完成数量-1
		}

		return dao.delAndonInfo(id);
	}

	@Override
	public Integer InsertAndonInfo(String stationName, String dt, String sn, Integer countType, Integer workId,
			Integer number) throws ServicesException {
		// TODO Auto-generated method stub
//	    Integer  sum=mapper.findWorkBySn(sn);
//	    if(sum>0){
//	    	throw new ParameterNullException("当前SN已存在", 200);
//	    }
		Map<String, Object> findCompleteByWorkId = schedao.findCompleteByWorkId(workId); // 产品标记
		String productMark = findCompleteByWorkId.get("productMark").toString(); // 产品标记
		int scheId = Integer.parseInt(findCompleteByWorkId.get("id").toString()); // 排班id
		int planId = Integer.parseInt(findCompleteByWorkId.get("planId").toString()); // 计划id
		String lineName = dao.findLineName(scheId); // 产线名称
	//	System.err.println(lineName+"==="+stationName+"==="+dt+"==="+sn+"==="+productMark+"===="+countType+"===="+workId);
		InsertInfo info = new InsertInfo();
		info.setLineName(lineName);
		info.setStationName(stationName);
		info.setDt(dt);
		info.setSn(sn);
		info.setProductMark(productMark);
		info.setCountType(countType);
		info.setWorkId(workId);
		if (number == 0) {
			number = 1;
		}
		schedao.addWorkNumber(workId, number); // 修改工单数量
		schedao.addScheNumber(scheId, number); // 修改排班数量
		schedao.addPlaNumber(planId, number); // 修改计划数量

		for (int i = 0; i < number; i++) {
			dao.insertInfo(info);
		}
		return 1;
	}

	@Override
	public String findByLineName(String lineId) {
		// TODO Auto-generated method stub
		return dao.findByLineName(lineId);
	}

}

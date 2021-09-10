package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.ApprovalResultDao;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * 审批结果
 *
 * @author yinp
 *
 */
@Service
public class ApprovalResultServiceImpl implements ApprovalResultService {

	@Autowired
	ApprovalResultDao dao;

	@Override
	public List<CWmsApprovalT> findApprovalList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findApprovalList(map);
	}

	@Override
	public List<CWmsStorageDetailT> findStorageDetailList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findStorageDetailList(map);
	}

	@Override
	public List<CWmsStorageDetailT> findPStorageDetailList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findPStorageDetailList(map);
	}

}

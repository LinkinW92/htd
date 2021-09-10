package com.skeqi.mes.service.wf;

import com.skeqi.mes.pojo.chenj.srm.req.CMesFactoryTReq;
import com.skeqi.mes.pojo.chenj.srm.req.CWmsDepartmentTReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CWmsDepartmentTRsp;
import com.skeqi.mes.pojo.qh.CMesFactoryT;
import com.skeqi.mes.util.Rjson;

import java.util.List;

public interface CMesFactoryTService{

    List<CMesFactoryT> findFactoryAll(String companyName,String factoryCode,String factoryName);

    Rjson addFactory(CMesFactoryT factoryT) throws Exception;

    Rjson editFactory(CMesFactoryT factoryT) throws Exception;

    Rjson delFactoryByIdAndCode(CMesFactoryT factoryT) throws Exception;

    Rjson findFactoryList(CMesFactoryTReq req);

	// 查询部门编码和部门名称
	Rjson findDepartmentData(CWmsDepartmentTReq req);
}

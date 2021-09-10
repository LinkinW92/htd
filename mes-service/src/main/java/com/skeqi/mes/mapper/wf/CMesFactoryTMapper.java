package com.skeqi.mes.mapper.wf;

import com.skeqi.mes.pojo.chenj.srm.req.CMesFactoryTReq;
import com.skeqi.mes.pojo.chenj.srm.req.CWmsDepartmentTReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CMesFactoryTRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CWmsDepartmentTRsp;
import com.skeqi.mes.pojo.qh.CMesFactoryT;
import com.skeqi.mes.util.Rjson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CMesFactoryTMapper {
    List<CMesFactoryT> findFactoryAll(@Param("companyName") String companyName,@Param("factoryCode") String factoryCode,@Param("factoryName") String factoryName);

    Integer addFactory(CMesFactoryT factoryT);

    List<CMesFactoryT> selectFactoryByCode(@Param("factoryCode")String factoryCode);

    Integer editFactory(CMesFactoryT factoryT);

    Integer delFactoryByIdAndCode(CMesFactoryT factoryT);

    List<CMesFactoryT> selectFactoryById(@Param("id")Integer id);

    List<CMesFactoryT> selectFactoryByCompanyCode(@Param("companyCode") String companyCode);

    Integer delFactoryByCompanyCode(@Param("companyCode")String companyCode);

    List<CMesFactoryTRsp> findFactoryList(CMesFactoryTReq req);

	// 查询部门编码和部门名称
	List<CWmsDepartmentTRsp> findDepartmentData(CWmsDepartmentTReq req);
}

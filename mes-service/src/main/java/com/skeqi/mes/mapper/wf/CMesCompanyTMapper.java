package com.skeqi.mes.mapper.wf;

import com.skeqi.mes.pojo.qh.CMesCompanyT;
import com.skeqi.mes.pojo.qh.CMesFactoryT;
import com.skeqi.mes.util.Rjson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CMesCompanyTMapper {

    List<CMesCompanyT> findCompanyAll(@Param("companyCode") String companyCode,@Param("companyName") String companyName);

    Integer addCompany(CMesCompanyT cMesCompanyT);

    List<CMesCompanyT> selectCompanyByCode(@Param("companyCode")String companyCode);

    Integer editCompany(CMesCompanyT cMesCompanyT);

    Integer delCompanyByIdAndCode(CMesCompanyT factoryT);
}

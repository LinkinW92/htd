package com.skeqi.mes.service.wf;

import com.skeqi.mes.pojo.qh.CMesCompanyT;
import com.skeqi.mes.pojo.qh.CMesFactoryT;
import com.skeqi.mes.util.Rjson;

import java.util.List;

public interface CMesCompanyTService{

    List<CMesCompanyT> findCompanyAll(String companyCode,String companyName);

    Rjson addCompany(CMesCompanyT cMesCompanyT) throws Exception;

    Rjson editCompany(CMesCompanyT cMesCompanyT) throws Exception;

    Rjson delCompanyByIdAndCode(CMesCompanyT factoryT) throws Exception;
}

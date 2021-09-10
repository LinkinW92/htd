package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

public interface QuotationChangeManagementHService {

    public List<Map<String,Object>> showQuotationChangeHInfo( String companyCode,  String applicant);
    //查询头表

    public List<Map<String,Object>> showQuotationChangeHInfoEx(String changeRecordNo);
    //查询行表

    public List<Map<String,Object>> showQuotationChangeRInfoEx(String changeRecordNo);

    //	查询行表单号数据1

    public List<Map<String,Object>> showQuotationChangeRInfoByCode(String changeRecordNo);

    //删除指定行数据1

    public Integer delQuotationChangeRDataByLineNum(String changeRecordNo,String lineNumber);

    //新增头表数据1

    public Integer addQuotationChangeHInfo(String changeRecordNo,String customerID,String applicant,String applicationTime,
                                           String reasonForChange,String companyCode);

    //	更新头表数据1
    public Integer updateQuotationChangeHInfoH(String applicant,String applicationTime,String changeRecordNo,String customerID,String companyCode,String status,String reasonForChange);


    //	查询指定最大行数据

    public String showLineNumberChange(String changeRecordNo);

    //	新增行表数据1

    public Integer addQuotationChangeRInfo(String changeRecordNo,String lineNumber,String materialCode,String materialName,String originalQuotation,String newOffer,
                                          String unit);

    //    删除指定行信息

    public Integer updateQuotationChangeHInfoR(String materialCode,String materialName,String originalQuotation,String newOffer,String unit,String changeRecordNo,String lineNumber);

    //删除头数据1

    public Integer delQuotationChangeHData(String changeRecordNo);

    //删除行数据1

    public Integer delQuotationChangeRData(String changeRecordNo);
}

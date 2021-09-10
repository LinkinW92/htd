package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

public interface QuotationManagementHService {

    public List<Map<String, Object>> showQuotationHInfo(String quotationRecordNo, String companyCode, String customerID);

    public List<Map<String, Object>> showQuotationHInfoByCode(String quotationRecordNo);

    public List<Map<String, Object>> showQuotationRInfoByCode(String quotationRecordNo);

    public Integer delQuotationHData(String quotationRecordNo);


    public Integer delQuotationRData(String quotationRecordNo);


    public Integer delQuotationRDataByLineNum(String quotationRecordNo, String lineNumber);


    public Integer addQuotationHInfo(String quotationRecordNo, String customerID, String creationTime, String founder,
                                     String reviser, String companyCode);


    public Integer addQuotationRInfo(String quotationRecordNo, String lineNumber, String materialCode, String materialName, String costPrice, String offer,
                                     String priceUnit);


    public List<Map<String, Object>> showQuotationHMaxIdData(String quotationRecordNo);


    public Integer updateQuotationHInfoH(String reviser, String revisionTime, String quotationRecordNo, String customerID, String companyCode, String status);


    public Integer updateQuotationHInfoR(String materialCode, String materialName, String costPrice, String offer, String priceUnit, String quotationRecordNo, String lineNumber);


    public List<Map<String, Object>> showQuotationHDataById(String id);


    public String showLineNumber(String quotationRecordNo);


    public List<Map<String, Object>> showQuotationRById(String quotationRecordNo);


    public Integer countRNum(String quotationRecordNo);
}

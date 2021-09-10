package com.skeqi.mes.service.crm;


import com.skeqi.mes.mapper.crm.QuotationManagementHDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuotationManagementHServiceImpl implements QuotationManagementHService {
    @Autowired
    private QuotationManagementHDao dao;
    @Override
    public List<Map<String, Object>> showQuotationHInfo(String quotationRecordNo, String companyCode, String customerID) {
        return dao.showQuotationHInfo(quotationRecordNo, companyCode, customerID);
    }

    @Override
    public List<Map<String, Object>> showQuotationHInfoByCode(String quotationRecordNo) {
        return dao.showQuotationHInfoByCode(quotationRecordNo);
    }

    @Override
    public List<Map<String, Object>> showQuotationRInfoByCode(String quotationRecordNo) {
        return dao.showQuotationRInfoByCode(quotationRecordNo);
    }

    @Override
    public Integer delQuotationHData(String quotationRecordNo) {
        return dao.delQuotationHData(quotationRecordNo);
    }

    @Override
    public Integer delQuotationRData(String quotationRecordNo) {
        return dao.delQuotationRData(quotationRecordNo);
    }

    @Override
    public Integer delQuotationRDataByLineNum(String quotationRecordNo, String lineNumber) {
        return dao.delQuotationRDataByLineNum(quotationRecordNo, lineNumber);
    }

    @Override
    public Integer addQuotationHInfo(String quotationRecordNo, String customerID, String creationTime, String founder, String reviser, String companyCode) {
        return dao.addQuotationHInfo(quotationRecordNo, customerID, creationTime, founder, reviser, companyCode);
    }

    @Override
    public Integer addQuotationRInfo(String quotationRecordNo, String lineNumber, String materialCode, String materialName, String costPrice, String offer, String priceUnit) {
        return dao.addQuotationRInfo(quotationRecordNo, lineNumber, materialCode, materialName, costPrice, offer, priceUnit);
    }

    @Override
    public List<Map<String, Object>> showQuotationHMaxIdData(String quotationRecordNo) {
        return dao.showQuotationHMaxIdData(quotationRecordNo);
    }

    @Override
    public Integer updateQuotationHInfoH(String reviser, String revisionTime, String quotationRecordNo, String customerID, String companyCode, String status) {
        return dao.updateQuotationHInfoH(reviser, revisionTime, quotationRecordNo, customerID, companyCode, status);
    }

    @Override
    public Integer updateQuotationHInfoR(String materialCode, String materialName, String costPrice, String offer, String priceUnit, String quotationRecordNo, String lineNumber) {
        return dao.updateQuotationHInfoR(materialCode, materialName, costPrice, offer, priceUnit, quotationRecordNo, lineNumber);
    }

    @Override
    public List<Map<String, Object>> showQuotationHDataById(String id) {
        return dao.showQuotationHDataById(id);
    }

    @Override
    public String showLineNumber(String quotationRecordNo) {
        return dao.showLineNumber(quotationRecordNo);
    }

    @Override
    public List<Map<String, Object>> showQuotationRById(String quotationRecordNo) {
        return dao.showQuotationRById(quotationRecordNo);
    }

    @Override
    public Integer countRNum(String quotationRecordNo) {
        return dao.countRNum(quotationRecordNo);
    }
}

package com.skeqi.mes.service.crm;


import com.skeqi.mes.mapper.crm.QuotationChangeManagementHDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuotationChangeManagementHServiceImpl implements QuotationChangeManagementHService {
    @Autowired
    private QuotationChangeManagementHDao dao;

    @Override
    public List<Map<String, Object>> showQuotationChangeHInfo(String companyCode, String applicant) {
        return dao.showQuotationChangeHInfo(companyCode, applicant);
    }

    @Override
    public List<Map<String, Object>> showQuotationChangeHInfoEx(String changeRecordNo) {
        return dao.showQuotationChangeHInfoEx(changeRecordNo);
    }

    @Override
    public List<Map<String, Object>> showQuotationChangeRInfoEx(String changeRecordNo) {
        return dao.showQuotationChangeRInfoEx(changeRecordNo);
    }

    @Override
    public List<Map<String, Object>> showQuotationChangeRInfoByCode(String changeRecordNo) {
        return dao.showQuotationChangeRInfoByCode(changeRecordNo);
    }

    @Override
    public Integer delQuotationChangeRDataByLineNum(String changeRecordNo, String lineNumber) {
        return dao.delQuotationChangeRDataByLineNum(changeRecordNo, lineNumber);
    }

    @Override
    public Integer addQuotationChangeHInfo(String changeRecordNo, String customerID, String applicant, String applicationTime, String reasonForChange, String companyCode) {
        return dao.addQuotationChangeHInfo(changeRecordNo, customerID, applicant, applicationTime, reasonForChange, companyCode);
    }

    @Override
    public Integer updateQuotationChangeHInfoH(String applicant, String applicationTime, String changeRecordNo, String customerID, String companyCode, String status, String reasonForChange) {
        return dao.updateQuotationChangeHInfoH(applicant, applicationTime, changeRecordNo, customerID, companyCode, status, reasonForChange);
    }

    @Override
    public String showLineNumberChange(String changeRecordNo) {
        return dao.showLineNumberChange(changeRecordNo);
    }

    @Override
    public Integer addQuotationChangeRInfo(String changeRecordNo, String lineNumber, String materialCode, String materialName, String originalQuotation, String newOffer, String unit) {
        return dao.addQuotationChangeRInfo(changeRecordNo, lineNumber, materialCode, materialName, originalQuotation, newOffer, unit);
    }

    @Override
    public Integer updateQuotationChangeHInfoR(String materialCode, String materialName, String originalQuotation, String newOffer, String unit, String changeRecordNo, String lineNumber) {
        return dao.updateQuotationChangeHInfoR(materialCode, materialName, originalQuotation, newOffer, unit, changeRecordNo, lineNumber);
    }

    @Override
    public Integer delQuotationChangeHData(String changeRecordNo) {
        return dao.delQuotationChangeHData(changeRecordNo);
    }

    @Override
    public Integer delQuotationChangeRData(String changeRecordNo) {
        return dao.delQuotationChangeRData(changeRecordNo);
    }
}

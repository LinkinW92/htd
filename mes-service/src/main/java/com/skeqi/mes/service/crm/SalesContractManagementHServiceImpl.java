package com.skeqi.mes.service.crm;

import com.skeqi.mes.mapper.crm.SalesContractManagementHDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SalesContractManagementHServiceImpl implements SalesContractManagementHService {

	@Autowired
	private SalesContractManagementHDao dao;
	@Override
	public List<Map<String, Object>> showContractHInfo(String contractNo, String companyCode, String customerID) {
		return dao.showContractHInfo(contractNo, companyCode, customerID);
	}

	@Override
	public List<Map<String, Object>> showContractHInfoByCode(String contractNo) {
		return dao.showContractHInfoByCode(contractNo);
	}

	@Override
	public List<Map<String, Object>> showContractRInfoByCode(String contractNo) {
		return dao.showContractRInfoByCode(contractNo);
	}

	@Override
	public Integer delContractHData(String contractNo) {
		return dao.delContractHData(contractNo);
	}

	@Override
	public Integer delContractRData(String contractNo) {
		return dao.delContractRData(contractNo);
	}

	@Override
	public Integer delContractRDataByLineNum(String contractNo, String lineNumber) {
		return dao.delContractRDataByLineNum(contractNo, lineNumber);
	}

	@Override
	public Integer addContractHInfo(String contractNo, String customerID, String creationTime, String founder, String reviser, String companyCode) {
		return dao.addContractHInfo(contractNo, customerID, creationTime, founder, reviser, companyCode);
	}

	@Override
	public Integer addContractRInfo(String contractNo, String lineNumber, String materialCode, String materialName, String moldAmortization, String amortizationAmount, String amortizationUnitPrice, String productPrice, String demandQuantity, String quantityDelivered) {
		return dao.addContractRInfo(contractNo, lineNumber, materialCode, materialName, moldAmortization, amortizationAmount, amortizationUnitPrice, productPrice, demandQuantity, quantityDelivered);
	}

	@Override
	public List<Map<String, Object>> showContractHMaxIdData(String contractNo) {
		return null;
	}

	@Override
	public Integer updateContractHInfoH(String reviser, String revisionTime, String contractNo, String customerID, String companyCode, String status) {
		return dao.updateContractHInfoH(reviser, revisionTime, contractNo, customerID, companyCode, status);
	}

	@Override
	public Integer updateContractHInfoH1(String reviser, String revisionTime, String contractNo, String customerID, String companyCode, String status, String effectiveDate) {
		return dao.updateContractHInfoH1(reviser, revisionTime, contractNo, customerID, companyCode, status,effectiveDate);
	}

	@Override
	public Integer updateContractHInfoR(String materialCode, String materialName, String moldAmortization, String amortizationAmount,String amortizationUnitPrice, String productPrice, String demandQuantity, String quantityDelivered, String contractNo, String lineNumber) {
		return dao.updateContractHInfoR(materialCode, materialName, moldAmortization, amortizationAmount,amortizationUnitPrice, productPrice, demandQuantity, quantityDelivered, contractNo, lineNumber);
	}

	@Override
	public List<Map<String, Object>> showContractHDataById(String id) {
		return dao.showContractHDataById(id);
	}

	@Override
	public String showLineNumber(String contractNo) {
		return dao.showLineNumber(contractNo);
	}

	@Override
	public List<Map<String, Object>> showContractRById(String contractNo) {
		return dao.showContractRById(contractNo);
	}

	@Override
	public Integer countRNum(String contractNo) {
		return dao.countRNum(contractNo);
	}
}

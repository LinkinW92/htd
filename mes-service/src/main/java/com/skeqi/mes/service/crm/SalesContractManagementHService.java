package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

public interface SalesContractManagementHService {


	public List<Map<String, Object>> showContractHInfo(String contractNo, String companyCode, String customerID);

	public List<Map<String, Object>> showContractHInfoByCode(String contractNo);

	public List<Map<String, Object>> showContractRInfoByCode(String contractNo);

	public Integer delContractHData(String contractNo);


	public Integer delContractRData(String contractNo);


	public Integer delContractRDataByLineNum(String contractNo, String lineNumber);


	public Integer addContractHInfo(String contractNo, String customerID, String creationTime, String founder,
									 String reviser, String companyCode);


	public Integer addContractRInfo(String contractNo, String lineNumber, String materialCode, String materialName, String moldAmortization, String amortizationAmount, String amortizationUnitPrice, String productPrice, String demandQuantity, String quantityDelivered) ;



		public List<Map<String, Object>> showContractHMaxIdData(String contractNo);


	public Integer updateContractHInfoH(String reviser, String revisionTime, String contractNo, String customerID, String companyCode, String status);

	public Integer updateContractHInfoH1(String reviser, String revisionTime, String contractNo, String customerID, String companyCode, String status,String effectiveDate );

	public Integer updateContractHInfoR(String materialCode, String materialName, String moldAmortization, String amortizationAmount,String amortizationUnitPrice, String productPrice, String demandQuantity, String quantityDelivered, String contractNo, String lineNumber) ;



		public List<Map<String, Object>> showContractHDataById(String id);


	public String showLineNumber(String contractNo);


	public List<Map<String, Object>> showContractRById(String contractNo);


	public Integer countRNum(String contractNo);
}

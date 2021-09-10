package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface BillingInformationService {

	public List<Map<String,Object>> showBillingInformationList(Integer customerId);


	public Integer addBillingInformationList(Integer customerId, String dutyParagraph, String unitAddress, String tel, String bankOfDeposit, String bankAccount, String QRCode);


	public Integer editBillingInformationList(Integer id, String dutyParagraph, String unitAddress, String tel, String bankOfDeposit, String bankAccount, String QRCode);


	public Integer delBillingInformationList(Integer id);

	public Map<String,Object> showCustomerNameById(Integer id);

}

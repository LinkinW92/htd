package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.crm.BillingInformationDao;

@Service
public class BillingInformationServiceImpl implements BillingInformationService {

	@Autowired
	private BillingInformationDao dao;
	@Override
	public List<Map<String, Object>> showBillingInformationList(Integer customerId) {
		// TODO Auto-generated method stub
		return dao.showBillingInformationList(customerId);
	}
	@Override
	public Integer addBillingInformationList(Integer customerId, String dutyParagraph, String unitAddress, String tel,
			String bankOfDeposit, String bankAccount, String QRCode) {
		// TODO Auto-generated method stub
		return dao.addBillingInformationList(customerId, dutyParagraph, unitAddress, tel, bankOfDeposit, bankAccount, QRCode);
	}
	@Override
	public Integer editBillingInformationList(Integer id, String dutyParagraph, String unitAddress, String tel,
			String bankOfDeposit, String bankAccount, String QRCode) {
		// TODO Auto-generated method stub
		return dao.editBillingInformationList(id, dutyParagraph, unitAddress, tel, bankOfDeposit, bankAccount, QRCode);
	}
	@Override
	public Integer delBillingInformationList(Integer id) {
		// TODO Auto-generated method stub
		return dao.delBillingInformationList(id);
	}
	@Override
	public Map<String, Object> showCustomerNameById(Integer id) {
		// TODO Auto-generated method stub
		return dao.showCustomerNameById(id);
	}



}

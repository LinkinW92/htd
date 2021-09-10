package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.crm.PostalAddressDao;

@Service
public class PostalAddressServiceImpl implements PostalAddressService {

	@Autowired
	private PostalAddressDao dao;
	@Override
	public List<Map<String, Object>> showPostalAddressList(Integer customerId) {
		// TODO Auto-generated method stub
		return dao.showPostalAddressList(customerId);
	}
	@Override
	public Integer addPostalAddress(Integer customerId, String receivingAddress, String receivingAddressContacts,
			String invoiceRecevingAddress, String invoiceReceivingAddressContacts) {
		// TODO Auto-generated method stub
		return dao.addPostalAddress(customerId, receivingAddress, receivingAddressContacts, invoiceRecevingAddress, invoiceReceivingAddressContacts);
	}
	@Override
	public Integer editPostalAddress(Integer id, String receivingAddress, String receivingAddressContacts,
			String invoiceRecevingAddress, String invoiceReceivingAddressContacts) {
		// TODO Auto-generated method stub
		return dao.editPostalAddress(id, receivingAddress, receivingAddressContacts, invoiceRecevingAddress, invoiceReceivingAddressContacts);
	}
	@Override
	public Integer delPostalAddress(Integer id) {
		// TODO Auto-generated method stub
		return dao.delPostalAddress(id);
	}

}

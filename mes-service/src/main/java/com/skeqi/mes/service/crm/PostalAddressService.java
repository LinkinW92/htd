package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PostalAddressService {

	public List<Map<String,Object>> showPostalAddressList(Integer customerId);

	public Integer addPostalAddress(Integer customerId,String receivingAddress,String receivingAddressContacts,String invoiceRecevingAddress,String invoiceReceivingAddressContacts);

	public Integer editPostalAddress(Integer id,String receivingAddress,String receivingAddressContacts,String invoiceRecevingAddress,String invoiceReceivingAddressContacts);

	public Integer delPostalAddress(Integer id);

}

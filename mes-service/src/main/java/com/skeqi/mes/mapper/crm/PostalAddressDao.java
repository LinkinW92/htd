package com.skeqi.mes.mapper.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component("PostalAddressDao")
@MapperScan
public interface PostalAddressDao {

	@Select("select * from c_crm_postal_address c right join c_crm_customer_basic_information b on c.CUSTOMER_ID=b.ID WHERE c.CUSTOMER_ID=#{customerId}")
	public List<Map<String,Object>> showPostalAddressList(@Param("customerId")Integer customerId);

	@Insert("insert into c_crm_postal_address(CUSTOMER_ID,RECEIVING_ADDRESS,RECEIVING_ADDRESS_CONTACTS,INVOICE_RECEIVING_ADDRESS,INVOICE_RECEIVING_ADDRESS_CONTACTS) values(#{customerId},#{receivingAddress},#{receivingAddressContacts},#{invoiceRecevingAddress},#{invoiceReceivingAddressContacts})")
	public Integer addPostalAddress(@Param("customerId")Integer customerId,@Param("receivingAddress")String receivingAddress,@Param("receivingAddressContacts")String receivingAddressContacts,@Param("invoiceRecevingAddress")String invoiceRecevingAddress,@Param("invoiceReceivingAddressContacts")String invoiceReceivingAddressContacts);

	@Update("update c_crm_postal_address set RECEIVING_ADDRESS=#{receivingAddress},RECEIVING_ADDRESS_CONTACTS=#{receivingAddressContacts},INVOICE_RECEIVING_ADDRESS=#{invoiceRecevingAddress},INVOICE_RECEIVING_ADDRESS_CONTACTS=#{invoiceReceivingAddressContacts} where ID=#{id}")
	public Integer editPostalAddress(@Param("id")Integer id,@Param("receivingAddress")String receivingAddress,@Param("receivingAddressContacts")String receivingAddressContacts,@Param("invoiceRecevingAddress")String invoiceRecevingAddress,@Param("invoiceReceivingAddressContacts")String invoiceReceivingAddressContacts);

	@Delete("delete from c_crm_postal_address where ID=#{id}")
	public Integer delPostalAddress(@Param("id") Integer id);

}

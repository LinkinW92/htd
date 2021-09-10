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

@Component("BillingInformationDao")
@MapperScan
public interface BillingInformationDao {

	@Select("select * from c_crm_billing_information b left join c_crm_customer_basic_information c on b.CUSTOMER_ID=c.ID where b.CUSTOMER_ID=#{customerId}")
	public List<Map<String,Object>> showBillingInformationList(@Param("customerId")Integer customerId);

	@Select("select * from c_crm_customer_basic_information  where ID=(select CUSTOMER_ID from c_crm_billing_information where ID=#{id})")
	public Map<String,Object> showCustomerNameById(@Param("id")Integer id);

	@Insert("insert into c_crm_billing_information(CUSTOMER_ID,DUTY_PARAGRAPH,UNIT_ADDRESS,TEL,BANK_OF_DEPOSIT,BANK_ACCOUNT,QR_CODE) values(#{customerId},#{dutyParagraph},#{unitAddress},#{tel},#{bankOfDeposit},#{bankAccount},#{QRCode})")
	public Integer addBillingInformationList(@Param("customerId")Integer customerId,@Param("dutyParagraph") String dutyParagraph,@Param("unitAddress") String unitAddress,@Param("tel") String tel,@Param("bankOfDeposit") String bankOfDeposit,@Param("bankAccount") String bankAccount,@Param("QRCode") String QRCode);

	@Update("update c_crm_billing_information set DUTY_PARAGRAPH=#{dutyParagraph},UNIT_ADDRESS=#{unitAddress},TEL=#{tel},BANK_OF_DEPOSIT=#{bankOfDeposit},BANK_ACCOUNT=#{bankAccount},QR_CODE=#{QRCode} where ID=#{id}")
	public Integer editBillingInformationList(@Param("id")Integer id,@Param("dutyParagraph") String dutyParagraph,@Param("unitAddress") String unitAddress,@Param("tel") String tel,@Param("bankOfDeposit") String bankOfDeposit,@Param("bankAccount") String bankAccount,@Param("QRCode") String QRCode);

	@Delete("delete from c_crm_billing_information where ID=#{id}")
	public Integer delBillingInformationList(@Param("id")Integer id);

}

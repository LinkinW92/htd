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

@Component("CustomerBaseInfoDao")
@MapperScan
public interface CustomerBaseInfoDao {

	@Select("<script>select * from c_crm_customer_basic_information where 1=1 "
			+ "<if test=\"customerName!=null and customerName!=''\">and CUSTOMER_NAME like '%${customerName}%' </if> "
			+ "<if test=\"id!=null and id!=''\">and ID =#{id}</if> "
			+ "<if test=\"customerType!=null and customerType!=''\">and CUSTOMER_TYPE like '%${customerType}%' </if></script>")
	public List<Map<String,Object>> showCustomerBaseInfoList(@Param("customerName")String customerName,@Param("customerType")String customerType,@Param("id")String id);//获取客户基础资料详情

	@Insert("insert into c_crm_customer_basic_information(CUSTOMER_NAME,CUSTOMER_TYPE,CUSTOMER_WEBSITE,CUSTOMER_PROFILE,BUSINESS_PERSON) values(#{customerName},#{customerType},#{customerWebsite},#{customerProfile},#{businessPerson})")
	public Integer addCustomerBaseInfo(@Param("customerName")String customerName,@Param("customerType") String customerType,@Param("customerWebsite") String customerWebsite,@Param("customerProfile") String customerProfile,@Param("businessPerson") String businessPerson);

	@Update("update c_crm_customer_basic_information set CUSTOMER_NAME=#{customerName},CUSTOMER_TYPE=#{customerType},CUSTOMER_WEBSITE=#{customerWebsite},CUSTOMER_PROFILE=#{customerProfile},BUSINESS_PERSON=#{businessPerson} where ID=#{id}")
	public Integer editCustomerBaseInfo(@Param("id") Integer id,@Param("customerName")String customerName,@Param("customerType") String customerType,@Param("customerWebsite") String customerWebsite,@Param("customerProfile") String customerProfile,@Param("businessPerson") String businessPerson);

	@Delete("delete from c_crm_customer_basic_information where id=#{id}")
	public Integer delCustomerBaseInfo(@Param("id")Integer id);

	@Select("select CUSTOMER_NAME from c_crm_customer_basic_information")
	public List<String> showAllCustomerNameList();

	@Select("select CUSTOMER_NAME from c_crm_customer_basic_information where id=#{id}")
	public String showCustomerNameById(@Param("id") Integer id);

	@Select("select * from c_crm_customer_basic_information where ID=#{id}")
	public List<Map<String,Object>> showCustomerBaseAllInfoListById(@Param("id")Integer id);

//	删除客户判断部门是否存在关联
	@Select("select count(*) from c_crm_customer_department_information where CUSTOMER_ID=#{id}")
	public Integer countDepartmentNum(@Param("id")Integer id);

//	删除客户判断成员是否存在关联
	@Select("select count(*) from c_crm_customer_department_member where CUSTOMER_ID=#{id}")
	public Integer countMemberNum(@Param("id")Integer id);
}

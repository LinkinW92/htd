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

@MapperScan
@Component("DepartmentInformationDao")
public interface DepartmentInformationDao {

	@Select("select * from c_crm_customer_department_information d right join c_crm_customer_basic_information b on d.CUSTOMER_ID=b.ID where d.CUSTOMER_ID=#{custoemrId}")
	public List<Map<String,Object>> showDepartmentInfoById(@Param("custoemrId")Integer customerId);

	@Select("<script> select * from c_crm_customer_department_information d right join c_crm_customer_basic_information b on d.CUSTOMER_ID=b.ID where CUSTOMER_ID is not null and 1=1 "
			+ "<if test=\"customerName!=''\"> and b.CUSTOMER_NAME like '%${customerName}%' </if> order by b.CUSTOMER_NAME,d.ID desc </script>")
	public List<Map<String,Object>> showDepartmentInfo(@Param("customerName")String customerName);

	@Insert("insert into c_crm_customer_department_information(CUSTOMER_ID,CUSTOMER_DEPARTMENT,DEPARTMENT_DESCRIPTION) values(#{customerId},#{customerDepartmrnt},#{departDescription})")
	public Integer addDepartmentInfo(@Param("customerId")Integer customerId,@Param("customerDepartmrnt")String customerDepartmrnt,@Param("departDescription")String departDescription);

	@Update("update c_crm_customer_department_information set CUSTOMER_ID=#{customerId},CUSTOMER_DEPARTMENT=#{customerDepartmrnt},DEPARTMENT_DESCRIPTION=#{departDescription} where ID=#{id}")
	public Integer editDepartmentInfo(@Param("id")Integer id,@Param("customerId")Integer customerId,@Param("customerDepartmrnt")String customerDepartmrnt,@Param("departDescription")String departDescription);

	@Delete("delete from c_crm_customer_department_information where ID=#{id}")
	public Integer delDepartmentInfo(@Param("id")Integer id);

	@Select("select ID,CUSTOMER_NAME from c_crm_customer_basic_information group by CUSTOMER_NAME")
	public List<Map<String,Object>> showAllCustomerNameList();//搜索去重后的客户名称及其ID

	@Select("select ID,CUSTOMER_NAME from c_crm_customer_basic_information where ID=#{id} group by CUSTOMER_NAME ")
	public List<Map<String,Object>> showAllCustomerNameListsById(@Param("id")Integer id);//搜索去重后的客户名称及其ID

	@Select("select CUSTOMER_DEPARTMENT from c_crm_customer_department_information where CUSTOMER_ID=#{id}")
	public List<String> showAllCustomerNameLists(@Param("id") Integer id);

	@Select("select CUSTOMER_DEPARTMENT from c_crm_customer_department_information where ID=#{id}")
	public String showAllCustomerNameListById(@Param("id") Integer id);

	@Select("select count(*) from c_crm_customer_department_member where CUSTOMER_DEPARTMENT_ID = #{departmentId}")
	public Integer countMemberNum(@Param("departmentId") Integer departmentId);

}

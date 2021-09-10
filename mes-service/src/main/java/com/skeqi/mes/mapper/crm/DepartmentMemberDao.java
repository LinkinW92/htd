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
@Component("DepartmentMemberDao")
public interface DepartmentMemberDao {

	@Select("<script>select * from (select m.POSITION,m.ID,m.CUSTOMER_DEPARTMENT_ID,m.CUSTOMER_ID,m.`NAME`,m.TEL,m.ADDRESS,m.HOBBY,m.MAIL,d.CUSTOMER_DEPARTMENT,d.DEPARTMENT_DESCRIPTION "
			+ "from c_crm_customer_department_member m left join c_crm_customer_department_information d on m.CUSTOMER_DEPARTMENT_ID=d.ID) k "
			+ "left join c_crm_customer_basic_information g on k.CUSTOMER_ID=g.ID "
			+ " where 1=1 "
			+ "<if test=\"customerName!=''\">and CUSTOMER_NAME like '%${customerName}%'</if> "
			+ "<if test=\"departmentIName!=''\"> and CUSTOMER_DEPARTMENT like '%${departmentIName}%'</if> "
			+ "<if test=\"memberName!=''\"> and NAME like '%${memberName}%' </if>"
			+ "order by CUSTOMER_NAME,CUSTOMER_DEPARTMENT,`NAME` desc </script>")
	public List<Map<String,Object>> showAllDepartmentMemberList(@Param("customerName")String customerName,@Param("departmentIName")String departmentIName,@Param("memberName")String memberName);

	@Insert("insert into c_crm_customer_department_member(CUSTOMER_ID,CUSTOMER_DEPARTMENT_ID,NAME,TEL,ADDRESS,HOBBY,MAIL,POSITION) values(#{customerId},#{customerDepartmentId},#{name},#{tel},#{address},#{hobby},#{mail},#{position})")
	public Integer addDepartmentMember(@Param("customerId") Integer customerId,@Param("customerDepartmentId") Integer customerDepartmentId,@Param("name") String name,@Param("tel") String tel,@Param("address") String address,@Param("hobby") String hobby,@Param("mail") String mail,@Param("position")String position);

	@Update("update c_crm_customer_department_member set POSITION=#{position},CUSTOMER_ID=#{customerId},CUSTOMER_DEPARTMENT_ID=#{customerDepartmentId},NAME=#{name},TEL=#{tel},ADDRESS=#{address},HOBBY=#{hobby},MAIL=#{mail} where ID=#{id}")
	public Integer editDepartmentMember(@Param("id") Integer id,@Param("customerId") Integer customerId,@Param("customerDepartmentId") Integer customerDepartmentId,@Param("name") String name,@Param("tel") String tel,@Param("address") String address,@Param("hobby") String hobby,@Param("mail") String mail,@Param("position")String position);

	@Delete("delete from c_crm_customer_department_member where ID=#{id}")
	public Integer delDepartmentMember(@Param("id")Integer id);

	@Select("select d.ID ,CUSTOMER_DEPARTMENT from c_crm_customer_department_information d where CUSTOMER_ID=#{customerId} group by CUSTOMER_DEPARTMENT ")
	public List<Map<String,Object>> showDepartmentListBycustomerId(@Param("customerId")Integer customerId);

	@Select("<script>select * from (select m.POSITION,m.ID,m.CUSTOMER_DEPARTMENT_ID,m.CUSTOMER_ID,m.`NAME`,m.TEL,m.ADDRESS,m.HOBBY,m.MAIL,d.CUSTOMER_DEPARTMENT,d.DEPARTMENT_DESCRIPTION from c_crm_customer_department_member m left join c_crm_customer_department_information d on m.CUSTOMER_DEPARTMENT_ID=d.ID) k  left join c_crm_customer_basic_information g on k.CUSTOMER_ID=g.ID  where CUSTOMER_ID=#{id} and 1=1 "
			+ "<if test=\"customerName!=''\">and CUSTOMER_NAME like '%${customerName}%'</if> "
			+ "<if test=\"departmentIName!=''\"> and CUSTOMER_DEPARTMENT like '%${departmentIName}%'</if> "
			+ "<if test=\"memberName!=''\"> and NAME like '%${memberName}%' </if>"
			+ "order by CUSTOMER_NAME,CUSTOMER_DEPARTMENT,`NAME` desc</script>")
	public List<Map<String,Object>> showDepartmentMemberByCustomerId(@Param("id")Integer id,@Param("customerName")String customerName,@Param("departmentIName")String departmentIName,@Param("memberName")String memberName);

}

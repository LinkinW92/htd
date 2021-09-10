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
@Component("ProjectBasicInfoDao")
public interface ProjectBasicInfoDao {

	//展示项目基础信息
	@Select("select * from c_crm_project_basic_info")
	public List<Map<String,Object>> showProjectBasicInfo();

    //根据ID展示项目基础信息5.0
//	@Select("<script> select * from c_crm_project_basic_info where 1=1"
//			+ "<if test=\"id!=null and id!=''\"> and ID=#{id}</if>"
//			+ "<if test=\"projectName!=''\">and PROJECT_NAME like '%${projectName}%'</if> "
//			+ "<if test=\"projectNo!=''\">and PROJECT_NO like '%${projectNo}%'</if> "
//			+ "<if test=\"schemeNo!=''\">and SCHEME_NO like '%${schemeNo}%'</if>  </script>")
	@Select("<script> select * from c_crm_project_basic_info a left join c_crm_customer_basic_information b on a.CUSTOMER_ID=b.ID   where 1=1"
			+ "<if test=\"id!=null and id!=''\"> and a.ID=#{id}</if>"
			+ "<if test=\"ids!=null and ids!=''\"> and b.ID=#{ids}</if>"
			+ "<if test=\"projectName!=''\">and a.PROJECT_NAME like '%${projectName}%'</if> "
			+ "<if test=\"projectNo!=''\">and a.PROJECT_NO like '%${projectNo}%'</if> "
			+ "<if test=\"schemeNo!=''\">and a.SCHEME_NO like '%${schemeNo}%'</if>  </script>")
	public List<Map<String,Object>> showProjectBasicInfoById(@Param("id")String id,@Param("projectName")String projectName,@Param("projectNo")String projectNo,@Param("schemeNo")String schemeNo,@Param("ids")String ids);

	//新增项目基础信息
	@Insert("insert into c_crm_project_basic_info(PROJECT_NAME,PROJECT_NO,SCHEME_NO,PROJECT_ADDRESS,PROJECT_MANAGER,CUSTOMER_ID) values(#{projectName},#{projectNo},#{schemeNo},#{projectAddress},#{projectManager},#{customerId})")
	public Integer addProjectBasicInfo(@Param("projectName")String projectName,@Param("projectNo")String projectNo,@Param("schemeNo")String schemeNo,@Param("projectAddress")String projectAddress,@Param("projectManager")String projectManager,@Param("customerId")String customerId);

	//编辑项目基础信息
	@Update("update c_crm_project_basic_info set CUSTOMER_ID=#{customerId},PROJECT_NAME=#{projectName},PROJECT_NO=#{projectNo},SCHEME_NO=#{schemeNo},PROJECT_ADDRESS=#{projectAddress},PROJECT_MANAGER=#{projectManager} where ID=#{id}")
	public Integer editProjectBasicInfo(@Param("id") Integer id,@Param("projectName")String projectName,@Param("projectNo")String projectNo,@Param("schemeNo")String schemeNo,@Param("projectAddress")String projectAddress,@Param("projectManager")String projectManager,@Param("customerId")String customerId);

	//删除项目基础信息
	@Delete("delete from c_crm_project_basic_info where ID=#{id}")
	public Integer delProjectBasicInfo(@Param("id")Integer id);

    //项目号唯一性
	@Select("<script>select count(*) from c_crm_project_basic_info where PROJECT_NO=#{projectNo} <if test=\"id!=null and id!=''\"> and ID!=#{id} </if></script>")
	public Integer countProjectNOById(@Param("projectNo")String projectNo,@Param("id")String id);
}

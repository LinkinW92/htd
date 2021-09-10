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
@Component("FileTypeInformationDao")
public interface FileTypeInformationDao {
	//	展示文件类型
	@Select("select * from c_crm_file_type_info where FILE_SHOW!=0 order by FILE_SHOW asc")
	public List<Map<String,Object>> showAllFileTypeInfo();

	//新增文件类型
	@Insert("insert into c_crm_file_type_info(FILE_TYPE_NAME,FILE_SHOW) values(#{fileName},#{fileType})")
	public Integer addFileTypeInfo(@Param("fileType")String fileType,@Param("fileName")String fileName);

	//编辑文件类型
	@Update("update c_crm_file_type_info set FILE_TYPE_NAME=#{fileName},FILE_SHOW=#{fileType} where ID=#{id}")
	public Integer editFileTypeInfo(@Param("id")String id,@Param("fileType")String fileType,@Param("fileName")String fileName);

	//删除文件类型
	@Delete("delete from c_crm_file_type_info where ID=#{id}")
	public Integer delFileTypeInfo(@Param("id")Integer id);

	//检索是否存在
	@Select("<script> select count(*) from c_crm_file_type_info where FILE_TYPE_NAME=#{fileName} and FILE_SHOW=#{fileType} <if test=\"id!='' and id!=null\"> and ID!=#{id} </if> </script>")
	public Integer countFileTypeInfo(@Param("fileType")String fileType,@Param("fileName")String fileName,@Param("id")String id);

   //查询关联该文件类型是否存在关联项
	@Select("select count(*) from c_crm_file_upload_info where TYPE_ID=#{typeId}")
	public Integer countTypeNum(@Param("typeId")Integer typeId);



}

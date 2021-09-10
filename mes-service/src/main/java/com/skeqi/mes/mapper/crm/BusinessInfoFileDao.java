package com.skeqi.mes.mapper.crm;

import java.util.Date;
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
@Component("BusinessInfoFileDao")
public interface BusinessInfoFileDao {

	@Select("SELECT a.ID,a.TYPE_ID,a.PROJECT_ID,a.FILE_TYPE_NAME,p.PROJECT_NO,p.PROJECT_NAME  FROM (select u.ID,u.PROJECT_ID,t.FILE_TYPE_NAME,u.TYPE_ID from c_crm_file_upload_info u left join c_crm_file_type_info t on u.TYPE_ID = t.ID ) a left join c_crm_project_basic_info p on a.PROJECT_ID = p.ID where a.PROJECT_ID=#{projectId}")
	public List<Map<String,Object>> showBusinessInfoFileList(@Param("projectId")Integer projectId);

    //展示所有可用于展示的文件类型
	@Select("select * from c_crm_file_type_info WHERE FILE_SHOW =1")
	public List<Map<String,Object>> showAllFileType();

	 //展示所有可用于展示的公司文件类型
		@Select("select * from c_crm_file_type_info WHERE FILE_SHOW =2")
		public List<Map<String,Object>> showAllCompanyFileType();

	@Insert("insert into c_crm_file_upload_info(PROJECT_ID,TYPE_ID,FILE_PATH) values(#{projectId},#{typeId},#{filePath})")
	public Integer addBusinessInfoFile(@Param("projectId")Integer projectId,@Param("typeId") Integer typeId,@Param("filePath")String filePath);

	@Update("update  c_crm_file_upload_info set PROJECT_ID=#{projectId},TYPE_ID=#{typeId},FILE_PATH=#{filePath},UPLOAD_TIME=#{uploadTime},FILE_NAME=#{fileName},FILE_SIZE=#{fileSize} where ID=#{id}")
	public Integer editBusinessInfoFile(@Param("projectId")Integer projectId,@Param("typeId") Integer typeId,@Param("filePath")String filePath,@Param("uploadTime")Date uploadTIme,@Param("fileName")String fileName,@Param("fileSize")String fileSize);

	@Delete("delete from c_crm_file_upload_info where ID=#{id}")
	public Integer delBusinessInfoFile(@Param("id")Integer id);

	@Select("select * from c_crm_file_upload_info where ID=#{id}")
	public List<Map<String,Object>> showFileInfoById(@Param("id") Integer id);

   //查重文件路径
	@Select("select count(*) from c_crm_file_upload_info where PROJECT_ID=#{projectId} and TYPE_ID=#{typeId}")
	public Integer countFilePath(@Param("projectId")Integer projectId,@Param("typeId") Integer typeId);

//	查询文件类型名称
	@Select("select FILE_TYPE_NAME from c_crm_file_type_info  where ID=#{id}")
	public String showFileTypeName(@Param("id")Integer id);

}

package com.skeqi.mes.service.crm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BusinessInfoFileService {


	public List<Map<String,Object>> showAllFileType();

	public List<Map<String,Object>> showBusinessInfoFileList(Integer projectId);


	public Integer addBusinessInfoFile(Integer projectId,Integer typeId,String filePath);


	public Integer editBusinessInfoFile(Integer projectId,Integer typeId,String filePath,Date uploadTIme,String fileName,String fileSize);


	public Integer delBusinessInfoFile(Integer id);

	public List<Map<String,Object>> showFileInfoById(Integer id);

	 //排查重复类型
	public Integer countFilePath(Integer projectId,Integer typeId);

	public List<Map<String,Object>> showAllCompanyFileType();

	public String showFileTypeName(Integer id);

}

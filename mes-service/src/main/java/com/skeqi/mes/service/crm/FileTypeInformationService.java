package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface FileTypeInformationService {

	//	展示文件类型

	public List<Map<String,Object>> showAllFileTypeInfo();

	//新增文件类型

	public Integer addFileTypeInfo(String fileType,String fileName);

	//编辑文件类型

	public Integer editFileTypeInfo(String id,String fileType,String fileName);

	//删除文件类型

	public Integer delFileTypeInfo(Integer id);

	public Integer countFileTypeInfo(String fileType,String fileName,String id);

	public Integer countTypeNum(Integer typeId);

}

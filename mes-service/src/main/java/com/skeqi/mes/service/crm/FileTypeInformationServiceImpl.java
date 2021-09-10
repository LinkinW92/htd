package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.crm.FileTypeInformationDao;
import java.util.Map;

@Service
public class FileTypeInformationServiceImpl implements FileTypeInformationService {

	@Autowired
	private FileTypeInformationDao dao;
	@Override
	public List<Map<String, Object>> showAllFileTypeInfo() {
		// TODO Auto-generated method stub
		return dao.showAllFileTypeInfo();
	}

	@Override
	public Integer addFileTypeInfo(String fileType, String fileName) {
		// TODO Auto-generated method stub
		return dao.addFileTypeInfo(fileType, fileName);
	}

	@Override
	public Integer editFileTypeInfo(String id, String fileType, String fileName) {
		// TODO Auto-generated method stub
		return dao.editFileTypeInfo(id, fileType, fileName);
	}

	@Override
	public Integer delFileTypeInfo(Integer id) {
		// TODO Auto-generated method stub
		return dao.delFileTypeInfo(id);
	}

	@Override
	public Integer countFileTypeInfo(String fileType, String fileName,String id) {
		// TODO Auto-generated method stub
		return dao.countFileTypeInfo(fileType, fileName,id);
	}

	@Override
	public Integer countTypeNum(Integer typeId) {
		// TODO Auto-generated method stub
		return dao.countTypeNum(typeId);
	}

}

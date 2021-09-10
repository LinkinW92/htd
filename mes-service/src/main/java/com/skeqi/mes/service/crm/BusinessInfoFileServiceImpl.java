package com.skeqi.mes.service.crm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.crm.BusinessInfoFileDao;

@Service
public class BusinessInfoFileServiceImpl implements BusinessInfoFileService {

	@Autowired
	private  BusinessInfoFileDao dao;
	@Override
	public List<Map<String, Object>> showBusinessInfoFileList(Integer projectId) {
		// TODO Auto-generated method stub
		return dao.showBusinessInfoFileList(projectId);
	}

	@Override
	public Integer addBusinessInfoFile(Integer projectId, Integer typeId, String filePath) {
		// TODO Auto-generated method stub
		return dao.addBusinessInfoFile(projectId, typeId, filePath);
	}

	@Override
	public Integer editBusinessInfoFile(Integer projectId, Integer typeId, String filePath, Date uploadTIme,
			String fileName, String fileSize) {
		// TODO Auto-generated method stub
		return dao.editBusinessInfoFile(projectId, typeId, filePath, uploadTIme, fileName, fileSize);
	}

	@Override
	public Integer delBusinessInfoFile(Integer id) {
		// TODO Auto-generated method stub
		return dao.delBusinessInfoFile(id);
	}

	@Override
	public List<Map<String, Object>> showAllFileType() {
		// TODO Auto-generated method stub
		return dao.showAllFileType();
	}

	@Override
	public List<Map<String, Object>> showFileInfoById(Integer id) {
		// TODO Auto-generated method stub
		return dao.showFileInfoById(id);
	}

	@Override
	public Integer countFilePath(Integer projectId, Integer typeId) {
		// TODO Auto-generated method stub
		return dao.countFilePath(projectId, typeId);
	}

	@Override
	public List<Map<String, Object>> showAllCompanyFileType() {
		// TODO Auto-generated method stub
		return dao.showAllCompanyFileType();
	}

	@Override
	public String showFileTypeName(Integer id) {
		// TODO Auto-generated method stub
		return dao.showFileTypeName(id);
	}

}

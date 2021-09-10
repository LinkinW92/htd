package com.skeqi.mes.service.wjp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.wjp.ProductManagementDao;
import com.skeqi.mes.pojo.CMesProductionT;
@Service
public class ProductManagementServiceImpl implements ProductManagementService{

	@Autowired
	private ProductManagementDao productManagementDao;
	//产品列表
	@Override
	public List<CMesProductionT> findAll() {
		return productManagementDao.findAll();
	}
	//产品新增
	@Transactional
	@Override
	public Integer addProduction(CMesProductionT cMesProductionT) {
		return productManagementDao.addProduction(cMesProductionT);
	}
	//根据id查询
	@Override
	public CMesProductionT findProductionById(int id) {
		return productManagementDao.findProductionById(id);
	}
	//产品修改
	@Override
	@Transactional
	public boolean updateProduction(Map map) {
		return productManagementDao.updateProduction(map);
	}
	//产品删除
	@Override
	@Transactional
	public boolean deleteProduction(int id) {
		return productManagementDao.deleteProduction(id);
	}
	@Override
	public int countProductionProcessByProductionId(Map<String, Object> map) {
		return productManagementDao.countProductionProcessByProductionId(map);
	}
	@Override
	public int countBomByProductionId(Map<String, Object> map) {
		return productManagementDao.countBomByProductionId(map);
	}
	@Override
	public int countRecipeByProductionId(Map<String, Object> map) {
		return productManagementDao.countRecipeByProductionId(map);
	}
	@Override
	public int countPlanByProductionId(Map<String, Object> map) {
		return productManagementDao.countPlanByProductionId(map);
	}
	@Override
	public int countBarCodeByProduction(Map<String, Object> map) {
		return productManagementDao.countBarCodeByProduction(map);
	}
	@Override
	public int NoRepeat(CMesProductionT cMesProductionT) {
		return productManagementDao.NoRepeat(cMesProductionT);
	}
	@Override
	public int NoRepeats(CMesProductionT cMesProductionT) {
		// TODO Auto-generated method stub
		return productManagementDao.NoRepeats(cMesProductionT);
	}

}

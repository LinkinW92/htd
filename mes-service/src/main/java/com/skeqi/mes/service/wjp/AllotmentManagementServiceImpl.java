package com.skeqi.mes.service.wjp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.wjp.AllotmentManagementDao;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesStationT;

@Service
public class AllotmentManagementServiceImpl implements AllotmentManagementService {

	@Autowired
	private AllotmentManagementDao allotmentManagementDao;

	// 配方管理
	@Override
	public List<CMesRecipeT> findAll(Map<String, Object> map) {
		return allotmentManagementDao.findAll(map);
	}

	// 产品清单列表
	@Override
	public List<CMesProductionRecipeT> findAllAllotment(Map<String, Object> map) {
		return allotmentManagementDao.findAllAllotment(map);
	}

	// 产品信息
	@Override
	public List<CMesProductionT> findProduction() {
		return allotmentManagementDao.findProduction();
	}

	//产品名称不可重复
	@Override
	public int NotRepeatable(String recipeName) {
		return allotmentManagementDao.NotRepeatable(recipeName);
	}

	// 根据ID查询
	@Override
	public CMesRecipeT findById(int id) {
		return allotmentManagementDao.findById(id);
	}

	//配方删除前判断是否解绑
	@Transactional
	@Override
	public int deleteAllotmentAs(int id) {
		return allotmentManagementDao.deleteAllotmentAs(id);
	}

	//删除配方
	@Transactional
	@Override
	public boolean deleteAllotment(int id) {
		return allotmentManagementDao.deleteAllotment(id);
	}

	//解除绑定
	@Override
	@Transactional
	public boolean deleteUntie(int id) {
		return allotmentManagementDao.deleteUntie(id);
	}

	//新增配方
	@Override
	@Transactional
	public void addRectipe(CMesRecipeT cMesRecipeT) {
		allotmentManagementDao.addRectipe(cMesRecipeT);
	}

	//修改配方
	@Override
	@Transactional
	public boolean updateAllotment(CMesRecipeT cMesRecipeT) {
		return allotmentManagementDao.updateAllotment(cMesRecipeT);
	}

	//工位信息
	@Override
	@Transactional
	public List<CMesStationT> findStation() {
		return allotmentManagementDao.findStation();
	}
	@Override
	@Transactional
	public boolean updateStation(CMesStationT cMesStationT) {
		return allotmentManagementDao.updateStation(cMesStationT);
	}
	@Override
	@Transactional
	public boolean updateProduction(CMesProductionT cMesProductionT) {
		return allotmentManagementDao.updateProduction(cMesProductionT);
	}
	@Override
	@Transactional
	public void updateproductionRecipe(Map map) {
		allotmentManagementDao.updateproductionRecipe(map);
	}
	@Override
	@Transactional
	public void addProRec(Map map) {
		allotmentManagementDao.addProRec(map);
	}
	@Override
	@Transactional
	public int getMaxNumber() {
		return allotmentManagementDao.getMaxNumber();
	}
	@Override
	@Transactional
	public List<CMesProductionRecipeT> productionRecipe(Map map) {
		return allotmentManagementDao.productionRecipe(map);
	}
	@Override
	@Transactional
	public List<CMesProductionRecipeT> findAllAllotmentByrId(Map map) {
		return allotmentManagementDao.findAllAllotmentByrId(map);
	}

	@Override
	public CMesRecipeT findByIdToAndR(CMesRecipeT cMesRecipeT) {
		return allotmentManagementDao.findByIdToAndR(cMesRecipeT);
	}
}

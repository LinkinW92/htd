package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesImportRecipeDAO;
import com.skeqi.mes.pojo.CMesProductionRecipeT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesRecipeVersion;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;
import com.skeqi.mes.pojo.CMesStationT;

@Service
@Transactional
public class CMesImportRecipeServiceImpl implements CMesImportRecipeService{

	@Autowired
	private CMesImportRecipeDAO dao;

	@Override
	public List<CMesProductionT> findAllProduction(CMesProductionT c) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllProduction(c);
	}

	@Override
	public List<CMesRecipeVersion> findAllRecipeVersion(CMesRecipeVersion version) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllRecipeVersion(version);
	}

	@Override
	public List<CMesRecipeVersionDetail> findAllRecipeVersionDetail(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findAllRecipeVersionDetail(id);
	}

	@Override
	public List<CMesRecipeDatilT> findAllRecipeDetail(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllRecipeDetail(id);
	}

	@Override
	public List<CMesRecipeTypeT> findRecipeType(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findRecipeType(name);
	}

	@Override
	public CMesStationT findStation(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findStation(name);
	}

	@Override
	public CMesProductionT findProduction(CMesProductionT t) throws ServicesException  {
		// TODO Auto-generated method stub
		return dao.findProduction(t);
	}

	@Override
	public Integer addRecipeVersion(CMesRecipeVersion version) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addRecipeVersion(version);
	}

	@Override
	public Integer addRecipeVersionDetail(CMesRecipeVersionDetail version) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addRecipeVersionDetail(version);
	}

	@Override
	public CMesRecipeVersion findMaxVersion(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findMaxVersion(id);
	}

	@Override
	public Integer deleteVersion(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.deleteVersion(id);
	}

	@Override
	public Integer deleteVersionDetail(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.deleteVersionDetail(id);
	}

	@Override
	public List<CMesProductionRecipeT> findProductionRecipe(String pname, String sname) throws ServicesException{
		// TODO Auto-generated method stub
		return dao.findProductionRecipe(pname, sname);
	}

	@Override
	public Integer delRecipeDetail(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.delRecipeDetail(id);
	}

	@Override
	public Integer addRecipeDetail(CMesRecipeDatilT recipe) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addRecipeDetail(recipe);
	}

}

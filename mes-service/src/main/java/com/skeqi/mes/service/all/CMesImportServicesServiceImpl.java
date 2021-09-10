package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesImportRecipesDAO;
import com.skeqi.mes.pojo.CMesImportT;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;

@Service
@Transactional
public class CMesImportServicesServiceImpl implements CMesImportRecipesService{

	@Autowired
	private CMesImportRecipesDAO dao;

	@Override
	public List<CMesTotalRecipeT> findAllTotalRecipe() throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllTotalRecipe();
	}

	@Override
	public List<Map<String, Object>> findAllRecipeDetail(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllRecipeDetail(id);
	}

	@Override
	public Integer findByNameStation(String name,Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findByNameStation(name,id);
	}

	@Override
	public Integer findByNameType(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findByNameType(name);
	}

	@Override
	public Integer addTotalRecipe(CMesTotalRecipeT recipe) throws ServicesException {
		// TODO Auto-generated method stub
		Integer findByNameTotal = dao.findByNameTotal(recipe.getTotalRecipeName());
		if(findByNameTotal>0){
			throw new NameRepeatException("总配方名称重复",100);
		}
		return dao.addTotalRecipe(recipe);
	}

	@Override
	public Integer findTotalId() throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findTotalId();
	}

	@Override
	public Integer addRecipe(CMesRecipe recipe) throws ServicesException {
		// TODO Auto-generated method stub
		Integer findByNameRecipe = dao.findByNameRecipe(recipe.getRecipeName(),recipe.getTotalId());
		if(findByNameRecipe>0){
			throw new NameRepeatException("工位配方名称重复",100);
		}
		return dao.addRecipe(recipe);
	}

	@Override
	public Integer findRecipeId() throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findRecipeId();
	}

	@Override
	public Integer addRecipeDatil(CMesRecipeDatilT datil) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addRecipeDatil(datil);
	}

	@Override
	public Integer findProductionId(String pname) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findProductionId(pname);
	}

	@Override
	public Integer findLineId(String lname) {
		// TODO Auto-generated method stub
		return dao.findLineId(lname);
	}

	@Override
	public List<CMesImportT> findAllRecipeDetailTwo(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllRecipeDetailTwo(id);
	}

	@Override
	public Integer findCountByTotalName(String totalName) {
		return dao.findCountByTotalName(totalName);
	}

}

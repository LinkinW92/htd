package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.RecipeVersionDAO;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeVersion;
import com.skeqi.mes.pojo.CMesRecipeVersionDetail;

@Service
public class RecipeVersionServiceImplement implements RecipeVersionService {

	@Autowired
	private RecipeVersionDAO dao;

	@Override
	public Double listName(Integer pid) {
		// TODO Auto-generated method stub
		return dao.listName(pid);
	}
	@Override
	public void addVersion(Map<String,Object> map) {
		// TODO Auto-generated method stub
		dao.addVersion(map);
	}
	@Override
	public Integer listId() {
		// TODO Auto-generated method stub
		return dao.listId();
	}
	@Override
	public void addVersionDetail(Integer recipeName, CMesRecipeVersionDetail recipe) {
		// TODO Auto-generated method stub
		dao.addVersionDetail(recipeName, recipe);
	}
	@Override
	public List<Map<String,Object>> findVersion(String vr) {
		// TODO Auto-generated method stub
		return dao.findVersion(vr);
	}
	@Override
	public List<CMesRecipeVersion> findPVersion(String productionVr) {
		// TODO Auto-generated method stub
		return dao.findPVersion(productionVr);
	}
	@Override
	public String findId(String vername) {
		// TODO Auto-generated method stub
		return dao.findId(vername);
	}
	@Override
	public List<CMesRecipeVersionDetail> recipeDatilListsTwo(Integer id, Double PVersion) {
		// TODO Auto-generated method stub
		return dao.recipeDatilListsTwo(id, PVersion);
	}
	@Override
	public Integer findName(String name) {
		// TODO Auto-generated method stub
		return dao.findName(name);
	}
	@Override
	public String findPName(String id) {
		// TODO Auto-generated method stub
		return dao.findPName(id);
	}
	@Override
	public Integer findStationName(String name) {
		// TODO Auto-generated method stub
		return dao.findStationName(name);
	}
	@Override
	public Integer findProductName(String name) {
		// TODO Auto-generated method stub
		return dao.findProductName(name);
	}
	@Override
	public Integer findPCId(Integer pid, Integer sid) {
		// TODO Auto-generated method stub
		return dao.findPCId(pid, sid);
	}
	@Override
	public Integer findTId(String name) {
		// TODO Auto-generated method stub
		return dao.findTId(name);
	}
	@Override
	public void addrecipe(Map<String, Object> map) {
		// TODO Auto-generated method stub
		dao.addrecipe(map);
	}
	@Override
	public void deleterecipe(Integer id) {
		// TODO Auto-generated method stub
		dao.deleterecipe(id);
	}
	@Override
	public List<Integer> findRId(Integer id) {
		// TODO Auto-generated method stub
		return dao.findRId(id);
	}
	@Override
	public Integer findMaxId() {
		// TODO Auto-generated method stub
		return dao.findMaxId();
	}
	@Override
	public void deleteVersion(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteVersion(id);
	}
	@Override
	public void ddelteVersionDetil(Integer verid) {
		// TODO Auto-generated method stub
		dao.ddelteVersionDetil(verid);
	}
	@Override
	public CMesProductionT findPID(String pvr) {
		// TODO Auto-generated method stub
		return dao.findPID(pvr);
	}
	@Override
	public List<CMesRecipeDatilT> findRecipe(String id) {
		// TODO Auto-generated method stub
		return dao.findRecipe(id);
	}

}

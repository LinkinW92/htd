package com.skeqi.mes.service.rework;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.rework.ReworkDao;
import com.skeqi.mes.pojo.qh.APIResult;
import com.skeqi.mes.pojo.qh.ReworkEntity;

@Service
public class ReworkServiceImpl implements ReworkService{

	@Autowired
	ReworkDao dao;

	@Override
	public void currentStation(ReworkEntity re) {
		APIResult api = new APIResult();
		if(re.getRecipeType().equals("0")) {  //物料
			dao.moveKeypart(re.getSn(), re.getSt(),re.getStep());
			if(re.getType().equals("1")) {  //替换
				dao.replaceKeypart(re.getSn(), re.getSt(), re.getStep(), re.getBarcode());
			}else {
				dao.dismanKeypart(re.getSn(), re.getSt(),re.getStep());
			}
		}else if(re.getRecipeType().equals("1")) {  //螺栓
			dao.moveBolt(re.getSn(), re.getSt(),re.getStep());
			dao.dismanBolt(re.getSn(), re.getSt(),re.getStep());
		}else {  //测试
			dao.moveLeakage(re.getSn(), re.getSt(), re.getStep());
			dao.dismanLeakage(re.getSn(), re.getSt(),re.getStep());
		}
	}

	@Override
	public void Annulment(String sn, String reason) {
		// TODO Auto-generated method stub
		dao.Annulment(sn, reason);
	}

	@Override
	public 	Map<String,Object> findisNg(String sn) {
		// TODO Auto-generated method stub
		return dao.findisNg(sn);
	}

	@Override
	public List<Map<String, Object>> findStationBySn(String sn) {
		// TODO Auto-generated method stub
		return dao.findStBySn(sn);
	}

	@Override
	public void updateOK(String sn) {
		// TODO Auto-generated method stub
		dao.updateOK(sn);
	}

	@Override
	public APIResult ScrapSN(String sn,String type,String reason) {
		//转移数据
		Integer findIsScrap = dao.findIsScrap(sn);
		if(findIsScrap==0) {
			return new APIResult("200","此总成不在线上无法判废！","false");
		}
		if(type.equals("1")) {
			dao.moveKeypartLi(sn, null, null);
		}
		dao.moveBolt(sn,null,null);
		dao.moveKeypart(sn, null, null);
		dao.moveLeakage(sn, null, null);
		dao.moveStation(sn);
		dao.moveTrack(sn,reason);
		//删除R表数据
		dao.delDataBySN(sn);
		return new APIResult("100","成功","true");
	}

	@Override
	public Integer findRoute(String sn) {
		// TODO Auto-generated method stub
		return dao.findRoute(sn);
	}

	@Override
	public List<Map<String, Object>> findP() {
		// TODO Auto-generated method stub
		return dao.findP();
	}

	@Override
	public List<Map<String, Object>> findL() {
		// TODO Auto-generated method stub
		return dao.findL();
	}

	@Override
	public List<Map<String, Object>> findTotalRecipe(String lineId, String proId) {
		// TODO Auto-generated method stub
		return dao.findTotalRecipe(lineId, proId);
	}

	@Override
	public List<Map<String, Object>> findProRoute(String lineId, String proId) {
		// TODO Auto-generated method stub
		return dao.findProRoute(lineId, proId);
	}

	@Override
	public void addReworkWork(String lineId, String proId, String recipeId, String routeId,String sn) {
		// TODO Auto-generated method stub
		 SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddhhmmss");
		 dao.addReworkWork(sim.format(new Date()),lineId, proId, recipeId, routeId);
		 Integer findMaxWid = dao.findMaxWid();
		 dao.addPlanPrint(lineId, proId, sn, findMaxWid.toString());
		 dao.addRTrack(sn, proId, lineId, findMaxWid.toString());
	}

	@Override
	public void deleteP(String sn) {
		// TODO Auto-generated method stub
		dao.deletePtrack(sn);
		dao.updateKeypartFlag(sn);
	}

	@Override
	public List<Map<String, Object>> findIdAndWorkOrderId(Integer id, String workOrderId) {
		return dao.findIdAndWorkOrderId(id,workOrderId);
	}
}

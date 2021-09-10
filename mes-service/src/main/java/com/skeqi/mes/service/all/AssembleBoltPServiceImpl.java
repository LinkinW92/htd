package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.all.AssembleBoltPDao;
import com.skeqi.mes.pojo.api.AssembleBoltPT;
import com.skeqi.mes.pojo.api.CheckAllRecipePT;
import com.skeqi.mes.pojo.api.GetcurrentBoltPT;
import com.skeqi.mes.service.all.AssembleBoltPService;

@Service
public class AssembleBoltPServiceImpl implements AssembleBoltPService {

	@Autowired
	AssembleBoltPDao dao;

	@Override
	public AssembleBoltPT find1(String stationName) {
		// TODO Auto-generated method stub
		return dao.find1(stationName);
	}

	@Override
	public AssembleBoltPT find2(Integer totalRecipeId, String stationBoltName, String snBarcode, String stepNo) {
		// TODO Auto-generated method stub
		return dao.find2(totalRecipeId, stationBoltName, snBarcode, stepNo);
	}

	@Override
	public AssembleBoltPT find3(Integer totalRecipeId, String stationBoltName, String snBarcode, String stepNo) {
		// TODO Auto-generated method stub
		return dao.find3(totalRecipeId, stationBoltName, snBarcode, stepNo);
	}

	@Override
	public AssembleBoltPT find4(String lineName, String stationBoltName, String snBarcode, String stepNo) {
		// TODO Auto-generated method stub
		return dao.find4(lineName, stationBoltName, snBarcode, stepNo);
	}

	@Override
	public AssembleBoltPT find5(String lineName, String stationBoltName, String snBarcode, String stepNo) {
		// TODO Auto-generated method stub
		return dao.find5(lineName, stationBoltName, snBarcode, stepNo);
	}

	@Override
	public AssembleBoltPT find6(String lineName, String stationBoltName, String snBarcode, String stepNo) {
		// TODO Auto-generated method stub
		return dao.find6(lineName, stationBoltName, snBarcode, stepNo);
	}

	@Override
	public AssembleBoltPT find7(String lineName, String stationBoltName, String snBarcode, String stepNo) {
		// TODO Auto-generated method stub
		return dao.find7(lineName, stationBoltName, snBarcode, stepNo);
	}

	@Override
	public AssembleBoltPT find8(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find8(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find9(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find9(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find10(String tempMinId) {
		// TODO Auto-generated method stub
		return dao.find10(tempMinId);
	}

	@Override
	public void update1(String aValues, String tValues, String tempAllResult, String emp, String tempMinId) {
		// TODO Auto-generated method stub
		dao.update1(aValues, tValues, tempAllResult, emp, tempMinId);
	}

	@Override
	public void insert1(AssembleBoltPT dx) {
		// TODO Auto-generated method stub
		dao.insert1(dx);
	}

	@Override
	public AssembleBoltPT find11(String snBarcode, String stationBoltName, String tempBoltName) {
		// TODO Auto-generated method stub
		return dao.find11(snBarcode, stationBoltName, tempBoltName);
	}

	@Override
	public AssembleBoltPT find12(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find12(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find13(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find13(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find14(String snBarcode, String stationBoltName, String tempBoltName) {
		// TODO Auto-generated method stub
		return dao.find14(snBarcode, stationBoltName, tempBoltName);
	}

	@Override
	public AssembleBoltPT find15(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find15(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find16(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find16(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find17(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find17(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find18(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find18(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find19(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find19(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find20(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find20(snBarcode, stationBoltName, tempMaterialName);
	}




	@Override
	public Integer find1001(String snBarcode, String stationName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find1001(snBarcode, stationName, tempMaterialName);
	}

	@Override
	public AssembleBoltPT find1002(String snBarcode, String stationName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find1002(snBarcode, stationName, tempMaterialName);
	}

	@Override
	public Integer insert1001(AssembleBoltPT dx) {
		// TODO Auto-generated method stub
		return dao.insert1001(dx);
	}

	@Override
	public Integer update1001(String snBarcode, String stationName, String tempMaterialName, Integer Y) {
		// TODO Auto-generated method stub
		return dao.update1001(snBarcode, stationName, tempMaterialName, Y);
	}

	@Override
	public AssembleBoltPT find1003(String snBarcode, String stationName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find1003(snBarcode, stationName, tempMaterialName);
	}

	@Override
	public Integer find1004(String snBarcode, String stationName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find1004(snBarcode, stationName, tempMaterialName);
	}

	@Override
	public Integer update1002(String aValues, String tValues, String tempAllResult, String emp, String snBarcode,
			String stationName, String boltName) {
		// TODO Auto-generated method stub
		return dao.update1002(aValues, tValues, tempAllResult, emp, snBarcode, stationName, boltName);
	}

	@Override
	public List<CheckAllRecipePT> queryProductionWayList(String sn) {
		// TODO Auto-generated method stub
		return dao.queryProductionWayList(sn);
	}

	//螺栓校验
	@Override
	public GetcurrentBoltPT finds1(String stationBoltName,String lineName) {
		// TODO Auto-generated method stub
		return dao.finds1(stationBoltName,lineName);
	}

	@Override
	public GetcurrentBoltPT finds2(String totalId,String lineName, String stationBoltName, String snBarcode, String stepNo) {
		// TODO Auto-generated method stub
		return dao.finds2(totalId,lineName, stationBoltName, snBarcode, stepNo);
	}

	@Override
	public GetcurrentBoltPT finds3(String totalId,String lineName, String stationBoltName, String snBarcode, String stepNo) {
		// TODO Auto-generated method stub
		return dao.finds3(totalId,lineName, stationBoltName, snBarcode, stepNo);
	}

	@Override
	public Integer finds4(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.finds4(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public Integer finds5(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.finds5(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public GetcurrentBoltPT finds6(String tempMinId) {
		// TODO Auto-generated method stub
		return dao.finds6(tempMinId);
	}

	@Override
	public Integer finds7(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.finds7(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public Integer finds8(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.finds8(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public String findAllPlan(String sn) {
		// TODO Auto-generated method stub
		return dao.findAllPlan(sn);
	}

	@Override
	public Integer find15s(String snBarcode, String stationBoltName, String tempMaterialName) {
		// TODO Auto-generated method stub
		return dao.find15s(snBarcode, stationBoltName, tempMaterialName);
	}

	@Override
	public Integer update1002s(String aValues, String tValues, String tempAllResult, String emp, String snBarcode,
			String stationName, String boltName) {
		// TODO Auto-generated method stub
		return dao.update1002s(aValues, tValues, tempAllResult, emp, snBarcode, stationName, boltName);
	}



}

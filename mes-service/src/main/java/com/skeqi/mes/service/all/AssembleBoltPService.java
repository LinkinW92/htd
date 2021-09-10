package com.skeqi.mes.service.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.api.AssembleBoltPT;
import com.skeqi.mes.pojo.api.CheckAllRecipePT;
import com.skeqi.mes.pojo.api.GetcurrentBoltPT;

public interface AssembleBoltPService {

	List<CheckAllRecipePT> queryProductionWayList(String sn);

	public AssembleBoltPT find1(String stationName);

	public AssembleBoltPT find2(Integer totalRecipeId,String stationBoltName,String snBarcode,String stepNo);

	public AssembleBoltPT find3(Integer totalRecipeId,String stationBoltName,String snBarcode,String stepNo);

	public AssembleBoltPT find4(String lineName,String stationBoltName,String snBarcode,String stepNo);

	public AssembleBoltPT find5(String lineName,String stationBoltName,String snBarcode,String stepNo);

	public AssembleBoltPT find6(String lineName,String stationBoltName,String snBarcode,String stepNo);

	public AssembleBoltPT find7(String lineName,String stationBoltName,String snBarcode,String stepNo);

	public AssembleBoltPT find8(String snBarcode,String stationBoltName,String tempMaterialName);

	public AssembleBoltPT find9(String snBarcode,String stationBoltName,String tempMaterialName);

	public AssembleBoltPT find10(String tempMinId);

	public void update1(String aValues,String tValues,String tempAllResult,String emp,String tempMinId);

	public void insert1(AssembleBoltPT dx);

	public AssembleBoltPT find11(String snBarcode,String stationBoltName,String tempBoltName);

	public AssembleBoltPT find12(String snBarcode,String stationBoltName,String tempMaterialName);

	public AssembleBoltPT find13(String snBarcode,String stationBoltName,String tempMaterialName);

	public AssembleBoltPT find14(String snBarcode,String stationBoltName,String tempBoltName);

	public AssembleBoltPT find15(String snBarcode,String stationBoltName,String tempMaterialName);

	public AssembleBoltPT find16(String snBarcode,String stationBoltName,String tempMaterialName);

	public AssembleBoltPT find17(String snBarcode,String stationBoltName,String tempMaterialName);

	public AssembleBoltPT find18(String snBarcode,String stationBoltName,String tempMaterialName);

	public AssembleBoltPT find19(String snBarcode,String stationBoltName,String tempMaterialName);

	public AssembleBoltPT find20(String snBarcode,String stationBoltName,String tempMaterialName);

	public Integer find15s(String snBarcode,String stationBoltName,String tempMaterialName);


	public Integer update1002s(String aValues,
			String tValues,
			String tempAllResult,
			String emp,
			String snBarcode,
			String stationName,
			String boltName);

	public Integer find1001(String snBarcode,
			String stationName,
			String tempMaterialName);

	public AssembleBoltPT find1002(String snBarcode,
			String stationName,
			String tempMaterialName);

	public Integer insert1001(AssembleBoltPT dx);

	public Integer update1001(String snBarcode,
			String stationName,
			String tempMaterialName,
			Integer Y);

	public AssembleBoltPT find1003(String snBarcode,
			String stationName,
			String tempMaterialName);

	public Integer find1004(String snBarcode,
			String stationName,
			String tempMaterialName);

	public Integer update1002(String aValues,
			String tValues,
			String tempAllResult,
			String emp,
			String snBarcode,
			String stationName,
			String boltName);

	//校验螺栓
	public 	String  findAllPlan(String sn);

	public GetcurrentBoltPT finds1(String stationBoltName,String lineName);

	public GetcurrentBoltPT finds2(String totalId,String lineName, String stationBoltName, String snBarcode, String stepNo);

	public GetcurrentBoltPT finds3(String totalId,String lineName, String stationBoltName, String snBarcode, String stepNo);

	public Integer finds4(String snBarcode, String stationBoltName, String tempMaterialName);

	public Integer finds5(String snBarcode, String stationBoltName, String tempMaterialName);

	public GetcurrentBoltPT finds6(String tempMinId);

	public Integer finds7(String snBarcode, String stationBoltName, String tempMaterialName);

	public Integer finds8(String snBarcode, String stationBoltName, String tempMaterialName);

}

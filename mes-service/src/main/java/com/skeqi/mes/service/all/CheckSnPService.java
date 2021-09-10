package com.skeqi.mes.service.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.CheckSnPT;
import com.skeqi.mes.pojo.api.UpdateReworkSnPT;

public interface CheckSnPService {

	JSONObject checkSN(String snBarcode, String station, String line, JSONObject jo);

	public UpdateReworkSnPT find1(String snBarconde);

	public UpdateReworkSnPT find2(String snBarconde);

	public UpdateReworkSnPT find3(String snBarconde);

	public UpdateReworkSnPT find4(String snBarconde);

	public UpdateReworkSnPT find5(String snBarconde);

	public void update1(String snBarconde);

	public void update2(String snBarconde);

	public void insert1(UpdateReworkSnPT dx);

	public void insert2(UpdateReworkSnPT dx);

	public void insert3(UpdateReworkSnPT dx);

	public void insert4(UpdateReworkSnPT dx);

	public void delete1(String snBarconde);

	public void delete2(String snBarconde);

	public void delete3(String snBarconde);

	public void delete4(String snBarconde);

	public void delete5(String snBarconde);

	public void delete6(String snBarconde);

	public void delete7(String snBarconde);

	public void delete8(String snBarconde);

	public void delete9(String snBarconde);

	public void delete10(String snBarconde);

	public UpdateReworkSnPT find6(String snBarconde);

	public void insert5(UpdateReworkSnPT dx);

	public void delete11(String snBarconde);

	public void delete12(String snBarconde);

	public void delete13(String snBarconde);

	public void updateRMesReworkSn(String sn);

	public List<UpdateReworkSnPT> insert2Find(String snBarconde);
	public List<UpdateReworkSnPT> insert3Find(String snBarconde);
	public List<UpdateReworkSnPT> insert4Find(String snBarconde);

	CheckSnPT queryCheckSnPTByLineAndStation1(@Param("line") String line,@Param("station")  String station);

}

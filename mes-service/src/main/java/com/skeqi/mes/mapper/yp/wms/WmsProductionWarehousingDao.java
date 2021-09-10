package com.skeqi.mes.mapper.yp.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 生产入库
 * @date 2021-07-20
 */
public interface WmsProductionWarehousingDao {

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 更新
	 * @param json
	 * @throws Exception
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 查询R
	 * @param json
	 * @return
	 */
	public List<JSONObject> listR(JSONObject json);

	/**
	 * 查询D
	 * @param json
	 * @return
	 */
	public List<JSONObject> listD(JSONObject json);

	/**
	 * 通过单号查询线边库产品下线明细
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findListDByListNo(@Param("listNo")String listNo);

	/**
	 * 查询线边库库存表
	 * @param packNo
	 * @param materialSn
	 * @return
	 */
	public JSONObject findLineSideLibraryStockByBatchCodeAndMaterialCode(@Param("packNo")String packNo,@Param("materialSn")String materialSn);

	/**
	 * 更新线边库库存表
	 * @param json
	 * @return
	 */
	public int updateLineSideLibraryStock(JSONObject json);

	/**
	 * 查询库存表
	 * @param packageId
	 * @param unitCode
	 * @return
	 */
	public JSONObject findStockByCode(@Param("packageId")String packageId,@Param("unitCode")String unitCode);

	/**
	 * 更新线边库产品下线表状态
	 * @param id
	 * @return
	 */
	public int updateStatus(@Param("id")Integer id,@Param("status")String status);

	/**
	 * 查询R跟D表数据
	 * @param listNo
	 * @return
	 */
	public List<JSONObject> findRAndD(@Param("listNo")String listNo);
	
	/**
	 * 查询所有产线
	 * @return
	 */
	public List<JSONObject> findLineAll();

}

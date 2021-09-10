package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsIncomingInspectionDao;
import com.skeqi.mes.service.yp.wms.WmsIncomingInspectionService;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * @author yinp
 * @explain 来料检验
 * @date 2021-07-16
 */
@Service
public class WmsIncomingInspectionServiceImpl implements WmsIncomingInspectionService {

	@Autowired
	WmsIncomingInspectionDao dao;

	@Autowired
	WmsStockService wmsStockService;

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		return dao.list(json);
	}

	/**
	 * 查询行
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> listRow(JSONObject json) {
		return dao.listRow(json);
	}

	/**
	 * 查询详情
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> listD(JSONObject json) {
		return dao.listD(json);
	}

	/**
	 * 更新详情
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void updateD(JSONObject json) throws Exception {

		if (dao.updateD(json) != 1) {
			throw new Exception("更新失败");
		}

	}

	/**
	 * 校验完成
	 *
	 * @param id
	 * @param listNo
	 * @throws Exception
	 */
	@Override
	public void verificationComplete(Integer id, String listNo, Integer checkUserId) throws Exception {
		// 查询检验完成的数据
		// 判断是否检验完成
		List<JSONObject> verificationCompleteList = dao.queryVerificationComplete(listNo);
		if (verificationCompleteList == null || verificationCompleteList.size() == 0) {
			throw new Exception("单据内无数据");
		}
		for (JSONObject jsonObject : verificationCompleteList) {
			// 单件码
			String code = jsonObject.getString("code");
			// 包装id
			String packingId = jsonObject.getString("packingId");

			// 数量
			Integer num = 0;
			if (code != null && !code.equals("")) {
				num = jsonObject.getInteger("unitQuantity");
			} else if (packingId != null && !packingId.equals("")) {
				num = jsonObject.getInteger("numberOfPackages");
			}
			
			if (num == 0) {
				throw new Exception("包装ID：" + packingId + ",单件码：" + code + "  未录入数量");
			}

			if (jsonObject.getString("checkNumber") == null) {
				throw new Exception("存在未检验完成的物料");
			}

			// 判断是否检验完成
			if (jsonObject.getString("InspectionMethod").equals("全检")) {
				// 全检
				if (!num.toString().equals(jsonObject.getString("checkNumber"))) {
					throw new Exception("存在未检验完成的物料");
				}

			} else {
				// 抽检
				if (num.toString().equals("0")) {
					throw new Exception("存在未检验完成的物料");
				}
			}
		}

		// 通过单据号查询表头
		JSONObject h = dao.findHByListNo(listNo);
		if (h.getString("status").equals("5")) {
			for (JSONObject jsonObject : verificationCompleteList) {
				// 单件码
				String code = jsonObject.getString("code");
				// 包装id
				String packingId = jsonObject.getString("packingId");

				// 通过单件码跟包装id查询库存
				JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
				if (stock == null) {
					throw new Exception("包装ID：" + packingId + "-单件码：" + code + "；库存不存在");
				}
				// 库存数的
				Integer number = 0;
				// 待检数
				Integer toBeInspectedNumber = stock.getInteger("toBeInspectedNumber");
				// 合格数
				Integer qualifiedNumber = jsonObject.getInteger("qualifiedNumber");
				toBeInspectedNumber = toBeInspectedNumber - qualifiedNumber;
				number = qualifiedNumber;
				// 更新库存
				JSONObject stockUpdate = new JSONObject();
				stockUpdate.put("id", stock.getInteger("id"));
				stockUpdate.put("unitCode", code);
				stockUpdate.put("packingId", packingId);
				if(code!=null && !code.equals("")) {
					stockUpdate.put("number", number);
				}else {
					stockUpdate.put("numberOfPackages", number);
				}
				stockUpdate.put("toBeInspectedNumber", toBeInspectedNumber);
				wmsStockService.update(stockUpdate);

			}
		}

		h.put("checkUserId", checkUserId);
		if(h.getString("status").equals("1")) {
			h.put("status", 2);
		}
		// 更新详情
		dao.update(h);

	}

	/**
	 * 查询R跟D表数据
	 *
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findRAndD(String listNo) {
		return dao.findRAndD(listNo);
	}

}

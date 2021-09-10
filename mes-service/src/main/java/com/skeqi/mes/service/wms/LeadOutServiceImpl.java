package com.skeqi.mes.service.wms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.LeadOutDao;



/**
 * 领用出库
 *
 * @author yinp
 * @date 2020年3月18日
 *
 */
@Service
public class LeadOutServiceImpl implements LeadOutService {

	@Autowired
	LeadOutDao dao;

	/**
	 * 查询
	 * @param josn
	 * @return
	 */
	@Override
	public List<JSONObject> list(JSONObject josn) {
		return dao.list(josn);
	}

	/**
	 * 通过名称查询物料id、NAME
	 *
	 * @param materialName
	 * @return
	 */
	@Override
	public List<JSONObject> findMaterialByName(String materialName) {
		return dao.findMaterialByName(materialName);
	}

	/**
	 * 通过名称查询项目id、NAME
	 *
	 * @param projectName
	 * @return
	 */
	@Override
	public List<JSONObject> findProjectByName(String projectName) {
		return dao.findProjectByName(projectName);
	}

	/**
	 * 查询库位id、NAME
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findLocationAll() {
		return dao.findLocationAll();
	}

	/**
	 * 通过物料、项目查询库存
	 *
	 * @param materialId
	 * @param projectId
	 * @return
	 */
	@Override
	public List<JSONObject> findStock(Integer materialId, Integer projectId, Integer locationId) {
		return dao.findStock(materialId, projectId, locationId);
	}

	/**
	 * 提交
	 *
	 * @param json
	 */
	@Override
	public void sub(JSONObject json) throws Exception {
		Integer materialId = json.getInteger("materialId");
		Integer projectId = json.getInteger("projectId");
		Integer locationId = json.getInteger("locationId");
		Integer userId = json.getInteger("userId");

		// 已选库存集合
		List<JSONObject> selectStoclList = JSONObject.parseArray(json.getString("list"), JSONObject.class);
		if (selectStoclList == null || selectStoclList.size() == 0) {
			throw new Exception("未选库存");
		}

		// 记录是否有异常
		boolean ifError = false;
		JSONObject errorJson = new JSONObject();

		// 查询库存
		List<JSONObject> stockList = dao.findStock(materialId, projectId, locationId);
		if (stockList == null || stockList.size() == 0) {

			// 确认此处发生异常
			ifError = true;

			if (selectStoclList.size() == 1) {
				errorJson.put("errorMsg", "选择的库存不存在");
				selectStoclList.get(0).put("materialNumber", 0);
			} else {
				errorJson.put("errorMsg", "选择的库存均不存在");
				for (int i = 0; i < selectStoclList.size(); i++) {
					selectStoclList.get(i).put("materialNumber", 0);
				}
			}

			throw new Exception(errorJson.toJSONString());
		}

		for (int j = 0; j < selectStoclList.size(); j++) {
			JSONObject jsonj = selectStoclList.get(j);

			i: for (int i = 0; i < stockList.size(); i++) {
				JSONObject jsoni = stockList.get(i);

				if (jsoni.getInteger("id").equals(jsonj.getInteger("id"))) {
					if (jsoni.getInteger("materialNumber") - jsoni.getInteger("lmminentRelease") < jsonj
							.getInteger("selectedNumber")) {
						// 剩余库存少于已选库存就抛出异常提示出去

						// 吧新的数据赋值给已选的数量
						selectStoclList.get(j).put("materialNumber", jsoni.getInteger("materialNumber"));
						selectStoclList.get(j).put("lmminentRelease", jsoni.getInteger("lmminentRelease"));

						// 确认此处发生异常
						ifError = true;
						System.out.println("132行出现了异常" + ifError);
					}
					break i;
				}

				// 如果到了最后一次还没退出本循环说明当前选择的物料已经没有库存了
				// 抛出异常提示
				if (i == (stockList.size() - 1)) {

					// 吧新的数据赋值给已选的数量
					selectStoclList.get(j).put("materialNumber", 0);

					// 确认此处发生异常
					ifError = true;
					System.out.println("145行出现了异常" + ifError);
				}
			}
		}

		// 确认是否发生过异常
		if (ifError) {
			// 抛出异常提示
			errorJson.put("list", selectStoclList);
			throw new Exception(errorJson.toJSONString());
		}

		// 生成单号
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		// 单号
		String listNo = "DCK" + s.format(new Date());

		StringBuffer sqlBuffer = new StringBuffer();
		// 获取到信息存入库存详情表
		for (JSONObject jsonObject : selectStoclList) {
			sqlBuffer.append("(");
			sqlBuffer.append("now(),");
			sqlBuffer.append(jsonObject.getInteger("materialId") + ",");
			sqlBuffer.append(jsonObject.getInteger("selectedNumber") + ",");
			sqlBuffer.append(jsonObject.getInteger("locationId") + ",");
			sqlBuffer.append("'" + listNo + "',");
			sqlBuffer.append(jsonObject.getInteger("projectId") + ",");
			sqlBuffer.append("'" + jsonObject.getString("tray") + "',");
			sqlBuffer.append(0 + ",");
			sqlBuffer.append(0 + ",");
			sqlBuffer.append(jsonObject.getInteger("id"));
			sqlBuffer.append("),");
		}

		// sql语句
		String sql = sqlBuffer.toString();
		// 去掉最后面的逗号
		sql = sql.substring(0, sql.length() - 1);
		// 新增临时库存详情表记录
		if (dao.addRStorageDetail(sql) <= 0) {
			throw new Exception("提交失败");
		}

		for (JSONObject jsonObject : selectStoclList) {
			// 更新物料库存待出库字段
			if (dao.updateStockNumber(jsonObject.getInteger("id"), jsonObject.getInteger("selectedNumber")) != 1) {
				throw new Exception("提交失败");
			}
		}

		// 保存出库队列
		List<JSONObject> identicalLocationJsonList = new ArrayList<JSONObject>();
		identicalLocationJsonList.add(selectStoclList.get(0));

		// 过滤掉相同库位
		for (int h = 0; h < selectStoclList.size(); h++) {
			JSONObject jsonh = selectStoclList.get(h);
			k: for (int k = 0; k < identicalLocationJsonList.size(); k++) {
				JSONObject jsonk = identicalLocationJsonList.get(k);

				if (jsonh.getInteger("locationId").equals(jsonk.getInteger("locationId"))) {
					break k;
				}

				if (k == identicalLocationJsonList.size() - 1) {
					identicalLocationJsonList.add(jsonh);
				}
			}
		}

		//出库队列
		sqlBuffer = new StringBuffer();
		for (JSONObject jsonObject : identicalLocationJsonList) {
			sqlBuffer.append("(");
			sqlBuffer.append("now(),");
			sqlBuffer.append("'" + listNo + "',");
			sqlBuffer.append("'" + jsonObject.getString("tray") + "',");
			sqlBuffer.append(0 + ",");
			sqlBuffer.append(jsonObject.getInteger("locationId"));
			sqlBuffer.append("),");
		}

		// sql语句
		sql = sqlBuffer.toString();
		// 去掉最后面的逗号
		sql = sql.substring(0, sql.length() - 1);
		// 新增临时出库队列
		if (dao.addROutTaskqueue(sql) <= 0) {
			throw new Exception("提交失败");
		}

		JSONObject approvalJson = new JSONObject();
		approvalJson.put("listNo", listNo);
		approvalJson.put("processId", 31);
		approvalJson.put("userId", userId);
		approvalJson.put("state", 2);
		if(dao.addApproval(approvalJson)!=1) {
			throw new Exception("提交失败");
		}

	}

}

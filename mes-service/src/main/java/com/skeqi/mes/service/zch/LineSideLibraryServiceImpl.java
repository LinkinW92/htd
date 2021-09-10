package com.skeqi.mes.service.zch;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.zch.LineSideLibraryDao;
import com.skeqi.mes.util.UDPUtils;
import com.skeqi.mes.util.yp.FileReading;

@Service
public class LineSideLibraryServiceImpl implements LineSideLibraryService {

	private String ptlIp = FileReading.getValue("config.properties", "PTLCallIp");

	@Autowired
	private LineSideLibraryDao dao;

	@Override
	public List<Map<String, Object>> findRockList(Map<String, Object> map) {
		return dao.findRockList(map);
	}

	@Override
	public Integer addRock(Map<String, Object> map) {
		Map<String, Object> rockMap = dao.getRock(map);
		if(rockMap != null) {
			return -1;
		}
		rockMap = dao.getRockByPtlNo(map);
		if(rockMap != null) {
			return -2;
		}
		return dao.insertRock(map);
	}

	@Override
	public Integer editRock(Map<String, Object> map) {
		Map<String, Object> rockMap = dao.getRock(map);
		if(rockMap != null) {
			return -1;
		}
		rockMap = dao.getRockByPtlNo(map);
		if(rockMap != null) {
			return -2;
		}
		return dao.editRock(map);
	}

	@Override
	public Integer deleteRock(Map<String, Object> map) {
		Map<String, Object> resultMap = dao.getRockConfigByRockId(map);
		if(resultMap != null) {
			return -1;
		}
		return dao.deleteRock(map);
	}

	@Override
	public List<Map<String, Object>> findRockConfigList(Map<String, Object> map) {
		return dao.findRockConfigList(map);
	}

	@Override
	public Integer addRockConfig(Map<String, Object> map) {
		Map<String, Object> rockMap = dao.getRockConfig(map);
		if(rockMap != null) {
			return 0;
		}
		return dao.insertRockConfig(map);
	}

	@Override
	public Integer editRockConfig(Map<String, Object> map) {
		Map<String, Object> rockMap = dao.getRockConfig(map);
		if(rockMap != null) {
			return 0;
		}
		return dao.editRockConfig(map);
	}

	@Override
	public Integer deleteRockConfig(Map<String, Object> map) {
		return dao.deleteRockConfig(map);
	}

	@Override
	public List<Map<String, Object>> findInventoryList(Map<String, Object> map) {
		return dao.findInventoryList(map);
	}

	@Override
	public Integer addInventory(Map<String, Object> map) {
		return dao.insertInventory(map);
	}

	@Override
	public Integer editInventory(Map<String, Object> map) {
		return dao.editInventory(map);
	}

	@Override
	public List<Map<String, Object>> findRockConfigVersionList(Map<String, Object> map) {
		return dao.findRockConfigVersionList(map);
	}

	@Override
	public Integer addRockConfigVersion(Map<String, Object> map) {
		Map<String, Object> rockMap = dao.getRockConfigVersionByVersion(map);
		if(rockMap != null) {
			return 0;
		}
		return dao.insertRockConfigVersion(map);
	}

	@Override
	public Integer editRockConfigVersion(Map<String, Object> map) {
		Map<String, Object> rockMap = dao.getRockConfigVersionByVersion(map);
		if(rockMap != null) {
			return 0;
		}
		return dao.editRockConfigVersion(map);
	}

	@Override
	public Integer deleteRockConfigVersion(Map<String, Object> map) {
		dao.deleteRockConfigByVersionId(map);
		return dao.deleteRockConfigVersion(map);
	}


	@Override
	public Integer enableRockConfigVersion(Map<String, Object> map) {
		Map<String, Object> rockMap = dao.getRockConfigVersion(map);
		if(rockMap != null) {
			dao.disableRockConfigVersion(rockMap);
		}
		return dao.enableRockConfigVersion(map);
	}

	@Override
	public List<Map<String, Object>> findMaterialFuzzy(Map<String, Object> map) {
		return dao.findMaterialFuzzy(map);
	}

	@Override
	public JSONObject findMaterialQuestKanban() throws ParseException {
		JSONObject jo = new JSONObject();
		String[] headerData = {"单据号", "产线", "工位", "物料编码", "物料名称", "数量", "请求时间", "未处理时长", "状态"};
		JSONArray bodyDatas = new JSONArray();
		List<Map<String, Object>> kanbanList = dao.findMaterialQuestKanban();
		for (Map<String, Object> kanbanMap : kanbanList) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
			Date cdt = sdf1.parse(kanbanMap.get("cdt").toString());
			Date now = new Date();
			Long subDate = now.getTime() - cdt.getTime();
			Long h = subDate / (60 * 60 * 1000);
			Long min = subDate / (60 * 1000) - h * 60;
//			Long s = subDate / 1000 - min * 60 - h * 60 * 60;
			if(h < 0) h = 0l;
			if(min < 0) min = 0l;
//			if(s < 0) s = 0l;
//			String timing = h + ":" + min + ":" + s;
			String sh = h.toString();
			String sm = min.toString();
			if(h < 10) sh = "0" + sh;
			if(min < 10) sm = "0" + sm;
			String timing = sh + ":" + sm;

			JSONObject status = new JSONObject();
			switch (kanbanMap.get("status").toString()) {
			case "0":
				status.put("text", "待处理");
				status.put("style", "color:#ff0000;font-weight: 900;");
				break;
			case "1":
				status.put("text", "拣货中");
				status.put("style", "color:#ffff00;font-weight: 900;");
				break;
			case "2":
				status.put("text", "已出库");
				status.put("style", "color:#00ff00;font-weight: 900;");
				break;
			case "5":
				status.put("text", "已拒绝");
				status.put("style", "color:#ff0000;font-weight: 900;");
				break;
			}

			List<Object> bodyData = new ArrayList<>();
			bodyData.add(kanbanMap.get("billNo"));
			bodyData.add(kanbanMap.get("NAME"));
			bodyData.add(kanbanMap.get("STATION_NAME"));
			bodyData.add(kanbanMap.get("materialNo"));
			bodyData.add(kanbanMap.get("materialName"));
			bodyData.add(kanbanMap.get("requiredQuantity").toString());
			bodyData.add(sdf2.format(cdt));
			bodyData.add(timing);
			bodyData.add(status);

			bodyDatas.add(bodyData);
		}
		jo.put("headerData", headerData);
		jo.put("bodyDatas", bodyDatas);
		return jo;
	}

	@Override
	public List<Map<String, Object>> reclaimingPTL(Map<String, Object> map) throws IOException {
		List<Map<String, Object>> list = new ArrayList<>();

		// 更新捡料员、捡料时间
		dao.updateRequestPicker(map);

		List<Map<String, Object>> requestList = dao.findMaterialRequestListByBillNo(map);
		String data = "P100";

		for (Map<String, Object> requestMap : requestList) {
			Map<String, Object> resultMap = new HashMap<>();

			Integer requiredQuantity = Integer.parseInt(requestMap.get("requiredQuantity").toString());

			List<Map<String, Object>> materialInstanceList = dao.findInventoryListOrderByQuantity(requestMap);

			resultMap.put("materialNo", requestMap.get("materialNo"));
			Integer remaindNumTotal = 0;

			for (Map<String, Object> materialInstanceMap : materialInstanceList) {
				Integer remaindNum = Integer.parseInt(materialInstanceMap.get("remaindNum").toString());
				remaindNumTotal += remaindNum;

				if(requiredQuantity <= 0) {
					continue;
				}

				Object ptlNo = materialInstanceMap.get("PTL_NO");
				if(!StringUtils.isEmpty(ptlNo)) {
					data += ptlNo;

					if(requiredQuantity <= remaindNum) {
						data += String.format("%05d", requiredQuantity);
					} else {
						data += String.format("%05d", remaindNum);
					}
				}
				requiredQuantity -= remaindNum;

			}

			resultMap.put("remaindNum", remaindNumTotal);
			list.add(resultMap);
		}

		if(data.length() > 4) {
			System.out.println("data: " + data);
//			String ip = "192.168.5.170";
//			String ip = "192.168.1.254";
//			String ip = "127.0.0.1";
			UDPUtils.sendUDP(data, ptlIp, 4001);
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> findRockListFuzzy(Map<String, Object> map) {
		return dao.findRockListFuzzy(map);
	}

	@Override
	public Map<String, Object> findMateriaInstanceByMaterialNo(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> batchList = dao.findMateriaInstanceBatchByMaterialNo(map);
		List<Map<String, Object>> singleList = dao.findMateriaInstanceSingleByMaterialNo(map);

		Map<String, Object> batchMap = new HashMap<>();
		Map<String, Object> singleMap = new HashMap<>();

		for (Map<String, Object> bMap : batchList) {
			if(!StringUtils.isEmpty(bMap.get("MATERIAL_BATCH")))
				batchMap.put(bMap.get("MATERIAL_BATCH").toString(), bMap.get("NUMBER_REMAINING"));
		}
		for (Map<String, Object> sMap : singleList) {
			if(!StringUtils.isEmpty(sMap.get("MATERIAL_SN")))
				singleMap.put(sMap.get("MATERIAL_SN").toString(), sMap.get("NUMBER_REMAINING"));
		}

		resultMap.put("batchMap", batchMap);
		resultMap.put("singleMap", singleMap);
		return resultMap;
	}

	public static void main(String[] args) {
//		String data = "D050105020503";
//		String ip = "192.168.5.170";
//		try {
//			UDPUtils.sendUDP(data, ip, 4000);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		System.out.println(FileReading.getValue("config.properties", "PTLCallIp"));
	}

}

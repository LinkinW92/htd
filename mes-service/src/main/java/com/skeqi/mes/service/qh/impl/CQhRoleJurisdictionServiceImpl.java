package com.skeqi.mes.service.qh.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.CQhRoleJurisdictionDao;
import com.skeqi.mes.service.qh.CQhRoleJurisdictionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yinp
 * @explain 角色权限
 * @date 2020-9-3
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CQhRoleJurisdictionServiceImpl implements CQhRoleJurisdictionService {

	@Autowired
	CQhRoleJurisdictionDao dao;

	@Override
	public List<JSONObject> findJurisdictionList() {

//		List<JSONObject> qjsonList = mapper.findJurisdictionList();
//		List<JSONObject> hjsonList = new ArrayList<JSONObject>();
//
//		for (JSONObject jsonObject : qjsonList) {
//			// 保存修改后的一级菜单
//			JSONObject yiJiMenuJson = new JSONObject();
//			yiJiMenuJson = jsonObject;
//
//			// 二级菜单集合
//			List<Object> erJiMenuObjectList = jsonObject.getJSONArray("data");
//			for (int i = 0; i < erJiMenuObjectList.size(); i++) {
//				// 二级菜单
//				JSONObject erJiMenuJson = JSONObject.parseObject(erJiMenuObjectList.get(i).toString());
//				if (JSONObject.parseArray(erJiMenuJson.getString("operationTypeList").toString()).size() > 0) {
//					// 操作类型集合（增删改查）
//					List<JSONObject> zsgcList = JSONObject.parseArray(erJiMenuJson.getString("operationTypeList"),
//							JSONObject.class);
//					for (int j = 0; j < zsgcList.size(); j++) {
//						JSONObject zsgcJson = zsgcList.get(j);
//						zsgcJson.put("menuName", getzsgc(zsgcJson.getInteger("operationType")));
//						zsgcJson.put("id", erJiMenuJson.getInteger("id") + "-" + zsgcJson.getInteger("operationType"));
//						zsgcJson.put("superiorMenuId", erJiMenuJson.getInteger("id"));
//						zsgcJson.remove("operationType");
//						zsgcList.set(j, zsgcJson);
//					}
//					erJiMenuJson.put("data", JSONObject.parse(zsgcList.toString()));
//					erJiMenuJson.remove("operationTypeList");
//					erJiMenuObjectList.set(i, erJiMenuJson);
//				} else {
//					// 三级菜单集合
//					List<JSONObject> sanJiMenuObjectList = JSONObject
//							.parseArray(erJiMenuJson.getJSONArray("data").toString(), JSONObject.class);
//					for (int j = 0; j < sanJiMenuObjectList.size(); j++) {
//						JSONObject sanJiMenuJson = JSONObject.parseObject(sanJiMenuObjectList.get(j).toString());
//						// 操作类型集合（增删改查）
//						List<JSONObject> zsgcList = JSONObject.parseArray(sanJiMenuJson.getString("operationTypeList"),
//								JSONObject.class);
//						if (zsgcList == null) {
//							continue;
//						}
//						for (int s = 0; s < zsgcList.size(); s++) {
//							JSONObject zsgcJson = zsgcList.get(s);
//							zsgcJson.put("menuName", getzsgc(zsgcJson.getInteger("operationType")));
//							zsgcJson.put("id", erJiMenuJson.getInteger("id") + "-" + sanJiMenuJson.getInteger("id")
//									+ "-" + zsgcJson.getInteger("operationType"));
//							zsgcJson.put("superiorMenuId", sanJiMenuJson.getInteger("id"));
//							zsgcJson.remove("operationType");
//							zsgcList.set(s, zsgcJson);
//						}
//						sanJiMenuJson.put("data", JSONObject.parse(zsgcList.toString()));
//						sanJiMenuJson.remove("operationTypeList");
//						sanJiMenuObjectList.set(j, sanJiMenuJson);
//					}
//					erJiMenuJson.put("data", sanJiMenuObjectList);
//					erJiMenuJson.remove("operationTypeList");
//					erJiMenuObjectList.set(i, erJiMenuJson);
//				}
//			}
//			yiJiMenuJson.put("data", erJiMenuObjectList);
//			hjsonList.add(yiJiMenuJson);
//		}

		return dao.findJurisdictionList();
	}

	public String getzsgc(Integer type) {
		switch (type) {
		case 1:
			return "新增";
		case 2:
			return "删除";
		case 3:
			return "修改";
		case 4:
			return "查询";
		default:
			return null;
		}
	}

	@Override
	public List<JSONObject> findRoleJurisdiction(Integer roleId) {
		// TODO Auto-generated method stub
		return dao.findRoleJurisdiction(roleId);
	}

	@Override
	public Integer addRoleJurisdiction(Integer roleId, String[] menu) throws Exception {

		dao.deleteRoleJurisdiction(roleId);

		StringBuffer sqlBuffer = new StringBuffer();

		for (String string : menu) {
			String[] menuString = string.split("-");
			if(menuString.length==2) {
				sqlBuffer.append("("+roleId+","+menuString[0]+","+menuString[1]+"),");
			}
		}
		String sql = "";
		if (sqlBuffer.length()>0){
			sql = sqlBuffer.toString();
			sql = sql.substring(0,sql.length()-1);
		}else {
			throw new Exception("请配置模块操作");
		}

		return dao.addRoleJurisdiction(sql);
	}

	public static void main(String[] args) {
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		String s = "1,1-10,2,3,3-1,3-2,3-3,3-4,4,4-5,4-6,4-7,4-8,4-9,5,6,7,8,9,10,11,11-11";
		String[] menuString = s.split(",");
		for (String string : menuString) {
			if(string.split("-").length==2) {
				JSONObject json = new JSONObject();
				json.put("menuId", string.split("-")[0]);
				json.put("operationTypeId", string.split("-")[1]);
				jsonList.add(json);
			}
		}

	}

}

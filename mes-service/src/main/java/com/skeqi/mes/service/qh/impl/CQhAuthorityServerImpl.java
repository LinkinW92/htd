package com.skeqi.mes.service.qh.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.CQhAuthorityDao;
import com.skeqi.mes.service.qh.CQhAuthorityServer;

/**
 * @author yinp
 * @explain 琦航权限
 * @date 2020-10-8
 */
@Service
public class CQhAuthorityServerImpl implements CQhAuthorityServer {
	@Autowired
	CQhAuthorityDao dao;

	@Override
	public List<JSONObject> findAuthorityInterfaceByRoleId(Integer roleId) {
//		// 一级菜单集合
//		List<JSONObject> yijiMenuList = mapper.findMenuByGrade(1);
//		// 二级菜单集合
//		List<JSONObject> erjiMenuList = mapper.findMenuByGrade(2);
//		// 三级菜单集合
//		List<JSONObject> sanjiMenuList = mapper.findMenuByGrade(3);
//		// 保存遍历好的菜单权限
//		List<JSONObject> menuList = new ArrayList<JSONObject>();
//
//		List<JSONObject> authorityInterfaceList = mapper.findAuthorityInterfaceByRoleId(roleId);
//
//		// 遍历一级菜单
//		for (JSONObject yijiMenu : yijiMenuList) {
//			// 一级菜单
//			JSONObject yijiMenuJson = new JSONObject();
//			// 路径
//			yijiMenuJson.put("path", yijiMenu.getString("path"));
//			// 一级菜单meta
//			JSONObject yijiMetaJson = new JSONObject();
//			// 模块名称
//			yijiMetaJson.put("title", yijiMenu.getString("menuName"));
//			// icon
//			yijiMetaJson.put("icon", yijiMenu.getString("icon"));
//			yijiMenuJson.put("meta", yijiMetaJson);
//			List<JSONObject> yijiChildrenJsonList = new ArrayList<JSONObject>();
//
//			// 遍历角色有的等级
//			for (JSONObject authorityInterfaceJson : authorityInterfaceList) {
//
//				if (1 == authorityInterfaceJson.getInteger("menuGrade")) {
//					for (JSONObject obj : JSONArray.parseArray(authorityInterfaceJson.getString("meta"))
//							.toJavaList(JSONObject.class)) {
//						if (yijiMenu.getString("menuName").equals(obj.getString("title"))) {
//							yijiMenuJson.put("authorityInterfaceList",
//									authorityInterfaceJson.getJSONArray("authorityInterfaceList"));
//						}
//					}
//				}
//			}
//
//			// 遍历二级菜单
//			for (JSONObject erjiMenu : erjiMenuList) {
//				// 二级菜单
//				JSONObject erjiMenuJson = new JSONObject();
//
//				if (erjiMenu.getInteger("superiorMenuId").equals(yijiMenu.getInteger("id"))) {
//					// 路径
//					erjiMenuJson.put("path", erjiMenu.getString("path"));
//					// 二级菜单meta
//					JSONObject erjiMetaJson = new JSONObject();
//					// 模块名称
//					erjiMetaJson.put("title", erjiMenu.getString("menuName"));
//					// icon
//					erjiMetaJson.put("icon", erjiMenu.getString("icon"));
//					erjiMenuJson.put("meta", erjiMetaJson);
//					List<JSONObject> erjiChildrenJsonList = new ArrayList<JSONObject>();
//
//					// 遍历角色有的等级
//					for (JSONObject authorityInterfaceJson : authorityInterfaceList) {
//						if (2 == authorityInterfaceJson.getInteger("menuGrade")) {
//							for (JSONObject obj : JSONArray.parseArray(authorityInterfaceJson.getString("meta"))
//									.toJavaList(JSONObject.class)) {
//								if (erjiMenu.getString("menuName").equals(obj.getString("title"))) {
//									erjiMenuJson.put("authorityInterfaceList",
//											authorityInterfaceJson.getJSONArray("authorityInterfaceList"));
//								}
//							}
//						}
//					}
//
//					// 遍历三级菜单
//					for (JSONObject sanjiMenu : sanjiMenuList) {
//						// 二级菜单
//						JSONObject sanjiMenuJson = new JSONObject();
//
//						if (sanjiMenu.getInteger("superiorMenuId").equals(erjiMenu.getInteger("id"))) {
//							// 路径
//							sanjiMenuJson.put("path", sanjiMenu.getString("path"));
//							// 二级菜单meta
//							JSONObject sanjiMetaJson = new JSONObject();
//							// 模块名称
//							sanjiMetaJson.put("title", sanjiMenu.getString("menuName"));
//							// icon
//							sanjiMetaJson.put("icon", sanjiMenu.getString("icon"));
//							sanjiMenuJson.put("meta", sanjiMetaJson);
//						}
//
//						// 遍历角色有的等级
//						for (JSONObject authorityInterfaceJson : authorityInterfaceList) {
//
//							if (3 == authorityInterfaceJson.getInteger("menuGrade")) {
//								for (JSONObject obj : JSONArray.parseArray(authorityInterfaceJson.getString("meta"))
//										.toJavaList(JSONObject.class)) {
//									if (sanjiMenu.getString("menuName").equals(obj.getString("title"))) {
//										sanjiMenuJson.put("authorityInterfaceList", authorityInterfaceJson.getJSONArray("authorityInterfaceList"));
//										erjiChildrenJsonList.add(sanjiMenuJson);
//									}
//								}
//							}
//						}
//						if(sanjiMenuJson.getString("authorityInterfaceList")==null) {
//							erjiMenuJson.put("hide", true);
//						}else {
//							erjiMenuJson.put("hide", false);
//						}
//					}
//
//					erjiMenuJson.put("children", erjiChildrenJsonList);
//					yijiChildrenJsonList.add(erjiMenuJson);
//
//				}
//
//			}
//
//			yijiMenuJson.put("children", yijiChildrenJsonList);
//			menuList.add(yijiMenuJson);
//		}
//		System.out.println(menuList);
//		deletePermissionsYouDonTHave(menuList);
		return dao.findAuthorityInterfaceByRoleId(roleId);
	}

	void deletePermissionsYouDonTHave(List<JSONObject> list) {

		for (int i = 0; i < list.size(); i++) {
			if (JSONArray.parseArray(list.get(i).getString("children")).size() == 0) {
				if (list.get(i).getString("authorityInterfaceList") == null) {
					list.remove(i);
					i--;
					break;
				}
			}
			for (int j = 0; j < JSONArray.parseArray(list.get(i).getString("children")).size(); j++) {
				if (JSONArray.parseArray(list.get(i).getString("children")).size()==0) {
					if (JSONArray.parseArray(list.get(i).getString("authorityInterfaceList")) == null) {
						JSONArray.parseArray(list.get(i).getString("children")).remove(j);
						j--;
						break;
					}
				}
				for (int h = 0; h < JSONArray.parseArray(JSONObject.parseObject(JSONArray.parseArray(list.get(i).getString("children")).get(j).toString()).getString("children")).size(); h++) {
					if(JSONArray.parseArray(JSONObject.parseObject(JSONArray.parseArray(list.get(i).getString("children")).get(j).toString()).getString("children")).size()==0) {
						if(JSONArray.parseArray(JSONObject.parseObject(JSONArray.parseArray(list.get(i).getString("children")).get(j).toString()).getString("authorityInterfaceList"))==null) {
							JSONArray.parseArray(JSONObject.parseObject(JSONArray.parseArray(list.get(i).getString("children")).get(j).toString()).getString("children")).remove(h);

							break;
						}
					}
				}

			}

		}

	}

	public static void main(String[] args) {
		String s = "[{\"path\":\"/\",\"children\":[],\"meta\":{\"icon\":\"shouye\",\"title\":\"首页\"},\"authorityInterfaceList\":[{\"operationType\":\"查询首页\",\"operationTypeCode\":\"list\"}]}, {\"path\":\"/skq\",\"children\":[{\"path\":\"userList\",\"children\":[],\"meta\":{\"icon\":\"more\",\"title\":\"用户管理\"},\"authorityInterfaceList\":[{\"operationType\":\"查询用户集合\",\"operationTypeCode\":\"list\"},{\"operationType\":\"新增用户\",\"operationTypeCode\":\"add\"},{\"operationType\":\"删除用户\",\"operationTypeCode\":\"delete\"},{\"operationType\":\"编辑用户\",\"operationTypeCode\":\"edit\"}]},{\"path\":\"roleManager\",\"children\":[],\"meta\":{\"icon\":\"more\",\"title\":\"角色管理\"},\"authorityInterfaceList\":[{\"operationType\":\"查询角色集合\",\"operationTypeCode\":\"list\"},{\"operationType\":\"新增角色\",\"operationTypeCode\":\"add\"},{\"operationType\":\"删除角色\",\"operationTypeCode\":\"delete\"},{\"operationType\":\"编辑角色\",\"operationTypeCode\":\"edit\"},{\"operationType\":\"分配角色权限\",\"operationTypeCode\":\"assignPermissions\"}]},{\"path\":\"Department\",\"children\":[],\"meta\":{\"icon\":\"more\",\"title\":\"部门管理\"}},{\"path\":\"lineManager\",\"children\":[],\"meta\":{\"icon\":\"more\",\"title\":\"产线管理\"}},{\"path\":\"stationManager\",\"children\":[],\"meta\":{\"icon\":\"more\",\"title\":\"工位管理\"}},{\"path\":\"programerRegiste\",\"children\":[],\"meta\":{\"icon\":\"more\",\"title\":\"程序注册\"}}],\"meta\":{\"icon\":\"moban\",\"title\":\"基础建模\"}}, {\"path\":\"/productions\",\"children\":[{\"path\":\"/material\",\"children\":[],\"meta\":{\"icon\":\"wuliao\",\"title\":\"物料\"}}],\"meta\":{\"icon\":\"shengchan\",\"title\":\"生产管理\"}}]";

		List<JSONObject> list = JSONArray.parseArray(s).toJavaList(JSONObject.class);
		for (int i = 0; i < list.size(); i++) {
			if (JSONArray.parseArray(list.get(i).getString("children")).size() == 0) {
				if (list.get(i).getString("authorityInterfaceList") == null) {
					list.remove(i);
					break;
				}
			}

		}
	}

}

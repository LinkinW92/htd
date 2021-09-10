package com.skeqi.mes.service.wms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.Encryption;
import com.skeqi.mes.mapper.wms.DepartmentDao;
import com.skeqi.mes.util.yp.CheckUtil;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDao dao;

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> list() {
		List<JSONObject> list = dao.list();
		list = get(list, "0");
		return list;
	}

	public List<JSONObject> get(List<JSONObject> list, String id) {
		List<JSONObject> a = new ArrayList<JSONObject>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getString("superiorId").equals(id)) {
				JSONObject b = list.get(i);
				List<JSONObject> aList = get(list, list.get(i).getString("id"));
				if (aList.size() > 0) {
					b.put("list", aList);
				}
				a.add(b);
			}
		}

		return a;
	}

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String name = json.getString("name");
		Integer superiorId = json.getInteger("superiorId");

		// 查询部门名称存在得数量
		if (dao.findCountName(name, superiorId, null) > 0) {
			throw new Exception("部门名称已存在");
		}

		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}
	}

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		Integer id = json.getInteger("id");
		String name = json.getString("name");
		Integer superiorId = json.getInteger("superiorId");
		String executive = json.getString("executive");

		List<JSONObject> selectUserList = JSONObject.parseArray(executive, JSONObject.class);
		if (selectUserList == null) {
			json.put("executive", "[]");
		}

		// 查询部门名称存在得数量
		if (dao.findCountName(name, superiorId, id) > 0) {
			throw new Exception("部门名称已存在");
		}

		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}

		// 删除部门主管
		dao.deleteHeadOfDepartment(id);
		if (selectUserList != null) {
			for (int i = 0; i < selectUserList.size(); i++) {
				// 新增部门主管
				JSONObject headOfDepartment = new JSONObject();
				headOfDepartment.put("departmentId", id);
				headOfDepartment.put("userId", selectUserList.get(i).getInteger("id"));
				headOfDepartment.put("order", i + 1);
				dao.addHeadOfDepartment(headOfDepartment);
			}
		}

	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void delete(int id) throws Exception {
		// 通过部门id查询有多少用户
		if (dao.findUserCountByDepartmentId(id) > 0) {
			throw new Exception("该部门有已绑定的用户数据");
		}

		// 通过部门id查询有多少下级部门
		if (dao.findDepartmentCountBySuperiorId(id) > 0) {
			throw new Exception("该部门有已绑定的下级部门");
		}

		if (dao.delete(id) != 1) {
			throw new Exception("删除失败");
		}

		// 删除部门主管
		dao.deleteHeadOfDepartment(id);
	}

	/**
	 * 查询部门
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findDepartmentBySuperiorId(Integer superiorId) {

		List<JSONObject> list = dao.findDepartmentBySuperiorId(superiorId);

		return list;
	}

	/**
	 * 通过部门ID查询用户
	 *
	 * @param departmentId
	 * @return
	 */
	@Override
	public List<JSONObject> findUserByDepartmentId(int departmentId) {
		return dao.findUserByDepartmentId(departmentId);
	}

	/**
	 * 批量导入用户
	 *
	 * @throws Exception
	 */
	@Override
	public void batchImportUsers(Integer departmentId, String users) throws Exception {
		List<JSONObject> userList = JSONObject.parseArray(users, JSONObject.class);

		List<JSONObject> roleList = dao.findRoleAll();

		// 查询所有职级
		List<JSONObject> rankList = dao.findRankAll();

		for (JSONObject jsonObject : userList) {
			jsonObject.put("status", 1);
			if (jsonObject.getString("用户名") == null) {
				continue;
			}

			if (jsonObject.getString("用户名").equals("admin")) {
				continue;
			}

			if (jsonObject.getString("电话") != null) {
				try {
					CheckUtil.phone(jsonObject.getString("电话"));
				} catch (Exception e) {
					jsonObject.put("电话", "");
				}
			}

			if (jsonObject.getString("职级") != null) {
				for (JSONObject rank : rankList) {
					if (rank.getString("rankName").equals(jsonObject.getString("职级"))) {
						jsonObject.put("rankId", rank.getInteger("id"));
					}
				}
				if (jsonObject.getString("rankId") == null || jsonObject.getString("rankId").equals("")) {

					JSONObject rank = new JSONObject();
					rank.put("rankName", jsonObject.getString("职级"));
					rank.put("grade", rankList.get(rankList.size() - 1).getInteger("grade"));
					dao.addRank(rank);
					jsonObject.put("rankId", rank.getInteger("id"));
				}
			}

			Integer roleId = null;

			jsonObject.put("userName", jsonObject.getString("用户名"));
			jsonObject.put("state", 1);
			jsonObject.put("departmentId", departmentId);
			jsonObject.put("roleId", roleId);
			jsonObject.put("mobile", jsonObject.getString("电话"));
			jsonObject.put("position", jsonObject.getString("职位"));
			jsonObject.put("email", jsonObject.getString("邮箱"));
			jsonObject.put("name", jsonObject.getString("姓名"));

			if (jsonObject.getString("角色") != null) {
				for (int i = 0; i < roleList.size(); i++) {
					if (jsonObject.getString("角色").equals(roleList.get(i).getString("roleName"))) {
						roleId = roleList.get(i).getInteger("id");
					}
				}
			}

			jsonObject.put("roleId", roleId);
			JSONObject user = dao.findUserByUserName(jsonObject.getString("用户名"));
			if (user == null) {

				String password = Encryption.getPassWord("123456" + jsonObject.getString("用户名") + 666666 + "123456",
						555);
				jsonObject.put("password", password);
				// 新增
				dao.addUser(jsonObject);
			} else {
				// 更新
				jsonObject.put("id", user.getInteger("id"));
				dao.updateUser(jsonObject);
			}
		}

	}

	/**
	 * 新增用户
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void addUser(JSONObject json) throws Exception {
		String userName = json.getString("userName");
		String password = json.getString("password");
		JSONObject user = dao.findUserByUserName(json.getString("userName"));
		if (user != null) {
			throw new Exception("用户名已存在");
		}
		if (password != null) {
			password = Encryption.getPassWord(password + userName + 666666 + password, 555);
			json.put("password", password);
		} else {
			password = Encryption.getPassWord("123456" + userName + 666666 + "123456", 555);
			json.put("password", password);
		}

		dao.addUser(json);
	}

	/**
	 * 更新用户
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void updateUser(JSONObject json) throws Exception {
		String userName = json.getString("userName");
		String password = json.getString("password");
		if (password != null) {
			password = Encryption.getPassWord(password + userName + 666666 + password, 555);
			json.put("password", password);
		}

		dao.updateUser(json);
	}

	/**
	 * 删除用户
	 *
	 * @param userId
	 * @return
	 */
	@Override
	public void deleteUser(Integer userId) {
		dao.deleteUser(userId);
	}

}

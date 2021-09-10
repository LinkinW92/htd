package com.skeqi.mes.controller.fqz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesMenuCrudT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleT;
import com.skeqi.mes.pojo.Ztree;
import com.skeqi.mes.pojo.wms.CWmsDepartmentT;
import com.skeqi.mes.service.fqz.RoleService;
import com.skeqi.mes.util.ToolUtils;

import net.sf.json.JSONArray;

@RequestMapping("roles")
@Controller
public class RoleController {

	@Autowired
	private RoleService service;

	/**
	 * 查询所有角色
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="findList",method = RequestMethod.POST)
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			List<CMesRoleT> list = service.findRoleList();

			data.put("msg", "查询成功！");
			data.put("code", true);
			result.put("result", data);
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", e.getMessage());
			data.put("code", false);
			result.put("result", data);
		} finally {
			response.getWriter().append(result.toJSONString());
		}
	}

	@SuppressWarnings("unused")
	@RequestMapping("/getmenu")
	public @ResponseBody List<Ztree> findMenu(HttpServletRequest request){
		int a = 200;
		List<Ztree> list = new ArrayList<Ztree>();
		String id = request.getParameter("id");
		List<CMesMenuT> findMenu = service.findMenu(Integer.parseInt(id));
		for (CMesMenuT cMesMenuT : findMenu) {
			List<CMesMenuCrudT> findCrud = service.findCrud(Integer.parseInt(id),cMesMenuT.getId());
			List<Integer> Crudlist = new ArrayList<Integer>();
			for (CMesMenuCrudT cMesMenuCrudT : findCrud) {
				Crudlist.add(cMesMenuCrudT.getMenuCrudId());
			}
			Ztree z = new Ztree();
			if(Crudlist.size()>0){
				z.setId(cMesMenuT.getId());
				z.setName(cMesMenuT.getMenuName());
				z.setPid(0);
				z.setChecked("true");
			}else{
				z.setId(cMesMenuT.getId());
				z.setName(cMesMenuT.getMenuName());
				z.setPid(0);
			}

			Ztree z1 = new Ztree();
			if(Crudlist.contains(1)){
				z1.setId(a);
				z1.setName("添加");
				z1.setPid(cMesMenuT.getId());
				z1.setChecked("true");
			}else{
				z1.setId(a);
				z1.setName("添加");
				z1.setPid(cMesMenuT.getId());
			}

			a++;

			Ztree z2 = new Ztree();
			if(Crudlist.contains(2)){
				z2.setId(a);
				z2.setName("修改");
				z2.setPid(cMesMenuT.getId());
				z2.setChecked("true");
			}else{
				z2.setId(a);
				z2.setName("修改");
				z2.setPid(cMesMenuT.getId());
			}

			a++;

			Ztree z3 = new Ztree();
			if(Crudlist.contains(3)){
				z3.setId(a);
				z3.setName("删除");
				z3.setPid(cMesMenuT.getId());
				z3.setChecked("true");
			}else{
				z3.setId(a);
				z3.setName("删除");
				z3.setPid(cMesMenuT.getId());
			}

			a++;

			list.add(z);
			list.add(z1);
			list.add(z2);
			list.add(z3);
			}
		return list;
	}

	@RequestMapping("/editmenu")
	@ResponseBody
	public Map<String,Object> editmenu(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String rid = request.getParameter("rid");  //角色id
		String list = request.getParameter("nodes");

		System.err.println("list====="+list);
		try {
			service.deleteMenu(Integer.parseInt(rid));  //删除原有的权限
			JSONArray jsonArray = JSONArray.fromObject(list);
			System.err.println("jsonArray====="+jsonArray);
			for(int i=0;i<jsonArray.size();i++){
				Object object = jsonArray.get(i);
				String str = (String)object;
				JSONObject jsonObject1 =JSONObject.parseObject(str);
				if(jsonObject1.get("name").equals("添加")){   //添加权限
					service.addRoleMenu(Integer.parseInt(rid), Integer.parseInt(jsonObject1.get("id").toString()),1);
				}else if(jsonObject1.get("name").equals("修改")){   //修改权限
					service.addRoleMenu(Integer.parseInt(rid), Integer.parseInt(jsonObject1.get("id").toString()),2);
				}else if(jsonObject1.get("name").equals("删除")){   //删除权限
					service.addRoleMenu(Integer.parseInt(rid), Integer.parseInt(jsonObject1.get("id").toString()),3);
				}
			}
			map.put("msg",1);
		} catch (Exception e) {
			map.put("msg",2);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}
}

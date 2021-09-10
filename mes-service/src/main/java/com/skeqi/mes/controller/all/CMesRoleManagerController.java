package com.skeqi.mes.controller.all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.neo4j.cypher.internal.compiler.v2_2.perty.recipe.Pretty.listAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesCrud;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMenuCrudT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesRoleMenuT;
import com.skeqi.mes.pojo.CMesRoleMenuTl;
import com.skeqi.mes.pojo.CMesRoleT;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.pojo.Ztree;
import com.skeqi.mes.service.all.CMesRoleTService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;

/***
 *
 * @author ENS 角色管理
 *
 */

@Controller
@RequestMapping(value = "roles")
public class CMesRoleManagerController {





	//
	// @RequestMapping("/getmenu")
	// public @ResponseBody List<Ztree> findMenu(HttpServletRequest request){
	// int a = 200;
	// List<Ztree> list = new ArrayList<Ztree>();
	// String id = request.getParameter("id");
	// CMesMenuT menu = new CMesMenuT();
	// menu.setId(Integer.parseInt(id));
	// List<CMesMenuT> findMenu = roleService.findAllMenu(menu);
	//
	// for (CMesMenuT cMesMenuT : findMenu) {
	// List<CMesMenuCrudT> findCrud = roleService.find
	//
	// service.findCrud(Integer.parseInt(id),cMesMenuT.getId());
	// List<Integer> Crudlist = new ArrayList<Integer>();
	// for (CMesMenuCrudT cMesMenuCrudT : findCrud) {
	// Crudlist.add(cMesMenuCrudT.getMenuCrudId());
	// }
	// Ztree z = new Ztree();
	// if(Crudlist.size()>0){
	// z.setId(cMesMenuT.getId());
	// z.setName(cMesMenuT.getMenuName());
	// z.setPid(0);
	// z.setChecked("true");
	// }else{
	// z.setId(cMesMenuT.getId());
	// z.setName(cMesMenuT.getMenuName());
	// z.setPid(0);
	// }
	//
	// Ztree z1 = new Ztree();
	// if(Crudlist.contains(1)){
	// z1.setId(a);
	// z1.setName("添加");
	// z1.setPid(cMesMenuT.getId());
	// z1.setChecked("true");
	// }else{
	// z1.setId(a);
	// z1.setName("添加");
	// z1.setPid(cMesMenuT.getId());
	// }
	//
	// a++;
	//
	// Ztree z2 = new Ztree();
	// if(Crudlist.contains(2)){
	// z2.setId(a);
	// z2.setName("修改");
	// z2.setPid(cMesMenuT.getId());
	// z2.setChecked("true");
	// }else{
	// z2.setId(a);
	// z2.setName("修改");
	// z2.setPid(cMesMenuT.getId());
	// }
	//
	// a++;
	//
	// Ztree z3 = new Ztree();
	// if(Crudlist.contains(3)){
	// z3.setId(a);
	// z3.setName("删除");
	// z3.setPid(cMesMenuT.getId());
	// z3.setChecked("true");
	// }else{
	// z3.setId(a);
	// z3.setName("删除");
	// z3.setPid(cMesMenuT.getId());
	// }
	//
	// a++;
	//
	// list.add(z);
	// list.add(z1);
	// list.add(z2);
	// list.add(z3);
	// }
	// return list;
	// }
	//
	// @RequestMapping("/editmenu")
	// @ResponseBody
	// public JSONObject editmenu(HttpServletRequest request){
	// JSONObject json = new JSONObject();
	// String rid = request.getParameter("rid"); //角色id
	// String list = request.getParameter("nodes");
	// RoleT role = new RoleT();
	// role.setId(Integer.parseInt(rid));
	// try {
	// roleService.updateRole(role, list);
	// json.put("code", 0);
	// }catch (ServicesException e) {
	// json.put("code", e.getCode());
	// json.put("msg", e.getMessage());
	// } catch (Exception e) {
	// json.put("code", -1);
	// json.put("msg", e.getMessage());
	// }
	// return json;
	// }

}

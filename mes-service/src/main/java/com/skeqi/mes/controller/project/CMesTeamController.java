package com.skeqi.mes.controller.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesTeamT;
import com.skeqi.mes.service.project.CMesTeamService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="api",produces = MediaType.APPLICATION_JSON)
@Api(value = "班组管理", description = "班组管理", produces = MediaType.APPLICATION_JSON)
public class CMesTeamController {

	@Autowired
	CMesTeamService service;



	@RequestMapping(value="findTeam",method=RequestMethod.POST)
	@ApiOperation(value = "班组列表", notes = "班组列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "名称", required = false, paramType = "query"),
      @ApiImplicitParam(name = "pages",value = "当前页", required = false, paramType = "query"),
    })
	public JSONObject findTeam(HttpServletRequest request, String name,Integer pages){
		JSONObject json = new JSONObject();
		try {
			List<CMesTeamT> findAllTeam = service.findAllTeam(name);
			if(pages==null || pages==0){
				findAllTeam = service.findAllTeam(name);
			}else{
				 PageHelper.startPage(pages,10);
				 findAllTeam = service.findAllTeam(name);
				 PageInfo<CMesTeamT> pageinfo = new PageInfo<CMesTeamT>(findAllTeam,5);
				 json.put("pageNum", pageinfo.getTotal());
				 findAllTeam = pageinfo.getList();
			}
			json.put("code",0);
			json.put("info", findAllTeam);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}


	@RequestMapping(value="addTeam",method=RequestMethod.POST)
	@ApiOperation(value = "添加班组", notes = "添加班组")
    @ApiImplicitParams({
	    @ApiImplicitParam(name = "shiftId",value = "班次id", required = true, paramType = "query"),
      @ApiImplicitParam(name = "name",value = "名称", required = true, paramType = "query"),
      @ApiImplicitParam(name = "dis",value = "备注", required = false, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="班组管理", method="新增班组")
	public JSONObject addTeam(HttpServletRequest request, CMesTeamT team){
		JSONObject json = new JSONObject();
		Integer a =service.findByName(team.getName());
		if(a!=0){
			json.put("code",1);
			json.put("msg", "当前班组已存在");
		}else {
			try {
				service.addTeam(team);
				json.put("code",0);
				json.put("msg", "添加成功");
			}catch (ServicesException e) {
				// TODO: handle exception
				json.put("code",1);
				json.put("msg", e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				// TODO: handle exception
				json.put("code",1);
				json.put("msg", "未知错误");
			}
		}
		return json;
	}


	@RequestMapping(value="updateTeam",method=RequestMethod.POST)
	@ApiOperation(value = "修改班组", notes = "修改班组")
    @ApiImplicitParams({
	    @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
	    @ApiImplicitParam(name = "shiftId",value = "班次id", required = true, paramType = "query"),
	    @ApiImplicitParam(name = "name",value = "名称", required = true, paramType = "query"),
	    @ApiImplicitParam(name = "dis",value = "备注", required = false, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="班组管理", method="编辑班组")
	public JSONObject updateTeam(HttpServletRequest request, CMesTeamT team){
		JSONObject json = new JSONObject();
		try {
			service.updateTeam(team);
			json.put("code",0);
			json.put("msg", "修改成功");
		}catch (ServicesException e) {
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value="deleteTeam",method=RequestMethod.POST)
	@ApiOperation(value = "删除班组", notes = "删除班组")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="班组管理", method="删除班组")
	public JSONObject deleteTeam(HttpServletRequest request, Integer id){
		JSONObject json = new JSONObject();
		try {
			service.delTeam(id);
			json.put("code",0);
			json.put("msg", "删除成功");
		}catch (ServicesException e) {
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", "未知错误");
		}
		return json;
	}

}

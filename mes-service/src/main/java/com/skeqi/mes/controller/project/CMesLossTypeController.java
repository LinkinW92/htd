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
import com.skeqi.mes.pojo.project.CMesLossReasonT;
import com.skeqi.mes.pojo.project.CMesLossTypeT;
import com.skeqi.mes.service.project.CMesLossTypeService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="api",produces = MediaType.APPLICATION_JSON)
@Api(value = "损失类型管理", description = "损失类型管理", produces = MediaType.APPLICATION_JSON)
public class CMesLossTypeController {

	@Autowired
	CMesLossTypeService service;


	@RequestMapping(value="findLossType",method=RequestMethod.POST)
	@ApiOperation(value = "损失类型列表", notes = "损失类型列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "名称", required = false, paramType = "query"),
      @ApiImplicitParam(name = "pages",value = "当前页", required = false, paramType = "query"),
    })
	public JSONObject findLossType(HttpServletRequest request, String name,Integer pages){
		JSONObject json = new JSONObject();
		try {
			List<CMesLossTypeT> findAllLoss = null;
			if(pages==0 || pages==null){
				 findAllLoss = service.findAllLoss(name);
			}else{
				 PageHelper.startPage(pages,10);
				 findAllLoss = service.findAllLoss(name);
				 PageInfo<CMesLossTypeT> pageinfo  = new PageInfo<CMesLossTypeT>(findAllLoss,5);
				 json.put("pageNum", pageinfo.getTotal());
				 findAllLoss = pageinfo.getList();
			}
			json.put("code",0);
			json.put("info", findAllLoss);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}



	@RequestMapping(value="addLossType",method=RequestMethod.POST)
	@ApiOperation(value = "添加损失类型", notes = "添加损失类型")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "名称", required = true, paramType = "query"),
      @ApiImplicitParam(name = "note",value = "备注", required = false, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="损失类型", method="添加损失类型")
	public JSONObject addLossType(HttpServletRequest request, String name,String note){
		JSONObject json = new JSONObject();
		try {
			service.addLoss(name, note);
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
		return json;
	}


	@RequestMapping(value="updateLossType",method=RequestMethod.POST)
	@ApiOperation(value = "修改损失类型", notes = "修改损失类型")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "故障名称", required = true, paramType = "query"),
      @ApiImplicitParam(name = "note",value = "备注", required = false, paramType = "query"),
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="损失类型", method="修改损失类型")
	public JSONObject updateLossType(HttpServletRequest request, String name,String note,Integer id){
		JSONObject json = new JSONObject();
		try {
			service.updateLoss(name, note, id);
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

	@RequestMapping(value="deleteLossType",method=RequestMethod.POST)
	@ApiOperation(value = "删除故障类型", notes = "删除故障类型")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="损失类型", method="删除损失类型")
	public JSONObject deleteLossType(HttpServletRequest request, Integer id){
		JSONObject json = new JSONObject();
		try {
			service.delLoss(id);
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


	@RequestMapping(value="findLossReason",method=RequestMethod.POST)
	@ApiOperation(value = "损失原因列表", notes = "损失原因列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "pages",value = "当前页", required = false, paramType = "query"),
    })
	public JSONObject findLossReason(HttpServletRequest request, Integer pages){
		JSONObject json = new JSONObject();
		try {
			List<CMesLossReasonT> findReason = null;
			pages=null==pages?0:pages;
			if(pages==0 || pages==null){
			     findReason = service.findReason();
			}else{
				 PageHelper.startPage(pages,10);
				 findReason = service.findReason();
				 PageInfo<CMesLossReasonT> pageinfo  = new PageInfo<CMesLossReasonT>(findReason,5);
				 json.put("pageNum", pageinfo.getTotal());
				 findReason = pageinfo.getList();
			}
			json.put("code",0);
			json.put("info", findReason);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}




	@RequestMapping(value="addLossReason",method=RequestMethod.POST)
	@ApiOperation(value = "添加损失原因", notes = "添加损失类型")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "reasonNo",value = "编号", required = true, paramType = "query"),
      @ApiImplicitParam(name = "name",value = "名称", required = true, paramType = "query"),
      @ApiImplicitParam(name = "note",value = "备注", required = false, paramType = "query"),
      @ApiImplicitParam(name = "lossId",value = "损失类型Id", required = false, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="损失原因", method="添加损失原因")
	public JSONObject addLossReason(HttpServletRequest request, String reasonNo,String name,String note,Integer lossId){
		JSONObject json = new JSONObject();
		try {
			service.addReason(reasonNo,lossId, name, note);
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
		return json;
	}


	@RequestMapping(value="updateLossReason",method=RequestMethod.POST)
	@ApiOperation(value = "修改损失原因", notes = "修改损失原因")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "reasonNo",value = "损失原因编号", required = true, paramType = "query"),
      @ApiImplicitParam(name = "name",value = "原因名称", required = true, paramType = "query"),
      @ApiImplicitParam(name = "note",value = "备注", required = false, paramType = "query"),
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
      @ApiImplicitParam(name = "lossId",value = "损失类型Id", required = false, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="损失原因", method="修改损失原因")
	public JSONObject updateLossReason(HttpServletRequest request, String reasonNo,String name,String note,Integer id,Integer lossId){
		System.err.println("id-=="+id+"===="+reasonNo+"====="+lossId+"==="+name+"===="+note);
		JSONObject json = new JSONObject();
		try {
			service.updateReason(reasonNo,lossId, name, note, id);
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

	@RequestMapping(value="deleteLossReason",method=RequestMethod.POST)
	@ApiOperation(value = "删除损失原因", notes = "删除损失原因")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="损失原因", method="删除损失原因")
	public JSONObject deleteLossReason(HttpServletRequest request, Integer id){
		JSONObject json = new JSONObject();
		try {
			service.delReason(id);
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

	@RequestMapping(value="findReasonNameByNO",method=RequestMethod.POST)
	@ApiOperation(value = "根据损失原因编号查询损失name", notes = "根据损失原因编号查询损失name")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "reasonNo",value = "reasonNo", required = true, paramType = "query"),
    })
	public JSONObject findReasonNameByNO(HttpServletRequest request, String reasonNo){
		JSONObject json = new JSONObject();
		try {
			String findReasonNameByNO = service.findReasonNameByNO(reasonNo);
			json.put("code",0);
			json.put("name",findReasonNameByNO);
			json.put("msg", "返回成功");
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

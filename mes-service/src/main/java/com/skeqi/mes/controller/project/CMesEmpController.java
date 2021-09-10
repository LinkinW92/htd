package com.skeqi.mes.controller.project;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.service.project.CMesAndonPlanService;
import com.skeqi.mes.service.project.CMesEmpService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="api",produces = MediaType.APPLICATION_JSON)
@Api(value = "员工管理", description = "员工管理", produces = MediaType.APPLICATION_JSON)
public class CMesEmpController {

	@Autowired
	CMesEmpService service;

	@Autowired
	CMesAndonPlanService Planservice;

	@RequestMapping(value="findAllEmp",method=RequestMethod.POST)
	@ApiOperation(value = "员工列表", notes = "员工列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "名称", required = false, paramType = "query"),
      @ApiImplicitParam(name = "pages",value = "当前页", required = false, paramType = "query"),
    })
	public JSONObject findEmp(HttpServletRequest request, String name,Integer pages){
		JSONObject json = new JSONObject();
		try {
			List<CMesEmpT> findAllEmp  = null;
			if(pages==null|| pages==0){
				findAllEmp = service.findAllEmp(name);
			}else{
				PageHelper.startPage(pages,10);
				findAllEmp = service.findAllEmp(name);
				PageInfo<CMesEmpT> pageinfo = new PageInfo<CMesEmpT>(findAllEmp,5);
				json.put("pageNum", pageinfo.getTotal());
				findAllEmp = pageinfo.getList();
				for (CMesEmpT cMesEmpT : findAllEmp) {
					List<CMesEmpT> emps = service.findStationNameById(cMesEmpT);
					StringBuffer stationNames = new StringBuffer();
					for (CMesEmpT emp : emps) {
						stationNames.append(emp.getStationName()).append(",");
					}
					if (stationNames.length() > 0){
						cMesEmpT.setStationName(stationNames.substring(0, stationNames.length() - 1));
					}
				}
			}
			json.put("code",0);
			json.put("info", findAllEmp);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}

		@RequestMapping(value="addEmp",method=RequestMethod.POST)
		@ApiOperation(value = "添加员工", notes = "添加员工")
	    @ApiImplicitParams({
	      @ApiImplicitParam(name = "empNo",value = "员工编号", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empName",value = "员工名称", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empSex",value = "性别", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empType",value = "员工类型ID", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empTp",value = "员工电话", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empDepartment",value = "员工部门", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empTeamId",value = "班组ID", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "lineId",value = "产线Id", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "stationId",value = "工位Id", required = true, paramType = "query"),

	    })
		@OptionalLog(module="生产", module2="员工管理", method="新增员工")
		public JSONObject addEmp(HttpServletRequest request, CMesEmpT emp){
			JSONObject json = new JSONObject();
			try {
				service.addEmp(emp);
				json.put("code",0);
				json.put("msg", "添加成功");
			}catch (ServicesException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
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


		@RequestMapping(value="updateEmp",method=RequestMethod.POST)
		@ApiOperation(value = "修改员工", notes = "修改员工")
	    @ApiImplicitParams({
	      @ApiImplicitParam(name = "empNo",value = "员工编号", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empName",value = "员工名称", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empSex",value = "性别", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empType",value = "员工类型ID", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empTp",value = "员工电话", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empDepartment",value = "员工部门", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "empTeamId",value = "班组ID", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "lineId",value = "产线Id", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "stationId",value = "工位Id", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "id",value = "ID", required = true, paramType = "query"),

	    })
		@OptionalLog(module="生产", module2="员工管理", method="编辑员工")
		public JSONObject updateEmp(HttpServletRequest request, CMesEmpT emp){
			JSONObject json = new JSONObject();
			try {
				service.updateEmp(emp);
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

		//修改故障烈性
		@RequestMapping(value="deleteEmp",method=RequestMethod.POST)
		@ApiOperation(value = "删除员工", notes = "删除员工")
	    @ApiImplicitParams({
	      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
	    })
		@OptionalLog(module="生产", module2="员工管理", method="删除员工")
		public JSONObject deleteEmp(HttpServletRequest request, Integer id){
			JSONObject json = new JSONObject();
			try {
				service.delEmp(id);
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



		@RequestMapping(value="findStation",method=RequestMethod.POST)
		@ApiOperation(value = "工位列表", notes = "工位列表")
		public JSONObject findStation(HttpServletRequest request){
			JSONObject json = new JSONObject();
			try {
				List<CMesStationT> findStation = service.findStation();
				json.put("code",0);
				json.put("info", findStation);
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				// TODO: handle exception
				json.put("code", 1);
			}
			return json;
		}

		@RequestMapping(value="findEmpType",method=RequestMethod.POST)
		@ApiOperation(value = "员工类型列表", notes = "员工类型列表")
		public JSONObject findEmpType(HttpServletRequest request){
			JSONObject json = new JSONObject();
			try {
				List<CMesEmpTypeT> findEmpType = service.findsEmpType();
				json.put("code",0);
				json.put("info", findEmpType);
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				// TODO: handle exception
				json.put("code", 1);
			}
			return json;
		}

		@RequestMapping(value = "importEmp", method = RequestMethod.POST, produces = "application/json")
		@ApiOperation(value = "导入员工 ", notes = "导入员工 ")
		@Transactional(rollbackFor = { Exception.class })
		@OptionalLog(module="生产", module2="员工管理", method="导入员工")
		public JSONObject importAndonPlan(HttpServletRequest request, @RequestBody JSONArray info) throws Exception {
			JSONObject json = new JSONObject();
			Integer b=0;
			int sum = info.size();
			json.put("code", 0);
			if (info.size() < 0) {
				json.put("code", 1);
				json.put("msg", "导入数据为空");
				return json;
			}
			Integer findLineByName = 0;
			Integer stationId = 0;
			Integer findEmpTypeName = 0;
			Integer findTeamName = 0;
			try {
				for (int i = 0; i < info.size(); i++) {
					JSONObject jsonObject = info.getJSONObject(i);

					Object empNo = jsonObject.get("empNo");
					if (empNo == null || empNo == "") {
						json.put("code", 1);
						json.put("msg", "员工编号不能为空");
						return json;
					}
					String empNos = empNo.toString();
					Integer c=0;
                    if(!empNos.equals("无")){
                    	 c=service.findEmpByName(empNos);
                    }
                    if(c>0){
                    	b++;
                    }else {
    					Object empName   = jsonObject.get("empName");
    					if (empName    == null || empName    == "") {
    						json.put("code", 1);
    						json.put("msg", "员工名称不能为空");
    						return json;
    					}
    					String empNames    = empName.toString();
    					int a=0;
    					Object sex= jsonObject.get("empSex");
    					if (sex== null || sex == "") {
    						json.put("code", 1);
    						json.put("msg", "性别不能为空");
    						return json;
    					}
    					String sexs = sex.toString();
    					if (sexs.equals("男")) {
    						a=0;
    					}else if (sexs.equals("女")){
    						a=1;
    					}
    					Object telephone= jsonObject.get("empTp");
    					if (telephone== null || telephone == "") {
    						json.put("code", 1);
    						json.put("msg", "电话不能为空");
    						return json;
    					}
    					String telephones = telephone.toString();
    					Object dept= jsonObject.get("empDepartment");
    					if (dept== null || dept == "") {
    						json.put("code", 1);
    						json.put("msg", "部门能为空");
    						return json;
    					}
    					String depts = dept.toString();
    					Object empType= jsonObject.get("empType");
    					if (empType== null || empType == "") {
    						json.put("code", 1);
    						json.put("msg", "员工类型不能为空");
    						return json;
    					}else {
    						 findEmpTypeName = service.findEmpTypeName(empType.toString());
    						 if (findLineByName == null) {
    								json.put("code", 1);
    								json.put("msg", "[" + empType + "]员工类型不存在");
    								return json;
    							}
    					}
    					Object teamName= jsonObject.get("empTeamName");
    					if (teamName== null || teamName == "") {
    						json.put("code", 1);
    						json.put("msg", "班组不能为空");
    						return json;
    					}else {
    						  findTeamName = service.findTeamName(teamName.toString());
    						 if (findTeamName == null) {
    								json.put("code", 1);
    								json.put("msg", "[" + teamName + "]班组不存在");
    								return json;
    							}
    					}
    					Object lineNames = jsonObject.get("lineName");
    					if (lineNames == null || lineNames == "") {
    						json.put("code", 1);
    						json.put("msg", "产线名称不能为空");
    						return json;
    					} else {
    						findLineByName = Planservice.findLineByName(lineNames.toString());
    						if (findLineByName == null) {
    							json.put("code", 1);
    							json.put("msg", "[" + lineNames + "]产线不存在");
    							return json;
    						}
    					}
    					String str = "";
    					Object stationName = jsonObject.get("stationName");
    					if (stationName == null || stationName == "") {
    						json.put("code", 1);
    						json.put("msg", "工位名称不能为空");
    						return json;
    					} else {
    						String[] split = stationName.toString().split(",");
    						for (String strings : split) {
    							stationId = service.findStationName(strings);
    							str = str + stationId+",";
							}
    						if (stationId == null) {
    							json.put("code", 1);
    							json.put("msg", "[" + stationName + "]工位不存在");
    							return json;
    						}
    					}
						Object isWhole = jsonObject.get("isWhole");
    					if(isWhole==null || isWhole==""){
							json.put("code", 1);
							json.put("msg", "是否全班不能为空");
							return json;
						}
						Object password = jsonObject.get("password");
						if(password==null || password==""){
							json.put("code", 1);
							json.put("msg", "密码不能为空");
							return json;
						}

						CMesEmpT emp = new CMesEmpT();
    					emp.setEmpNo(empNos);
    					emp.setEmpName(empNames);
    					emp.setEmpSex(a);
    					emp.setEmpTp(telephones);
    					emp.setEmpType(findEmpTypeName);
    					emp.setEmpTeamId(findTeamName);
    					emp.setLineId(findLineByName);
    					emp.setEmpDepartment(depts);
    					emp.setStationId(str.substring(0,str.length()-1));
    					emp.setIsWhole(isWhole.toString());
    					emp.setpassword(password.toString());
    					service.addEmp(emp);
					}
				}
				json.put("code", 0);
				json.put("sum", sum);
				json.put("error", b);
				json.put("msg", "导入成功");
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				json.put("code", 1);
				json.put("msg", "未知错误");
			}
			return json;
		}

}

package com.skeqi.mes.controller.qh;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.qh.CMesDeciveService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 设备管理
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "设备管理", description = "设备管理", produces = MediaType.APPLICATION_JSON)
public class CMesDeciveController {

	@Autowired
	private CMesDeciveService deciveService;


	/**
	 * 查询 分页
	 *
	 * @author WangJ
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/decive/findAll", method = RequestMethod.POST)
	@ApiOperation(value = "设备列表", notes = "设备列表")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "overType", value = "超出类型(值：0 - 超出寿命，1 - 超出维护时间)", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "name", value = "设备名称", required = false, paramType = "query", dataType = "string")
			})
	public JSONObject findAll(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize,Integer overType,String name) throws ServicesException, UnsupportedEncodingException {
		PageHelper.startPage(pageNum, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		Integer pageNumber = null;// 当前页
		if (overType!=null&&!"".equals(overType)) {
			if (overType == 0) {
				map.put("suprplusLife", overType);
			}else if (overType == 1) {
				map.put("surplusMaintain", overType);
			}
		}
		if (name!=null&&!"".equals(name)) {
			map.put("name", new String(name.getBytes("ISO8859-1"), "UTF-8"));
		}

		map.put("pageNumber", pageNumber);


		List<CMesDeviceT> list = deciveService.findAll(map);
		PageInfo<CMesDeviceT> pageInfo = new PageInfo<>(list);

		JSONObject json = new JSONObject();
		try {

			json.put("code", 0);
			json.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "/decive/add", method = RequestMethod.POST)
	@ApiOperation("新增设备")
	@ResponseBody
	public JSONObject add(HttpServletRequest request, @ModelAttribute @Valid CMesDeviceT c) throws ServicesException {
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = deciveService.addTool(c);
			System.out.println("code"+map.get("code"));
			System.out.println("msg"+map.get("msg"));
			json.put("code", map.get("code"));
			json.put("msg", map.get("msg"));

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 2);
			json.put("msg", "未知错误");
		}finally {
			return json;
		}

	}


	@RequestMapping(value = "/decive/alterSurplusMaintain", method = RequestMethod.POST)
	@ApiOperation(value = "修改当前剩余天数", notes = "修改当前剩余天数")
	@ResponseBody
	public JSONObject alterSurplusMaintain(HttpServletRequest request, @ModelAttribute @Valid CMesDeviceT c) {
		JSONObject json = new JSONObject();

		try {
			Integer index = deciveService.alterSurplusMaintain(c);
			if (index==1) {
				json.put("code", 0);
				json.put("msg", "成功");
			}else{
				json.put("code", 1);
				json.put("msg", "失败");
			}

		}catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 2);
			json.put("msg", "未知错误");
		}finally {
			return json;
		}
	}



	/**
	 * 查询设备名是否存在并判断当前修改名与原名不同
	 * @Title: findToolName_updata
	 * @author WangJ
	 * @Description: 作用于 修改
	 * @param toolName
	 * @param id
	 * @throws UnsupportedEncodingException 参数
	 * @return JSONObject返回类型
	 * @throws
	 */
//	@RequestMapping(value = "/decive/updata/findToolName", method = RequestMethod.POST)
//	@ApiOperation(value = "查询设备名是否存在并判断当前修改名与原名是否相同", notes = "修改")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "id", value = "设备id", required = false, paramType = "query", dataType = "int"),
//		@ApiImplicitParam(paramType = "query", name = "toolName", value = "设备名", required = false, dataType = "string")
//		})
//	@ResponseBody
//	public JSONObject findToolName_updata(String toolName,Integer id) throws UnsupportedEncodingException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		JSONObject json = new JSONObject();
//
//		try {
//			map.put("name_t", toolName);
//			List<CMesDeviceT> list2 = deciveService.findAll(map);
//			if (list2.size()!=0) {
//				json.put("code", 1);
//				json.put("msg", "设备名不可用");
//			}else{
//				json.put("code", 0);
//			}
//			map.clear();
//			map.put("id", id);
//			List<CMesDeviceT> list = deciveService.findAll(map);
//			for (CMesDeviceT c : list) {
//				if (c.getToolName().equals(toolName)) {
//					json.put("code", 0);
//				}
//			}
//			json.put("list", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			json.put("code", 1);
//			json.put("msg", "未知错误");
//		}
//		return json;
//	}

	@RequestMapping(value = "/decive/alter", method = RequestMethod.POST)
	@ApiOperation("修改设备")
	@ResponseBody
	public JSONObject alter(HttpServletRequest request, @ModelAttribute @Valid CMesDeviceT c) throws ServicesException {

		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = deciveService.alterTool(c);
			json.put("code", map.get("code"));
			json.put("msg", map.get("msg"));

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 2);
			json.put("msg", "未知错误");
		}finally {
			return json;
		}


	}



	@ResponseBody
	@RequestMapping(value = "/decive/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询设备信息", notes = "根据id查询设备信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "设备id", required = true, dataType = "Integer")

	public JSONObject findById(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			List<CMesDeviceT> list = deciveService.findAll(map);
			CMesDeviceT t = list.get(0);
			String fiist = t.getFirstUse().substring(0,10);
			t.setFirstUse(fiist);
			if (t.getLastMaintain()!=null) {
				String lastMaintain = t.getLastMaintain().substring(0,10);
				t.setLastMaintain(lastMaintain);
			}
			json.put("list", list);
			json.put("code", 0);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	@RequestMapping(value = "/decive/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除设备信息", notes = "根据id删除设备信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "设备id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject delete(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();

		try {
			int index = deciveService.delTool(id);
			if (index!=1) {
				json.put("code", 1);
				json.put("msg", "删除失败");
			}else{
				json.put("code", 0);
				json.put("msg", "删除成功");
			}
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}catch (Exception e) {
			json.put("code", 2);
			json.put("msg","数据异常");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}finally {
			return json;
		}

	}
}

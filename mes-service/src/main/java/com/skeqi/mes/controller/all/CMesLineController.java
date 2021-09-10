package com.skeqi.mes.controller.all;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.gmg.UserService;
import com.skeqi.mes.service.yin.UsersService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import scala.sys.process.ProcessBuilderImpl.Simple;


@Controller
@RequestMapping(value="skq",produces = MediaType.APPLICATION_JSON)
@Api(value = "产线管理", description = "产线信息", produces = MediaType.APPLICATION_JSON)
/***
 *
 * @author ENS  产线管理 1
 *
 */
public class CMesLineController {

	@Autowired
	CMesLineTService cMesLineTService;

	@Autowired
	UsersService usersService;


	@RequestMapping("/toeditstatus")
	@ResponseBody
	public Map<String, Object> toeditstatus(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String status = request.getParameter("status");
		try {
			cMesLineTService.updateStatus(Integer.parseInt(id), Integer.parseInt(status));
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}


	//根据id删除产线
	@RequestMapping("delLine")
	@ResponseBody
	public  JSONObject deleteLineById(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			cMesLineTService.delLine(Integer.parseInt(id));
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;

	}


	//根据id查询产线
	@RequestMapping("toEditLine")
	@ResponseBody
	public  JSONObject findLineById(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			CMesLineT line = cMesLineTService.findLineByid(Integer.parseInt(id));
			json.put("line", line);
			json.put("code", 0);
		}  catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}


	//修改产线信息
	@RequestMapping("editLine")
	@ResponseBody
	public  JSONObject editLineById(HttpServletRequest request) {

		JSONObject json = new JSONObject();
		String id = request.getParameter("id4");
		String lineName = request.getParameter("lineName4").trim();
		String lineDsc = request.getParameter("lineDsc4");
		String codeType = request.getParameter("codeType");
		System.out.println("id:"+id+"lineName:"+lineName+"lineDsc4:"+lineDsc+"codeType��"+codeType);
		CMesLineT line = new CMesLineT();
		line.setId(Integer.parseInt(id));
		line.setName(lineName);
		line.setDsc(lineDsc);
		line.setCodeType(Integer.parseInt(codeType));
		try {
			cMesLineTService.updateLine(line);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}

//	//新增产线
//	@ResponseBody
//	@RequestMapping(value="addLine",method=RequestMethod.POST)
//	@ApiOperation(value = "添加产线", notes = "添加产线")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "lineName",value = "产线id", required = true, paramType = "query"),
//        @ApiImplicitParam(name = "lineDsc",value = "产线描述", required = false, paramType = "query"),
//        @ApiImplicitParam(name = "codeType",value = "条码生成方式", required = true, paramType = "query"),
//    })
//	public  JSONObject addLine(String lineName,String lineDsc,String codeType) {
//
//		JSONObject json = new JSONObject();
//		CMesLineT line = new CMesLineT();
//		line.setName(lineName);
//		line.setDsc(lineDsc);
//		line.setCodeType(Integer.parseInt(codeType));
//
//		try {
//			Integer count = cMesLineTService.addLine(line);
//			json.put("code", 0);
//			json.put("msg", count);
//		} catch (ServicesException e) {
//			json.put("code", e.getCode());
//			json.put("msg", e.getMessage());
//			System.out.println(e.getCode());
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}catch (Exception e) {
//			json.put("code", -1);
//			json.put("msg", e.getMessage());
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//
//		return json;
//	}
//
//	@ResponseBody
//	@RequestMapping(value="lineManager",method=RequestMethod.POST)
//	@ApiOperation(value = "产线列表", notes = "产线列表")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "lineId",value = "产线id", required = false, paramType = "query"),
//    })
//	public List<CMesLineT> lineManager(Integer lineId) throws ServicesException{
//		CMesLineT line = new CMesLineT();
//		if(lineId!=null){
//			line.setId(lineId);
//		}
//		List<CMesLineT> lineList = cMesLineTService.findAllLine(line);
//		return lineList;
//	}

	//新增产线
	@RequestMapping("addLine")
	@ResponseBody
	public  JSONObject addLine(HttpServletRequest request) {

		JSONObject json = new JSONObject();
		CMesLineT line = new CMesLineT();
		String lineName = request.getParameter("lineName").trim();
		String lineDsc = request.getParameter("lineDsc");
		String codeType = request.getParameter("codeType");
		line.setName(lineName);
		line.setDsc(lineDsc);
		line.setCodeType(Integer.parseInt(codeType));

		try {
			Integer count = cMesLineTService.addLine(line);
			json.put("code", 0);
			json.put("msg", count);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			System.out.println(e.getCode());
			System.out.println(e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			System.out.println(e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return json;
	}


	@RequestMapping("lineManager")
	public String lineManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) throws ServicesException{
		CMesLineT line = new CMesLineT();
		String lineId = request.getParameter("lineId");
		if(lineId!=null){
			line.setId(Integer.parseInt(lineId));
		}
		PageHelper.startPage(page,8);
		List<CMesLineT> lineList = cMesLineTService.findAllLine(line);
		PageInfo<CMesLineT> pageInfo2 = new PageInfo<>(lineList,5);
//		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("pageInfo2", pageInfo2);
		request.setAttribute("lineId", lineId);
		return "base_control/lineManager";
	}


}

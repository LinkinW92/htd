package com.skeqi.mes.controller.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.neo4j.cypher.internal.compiler.v2_2.ast.False;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.project.InsertInfo;
import com.skeqi.mes.service.project.CMesAndonInfoService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="api",produces = MediaType.APPLICATION_JSON)
@Api(value = "生产计数管理", description = "生产计数管理", produces = MediaType.APPLICATION_JSON)
public class CMesAndonInfoController {


	@Autowired
	CMesAndonInfoService service;

	@RequestMapping(value="findAllAndonInfo",method=RequestMethod.POST)
	@ApiOperation(value = "生产计数列表", notes = "生产计数列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "pages",value = "当前页", required = false, paramType = "query"),
      @ApiImplicitParam(name = "workId",value = "工单id", required = false, paramType = "query"),
      @ApiImplicitParam(name = "lineId",value = "产线ID", required = false, paramType = "query"),
      @ApiImplicitParam(name = "stationName",value = "工位名称", required = false, paramType = "query"),
      @ApiImplicitParam(name = "sn",value = "sn", required = false, paramType = "query"),
      @ApiImplicitParam(name = "startDate",value = "开始时间", required = false, paramType = "query"),
      @ApiImplicitParam(name = "endDate",value = "结束时间", required = false, paramType = "query"),
    })
	public JSONObject findAllAndonInfo(HttpServletRequest request, Integer pages,Integer workId,String lineId,String stationName,String sn,String startDate,String endDate){
		JSONObject json = new JSONObject();
		System.err.println("workId"+workId);
	String lineName=	service.findByLineName(lineId);
		try {
			List<InsertInfo> findAllInfo =null;
			if(pages==null || pages==0){
				findAllInfo = service.findAllInfo(workId,lineName, stationName, sn,startDate,endDate);
				for (InsertInfo insertInfo : findAllInfo) {
					if(insertInfo!=null) {
//						int num = new CMesAndonInfoController().countStr(insertInfo.getSn(),"|");
//						if(num==2) {
//							try {
//								String string = insertInfo.getSn().split("\\|")[2];
//								insertInfo.setNumber(string);
//							} catch (Exception e) {
//							}
//						}else {
//							insertInfo.setNumber("");
//						}
						if(new CMesAndonInfoController().countStr(insertInfo.getSn(),"\\|")) {
							try {
								String string = insertInfo.getSn().split("\\|")[2];
								insertInfo.setNumber(string);
							} catch (Exception e) {
							}
						}else {
							insertInfo.setNumber("");
						}
					}
				}
			}else{
				PageHelper.startPage(pages,10);
				findAllInfo = service.findAllInfo(workId,lineName, stationName, sn,startDate,endDate);
				for (InsertInfo insertInfo : findAllInfo) {
					if(insertInfo!=null) {
//						int num = new CMesAndonInfoController().countStr(insertInfo.getSn(),"|");
//						if(num==2) {
//							try {
//								String string = insertInfo.getSn().split("\\|")[2];
//								insertInfo.setNumber(string);
//							} catch (Exception e) {
//							}
//						}else {
//							insertInfo.setNumber("");
//						}
						if(new CMesAndonInfoController().countStr(insertInfo.getSn(),"\\|")) {
							try {
								String string = insertInfo.getSn().split("\\|")[2];
								insertInfo.setNumber(string);
							} catch (Exception e) {
							}
						}else {
							insertInfo.setNumber("");
						}
					}
				}
				PageInfo<InsertInfo> pageinfo = new PageInfo<InsertInfo>(findAllInfo,5);
				json.put("pageNum",pageinfo.getTotal());
				findAllInfo=pageinfo.getList();
			}
			json.put("code",0);
			json.put("info", findAllInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}

	@RequestMapping(value="addAndonInfo",method=RequestMethod.POST)
	@ApiOperation(value = "手动添加计数",notes = "手动添加计数")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "stationName" ,value="工位名称",required = true, paramType = "query"),
		@ApiImplicitParam(name = "dt" ,value="时间",required = true, paramType = "query"),
		@ApiImplicitParam(name = "sn" ,value="sn",required = false, paramType = "query"),
		@ApiImplicitParam(name = "countType" ,value="计数方式",required = true, paramType = "query"),
		@ApiImplicitParam(name = "workId" ,value="工单id",required = true, paramType = "query"),
		@ApiImplicitParam(name = "number" ,value="数量",required = true, paramType = "query"),
	})
	public JSONObject addAndonInfo(HttpServletRequest request, String stationName,String dt,String sn,Integer countType,Integer workId,Integer number){
		JSONObject json = new JSONObject();
		try {
			service.InsertAndonInfo(stationName, dt, sn, countType, workId, number);
			 json.put("code",0);
			 json.put("msg", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			 json.put("code",1);
			 json.put("msg",e.getMessage());
		}
		return json ;
	}



	@RequestMapping(value="delAndonInfo",method=RequestMethod.POST)
	@ApiOperation(value = "删除生产计数",notes = "删除生产计数")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id" ,value="生产计数id",required = true, paramType = "query")
	})
	public JSONObject delAndonInfo(HttpServletRequest request, Integer id){
		JSONObject json = new JSONObject();
		try {
			service.delAndonInfo(id);
			 json.put("code",0);
			 json.put("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			 json.put("code",1);
			 json.put("msg",e.getMessage());
		}
		return json ;
	}



//    private static final int countStr(String str,String sToFind) {
//        int num = 0;
//        while (str.contains(sToFind)) {
//            str = str.substring(str.indexOf(sToFind) + sToFind.length());
//            num ++;
//        }
//        return num;
//    }

    private final boolean countStr(String str,String sToFind) {
    	if(str.split(sToFind).length==3) {
    		return true;
    	}else {
    		return false;
    	}
    }


    public static void main(String[] args) {
    	System.out.println("14368391|123|0001".split("\\|").length);
	}

}

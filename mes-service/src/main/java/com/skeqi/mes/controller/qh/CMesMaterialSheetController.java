package com.skeqi.mes.controller.qh;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.pojo.*;
import com.skeqi.mes.service.all.*;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.CWmsDepartmentT;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import scala.Int;
import scala.reflect.internal.Trees.This;

@RestController
@RequestMapping(value = "api/materialList", produces = MediaType.APPLICATION_JSON)
@Api(value = "BOM料单管理", description = "BOM料单管理", produces = MediaType.APPLICATION_JSON)
public class CMesMaterialSheetController {

    @Autowired
    CMesBomService bomService;

    @Autowired
    CMesMaterialService materialService;
    @Autowired
    CMesStationTService stationService;
    @Autowired
    CMesLineTService lineService;
    @Autowired
    CMesProductionTService productionService;


    //按物料编码或bomId查询BOM详情
    @RequestMapping(value = "/findBOMDetailByMaterialCode", method = RequestMethod.POST)
    @ApiOperation(value = "按物料编码或bomId查询BOM详情", notes = "按物料编码或bomId查询BOM详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "BOMid", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "materialCode", value = "物料编号", required = false, dataType = "String")})
    @ResponseBody
    public JSONObject findBOMDetailByMaterialCode(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        List<String> materialListId = new ArrayList<>();
        try {

            String materialCode = EqualsUtil.string(request, "materialCode", "物料编码", false);
            //物料编号不等于空先查明细表得到bom编号集合
            if (!StringUtils.isEmpty(materialCode)) {
                List<CMesMaterialListDetailT> listDetailTS = new ArrayList<>();
                listDetailTS = bomService.findMaterialListDetailByCode(materialCode);

                //得到BOM编号
                List<String> material = new ArrayList<>();
                listDetailTS.forEach(cMesMaterialListDetailT -> {
                    material.add(cMesMaterialListDetailT.getMaterilaListId());
                });

                //去重
                materialListId = material.stream().distinct().collect(Collectors.toList());
            }
            //循环bom明细编号查询bom信息
            List<CMesMaterialListT> materialList = new ArrayList<>();

            String id = EqualsUtil.string(request, "id", "BOMid", false);
            if (!StringUtils.isEmpty(id)) {
                CMesMaterialListT cMesMaterialListT = new CMesMaterialListT();
                cMesMaterialListT.setId(Integer.valueOf(id));
                List<CMesMaterialListT> materialByIdAndListNo = bomService.findMaterialByIdAndListNo(cMesMaterialListT);
                if (materialByIdAndListNo.size() > 0) {
                    materialList.add(materialByIdAndListNo.get(0));
                }
            } else {
                for (String item : materialListId) {
                    if (!StringUtils.isEmpty(item)) {
                        CMesMaterialListT cMesMaterialListT = new CMesMaterialListT();
                        cMesMaterialListT.setListNo(item);
                        List<CMesMaterialListT> materialByIdAndListNo = bomService.findMaterialByIdAndListNo(cMesMaterialListT);
                        if (materialByIdAndListNo.size() > 0) {
                            materialList.add(materialByIdAndListNo.get(0));
                        }
                    }
                }
            }

            //循环bom列表得到bom明细
            CMesMaterialListDetailT cMesMaterialListDetailT = new CMesMaterialListDetailT();
            for (CMesMaterialListT item : materialList) {
                cMesMaterialListDetailT.setMaterilaListId(item.getListNo());
                cMesMaterialListDetailT.setMaterialNo(materialCode);
                item.setObjects(bomService.findMaterialListDetail(cMesMaterialListDetailT));
            }
            json.put("materialList", materialList);
            json.put("code", 0);
        } catch (ServicesException e) {
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        } catch (Exception e) {
            e.getMessage();
            json.put("code", -1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    //根据id或者根据bom编号查询BOM
    @RequestMapping(value = "/findMaterialByIdAndListNo", method = RequestMethod.POST)
    @ApiOperation(value = "根据id或者根据bom编号查询BOM", notes = "根据id或者根据bom编号查询BOM")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bomId", value = "BOM料单编号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", required = false, dataType = "String")})
    @ResponseBody
    public JSONObject findMaterialByIdAndListNo(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            Integer id = EqualsUtil.integer(request, "id", "id", false);
            String listNo = EqualsUtil.string(request, "listNo", "listNo", false);
            CMesMaterialListT material = new CMesMaterialListT();
            material.setId(id);
            material.setListNo(listNo);
            List<CMesMaterialListT> materialList = bomService.findMaterialByIdAndListNo(material);
            json.put("materialList", materialList);
            json.put("code", 0);
        } catch (ServicesException e) {
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        } catch (Exception e) {
            e.getMessage();
            json.put("code", -1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    // 查询料单列表
    @RequestMapping(value = "/findMaterialList", method = RequestMethod.POST)
    @ApiOperation(value = "查询BOM料单列表", notes = "查询BOM料单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bomId", value = "BOM料单编号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "productionType", value = "产品型号", required = false, dataType = "String")})
    @ResponseBody
    public JSONObject findMaterialList(HttpServletRequest request,
                                       @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                       @RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize) {
        JSONObject json = new JSONObject();
        try {
            String listNo = request.getParameter("bomId");
            String productionType = request.getParameter("productionType");
//			System.out.println("*************************************lid:"+lid);
//			System.out.println("*************************************lno:"+lno);
//
            CMesMaterialListT material = new CMesMaterialListT();
            if (listNo != null && listNo != "" && !listNo.equals("")) {
                material.setListNo(listNo);
            }
            if (productionType != null && productionType != "" && !productionType.equals("")) {
                material.setProductType(productionType);
            }


            PageHelper.startPage(pageNum, pageSize);
            List<CMesMaterialListT> materialLists = bomService.findAllMaterialList(material);
            PageInfo<CMesMaterialListT> pageInfo = new PageInfo<>(materialLists, 5);// 料单列表
            json.put("pageInfo", pageInfo);
            json.put("code", 0);
        } catch (ServicesException e) {
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        } catch (Exception e) {
            e.getMessage();
            json.put("code", -1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    /**
     * 查询料单明细
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "查询料单明细列表", notes = "查询料单明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "listNo", value = "BOM编号", required = false, paramType = "query"),
    })
    @RequestMapping(value = "findMaterialDetailList", method = RequestMethod.POST)
    public JSONObject findMaterialDetailList(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                             @RequestParam(required = false, defaultValue = "1", value = "pageSize") Integer pageSize) throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        try {

            String listNo = request.getParameter("listNo");
//					Integer stationId = Integer.parseInt(request.getParameter("stationId"));
            //String stationId = request.getParameter("stationId");
            //System.out.println(":::::::::::::::::::" + stationId);
            CMesMaterialListDetailT materialListDetail = new CMesMaterialListDetailT();
            if (listNo != null && listNo != "" && !listNo.equals("")) {
                materialListDetail.setMaterilaListId(listNo);
            }
/*					if(stationId!=null && stationId!="" && !stationId.equals("")) {
						// materialListDetail.setStationName(staname);
						materialListDetail.setStationId(Integer.parseInt(stationId));
					}*/
            PageHelper.startPage(pageNum, pageSize);
            List<CMesMaterialListDetailT> materialListDetails = bomService.findMaterialListDetail(materialListDetail);
            PageInfo<CMesMaterialListDetailT> pageInfo = new PageInfo<>(materialListDetails, 5);// 料单清单列表
            request.setAttribute("pageInfo", pageInfo);
            json.put("code", 0);
            json.put("pageInfo", pageInfo);
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    /**
     * 查询所有的料单
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
//		@ApiOperation(value = "查询所有的料单", notes = "查询所有的料单")
//		@RequestMapping(value = "findAllMaterials", method = RequestMethod.POST)
//		public JSONObject findAllMaterial1s(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServicesException {
//			JSONObject json = new JSONObject();
//				try {
//					CMesMaterialListT material = new CMesMaterialListT();
//					List<CMesMaterialListT> materialLists = bomService.findAllMaterialList(material);
//					System.out.println(materialLists);
//					json.put("code", 0);
//					json.put("materialLists", materialLists);
//				}catch(ServicesException e) {
//					e.getMessage();
//					json.put("code", 1);
//					json.put("msg", e.getMessage());
//				}
//			return json;
//		}


    /**
     * 查询所有的Bom料单
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "查询所有的Bom料单", notes = "查询所有的Bom料单")
    @RequestMapping(value = "findBomListNoAll", method = RequestMethod.POST)
    public JSONObject findBomListNoAll(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        try {
            CMesMaterialListT material = new CMesMaterialListT();
            List<CMesMaterialListT> materialLists = bomService.findAllMaterialList(material);
            System.out.println(materialLists);
            json.put("code", 0);
            json.put("materialLists", materialLists);
        } catch (ServicesException e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", 1);
            json.put("msg", e.getMessage());
        }
        return json;
    }


    /**
     * 查询所有产线
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "查询所有产线", notes = "查询所有产线")
    @RequestMapping(value = "findAllLine", method = RequestMethod.POST)
    public JSONObject findAllLine(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        try {
            CMesLineT line = new CMesLineT();
            List<CMesLineT> lineList = lineService.findAllLine(line);
            System.out.println(lineList);
            json.put("code", 0);
            json.put("lineList", lineList);
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    /**
     * 查询所有产品型号
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "查询所有产品型号", notes = "查询所有产品型号")
    @RequestMapping(value = "findProductTypeList", method = RequestMethod.POST)
    public JSONObject findProductTypeList(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        try {
            List<CMesProductionT> productionTList = productionService.findProductTypeList();
            System.out.println(productionTList);
            json.put("code", 0);
            json.put("productionTList", productionTList);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", 1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    /**
     * 查询所有工位
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "查询所有工位", notes = "查询所有工位")
    @RequestMapping(value = "findAllStation", method = RequestMethod.POST)
    public JSONObject findAllStation(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        try {

            CMesStationT st = new CMesStationT();
            List<CMesStationT> stationList = stationService.findAllStation(st);
            json.put("code", 0);
            json.put("stationList", stationList);
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    /**
     * 根据料单id查询所属产线下的工位
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "根据料单id查询所属产线下的工位", notes = "根据料单id查询所属产线下的工位")
    @ApiImplicitParams({@ApiImplicitParam(name = "listId", value = "料单id", required = true, paramType = "query")})
    @RequestMapping(value = "findStationByListId", method = RequestMethod.POST)
    public JSONObject findStationByListId(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        String listIdStr = request.getParameter("listId");
        Integer listId = 0;
        if (listIdStr != null) {
            listId = Integer.parseInt(request.getParameter("listId"));
        }

        JSONObject json = new JSONObject();
        try {
            List<CMesStationT> stationList = null;
            if (listId != 0) {
                stationList = stationService.findStationByListId(listId);
            }
            json.put("code", 0);
            json.put("stationList", stationList);
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    /**
     * 查询所有物料
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "查询所有物料", notes = "查询所有物料")
    @RequestMapping(value = "findAllMaterial", method = RequestMethod.POST)
    public JSONObject findAllMaterial(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        try {
            CMesJlMaterialT jl = new CMesJlMaterialT();
            List<CMesJlMaterialT> findJT = materialService.findAllMaterial(jl);
            json.put("code", 0);
            json.put("findJt", findJT);
        } catch (ServicesException e) {
            json.put("code", 1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    /**
     * 新增料单
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "新增料单", notes = "新增料单")
    @ApiImplicitParams({@ApiImplicitParam(name = "listNo", value = "BOM编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "listName", value = "BOM名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "productType", value = "产品型号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "effectiveTime", value = "有效时间", required = false, paramType = "query"),
            @ApiImplicitParam(name = "invalidTime", value = "失效时间", required = false, paramType = "query"),
            @ApiImplicitParam(name = "listVersion", value = "版本", required = false, paramType = "query")})
//		@Transactional
    @RequestMapping(value = "addMeterial", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "BOM管理", method = "新增料单")
    public JSONObject addMeterial(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject json = new JSONObject();
        try {
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String listNo = EqualsUtil.string(request, "listNo", "BOM编号", true).trim();
            String listName = EqualsUtil.string(request, "listName", "BOM名称", true).trim();
            String productType = EqualsUtil.string(request, "productType", "产品型号", false);
            String effectiveTime = EqualsUtil.string(request, "effectiveTime", "有效时间", true);
            String invalidTime = EqualsUtil.string(request, "invalidTime", "失效时间", true);
            String listVersion = EqualsUtil.string(request, "listVersion", "版本", false);

//			String listName = request.getParameter("listName").trim();
//				String productType = request.getParameter("productType");
//				String effectiveTime = request.getParameter("effectiveTime");
//				System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;" + effectiveTime);
////				effectiveTime = effectiveTime + ":00";
//				String invalidTime = request.getParameter("invalidTime");
////				invalidTime = invalidTime + ":00";
//				String listVersion = request.getParameter("listVersion");

            CMesMaterialListT material = new CMesMaterialListT();
            material.setListNo(listNo);
            material.setDt(new Date());
            material.setEffectiveTime(effectiveTime);
            material.setProductType(productType);
            material.setListName(listName);
            material.setListVersion(listVersion);
            material.setInvalidTime(invalidTime);
            bomService.addMaterialList(material);
            json.put("code", 0);
        } catch (ServicesException e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            e.getMessage();
            json.put("code", -1);
            json.put("msg", e.getMessage());
        }
        return json;

    }

    /**
     * 修改
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "修改料单", notes = "修改料单")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "listNo", value = "BOM编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "listName", value = "BOM名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "productType", value = "产品型号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "effectiveTime", value = "有效时间", required = false, paramType = "query"),
            @ApiImplicitParam(name = "invalidTime", value = "失效时间", required = false, paramType = "query"),
            @ApiImplicitParam(name = "listVersion", value = "版本", required = false, paramType = "query")})
//		@Transactional
    @RequestMapping(value = "updateMeterial", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "BOM管理", method = "修改料单")
    public JSONObject updateMeterial(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        try {
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Integer id = EqualsUtil.integer(request, "id", "id", true);
            String listNo = EqualsUtil.string(request, "listNo", "BOM编号", true).trim();
            String listName = EqualsUtil.string(request, "listName", "BOM名称", true).trim();
            String productType = EqualsUtil.string(request, "productType", "产品型号", false);
            String effectiveTime = EqualsUtil.string(request, "effectiveTime", "有效时间", true);
            String invalidTime = EqualsUtil.string(request, "invalidTime", "失效时间", true);
            String listVersion = EqualsUtil.string(request, "listVersion", "版本", false);
//				String listNo = request.getParameter("listNo").trim();
//				String listName = request.getParameter("listName").trim();
//				String lineId = request.getParameter("lineId");
//				String effectiveTime = request.getParameter("effectiveTime");
////				effectiveTime = effectiveTime + ":00";
//				String invalidTime = request.getParameter("invalidTime");
////				invalidTime = invalidTime + ":00";
//				String listVersion = request.getParameter("listVersion");

            CMesMaterialListT material = new CMesMaterialListT();
            material.setId(id);

            HttpSession session = request.getSession();
            String listCode = request.getParameter("listCode");
            // 获取料单外部ID
            if (StringUtil.eqNu(listCode)) {
                CMesMaterialListT cMesMaterialListT = new CMesMaterialListT();
                cMesMaterialListT.setListNo(listCode);
                List<CMesMaterialListT> materialByIdAndListNo = bomService.findMaterialByIdAndListNo(cMesMaterialListT);
                if(materialByIdAndListNo.size()>0){
                    material.setId(materialByIdAndListNo.get(0).getId());
                    session.setAttribute("listCode", listCode);
                }else {
                    json.put("code", 1);
                    json.put("msg", "料单不存在");
                    return json;
                }

            } else {
                CMesMaterialListT cMesMaterialListT = new CMesMaterialListT();
                cMesMaterialListT.setId(material.getId());
                List<CMesMaterialListT> materialByIdAndListNo = bomService.findMaterialByIdAndListNo(cMesMaterialListT);
                if(materialByIdAndListNo.size()>0){
                    session.setAttribute("listCode", materialByIdAndListNo.get(0).getListNo());
                }else {
                    json.put("code", 1);
                    json.put("msg", "料单不存在");
                    return json;
                }

            }
            material.setListNo(listNo);
            material.setDt(new Date());
            material.setEffectiveTime(effectiveTime);
            material.setProductType(productType);
            material.setListName(listName);
            material.setListVersion(listVersion);
            material.setInvalidTime(invalidTime);


            bomService.updateMaterialList(material);
            json.put("code", 0);
        } catch (ServicesException e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", -1);
            json.put("msg", e.getMessage());
        }
        return json;

    }

    /**
     * 删除
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "删除料单", notes = "删除料单")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "料单id", required = true, paramType = "query")})
    @Transactional
    @RequestMapping(value = "deleteMaterial", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "BOM管理", method = "删除料单")
    public JSONObject deleteMaterial(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        HttpSession session = request.getSession();

        try {
            String listCode = request.getParameter("listNo");
            // 获取料单外部ID
            if (StringUtil.eqNu(listCode)) {
                CMesMaterialListT cMesMaterialListT = new CMesMaterialListT();
                cMesMaterialListT.setListNo(listCode);
                List<CMesMaterialListT> materialByIdAndListNo = bomService.findMaterialByIdAndListNo(cMesMaterialListT);
                if(materialByIdAndListNo.size()>0){
                    id = String.valueOf(materialByIdAndListNo.get(0).getId());
                    session.setAttribute("listNo", listCode);
                }else {
                    json.put("code", 1);
                    json.put("msg", "料单不存在");
                    return json;
                }

            } else {
                CMesMaterialListT cMesMaterialListT = new CMesMaterialListT();
                cMesMaterialListT.setId(Integer.parseInt(id));
                List<CMesMaterialListT> materialByIdAndListNo = bomService.findMaterialByIdAndListNo(cMesMaterialListT);
                if(materialByIdAndListNo.size()>0){
                    session.setAttribute("listNo", materialByIdAndListNo.get(0).getListNo());
                }else {
                    json.put("code", 1);
                    json.put("msg", "料单不存在");
                    return json;
                }

            }
            bomService.delMaterialList(Integer.parseInt(id));
            json.put("code", 0);
        } catch (ServicesException e) {
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", -1);
            json.put("msg", e.getMessage());
        }
        return json;

    }

    /**
     * 新增料单明细
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "新增料单明细", notes = "新增料单明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "materilaListId", value = "所属Bom", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialCode", value = "物料编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialName", value = "物料名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "graphNumber", value = "图号", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialSheft", value = "料槽号", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialReplace", value = "替换组", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialTrace", value = "追溯方式0-批次追溯1-精确追溯2-不追诉", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialNumber", value = "部件数量", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "materialImpFlag", value = "主关件标记1-是0-否", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialCheck", value = "完整性检查1-检查2-不检查", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialGetNumber", value = "取料数量", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialStore", value = "存放料仓", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialGetCheckFlag", value = "取料检查标记1-检查2-不检查", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "stationId", value = "工位名称", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "materialPullFalg", value = "拉动标记0-自动拉动标记1-手动拉动标记", required = false, paramType = "query")
    })
    @RequestMapping(value = "addMaterialDetail", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "BOM管理", method = "新增料单明细")
    public JSONObject addMaterialDetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        String materilaListId = null == request.getParameter("materilaListId") || "".equals(request.getParameter("materilaListId")) ? null : request.getParameter("materilaListId");

        String materialNo = request.getParameter("materialCode");
        String materialName = request.getParameter("materialName");
        String figureNo = request.getParameter("figureNo").trim();

//			String materialSheft = request.getParameter("materialSheft").trim();
//			String materialReplace = request.getParameter("materialReplace");
        String materialTrace = request.getParameter("materialTrace");
        String materialNumber = request.getParameter("materialNumber");

//			String materialImpFlag = request.getParameter("materialImpFlag");
//			String materialCheck = request.getParameter("materialCheck");
//			String materialGetNumber = request.getParameter("materialGetNumber");
//			String materialStore = request.getParameter("materialStore");
//			String materialGetCheckFlag = request.getParameter("materialGetCheckFlag");
//			String stationId = request.getParameter("stationId");
//			String materialPullFalg = request.getParameter("materialPullFalg");

        CMesMaterialListDetailT detail = new CMesMaterialListDetailT();
        detail.setMaterilaListId(materilaListId);
        detail.setMaterialNo(materialNo);
        detail.setMaterialName(materialName);
        detail.setFigureNo(figureNo);
//			detail.setMaterialSheft(materialSheft);
//			detail.setMaterialReplace(materialReplace);
        detail.setMaterialTrace(Integer.parseInt(materialTrace));
        detail.setMaterialNumber(Integer.parseInt(materialNumber));
//			detail.setMaterialImpFlag(Integer.parseInt(materialImpFlag));
//			detail.setMaterialCheck(Integer.parseInt(materialCheck));
//			detail.setMaterialGetNumber(Integer.parseInt(materialGetNumber));
//			detail.setMaterialStore(materialStore);
//			detail.setMaterialGetCheckFlag(Integer.parseInt(materialGetCheckFlag));
//			detail.setStationId(Integer.decode(stationId));
//			detail.setMaterialPullFalg(Integer.parseInt(materialPullFalg));

        try {
            bomService.addMaterialListDetailTwo(detail);
            json.put("code", 0);
        } catch (ServicesException e) {
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            json.put("code", -1);
            json.put("msg", e.getMessage());
        }
        return json;
    }

    /**
     * 修改料单明细
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "修改料单明细", notes = "修改料单明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "所属料单", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materilaListId", value = "所属Bom", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialCode", value = "物料编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "materialName", value = "物料名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "graphNumber", value = "图号", required = true, paramType = "query"),

//			@ApiImplicitParam(name = "materialSheft", value = "料槽号", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialReplace", value = "替换组", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialTrace", value = "追溯方式0-批次追溯1-精确追溯2-不追诉", required = false, paramType = "query"),
            @ApiImplicitParam(name = "materialNumber", value = "部件数量", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "materialImpFlag", value = "主关件标记1-是0-否", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialCheck", value = "完整性检查1-检查2-不检查", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialGetNumber", value = "取料数量", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialStore", value = "存放料仓", required = true, paramType = "query"),
//			@ApiImplicitParam(name = "materialGetCheckFlag", value = "取料检查标记1-检查2-不检查", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "stationId", value = "工位名称", required = false, paramType = "query"),
//			@ApiImplicitParam(name = "materialPullFalg", value = "拉动标记0-自动拉动标记1-手动拉动标记", required = false, paramType = "query")
    })
    @RequestMapping(value = "editMaterialDetail", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "BOM管理", method = "编辑料单明细")
    public JSONObject editMaterialDetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();

        String id = request.getParameter("id");
        String materilaListId = request.getParameter("materilaListId");
        String materialNo = request.getParameter("materialCode");
        String materialName = request.getParameter("materialName");
        String graphNumber = request.getParameter("figureNo").trim();

//			String materialSheft = request.getParameter("materialSheft").trim();
//			String materialReplace = request.getParameter("materialReplace");
        String materialTrace = request.getParameter("materialTrace");
        String materialNumber = request.getParameter("materialNumber");

//			String materialImpFlag = request.getParameter("materialImpFlag");
//			String materialCheck = request.getParameter("materialCheck");
//			String materialGetNumber = request.getParameter("materialGetNumber");
//			String materialStore = request.getParameter("materialStore");
//			String materialGetCheckFlag = request.getParameter("materialGetCheckFlag");
//			String stationId = request.getParameter("stationId");
//			String materialPullFalg = request.getParameter("materialPullFalg");

        CMesMaterialListDetailT detail = new CMesMaterialListDetailT();
        detail.setId(Integer.valueOf(id));
        // API外部ID支持
        if (null == detail.getId() || 0 == detail.getId()) {
            // 根据图号查询ID
            CMesMaterialListDetailT mesMaterialListDetailT = bomService.findMaterialListDetailByfigureNo(null, graphNumber);
            if (null!=mesMaterialListDetailT) {
                detail.setId(mesMaterialListDetailT.getId());
            }else{
                json.put("code", 1);
                json.put("msg", "料单不存在");
                return json;
            }
        }
        detail.setMaterilaListId(materilaListId);
        detail.setMaterialNo(materialNo);
        detail.setMaterialName(materialName);
        detail.setFigureNo(graphNumber);
//			detail.setMaterialSheft(materialSheft);
//			detail.setMaterialReplace(materialReplace);
        detail.setMaterialTrace(Integer.parseInt(materialTrace));
        detail.setMaterialNumber(Integer.parseInt(materialNumber));
//			detail.setMaterialImpFlag(Integer.parseInt(materialImpFlag));
//			detail.setMaterialCheck(Integer.parseInt(materialCheck));
//			detail.setMaterialGetNumber(Integer.parseInt(materialGetNumber));
//			detail.setMaterialStore(materialStore);
//			detail.setMaterialGetCheckFlag(Integer.parseInt(materialGetCheckFlag));
//			detail.setStationId(Integer.decode(stationId));
//			detail.setMaterialPullFalg(Integer.parseInt(materialPullFalg));
        try {
            bomService.updateMaterialListDetailTwo(detail);
            json.put("code", 0);
        } catch (ServicesException e) {
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        } catch (Exception e) {
            json.put("code", -1);
            json.put("msg", e.getMessage());
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
        }
        return json;
    }

    /**
     * 删除料单明细
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServicesException
     */
    @ApiOperation(value = "删除料单明细", notes = "删除料单明细")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "料单id", required = true, paramType = "query")})
    @Transactional
    @RequestMapping(value = "delMaterialDetail", method = RequestMethod.POST)
    @OptionalLog(module = "生产", module2 = "BOM管理", method = "删除料单明细")
    public JSONObject delMaterialDetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServicesException {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        String figureNo = request.getParameter("figureNo");
        HttpSession session=request.getSession();
        try {
            // API外部ID支持
            if (null == id || "" .equals(id)) {
                // 根据图号查询ID
                CMesMaterialListDetailT mesMaterialListDetailT = bomService.findMaterialListDetailByfigureNo(null, figureNo);
                if (null!=mesMaterialListDetailT) {
                    id=String.valueOf(mesMaterialListDetailT.getId());
                    session.setAttribute("figureNo",mesMaterialListDetailT.getFigureNo());
                }else{
                    json.put("code", 1);
                    json.put("msg", "料单不存在");
                    return json;
                }
            }else{
                // 根据id查询图号
                CMesMaterialListDetailT mesMaterialListDetailT = bomService.findMaterialListDetailByfigureNo(Integer.parseInt(id), null);
                if (null!=mesMaterialListDetailT) {
                    session.setAttribute("figureNo",mesMaterialListDetailT.getFigureNo());
                }else {
                    json.put("code", 1);
                    json.put("msg", "料单不存在");
                    return json;
                }
            }
            bomService.delMaterialListDetail(Integer.parseInt(id));
            json.put("code", 0);
        } catch (ServicesException e) {
            json.put("code", e.getCode());
            json.put("msg", e.getMessage());
        } catch (Exception e) {
            json.put("code", -1);
            json.put("msg", e.getMessage());
        }
        return json;

    }
}

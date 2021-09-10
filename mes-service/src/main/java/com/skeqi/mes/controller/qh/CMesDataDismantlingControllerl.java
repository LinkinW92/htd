package com.skeqi.mes.controller.qh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.PTrackingT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "数据拆解", description = "数据拆解", produces = MediaType.APPLICATION_JSON)
public class CMesDataDismantlingControllerl {

	@Autowired
	QualityService qualityService;

	@Autowired
	CMesProductionTService productionService;


	// 配方明细列表
	@RequestMapping(value = "/DataDismantList", method = RequestMethod.POST)
	@ApiOperation(value = "数据拆解列表", notes = "数据拆解列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum1", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageNum2", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageNum3", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "sn", value = "总成号", required = false, paramType = "query", dataType = "int"),
			})
	@ResponseBody
	public JSONObject DataDismantList(@RequestParam(defaultValue = "1") Integer pageNum1,
			@RequestParam(defaultValue = "1") Integer pageNum2,
			@RequestParam(defaultValue = "1") Integer pageNum3,
			@RequestParam(defaultValue = "10") Integer pageSize,String sn) throws ServicesException {
		JSONObject json = new JSONObject();
		if (sn == null) {
			sn = "";
		}
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<PTrackingT> ptrackingList = qualityService.findPTrack(sn);
		if (ptrackingList.size() > 0) {

			PageHelper.startPage(pageNum1,10);
			List<PMesBoltT> boltListR = qualityService.findPBolt(sn);
			PageInfo<PMesBoltT> pageInfo = new PageInfo<>(boltListR, 5);

			PageHelper.startPage(pageNum2,10);
			List<PMesKeypartT> keypartListR = qualityService.findPKeypart(sn);
			PageInfo<PMesKeypartT> pageInfo2 = new PageInfo<>(keypartListR, 5);

			PageHelper.startPage(pageNum3,10);
			List<PMesLeakageT> leakageListR = qualityService.findPLeakage(sn);
			PageInfo<PMesLeakageT> pageInfo3 = new PageInfo<>(leakageListR, 5);
			Integer productionId = ptrackingList.get(0).getProductionId();//产品id
			String productionType = productionService.findProductionByid(productionId).getProductionType();
			json.put("BoltList", pageInfo);
			json.put("KeypartList", pageInfo2);
			json.put("LeakageList", pageInfo3);
			json.put("status", ptrackingList.get(0).getStatus());
			json.put("reason",ptrackingList.get(0).getReason());
			json.put("productionType",productionType );   //产品类型
			json.put("startTime",sim.format(ptrackingList.get(0).getDt()));  //开始时间
			if(ptrackingList.get(0).getOfflineDt()!=null) {
				json.put("endTime", sim.format(ptrackingList.get(0).getOfflineDt()));   //结束时间
			}else{
				json.put("endTime", "");
			}
		} else {
			List<RTrackingT> rtrackingList = qualityService.findRTrack(sn);
			if (rtrackingList.size() > 0) {

				PageHelper.startPage(pageNum1,10);
				List<RMesBolt> boltListR = qualityService.findRbolt(sn);
				PageInfo<RMesBolt> pageInfo = new PageInfo<>(boltListR, 5);

				PageHelper.startPage(pageNum2,10);
				List<RMesKeypart> keypartListR = qualityService.findRKeypart(sn);
				PageInfo<RMesKeypart> pageInfo2 = new PageInfo<>(keypartListR, 5);

				PageHelper.startPage(pageNum3,10);
				List<RMesLeakage> leakageListR = qualityService.findRLeakage(sn);
				PageInfo<RMesLeakage> pageInfo3 = new PageInfo<>(leakageListR, 5);
				Integer productionId = rtrackingList.get(0).getProductionId();
				String productionType = productionService.findProductionByid(productionId).getProductionType();
				json.put("boltList", pageInfo);
				json.put("KeypartList", pageInfo2);
				json.put("LeakageList", pageInfo3);
				json.put("status", rtrackingList.get(0).getStatus());
				json.put("reason",rtrackingList.get(0).getReason());
				json.put("productionType",productionType );   //产品类型
				json.put("startTime",sim.format(rtrackingList.get(0).getDT()));  //开始时间
				json.put("endTime", null);   //结束时间
			}
		}
		return json;
	}


	/**
	 * 拆解数据（修改bolt、keypart、leakage状态）
	 */
	@RequestMapping(value = "/relieveData", method = RequestMethod.POST)
	@ApiOperation(value = "数据拆解", notes = "数据拆解")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "mode", value = "mode", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "reasons", value = "reasons", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "sn", value = "sn", required = false, paramType = "query", dataType = "String"),
		})
	@ResponseBody
	@Transactional
	@OptionalLog(module="生产", module2="数据拆解", method="数据拆解")
	public JSONObject relieveData(Integer id,String mode,String sn,String reasons) {
		JSONObject json = new JSONObject();
		try {
			List<PTrackingT> ptrackingList = qualityService.findPTrack(sn);
			if (ptrackingList.size() > 0) {
				if ("1".equals(mode)) {
					qualityService.updateBoltP(id, reasons);
					qualityService.insertPBolt(id);
				} else if (mode.equals("2")) {
					qualityService.updateKeypartP(id, reasons);
					qualityService.insertPKeypart(id);
				} else if (mode.equals("3")) {
					qualityService.updateLeakageP(id, reasons);
					qualityService.insertLeakage(id);
				}
			} else {
				List<RTrackingT> rtrackingList = qualityService.findRTrack(sn);
				if (rtrackingList.size() > 0) {
					if ("1".equals(mode)) {
						qualityService.updateBoltR(id, reasons);
						qualityService.insertPBoltByR(id);
					} else if (mode.equals("2")) {
						qualityService.updateKeypartR(id, reasons);
						qualityService.insertPKeypartByR(id);
					} else if (mode.equals("3")) {
						qualityService.updateLeakageR(id, reasons);
						qualityService.insertLeakageByR(id);
					}
				}
			}
			json.put("code", 0);
			json.put("msg","拆解成功");
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return json;
	}

	@RequestMapping(value = "replaceData", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "isadd", value = "id", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "mode", value = "mode", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "keypartNum", value = "keypartNum", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "sn", value = "sn", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "a", value = "a", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "t", value = "t", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "leakagePv", value = "leakagePv", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "leakageLv", value = "leakageLv", required = false, paramType = "query", dataType = "String"),
		})
	@ResponseBody
	@Transactional
	public JSONObject replaceData(Integer id,Integer isadd,String mode,String keypartNum,String sn,String a,String t,String leakagePv,String leakageLv) {
		JSONObject json = new JSONObject();
		try {
			List<PTrackingT> ptrackingList = qualityService.findPTrack(sn);
			if (ptrackingList.size() > 0) {
				if ("1".equals(mode)) {
					if(isadd==1) {
						qualityService.insertPBolt(id);
					}
					qualityService.updateBolt(id, a, t);
				} else if (mode.equals("2")) {
					if(isadd==1) {
						qualityService.insertPKeypart(id);
					}
					qualityService.updateKeyparts(id, keypartNum);
				} else if (mode.equals("3")) {
					if(isadd==1) {
						qualityService.insertLeakage(id);
					}
					qualityService.updateLeakage(id, leakagePv, leakageLv);
				}
			}else {
				if ("1".equals(mode)) {
					if(isadd==1) {
						qualityService.insertPBoltByR(id);
					}
					qualityService.updateBoltR(id, a, t);
				} else if (mode.equals("2")) {
					if(isadd==1) {
						qualityService.insertPKeypartByR(id);
					}
					qualityService.updateKeypartsR(id, keypartNum);
				} else if (mode.equals("3")) {
					if(isadd==1) {
						qualityService.insertLeakageByR(id);
					}
					qualityService.updateLeakageR(id, leakagePv, leakageLv);
				}
			}
			json.put("code",100);
			json.put("msg","替换成功");
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code",300);
			json.put("msg","替换失败");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return json;
		}
	}


}

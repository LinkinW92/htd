package com.skeqi.mes.controller.rework;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.qh.APIResult;
import com.skeqi.mes.pojo.qh.ReworkEntity;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.rework.ReworkService;
import com.skeqi.mes.util.DateUtil;
import io.swagger.annotations.Api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/3/5 10:29
 */
@Api(value = "返修",description = "返修")
@RequestMapping("api/rework")
@RestController
public class ReworkController {

    @Autowired
    ReworkService service;

    /**
         * 当前工位替换/拆解
    * @author FQZ
    * @date 2021-3-198:43:41
     */
    @RequestMapping(value = "currentStation",method = RequestMethod.POST)
    @Transactional
    public APIResult currentStation(@RequestBody ReworkEntity re) {
    	try {
			service.currentStation(re);
			return new APIResult("100","成功","true");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new APIResult("200","失败","false");
		}
    }

    /**
        * 总成判废
    * @author FQZ
    * @date 2021-3-198:43:53
     */
    @RequestMapping(value = "Annulment",method = RequestMethod.POST)
    public APIResult Annulment(@RequestBody ReworkEntity re) {
    	try {
			service.Annulment(re.getSn(), re.getReason());
			return new APIResult("100","成功","true");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new APIResult("200","失败","false");
		}
    }

    /**
     * 查询此总成是否NG
    * @author FQZ
    * @date 2021-3-1910:19:10
     */
    @RequestMapping(value = "isAnnulment",method =  RequestMethod.POST)
    public JSONObject isAnnulment(@RequestBody ReworkEntity re) {
    	JSONObject json = new JSONObject();
    	try {
			Map<String, Object> findisNg = service.findisNg(re.getSn());
			if(findisNg!=null) {
				if(findisNg.get("reason")==null) {
					json.put("msg", "此总成未判废！");
					json.put("code", 200);
					return  json;
				}else {
					List<Map<String, Object>> findStationBySn = service.findStationBySn(re.getSn());
					json.put("msg", "返回成功");
					json.put("code", 100);
					json.put("reason", findisNg.get("reason"));
					json.put("findStationBySn", findStationBySn);
					json.put("pid",findisNg.get("pid"));
					return  json;
				}
			}else {
				json.put("msg", "未查询到此总成或此总成未判废！");
				json.put("code", 200);
				return json;
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "返回失败");
			json.put("code", 200);
			return json;
		}
    }

   /**
        *  总成状态回复OK
    * @author FQZ
    * @date 2021-3-1911:37:45
    */
    @RequestMapping(value = "recoveryOk",method =  RequestMethod.POST)
    public APIResult recoveryOk(@RequestBody ReworkEntity re) {
    	try {
    		service.updateOK(re.getSn());
    		return new APIResult("100","成功","true");
		} catch (Exception e) {
			e.printStackTrace();
			return new APIResult("200","失败","false");
		}
    }


    /**
          *  报废接口
     * @author FQZ
     * @date 2021-3-1911:44:15
     */
    @Transactional
    @RequestMapping(value = "ScrapSN",method = RequestMethod.POST)
    public APIResult ScrapSN(@RequestBody ReworkEntity re) {
    	try {
			APIResult scrapSN = service.ScrapSN(re.getSn(),re.getType(),re.getReason());
			return scrapSN;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new APIResult("200","失败","false");
		}
    }

    /**
     *  查询此总成是否可以制定返修路线
    * @author FQZ
    * @date 2021-3-229:15:28
     */
    @RequestMapping(value = "findRoute",method = RequestMethod.POST)
    public APIResult findRoute(@RequestBody ReworkEntity re) {
    	try {
			Integer findRoute = service.findRoute(re.getSn());
			if(findRoute>0) {
				return new APIResult("100","成功","true");
			}else {
				return new APIResult("200","该总成不存在或未下线","false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new APIResult("200","失败","false");
		}
    }

    /**
          *  查询产品和产线
     * @author FQZ
     * @date 2021-3-229:35:56
     */
    @RequestMapping(value = "findPL",method = RequestMethod.GET)
    public JSONObject findPL() {
    	JSONObject json = new JSONObject();
    	try {
			List<Map<String, Object>> findP = service.findP();
			List<Map<String, Object>> findL = service.findL();
			json.put("findP", findP);
			json.put("findL", findL);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return json;
			// TODO: handle exception
		}
    }

    /**
       * 根据产线和产品查询配方
    * @author FQZ
    * @date 2021-3-2211:00:01
     */
    @RequestMapping(value = "findRecipe",method = RequestMethod.POST)
    public JSONObject findRecipe(String lineId,String proId) {
    	JSONObject json = new JSONObject();
    	try {
			List<Map<String, Object>> findTotalRecipe = service.findTotalRecipe(lineId, proId);
			List<Map<String, Object>> findRoute = service.findProRoute(lineId, proId);
			json.put("recipeLIst", findTotalRecipe);
			json.put("findRoute", findRoute);
			json.put("code", 100);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.put("code",200);
			return json;
		}
    }

    @Transactional
    @RequestMapping(value = "addReworkRoute",method = RequestMethod.POST)
    public JSONObject addReworkRoute(String sn,String lineId,String proId,String recipeId,String routeId,String newSn) {
    	JSONObject json = new JSONObject();
    	try {
    		if(newSn!=null && newSn!="") {
    			service.addReworkWork(lineId, proId, recipeId, routeId, newSn);
    		}else {
    			service.addReworkWork(lineId, proId, recipeId, routeId, sn);
    		}
    		service.deleteP(sn);
    		json.put("code",100);
    		json.put("msg", "已生成工单！");
    		return json;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			json.put("code",200);
    		json.put("msg", "生成失败！");
    		return json;
		}
    }
}

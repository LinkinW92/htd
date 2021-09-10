package com.skeqi.mes.controller.yin;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.yin.WareHouseDao;
import com.skeqi.mes.pojo.CMesInWareHouse;
import com.skeqi.mes.pojo.CMesLibraryPositionT;
import com.skeqi.mes.pojo.CMesMaterialMessage;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesOutWareHouse;
import com.skeqi.mes.pojo.CMesWareHouseT;
import com.skeqi.mes.service.yin.MaterialService;
import com.skeqi.mes.util.ToolUtils;

@Controller
@RequestMapping("skq")
public class WareHouseController {
	@Autowired
	WareHouseDao wareHouseDao;

	@Autowired
	MaterialService materialService;

	/**
	 * 库存信息
	 */
	@RequestMapping("stockManager")
	public String stockManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CMesMaterialT> materialList = materialService.materialList(map);
		List<CMesLibraryPositionT> libraryPositionList = wareHouseDao.libraryPositionList(map);
		request.setAttribute("materialList", materialList);
		String materialId = request.getParameter("materialId");
		if (materialId!=null&&materialId!="") {
			map.put("materialId", materialId);
		}
		PageHelper.startPage(page,8);
		List<CMesMaterialMessage> stockManager = wareHouseDao.stockManager(map);
		PageInfo<CMesMaterialMessage> pageInfo = new PageInfo<>(stockManager,5);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("libraryPositionList", libraryPositionList);
		request.setAttribute("materialId", materialId);
		return "warehouse_control/stockManager";
	}

	/**
	 * 入库记录
	 */
	@RequestMapping("purchaseRecord")
	public String inWareHouseList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CMesMaterialT> materialList = materialService.materialList(map);

		List<CMesLibraryPositionT> libraryPositionList = wareHouseDao.libraryPositionList(map);
		String materialId = request.getParameter("materialId");
		String orderNumber = request.getParameter("orderNumber");
		if (materialId!="") {
			map.put("materialId", materialId);
		}
		if (orderNumber!=null&&orderNumber!="") {
			map.put("orderNumber", orderNumber.trim());
		}
		PageHelper.startPage(page,8);
		List<CMesInWareHouse> inWareHouseList = wareHouseDao.inWareHouseList(map);
		PageInfo<CMesInWareHouse> pageInfo = new PageInfo<>(inWareHouseList,5);
		request.setAttribute("materialList", materialList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("libraryPositionList", libraryPositionList);
		request.setAttribute("materialId", materialId);
		request.setAttribute("orderNumber", orderNumber);
		return "warehouse_control/purchaseManager";
	}
	/**
	 * 添加物料入库信息
	 */
	@Transactional
	@RequestMapping("addPurchase")
	public @ResponseBody Object addPurchase(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		String materialId = request.getParameter("materialId");
		String orderNumber = request.getParameter("orderNumber");
		String position = request.getParameter("position").trim();
		String materialNumber = request.getParameter("materialNumber").trim();
		String checkName = request.getParameter("checkName").trim();
		String operatorName = request.getParameter("operatorName").trim();
		String inWarehouseType = request.getParameter("inWarehouseType");
		String supplier = request.getParameter("supplier");
		String dis = request.getParameter("dis");
		//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		map.put("orderNumber", orderNumber);
		map.put("materialId", materialId);
		map.put("positionId", position);
		map.put("materialNumber", materialNumber);
		map.put("checkName", checkName);
		map.put("operatorName", operatorName);
		map.put("inWarehouseType", inWarehouseType);
		map.put("supplier", supplier);
		map.put("dis", dis);

		map.put("msg", -1);
		List<CMesMaterialMessage> stockManager = wareHouseDao.stockManager(map);

		try {
			if (stockManager.size()>0) {
				//若同一物料名称且同一供应商，在原有记录修改数量
				map.put("id", stockManager.get(0).getId());
				wareHouseDao.editStockManager(map);
			}else {
				map2.put("position", position);
				List<CMesMaterialMessage> stockManager2 = wareHouseDao.stockManager(map2);
				for (CMesMaterialMessage cMesMaterialMessage : stockManager2) {
					if (Integer.parseInt(materialId)==cMesMaterialMessage.getMaterielId()&&
							supplier.equals(cMesMaterialMessage.getSupplier())) {
					}else {
						map.put("msg", 2);
						return map;
					}
				}
				wareHouseDao.addStockManager(map);
				//物料和库位绑定
				wareHouseDao.editLibraryPosition(map);
			}
			wareHouseDao.addPurchase(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	@RequestMapping("toEditPurchase")
	@ResponseBody
	public Map<String, Object> toUpdatePurchase(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesInWareHouse> inWareHouseList = wareHouseDao.inWareHouseList(map);
		map.put("inWareHouse", inWareHouseList.get(0));
		return map;
	}
	/**
	 * 修改入库信息
	 */
	@RequestMapping("editPurchase")
	public @ResponseBody Map<String, Object> editPurchase(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		String orderNumber = request.getParameter("orderNumber");
		String materialId = request.getParameter("materialId");
		String position = request.getParameter("position").trim();
		String materialNumber = request.getParameter("materialNumber").trim();
		String checkName = request.getParameter("checkName").trim();
		String operatorName = request.getParameter("operatorName").trim();
		String inWarehouseType = request.getParameter("inWarehouseType");
		String supplier = request.getParameter("supplier");
		String dis = request.getParameter("dis");
		map.put("id", id);
		map.put("orderNumber", orderNumber);
		map.put("materialId", materialId);
		map.put("positionId", position);
		map.put("materialNumber", materialNumber);
		map.put("checkName", checkName);
		map.put("operatorName", operatorName);
		map.put("inWarehouseType", inWarehouseType);
		map.put("supplier", supplier);
		map.put("dis", dis);
		try {
			wareHouseDao.editPurchase(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}
	/**
	 * 删除入库物料信息
	 */
	@RequestMapping("delPurchase")
	public @ResponseBody Map<String, Object> delPurchase(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		try {
			wareHouseDao.delPurchase(map);
			map.put("msg", 0);
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 * 出库列表
	 */
	@RequestMapping("shipmentsManager")
	public String outWareHouseList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CMesMaterialT> materialList1 = materialService.materialList(map);
		request.setAttribute("materialList", materialList1);
		String materialId = request.getParameter("materialId");
		map.put("materialId", materialId);
		request.setAttribute("materialId", materialId);
		List<CMesMaterialT> materialList = materialService.materialList(map);
		if (materialList.size()==1) {
			map.put("materialName", materialList.get(0).getMaterialName());
		}
		PageHelper.startPage(page,8);
		List<CMesOutWareHouse> outWareHouseList = wareHouseDao.outWareHouseList(map);
		PageInfo<CMesOutWareHouse> pageInfo = new PageInfo<>(outWareHouseList,5);
		request.setAttribute("pageInfo", pageInfo);
		return "warehouse_control/shipmentsManager";
	}

	/**
	 * 添加物料出库信息
	 */
	@RequestMapping("addShiments")
	public @ResponseBody Object addShiments(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		String materialId = request.getParameter("materialId");
		String orderNumber = request.getParameter("orderNumber");
		String materialNumber = request.getParameter("materialNumber").trim();
		String checkName = request.getParameter("checkName").trim();
		String operatorName = request.getParameter("operatorName").trim();
		String outType = request.getParameter("outType");
		String dis = request.getParameter("dis");
		map.put("orderNumber", orderNumber);
		map.put("materialId", materialId);
		map.put("materialNumber", materialNumber);
		map.put("checkName", checkName);
		map.put("operatorName", operatorName);
		map.put("outType", outType);
		map.put("dis", dis);
		try {
			wareHouseDao.addShiments(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	@RequestMapping("toEditshipments")
	@ResponseBody
	public Map<String, Object> toEditshipments(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesOutWareHouse> outWareHouseList = wareHouseDao.outWareHouseList(map);
		map.put("outWareHouse", outWareHouseList.get(0));
		return map;
	}
	/**
	 * 修改出库信息
	 */
	@RequestMapping("editShipments")
	public @ResponseBody Map<String, Object> editShipments(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		String materialId = request.getParameter("materialId");
		String orderNumber = request.getParameter("orderNumber");
		String materialNumber = request.getParameter("materialNumber").trim();
		String checkName = request.getParameter("checkName").trim();
		String operatorName = request.getParameter("operatorName").trim();
		String outType = request.getParameter("outType");
		String dis = request.getParameter("dis");
		map.put("orderNumber", orderNumber);
		map.put("materialId", materialId);
		map.put("materialNumber", materialNumber);
		map.put("checkName", checkName);
		map.put("operatorName", operatorName);
		map.put("outType", outType);
		map.put("dis", dis);
		map.put("id", id);
		try {
			wareHouseDao.editShipments(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}


	/**
	 * 删除出库信息
	 */
	@RequestMapping("delShipments")
	public @ResponseBody Map<String, Object> delShipments(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		try {
			wareHouseDao.delShipments(map);
			map.put("msg", 0);
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 *
	 */
	@RequestMapping("toEditStockManager")
	@ResponseBody
	public Map<String, Object> toEditStockManager(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesMaterialMessage> stockManager = wareHouseDao.stockManager(map);
		map.put("stockManager", stockManager.get(0));
		return map;
	}
	/**
	 * 出库
	 */
	@Transactional
	@RequestMapping("editStockManager")
	public @ResponseBody Map<String, Object> editStockManager(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> map2 = new HashMap<>();
		String id = request.getParameter("id");
		String outType = request.getParameter("outType");
		String dis = request.getParameter("dis");
		String materialNumber = request.getParameter("materialNumber").trim();
		String materialName = request.getParameter("materialName").trim();
		map.put("materialNumber", materialNumber);
		map.put("materialName", materialName);
		map.put("outType", outType);
		map.put("id", id);
		map.put("dis", dis);
		map.put("msg", 1);
		List<CMesMaterialMessage> stockManager = wareHouseDao.stockManager(map);
		if (stockManager.get(0).getMaterielNumber()-Integer.parseInt(materialNumber)<0) {
			map.put("flag", 0);
			return map;
		}
		try {
			if (Integer.parseInt(materialNumber)-stockManager.get(0).getMaterielNumber()==0) {
				wareHouseDao.delStockManager(map);
				String positionName = request.getParameter("positionName");
				map2.put("positionNames", positionName);
				List<CMesLibraryPositionT> libraryPositionList = wareHouseDao.libraryPositionList(map2);
				map2.put("positionId", libraryPositionList.get(0).getId());
				wareHouseDao.editLibraryPosition2(map2);
			}
			//修改库存数量信息
			wareHouseDao.editStockManager(map);
			//添加到记录表
			wareHouseDao.addShiments(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	//库位管理
	@RequestMapping("stockPositionManager1")
	public @ResponseBody Map<String, Object> stockPositionManager(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		List<CMesLibraryPositionT> libraryPositionList = wareHouseDao.libraryPositionList(map);
		map.put("libraryPositionList", libraryPositionList);
		return map;
	}
	@RequestMapping("repositoryInformation")
	public String stockPositionManager(){
		return "warehouse_control/stockPositionManager";
	}
	//查询库位信息
	@RequestMapping("findPosition")
	public @ResponseBody Map<String, Object> findPosition(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesLibraryPositionT> libraryPositionList = wareHouseDao.libraryPositionList(map);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("lastDate", sdf.format(libraryPositionList.get(0).getLastDate()));
		map.put("libraryPosition", libraryPositionList.get(0));
		return map;
	}
	/**
	 * 库位管理
	 */
	@RequestMapping("repositoryManagement")
	public String repositoryManagement(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
		HashMap<String, Object> map = new HashMap<>();
		PageHelper.startPage(page,8);
		List<CMesLibraryPositionT> repositoryManagement = wareHouseDao.repositoryManagement(map);
		PageInfo<CMesLibraryPositionT> pageInfo = new PageInfo<>(repositoryManagement,5);
		List<CMesWareHouseT> wareHouseList = wareHouseDao.wareHouseList(map);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("wareHouseList", wareHouseList);
		return "warehouse_control/repositoryManagement";
	}
	/**
	 * 添加库位信息
	 */
	@RequestMapping("addRepositoryManagement")
	public @ResponseBody Object addRepositoryManagement(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		String warehouseId = request.getParameter("warehouseId").trim();
		String positionName = request.getParameter("positionName").trim();
		String dis = request.getParameter("dis");
		map.put("warehouseId", warehouseId);
		map.put("positionName", positionName);
		map.put("dis", dis);
		List<CMesLibraryPositionT> repositoryManagement = wareHouseDao.repositoryManagement(map);
		if (repositoryManagement.size()>0) {
			map.put("msg", 2);
			return map;
		}
		try {
			wareHouseDao.addRepositoryManagement(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	@RequestMapping("toEditRepositoryManagement")
	@ResponseBody
	public Map<String, Object> toEditRepositoryManagement(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesLibraryPositionT> repositoryManagement = wareHouseDao.repositoryManagement(map);
		map.put("repositoryManagement", repositoryManagement.get(0));
		return map;
	}

	/**
	 * 修改库位信息
	 * @param request
	 * @return
	 */
	@RequestMapping("editRepositoryManagement")
	public @ResponseBody Map<String, Object> editRepositoryManagement(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		String warehouseId = request.getParameter("warehouseId").trim();
		String positionName = request.getParameter("positionName").trim();
		String dis = request.getParameter("dis");
		String id = request.getParameter("id");
		map1.put("id", id);
		map.put("warehouseId", warehouseId);
		map.put("positionName", positionName);
		map.put("dis", dis);
		List<CMesLibraryPositionT> repositoryManagement = wareHouseDao.repositoryManagement(map1);
		if (!repositoryManagement.get(0).getPositionName().equals(positionName)) {
			List<CMesLibraryPositionT> repositoryManagement1 = wareHouseDao.repositoryManagement(map);
			if (repositoryManagement1.size()>0) {
				map.put("msg", 2);
				return map;
			}
		}
		map.put("id", id);
		try {
			wareHouseDao.editRepositoryManagement(map);
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}
	/**
	 * 删除库位信息
	 * @param request]]
	 * @return
	 */
	@RequestMapping("delRepositoryManagement")
	public @ResponseBody Map<String, Object> delRepositoryManagement(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesLibraryPositionT> repositoryManagement = wareHouseDao.repositoryManagement(map);
		if (repositoryManagement.get(0).getMaterialId()!=null&&!repositoryManagement.get(0).getMaterialId().equals("")) {
			map.put("msg", 1);
			return map;
		}
		try {
			wareHouseDao.delRepositoryManagement(map);
			map.put("msg", 0);
		} catch (Exception e) {
		}
		return map;
	}

}

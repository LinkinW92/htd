package com.skeqi.mes.service.wms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.wms.WarehousingDao;
import com.skeqi.mes.pojo.CMesSystem;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;
import com.skeqi.mes.service.all.CMesSystemService;


/**
 * 入库管理
 *
 * @author yinp
 * @date 2020年3月21日
 *
 */
@Service
public class WarehousingServiceImpl implements WarehousingService {

	@Autowired
	CMesSystemService sService;

	@Autowired
	WarehousingDao dao;

	@Override
	public boolean add(Integer userId,String listNo, Integer projectId, Integer warehouseId, Integer areaId, Integer resevoirAreaId,
			Integer locationId, Integer materialId, Integer materialNumber) throws Exception {

		// 保存库存详情
		List<CWmsStorageDetailT> sdList = new ArrayList<CWmsStorageDetailT>();

		if (listNo == null || listNo.equals("")) {
			// 生成单号
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
			// 单号
			listNo = "RK" + s.format(new Date());

			CMesUserT user = dao.findUserById(userId);
			if(user == null || user.getId()==null) {
				throw new Exception("用户有误");
			}

			// 审批流程类型id
			Integer typeId = 1;

			ProcessApproval padx = new ProcessApproval();
			padx.setDeptId(Integer.parseInt(user.getDepartment()));
			padx.setRoleId(user.getRoleId());
			padx.setTypeId(typeId);

			// 查询审批流程
			List<ProcessApproval> paList = dao.findProcessApproval(padx);
			if (paList.size() == 0) {
				throw new Exception("您不能生成执行出库操作，可能是没有配置审批流程");
			}

			padx = paList.get(0);

			// 保存审批表
			CWmsApprovalT approval = new CWmsApprovalT();
			approval.setListNo(listNo);// 单号
			approval.setProcessId(padx.getId());// 流程主表id
			approval.setUserId(user.getId());// 申请用户id
			approval.setState(0);// 审批状态 0=未审批
			// 新增审批表记录
			Integer res = dao.addApproval(approval);
			if (res <= 0) {
				throw new Exception("新增审批记录时出错了");
			}

			// 查询流程详情
			Map<String, Object> padMap = new HashMap<String, Object>();
			padMap.put("processId", padx.getId());
			List<ProcessApprovalDetail> padList = dao.findProcessApprovalDetailList(padMap);
			if (padList.size() == 0) {
				throw new Exception("查询审批流程详情出错了");
			}

			// 查询出刚刚新增的那一条审批记录数据
			CWmsApprovalT adx = dao.findApprovalByListNo(listNo);
			if (adx == null) {
				throw new Exception("没有查询到刚才新增的审批记录");
			}

			for (ProcessApprovalDetail processApprovalDetail : padList) {
				// 创建审批详情表对象
				CWmsApprovalDetailsT detail = new CWmsApprovalDetailsT();
				detail.setListNo(listNo);// 单号
				detail.setApprovalResult(0);// 0:未审批，1:通过，2:不通过
				detail.setUserId(processApprovalDetail.getUserId());// 审批人id
				detail.setReason("");// 原因
				detail.setApprovalId(adx.getId());// 审批主表id
				detail.setPriorityLevel(processApprovalDetail.getPriorityLevel());// 优先级
				if (processApprovalDetail.getPriorityLevel() == 1) {
					detail.setYnApproved(1);
				}
				// 开始新增审批详情数据
				res = dao.addApprovalDetails(detail);
				if (res <= 0) {
					throw new Exception("新增审批详情数据出错了");
				}

			}
		}

//		for (int i = 0; i < materialNumber; i++) {
			CWmsStorageDetailT sddx = new CWmsStorageDetailT();
			sddx.setMaterialId(materialId);
			sddx.setMaterialNumber(materialNumber);
			sddx.setWarehouseId(warehouseId);
			sddx.setAreaId(areaId);
			sddx.setReservoirAreaId(resevoirAreaId);
			sddx.setLocationId(locationId);
			sddx.setListNo(listNo);
			sddx.setProjectId(projectId);
			sddx.setYnShift(0);
			sddx.setIssueOrReceipt(1);
			// 新增临时库存详情表记录
			Integer res = dao.addStorageDetail(sddx);
			if (res <= 0) {
				throw new Exception("新增临时库存详情记录出错了");
			}
//		}





		return true;
	}

	/**
	 * 撤销
	 *
	 * @param listNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean revoke(String listNo,Integer userId) throws Exception {

		CWmsApprovalT approval = new CWmsApprovalT();
		approval.setListNo(listNo);
		// 查询审批流程
		List<CWmsApprovalT> aList = dao.findApproval(approval);
		// 没有查询出单据说明数据被恶意篡改
		if (aList.size() != 1) {
			throw new Exception("未查询出审批流程，数据可能被篡改");
		}

		approval = aList.get(0);
		// 登录的用户跟操作的审批单据申请人id如果有不符 说明数据可能被恶意篡改
		if (approval.getUserId() != userId) {
			throw new Exception("用户无法撤销他人申请的单据，数据可能被篡改");
		}

		// 修改状态为撤销
		approval.setState(4);
		Integer res = dao.updateApproval(approval);
		if (res <= 0) {
			throw new Exception("修改审批表状态失败");
		}

		// 领用出库
		// 查询出库存详情
		CWmsStorageDetailT sdDx = new CWmsStorageDetailT();
		sdDx.setListNo(listNo);
		List<CWmsStorageDetailT> stoDetailList = dao.findStorageDetail(sdDx);

		if (approval.getProcessType().getId() == 5) {
			// 领用出库
			for (CWmsStorageDetailT cWmsStorageDetailT : stoDetailList) {

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("locationId", cWmsStorageDetailT.getLocationId());
				map.put("projectId", cWmsStorageDetailT.getProjectId());
				map.put("materialId", cWmsStorageDetailT.getMaterialId());
				// 查询物料库存
				List<CWmsMaterialNumberT> mnList = dao.findMaterialNumberList(map);
				if (mnList.size() != 1) {
					throw new Exception("查询物料库存时出错了");
				}
				CWmsMaterialNumberT mnDx = mnList.get(0);

				// 新增到P表
				res = dao.addPStorageDetail(cWmsStorageDetailT);
				if (res <= 0) {
					throw new Exception("R表记录转移P表时出错了");
				}
				// 删除R表
				res = dao.deleteStorageDetail(cWmsStorageDetailT.getId());
				if (res <= 0) {
					throw new Exception("R表记录转移P表后删除R表数据出错了");
				}

			}
		} else {
			// 入库
			for (CWmsStorageDetailT cWmsStorageDetailT : stoDetailList) {

				// 新增到P表
				res = dao.addPStorageDetail(cWmsStorageDetailT);
				if (res <= 0) {
					throw new Exception("R表记录转移P表时出错了");
				}
				// 删除R表
				res = dao.deleteStorageDetail(cWmsStorageDetailT.getId());
				if (res <= 0) {
					throw new Exception("R表记录转移P表后删除R表数据出错了");
				}

			}
		}

		return true;
	}

	@Override
	public boolean xiagmuAdd(String listNo, Integer projectId, Integer warehouseId, Integer areaId,
			Integer resevoirAreaId, Integer locationId, Integer materialId, Integer materialNumber) throws Exception {

		if (listNo == null || listNo.equals("")) {

			// 生成单号
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
			// 单号
			listNo = "RK" + s.format(new Date());

			// 获取user //TODO
//			Subject subject = SecurityUtils.getSubject();
//			CMesUserT user = (CMesUserT) subject.getSession().getAttribute("user");
//			if (user == null) {
//				throw new Exception("用户已失效");
//			}

			// 保存审批表
			CWmsApprovalT approval = new CWmsApprovalT();
			approval.setListNo(listNo);// 单号
			approval.setProcessId(0);// 流程主表id
		//	approval.setUserId(user.getId());// 申请用户id
			approval.setState(0);// 审批状态 0=未审批
			// 新增审批表记录
			Integer res = dao.addApproval(approval);
			if (res <= 0) {
				throw new Exception("新增审批记录时出错了");
			}
		}

		CWmsStorageDetailT sddx = new CWmsStorageDetailT();
		sddx.setMaterialId(materialId);
		sddx.setMaterialNumber(materialNumber);
		sddx.setWarehouseId(warehouseId);
		sddx.setAreaId(areaId);
		sddx.setReservoirAreaId(resevoirAreaId);
		sddx.setLocationId(locationId);
		sddx.setListNo(listNo);
		sddx.setProjectId(projectId);
		sddx.setYnShift(0);
		sddx.setIssueOrReceipt(1);

		// 新增临时库存详情表记录
		Integer res = dao.addStorageDetail(sddx);
		if (res <= 0) {
			throw new Exception("新增临时库存详情记录出错了");
		}

		return true;
	}

	@Override
	public PageInfo<CWmsApprovalT> queryStockInInformation(JSONObject json) throws Exception {
		/**
		 * 查询系统配置
		 */
		List<CMesSystem> systemList = sService.findByAll(null);
		if (systemList == null || systemList.size() == 0) {
			throw new Exception("查询系统配置出错了");
		}

		Integer pageNumber = json.getInteger("pageNumber");
		if (pageNumber == null) {
			pageNumber = 1;
		}

		for (CMesSystem cMesSystem : systemList) {
			// 比对项目号
			if (cMesSystem.getName().equals("通用.项目")) {
				if (cMesSystem.getParameter().equals("XT357")) {
					PageHelper.startPage(pageNumber, 8);
					List<CWmsApprovalT> list = dao.queryStockInInformationXT355_356_357(json);
					PageInfo<CWmsApprovalT> pageInfo = new PageInfo<CWmsApprovalT>(list, 5);
					return pageInfo;
				}
			}
		}

		CWmsApprovalT dx = new CWmsApprovalT();
		dx.setUserId(json.getInteger("userId"));

		// 结束
		PageHelper.startPage(pageNumber, 8);
		List<CWmsApprovalT> list = dao.findApproval(dx);
		PageInfo<CWmsApprovalT> pageInfo = new PageInfo<CWmsApprovalT>(list, 5);
		return pageInfo;
	}

	@Override
	public boolean zhixingXT355_356_357(JSONObject json) throws Exception {
		String listNo = json.getString("listNo");

		CWmsStorageDetailT sddx = new CWmsStorageDetailT();
		sddx.setListNo(listNo);
		//查询临时库存详情
		List<CWmsStorageDetailT> storageDetailList = dao.findStorageDetail(sddx);
		if(storageDetailList==null || storageDetailList.size()==0){
			throw new Exception("单号不存在.临时库存详情");
		}

		CWmsApprovalT aDx = new CWmsApprovalT();
		aDx.setListNo(listNo);
		aDx.setState(2);
		//更新审批表状态
		Integer res = dao.updateApproval(aDx);
		if(res<=0){
			throw new Exception("单号不存在.审批记录");
		}

		List<CWmsInTaskqueueT> itDxList = new ArrayList<CWmsInTaskqueueT>();

		for (int i = 0; i < storageDetailList.size(); i++) {
			CWmsInTaskqueueT itDx = new CWmsInTaskqueueT();
			if(i==0){
				itDx.setListNo(listNo);
				itDx.setTray("");
				itDx.setOverDt("");
				itDx.setLocationId(storageDetailList.get(i).getLocationId());

				itDxList.add(itDx);

			}else{
				for (int j = 0; j < itDxList.size(); j++) {
					if(storageDetailList.get(i).getLocationId().equals(itDxList.get(j).getLocationId())){
						continue;
					}else if((itDxList.size()-1)==j){
						itDx.setListNo(listNo);
						itDx.setTray("");
						itDx.setOverDt("");
						itDx.setLocationId(storageDetailList.get(i).getLocationId());

						itDxList.add(itDx);
					}
				}
			}
		}

		for (CWmsInTaskqueueT cWmsInTaskqueueT : itDxList) {
			//新增临时入库队列
			res = dao.addInTaskqueue(cWmsInTaskqueueT);
			if(res<=0){
				throw new Exception("新增入库队列出错了");
			}
		}

		return true;
	}

	@Override
	public List<CWmsStorageDetailT> findPStorageDetail(CWmsStorageDetailT storageDetail) {
		// TODO Auto-generated method stub
		return dao.findPStorageDetail(storageDetail);
	}

	@Override
	public List<CWmsStorageDetailT> findStorageDetail(CWmsStorageDetailT storageDetail) {
		// TODO Auto-generated method stub
		return dao.findStorageDetail(storageDetail);
	}

	@Override
	public List<Map<String,Object>> findPStorageDetailSum(String listNo) {
		// TODO Auto-generated method stub
		return dao.findPStorageDetailSum(listNo);
	}

	@Override
	public List<Map<String,Object>> findRStorageDetailSum(String listNo) {
		// TODO Auto-generated method stub
		return dao.findRStorageDetailSum(listNo);
	}

	@Override
	public List<JSONObject> findLocationIdAndName(Integer raId) {
		// TODO Auto-generated method stub
		return dao.findLocationIdAndName(raId);
	}

	@Override
	public List<JSONObject> findWarehouse() {
		// TODO Auto-generated method stub
		return dao.findWarehouse();
	}

	@Override
	public List<JSONObject> findArea(Integer warehouseId) {
		// TODO Auto-generated method stub
		return dao.findArea(warehouseId);
	}

	@Override
	public List<JSONObject> findReservoirArea(Integer areaId) {
		// TODO Auto-generated method stub
		return dao.findReservoirArea(areaId);
	}

	@Override
	public List<JSONObject> findLocation(Integer reservoirAreaId) {
		// TODO Auto-generated method stub
		return dao.findLocation(reservoirAreaId);
	}

	@Override
	public List<JSONObject> findProject(String projectName) {
		// TODO Auto-generated method stub
		return dao.findProject(projectName);
	}

	@Override
	public List<JSONObject> findMaterial(String materialName) {
		// TODO Auto-generated method stub
		return dao.findMaterial(materialName);
	}

}

package com.skeqi.mes.service.wms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.MaterialAdditionDao;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;

/**
 * 物料追加
 *
 * @author yinp
 *
 */
@Service
public class MaterialAdditionServiceImpl implements MaterialAdditionService {

	@Autowired
	MaterialAdditionDao dao;

	// 新增session
	@Override
	public boolean addSession(HttpSession session, Integer projectId, String projectName, Integer materialId,
			String materialName, Integer materialNumber) {

		List<JSONObject> list = (List<JSONObject>) session.getAttribute("MaterialAdditionData");

		// 判断是否第一次进入新增方法
		if (list == null) {
			list = new ArrayList<JSONObject>();

			JSONObject jo = new JSONObject();
			jo.put("projectId", projectId);
			jo.put("projectName", projectName);
			jo.put("materialId", materialId);
			jo.put("materialName", materialName);
			jo.put("materialNumber", materialNumber);

			list.add(jo);
			session.setAttribute("MaterialAdditionData", list);
			// 第一次进入新增方法执行 把对象塞进session，然后返回
			return true;
		}

		for (int i = 0; i < list.size(); i++) {

			JSONObject js = list.get(i);
			if (js.getInteger("projectId").equals(projectId) && js.getInteger("materialId").equals(materialId)) {
				// 碰到一样的项目+物料 直接修改数量
				Integer number = js.getInteger("materialNumber");
				number = number + materialNumber;
				js.put("materialNumber", number);
				list.set(i, js);
				return true;
			}

			// 循环到最后一次还是没有找到一样的项目+物料，直接吧对象塞进session
			if (i == (list.size() - 1)) {
				JSONObject jo = new JSONObject();
				jo.put("projectId", projectId);
				jo.put("projectName", projectName);
				jo.put("materialId", materialId);
				jo.put("materialName", materialName);
				jo.put("materialNumber", materialNumber);

				list.add(jo);
				session.setAttribute("MaterialAdditionData", list);
				return true;
			}
		}

		return true;
	}

	// 更新session
	@Override
	public boolean updateSession(HttpSession session, JSONObject json) {
		// TODO Auto-generated method stub
		return false;
	}

	// 删除session
	@Override
	public boolean deleteSession(HttpSession session, int index) throws Exception {
		List<JSONObject> list = (List<JSONObject>) session.getAttribute("MaterialAdditionData");

		// 判断是否第一次进入新增方法
		if (list == null) {
			throw new Exception("你没有保存的数据了");
		} else {
			list.remove(index);
			if (list.size() == 0) {
				session.setAttribute("MaterialAdditionData", null);
			}
		}
		return true;
	}

	// 保存加料
	@Override
	public boolean addMaterialAddition(String feedingData, Integer materialNumberId, int userId) throws Exception {

		List<Object> list = JSONObject.parseArray(feedingData);

		if (list == null) {
			throw new Exception("没有追加的物料信息");
		}

		// 获取user
		CMesUserT user = dao.findUserById(userId);
		if (user == null) {
			throw new Exception("用户不存在");
		}

		// 生成单号
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		// 单号
		String listNo = "JL" + s.format(new Date());

		// 审批流程类型id
		Integer typeId = 10;

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
		if (res == 0) {
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
			detail.setUserId(user.getId());// 审批人id
			detail.setReason("");// 原因
			detail.setApprovalId(adx.getId());// 审批主表id
			detail.setPriorityLevel(processApprovalDetail.getPriorityLevel());// 优先级
			if (processApprovalDetail.getPriorityLevel() == 1) {
				detail.setYnApproved(1);
			}
			// 开始新增审批详情数据
			res = dao.addApprovalDetails(detail);
			if (res == 0) {
				throw new Exception("新增审批详情数据出错了");
			}

		}

		CWmsMaterialNumberT mnDx = dao.findMaterialNumberById(materialNumberId);
		if (mnDx == null) {
			throw new Exception("查询物料库存出错了");
		}

		Integer count = dao.findRInTaskqueueCount(mnDx.getLocationId());
		if (count > 0) {
			throw new Exception("托盘存在出入库操作，无法执行加料操作");
		}

		count = dao.findROutTaskqueueCount(mnDx.getLocationId());
		if (count > 0) {
			throw new Exception("托盘存在出入库操作，无法执行加料操作");
		}

		for (Object object : list) {
			JSONObject js = JSONObject.parseObject(object.toString());
			CWmsStorageDetailT sddx = new CWmsStorageDetailT();
			sddx.setMaterialId(js.getInteger("materialId"));
			sddx.setMaterialNumber(js.getInteger("materialNumber"));
			sddx.setWarehouseId(mnDx.getWareHouseId());
			sddx.setAreaId(mnDx.getAreaId());
			sddx.setReservoirAreaId(mnDx.getReservoirareaId());
			sddx.setLocationId(mnDx.getLocationId());
			sddx.setListNo(listNo);
			sddx.setTray(mnDx.getTray());
			sddx.setProjectId(js.getInteger("projectId"));
			sddx.setYnShift(0);
			sddx.setIssueOrReceipt(1);
			sddx.setMaterialNumberId(mnDx.getId());

			// 新增临时库存详情表记录
			res = dao.addStorageDetail(sddx);
			if (res == 0) {
				throw new Exception("新增临时库存详情记录出错了");
			}

		}

		return true;
	}

	@Override
	public List<CWmsProject> findProjectAll() {
		// TODO Auto-generated method stub
		return dao.findProjectAll();
	}

	@Override
	public List<CMesJlMaterialT> findMaterialAll() {
		// TODO Auto-generated method stub
		return dao.findMaterialAll();
	}

	@Override
	public boolean queryWhetherThereIsInventory() {
		Integer result = dao.queryWhetherThereIsInventory();
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}

}

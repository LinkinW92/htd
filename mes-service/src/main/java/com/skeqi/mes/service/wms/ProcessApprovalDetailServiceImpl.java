package com.skeqi.mes.service.wms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.ProcessApprovalDetailDao;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;

@Service
public class ProcessApprovalDetailServiceImpl implements ProcessApprovalDetailService{

//	public ProcessApprovalDetailServiceImpl() {
//		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
//		mapper = (ProcessApprovalDetailDao) ap.getBean("processApprovalDetailDao");
//	}

	@Autowired
	ProcessApprovalDetailDao dao;

	@Autowired
	ProcessApprovalServiceImpl serviceImpl;

	/**
	 * 查询
	 * @param processApprovalDetail
	 * @return
	 */
	@Override
	public List<ProcessApprovalDetail> findProcessApprovalDetailList(Map<String, Object> map) {
		List<ProcessApprovalDetail> processApprovalDetailList = dao.findProcessApprovalDetailList(map);
		return processApprovalDetailList;
	}

	/**
	 * 通过id查询
	 * @param processApprovalDetailId
	 * @return
	 */
	@Override
	public ProcessApprovalDetail findProcessApprovalDetailById(Integer processApprovalDetailId) {
		ProcessApprovalDetail processApprovalDetail = new ProcessApprovalDetail();
//		processApprovalDetail.setId(processApprovalDetailId);
		Map<String, Object> map = new HashedMap<String, Object>();
		map.put("id", processApprovalDetailId);

		List<ProcessApprovalDetail> processApprovalDetailList = dao.findProcessApprovalDetailList(map);
		if(processApprovalDetailList.size()==1){
			processApprovalDetail = processApprovalDetailList.get(0);
			return processApprovalDetail;
		}else{
			return null;
		}
	}

	/**
	 * 新增
	 * @param processApprovalDetail
	 * @return
	 */
	@Override
	public boolean addProcessApprovalDetail(ProcessApprovalDetail processApprovalDetail) {
		Integer result = dao.addProcessApprovalDetail(processApprovalDetail);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 更新
	 * @param processApprovalDetail
	 * @return
	 */
	@Override
	public boolean updateProcessApprovalDetail(ProcessApprovalDetail processApprovalDetail) {
		Integer result = dao.updateProcessApprovalDetail(processApprovalDetail);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除
	 * @param processApprovalDetailId
	 * @return
	 */
	@Override
	public boolean deleteProcessApprovalDetail(Integer processApprovalDetailId) {

		try {
			//查询出审批详情数据
			ProcessApprovalDetail processApprovalDetail = findProcessApprovalDetailById(processApprovalDetailId);

			Map<String,Object> map = new HashMap<String, Object>();
			map.put("processId", processApprovalDetail.getProcessId());

			//删除审批详情
			dao.deleteProcessApprovalDetail(processApprovalDetailId);

			//查询
			List<ProcessApprovalDetail> list = findProcessApprovalDetailList(map);

			//判断是否还有审批流程详情
			//没有就吧审批流程主表记录一起删除
			if(list.size()>0){
				//更新审批详情的优先级
				dao.updateApprovaDetailsPriorityLevel(processApprovalDetail.getPriorityLevel(), processApprovalDetail.getProcessId(),2);
			}else{
				serviceImpl.deleteProcessApproval(processApprovalDetail.getProcessId());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Integer findMaxPriorityLevel(Integer dmId, Integer roleId, Integer typeId) {
		// TODO Auto-generated method stub
		return dao.findMaxPriorityLevel(dmId, roleId, typeId);
	}

	@Override
	public Integer updateApprovaDetailsPriorityLevel(Integer priorityLevel, Integer processId,Integer str) {
		// TODO Auto-generated method stub
		return dao.updateApprovaDetailsPriorityLevel(priorityLevel, processId,str);
	}

//	@Test
//	public void findProcessApprovalDetailList(){
//		ProcessApprovalDetail processApprovalDetail = new ProcessApprovalDetail();
////		processApprovalDetail.setId(1);
////		processApprovalDetail.setUserId(1);
//
//		List<ProcessApprovalDetail> approvalDetailList = findProcessApprovalDetailList(processApprovalDetail);
//		for (ProcessApprovalDetail processApprovalDetail2 : approvalDetailList) {
//			System.out.println(processApprovalDetail2);
//		}
//	}

	@Test
	public void findProcessApprovalDetailById(){
		Integer processApprovalDetailId = 1;
		ProcessApprovalDetail processApprovalDetail = findProcessApprovalDetailById(processApprovalDetailId);
		System.out.println(processApprovalDetail);
	}

	@Test
	public void addProcessApprovalDetail(){
		ProcessApprovalDetail processApprovalDetail = new ProcessApprovalDetail();
		processApprovalDetail.setUserId(1);
		processApprovalDetail.setPriorityLevel(1);
		processApprovalDetail.setProcessId(1);
		processApprovalDetail.setDis("测试审批流程");

		boolean result = addProcessApprovalDetail(processApprovalDetail);
		System.out.println(result);
	}

	@Test
	public void updateProcessApprovalDetail(){
		ProcessApprovalDetail processApprovalDetail = new ProcessApprovalDetail();
		processApprovalDetail.setId(6);
		processApprovalDetail.setDis("测试审批流程");

		boolean result = updateProcessApprovalDetail(processApprovalDetail);
		System.out.println(result);
	}

	@Test
	public void deleteProcessApprovalDetail(){
		Integer processApprovalDetailId = 6;
		boolean result = deleteProcessApprovalDetail(processApprovalDetailId);
		System.out.println(result);
	}

}

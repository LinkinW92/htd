package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.ApprovalDetailsDao;
import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;

/**
 * @package com.skeqi.mes.service.wms
 * @author yp
 * @date 2020年2月15日
 * 审批详情记录
 */
@Service
public class ApprovalDetailsServiceImpl implements ApprovalDetailsService {

//	public ApprovalDetailsServiceImpl() {
//		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
//		mapper = (ApprovalDetailsDao) ap.getBean("approvalDetailsDao");
//	}

	@Autowired
	ApprovalDetailsDao dao;

	/**
	 * 查询
	 * @param approvalDetails
	 * @return
	 */
	@Override
	public List<CWmsApprovalDetailsT> findApprovalDetails(CWmsApprovalDetailsT approvalDetails) {
		List<CWmsApprovalDetailsT> approvalDetailsList = dao.findApprovalDetails(approvalDetails);
		return approvalDetailsList;
	}

	/**
	 * 通过id查询
	 * @param approvalDetailsId
	 * @return
	 */
	@Override
	public CWmsApprovalDetailsT findApprovalDetailsById(Integer approvalDetailsId) {
		CWmsApprovalDetailsT approvalDetails = new CWmsApprovalDetailsT();
		approvalDetails.setId(approvalDetailsId);

		List<CWmsApprovalDetailsT> approvalDetailsList = dao.findApprovalDetails(approvalDetails);
		if(approvalDetailsList.size()==1){
			approvalDetails = approvalDetailsList.get(0);
			return approvalDetails;
		}else{
			return null;
		}
	}

	/**
	 * 新增
	 * @param approvalDetails
	 * @return
	 */
	@Override
	public boolean addApprovalDetails(CWmsApprovalDetailsT approvalDetails) {
		Integer result = dao.addApprovalDetails(approvalDetails);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 更新
	 * @param approvalDetails
	 * @return
	 */
	@Override
	public boolean updateApprovalDetails(CWmsApprovalDetailsT approvalDetails) {
		Integer result = dao.updateApprovalDetails(approvalDetails);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除
	 * @param ApprovalDetailsId
	 * @return
	 */
	@Override
	public boolean deleteApprovalDetails(Integer ApprovalDetailsId) {
		Integer result = dao.deleteApprovalDetails(ApprovalDetailsId);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 查询是否最后一个人审批
	 * @param listNo
	 * @param priorityLevel
	 * @return
	 */
	@Override
	public List<CWmsApprovalDetailsT> findShiFouZuiHouYiGeShenPiRen(String listNo, Integer priorityLevel) {
		// TODO Auto-generated method stub
		return dao.findShiFouZuiHouYiGeShenPiRen(listNo, priorityLevel);
	}

	/**
	 * 更新审批详表审批结果
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateApprovalResult(Integer id) {
		// TODO Auto-generated method stub
		Integer result = dao.updateApprovalResult(id);
		if(result==1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 更新审批详表ynApproved
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateYnApproved(Integer id) {
		// TODO Auto-generated method stub
		Integer result = dao.updateYnApproved(id);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}


	@Test
	public void findApprovalDetails(){
		CWmsApprovalDetailsT approvalDetails = new CWmsApprovalDetailsT();
		approvalDetails.setApprovalId(2704);
		approvalDetails.setListNo("测试单号");

		List<CWmsApprovalDetailsT> approvalDetailsList = findApprovalDetails(approvalDetails);
		for (CWmsApprovalDetailsT cWmsApprovalDetailsT : approvalDetailsList) {
			System.out.println(cWmsApprovalDetailsT);
		}
	}

	@Test
	public void findApprovalDetailsById(){
		Integer approvalDetailsId = 1;

		CWmsApprovalDetailsT approvalDetails = findApprovalDetailsById(approvalDetailsId);
		System.out.println(approvalDetails);
	}

	@Test
	public void addApprovalDetails(){

		CWmsApprovalDetailsT approvalDetails = new CWmsApprovalDetailsT();
		approvalDetails.setListNo("测试单号");
		approvalDetails.setApprovalResult(0);
		approvalDetails.setUserId(1);
		approvalDetails.setReason("");
		approvalDetails.setApprovalId(1);
		approvalDetails.setPriorityLevel(1);
		approvalDetails.setYnApproved(1);

		boolean result = addApprovalDetails(approvalDetails);
		System.out.println(result);
	}

	@Test
	public void updateApprovalDetails(){
		CWmsApprovalDetailsT approvalDetails = new CWmsApprovalDetailsT();
		approvalDetails.setId(1);
		approvalDetails.setListNo("测试单号");

		boolean result = updateApprovalDetails(approvalDetails);
		System.out.println(result);
	}

	@Test
	public void deleteApprovalDetails(){
		Integer approvalDetailsId = 2704;

		boolean result = deleteApprovalDetails(approvalDetailsId);
		System.out.println(result);
	}

}

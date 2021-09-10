package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.ProcessTypeDao;
import com.skeqi.mes.pojo.wms.ProcessType;

@Service
public class ProcessTypeServiceImpl implements ProcessTypeService {

//	public ProcessTypeServiceImpl() {
//		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
//		mapper = (ProcessTypeDao) ap.getBean("processTypeDao");
//	}

	@Autowired
	ProcessTypeDao dao;

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	@Override
	public List<ProcessType> findProcessTypeList(ProcessType dx) {
		List<ProcessType> list = dao.findProcessTypeList(dx);
		return list;
	}

	/**
	 * 查询所有Id、NAME
	 * @return
	 */
	@Override
	public List<ProcessType> findProcessTypeAll() {
		// TODO Auto-generated method stub
		return dao.findProcessTypeAll();
	}

	/**
	 * 通过id查询
	 * @param dx
	 * @return
	 */
	@Override
	public ProcessType findProcessTypeById(Integer processTypeId) {
		ProcessType dx = new ProcessType();
		dx.setId(processTypeId);
		List<ProcessType> list = dao.findProcessTypeList(dx);
		if(list.size()==1){
			dx = list.get(0);
			return dx;
		}else{
			return null;
		}
	}

	/**
	 * 通过name查询
	 * @param dx
	 * @return
	 */
	@Override
	public ProcessType findProcessTypeByName(String processTypeName) {
		ProcessType dx = new ProcessType();
		dx.setProcessType(processTypeName);
		List<ProcessType> list = dao.findProcessTypeList(dx);
		if(list.size()>=1){
			dx = list.get(0);
			return dx;
		}else{
			return null;
		}
	}

	/**
	 * 新增
	 * @param dx
	 * @return
	 */
	@Override
	public boolean addProcessType(ProcessType dx) {
		Integer result = dao.addProcessType(dx);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 更新
	 * @param dx
	 * @return
	 */
	@Override
	public boolean updateProcessType(ProcessType dx) {
		Integer result = dao.updateProcessType(dx);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除
	 * @param processTypeId
	 * @return
	 */
	@Override
	public boolean deleteProcessType(Integer processTypeId) {
		Integer result = dao.deleteProcessType(processTypeId);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Test
	public void findProcessTypeList(){
		ProcessType dx = new ProcessType();
		dx.setId(1);
		dx.setProcessType("出库审批");

		List<ProcessType> list = findProcessTypeList(dx);
		for (ProcessType processType : list) {
			System.out.println(processType);
		}
	}

	@Test
	public void findProcessTypeById(){
		Integer processTypeId = 1;
		ProcessType dx = findProcessTypeById(processTypeId);
		System.out.println(dx);
	}

	@Test
	public void findProcessTypeByName(){
		String processTypeName = "出库审批";
		ProcessType dx = findProcessTypeByName(processTypeName);
		System.out.println(dx);
	}

	@Test
	public void addProcessType(){
		ProcessType dx = new ProcessType();
		dx.setProcessType("测试审批");
		dx.setDis("测试用的");

		boolean result = addProcessType(dx);
		System.out.println(result);
	}

	@Test
	public void updateProcessType(){
		ProcessType dx = new ProcessType();
		dx.setId(1);
		dx.setDis("测试用的");

		boolean result = updateProcessType(dx);
		System.out.println(result);
	}

	@Test
	public void deleteProcessType(){
		Integer processTypeId = 1;
		boolean result = deleteProcessType(processTypeId);
		System.out.println(result);
	}

}

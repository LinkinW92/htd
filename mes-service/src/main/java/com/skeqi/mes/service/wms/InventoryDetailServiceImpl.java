package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skeqi.mes.mapper.wms.InventoryDetailDao;
import com.skeqi.mes.pojo.wms.InventoryDetailT;

public class InventoryDetailServiceImpl implements InventoryDetailService {

	public InventoryDetailServiceImpl() {
		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (InventoryDetailDao) ap.getBean("inventoryDetailDao");
	}

	InventoryDetailDao dao;

	/**
	 * 查询
	 * @param inventoryDetail
	 * @return
	 */
	@Override
	public List<InventoryDetailT> findInventoryDetail(InventoryDetailT inventoryDetail) {
		List<InventoryDetailT> list = dao.findInventoryDetail(inventoryDetail);
		return list;
	}

	/**
	 * 通过id查询
	 * @param inventoryDetailId
	 * @return
	 */
	@Override
	public InventoryDetailT findInventoryDetailById(Integer inventoryDetailId) {
		InventoryDetailT inventoryDetail = new InventoryDetailT();
		inventoryDetail.setId(inventoryDetailId);

		List<InventoryDetailT> list = dao.findInventoryDetail(inventoryDetail);
		if(list.size()==1){
			inventoryDetail = list.get(0);
			return inventoryDetail;
		}else{
			return null;
		}
	}

	/**
	 * 新增
	 * @param inventoryDetail
	 * @return
	 */
	@Override
	public boolean addInventoryDetail(InventoryDetailT inventoryDetail) {
		Integer result = dao.addInventoryDetail(inventoryDetail);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 更新
	 * @param inventoryDetail
	 * @return
	 */
	@Override
	public boolean updateInventoryDetail(InventoryDetailT inventoryDetail) {
		Integer result = dao.updateInventoryDetail(inventoryDetail);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除
	 * @param inventoryDetailId
	 * @return
	 */
	@Override
	public boolean deleteInventoryDetail(Integer inventoryDetailId) {
		Integer result = dao.deleteInventoryDetail(inventoryDetailId);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Test
	public void findInventoryDetail(){
		InventoryDetailT inventoryDetail = new InventoryDetailT();
		inventoryDetail.setId(1);

		List<InventoryDetailT> list = findInventoryDetail(inventoryDetail);
		for (InventoryDetailT inventoryDetailT : list) {
			System.out.println(inventoryDetailT);
		}
	}

	@Test
	public void findInventoryDetailById(){
		Integer inventoryDetailId = 1;
		InventoryDetailT inventoryDetail = findInventoryDetailById(inventoryDetailId);
		System.out.println(inventoryDetail);
	}

	@Test
	public void addInventoryDetail(){
		InventoryDetailT inventoryDetail = new InventoryDetailT();
		inventoryDetail.setInventoryId(1);
		inventoryDetail.setMaterialId(1);
		inventoryDetail.setInventoryNo(100);
		inventoryDetail.setStockNo(50);
		inventoryDetail.setDifferenceNo(50);
		inventoryDetail.setLocationId(1);
		inventoryDetail.setProjectId(1);

		boolean result = addInventoryDetail(inventoryDetail);
		System.out.println(result);
	}

	@Test
	public void updateInventoryDetail(){
		InventoryDetailT inventoryDetail = new InventoryDetailT();
		inventoryDetail.setId(1);
		inventoryDetail.setInventoryNo(100);

		boolean result = updateInventoryDetail(inventoryDetail);
		System.out.println(result);
	}

	@Test
	public void deleteInventoryDetail(){
		Integer inventoryDetailId = 1;

		boolean result = deleteInventoryDetail(inventoryDetailId);
		System.out.println(result);
	}

}

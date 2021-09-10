package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skeqi.mes.mapper.wms.InventoryDao;
import com.skeqi.mes.pojo.wms.InventoryT;

/**
 * @date 2020年2月24日
 * @author yinp
 * 盘点单据
 */
public class InventoryServiceImpl implements InventoryService {

	public InventoryServiceImpl() {
		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (InventoryDao) ap.getBean("inventoryDao");
	}

	InventoryDao dao;

	/**
	 * 查询
	 * @param inventory
	 * @return
	 */
	@Override
	public List<InventoryT> findInventory(InventoryT inventory) {
		List<InventoryT> list = dao.findInventory(inventory);
		return list;
	}

	/**
	 * 通过id查询
	 * @param inventory
	 * @return
	 */
	@Override
	public InventoryT findInventoryById(Integer inventoryId) {
		InventoryT inventory = new InventoryT();
		inventory.setId(inventoryId);

		List<InventoryT> list = dao.findInventory(inventory);
		if(list.size()==1){
			inventory = list.get(0);
			return inventory;
		}else{
			return null;
		}
	}

	/**
	 * 新增
	 * @param inventory
	 * @return
	 */
	@Override
	public boolean addInventory(InventoryT inventory) {
		Integer result = dao.addInventory(inventory);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 更新
	 * @param inventory
	 * @return
	 */
	@Override
	public boolean updateInventory(InventoryT inventory) {
		Integer result = dao.updateInventory(inventory);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除
	 * @param inventoryId
	 * @return
	 */
	@Override
	public boolean deleteInventory(Integer inventoryId) {
		Integer result = dao.deleteInventory(inventoryId);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Test
	public void findInventory(){
		InventoryT inventory = new InventoryT();
		inventory.setId(1);

		List<InventoryT> list = findInventory(inventory);
		for (InventoryT inventoryT : list) {
			System.out.println(inventoryT);
		}
	}

	@Test
	public void findInventoryById(){
		Integer inventoryId = 1;

		InventoryT inventoryT = findInventoryById(inventoryId);
		System.out.println(inventoryT);
	}

	@Test
	public void addInventory(){
		InventoryT inventory = new InventoryT();
		inventory.setListNo("SKQ123");
		inventory.setUserId(1);
		inventory.setDis("描述");
		inventory.setState(0);

		boolean result = addInventory(inventory);
		System.out.println(result);
	}

	@Test
	public void updateInventory(){
		InventoryT inventory = new InventoryT();
		inventory.setId(1);
		inventory.setListNo("SKQ123");

		boolean result = updateInventory(inventory);
		System.out.println(result);
	}

	@Test
	public void deleteInventory(){
		Integer inventoryId = 1;

		boolean result = deleteInventory(inventoryId);
		System.out.println(result);
	}

}

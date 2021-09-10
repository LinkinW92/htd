package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wms.WmsMaterialDao;
import com.skeqi.mes.pojo.wms.CWmsMaterialT;

@Service
public class WmsMaterialServiceImpl implements WmsMaterialService {

//	public WmsMaterialServiceImpl() {
//		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
//		mapper = (WmsMaterialDao) ap.getBean("wmsMaterialDao");
//	}

	@Autowired
	WmsMaterialDao dao;

	/**
	 * 查询
	 * @param material
	 * @return
	 */
	@Override
	public List<CWmsMaterialT> findMaterialList(CWmsMaterialT material) {
		List<CWmsMaterialT> materialList = dao.findMaterialList(material);
		return materialList;
	}

	/**
	 * 查询所有物料ID、NAME
	 * @return
	 */
	@Override
	public List<CWmsMaterialT> findMaterialAll() {
		// TODO Auto-generated method stub
		return dao.findMaterialAll();
	}

	/**
	 * 通过id查询
	 * @param materialId
	 * @return
	 */
	@Override
	public CWmsMaterialT findMaterialById(Integer materialId) {
		CWmsMaterialT material = new CWmsMaterialT();
		material.setId(materialId);

		List<CWmsMaterialT> list = dao.findMaterialList(material);
		if(list.size()==1){
			material = list.get(0);
			return material;
		}else{
			return null;
		}
	}

	/**
	 * 通过name查询
	 * @param materialName
	 * @return
	 */
	@Override
	public CWmsMaterialT findMaterialByName(String materialName) {
		CWmsMaterialT material = new CWmsMaterialT();
		material.setMaterialName(materialName);

		List<CWmsMaterialT> list = dao.findMaterialList(material);
		if(list.size()>=1){
			material = list.get(0);
			return material;
		}else{
			return null;
		}
	}

	/**
	 * 新增
	 * @param material
	 * @return
	 */
	@Override
	public boolean addMaterial(CWmsMaterialT material) {
		Integer result = dao.addMaterial(material);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 更新
	 * @param material
	 * @return
	 */
	@Override
	public boolean updateMaterial(CWmsMaterialT material) {
		Integer result = dao.updateMaterial(material);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除
	 * @param materialId
	 * @return
	 */
	@Override
	public boolean deleteMaterial(Integer materialId) {
		Integer result = dao.deleteMaterial(materialId);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Test
	public void findMaterialList(){
		CWmsMaterialT material = new CWmsMaterialT();
		material.setId(1);
		material.setMaterialName("PLC");

		List<CWmsMaterialT> list = findMaterialList(material);
		for (CWmsMaterialT cWmsMaterialT : list) {
			System.out.println(cWmsMaterialT);
		}
	}

	@Test
	public void findMaterialById(){
		Integer materialId = 1;

		CWmsMaterialT material = findMaterialById(materialId);
		System.out.println(material);
	}

	@Test
	public void findMaterialByName(){
		String materialName = "PLC";

		CWmsMaterialT material = findMaterialByName(materialName);
		System.out.println(material);
	}

	@Test
	public void addMaterial(){
		CWmsMaterialT material = new CWmsMaterialT(null,null,"100001","100001","测试物料",1,1,1.0,1.0,1.0,1.0,1.0,1,0,"测试型号","##123",100,"测试防伪码",10,"###",90,"note",10);
		boolean result = addMaterial(material);
		System.out.println(result);
	}

	@Test
	public void updateMaterial(){
		CWmsMaterialT material = new CWmsMaterialT();
		material.setId(999);
		material.setMaterialName("PLC");

		boolean result = updateMaterial(material);
		System.out.println(result);
	}

	@Test
	public void deleteMaterial(){
		Integer materialId = 9999;
		boolean result = deleteMaterial(materialId);
		System.out.println(result);
	}


}

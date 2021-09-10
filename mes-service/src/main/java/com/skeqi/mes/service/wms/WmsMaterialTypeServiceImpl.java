package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skeqi.mes.mapper.wms.WmsMaterialTypeDao;
import com.skeqi.mes.pojo.wms.CWmsMaterialTypeT;

public class WmsMaterialTypeServiceImpl implements WmsMaterialTypeService {

	public WmsMaterialTypeServiceImpl() {
		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (WmsMaterialTypeDao) ap.getBean("wmsMaterialTypeDao");
	}

	WmsMaterialTypeDao dao;

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	@Override
	public List<CWmsMaterialTypeT> findMaterialTypeList(CWmsMaterialTypeT dx) {
		List<CWmsMaterialTypeT> list = dao.findMaterialTypeList(dx);
		return list;
	}

	/**
	 * 通过id查询
	 * @param dx
	 * @return
	 */
	@Override
	public CWmsMaterialTypeT findMaterialTypeById(Integer materialTypeId) {
		CWmsMaterialTypeT dx = new CWmsMaterialTypeT();
		dx.setId(materialTypeId);
		List<CWmsMaterialTypeT> list = dao.findMaterialTypeList(dx);
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
	public CWmsMaterialTypeT findMaterialTypeByName(String materialTypeName) {
		CWmsMaterialTypeT dx = new CWmsMaterialTypeT();
		dx.setMtName(materialTypeName);
		List<CWmsMaterialTypeT> list = dao.findMaterialTypeList(dx);
		if(list.size()==1){
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
	public boolean addMaterialType(CWmsMaterialTypeT dx) {
		Integer result = dao.addMaterialType(dx);
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
	public boolean updateMaterialType(CWmsMaterialTypeT dx) {
		// TODO Auto-generated method stub
		Integer result = dao.updateMaterialType(dx);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除
	 * @param materialTypeId
	 * @return
	 */
	@Override
	public boolean deleteMaterialType(Integer materialTypeId) {
		Integer result = dao.deleteMaterialType(materialTypeId);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Test
	public void findMaterialTypeList(){
		CWmsMaterialTypeT dx = new CWmsMaterialTypeT();
		dx.setId(1);
		dx.setMtName("类型");

		List<CWmsMaterialTypeT> list = findMaterialTypeList(dx);
		for (CWmsMaterialTypeT cWmsMaterialTypeT : list) {
			System.out.println(cWmsMaterialTypeT);
		}
	}

	@Test
	public void findMaterialTypeById(){
		Integer materialTypeId = 1;

		CWmsMaterialTypeT dx = findMaterialTypeById(materialTypeId);
		System.out.println(dx);
	}

	@Test
	public void findMaterialTypeByName(){
		String materialTypeName = "类型1";

		CWmsMaterialTypeT dx = findMaterialTypeByName(materialTypeName);
		System.out.println(dx);
	}

	@Test
	public void addMaterialType(){
		CWmsMaterialTypeT dx = new CWmsMaterialTypeT();
		dx.setMtName("类型");
		dx.setMtNo("编号");

		boolean result = addMaterialType(dx);
		System.out.println(result);
	}

	@Test
	public void updateMaterialType(){
		CWmsMaterialTypeT dx = new CWmsMaterialTypeT();
		dx.setId(1);
		dx.setMtNo("类型说明");

		boolean result = updateMaterialType(dx);
		System.out.println(result);
	}

	@Test
	public void deleteMaterialType(){
		Integer materialTypeId = 1;
		boolean result = deleteMaterialType(materialTypeId);
		System.out.println(result);
	}

}

package com.skeqi.mes.service.wms;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skeqi.mes.mapper.wms.MaterialUnitDao;
import com.skeqi.mes.pojo.wms.CWmsMaterialUnitT;

/**
 * @date 2020年3月5日
 * @author yinp
 * @ 物料单位
 */
public class MaterialUnitServiceImpl implements MaterialUnitService {

	public MaterialUnitServiceImpl() {
		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (MaterialUnitDao) ap.getBean("materialUnitDao");
	}

	MaterialUnitDao dao;

	/**
	 * 查询
	 * @param dx
	 * @return
	 */
	@Override
	public List<CWmsMaterialUnitT> findMaterialUnitList(CWmsMaterialUnitT dx) {
		List<CWmsMaterialUnitT> list = dao.findMaterialUnitList(dx);
		return list;
	}

	/**
	 * 通过id查询
	 * @param dx
	 * @return
	 */
	@Override
	public CWmsMaterialUnitT findMaterialUnitById(Integer materialUnitId) {
		CWmsMaterialUnitT dx = new CWmsMaterialUnitT();
		dx.setId(materialUnitId);
		List<CWmsMaterialUnitT> list = dao.findMaterialUnitList(dx);
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
	public CWmsMaterialUnitT findMaterialUnitByName(String materialUnitName) {
		CWmsMaterialUnitT dx = new CWmsMaterialUnitT();
		dx.setUnitName(materialUnitName);
		List<CWmsMaterialUnitT> list = dao.findMaterialUnitList(dx);
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
	public boolean addMaterialUnit(CWmsMaterialUnitT dx) {
		Integer result = dao.addMaterialUnit(dx);
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
	public boolean updateMaterialUnit(CWmsMaterialUnitT dx) {
		Integer result = dao.updateMaterialUnit(dx);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除
	 * @param materialUnitId
	 * @return
	 */
	@Override
	public boolean deleteMaterialUnit(Integer materialUnitId) {
		Integer result = dao.deleteMaterialUnit(materialUnitId);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Test
	public void findMaterialUnitList(){
		CWmsMaterialUnitT dx = new CWmsMaterialUnitT();
		dx.setId(1);
		dx.setUnitName("单位");

		List<CWmsMaterialUnitT> list = findMaterialUnitList(dx);
		for (CWmsMaterialUnitT dx1 : list) {
			System.out.println(dx1);
		}
	}

	@Test
	public void findMaterialUnitById(){
		Integer materialUnitId = 1;

		CWmsMaterialUnitT dx = findMaterialUnitById(materialUnitId);
		System.out.println(dx);
	}

	@Test
	public void findMaterialUnitByName(){
		String materialUnitName = "单位1";

		CWmsMaterialUnitT dx = findMaterialUnitByName(materialUnitName);
		System.out.println(dx);
	}

	@Test
	public void addMaterialUnit(){
		CWmsMaterialUnitT dx = new CWmsMaterialUnitT();
		dx.setUnitName("单位");
		dx.setUnitNo("1001");

		boolean result = addMaterialUnit(dx);
		System.out.println(result);
	}

	@Test
	public void updateMaterialUnit(){
		CWmsMaterialUnitT dx = new CWmsMaterialUnitT();
		dx.setId(1);
		dx.setUnitNo("1002");

		boolean result = updateMaterialUnit(dx);
		System.out.println(result);
	}

	@Test
	public void deleteMaterialUnit(){
		Integer materialUnitId = 1;
		boolean result = deleteMaterialUnit(materialUnitId);
		System.out.println(result);
	}

}

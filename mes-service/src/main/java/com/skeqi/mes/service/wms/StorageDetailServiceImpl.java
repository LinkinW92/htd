package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.StorageDetailDao;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;

/**
 * @package com.skeqi.mes.service.wms
 * @date 2020年2月17日
 * @author Yinp
 * 库存详情
 */
@Service
public class StorageDetailServiceImpl implements StorageDetailService{

//	public StorageDetailServiceImpl() {
//		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
//		mapper = (StorageDetailDao) ap.getBean("storageDetailDao");
//	}

	@Autowired
	StorageDetailDao dao;

	/**
	 * 查询
	 * @param storageDetail
	 * @return
	 */
	@Override
	public List<CWmsStorageDetailT> findStorageDetail(CWmsStorageDetailT storageDetail) {
		List<CWmsStorageDetailT> storageDetailList = dao.findStorageDetail(storageDetail);
		return storageDetailList;
	}

	@Override
	public List<CWmsStorageDetailT> findPStorageDetail(CWmsStorageDetailT storageDetail) {
		// TODO Auto-generated method stub
		return dao.findPStorageDetail(storageDetail);
	}

	/**
	 * 通过id查询
	 * @param storageDetailId
	 * @return
	 */
	@Override
	public CWmsStorageDetailT findStorageDetailById(Integer storageDetailId) {
		CWmsStorageDetailT storageDetail = new CWmsStorageDetailT();
		storageDetail.setId(storageDetailId);

		List<CWmsStorageDetailT> list = dao.findStorageDetail(storageDetail);
		if(list.size()==1){
			storageDetail = list.get(0);
			return storageDetail;
		}else{
			return null;
		}
	}

	/**
	 * 新增
	 * @param storageDetail
	 * @return
	 */
	@Override
	public boolean addStorageDetail(CWmsStorageDetailT storageDetail) {
		Integer result = dao.addStorageDetail(storageDetail);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 新增P表
	 * @param storageDetail
	 * @return
	 */
	@Override
	public boolean addPStorageDetail(CWmsStorageDetailT storageDetail) {
		Integer result = dao.addPStorageDetail(storageDetail);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 更新
	 * @param storageDetail
	 * @return
	 */
	@Override
	public boolean updateStorageDetail(CWmsStorageDetailT storageDetail) {
		Integer result = dao.updateStorageDetail(storageDetail);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 删除
	 * @param storageDetailId
	 * @return
	 */
	@Override
	public boolean deleteStorageDetail(Integer storageDetailId) {
		Integer result = dao.deleteStorageDetail(storageDetailId);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<CWmsStorageDetailT> findStorageDetailList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dao.findStorageDetailList(map);
	}


	@Override
	public List<CWmsProject> findProjectIdName() {
		// TODO Auto-generated method stub
		return dao.findProjectIdName();
	}

	@Override
	public List<CWmsLocationT> findLocationIdName() {
		// TODO Auto-generated method stub
		return dao.findLocationIdName();
	}

	@Test
	public void findStorageDetail(){
		CWmsStorageDetailT storageDetail = new CWmsStorageDetailT();
		storageDetail.setId(5509);
		storageDetail.setMaterialId(1);
		List<CWmsStorageDetailT> list = findStorageDetail(storageDetail);
		for (CWmsStorageDetailT cWmsStorageDetailT : list) {
			System.out.println(cWmsStorageDetailT);
		}
	}

	@Test
	public void findStorageDetailById(){
		Integer storageDetailId = 1;
		CWmsStorageDetailT storageDetail = findStorageDetailById(storageDetailId);
		System.out.println(storageDetail);
	}

	@Test
	public void addStorageDetail(){
		CWmsStorageDetailT detail = new CWmsStorageDetailT();
		detail.setMaterialId(1);
		detail.setMaterialNumber(1);
		detail.setMaterialCode("12345678");
		detail.setAreaId(1);
		detail.setReservoirAreaId(1);
		detail.setLocationId(1);
		detail.setNote("测试备注");
		detail.setListNo("RK12456");
		detail.setProjectId(1);
		detail.setSupplierId(1);
		detail.setTray("SKQ123456");
		detail.setWarehouseId(1);
		detail.setYnShift(0);

		boolean result = addStorageDetail(detail);
		System.out.println(result);
	}

	@Test
	public void updateStorageDetail(){
		CWmsStorageDetailT detail = new CWmsStorageDetailT();
		detail.setId(1);
		detail.setMaterialNumber(2);

		boolean result = updateStorageDetail(detail);
		System.out.println(result);
	}

	@Test
	public void deleteStorageDetail(){
		Integer storageDetailId = 1;

		boolean result = deleteStorageDetail(storageDetailId);
		System.out.println(result);
	}

	@Override
	public List<JSONObject> findBarCode(String listNo, Integer materialId, Integer locationId, Integer projectId) {
		// TODO Auto-generated method stub
		return dao.findBarCode(listNo, materialId, locationId, projectId);
	}

	@Override
	public List<JSONObject> exportExcel(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.exportExcel(json);
	}

}

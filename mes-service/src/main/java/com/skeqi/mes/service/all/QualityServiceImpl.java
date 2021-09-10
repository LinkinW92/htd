package com.skeqi.mes.service.all;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.QualityDAO;
import com.skeqi.mes.pojo.CMesDefectEntryT;
import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesPatrolT;
import com.skeqi.mes.pojo.CMesReturnRepairT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.PTrackingT;
import com.skeqi.mes.pojo.RMesBolt;
import com.skeqi.mes.pojo.RMesKeypart;
import com.skeqi.mes.pojo.RMesLeakage;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.qh.CMesDefectManagerL;

@Service
@Transactional
public class QualityServiceImpl implements QualityService {

	@Autowired
	private QualityDAO dao;

	@Override
	public List<CMesDefectEntryT> findAllDefectEntry(CMesDefectEntryT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllDefectEntry(t);
	}

	@Override
	public CMesDefectEntryT findDefectEntryByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findDefectEntryByid(id);
	}

	@Override
	public Integer addDefectEntry(CMesDefectEntryT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getEmp()==null || t.getEmp()==""){
			throw new ParameterNullException("录入人不能为空",200);
		}else if(t.getSn()==null || t.getSn()==""){
			throw new ParameterNullException("SN不能为空",200);
		}
		return dao.addDefectEntry(t);
	}

	@Override
	public Integer updateDefectEntry(CMesDefectEntryT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getEmp()==null || t.getEmp()==""){
			throw new ParameterNullException("录入人不能为空",200);
		}else if(t.getSn()==null || t.getSn()==""){
			throw new ParameterNullException("SN不能为空",200);
		}
		return dao.updateDefectEntry(t);
	}

	@Override
	public Integer delDefectEntry(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delDefectEntry(id);
	}

	@Override
	public List<CMesPatrolT> findAllPatrol(CMesPatrolT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllPatrol(t);
	}

	@Override
	public CMesPatrolT findPatrolByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findPatrolByid(id);
	}

	@Override
	public Integer addPatrol(CMesPatrolT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getEmp()==null || t.getEmp()==""){
			throw new ParameterNullException("录入人不能为空",200);
		}else if(t.getSn()==null || t.getSn()==""){
			throw new ParameterNullException("SN不能为空",200);
		}
		return dao.addPatrol(t);
	}

	@Override
	public Integer updatePatrol(CMesPatrolT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getEmp()==null || t.getEmp()==""){
			throw new ParameterNullException("录入人不能为空",200);
		}else if(t.getSn()==null || t.getSn()==""){
			throw new ParameterNullException("SN不能为空",200);
		}
		return dao.updatePatrol(t);
	}

	@Override
	public Integer delPatrol(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delPatrol(id);
	}

	@Override
	public List<CMesReturnRepairT> findAllReturnRepair(CMesReturnRepairT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllReturnRepair(t);
	}

	@Override
	public CMesReturnRepairT findReturnRepairByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub'
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findReturnRepairByid(id);
	}

	@Override
	public Integer addReturnRepair(CMesReturnRepairT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addReturnRepair(t);
	}

	@Override
	public Integer updateReturnRepair(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.updateReturnRepair(id);
	}

	@Override
	public Integer delReturnRepair(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delReturnRepair(id);
	}

	@Override
	public List<CMesDefectGradeManagerT> findAllDefectGrade(CMesDefectGradeManagerT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllDefectGrade(t);
	}

	@Override
	public CMesDefectGradeManagerT findDefectGradeByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findDefectGradeByid(id);
	}

	@Override
	public Integer addDefectGrade(CMesDefectGradeManagerT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addDefectGrade(t);
	}

	@Override
	public Integer updateDefectGrade(CMesDefectGradeManagerT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateDefectGrade(t);
	}

	@Override
	public Integer delDefectGrade(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delDefectGrade(id);
	}

	@Override
	public List<CMesDefectManager> findAllDefectManager(CMesDefectManager t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllDefectManager(t);
	}

	@Override
	public CMesDefectManager findDefectManagerByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findDefectManagerByid(id);
	}

	@Override
	public Integer addDefectManager(CMesDefectManager t) throws ServicesException {
		// TODO Auto-generated method stub

		return dao.addDefectManager(t);
	}

	@Override
	public Integer updateDefectManager(CMesDefectManager t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateDefectManager(t);
	}

	@Override
	public Integer delDefectManager(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delDefectManager(id);
	}

	@Override
	public List<CMesDefectResionT> findAllReason(CMesDefectResionT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllReason(t);
	}

	@Override
	public CMesDefectResionT findReasonByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findReasonByid(id);
	}

	@Override
	public Integer addReason(CMesDefectResionT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addReason(t);
	}

	@Override
	public Integer updateReason(CMesDefectResionT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateReason(t);
	}

	@Override
	public Integer delReason(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delReason(id);
	}

	@Override
	public List<CMesDutyTypeManagerT> findAllDutyType(CMesDutyTypeManagerT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllDutyType(t);
	}

	@Override
	public CMesDutyTypeManagerT findDutyTypeByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findDutyTypeByid(id);
	}

	@Override
	public Integer addDutyType(CMesDutyTypeManagerT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addDutyType(t);
	}

	@Override
	public Integer updateDutyType(CMesDutyTypeManagerT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateDutyType(t);
	}

	@Override
	public Integer delDutyType(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delDutyType(id);
	}

	@Override
	public List<CMesDutyManagerT> findAllDutyManager(CMesDutyManagerT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllDutyManager(t);
	}

	@Override
	public CMesDutyManagerT findDutyManagerByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findDutyManagerByid(id);
	}

	@Override
	public Integer addDutyManager(CMesDutyManagerT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addDutyManager(t);
	}

	@Override
	public Integer updateDutyManager(CMesDutyManagerT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateDutyManager(t);
	}

	@Override
	public Integer delDutyManager(Integer id) throws ServicesException {

		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delDutyManager(id);
	}

	@Override
	public List<CMesIqcCheckT> findAllIQC(Map<String, Object> map) throws ServicesException {

		if (map.get("iqc")!=null) {
			//旧mes接口
			CMesIqcCheckT iqc = (CMesIqcCheckT)map.get("iqc");
			return dao.findAllIQC(iqc);
		}else{
			//新接口
			CMesIqcCheckT iqc = new CMesIqcCheckT();

			//非空验证
			if (map.get("factoryNo")!=null&&!"".equals(map.get("factoryNo").toString())) {
				iqc.setFactoryNo(map.get("factoryNo").toString());
			}
			if (map.get("materialName")!=null&&!"".equals(map.get("materialName").toString())) {
				iqc.setMaterialName(map.get("materialName").toString());
			}
			if (map.get("materialVoucher")!=null&&!"".equals(map.get("materialVoucher").toString())) {
				iqc.setMaterialVoucher(map.get("materialVoucher").toString());
			}
			if (map.get("checkBatch")!=null&&!"".equals(map.get("checkBatch").toString())) {
				iqc.setCheckBatch(map.get("checkBatch").toString());
			}
			if (map.get("otigin")!=null&&!"".equals(map.get("otigin").toString())) {
				iqc.setOtigin(map.get("otigin").toString());
			}
			if (map.get("materialNo")!=null&&!"".equals(map.get("materialNo").toString())) {
				iqc.setMaterialNo(map.get("materialNo").toString());
			}
			if (map.get("supplierName")!=null&&!"".equals(map.get("supplierName").toString())) {
				iqc.setSupplierName(map.get("supplierName").toString());
			}
			if (map.get("emp")!=null&&!"".equals(map.get("emp").toString())) {
				iqc.setEmp(map.get("emp").toString());
			}
			if (map.get("checkPerson")!=null&&!"".equals(map.get("checkPerson").toString())) {
				iqc.setCheckPerson(map.get("checkPerson").toString());
			}
			if (map.get("productionHandie")!=null&&!"".equals(map.get("productionHandie").toString())) {
				iqc.setProductionHandie(map.get("productionHandie").toString());
			}
			if (map.get("status")!=null&&!"".equals(map.get("status").toString())) {
				iqc.setStatus(Integer.parseInt(map.get("status").toString()) );
			}

			if(map.get("act_start_time")!=null&&map.get("act_stop_time")!=null&&
					!"".equals(map.get("act_start_time").toString())&&!"".equals(map.get("act_stop_time").toString())) {
				iqc.setStarttime(map.get("act_start_time").toString());
				iqc.setEndtime(map.get("act_stop_time").toString());
			}
			return dao.findAllIQC(iqc);
		}


	}

	@Override
	public CMesIqcCheckT findIQCByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findIQCByid(id);
	}

	@Override
	public Integer addIQC(CMesIqcCheckT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getMaterialName()==null || t.getMaterialName()==""){
			throw new ParameterNullException("物料名称不能为空",200);
		}else if(t.getMaterialNo()==null || t.getMaterialNo()==""){
			throw new ParameterNullException("物料编号不能为空",200);
		}else if(t.getEmp()==null || t.getEmp()==""){
			throw new ParameterNullException("创建人不能为空",200);
		}else if(t.getFactoryNo()==null || t.getFactoryNo()==""){
			throw new ParameterNullException("工厂编号不能为空",200);
		}else if(t.getMaterialVoucher()==null || t.getMaterialVoucher()==""){
			throw new ParameterNullException("物料凭证号不能为空",200);
		}else if(t.getCheckBatch()==null || t.getCheckBatch()==""){
			throw new ParameterNullException("校验批次不能为空",200);
		}else if(t.getOtigin()==null || t.getOtigin()==""){
			throw new ParameterNullException("校验批来源不能为空",200);
		}else if(t.getCheckNum()==null){
			throw new ParameterNullException("校验批数量不能为空",200);
		}else if(t.getCalculateUnit()==null ||t.getCalculateUnit()==""){
			throw new ParameterNullException("计量单位不能为空",200);
		}else if(t.getSupplierName()==null || t.getSupplierName()==""){
			throw new ParameterNullException("供应商名称不能为空",200);
		}else if(t.getCheckPerson()==null || t.getCheckPerson()==""){
			throw new ParameterNullException("校验人不能为空",200);
		}

		return dao.addIQC(t);
	}


	@Override
	/**
	 * 根据id修改IQC校验信息
	 * @Title: updateIQC
	 * @author WangJ
	 * @param @param t
	 * @param @return
	 * @param @throws ServicesException 参数
	 * @return Integer返回类型
	 * @throws
	 */
	public Integer updateIQC(CMesIqcCheckT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getMaterialName()==null || t.getMaterialName()==""){
			throw new ParameterNullException("物料名称不能为空",200);
		}else if(t.getMaterialNo()==null || t.getMaterialNo()==""){
			throw new ParameterNullException("物料编号不能为空",200);
		}else if(t.getEmp()==null || t.getEmp()==""){
			throw new ParameterNullException("创建人不能为空",200);
		}else if(t.getFactoryNo()==null || t.getFactoryNo()==""){
			throw new ParameterNullException("工厂编号不能为空",200);
		}else if(t.getMaterialVoucher()==null || t.getMaterialVoucher()==""){
			throw new ParameterNullException("物料凭证号不能为空",200);
		}else if(t.getCheckBatch()==null || t.getCheckBatch()==""){
			throw new ParameterNullException("校验批次不能为空",200);
		}else if(t.getOtigin()==null || t.getOtigin()==""){
			throw new ParameterNullException("校验批来源不能为空",200);
		}else if(t.getCheckNum()==null){
			throw new ParameterNullException("校验批数量不能为空",200);
		}else if(t.getCalculateUnit()==null ||t.getCalculateUnit()==""){
			throw new ParameterNullException("计量单位不能为空",200);
		}else if(t.getSupplierName()==null || t.getSupplierName()==""){
			throw new ParameterNullException("供应商名称不能为空",200);
		}else if(t.getCheckPerson()==null || t.getCheckPerson()==""){
			throw new ParameterNullException("校验人不能为空",200);
		}
		return dao.updateIQC(t);
	}

	@Override
	public Integer updateStatus(Integer id, String name) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(name==null || name==""){
			throw new ParameterNullException("状态不能为空",200);
		}
		return dao.updateStatus(id, name);
	}

	@Override
	public Integer updateFreeze(Integer id, Integer rid) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(rid==null || rid==0){
			throw new ParameterNullException("参数错误",200);
		}
		return dao.updateFreeze(id, rid);
	}

	@Override
	public List<PMesLeakageT> findPLeakage(String sn) throws ServicesException {
		// TODO Auto-generated method stub
//		if(sn==null || sn==""){
//			throw new ParameterNullException("sn不能为空",200);
//		}
		return dao.findPLeakage(sn);
	}

	@Override
	public List<RMesLeakage> findRLeakage(String sn) throws ServicesException {
		// TODO Auto-generated method stub
//		if(sn==null || sn==""){
//			throw new ParameterNullException("sn不能为空",200);
//		}
		return dao.findRLeakage(sn);
	}

	@Override
	public List<RMesKeypart> findRKeypart(String sn) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findRKeypart(sn);
	}

	@Override
	public List<PMesKeypartT> findPKeypart(String sn) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findPKeypart(sn);
	}

	@Override
	public List<RMesBolt> findRbolt(String sn) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findRbolt(sn);
	}

	@Override
	public List<PMesBoltT> findPBolt(String sn) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findPBolt(sn);
	}

	@Override
	public List<RTrackingT> findRTrack(String sn) throws ServicesException {
		// TODO Auto-generated method stub

		return dao.findRTrack(sn);
	}

	@Override
	public List<PTrackingT> findPTrack(String sn) throws ServicesException {
		// TODO Auto-generated method stub

		return dao.findPTrack(sn);
	}

	@Override
	public Integer updateKeypartR(Integer id, String reasons) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(reasons==null || reasons==""){
			throw new ParameterNullException("原因不能为空",200);
		}
		return dao.updateKeypartR(id, reasons);
	}

	@Override
	public Integer updateKeypartP(Integer id, String reasons) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(reasons==null || reasons==""){
			throw new ParameterNullException("原因不能为空",200);
		}
		return dao.updateKeypartP(id, reasons);
	}

	@Override
	public Integer updateBoltR(Integer id, String reasons) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(reasons==null || reasons==""){
			throw new ParameterNullException("原因不能为空",200);
		}
		return dao.updateBoltR(id, reasons);
	}

	@Override
	public Integer updateBoltP(Integer id, String reasons) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(reasons==null || reasons==""){
			throw new ParameterNullException("原因不能为空",200);
		}
		return dao.updateBoltP(id, reasons);
	}

	@Override
	public Integer updateLeakageR(Integer id, String reasons) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(reasons==null || reasons==""){
			throw new ParameterNullException("原因不能为空",200);
		}
		return dao.updateLeakageR(id, reasons);
	}

	@Override
	public Integer updateLeakageP(Integer id, String reasons) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}else if(reasons==null || reasons==""){
			throw new ParameterNullException("原因不能为空",200);
		}
		return dao.updateBoltP(id, reasons);
	}

	@Override
	public List<CMesDefectManagerL> findAllDefectManagerL() {
		// TODO Auto-generated method stub
		return dao.findAllDefectManagerL();
	}

	@Override
	public void insertPKeypart(Integer id) {
		// TODO Auto-generated method stub
		dao.insertPKeypart(id);
	}

	@Override
	public void insertPBolt(Integer id) {
		// TODO Auto-generated method stub
		dao.insertPBolt(id);
	}

	@Override
	public void insertLeakage(Integer id) {
		// TODO Auto-generated method stub
		dao.insertLeakage(id);
	}

	@Override
	public void insertPKeypartByR(Integer id) {
		// TODO Auto-generated method stub
		dao.insertPKeypartByR(id);
	}

	@Override
	public void insertPBoltByR(Integer id) {
		// TODO Auto-generated method stub
		dao.insertPBoltByR(id);
	}

	@Override
	public void insertLeakageByR(Integer id) {
		// TODO Auto-generated method stub
		dao.insertLeakageByR(id);
	}

	@Override
	public void updateKeyparts(Integer id, String num) {
		// TODO Auto-generated method stub
		dao.updateKeyparts(id, num);
	}

	@Override
	public void updateBolt(Integer id, String a, String t) {
		// TODO Auto-generated method stub
		dao.updateBolt(id, a, t);
	}

	@Override
	public void updateLeakage(Integer id, String leakagePv, String leakageLv) {
		// TODO Auto-generated method stub
		dao.updateLeakage(id, leakagePv, leakageLv);
	}

	@Override
	public void updateKeypartsR(Integer id, String num) {
		// TODO Auto-generated method stub
		dao.updateKeypartsR(id, num);
	}

	@Override
	public void updateBoltR(Integer id, String a, String t) {
		// TODO Auto-generated method stub
		dao.updateBoltsR(id, a, t);
	}

	@Override
	public void updateLeakageR(Integer id, String leakagePv, String leakageLv) {
		// TODO Auto-generated method stub
		dao.updateLeakagesR(id, leakagePv, leakageLv);
	}


}

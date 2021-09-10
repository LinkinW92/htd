package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesBomTDAO;
import com.skeqi.mes.mapper.all.CMesMaterialTDAO;
import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.CMesBomDetailT;
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesLeakageInfoT;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMaterialListDetailT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.CMesMfParametersDetailT;
import com.skeqi.mes.pojo.CMesOtherDataT;
import com.skeqi.mes.pojo.CMesStationT;

@Service
@Transactional
public class CMesBomServiceImpl implements CMesBomService {

	@Autowired
	private CMesBomTDAO dao;

	@Autowired
	private CMesMaterialTDAO mdao;

	@Override
	public List<CMesMaterialListT> findAllMaterialList(CMesMaterialListT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllMaterialList(t);
	}

	@Override
	public CMesMaterialListT findMaterialListByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findMaterialListByid(id);
	}

	@Override
	public Integer addMaterialList(CMesMaterialListT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getListName()==null || t.getListName()==""){
			throw new ParameterNullException("料单编号不能为空",200);
		}else if(t.getListNo()==null || t.getListNo()==""){
			throw new ParameterNullException("料单名称不能为空",200);
		}else if(t.getEffectiveTime()==null){
			throw new ParameterNullException("生效时间不能为空",200);
		}else if(t.getInvalidTime()==null){
			throw new ParameterNullException("失效时间不能为空",200);
		}
		CMesMaterialListT one = new CMesMaterialListT();
		one.setListNo(t.getListNo());
		List<CMesMaterialListT> findAllMaterialList = dao.findAllMaterialListByListNo(one);
		if(findAllMaterialList.size()>0){
			throw new NameRepeatException("BOM编号重复",100);
		}

//		CMesMaterialListT two = new CMesMaterialListT();
//		two.setListNo(t.getListNo());
//		List<CMesMaterialListT> findAllMaterialList2 = mapper.findAllMaterialList(two);
//		if(findAllMaterialList2.size()>0){
//			throw new NameRepeatException("料单编号重复",100);
//		}
		return dao.addMaterialList(t);
	}

	@Override
	public Integer updateMaterialList(CMesMaterialListT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getListName()==null || t.getListName()==""){
			throw new ParameterNullException("料单名称不能为空",200);
		}else if(t.getListNo()==null || t.getListNo()==""){
			throw new ParameterNullException("料单编号不能为空",200);
		}else if(t.getEffectiveTime()==null){
			throw new ParameterNullException("生效时间不能为空",200);
		}else if(t.getInvalidTime()==null){
			throw new ParameterNullException("失效时间不能为空",200);
		}
		CMesMaterialListT findMaterialListByid;
		if(t.getId()==null||t.getId().equals("")){
			findMaterialListByid = new CMesMaterialListT();
		}else {
			findMaterialListByid = dao.findMaterialListByid(t.getId());
		}
/*		if(!findMaterialListByid.getListName().equals(t.getListName())){
			CMesMaterialListT one = new CMesMaterialListT();
			one.setListName(t.getListName());
			List<CMesMaterialListT> findAllMaterialList = mapper.findAllMaterialList(one);
			if(findAllMaterialList.size()>0){
				throw new NameRepeatException("料单名称重复",100);
			}
		}*/

		if(!findMaterialListByid.getListNo().equals(t.getListNo())){
			CMesMaterialListT two = new CMesMaterialListT();
			two.setListNo(t.getListNo());
			List<CMesMaterialListT> findAllMaterialList2 = dao.findAllMaterialListByListNo(two);
			if(findAllMaterialList2.size()>0){
				throw new NameRepeatException("料单编号重复",100);
			}
		}
		return dao.updateMaterialList(t);
	}

	@Override
	public Integer delMaterialList(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delMaterialList(id);
	}

	@Override
	public List<CMesMaterialListDetailT> findMaterialListDetail(CMesMaterialListDetailT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findMaterialListDetail(t);
	}

	@Override
	public CMesMaterialListDetailT findMaterialListDetailByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findMaterialListDetailByid(id);
	}

	@Override
	public Integer addMaterialListDetail(CMesMaterialListDetailT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getMaterialName()==null || t.getMaterialName()==""){
			throw new ParameterNullException("物料名称不能为空",200);
		}
		CMesJlMaterialT m = new CMesJlMaterialT();
		m.setMaterialName(t.getMaterialName());

		List<CMesJlMaterialT> findAllMaterial = mdao.findAllMaterial(m);
		t.setMaterialNo(findAllMaterial.get(0).getBomId());

		return dao.addMaterialListDetail(t);
	}

	@Override
	public Integer updateMaterialListDetail(CMesMaterialListDetailT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getMaterialName()==null || t.getMaterialName()==""){
			throw new ParameterNullException("物料名称不能为空",200);
		}
		CMesJlMaterialT m = new CMesJlMaterialT();
		m.setMaterialName(t.getMaterialName());
		System.out.println("*************************************:"+t.getMaterialName());
		List<CMesJlMaterialT> findAllMaterial = mdao.findAllMaterial(m);
		t.setMaterialNo(findAllMaterial.get(0).getBomId());

		return dao.updateMaterialListDetail(t);
	}

	@Override
	public Integer delMaterialListDetail(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delMaterialListDetail(id);
	}

	@Override
	public List<CMesManufactureParametersT> findAllMFG(CMesManufactureParametersT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllMFG(t);
	}

	@Override
	public CMesManufactureParametersT findMFGByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findMFGByid(id);
	}

	@Override
	public Integer addMFG(CMesManufactureParametersT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getListName()==null || t.getListName()==""){
			throw new ParameterNullException("制造参数编号不能为空",200);
		}else if(t.getListNo()==null || t.getListNo()==""){
			throw new ParameterNullException("制造参数名称不能为空",200);
		}

		CMesManufactureParametersT one = new CMesManufactureParametersT();
		one.setListName(t.getListName());
		List<CMesManufactureParametersT> findAllMFG = dao.findAllMFG(one);
		if(findAllMFG.size()>0){
			throw new NameRepeatException("制造参数名称重复",100);
		}

		CMesManufactureParametersT two = new CMesManufactureParametersT();
		two.setListNo(t.getListNo());
		List<CMesManufactureParametersT> findAllMFG2 = dao.findAllMFG(two);
		if(findAllMFG2.size()>0){
			throw new NameRepeatException("制造参数编号重复",100);
		}
		return dao.addMFG(t);
	}

	@Override
	public Integer updateMFG(CMesManufactureParametersT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getListName()==null || t.getListName()==""){
			throw new ParameterNullException("制造参数编号不能为空",200);
		}else if(t.getListNo()==null || t.getListNo()==""){
			throw new ParameterNullException("制造参数名称不能为空",200);
		}
		CMesManufactureParametersT findMFGByid = dao.findMFGByid(t.getId());
		if(!findMFGByid.getListName().equals(t.getListName())){
			CMesManufactureParametersT one = new CMesManufactureParametersT();
			one.setListName(t.getListName());
			List<CMesManufactureParametersT> findAllMFG = dao.findAllMFG(one);
			if(findAllMFG.size()>0){
				throw new NameRepeatException("制造参数名称重复",100);
			}

		}

		if(!findMFGByid.getListNo().equals(t.getListNo())){
			CMesManufactureParametersT two = new CMesManufactureParametersT();
			two.setListNo(t.getListNo());
			List<CMesManufactureParametersT> findAllMFG2 = dao.findAllMFG(two);
			if(findAllMFG2.size()>0){
				throw new NameRepeatException("制造参数编号重复",100);
			}
		}
		return dao.updateMFG(t);
	}

	@Override
	public Integer delMFG(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delMFG(id);
	}

	@Override
	public List<CMesMfParametersDetailT> findAllMFGDetail(CMesMfParametersDetailT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllMFGDetail(t);
	}

	@Override
	public CMesMfParametersDetailT findMFGDetailByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findMFGDetailByid(id);
	}

	@Override
	public Integer addMFGDetail(CMesMfParametersDetailT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addMFGDetail(t);
	}

	@Override
	public Integer updateMFGDetail(CMesMfParametersDetailT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateMFGDetail(t);
	}

	@Override
	public Integer delMFGDetail(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delMFGDetail(id);
	}

	@Override
	public List<CMesBomT> findAllBom(CMesBomT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllBom(t);
	}

	@Override
	public CMesBomT findBomByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findBomByid(id);
	}

	@Override
	public Integer addBom(CMesBomT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getBomName()==null || t.getBomName()==""){
			throw new ParameterNullException("bom名称不能为空",200);
		}
		List<CMesBomT> findAllBom = dao.findAllBom(t);
		if(findAllBom.size()>0){
			throw new NameRepeatException("bom名称重复",100);
		}

		return dao.addBom(t);
	}

	@Override
	public Integer updateBom(CMesBomT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getBomName()==null || t.getBomName()==""){
			throw new ParameterNullException("bom名称不能为空",200);
		}
		CMesBomT findBomByid = dao.findBomByid(t.getId());
		if(!findBomByid.getBomName().equals(t.getBomName())){
			CMesBomT one = new CMesBomT();
			one.setBomName(t.getBomName());
			List<CMesBomT> findAllBom = dao.findAllBom(one);
			if(findAllBom.size()>0){
				throw new NameRepeatException("bom名称重复",100);
			}
		}
		return dao.updateBom(t);
	}

	@Override
	public Integer delBom(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delBom(id);
	}

	@Override
	public List<CMesMaterialMsgT> findAllMaterialMsg(CMesMaterialMsgT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllMaterialMsg(t);
	}

	@Override
	public CMesMaterialMsgT findMaterialMsgByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findMaterialMsgByid(id);
	}

	@Override
	public Integer addMaterialMsg(CMesMaterialMsgT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getMaterialName()==null || t.getMaterialName()==""){
			throw new ParameterNullException("物料名称不能为空",200);
		}
		return dao.addMaterialMsg(t);
	}

	@Override
	public Integer updateMaterialMsg(CMesMaterialMsgT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getMaterialName()==null || t.getMaterialName()==""){
			throw new ParameterNullException("物料名称不能为空",200);
		}
		return dao.updateMaterialMsg(t);
	}

	@Override
	public Integer delMaterialMsg(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delMaterialMsg(id);
	}

	@Override
	public List<CMesBoltInfomationT> findAllBolt(CMesBoltInfomationT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllBolt(t);
	}

	@Override
	public CMesBoltInfomationT findBoltByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findBoltByid(id);
	}

	@Override
	public Integer addBolt(CMesBoltInfomationT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getBoltName()==null || t.getBoltName()==""){
			throw new ParameterNullException("螺栓不能为空",200);
		}
		return dao.addBolt(t);
	}

	@Override
	public Integer updateBolt(CMesBoltInfomationT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getBoltName()==null || t.getBoltName()==""){
			throw new ParameterNullException("螺栓不能为空",200);
		}
		return dao.updateBolt(t);
	}

	@Override
	public Integer delBolt(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delBolt(id);
	}

	@Override
	public List<CMesLeakageInfoT> findAllLeakage(CMesLeakageInfoT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllLeakage(t);
	}

	@Override
	public CMesLeakageInfoT findLeakageByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findLeakageByid(id);
	}

	@Override
	public Integer addLeakage(CMesLeakageInfoT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getLeakageName()==null || t.getLeakageName()==""){
			throw new ParameterNullException("气密性名称不能为空",200);
		}
		return dao.addLeakage(t);
	}

	@Override
	public Integer updateLeakage(CMesLeakageInfoT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getLeakageName()==null || t.getLeakageName()==""){
			throw new ParameterNullException("气密性名称不能为空",200);
		}
		return dao.updateLeakage(t);
	}

	@Override
	public Integer delLeakage(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delLeakage(id);
	}

	@Override
	public List<CMesOtherDataT> findAllOther(CMesOtherDataT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllOther(t);
	}

	@Override
	public CMesOtherDataT findOtherByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findOtherByid(id);
	}

	@Override
	public Integer addOther(CMesOtherDataT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.addOther(t);
	}

	@Override
	public Integer updateOther(CMesOtherDataT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateOther(t);
	}

	@Override
	public Integer delOther(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delOther(id);
	}

	@Override
	public List<CMesBomDetailT> findAllBomDetail(CMesBomDetailT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllBomDetail(t);
	}

	@Override
	public CMesBomDetailT findBomDetailByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findBomDetailByid(id);
	}

	@Override
	public Integer addBomDetail(CMesBomDetailT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getBomId()==null){
			throw new ParameterNullException("bom名称不能为空",200);
		}
		return dao.addBomDetail(t);
	}

	@Override
	public Integer updateBomDetail(CMesBomDetailT t) throws ServicesException {
		// TODO Auto-generated method stub
		if(t.getBomId()==null){
			throw new ParameterNullException("bom名称不能为空",200);
		}
		return dao.updateBomDetail(t);
	}

	@Override
	public Integer delBomDetail(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delBomDetail(id);
	}

	@Override
	public Integer addMaterialListDetailTwo(CMesMaterialListDetailT t) throws Exception {
		// TODO Auto-generated method stub
		//图号不能重复
		Integer integer = dao.findMaterialListDetailByFigureNo(t.getFigureNo());
		if (integer>0){
			throw new Exception("图号已存在");
		}
		return dao.addMaterialListDetail(t);
	}

	@Override
	public Integer updateMaterialListDetailTwo(CMesMaterialListDetailT t) throws Exception {
		// TODO Auto-generated method stub
//		//图号不能重复
//		Integer integer = mapper.findMaterialListDetailByFigureNo(t.getFigureNo());
//		if (integer>0){
//			throw new Exception("图号已存在");
//		}
		return dao.updateMaterialListDetail(t);
	}

	@Override
	public List<CMesStationT> findStationById(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findStationById(id);
	}

	/**
	 * 按产品型号查询Bom料单
	 * @param material
	 * @return
	 */
	@Override
	public List<CMesMaterialListT> findBOMMaterialList(CMesMaterialListT material) {
		return dao.findBOMMaterialList(material);
	}

	@Override
	public List<CMesMaterialListT> findBOMDetailByMaterialCode(CMesMaterialListDetailT material) {
		return dao.findBOMDetailByMaterialCode(material);
	}

	/**
	 * 根据id或者根据bom编号查询BOM
	 * @param material
	 * @return
	 */
	@Override
	public List<CMesMaterialListT> findMaterialByIdAndListNo(CMesMaterialListT material) {
		return dao.findMaterialByIdAndListNo(material);
	}

	@Override
	public List<CMesMaterialListDetailT> findMaterialListDetailByCode(String materialCode) {
		return dao.findMaterialListDetailByCode(materialCode);
	}

	@Override
	public List<CMesMaterialListDetailT> findMaterialListDetailByLike(CMesMaterialListDetailT materialListDetail) {
		return dao.findMaterialListDetailByLike(materialListDetail);
	}

	@Override
	public CMesMaterialListT findAllBomIdAndListName(Integer id, String listName) {
		return dao.findAllBomIdAndListName(id,listName);
	}

	@Override
	public List<CMesMaterialListT> findAllMaterialListByListNo(CMesMaterialListT one) {
		return dao.findAllMaterialListByListNo(one);
	}

	@Override
	public CMesMaterialListDetailT findMaterialListDetailByfigureNo(Integer id,String figureNo) throws ServicesException {
		return dao.findMaterialListDetailByfigureNo(id,figureNo);
	}
}

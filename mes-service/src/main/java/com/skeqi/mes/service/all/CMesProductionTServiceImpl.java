package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesProductionTDAO;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesProductionT;

@Service
@Transactional
public class CMesProductionTServiceImpl implements CMesProductionTService {

	@Autowired
	private CMesProductionTDAO dao;

	@Override
	public List<CMesProductionT> findAllProduction(CMesProductionT pro)  throws ServicesException{
		// TODO Auto-generated method stub
		return dao.findAllProduction(pro);
	}

	@Override
	public CMesProductionT findProductionByid(Integer id)  throws ServicesException{
		// TODO Auto-generated method stub

		return dao.findProductionByid(id);
	}

	@Override
	public Integer addProduction(CMesProductionT pro)  throws ServicesException{
		// TODO Auto-generated method stub
		if(pro.getProductionName()==null || pro.getProductionName()==""){
			throw new ParameterNullException("产品名称不能为空",200);
		}else if(pro.getProductionVr()==null || pro.getProductionVr()==""){
			throw new ParameterNullException("校验规则不能为空",200);
		}

		List<CMesProductionT> findProductionByName = dao.findProductionByName(pro.getProductionName());
		if(findProductionByName.size()>0){
			throw new NameRepeatException("产品名称重复",100);
		}
		return dao.addProduction(pro);
	}

	@Override
	public Integer updateProduction(CMesProductionT pro)  throws ServicesException{
		// TODO Auto-generated method stub
		if(pro.getProductionName()==null || pro.getProductionName()==""){
			throw new ParameterNullException("产品名称不能为空",200);
		}else if(pro.getProductionVr()==null || pro.getProductionVr()==""){
			throw new ParameterNullException("校验规则不能为空",200);
		}

		CMesProductionT findProductionByid = dao.findProductionByid(pro.getId());  //查询原name
		if(!findProductionByid.getProductionName().equals(pro.getProductionName())){
			List<CMesProductionT> findProductionByName = dao.findProductionByName(pro.getProductionName());  //查询新的name是否重复
			if(findProductionByName.size()>0){
				throw new NameRepeatException("产品名称重复",100);
			}
		}
		return dao.updateProduction(pro);
	}

	@Override
	public Integer delProduction(Integer id)  throws ServicesException{
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delProduction(id);
	}

	@Override
	public List<CMesLabelManagerT> findAllLable() throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllLable();
	}

	@Override
	public List<CMesProductionT> findAllProductionL() {
		// TODO Auto-generated method stub
		return dao.findAllProductionL();
	}

	@Override
	public Integer addProductionL(CMesProductionT pro) throws Exception {
//		if(pro.getProductionName()==null || pro.getProductionName()==""){
//			throw new ParameterNullException("产品名称不能为空",200);
//		}else if(pro.getProductionVr()==null || pro.getProductionVr()==""){
//			throw new ParameterNullException("校验规则不能为空",200);
//		}
//
//	    CMesProductionT findProductionByid = mapper.findProductionByid(pro.getId());  //查询原name
//		if(!findProductionByid.getProductionName().equals(pro.getProductionName())){
//			List<CMesProductionT> findProductionByName = mapper.findProductionByName(pro.getProductionName());  //查询新的name是否重复
//			if(findProductionByName.size()>0){
//				throw new NameRepeatException("产品名称重复",100);
//			}
//		}
		List<CMesProductionT> name = dao.findProductionByNameL(pro.getProductionName(),null);
		if (name.size()>0) {
			throw new NameRepeatException("产品名称重复",100);
		}
		return dao.addProductionL(pro);
	}

	@Override
	public Integer delProductionL(Integer id) {
		// TODO Auto-generated method stub
		return dao.delProduction(id);
	}

	@Override
	public Integer updateProductionL(CMesProductionT cMesProductionT) throws Exception {
		// TODO Auto-generated method stub
		System.err.println(cMesProductionT.getProductionTrademark()+"===="+cMesProductionT.getId());
		String productionName = cMesProductionT.getProductionName();
		Integer id = cMesProductionT.getId();
		List<CMesProductionT> name = dao.findProductionByNameL(productionName,id);
		if (name.size()>0) {
			throw new NameRepeatException("产品名称重复",100);
		}

		return dao.updateProduction(cMesProductionT);
	}

	@Override
	public CMesProductionT findProductionByidL(Integer id,String productionType) {
		// TODO Auto-generated method stub
		return dao.findProductionByidL(id,productionType);
	}

	@Override
	public List<CMesProductionT> findProductTypeList() {
		return dao.findAllProductionL();
	}

	@Override
	public List<CMesProductionT> findProductionByCode(CMesProductionT cMesProductionT) {
		return dao.findProductionByCode(cMesProductionT);
	}

	@Override
	public List<CMesProductionT> findProductionByNameAndId(String name, Integer id) {
		return dao.findProductionByNameAndId(name,id);
	}
}

package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesCheckoutListT;
import com.skeqi.mes.pojo.CMesCheckoutMethodT;
import com.skeqi.mes.pojo.CMesProductionT;

public interface CheckoutlistService {

	List<CMesCheckoutListT> findAll(Map<String,Object> map);

	void insertcheckout(CMesCheckoutListT list);

	void updatecheckout(CMesCheckoutListT list);

	void deletecheckout(String id);

	CMesCheckoutListT findByid(String id);

	List<CMesProductionT> findPro();

	Integer findOrdernumber(String ordernumber,String pid);

	String findCode(String id);

	List<CMesCheckoutMethodT> findMethod();

	Integer findMethodId(String mid,String pid);
}

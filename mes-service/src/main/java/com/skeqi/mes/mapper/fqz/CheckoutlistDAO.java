package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesCheckoutListT;
import com.skeqi.mes.pojo.CMesCheckoutMethodT;
import com.skeqi.mes.pojo.CMesProductionT;

public interface CheckoutlistDAO {

	List<CMesCheckoutListT> findAll(Map<String,Object> map);

	void insertcheckout(CMesCheckoutListT list);

	void updatecheckout(CMesCheckoutListT list);

	void deletecheckout(String id);

	CMesCheckoutListT findByid(String id);

	List<CMesProductionT> findPro();

	Integer findOrdernumber(@Param("ordernumber")String ordernumber,@Param("pid")String pid);

	String findCode(String id);

	List<CMesCheckoutMethodT> findMethod();

	//查询该产品的该类型是否重复
	Integer findMethodId(@Param("mid")String mid,@Param("pid")String pid);
}

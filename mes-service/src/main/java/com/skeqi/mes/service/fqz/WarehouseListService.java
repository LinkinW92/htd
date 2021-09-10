package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesCheckoutEnterT;
import com.skeqi.mes.pojo.CMesWarehouseListT;

public interface WarehouseListService {

	public List<CMesWarehouseListT> findAll(Map<String,Object> map);

	public List<CMesCheckoutEnterT> listEnter(String sn);
}

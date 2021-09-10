package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesCheckoutEnterT;
import com.skeqi.mes.pojo.CMesWarehouseListT;

public interface WarehouseListDAO {

	public List<CMesWarehouseListT> findAll(Map<String,Object> map);

	public List<CMesCheckoutEnterT> listEnter(@Param("sn")String sn);
}

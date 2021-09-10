package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SupplierManageService {


	public List<Map<String,Object>> showAllSupplierInfos();


	public Integer addSupplier( String supplier);


	public Integer updateSupplier(String supplier,String id);


	public Integer delSupplier(Integer id);

}

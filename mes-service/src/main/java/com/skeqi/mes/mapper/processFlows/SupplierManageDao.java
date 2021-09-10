package com.skeqi.mes.mapper.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@MapperScan
@Component("SupplierManageDao")
public interface SupplierManageDao {

	@Select("select * from c_mes_process_supplier_t ")
	public List<Map<String,Object>> showAllSupplierInfos();

	@Insert("insert into c_mes_process_supplier_t(SUPPLIER) values(#{supplier})")
	public Integer addSupplier(@Param("supplier") String supplier);

	@Update("update c_mes_process_supplier_t set SUPPLIER=#{supplier} where ID=#{id}")
	public Integer updateSupplier(@Param("supplier")String supplier,@Param("id")String id);

	@Delete("delete from c_mes_process_supplier_t where ID=#{id}")
	public Integer delSupplier(@Param("id")Integer id);


}

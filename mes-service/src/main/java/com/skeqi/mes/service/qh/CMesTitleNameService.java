package com.skeqi.mes.service.qh;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CMesTitleNameService {

	@Select("select name from c_mes_title_name_t where id=1")
	public String findTitleName();

	@Update("update c_mes_title_name_t set name=#{name},dt=now() where id=1 ")
	public void updateTitleName(@Param("name")String name);
}

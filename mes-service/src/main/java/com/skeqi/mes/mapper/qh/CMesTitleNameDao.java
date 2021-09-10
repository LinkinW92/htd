package com.skeqi.mes.mapper.qh;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface CMesTitleNameDao {

	//查询当前登录页标题
	@Select("select name from c_mes_title_name_t where id=1")
	public String findTitleName();

	//修改标题
	@Update("update c_mes_title_name_t set name=#{name},dt=now() where id=1 ")
	public void updateTitleName(@Param("name")String name);
}

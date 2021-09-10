package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.project.CMesFaultTypeT;

@Component
@MapperScan
public interface CMesFaultTypeDAO {

	@Select("<script>" +
			"select * from c_mes_fault_type_t where 1=1"+
			  "<if test=\"name!='' and name!=null \"> and TYPE_NAME=#{name} </if>"
			  + "</script>")
	public List<CMesFaultTypeT> findAllFault(@Param("name")String name);


	@Insert("insert into c_mes_fault_type_t(TYPE_NAME,NOTE,DT) values(#{name},#{note},now())")
	public Integer addFault(@Param("name")String name,@Param("note")String note);

	@Update("update c_mes_fault_type_t set TYPE_NAME=#{name},NOTE=#{note} where ID=#{id} ")
	public Integer updateFault(@Param("name")String name,@Param("note")String note,@Param("id")Integer id);

	@Delete("delete from c_mes_fault_type_t where ID=#{id}")
	public Integer delFault(@Param("id")Integer id);
}

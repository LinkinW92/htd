package com.skeqi.mes.mapper.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * CRM日志
 * @author Huangzs
 *
 */
@Component("CRMLogDao")
@MapperScan
public interface CRMLogDao {

	@Insert("insert into c_crm_log_t(DT,USER,MENU_NAME) values(now(),#{user},#{menuName})")
	public Integer addCRMLogInfo(@Param("user")String user,@Param("menuName")String menuName);

	@Select("select * from c_mes_user_t where USER_NAME=#{userName}")
	public List<Map<String,Object>> showUser(@Param("userName")String userName);
}

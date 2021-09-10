package com.skeqi.mes.mapper.all;

import org.apache.ibatis.annotations.Insert;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.api.CMesWebApiLogs;

/**
 * @date 2020年1月16日
 * @author yinp
 *
 */
@Component
@MapperScan
public interface CMesWebApiLogsDao {

	@Insert("INSERT INTO C_MES_WEB_API_LOGS(API_NAME,CALL_TIME,PARAMETER,RETURN_RESULT,RETURN_TIME,SN)"
			+ "VALUES(#{apiName},#{callTime},#{parameter},#{returnResult},#{returnTime},#{sn})")
	public void add(CMesWebApiLogs dx);

}

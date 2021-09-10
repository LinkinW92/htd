package com.skeqi.mes.mapper.chenj.srm;

import org.apache.ibatis.annotations.Param;

/**
 * SRM日志
 * @author chenj
 *
 */
public interface SRMLogMapper {

	Integer addSRMLogInfo(@Param("user") String user, @Param("menuName") String menuName);
}

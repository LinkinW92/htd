package com.skeqi.mes.service.qh;

import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.qh.CMesJounralT;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/26 11:15
 */
public interface CMesJounralService {

    List<CMesWebApiLogs> findAllJounral(@Param("startDt")String startDt, @Param("endDt")String endDt);

    void addJounral(@Param("name")String name,@Param("desc")String desc);
}

package com.skeqi.mes.mapper.qh;

import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.qh.CMesJounralT;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/26 11:09
 */
public interface CMesJounralDao {

    List<CMesWebApiLogs> findAllJounral(@Param("sn")String sn, @Param("types")String types);

    void addJounral(@Param("name")String name,@Param("desc")String desc);
}

package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/8 16:11
 * @Description TODO
 */
public interface WorkInProcessDao {

    List<Map> data(@Param("groupList")List groupList, @Param("whereList") Map whereList, @Param("start") String start, @Param("end") String end);

}

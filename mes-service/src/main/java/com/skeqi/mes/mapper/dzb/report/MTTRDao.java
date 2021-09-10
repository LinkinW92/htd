package com.skeqi.mes.mapper.dzb.report;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/3/26 15:12
 * @Description TODO
 */
public interface MTTRDao {

    List<Map> listEqu();
    List<Map> listAndon(@Param("equId")Integer equId, @Param("start")Date start,@Param("end")Date end);
    List<Map> listAndon2(@Param("equId")Integer equId, @Param("start")Date start,@Param("end")Date end);

    Integer getmaxid();
    Integer getmaxid2();
    Integer getmaxid3();
    void udpate(@Param("date")Date date,@Param("maxID")Integer maxId,@Param("maxID2")Integer maxId2,@Param("maxID3")Integer maxId3);
}

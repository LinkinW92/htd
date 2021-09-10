package com.skeqi.mes.mapper.dzb.monitor;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/4/22 15:29
 * @Description TODO
 */
public interface WholeLineMonDao {

    //region 每日生产
    //每天下线的
    List<Map> listOfflineCount(@Param("start")String start,@Param("end")String end);
    //每天上线的p表
    List<Map> listOnlineCount(@Param("start")String start,@Param("end")String end);
    //每天上线的r表
    List<Map> listOnlineCount2(@Param("start")String start,@Param("end")String end);
    //NG数量
    List<Map> listNgCount(@Param("start")String start,@Param("end")String end);
    //endregion

    List<Map> listOrder();

}

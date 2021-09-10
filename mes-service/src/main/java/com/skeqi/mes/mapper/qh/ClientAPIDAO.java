package com.skeqi.mes.mapper.qh;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/21 15:45
 */
public interface ClientAPIDAO {

    //用户密码是否正确
    Integer UserLogin(@Param("userName")String userName,@Param("password")String password);

    //查询此用户操作的工位
    Map<String,Object> findUserStation(@Param("userName")String userName);

    //根据工位id查询工位名称
    String findStationName(@Param("id")String id);
}

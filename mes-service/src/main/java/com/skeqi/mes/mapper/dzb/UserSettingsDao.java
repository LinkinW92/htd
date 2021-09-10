package com.skeqi.mes.mapper.dzb;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/28 10:39
 * @Description TODO
 */
public interface UserSettingsDao {
    //根据id查询用户信息
    Map getUserById(@Param("id")Integer id);

    //根据id查询用户信息
    void updateUser(Map user);
}

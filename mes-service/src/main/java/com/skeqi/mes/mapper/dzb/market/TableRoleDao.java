package com.skeqi.mes.mapper.dzb.market;

import org.apache.ibatis.annotations.Param;

/**
 * @Created by DZB
 * @Date 2021/5/19 15:04
 * @Description TODO
 */
public interface TableRoleDao {

    //批量添加所有人的权限
    void batchSaveTableRole(@Param("colId")Integer colId);

    //删除列的权限
    void deleteTableRole(@Param("colId")Integer colId);

}

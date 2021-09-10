package com.skeqi.mes.mapper.dzb.market;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/19 14:31
 * @Description TODO
 */
public interface TableDao {

    //表信息
    List<Map> listTable();

    //根据id查询表格信息
    Map getTalbeByTableId(@Param("tableId") Integer tableId);

    void updateTable(@Param("tableId")Integer tableId,@Param("maxId")Integer maxId);
}

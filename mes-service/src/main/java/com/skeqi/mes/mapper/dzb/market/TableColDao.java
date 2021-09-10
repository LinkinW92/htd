package com.skeqi.mes.mapper.dzb.market;

import com.skeqi.mes.controller.dzb.reqobj.TableColParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/19 14:48
 * @Description TODO
 */
public interface TableColDao {

   //查询所有列信息
   List<Map> listTableCol(@Param("tableId")Integer tableId, @Param("colName")String colName);

   //执行sql：添加列。
   void exceSql(@Param("sql") String sql);

   //添加字段表数据
   int saveTableCol(Map tableCol);

   //删除列
   Integer deleteTablcCol(@Param("id") Integer id);


   //修改列
   Integer updateTablcCol(TableColParam tableColParam);

   //查询列名和表名
   Map getTableCol(@Param("colId")Integer colId);
}

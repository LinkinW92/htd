package com.skeqi.mes.mapper.dzb.market;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/6 8:42
 * @Description TODO
 */
public interface MkDao {

    //region mk_table表
    //查询表格现最大的列id
    Integer getTableColMaxId(@Param("tableId") Integer tableId);

    //根据id查询表格信息
    Map getMkTalbe(@Param("tableId") Integer tableId);

    //根据id查询表格的 数据库表名
    String getMkTableName(@Param("tableId") Integer tableId);

    List<String> getMkTableNameElse(@Param("tableId") Integer tableId);

    //表格最大列的id自增
    void colMaxIdIncreas(@Param("tableId") Integer tableId);

    //表格的信息
    List<Map> listMkTable();
    //endregion

    //region mk_table_col
    //执行sql：添加列。
    void exceSql(@Param("sql") String sql);
    //根据id查询 列的 数据库名
    String getMkTableColName(@Param("colId") Integer colId);
    //根据id查询 表的 所有列
    List<String> listMkTableColName(@Param("tableId") Integer tableId);
    //列的信息
    List<Map> listMkTableCol(@Param("tableId") Integer tableId, @Param("roleId") Integer roleId);
    //查询id集合的所有列和表
    List<Map> listTableIdAndCol(@Param("cols")String cols);
    //endregion

    //添加字段表数据
    void saveMkTableCol(@Param("tableId") Integer tableId, @Param("colName") String colName, @Param("colType") Integer colType, @Param("showName") String showName);

    //表信息
    List<Map> listTable();

    //region 具体表数据
    //备份当前数据（用于修改数据记录原始数据）
    Integer backUpMkTableData(@Param("pjNO") String pjNO, @Param("table") String table, @Param("listCol") List<String> listCol);


    void initMkTableData(@Param("pjNo") String pjNO, @Param("table") String table);

    //查询所有数据
    List<Map> data(@Param("sql") String sql);
    //批量导入数据
    void batchSaveMkTableData(@Param("tableName")String tableName,@Param("cols")List cols,@Param("data")List data);
    //修改当前数据
    Integer updateMkTableData(@Param("pjNo") String pjNO, @Param("table") String table, @Param("col") String col, @Param("value") Object value);
    //添加数据
    void saveMkTableData(@Param("pjNo") String pjNO, @Param("table") String table, @Param("col") String col, @Param("value") Object value);
    //删除数据
    void insertDelete(@Param("pjNo")String pjNo,@Param("table") String table);
    //删除数据,假删除，改为0
    void deleteData(@Param("pjNo")String pjNo,@Param("table") String table);

    //endregion

    //region 权限数据
    //获取角色信息
    List<Map> listRole();

    //获取资源信息
    List<Map> listCol();

    //获取角色资源权限
    List<Map> listRoleAuth();

    void saveRoleCol(@Param("roleId") Integer roleId, @Param("colId") Integer colId, @Param("authId") Integer authId);

    void deleteRoleCol(@Param("roleId") Integer roleId, @Param("colId") Integer colId, @Param("authId") Integer authId);
    //endregion
}

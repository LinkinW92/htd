package com.skeqi.mes.service.dzb.market;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.controller.dzb.reqobj.TableColParam;
import com.skeqi.mes.mapper.dzb.market.MkDao;
import com.skeqi.mes.mapper.dzb.market.TableColDao;
import com.skeqi.mes.mapper.dzb.market.TableDao;
import com.skeqi.mes.mapper.dzb.market.TableRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/5/19 14:27
 * @Description TODO
 */
@Service("TableColService")
public class TableColService {
    public final String intS = " int ";
    public final String varchar = " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL ";
    public final String datetime = "  datetime  ";
    public final String doubleS = " double ";
    public final String date = " date ";
    public final String time = " time ";

    @SuppressWarnings("all")
    @Autowired
    public TableColDao dao;

    @SuppressWarnings("all")
    @Autowired
    public TableDao tableDao;


    @SuppressWarnings("all")
    @Autowired
    public TableRoleDao tableRoleDao;

    @SuppressWarnings("all")
    @Autowired
    public MkDao mkDao;

    public String getColTypeSql(Integer type) {
        if (type == 1) {
            return intS;
        } else if (type == 2) {
            return varchar;
        } else if (type == 3) {
            return datetime;
        } else if (type == 4) {
            return doubleS;
        } else if (type == 5) {
            return date;
        } else if (type == 6) {
            return time;
        } else {
            return null;
        }
    }

    /**
     * 查询所有列
     */
    public JSONObject listTableCol(Integer tableId, String colName) {
        JSONObject jo = new JSONObject();
        List<Map> data = dao.listTableCol(tableId, colName);
        jo.put("data", data);
        return jo;
    }


    /**
     * 添加一列，添加自定义属性
     *
     */
    @Transactional
    public JSONObject saveCol(TableColParam tableColParam) throws Exception {
        JSONObject jo = new JSONObject();
        Integer tableId = tableColParam.getTableId();
        Integer colType = tableColParam.getColType();
        String showName = tableColParam.getShowName();
        //查询表格信息
        Map mkTable = tableDao.getTalbeByTableId(tableId);
        if (mkTable == null || mkTable.get("maxId") == null) {
            jo.put("msg", "所选择的表格不存在");
            return jo;
        }
        Integer colMaxId = (Integer) mkTable.get("maxId");
        String tableName = mkTable.get("tableName").toString();
        //计算下一个列
        String colName = asciiToString(colMaxId);
        //最大列自增
        tableDao.updateTable(tableId, colMaxId + 1);
        //字段表添加
        Map tableCol = new HashMap();
        tableCol.put("tableId", tableId);
        tableCol.put("colName", colName);
        tableCol.put("colType", colType);
        tableCol.put("showName", showName);
        Integer count = dao.saveTableCol(tableCol);
        Integer tableColId = (Integer) tableCol.get("id");
        //默认针对所有人都拥有显示的权限
        tableRoleDao.batchSaveTableRole(tableColId);
        //最后执行，操作数据表结构的不能够回滚
        //字段类型
        StringBuffer addColSql = new StringBuffer();
        addColSql.append(String.format("ALTER TABLE %s ADD %s", tableName, colName));
        addColSql.append(getColTypeSql(colType));
        addColSql.append(String.format(" COMMENT '%s'", showName));
        //添加字段
        dao.exceSql(addColSql.toString());
        return jo;
    }


    /**
     * 修改一列
     */
    @Transactional
    public JSONObject updateCol(TableColParam tableColParam) {
        JSONObject jo = new JSONObject();
        Integer colType = tableColParam.getColType();
        Integer id = tableColParam.getId();
        String showName = tableColParam.getShowName();
        Map tableCol = dao.getTableCol(id);
        if (tableCol == null) {
            jo.put("msg", "不存在的列");
            return jo;
        }
        //1.修改类型.数据存在不能修改。
        //1.1查找该列所在的表以及列名
        if (colType != null) {
            //1.2查询该列的信息
            StringBuffer selectSql = new StringBuffer();
            String colName = tableCol.get("colName").toString();
            String tableName = tableCol.get("tableName").toString();
            selectSql.append(String.format("select distinct(%s) data from %s where status = '0'", colName, tableName));
            List<Map> data = mkDao.data(selectSql.toString());
            //1.3如果信息为俩个或者为1个但是不是空。不允许修改类型。
            if (data.size() > 1 || (data.size() == 1 && data.get(0)!= null)) {
                tableColParam.setColType(null);
                jo.put("msg", "存在数据不能修改列类型");

            } else {
                //2.修改列类型
                //字段类型
                StringBuffer addColSql = new StringBuffer();
                addColSql.append(String.format("ALTER TABLE %s MODIFY %s", tableName, colName));
                addColSql.append(getColTypeSql(colType));
                addColSql.append(String.format(" COMMENT '%s'", showName));
                //添加字段
                dao.exceSql(addColSql.toString());
            }
        }
        //修改列信息
        Integer influenceLine = dao.updateTablcCol(tableColParam);
        return jo;
    }


    /**
     * 删除一列
     *
     */
    @Transactional
    public JSONObject deleteCol(Integer colId) {
        JSONObject jo = new JSONObject();
        //1.删除存在数据的列,不允许
        Map tableCol = dao.getTableCol(colId);
        if (tableCol == null) {
            jo.put("msg", "不存在的列");
            return jo;
        }
        //1.2查询该列的信息
        StringBuffer selectSql = new StringBuffer();
        String colName = tableCol.get("colName").toString();
        String tableName = tableCol.get("tableName").toString();
        selectSql.append(String.format("select distinct(%s) data from %s where status = '0'", colName, tableName));
        List<Map> data = mkDao.data(selectSql.toString());
        //1.3如果信息为俩个或者为1个但是不是空。不允许修改类型。
        if (data.size() > 1 || (data.size() == 1 && data.get(0)!= null)) {
            jo.put("msg", "存在数据不能修改列类型");

        }
        //删除表列的数据
        Integer influenceLine = dao.deleteTablcCol(colId);
        //删除权限的数据
        tableRoleDao.deleteTableRole(colId);
        //修改数据库表结构
        String sql = String.format("ALTER TABLE %s DROP %s;", tableName, colName);
        dao.exceSql(sql);
        return jo;
    }


    private String asciiToString(int value) {
        int div = value / 26;
        int sur = value % 26;
        char c = (char) (sur + 65);
        StringBuffer sb = new StringBuffer();
        if (div != 0) {
            sb.append(asciiToString(div - 1));
        }
        sb.append(c);
        return sb.toString();
    }

}

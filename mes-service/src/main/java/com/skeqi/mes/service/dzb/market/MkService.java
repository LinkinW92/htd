package com.skeqi.mes.service.dzb.market;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.market.MkDao;
import com.skeqi.mes.mapper.dzb.market.TableDao;
import com.skeqi.mes.mapper.qh.CQhAuthorityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/5/6 8:26
 * @Description TODO
 */
@Service("MkService")
@Transactional
public class MkService {

    @SuppressWarnings("all")
    @Autowired
    public MkDao dao;
//    @Autowired
//    CQhAuthorityDao authDao;

    @SuppressWarnings("all")
    @Autowired
    public TableDao tableDao;

    public final String varchar = " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL ";
    public final String datetime = "  datetime  ";
    public final String number = " int ";


    /**
     * 添加自定义属性
     *
     * @param tableId  表格id
     * @param colType  字段类型。时间，字符串，数组
     * @param showName 字段显示名称
     * @return
     */
    public JSONObject saveCol(Integer tableId, Integer colType, String showName) {
        JSONObject jo = new JSONObject();
        //查询表格信息
        Map mkTable = dao.getMkTalbe(tableId);
        if (mkTable == null || mkTable.get("maxId") == null) {
            jo.put("msg", "所选择的表格不存在");
            return jo;
        }
        Integer colAscii = (Integer) mkTable.get("maxId");
        String tableName = mkTable.get("tableName").toString();
        //计算下一个列
        String colName = asciiToString(colAscii);
        //最大列自增
        dao.colMaxIdIncreas(tableId);
        //字段类型
        StringBuffer addColSql = new StringBuffer();
        addColSql.append(String.format("ALTER TABLE %s ADD %s", tableName, colName));
        if (colType == 1) {
            addColSql.append(number);
        } else if (colType == 2) {
            addColSql.append(varchar);
        } else if (colType == 3) {
            addColSql.append(datetime);
        }
        addColSql.append(String.format(" COMMENT '%s'", showName));
        //添加字段
        dao.exceSql(addColSql.toString());
        //字段表添加
        dao.saveMkTableCol(tableId, colName, colType, showName);

        return jo;
    }

    /**
     * 添加表数据
     *
     * @param tableId
     * @param json
     * @return
     */
    public JSONObject saveTableInfo(Integer tableId, String json) {
        JSONObject jo = new JSONObject();
        Object obj = JSONObject.parse(json);

        return jo;
    }

    /**
     * 修改表数据
     *
     * @param tableId 表格id
     * @param colId   列id
     * @param pjNo    唯一编码
     * @param value   值
     * @return
     */
    public JSONObject updateMkData(Integer tableId, Integer colId, String pjNo, String value) {
        JSONObject jo = new JSONObject();
        //查询表名
        String tableName = dao.getMkTableName(tableId);
        //查询列明
        String colName = dao.getMkTableColName(colId);
        if (tableName == null || colName == null || tableName.equals("") || colName.equals("")) {
            jo.put("code", 201);
            jo.put("msg", "不存在该列数据");
            return jo;
        }
        if(value.equals(""))value=null;
        //查询表的所有列
        List<String> listColName = dao.listMkTableColName(tableId);
        //插入一条当前数据 状态+1
        Integer affected = dao.backUpMkTableData(pjNo, tableName, listColName);
        if (affected == 0) {
            jo.put("code", 201);
            jo.put("msg", "数据修改失败");
            return jo;
        }
        //修改当前数据的一列值
        affected = dao.updateMkTableData(pjNo, tableName, colName, value);
        if (affected == 0) {
            jo.put("code", 201);
            jo.put("msg", "数据修改失败");
            return jo;
        }
        return jo;
    }

    /**
     * 添加表数据
     *
     * @param tableId 表格id
     * @param colId   列id
     * @param value   值
     * @return
     */
    public JSONObject saveMkData(Integer tableId, Integer colId, String value) {
        JSONObject jo = new JSONObject();
        //查询表名
        String tableName = dao.getMkTableName(tableId);
        //查询列明
        String colName = dao.getMkTableColName(colId);
        if (tableName == null || colName == null || tableName.equals("") || colName.equals("")) {
            jo.put("code", 201);
            jo.put("msg", "不存在该列数据");
            return jo;
        }
        //生成项目号
        String pjNo = getPjNo();
        //添加数据
        dao.saveMkTableData(pjNo, tableName, colName, value);
        //查询不是添加表的表明
        List<String> listMkTableNameElse = dao.getMkTableNameElse(tableId);
        for (String table : listMkTableNameElse) {
            //循环添加数据
            dao.initMkTableData(pjNo, table);
        }
        jo.put("pjNo", pjNo);
        return jo;
    }

    /**
     * 表信息
     *
     * @return
     */
    public JSONObject listTable() {
        JSONObject jo = new JSONObject();
        List<Map> data = dao.listTable();
        jo.put("data", data);
        return jo;
    }

    /**
     * @return 表数据
     */
    public JSONObject data(Integer roleId) throws Exception {
        JSONObject jo = new JSONObject();

        //创建列信息对象
        List colInfo = new ArrayList();
        //创建查询sql
        StringBuffer dataSql = new StringBuffer();
        StringBuffer colSql = new StringBuffer();
        StringBuffer selectSql = new StringBuffer();

        StringBuffer ifnullSql = new StringBuffer();
        List<StringBuffer> listFrom = new ArrayList<>();
        //查询所需要的表
        List<Map> listMkTable = dao.listMkTable();
        int i = 0;
        int lastTableId = 0;
        //循环所有表
        for (Map mkTable : listMkTable) {
            //一级表头对象
            Map col1 = new HashMap();
            //查询所有列
            Integer tableId = (Integer) mkTable.get("id");
            String name = mkTable.get("name").toString();
            List<Map> listMkTableCol = dao.listMkTableCol(tableId, roleId);
            //封装列信息对象
            col1.put("id", tableId);
            col1.put("name", name);
            //二级表头集合
            List col2List = new ArrayList();
            col1.put("props", col2List);
            colInfo.add(col1);

            //sql封装
            if (i > 0) {
                selectSql.append(" inner join");
            }
            selectSql.append(" (select  pj_no");
            for (Map col : listMkTableCol) {
                Map col2 = new HashMap();
                Integer colId = (Integer) col.get("id");
                String label = col.get("label").toString();
                String prop = col.get("prop").toString();
                Integer colType = (Integer) col.get("col_type");
                Integer authId = (Integer) col.get("authId");
                col2.put("id", colId);
                col2.put("label", label);
                col2.put("type", colType);
                col2.put("authId", authId != null);
                col2.put("prop", String.format("t%d%s", tableId, prop));
                col2List.add(col2);

                selectSql.append(String.format(",%s", prop));
                if (colType == 3) {
                    colSql.append(String.format(",DATE_FORMAT(t%d.%s,'%%Y-%%m-%%d %%H:%%i:%%s') t%d%s", tableId, prop, tableId, prop));
                } else {
                    colSql.append(String.format(",t%d.%s t%d%s", tableId, prop, tableId, prop));
                }
            }

            String tableName = mkTable.get("table_name").toString();
            selectSql.append(String.format(" from %s where `status` = 0) t%d", tableName, tableId));
            if (i == 0) {
                ifnullSql.append(String.format("t%d.pj_no", tableId));
            } else {
                ifnullSql.insert(0, String.format("IFNULL("));
                ifnullSql.append(String.format(",t%d.pj_no)", tableId));
                selectSql.append(String.format(" on t%d.pj_no=t%d.pj_no", lastTableId, tableId));
            }
            lastTableId = tableId;
            //封装查询sql
            i++;
        }
        ifnullSql.append(" pjNo");
        dataSql.append("select ");
        dataSql.append(ifnullSql);
        dataSql.append(colSql);
        dataSql.append(" from");
        dataSql.append(selectSql);
//        System.out.println(dataSql.toString());
        List<Map> data = dao.data(dataSql.toString());
        jo.put("data", data);
        jo.put("tables", colInfo);
        return jo;
    }

    /**
     * 删除数据
     * @param pjNo
     * @return
     */
    @Transactional
    public JSONObject deleteData(String pjNo){
        JSONObject jo = new JSONObject();
        //查询所有表
        List<Map> listTable = tableDao.listTable();
        for(Map table : listTable){
            String tableName = table.get("tableName").toString();
            //添加一条
//            mapper.insertDelete(pjNo,tableName);
            dao.deleteData(pjNo,tableName);
        }
        //循环删除每一个表
        return jo;
    }

    /**
     * 权限信息
     *
     * @return
     */
    public JSONObject authInfo() {
        JSONObject jo = new JSONObject();
        //获取所有角色
        List<Map> listRole = dao.listRole();
        jo.put("listRole", listRole);
        List<Map> listCol = dao.listCol();
        List<Map> authInfo = new ArrayList();
        jo.put("authInfo", authInfo);
        //初始化权限
        for (Map col : listCol) {
            Map info = new HashMap();
            String authName = col.get("authName").toString();
            Integer colId = (Integer) col.get("id");
            info.put("colId", colId);
            info.put("authName", authName);
            for (Map role : listRole) {
                Integer id = (Integer) role.get("id");
                Map roleAuth = new HashMap();
                info.put("role" + id, roleAuth);
                roleAuth.put(1, false);
                roleAuth.put(2, false);
            }
            authInfo.add(info);
        }
        List<Map> listRoleAuth = dao.listRoleAuth();
        for (Map roleAuth : listRoleAuth) {
            Integer colId = (Integer) roleAuth.get("colId");
            Integer roleId = (Integer) roleAuth.get("roleId");
            Integer authId = (Integer) roleAuth.get("authId");
            for (Map auth : authInfo) {
                Integer authColId = (Integer) auth.get("colId");
                if (authColId == colId) {
                    if (auth.keySet().contains("role" + roleId)) {
                        ((Map) auth.get("role" + roleId)).put(authId, true);
                    }
                }
            }
        }
        return jo;
    }

    /**
     * 修改权限信息
     *
     * @return
     */
    public JSONObject updateAuth(List<Map> roleChang) {
        JSONObject jo = new JSONObject();
        for (Map role : roleChang) {
            Integer id = (Integer) role.get("id");
            Integer type = (Integer) role.get("type");
            Integer roleId = (Integer) role.get("roleId");
            Boolean value = (Boolean) role.get("value");
            if (value) {
                dao.saveRoleCol(roleId, id, type);
            } else {
                dao.deleteRoleCol(roleId, id, type);
            }
        }
        return jo;
    }

    /**
     * 修改权限信息
     *
     * @return
     */
    public JSONObject importData(List<Map> importList) {
        JSONObject jo = new JSONObject();
        //拼接insertsql.
        //查询存在的表名和真实列名
        Map map = importList.get(1);
        Set set = map.keySet();
        String join = String.join(",", set);
        List<Map> listTableAndCol = dao.listTableIdAndCol(join);
        Map main = new HashMap();
        for (Map tableAndcol : listTableAndCol) {
            Integer tableId = (Integer) tableAndcol.get("table_id");
            String colName = tableAndcol.get("col_name").toString();
            Integer id = (Integer) tableAndcol.get("id");
            if (main.containsKey(tableId)) {
                Map table = (Map) main.get(tableId);
                List cols = (List) table.get("cols");
                List colIds = (List) table.get("colIds");
                cols.add(colName);
                colIds.add(id);
            } else {
                Map table = new HashMap();
                main.put(tableId, table);
                table.put("tableName", dao.getMkTableName(tableId));

                List cols = new ArrayList();
                List colIds = new ArrayList();
                cols.add(colName);
                colIds.add(id);
                table.put("cols", cols);
                table.put("colIds", colIds);
            }
        }
        //处理数据
        {
            Map imData = importList.get(2);
            String pjNo = getPjNo();
            for (Object key : main.keySet()) {
                Map table = (Map) main.get(key);
                List colIds = (List) table.get("colIds");
                List data = new ArrayList();
                table.put("data", data);
                List row = new ArrayList();
                data.add(row);
                row.add(pjNo);
                for (Object colId : colIds) {
                    row.add(imData.get(""+colId));
                }
            }
        }
        //处理数据
        for (int i = 3; i < importList.size(); i++) {
            Map imData = importList.get(i);
            String pjNo = getPjNo();
            for (Object key : main.keySet()) {
                Map table = (Map) main.get(key);
                List colIds = (List) table.get("colIds");
                List data = (List) table.get("data");
                List row = new ArrayList();
                data.add(row);
                row.add(pjNo);
                for (Object colId : colIds) {
                    row.add(imData.get(""+colId));
                }
            }
        }
        //执行sql
        for (Object key : main.keySet()) {
            Map table = (Map) main.get(key);
            String tableName = table.get("tableName").toString();
            List cols = (List) table.get("cols");
            List data = (List) table.get("data");
            dao.batchSaveMkTableData(tableName, cols, data);
        }
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

    private String getPjNo() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}

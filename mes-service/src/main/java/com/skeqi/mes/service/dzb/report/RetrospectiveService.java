package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.RetrospectiveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by DZB
 * @Date 2021/7/11 22:10
 * @Description TODO
 */

@Service("RetrospectiveService")
public class RetrospectiveService {

    @SuppressWarnings("all")
    @Autowired
    private RetrospectiveDao dao;

    //查询所有数据

    /**
     * @param where 查询条件
     * @param type  1：自上而下  2：自下而上
     * @param times 1：单层  其他：多层
     * @return
     */
    public JSONObject data(Map where, int type, int times) {

        JSONObject out = new JSONObject();
        List paramTable = new ArrayList();
        List materialTable = new ArrayList();

        List<Map> listMaterial = dao.listMaterial(where);
        materialTable.addAll(listMaterial);
        materialData(materialTable, listMaterial, type, times);


        List<Map> listParam = dao.listParam(where);
        paramTable.addAll(listParam);
        paramData(paramTable, listParam, type, times);

        Map data = new HashMap();
        out.put("paramTable", paramTable);
        out.put("materialTable", materialTable);
        return out;
    }

    private void materialData(List materialTable, List<Map> listMaterial, int type, int times) {
        if (times != 1) {
            if (type == 1) {
                for (Map material : listMaterial) {
                    String sn = ifnull(material.get("SN"), null);
                    if (sn == null) continue;
                    List<Map> snChild = dao.listChildren(sn);
                    List snList = new ArrayList();
                    for (Map snMap : snChild) {
                        snList.add(snMap.get("sn").toString());
                    }
                    if (snList.size() > 0) {
                        Map where = new HashMap();
                        where.put("SN", snList);
                        List<Map> listMaterialChilden = dao.listMaterial(where);
                        materialTable.addAll(listMaterialChilden);
                        materialData(materialTable, listMaterialChilden, type, times);
                    }
                }
            } else {
                for (Map material : listMaterial) {
                    String sn = ifnull(material.get("SN"), null);
                    if (sn == null) continue;
                    List<Map> snParent = dao.listParent(sn);
                    List snList = new ArrayList();
                    for (Map snMap : snParent) {
                        snList.add(snMap.get("sn").toString());
                    }
                    if (snList.size() > 0) {
                        Map where = new HashMap();
                        where.put("SN", snList);
                        List<Map> listMaterialChildend = dao.listMaterial(where);
                        materialTable.addAll(listMaterialChildend);
                        materialData(materialTable, listMaterialChildend, type, times);
                    }
                }
            }
        }
    }

    private void paramData(List paramTable, List<Map> listParam, int type, int times) {
        if (times != 1) {
            if (type == 1) {
                for (Map param : listParam) {
                    String sn = ifnull(param.get("SN"), null);
                    if (sn == null) continue;
                    List<Map> snChild = dao.listChildren(sn);
                    List snList = new ArrayList();
                    for (Map snMap : snChild) {
                        snList.add(snMap.get("sn").toString());
                    }
                    if (snList.size() > 0) {
                        Map where = new HashMap();
                        where.put("SN", snList);
                        List<Map> listParamChilden = dao.listParam(where);
                        paramTable.addAll(listParamChilden);
                        materialData(paramTable, listParamChilden, type, times);
                    }
                }
            } else {
                for (Map param : listParam) {
                    String sn = ifnull(param.get("SN"), null);
                    if (sn == null) continue;
                    List<Map> snParent = dao.listParent(sn);
                    List snList = new ArrayList();
                    for (Map snMap : snParent) {
                        snList.add(snMap.get("sn").toString());
                    }
                    if (snList.size() > 0) {
                        Map where = new HashMap();
                        where.put("SN", snList);
                        List<Map> listParamChilden = dao.listMaterial(where);
                        paramTable.addAll(listParamChilden);
                        materialData(paramTable, listParamChilden, type, times);
                    }
                }
            }
        }
    }

    private String ifnull(Object obj, String el) {
        return obj != null ? obj.toString() : el;
    }

}

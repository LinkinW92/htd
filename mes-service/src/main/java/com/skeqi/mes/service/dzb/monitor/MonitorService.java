package com.skeqi.mes.service.dzb.monitor;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.monitor.AllMonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MonitorService implements MonitorServiceI {

    @SuppressWarnings("all")
    @Autowired
    private AllMonDao dao;

    private static final String OUTPUT = "下线数量";
    private static final String OUTPUTSUM = "累计下线数量";
    private static final String INPUT = "上线数量";
    private static final String OK = "合格数量";
    private static final String NG = "不合格数量";
    private static final String OKSUM = "累计合格数量";
    private static final String YIELD = "良率";

    //今天每时的 下线数量
    private static Map hourOutputToday;
    //今天每时的  合格数量
    private static Map hourOKToday;
    //今天每时的  合格数量累计
    private static Map hourOKSumToday;
    //今天每时的  不合格数量
    private static Map hourNGToday;
    //今天每时的  良率
    private static Map hourYieldToday;

    //当月每日的下线数量
    private static Map dayOutputMonth;
    //当月累计的下线数量
    private static Map daySumMonth;

    //当年每月的合格数量
    private static Map monthOkYear;
    //当年每月的不合格数量
    private static Map monthNgYear;

    //每年的合格数量
    private static Map yearOk;
    //每年的不合格数量
    private static Map yearNg;

    //开始年份
    private static int startYear;

    static {
        hourOutputToday = new HashMap();
        hourOKToday = new HashMap();
        hourOKSumToday = new HashMap();
        hourNGToday = new HashMap();
        hourYieldToday = new HashMap();

        dayOutputMonth = new HashMap();
        daySumMonth = new HashMap();

        monthOkYear = new HashMap();
        monthNgYear = new HashMap();

        yearOk = new HashMap();
        yearNg = new HashMap();
        startYear = 2020;
    }

    //region 数据查询

    /**
     * 单个对象数据
     *
     * @param eleId
     * @return
     */
    public JSONObject objData(Integer eleId) {
        String orderBranch = "订单";
        JSONObject out = new JSONObject();
        String objName = dao.getObjName(eleId);
        //获取全部属性
        List listAttr = dao.listAttr(eleId);
        List thDatas = new ArrayList();
        List tdDatas = new ArrayList();
        if (objName.equals(orderBranch)) {
            Map orderInfo = dao.orderInfo();
            List tdData = new ArrayList();
            tdDatas.add(tdData);
            for (Object attr : listAttr) {
                thDatas.add((String) attr);
                if (orderInfo != null) {
                    tdData.add(orderInfo.get((String) attr));
                }
            }
        }

        Map data = new HashMap();
        data.put("thDatas", thDatas);
        data.put("tdDatas", tdDatas);
        out.put("data", data);
        return out;

    }

    /**
     * 集合对象数据
     *
     * @param eleId
     * @return
     */
    public JSONObject listObjData(Integer eleId) {
        String orderBranch = "订单集合";
        JSONObject out = new JSONObject();
        Map data = new HashMap();
        String objName = dao.getObjName(eleId);
        //获取全部属性
        List listAttr = dao.listAttr(eleId);
        List tdDatas = new ArrayList();
        if (objName.equals(orderBranch)) {
            List<Map> listOrder = dao.listOrder();
            int orderListSize = listOrder.size();
            for (int i = 0; i < orderListSize; i++) {
                Map order = listOrder.get(i);
                List tdData = new ArrayList();
                for (Object attr : listAttr) {
                    tdData.add(order.get((String) attr));
                }
                tdDatas.add(tdData);
            }

        }
        data.put("tdDatas", tdDatas);
        data.put("thDatas", listAttr);
        out.put("data", data);

        return out;
    }

    /**
     * 时间序列数据
     *
     * @param eleId
     * @return
     */
    public JSONObject timeData(Integer eleId) {
        String hourProductionBranch = "每时生产";
        String dayProductionBranch = "每日生产";
        String monthProductionBranch = "每月生产";
        String yearProductionBranch = "每年生产";
        JSONObject out = new JSONObject();
        Map data = new HashMap();
        String objName = dao.getObjName(eleId);
        //获取全部属性
        List listAttr = dao.listAttr(eleId);
        int listAttrSize = listAttr.size();
        List legendDatas = listAttr;
        List xData = new ArrayList();
        List yData = new ArrayList();
        Calendar nowC = Calendar.getInstance();
        nowC.setTime(new Date());
        //获取where条件
        List listCondit = dao.listCondit(eleId);
        String condits = listCondit == null ? null : String.join(" ", listCondit);
        //region 每时
        if (objName.equals(hourProductionBranch)) {
            //获取现在时间
            int hourNow = nowC.get(Calendar.HOUR_OF_DAY);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
            if (listAttr.contains(OUTPUT)) {
                Object today = hourOutputToday.get(sdf.format(nowC.getTime()));
                if (today == null) {
                    hourOutputToday.clear();
                    hourOutputToday.put(sdf.format(nowC.getTime()), "1");
                }
            }
            if (listAttr.contains(OK)) {
                Object today = hourOKToday.get(sdf.format(nowC.getTime()));
                if (today == null) {
                    hourOKToday.clear();
                    hourOKToday.put(sdf.format(nowC.getTime()), "1");
                }
            }
            if (listAttr.contains(NG)) {
                Object today = hourNGToday.get(sdf.format(nowC.getTime()));
                if (today == null) {
                    hourNGToday.clear();
                    hourNGToday.put(sdf.format(nowC.getTime()), "1");
                }
            }
            if (listAttr.contains(OKSUM)) {
                Object today = hourOKSumToday.get(sdf.format(nowC.getTime()));
                if (today == null) {
                    hourOKSumToday.clear();
                    hourOKSumToday.put(sdf.format(nowC.getTime()), "1");
                }
            }
            if (listAttr.contains(YIELD)) {
                Object today = hourYieldToday.get(sdf.format(nowC.getTime()));
                if (today == null) {
                    hourYieldToday.clear();
                    hourYieldToday.put(sdf.format(nowC.getTime()), "1");
                }
            }
            for (int c = 0; c < listAttrSize; c++) {
                List cydata = new ArrayList();
                String attr = (String) listAttr.get(c);
                Integer ydOkSum = 0;
                for (int i = 0; i <= hourNow; i++) {
                    if (c == 0) {
                        xData.add(i);
                    }
                    if (attr.equals(OUTPUT)) {
                        Integer yd;
                        Object ydObj = hourOutputToday.get(i);
                        if (ydObj != null) {
                            yd = (Integer) ydObj;
                        } else {
                            yd = dao.outputByHourAndLine(condits, i);
                            if (i < hourNow) {
                                hourOutputToday.put(i, yd);
                            }
                        }
                        cydata.add(yd);
                    } else if (attr.equals(INPUT)) {

                    } else if (attr.equals(OK)) {
                        Integer yd;
                        Object ydObj = hourOKToday.get(i);
                        if (ydObj != null) {
                            yd = (Integer) ydObj;
                        } else {
                            yd = dao.okByHourAndLine(condits, i);
                            if (i < hourNow) {
                                hourOKToday.put(i, yd);
                            }
                        }
                        cydata.add(yd);
                    } else if (attr.equals(NG)) {
                        Integer yd;
                        Object ydObj = hourNGToday.get(i);
                        if (ydObj != null) {
                            yd = (Integer) ydObj;
                        } else {
                            yd = dao.ngByHourAndLine(condits, i);
                            if (i < hourNow) {
                                hourNGToday.put(i, yd);
                            }
                        }
                        cydata.add(yd);
                    } else if (attr.equals(OKSUM)) {
                        Object ydObj = hourOKSumToday.get(i);
                        if (ydObj != null) {
                            ydOkSum = (Integer) ydObj;
                        } else {
                            ydOkSum += dao.okByHourAndLine(condits, i);
                            if (i < hourNow) {
                                hourOKSumToday.put(i, ydOkSum);
                            }
                        }
                        cydata.add(ydOkSum);

                    } else if (attr.equals(YIELD)) {
                        String y3d;
                        Object y3dObj = hourYieldToday.get(i);
                        if (y3dObj != null) {
                            y3d = (String) y3dObj;
                        } else {
                            Integer y1d;
                            Integer y2d;
                            Object y1dObj = hourOKToday.get(i);
                            if (y1dObj != null) {
                                y1d = (Integer) y1dObj;
                            } else {
                                y1d = dao.okByHourAndLine(condits, i);
                                if (i < hourNow) {
                                    hourOKToday.put(i, y1d);
                                }
                            }
                            Object y2dObj = hourNGToday.get(i);
                            if (y2dObj != null) {
                                y2d = (Integer) y2dObj;
                            } else {
                                y2d = dao.ngByHourAndLine(condits, i);
                                if (i < hourNow) {
                                    hourNGToday.put(i, y2d);
                                }
                            }
                            y3d = baifenbi(y1d, (y1d + y2d));
                            if (i < hourNow) {
                                hourYieldToday.put(i, y3d);
                            }
                        }
                        cydata.add(y3d);
                    }
                }
                yData.add(cydata);
            }
        }
        //endregion
        //region 每天
        else if (objName.equals(dayProductionBranch)) {
            //获取现在时间
            int dayNow = nowC.get(Calendar.DAY_OF_MONTH);
            int maxDay = nowC.getActualMaximum(Calendar.DATE);
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMM");
            if (listAttr.contains(OUTPUT)) {
                Object today = dayOutputMonth.get(sdf.format(nowC.getTime()));
                if (today == null) {
                    dayOutputMonth.clear();
                    dayOutputMonth.put(sdf.format(nowC.getTime()), "1");
                }
            }
            if (listAttr.contains(OUTPUTSUM)) {
                Object today = daySumMonth.get(sdf.format(nowC.getTime()));
                if (today == null) {
                    daySumMonth.clear();
                    daySumMonth.put(sdf.format(nowC.getTime()), "1");
                }
            }
            for (int c = 0; c < listAttrSize; c++) {
                List cydata = new ArrayList();
                String attr = (String) listAttr.get(c);
                Integer ydOkSum = 0;
                for (int i = 0; i <= maxDay; i++) {
                    if (c == 0) {
                        xData.add(i);
                    }
                    if (attr.equals(OUTPUT)) {
                        Integer y1d = 0;
                        if (i <= dayNow) {
                            Object y1dObj = dayOutputMonth.get(i);
                            if (y1dObj != null) {
                                y1d = (Integer) y1dObj;
                                cydata.add(y1d);
                                continue;
                            } else {
                                y1d = dao.outputByDayAndLine(condits, i);
                                if (i < maxDay) {
                                    dayOutputMonth.put(i, y1d);
                                }
                            }
                        }
                        cydata.add(y1d);
                    } else if (attr.equals(OUTPUTSUM)) {
                        if (i <= dayNow) {
                            Object ydObj = daySumMonth.get(i);
                            if (ydObj != null) {
                                ydOkSum = (Integer) ydObj;
                            } else {
                                ydOkSum += dao.outputByDayAndLine(condits, i);
                                if (i < dayNow) {
                                    daySumMonth.put(i, ydOkSum);
                                }
                            }
                        }
                        cydata.add(ydOkSum);
                    }
                }
                yData.add(cydata);
            }
        }
        //endregion
        //region 每月
        else if (objName.equals(monthProductionBranch)) {
            int monthNow = nowC.get(Calendar.MONTH) + 1;
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
            if (listAttr.contains(OK)) {
                monthOkYear.clear();
                monthOkYear.put(sdf.format(nowC.getTime()), "1");
            }
            if (listAttr.contains(NG)) {
                monthNgYear.clear();
                monthNgYear.put(sdf.format(nowC.getTime()), "1");
            }
            for (int c = 0; c < listAttrSize; c++) {
                List cydata = new ArrayList();
                String attr = (String) listAttr.get(c);
                for (int i = 1; i <= 12; i++) {
                    if (c == 0) {
                        xData.add(i);
                    }
                    Integer yd = 0;
                    if (attr.equals(OK)) {
                        if (i <= monthNow) {
                            Object ydObj = monthOkYear.get(i);
                            if (ydObj != null) {
                                yd = (Integer) ydObj;
                            } else {
                                yd = dao.okByMonthAndLine(condits, i);
                                if (i < monthNow) {
                                    monthOkYear.put(i, yd);
                                }
                            }
                        }
                        cydata.add(yd);
                    } else if (attr.equals(NG)) {
                        if (i <= monthNow) {
                            Object ydObj = monthNgYear.get(i);
                            if (ydObj != null) {
                                yd = (Integer) ydObj;
                            } else {
                                yd = dao.ngByMonthAndLine(condits, i);
                                if (i < monthNow) {
                                    monthNgYear.put(i, yd);
                                }
                            }
                        }
                        cydata.add(yd);
                    }
                }
                yData.add(cydata);
            }
        }
        //endregion
        //region 每年
        else if (objName.equals(yearProductionBranch)) {
            int yearNow = nowC.get(Calendar.YEAR);
            for (int c = 0; c < listAttrSize; c++) {
                List cydata = new ArrayList();
                String attr = (String) listAttr.get(c);
                for (int i = startYear; i <= yearNow; i++) {
                    if (c == 0) {
                        xData.add(i);
                    }
                    Integer yd;
                    if (attr.equals(OK)) {
                        Object ydObj = yearOk.get(i);
                        if (ydObj != null) {
                            yd = (Integer) ydObj;
                        } else {
                            yd = dao.okByYearAndLine(condits, i);
                            if (i < yearNow) {
                                yearOk.put(i, yd);
                            }
                        }
                        cydata.add(yd);
                    } else if (attr.equals(NG)) {
                        Object ydObj = yearNg.get(i);
                        if (ydObj != null) {
                            yd = (Integer) ydObj;
                        } else {
                            yd = dao.ngByYearAndLine(condits, i);
                            if (i < yearNow) {
                                yearNg.put(i, yd);
                            }
                        }
                        cydata.add(yd);
                    }
                }
                yData.add(cydata);
            }
        }
        //endregion

        data.put("legendDatas", legendDatas);
        data.put("xData", xData);
        data.put("yData", yData);
        out.put("data", data);
        return out;
    }

    //endregion


    //region 看板业务
    public JSONObject onekanban(String kanban, int id) {

        JSONObject out = new JSONObject();
        Map kanbanById = dao.findKanbanById(kanban, id);
        if (kanbanById == null) {
            throw new NullPointerException();
        }
        int flag = dao.getFlag();
        int editAuth = dao.getAuthFlag();
        out.put("out", kanbanById);
        out.put("flag", flag);
        out.put("editAuth", editAuth);
        return out;
    }

    public JSONObject manykanban(String kanban) {
        JSONObject out = new JSONObject();
        List<Map> kanbanList = dao.findKanbanList(kanban);
        out.put("kanbanList", kanbanList);
        return out;
    }

    public JSONObject haveImage(String kanban, int id) {
        JSONObject out = new JSONObject();
        out.put("have", dao.haveImage(kanban, id));
        return out;
    }

    @Transactional
    public JSONObject saveKanban(Map saveMap) throws Exception {
        JSONObject out = new JSONObject();
        int count = dao.saveKanban(saveMap);
        //添加菜单
//        mapper.saveMune(saveMap.get("kanban").toString(), saveMap.get("name").toString(), Integer.parseInt(saveMap.get("id").toString()));
//        if (count == 1) {
//            out.put("id", saveMap.get("id"));
//        } else {
//            throw new Exception("添加失败");
//        }
        return out;
    }
    @Transactional
    public JSONObject copyKanban(Map saveMap) throws Exception {
        JSONObject out = new JSONObject();
        int count = dao.copyKanban(saveMap);
        //添加菜单
//        mapper.saveMune(saveMap.get("kanban").toString(), saveMap.get("name").toString(), Integer.parseInt(saveMap.get("id").toString()));
//        if (count == 1) {
//            out.put("id", saveMap.get("id"));
//        } else {
//            throw new Exception("添加失败");
//        }
        return out;
    }

    @Transactional
    public void updateKanban(Map updateMap) {
        dao.updateKanban(updateMap);
        //修改菜单
//        if (updateMap.get("name") != null)
//            mapper.updateMenu(updateMap.get("kanban").toString(), updateMap.get("name").toString(), Integer.parseInt(updateMap.get("id").toString()));

    }

    @Transactional
    public void removeKanban(String kanban, int id) {
        dao.removeKanban(kanban, id);
        //删除菜单
        dao.removeMenu(kanban, id);
    }

    @Transactional
    public void saveBgimg(String path) {
        dao.saveBgimg(path);
    }

    public JSONObject listBgImg(HttpServletRequest request) {
        JSONObject out = new JSONObject();
        List listBgImg = dao.listBgImg();
        List list = new ArrayList();
        for (Object bgImg : listBgImg) {
            String cloud = "/MES_System/cloud/img/";
            String path = "c:\\kanbanUpload\\img\\";
            String filePath = path + bgImg.toString().replace(cloud, "");
            File file = new File(filePath);
            if (file.exists()) {
                list.add(bgImg);
            } else {
                dao.removeBgImg(bgImg.toString());
            }
        }
        out.put("listBgImg", list);
        return out;
    }

    public void removeBgImg(String path) {
        dao.removeBgImg(path);
    }


    //endregion

    //region 元素业务
    public JSONObject dataObjAttr(int eleId, String eleName) {
        JSONObject out = new JSONObject();
        //获取当前 对象
        Integer objId = dao.getObjIdByEleId(eleId);
        //获取当前 属性
        List attrIds = dao.listAttrIdsByEleId(eleId);
        //获取所有 对象
        List objs = dao.listObjsByEleName(eleName);
        //获取所有的 属性
        List attrs = dao.listAttrsByObjId(objId);
        out.put("objId", objId);
        out.put("attrIds", attrIds);
        out.put("objs", objs);
        out.put("attrs", attrs);
        return out;

    }

    public JSONObject listObjs(String eleName) {
        JSONObject out = new JSONObject();
        //获取所有 对象
        List objs = dao.listObjsByEleName(eleName);
        out.put("objs", objs);
        return out;
    }

    public JSONObject listAttrs(int objId) {
        JSONObject out = new JSONObject();
        List attrs = dao.listAttrsByObjId(objId);
        out.put("attrs", attrs);
        return out;
    }

    public JSONObject updateEleAttr(int eleId, int[] attrs) {
        JSONObject out = new JSONObject();
        dao.remarkEleAttrByEleId(eleId);
        //如果等于0，说明时新增属性
        if (eleId == 0) {
            Integer maxId = dao.getMaxEleId();
            eleId = maxId == null ? 1 : maxId + 1;
        }
        dao.saveEleAttrs(eleId, attrs);
        String path = dao.getApiByAttrId(attrs[0]);
        out.put("path", path);
        out.put("eleId", eleId);
        return out;
    }

    public void removeEleId(int eleId) {
        dao.removeAttrsByEleId(eleId);
    }
    //endregion

    //计算百分比
    private String baifenbi(int a, int b) {
        if (b == 0) {
            return "0";
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) a / (float) b * 100);
        return result;
    }

}


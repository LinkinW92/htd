package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.NgReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/7/11 19:58
 * @Description TODO
 */
@Service("NgReportService")
public class NgReportService {


    @SuppressWarnings("all")
    @Autowired
    private NgReportDao dao;


    //查询所有数据

    /**
     * @param where
     * @param start
     * @param type  1:当天每小时。  2：当月每天
     * @return
     */
    public JSONObject data(Map where, String start, int type) throws Exception {
        String col ="";
        if(type == 1){
            col = "hourNo";
        }else if(type == 2){
            col = "dayNo";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject out = new JSONObject();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(sdf.parse(start));
        String startStr = "";
        String end = "";
        if (type == 1) {
            startStr = start;
            endCalendar.add(Calendar.DATE, 1);
        } else if (type == 2) {
            endCalendar.set(endCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.MONTH), 1);
            startStr = sdf.format(endCalendar.getTime());
            endCalendar.add(Calendar.MONTH, 1);
        }
        end = sdf.format(endCalendar.getTime());

        List<Map> qtyList = dao.data(col, where, startStr, end);
        List dtList = new ArrayList();
        List<Integer> ngList = new ArrayList();
        List<Integer> totalList = new ArrayList();
        int total = 0;
        List rateList = new ArrayList();
        if (type == 1) {
            //数据初始化
            for (int i = 0; i < 24; i++) {
                dtList.add(i);
                ngList.add(0);
                rateList.add(0);
                totalList.add(0);
            }
            for (Map qty : qtyList) {
                String status = qty.get("STATUS").toString();
                int hour = Integer.parseInt(qty.get("hourNo").toString());
                int num = Integer.parseInt(qty.get("qty").toString());
                total += num;
                if (status.equals("NG")) {
                    ngList.set(hour, num);
                }
                totalList.set(hour, num);
            }
        } else if (type == 2) {
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(start));
            c.set(Calendar.DATE, 1);
            int maxDay = c.getActualMaximum(Calendar.DATE);
            int startDay = (int) ((c.getTimeInMillis() + 1000 * 60 * 60 * 8) / (1000 * 60 * 60 * 24)) + 719528;
//            System.out.println(startDay);
            //数据初始化
            for (int i = 1; i <= maxDay; i++) {
                dtList.add(i);
                ngList.add(0);
                rateList.add(0);
                totalList.add(0);
            }
            for (Map qty : qtyList) {
                String status = qty.get("STATUS").toString();
                int dayNo = Integer.parseInt(qty.get("dayNo").toString());
                int num = Integer.parseInt(qty.get("qty").toString());
                total += num;
                if (status.equals("NG")) {
                    ngList.set(dayNo-startDay, num);
                }
                totalList.set(dayNo-startDay, num);
            }

        }
        //总数初始化
        //计算累计不合格率
        rateList.set(0, calculatePercentageOfTwoNumbers(ngList.get(0), totalList.get(0)));
        for (int i = 1; i < totalList.size(); i++) {
            int t = totalList.get(i);
            int t1 = totalList.get(i - 1);
            if (t == 0) {
                totalList.set(i, t1);
                rateList.set(i, calculatePercentageOfTwoNumbers(ngList.get(i), t1));
            } else {
                rateList.set(i, calculatePercentageOfTwoNumbers(ngList.get(i), t));
            }
        }
        Map data = new HashMap();
        data.put("dtList", dtList);
        data.put("ngList", ngList);
        data.put("rateList", rateList);
        out.put("data", data);
        return out;
    }


    //计算俩数百分比
    public String calculatePercentageOfTwoNumbers(int small, int big) {
        if(big == 0)return "0%";
        double p3 = Double.valueOf(small) / Double.valueOf(big);
        NumberFormat nf = NumberFormat.getPercentInstance();
//        nf.setMinimumFractionDigits(2);
        String format = nf.format(p3);
        return format;
    }
}

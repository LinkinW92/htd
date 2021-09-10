package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.mapper.dzb.report.EquipmentOEEDao;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.service.lcy.GetSomeYieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/3/19 9:36
 * @Description TODO
 */
@Service
public class EquipmentOEEService implements EquipmentUseServiceI {

    @SuppressWarnings("all")
    @Autowired
    private EquipmentOEEDao dao;

    public JSONObject listLine() {
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listLine();
        out.put("data", mapList);
        return out;
    }

    public JSONObject queryEquipmentOEEValue(Date date, Integer lineId) {
        int dayNum = 7;
        JSONObject jo = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -dayNum);

        //region 获取理论工作时间
        Integer getTheoryTime = 0;
        List<CMesShiftsTeamT> getShiftsTeamList = dao.getShiftsTeamList(lineId);//获取排班列表
        for (CMesShiftsTeamT cMesShiftsTeamT : getShiftsTeamList) {
            String[] strs2 = cMesShiftsTeamT.getStartTime().split(":");
            String[] strs1 = cMesShiftsTeamT.getEndTime().split(":");
            if (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0]) > 0) {
                getTheoryTime += (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0])) * 60 * 60;//获取小时的  秒数
                getTheoryTime += (Integer.parseInt(strs1[1]) - Integer.parseInt(strs2[1])) * 60;//获取分钟的秒数
            } else if (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0]) == 0 &&
                    Integer.parseInt(strs1[1]) - Integer.parseInt(strs2[1]) > 0) {
                getTheoryTime += (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0])) * 60 * 60;//获取小时的  秒数
                getTheoryTime += (Integer.parseInt(strs1[1]) - Integer.parseInt(strs2[1])) * 60;//获取分钟的秒数
            } else {//结束时间小于开始时间   也就是说 从第一天去上班到了第二天
                getTheoryTime += (Integer.parseInt(strs1[0]) - Integer.parseInt(strs2[0])) * 60 * 60;//获取小时的  秒数
                getTheoryTime += (Integer.parseInt(strs1[1]) - Integer.parseInt(strs2[1])) * 60;//获取分钟的秒数
                getTheoryTime += 24 * 60 * 60;
            }
        }
        //endregion
        List oeeInfoList = new ArrayList();
        for (int i = 0; i < dayNum; i++) {
            c.add(Calendar.DATE,1);
            Date thisDate = c.getTime();
            Map oeeInfo = new HashMap();
            Map map = new HashMap();
            map.put(sdf.format(thisDate),oeeInfo);
            oeeInfoList.add(map);

            //region //时间开动率   即  实际工作时间/理论工作时间
            oeeInfo.put("getTheoryTime",getTheoryTime);
            Integer getActualTime = dao.getTime(lineId,thisDate);//获取实际时间
            if (getActualTime == null) {
                getActualTime = 0;
            }
            oeeInfo.put("getActualTime",getActualTime);
            //endregion

            //region 性能开动率    实际产量/理论产量
            //实际产量
            Integer productNumber = dao.getSnCount(lineId,thisDate,null);//获取当天的产品的数量
            //合格品的数量
            Integer qualificationNumber =  dao.getSnCount(lineId,thisDate,"OK");//获取当天的合格品的数量
            //endregion
            oeeInfo.put("productNumber",productNumber);
            oeeInfo.put("qualificationNumber",qualificationNumber);
        }
        jo.put("data",oeeInfoList);
        return jo;
    }


}

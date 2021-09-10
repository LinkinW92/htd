package com.skeqi.mes.service.dzb.report;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.dzb.report.EquipmentUseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Created by DZB
 * @Date 2021/3/10 9:23
 * @Description TODO
 */
@Service("EquipmentUseService")
public class EquipmentUseService implements EquipmentUseServiceI {


    @SuppressWarnings("all")
    @Autowired
    public EquipmentUseDao dao;


    public JSONObject listLine() {
        JSONObject out = new JSONObject();
        List<Map> mapList = dao.listLine();
        out.put("data", mapList);
        return out;
    }


    public JSONObject queryEquipmentUse(int lineId, Date date, Integer dayNum) {
        JSONObject out = new JSONObject();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -dayNum);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List equipmentUse = new ArrayList();
        for (int i = 0; i < dayNum; i++) {
            Map map = new HashMap();
            c.add(Calendar.DATE, 1);
            int snCount = dao.countSnByDayAndLineId(lineId, c.getTime());
//            BigDecimal getEquipmentValue=null;
//            BigDecimal num=new BigDecimal(snCount);//当天产量
//            //当天产量/当天理论产量 *100% 获取设备利用率
//            getEquipmentValue = num.divide(new BigDecimal(theoreticalOutput), 2, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
            map.put("date", sdf.format(c.getTime()));
            map.put("value", snCount);
            equipmentUse.add(map);
        }
        out.put("data", equipmentUse);
        return out;
    }

    public JSONObject queryEquipmentUseSt(int lineId, Date date) {
        JSONObject out = new JSONObject();

        List<Map> equipmentUseSt = dao.countSnGroupSt(lineId, date);
        out.put("data", equipmentUseSt);
        return out;
    }
}

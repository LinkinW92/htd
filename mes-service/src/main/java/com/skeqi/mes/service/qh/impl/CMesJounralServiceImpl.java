package com.skeqi.mes.service.qh.impl;

import com.skeqi.mes.mapper.qh.CMesJounralDao;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.pojo.qh.CMesJounralT;
import com.skeqi.mes.service.qh.CMesJounralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/26 11:16
 */
@Service
public class CMesJounralServiceImpl implements CMesJounralService {

    @Autowired
    CMesJounralDao dao;

    @Override
    public List<CMesWebApiLogs> findAllJounral(String startDt, String endDt) {
        return dao.findAllJounral(startDt,endDt);
    }

    @Override
    public void addJounral(String name, String desc) {
        dao.addJounral(name,desc);
    }
}

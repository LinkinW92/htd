package com.skeqi.mes.service.qh.impl;

import com.skeqi.mes.mapper.qh.CMesBadReasonDao;
import com.skeqi.mes.pojo.qh.CMesBadReasonT;
import com.skeqi.mes.service.qh.CMesBadReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/19 14:14
 */
@Service
public class CMesBadReasonServiceImpl implements CMesBadReasonService {

    @Autowired
    CMesBadReasonDao dao;

    @Override
    public List<CMesBadReasonT> findAllBadReason(CMesBadReasonT t) {
        return dao.findAllBadReason(t);
    }

    @Override
    public void addBadReason(CMesBadReasonT t) {
        dao.addBadReason(t);
    }

    @Override
    public void updateBadReason(CMesBadReasonT t) {
        dao.updateBadReason(t);
    }

    @Override
    public void delBadReason(Integer id) {
        dao.delBadReason(id);
    }
}

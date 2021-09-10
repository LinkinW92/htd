package com.skeqi.mes.mapper.qh;

import com.skeqi.mes.pojo.qh.CMesBadReasonT;

import java.util.List;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/19 14:04
 */
public interface CMesBadReasonDao {

    //查询不良原因
    List<CMesBadReasonT> findAllBadReason(CMesBadReasonT t);

    //添加
    void addBadReason(CMesBadReasonT t);

    //修改
    void updateBadReason(CMesBadReasonT t);

    //删除
    void delBadReason(Integer id);
}

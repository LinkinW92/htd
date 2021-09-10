package com.skeqi.mes.service.wf.linesidelibrary.productOffline.impl;

import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailT;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.skeqi.mes.mapper.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailTService;

import java.util.List;

@Service
public class LslProductOfflineDetailedDetailTServiceImpl implements LslProductOfflineDetailedDetailTService {

    @Resource
    private LslProductOfflineDetailedDetailTMapper lslProductOfflineDetailedDetailTMapper;


    @Override
    public Integer insertByList(List<LslProductOfflineDetailedDetailT> detailTList) {
        return lslProductOfflineDetailedDetailTMapper.insertByList(detailTList);
    }

    @Override
    public List<LslProductOfflineDetailedDetailT> selectAll(LslProductOfflineDetailedDetailT detailedDetailT) {
        return lslProductOfflineDetailedDetailTMapper.selectAll(detailedDetailT);
    }

    @Override
    public LslProductOfflineDetailedDetailT selectByPrimaryKey(String number) {
        return lslProductOfflineDetailedDetailTMapper.selectByPrimaryKey(number);
    }

    @Override
    public Integer deleteDetailedDetailByOfflineNumber(String number) {
        return lslProductOfflineDetailedDetailTMapper.deleteDetailedDetailByOfflineNumber(number);
    }

}

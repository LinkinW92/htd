package com.skeqi.mes.service.wf.linesidelibrary.productOffline.impl;

import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedT;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.linesidelibrary.productOffline.LslProductOfflineDetailedTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.productOffline.LslProductOfflineDetailedTService;

import java.util.List;

@Service
public class LslProductOfflineDetailedTServiceImpl implements LslProductOfflineDetailedTService{

    @Resource
    private LslProductOfflineDetailedTMapper lslProductOfflineDetailedTMapper;

    @Override
    public List<LslProductOfflineDetailedT> selectAll(LslProductOfflineDetailedT detailedT) {
        return lslProductOfflineDetailedTMapper.selectAll(detailedT);
    }

    @Override
    public LslProductOfflineDetailedT selectByPrimaryKey(String number) {
        return lslProductOfflineDetailedTMapper.selectByPrimaryKey(number);
    }

    @Override
    public Integer insertByList(List<LslProductOfflineDetailedT> detailedTList) {
        return lslProductOfflineDetailedTMapper.insertByList(detailedTList);
    }

    @Override
    public Integer deleteDetailedByOfflineNumber(String number) {
        return lslProductOfflineDetailedTMapper.deleteDetailedByOfflineNumber(number);
    }
}

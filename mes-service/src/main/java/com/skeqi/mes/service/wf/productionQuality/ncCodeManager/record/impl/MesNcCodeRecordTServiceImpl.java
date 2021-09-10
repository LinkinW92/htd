package com.skeqi.mes.service.wf.productionQuality.ncCodeManager.record.impl;

import com.skeqi.mes.util.Rjson;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.productionQuality.ncCodeManager.record.MesNcCodeRecordTMapper;
import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.record.MesNcCodeRecordT;
import com.skeqi.mes.service.wf.productionQuality.ncCodeManager.record.MesNcCodeRecordTService;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class MesNcCodeRecordTServiceImpl implements MesNcCodeRecordTService{

    @Resource
    private MesNcCodeRecordTMapper mesNcCodeRecordTMapper;

    @Override
    public Rjson deleteByPrimaryKey(String number) throws Exception {
        MesNcCodeRecordT mesNcCodeRecordT = mesNcCodeRecordTMapper.selectByPrimaryKey(number);
        if (StringUtils.isEmpty(mesNcCodeRecordT)){
            throw new Exception("删除失败!数据不存在");
        }
        Integer integer =  mesNcCodeRecordTMapper.deleteByPrimaryKey(number);
        if (integer<1){
            throw new Exception("删除失败!请稍后重试");
        }
        return Rjson.success();
    }

    @Override
    public Rjson insertSelective(MesNcCodeRecordT record) throws Exception {
        Integer integer =  mesNcCodeRecordTMapper.insertSelective(record);
        if (integer<1){
            throw new Exception("新增失败!请稍后重试");
        }
        return Rjson.success();
    }

    @Override
    public MesNcCodeRecordT selectByPrimaryKey(String number) {
        return mesNcCodeRecordTMapper.selectByPrimaryKey(number);
    }

    @Override
    public Rjson updateByStatus(MesNcCodeRecordT record) throws Exception {
        MesNcCodeRecordT mesNcCodeRecordT = mesNcCodeRecordTMapper.selectByPrimaryKey(record.getNumber());
        if (StringUtils.isEmpty(mesNcCodeRecordT)){
            throw new Exception("关闭失败!数据不存在");
        }
        Integer integer =  mesNcCodeRecordTMapper.updateByStatus(record);
        if (integer<1){
            throw new Exception("关闭失败!请稍后重试");
        }
        return Rjson.success();
    }

    @Override
    public List<MesNcCodeRecordT> selectAll(MesNcCodeRecordT mesNcCodeRecordT) {
        return mesNcCodeRecordTMapper.selectAll(mesNcCodeRecordT);
    }

    @Override
    public Integer addNcCodeRecordByList(List<MesNcCodeRecordT> ncCodeRecordTList) {
        return mesNcCodeRecordTMapper.addNcCodeRecordByList(ncCodeRecordTList);
    }

}

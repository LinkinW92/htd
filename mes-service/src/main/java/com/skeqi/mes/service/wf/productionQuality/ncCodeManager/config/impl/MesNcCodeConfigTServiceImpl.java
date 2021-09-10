package com.skeqi.mes.service.wf.productionQuality.ncCodeManager.config.impl;

import com.skeqi.mes.util.Rjson;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigT;
import com.skeqi.mes.mapper.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigTMapper;
import com.skeqi.mes.service.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigTService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MesNcCodeConfigTServiceImpl implements MesNcCodeConfigTService {

    @Resource
    private MesNcCodeConfigTMapper mesNcCodeConfigTMapper;

    @Override
    public Rjson deleteByPrimaryKey(Integer id) throws Exception {
        Integer integer = mesNcCodeConfigTMapper.deleteByPrimaryKey(id);
        if (integer<1){
            throw new Exception("删除失败!请稍后重试");
        }
        return Rjson.success();
    }

    @Override
    public Rjson insertSelective(MesNcCodeConfigT record) throws Exception {
         Integer integer =mesNcCodeConfigTMapper.insertSelective(record);
        if (integer<1){
            throw new Exception("新增失败!请稍后重试");
        }
        return Rjson.success();
    }

    @Override
    public MesNcCodeConfigT selectByPrimaryKey(Integer id) {
        return mesNcCodeConfigTMapper.selectByPrimaryKey(id);
    }

    @Override
    public Rjson updateByPrimaryKeySelective(MesNcCodeConfigT record) throws Exception {
        Integer integer =  mesNcCodeConfigTMapper.updateByPrimaryKeySelective(record);
        if (integer<1){
            throw new Exception("编辑失败!请稍后重试");
        }
        return Rjson.success();
    }

    @Override
    public List<MesNcCodeConfigT> selectAll(MesNcCodeConfigT mesNcCodeConfigT) {

        return mesNcCodeConfigTMapper.selectAll(mesNcCodeConfigT);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Rjson verifySn(String sn) throws Exception {
        //验证是否拦截
        List<MesNcCodeConfigT> configTList =  mesNcCodeConfigTMapper.verifySn(sn);
        if (configTList.size()>0){
            throw new Exception("请关闭当前总成号对应的不合格记录!!!");
        }
        return Rjson.success();
    }

}

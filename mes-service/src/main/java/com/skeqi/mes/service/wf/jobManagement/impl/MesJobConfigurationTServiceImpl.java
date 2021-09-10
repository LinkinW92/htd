package com.skeqi.mes.service.wf.jobManagement.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.jobManagement.MesJobConfigurationTMapper;
import com.skeqi.mes.pojo.wf.jobManagement.MesJobConfigurationT;
import com.skeqi.mes.service.wf.jobManagement.MesJobConfigurationTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MesJobConfigurationTServiceImpl implements MesJobConfigurationTService{

    @Resource
    private MesJobConfigurationTMapper mesJobConfigurationTMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mesJobConfigurationTMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(MesJobConfigurationT record) throws Exception {
        MesJobConfigurationT mesJobConfigurationT = selectByPrimaryKey(record.getCode());
        if (!StringUtils.isEmpty(mesJobConfigurationT)) {
            throw new Exception("编码已存在,请验证后重试!!!");
        }
        return mesJobConfigurationTMapper.insertSelective(record);
    }

    @Override
    public MesJobConfigurationT selectByPrimaryKey(String code) {
        return mesJobConfigurationTMapper.selectByPrimaryKey(code);
    }

    @Override
    public int updateByPrimaryKeySelective(MesJobConfigurationT record) {
        return mesJobConfigurationTMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<MesJobConfigurationT> selectAll() {
        return mesJobConfigurationTMapper.selectAll();
    }

}

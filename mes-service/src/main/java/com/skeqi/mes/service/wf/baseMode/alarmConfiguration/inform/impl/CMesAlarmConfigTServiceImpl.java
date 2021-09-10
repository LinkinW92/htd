package com.skeqi.mes.service.wf.baseMode.alarmConfiguration.inform.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.baseMode.alarmConfiguration.inform.CMesAlarmConfigTMapper;
import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.inform.CMesAlarmConfigT;
import com.skeqi.mes.service.wf.baseMode.alarmConfiguration.inform.CMesAlarmConfigTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CMesAlarmConfigTServiceImpl implements CMesAlarmConfigTService{

    @Resource
    private CMesAlarmConfigTMapper cMesAlarmConfigTMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cMesAlarmConfigTMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insertSelective(CMesAlarmConfigT record) throws Exception {
        CMesAlarmConfigT result = selectByCode(record.getCode());
        if (!StringUtils.isEmpty(result)){
            throw new Exception("告警配置信息已存在!");
        }
        return cMesAlarmConfigTMapper.insertSelective(record);
    }

    @Override
    public CMesAlarmConfigT selectByCode(String code) {
        return cMesAlarmConfigTMapper.selectByCode(code);
    }

    @Override
    public List<CMesAlarmConfigT> selectAll() {
        return cMesAlarmConfigTMapper.selectAll();
    }

    @Override
    public List<Map<String, Object>> selectSqlBySql(String sql) {
        return cMesAlarmConfigTMapper.selectSqlBySql(sql);
    }

    @Override
    public int updateByPrimaryKeySelective(CMesAlarmConfigT record) {
        return cMesAlarmConfigTMapper.updateByPrimaryKeySelective(record);
    }
}

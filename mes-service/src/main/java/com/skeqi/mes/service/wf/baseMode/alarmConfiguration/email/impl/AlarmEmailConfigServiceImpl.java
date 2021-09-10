package com.skeqi.mes.service.wf.baseMode.alarmConfiguration.email.impl;

import com.skeqi.mes.mapper.wf.baseMode.alarmConfiguration.email.AlarmEmailConfigMapper;
import com.skeqi.mes.pojo.wf.baseMode.alarmConfiguration.email.AlarmEmailConfig;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.skeqi.mes.service.wf.baseMode.alarmConfiguration.email.AlarmEmailConfigService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AlarmEmailConfigServiceImpl implements AlarmEmailConfigService {

    @Resource
    private AlarmEmailConfigMapper alarmEmailConfigMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return alarmEmailConfigMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(AlarmEmailConfig record) throws Exception {
        AlarmEmailConfig cAlarmEmailConfig = alarmEmailConfigMapper.selectBySenderEmail(record.getSenderEmail());
        if (!StringUtils.isEmpty(cAlarmEmailConfig)){
            throw new Exception("发件人已存在!!!");
        }
        if (record.getIfEnable()==1){
            cAlarmEmailConfig = new AlarmEmailConfig();
            //修改其他发件人状态为0
            cAlarmEmailConfig.setSenderEmail(record.getSenderEmail());
            cAlarmEmailConfig.setIfEnable(0);
            alarmEmailConfigMapper.updateIfEnableBySenderEmail(cAlarmEmailConfig);
        }
        return alarmEmailConfigMapper.insertSelective(record);
    }

    @Override
    public AlarmEmailConfig selectBySenderEmail(String senderEmail) {
        return alarmEmailConfigMapper.selectBySenderEmail(senderEmail);
    }

    @Override
    public int updateByPrimaryKeySelective(AlarmEmailConfig record) {
        if (record.getIfEnable()==1){
            AlarmEmailConfig cAlarmEmailConfig = new AlarmEmailConfig();
            //修改其他发件人状态为0
            cAlarmEmailConfig.setSenderEmail(record.getSenderEmail());
            cAlarmEmailConfig.setIfEnable(0);
            alarmEmailConfigMapper.updateIfEnableBySenderEmail(cAlarmEmailConfig);
        }
        return alarmEmailConfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<AlarmEmailConfig> selectAll() {
        return alarmEmailConfigMapper.selectAll();
    }

    @Override
    public AlarmEmailConfig selectByIfEnable(int i) {
        return alarmEmailConfigMapper.selectByIfEnable(i);
    }

}

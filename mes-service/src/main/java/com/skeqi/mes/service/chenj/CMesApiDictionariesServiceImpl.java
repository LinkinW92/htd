package com.skeqi.mes.service.chenj;


import com.skeqi.mes.mapper.chenj.CMesApiDictionariesDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenj
 * @Description: API管理业务实现处理
 * @DateTime: 2021/3/18 20:04
 **/
@Service
public class CMesApiDictionariesServiceImpl implements CMesApiDictionariesService {

    @Resource
    private CMesApiDictionariesDao cMesApiDictionariesDao;

    @Override
    public List<Map<String, Object>> findApiOperationList(Map<String, Object> map) {
        return cMesApiDictionariesDao.findApiOperationList(map);
    }


    @Override
    public List<Map<String, Object>> findApiDictionariesList(Map<String, Object> map) {
        return cMesApiDictionariesDao.findApiDictionariesList(map);
    }

    @Override
    public Integer addApiDictionaries(Map<String, Object> map) {
        return cMesApiDictionariesDao.addApiDictionaries(map);
    }

    @Override
    public Integer selectApiDictionaries(String actionurl) {
        return cMesApiDictionariesDao.selectApiDictionaries(actionurl);
    }

    @Override
    public Integer delApiOperationData(int isDelete,Integer[] id) {
        return cMesApiDictionariesDao.delApiOperationData(isDelete,id);
    }
}

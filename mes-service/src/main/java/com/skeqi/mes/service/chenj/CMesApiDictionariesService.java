package com.skeqi.mes.service.chenj;

import java.util.List;
import java.util.Map;

/**
 * @Author: chenj
 * @Description: API管理业务定义处理
 * @DateTime: 2021/3/18 19:59
 **/
public interface CMesApiDictionariesService {

    List<Map<String, Object>> findApiOperationList(Map<String, Object> map);
    List<Map<String, Object>> findApiDictionariesList(Map<String, Object> map);
    // 用作字典表插值用
    Integer addApiDictionaries(Map<String, Object> map);
    // 用作字典表插值用
    Integer selectApiDictionaries(String actionurl);
    // 删除日志记录
    Integer delApiOperationData(int isDelete, Integer[] id);
}

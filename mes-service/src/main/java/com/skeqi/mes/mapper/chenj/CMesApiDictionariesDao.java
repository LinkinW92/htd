package com.skeqi.mes.mapper.chenj;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: chenj
 * @Description: API管理数据接口
 * @DateTime: 2021/3/18 19:49
 **/
public interface CMesApiDictionariesDao {

    List<Map<String, Object>> findApiOperationList(Map<String, Object> map);
    List<Map<String, Object>> findApiDictionariesList(Map<String, Object> map);
    // 用作字典表插值用
    Integer addApiDictionaries(Map<String, Object> map);
    // 用作字典表插值用
    Integer selectApiDictionaries(String actionurl);
    // 删除日志记录
    Integer delApiOperationData(@Param("isDelete") int isDelete, @Param("ids") Integer[] id);
}

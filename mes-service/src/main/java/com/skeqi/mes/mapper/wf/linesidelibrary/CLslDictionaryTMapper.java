package com.skeqi.mes.mapper.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.CLslDictionaryT;

import java.util.List;

public interface CLslDictionaryTMapper {
    List<CLslDictionaryT> selectAll();

    Integer deleteDictionaryById(Integer id);

    Integer insertDictionary(CLslDictionaryT cLslDictionaryT);

    CLslDictionaryT selectByKey(String key);

    Integer updateDictionary(CLslDictionaryT cLslDictionaryT);
}

package com.skeqi.mes.service.wf.linesidelibrary;

import com.skeqi.mes.pojo.wf.linesidelibrary.CLslDictionaryT;
import com.skeqi.mes.util.Rjson;

import java.util.List;

/**
 * @author Lenovo
 */
public interface CLslDictionaryTService{
    List<CLslDictionaryT> selectAll();

    Rjson deleteDictionaryById(Integer id) throws Exception;

    Rjson insertDictionary(CLslDictionaryT cLslDictionaryT) throws Exception;

    Rjson updateDictionary(CLslDictionaryT cLslDictionaryT) throws Exception;
}

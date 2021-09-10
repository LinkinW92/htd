package com.skeqi.mes.service.wf.linesidelibrary.impl;

import com.skeqi.mes.util.Rjson;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.linesidelibrary.CLslDictionaryTMapper;
import com.skeqi.mes.pojo.wf.linesidelibrary.CLslDictionaryT;
import com.skeqi.mes.service.wf.linesidelibrary.CLslDictionaryTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CLslDictionaryTServiceImpl implements CLslDictionaryTService{

    @Resource
    private CLslDictionaryTMapper cLslDictionaryTMapper;

    @Override
    public List<CLslDictionaryT> selectAll() {
        return cLslDictionaryTMapper.selectAll();
    }

    @Override
    public Rjson deleteDictionaryById(Integer id) throws Exception {
        Integer result = cLslDictionaryTMapper.deleteDictionaryById(id);
        if (result<1){
            throw new Exception("删除失败");
        }
        return Rjson.success("删除成功");
    }

    @Override
    public Rjson insertDictionary(CLslDictionaryT cLslDictionaryT) throws Exception {
        //验证唯一
        CLslDictionaryT lslDictionaryT = cLslDictionaryTMapper.selectByKey(cLslDictionaryT.getKey());
        if (!StringUtils.isEmpty(lslDictionaryT)){
            throw new Exception("属性已存在!!!");
        }
        Integer result = cLslDictionaryTMapper.insertDictionary(cLslDictionaryT);
        if (result<1){
            throw new Exception("新增失败");
        }
        return Rjson.success("新增成功");
    }

    @Override
    public Rjson updateDictionary(CLslDictionaryT cLslDictionaryT) throws Exception {
        Integer result = cLslDictionaryTMapper.updateDictionary(cLslDictionaryT);
        if (result<1){
            throw new Exception("编辑失败");
        }
        return Rjson.success("编辑成功");
    }
}

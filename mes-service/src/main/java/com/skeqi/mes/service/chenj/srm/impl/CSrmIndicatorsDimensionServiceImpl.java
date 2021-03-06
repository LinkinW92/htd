package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.mapper.chenj.srm.CSrmIndicatorsDimensionMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmIndicatorsDimension;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmIndicatorsDimensionReq;
import com.skeqi.mes.service.chenj.srm.CSrmIndicatorsDimensionService;
import com.skeqi.mes.util.Rjson;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmIndicatorsDimensionServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmIndicatorsDimensionServiceImpl implements CSrmIndicatorsDimensionService {

    @Resource
    private CSrmIndicatorsDimensionMapper cSrmIndicatorsDimensionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmIndicatorsDimensionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmIndicatorsDimension record) {
        return cSrmIndicatorsDimensionMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmIndicatorsDimension record) {
        return cSrmIndicatorsDimensionMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmIndicatorsDimension record) {
        return cSrmIndicatorsDimensionMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmIndicatorsDimension record) {
        return cSrmIndicatorsDimensionMapper.insertSelective(record);
    }

    @Override
    public CSrmIndicatorsDimension selectByPrimaryKey(CSrmIndicatorsDimension record) {
        return cSrmIndicatorsDimensionMapper.selectByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmIndicatorsDimension record) {
        return cSrmIndicatorsDimensionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmIndicatorsDimension record) {
        return cSrmIndicatorsDimensionMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmIndicatorsDimension> list) {
        return cSrmIndicatorsDimensionMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmIndicatorsDimension> list) {
        return cSrmIndicatorsDimensionMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmIndicatorsDimension> list) {
        return cSrmIndicatorsDimensionMapper.batchInsert(list);
    }

    @Override
    public Rjson createPerformanceEvaluation(CSrmIndicatorsDimensionReq cSrmIndicatorsDimensionReq) {
        // ??????????????????????????????
        CSrmIndicatorsDimension cSrmIndicatorsDimension = new CSrmIndicatorsDimension();
        cSrmIndicatorsDimension.setIndexCoding(cSrmIndicatorsDimensionReq.getIndexCoding());
        CSrmIndicatorsDimension cSrmIndicatorsDimension1 = cSrmIndicatorsDimensionMapper.selectByPrimaryKey(cSrmIndicatorsDimension);
        if (cSrmIndicatorsDimension1 == null) {
            // ????????????????????????????????????????????????
            BigDecimal scoreStart = new BigDecimal(cSrmIndicatorsDimensionReq.getScoreStart());
            BigDecimal scoreStop = new BigDecimal(cSrmIndicatorsDimensionReq.getScoreStop());
            if (scoreStart.compareTo(scoreStop) == 0) {
                return Rjson.error("?????????????????????????????????????????????");
            } else if (scoreStart.compareTo(scoreStop) > 0) {
                return Rjson.error("??????????????????????????????????????????");
            }
            cSrmIndicatorsDimension = new CSrmIndicatorsDimension();
            BeanUtils.copyProperties(cSrmIndicatorsDimensionReq, cSrmIndicatorsDimension);
            cSrmIndicatorsDimension.setCreateTime(new Date());
            cSrmIndicatorsDimensionMapper.insertOrUpdateSelective(cSrmIndicatorsDimension);
            return Rjson.success("????????????", cSrmIndicatorsDimensionReq);
        } else {
            return Rjson.error("?????????????????????");
        }


    }

    @Override
    public Rjson findPerformanceEvaluation(CSrmIndicatorsDimensionReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmIndicatorsDimensionMapper.findPerformanceEvaluation(req)));
    }


}



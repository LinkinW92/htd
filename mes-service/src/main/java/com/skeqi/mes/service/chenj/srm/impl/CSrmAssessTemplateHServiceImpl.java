package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.chenj.srm.CSrmAssessTemplateHMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmAssessTemplateRMapper;
import com.skeqi.mes.mapper.chenj.srm.CSrmIndicatorsDimensionMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmAssessTemplateH;
import com.skeqi.mes.pojo.chenj.srm.CSrmAssessTemplateR;
import com.skeqi.mes.pojo.chenj.srm.CSrmIndicatorsDimension;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmAssessTemplateHReq;
import com.skeqi.mes.service.chenj.srm.CSrmAssessTemplateHService;
import com.skeqi.mes.util.Rjson;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmAssessTemplateHServiceImpl
 * @Description ${Description}
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CSrmAssessTemplateHServiceImpl implements CSrmAssessTemplateHService {

    @Resource
    private CSrmAssessTemplateHMapper cSrmAssessTemplateHMapper;
    @Resource
    private CSrmAssessTemplateRMapper cSrmAssessTemplateRMapper;
    @Resource
    private CSrmIndicatorsDimensionMapper cSrmIndicatorsDimensionMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmAssessTemplateHMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.insertSelective(record);
    }

    @Override
    public CSrmAssessTemplateH selectByPrimaryKey(Integer id) {
        return cSrmAssessTemplateHMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmAssessTemplateH record) {
        return cSrmAssessTemplateHMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmAssessTemplateH> list) {
        return cSrmAssessTemplateHMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmAssessTemplateH> list) {
        return cSrmAssessTemplateHMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmAssessTemplateH> list) {
        return cSrmAssessTemplateHMapper.batchInsert(list);
    }

    @Override
    public Rjson createEvaluationTemplate(CSrmAssessTemplateHReq cSrmAssessTemplateHReq) {

        // ???????????????????????????????????????????????????
        CSrmIndicatorsDimension cSrmIndicatorsDimension = new CSrmIndicatorsDimension();
        cSrmIndicatorsDimension.setIndexCoding(cSrmAssessTemplateHReq.getIndexCode());
        CSrmIndicatorsDimension cSrmIndicatorsDimension1 = cSrmIndicatorsDimensionMapper.selectByPrimaryKey(cSrmIndicatorsDimension);
        if (null != cSrmIndicatorsDimension1) {
            // ???????????????????????????????????????R????????????
            CSrmAssessTemplateR cSrmAssessTemplateR = new CSrmAssessTemplateR();
            cSrmAssessTemplateR.setIndexCode(cSrmAssessTemplateHReq.getIndexCode());
            CSrmAssessTemplateR selectByPrimaryKey = cSrmAssessTemplateRMapper.selectByPrimaryKey(cSrmAssessTemplateR);
            if (selectByPrimaryKey == null) {
                // ????????????????????????PEM??????+?????????+3???????????????????????????????????????????????????????????????
                CSrmAssessTemplateH cSrmAssessTemplateH = cSrmAssessTemplateHMapper.selectFinallyData();
                if (cSrmAssessTemplateH == null) {
                    cSrmAssessTemplateH = new CSrmAssessTemplateH();
                    // ???????????????????????????????????????
                    String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                    cSrmAssessTemplateH.setTemplateCode("PEM" + yyyyMMdd + 100);
                } else {
                    String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(new Date());
                    int requestCode = Integer.parseInt(cSrmAssessTemplateH.getTemplateCode().substring(11)) + 1;
                    cSrmAssessTemplateH.setTemplateCode("PEM" + yyyyMMdd + requestCode);
                }

                // ????????????????????????
                String templateCode = cSrmAssessTemplateH.getTemplateCode();
                BeanUtils.copyProperties(cSrmAssessTemplateHReq, cSrmAssessTemplateH);
                cSrmAssessTemplateH.setStatus("1");
                cSrmAssessTemplateH.setTemplateCode(templateCode);
                cSrmAssessTemplateH.setCreateTime(new Date());
                cSrmAssessTemplateHMapper.insertOrUpdateSelective(cSrmAssessTemplateH);
                // ????????????????????????
                BeanUtils.copyProperties(cSrmAssessTemplateHReq, cSrmAssessTemplateR);
                cSrmAssessTemplateR.setCreateTime(new Date());
                cSrmAssessTemplateR.setTemplateCode(templateCode);
                cSrmAssessTemplateRMapper.insertOrUpdateSelective(cSrmAssessTemplateR);
                return Rjson.success("????????????", cSrmAssessTemplateHReq);
            } else {
                return Rjson.error("?????????????????????????????????????????????");
            }

        } else {
            return Rjson.error("?????????????????????????????????????????????");
        }
    }

    @Override
    public Rjson updateEvaluationTemplate(CSrmAssessTemplateHReq cSrmAssessTemplateHReq) {
        // ??????????????????????????????
        CSrmAssessTemplateR cSrmAssessTemplateR = new CSrmAssessTemplateR();
        cSrmAssessTemplateR.setTemplateCode(cSrmAssessTemplateHReq.getTemplateCode());
        CSrmAssessTemplateR selectByPrimaryKey = cSrmAssessTemplateRMapper.selectByPrimaryKey(cSrmAssessTemplateR);
        if (null != selectByPrimaryKey) {
            // ????????????
            cSrmAssessTemplateR = new CSrmAssessTemplateR();
            cSrmAssessTemplateR.setTemplateCode(cSrmAssessTemplateHReq.getTemplateCode());
            cSrmAssessTemplateR.setVersion(cSrmAssessTemplateHReq.getVersion());
            cSrmAssessTemplateR.setUpdateTime(new Date());
            cSrmAssessTemplateRMapper.updateByPrimaryKeySelective(cSrmAssessTemplateR);
            // ????????????
            CSrmAssessTemplateH cSrmAssessTemplateH = new CSrmAssessTemplateH();
            cSrmAssessTemplateH.setTemplateCode(cSrmAssessTemplateHReq.getTemplateCode());
            cSrmAssessTemplateH.setVersion(cSrmAssessTemplateHReq.getVersion());
            cSrmAssessTemplateH.setStatus(cSrmAssessTemplateHReq.getUpdateSign());
            cSrmAssessTemplateH.setUpdateTime(new Date());
            cSrmAssessTemplateHMapper.updateByPrimaryKeySelective(cSrmAssessTemplateH);
            return Rjson.success("????????????", null);
        } else {
            return Rjson.error("?????????????????????????????????????????????");
        }


    }

    @Override
    public Rjson findEvaluationTemplate(CSrmAssessTemplateHReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmAssessTemplateHMapper.findEvaluationTemplate(req)));

    }
}


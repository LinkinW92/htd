package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.init.TGlInitDimensionAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.init.TGlInitDimensionQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.init.TGlInitDimensionEditBo;
import com.skeqi.finance.domain.TGlInitDimension;
import com.skeqi.finance.mapper.TGlInitDimensionMapper;
import com.skeqi.finance.pojo.vo.TGlInitDimensionVo;
import com.skeqi.finance.service.init.ITGlInitDimensionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 科目核算维度初始数据Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TGlInitDimensionServiceImpl extends ServicePlusImpl<TGlInitDimensionMapper, TGlInitDimension> implements ITGlInitDimensionService {

    @Override
    public TGlInitDimensionVo queryById(Integer fId){
        return getVoById(fId, TGlInitDimensionVo.class);
    }

    @Override
    public TableDataInfo<TGlInitDimensionVo> queryPageList(TGlInitDimensionQueryBo bo) {
        PagePlus<TGlInitDimension, TGlInitDimensionVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlInitDimensionVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlInitDimensionVo> queryList(TGlInitDimensionQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlInitDimensionVo.class);
    }

    private LambdaQueryWrapper<TGlInitDimension> buildQueryWrapper(TGlInitDimensionQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlInitDimension> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFAcctId() != null, TGlInitDimension::getFAcctId, bo.getFAcctId());
        lqw.eq(bo.getFBookId() != null, TGlInitDimension::getFBookId, bo.getFBookId());
        lqw.eq(bo.getFDimensionId() != null, TGlInitDimension::getFDimensionId, bo.getFDimensionId());
        lqw.like(StrUtil.isNotBlank(bo.getFDimensionName()), TGlInitDimension::getFDimensionName, bo.getFDimensionName());
        lqw.eq(bo.getFBeginBalancefor() != null, TGlInitDimension::getFBeginBalancefor, bo.getFBeginBalancefor());
        lqw.eq(bo.getFBeginBalance() != null, TGlInitDimension::getFBeginBalance, bo.getFBeginBalance());
        lqw.eq(bo.getFDebitFor() != null, TGlInitDimension::getFDebitFor, bo.getFDebitFor());
        lqw.eq(bo.getFDebit() != null, TGlInitDimension::getFDebit, bo.getFDebit());
        lqw.eq(bo.getFCreditFor() != null, TGlInitDimension::getFCreditFor, bo.getFCreditFor());
        lqw.eq(bo.getFCredit() != null, TGlInitDimension::getFCredit, bo.getFCredit());
        lqw.eq(bo.getFYtdDebitfor() != null, TGlInitDimension::getFYtdDebitfor, bo.getFYtdDebitfor());
        lqw.eq(bo.getFYtdDebit() != null, TGlInitDimension::getFYtdDebit, bo.getFYtdDebit());
        lqw.eq(bo.getFYtdCreditfor() != null, TGlInitDimension::getFYtdCreditfor, bo.getFYtdCreditfor());
        lqw.eq(bo.getFYtdCredit() != null, TGlInitDimension::getFYtdCredit, bo.getFYtdCredit());
        lqw.eq(bo.getFEndBalancefor() != null, TGlInitDimension::getFEndBalancefor, bo.getFEndBalancefor());
        lqw.eq(bo.getFEndBalance() != null, TGlInitDimension::getFEndBalance, bo.getFEndBalance());
        lqw.eq(bo.getFCreateOrgid() != null, TGlInitDimension::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TGlInitDimension::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TGlInitDimension::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TGlInitDimension::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TGlInitDimension::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TGlInitDimension::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TGlInitDimension::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TGlInitDimension::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TGlInitDimension::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TGlInitDimension::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TGlInitDimension::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TGlInitDimension::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TGlInitDimension::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TGlInitDimension::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlInitDimensionAddBo bo) {
        TGlInitDimension add = BeanUtil.toBean(bo, TGlInitDimension.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlInitDimensionEditBo bo) {
        TGlInitDimension update = BeanUtil.toBean(bo, TGlInitDimension.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlInitDimension entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}

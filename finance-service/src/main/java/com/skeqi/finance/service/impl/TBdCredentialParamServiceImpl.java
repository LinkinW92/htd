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
import com.skeqi.finance.pojo.bo.TBdCredentialParamAddBo;
import com.skeqi.finance.pojo.bo.TBdCredentialParamQueryBo;
import com.skeqi.finance.pojo.bo.TBdCredentialParamEditBo;
import com.skeqi.finance.domain.TBdCredentialParam;
import com.skeqi.finance.mapper.TBdCredentialParamMapper;
import com.skeqi.finance.pojo.vo.TBdCredentialParamVo;
import com.skeqi.finance.service.ITBdCredentialParamService;

import java.util.List;
import java.util.Collection;

/**
 * 凭证Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdCredentialParamServiceImpl extends ServicePlusImpl<TBdCredentialParamMapper, TBdCredentialParam> implements ITBdCredentialParamService {

    @Override
    public TBdCredentialParamVo queryById(Integer fId){
        return getVoById(fId, TBdCredentialParamVo.class);
    }

    @Override
    public TableDataInfo<TBdCredentialParamVo> queryPageList(TBdCredentialParamQueryBo bo) {
        PagePlus<TBdCredentialParam, TBdCredentialParamVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdCredentialParamVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdCredentialParamVo> queryList(TBdCredentialParamQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdCredentialParamVo.class);
    }

    private LambdaQueryWrapper<TBdCredentialParam> buildQueryWrapper(TBdCredentialParamQueryBo bo) {
        LambdaQueryWrapper<TBdCredentialParam> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFBaseOne() != null, TBdCredentialParam::getFBaseOne, bo.getFBaseOne());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseTow()), TBdCredentialParam::getFBaseTow, bo.getFBaseTow());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseThree()), TBdCredentialParam::getFBaseThree, bo.getFBaseThree());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseFour()), TBdCredentialParam::getFBaseFour, bo.getFBaseFour());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseFive()), TBdCredentialParam::getFBaseFive, bo.getFBaseFive());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseSix()), TBdCredentialParam::getFBaseSix, bo.getFBaseSix());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseSeven()), TBdCredentialParam::getFBaseSeven, bo.getFBaseSeven());
        lqw.eq(StrUtil.isNotBlank(bo.getFCashFlowOne()), TBdCredentialParam::getFCashFlowOne, bo.getFCashFlowOne());
        lqw.eq(StrUtil.isNotBlank(bo.getFCashFlowTwo()), TBdCredentialParam::getFCashFlowTwo, bo.getFCashFlowTwo());
        lqw.eq(StrUtil.isNotBlank(bo.getFCashFlowThree()), TBdCredentialParam::getFCashFlowThree, bo.getFCashFlowThree());
        lqw.eq(StrUtil.isNotBlank(bo.getFCashFlowFour()), TBdCredentialParam::getFCashFlowFour, bo.getFCashFlowFour());
        lqw.eq(StrUtil.isNotBlank(bo.getFCashFlowFive()), TBdCredentialParam::getFCashFlowFive, bo.getFCashFlowFive());
        lqw.eq(StrUtil.isNotBlank(bo.getFDataValidationOne()), TBdCredentialParam::getFDataValidationOne, bo.getFDataValidationOne());
        lqw.eq(StrUtil.isNotBlank(bo.getFDataValidationTwo()), TBdCredentialParam::getFDataValidationTwo, bo.getFDataValidationTwo());
        lqw.eq(StrUtil.isNotBlank(bo.getFDataValidationThree()), TBdCredentialParam::getFDataValidationThree, bo.getFDataValidationThree());
        lqw.eq(StrUtil.isNotBlank(bo.getFVoucherNumberOne()), TBdCredentialParam::getFVoucherNumberOne, bo.getFVoucherNumberOne());
        lqw.eq(StrUtil.isNotBlank(bo.getFVoucherNumberTwo()), TBdCredentialParam::getFVoucherNumberTwo, bo.getFVoucherNumberTwo());
        lqw.eq(StrUtil.isNotBlank(bo.getFVoucherNumberThree()), TBdCredentialParam::getFVoucherNumberThree, bo.getFVoucherNumberThree());
        lqw.eq(StrUtil.isNotBlank(bo.getFVoucherNumberFour()), TBdCredentialParam::getFVoucherNumberFour, bo.getFVoucherNumberFour());
        lqw.eq(StrUtil.isNotBlank(bo.getFOtherOne()), TBdCredentialParam::getFOtherOne, bo.getFOtherOne());
        lqw.eq(StrUtil.isNotBlank(bo.getFOtherTwo()), TBdCredentialParam::getFOtherTwo, bo.getFOtherTwo());
        lqw.eq(StrUtil.isNotBlank(bo.getFOtherThree()), TBdCredentialParam::getFOtherThree, bo.getFOtherThree());
        lqw.eq(StrUtil.isNotBlank(bo.getFOtherFour()), TBdCredentialParam::getFOtherFour, bo.getFOtherFour());
        lqw.eq(bo.getFBookId() != null, TBdCredentialParam::getFBookId, bo.getFBookId());
        lqw.eq(bo.getFCreateOrgid() != null, TBdCredentialParam::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdCredentialParam::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdCredentialParam::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdCredentialParam::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdCredentialParam::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdCredentialParam::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdCredentialParam::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdCredentialParam::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdCredentialParam::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdCredentialParam::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdCredentialParam::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdCredentialParam::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdCredentialParam::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdCredentialParam::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdCredentialParamAddBo bo) {
        TBdCredentialParam add = BeanUtil.toBean(bo, TBdCredentialParam.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdCredentialParamEditBo bo) {
        TBdCredentialParam update = BeanUtil.toBean(bo, TBdCredentialParam.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdCredentialParam entity){
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

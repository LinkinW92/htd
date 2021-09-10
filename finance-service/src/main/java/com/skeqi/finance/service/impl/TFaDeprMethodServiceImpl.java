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
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEditBo;
import com.skeqi.finance.domain.TFaDeprMethod;
import com.skeqi.finance.mapper.TFaDeprMethodMapper;
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprMethodVo;
import com.skeqi.finance.service.depr.ITFaDeprMethodService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 折旧方法Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TFaDeprMethodServiceImpl extends ServicePlusImpl<TFaDeprMethodMapper, TFaDeprMethod> implements ITFaDeprMethodService {

    @Override
    public TFaDeprMethodVo queryById(Integer fId){
        return getVoById(fId, TFaDeprMethodVo.class);
    }

    @Override
    public TableDataInfo<TFaDeprMethodVo> queryPageList(TFaDeprMethodQueryBo bo) {
        PagePlus<TFaDeprMethod, TFaDeprMethodVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TFaDeprMethodVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TFaDeprMethodVo> queryList(TFaDeprMethodQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFaDeprMethodVo.class);
    }

    private LambdaQueryWrapper<TFaDeprMethod> buildQueryWrapper(TFaDeprMethodQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TFaDeprMethod> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TFaDeprMethod::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFDeprOption()), TFaDeprMethod::getFDeprOption, bo.getFDeprOption());
        lqw.eq(StrUtil.isNotBlank(bo.getFCalcWay()), TFaDeprMethod::getFCalcWay, bo.getFCalcWay());
        lqw.eq(bo.getFCreateOrgid() != null, TFaDeprMethod::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TFaDeprMethod::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TFaDeprMethod::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TFaDeprMethod::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TFaDeprMethod::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TFaDeprMethod::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TFaDeprMethod::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TFaDeprMethod::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TFaDeprMethod::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TFaDeprMethod::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TFaDeprMethod::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TFaDeprMethod::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TFaDeprMethod::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TFaDeprMethod::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFaDeprMethodAddBo bo) {
        TFaDeprMethod add = BeanUtil.toBean(bo, TFaDeprMethod.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TFaDeprMethodEditBo bo) {
        TFaDeprMethod update = BeanUtil.toBean(bo, TFaDeprMethod.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TFaDeprMethod entity){
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

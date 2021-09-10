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
import com.skeqi.finance.pojo.bo.TBdBookParamAddBo;
import com.skeqi.finance.pojo.bo.TBdBookParamQueryBo;
import com.skeqi.finance.pojo.bo.TBdBookParamEditBo;
import com.skeqi.finance.domain.TBdBookParam;
import com.skeqi.finance.mapper.TBdBookParamMapper;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdBookParamVo;
import com.skeqi.finance.service.basicinformation.base.ITBdBookParamService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 总账管理参数-账簿参数Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdBookParamServiceImpl extends ServicePlusImpl<TBdBookParamMapper, TBdBookParam> implements ITBdBookParamService {

    @Override
    public TBdBookParamVo queryById(Integer fId){
        return getVoById(fId, TBdBookParamVo.class);
    }

    @Override
    public TableDataInfo<TBdBookParamVo> queryPageList(TBdBookParamQueryBo bo) {
        PagePlus<TBdBookParam, TBdBookParamVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdBookParamVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdBookParamVo> queryList(TBdBookParamQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdBookParamVo.class);
    }

    private LambdaQueryWrapper<TBdBookParam> buildQueryWrapper(TBdBookParamQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdBookParam> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFProfitDistributionAccount() != null, TBdBookParam::getFProfitDistributionAccount, bo.getFProfitDistributionAccount());
        lqw.eq(bo.getFCurrentYearProfitAccount() != null, TBdBookParam::getFCurrentYearProfitAccount, bo.getFCurrentYearProfitAccount());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseOne()), TBdBookParam::getFBaseOne, bo.getFBaseOne());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseTwo()), TBdBookParam::getFBaseTwo, bo.getFBaseTwo());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseThree()), TBdBookParam::getFBaseThree, bo.getFBaseThree());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseFour()), TBdBookParam::getFBaseFour, bo.getFBaseFour());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseFive()), TBdBookParam::getFBaseFive, bo.getFBaseFive());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseSix()), TBdBookParam::getFBaseSix, bo.getFBaseSix());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseSeven()), TBdBookParam::getFBaseSeven, bo.getFBaseSeven());
        lqw.eq(StrUtil.isNotBlank(bo.getFCheckoutOptionsOne()), TBdBookParam::getFCheckoutOptionsOne, bo.getFCheckoutOptionsOne());
        lqw.eq(StrUtil.isNotBlank(bo.getFCheckoutOptionsTwo()), TBdBookParam::getFCheckoutOptionsTwo, bo.getFCheckoutOptionsTwo());
        lqw.eq(StrUtil.isNotBlank(bo.getFCheckoutOptionsThree()), TBdBookParam::getFCheckoutOptionsThree, bo.getFCheckoutOptionsThree());
        lqw.eq(StrUtil.isNotBlank(bo.getFCheckoutOptionsFour()), TBdBookParam::getFCheckoutOptionsFour, bo.getFCheckoutOptionsFour());
        lqw.eq(StrUtil.isNotBlank(bo.getFCheckoutOptionsFive()), TBdBookParam::getFCheckoutOptionsFive, bo.getFCheckoutOptionsFive());
        lqw.eq(bo.getFBookId() != null, TBdBookParam::getFBookId, bo.getFBookId());
        lqw.eq(bo.getFCreateOrgid() != null, TBdBookParam::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdBookParam::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdBookParam::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdBookParam::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdBookParam::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdBookParam::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdBookParam::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdBookParam::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdBookParam::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdBookParam::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdBookParam::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdBookParam::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdBookParam::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdBookParam::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdBookParamAddBo bo) {
        TBdBookParam add = BeanUtil.toBean(bo, TBdBookParam.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdBookParamEditBo bo) {
        TBdBookParam update = BeanUtil.toBean(bo, TBdBookParam.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdBookParam entity){
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

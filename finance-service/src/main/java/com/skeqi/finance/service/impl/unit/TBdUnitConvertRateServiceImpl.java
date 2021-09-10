package com.skeqi.finance.service.impl.unit;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.unit.TBdUnitConvertRateAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitConvertRateQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitConvertRateEditBo;
import com.skeqi.finance.domain.unit.TBdUnitConvertRate;
import com.skeqi.finance.mapper.TBdUnitConvertRateMapper;
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitConvertRateVo;
import com.skeqi.finance.service.unit.ITBdUnitConvertRateService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 单位换算Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdUnitConvertRateServiceImpl extends ServicePlusImpl<TBdUnitConvertRateMapper, TBdUnitConvertRate> implements ITBdUnitConvertRateService {

    @Override
    public TBdUnitConvertRateVo queryById(Integer fUnitConvertRateid){
        return getVoById(fUnitConvertRateid, TBdUnitConvertRateVo.class);
    }

    @Override
    public TableDataInfo<TBdUnitConvertRateVo> queryPageList(TBdUnitConvertRateQueryBo bo) {
        PagePlus<TBdUnitConvertRate, TBdUnitConvertRateVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdUnitConvertRateVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdUnitConvertRateVo> queryList(TBdUnitConvertRateQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdUnitConvertRateVo.class);
    }

    private LambdaQueryWrapper<TBdUnitConvertRate> buildQueryWrapper(TBdUnitConvertRateQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdUnitConvertRate> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFBillNo()), TBdUnitConvertRate::getFBillNo, bo.getFBillNo());
        lqw.eq(bo.getFUnitId() != null, TBdUnitConvertRate::getFUnitId, bo.getFUnitId());
        lqw.eq(StrUtil.isNotBlank(bo.getFFormid()), TBdUnitConvertRate::getFFormid, bo.getFFormid());
        lqw.eq(bo.getFMaterialId() != null, TBdUnitConvertRate::getFMaterialId, bo.getFMaterialId());
        lqw.eq(bo.getFCurrentUnitId() != null, TBdUnitConvertRate::getFCurrentUnitId, bo.getFCurrentUnitId());
        lqw.eq(bo.getFDestUnitId() != null, TBdUnitConvertRate::getFDestUnitId, bo.getFDestUnitId());
        lqw.eq(StrUtil.isNotBlank(bo.getFConvertType()), TBdUnitConvertRate::getFConvertType, bo.getFConvertType());
        lqw.eq(bo.getFConvertNumerator() != null, TBdUnitConvertRate::getFConvertNumerator, bo.getFConvertNumerator());
        lqw.eq(bo.getFConvertDenominator() != null, TBdUnitConvertRate::getFConvertDenominator, bo.getFConvertDenominator());
        lqw.eq(bo.getFCreateOrgid() != null, TBdUnitConvertRate::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdUnitConvertRate::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdUnitConvertRate::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdUnitConvertRate::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdUnitConvertRate::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdUnitConvertRate::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdUnitConvertRate::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdUnitConvertRate::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdUnitConvertRate::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdUnitConvertRate::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdUnitConvertRate::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdUnitConvertRate::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdUnitConvertRate::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdUnitConvertRate::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdUnitConvertRateAddBo bo) {
        TBdUnitConvertRate add = BeanUtil.toBean(bo, TBdUnitConvertRate.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdUnitConvertRateEditBo bo) {
        TBdUnitConvertRate update = BeanUtil.toBean(bo, TBdUnitConvertRate.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdUnitConvertRate entity){
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

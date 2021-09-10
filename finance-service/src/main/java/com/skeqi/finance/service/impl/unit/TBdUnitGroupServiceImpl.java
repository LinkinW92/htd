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
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupEditBo;
import com.skeqi.finance.domain.unit.TBdUnitGroup;
import com.skeqi.finance.mapper.TBdUnitGroupMapper;
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitGroupVo;
import com.skeqi.finance.service.unit.ITBdUnitGroupService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 计量单位组Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdUnitGroupServiceImpl extends ServicePlusImpl<TBdUnitGroupMapper, TBdUnitGroup> implements ITBdUnitGroupService {

    @Override
    public TBdUnitGroupVo queryById(Integer fUnitGroupId){
        return getVoById(fUnitGroupId, TBdUnitGroupVo.class);
    }

    @Override
    public TableDataInfo<TBdUnitGroupVo> queryPageList(TBdUnitGroupQueryBo bo) {
        PagePlus<TBdUnitGroup, TBdUnitGroupVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdUnitGroupVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdUnitGroupVo> queryList(TBdUnitGroupQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdUnitGroupVo.class);
    }

    private LambdaQueryWrapper<TBdUnitGroup> buildQueryWrapper(TBdUnitGroupQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdUnitGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdUnitGroup::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdUnitGroup::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFBaseUnitNumber()), TBdUnitGroup::getFBaseUnitNumber, bo.getFBaseUnitNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFBaseUnitName()), TBdUnitGroup::getFBaseUnitName, bo.getFBaseUnitName());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdUnitGroup::getFDescription, bo.getFDescription());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsconvertenable()), TBdUnitGroup::getFIsconvertenable, bo.getFIsconvertenable());
        lqw.eq(StrUtil.isNotBlank(bo.getFGroup()), TBdUnitGroup::getFGroup, bo.getFGroup());
        lqw.eq(bo.getFCreateOrgid() != null, TBdUnitGroup::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdUnitGroup::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdUnitGroup::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdUnitGroup::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdUnitGroup::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdUnitGroup::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdUnitGroup::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdUnitGroup::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdUnitGroup::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdUnitGroup::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdUnitGroup::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdUnitGroup::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdUnitGroup::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdUnitGroup::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdUnitGroupAddBo bo) {
        TBdUnitGroup add = BeanUtil.toBean(bo, TBdUnitGroup.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdUnitGroupEditBo bo) {
        TBdUnitGroup update = BeanUtil.toBean(bo, TBdUnitGroup.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdUnitGroup entity){
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

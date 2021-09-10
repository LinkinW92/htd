package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.finance.domain.TGlExplanation;
import com.skeqi.finance.mapper.TGlExplanationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationTypeAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationTypeQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationTypeEditBo;
import com.skeqi.finance.domain.TGlExplanationType;
import com.skeqi.finance.mapper.TGlExplanationTypeMapper;
import com.skeqi.finance.pojo.vo.TGlExplanationTypeVo;
import com.skeqi.finance.service.ITGlExplanationTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 摘要类别Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TGlExplanationTypeServiceImpl extends ServicePlusImpl<TGlExplanationTypeMapper, TGlExplanationType> implements ITGlExplanationTypeService {

	@Autowired
	TGlExplanationMapper tGlExplanationMapper;
    @Override
    public TGlExplanationTypeVo queryById(Integer fId){
        return getVoById(fId, TGlExplanationTypeVo.class);
    }

    @Override
    public TableDataInfo<TGlExplanationTypeVo> queryPageList(TGlExplanationTypeQueryBo bo) {
        PagePlus<TGlExplanationType, TGlExplanationTypeVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlExplanationTypeVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlExplanationTypeVo> queryList(TGlExplanationTypeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlExplanationTypeVo.class);
    }

    private LambdaQueryWrapper<TGlExplanationType> buildQueryWrapper(TGlExplanationTypeQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlExplanationType> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlExplanationType::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TGlExplanationType::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TGlExplanationType::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFCreateOrgid() != null, TGlExplanationType::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TGlExplanationType::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TGlExplanationType::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TGlExplanationType::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TGlExplanationType::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TGlExplanationType::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TGlExplanationType::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TGlExplanationType::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TGlExplanationType::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TGlExplanationType::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TGlExplanationType::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TGlExplanationType::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TGlExplanationType::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TGlExplanationType::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlExplanationTypeAddBo bo) {
        TGlExplanationType add = BeanUtil.toBean(bo, TGlExplanationType.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlExplanationTypeEditBo bo) {
        TGlExplanationType update = BeanUtil.toBean(bo, TGlExplanationType.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlExplanationType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
           ids.forEach(v->{
			   QueryWrapper<TGlExplanation> wrapper=Wrappers.query();
			   wrapper.eq("f_explanation_groupid",v);
			   List<TGlExplanation> list=tGlExplanationMapper.selectList(wrapper);
			   if(CollectionUtil.isNotEmpty(list)){
			   	throw new CustomException("当前类别下级存在记录，删除失败");
			   }
			   baseMapper.deleteById(v);
		   });
        }
        return true;
    }
}

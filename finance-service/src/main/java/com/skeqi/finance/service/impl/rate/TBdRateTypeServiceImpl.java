package com.skeqi.finance.service.impl.rate;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.finance.pojo.bo.rate.TBdRateQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.rate.TBdRateVo;
import com.skeqi.finance.service.rate.ITBdRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.rate.TBdRateTypeAddBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateTypeQueryBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateTypeEditBo;
import com.skeqi.finance.domain.rate.TBdRateType;
import com.skeqi.finance.mapper.rate.TBdRateTypeMapper;
import com.skeqi.finance.pojo.vo.basicinformation.rate.TBdRateTypeVo;
import com.skeqi.finance.service.rate.ITBdRateTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 汇率类型Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdRateTypeServiceImpl extends ServicePlusImpl<TBdRateTypeMapper, TBdRateType> implements ITBdRateTypeService {

	@Autowired
	ITBdRateService iTBdRateService;
    @Override
    public TBdRateTypeVo queryById(Integer fRatetypeId){
        return getVoById(fRatetypeId, TBdRateTypeVo.class);
    }

    @Override
    public TableDataInfo<TBdRateTypeVo> queryPageList(TBdRateTypeQueryBo bo) {
        PagePlus<TBdRateType, TBdRateTypeVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdRateTypeVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdRateTypeVo> queryList(TBdRateTypeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdRateTypeVo.class);
    }

    private LambdaQueryWrapper<TBdRateType> buildQueryWrapper(TBdRateTypeQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdRateType> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdRateType::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdRateType::getFNumber, bo.getFNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdRateType::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFDigits() != null, TBdRateType::getFDigits, bo.getFDigits());
        lqw.eq(bo.getFReverseDigits() != null, TBdRateType::getFReverseDigits, bo.getFReverseDigits());
        lqw.eq(bo.getFCreateOrgid() != null, TBdRateType::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdRateType::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdRateType::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdRateType::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdRateType::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdRateType::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdRateType::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdRateType::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdRateType::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdRateType::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdRateType::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdRateType::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdRateType::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdRateType::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdRateTypeAddBo bo) {
        TBdRateType add = BeanUtil.toBean(bo, TBdRateType.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdRateTypeEditBo bo) {
        TBdRateType update = BeanUtil.toBean(bo, TBdRateType.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdRateType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
           ids.forEach(v->{
			   TBdRateQueryBo bo=new TBdRateQueryBo();
			   bo.setFRateTypeId(v);
			   List<TBdRateVo> list=iTBdRateService.queryList(bo);
			   if(CollectionUtil.isNotEmpty(list)){
			   	throw new CustomException("该分组下存在数据，删除失败");
			   }
           });
        }
        return removeByIds(ids);
    }
}

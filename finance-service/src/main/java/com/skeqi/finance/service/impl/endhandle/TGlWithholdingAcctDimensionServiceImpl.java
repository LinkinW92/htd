package com.skeqi.finance.service.impl.endhandle;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctDimensionEditBo;
import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingAcctDimension;
import com.skeqi.finance.mapper.endhandle.TGlWithholdingAcctDimensionMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingAcctDimensionVo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingAcctDimensionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证预提科目维度控制Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlWithholdingAcctDimensionServiceImpl extends ServicePlusImpl<TGlWithholdingAcctDimensionMapper, TGlWithholdingAcctDimension> implements ITGlWithholdingAcctDimensionService {

    @Override
    public TGlWithholdingAcctDimensionVo queryById(Long dimensionId){
        return getVoById(dimensionId, TGlWithholdingAcctDimensionVo.class);
    }

    @Override
    public TableDataInfo<TGlWithholdingAcctDimensionVo> queryPageList(TGlWithholdingAcctDimensionQueryBo bo) {
        PagePlus<TGlWithholdingAcctDimension, TGlWithholdingAcctDimensionVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlWithholdingAcctDimensionVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlWithholdingAcctDimensionVo> queryList(TGlWithholdingAcctDimensionQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlWithholdingAcctDimensionVo.class);
    }

    private LambdaQueryWrapper<TGlWithholdingAcctDimension> buildQueryWrapper(TGlWithholdingAcctDimensionQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlWithholdingAcctDimension> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getDimensionId() != null, TGlWithholdingAcctDimension::getDimensionId, bo.getDimensionId());
        lqw.eq(StrUtil.isNotBlank(bo.getDsCode()), TGlWithholdingAcctDimension::getDsCode, bo.getDsCode());
        lqw.like(StrUtil.isNotBlank(bo.getDsName()), TGlWithholdingAcctDimension::getDsName, bo.getDsName());
        lqw.eq(bo.getAmortEntryId() != null, TGlWithholdingAcctDimension::getAmortEntryId, bo.getAmortEntryId());
        lqw.eq(bo.getAcctId() != null, TGlWithholdingAcctDimension::getAcctId, bo.getAcctId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlWithholdingAcctDimensionAddBo bo) {
        TGlWithholdingAcctDimension add = BeanUtil.toBean(bo, TGlWithholdingAcctDimension.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlWithholdingAcctDimensionEditBo bo) {
        TGlWithholdingAcctDimension update = BeanUtil.toBean(bo, TGlWithholdingAcctDimension.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlWithholdingAcctDimension entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}

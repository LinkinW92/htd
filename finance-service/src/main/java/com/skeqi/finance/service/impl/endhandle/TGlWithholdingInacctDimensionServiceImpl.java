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
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctDimensionEditBo;
import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingInacctDimension;
import com.skeqi.finance.mapper.endhandle.TGlWithholdingInacctDimensionMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingInacctDimensionVo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingInacctDimensionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证预提转入科目维度控制Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlWithholdingInacctDimensionServiceImpl extends ServicePlusImpl<TGlWithholdingInacctDimensionMapper, TGlWithholdingInacctDimension> implements ITGlWithholdingInacctDimensionService {

    @Override
    public TGlWithholdingInacctDimensionVo queryById(Long dimensionId){
        return getVoById(dimensionId, TGlWithholdingInacctDimensionVo.class);
    }

    @Override
    public TableDataInfo<TGlWithholdingInacctDimensionVo> queryPageList(TGlWithholdingInacctDimensionQueryBo bo) {
        PagePlus<TGlWithholdingInacctDimension, TGlWithholdingInacctDimensionVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlWithholdingInacctDimensionVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlWithholdingInacctDimensionVo> queryList(TGlWithholdingInacctDimensionQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlWithholdingInacctDimensionVo.class);
    }

    private LambdaQueryWrapper<TGlWithholdingInacctDimension> buildQueryWrapper(TGlWithholdingInacctDimensionQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlWithholdingInacctDimension> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getDimensionId() != null, TGlWithholdingInacctDimension::getDimensionId, bo.getDimensionId());
        lqw.eq(StrUtil.isNotBlank(bo.getDsCode()), TGlWithholdingInacctDimension::getDsCode, bo.getDsCode());
        lqw.like(StrUtil.isNotBlank(bo.getDsName()), TGlWithholdingInacctDimension::getDsName, bo.getDsName());
        lqw.eq(bo.getAmortEntryId() != null, TGlWithholdingInacctDimension::getAmortEntryId, bo.getAmortEntryId());
        lqw.eq(bo.getAcctId() != null, TGlWithholdingInacctDimension::getAcctId, bo.getAcctId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlWithholdingInacctDimensionAddBo bo) {
        TGlWithholdingInacctDimension add = BeanUtil.toBean(bo, TGlWithholdingInacctDimension.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlWithholdingInacctDimensionEditBo bo) {
        TGlWithholdingInacctDimension update = BeanUtil.toBean(bo, TGlWithholdingInacctDimension.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlWithholdingInacctDimension entity){
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

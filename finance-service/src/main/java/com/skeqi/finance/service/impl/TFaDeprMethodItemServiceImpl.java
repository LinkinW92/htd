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
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodItemAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodItemQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodItemEditBo;
import com.skeqi.finance.domain.TFaDeprMethodItem;
import com.skeqi.finance.mapper.TFaDeprMethodItemMapper;
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprMethodItemVo;
import com.skeqi.finance.service.depr.ITFaDeprMethodItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 折旧方法元素Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TFaDeprMethodItemServiceImpl extends ServicePlusImpl<TFaDeprMethodItemMapper, TFaDeprMethodItem> implements ITFaDeprMethodItemService {

    @Override
    public TFaDeprMethodItemVo queryById(Integer fId){
        return getVoById(fId, TFaDeprMethodItemVo.class);
    }

    @Override
    public TableDataInfo<TFaDeprMethodItemVo> queryPageList(TFaDeprMethodItemQueryBo bo) {
        PagePlus<TFaDeprMethodItem, TFaDeprMethodItemVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TFaDeprMethodItemVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TFaDeprMethodItemVo> queryList(TFaDeprMethodItemQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFaDeprMethodItemVo.class);
    }

    private LambdaQueryWrapper<TFaDeprMethodItem> buildQueryWrapper(TFaDeprMethodItemQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TFaDeprMethodItem> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFFieldType() != null, TFaDeprMethodItem::getFFieldType, bo.getFFieldType());
        lqw.eq(StrUtil.isNotBlank(bo.getFFormulaType()), TFaDeprMethodItem::getFFormulaType, bo.getFFormulaType());
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TFaDeprMethodItem::getFNumber, bo.getFNumber());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFaDeprMethodItemAddBo bo) {
        TFaDeprMethodItem add = BeanUtil.toBean(bo, TFaDeprMethodItem.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TFaDeprMethodItemEditBo bo) {
        TFaDeprMethodItem update = BeanUtil.toBean(bo, TFaDeprMethodItem.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TFaDeprMethodItem entity){
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

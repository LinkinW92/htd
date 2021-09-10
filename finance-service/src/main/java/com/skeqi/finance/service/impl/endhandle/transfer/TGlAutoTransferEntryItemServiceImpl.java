package com.skeqi.finance.service.impl.endhandle.transfer;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryItemAddBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryItemQueryBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryItemEditBo;
import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransferEntryItem;
import com.skeqi.finance.mapper.endhandle.TGlAutoTransferEntryItemMapper;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferEntryItemVo;
import com.skeqi.finance.service.endhandle.transfer.ITGlAutoTransferEntryItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 自动转账核算维度Service业务层处理
 *
 * @author toms
 * @date 2021-07-26
 */
@Service
public class TGlAutoTransferEntryItemServiceImpl extends ServicePlusImpl<TGlAutoTransferEntryItemMapper, TGlAutoTransferEntryItem> implements ITGlAutoTransferEntryItemService {

    @Override
    public TGlAutoTransferEntryItemVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TGlAutoTransferEntryItemVo.class);
    }

    @Override
    public TableDataInfo<TGlAutoTransferEntryItemVo> queryPageList(TGlAutoTransferEntryItemQueryBo bo) {
        PagePlus<TGlAutoTransferEntryItem, TGlAutoTransferEntryItemVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlAutoTransferEntryItemVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlAutoTransferEntryItemVo> queryList(TGlAutoTransferEntryItemQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlAutoTransferEntryItemVo.class);
    }

    private LambdaQueryWrapper<TGlAutoTransferEntryItem> buildQueryWrapper(TGlAutoTransferEntryItemQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlAutoTransferEntryItem> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFTransferEntryId() != null, TGlAutoTransferEntryItem::getFTransferEntryId, bo.getFTransferEntryId());
        lqw.eq(bo.getFFlexitemPropertyId() != null, TGlAutoTransferEntryItem::getFFlexitemPropertyId, bo.getFFlexitemPropertyId());
        lqw.eq(StrUtil.isNotBlank(bo.getFBeginItemNumber()), TGlAutoTransferEntryItem::getFBeginItemNumber, bo.getFBeginItemNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFEndItemNumber()), TGlAutoTransferEntryItem::getFEndItemNumber, bo.getFEndItemNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFIssequentSelect()), TGlAutoTransferEntryItem::getFIssequentSelect, bo.getFIssequentSelect());
        lqw.eq(StrUtil.isNotBlank(bo.getFItemNumber()), TGlAutoTransferEntryItem::getFItemNumber, bo.getFItemNumber());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlAutoTransferEntryItemAddBo bo) {
        TGlAutoTransferEntryItem add = BeanUtil.toBean(bo, TGlAutoTransferEntryItem.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlAutoTransferEntryItemEditBo bo) {
        TGlAutoTransferEntryItem update = BeanUtil.toBean(bo, TGlAutoTransferEntryItem.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlAutoTransferEntryItem entity){
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

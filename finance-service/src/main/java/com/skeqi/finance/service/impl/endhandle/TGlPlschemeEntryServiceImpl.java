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
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEntryAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEntryQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEntryEditBo;
import com.skeqi.finance.domain.endhandle.TGlPlschemeEntry;
import com.skeqi.finance.mapper.endhandle.TGlPlschemeEntryMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeEntryVo;
import com.skeqi.finance.service.endhandle.ITGlPlschemeEntryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 结转损益方案分录Service业务层处理
 *
 * @author toms
 * @date 2021-08-02
 */
@Service
public class TGlPlschemeEntryServiceImpl extends ServicePlusImpl<TGlPlschemeEntryMapper, TGlPlschemeEntry> implements ITGlPlschemeEntryService {

    @Override
    public TGlPlschemeEntryVo queryById(Long fEntryId){
        return getVoById(fEntryId, TGlPlschemeEntryVo.class);
    }

    @Override
    public TableDataInfo<TGlPlschemeEntryVo> queryPageList(TGlPlschemeEntryQueryBo bo) {
        PagePlus<TGlPlschemeEntry, TGlPlschemeEntryVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlPlschemeEntryVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlPlschemeEntryVo> queryList(TGlPlschemeEntryQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlPlschemeEntryVo.class);
    }

    private LambdaQueryWrapper<TGlPlschemeEntry> buildQueryWrapper(TGlPlschemeEntryQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlPlschemeEntry> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFId() != null, TGlPlschemeEntry::getFId, bo.getFId());
        lqw.eq(bo.getFAcctId() != null, TGlPlschemeEntry::getFAcctId, bo.getFAcctId());
        lqw.eq(bo.getFPlacctId() != null, TGlPlschemeEntry::getFPlacctId, bo.getFPlacctId());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsSelected()), TGlPlschemeEntry::getFIsSelected, bo.getFIsSelected());
        lqw.eq(bo.getFSeq() != null, TGlPlschemeEntry::getFSeq, bo.getFSeq());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlPlschemeEntryAddBo bo) {
        TGlPlschemeEntry add = BeanUtil.toBean(bo, TGlPlschemeEntry.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlPlschemeEntryEditBo bo) {
        TGlPlschemeEntry update = BeanUtil.toBean(bo, TGlPlschemeEntry.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlPlschemeEntry entity){
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

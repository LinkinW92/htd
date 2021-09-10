package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountcyAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountcyQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountcyEditBo;
import com.skeqi.finance.domain.TBdAccountcy;
import com.skeqi.finance.mapper.TBdAccountcyMapper;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountcyVo;
import com.skeqi.finance.service.account.ITBdAccountcyService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 科目核算币别Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdAccountcyServiceImpl extends ServicePlusImpl<TBdAccountcyMapper, TBdAccountcy> implements ITBdAccountcyService {

    @Override
    public TBdAccountcyVo queryById(Integer fCurrencyId){
        return getVoById(fCurrencyId, TBdAccountcyVo.class);
    }

    @Override
    public TableDataInfo<TBdAccountcyVo> queryPageList(TBdAccountcyQueryBo bo) {
        PagePlus<TBdAccountcy, TBdAccountcyVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdAccountcyVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdAccountcyVo> queryList(TBdAccountcyQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdAccountcyVo.class);
    }

    private LambdaQueryWrapper<TBdAccountcy> buildQueryWrapper(TBdAccountcyQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdAccountcy> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFEntryId() != null, TBdAccountcy::getFEntryId, bo.getFEntryId());
        lqw.eq(bo.getFAcctId() != null, TBdAccountcy::getFAcctId, bo.getFAcctId());
        lqw.eq(bo.getFSeq() != null, TBdAccountcy::getFSeq, bo.getFSeq());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdAccountcyAddBo bo) {
        TBdAccountcy add = BeanUtil.toBean(bo, TBdAccountcy.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdAccountcyEditBo bo) {
        TBdAccountcy update = BeanUtil.toBean(bo, TBdAccountcy.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdAccountcy entity){
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

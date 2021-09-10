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
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctEditBo;
import com.skeqi.finance.domain.endhandle.amortization.TGlAmortAcct;
import com.skeqi.finance.mapper.endhandle.TGlAmortAcctMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortAcctVo;
import com.skeqi.finance.service.endhandle.ITGlAmortAcctService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证摊销-待摊销科目Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlAmortAcctServiceImpl extends ServicePlusImpl<TGlAmortAcctMapper, TGlAmortAcct> implements ITGlAmortAcctService {

    @Override
    public TGlAmortAcctVo queryById(Long fId){
        return getVoById(fId, TGlAmortAcctVo.class);
    }

    @Override
    public TableDataInfo<TGlAmortAcctVo> queryPageList(TGlAmortAcctQueryBo bo) {
        PagePlus<TGlAmortAcct, TGlAmortAcctVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlAmortAcctVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlAmortAcctVo> queryList(TGlAmortAcctQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlAmortAcctVo.class);
    }

    private LambdaQueryWrapper<TGlAmortAcct> buildQueryWrapper(TGlAmortAcctQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlAmortAcct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFSchemeId() != null, TGlAmortAcct::getFSchemeId, bo.getFSchemeId());
        lqw.eq(bo.getFAmortizeAccount() != null, TGlAmortAcct::getFAmortizeAccount, bo.getFAmortizeAccount());
        lqw.eq(bo.getFAmortizingAmount() != null, TGlAmortAcct::getFAmortizingAmount, bo.getFAmortizingAmount());
        lqw.eq(bo.getFDetailId() != null, TGlAmortAcct::getFDetailId, bo.getFDetailId());
        lqw.eq(StrUtil.isNotBlank(bo.getFExpression()), TGlAmortAcct::getFExpression, bo.getFExpression());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlAmortAcctAddBo bo) {
        TGlAmortAcct add = BeanUtil.toBean(bo, TGlAmortAcct.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlAmortAcctEditBo bo) {
        TGlAmortAcct update = BeanUtil.toBean(bo, TGlAmortAcct.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlAmortAcct entity){
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

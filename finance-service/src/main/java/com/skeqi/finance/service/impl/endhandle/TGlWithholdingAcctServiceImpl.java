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
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctEditBo;
import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingAcct;
import com.skeqi.finance.mapper.endhandle.TGlWithholdingAcctMapper;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingAcctVo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingAcctService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证预提-预提科目Service业务层处理
 *
 * @author toms
 * @date 2021-07-27
 */
@Service
public class TGlWithholdingAcctServiceImpl extends ServicePlusImpl<TGlWithholdingAcctMapper, TGlWithholdingAcct> implements ITGlWithholdingAcctService {

    @Override
    public TGlWithholdingAcctVo queryById(Long fId){
        return getVoById(fId, TGlWithholdingAcctVo.class);
    }

    @Override
    public TableDataInfo<TGlWithholdingAcctVo> queryPageList(TGlWithholdingAcctQueryBo bo) {
        PagePlus<TGlWithholdingAcct, TGlWithholdingAcctVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlWithholdingAcctVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlWithholdingAcctVo> queryList(TGlWithholdingAcctQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlWithholdingAcctVo.class);
    }

    private LambdaQueryWrapper<TGlWithholdingAcct> buildQueryWrapper(TGlWithholdingAcctQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlWithholdingAcct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFSchemeId() != null, TGlWithholdingAcct::getFSchemeId, bo.getFSchemeId());
        lqw.eq(bo.getFProvisionAccount() != null, TGlWithholdingAcct::getFProvisionAccount, bo.getFProvisionAccount());
        lqw.eq(bo.getFDetailId() != null, TGlWithholdingAcct::getFDetailId, bo.getFDetailId());
        lqw.eq(bo.getFProvisionRatio() != null, TGlWithholdingAcct::getFProvisionRatio, bo.getFProvisionRatio());
        lqw.eq(StrUtil.isNotBlank(bo.getFProvisionFixed()), TGlWithholdingAcct::getFProvisionFixed, bo.getFProvisionFixed());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlWithholdingAcctAddBo bo) {
        TGlWithholdingAcct add = BeanUtil.toBean(bo, TGlWithholdingAcct.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlWithholdingAcctEditBo bo) {
        TGlWithholdingAcct update = BeanUtil.toBean(bo, TGlWithholdingAcct.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlWithholdingAcct entity){
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

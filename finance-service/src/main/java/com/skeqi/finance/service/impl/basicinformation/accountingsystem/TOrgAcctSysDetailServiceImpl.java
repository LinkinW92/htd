package com.skeqi.finance.service.impl.basicinformation.accountingsystem;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysDetailAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysDetailQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysDetailEditBo;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysDetail;
import com.skeqi.finance.mapper.basicinformation.accountingsystem.TOrgAcctSysDetailMapper;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAcctSysDetailVo;
import com.skeqi.finance.service.basicinformation.accountingsystem.ITOrgAcctSysDetailService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会计核算体系之下级组织Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TOrgAcctSysDetailServiceImpl extends ServicePlusImpl<TOrgAcctSysDetailMapper, TOrgAcctSysDetail> implements ITOrgAcctSysDetailService {

    @Override
    public TOrgAcctSysDetailVo queryById(Integer fId){
        return getVoById(fId, TOrgAcctSysDetailVo.class);
    }

    @Override
    public TableDataInfo<TOrgAcctSysDetailVo> queryPageList(TOrgAcctSysDetailQueryBo bo) {
        PagePlus<TOrgAcctSysDetail, TOrgAcctSysDetailVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TOrgAcctSysDetailVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TOrgAcctSysDetailVo> queryList(TOrgAcctSysDetailQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TOrgAcctSysDetailVo.class);
    }

    private LambdaQueryWrapper<TOrgAcctSysDetail> buildQueryWrapper(TOrgAcctSysDetailQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TOrgAcctSysDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFAcctsysentryId() != null, TOrgAcctSysDetail::getFAcctsysentryId, bo.getFAcctsysentryId());
        lqw.eq(bo.getFAcctOrgid() != null, TOrgAcctSysDetail::getFAcctOrgid, bo.getFAcctOrgid());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TOrgAcctSysDetail::getFDescription, bo.getFDescription());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TOrgAcctSysDetailAddBo bo) {
        TOrgAcctSysDetail add = BeanUtil.toBean(bo, TOrgAcctSysDetail.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TOrgAcctSysDetailEditBo bo) {
        TOrgAcctSysDetail update = BeanUtil.toBean(bo, TOrgAcctSysDetail.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TOrgAcctSysDetail entity){
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

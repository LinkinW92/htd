package com.skeqi.finance.service.impl.basicinformation.voucher;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryDimensionAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryDimensionQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryDimensionEditBo;
import com.skeqi.finance.domain.voucher.TGlVoucherEntryDimension;
import com.skeqi.finance.mapper.basicinformation.voucher.TGlVoucherEntryDimensionMapper;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryDimensionVo;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherEntryDimensionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 凭证分录维度控制Service业务层处理
 *
 * @author toms
 * @date 2021-07-21
 */
@Service
public class TGlVoucherEntryDimensionServiceImpl extends ServicePlusImpl<TGlVoucherEntryDimensionMapper, TGlVoucherEntryDimension> implements ITGlVoucherEntryDimensionService {

    @Override
    public TGlVoucherEntryDimensionVo queryById(Integer id){
        return getVoById(id, TGlVoucherEntryDimensionVo.class);
    }

    @Override
    public TableDataInfo<TGlVoucherEntryDimensionVo> queryPageList(TGlVoucherEntryDimensionQueryBo bo) {
        PagePlus<TGlVoucherEntryDimension, TGlVoucherEntryDimensionVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TGlVoucherEntryDimensionVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TGlVoucherEntryDimensionVo> queryList(TGlVoucherEntryDimensionQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlVoucherEntryDimensionVo.class);
    }

    private LambdaQueryWrapper<TGlVoucherEntryDimension> buildQueryWrapper(TGlVoucherEntryDimensionQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TGlVoucherEntryDimension> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getDimensionId() != null, TGlVoucherEntryDimension::getDimensionAll, bo.getDimensionId());
        lqw.eq(StrUtil.isNotBlank(bo.getDsCode()), TGlVoucherEntryDimension::getDsCode, bo.getDsCode());
        lqw.like(StrUtil.isNotBlank(bo.getDsName()), TGlVoucherEntryDimension::getDsName, bo.getDsName());
        lqw.eq(bo.getVoucherEntryId() != null, TGlVoucherEntryDimension::getVoucherEntryId, bo.getVoucherEntryId());
        lqw.eq(bo.getCreateUser() != null, TGlVoucherEntryDimension::getCreateUser, bo.getCreateUser());
        lqw.eq(bo.getUpdateUser() != null, TGlVoucherEntryDimension::getUpdateUser, bo.getUpdateUser());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlVoucherEntryDimensionAddBo bo) {
        TGlVoucherEntryDimension add = BeanUtil.toBean(bo, TGlVoucherEntryDimension.class);
        validEntityBeforeSave(add);
        return save(add);
    }

	@Override
	public Boolean insertBatch(List<TGlVoucherEntryDimension> list) {
		if(CollectionUtil.isNotEmpty(list)){
			baseMapper.insertAll(list);
		}
		return true;
	}

    @Override
    public Boolean updateByEditBo(TGlVoucherEntryDimensionEditBo bo) {
        TGlVoucherEntryDimension update = BeanUtil.toBean(bo, TGlVoucherEntryDimension.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlVoucherEntryDimension entity){
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

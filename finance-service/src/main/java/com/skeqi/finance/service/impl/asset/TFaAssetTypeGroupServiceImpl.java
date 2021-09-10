package com.skeqi.finance.service.impl.asset;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeGroupAddBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeGroupQueryBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeGroupEditBo;
import com.skeqi.finance.domain.asset.TFaAssetTypeGroup;
import com.skeqi.finance.mapper.TFaAssetTypeGroupMapper;
import com.skeqi.finance.pojo.vo.asset.TFaAssetTypeGroupVo;
import com.skeqi.finance.service.asset.ITFaAssetTypeGroupService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 资产类别组Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TFaAssetTypeGroupServiceImpl extends ServicePlusImpl<TFaAssetTypeGroupMapper, TFaAssetTypeGroup> implements ITFaAssetTypeGroupService {

    @Override
    public TFaAssetTypeGroupVo queryById(Integer fId){
        return getVoById(fId, TFaAssetTypeGroupVo.class);
    }

    @Override
    public TableDataInfo<TFaAssetTypeGroupVo> queryPageList(TFaAssetTypeGroupQueryBo bo) {
        PagePlus<TFaAssetTypeGroup, TFaAssetTypeGroupVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TFaAssetTypeGroupVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TFaAssetTypeGroupVo> queryList(TFaAssetTypeGroupQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TFaAssetTypeGroupVo.class);
    }

    private LambdaQueryWrapper<TFaAssetTypeGroup> buildQueryWrapper(TFaAssetTypeGroupQueryBo bo) {
        LambdaQueryWrapper<TFaAssetTypeGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TFaAssetTypeGroup::getFNumber, bo.getFNumber());
		lqw.eq(StrUtil.isNotBlank(bo.getFName()), TFaAssetTypeGroup::getFName, bo.getFName());
		lqw.eq(TFaAssetTypeGroup::getFParentId, bo.getFParentId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TFaAssetTypeGroupAddBo bo) {
        TFaAssetTypeGroup add = BeanUtil.toBean(bo, TFaAssetTypeGroup.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TFaAssetTypeGroupEditBo bo) {
        TFaAssetTypeGroup update = BeanUtil.toBean(bo, TFaAssetTypeGroup.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TFaAssetTypeGroup entity){
		//TODO 做一些数据校验,如唯一约束
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("保存数据不能为空!",1000);
		}
		LambdaQueryWrapper<TFaAssetTypeGroup> lqw = Wrappers.lambdaQuery();
		lqw.eq(StringUtils.checkValNotNull(entity.getFNumber()), TFaAssetTypeGroup::getFNumber, entity.getFNumber());
		lqw.eq(TFaAssetTypeGroup::getFParentId, entity.getFParentId());
		TFaAssetTypeGroup voOne = getVoOne(lqw, TFaAssetTypeGroup.class);
		if (StringUtils.checkValNotNull(voOne)){
			throw new CustomException("编码已存在!",1000);
		}
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}

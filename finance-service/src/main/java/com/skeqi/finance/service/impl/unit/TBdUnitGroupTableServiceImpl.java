package com.skeqi.finance.service.impl.unit;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupTableAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupTableQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupTableEditBo;
import com.skeqi.finance.domain.unit.TBdUnitGroupTable;
import com.skeqi.finance.mapper.TBdUnitGroupTableMapper;
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitGroupTableVo;
import com.skeqi.finance.service.unit.ITBdUnitGroupTableService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 计量单位分组Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdUnitGroupTableServiceImpl extends ServicePlusImpl<TBdUnitGroupTableMapper, TBdUnitGroupTable> implements ITBdUnitGroupTableService {

    @Override
    public TBdUnitGroupTableVo queryById(Integer fId){
        return getVoById(fId, TBdUnitGroupTableVo.class);
    }

    @Override
    public TableDataInfo<TBdUnitGroupTableVo> queryPageList(TBdUnitGroupTableQueryBo bo) {
        PagePlus<TBdUnitGroupTable, TBdUnitGroupTableVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdUnitGroupTableVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdUnitGroupTableVo> queryList(TBdUnitGroupTableQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdUnitGroupTableVo.class);
    }

    private LambdaQueryWrapper<TBdUnitGroupTable> buildQueryWrapper(TBdUnitGroupTableQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdUnitGroupTable> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdUnitGroupTable::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdUnitGroupTable::getFName, bo.getFName());
        lqw.eq(bo.getFParentId() != null, TBdUnitGroupTable::getFParentId, bo.getFParentId());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdUnitGroupTable::getFDescription, bo.getFDescription());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdUnitGroupTableAddBo bo) {
        TBdUnitGroupTable add = BeanUtil.toBean(bo, TBdUnitGroupTable.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdUnitGroupTableEditBo bo) {
        TBdUnitGroupTable update = BeanUtil.toBean(bo, TBdUnitGroupTable.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdUnitGroupTable entity){
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

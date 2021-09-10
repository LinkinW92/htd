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
import com.skeqi.finance.pojo.bo.basicinformation.file.TBdFileManageAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.file.TBdFileManageQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.file.TBdFileManageEditBo;
import com.skeqi.finance.domain.TBdFileManage;
import com.skeqi.finance.mapper.TBdFileManageMapper;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdFileManageVo;
import com.skeqi.finance.service.basicinformation.base.ITBdFileManageService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 文件管理Service业务层处理
 *
 * @author toms
 * @date 2021-08-18
 */
@Service
public class TBdFileManageServiceImpl extends ServicePlusImpl<TBdFileManageMapper, TBdFileManage> implements ITBdFileManageService {

    @Override
    public TBdFileManageVo queryById(Integer id){
        return getVoById(id, TBdFileManageVo.class);
    }

    @Override
    public TableDataInfo<TBdFileManageVo> queryPageList(TBdFileManageQueryBo bo) {
        PagePlus<TBdFileManage, TBdFileManageVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdFileManageVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdFileManageVo> queryList(TBdFileManageQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdFileManageVo.class);
    }

    private LambdaQueryWrapper<TBdFileManage> buildQueryWrapper(TBdFileManageQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdFileManage> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getName()), TBdFileManage::getName, bo.getName());
        lqw.eq(StrUtil.isNotBlank(bo.getUrl()), TBdFileManage::getUrl, bo.getUrl());
        lqw.eq(StrUtil.isNotBlank(bo.getCode()), TBdFileManage::getCode, bo.getCode());
        lqw.eq(StrUtil.isNotBlank(bo.getOutNumber()), TBdFileManage::getOutNumber, bo.getOutNumber());
        lqw.eq(StrUtil.isNotBlank(bo.getFileType()), TBdFileManage::getFileType, bo.getFileType());
        lqw.eq(StrUtil.isNotBlank(bo.getBusType()), TBdFileManage::getBusType, bo.getBusType());
        lqw.eq(bo.getCreateUser() != null, TBdFileManage::getCreateUser, bo.getCreateUser());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdFileManageAddBo bo) {
        TBdFileManage add = BeanUtil.toBean(bo, TBdFileManage.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdFileManageEditBo bo) {
        TBdFileManage update = BeanUtil.toBean(bo, TBdFileManage.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdFileManage entity){
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

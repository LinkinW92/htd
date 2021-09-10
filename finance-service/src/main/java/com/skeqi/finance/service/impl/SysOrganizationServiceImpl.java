package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.org.SysOrganizationAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.org.SysOrganizationQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.org.SysOrganizationEditBo;
import com.skeqi.finance.domain.SysOrganization;
import com.skeqi.finance.mapper.SysOrganizationMapper;
import com.skeqi.finance.pojo.vo.SysOrganizationVo;
import com.skeqi.finance.service.ISysOrganizationService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 组织管理Service业务层处理
 *
 * @author toms
 * @date 2021-07-16
 */
@Service
public class SysOrganizationServiceImpl extends ServicePlusImpl<SysOrganizationMapper, SysOrganization> implements ISysOrganizationService {

    @Override
    public SysOrganizationVo queryById(Integer id){
        return getVoById(id, SysOrganizationVo.class);
    }

    @Override
    public TableDataInfo<SysOrganizationVo> queryPageList(SysOrganizationQueryBo bo) {
        PagePlus<SysOrganization, SysOrganizationVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), SysOrganizationVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<SysOrganizationVo> queryList(SysOrganizationQueryBo bo) {
        return listVo(buildQueryWrapper(bo), SysOrganizationVo.class);
    }

    private LambdaQueryWrapper<SysOrganization> buildQueryWrapper(SysOrganizationQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysOrganization> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getName()), SysOrganization::getName, bo.getName());
        lqw.eq(StrUtil.isNotBlank(bo.getOrgCode()), SysOrganization::getOrgCode, bo.getOrgCode());
        lqw.eq(StrUtil.isNotBlank(bo.getCmpType()), SysOrganization::getCmpType, bo.getCmpType());
        lqw.eq(StrUtil.isNotBlank(bo.getAddress()), SysOrganization::getAddress, bo.getAddress());
        lqw.eq(StrUtil.isNotBlank(bo.getDescription()), SysOrganization::getDescription, bo.getDescription());
        lqw.eq(StrUtil.isNotBlank(bo.getLeader()), SysOrganization::getLeader, bo.getLeader());
        lqw.eq(StrUtil.isNotBlank(bo.getPhone()), SysOrganization::getPhone, bo.getPhone());
        lqw.eq(StrUtil.isNotBlank(bo.getPostCode()), SysOrganization::getPostCode, bo.getPostCode());
        lqw.eq(StrUtil.isNotBlank(bo.getOrgType()), SysOrganization::getOrgType, bo.getOrgType());
        lqw.eq(bo.getLegalParentId() != null, SysOrganization::getLegalParentId, bo.getLegalParentId());
        lqw.like(StrUtil.isNotBlank(bo.getLegalParentName()), SysOrganization::getLegalParentName, bo.getLegalParentName());
        lqw.eq(bo.getStatus() != null, SysOrganization::getStatus, bo.getStatus());
        lqw.eq(bo.getCreateUser() != null, SysOrganization::getCreateUser, bo.getCreateUser());
        lqw.eq(bo.getUpdateUser() != null, SysOrganization::getUpdateUser, bo.getUpdateUser());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(SysOrganizationAddBo bo) {
        SysOrganization add = BeanUtil.toBean(bo, SysOrganization.class);
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(SysOrganizationEditBo bo) {
		SysOrganization old=getById(bo.getId());
    	if(null==old){
    		throw new CustomException("组织不存在",1000);
		}
        BeanUtil.copyProperties(bo,old);
        validEntityBeforeSave(old);
        return updateById(old);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(SysOrganization entity){
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

package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.finance.domain.TBdAccountGroupTable;
import com.skeqi.finance.enums.AssetsTypeEnum;
import com.skeqi.finance.enums.BorrowEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.mapper.TBdAccountGroupTableMapper;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupEditBo;
import com.skeqi.finance.domain.TBdAccountGroup;
import com.skeqi.finance.mapper.TBdAccountGroupMapper;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountGroupVo;
import com.skeqi.finance.service.account.ITBdAccountGroupService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会计要素Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdAccountGroupServiceImpl extends ServicePlusImpl<TBdAccountGroupMapper, TBdAccountGroup> implements ITBdAccountGroupService {

	@Autowired
	TBdAccountGroupTableMapper tBdAccountGroupTableMapper;
	@Autowired
	TBdAccountGroupMapper tBdAccountGroupMapper;
	@Autowired
	TokenService tokenService;
    @Override
    public TBdAccountGroupVo queryById(Integer fAcctgroupId){
        return getVoById(fAcctgroupId, TBdAccountGroupVo.class);
    }

    @Override
    public TableDataInfo<TBdAccountGroupVo> queryPageList(TBdAccountGroupQueryBo bo) {
        Page<TBdAccountGroupQueryBo> page=new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TBdAccountGroupVo> res=tBdAccountGroupMapper.queryPageList(page,bo);
		return PageUtils.buildDataInfo(res);
    }

    @Override
    public List<TBdAccountGroupVo> queryList(TBdAccountGroupQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdAccountGroupVo.class);
    }

    private LambdaQueryWrapper<TBdAccountGroup> buildQueryWrapper(TBdAccountGroupQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdAccountGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdAccountGroup::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdAccountGroup::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdAccountGroup::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFDc() != null, TBdAccountGroup::getFDc, bo.getFDc());
        lqw.eq(StrUtil.isNotBlank(bo.getFAccountType()), TBdAccountGroup::getFAccountType, bo.getFAccountType());
        lqw.eq(bo.getFAcctgroupTblid() != null, TBdAccountGroup::getFAcctgroupTblid, bo.getFAcctgroupTblid());
        lqw.eq(StrUtil.isNotBlank(bo.getFIsprofit()), TBdAccountGroup::getFIsprofit, bo.getFIsprofit());
        lqw.eq(bo.getFCreateOrgid() != null, TBdAccountGroup::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdAccountGroup::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdAccountGroup::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdAccountGroup::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdAccountGroup::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdAccountGroup::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdAccountGroup::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdAccountGroup::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdAccountGroup::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdAccountGroup::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdAccountGroup::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdAccountGroup::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdAccountGroup::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdAccountGroup::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdAccountGroupAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        TBdAccountGroup add = BeanUtil.toBean(bo, TBdAccountGroup.class);
        validEntityBeforeSave(add);
        add.setFDocumentStatus(DataStatusEnum.CREATE.getCode());
        add.setFCreateDate(new Date());
        add.setFCreatorid(user.getUserId().intValue());
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdAccountGroupEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TBdAccountGroup group=this.getById(bo.getFAcctgroupId());
    	if(null==group){
             throw new CustomException("会计要素不存在");
		}
        BeanUtil.copyProperties(bo, group);
        validEntityBeforeSave(group);
		group.setFCreateDate(new Date());
		group.setFModifierid(user.getUserId().intValue());
        return updateById(group);
    }
	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	@Override
	public Boolean audit(Collection<Integer> ids){
		if(CollectionUtil.isNotEmpty(ids)){
			ids.forEach(v->{
				TBdAccountGroup group=this.getById(v);
				if(group!=null){
					group.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
					this.updateById(group);
				}
			});
		}
		return true;
	}


    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdAccountGroup entity){
		TBdAccountGroupTable g=tBdAccountGroupTableMapper.selectById(entity.getFAcctgroupTblid());
		if(null==g){
			throw new CustomException("会计要素表不存在",1000);
		}
		BorrowEnum borrowEnum=BorrowEnum.getObj(entity.getFDc());
		if(null==borrowEnum){
			throw new CustomException("借贷方向错误",1000);
		}
		AssetsTypeEnum assetsTypeEnum= AssetsTypeEnum.getObj(entity.getFAccountType());
		if(null==assetsTypeEnum){
			throw new CustomException("类别错误",1000);
		}

    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
			if(CollectionUtil.isNotEmpty(ids)){
				ids.forEach(v->{
					TBdAccountGroup group=this.getById(v);
					if(group!=null && DataStatusEnum.CREATE.getCode().equals(group.getFDocumentStatus())){
						removeById(v);
					}

				});
			}
        }
        return true;
    }
}

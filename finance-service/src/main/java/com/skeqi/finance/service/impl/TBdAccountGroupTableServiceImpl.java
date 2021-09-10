package com.skeqi.finance.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountGroupVo;
import com.skeqi.finance.service.account.ITBdAccountGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupTableAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupTableQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupTableEditBo;
import com.skeqi.finance.domain.TBdAccountGroupTable;
import com.skeqi.finance.mapper.TBdAccountGroupTableMapper;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountGroupTableVo;
import com.skeqi.finance.service.account.ITBdAccountGroupTableService;

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
public class TBdAccountGroupTableServiceImpl extends ServicePlusImpl<TBdAccountGroupTableMapper, TBdAccountGroupTable> implements ITBdAccountGroupTableService {

	@Autowired
	ITBdAccountGroupService iTBdAccountGroupService;
    @Override
    public TBdAccountGroupTableVo queryById(Integer fAcctgroupTblid){
        return getVoById(fAcctgroupTblid, TBdAccountGroupTableVo.class);
    }

    @Override
    public TableDataInfo<TBdAccountGroupTableVo> queryPageList(TBdAccountGroupTableQueryBo bo) {
        PagePlus<TBdAccountGroupTable, TBdAccountGroupTableVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdAccountGroupTableVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdAccountGroupTableVo> queryList(TBdAccountGroupTableQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdAccountGroupTableVo.class);
    }

    private LambdaQueryWrapper<TBdAccountGroupTable> buildQueryWrapper(TBdAccountGroupTableQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdAccountGroupTable> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TBdAccountGroupTable::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TBdAccountGroupTable::getFName, bo.getFName());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TBdAccountGroupTable::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFCreateOrgid() != null, TBdAccountGroupTable::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TBdAccountGroupTable::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TBdAccountGroupTable::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TBdAccountGroupTable::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TBdAccountGroupTable::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TBdAccountGroupTable::getFModifyDate, bo.getFModifyDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFDocumentStatus()), TBdAccountGroupTable::getFDocumentStatus, bo.getFDocumentStatus());
        lqw.eq(bo.getFAuditorid() != null, TBdAccountGroupTable::getFAuditorid, bo.getFAuditorid());
        lqw.eq(bo.getFAuditDate() != null, TBdAccountGroupTable::getFAuditDate, bo.getFAuditDate());
        lqw.eq(StrUtil.isNotBlank(bo.getFForbidStatus()), TBdAccountGroupTable::getFForbidStatus, bo.getFForbidStatus());
        lqw.eq(bo.getFForbidderid() != null, TBdAccountGroupTable::getFForbidderid, bo.getFForbidderid());
        lqw.eq(bo.getFForbidDate() != null, TBdAccountGroupTable::getFForbidDate, bo.getFForbidDate());
        lqw.eq(bo.getFIssysPreset() != null, TBdAccountGroupTable::getFIssysPreset, bo.getFIssysPreset());
        lqw.eq(bo.getFMasterId() != null, TBdAccountGroupTable::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdAccountGroupTableAddBo bo) {
        TBdAccountGroupTable add = BeanUtil.toBean(bo, TBdAccountGroupTable.class);
        validEntityBeforeSave(add);
        add.setFCreateDate(new Date());
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdAccountGroupTableEditBo bo) {
        TBdAccountGroupTable update = BeanUtil.toBean(bo, TBdAccountGroupTable.class);
        validEntityBeforeSave(update);
		update.setFModifyDate(new Date());
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdAccountGroupTable entity){
        //TODO 做一些数据校验,如唯一约束
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
        	ids.forEach(v->{
				TBdAccountGroupQueryBo bo=new TBdAccountGroupQueryBo();
				bo.setFAcctgroupTblid(v);
				List<TBdAccountGroupVo>  list=iTBdAccountGroupService.queryList(bo);
				if(CollectionUtil.isNotEmpty(list)){
					throw new CustomException("该分组下有数据，删除失败");
				}
			});
        }
        return removeByIds(ids);
    }
}

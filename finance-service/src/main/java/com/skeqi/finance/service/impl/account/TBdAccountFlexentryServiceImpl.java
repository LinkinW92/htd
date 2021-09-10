package com.skeqi.finance.service.impl.account;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.enums.DataStatusEnum;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import com.skeqi.finance.service.account.ITBdAccountService;
import com.skeqi.finance.service.dimension.ITBdFlexItemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryAddBo;
import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryEditBo;
import com.skeqi.finance.domain.TBdAccountFlexentry;
import com.skeqi.finance.mapper.TBdAccountFlexentryMapper;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountFlexentryVo;
import com.skeqi.finance.service.account.ITBdAccountFlexentryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 科目核算维度组分录Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class TBdAccountFlexentryServiceImpl extends ServicePlusImpl<TBdAccountFlexentryMapper, TBdAccountFlexentry> implements ITBdAccountFlexentryService {

    @Override
    public TBdAccountFlexentryVo queryById(Integer fEntryId){
        return getVoById(fEntryId, TBdAccountFlexentryVo.class);
    }

	/**
	 * 查询列表
	 * @return
	 */
	@Override
	public List<TBdAccountFlexentryVo> queryByAcctId(Integer acctId){
		TBdAccountFlexentryQueryBo bo=new TBdAccountFlexentryQueryBo();
		bo.setFAcctId(acctId);
		return queryList(bo);
	}

    @Override
    public TableDataInfo<TBdAccountFlexentryVo> queryPageList(TBdAccountFlexentryQueryBo bo) {
        PagePlus<TBdAccountFlexentry, TBdAccountFlexentryVo> result = pageVo(PageUtils.buildPagePlus(), buildQueryWrapper(bo), TBdAccountFlexentryVo.class);
        return PageUtils.buildDataInfo(result);
    }

    @Override
    public List<TBdAccountFlexentryVo> queryList(TBdAccountFlexentryQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TBdAccountFlexentryVo.class);
    }

    private LambdaQueryWrapper<TBdAccountFlexentry> buildQueryWrapper(TBdAccountFlexentryQueryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TBdAccountFlexentry> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getFAcctId() != null, TBdAccountFlexentry::getFAcctId, bo.getFAcctId());
        lqw.eq(bo.getFFlexitempropertyId() != null, TBdAccountFlexentry::getFFlexitempropertyId, bo.getFFlexitempropertyId());
        lqw.eq(StrUtil.isNotBlank(bo.getFAcctitemisvalid()), TBdAccountFlexentry::getFAcctitemisvalid, bo.getFAcctitemisvalid());
        lqw.eq(StrUtil.isNotBlank(bo.getFInputType()), TBdAccountFlexentry::getFInputType, bo.getFInputType());
        lqw.like(StrUtil.isNotBlank(bo.getFDataFieldname()), TBdAccountFlexentry::getFDataFieldname, bo.getFDataFieldname());
        lqw.eq(StrUtil.isNotBlank(bo.getFSeq()), TBdAccountFlexentry::getFSeq, bo.getFSeq());
        lqw.eq(bo.getFMasterId() != null, TBdAccountFlexentry::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TBdAccountFlexentryAddBo bo) {
        TBdAccountFlexentry add = BeanUtil.toBean(bo, TBdAccountFlexentry.class);
        validEntityBeforeSave(add);
        add.setFAcctitemisvalid(BaseEnum.YES.getCode());
        add.setFMasterId(null);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TBdAccountFlexentryEditBo bo) {
        TBdAccountFlexentry update = BeanUtil.toBean(bo, TBdAccountFlexentry.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    //科目信息
	@Autowired
    private ITBdAccountService itBdAccountService;
	//核算维度
	@Autowired
	private ITBdFlexItemPropertyService itBdFlexItemPropertyService;
    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TBdAccountFlexentry entity){
        //TODO 做一些数据校验,如唯一约束
//		//科目主表内码
//		Integer acctId = entity.getFAcctId();
//		TBdAccountVo tBdAccountVo = itBdAccountService.queryById(acctId);
//		if(tBdAccountVo == null){
//			throw new CustomException(String.format("%s科目主表内码不存在",""+acctId));
//		}
		//维度内码
		Integer flexitempropertyId = entity.getFFlexitempropertyId();
		TBdFlexItemPropertyVo tBdFlexItemPropertyVo = itBdFlexItemPropertyService.queryById(flexitempropertyId);
		if(tBdFlexItemPropertyVo == null){
			throw new CustomException(String.format("%s维度内码不存在",""+flexitempropertyId));
		}else if (!DataStatusEnum.AUDIT.getCode().equals(tBdFlexItemPropertyVo.getFDocumentStatus())) {
			throw new CustomException(String.format("%s维度内码没有审核通过", "" + flexitempropertyId));
		} else if (BaseEnum.YES.getCode().equals(tBdFlexItemPropertyVo.getFForbidStatus())) {
			throw new CustomException(String.format("%s维度内码被禁用", "" + flexitempropertyId));
		}
		//必录类型需要有分类：1 必录 0 可选
		String acctitemisvalid = entity.getFAcctitemisvalid();
		if(null == BaseEnum.getObj(acctitemisvalid)){
			throw new CustomException(String.format("%s必录类型：1 必录 0 可选", "" + acctitemisvalid));
		}
		//维度数据表中的字段名称
		entity.setFDataFieldname(tBdFlexItemPropertyVo.getFName());
	}

    @Override
    public Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return removeByIds(ids);
    }
}

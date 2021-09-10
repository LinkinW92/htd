package com.skeqi.finance.service.impl.basicinformation.cashflow;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicy;
import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlow;
import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlowItemTable;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowVo;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowService;
import com.skeqi.framecore.web.service.TokenService;
import org.springframework.stereotype.Service;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowTypeAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowTypeQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowTypeEditBo;
import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlowType;
import com.skeqi.finance.mapper.basicinformation.cashflow.TGlCashFlowTypeMapper;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowTypeService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 现金流量项目类别-2Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TGlCashFlowTypeServiceImpl extends ServicePlusImpl<TGlCashFlowTypeMapper, TGlCashFlowType> implements ITGlCashFlowTypeService {
	@Resource
	private TokenService tokenService;

	@Resource
	private TGlCashFlowTypeMapper mapper;

	@Resource
	private ITGlCashFlowService cashFlowService;

    @Override
    public TGlCashFlowTypeVo queryById(Integer fItemTypeid){
        return getVoById(fItemTypeid, TGlCashFlowTypeVo.class);
    }

    @Override
    public TableDataInfo<TGlCashFlowTypeVo> queryPageList(TGlCashFlowTypeQueryBo bo) {
		Page<TGlCashFlowTypeQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TGlCashFlowTypeVo> tGlCashFlowVoIPage = mapper.queryPageList(userPage,bo);
		return PageUtils.buildDataInfo(tGlCashFlowVoIPage);
    }

    @Override
    public List<TGlCashFlowTypeVo> queryList(TGlCashFlowTypeQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlCashFlowTypeVo.class);
    }

    private LambdaQueryWrapper<TGlCashFlowType> buildQueryWrapper(TGlCashFlowTypeQueryBo bo) {
        LambdaQueryWrapper<TGlCashFlowType> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getFNumber()), TGlCashFlowType::getFNumber, bo.getFNumber());
        lqw.like(StrUtil.isNotBlank(bo.getFName()), TGlCashFlowType::getFName, bo.getFName());
        lqw.eq(bo.getFItemGroupid() != null, TGlCashFlowType::getFItemGroupid, bo.getFItemGroupid());
        lqw.eq(bo.getFGroupTypeid() != null, TGlCashFlowType::getFGroupTypeid, bo.getFGroupTypeid());
        lqw.eq(StrUtil.isNotBlank(bo.getFDescription()), TGlCashFlowType::getFDescription, bo.getFDescription());
        lqw.eq(bo.getFCashFlowItemTable() != null, TGlCashFlowType::getFCashFlowItemTable, bo.getFCashFlowItemTable());
        lqw.eq(bo.getFCreateOrgid() != null, TGlCashFlowType::getFCreateOrgid, bo.getFCreateOrgid());
        lqw.eq(bo.getFCreatorid() != null, TGlCashFlowType::getFCreatorid, bo.getFCreatorid());
        lqw.eq(bo.getFCreateDate() != null, TGlCashFlowType::getFCreateDate, bo.getFCreateDate());
        lqw.eq(bo.getFUseOrgid() != null, TGlCashFlowType::getFUseOrgid, bo.getFUseOrgid());
        lqw.eq(bo.getFModifierid() != null, TGlCashFlowType::getFModifierid, bo.getFModifierid());
        lqw.eq(bo.getFModifyDate() != null, TGlCashFlowType::getFModifyDate, bo.getFModifyDate());
        lqw.eq(bo.getFMasterId() != null, TGlCashFlowType::getFMasterId, bo.getFMasterId());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlCashFlowTypeAddBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		// 新增
		TGlCashFlowType add = BeanUtil.toBean(bo, TGlCashFlowType.class);
		add.setFCreatorid(user.getUserId().intValue());
		add.setFCreateDate(new Date());
        validEntityBeforeSave(add);
        return save(add);
    }

    @Override
    public Boolean updateByEditBo(TGlCashFlowTypeEditBo bo) {
		SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
		TGlCashFlowType update = BeanUtil.toBean(bo, TGlCashFlowType.class);
		update.setFModifierid(user.getUserId().intValue());
		update.setFModifyDate(new Date());
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlCashFlowType entity){
		//TODO 做一些数据校验,如唯一约束
		if (StringUtils.checkValNull(entity)){
			throw new CustomException("保存数据不能为空!",1000);
		}
		LambdaQueryWrapper<TGlCashFlowType> lqw = Wrappers.lambdaQuery();
		lqw.eq(entity.getFNumber() != null, TGlCashFlowType::getFNumber, entity.getFNumber());
		lqw.eq(entity.getFCashFlowItemTable() != null, TGlCashFlowType::getFCashFlowItemTable, entity.getFCashFlowItemTable());
		TGlCashFlowType voOne = getVoOne(lqw, TGlCashFlowType.class);
		if (StringUtils.checkValNotNull(voOne)){
			// 编辑时执行
			if (voOne.getFItemTypeid().equals(entity.getFItemTypeid())){
				return;
			}
			throw new CustomException("当前编码已存在!",1000);
		}
    }

    @Override
    public Boolean deleteWithValidById(Collection<Integer> ids) {
		ids.forEach(id->{
			TGlCashFlowQueryBo tGlCashFlow = new TGlCashFlowQueryBo();
			tGlCashFlow.setFParentId(id);
			List<TGlCashFlowVo> tGlCashFlowVos = cashFlowService.queryList(tGlCashFlow);
			if (tGlCashFlowVos.size()>0){
				throw new CustomException(String.format("该现金流量项目[ %s ]下存在现金流量项目，禁止删除！", id),1000);
			}
			this.removeById(id);
		});
		return true;
    }
}

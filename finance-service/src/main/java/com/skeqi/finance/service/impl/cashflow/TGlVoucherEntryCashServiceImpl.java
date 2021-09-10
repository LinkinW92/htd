package com.skeqi.finance.service.impl.cashflow;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.finance.domain.voucher.TGlVoucherEntryCash;
import com.skeqi.finance.mapper.cashflow.TGlVoucherEntryCashMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryCashAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryCashQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryCashEditBo;
import com.skeqi.finance.pojo.vo.cashflow.TGlVoucherEntryCashVo;
import com.skeqi.finance.service.cashflow.ITGlVoucherEntryCashService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 凭证分录现金流量Service业务层处理
 *
 * @author toms
 * @date 2021-07-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TGlVoucherEntryCashServiceImpl extends ServicePlusImpl<TGlVoucherEntryCashMapper, TGlVoucherEntryCash> implements ITGlVoucherEntryCashService {

	@Resource
	private TGlVoucherEntryCashMapper mapper;
    @Override
    public TGlVoucherEntryCashVo queryById(Integer id){
        return getVoById(id, TGlVoucherEntryCashVo.class);
    }

	/**
	 * 查询列表
	 * @return
	 */
	@Override
	public List<TGlVoucherEntryCashVo> queryByList(List<Integer> ids){
          return mapper.queryByVid(ids);
	}


    @Override
    public TableDataInfo<TGlVoucherEntryCashVo> queryPageList(TGlVoucherEntryCashQueryBo bo) {
		Page<TGlVoucherEntryCashQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<TGlVoucherEntryCashVo> tGlVoucherEntryCashVoIPage = mapper.queryPageList(userPage,bo);
        return PageUtils.buildDataInfo(tGlVoucherEntryCashVoIPage);
    }

    @Override
    public List<TGlVoucherEntryCashVo> queryList(TGlVoucherEntryCashQueryBo bo) {
        return listVo(buildQueryWrapper(bo), TGlVoucherEntryCashVo.class);
    }

    private LambdaQueryWrapper<TGlVoucherEntryCash> buildQueryWrapper(TGlVoucherEntryCashQueryBo bo) {
        LambdaQueryWrapper<TGlVoucherEntryCash> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getBookId() != null, TGlVoucherEntryCash::getBookId, bo.getBookId());
        lqw.eq(bo.getAcctId() != null, TGlVoucherEntryCash::getForAcctId, bo.getAcctId());
        lqw.eq(bo.getVoucherEntryId() != null, TGlVoucherEntryCash::getVoucherEntryId, bo.getVoucherEntryId());
        lqw.eq(bo.getMainTableId() != null, TGlVoucherEntryCash::getMainTableId, bo.getMainTableId());
        lqw.eq(bo.getAttachTableId() != null, TGlVoucherEntryCash::getAttachTableId, bo.getAttachTableId());
        lqw.eq(bo.getAmount() != null, TGlVoucherEntryCash::getAmount, bo.getAmount());
        return lqw;
    }

    @Override
    public Boolean insertByAddBo(TGlVoucherEntryCashAddBo bo) {
        TGlVoucherEntryCash add = BeanUtil.toBean(bo, TGlVoucherEntryCash.class);
        validEntityBeforeSave(add);
        return save(add);
    }

	/**
	 * 批量新增维度
	 * @param list
	 * @return
	 */
	@Override
	public AjaxResult insertBatch(List<TGlVoucherEntryCash> list){
		if(CollectionUtil.isNotEmpty(list)){
          baseMapper.insertAll(list);
		}
		return AjaxResult.success();
	}

    @Override
    public Boolean updateByEditBo(TGlVoucherEntryCashEditBo bo) {
        TGlVoucherEntryCash update = BeanUtil.toBean(bo, TGlVoucherEntryCash.class);
        validEntityBeforeSave(update);
        return updateById(update);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(TGlVoucherEntryCash entity){
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

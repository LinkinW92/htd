package com.skeqi.finance.service.impl.report;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.core.ServicePlusImpl;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.utils.PageUtils;
import com.skeqi.finance.domain.report.DimensionBalance;
import com.skeqi.finance.mapper.report.DimensionBalanceMapper;
import com.skeqi.finance.pojo.bo.report.DimensionBalanceQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpDataVo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import com.skeqi.finance.pojo.vo.report.DimensionBalanceVo;
import com.skeqi.finance.service.report.DimensionBalanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 科目余额Service业务层处理
 *
 * @author toms
 * @date 2021-07-09
 */
@Service
public class DimensionBalanceServiceImpl extends ServicePlusImpl<DimensionBalanceMapper, DimensionBalance> implements DimensionBalanceService {

	@Resource
	private DimensionBalanceMapper mapper;

	@Override
	public TableDataInfo<DimensionBalanceVo> queryPageList(DimensionBalanceQueryBo bo) {
		Page<DimensionBalanceQueryBo> userPage = new Page<>(bo.getPageNum(), bo.getPageSize());
		IPage<DimensionBalanceVo> tBdVoucherGroupVoIPage = mapper.queryPageList(userPage,bo);
		// 获取查询数据
		List<DimensionBalanceVo> records = tBdVoucherGroupVoIPage.getRecords();
		// 去重过滤
		List<DimensionBalanceVo> collect = records.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
			Comparator.comparing(o -> o.getFYear() + ";" + o.getFPeriod() +";"+ o.getFDetailCode() +";"+ o.getFAccountId())
		)), ArrayList::new));

		List<DimensionBalanceVo> resultList = new ArrayList<>();
		for (DimensionBalanceVo d1 : collect) {
			DimensionBalanceVo dimensionBalanceVo = new DimensionBalanceVo();
			dimensionBalanceVo.setFDetailName(d1.getFDetailName()+"小计");
			for (DimensionBalanceVo d2 : records) {
				if (d1.getFYear().equals(d2.getFYear()) && d1.getFPeriod().equals(d2.getFPeriod()) && d1.getFDetailCode().equals(d2.getFDetailCode()) && d1.getFAccountId().equals(d2.getFAccountId())) {
					// 同维度小计 金额计算
					dimensionBalanceVo.setInitDebit(d2.getInitDebit().add(dimensionBalanceVo.getInitDebit()!=null?dimensionBalanceVo.getInitDebit(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setInitCredit(d2.getInitCredit().add(dimensionBalanceVo.getInitCredit()!=null?dimensionBalanceVo.getInitCredit(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setFDebitFor(d2.getFDebitFor().add(dimensionBalanceVo.getFDebitFor()!=null?dimensionBalanceVo.getFDebitFor(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setFDebit(d2.getFDebit().add(dimensionBalanceVo.getFDebit()!=null?dimensionBalanceVo.getFDebit(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setFCreditFor(d2.getFCreditFor().add(dimensionBalanceVo.getFCreditFor()!=null?dimensionBalanceVo.getFCreditFor(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setFCredit(d2.getFCredit().add(dimensionBalanceVo.getFCredit()!=null?dimensionBalanceVo.getFCredit(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setFYtdDebitfor(d2.getFYtdDebitfor().add(dimensionBalanceVo.getFYtdDebitfor()!=null?dimensionBalanceVo.getFYtdDebitfor(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setFYtdDebit(d2.getFYtdDebit().add(dimensionBalanceVo.getFYtdDebit()!=null?dimensionBalanceVo.getFYtdDebit(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setFYtdCreditfor(d2.getFYtdCreditfor().add(dimensionBalanceVo.getFYtdCreditfor()!=null?dimensionBalanceVo.getFYtdCreditfor(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setFYtdCredit(d2.getFYtdCredit().add(dimensionBalanceVo.getFYtdCredit()!=null?dimensionBalanceVo.getFYtdCredit(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setEndDebit(d2.getEndDebit().add(dimensionBalanceVo.getEndDebit()!=null?dimensionBalanceVo.getEndDebit(): BigDecimal.valueOf(0)));
					dimensionBalanceVo.setEndCredit(d2.getEndCredit().add(dimensionBalanceVo.getEndCredit()!=null?dimensionBalanceVo.getEndCredit(): BigDecimal.valueOf(0)));
					// 同维度信息add
					resultList.add(d2);
				}
			}
			resultList.add(dimensionBalanceVo);
		}
		// 计算重新赋值
		tBdVoucherGroupVoIPage.setRecords(resultList);
		return PageUtils.buildDataInfo(tBdVoucherGroupVoIPage);
	}

	@Override
	public List<TBdFlexItemPropertyVo> dimensionList(Integer fAccountId) {
		return mapper.dimensionList(fAccountId);
	}

	@Override
	public List<Map<String,Object>> queryDimensionDetails(TGlVoucherEntryQueryBo bo) {
		return mapper.queryDimensionDetails(bo);
	}

	@Override
	public List<TBdHelpDataVo> dimensionCodeList(Integer flexItemPropertyId) {
		List<TBdHelpDataVo> tBdHelpDataVos = new ArrayList<>();
		TBdFlexItemPropertyVo tBdFlexItemPropertyVos = mapper.selectByFlexItemPropertyId(flexItemPropertyId);
		if (tBdFlexItemPropertyVos!=null){
			if (tBdFlexItemPropertyVos.getFType().equals("2")){
				tBdHelpDataVos = mapper.selectHelpDataByFlexItemPropertyId(tBdFlexItemPropertyVos.getFId());
			}
		}
		return tBdHelpDataVos;
	}
}

package com.skeqi.finance.service.impl.init;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.skeqi.common.exception.CustomException;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skeqi.finance.mapper.init.TGlInitCashflowMapper;
import com.skeqi.finance.domain.init.TGlInitCashflow;
import com.skeqi.finance.service.init.TGlInitCashflowService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TGlInitCashflowServiceImpl extends ServiceImpl<TGlInitCashflowMapper, TGlInitCashflow> implements TGlInitCashflowService{

	@Resource
	private TGlInitCashflowMapper mapper;
    @Override
    public List<TGlCashFlowTypeVo> queryCashFlow() {
        return mapper.queryCashFlow();
    }

	@Override
	public Boolean insertByList(List<TGlInitCashflow> tGlInitCashflows) {
		List<TGlInitCashflow> add = new ArrayList<>();
		List<TGlInitCashflow> edit = new ArrayList<>();
		Boolean result=false;
    	if (StringUtils.checkValNotNull(tGlInitCashflows)&&tGlInitCashflows.size()>0) {
			for (Object a : tGlInitCashflows) {
				TGlInitCashflow s = BeanUtil.toBean(a,TGlInitCashflow.class);
				if (s.getFId() != null && s.getFId() > 0) {
					edit.add(s);
				} else {
					add.add(s);
				}
			}
			if (add.size()>0){
				result = mapper.insertByList(add)>0;
			}
			if (edit.size()>0){
				result = mapper.updateByList(edit)>0;
			}
			if (!result){
				throw new CustomException("保存失败!");
			}

    	}
		return result;
	}

	@Override
	public List<TGlInitCashflow> queryInitCashFlow(TGlInitCashflow initCashflow) {
		if (initCashflow.getFAccountBookId() == null) {
			throw new CustomException("账簿不能为空!");
		}
		if (initCashflow.getFCurrencyId() == null) {
			throw new CustomException("币别不能为空!");
		}
		return mapper.queryInitCashFlow(initCashflow);
	}
}

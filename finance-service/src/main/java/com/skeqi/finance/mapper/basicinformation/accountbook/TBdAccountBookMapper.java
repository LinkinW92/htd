package com.skeqi.finance.mapper.basicinformation.accountbook;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.page.PagePlus;
import com.skeqi.finance.domain.basicinformation.accountbook.TBdAccountBook;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookQueryBo;
import com.skeqi.finance.pojo.vo.book.BookPolicyVo;
import com.skeqi.finance.pojo.vo.book.TBdAccountBookVo;
import org.apache.ibatis.annotations.Param;

/**
 * 账簿Mapper接口
 *
 * @author toms
 * @date 2021-07-13
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
public interface TBdAccountBookMapper extends BaseMapperPlus<TBdAccountBook> {


	Integer queryPolicyId(@Param("orgId") Integer orgId,@Param("acctsystemId") Integer acctsystemId);

	BookPolicyVo queryCalendarInfo(@Param("acctpolicyId") Integer acctpolicyId);

	IPage<TBdAccountBookVo> queryByPage(Page<TBdAccountBookQueryBo> page,@Param("bo") TBdAccountBookQueryBo bo);

	TBdAccountBookVo queryOne(@Param("id") Integer id);

	Integer countMainBook(@Param("orgId") Integer orgId,@Param("acctsystemId") Integer acctsystemId);
}

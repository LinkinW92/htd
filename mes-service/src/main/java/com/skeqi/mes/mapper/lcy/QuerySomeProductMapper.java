package com.skeqi.mes.mapper.lcy;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesTableColumnsReportT;
import com.skeqi.mes.pojo.CMesTableReportT;
import com.skeqi.mes.pojo.QueryProduct;

public interface QuerySomeProductMapper {

	//查看产品列表
	List<CMesTableColumnsReportT> queryAllReportList(Integer id);
	//查询表名
	CMesTableReportT queryTableName(Integer pId);
	//查询数据列表名
	List<CMesTableReportT> queryAllReport();


	List<QueryProduct> getQueryProductList(@Param("string")String string, @Param("queryStartDate")String queryStartDate,
			@Param("queryEndDate")String queryEndDate,@Param("queryValue")String queryValue);




}

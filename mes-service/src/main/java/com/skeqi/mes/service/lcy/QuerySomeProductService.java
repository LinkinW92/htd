package com.skeqi.mes.service.lcy;

import java.util.List;

import com.skeqi.mes.pojo.CMesTableColumnsReportT;
import com.skeqi.mes.pojo.CMesTableReportT;
import com.skeqi.mes.pojo.QueryProduct;

public interface QuerySomeProductService {
	//查询产品类型
		List<CMesTableReportT> queryAllReprot();
		//查询所显示的产品列表
		List<CMesTableColumnsReportT> queryAllReportList(Integer id);


		//查询表名
		CMesTableReportT queryTableName(Integer pId);


		//查询的语句
		List<QueryProduct> getQueryProductList(String string, String queryStartDate, String queryEndDate,
				String queryValue);




}

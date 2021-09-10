package com.skeqi.mes.service.lcy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lcy.QuerySomeProductMapper;
import com.skeqi.mes.pojo.CMesTableColumnsReportT;
import com.skeqi.mes.pojo.CMesTableReportT;
import com.skeqi.mes.pojo.QueryProduct;


@Service
public class QuerySomeProductServiceImpl implements QuerySomeProductService{
	@Autowired
	private  QuerySomeProductMapper querySomeProductMapper;
	//返回表名
	@Override
	public List<CMesTableReportT> queryAllReprot() {
		return this.querySomeProductMapper.queryAllReport();
	}

	//返回列表名
	@Override
	public List<CMesTableColumnsReportT> queryAllReportList(Integer queryProduct) {
		return this.querySomeProductMapper.queryAllReportList(queryProduct);
	}

	//	根据id找数据库表名
	@Override
	public CMesTableReportT queryTableName(Integer pId) {
		return this.querySomeProductMapper.queryTableName(pId);
	}

	//查询产品
	@Override
	public List<QueryProduct> getQueryProductList(String string, String queryStartDate, String queryEndDate,
			String queryValue) {
		// TODO Auto-generated method stub
		return querySomeProductMapper.getQueryProductList(string,queryStartDate,queryEndDate,queryValue);
	}

}

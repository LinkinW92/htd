package com.skeqi.htd.service;

import com.skeqi.htd.po.entity.Product;

import java.util.List;

/**
 * 产品信息服务
 *
 * @author linkin
 */
public interface ProductService {
	/**
	 * 根据数据库id获取产品信息
	 *
	 * @param ids
	 * @return
	 */
	List<Product> getProductByIds(List<Integer> ids);

	/**
	 * 分页
	 *
	 * @param product
	 * @param code
	 * @param productSku
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Product> selectPage(String product, String code, String productSku, Integer pageNo, Integer pageSize);

	/**
	 * 分页记录总数
	 *
	 * @param product
	 * @param code
	 * @param productSku
	 * @return
	 */
	Integer getTotal(String product, String code, String productSku);
}

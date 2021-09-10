package com.skeqi.htd.po.mapper;

import com.skeqi.htd.po.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品信息持久层
 *
 * @author linkin
 */
@Mapper
public interface ProductMapper {
	/**
	 * 根据id获取产品信息
	 *
	 * @param ids
	 * @return
	 */
	List<Product> getProductByIds(@Param("ids") List<Integer> ids);

	/**
	 * 分页
	 *
	 * @param product
	 * @param code
	 * @param productSku
	 * @param pageSize
	 * @param offset
	 * @return
	 */
	List<Product> selectPage(@Param("product") String product,
							 @Param("code") String code,
							 @Param("productSku") String productSku,
							 @Param("pageSize") Integer pageSize,
							 @Param("offset") Integer offset);

	/**
	 * 分页总数
	 *
	 * @param product
	 * @param code
	 * @param productSku
	 * @return
	 */
	Integer getTotal(@Param("product") String product, @Param("code") String code, @Param("productSku") String productSku);
}

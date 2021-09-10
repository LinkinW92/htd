package com.skeqi.htd.service.impl;

import com.skeqi.htd.po.entity.Product;
import com.skeqi.htd.po.mapper.ProductMapper;
import com.skeqi.htd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linkin
 */
@Service
public class ProductServiceImpl implements ProductService {
	private final ProductMapper mapper;

	@Autowired
	public ProductServiceImpl(ProductMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Product> getProductByIds(List<Integer> ids) {
		return this.mapper.getProductByIds(ids);
	}

	@Override
	public List<Product> selectPage(String product,
									String code,
									String productSku,
									Integer pageNo,
									Integer pageSize) {
		return this.mapper.selectPage(product, code, productSku, pageSize, pageSize * pageNo);
	}

	@Override
	public Integer getTotal(String product, String code, String productSku) {
		return this.mapper.getTotal(product, code, productSku);
	}
}

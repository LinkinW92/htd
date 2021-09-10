package com.skeqi.htd.service.impl;

import com.skeqi.common.utils.time.CollectionUtil;
import com.skeqi.htd.po.entity.Supplier;
import com.skeqi.htd.po.mapper.SupplierMapper;
import com.skeqi.htd.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商服务
 *
 * @author linkin
 */
@Service
public class SupplierServiceImpl implements SupplierService {
	private final SupplierMapper mapper;

	@Autowired
	public SupplierServiceImpl(SupplierMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Supplier> getSupplierByNames(List<String> supplierNames) {
		if (CollectionUtil.isEmpty(supplierNames)) {
			return new ArrayList<>();
		}
		return this.mapper.getSupplierByNames(supplierNames);
	}

	@Override
	public List<String> getAllSupplierNames() {
		return mapper.getAllSupplierNames();
	}

	@Override
	public List<Supplier> getSuppliersByName(String supplierName) {
		return this.mapper.getSuppliersByName(supplierName);
	}
}

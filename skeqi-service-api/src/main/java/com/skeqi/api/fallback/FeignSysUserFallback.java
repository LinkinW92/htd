package com.skeqi.api.fallback;

import cn.hutool.json.JSONUtil;
import com.skeqi.api.feign.ISysUserServiceFeignClient;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.exception.CustomException;
//import com.skeqi.manage.domain.SysUserRole;
import com.skeqi.common.utils.Rjson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FeignSysUserFallback implements FallbackFactory<ISysUserServiceFeignClient> {


	@Override
	public ISysUserServiceFeignClient create(Throwable cause) {

		log.error("fallback; reason was: " + cause.getMessage(), cause);

		return new ISysUserServiceFeignClient() {
			@Override
			public AjaxResult addUserInfo(SysUser user) {
				log.error("【新增用户异常】入参[{}]", JSONUtil.toJsonStr(user));
				throw new CustomException("新增用户异常");
			}

			@Override
			public AjaxResult updateStatus(SysUser user) {
				log.error("【修改用户状态异常】入参[{}]", JSONUtil.toJsonStr(user));
				throw new CustomException("修改用户状态异常");
			}

			@Override
			public List<SysUser> findSysUserList(String supplierCode) {
				log.error("【查询用户列表异常】入参[{}]", JSONUtil.toJsonStr(supplierCode));
				throw new CustomException("查询用户列表异常");
			}

			@Override
			public Long findSysUserRole(Long userId) {
				log.error("【查询用户权限异常】入参[{}]", JSONUtil.toJsonStr(userId));
				throw new CustomException("查询用户权限异常");
			}

			@Override
			public AjaxResult batchUpdateRole(String jsonStr) {
				log.error("【批量修改用户权限异常】入参[{}]", JSONUtil.toJsonStr(jsonStr));
				throw new CustomException("批量修改用户权限异常");
			}

			@Override
			public AjaxResult checkUserAccount(String account) {
				log.error("【登录账号校验是否已存在异常】入参[{}]", JSONUtil.toJsonStr(account));
				throw new CustomException("登录账号校验是否已存在异常");
			}

			@Override
			public Rjson checkSupplierCode(String jsonStr) {
				log.error("【获取供应商信息异常】入参[{}]", JSONUtil.toJsonStr(jsonStr));
				throw new CustomException("获取供应商信息异常");
			}
		};
	}

}

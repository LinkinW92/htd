package com.skeqi.api.feign;

import com.skeqi.api.constant.FeignConstant;
import com.skeqi.api.fallback.FeignSysUserFallback;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.utils.Rjson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//import com.skeqi.manage.domain.SysUserRole;

@FeignClient(
	name = FeignConstant.MANAGE_SERVICE,
	url = FeignConstant.LOCAL_URL,
	fallback = FeignSysUserFallback.class)
public interface ISysUserServiceFeignClient {

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/system/user/addUser", method = RequestMethod.POST)
	@ResponseBody
	AjaxResult addUserInfo(SysUser user);

	/**
	 * 根据供应商编码修改用户状态
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/system/user/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	AjaxResult updateStatus(SysUser user);

	/**
	 * 根据供应商编码查询用户列表
	 * @param supplierCode
	 * @return
	 */
	@RequestMapping(value = "/system/user/findSysUserList", method = RequestMethod.POST)
	@ResponseBody
	List<SysUser> findSysUserList(String supplierCode);

	/**
	 * 根据用户ID查询用户权限id
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/system/role/findSysUserRole/{userId}", method = RequestMethod.POST)
	@ResponseBody
	Long findSysUserRole(@PathVariable(value = "userId") Long userId);


	/**
	 * 根据用户ID批量修改用户权限id
	 * @param jsonStr
	 * @return
	 */
	@RequestMapping(value = "/system/role/batchUpdateRole", method = RequestMethod.POST)
	@ResponseBody
	AjaxResult batchUpdateRole(String jsonStr);

	/**
	 * 根据用户名查询账号是否已存在
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/system/user/checkUserAccount/{account}", method = RequestMethod.POST)
	@ResponseBody
	AjaxResult checkUserAccount(@PathVariable(value = "account") String account);


	/**
	 * 获取供应商信息
	 * @param supplierCode
	 * @return
	 */
	@RequestMapping(value = "/cSrmSupplier/checkSupplierCode", method = RequestMethod.POST)
	@ResponseBody
	Rjson checkSupplierCode(String supplierCode);





}

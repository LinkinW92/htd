package com.skeqi.manage.system;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.skeqi.api.feign.ISysUserServiceFeignClient;
import com.skeqi.common.constant.Constants;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.domain.entity.SysMenu;
import com.skeqi.common.core.domain.entity.SysUser;
import com.skeqi.common.core.domain.model.LoginBody;
import com.skeqi.common.core.domain.model.LoginUser;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.JsonUtils;
import com.skeqi.common.utils.Rjson;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.framecore.web.service.SysLoginService;
import com.skeqi.framecore.web.service.SysPermissionService;
import com.skeqi.framecore.web.service.TokenService;
import com.skeqi.manage.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 登录验证
 *
 * @author skeqi
 */
@RestController
@Slf4j
public class SysLoginController {
	@Autowired
	private SysLoginService loginService;

	@Autowired
	private ISysMenuService menuService;

	@Autowired
	private SysPermissionService permissionService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	ISysUserServiceFeignClient iSysUserServiceFeignClient;

	/**
	 * 登录方法
	 *
	 * @param loginBody 登录信息
	 * @return 结果
	 */
	@PostMapping("/login")
	public AjaxResult login(@RequestBody LoginBody loginBody) {
		Map<String, Object> ajax = new HashMap<>();
		// 生成令牌
		String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
			loginBody.getUuid());
		ajax.put(Constants.TOKEN, token);
		return AjaxResult.success(ajax);
	}

	/**
	 * 获取用户信息
	 *
	 * @return 用户信息
	 */
	@GetMapping("getInfo")
	public AjaxResult getInfo() {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		SysUser user = loginUser.getUser();
		// 角色集合
		Set<String> roles = permissionService.getRolePermission(user);
		// 权限集合
		Set<String> permissions = permissionService.getMenuPermission(user);
		//供应商信息
		Map<String, Object> supplierInfo = new HashMap<>();
		// 响应数据
		Map<String, Object> ajax = new HashMap<>();
		if (!StrUtil.isBlank(user.getSupplierCode())) {
			Rjson result = iSysUserServiceFeignClient.checkSupplierCode(user.getSupplierCode());
			if (HttpStatus.HTTP_OK != result.getCode()) {
				throw new CustomException(result.getMsg());
			} else if (ObjectUtil.isNotNull(result.getResult())) {
				log.info("【获取供应商信息出参】[{}]", JSONUtil.toJsonStr(result.toString()));
				JSONObject jsonObject = JsonUtils.parseObject(JSONUtil.toJsonStr(result.getResult()), JSONObject.class);
				supplierInfo.put("supplierCode", jsonObject.get("supplierCode"));
				supplierInfo.put("supplierName", jsonObject.get("name"));
				supplierInfo.put("status", jsonObject.get("status"));
				ajax.put("supplierInfo", supplierInfo);
			}

		}
		ajax.put("user", user);
		ajax.put("roles", roles);
		ajax.put("permissions", permissions);

		return AjaxResult.success(ajax);
	}

	/**
	 * 获取路由信息
	 *
	 * @return 路由信息
	 */
	@GetMapping("getRouters")
	public AjaxResult getRouters() {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
		// 用户信息
		SysUser user = loginUser.getUser();
		List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
		return AjaxResult.success(menuService.buildMenus(menus));
	}
}

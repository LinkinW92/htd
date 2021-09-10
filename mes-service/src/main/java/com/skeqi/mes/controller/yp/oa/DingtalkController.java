package com.skeqi.mes.controller.yp.oa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.skeqi.mes.dingtalk.domain.ConfigDTO;
import com.skeqi.mes.dingtalk.config.AppConfig;
import com.skeqi.mes.dingtalk.domain.ServiceResult;
import com.skeqi.mes.dingtalk.domain.UserDTO;
import com.skeqi.mes.dingtalk.exception.DingtalkEncryptException;
import com.skeqi.mes.dingtalk.service.DingtalkTokenService;
import com.skeqi.mes.dingtalk.util.JsApiSignature;
import com.skeqi.mes.service.yp.oa.DingtalkService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import com.taobao.api.ApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import static com.skeqi.mes.dingtalk.config.UrlConstant.URL_GET_USER_INFO;
import static com.skeqi.mes.dingtalk.config.UrlConstant.URL_USER_GET;

import javax.servlet.http.HttpServletRequest;

/**
 * 钉钉企业内部微应用DEMO, 实现了身份验证（免登）功能
 */
@RestController
@RequestMapping("/api/oa/Dingtalk")
public class DingtalkController {

	 @Autowired
	 AppConfig appConfig ;

	private DingtalkTokenService tokenService  = new DingtalkTokenService(appConfig);

	@Autowired
	DingtalkService service;

	/**
	 * 欢迎页面，通过 /welcome 访问，判断后端服务是否启动
	 *
	 * @return 字符串 welcome
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	/**
	 * 钉钉用户登录，显示当前登录用户的userId和名称
	 *
	 * @param authCode 免登临时authCode
	 * @return 当前用户
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ServiceResult<UserDTO> login(@RequestParam String authCode) {
		System.out.println("2收到的参数" + authCode);
		String accessToken;

		// 获取accessToken
		ServiceResult<String> accessTokenSr = tokenService.getAccessToken();
		if (!accessTokenSr.isSuccess()) {
			return ServiceResult.failure(accessTokenSr.getCode(), accessTokenSr.getMessage());
		}
		accessToken = accessTokenSr.getResult();

		// 获取用户userId
		ServiceResult<String> userIdSr = getUserInfo(accessToken, authCode);
		if (!userIdSr.isSuccess()) {
			return ServiceResult.failure(userIdSr.getCode(), userIdSr.getMessage());
		}

		// 获取用户详情
		return getUser(accessToken, userIdSr.getResult());
	}

	/**
	 * 访问/user/getuserinfo接口获取用户userId
	 *
	 * @param accessToken access_token
	 * @param authCode    临时授权码
	 * @return 用户userId或错误信息
	 */
	private ServiceResult<String> getUserInfo(String accessToken, String authCode) {
		System.out.println("3收到的参数AccessToken:" + accessToken + "应用码" + authCode);
		DingTalkClient client = new DefaultDingTalkClient(URL_GET_USER_INFO);
		OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
		request.setCode(authCode);
		request.setHttpMethod("GET");

		OapiUserGetuserinfoResponse response;
		try {
			response = client.execute(request, accessToken);
		} catch (ApiException e) {
			return ServiceResult.failure(e.getErrCode(), "Failed to getUserInfo: " + e.getErrMsg());
		}
		if (!response.isSuccess()) {
			return ServiceResult.failure(response.getErrorCode(), response.getErrmsg());
		}
		System.out.println("3-2从钉钉获取的信息" + JSON.toJSONString(response));
		return ServiceResult.success(response.getUserid());
	}

	/**
	 * 访问/user/get 获取用户名称
	 *
	 * @param accessToken access_token
	 * @param userId      用户userId
	 * @return 用户名称或错误信息
	 */
	@RequestMapping("/user/get")
	private ServiceResult<UserDTO> getUser(String accessToken, String userId) {
		System.out.println("4收到的参数" + accessToken + "用户编号" + userId);
		DingTalkClient client = new DefaultDingTalkClient(URL_USER_GET);
		OapiUserGetRequest request = new OapiUserGetRequest();
		request.setUserid(userId);
		request.setHttpMethod("GET");

		OapiUserGetResponse response;
		try {
			response = client.execute(request, accessToken);
		} catch (ApiException e) {
			return ServiceResult.failure(e.getErrCode(), "Failed to getUserName: " + e.getErrMsg());
		}
		System.out.println("4-2从钉钉获取的用户信息" + JSON.toJSONString(response));

		UserDTO user = new UserDTO();
		user.setName(response.getName());
		user.setUserid(response.getUserid());
		user.setAvatar(response.getAvatar());
		user.setMobile(response.getMobile());
		user.setAccessToken(accessToken);

		return ServiceResult.success(user);
	}

	@RequestMapping(value = "/config", method = RequestMethod.POST)
	public ServiceResult<ConfigDTO> config(@RequestParam String url) {
		System.out.println("1收到的参数" + url);
		ConfigDTO config = new ConfigDTO();

		ServiceResult<String> jsTicketSr = tokenService.getJsTicket();
		if (!jsTicketSr.isSuccess()) {
			return ServiceResult.failure(jsTicketSr.getCode(), jsTicketSr.getMessage());
		}

		config.setAgentId(appConfig.getAgentId());
		config.setCorpId(appConfig.getCorpId());
		config.setJsticket(jsTicketSr.getResult());
		config.setNonceStr(JsApiSignature.genNonce());
		config.setTimeStamp(System.currentTimeMillis() / 1000);
		String sign;
		try {
			sign = JsApiSignature.sign(url, config.getNonceStr(), config.getTimeStamp(), config.getJsticket());
		} catch (DingtalkEncryptException e) {
			e.printStackTrace();
			return ServiceResult.failure(e.getCode().toString(), e.getMessage());
		}
		config.setSignature(sign);
		System.out.println("返回的参数" + config);
		return ServiceResult.success(config);
	}

	/**
	 * 绑定用户
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "钉钉", method= "绑定用户")
	@Transactional
	@RequestMapping("/bindingUsers")
	public Rjson bindingUsers(HttpServletRequest request) {
		try {
			String mobile = EqualsUtil.string(request, "mobile", "手机号", true);
			String appId = EqualsUtil.string(request, "appId", "AppID", true);

			JSONObject json = new JSONObject();
			json.put("mobile", mobile);
			json.put("appId", appId);

			JSONObject jsonObject = service.bindingUsers(json);

			return Rjson.success(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新用户信息
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "钉钉", method= "更新用户信息")
	@Transactional
	@RequestMapping("/updateUser")
	public Rjson updateUser(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名", true);
			String passWord = EqualsUtil.string(request, "passWord", "密码", true);
			String mobile = EqualsUtil.string(request, "mobile", "手机号", true);
			String appId = EqualsUtil.string(request, "appId", "AppID", true);

			JSONObject json = new JSONObject();
			json.put("userName", userName);
			json.put("passWord", passWord);
			json.put("mobile", mobile);
			json.put("appId", appId);

			service.updateUser(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}

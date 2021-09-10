package com.skeqi.mes.controller.qh;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.pojo.chenj.srm.CSrmSupplier;
import com.skeqi.mes.service.chenj.srm.CSrmSupplierService;
import com.skeqi.mes.service.chenj.srm.SRMLogService;
import com.skeqi.mes.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.Encryption;
import com.skeqi.mes.pojo.CMesCrudT;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.service.gmg.UserService;
import com.skeqi.mes.service.lcy.GetUserService;
import com.skeqi.mes.service.qh.ToGrantAuthorizationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "h.supplierCode管理", description = "登录管理", produces = MediaType.APPLICATION_JSON)
public class CMesGetControllerl {

    @Autowired
    UserService userService;
    @Autowired
    private GetUserService us;

    @Autowired
    ToGrantAuthorizationService toGrantAuthorizationService;

    @Autowired
    private CSrmSupplierService cSrmSupplierService;
    @Autowired
    private SRMLogService srmService;

    @SuppressWarnings("unused")
    @ResponseBody
    @RequestMapping(value = "getLoginSomeValueL", method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public Rjson getLogin(HttpServletRequest request, String userName, String passWord, HttpServletResponse response,
                          HttpSession session) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Timer timer = new Timer();
        JSONObject json = null;
        try {
//			JSONObject registry = InsertRegistry.findRegistry();
//			Object object = registry.get("code");
//			Object object1 = registry.get("msg");
//			if (Integer.parseInt(object.toString()) == 1) {
//				map.put("flag", 5);
//				map.put("msg", object1.toString());
//				return map;
//			}
            //校验
//			String youXiaoQi = toGrantAuthorizationService.check();
            // 把密码加密
            String pwd = Encryption.getPassWord(passWord + userName + 666666 + passWord, 555);
            // 查询是否有这个账号
            CMesUserT user = userService.findByName(userName);

            if (user != null) {

                if (user.getRoleId() == null) {
                    throw new Exception("用户无角色");
                }

                if (user.getStatus().equals("1")) {
                    int a = userService.GetUserInfoByCondition(userName, pwd);
                    if (a > 0) {
                        /*
                         * Map<String, Object> params = new HashMap<>(); params.put("name",
                         * user.getUserName()); String name = TokenUtil.creatToken(params);
                         */
//						Cookie aa = new Cookie("userName", URLEncoder.encode(userName, "UTF-8"));
//						aa.setPath("/");
//						aa.setMaxAge(360 * 60);//
//						response.addCookie(aa);
//						String name = Encryption.getPassWord(userName + 666666, 555);
//						Cookie nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
//						nameCookie.setPath("/");
//						nameCookie.setMaxAge(360 * 60);// 设置Cookie失效时间 负数永久不失效
//						response.addCookie(nameCookie);
//						CMesUserT cMesUserT = new CMesUserT();
//						cMesUserT.setId(user.getId());
//						cMesUserT.setUserName(userName);
//						cMesUserT.setRoleId(user.getRoleId());
//						cMesUserT.setToken(user.getToken());

                        json = new JSONObject();
//						json.put("youXiaoQi", youXiaoQi);
                        json.put("id", user.getId());
                        json.put("userName", userName);
                        json.put("roleId", user.getRoleId());
                        json.put("token", user.getToken());
                        json.put("department", user.getDepartment());
                        session.setAttribute("userName", userName);
                        // 供应商账号登录
                        if (StringUtil.eqNu(user.getSupplierCode())) {
                            CSrmSupplier cSrmSupplier = cSrmSupplierService.checkSupplierCode(user.getSupplierCode(), null);
                            json.put("supplierCode", cSrmSupplier.getSupplierCode());
                            json.put("supplierName", cSrmSupplier.getName());
                            json.put("status", cSrmSupplier.getStatus());
                            ShowIPInfo ip = new ShowIPInfo();
                            String ipInfo = ip.getIpAddr(request);
                            srmService.addSRMLogInfo(userName + "(" + ipInfo + ")", "登入系统");
                        }

                        return new Rjson().success("登录成功", json);

                        // if(Integer.parseInt(object.toString())==2){
                        // map.put("flag", 6);
                        // map.put("msg",object1.toString());
                        // }
                        // 查询登录标识符
//						String loginStatus = userService.findByStatus(userName);
//						if (loginStatus != null && !loginStatus.equals("")) {
//							if (loginStatus.equals("1")) {
//								System.err.println("loginStatus为1");
//								loginStatus = "2";
//								// 根据，名称修改表示符
//								userService.updateStatus(loginStatus, userName);
//								// session.setAttribute("login", "2");
//								Cookie aa1 = new Cookie("login", URLEncoder.encode(loginStatus, "UTF-8"));
//								aa1.setPath("/");
//								aa1.setMaxAge(360 * 60);// 设置Cookie失效时间
//								response.addCookie(aa1);
//							} else if (loginStatus.equals("2")) {
//								loginStatus = "1";
//								userService.updateStatus(loginStatus, userName);
//								// session.setAttribute("login", "1");
//								Cookie aa1 = new Cookie("login", URLEncoder.encode(loginStatus, "UTF-8"));
//								aa1.setPath("/");
//								aa1.setMaxAge(360 * 60);// 设置Cookie失效时间
//								response.addCookie(aa1);
//							}
//
//						} else {
//							System.err.println("loginStatus为空");
//							loginStatus = "1";
//							userService.updateStatus(loginStatus, userName);
//							Cookie aa1 = new Cookie("login", URLEncoder.encode(loginStatus, "UTF-8"));
//							aa1.setPath("/");
//							aa1.setMaxAge(360 * 60);// 设置Cookie失效时间
//							response.addCookie(aa1);
//						}

                    } else {
                        return new Rjson().error("密码错误");
                    }
                } else {
                    return new Rjson().error("账号已冻结");
                }
//
//			} else {
//				// 查询是否有这个账号
//				CSrmSupplier cSrmSupplier = cSrmSupplierService.checkSupplierAccount(userName);
//				if (null != cSrmSupplier) {
//					if (cSrmSupplier.getStatus() > 0) {
//						if (pwd.equals(cSrmSupplier.getPassword()) && userName.equals(cSrmSupplier.getAccount())) {
//							json = new JSONObject();
//							json.put("id", cSrmSupplier.getId());
//							json.put("userName", cSrmSupplier.getAccount());
//							json.put("roleId", cSrmSupplier.getRoleId());
//							// 更新token值与token时间
////                            cSrmSupplierService.updateTokenCreateTimeOrToken(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), userName, token);
//							json.put("token", cSrmSupplier.getToken());
//							json.put("supplierCode", cSrmSupplier.getSupplierCode());
//							json.put("supplierName", cSrmSupplier.getName());
//							json.put("status", cSrmSupplier.getStatus());
//							ShowIPInfo ip = new ShowIPInfo();
//							String ipInfo = ip.getIpAddr(request);
//							srmService.addSRMLogInfo(userName + "(" + ipInfo + ")", "登入系统");
//							session.setAttribute("userName", userName);
//							return new Rjson().success("登录成功", json);
//						} else {
//							return new Rjson().error("密码错误");
//						}
//					} else {
//						return new Rjson().error("账号已冻结");
//					}
//				}

            } else {
                return new Rjson().error("用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            // TODO: handle exception
            return new Rjson().error(e.getMessage());
        }
//		return new Rjson().error("用户不存在");
    }
    /*
     * @RequestMapping(value = "getLogin", method = RequestMethod.POST)
     *
     * @ResponseBody
     *
     * @ApiOperation(value = "退出") public Map<String, Object>
     * getLogoutSomeValue(HttpServletRequest request, String userName) { Map<String,
     * Object> map = new HashMap<String, Object>(); // Map<String, Cookie> cookieMap
     * = ReadCookie(request); String status = userService.findByStatus(userName);
     * HttpSession session = request.getSession(); String id = (String)
     * session.getAttribute("status"); // if(){ // map.put("flag", 0); // }else{ //
     * map.put("flag", 1); // map.put("msg","当前账号在别处登录"); // } // return map; }
     */

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询所有数据...")
    @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true)
    protected JSONObject getLoginValue(String username) {

        // 查询用户拥有的模块信息
        CMesUserT login = us.getLoginValue(username);
        Set<CMesMenuT> setMenu = login.getSetMenu();
        Integer roleId = login.getRoleId();
        List<List<CMesCrudT>> list = new ArrayList<>();
        JSONObject json = new JSONObject();
        for (CMesMenuT cMesMenuT : setMenu) {
            Integer mid = cMesMenuT.getId();
            List<CMesCrudT> crudList = us.findCrudList(roleId, mid);
            if (crudList != null && crudList.size() != 0) {
                list.add(crudList);
            }
        }
        json.put("login", login);
        json.put("crud", list);

        return json;
    }

    @RequestMapping(value = "findBystatus", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询状态")
    protected JSONObject getLoginValue1(String userName, HttpSession session, HttpServletRequest request) {
        JSONObject map = new JSONObject();
        if (!userName.equals("admin")) {
            CMesUserT user = userService.findByName(userName);
            // 数据库status
            String status = user.getLoginStatus();
            map.put("msg", status);
        } else {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                Cookie cook = cookies[i];
                if (cook.getName().equalsIgnoreCase("login")) { // 获取键
                    String loginStatus = cook.getValue().toString(); // 获取值
                    userService.updateStatus(loginStatus, userName);
                }
            }
            CMesUserT user = userService.findByName(userName);
            // 数据库status
            String status = user.getLoginStatus();
            map.put("msg", status);
            map.put("info", "此接口登录用的，不用管");
        }
        return map;

    }

}

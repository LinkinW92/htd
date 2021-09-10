package com.skeqi.mes.util.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.zch.LogDao;
import com.skeqi.mes.service.chenj.CMesApiDictionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.skeqi.mes.pojo.zch.MesOperationLogT;
import com.skeqi.mes.service.zch.LogService;
import com.skeqi.mes.util.ToolUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LogAopAction {
    Logger log = Logger.getLogger(LogAopAction.class);

    // 注入service,用来将日志信息保存在数据库
    @Autowired
    private LogService logService;
    @Autowired
    private CMesApiDictionariesService cMesApiDictionariesService;

    // 配置接入点，即为所要记录的action操作目录
    @Pointcut("@annotation(com.skeqi.mes.util.aop.OptionalLog)")
    private void operationLogAspect() {
    }

    @Autowired
    private LogDao logDao;

    @Around("operationLogAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("=================around");
        // 日志实体对象
        MesOperationLogT logBo = new MesOperationLogT();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // 从cookie获取用户名
        String username = ToolUtils.getCookieUserName(request);
        logBo.setUsername(username);
        // 获取系统当前时间
        logBo.setDt(new Date());

        // 获取访问真实IP
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        logBo.setIp(ipAddress);

        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        HttpSession session = request.getSession();
        // 获取请求路径
//		String actionUrl = request.getRequestURI();
//		System.err.println("actionUrls"+actionUrl);
        String actionUrl = request.getRequestURI().replaceAll("/MES_System/", "");

        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = null;

        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (SecurityException e1) {
            e1.printStackTrace();
        }
        if (null != method) {
            // 获取方法（此为自定义注解）
            OptionalLog op = method.getAnnotation(OptionalLog.class);

            // 获取注解的modules 设为操作模块
            logBo.setModule(op.module());
            logBo.setModule2(op.module2());
            // 获取注解的methods 设为执行方法
            logBo.setMethods(op.method());
            // 将上面获取到的请求路径 设为请求路径
            logBo.setActionurl(actionUrl);
            try {
                // 执行目标方法
                object = pjp.proceed();
                // 接受客户端的数据
                Map<String, String[]> map = request.getParameterMap();
                // 解决获取参数乱码
                Map<String, Object> newmap = new HashMap<String, Object>();
                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                    String name = entry.getKey();
                    String values[] = entry.getValue();

                    if (values == null) {
                        newmap.put(name, null);
                        continue;
                    }
                    String newvalues[] = new String[values.length];
                    for (int i = 0; i < values.length; i++) {
                        String value = values[i];
                        value = new String(value.getBytes("iso8859-1"), request.getCharacterEncoding());
                        newvalues[i] = value; // 解决乱码后封装到Map中
                    }
                    if (newvalues.length == 1) {
                        newmap.put(name, newvalues[0]);
                    } else {
                        newmap.put(name, newvalues);
                    }

                }
                logBo.setContent(JSONObject.toJSONString(newmap));
                // 1为执行成功
                logBo.setCommite((byte) 1);

                log.warn(logBo.toString());

                // 添加到数据库
                try {

                    String params = String.valueOf(session.getAttribute("params"));
//                    System.err.println("params正常获取后" + params);
                    // 初始化参数
                    params = initScriptParams(actionUrl, params, session);
//                    System.err.println("params最终数据" + params);
                    logBo.setParams(params);
//                    System.err.println("LogAop获取后" + logBo.getParams());
                    session.removeAttribute("params");


                    Map<String, Object> maps = new HashMap<>();
                    maps.put("actionUrl", actionUrl);
                    maps.put("commitType", session.getAttribute("commitType"));
                    maps.put("paramsType", session.getAttribute("paramsType"));
                    if (cMesApiDictionariesService.selectApiDictionaries(actionUrl) < 1) {
                        cMesApiDictionariesService.addApiDictionaries(maps);
                    }
                    // 过滤apiAnnotation的数据
                    String apiAnnotation = String.valueOf(session.getAttribute("apiAnnotation"));
                    if (apiAnnotation.contains("=") || apiAnnotation.contains(":")) {
                        apiAnnotation = null;
                    } else {
                        logBo.setApiAnnotation(apiAnnotation);
                    }
                    logService.addOperationLog(logBo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    /**
     * 表单参数数据拼接=》(json)
     *
     * @param request
     * @return
     */
//    private String getParamStringAppendJson(HttpServletRequest request) throws IOException {
//        JSONObject requestJsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
//        return requestJsonObject.toJSONString();
//    }

    // 初始化脚本参数
    private String initScriptParams(String actionUrl, String params, HttpSession session) {
        // 新增配方明细
        if(actionUrl.equals("api/insertrecipeDetail")){
            JSONObject jsonObject = JSONObject.parseObject(params);
            if(null!=jsonObject.get("recipeId")) jsonObject.remove("recipeId");
            if(null!=jsonObject.get("id")) jsonObject.remove("id");
            jsonObject.put("recipeCode",session.getAttribute("recipeCode"));
            jsonObject.put("totalRecipeCode",session.getAttribute("totalRecipeCode"));
            session.removeAttribute("recipeCode");
            session.removeAttribute("totalRecipeCode");
            params=jsonObject.toJSONString();
            return params;
        }else if(actionUrl.equals("api/updaterecipedetails")){
            JSONObject jsonObject = JSONObject.parseObject(params);
            if(null!=jsonObject.get("recipeId")) jsonObject.remove("recipeId");
            if(null!=jsonObject.get("id")) jsonObject.remove("id");
            jsonObject.put("recipeCode",session.getAttribute("recipeCode"));
            jsonObject.put("totalRecipeCode",session.getAttribute("totalRecipeCode"));
            session.removeAttribute("recipeCode");
            session.removeAttribute("totalRecipeCode");
            params=jsonObject.toJSONString();
            return params;
            // 生成条码
        }else if(actionUrl.equals("qhapi/nextBarcode")){
            System.err.println("lineCode:||"+session.getAttribute("lineCode"));
            JSONObject jsonObject = JSONObject.parseObject(params);
            if(null!=jsonObject.get("lineId")) jsonObject.remove("lineId");
            jsonObject.put("lineCode",session.getAttribute("lineCode"));
            session.removeAttribute("lineCode");
            params=jsonObject.toJSONString();
            return params;
        }


        String[] paramsItem = params.split(" ");
//        System.err.println("拆分后paramsItem:");
//        for (String s : paramsItem) {
//            System.err.println(s);
//        }
        StringBuilder sb = new StringBuilder(1000);
        for (int i = 0; i < paramsItem.length; i++) {
            String[] pa = paramsItem[i].split("=");
//            System.err.println("pa" + pa.length);
//            System.err.println("拆分后pa:");
//            for (String s : pa) {
//                System.err.println(s);
//            }
            switch (actionUrl) {
                // 工位
                case "api/line/addLine":
                    if ("code".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("lineCode").append("=").append(pa[1]);
//                            System.err.println("session作用域中获取" + session.getAttribute("lineCode"));
                        System.err.println(sb.toString());
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 工位
                case "api/station/addStation":
                    if ("lineId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 新增物料
                case "api/material/addMaterial":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("materialCode").append("=").append('"').append(session.getAttribute("materialCode")).append('"');
                        session.removeAttribute("materialCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 新增标签
                case "api/addLabelManager":
                    if ("labelTypeId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("labelTypeCode").append("=").append('"').append(session.getAttribute("labelTypeCode")).append('"');
                        session.removeAttribute("labelTypeCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("lineId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 添加配方明细
//                case "api/insertrecipeDetail":
//                    if ("recipeId".equals(pa[0])) {
//                        String oldData = pa[0] + ":" + pa[1];
//                        sb.append("recipeCode").append(":").append('"').append(session.getAttribute("recipeCode")).append('"').append(" ");
//                        sb.append("totalRecipeCode").append(":").append('"').append(session.getAttribute("totalRecipeCode")).append('"');
//                        session.removeAttribute("recipeCode");
//                        session.removeAttribute("totalRecipeCode");
//                        params = params.replaceAll(oldData, sb.toString());
//                        sb.setLength(0);
//                        break;
//                    }else if("id".equals(pa[0])){
//                        String oldData = pa[0] + ":" + pa[1];
//                        params = params.replaceAll(oldData, "");
//                        sb.setLength(0);
//                        break;
//                    }
//                    break;
                    // 保存工艺路线
                case "api/saveProcessRoute":
                    if ("productionId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("productionCode").append("=").append('"').append(session.getAttribute("productionCode")).append('"');
                        session.removeAttribute("productionCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("lineId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("routingId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        params = params.replaceAll(oldData, "");
                        sb.setLength(0);
                        break;
                    }else if("lineList".equals(pa[0])){
                        String oldData = pa[0] + "=" + pa[1];
                        System.err.println("lineList"+oldData);
                        sb.append("lineListCode").append("=").append('"').append(session.getAttribute("lineListCode")).append('"');
                        session.removeAttribute("lineListCode");
                        params = params.replace(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }else if("stepList".equals(pa[0])){
                        String oldData = pa[0] + "=" + pa[1];
                        System.err.println("stepList"+oldData);
                        sb.append("stepListCode").append("=").append('"').append(session.getAttribute("stepListCode")).append('"');
                        session.removeAttribute("stepListCode");
                        params = params.replace(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }else if("nodeList".equals(pa[0])){
                        String oldData = pa[0] + "=" + pa[1];
                        System.err.println("nodeList"+oldData);
                        sb.append("nodeListCode").append("=").append('"').append(session.getAttribute("nodeListCode")).append('"');
//                        session.removeAttribute("nodeListCode");
                        params = params.replace(oldData, sb.toString());
//                        params =params+sb.toString();
                        sb.setLength(0);
                        break;
                    }
                    // 添加总配方
                case "api/addtotalRecipe":
                    if ("productionId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("productionCode").append("=").append('"').append(session.getAttribute("productionCode")).append('"');
                        session.removeAttribute("productionCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("lineId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;

                // 添加工位配方
                case "api/addRecipe":
                    if ("totalId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("totalCode").append("=").append('"').append(session.getAttribute("totalCode")).append('"');
                        session.removeAttribute("totalCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("stationId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("stationCode").append("=").append('"').append(session.getAttribute("stationCode")).append('"');
                        session.removeAttribute("stationCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;

                // 添加BOM管理
//                case "api/materialList/addMeterial":
//                    if ("listNo".equals(pa[0])) {
//                        String oldData = pa[0] + "=" + pa[1];
//                        sb.append("listCode").append("=").append(pa[1]);
//                        params = params.replaceAll(oldData, sb.toString());
//                        sb.setLength(0);
//                        break;
//                    }
//                    break;

                // 添加班次
//                case "api/addShfitTeam":
//                    if ("lineName".equals(pa[0])) {
//                        String oldData = pa[0] + "=" + pa[1];
//                        sb.append("listCode").append("=").append(pa[1]);
//                        params = params.replaceAll(oldData, sb.toString());
//                        sb.setLength(0);
//                        break;
//                    }
//                    break;

                // 修改BOM明细管理
                case "api/materialList/editMaterialDetail":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        params = params.replaceAll(oldData, "");
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 新增工单
                case "wor/addWorkorder":
                    // 修改工单
                case "wor/updateWorkorder":
                    if ("lineId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("productId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("productCode").append("=").append('"').append(session.getAttribute("productCode")).append('"');
                        session.removeAttribute("productCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("routingId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("routingCode").append("=").append('"').append(session.getAttribute("routingCode")).append('"');
                        session.removeAttribute("routingCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("teamId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("teamName").append("=").append('"').append(session.getAttribute("teamName")).append('"').append(" ");
                        sb.append("startTime").append("=").append('"').append(session.getAttribute("startTime")).append('"').append(" ");
                        sb.append("endTime").append("=").append('"').append(session.getAttribute("endTime")).append('"');
                        session.removeAttribute("teamName");
                        session.removeAttribute("startTime");
                        session.removeAttribute("endTime");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("barcodeRuleId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("barcodeRuleCode").append("=").append('"').append(session.getAttribute("barcodeRuleCode")).append('"');
                        session.removeAttribute("barcodeRuleCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("bomId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("bomCode").append("=").append('"').append(session.getAttribute("bomCode")).append('"');
                        session.removeAttribute("bomCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("totalRecipeId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("totalRecipeCode").append("=").append('"').append(session.getAttribute("totalRecipeCode")).append('"');
                        session.removeAttribute("totalRecipeCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("ID".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("workOrderCode").append("=").append('"').append(session.getAttribute("workOrderCode")).append('"');
                        session.removeAttribute("workOrderCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    // 删除工单
                case "wor/deleteWorkorder":
                    if ("ID".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("workOrderCode").append("=").append('"').append(session.getAttribute("workOrderCode")).append('"');
                        session.removeAttribute("workOrderCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除班次
                case "api/deleteShiftTeam":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("teamName").append("=").append('"').append(session.getAttribute("teamName")).append('"').append(" ");
                        sb.append("startTime").append("=").append('"').append(session.getAttribute("startTime")).append('"').append(" ");
                        sb.append("endTime").append("=").append('"').append(session.getAttribute("endTime")).append('"');
                        session.removeAttribute("teamName");
                        session.removeAttribute("startTime");
                        session.removeAttribute("endTime");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除料单
                case "api/materialList/deleteMaterial":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("listNo").append("=").append('"').append(session.getAttribute("listNo")).append('"');
                        session.removeAttribute("listNo");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除料单明细
                case "api/materialList/delMaterialDetail":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("figureNo").append("=").append('"').append(session.getAttribute("figureNo")).append('"');
                        session.removeAttribute("figureNo");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除配方明细
                case "api/delRecipeDetail":
                    if ("recipeId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("recipeCode").append("=").append('"').append(session.getAttribute("recipeCode")).append('"').append(" ");
                        sb.append("totalRecipeCode").append("=").append('"').append(session.getAttribute("totalRecipeCode")).append('"');
                        session.removeAttribute("recipeCode");
                        session.removeAttribute("totalRecipeCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }else if("id".equals(pa[0])){
                        String oldData = pa[0] + "=" + pa[1];
                        params = params.replaceAll(oldData, "");
                        sb.setLength(0);
                        break;
                    }else if("stepNo".equals(pa[0])){
                        String oldData = pa[0] + "=" + pa[1];
                        params = params.replaceAll(oldData, "");
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除工位配方
                case "api/delRecipe":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("totalRecipeName").append("=").append('"').append(session.getAttribute("totalRecipeName")).append('"').append(" ");
                        sb.append("recipeName").append("=").append('"').append(session.getAttribute("recipeName")).append('"');
                        session.removeAttribute("totalRecipeName");
                        session.removeAttribute("recipeName");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                //  删除总配方
                case "api/delTotalRecipe":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("totalRecipeName").append("=").append('"').append(session.getAttribute("totalRecipeName")).append('"');
                        session.removeAttribute("totalRecipeName");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除工艺路线
                case "api/deleteProcessRoute":
                    if ("routingId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("routingCode").append("=").append('"').append(session.getAttribute("routingCode")).append('"').append(" ");
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("routingCode");
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除产品
                case "api/Production/deletePro":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("proCode").append("=").append('"').append(session.getAttribute("proCode")).append('"');
                        session.removeAttribute("proCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除标签
                case "api/removeLabelManager":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("labelCode").append("=").append('"').append(session.getAttribute("labelCode")).append('"').append(" ");
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("labelCode");
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除规则
                case "api/removeRuleTypeManager":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("ruleCode").append("=").append('"').append(session.getAttribute("ruleCode")).append('"');
                        session.removeAttribute("ruleCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除物料
                case "api/material/deleteMaterial":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("materialCode").append("=").append('"').append(session.getAttribute("materialCode")).append('"');
                        session.removeAttribute("materialCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除工位
                case "api/station/delStation":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("stationCode").append("=").append('"').append(session.getAttribute("stationCode")).append('"').append(" ");
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"').append(" ");
                        sb.append("stationIndex").append("=").append('"').append(session.getAttribute("stationIndex")).append('"');
                        session.removeAttribute("stationCode");
                        session.removeAttribute("lineCode");
                        session.removeAttribute("stationIndex");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 删除产线信息
                case "api/line/delLine":
                    // 修改产线
                case "api/line/editLine":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;

                // 修改工位
                case "api/station/editStation":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("stationCode").append("=").append('"').append(session.getAttribute("stationCode")).append('"');
                        session.removeAttribute("stationCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("lineId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 修改物料
                case "api/material/updateMaterial":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("materialCode").append("=").append('"').append(session.getAttribute("materialCode")).append('"');
                        session.removeAttribute("materialCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("bomId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        params = params.replaceAll(oldData, "");
                        break;
                    }
                    break;
                // 修改规则
                case "api/editRuleTypeManager":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("ruleCode").append("=").append('"').append(session.getAttribute("ruleCode")).append('"');
                        session.removeAttribute("ruleCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 修改标签
                case "api/editLabelManager":
                    if ("labelTypeId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("labelTypeCode").append("=").append('"').append(session.getAttribute("labelTypeCode")).append('"');
                        session.removeAttribute("labelTypeCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("lineId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("lineCode").append("=").append('"').append(session.getAttribute("lineCode")).append('"');
                        session.removeAttribute("lineCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        params = params.replaceAll(oldData, "");
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 修改产品
                case "api/Production/updateProduct":
                    if ("id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("productionCode").append("=").append('"').append(session.getAttribute("productionCode")).append('"');
                        session.removeAttribute("productionCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    }
                    break;
                    // 修改料单
                case "api/materialList/updateMeterial":
                    if ("listNo".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("listCode").append("=").append('"').append(session.getAttribute("listCode")).append('"');
                        session.removeAttribute("listCode");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("lineId".equals(pa[0]) || "id".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        params = params.replaceAll(oldData, "");
                        sb.setLength(0);
                        break;
                    }
                    break;
                // 修改配方明细
//                case "api/updaterecipedetails":
//                    if ("recipeId".equals(pa[0])) {
//                        String oldData = pa[0] + ":" + pa[1];
//                        sb.append("recipeCode").append(":").append('"').append(session.getAttribute("recipeCode")).append('"').append(" ");
//                        sb.append("totalRecipeCode").append(":").append('"').append(session.getAttribute("totalRecipeCode")).append('"');
//                        session.removeAttribute("recipeCode");
//                        session.removeAttribute("totalRecipeCode");
//                        params = params.replaceAll(oldData, sb.toString());
//                        sb.setLength(0);
//                        break;
//                    }else if("id".equals(pa[0])){
//                        String oldData = pa[0] + ":" + pa[1];
//                        params = params.replaceAll(oldData, "");
//                        sb.setLength(0);
//                        break;
//                    }
//                    break;
                    // 修改料单
                case "api/updateShiftTeam":
                    if ("lineName".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        sb.append("teamName").append("=").append('"').append(session.getAttribute("teamName")).append('"').append(" ");
                        sb.append("startTime").append("=").append('"').append(session.getAttribute("startTime")).append('"').append(" ");
                        sb.append("endTime").append("=").append('"').append(session.getAttribute("endTime")).append('"');
                        session.removeAttribute("teamName");
                        session.removeAttribute("startTime");
                        session.removeAttribute("endTime");
                        params = params.replaceAll(oldData, sb.toString());
                        sb.setLength(0);
                        break;
                    } else if ("id".equals(pa[0]) || "lineId".equals(pa[0])) {
                        String oldData = pa[0] + "=" + pa[1];
                        params = params.replaceAll(oldData, "");
                        sb.setLength(0);
                        break;
                    }
                    break;
                default:
                    // 默认脚本重放操作,已处理过
                    return params;
            }
        }
        return params;
    }
}


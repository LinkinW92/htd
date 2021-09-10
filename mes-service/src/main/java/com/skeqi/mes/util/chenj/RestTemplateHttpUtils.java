package com.skeqi.mes.util.chenj;


import com.skeqi.mes.mapper.wf.linesidelibrary.CLslDictionaryTMapper;
import com.skeqi.mes.pojo.wf.linesidelibrary.CLslDictionaryT;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author ChenJ
 * @date 2021/7/30
 * @Classname RestTemplateHttp
 * @Description
 */
@Component
public class RestTemplateHttpUtils {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CLslDictionaryTMapper cLslDictionaryTMapper;

    /**
     * 发送post请求远程调用
     * @param map
     * @throws Exception
     */
    public String  sendPostRequestKThree(Map<String, Object> map) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("jktype", "PORequest");
//        map.put("method", "view");
//        map.put("filter", "");
        // 将编码改为utf-8
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // 获取请求路径
        // kThreeUrl K3接口访问地址
        CLslDictionaryT kThreeUrl = cLslDictionaryTMapper.selectByKey(String.valueOf(map.get("key")));
        String requestUrl = kThreeUrl.getValue();
        ResponseEntity<String> forEntity = restTemplate.postForEntity(requestUrl, map, String.class);
        return forEntity.getBody();
//        } else {
//            return Rjson.error("服务不可用,请稍后再试"+String.valueOf(System.currentTimeMillis()).substring(0,10));
//            return null;
//        }
    }
}

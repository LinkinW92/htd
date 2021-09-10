package com.skeqi.mes.util.yp.WeiXin;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class WeiXinHttpClientUtil {

	 /**
     * 以get方式调用第三方接口
     * @param url
     * @param token
     * @return
     */
    public static JSONObject doGet(String url) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        //api_gateway_auth_token自定义header头，用于token验证使用
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //返回json格式
                String res = EntityUtils.toString(response.getEntity());
                return JSONObject.parseObject(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * java发送raw
	 * @author zengwei
	 * @email 1014483974@qq.com
	 * @version 2019年3月01日 下午4:02:02
	 */
	public static JSONObject deviceRequest(String url,JSONObject param) {

	    JSONObject result = null;

	    try {
	        @SuppressWarnings({"resource"})
	        HttpClient httpClient = new DefaultHttpClient();
	        HttpPost post = new HttpPost(url);
	        StringEntity postingString = new StringEntity(param.toJSONString(),Charset.forName("UTF-8"));
	        post.setEntity(postingString);
	        post.setHeader("Content-type", "application/json; charset=utf-8");
	        HttpResponse response = httpClient.execute(post);
	        String content = EntityUtils.toString(response.getEntity());

	        result = (JSONObject) JSONObject.parse(content);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}
}

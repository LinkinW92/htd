package com.skeqi.mes.util.webservice;

import com.alibaba.fastjson.JSONObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/5/6 9:58
 */
public class threeModoule {

    public void test1(){
//        String webServiceUrl = "";
//        Service service = new Service();
//        Call call = null;
//        String invoke = "";
//        String issuccess = "";
//        try {
//            call = (Call)service.createCall();
//            call.setTargetEndpointAddress(new java.net.URL(webServiceUrl));
//            call.setOperationName(new QName(webServiceUrl,""));
//            JSONObject j = new JSONObject();
//            j.put("theCityName","上海");
//            invoke = (String)call.invoke(j.toString());
//            System.out.println(invoke);
//        }
    }

    public static void main(String[] args) throws Exception{
        String webServiceUrl = "http://localhost:9000/HelloWorld?wsdl";
        Service service = new Service();
        Call call = null;
        String invoke = "";
        String issuccess = "";
        try {
            call = (Call)service.createCall();
            call.setTargetEndpointAddress(webServiceUrl);
            call.setSOAPActionURI("http://webservice.util.skeqi.com/");
            call.setOperationName(new QName("http://webservice.util.skeqi.com/","sayHello"));
            call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);
            JSONObject j = new JSONObject();
            j.put("theCityName","上海");
            j.put("weather","晴天");
            j.put("age","10");
            j.put("sex","男");
            invoke = call.invoke(new Object[]{j.toJSONString()}).toString();
            System.out.println(invoke);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

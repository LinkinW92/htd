package com.skeqi.mes.util.webservice;

import com.alibaba.fastjson.JSONObject;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/5/6 14:34
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public  class Test {

    @WebMethod
    public String sayHello(String str){
        JSONObject js = JSONObject.parseObject(str);
        for (String strs : js.keySet()){
            System.out.println("value = "+js.get(strs));
        }
        System.out.println("get Message...");
        String result = "Hello World, "+str;
        return result;
    }

    public static void main(String[] args) {
        System.out.println("server is running");
        String address="http://localhost:9000/HelloWorld?wsdl";
        Object implementor =new Test();
        Endpoint.publish(address, implementor);
    }
}

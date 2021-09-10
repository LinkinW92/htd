package com.skeqi.mes.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

public class Rjson {

	private Integer code;
	private String msg;
	private Object result;


	public static Rjson success(Object result){
		return new Rjson(200,"请求成功",result);
	}

	public static Rjson success(Object... result){
		return new Rjson(200,"请求成功",result);
	}

	public static Rjson success(String msg,Object result){
		return new Rjson(200,msg,result);
	}

	public static Rjson success(Integer code,Object result){
		return new Rjson(code,"请求成功",result);
	}

	public static Rjson success(){
		return new Rjson(200,"请求成功",null);
	}

	public static Rjson error(Integer code,String msg){
		return new Rjson(code,msg,null);
	}

	public static Rjson error(String msg){
		return new Rjson(500,msg,null);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Rjson(Integer code, String msg, Object result) {
		this.code = code;
		this.result = result;
		this.msg = msg;
	}

	public Rjson() {

	}

	@Override
	public String toString() {
		return "Rjson [code=" + code + ", msg=" + msg + ", result=" + result + "]";
	}

	public static PageInfo<Map<String, Object>> getPageInfoByFormatTime(List<Map<String, Object>> list){
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		List<Map<String, Object>> l = pageInfo.getList();
		for (Map<String, Object> m : l) {
			for (Map.Entry<String, Object> entry : m.entrySet()) {
				Object value = entry.getValue();
				if(value != null && "Timestamp".equals(value.getClass().getSimpleName())){
					entry.setValue(value.toString().substring(0,19));
				}
			}
		}
		return pageInfo;
	}

	public static List<Map<String, Object>> getListByFormatTime(List<Map<String, Object>> list){
		for (Map<String, Object> m : list) {
			for (Map.Entry<String, Object> entry : m.entrySet()) {
				Object value = entry.getValue();
				if(value != null && "Timestamp".equals(value.getClass().getSimpleName())){
					entry.setValue(value.toString().substring(0,19));
				}
			}
		}
		return list;
	}

	public static Map<String, Object> getMapByFormatTime(Map<String, Object> map){
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Object value = entry.getValue();
			if(value != null && "Timestamp".equals(value.getClass().getSimpleName())){
				entry.setValue(value.toString().substring(0,19));
			}
		}
		return map;
	}

	public static Map<String, Object> reservingDifferences(Map<String, Object> nMap, Map<String, Object> oMap) throws Exception{
		oMap = getMapByFormatTime(oMap);
		System.out.println(nMap);
		System.out.println(oMap);
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> oldMap = new HashMap<>();
		Iterator<String> iterator = nMap.keySet().iterator();
		Set<String> keys = new HashSet<>();
		while (iterator.hasNext()) {
			String key = iterator.next();
			oldMap.put(key, oMap.get(key));
			if(!key.equals("ID")){
				if(nMap.get(key) == null || "".equals(nMap.get(key).toString()) || (oMap.get(key) != null && oMap.get(key).toString().equals(nMap.get(key).toString()))){
					keys.add(key);
				}
			}
		}
		for (String key : keys) {
			nMap.remove(key);
			oldMap.remove(key);
		}
		map.put("nMap", JSONObject.toJSONString(nMap));
		map.put("oMap", JSONObject.toJSONString(oldMap));
		return map;
	}
}

package com.skeqi.mes.service.clientpur;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesClientPurviewT;

public interface ClientPurviewService {

	//添加客户端
	void addClientPurview(Map<String, Object> map);

	//客户端功能信息
	List<CMesClientPurviewT> clientPurviewList(Map<String, Object> map);

	//修改客户端信息
	void updateClientPur(Map<String, Object> map);

	//删除客户端信息
	void delClientPur(Map<String, Object> map);

}

package com.skeqi.mes.service.wms.homepage;

import java.util.List;

import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsStorageListDetailT;


/**
 * 首页
 * @author Timberon
 *
 */
public interface HomepageService {

	//出库量查询
	public Integer outboundQuantityQuery(String ks,String js);

	//入库量查询
	public Integer inputVolumeQuery(String ks,String js);

	//库存余量查询
	public Integer inventoryMarginQuery();

	//通知消息查询
	public Integer messageNumberQuery();

	//曲线图入库查询
	public List<CWmsStorageListDetailT> rkfind(String ks,String js);

	//曲线图出库查询
	public List<CWmsStorageListDetailT> ckfind(String ks,String js);

	//库位查询
	public List<CWmsLocationT> cWmsLocationTQuery();

}

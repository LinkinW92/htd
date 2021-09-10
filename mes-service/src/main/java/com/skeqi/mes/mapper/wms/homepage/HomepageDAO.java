package com.skeqi.mes.mapper.wms.homepage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsStorageListDetailT;

/**
 * 首页
 * @author Timberon
 *
 */
public interface HomepageDAO {
	// 出库量查询
	public Integer outboundQuantityQuery(@Param("ks")String ks,@Param("js")String js);

	// 入库量查询
	public Integer inputVolumeQuery(@Param("ks")String ks,@Param("js")String js);

	// 库存余量查询
	public Integer inventoryMarginQuery();

	// 通知消息查询
	public Integer messageNumberQuery();

	//曲线图入库查询
	public List<CWmsStorageListDetailT> rkfind(@Param("ks")String ks,@Param("js")String js);

	//曲线图出库查询
	public List<CWmsStorageListDetailT> ckfind(@Param("ks")String ks,@Param("js")String js);

	//库位查询
	public List<CWmsLocationT> cWmsLocationTQuery();

}

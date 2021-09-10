package com.skeqi.mes.mapper.fqz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesCheckoutEnterT;
import com.skeqi.mes.pojo.CMesCheckoutListT;
import com.skeqi.mes.pojo.CMesFindT;
import com.skeqi.mes.pojo.CMesWarehouseListT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesEolT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.PMesPlanPrintT;

public interface CheckoutEnterDAO {

	//在下线总成表里查询sn
	public Integer findSN(@Param("sn")String sn);

	//查询产品名称和编号
	public CMesFindT findPro(@Param("sn")String sn);

	//查询入库检验项
	public List<CMesCheckoutListT> findCheckList(@Param("sn")String sn);

	//查询物料表keypart_num为空的条数
	public Integer findKeypartCount(@Param("sn")String sn);

	//查询螺栓表A、T列为空的数据
	public Integer findBoltCount(@Param("sn")String sn);

	//查询气密表LEAKAGE_PV、LEAKAGE_LV为空的条数
	public Integer findLeakageCount(@Param("sn")String sn);

	//查询eol表
	public Integer findEolCount(@Param("sn")String sn);

	//根据sn查询物料信息
	public List<PMesKeypartT> findKeypart(@Param("sn")String sn);

	//根据sn查询螺栓信息
	public List<PMesBoltT> findBolt(@Param("sn")String sn);

	//根据sn查询气密信息
	public List<PMesLeakageT> findLeakage(@Param("sn")String sn);

	//查询Eol表
	public List<PMesEolT> findEol(@Param("sn")String sn);


	//添加入库检验表
	public void insertEnter(CMesCheckoutEnterT list);

	//根据产品id和序号查询内容
	public CMesCheckoutListT findByNo(@Param("pid")String pid,@Param("no")String no);

	//根据sn在打印表里查询计划和订单id
	public PMesPlanPrintT findPlan(@Param("sn")String sn);

	//添加仓库
	public void inserWareHouse(CMesWarehouseListT list);

	//查询仓库表是否有这个总成
	public Integer findHouseSN(@Param("sn")String sn);

}

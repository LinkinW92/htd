package com.skeqi.mes.service.fqz;

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

public interface CheckoutEnterService {

	    //在下线总成表里查询sn
		public Integer findSN(String sn);

		//查询产品名称和编号
		public CMesFindT findPro(String sn);

		public List<CMesCheckoutListT> findCheckList(String sn);

		//根据sn查询物料信息
		public List<PMesKeypartT> findKeypart(String sn);

		//根据sn查询螺栓信息
		public List<PMesBoltT> findBolt(String sn);

		//根据sn查询气密信息
		public List<PMesLeakageT> findLeakage(String sn);

		//根据sn查询eol信息
		public List<PMesEolT> findEol(String sn);

		//查询物料表keypart_num为空的条数
		public Integer findKeypartCount(String sn);

		//查询螺栓表A、T列为空的数据
		public Integer findBoltCount(String sn);

		//查询气密表LEAKAGE_PV、LEAKAGE_LV为空的条数
		public Integer findLeakageCount(String sn);

		//查询eol表
		public Integer findEolCount(String sn);

		//添加入库检验表
		public void insertEnter(CMesCheckoutEnterT list);

		//根据产品id和序号查询内容
		public CMesCheckoutListT findByNo(String pid,String no);

		//根据sn在打印表里查询计划和订单id
		public PMesPlanPrintT findPlan(String sn);

		//添加仓库
		public void inserWareHouse(CMesWarehouseListT list);

		//查询仓库有没有此总成
		public Integer findHouseSN(String sn);

}

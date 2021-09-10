package com.skeqi.mes.service.fqz;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.RMesNewSn;
import com.skeqi.mes.pojo.RMesPlanPrintT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;

public interface HeavyService {

	public List<RMesNewSn> findAll();

	public List<RMesPlanT> findRmesPlanT();

	public Integer jugdeSN(String sn);

	public List<RMesWorkorderDetailT> findDetil(Integer id);

	public Integer findPrint(Integer id);

	public List<RMesPlanPrintT> findSn(Integer id);

	public void insertnewsn(String newsn,String oldsn);

	public String findNewSN(Integer id);

	public Integer jugdeoldSN(String sn);

	public Integer jugdenewSN(String sn);

	public Integer jugdeoldSN_New(String sn);
}

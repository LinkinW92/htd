package com.skeqi.mes.mapper.crm;

import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@MapperScan
@Component("SalesContractManagementHDao")
public interface SalesContractManagementHDao {

	//	查询头表全部数据1
	@Select("<script> select * from c_sd_sales_contract_h a where 1=1 and isDelete!=1"+
		"<if test=\"contractNo!='' and contractNo!=null and contractNo!='null'\">and  a.contractNo like '%${contractNo}%' </if>"
		+ "<if test=\"companyCode!=''  and companyCode!=null and companyCode!='null'\">and a.companyCode like '%${companyCode}%'</if>"
		+ "<if test=\"customerID!=''  and customerID!=null and customerID!='null'\"> and a.customerID like '%${customerID}%'</if>"
		+ "</script>")
	public List<Map<String,Object>> showContractHInfo(@Param("contractNo") String contractNo, @Param("companyCode") String companyCode, @Param("customerID") String customerID);

	//	查询头表单号数据1
	@Select("select * from c_sd_sales_contract_h  where contractNo=#{contractNo} and isDelete!=1")
	public List<Map<String,Object>> showContractHInfoByCode(@Param("contractNo")String contractNo);
	//	查询行表单号数据1
	@Select("select `contractNo`, `lineNumber`, `materialCode`, `materialName`, `moldAmortization`, `amortizationAmount`, `amortizationUnitPrice`, `productPrice`, `demandQuantity`, `quantityDelivered`, `enclosure` from c_sd_sales_contract_r  where contractNo=#{contractNo} and isDelete!=1")
	public List<Map<String,Object>> showContractRInfoByCode(@Param("contractNo")String contractNo);

	//删除头数据1
	@Delete("update  c_sd_sales_contract_h set isDelete=1 where contractNo=#{contractNo}")
	public Integer delContractHData(@Param("contractNo")String contractNo);

	//删除行数据1
	@Delete("update  c_sd_sales_contract_r set isDelete=1 where contractNo=#{contractNo}")
	public Integer delContractRData(@Param("contractNo")String contractNo);

	//删除指定行数据1
	@Update("update  c_sd_sales_contract_r set isDelete=1 where contractNo=#{contractNo} and lineNumber=#{lineNumber}")
	public Integer delContractRDataByLineNum(@Param("contractNo")String contractNo,@Param("lineNumber")String lineNumber);

	//新增头表数据1  `contractNo`, `customerID`, `founder`, `creationTime`, `status`, `effectiveDate`, `reviser`, `revisionTime`, `companyCode`
	@Insert("insert into c_sd_sales_contract_h(contractNo,customerID,creationTime,founder,status,reviser,companyCode) values(#{contractNo},#{customerID},#{creationTime},#{founder},1,#{reviser},#{companyCode})")
	public Integer addContractHInfo(@Param("contractNo")String contractNo,@Param("customerID")String customerID,@Param("creationTime")String creationTime,@Param("founder")String founder,
									 @Param("reviser")String reviser,@Param("companyCode")String companyCode);

	//	新增行表数据1 `contractNo`, `lineNumber`, `materialCode`, `materialName`, `moldAmortization`, `amortizationAmount`, `amortizationUnitPrice`, `productPrice`, `demandQuantity`, `quantityDelivered`
	@Insert("insert into c_sd_sales_contract_r(contractNo,lineNumber,materialCode,materialName,moldAmortization,amortizationAmount,amortizationUnitPrice,productPrice,demandQuantity,quantityDelivered) values("
		+ "#{contractNo},#{lineNumber},#{materialCode},#{materialName},#{moldAmortization},#{amortizationAmount},#{amortizationUnitPrice},#{productPrice},#{demandQuantity},#{quantityDelivered})")
	public Integer addContractRInfo(@Param("contractNo")String contractNo,@Param("lineNumber")String lineNumber,@Param("materialCode")String materialCode,@Param("materialName")String materialName,@Param("moldAmortization")String moldAmortization,@Param("amortizationAmount")String amortizationAmount,
									 @Param("amortizationUnitPrice")String amortizationUnitPrice,@Param("productPrice")String productPrice,@Param("demandQuantity")String demandQuantity,@Param("quantityDelivered")String quantityDelivered);



	//	查询新增的最大ID1
	@Select("select * from c_sd_sales_contract_h where ID=(SELECT MAX(ID) from c_sd_sales_contract_h where  contractNo=#{contractNo}) ")
	public List<Map<String,Object>> showContractHMaxIdData(@Param("contractNo")String contractNo);

	//	更新头表数据1
	@Update("update c_sd_sales_contract_h set status=#{status},customerID=#{customerID},companyCode=#{companyCode},reviser=#{reviser} ,revisionTime=#{revisionTime} where contractNo=#{contractNo}")
	public Integer updateContractHInfoH(@Param("reviser")String reviser,@Param("revisionTime")String revisionTime,@Param("contractNo")String contractNo,@Param("customerID")String customerID,@Param("companyCode")String companyCode,@Param("status")String status);

	@Update("update c_sd_sales_contract_h set status=#{status},customerID=#{customerID},companyCode=#{companyCode},reviser=#{reviser} ,revisionTime=#{revisionTime},effectiveDate=#{effectiveDate} where contractNo=#{contractNo}")
	public Integer updateContractHInfoH1(@Param("reviser")String reviser,@Param("revisionTime")String revisionTime,@Param("contractNo")String contractNo,@Param("customerID")String customerID,@Param("companyCode")String companyCode,@Param("status")String status,@Param("effectiveDate")String effectiveDate);

	@Update("update c_sd_sales_contract_r set materialCode=#{materialCode},materialName=#{materialName},moldAmortization=#{moldAmortization},amortizationAmount=#{amortizationAmount},amortizationUnitPrice=#{amortizationUnitPrice},productPrice=#{productPrice},demandQuantity=#{demandQuantity},quantityDelivered=#{quantityDelivered} where contractNo=#{contractNo} and lineNumber=#{lineNumber}")
	public Integer updateContractHInfoR(@Param("materialCode")String materialCode,@Param("materialName")String materialName,@Param("moldAmortization")String moldAmortization,@Param("amortizationAmount")String amortizationAmount,@Param("amortizationUnitPrice")String amortizationUnitPrice,@Param("productPrice")String productPrice,@Param("demandQuantity")String demandQuantity,@Param("quantityDelivered")String quantityDelivered, @Param("contractNo")String contractNo,@Param("lineNumber")String lineNumber);


	//	查询指定头数据1
	@Select("select * from c_sd_sales_contract_h where ID=#{id}")
	public List<Map<String,Object>> showContractHDataById(@Param("id")String id);




//	-================行数据处理=====================




	//	查询指定最大行数据
	@Select("select lineNumber from c_sd_sales_contract_r where ID=(select MAX(ID) from c_sd_sales_contract_r where contractNo=#{contractNo})")
	public String showLineNumber(@Param("contractNo")String contractNo);

	//	根据ID查找数据
	@Select("select * from c_sd_sales_contract_r where contractNo=#{contractNo} order by ID desc")
	public List<Map<String,Object>> showContractRById(@Param("contractNo")String contractNo);

	//	查询是否存在绑定的详情数据
	@Select("select count(*) from c_sd_sales_contract_r where contractNo=#{contractNo} and isDelete!=1")
	public Integer countRNum(@Param("contractNo")String contractNo);
}

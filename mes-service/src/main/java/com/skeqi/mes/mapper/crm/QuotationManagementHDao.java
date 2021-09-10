package com.skeqi.mes.mapper.crm;

import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@MapperScan
@Component("QuotationManagementHDao")
public interface QuotationManagementHDao {

//	查询头表全部数据1
	@Select("<script> select *  from  (select a.quotationRecordNo,a.customerID,b.companyName,a.creationTime,a.founder,a.`status`,a.reviser,a.revisionTime,a.companyCode,a.isDelete from c_sd_quotation_record_h a left join c_mes_company_t b on a.companyCode = b.companyCode) c left join c_crm_customer_basic_information d on c.customerID = d.ID where 1=1 and c.isDelete!=1"+
			"<if test=\"quotationRecordNo!='' and quotationRecordNo!=null and quotationRecordNo!='null'\">and  c.quotationRecordNo like '%${quotationRecordNo}%' </if>"
			+ "<if test=\"companyCode!=''  and companyCode!=null and companyCode!='null'\">and c.companyCode like '%${companyCode}%'</if>"
			+ "<if test=\"customerID!=''  and customerID!=null and customerID!='null'\"> and c.customerID like '%${customerID}%'</if>"
			+ "</script>")
	public List<Map<String,Object>> showQuotationHInfo(@Param("quotationRecordNo") String quotationRecordNo,@Param("companyCode") String companyCode,@Param("customerID") String customerID);

//	查询头表单号数据1
//	@Select("select * from c_sd_quotation_record_h  where quotationRecordNo=#{quotationRecordNo} and isDelete!=1")
	@Select("<script>select *  from  (select a.quotationRecordNo,a.customerID,b.companyName,a.creationTime,a.founder,a.`status`,a.reviser,a.revisionTime,a.companyCode,a.isDelete from c_sd_quotation_record_h a left join c_mes_company_t b on a.companyCode = b.companyCode) c left join c_crm_customer_basic_information d on c.customerID = d.ID where 1=1 and c.isDelete!=1"+
		"<if test=\"quotationRecordNo!='' and quotationRecordNo!=null and quotationRecordNo!='null'\">and  c.quotationRecordNo like '%${quotationRecordNo}%' </if> </script>")
	public List<Map<String,Object>> showQuotationHInfoByCode(@Param("quotationRecordNo")String quotationRecordNo);
//	查询行表单号数据1
	@Select("select `quotationRecordNo`, `lineNumber`, `materialCode`, `materialName`, `costPrice`, `offer`, `priceUnit`, DATE_FORMAT(effectiveDate,'%Y-%m-%d %H:%i:%s') as effectiveDate from c_sd_quotation_record_r  where quotationRecordNo=#{quotationRecordNo} and isDelete!=1")
	public List<Map<String,Object>> showQuotationRInfoByCode(@Param("quotationRecordNo")String quotationRecordNo);

	//删除头数据1
	@Delete("update  c_sd_quotation_record_h set isDelete=1 where quotationRecordNo=#{quotationRecordNo}")
	public Integer delQuotationHData(@Param("quotationRecordNo")String quotationRecordNo);

	//删除行数据1
	@Delete("update  c_sd_quotation_record_r set isDelete=1 where quotationRecordNo=#{quotationRecordNo}")
	public Integer delQuotationRData(@Param("quotationRecordNo")String quotationRecordNo);

    //删除指定行数据1
	@Update("update  c_sd_quotation_record_r set isDelete=1 where quotationRecordNo=#{quotationRecordNo} and lineNumber=#{lineNumber}")
	public Integer delQuotationRDataByLineNum(@Param("quotationRecordNo")String quotationRecordNo,@Param("lineNumber")String lineNumber);

	//新增头表数据1
	@Insert("insert into c_sd_quotation_record_h(quotationRecordNo,customerID,creationTime,founder,status,reviser,companyCode) values(#{quotationRecordNo},#{customerID},#{creationTime},#{founder},1,#{reviser},#{companyCode})")
	public Integer addQuotationHInfo(@Param("quotationRecordNo")String quotationRecordNo,@Param("customerID")String customerID,@Param("creationTime")String creationTime,@Param("founder")String founder,
			@Param("reviser")String reviser,@Param("companyCode")String companyCode);

//	新增行表数据1
	@Insert("insert into c_sd_quotation_record_r(quotationRecordNo,lineNumber,materialCode,materialName,costPrice,offer,priceUnit,effectiveDate) values("
			+ "#{quotationRecordNo},#{lineNumber},#{materialCode},#{materialName},#{costPrice},#{offer},#{priceUnit},now())")
	public Integer addQuotationRInfo(@Param("quotationRecordNo")String quotationRecordNo,@Param("lineNumber")String lineNumber,@Param("materialCode")String materialCode,@Param("materialName")String materialName,@Param("costPrice")String costPrice,@Param("offer")String offer,
									 @Param("priceUnit")String priceUnit);

//	查询新增的最大ID1
	@Select("select * from c_sd_quotation_record_h where ID=(SELECT MAX(ID) from c_sd_quotation_record_h where  quotationRecordNo=#{quotationRecordNo}) ")
	public List<Map<String,Object>> showQuotationHMaxIdData(@Param("quotationRecordNo")String quotationRecordNo);

//	更新头表数据1
	@Update("update c_sd_quotation_record_h set status=#{status},customerID=#{customerID},companyCode=#{companyCode},reviser=#{reviser} ,revisionTime=#{revisionTime} where quotationRecordNo=#{quotationRecordNo}")
	public Integer updateQuotationHInfoH(@Param("reviser")String reviser,@Param("revisionTime")String revisionTime,@Param("quotationRecordNo")String quotationRecordNo,@Param("customerID")String customerID,@Param("companyCode")String companyCode,@Param("status")String status);

	@Update("update c_sd_quotation_record_r set materialCode=#{materialCode},materialName=#{materialName},costPrice=#{costPrice},offer=#{offer},priceUnit=#{priceUnit} where quotationRecordNo=#{quotationRecordNo} and lineNumber=#{lineNumber}")
	public Integer updateQuotationHInfoR(@Param("materialCode")String materialCode,@Param("materialName")String materialName,@Param("costPrice")String costPrice,@Param("offer")String offer,@Param("priceUnit")String priceUnit,@Param("quotationRecordNo")String quotationRecordNo,@Param("lineNumber")String lineNumber);


//	查询指定头数据1
	@Select("select * from c_sd_quotation_record_h where ID=#{id}")
	public List<Map<String,Object>> showQuotationHDataById(@Param("id")String id);




//	-================行数据处理=====================




//	查询指定最大行数据
	@Select("select lineNumber from c_sd_quotation_record_r where ID=(select MAX(ID) from c_sd_quotation_record_r where quotationRecordNo=#{quotationRecordNo})")
	public String showLineNumber(@Param("quotationRecordNo")String quotationRecordNo);

//	根据ID查找数据
	@Select("select * from c_sd_quotation_record_r where quotationRecordNo=#{quotationRecordNo} order by ID desc")
	public List<Map<String,Object>> showQuotationRById(@Param("quotationRecordNo")String quotationRecordNo);

//	查询是否存在绑定的详情数据
	@Select("select count(*) from c_sd_quotation_record_r where quotationRecordNo=#{quotationRecordNo} and isDelete!=1")
	public Integer countRNum(@Param("quotationRecordNo")String quotationRecordNo);









}

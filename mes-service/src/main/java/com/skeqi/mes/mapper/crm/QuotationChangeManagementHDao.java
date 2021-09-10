package com.skeqi.mes.mapper.crm;

import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@MapperScan
@Component("QuotationChangeManagementHDao")
public interface QuotationChangeManagementHDao {
    //	查询头表单号
    @Select("<script> select * from c_sd_quotation_change_record_h a where 1=1 and isDelete!=1"+
            "<if test=\"companyCode!='' and companyCode!=null and companyCode!='null'\">and  a.companyCode like '%${companyCode}%' </if>"
            + "<if test=\"applicant!=''  and applicant!=null and applicant!='null'\">and a.applicant like '%${applicant}%'</if>"
            + "</script>")
    public List<Map<String,Object>> showQuotationChangeHInfo(@Param("companyCode") String companyCode,@Param("applicant") String applicant);
//查询头表
    @Select(" select * from c_sd_quotation_change_record_h a where 1=1 and isDelete!=1 and changeRecordNo=#{changeRecordNo}")
    public List<Map<String,Object>> showQuotationChangeHInfoEx(@Param("changeRecordNo")String changeRecordNo);
    //查询行表
    @Select(" select `changeRecordNo`, `lineNumber`, `materialCode`, `materialName`, `originalQuotation`, `newOffer`, `unit`, DATE_FORMAT(effectiveDate,'%Y-%m-%d %H:%i:%s') as effectiveDate, `isDelete` from c_sd_quotation_change_record_r a where 1=1 and isDelete!=1 and changeRecordNo=#{changeRecordNo}")
    public List<Map<String,Object>> showQuotationChangeRInfoEx(@Param("changeRecordNo")String changeRecordNo);

    //	查询行表单号数据1
    @Select("select `changeRecordNo`, `lineNumber`, `materialCode`, `materialName`, `originalQuotation`, `newOffer`, `unit`, DATE_FORMAT(effectiveDate,'%Y-%m-%d %H:%i:%s') as effectiveDate, `isDelete` from c_sd_quotation_change_record_r  where changeRecordNo=#{changeRecordNo} and isDelete!=1")
    public List<Map<String,Object>> showQuotationChangeRInfoByCode(@Param("changeRecordNo")String changeRecordNo);

    //删除指定行数据1
    @Update("update  c_sd_quotation_change_record_r set isDelete=1 where changeRecordNo=#{changeRecordNo} and lineNumber=#{lineNumber}")
    public Integer delQuotationChangeRDataByLineNum(@Param("changeRecordNo")String changeRecordNo,@Param("lineNumber")String lineNumber);

    //新增头表数据1
    @Insert("insert into c_sd_quotation_change_record_h(`changeRecordNo`, `customerID`, `applicant`, `applicationTime`, `status`, `reasonForChange`, `companyCode`) values(#{changeRecordNo},#{customerID},#{applicant},#{applicationTime},1,#{reasonForChange},#{companyCode})")
    public Integer addQuotationChangeHInfo(@Param("changeRecordNo")String changeRecordNo,@Param("customerID")String customerID,@Param("applicant")String applicant,@Param("applicationTime")String applicationTime,
                                     @Param("reasonForChange")String reasonForChange,@Param("companyCode")String companyCode);

    //	更新头表数据1
    @Update("update c_sd_quotation_change_record_h set reasonForChange=#{reasonForChange},status=#{status},customerID=#{customerID},companyCode=#{companyCode},applicant=#{applicant} ,applicationTime=#{applicationTime} where changeRecordNo=#{changeRecordNo}")
    public Integer updateQuotationChangeHInfoH(@Param("applicant")String applicant,@Param("applicationTime")String applicationTime,@Param("changeRecordNo")String changeRecordNo,@Param("customerID")String customerID,@Param("companyCode")String companyCode,@Param("status")String status,@Param("reasonForChange")String reasonForChange);


    //	查询指定最大行数据
    @Select("select lineNumber from c_sd_quotation_change_record_r where ID=(select MAX(ID) from c_sd_quotation_change_record_r where changeRecordNo=#{changeRecordNo})")
    public String showLineNumberChange(@Param("changeRecordNo")String changeRecordNo);

    //	新增行表数据1
    @Insert("insert into c_sd_quotation_change_record_r(`changeRecordNo`, `lineNumber`, `materialCode`, `materialName`, `originalQuotation`, `newOffer`, `unit`, `effectiveDate`) values("
            + "#{changeRecordNo},#{lineNumber},#{materialCode},#{materialName},#{originalQuotation},#{newOffer},#{unit},now())")
    public Integer addQuotationChangeRInfo(@Param("changeRecordNo")String changeRecordNo,@Param("lineNumber")String lineNumber,@Param("materialCode")String materialCode,@Param("materialName")String materialName,@Param("originalQuotation")String originalQuotation,@Param("newOffer")String newOffer,
                                     @Param("unit")String unit);

//    删除指定行信息
    @Update("update c_sd_quotation_change_record_r set materialCode=#{materialCode},materialName=#{materialName},originalQuotation=#{originalQuotation},newOffer=#{newOffer},unit=#{unit} where changeRecordNo=#{changeRecordNo} and lineNumber=#{lineNumber}")
    public Integer updateQuotationChangeHInfoR(@Param("materialCode")String materialCode,@Param("materialName")String materialName,@Param("originalQuotation")String originalQuotation,@Param("newOffer")String newOffer,@Param("unit")String unit,@Param("changeRecordNo")String changeRecordNo,@Param("lineNumber")String lineNumber);

    //删除头数据1
    @Delete("update  c_sd_quotation_change_record_h set isDelete=1 where changeRecordNo=#{changeRecordNo}")
    public Integer delQuotationChangeHData(@Param("changeRecordNo")String changeRecordNo);

    //删除行数据1
    @Delete("update  c_sd_quotation_change_record_r set isDelete=1 where changeRecordNo=#{changeRecordNo}")
    public Integer delQuotationChangeRData(@Param("changeRecordNo")String changeRecordNo);
}

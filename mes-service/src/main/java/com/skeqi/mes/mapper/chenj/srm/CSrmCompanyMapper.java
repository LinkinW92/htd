package com.skeqi.mes.mapper.chenj.srm;
import com.skeqi.mes.pojo.chenj.srm.CSrmCompany;
import com.skeqi.mes.pojo.chenj.srm.kthree.KThreeSupplier;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmCompanyReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnterpriseReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmCompanyRsp;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmSupplierChangeRecordRsp;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmCompanyMapper
 * @Description ${Description}
 */

public interface CSrmCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSrmCompany record);

    int insertOrUpdate(CSrmCompany record);

    int insertOrUpdateSelective(CSrmCompany record);

    int insertSelective(CSrmCompany record);

    CSrmCompany selectByPrimaryKey(CSrmCompany record);

    // 根据公司名称查询
    CSrmCompany selectCompanyName(String companyName);


    // 根据公司编码查询
    CSrmCompany selectCompanyCode(String companyCode);

    int updateByPrimaryKeySelective(CSrmCompany record);

    int updateByPrimaryKey(CSrmCompany record);

    int updateBatch(List<CSrmCompany> list);

    int updateBatchSelective(List<CSrmCompany> list);

    int batchInsert(@Param("list") List<CSrmCompany> list);

    int batchInsertKThree(@Param("list") List<KThreeSupplier> list);


    // 查询公司和公司代码
    List<CSrmCompanyRsp> selectCompanyList(CSrmCompanyReq req);

    CSrmCompany selectFinallyData();


    // 查询供应商代码是否存在
    CSrmCompany selectSupplierCode(String supplierCode);


    // 查询公司信息
    CSrmSupplierChangeRecordRsp selectByPrimaryKeyList(CSrmEnterpriseReq req);


}

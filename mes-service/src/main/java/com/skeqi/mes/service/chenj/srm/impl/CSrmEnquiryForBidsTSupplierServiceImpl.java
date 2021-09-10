package com.skeqi.mes.service.chenj.srm.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.skeqi.mes.mapper.chenj.srm.CSrmEnquiryForBidsTSupplierMapper;
import com.skeqi.mes.pojo.chenj.srm.CSrmEnquiryForBidsTSupplier;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryForBidsTSupplierReq;
import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnquiryInvitationForBidsTHReq;
import com.skeqi.mes.pojo.chenj.srm.rsp.CSrmEnquiryForBidsTSupplierRsp;
import com.skeqi.mes.service.chenj.srm.CSrmEnquiryForBidsTSupplierService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmEnquiryForBidsTSupplierServiceImpl
 * @Description ${Description}
 */

@Service
public class CSrmEnquiryForBidsTSupplierServiceImpl implements CSrmEnquiryForBidsTSupplierService {

    @Resource
    private CSrmEnquiryForBidsTSupplierMapper cSrmEnquiryForBidsTSupplierMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return cSrmEnquiryForBidsTSupplierMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CSrmEnquiryForBidsTSupplier record) {
        return cSrmEnquiryForBidsTSupplierMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(CSrmEnquiryForBidsTSupplier record) {
        return cSrmEnquiryForBidsTSupplierMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CSrmEnquiryForBidsTSupplier record) {
        return cSrmEnquiryForBidsTSupplierMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(CSrmEnquiryForBidsTSupplier record) {
        return cSrmEnquiryForBidsTSupplierMapper.insertSelective(record);
    }

    @Override
    public CSrmEnquiryForBidsTSupplier selectByPrimaryKey(Integer id) {
        return cSrmEnquiryForBidsTSupplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CSrmEnquiryForBidsTSupplier record) {
        return cSrmEnquiryForBidsTSupplierMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CSrmEnquiryForBidsTSupplier record) {
        return cSrmEnquiryForBidsTSupplierMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<CSrmEnquiryForBidsTSupplier> list) {
        return cSrmEnquiryForBidsTSupplierMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<CSrmEnquiryForBidsTSupplier> list) {
        return cSrmEnquiryForBidsTSupplierMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<CSrmEnquiryForBidsTSupplier> list) {
        return cSrmEnquiryForBidsTSupplierMapper.batchInsert(list);
    }


    @Override
    public Rjson findTheTenderHall(CSrmEnquiryInvitationForBidsTHReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmEnquiryForBidsTSupplierMapper.findTheTenderHall(req)));
    }

    @Override
    public Rjson findSupplierNewList(CSrmEnquiryForBidsTSupplierReq req) {
        return Rjson.success(cSrmEnquiryForBidsTSupplierMapper.findSupplierNewList(req));
    }

    @Override
    public Rjson findSupplierList(CSrmEnquiryForBidsTSupplierReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        return Rjson.success(new PageInfo<>(cSrmEnquiryForBidsTSupplierMapper.findSupplierList(req)));
    }

    @Override
    public Rjson findHistorySupplierList(CSrmEnquiryForBidsTSupplierReq req) {
        if (CollectionUtils.isEmpty(req.getMaterialCodeList())) {
            return Rjson.success();
        }
        // 查询询价供应商表数据
        List<CSrmEnquiryForBidsTSupplierRsp> historySupplierList = cSrmEnquiryForBidsTSupplierMapper.findHistorySupplierList(req);
        // 查询采购订单头行数据
        List<CSrmEnquiryForBidsTSupplierRsp> historySupplierListByTime = cSrmEnquiryForBidsTSupplierMapper.findHistorySupplierListByTime(req);
        List<CSrmEnquiryForBidsTSupplierRsp> res = Stream.of(historySupplierList, historySupplierListByTime).flatMap(Collection::stream).collect(Collectors.toList());
        List<CSrmEnquiryForBidsTSupplierRsp> resList = new ArrayList<>();
        // name有值(供应商已淘汰) 则将supplierCode的值清除
        if (res.size() > 0) {
            res.forEach(item -> {
                if (StringUtil.eqNu(item.getName())) {
                    resList.add(item);
                }
            });
        }

        return Rjson.success(resList);
    }
}


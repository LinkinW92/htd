package com.skeqi.mes.service.wf.linesidelibrary.productOffline.impl;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedT;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineDetailedT;
import com.skeqi.mes.service.wf.linesidelibrary.CLslMaterialInventoryTService;
import com.skeqi.mes.service.wf.linesidelibrary.productOffline.LslProductOfflineDetailedDetailTService;
import com.skeqi.mes.service.wf.linesidelibrary.productOffline.LslProductOfflineDetailedTService;
import com.skeqi.mes.util.wf.UniversalUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineT;
import com.skeqi.mes.mapper.wf.linesidelibrary.productOffline.LslProductOfflineTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.productOffline.LslProductOfflineTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class LslProductOfflineTServiceImpl implements LslProductOfflineTService{

    @Resource
    private LslProductOfflineTMapper lslProductOfflineTMapper;

    @Resource
    private LslProductOfflineDetailedTService detailedTService;

    @Resource
    private LslProductOfflineDetailedDetailTService detailedDetailTService;

    @Resource
    private CLslMaterialInventoryTService inventoryTService;

    @Override
    public int insertSelective(LslProductOfflineT record, List<LslProductOfflineDetailedDetailT> detailedDetailTS) throws Exception {
        LslProductOfflineT offlineT = lslProductOfflineTMapper.selectByPrimaryKey(record.getNumber());
        if (!StringUtils.isEmpty(offlineT)){
            throw new Exception("下线单号重复,请稍后重试!!!");
        }

        // 去重
        List<LslProductOfflineDetailedDetailT> distinctClass =  detailedDetailTS.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<>(
                                        Comparator.comparing(o -> o.getMaterialCode() + ";" )
                                )
                        ), ArrayList::new
                )
        );

        // 详情信息
        List<LslProductOfflineDetailedT>  detailedTList = new ArrayList<>();

        for (LslProductOfflineDetailedDetailT dd : distinctClass) {
            //调用工具类生成编号
            String number = UniversalUtil.generateNumber();
            Boolean flag = true;
            while (flag){
                LslProductOfflineDetailedT selectByPrimaryKey = detailedTService.selectByPrimaryKey(number);
                if (!StringUtils.isEmpty(selectByPrimaryKey)){
                    number = UniversalUtil.generateNumber();
                    continue;
                }
                flag=false;
            }
            // 详情实体类
            LslProductOfflineDetailedT detailedT = new LslProductOfflineDetailedT();
            String ddMaterialCode = dd.getMaterialCode();
            for (LslProductOfflineDetailedDetailT dd1 : detailedDetailTS) {
                String dd1MaterialCode = dd1.getMaterialCode();
                if (ddMaterialCode.equals(dd1MaterialCode)) {
                    // 下线数量
                    Integer r = detailedT.getQuantity() == null ? 0 : detailedT.getQuantity();
                    Integer r1 = dd1.getQuantity() == null ? 0 : dd1.getQuantity();
                    detailedT.setQuantity( r + r1);
                }
                //调用工具类生成编号
                String number1 = UniversalUtil.generateNumber();
                Boolean flag1 = true;
                while (flag1){
                    LslProductOfflineDetailedDetailT selectByPrimaryKey = detailedDetailTService.selectByPrimaryKey(number1);
                    if (!StringUtils.isEmpty(selectByPrimaryKey)){
                        number1 = UniversalUtil.generateNumber();
                        continue;
                    }
                    flag1=false;
                }
                dd1.setNumber(number1);
                dd1.setDetailedNumber(number);
                dd1.setCdt(new Date());
                dd1.setUdt(new Date());
            }
            // 详情单号
            detailedT.setNumber(number);
            // 物料编号
            detailedT.setMaterialCode(ddMaterialCode);
            // 下线单号
            detailedT.setOfflineNumber(record.getNumber());
            //
            detailedT.setCdt(new Date());
            detailedT.setUdt(new Date());

            // 详情集合
            detailedTList.add(detailedT);
        }
        // 详情
        Integer detailed = detailedTService.insertByList(detailedTList);
        if (detailed<1){
            throw new Exception("新增下线失败,请稍后重试!!!");
        }
        // 详情明细
        Integer detailedDetail = detailedDetailTService.insertByList(detailedDetailTS);
        if (detailedDetail<1){
            throw new Exception("新增下线失败,请稍后重试!!!");
        }

        // 冻结
        List<Map<String,Object>> inventoryTList = new ArrayList<>();
        detailedDetailTS.forEach(detailT -> {
            Map<String, Object> map = new HashMap<>();
            map.put("materialSn",detailT.getMaterialSn());
            map.put("quantity",(detailT.getQuantity()-detailT.getQuantity()));
            map.put("frozenQuantity",detailT.getQuantity());
            inventoryTList.add(map);
        });
        Integer result = inventoryTService.frozenInventoryByList(inventoryTList);
        if (result<1){
            throw new Exception("退料冻结失败,请稍后重试");
        }

        return lslProductOfflineTMapper.insertSelective(record);
    }

    @Override
    public LslProductOfflineT selectByPrimaryKey(String number) {
        return lslProductOfflineTMapper.selectByPrimaryKey(number);
    }


    @Override
    public List<LslProductOfflineT> selectAll(LslProductOfflineT offlineT) {
        return lslProductOfflineTMapper.selectAll(offlineT);
    }

    @Override
    public Integer deleteByNumber(String number) throws Exception {
        LslProductOfflineT offlineT = lslProductOfflineTMapper.selectByPrimaryKey(number);
        if (StringUtils.isEmpty(offlineT)){
            throw new Exception("下线单据不存在,请验证后重试!!!");
        }else {
            if (1 != offlineT.getStatus()){
                throw new Exception("非新增状态下线单据无法删除,请验证后重试!!!");
            }
        }
        Integer detailedDetailNumber = detailedDetailTService.deleteDetailedDetailByOfflineNumber(number);
        if (detailedDetailNumber<1){
            throw new Exception("删除下线单据失败,请稍后重试!!!");
        }

        Integer detailedNumber = detailedTService.deleteDetailedByOfflineNumber(number);
        if (detailedNumber<1){
            throw new Exception("删除下线单据失败,请稍后重试!!!");
        }

        Integer integer = lslProductOfflineTMapper.deleteByNumber(number);
        if (integer<1){
            throw new Exception("删除下线单据失败,请稍后重试!!!");
        }
        return integer;
    }

}

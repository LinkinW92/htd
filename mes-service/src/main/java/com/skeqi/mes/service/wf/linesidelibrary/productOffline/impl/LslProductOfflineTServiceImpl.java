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
            throw new Exception("??????????????????,???????????????!!!");
        }

        // ??????
        List<LslProductOfflineDetailedDetailT> distinctClass =  detailedDetailTS.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<>(
                                        Comparator.comparing(o -> o.getMaterialCode() + ";" )
                                )
                        ), ArrayList::new
                )
        );

        // ????????????
        List<LslProductOfflineDetailedT>  detailedTList = new ArrayList<>();

        for (LslProductOfflineDetailedDetailT dd : distinctClass) {
            //???????????????????????????
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
            // ???????????????
            LslProductOfflineDetailedT detailedT = new LslProductOfflineDetailedT();
            String ddMaterialCode = dd.getMaterialCode();
            for (LslProductOfflineDetailedDetailT dd1 : detailedDetailTS) {
                String dd1MaterialCode = dd1.getMaterialCode();
                if (ddMaterialCode.equals(dd1MaterialCode)) {
                    // ????????????
                    Integer r = detailedT.getQuantity() == null ? 0 : detailedT.getQuantity();
                    Integer r1 = dd1.getQuantity() == null ? 0 : dd1.getQuantity();
                    detailedT.setQuantity( r + r1);
                }
                //???????????????????????????
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
            // ????????????
            detailedT.setNumber(number);
            // ????????????
            detailedT.setMaterialCode(ddMaterialCode);
            // ????????????
            detailedT.setOfflineNumber(record.getNumber());
            //
            detailedT.setCdt(new Date());
            detailedT.setUdt(new Date());

            // ????????????
            detailedTList.add(detailedT);
        }
        // ??????
        Integer detailed = detailedTService.insertByList(detailedTList);
        if (detailed<1){
            throw new Exception("??????????????????,???????????????!!!");
        }
        // ????????????
        Integer detailedDetail = detailedDetailTService.insertByList(detailedDetailTS);
        if (detailedDetail<1){
            throw new Exception("??????????????????,???????????????!!!");
        }

        // ??????
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
            throw new Exception("??????????????????,???????????????");
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
            throw new Exception("?????????????????????,??????????????????!!!");
        }else {
            if (1 != offlineT.getStatus()){
                throw new Exception("???????????????????????????????????????,??????????????????!!!");
            }
        }
        Integer detailedDetailNumber = detailedDetailTService.deleteDetailedDetailByOfflineNumber(number);
        if (detailedDetailNumber<1){
            throw new Exception("????????????????????????,???????????????!!!");
        }

        Integer detailedNumber = detailedTService.deleteDetailedByOfflineNumber(number);
        if (detailedNumber<1){
            throw new Exception("????????????????????????,???????????????!!!");
        }

        Integer integer = lslProductOfflineTMapper.deleteByNumber(number);
        if (integer<1){
            throw new Exception("????????????????????????,???????????????!!!");
        }
        return integer;
    }

}

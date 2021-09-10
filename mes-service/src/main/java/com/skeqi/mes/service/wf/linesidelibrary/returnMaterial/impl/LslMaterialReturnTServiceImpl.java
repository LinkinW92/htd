package com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.impl;

import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedT;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineT;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedT;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.wf.linesidelibrary.CLslMaterialInventoryTService;
import com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedDetailTService;
import com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.LslMaterialReturnDetailedTService;
import com.skeqi.mes.util.wf.UniversalUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.linesidelibrary.returnMaterial.LslMaterialReturnTMapper;
import com.skeqi.mes.pojo.wf.linesidelibrary.returnMaterial.LslMaterialReturnT;
import com.skeqi.mes.service.wf.linesidelibrary.returnMaterial.LslMaterialReturnTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class LslMaterialReturnTServiceImpl implements LslMaterialReturnTService{

    @Resource
    private LslMaterialReturnTMapper lslMaterialReturnTMapper;

    @Resource
    private LslMaterialReturnDetailedTService detailedTService;

    @Resource
    private LslMaterialReturnDetailedDetailTService detailedDetailTService;

    @Resource
    private CLslMaterialInventoryTService inventoryTService;

    @Override
    public int insertSelective(LslMaterialReturnT record,List<LslMaterialReturnDetailedT> detailedTList) throws Exception {
        // 详情明细集合
        List<LslMaterialReturnDetailedDetailT> detailedDetailTList = new ArrayList<>();

        for (LslMaterialReturnDetailedT detailedT : detailedTList) {
            //调用工具类生成编号
            String number = UniversalUtil.generateNumber();
            Boolean flag = true;
            while (flag){
                LslMaterialReturnDetailedT selectByPrimaryKey = detailedTService.selectByPrimaryKey(number);
                if (!StringUtils.isEmpty(selectByPrimaryKey)){
                    number = UniversalUtil.generateNumber();
                    continue;
                }
                flag=false;
            }
            detailedT.setNumber(number);
            detailedT.setReturnNumber(record.getNumber());
            detailedT.setCdt(new Date());
            detailedT.setUdt(new Date());

            for (LslMaterialReturnDetailedDetailT detailedDetailT : detailedT.getDetailedDetailTList()) {
                //调用工具类生成编号
                String detailNumber = UniversalUtil.generateNumber();
                Boolean detailFlag = true;
                while (detailFlag){
                    LslMaterialReturnDetailedDetailT selectByPrimaryKey = detailedDetailTService.selectByPrimaryKey(number);
                    if (!StringUtils.isEmpty(selectByPrimaryKey)){
                        detailNumber = UniversalUtil.generateNumber();
                        continue;
                    }
                    detailFlag=false;
                }
                detailedDetailT.setNumber(detailNumber);
                detailedDetailT.setDetailedNumber(detailedT.getNumber());
                detailedDetailT.setCdt(new Date());
                detailedDetailT.setUdt(new Date());
                detailedDetailTList.add(detailedDetailT);


            }
        }

        // 冻结
        List<Map<String,Object>> inventoryTList = new ArrayList<>();
        detailedDetailTList.forEach(detailT -> {
            Map<String, Object> map = new HashMap<>();
            map.put("materialSn",detailT.getMaterialSn());
            map.put("quantity",(detailT.getStockQuantity()-detailT.getQuantity()));
            map.put("frozenQuantity",detailT.getQuantity());
            inventoryTList.add(map);
        });
        Integer result = inventoryTService.frozenInventoryByList(inventoryTList);
        if (result<1){
            throw new Exception("退料冻结失败,请稍后重试");
        }


        Integer  detailedInteger = detailedTService.addDetailedByList(detailedTList);
        if (detailedInteger<1){
            throw new Exception("退料失败,请稍后重试");
        }

        Integer  detailedDetailInteger = detailedDetailTService.addDetailedDetailByList(detailedDetailTList);
        if (detailedDetailInteger<1){
            throw new Exception("退料失败,请稍后重试");
        }
        Integer materialReturnInteger = lslMaterialReturnTMapper.insertSelective(record);

        if (materialReturnInteger<1){
            throw new Exception("退料失败,请稍后重试");
        }
        return materialReturnInteger;
    }

    @Override
    public LslMaterialReturnT selectByPrimaryKey(String number) {
        return lslMaterialReturnTMapper.selectByPrimaryKey(number);
    }

    @Override
    public List<LslMaterialReturnT> selectAll(LslMaterialReturnT materialReturnT) {
        return lslMaterialReturnTMapper.selectAll(materialReturnT);
    }

    @Override
    public Integer deleteByNumber(String number) throws Exception {
        LslMaterialReturnT returnT = lslMaterialReturnTMapper.selectByPrimaryKey(number);
        if (StringUtils.isEmpty(returnT)){
            throw new Exception("退料单据不存在,请验证后重试!!!");
        }else {
            if (0 != returnT.getStatus()){
                throw new Exception("非待处理状态下线单据无法删除,请验证后重试!!!");
            }
        }
        Integer detailedDetailNumber = detailedDetailTService.deleteDetailedDetailByReturnNumber(number);
        if (detailedDetailNumber<1){
            throw new Exception("删除退料单据失败,请稍后重试!!!");
        }

        Integer detailedNumber = detailedTService.deleteDetailedByReturnNumber(number);
        if (detailedNumber<1){
            throw new Exception("删除退料单据失败,请稍后重试!!!");
        }

        Integer integer = lslMaterialReturnTMapper.deleteByNumber(number);
        if (integer<1){
            throw new Exception("删除退料单据失败,请稍后重试!!!");
        }
        return integer;
    }
}

package com.skeqi.mes.service.wf.linesidelibrary.checkInventory.impl;

import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedT;
import com.skeqi.mes.pojo.wf.linesidelibrary.productOffline.LslProductOfflineT;
import com.skeqi.mes.service.wf.linesidelibrary.CLslMaterialInventoryTService;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedDetailTService;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryDetailedTService;
import com.skeqi.mes.util.wf.UniversalUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.linesidelibrary.checkInventory.LslCheckInventoryT;
import com.skeqi.mes.mapper.wf.linesidelibrary.checkInventory.LslCheckInventoryTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.checkInventory.LslCheckInventoryTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class LslCheckInventoryTServiceImpl implements LslCheckInventoryTService{

    @Resource
    private LslCheckInventoryTMapper lslCheckInventoryTMapper;

    @Resource
    private CLslMaterialInventoryTService materialInventoryTService;

    @Resource
    private LslCheckInventoryDetailedTService detailedTService;

    @Resource
    private LslCheckInventoryDetailedDetailTService detailedDetailTService;

    @Override
    public int insertSelective(LslCheckInventoryT record,List<LslCheckInventoryDetailedT> detailedTList) throws Exception {
        // 详情明细集合
        List<LslCheckInventoryDetailedDetailT> detailedDetailTList = new ArrayList<>();

        for (LslCheckInventoryDetailedT detailedT : detailedTList) {
            //调用工具类生成编号
            String number = UniversalUtil.generateNumber();
            Boolean flag = true;
            while (flag){
                LslCheckInventoryDetailedT selectByPrimaryKey = detailedTService.selectByPrimaryKey(number);
                if (!StringUtils.isEmpty(selectByPrimaryKey)){
                    number = UniversalUtil.generateNumber();
                    continue;
                }
                flag=false;
            }
            detailedT.setNumber(number);
            detailedT.setCheckNumber(record.getNumber());
            detailedT.setCdt(new Date());
            detailedT.setUdt(new Date());

            for (LslCheckInventoryDetailedDetailT detailedDetailT : detailedT.getDetailedDetailTList()) {
                //调用工具类生成编号
                String detailNumber = UniversalUtil.generateNumber();
                Boolean detailFlag = true;
                while (detailFlag){
                    LslCheckInventoryDetailedDetailT selectByPrimaryKey = detailedDetailTService.selectByPrimaryKey(number);
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
        Integer  detailedInteger = detailedTService.addDetailedByList(detailedTList);
        if (detailedInteger<1){
            throw new Exception("盘点失败,请稍后重试");
        }

        Integer  detailedDetailInteger = detailedDetailTService.addDetailedDetailByList(detailedDetailTList);
        if (detailedDetailInteger<1){
            throw new Exception("盘点失败,请稍后重试");
        }
        Integer checkInteger = lslCheckInventoryTMapper.insertSelective(record);

        if (checkInteger<1){
            throw new Exception("盘点失败,请稍后重试");
        }

        //保存并过账则执行过账方法
        if (record.getStatus()==1){
            this.postingAccountByList(detailedDetailTList);
        }

        return checkInteger;
    }

    /**
     * 过账方法
     * @param detailedDetailTList
     * @throws Exception
     */
    private void postingAccountByList(List<LslCheckInventoryDetailedDetailT> detailedDetailTList) throws Exception {
        // 过账信息集合
        List<Map<String,Object>> inventoryTList = new ArrayList<>();
        detailedDetailTList.forEach(lslCheckInventoryDetailedDetailT -> {
            // 过账信息
            Map<String, Object> map = new HashMap<>();
            map.put("materialSn",lslCheckInventoryDetailedDetailT.getMaterialSn());
            map.put("quantity",lslCheckInventoryDetailedDetailT.getWarehouseQuantity()+lslCheckInventoryDetailedDetailT.getDiscrepancyQuantity());
            inventoryTList.add(map);
        });
        Integer result = materialInventoryTService.postingAccountByList(inventoryTList);
        if (result<1){
            throw new Exception("过账失败,请稍后重试");
        }
    }

    @Override
    public LslCheckInventoryT selectByPrimaryKey(String number) {
        return lslCheckInventoryTMapper.selectByPrimaryKey(number);
    }

    @Override
    public List<LslCheckInventoryT> selectAll(LslCheckInventoryT checkInventoryT) {
        return lslCheckInventoryTMapper.selectAll(checkInventoryT);
    }

    @Override
    public Integer deleteByNumber(String number) throws Exception {
        LslCheckInventoryT checkInventoryT = lslCheckInventoryTMapper.selectByPrimaryKey(number);
        if (StringUtils.isEmpty(checkInventoryT)){
            throw new Exception("盘点单据不存在,请验证后重试!!!");
        }else {
            if (0 != checkInventoryT.getStatus()){
                throw new Exception("非新增状态盘点单据无法删除,请验证后重试!!!");
            }
        }
        Integer detailedDetailNumber = detailedDetailTService.deleteDetailedDetailByCheckNumber(number);
        if (detailedDetailNumber<1){
            throw new Exception("删除盘点单据失败,请稍后重试!!!");
        }

        Integer detailedNumber = detailedTService.deleteDetailedByCheckNumber(number);
        if (detailedNumber<1){
            throw new Exception("删除盘点单据失败,请稍后重试!!!");
        }

        Integer integer = lslCheckInventoryTMapper.deleteByNumber(number);
        if (integer<1){
            throw new Exception("删除盘点单据失败,请稍后重试!!!");
        }
        return integer;
    }

}

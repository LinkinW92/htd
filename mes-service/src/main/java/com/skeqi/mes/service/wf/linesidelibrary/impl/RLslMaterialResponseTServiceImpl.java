package com.skeqi.mes.service.wf.linesidelibrary.impl;

import com.skeqi.mes.mapper.qh.CMesMaterialInstanceDao;
import com.skeqi.mes.mapper.wf.linesidelibrary.RLslMaterialRequestTMapper;
import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import com.skeqi.mes.pojo.wf.linesidelibrary.MaterialResponseParams;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialResponseT;
import com.skeqi.mes.service.qh.CMesMaterialInstanceTService;
import com.skeqi.mes.service.qh.RMesTrackingTService;
import com.skeqi.mes.util.Rjson;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.linesidelibrary.RLslMaterialResponseTMapper;
import com.skeqi.mes.service.wf.linesidelibrary.RLslMaterialResponseTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lenovo
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class RLslMaterialResponseTServiceImpl implements RLslMaterialResponseTService{

    @Resource
    private RLslMaterialResponseTMapper responseTMapper;

    @Resource
    private RLslMaterialRequestTMapper requestTMapper;

    @Resource
    private CMesMaterialInstanceDao cMesMaterialInstanceDao;

    @Override
    public Rjson insertSelective(List<MaterialResponseParams> responseParams,String billNo,String picker) throws Exception {
            //查询
        List<Integer> statusList = new ArrayList<>();
        //待处理
        statusList.add(0);
        //拣货中
        statusList.add(1);
        Integer requestId = requestTMapper.findMaterialRequestByBillNo(billNo,statusList);
            if (requestId!=null&&requestId>0){
                //移除已存在的记录
                responseParams.removeIf(s->s.getRequestDetailId().equals(requestId));
                throw new Exception("出库失败,要料请求任务已处理!");
            }
            if (responseParams.size()>0) {
                //调用处理返回参数方法
                List<RLslMaterialResponseT> rLslMaterialResponseTS = responseParamsDispose(responseParams);

                //再次获取单据号
                //状态 0 待处理、1 拣货中、2 已出库、3 已确认
                RLslMaterialRequestT rLslMaterialRequestT = new RLslMaterialRequestT();
                rLslMaterialRequestT.setBillNo(billNo);
                rLslMaterialRequestT.setStatus(2);
                rLslMaterialRequestT.setPicker(picker);
                Integer resultInteger = requestTMapper.updateByIdsAndStatus(rLslMaterialRequestT);
                if (resultInteger < 1) {
                    throw new Exception("出库失败,修改单据任务失败!");
                }
                //修改物料库存数量，添加出库锁定数量
                //1.1查询物料实例
                List<CMesMaterialInstanceT> cMesMaterialInstanceTList = cMesMaterialInstanceDao.findMaterialInstanceByCodeAndBatchAndSn(rLslMaterialResponseTS);
                //2021/5/31 条码不对提示
                if (cMesMaterialInstanceTList.size()!=rLslMaterialResponseTS.size()){
                    throw new Exception("扫码信息不正确,请确认扫码信息!");
                }
                //返回请求记录信息参数
                List<RLslMaterialResponseT> addResponse = new ArrayList<>();
                for (CMesMaterialInstanceT i : cMesMaterialInstanceTList) {
                    if (StringUtils.isEmpty(i.getMaterialCode())){
                        i.setMaterialCode("");
                    }
                    if (StringUtils.isEmpty(i.getMaterialBatch())){
                        i.setMaterialBatch("");
                    }
                    if (StringUtils.isEmpty(i.getMaterialSn())){
                        i.setMaterialSn("");
                    }
                    for (RLslMaterialResponseT r : rLslMaterialResponseTS) {
                        if (
                                i.getMaterialCode().equals(r.getMaterialNo())&&
                                i.getMaterialBatch().equals(r.getMaterialBatch())&&
                                i.getMaterialSn().equals(r.getMaterialCode())
                        ){
                            r.setMaterialId(i.getId());
                            r.setNumberRemaining(i.getNumberRemaining());
                            addResponse.add(r);
                        }
                    }
                }
                //1.2修改物料实例剩余数量和出库锁定数量
                if (addResponse.size()>0){
                    Integer instanceByRLslMaterialResponseTS = cMesMaterialInstanceDao.updateMaterialInstanceByRLslMaterialResponseTS(addResponse);
                    if (instanceByRLslMaterialResponseTS < 1) {
                        throw new Exception("出库失败,修改库存失败!");
                    }

                    //新增返回请求记录信息
                    int result = responseTMapper.insertSelective(addResponse);
                    if (result < 1) {
                        throw new Exception("出库失败,添加返回记录失败!");
                    }
                }
            }
        return Rjson.success("出库成功!");
    }

    @Override
    public List<RLslMaterialResponseT> findMaterialResponseByRequestDetailId(Integer requestDetailId) {
        return responseTMapper.findMaterialResponseByRequestDetailId(requestDetailId);
    }

    /**
     * 处理返回参数方法
     * @param materialResponseParams
     * @return
     */
    private List<RLslMaterialResponseT> responseParamsDispose (List<MaterialResponseParams> materialResponseParams){
        //返回记录集合
        List<RLslMaterialResponseT> responseTS = new ArrayList<>();
        //临时存储信息
        for (MaterialResponseParams responseParams : materialResponseParams) {
            switch (responseParams.getTracesType()){
                case 0:
                    for (RLslMaterialResponseT rLslMaterialResponseT : responseParams.getDetails()) {
                        for (String materialCode : rLslMaterialResponseT.getCodeList()) {
                            RLslMaterialResponseT rLslMaterialResponseT1 = new RLslMaterialResponseT();
                            //获取请求明细id
                            rLslMaterialResponseT1.setRequestDetailId(responseParams.getRequestDetailId());
                            //获取物料表的物料编号
                            rLslMaterialResponseT1.setMaterialNo(responseParams.getMaterialNo());
                            //获取批次信息
                            rLslMaterialResponseT1.setMaterialBatch(rLslMaterialResponseT.getMaterialBatch());
                            //获取物料单个条码
                            rLslMaterialResponseT1.setMaterialCode(materialCode);
                            //获取单个物料数量
                            rLslMaterialResponseT1.setQuantity(1);
                            //添加一条记录
                            responseTS.add(rLslMaterialResponseT1);
                        }
                    }
                    break;
                case 1:
                    for (RLslMaterialResponseT rLslMaterialResponseT : responseParams.getDetails()) {
                        RLslMaterialResponseT rLslMaterialResponseT1 = new RLslMaterialResponseT();
                        //获取请求明细id
                        rLslMaterialResponseT1.setRequestDetailId(responseParams.getRequestDetailId());
                        //获取物料表的物料编号
                        rLslMaterialResponseT1.setMaterialNo(responseParams.getMaterialNo());
                        //获取批次信息
                        rLslMaterialResponseT1.setMaterialBatch(rLslMaterialResponseT.getMaterialBatch());
                        //获取物料单个条码
                        rLslMaterialResponseT1.setMaterialCode("");
                        //获取物料数量
                        rLslMaterialResponseT1.setQuantity(rLslMaterialResponseT.getQuantity());
                        //添加一条记录
                        responseTS.add(rLslMaterialResponseT1);
                    }
                    break;
                case 2:
                    for (RLslMaterialResponseT rLslMaterialResponseT : responseParams.getDetails()) {
                        RLslMaterialResponseT rLslMaterialResponseT1 = new RLslMaterialResponseT();
                        //获取请求明细id
                        rLslMaterialResponseT1.setRequestDetailId(responseParams.getRequestDetailId());
                        //获取物料表的物料编号
                        rLslMaterialResponseT1.setMaterialNo(responseParams.getMaterialNo());
                        //获取批次信息
                        rLslMaterialResponseT1.setMaterialBatch("");
                        //获取物料单个条码
                        rLslMaterialResponseT1.setMaterialCode(rLslMaterialResponseT.getMaterialCode());
                        //获取物料数量
                        rLslMaterialResponseT1.setQuantity(rLslMaterialResponseT.getQuantity());
                        //添加一条记录
                        responseTS.add(rLslMaterialResponseT1);
                    }
                    break;
                default:
                    break;
            }
        }
        return responseTS;
    }



}

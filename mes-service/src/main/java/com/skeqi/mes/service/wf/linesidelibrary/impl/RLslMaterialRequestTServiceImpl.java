package com.skeqi.mes.service.wf.linesidelibrary.impl;

import com.skeqi.mes.mapper.wf.linesidelibrary.CLslMaterialRequestDetailTMapper;
import com.skeqi.mes.mapper.zch.*;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.wf.linesidelibrary.CLslMaterialRequestDetailT;
import com.skeqi.mes.pojo.wf.linesidelibrary.CLslRockConfigT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.wf.linesidelibrary.RLslMaterialResponseTService;
import com.skeqi.mes.util.Rjson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.skeqi.mes.mapper.wf.linesidelibrary.RLslMaterialRequestTMapper;
import com.skeqi.mes.pojo.wf.linesidelibrary.RLslMaterialRequestT;
import com.skeqi.mes.service.wf.linesidelibrary.RLslMaterialRequestTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lenovo
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RLslMaterialRequestTServiceImpl implements RLslMaterialRequestTService{

    @Resource
    private RLslMaterialRequestTMapper rLslMaterialRequestTMapper;

    @Resource
    private CMesMaterialService cMesMaterialService;

    @Resource
    private CLslMaterialRequestDetailTMapper requestDetailTMapper;

    @Autowired
    LineSideLibraryApiDao lineSideLibraryApiDao;

    @Override
    public List<RLslMaterialRequestT> findMaterialRequest(List<CLslRockConfigT> resultList) {
        return rLslMaterialRequestTMapper.findMaterialRequest(resultList);
    }

    @Override
    public List<RLslMaterialRequestT> addMaterialRequest(List<CLslRockConfigT> resultList) throws Exception {
        //1.??????
        List<CLslRockConfigT> distinctClass =  resultList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<>(
                                        Comparator.comparing(o -> o.getProductId() + ";" + o.getLineId()+";"+o.getStationId())
                                )
                        ), ArrayList::new
                )
        );
        //2.?????????????????????
        List<RLslMaterialRequestT> rLslMaterialRequestTS = new ArrayList<>();
        //?????????+4????????????
        //??????????????????
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String newDate = sdf.format(new Date());
        //?????????
        Integer serialNumber = 0;
        //???????????????????????????
        RLslMaterialRequestT sNum = rLslMaterialRequestTMapper.selectMaterialRequest();
        if (!StringUtils.isEmpty(sNum)){
            //????????????????????????
            String cdt = sdf.format(sNum.getCdt());
            if (cdt.equals(newDate)){
                serialNumber = Integer.valueOf(sNum.getSerialNumber());
            }
        }
        serialNumber++;
        for (CLslRockConfigT lslRockConfigT : distinctClass) {
            //????????????
            RLslMaterialRequestT rLslMaterialRequestT = new RLslMaterialRequestT();
            //??????#??????????????????
            String format = String.format("%04d", serialNumber);
            rLslMaterialRequestT.setSerialNumber(format);
            //???????????????
            //???????????????
            rLslMaterialRequestT.setBillNo(newDate+format);
            //???????????????
            serialNumber++;
            rLslMaterialRequestT.setProductId(lslRockConfigT.getProductId());
            rLslMaterialRequestT.setLineId(lslRockConfigT.getLineId());
            rLslMaterialRequestT.setStationId(lslRockConfigT.getStationId());
            rLslMaterialRequestT.setStatus(0);
            rLslMaterialRequestT.setCreator("system");

            rLslMaterialRequestTS.add(rLslMaterialRequestT);
        }

        //3.???????????????????????????????????????????????????
        Integer integer = rLslMaterialRequestTMapper.addMaterialRequest(rLslMaterialRequestTS);
        if (integer<1){
            throw new Exception("????????????????????????!");
        }

        //4.????????????????????????????????????
        List<String> materialNos = new ArrayList<>();
        resultList.forEach(cLslRockConfigT ->materialNos.add(cLslRockConfigT.getMaterialNo()));
            //??????
        List<String> myList = materialNos.stream().distinct().collect(Collectors.toList());
        //5.???????????????????????????????????????
        List<CMesMaterialT> cMesMaterialTList = cMesMaterialService.findMaterialByMaterialCode(myList);
            //?????????????????????????????????
        for (CMesMaterialT cMesMaterialT : cMesMaterialTList) {
            resultList.forEach(cLslRockConfigT -> {
                if (cLslRockConfigT.getMaterialNo().equals(cMesMaterialT.getMaterialNo())){
                    cLslRockConfigT.setTracesType(cMesMaterialT.getTracesType());
                }
            });
        }
        //5.??????????????????id?????????????????????
        List<CLslMaterialRequestDetailT> requestDetailTS = new ArrayList<>();
        for (RLslMaterialRequestT rLslMaterialRequestT1 : rLslMaterialRequestTS) {
            for (CLslRockConfigT cLslRockConfigT : resultList) {
                if (
                        rLslMaterialRequestT1.getProductId().equals(cLslRockConfigT.getProductId())&&
                                rLslMaterialRequestT1.getLineId().equals(cLslRockConfigT.getLineId())&&
                                rLslMaterialRequestT1.getStationId().equals(cLslRockConfigT.getStationId())
                ){
                    //??????????????????
                    CLslMaterialRequestDetailT requestDetailT = new CLslMaterialRequestDetailT();
                    requestDetailT.setCdt(new Date());
                    requestDetailT.setUdt(new Date());
                    requestDetailT.setBillNo(rLslMaterialRequestT1.getBillNo());
                    requestDetailT.setRockId(cLslRockConfigT.getRockId());
                    requestDetailT.setMaterialNo(cLslRockConfigT.getMaterialNo());
                    requestDetailT.setMaterialName(cLslRockConfigT.getMaterialName());
                    requestDetailT.setRequiredQuantity(cLslRockConfigT.getRequiredQuantity());
                    requestDetailT.setTracesType(cLslRockConfigT.getTracesType()==null||cLslRockConfigT.getTracesType()==""?"0":cLslRockConfigT.getTracesType());
                    //??????????????????
                    requestDetailTS.add(requestDetailT);
                }
            }
        }
        //6.???????????????????????????
        Integer insertMultiple = requestDetailTMapper.insertMultiple(requestDetailTS);
        if (insertMultiple<1){
            throw new Exception("????????????????????????????????????!");
        }
        return rLslMaterialRequestTS;
    }

    @Override
    public List<RLslMaterialRequestT> findMaterialRequestByStatus(List<Integer> statusList) {
        return rLslMaterialRequestTMapper.findMaterialRequestByStatus(statusList);
    }

    @Override
    public Integer materialRequestConfirmReceipt(Map<String, Object> map) throws Exception {
        Map<String, Object> requestMap = lineSideLibraryApiDao.getRequestByBillNo(map);
        if(requestMap == null) {
            throw  new Exception("?????????????????????");
        }
        if(!"2".equals(requestMap.get("status").toString())) {
            throw  new Exception("??????????????????????????????");
        }
        //???????????????????????????
        Integer integer = lineSideLibraryApiDao.insertInventory(map);
        if (integer<1){
            throw  new Exception("??????????????????");
        }

        List<Map<String, Object>> responseList = lineSideLibraryApiDao.findMaterialResponseListByBillNo(map);

        for (Map<String, Object> responseMap : responseList) {
            lineSideLibraryApiDao.reducePickingLockNumber(responseMap);
        }

        //????????????????????????????????????
        map.put("status", 3);
        Integer integer1 = lineSideLibraryApiDao.updateRequestStatus(map);
        if (integer1<1){
            throw  new Exception("??????????????????");
        }
        return integer1;
    }

    @Override
    public List<RLslMaterialRequestT> findMaterialRequestByBillNoList(List<RLslMaterialRequestT> result) {
        return rLslMaterialRequestTMapper.findMaterialRequestByBillNoList(result);
    }

    @Override
    public Integer updateMaterialRequestCancelTask(RLslMaterialRequestT r) throws Exception {
        List<Integer> statusList = new ArrayList<>();
        //2 ????????????
        statusList.add(2);
        //3 ?????????
        statusList.add(3);
        Integer requestMap = rLslMaterialRequestTMapper.findMaterialRequestByBillNo(r.getBillNo(),statusList);
        if(StringUtils.isEmpty(requestMap)&&requestMap <1) {
            throw  new Exception("?????????????????????");
        }
        //????????????????????????????????????
        r.setStatus(4);
        Integer integer1 = rLslMaterialRequestTMapper.updateMaterialRequestCancelTask(r);
        if (integer1<1){
            throw  new Exception("??????????????????");
        }
        return integer1;
    }

    @Override
    public List<RLslMaterialRequestT> findMaterialRequestAll(RLslMaterialRequestT requestT) {
        return rLslMaterialRequestTMapper.findMaterialRequestAll(requestT);
    }

    @Override
    public Integer rejectMaterialRequest(RLslMaterialRequestT r) throws Exception {
        List<Integer> statusList = new ArrayList<>();
        //?????? 0 ????????????1 ????????????2 ????????????3 ????????????4 ????????????5?????????
        statusList.add(0);
        statusList.add(1);
        statusList.add(3);
        statusList.add(4);
        statusList.add(5);
        //????????????????????????????????????
        Integer requestMap = rLslMaterialRequestTMapper.findMaterialRequestByBillNo(r.getBillNo(),statusList);
        if(StringUtils.isEmpty(requestMap)&&requestMap <1) {
            throw  new Exception("?????????????????????");
        }
        //????????????????????????????????????
        r.setStatus(5);
        Integer integer1 = rLslMaterialRequestTMapper.rejectMaterialRequest(r);
        if (integer1<1){
            throw  new Exception("??????????????????");
        }
        return integer1;
    }
}

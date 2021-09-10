package com.skeqi.mes.service.wf.productionQuality.dataCollection.record.impl;

import com.skeqi.mes.mapper.wf.productionQuality.dataCollection.record.MesDataCollectionRecordDetailTMapper;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.params.MesDataCollectionParamsT;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record.MesDataCollectionRecordDetailT;
import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigT;
import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.record.MesNcCodeRecordT;
import com.skeqi.mes.service.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigTService;
import com.skeqi.mes.service.wf.productionQuality.ncCodeManager.record.MesNcCodeRecordTService;
import com.skeqi.mes.util.wf.UniversalUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.record.MesDataCollectionRecordT;
import com.skeqi.mes.mapper.wf.productionQuality.dataCollection.record.MesDataCollectionRecordTMapper;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.record.MesDataCollectionRecordTService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class MesDataCollectionRecordTServiceImpl implements MesDataCollectionRecordTService{

    @Resource
    private MesDataCollectionRecordTMapper mesDataCollectionRecordTMapper;

    @Resource
    private MesDataCollectionRecordDetailTMapper detailTMapper;

    @Resource
    private MesNcCodeRecordTService ncCodeRecordTService;

    @Resource
    private MesNcCodeConfigTService ncCodeConfigTService;

    @Override
    public int insertSelective(MesDataCollectionRecordT record,List<MesDataCollectionParamsT> paramsDataList1) throws Exception {
        List<MesNcCodeConfigT> mesNcCodeConfigTS = ncCodeConfigTService.selectAll(new MesNcCodeConfigT());

        List<MesDataCollectionRecordDetailT> detailList = new ArrayList<>();

        List<MesNcCodeRecordT> ncCodeRecordTList = new ArrayList<>();

        for (MesDataCollectionParamsT mesDataCollectionParamsT : paramsDataList1) {
            MesDataCollectionRecordDetailT detailT = new MesDataCollectionRecordDetailT();
            detailT.setLowerLimit(mesDataCollectionParamsT.getLowerLimit());
            detailT.setUpperLimit(mesDataCollectionParamsT.getUpperLimit());
            detailT.setRecordNumber(record.getNumber());
            detailT.setParamsNumber(mesDataCollectionParamsT.getNumber());
            detailT.setParamsName(mesDataCollectionParamsT.getName());
            detailT.setValue(mesDataCollectionParamsT.getValue());
            detailT.setCdt(new Date());
            detailT.setUdt(new Date());
            detailList.add(detailT);

            //超限记录nc
            Boolean upper = Integer.parseInt(mesDataCollectionParamsT.getValue()) > Integer.parseInt(mesDataCollectionParamsT.getUpperLimit());
            Boolean lower = Integer.parseInt(mesDataCollectionParamsT.getValue()) < Integer.parseInt(mesDataCollectionParamsT.getLowerLimit());
            if (upper||lower){
                MesNcCodeRecordT ncCodeRecordT = new MesNcCodeRecordT();
                ncCodeRecordT.setNumber(UniversalUtil.generateNumber());
                ncCodeRecordT.setSn(record.getSn());
                ncCodeRecordT.setStaff(record.getStaff());
                ncCodeRecordT.setStatus(0);
                ncCodeRecordT.setCdt(new Date());
                ncCodeRecordT.setUdt(new Date());
                for (MesNcCodeConfigT mesNcCodeConfigT : mesNcCodeConfigTS) {
                    if (upper) {
                        if ("upperLimit".equals(mesNcCodeConfigT.getCode())) {
                            ncCodeRecordT.setNcCode("upperLimit");
                        }
                    }
                    if (lower) {
                        if ("lowerLimit".equals(mesNcCodeConfigT.getCode())) {
                            ncCodeRecordT.setNcCode("lowerLimit");
                        }
                    }
                }
                if (!StringUtils.isEmpty(ncCodeRecordT.getNcCode())){
                    throw new Exception("数据收集失败! 请添加超限不合格编码配置");
                }
                ncCodeRecordTList.add(ncCodeRecordT);
            }
        }

        //添加明细
        if (detailList.size()>0){
            Integer integer = detailTMapper.addRecordDetailByList(detailList);
            if (integer<1){
                throw new Exception("数据收集失败! 请稍后重试");
            }
        }
        // 添加nc记录
        if (ncCodeRecordTList.size()>0){
            Integer integer = ncCodeRecordTService.addNcCodeRecordByList(ncCodeRecordTList);
            if (integer<1){
                throw new Exception("数据收集失败! 请稍后重试");
            }
        }

        Integer integer = mesDataCollectionRecordTMapper.insertSelective(record);
        if (integer<1){
            throw new Exception("数据收集失败! 请稍后重试");
        }

        return integer;
    }

    @Override
    public List<MesDataCollectionRecordT> selectAll(MesDataCollectionRecordT recordT) {
        return mesDataCollectionRecordTMapper.selectAll(recordT);
    }

    @Override
    public MesDataCollectionRecordT selectByPrimaryKey(String number) {
        return mesDataCollectionRecordTMapper.selectByPrimaryKey(number);
    }

	@Override
	public List<MesDataCollectionRecordT> findRecordList(Map<String, Object> map) {
		return mesDataCollectionRecordTMapper.findRecordList(map);
	}



}

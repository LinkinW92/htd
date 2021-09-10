package com.skeqi.mes.service.wf.impl;

import com.skeqi.mes.mapper.wf.CMesAreaTMapper;
import com.skeqi.mes.mapper.wf.CMesFactoryTMapper;
import com.skeqi.mes.mapper.wf.CMesPlantTMapper;
import com.skeqi.mes.pojo.qh.CMesAreaT;
import com.skeqi.mes.pojo.qh.CMesFactoryT;
import com.skeqi.mes.pojo.qh.CMesPlantT;
import com.skeqi.mes.util.Rjson;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.skeqi.mes.mapper.wf.CMesCompanyTMapper;
import com.skeqi.mes.pojo.qh.CMesCompanyT;
import com.skeqi.mes.service.wf.CMesCompanyTService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CMesCompanyTServiceImpl implements CMesCompanyTService{

    @Resource
    private CMesCompanyTMapper cMesCompanyTMapper;

    @Resource
    private CMesFactoryTMapper cMesFactoryTMapper;

    @Resource
    private CMesAreaTMapper cMesAreaTMapper;

    @Resource
    private CMesPlantTMapper cMesPlantTMapper;

    @Override
    public List<CMesCompanyT> findCompanyAll(String companyCode,String companyName) {
        return cMesCompanyTMapper.findCompanyAll(companyCode,companyName);
    }

    @Override
    public Rjson addCompany(CMesCompanyT cMesCompanyT) throws Exception {
        //查询编号是否存在
        List<CMesCompanyT> companyTList = cMesCompanyTMapper.selectCompanyByCode(cMesCompanyT.getCompanyCode());
        if (companyTList.size()>0){
            throw new Exception("公司编号已存在");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        cMesCompanyT.setDateTime(date);
        cMesCompanyT.setAlterDt(date);
        Integer integer = cMesCompanyTMapper.addCompany(cMesCompanyT);
        if (integer<1){
            throw new Exception("添加公司失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson editCompany(CMesCompanyT cMesCompanyT) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        cMesCompanyT.setAlterDt(date);
        Integer integer = cMesCompanyTMapper.editCompany(cMesCompanyT);
        if (integer<1){
            throw new Exception("编辑公司失败");
        }
        return Rjson.success();
    }

    @Override
    public Rjson delCompanyByIdAndCode(CMesCompanyT companyT) throws Exception {
        Integer delCompanyByIdAndCode =cMesCompanyTMapper.delCompanyByIdAndCode(companyT);
        if(delCompanyByIdAndCode<1){
            throw new Exception("删除公司失败");
        }

        //1.获取当前公司旗下所有工厂
        List<CMesFactoryT> factoryTList =cMesFactoryTMapper.selectFactoryByCompanyCode(companyT.getCompanyCode());
        //2.工厂不为空
        if (factoryTList.size()>0){
            //3.删除工厂
            Integer delFactory = cMesFactoryTMapper.delFactoryByCompanyCode(companyT.getCompanyCode());
            if (delFactory < 1) {
                throw new Exception("删除公司失败");
            }
        }

        //1.获取当前公司旗下所有区域
        List<CMesAreaT> areaTList = cMesAreaTMapper.selectAreaByCompanyCode(companyT.getCompanyCode());
        //2.区域不为空
        if(areaTList.size()>0){
            //3.删除区域
            Integer delArea = cMesAreaTMapper.delAreaByCompanyCode(companyT.getCompanyCode());
            if (delArea<1){
                throw new Exception("删除公司失败");
            }
        }

        //1.获取当前公司旗下所有车间
        List<CMesPlantT> cMesPlantTList = cMesPlantTMapper.selectPlantByCompanyCode(companyT.getCompanyCode());
        //2.车间不为空
        if (cMesPlantTList.size()>0){
            //删除车间
            Integer plantDel = cMesPlantTMapper.delPlantByCompanyCode(companyT.getCompanyCode());
            if (plantDel<1){
                throw new Exception("删除公司失败");
            }
        }

        return Rjson.success();
    }
}

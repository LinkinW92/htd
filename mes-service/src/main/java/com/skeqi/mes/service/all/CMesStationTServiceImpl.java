package com.skeqi.mes.service.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.util.StringUtil;
import org.neo4j.cypher.internal.compiler.v2_2.perty.recipe.Pretty.nest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesStationTDAO;
import com.skeqi.mes.pojo.CMesStationT;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class CMesStationTServiceImpl implements CMesStationTService {

    @Autowired
    private CMesStationTDAO dao;

    @Override
    public List<CMesStationT> findAllStation(CMesStationT c) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findAllStation(c);
    }

    @Override
    public List<CMesStationT> findStationByListId(Integer listId) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findStationByListId(listId);
    }

    @Override
    public CMesStationT findStationByid(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("参数异常", 200);
        }
        return dao.findStationByid(id);
    }

    @Override
    public Integer addStation(CMesStationT c) throws ServicesException {
        if (c.getStationName() == "" || c.getStationName() == null) {
            throw new ParameterNullException("工位名称不能为空", 200);
        } else if (c.getStationIndex() == 0) {
            throw new ParameterNullException("工位下标不能为0", 200);
        } else if (c.getStationTime() == null || "".equals(c.getStationTime().toString())) {
            throw new ParameterNullException("工位节拍不能为空", 200);
        }

        CMesStationT station = new CMesStationT();
        station.setStationName(c.getStationName());
        station.setLineId(c.getLineId());

        // 查询名称是否重复
        List<CMesStationT> findAllStation = dao.findAllStation(station);

        if (findAllStation.size() > 0) {
            throw new NameRepeatException("工位名称重复", 100);
        }
        // 获取产线名称
        String getLineName = dao.findLineName(station.getLineId());
        // 查询工位下标是否存在
        int findIndex = dao.findIndex(c.getStationIndex(), getLineName);
        if (findIndex > 0) {
            throw new NameRepeatException("该工位下标已经存在", 101);
        }

        /**
         * else if(mapper.findIndex(c.getStationIndex(),map.get("LineName")) >0 ){
         * 			throw new NameRepeatException("该工位下标已经存在", 101);
         *                }
         */
        // if(c.getStationEndornot().equals("1")){ //如果是末站
        // List<CMesStationT> findEndornot = mapper.findEndornot(c.getLineId());
        // //查询该产线是否存在末站
        // if(findEndornot.size()>0){
        // throw new NameRepeatException("该产线已存在末站",102);
        // }
        // }
        return dao.addStation(c);
    }

    @Override
    public Integer updateStation(CMesStationT c) throws ServicesException {
        // TODO Auto-generated method stub
        if (c.getStationName() == null || c.getStationName().equals("")) {
            throw new ParameterNullException("工位名称不能为空", 200);
        } else if (c.getStationIndex() == 0) {
            throw new ParameterNullException("工位下标不能为0", 200);
        } else if (c.getStationTime() == null) {
            throw new ParameterNullException("工位节拍不能为空", 200);
        }

        if (!c.getStationName().equals(c.getStationName1())) {
            List<CMesStationT> findAllStation = dao.findAllStation(c);
            if (findAllStation.size() > 0) {
                throw new NameRepeatException("工位名称重复", 100);
            }
        }
        // 获取产线名称
        String getLineName = dao.findLineName(c.getLineId());
        if (c.getStationIndex() != c.getStationIndex1()) {
            int findIndex = dao.findIndex(c.getStationIndex(), getLineName);
            List<CMesStationT> indexs = dao.findIndexs(c.getStationIndex(), c.getLineId());
            if (indexs.size() == 0) {
                // 如果存在该下标
                if (findIndex > 0) {
                    throw new NameRepeatException("该工位下标已经存在", 101);
                }
            }

        }



	/*	CMesStationT findStationByid = mapper.findStationByid(c.getId()); // 获取修改前的name
		if (!findStationByid.getStationName().equals(c.getStationName())) {
			CMesStationT station = new CMesStationT();
			station.setStationName(c.getStationName());
			station.setLineId(c.getLineId());
			List<CMesStationT> findAllStation = mapper.findAllStation(station);
			if (findAllStation.size() > 0) {
				throw new NameRepeatException("工位名称重复", 100);
			}
		}*/

        // if (c.getStationEndornot().equals("1")) {
        // List<CMesStationT> findEndornot = mapper.findEndornot(c.getLineId()); //
        // 查询该产线是否存在末站
        // if (findEndornot.size() > 0) { // 如果有末站
        // if (findEndornot.get(0).getId() != c.getId()) {
        // throw new NameRepeatException("该产线已存在末站", 102);
        // }
        // }
        // }

        // List<CMesStationT> findIndex = mapper.findIndex(c.getStationIndex(),
        // c.getLineId());
        // if(findIndex.size()>0){ //如果存在该下标
        // if(findIndex.get(0).getId()!=c.getId()){
        // throw new NameRepeatException("该工位下标已经存在",101);
        // }
        // }
        return dao.updateStation(c);
    }

    @Override
    public Integer delStation(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.delStation(id);
    }

    @Override
    public List<CMesStationT> findStationAll() {
        // TODO Auto-generated method stub
        return dao.findStationAll();
    }

    @Override
    public Integer updateStationold(CMesStationT c) throws ServicesException {
        // TODO Auto-generated method stub
        if (c.getStationName() == "" || c.getStationName() == null) {
            throw new ParameterNullException("工位名称不能为空", 200);
        } else if (c.getStationIndex() == 0) {
            throw new ParameterNullException("工位下标不能为0", 200);
        } else if (c.getStationTime() == null || "".equals(c.getStationTime().toString())) {
            throw new ParameterNullException("工位节拍不能为空", 200);
        }

        CMesStationT findStationByid = dao.findStationByid(c.getId());  //获取修改前的name
        if (!findStationByid.getStationName().equals(c.getStationName())) {
            CMesStationT station = new CMesStationT();
            station.setStationName(c.getStationName());
            List<CMesStationT> findAllStation = dao.findAllStation(station);
            if (findAllStation.size() > 0) {
                throw new NameRepeatException("工位名称重复", 100);
            }
        }

        if (c.getStationEndornot().equals("1")) {
            List<CMesStationT> findEndornot = dao.findEndornot(c.getLineId());   //查询该产线是否存在末站
            if (findEndornot.size() > 0) {  //如果有末站
                if (findEndornot.get(0).getId() != c.getId()) {
                    throw new NameRepeatException("该产线已存在末站", 102);
                }
            }
        }

        List<CMesStationT> findIndex = dao.findIndexs(c.getStationIndex(), c.getLineId());
        if (findIndex.size() > 0) {     //如果存在该下标
            if (findIndex.get(0).getId() != c.getId()) {
                throw new NameRepeatException("该工位下标已经存在", 101);
            }
        }
        return dao.updateStations(c);
    }

    @Override
    public List<CMesStationT> findStationNameAndId(CMesStationT c) {
        return dao.findStationNameAndId(c);
    }
}

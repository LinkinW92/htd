package com.skeqi.mes.service.all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.pojo.qh.CMesPlantT;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.util.wf.CustomAttributesConstant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesLineTDAO;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesStationLine;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesScheduling;
import com.skeqi.mes.pojo.project.CMesSchedulingL;
import com.skeqi.mes.pojo.qh.CMesEndStocks;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class CMesLineTServiceImpl implements CMesLineTService {

    @Autowired
    private CMesLineTDAO dao;

    @Autowired
    private CustomPropertyDao cMesCustomPropertyMapper;

    @Autowired
    CMesCustomPropertyService customPropertyService;

    /**
     * 查询列表
     */
    @Override
    public List<CMesLineT> findAllLine(CMesLineT line) throws ServicesException {
        return dao.findAllLine(line);
    }

    /**
     * 根据id查询
     */
    @Override
    public CMesLineT findLineByid(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("参数异常", 200);
        }
        return dao.findLineByid(id);
    }

    /**
     * 操作自定义属性值（内容）
     *
     * @param line
     * @throws Exception
     */
    private void handleCustomValue(CMesLineT line) throws Exception {
        List<Map<String, Object>> editList = new ArrayList<>();
        List<Map<String, Object>> addList = new ArrayList<>();
        List<Map<String, Object>> list = (List<Map<String, Object>>) line.getCustom();
        if (list != null && list.size() > 0) {
            for (Map<String, Object> object : list) {
                if (!StringUtils.isEmpty(object)) {
                    System.out.println(object);
                    object.put("objectCode", line.getCode());
                    if (object.get("bindScopeKey").equals("0")) {
                        object.put("propertyId", object.get("id"));
                        if (object.get("value") == null || "".equals(object.get("value"))) {
                            object.put("value", object.get("defaults"));
                        }
                    }
                }
                CMesCustomProperty cMesCustomProperty = new CMesCustomProperty();
                cMesCustomProperty.setObjectType(object.get("objectType").toString());
                cMesCustomProperty.setBindScopeValue(object.get("objectCode").toString());
                List<Integer> customPropertyValueAll = cMesCustomPropertyMapper.selectCustomPropertyValueAll(cMesCustomProperty);
                if (customPropertyValueAll.size() > 0) {
                    //编辑
                    editList.add(object);
                } else {
                    //新增
                    addList.add(object);
                }
            }

            //新增操作
            if (addList.size() > 0) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put("list", addList);
                Integer editCustomPropertyValue = cMesCustomPropertyMapper.addCustomPropertyValue(map1);
                if (editCustomPropertyValue < 1) {
                    throw new Exception("添加自定义属性内容失败");
                }
            }

            //编辑操作
            if (editList.size() > 0) {
                for (Object object : editList) {
                    if (!StringUtils.isEmpty(object)) {
                        Map<String, Object> hashMap = (Map<String, Object>) object;
                        if (hashMap.get("bindScopeKey").equals("0")) {
                            if (StringUtils.isEmpty(hashMap.get("bindScopeValue"))) {
                                hashMap.put("bindScopeValue", line.getCode());
                            }
                        }
                        Integer integer = cMesCustomPropertyMapper.editCustomPropertyValue(hashMap);
                        if (integer < 1) {
                            throw new Exception("编辑自定义属性内容失败");
                        }
                    }
                }
            }
        }
    }

    /**
     * 添加
     */
    @Override
    public Integer addLine(CMesLineT line) throws Exception {
        // TODO Auto-generated method stub
//		List<CMesLineT> findAllLine = mapper.findAllLine(line);   //查询name是否重复
        Map<String, Object> lineMap = dao.getLineByName(line);
        if (line.getName() == null || line.getName() == "") {
            throw new ParameterNullException("参数异常", 200);
        } else if (lineMap != null) {
            throw new NameRepeatException("产线名称重复或产线编码重复", 100);
        }

        //新增自定义属性值（内容）
        handleCustomValue(line);
        Integer integer = dao.addLine(line);
        if (integer < 1) {
            throw new Exception("添加产线失败");
        }

        return integer;
    }

    /**
     * 修改
     */
    @Override
    public Integer updateLine(CMesLineT line) throws Exception {
        // TODO Auto-generated method stub
        Integer id = line.getId();   //id
        CMesLineT findLineByid = dao.findLineByid(id); //获取修改前的name
        if (!findLineByid.getName().equals(line.getName())) {
            CMesLineT lines = new CMesLineT();
            lines.setName(line.getName());

//			List<CMesLineT> findAllLine = mapper.findAllLine(lines);  //查询修改后的name是否重复
            Map<String, Object> lineMap = dao.getLineByName(lines);
            if (lineMap != null) {
                throw new NameRepeatException("产线名称重复", 100);
            }
        }


        //修改自定义属性值（内容）
        handleCustomValue(line);

        Integer integer = dao.updateLine(line);
        if (integer < 1) {
            throw new Exception("修改失败");
        }

        return integer;
    }

    /**
     * 删除
     */
    @Override
    public Integer delLine(Integer id) throws Exception {        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("参数异常", 200);
        }
        CMesLineT lineByid = dao.findLineByid(id);

        CMesCustomProperty customProperty = new CMesCustomProperty();
        customProperty.setBindScopeValue(lineByid.getCode());
        //删除类型
        customProperty.setObjectType(CustomAttributesConstant.lineManager);
        Integer integer = customPropertyService.delCustomPropertyByBindScopeValueAndObjectType(customProperty);
        if (integer < 1) {
            throw new Exception("删除失败");
        }
        return dao.delLine(id);
    }

    /**
     * 修改状态
     */
    @Override
    public Integer updateStatus(Integer id, Integer status) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.updateStatus(id, status);
    }

    @Override
    public List<CMesStationLine> findStationByLid(Integer id) {
        // TODO Auto-generated method stub
        return dao.findStationByLid(id);
    }

    @Override
    public int addSchebuling(CMesScheduling du) {

        return dao.addScheduling(du);
    }

    @Override
    public CMesEndStocks findByName(String proName, String lineName, String stationName, Integer lineRegion) throws Exception {

        return dao.findByName(proName, lineName, stationName, lineRegion);
    }

    @Override
    public List<Integer> findAllPro() {
        // TODO Auto-generated method stub
        return dao.findAllPro();
    }

    //查询看板列表
    @Override
    public List<Map<String, Object>> findAll() {
        // TODO Auto-generated method stub
        return dao.findAll();
    }

    @Override
    public Integer delBoard(Integer id) {
        // TODO Auto-generated method stub
        return dao.delBoard(id);
    }

    @Override
    public void addBoard(String boardName, String boardType, String lineName) {
        dao.addBoard(boardName, boardType, lineName);

    }

    @Override
    public void updateBoard(String boardName, String boardType, String lineName, Integer id) {
        // TODO Auto-generated method stub
        dao.updateBoard(boardName, boardType, lineName, id);
    }


    @Override
    public Map<String, Object> getLineByName(CMesLineT line) {
        return dao.getLineByName(line);
    }

    @Override
    public CMesLineT getLineByLineIdAndName(CMesLineT line) {
        return dao.getLineByLineIdAndName(line);
    }
}

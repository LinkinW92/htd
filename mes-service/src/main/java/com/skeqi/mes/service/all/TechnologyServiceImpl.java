package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.TechnologyDAO;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesLabelType;

@Service
@Transactional
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyDAO dao;

    @Override
    public List<CMesLabelType> findAllLabelType(CMesLabelType c) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findAllLabelType(c);
    }

    @Override
    public CMesLabelType findLabelTypeByid(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("id不能为空", 200);
        }
        return dao.findLabelTypeByid(id);
    }

    @Override
    public Integer addLabelType(CMesLabelType c) throws ServicesException {
        if (c.getName() == null || c.getName() == "") {
            throw new ParameterNullException("类型名称不能为空", 200);
        } else if (c.getLabelVr() == null || c.getLabelVr() == "") {
            throw new ParameterNullException("类型规则不能为空", 200);
        }

        List<CMesLabelType> findAllLabel = dao.findAllLabelType(c);
        if (findAllLabel.size() > 0) {
            throw new NameRepeatException("类型名称重复", 100);
        }
        // TODO Auto-generated method stub
        return dao.addLabelType(c);
    }

    @Override
    public Integer updateLabelType(CMesLabelType c) throws ServicesException {
        // TODO Auto-generated method stub
        if (c.getName() == null || c.getName() == "") {
            throw new ParameterNullException("类型名称不能为空", 200);
        } else if (c.getLabelVr() == null || c.getLabelVr() == "") {
            throw new ParameterNullException("类型规则不能为空", 200);
        }

        CMesLabelType findLabelByid = dao.findLabelTypeByid(c.getId());   //查询原标签name
        if (!findLabelByid.getName().equals(c.getName())) {
            CMesLabelType label = new CMesLabelType();
            label.setName(c.getName());
            List<CMesLabelType> findAllLabel = dao.findAllLabelType(label);
            if (findAllLabel.size() > 0) {
                throw new NameRepeatException("类型名称重复", 100);
            }
        }
        return dao.updateLabelType(c);
    }

    @Override
    public Integer delLabelType(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("id不能为空", 200);
        }
        return dao.delLabelType(id);
    }

    @Override
    public List<CMesLabelManagerT> findAllLabelManager(CMesLabelManagerT c) throws ServicesException {
        // TODO Auto-generated method stub
        return dao.findAllLabelManager(c);
    }

    @Override
    public CMesLabelManagerT findLabelManagerByid(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("id不能为空", 200);
        }
        return dao.findLabelManagerByid(id);
    }

    @Override
    public Integer addLabelManager(CMesLabelManagerT c) throws ServicesException {
        // TODO Auto-generated method stub
        if (c.getLabelName() == null || c.getLabelName() == "") {
            throw new ParameterNullException("标签name不能为空", 200);
        } else if (c.getLabelNumber() == null || c.getLabelNumber() == "") {
            throw new ParameterNullException("标签编号不能为空", 200);
        } else if (c.getLabelRules() == null || c.getLabelRules() == "") {
            throw new ParameterNullException("标签规则不能为空", 200);
        }





        List<CMesLabelManagerT> findAllLabelManager = dao.findAllLabelManager(c);
        if (findAllLabelManager.size() > 0) {
            throw new NameRepeatException("标签名称/标签编号重复", 100);
        }
        return dao.addLabelManager(c);
    }

    @Override
    public Integer updateLabelManager(CMesLabelManagerT c) throws ServicesException {
        // TODO Auto-generated method stub
        if (c.getLabelName() == null || c.getLabelName() == "") {
            throw new ParameterNullException("标签name不能为空", 200);
        } else if (c.getLabelNumber() == null || c.getLabelNumber() == "") {
            throw new ParameterNullException("标签编号不能为空", 200);
        } else if (c.getLabelRules() == null || c.getLabelRules() == "") {
            throw new ParameterNullException("标签规则不能为空", 200);
        }

        CMesLabelManagerT findLabelManagerByid = dao.findLabelManagerByid(c.getId());


        if (!findLabelManagerByid.getLabelName().equals(c.getLabelName())) {  //如果修改前后的name不同
            CMesLabelManagerT t = new CMesLabelManagerT();
            t.setLabelName(c.getLabelName());

            List<CMesLabelManagerT> findAllLabelManager = dao.findAllLabelManager(t);
            if (findAllLabelManager.size() > 0) {
                throw new NameRepeatException("标签名称重复", 100);
            }
        }
        if (!findLabelManagerByid.getLabelNumber().equals(c.getLabelNumber())) {
            CMesLabelManagerT m = new CMesLabelManagerT();
            m.setLabelNumber(c.getLabelNumber());
            List<CMesLabelManagerT> findAllLabelManager = dao.findAllLabelManager(m);
            if (findAllLabelManager.size() > 0) {
                throw new NameRepeatException("标签编号重复", 100);
            }
        }
        return dao.updateLabelManager(c);
    }

    @Override
    public Integer delLabelManager(Integer id) throws ServicesException {
        // TODO Auto-generated method stub
        if (id == null || id == 0) {
            throw new ParameterNullException("id不能为空", 200);
        }
        return dao.delLabelManager(id);
    }

}

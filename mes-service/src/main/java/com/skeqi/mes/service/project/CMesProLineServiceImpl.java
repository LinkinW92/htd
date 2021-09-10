package com.skeqi.mes.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesProLineDAO;
import com.skeqi.mes.pojo.CMesLineT;

@Service
public class CMesProLineServiceImpl implements CMesProLIneService {

	@Autowired
	CMesProLineDAO dao;

	@Override
	public List<CMesLineT> findAllLine(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllLine(name);
	}

	@Override
	public Integer addLine(CMesLineT line) throws ServicesException {
		// TODO Auto-generated method stub
		if (line.getName() == null || line.getName() == "") {
			throw new ParameterNullException("名称不能为空", 200);
		} else if (line.getYieldNumber() == null || line.getYieldNumber() == 0) {
			throw new ParameterNullException("理论产量不能为空", 200);
		} else if (line.getProductMark() == null || line.getProductMark() == "") {
			throw new ParameterNullException("产品标记不能为空", 200);
		} else if (line.getRegion() == null || line.getRegion() == 0) {
			throw new ParameterNullException("所属区域不能为空", 200);
		}
		return dao.addLine(line);
	}

	@Override
	public Integer updateLine(CMesLineT line) throws ServicesException {
		// TODO Auto-generated method stub
		if (line.getName() == null || line.getName() == "") {
			throw new ParameterNullException("名称不能为空", 200);
		} else if (line.getYieldNumber() == null || line.getYieldNumber() == 0) {
			throw new ParameterNullException("理论产量不能为空", 200);
		} else if (line.getId() == null || line.getId() == 0) {
			throw new ParameterNullException("Id不能为空", 200);
		} else if (line.getProductMark() == null || line.getProductMark() == "") {
			throw new ParameterNullException("产品标记不能为空", 200);
		} else if (line.getRegion() == null || line.getRegion() == 0) {
			throw new ParameterNullException("所属区域不能为空", 200);
		}
		return dao.updateLine(line);
	}

	@Override
	public Integer delLine(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if (id == null || id == 0) {
			throw new ParameterNullException("Id不能为空", 200);
		}
		return dao.delLine(id);
	}

	@Override
	public Integer findByName(String lineName) {
		// TODO Auto-generated method stub
		return dao.findByName(lineName);
	}

	@Override
	public Integer findProVrByLine(String productionVr) {
		// TODO Auto-generated method stub
		return dao.findProVrByLine(productionVr);
	}

	@Override
	public List<CMesLineT> findproTypeByLine(String proType) {
		// TODO Auto-generated method stub
		List<CMesLineT> list = dao.findproTypeByLine( proType);
			for (int i = 0; i < list.size(); i++) {
				String [] str =  list.get(i).getProductMark().split(",");
				for (int j = 0; j < str.length; j++) {
					if(j == (str.length+1)){
						list.remove(i);
					}else{
						if(str.equals("productionVr")){
							break;
						}
					}
				}
			}
			return list;
	}

	public static void main(String[] args) {
		String str = "1,2";
		String [] s = str.split(",");
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);

		}
//		String [] strings = str.indexOf(",");
	}

	@Override
	public List<CMesLineT> findLineIdByProType(Integer lineId) {
		// TODO Auto-generated method stub
		return dao.findLineIdByProType( lineId);
	}

	@Override
	public String findPidByType(Integer productionId) {
		// TODO Auto-generated method stub
		return dao.findPidByType( productionId) ;
	}

}

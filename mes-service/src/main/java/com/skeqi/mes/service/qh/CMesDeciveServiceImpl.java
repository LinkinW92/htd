package com.skeqi.mes.service.qh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.qh.DateTool;
import com.skeqi.mes.mapper.qh.CMesDeciveDao;
import com.skeqi.mes.pojo.CMesDeviceT;

@Service
public class CMesDeciveServiceImpl implements CMesDeciveService {

	@Autowired
	private CMesDeciveDao deciveDao;

	private DateTool dateTool = new DateTool();

	@Override
	public List<CMesDeviceT> findAll(Map<String, Object> map) {

		return deciveDao.findAll(map);
	}



	@Override
	public Integer delTool(Integer id) throws ServicesException {

		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return deciveDao.delTool(id);

	}

	@Override
	public Map<String, Object> alterTool(CMesDeviceT c) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();

		try {
			// 验证设备名是否可以使用
			map.put("id", c.getId());
			List<CMesDeviceT> list = deciveDao.findAll(map);
			CMesDeviceT b = list.get(0);// 原设备名
			map.clear();
			map.put("name_t", c.getToolName());
			List<CMesDeviceT> list2 = deciveDao.findAll(map);
			if (list2.size()==0) {
				// 无重复设备名
				map1.put("code", 0);
			}
			// 判断新设备名是否与原设备名相同
			if (!c.getToolName().equals(b.getToolName())) {
				// 新设备名与原设备名不相同
				throw new ServicesException(1,"设备名不可用");
			}

//			c.setToolName(new String(c.getToolName().getBytes("ISO8859-1"), "UTF-8"));
//			if (c.getLastMaintain()!=null) {
//				c.setLastMaintain(new String(c.getLastMaintain().getBytes("ISO8859-1"), "UTF-8"));
//			}
//			c.setFirstUse(new String(c.getFirstUse().getBytes("ISO8859-1"), "UTF-8"));
//			c.setToolDis(new String(c.getToolDis().getBytes("ISO8859-1"), "UTF-8"));

			//usefulLife 已用寿命
			c.setUsefulLife(b.getUsefulLife());
			//suprplusLife 剩余寿命
			int suprplusLife = c.getEstimateLife()-c.getUsefulLife();
			c.setSuprplusLife(suprplusLife);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
			if (c.getLastMaintain()==null||c.getLastMaintain()=="") {
				//nextMaintain  下次维护时间
				Date date = dateFormat.parse(c.getFirstUse()); // 指定日期
				Date newDate = dateTool.addDate(date, c.getMaintainCycle()); // 指定日期加上维护周期天数
				c.setNextMaintain(dateFormat.format(newDate));
			}else{
				//nextMaintain 下次维护时间
				Date date = dateFormat.parse(c.getLastMaintain()); // 指定日期
				Date newDate = dateTool.addDate(date, c.getMaintainCycle()); // 指定日期加上维护周期天数
				c.setNextMaintain(dateFormat.format(newDate));
			}

			// surplusMaintain 剩余维护天数
			Integer day = dateTool.dateAndDate(c.getNextMaintain(),dateTool.getDate());
			c.setSurplusMaintain(day);


			int index = deciveDao.alterTool(c);
			if (index != 1) {
				map1.put("code", 1);
				map1.put("msg", "设备修改失败");
			}else{
				map1.put("code", 1);
				map1.put("msg", "设备修改成功");
			}
		}catch(ServicesException servicesException){
			map1.put("code",servicesException.getCode());
			map1.put("msg", servicesException.getMessage());
			servicesException.printStackTrace();
		}catch (Exception e) {
			map1.put("code", 2);
			map1.put("msg", "数据库异常");
			e.printStackTrace();
		}finally {
			return map1;
		}

	}

	@Override
	public Map<String, Object> addTool(CMesDeviceT c)  {
		Map<String, Object> msg = new HashMap<String, Object>();//记录msg
		List<CMesDeviceT> list = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name_t",c.getToolName());
			// 验证设备名是否存在
			list = deciveDao.findAll(map);
			if (list.size()>0) {
				// 存在
				msg.put("msg", "设备名存在");
				msg.put("code", -1);
				throw new ServicesException(-1,"设备名存在");
			}

			map.clear();
			map.put("toolNo", c.getToolNo());

			// 验证设备编号是否存在
			list = deciveDao.findAll(map);
			if (list.size()>0) {
				// 存在
				msg.put("code", -1);
				msg.put("msg", "设备编号存在");
				throw new ServicesException(-1,"设备编号存在");
			}
//			c.setToolName( new String(c.getToolName().getBytes("ISO8859-1"), "UTF-8"));
//			c.setFirstUse(new String(c.getFirstUse().getBytes("ISO8859-1"), "UTF-8"));
//			c.setToolDis(new String(c.getToolDis().getBytes("ISO8859-1"), "UTF-8"));
//			c.setToolNo(new String(c.getToolNo().getBytes("ISO8859-1"), "UTF-8"));

			//usefulLife 已用寿命
			c.setUsefulLife(0);

			//suprplusLife  剩余寿命
			int suprplusLife = c.getEstimateLife()-c.getUsefulLife();
			c.setSuprplusLife(suprplusLife);
			// lastMaintain 上次维护时间 暂时不需要

			//nextMaintain 下次维护时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式

			Date date = dateFormat.parse(c.getFirstUse()); // 指定日期
			Date newDate = dateTool.addDate(date, c.getMaintainCycle()); // 指定日期加上维护周期天数
			c.setNextMaintain(dateFormat.format(newDate));
			// surplusMaintain;// 剩余维护天数
			Integer day = dateTool.dateAndDate(c.getNextMaintain(),dateTool.getDate());
			c.setSurplusMaintain(day);


			int index = deciveDao.addTool(c);

			if (index==1) {
				msg.put("code", 0);
				msg.put("msg", "设备新增成功");
			}else{
				msg.put("code", 0);
				msg.put("msg", "设备新增失败");
			}
		} catch (ServicesException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			msg.put("code", -1);
			msg.put("msg", "设备新增异常");
		}finally {
			return msg;
		}

	}

	@Override
	public Integer alterSurplusMaintain(CMesDeviceT c) {

		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			//当前时间
	        String now = sdf.format(new Date());

	        // 下次维修时间
	        String nextMaintain = c.getNextMaintain();

	        Date d1=sdf.parse(now);
	        Date d2=sdf.parse(nextMaintain);
	        // 得到2个时间中天差
	        long daysBetween=(d2.getTime()-d1.getTime()+1000000)/(60*60*24*1000);

	        Integer surplusMaintain=Integer.parseInt(String.valueOf(daysBetween))+1;
			// 比较当前系统时间是否大于下次维护时间
			if (d1.after(d2)) {
				c.setSurplusMaintain(surplusMaintain*-1);
			}else {
				c.setSurplusMaintain(surplusMaintain);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return deciveDao.alterSurplusMaintain(c);
//			return 0;
		}


	}


}

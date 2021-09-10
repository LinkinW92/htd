package com.skeqi.mes.controller.gmg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionProcessT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.PMesStaionT;
import com.skeqi.mes.pojo.PMesStationPassLineT;
import com.skeqi.mes.pojo.PMesStationPassT;
import com.skeqi.mes.pojo.PMesTesttimeT;
import com.skeqi.mes.service.yin.UsersService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.gmg.JDBCUtils;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;





@Controller
@RequestMapping("skq")
public class StationPassTIime
{
	@Autowired
	UsersService usersService;


	@RequestMapping("GetLine")
	public String GetLine(HttpServletRequest request,HttpSession session)
	{
		List<CMesLineT> list_line=usersService.findAllLine();
		session.setAttribute("lineLIstX", list_line);
		return "report_control/beatStatistics";
	}



	@RequestMapping("beatStatistics")
	public @ResponseBody String beatStatistics(HttpServletRequest request,HttpSession session) throws ParseException
	{
		String str_sl="";

		String str_time=request.getParameter("s_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str_beginTime="";
        String str_endTime="";
        	str_beginTime=new SimpleDateFormat("yyyy/MM/dd").format(sdf.parse(str_time))+" 00:00:00";
        	str_endTime=new SimpleDateFormat("yyyy/MM/dd").format(sdf.parse(str_time))+" 23:59:59";
		String str_line=request.getParameter("s_line");
		System.err.println("s_line:"+str_line);
		Map<String, Object> map=new HashMap<>();
		map.put("str_line", str_line);
		/*int int_line=usersService.GetLineIDByName(map);*/
		int int_line=Integer.parseInt(str_line) ;
		System.err.println("s_line:"+str_line+"---"+int_line);
		String sl_="[";
		String sql = "{call TRIPOD.P_MES_STATIONPASS_P(?,?,?,?,?,?,?,?)}";

		PMesTesttimeT pmtt=new PMesTesttimeT();
		List<PMesTesttimeT> list_pmtt=new ArrayList<PMesTesttimeT>();
						//获取每个工位所用的时间
						String[] sk=  testProcedure(request, int_line, str_beginTime,str_endTime,sql);
						System.err.println("sk.length:"+sk.length);
						if(sk[0]!=null)
						{
						  String[] group_alltime=	sk[0].trim().split(",");
						  String[] group_averagetime=sk[1].trim().split(",");
						  String[] group_oknum=sk[2].trim().split(",");
						  String[] group_line=sk[3].trim().split(",");
						  String[] group_station=sk[4].trim().split(",");
						  if(group_alltime.length>1)
						  {
							  for(int i=1;i<group_alltime.length;i++)
							  {
								  System.out.println(group_alltime[i]+"\n");
								  if(sl_.equals("["))
									{
										sl_  +="{\"line\":\""+group_line[i].trim()+"\",\"station\":\""+group_station[i].trim()+"\",\"alltime\":\""+group_alltime[i]+"min\",\"oknum\":\""+group_oknum[i].trim()+"\",\"averagetime\":\""+group_averagetime[i]+"min\"}";
									}
									else
									{
										sl_  +=",{\"line\":\""+group_line[i].trim()+"\",\"station\":\""+group_station[i].trim()+"\",\"alltime\":\""+group_alltime[i]+"min\",\"oknum\":\""+group_oknum[i].trim()+"\",\"averagetime\":\""+group_averagetime[i]+"min\"}";
									}
							  }
						  }
							 sl_+="]";
							 str_sl= sl_;
						}
						else
						{
							str_sl= "[{\"line\":\""+"无"+"\",\"station\":\""+"无"+"\",\"alltime\":\""+"无"+"\",\"oknum\":\""+"无"+"\",\"averagetime\":\""+"无"+"\"}]";
						}

		return str_sl;
	}

public String[] testProcedure(HttpServletRequest request, Integer str_lineID,String str_begintime,String str_endtime,String sql)
{


		String[] ss= new String[5];

		CallableStatement call = null;
		Connection conn = null;
		try {
			//得到一个数据库连接
			conn = JDBCUtils.getConnection();
			//通过连接创建出statement
			call = conn.prepareCall(sql);
			//对于in参数，赋值
			call.setString(1, str_lineID+"");  // (第几个问号,要赋的值)
			call.setString(2, str_begintime);
			call.setString(3, str_endtime);
			System.err.println("str_begintime:"+str_begintime+"str_endtime:"+str_endtime);
			//对out参数，声明
			call.registerOutParameter(4,java.sql.Types.VARCHAR);//(第几个问号，声明的类型)
			call.registerOutParameter(5,java.sql.Types.VARCHAR);
			call.registerOutParameter(6,java.sql.Types.VARCHAR);
			call.registerOutParameter(7,java.sql.Types.VARCHAR);
			call.registerOutParameter(8,java.sql.Types.VARCHAR);
			//执行调用
			call.execute();
			//取出结果
            System.err.println(call.getString(4));
			String str_addtime = call.getString(4);  ss[0]=str_addtime;
			String str_averagetime = call.getString(5); ss[1]=str_averagetime;
			String str_qualified = call.getString(6); ss[2]=str_qualified;
			String str_empgroup = call.getString(7); ss[3]=str_empgroup;
			String str_station = call.getString(8); ss[4]=str_station;
			return  ss;
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return ss;
		}finally{
                    //关闭链接，释放资源
                   JDBCUtils.release(conn, call, null);
		}
	}
}

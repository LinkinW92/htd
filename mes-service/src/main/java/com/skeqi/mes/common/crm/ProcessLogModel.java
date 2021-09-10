package com.skeqi.mes.common.crm;

public class ProcessLogModel {

//	用户谁，什么操作了任务单，任务单目前为什么状态（生产任务单）
	public String taskLogInfo(String user,String ip,String taskName,String operation,String oldPlanNum,String newPlanNum,String projectName,String specificationModel,String oldStatus,String newStatus,String modelName,String stationNum){
//		完成、删除、编辑、创建、开始、暂停、生产
		String logInfo=null;
		if(operation!=null){
			if(operation.equals("编辑")){
				logInfo = "用户"+user+"("+ip+")，"+operation+"了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]，将"+modelName+"从"+oldPlanNum+"修改为"+newPlanNum;
			}
			if(operation.equals("删除")||operation.equals("创建")||operation.equals("完成")){
				logInfo = "用户"+user+"("+ip+")，"+operation+"了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]";
			}
		}

		if(newStatus!=null){
			if(newStatus.equals("开始")){
				logInfo = "用户"+user+"("+ip+")，将生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]由{开始状态}更改为{"+newStatus+"状态}";
			}
			if(newStatus.equals("暂停")||newStatus.equals("生产")){
				logInfo = "用户"+user+"("+ip+")，将生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]由{"+oldStatus+"状态}更改为{"+newStatus+"状态}";
			}
		}

		return logInfo;
	}

//	什么时间（空格）、那个生产任务的那道工序，用户谁，提交了品检检验单，合格数量为，NG数量为，备注为，责任人为（品检）
	public String qualityLogInfo(String user,String ip,String taskName,String projectName,String specificationModel,String processName,String OKNum,String NGNum,String remarks,String operationPerson,String status,String stationNum ){
		String logInfo=null;

		if(status.equals("申请")){
			logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，品检申请单[合格数量："+OKNum+"  ，NG数量："+NGNum+"  ，备注："+remarks+"     ，品检员："+operationPerson+"]";
		}else if(status.equals("返修申请")){
			logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，返修申请单[申请合格数量："+OKNum+"  ，品检员："+operationPerson+"]";

		}else if(status.equals("返修审核")){
			logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，返修审核单[审核合格数量："+OKNum+"  ，品检员："+operationPerson+"]";

		}else if(status.equals("审核")){
			logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，品检审核单[合格数量："+OKNum+"  ，NG数量："+NGNum+"  ，审核员："+operationPerson+"]";
		}else if(status.equals("品检撤销")){
			logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，品检撤销单[撤销申请合格数量："+OKNum+"  ，撤销申请NG数量："+NGNum+"]";

		}else if(status.equals("委外发出撤销")){
			logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，委外发出撤销单[撤销申请合格数量："+OKNum+"]";

		}else if(status.equals("委外接收撤销")){
			logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，委外接收撤销单[撤销申请合格数量："+OKNum+"]";

		}else if(status.equals("成品撤销")){
			logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，成品撤销单[撤销申请合格数量："+OKNum+"]";

		}else if(status.equals("返修撤销")){
			logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，返修撤销单[撤销申请合格数量："+OKNum+"]";

		}

		return logInfo;
	}

//	那个生产任务的那道工序，用户谁，提交了下推请求，数量为，责任人为（下推）
	public String pushDownLogInfo(String user,String ip,String taskName,String projectName,String specificationModel,String processName,String OKNum,String operationPerson,String status,String supplier,String stationNum){
		String logInfo=null;
		if(status!=null){
			if(status.equals("正常")){
				logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，下推单[下推数量："+OKNum+"  ，负责人："+operationPerson+"]";
			}else if(status.equals("委外接收申请")){
				logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，委外接收申请单[委外接收申请："+OKNum+"  供应商："+supplier+"，负责人："+operationPerson+"]";

			}else if(status.equals("委外接收审核")){
				logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，委外接收审核单[委外接收审核："+OKNum+"  供应商："+supplier+"，负责人："+operationPerson+"]";
			}
			else if(status.equals("委外发出申请")){
				logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，委外发出申请单[委外发出申请："+OKNum+"  供应商："+supplier+"，负责人："+operationPerson+"]";

			}else if(status.equals("委外发出审核")){
				logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序，委外发出审核单[委外发出审核："+OKNum+"  供应商："+supplier+"，负责人："+operationPerson+"]";

			}else if(status.equals("成品入库申请")){
				logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"申请单[入库数量："+OKNum+"  ，负责人："+operationPerson+"]";

			}else if(status.equals("成品入库审核")){
				logInfo="用户"+user+"("+ip+")，提交了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"审核单[入库数量："+OKNum+"  ，负责人："+operationPerson+"]";

			}
		}


		return logInfo;
	}

	public String splitChildInfoLog(String user,String ip,String taskName,String projectName,String specificationModel,String processName,String stationNum,String status){
		String logInfo=null;
		if(status.equals("拆分")){
			logInfo="用户"+user+"("+ip+")，拆分了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序";
		}
		else if(status.equals("删除子分支")){
			logInfo="用户"+user+"("+ip+")，删除了生产任务[ "+taskName+"("+projectName+"，"+specificationModel+"，"+stationNum+")"+" ]"+processName+"工序的子分支";
		}

		return logInfo;
	}

//	工艺路线
	public String addRouteInfo(String user,String ip,String projectName,String specificationModel,String processName,String stationNum,String status,String orderNum,String oldOrder){
		String logInfo = null;
		if(status.equals("新增工艺工序")){
			logInfo="用户"+user+"("+ip+")，新增了工艺路线("+projectName+"，"+specificationModel+"，"+stationNum+") 工序顺序为"+orderNum+"的"+"["+processName+"]"+"工序";
		}
		if(status.equals("插入工艺工序")){
			logInfo="用户"+user+"("+ip+")，插入了工艺路线("+projectName+"，"+specificationModel+"，"+stationNum+") 工序顺序为"+orderNum+"的"+"["+processName+"]"+"工序";
		}
		if(status.equals("删除工艺工序")){
			logInfo="用户"+user+"("+ip+")，删除了工艺路线("+projectName+"，"+specificationModel+"，"+stationNum+") 工序顺序为"+orderNum+"的"+"["+processName+"]"+"工序";
		}
		if(status.equals("编辑工艺工序")){
			logInfo="用户"+user+"("+ip+")，编辑了工艺路线("+projectName+"，"+specificationModel+"，"+stationNum+") 将工序顺序为"+orderNum+"的"+"["+processName+"]"+"工序改为了工序顺序"+oldOrder;
		}
		if(status.equals("导入报错")){
			logInfo="报错内容:"+oldOrder;
		}
		return logInfo;
	}

//	工序管理
	public String addProcessManageLog(String user,String ip,String status,String processName){
		String logInfo = null;
		if(status.equals("新增工序操作")){
			logInfo="用户"+user+"("+ip+")，新增了工序 ["+processName+" ]";
		}
		if(status.equals("删除工序操作")){
			logInfo="用户"+user+"("+ip+")，删除了工序 ["+processName+" ]";
		}
		if(status.equals("编辑工序操作")){
			logInfo="用户"+user+"("+ip+")，编辑了工序 ["+processName+" ]";
		}
		return  logInfo;
	}

	public static void main(String[] args) {
		ProcessLogModel str = new ProcessLogModel();
		System.out.println(str.addRouteInfo("admin", "127.0.0.1", "测试打磨", "SFG-GQQW2", "下料", "10", "新增工艺工序","1","2"));
		System.out.println(str.taskLogInfo("admin", "127.0.0.1", "测试打磨", "编辑", "23","35", "XT456", "ASF-SAFJ-1SDF",null,null,"计划数量",""));
		System.out.println(str.taskLogInfo("admin", "127.0.0.1", "测试打磨", "创建", null,null, "XT456", "ASF-SAFJ-1SDF",null,null,null,""));
		System.out.println(str.taskLogInfo("admin", "127.0.0.1", "测试打磨", "删除", null,null, "XT456", "ASF-SAFJ-1SDF",null,null,null,""));

		System.out.println(str.taskLogInfo("admin", "127.0.0.1", "测试打磨", "完成", null,null, "XT456", "ASF-SAFJ-1SDF",null,null,null,""));

		System.out.println(str.taskLogInfo("admin", "127.0.0.1", "测试打磨", null, null,null, "XT456", "ASF-SAFJ-1SDF",null,"开始",null,""));
		System.out.println(str.taskLogInfo("admin", "127.0.0.1", "测试打磨", null, null,null, "XT456", "ASF-SAFJ-1SDF","开始","生产",null,""));
		System.out.println(str.taskLogInfo("admin", "127.0.0.1", "测试打磨", null, null,null, "XT456", "ASF-SAFJ-1SDF","生产","暂停",null,""));
		System.out.println(str.taskLogInfo("admin", "127.0.0.1", "测试打磨", null, null,null, "XT456", "ASF-SAFJ-1SDF","暂停","生产",null,""));

		System.out.println(str.qualityLogInfo("admin", "127.0.0.1", "测试打磨", "XT345", "SFG-GQQW2", "下料", "10", "4", "无", "张三","正常",""));
		System.out.println(str.qualityLogInfo("admin", "127.0.0.1", "测试打磨", "XT345", "SFG-GQQW2", "下料", "10", "4", "无", "张三","返修",""));
		System.out.println(str.pushDownLogInfo("admin", "127.0.0.1", "测试打磨", "XT345", "SFG-GQQW2", "下料", "10","张三","正常","",""));
		System.out.println(str.pushDownLogInfo("admin", "127.0.0.1", "测试打磨", "XT345", "SFG-GQQW2", "下料", "10","张三","委外接收","",""));
		System.out.println(str.pushDownLogInfo("admin", "127.0.0.1", "测试打磨", "XT345", "SFG-GQQW2", "下料", "10","张三","委外发出","",""));
		System.out.println(str.pushDownLogInfo("admin", "127.0.0.1", "测试打磨", "XT345", "SFG-GQQW2", "成品入库", "10","张三","成品入库","",""));
	}

}

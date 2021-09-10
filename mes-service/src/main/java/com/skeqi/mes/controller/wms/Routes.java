package com.skeqi.mes.controller.wms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wms")
public class Routes {

	//物料条码规则
	@RequestMapping(value="MaterialBarCodeRule.html")
	public String materialBarCodeRule(){
		return "/wms/view/MaterialBarCodeRule";
	}

	//物料库存
	@RequestMapping(value="MaterialNumber.html")
	public String materialNumber(){
		return "/wms/view/MaterialNumber";
	}

	//库位管理
	@RequestMapping(value="Location.html")
	public String location(){
		return "/wms/view/Location";
	}

	//领用出库
	@RequestMapping(value="LeadOut.html")
	public String leadOut(){
		return "/wms/view/LeadOut";
	}

	//k3入库信息
	@RequestMapping(value="K3ImportNotifydetail.html")
	public String k3ImportNotifydetail(){
		return "/wms/view/K3ImportNotifydetail";
	}

	//K3出库信息
	@RequestMapping(value="K3ExportNotifydetail.html")
	public String k3ExportNotifydetail(){
		return "/wms/view/K3ExportNotifydetail";
	}

	//入库队列
	@RequestMapping(value="InTaskqueue.html")
	public String inTaskqueue(){
		return "/wms/view/InTaskqueue";
	}

	//加料管理
	@RequestMapping(value="ChargingTaskqueue.html")
	public String chargingTaskqueue(){
		return "/wms/view/ChargingTaskqueue";
	}

	//待审批管理
	@RequestMapping(value="Approval.html")
	public String approval(){
		return "/wms/view/Approval";
	}

	//区域管理
	@RequestMapping(value="Area.html")
	public String area(){
		return "/wms/view/Area";
	}

	//部门管理
	@RequestMapping(value="Department.html")
	public String department(){
		return "/wms/view/Department";
	}





















































	//从120行开始修改路径得映射


	//仓库管理
	@RequestMapping(value="Warehouse.html")
	public String warehose(){
		return "/wms/view/Warehouse";
	}

	//库区管理
	@RequestMapping(value="ResevoirArea.html")
	public String resevoirArea(){
		return "/wms/view/ResevoirArea";
	}

	//http://localhost:8080/MES_System/view/wms/view/ProcessApproval.jsp
	//审批流程
	@RequestMapping(value="ProcessApproval.html")
	public String processApproval(){
		return "/wms/view/ProcessApproval";
	}
	//http://localhost:8080/MES_System/view/wms/view/StorageDetail.jsp
	//库存详情
	@RequestMapping(value="StorageDetail.html")
	public String storageDetail(){
		return "/wms/view/StorageDetail";
	}

	//项目管理
	//http://localhost:8080/MES_System/view/wms/view/Project.jsp
	@RequestMapping(value="Project.html")
	public String project(){
		return "/wms/view/Project";
	}

	//http://localhost:8080/MES_System/view/wms/view/ProjectType.jsp
	//项目类型管理
	@RequestMapping(value="ProjectType.html")
	public String projectType(){
		return "/wms/view/ProjectType";
	}

	//http://localhost:8080/MES_System/view/wms/view/OutTaskqueue.jsp
	//出库队列
	@RequestMapping(value="OutTaskqueue.html")
	public String outTaskqueue(){
		return "/wms/view/OutTaskqueue";
	}
	//http://localhost:8080/MES_System/view/wms/view/Warehousing.jsp
	//入库管理
	@RequestMapping(value="Warehousing.html")
	public String warehousing(){
		return "/wms/view/Warehousing";
	}
	//http://localhost:8080/MES_System/view/wms/view/stockInventory.jsp
	//库存盘点
	@RequestMapping(value="StockInventory.html")
	public String stockInventory(){
		return "/wms/view/stockInventory";
	}
	//http://localhost:8080/MES_System/view/wms/view/trayMoveLocation.jsp
	//托盘移库
	@RequestMapping(value="TrayMoveLocation.html")
	public String trayMoveLocation(){
		return "/wms/view/trayMoveLocation";
	}
	//http://localhost:8080/MES_System/view/wms/view/locationSelect.jsp
	//托盘管理
	@RequestMapping(value="LocationSelect.html")
	public String LocationSelect(){
		return "/wms/view/locationSelect";
	}
	//http://localhost:8080/MES_System/view/wms/view/approvalResult.jsp
	//审批结果
	@RequestMapping(value="approvalResult.html")
	public String ApprovalResult(){
		return "/wms/view/approvalResult";
	}

}

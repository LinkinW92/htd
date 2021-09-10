package com.skeqi.mes.service.zch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.zch.NextBarcodeDao;

@Service
public class NextBarcodeServiceImpl implements NextBarcodeService {
	@Autowired
	NextBarcodeDao nextBarcodeDao;

	@Override
	public JSONObject nextBarcode(Integer lineId, String lineName, String printFlag, Integer num) {
		JSONObject jo = new JSONObject();
		String barcode = "";
		String barcodeNext = "";

		Integer PRINTE_NUMBER = 0;
		Integer ORDER_NUMBER = 0;
		Object PRODUCT_ID = null;
		Object PRODUCTION_STE = null;
		Object BARCODE_RULE_ID = null;
		String labelRules = "";
		Integer serialNo = 0;
		Object workorderId = null;

		// 获取产线
		Map<String, Object> lineMap = nextBarcodeDao.getLineByIDAndName(lineId, lineName);

		if(lineMap == null) {
			return errJson("101", "传入产线不存在");
		}
		lineId = Integer.parseInt(lineMap.get("ID").toString());

		// 查询高优先级开始状态工单
		Map<String, Object> workorderMap = nextBarcodeDao.getHighPriorityWorkorderByLineId(lineId);
		if(workorderMap == null) {
			return errJson("122", "当前没有开始的工单");
		}
		// 生成条码数量
		PRINTE_NUMBER = Integer.parseInt(workorderMap.get("PRINTE_NUMBER").toString());
		// 订单数量
		ORDER_NUMBER = Integer.parseInt(workorderMap.get("ORDER_NUMBER").toString());
		// 产品id
		PRODUCT_ID = workorderMap.get("PRODUCT_ID");
		// 标签规则id
		BARCODE_RULE_ID = workorderMap.get("BARCODE_RULE_ID");
		// 工单id
		workorderId = workorderMap.get("ID");

		if(PRINTE_NUMBER >= ORDER_NUMBER) {
			return errJson("123", "当前工单已生成所有条码");
		}
		// 获取产品
		Map<String, Object> productMap = nextBarcodeDao.getProductByID(PRODUCT_ID);
		// 产品状态
		PRODUCTION_STE = productMap.get("PRODUCTION_STE");
		if("1".equals(PRODUCTION_STE)) {
			return errJson("124", "产品是离线状态，无法生成条码");
		}

		// 获取标签规则
		Map<String, Object> labelMap = nextBarcodeDao.getLabelByID(BARCODE_RULE_ID);
		if(labelMap == null) {
			return errJson("125", "标签规则为空或未找到");
		}
		// 获取
		if(labelMap.get("LABEL_RULES") == null) {
			return errJson("125", "标签规则为空或未找到");
		}else {
			labelRules = labelMap.get("LABEL_RULES").toString();
		}

		if(!labelRules.contains("###")) {
			return errJson("126", "标签的规则中不存在流水号代码");
		}
		// 获取#的位数
		Integer ws = labelRules.lastIndexOf("#") - labelRules.indexOf("#") + 1;

		labelRules = labelRules.replace("ymd", nian() + yue() + ri());

		// 获取最后生成条码R
		Map<String, Object> printMapR = nextBarcodeDao.getMaxPrintR(lineId);
		// 获取最后生成条码P
		Map<String, Object> printMapP = nextBarcodeDao.getMaxPrintP(lineId);

		Integer flag = 0;  //是否打印
		if(printMapP != null) {
			serialNo = Integer.parseInt(printMapP.get("SERIAL_NO").toString());
			flag = 1;
		}
		if(printMapR != null) {
			Integer serialNoR = Integer.parseInt(printMapR.get("SERIAL_NO").toString());
			if(serialNoR > serialNo) {
				serialNo = serialNoR;
				flag = Integer.parseInt(printMapR.get("PRINT_FLAG").toString());
			}
		}

		if(serialNo != 0 && flag == 0) { //今日生成过条码 且未打印
			barcode = printMapR.get("SN").toString();
			serialNo ++;
			barcodeNext = labelRules.substring(0, labelRules.length() - ws) + String.format("%" + ws +"d", serialNo).replace(" ", "0");
			jo.put("isSuccess", true);
			jo.put("code", "127");
			jo.put("errMsg", "条码已生成，但未打印");
			jo.put("barcode", barcode);
			jo.put("barcodeNext", barcodeNext);
			return jo;
		}else { // 今日未生成条码 或 今日生成过且已打印
			serialNo ++;
			barcode = labelRules.substring(0, labelRules.length() - ws) + String.format("%" + ws +"d", serialNo).replace(" ", "0");
			barcodeNext = labelRules.substring(0, labelRules.length() - ws) + String.format("%" + ws +"d", serialNo + 1).replace(" ", "0");

			Map<String, Object> print = new HashMap<>();
			print.put("SN", barcode);
			print.put("LINE_ID", lineId);
			print.put("PRODUCTION_ID", PRODUCT_ID);
			print.put("SERIAL_NO", serialNo);
			print.put("WORK_ORDER_ID", workorderId);
			// 数量
			if(num != null) {
				print.put("num", num);
			} else {
				print.put("num", 1);
			}
			if(printFlag != null && "1".equals(printFlag)) {
				print.put("printFlag", 1);
			}else{
				print.put("printFlag", 0);
			}
			// 插入条码打印表
			nextBarcodeDao.insertPrint(print);
			// 生成条码数量+1
			nextBarcodeDao.updatePrintNumber(workorderId);
		}

		jo.put("isSuccess", true);
		jo.put("code", "");
		jo.put("errMsg", "");
		jo.put("barcode", barcode);
		jo.put("barcodeNext", barcodeNext);
		return jo;
	}

	@Override
	public JSONObject printBarcode(String barcode) {
		JSONObject jo = new JSONObject();

		nextBarcodeDao.updatePrintFlag(barcode);

		jo.put("isSuccess", true);
		jo.put("code", "");
		jo.put("errMsg", "");
		return jo;
	}

	// 返回年代码
    public String nian() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy");// 设置日期格式
        String nian = df.format(new Date());// new Date()为获取当前系统时间

        switch (nian) {
            case "2011":
                nian = "1";
                break;
            case "2012":
                nian = "2";
                break;
            case "2013":
                nian = "3";
                break;
            case "2014":
                nian = "4";
                break;
            case "2015":
                nian = "5";
                break;
            case "2016":
                nian = "6";
                break;
            case "2017":
                nian = "7";
                break;
            case "2018":
                nian = "8";
                break;
            case "2019":
                nian = "9";
                break;
            case "2020":
                nian = "A";
                break;
            case "2021":
                nian = "B";
                break;
            case "2022":
                nian = "C";
                break;
            case "2023":
                nian = "D";
                break;
            case "2024":
                nian = "E";
                break;
            case "2025":
                nian = "F";
                break;
            case "2026":
                nian = "G";
                break;
            case "2027":
                nian = "H";
                break;
            case "2028":
                nian = "J";
                break;
            case "2029":
                nian = "K";
                break;
            case "2030":
                nian = "L";
                break;
            case "2031":
                nian = "M";
                break;
            case "2032":
                nian = "N";
                break;
            case "2033":
                nian = "P";
                break;
            case "2034":
                nian = "R";
                break;
            case "2035":
                nian = "S";
                break;
            case "2036":
                nian = "T";
                break;
            case "2037":
                nian = "V";
                break;
            case "2038":
                nian = "W";
                break;
            case "2039":
                nian = "X";
                break;
            case "2040":
                nian = "Y";
                break;

        }
        return nian;
    }

    // 返回月代码
    public String yue() {

        SimpleDateFormat df = new SimpleDateFormat("MM");// 设置日期格式
        // new Date()为获取当前系统时间
        String yue = df.format(new Date());

        switch (yue) {
            case "01":
                yue = "1";
                break;
            case "02":
                yue = "2";
                break;
            case "03":
                yue = "3";
                break;
            case "04":
                yue = "4";
                break;
            case "05":
                yue = "5";
                break;
            case "06":
                yue = "6";
                break;
            case "07":
                yue = "7";
                break;
            case "08":
                yue = "8";
                break;
            case "09":
                yue = "9";
                break;
            case "10":
                yue = "A";
                break;
            case "11":
                yue = "B";
                break;
            case "12":
                yue = "C";
                break;
        }
        return yue;
    }

    // 返回日代码
    public String ri() {
        SimpleDateFormat df = new SimpleDateFormat("dd");// 设置日期格式
        String ri = df.format(new Date());// new Date()为获取当前系统时间

        switch (ri) {
            case "01":
                ri = "1";
                break;
            case "02":
                ri = "2";
                break;
            case "03":
                ri = "3";
                break;
            case "04":
                ri = "4";
                break;
            case "05":
                ri = "5";
                break;
            case "06":
                ri = "6";
                break;
            case "07":
                ri = "7";
                break;
            case "08":
                ri = "8";
                break;
            case "09":
                ri = "9";
                break;
            case "10":
                ri = "A";
                break;
            case "11":
                ri = "B";
                break;
            case "12":
                ri = "C";
                break;
            case "13":
                ri = "D";
                break;
            case "14":
                ri = "E";
                break;
            case "15":
                ri = "F";
                break;
            case "16":
                ri = "G";
                break;
            case "17":
                ri = "H";
                break;
            case "18":
                ri = "J";
                break;
            case "19":
                ri = "K";
                break;
            case "20":
                ri = "L";
                break;
            case "21":
                ri = "M";
                break;
            case "22":
                ri = "N";
                break;
            case "23":
                ri = "P";
                break;
            case "24":
                ri = "R";
                break;
            case "25":
                ri = "S";
                break;
            case "26":
                ri = "T";
                break;
            case "27":
                ri = "V";
                break;
            case "28":
                ri = "W";
                break;
            case "29":
                ri = "X";
                break;
            case "30":
                ri = "Y";
                break;
            case "31":
                ri = "0";
                break;
        }

        return ri;
    }

	/**
	 * 返回错误信息json
	 * @param code
	 * @param errMsg
	 * @return
	 */
	private JSONObject errJson(String code, String errMsg) {
		JSONObject jo = new JSONObject();
		jo.put("isSuccess", false);
		jo.put("code", code);
		jo.put("errMsg", errMsg);
		jo.put("barcode", "");
		jo.put("barcodeNext", "");
		return jo;
	}

    @Override
    public Map<String, Object> getLabelByIDAndNameAndLineId(Integer id, String labelName, Integer lineId) {
        return nextBarcodeDao.getLabelByIDAndNameAndLineId(id,labelName,lineId);
    }
}

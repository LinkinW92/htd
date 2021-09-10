package com.skeqi.mes.controller.yp.oa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.oa.MyApplicationInquiryService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 我的申请
 *
 * @author yinp
 * @data 2021年5月10日
 */
@RestController
@RequestMapping("/api/oa/myApplicationInquiry")
public class MyApplicationInquiryController {

	@Autowired
	MyApplicationInquiryService service;

	/**
	 * 查询
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageNum = EqualsUtil.pageNum(request);
			int pageSize = EqualsUtil.pageSize(request);

			int userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String startDate = EqualsUtil.string(request, "startDate", "开始日期", false);
			String endDate = EqualsUtil.string(request, "endDate", "结束日期", false);
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			String type = EqualsUtil.string(request, "type", "单据类型", false);
			String state = EqualsUtil.string(request, "state", "状态", false);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("startDate", startDate);
			json.put("endDate", endDate);
			json.put("listNo", listNo);
			json.put("type", type);
			json.put("state", state);

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询明细
	 *
	 * @return
	 */
	@RequestMapping("/queryDetails")
	public Rjson queryDetails(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "ID", true);

			JSONObject json = new JSONObject();
			json.put("id", id);

			List<JSONObject> list = service.queryDetails(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询审批备注
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findApprovalRecordNote")
	@Transactional
	public Rjson findApprovalRecordNote(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);

			List<JSONObject> list = service.findApprovalRecordNote(id);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 重新发起申请
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "我的申请", method= "重新发起申请")
	@Transactional
	@RequestMapping("/reInitiateApplication")
	public Rjson reInitiateApplication(HttpServletRequest request) {
		try {
			int userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);
			String name = EqualsUtil.string(request, "name", "申请类型", true);
			String key = EqualsUtil.string(request, "key", "key", true);
			String detailed = EqualsUtil.string(request, "detailed", "明细", true);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("userName", userName);
			json.put("name", name);
			json.put("key", key);
			json.put("detailed", detailed);

			service.reInitiateApplication(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 加载图片
	 *
	 * @param response
	 * @param nian
	 * @param yue
	 * @param ri
	 * @param name
	 */
	@RequestMapping("/imgs/{nian}/{yue}/{ri}/{name:.+}")
	public void find(HttpServletResponse response, @PathVariable("nian") String nian, @PathVariable("yue") String yue,
			@PathVariable("ri") String ri, @PathVariable("name") String name) {

		FileInputStream fis = null;
		response.setContentType("image/gif");
		try {
			OutputStream out = response.getOutputStream();
			File file = new File("D:\\MES_file\\" + nian + "\\" + yue + "\\" + ri + "\\" + name);
			fis = new FileInputStream(file);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 下载文件
	 *
	 * @param response
	 * @param nian
	 * @param yue
	 * @param ri
	 * @param name
	 */
	@OptionalLog(module = "OA", module2 = "我的申请", method= "下载文件")
	@RequestMapping("/downloadFile/{nian}/{yue}/{ri}/{name:.+}/{oldName:.+}")
	public void downloadFile(HttpServletResponse response, @PathVariable("nian") String nian,
			@PathVariable("yue") String yue, @PathVariable("ri") String ri, @PathVariable("name") String name,
			@PathVariable("oldName") String oldName) {

		try {
			String path = "D:\\MES_file\\" + nian + "\\" + yue + "\\" + ri + "\\" + name;

			// 读到流中
			InputStream inStream = new FileInputStream(path);// 文件的存放路径
			// 设置输出的格式
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.addHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(oldName, "UTF-8") + "\"");
			// 循环取出流中的数据
			byte[] b = new byte[100];
			int len;
			try {
				while ((len = inStream.read(b)) > 0)
					response.getOutputStream().write(b, 0, len);
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询审批记录表表格
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findApprovalRecordTable")
	public Rjson findApprovalRecordTable(HttpServletRequest request) {
		try {
			int approvalRecordId = EqualsUtil.integer(request, "approvalRecordId", "ID", true);

			JSONObject json = service.findApprovalRecordTable(approvalRecordId);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 撤销
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "我的申请", method= "撤销")
	@Transactional
	@RequestMapping("/revoke")
	public Rjson revoke(HttpServletRequest request) {
		try {

			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);

			service.revoke(listNo, userId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询现有的单据类型
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findType")
	public Rjson findType(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findType();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 加急
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/urgent")
	public Rjson urgent(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			service.urgent(listNo);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}

package com.skeqi.mes.controller.dzb.monitor;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.dzb.monitor.MonitorService;
import com.skeqi.mes.util.ToolUtils;

import com.skeqi.mes.util.aop.OptionalLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mon/api")
public class MonitorController {

	@Autowired
	private MonitorService service;
	@Value("${filepath.upload}")
	private String filepath_upload;


	/**
	 * 表信息
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveBgimg", method = RequestMethod.POST)
	public Object saveBgimg(HttpServletRequest request, String path) {
		JSONObject out = new JSONObject();
		try {
			service.saveBgimg(path);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			out.put("msg", e.getMessage());
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}
	//region 元素数据

	/**
	 * 单个对象类型数据
	 *
	 * @param eleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "objdata", method = RequestMethod.POST)
//    @OptionalLog(module="看板", module2="看板展示", method="对象数据")
	public Object objData(@RequestParam(value = "eleId", required = false) Integer eleId) throws Exception {
		JSONObject out = new JSONObject();
		try {
			if (eleId == null) {
				throw new NullPointerException();
			}
			out = service.objData(eleId);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
		}
		return out;
	}


	/**
	 * 集合对象类型数据
	 *
	 * @param eleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listObjdata", method = RequestMethod.POST)
//    @OptionalLog(module="看板", module2="看板展示", method="集合数据")
	public Object listObjData(@RequestParam(value = "eleId", required = false) Integer eleId) throws Exception {
		JSONObject out = new JSONObject();
		try {
			if (eleId == null) {
				throw new NullPointerException();
			}
			out = service.listObjData(eleId);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
		}
		return out;
	}

	/**
	 * 时间段对象类型数据
	 *
	 * @param eleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "timedata", method = RequestMethod.POST)
//    @OptionalLog(module="看板", module2="看板展示", method="时间段数据")
	public Object timeData(@RequestParam(value = "eleId", required = false) Integer eleId) throws Exception {
		JSONObject out = new JSONObject();
		try {
			if (eleId == null) {
				throw new NullPointerException();
			}
			out = service.timeData(eleId);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
		}
		return out;
	}
	//endregion

	//region 看板

	/**
	 * 获取看板json
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "onekanban", method = RequestMethod.POST)
	public Object onekanban(String kanban, int id, HttpServletRequest request) {
		JSONObject out = new JSONObject();
		try {
			out = service.onekanban(kanban, id);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	/**
	 * 获取看板列表
	 *
	 * @return
	 */
	@RequestMapping(value = "manykanban", method = RequestMethod.POST)
	public Object manykanban(String kanban, HttpServletRequest request) {
		JSONObject out = new JSONObject();
		try {
			out = service.manykanban(kanban);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	/**
	 * 是否有缩略图
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "haveImage", method = RequestMethod.POST)
	public Object haveImage(HttpServletRequest request, String kanban, int id) {
		JSONObject out = new JSONObject();
		try {
			out = service.haveImage(kanban, id);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	/**
	 * 更新看板
	 *
	 * @param name
	 * @param json
	 * @param imagesByte
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateKanban", method = RequestMethod.POST)
	@OptionalLog(module = "看板", module2 = "看板设置", method = "更新看板")
	public Object updateKanban(HttpServletRequest request,
							   @RequestParam(required = false) String name,
							   @RequestParam(required = false) String json,
							   @RequestParam(required = false) String imagesByte,
							   @RequestParam(required = false) String content,
							   String id, @RequestParam(value = "kanban") String kanbanTable) {
		JSONObject out = new JSONObject();
		try {
			Map kanban = new HashMap();
			kanban.put("name", name);
			kanban.put("content", content);
			kanban.put("json", json);
			kanban.put("imagesByte", imagesByte);
			kanban.put("id", id);
			kanban.put("kanban", kanbanTable);
			service.updateKanban(kanban);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	/**
	 * 添加看板
	 *
	 * @param name
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "saveKanban", method = RequestMethod.POST)
	@OptionalLog(module = "看板", module2 = "看板设置", method = "添加看板")
	public Object saveKanban(HttpServletRequest request,
							 @RequestParam(required = false) String name,
							 @RequestParam(required = false) String json,
							 @RequestParam(required = false) String content, @RequestParam(value = "kanban") String kanbanTable) {
		JSONObject out = new JSONObject();
		try {
			Map kanban = new HashMap();
			kanban.put("name", name);
			kanban.put("content", content);
			kanban.put("json", json);
			kanban.put("kanban", kanbanTable);
			out = service.saveKanban(kanban);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}


	@RequestMapping(value = "copyKanban", method = RequestMethod.POST)
	@OptionalLog(module = "看板", module2 = "看板设置", method = "复制看板")
	public Object copyKanban(HttpServletRequest request,
							 int id, @RequestParam(value = "kanban") String kanbanTable) {
		JSONObject out = new JSONObject();
		try {
			Map kanban = new HashMap();
			kanban.put("id", id);
			kanban.put("kanban", kanbanTable);
			out = service.copyKanban(kanban);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	/**
	 * 删除看板
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "removeKanban", method = RequestMethod.POST)
	@OptionalLog(module = "看板", module2 = "看板设置", method = "删除看板")
	public Object removeKanban(String kanban, int id, HttpServletRequest request) {
		JSONObject out = new JSONObject();
		try {
			service.removeKanban(kanban, id);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}
	//endregion

	//region 元素
	@RequestMapping(value = "dataObjAttr", method = RequestMethod.POST)
//    @OptionalLog(module="看板", module2="看板设置", method="元素属性")
	public Object dataObjAttr(int eleId, String eleName, HttpServletRequest request) {

		JSONObject out = new JSONObject();
		try {
			out = service.dataObjAttr(eleId, eleName);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	@RequestMapping(value = "listAttrs", method = RequestMethod.POST)
//    @OptionalLog(module="看板", module2="看板设置", method="查询对象属性集合")
	public Object listAttrs(int objId, HttpServletRequest request) {
		JSONObject out = new JSONObject();
		try {
			out = service.listAttrs(objId);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	@RequestMapping(value = "updateEleAttr", method = RequestMethod.POST)
	@OptionalLog(module = "看板", module2 = "看板设置", method = "更新元素数据属性")
	public Object updateEleAttr(int eleId, int[] attrs, HttpServletRequest request) {
		JSONObject out = new JSONObject();
		try {
			out = service.updateEleAttr(eleId, attrs);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	@RequestMapping(value = "listObjs", method = RequestMethod.POST)
//    @OptionalLog(module="看板", module2="看板设置", method="查询元素绑定对象")
	public Object listObjs(HttpServletRequest request, String eleName) {
		JSONObject out = new JSONObject();
		try {
			out = service.listObjs(eleName);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	@RequestMapping(value = "removeEleId", method = RequestMethod.POST)
	@OptionalLog(module = "看板", module2 = "看板设置", method = "删除元素数据")
	public Object removeEleId(int eleId, HttpServletRequest request) {
		JSONObject out = new JSONObject();
		try {
			service.removeEleId(eleId);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}
	//endregion

	//region 看板图片管理

	//用于接收文件
	@RequestMapping("/upload")
	public Object upload(MultipartFile photo) throws IOException {
		JSONObject out = new JSONObject();
		//判断用户是否上传了文件
		if (!photo.isEmpty()) {

			//获取文件的名称
			String fileName = photo.getOriginalFilename();
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			fileName = UUID.randomUUID().toString() + suffixName;
			//限制文件上传的类型
//            String contentType = photo.getContentType();
			//完成文件的上传
			File file = new File(filepath_upload + "/kanban", fileName);
			photo.transferTo(file);
			System.out.println("图片上传成功!");
			String path01 = "../kanban/" + fileName;
			System.out.println(path01);
//            mv.addObject("path", path01);
//            mv.setViewName(path01);
			out.put("code", 200);
			out.put("path", path01);
		} else {
			System.out.println("上传失败！");
			out.put("code", 201);
			out.put("error", "上传失败!");
		}
		return out;
	}

	@RequestMapping("/listBgImg")
//    @OptionalLog(module="看板", module2="看板设置", method="查询图片")
	public Object listBgImg(HttpServletRequest request) {
		JSONObject out = new JSONObject();
		try {
			out = service.listBgImg(request);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}

	@RequestMapping("/removeBgImg")
	@OptionalLog(module = "看板", module2 = "看板设置", method = "删除图片")
	public Object removeBgImg(@RequestParam("path") String path, HttpServletRequest request) {
		JSONObject out = new JSONObject();
		try {
			service.removeBgImg(path);
			out.put("code", 200);
		} catch (Exception e) {
			out.put("code", 201);
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return out;
	}
}

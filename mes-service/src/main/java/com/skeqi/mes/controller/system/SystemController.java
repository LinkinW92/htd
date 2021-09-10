package com.skeqi.mes.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.skeqi.mes.finals.SystemFinal;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.Version;

/**
 * @author yinp
 * @explain 系统接口
 * @date 2020-11-18
 */
@RestController
@RequestMapping("/api/system")
public class SystemController {

	@Value("${system.version}")
	private String version;
	/**
	 * @explain 获取版本号
	 * @return
	 */
	@RequestMapping("/getVersion")
	public Rjson getVersion(HttpServletRequest request) {
		try {
			return Rjson.success(version);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}

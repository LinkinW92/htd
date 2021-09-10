package com.skeqi.manage.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skeqi.common.config.SqConfig;
import com.skeqi.common.constant.Constants;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.utils.file.FileUploadUtils;
import com.skeqi.common.utils.file.FileUtils;
import com.skeqi.framecore.config.ServerConfig;

import cn.hutool.core.util.StrUtil;

/**
 * 通用请求处理
 *
 * @author skeqi
 */
@RestController
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Value("spring.image-url")
	private String imageUrl;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StrUtil.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = SqConfig.getDownloadPath() + fileName;
			File file = new File(filePath);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
			FileUtils.writeToStream(file, response.getOutputStream());
            if (delete)
            {
				FileUtils.del(file);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = SqConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl()+ fileName;
			Map<String,Object> ajax = new HashMap<>();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return AjaxResult.success(ajax);
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

	/**
	 * 通用上传请求
	 */
	@PostMapping("common/uploadByDir")
	public AjaxResult uploadFileByDir(MultipartFile file,String dir) throws Exception
	{
		try
		{
			// 上传文件路径
			String filePath = SqConfig.getUploadPath()+ File.separator +dir;
			// 上传并返回新文件名称
			String fileName = FileUploadUtils.upload(filePath, file);
			String url = imageUrl+ fileName;
			Map<String,Object> ajax = new HashMap<>();
			ajax.put("fileName", fileName);
			ajax.put("url", url);
			return AjaxResult.success(ajax);
		}
		catch (Exception e)
		{
			return AjaxResult.error(e.getMessage());
		}
	}

    /**
     * 本地资源通用下载
     */
    @GetMapping("common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StrUtil.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = SqConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StrUtil.subAfter(resource, Constants.RESOURCE_PREFIX,false);
            // 下载名称
            String downloadName = StrUtil.subAfter(downloadPath, "/",true);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			File file = new File(downloadPath);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeToStream(file, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
}

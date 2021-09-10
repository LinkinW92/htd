package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 文件管理
 *
 * @author yinp
 * @data 2021年5月21日
 *
 */
public interface OAFileDao {

	/**
	 * 查询
	 *
	 * @param path
	 * @return
	 */
	public List<JSONObject> list(@Param("path") String path);

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 查询相同路径下文件名字相同的conut
	 *
	 * @param path
	 * @param name
	 * @param id
	 * @return
	 */
	public int findFileCountName(@Param("path") String path, @Param("name") String name, @Param("id") Integer id);

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	public JSONObject findFileById(@Param("id") int id);

	/**
	 * 通过id删除文件
	 *
	 * @param name
	 * @return
	 */
	public int deleteFileById(@Param("id") int id);

	/**
	 * 通过路径删除文件
	 *
	 * @param path
	 * @return
	 */
	public int deleteFileByPath(@Param("path") String path);

	/**
	 * 通过路径跟文件名查询count数量
	 *
	 * @param path
	 * @param name
	 * @param id
	 * @return
	 */
	public int findFileCountByPathAndName(@Param("path") String path, @Param("name") String name,
			@Param("id") Integer id);

	/**
	 * 更新文件名称
	 *
	 * @param id
	 * @param name
	 * @return
	 */
	public int updateFileName(@Param("id") int id, @Param("name") String name);

	/**
	 * 更新文件路径
	 *
	 * @param path1
	 * @param path2
	 * @return
	 */
	public int updateFilePath(@Param("path1") String path1, @Param("path2") String path2);

	/**
	 * 修改文件类型
	 *
	 * @param id
	 * @param type
	 * @return
	 */
	public int updateFileType(@Param("id") int id, @Param("type") String type);

	/**
	 * 查询文件权限
	 *
	 * @return
	 */
	public List<JSONObject> findFileAuthorityUser(@Param("path") String path);

	/**
	 * 查询文件权限
	 *
	 * @return
	 */
	public List<JSONObject> findFileAuthorityRole(@Param("path") String path);

	/**
	 * 新增文件权限
	 *
	 * @param json
	 * @return
	 */
	public int addFileAuthority(JSONObject json);

	/**
	 * 删除文件权限
	 *
	 * @param json
	 * @return
	 */
	public int deleteFileAuthority(@Param("path") String path);

	/**
	 * 通过路径查询那些用户有这个权限
	 *
	 * @param path
	 * @return
	 */
	public List<JSONObject> findFileAuthorityByPath(@Param("path") String path);

	/**
	 * 查询用户权限
	 *
	 * @param userId
	 * @return
	 */
	public List<JSONObject> findAuthorityByUserId(@Param("userId") int userId);

	/**
	 * 查询权限清单
	 * @param path
	 * @return
	 */
	public List<JSONObject> findPermissionList(@Param("path")String path);

	/**
	 * 通过路径查询路径前部分权限
	 * @param path
	 * @return
	 */
	public List<JSONObject> findFileAuthorityByLikePath(@Param("path")String path);

	/**
	 * 更新权限清单路径
	 * @param json
	 * @return
	 */
	public int updateFileAuthorityPath(JSONObject json);

}

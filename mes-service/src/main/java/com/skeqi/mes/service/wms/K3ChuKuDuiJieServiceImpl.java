package com.skeqi.mes.service.wms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.K3ChuKuDuiJieDao;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsProject;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.pojo.wms.K3ExportNotifydetall;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;
import com.skeqi.mes.util.jdbc.SqlServerJDBC;

@Service
public class K3ChuKuDuiJieServiceImpl implements K3ChuKuDuiJieService {

	Connection connection = null;
	String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
	String dbURL = "jdbc:sqlserver://192.168.7.8:1433;DatabaseName=DataCenter";// 数据源
																				// //
																				// ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
	String Name = "sa";
	String Pwd = "kingdee";
	Statement stmt = null;
	ResultSet rs = null;

	@Autowired
	K3ChuKuDuiJieDao dao;

	@Override
	public boolean getK3ExportNotifydetall(int userId) throws Exception {
		try {
			List<K3ExportNotifydetall> list = new ArrayList<K3ExportNotifydetall>();

			connection = SqlServerJDBC.getConnection();
			stmt = connection.createStatement();
			// 关闭事务自动提交功能
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
				SqlServerJDBC.close(connection, stmt);
				throw new Exception("关闭事务自动提交出错了");
			}

			String sql = "select * from K3_EXPORT_NOTIFYDETAIL where EXPORT_DOWNLOAD_FLAG = 11";

			try {

				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					K3ExportNotifydetall dx = new K3ExportNotifydetall();
					dx.setExportId(rs.getString("EXPORT_ACCT_NO"));
					dx.setExportId(rs.getString("EXPORT_ID"));
					dx.setBomId(rs.getString("EXPORT_GOODS_CODE"));
					dx.setExportLotNo(rs.getString("EXPORT_LOT_NO"));
					dx.setExportPackQuantity(rs.getInt("EXPORT_PACK_QUANTITY"));
					// dx.setExportWaerhouseId(rs.getInt("EXPORT_WAREHOUSE_ID"));
					dx.setExportWaerhouseId(1);
					dx.setMaterialName(rs.getString("IMPORT_MATERIAL_NAME"));
					dx.setMaterialGoodsModel(rs.getString("export_goods_model"));
					dx.setProjectName(rs.getString("export_project_no"));
					list.add(dx);
					sql = "update K3_EXPORT_NOTIFYDETAIL set EXPORT_DOWNLOAD_FLAG = 1 " + "where EXPORT_ACCT_NO = '"
							+ rs.getString("EXPORT_ACCT_NO") + "'" + "and EXPORT_ID = '" + rs.getString("EXPORT_ID")
							+ "'" + "and EXPORT_LIST_NO = '" + rs.getString("EXPORT_LIST_NO") + "'";
					stmt = connection.createStatement();
					stmt.executeUpdate(sql);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				SqlServerJDBC.close(connection, stmt);
				throw new Exception("对K3执行的sql语句有误");
			}

			// 查询K3下发的出库数据
			if (list.size() <= 0) {
				// 没有K3新下发的数据
				SqlServerJDBC.close(connection, stmt);
				throw new Exception("K3没有新下发数据");
			}

			// 生成单号
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
			// 单号
			String listNo = "K3CK" + s.format(new Date());

			List<CWmsStorageDetailT> stoList = new ArrayList<CWmsStorageDetailT>();

			for (K3ExportNotifydetall k3Dx : list) {

				// 通过物料名称查询物料信息
				CMesJlMaterialT material = dao.findMtaerialName(k3Dx.getMaterialName() + k3Dx.getMaterialGoodsModel());
				if (material == null) {
					continue;
//					throw new Exception("物料不存在");
				}

				// 通过项目名称查询项目
				CWmsProject project = dao.findProject(k3Dx.getProjectName());
				if (project == null) {
					continue;
//					throw new Exception("项目不存在");
				}

				List<CWmsMaterialNumberT> numberList = dao.findMaterialNumber(k3Dx.getExportWaerhouseId(),
						project.getId(), material.getId());

				if (numberList == null || numberList.size() <= 0) {
					SqlServerJDBC.close(connection, stmt);
					continue;
//					throw new Exception("查询物料库存出错了");
				}

				k3Dx.setMaterialId(material.getId());
				k3Dx.setProjectId(project.getId());
				// 新增K3出库记录表
				Integer res = dao.addK3ExportNotifydetall(k3Dx);
				if (res != 1) {
					SqlServerJDBC.close(connection, stmt);
					continue;
//					throw new Exception("新增K3出库记录时出错了");
				}
				for (int i = 0; i < numberList.size(); i++) {

					if (k3Dx.getExportPackQuantity() == 0) {
						break;
					}
					// 计算出当前数据有多少可以分配库存数量
					Integer number = numberList.get(i).getMaterialNumber() - numberList.get(i).getLmminentRelease()
							- numberList.get(i).getFreezingNumber() - numberList.get(i).getReservedNumber();

					// 扣除当前对象的物料
					if (number > k3Dx.getExportPackQuantity()) {
						// 表示仅这一条记录就可以满足出库的物料数量了

						// 修改即将出库的数量
						numberList.get(i).setLmminentRelease(
								numberList.get(i).getLmminentRelease() + k3Dx.getExportPackQuantity());
						res = dao.updateMaterialNumber(numberList.get(i).getId(),
								numberList.get(i).getLmminentRelease());
						if (res != 1) {
							SqlServerJDBC.close(connection, stmt);
							continue;
//							throw new Exception("更新即将出库数量出错了");
						}

						CWmsStorageDetailT sddx = new CWmsStorageDetailT();
						sddx.setMaterialId(material.getId());
						sddx.setMaterialNumber(k3Dx.getExportPackQuantity());
						sddx.setMaterialCode(numberList.get(i).getMaterialCode());
						sddx.setWarehouseId(numberList.get(i).getWareHouseId());
						sddx.setAreaId(numberList.get(i).getAreaId());
						sddx.setReservoirAreaId(numberList.get(i).getReservoirareaId());
						sddx.setLocationId(numberList.get(i).getLocationId());
						sddx.setListNo(listNo);
						sddx.setProjectId(numberList.get(i).getProjectId());
						sddx.setTray(numberList.get(i).getTray());
						sddx.setYnShift(0);
						sddx.setIssueOrReceipt(0);
						sddx.setStationId(1);
						sddx.setBarCode(numberList.get(i).getBarCode());
						sddx.setMaterialNumberId(numberList.get(i).getId());
						stoList.add(sddx);

						// 新增临时库存详情表记录
						res = dao.addStorageDetail(sddx);
						if (res != 1) {
							SqlServerJDBC.close(connection, stmt);
							continue;
//							throw new Exception("新增库存详情记录出错了");
						}

						break;

					} else if (number > 0) {
						// 一条记录不够 就继续第二条库存
						k3Dx.setExportPackQuantity(k3Dx.getExportPackQuantity() - number);

						// 修改即将出库的数量
						numberList.get(i).setLmminentRelease(numberList.get(i).getLmminentRelease() + number);
						res = dao.updateMaterialNumber(numberList.get(i).getId(),
								numberList.get(i).getLmminentRelease());
						if (res != 1) {
							SqlServerJDBC.close(connection, stmt);
							continue;
//							throw new Exception("修改即将出库数量出错了");
						}

						CWmsStorageDetailT sddx = new CWmsStorageDetailT();
						sddx.setMaterialId(material.getId());
						sddx.setMaterialNumber(number);
						sddx.setMaterialCode(numberList.get(i).getMaterialCode());
						sddx.setWarehouseId(numberList.get(i).getWareHouseId());
						sddx.setAreaId(numberList.get(i).getAreaId());
						sddx.setReservoirAreaId(numberList.get(i).getReservoirareaId());
						sddx.setLocationId(numberList.get(i).getLocationId());
						sddx.setListNo(listNo);
						sddx.setProjectId(numberList.get(i).getProjectId());
						sddx.setTray(numberList.get(i).getTray());
						sddx.setYnShift(0);
						sddx.setIssueOrReceipt(0);
						sddx.setStationId(1);
						stoList.add(sddx);

						// 新增库存详情表记录
						res = dao.addStorageDetail(sddx);
						if (res != 1) {
							SqlServerJDBC.close(connection, stmt);
							continue;
//							throw new Exception("新增库存详情记录出错了");
						}

					} else if (i == (numberList.size() - 1)) {
						continue;
//						throw new Exception("物料库存不足");
					}

				}

			}

			// 生成临时出库队列
			List<CWmsOutTaskqueueT> out = new ArrayList<CWmsOutTaskqueueT>();
			// 遍历库存详情
			for (int i = 0; i < stoList.size(); i++) {
				CWmsOutTaskqueueT cWmsOutTaskqueueT = new CWmsOutTaskqueueT();
				if (i == 0) {
					cWmsOutTaskqueueT.setListNo(stoList.get(i).getListNo());
					cWmsOutTaskqueueT.setTray(stoList.get(i).getTray());
					cWmsOutTaskqueueT.setStationId(stoList.get(i).getStationId());
					cWmsOutTaskqueueT.setLocationId(stoList.get(i).getLocationId());
					out.add(cWmsOutTaskqueueT);
				} else {
					// 遍历已经生成的出库队列
					for (int j = 0; j < out.size(); j++) {
						// 判断有不有同库位的出库
						if (out.get(j).getLocationId().equals(stoList.get(i).getLocationId())) {
							// 如果发现有库位的出库队列就直接进入下一次遍历
							continue;
						} else {
							if (j == (out.size() - 1)) {
								// 匹配到了最后还是没有匹配到有出库队列就加入一条出库队列
								cWmsOutTaskqueueT.setListNo(stoList.get(i).getListNo());
								cWmsOutTaskqueueT.setTray(stoList.get(i).getTray());
								cWmsOutTaskqueueT.setStationId(stoList.get(i).getStationId());
								cWmsOutTaskqueueT.setLocationId(stoList.get(i).getLocationId());
								out.add(cWmsOutTaskqueueT);
							}
						}
					}
				}
			}

			// 新增出库队列到数据库
			for (CWmsOutTaskqueueT outdx : out) {
				Integer res = dao.addOutTaskqueue(outdx);
				if (res != 1) {
					SqlServerJDBC.close(connection, stmt);
					continue;
//					throw new Exception("新增出库队列出错了");
				}
			}

			// 获取user
			CMesUserT user = dao.findUserById(userId);
			if (user == null) {
				throw new Exception("用户已失效");
			}
			// 审批流程类型id
			Integer typeId = 5;
			ProcessApproval padx = new ProcessApproval();
			padx.setDeptId(Integer.parseInt(user.getDepartment()));
			padx.setRoleId(user.getRoleId());
			padx.setTypeId(typeId);
			// 查询审批流程
			List<ProcessApproval> paList = dao.findProcessApproval(padx);
			if (paList.size() == 0) {
				throw new Exception("您不能生成执行出库操作，可能是没有配置审批流程");
			}
			padx = paList.get(0);

			// 查询流程详情
			Map<String, Object> padMap = new HashMap<String, Object>();
			padMap.put("processId", padx.getId());
			List<ProcessApprovalDetail> padList = dao.findProcessApprovalDetailList(padMap);
			if (padList.size() == 0) {
				throw new Exception("查询审批流程详情出错了");
			}

			// 保存审批表
			CWmsApprovalT approval = new CWmsApprovalT();
			approval.setListNo(listNo);// 单号
			approval.setProcessId(padx.getId());// 流程主表id
			approval.setUserId(user.getId());// 申请用户id
			approval.setState(2);// 审批状态
			// 新增审批表记录
			Integer res = dao.addApproval(approval);
			if (res <= 0) {
				throw new Exception("新增审批记录时出错了");
			}

			// 提交事务
			connection.commit();

			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			SqlServerJDBC.close(connection, stmt);
		}

	}

	// public static void main(String[] args) {
	// getJdbc();
	// }
	// public static List<K3ExportNotifydetall> getJdbc() {
	//
	//
	// List<K3ExportNotifydetall> list = new ArrayList<K3ExportNotifydetall>();
	//
	// try {
	// Class.forName(driverName);
	// connection = DriverManager.getConnection(dbURL, Name, Pwd);
	// System.out.println("连接数据库成功");
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.out.println("连接失败");
	// }
	//
	// try {
	// stmt = connection.createStatement();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// // 关闭事务自动提交功能
	// try {
	// connection.setAutoCommit(false);
	// } catch (SQLException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	//
	// String sql = "select * from K3_EXPORT_NOTIFYDETAIL where
	// EXPORT_DOWNLOAD_FLAG = 0";
	//
	// try {
	//
	// rs = stmt.executeQuery(sql);
	// while (rs.next()) {
	// K3ExportNotifydetall dx = new K3ExportNotifydetall();
	// dx.setExportId(rs.getString("EXPORT_ACCT_NO"));
	// dx.setExportId(rs.getString("EXPORT_ID"));
	// dx.setBomId(rs.getString("EXPORT_GOODS_CODE"));
	// dx.setExportLotNo(rs.getString("EXPORT_LOT_NO"));
	// dx.setExportPackQuantity(rs.getInt("EXPORT_PACK_QUANTITY"));
	// dx.setExportWaerhouseId(rs.getInt("EXPORT_WAREHOUSE_ID"));
	// dx.setMaterialName(rs.getString("IMPORT_MATERIAL_NAME"));
	// dx.setMaterialGoodsModel(rs.getString("export_goods_model"));
	// dx.setProjectName(rs.getString("export_project_no"));
	// list.add(dx);
	// sql = "update K3_EXPORT_NOTIFYDETAIL set EXPORT_DOWNLOAD_FLAG = 1 "
	// + "where EXPORT_ACCT_NO = '"+rs.getString("EXPORT_ACCT_NO")+"'"
	// + "and EXPORT_ID = '"+rs.getString("EXPORT_ID")+"'"
	// + "and EXPORT_LIST_NO = '"+rs.getString("EXPORT_LIST_NO")+"'";
	// System.out.println(sql);
	// stmt = connection.createStatement();
	// stmt.executeUpdate(sql);
	// System.out.println(dx);
	// }
	// // 提交事务
	// connection.commit();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } finally {
	// // 关闭
	// try {
	// stmt.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// try {
	// connection.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return list;
	// }

	@Override
	public List<CWmsOutTaskqueueT> findOutTaskqueue() {
		return dao.findOutTaskqueue();
	}

	@Override
	public List<CWmsStorageDetailT> findStorageDetailList(Map<String, Object> map) {
		return dao.findStorageDetailList(map);
	}

	@Override
	public List<CMesStationT> findStationList() {
		// TODO Auto-generated method stub
		return dao.findStationList();
	}

	@Override
	public boolean updateOutTaskqueueStationId(Integer id, Integer stationId) {
		Integer result = dao.updateOutTaskqueueStationId(id, stationId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public CWmsOutTaskqueueT findOutTaskqueueById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findOutTaskqueueById(id);
	}

	/**
	 * 异常处理
	 */
	@Override
	public boolean exceptionHandle(String exportId) throws Exception {
		connection = SqlServerJDBC.getConnection();
		stmt = connection.createStatement();
		// 关闭事务自动提交功能
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
			SqlServerJDBC.close(connection, stmt);
			throw new Exception("关闭事务自动提交出错了");
		}

		String sql = "DELETE FROM K3_EXPORT_NOTIFYDETAIL WHERE EXPORT_ID = '" + exportId + "'";

		try {
			Integer res = stmt.executeUpdate(sql);
			if (res <= 0) {
				throw new Exception("没有可执行的单据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			SqlServerJDBC.close(connection, stmt);
			if (!e.getMessage().equals("没有可执行的单据")) {
				throw new Exception("SQL执行有误");
			} else {
				throw new Exception(e.getMessage());
			}
		}

		try {
			// 提交事务
			connection.commit();
		} catch (SQLException e) {
			throw new Exception("提交事务失败");
		}

		SqlServerJDBC.close(connection, stmt);
		return true;
	}

	@Override
	public boolean deleteK3Number(Integer id) throws Exception {

		// 查询选中要删除的数据
		CWmsOutTaskqueueT outDx = dao.findOutTaskqueueById(id);
		if (outDx == null || outDx.getLocationId() == null || outDx.getLocationId().equals("")
				|| outDx.getListNo() == null || outDx.getListNo().equals("")) {
			throw new Exception("查询选中要删除的数据出错了");
		}

		// 出库的库位
		Integer locationId = outDx.getLocationId();
		// 单据号
		String listNo = outDx.getListNo();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("locationId", locationId);
		map.put("listNo", listNo);

		// 查询库存详情
		List<CWmsStorageDetailT> sdDxList = dao.findStorageDetailList(map);
		if (sdDxList == null || sdDxList.size() == 0) {
			throw new Exception("查询库存详情数据出错了");
		}

		// 循环要出库的物料
		for (CWmsStorageDetailT dx : sdDxList) {
			// 查询要出库的物料库存
			CWmsMaterialNumberT number = dao.findMaterialNumberByLocationIdAndMaterialIdAndProjectId(dx.getLocationId(),
					dx.getMaterialId(), dx.getProjectId());
			// 计算出减去的即将出库数量
			Integer num = number.getLmminentRelease() - dx.getMaterialNumber();
			// 更新物料库存即将出库数量
			Integer res = dao.updateMaterialNumber(number.getId(), num);
			if (res <= 0) {
				throw new Exception("更新物料库存即将出库数量出错了");
			}
			// 删除永久库存详情数据
			res = dao.deleteStorageDetail(dx.getId());
			if (res <= 0) {
				throw new Exception("删除临时库存详情数据出错了");
			}
		}

		// 删除出库队列
		Integer res = dao.deleteOutTaskqueue(id);
		if (res <= 0) {
			throw new Exception("删除出库队列数据出错了");
		}

		return true;
	};

}

package com.skeqi.mes.pojo;

import java.util.Date;

import org.neo4j.cypher.internal.compiler.v2_2.ast.False;
import org.neo4j.cypher.internal.compiler.v2_2.ast.True;

import io.swagger.annotations.ApiModelProperty;

/*程序注册表*/
public class RegisterT {
	private int id;//
	@ApiModelProperty(value="时间",required=false)
	private Date dt;//时间
	@ApiModelProperty(value="IP地址",required=true)
	private String ip;//IP地址
	@ApiModelProperty(value="客户端MAC地址",required=false)
	private String mac;//客户端MAC地址
	@ApiModelProperty(value="程序类型",required=true)
	private String hostname;//主机名称
	@ApiModelProperty(value="所属产线ID",required=false)
	private int line_id;//

	private int station_id;//所属工位
	@ApiModelProperty(value="所属工位id",required=true)
	private int stationId;
	@ApiModelProperty(value="工位名称",required=false)
	private String stationName;
	@ApiModelProperty(value="工位名字",required=false)
	private String lineName;
	@ApiModelProperty(value="所属产线ID",required=true)
	private String lineId;

	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public int getLine_id() {
		return line_id;
	}
	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}
	public int getStation_id() {
		return station_id;
	}
	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	@Override
	public String toString() {
		return "RegisterT [id=" + id + ", dt=" + dt + ", ip=" + ip + ", mac=" + mac + ", hostname=" + hostname
				+ ", line_id=" + line_id + ", station_id=" + station_id + ", stationName=" + stationName + ", lineName="
				+ lineName + "]";
	}

}

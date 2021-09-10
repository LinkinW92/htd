package com.skeqi.mes.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class UDPUtils {

	public static void sendUDP(String data, String ip, int post) throws IOException {
		//建立udp的服务
				DatagramSocket scoket = new DatagramSocket();
				//创建数据包
				DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName(ip), post);
				//发送数据包
				scoket.send(packet);
				//关闭资源
				scoket.close();
	}

	public static void sendUDPBatch(List<String> datas, String ip, int post) throws IOException {
		//建立udp的服务
		DatagramSocket scoket = new DatagramSocket();

		for (String data : datas) {
			//创建数据包
			DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName(ip), post);
			//发送数据包
			scoket.send(packet);
		}
		//关闭资源
		scoket.close();
	}
}

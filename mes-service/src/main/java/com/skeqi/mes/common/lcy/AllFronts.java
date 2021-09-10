package com.skeqi.mes.common.lcy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AllFronts {

	public static void stopAll(String name) throws IOException{
		//建立udp的服务
		DatagramSocket scoket = new DatagramSocket();
		//准备数据
		String data = "SKQ;STOP;"+name+";";
		//创建数据包
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName("192.168.2.255"), 4000);
		//发送数据包
		scoket.send(packet);
		//关闭资源
		scoket.close();
	}

	public static void startAll(String name) throws IOException{
		//建立udp的服务
		DatagramSocket scoket = new DatagramSocket();
		//准备数据
		String data = "SKQ;RESET;"+name+";";
		//创建数据包
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getByName("192.168.2.255"), 4000);
		//发送数据包
		scoket.send(packet);
		//关闭资源
		scoket.close();
	}
}

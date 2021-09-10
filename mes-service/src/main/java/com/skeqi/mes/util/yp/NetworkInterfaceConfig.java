package com.skeqi.mes.util.yp;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class NetworkInterfaceConfig {

	/**
	 * 获取当前机器端口号
	 *
	 * @throws MalformedObjectNameException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws AttributeNotFoundException
	 * @throws InstanceNotFoundException
	 */
	public static String getLocalPort() throws Exception {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName("*:type=Connector,*"), null);
		if (objectNames == null || objectNames.size() <= 0) {
			throw new IllegalStateException("Cannot get the names of MBeans controlled by the MBean server.");
		}
		for (ObjectName objectName : objectNames) {
			String protocol = String.valueOf(mBeanServer.getAttribute(objectName, "protocol"));
			String port = String.valueOf(mBeanServer.getAttribute(objectName, "port"));
			// windows下属性名称为HTTP/1.1, linux下为org.apache.coyote.http11.Http11NioProtocol
			if (protocol.equals("HTTP/1.1") || protocol.equals("org.apache.coyote.http11.Http11NioProtocol")) {
				return port;
			}
		}
		throw new IllegalStateException("Failed to get the HTTP port of the current server");
	}

	/**
	 * 获取当前机器的IP
	 *
	 * @throws UnknownHostException
	 */
	public static String getLocalIP() throws Exception {
		InetAddress addr = InetAddress.getLocalHost();
		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		return ipAddrStr;
	}

	// 获取所有网卡的MAC地址
	public static List<String> getAllMac() {
		List<String> list = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();// 返回所有网络接口的一个枚举实例
			while (e.hasMoreElements()) {
				NetworkInterface network = e.nextElement();// 获得当前网络接口
				if (network != null) {
					if (network.getHardwareAddress() != null) {
						// 获得MAC地址
						// 结果是一个byte数组，每项是一个byte，我们需要通过parseByte方法转换成常见的十六进制表示
						byte[] addres = network.getHardwareAddress();
						StringBuffer sb = new StringBuffer();
						if (addres != null && addres.length > 1) {
							sb.append(parseByte(addres[0])).append(":").append(parseByte(addres[1])).append(":")
									.append(parseByte(addres[2])).append(":").append(parseByte(addres[3])).append(":")
									.append(parseByte(addres[4])).append(":").append(parseByte(addres[5]));
							list.add(sb.toString());
						}
					}
				} else {
					System.out.println("获取MAC地址发生异常");
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 格式化二进制
	private static String parseByte(byte b) {
		int intValue = 0;
		if (b >= 0) {
			intValue = b;
		} else {
			intValue = 256 + b;
		}

		String s = "00" + Integer.toHexString(intValue);

		return s.substring(s.length() - 2);
	}
}

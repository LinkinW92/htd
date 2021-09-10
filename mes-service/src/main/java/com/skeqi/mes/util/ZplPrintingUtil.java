package com.skeqi.mes.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.standard.PrinterName;

public class ZplPrintingUtil {

	private String printerURI = null;// 打印机完整路径
	private PrintService printService = null;// 打印机服务
	private byte[] dotFont;
	private String begin = "^XA"; // 标签格式以^XA开始
	private String end = "^XZ"; // 标签格式以^XZ结束
	private String content = "";

	public static boolean printing(String barCode) {
		ZplPrintingUtil p = new ZplPrintingUtil("ZDesigner GK888t (EPL)");
		// 1.打印单个条码
		String bar0 = barCode;// 条码内容

		// 开始标签格式
		String a = "^XA";
		// 打印的原点位置
		String a1 = "^LH0,0";
		// 文本开始位置
		String a2 = "^F0203,203";
		// 字体类型与大小
		String a3 = "^ADN,30,30";
		// 条码线条的粗细
		String a4 = "^BY2.0,3.0";
		// 二维码的长宽比
		String a5 = "^B7N,5,3,,,N";
		// 条形码的高度
		String a6 = "^BCN,120,Y,N,N,A";

		String bar0Zpl = "^XA^LH10,120^FO80,30^ADN,30,18^BY2.5,1.8^BCN,120,Y,N,N,A^FD${data}^FS^XZ";// 条码样式模板
		p.setBarcode(bar0, bar0Zpl);
		String zpl = p.getZpl();
		System.out.println(zpl);
		boolean result1 = p.print(zpl);// 打印

		p.resetZpl();// 注意要清除上次的打印信息
		// 2.打印中、英、数字、条码混合
		// 左边的条码
		String bar1 = "07";
		p.setChar(bar1, 190, 130, 60, 60);
		String bar1Zpl = "^FO100,200^BY8,3.0,240^BCR,,N,N,N^FD${data}^FS";// 条码样式模板
		p.setBarcode(bar1, bar1Zpl);
		return result1;

	}

	public static void main(String[] args) {
		ZplPrintingUtil p = new ZplPrintingUtil("ZDesigner GK888t (EPL)");
		// 1.打印单个条码
		String bar0 = "SKQ20200516_.0001";// 条码内容

		// 开始标签格式
		String a = "^XA";
		// 打印的原点位置
		String a1 = "^LH0,0";
		// 文本开始位置
		String a2 = "^F0203,203";
		// 字体类型与大小
		String a3 = "^ADN,30,30";
		// 条码线条的粗细
		String a4 = "^BY2.0,3.0";
		// 二维码的长宽比
		String a5 = "^B7N,5,3,,,N";
		// 条形码的高度
		String a6 = "^BCN,120,Y,N,N,A";

		String bar0Zpl = "^XA^LH10,120^FO80,30^ADN,30,18^BY2.5,1.8^BCN,120,Y,N,N,A^FD${data}^FS^XZ";// 条码样式模板
		p.setBarcode(bar0, bar0Zpl);
		String zpl = p.getZpl();
		System.out.println(zpl);
		boolean result1 = p.print(zpl);// 打印

		p.resetZpl();// 注意要清除上次的打印信息
		// 2.打印中、英、数字、条码混合
		// 左边的条码
		String bar1 = "07";
		p.setChar(bar1, 190, 130, 60, 60);
		String bar1Zpl = "^FO100,200^BY8,3.0,240^BCR,,N,N,N^FD${data}^FS";// 条码样式模板
		p.setBarcode(bar1, bar1Zpl);

	}

	/**
	 * 构造方法
	 *
	 * @param printerURI
	 *            打印机路径
	 */
	public ZplPrintingUtil(String printerURI) {
		this.printerURI = printerURI;
		// 加载字体
		File file = new File("C://ts24.lib");
		if (file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				dotFont = new byte[fis.available()];
				fis.read(dotFont);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("C://ts24.lib文件不存在");
		}
		// 初始化打印机
		PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
		if (services != null && services.length > 0) {
			for (PrintService service : services) {
				if (printerURI.equals(service.getName())) {
					printService = service;
					break;
				}
			}
		}
		if (printService == null) {
			System.out.println("没有找到打印机：[" + printerURI + "]");
			// 循环出所有的打印机
			if (services != null && services.length > 0) {
				System.out.println("可用的打印机列表：");
				for (PrintService service : services) {
					System.out.println("[" + service.getName() + "]");
				}
			}
		} else {
			System.out.println("找到打印机：[" + printerURI + "]");
			System.out.println("打印机名称：[" + printService.getAttribute(PrinterName.class).getValue() + "]");
		}
	}

	/**
	 * 设置条形码
	 *
	 * @param barcode
	 *            条码字符
	 * @param zpl
	 *            条码样式模板
	 */
	public void setBarcode(String barcode, String zpl) {
		content += zpl.replace("${data}", barcode);
	}

	/**
	 * 中文字符、英文字符(包含数字)混合
	 *
	 * @param str
	 *            中文、英文
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param eh
	 *            英文字体高度height
	 * @param ew
	 *            英文字体宽度width
	 * @param es
	 *            英文字体间距spacing
	 * @param mx
	 *            中文x轴字体图形放大倍率。范围1-10，默认1
	 * @param my
	 *            中文y轴字体图形放大倍率。范围1-10，默认1
	 * @param ms
	 *            中文字体间距。24是个比较合适的值。
	 */
	public void setText(String str, int x, int y, int eh, int ew, int es, int mx, int my, int ms) {
		byte[] ch = str2bytes(str);
		for (int off = 0; off < ch.length;) {
			if (((int) ch[off] & 0x00ff) >= 0xA0) {
				int qcode = ch[off] & 0xff;
				int wcode = ch[off + 1] & 0xff;
				content += String.format("^FO%d,%d^XG0000%01X%01X,%d,%d^FS\n", x, y, qcode, wcode, mx, my);
				begin += String.format("~DG0000%02X%02X,00072,003,\n", qcode, wcode);
				qcode = (qcode + 128 - 32) & 0x00ff;
				wcode = (wcode + 128 - 32) & 0x00ff;
				int offset = ((int) qcode - 16) * 94 * 72 + ((int) wcode - 1) * 72;
				for (int j = 0; j < 72; j += 3) {
					qcode = (int) dotFont[j + offset] & 0x00ff;
					wcode = (int) dotFont[j + offset + 1] & 0x00ff;
					int qcode1 = (int) dotFont[j + offset + 2] & 0x00ff;
					begin += String.format("%02X%02X%02X\n", qcode, wcode, qcode1);
				}
				x = x + ms * mx;
				off = off + 2;
			} else if (((int) ch[off] & 0x00FF) < 0xA0) {
				setChar(String.format("%c", ch[off]), x, y, eh, ew);
				x = x + es;
				off++;
			}
		}
	}

	/**
	 * 英文字符串(包含数字)
	 *
	 * @param str
	 *            英文字符串
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param h
	 *            高度
	 * @param w
	 *            宽度
	 */
	public void setChar(String str, int x, int y, int h, int w) {
		content += "^FO" + x + "," + y + "^A0," + h + "," + w + "^FD" + str + "^FS";
	}

	/**
	 * 英文字符(包含数字)顺时针旋转90度
	 *
	 * @param str
	 *            英文字符串
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param h
	 *            高度
	 * @param w
	 *            宽度
	 */
	public void setCharR(String str, int x, int y, int h, int w) {
		content += "^FO" + x + "," + y + "^A0R," + h + "," + w + "^FD" + str + "^FS";
	}

	/**
	 * 获取完整的ZPL
	 *
	 * @return
	 */
	public String getZpl() {
		return begin + content + end;
	}

	/**
	 * 重置ZPL指令，当需要打印多张纸的时候需要调用。
	 */
	public void resetZpl() {
		begin = "^XA";
		end = "^XZ";
		content = "";
	}

	/**
	 * 打印
	 *
	 * @param zpl
	 *            完整的ZPL
	 */
	public boolean print(String zpl) {
		if (printService == null) {
			System.out.println("打印出错：没有找到打印机：[" + printerURI + "]");
			return false;
		}
		DocPrintJob job = printService.createPrintJob();
		byte[] by = zpl.getBytes();
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		Doc doc = new SimpleDoc(by, flavor, null);
		try {
			job.print(doc, null);
			System.out.println("已打印");
			return true;
		} catch (PrintException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 字符串转byte[]
	 *
	 * @param s
	 * @return
	 */
	private byte[] str2bytes(String s) {
		if (null == s || "".equals(s)) {
			return null;
		}
		byte[] abytes = null;
		try {
			abytes = s.getBytes("gb2312");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return abytes;
	}

}

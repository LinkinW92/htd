package com.skeqi.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 非空判断工具类
 * 
 * @author YinP
 *
 */
public class EqualsUtil {

	/**
	 * String 非空判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean StringEqualsNull(String str) {
		if (str == null || str == "") {
			return false;
		} else {
			return true;
		}
	}

	public static Integer pageNumber(HttpServletRequest request) {
		Integer pageNumber = null;
		try {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			if (pageNumber <= 0) {
				pageNumber = 1;
			}
			return pageNumber;
		} catch (Exception e) {
			return 1;
		}
	}

	public static Integer pageNum(HttpServletRequest request) {
		Integer pageNumber = null;
		try {
			pageNumber = Integer.parseInt(request.getParameter("pageNum"));
			if (pageNumber <= 0) {
				pageNumber = 1;
			}
			return pageNumber;
		} catch (Exception e) {
			return 1;
		}
	}

	public static Integer pageSize(HttpServletRequest request) {
		Integer pageSize = null;
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			if (pageSize <= 0) {
				pageSize = 1;
			}
			return pageSize;
		} catch (Exception e) {
			return 10;
		}
	}

	/**
	 * 
	 * @param request
	 * @param parameterName  参数名称
	 * @param parameterNotes 参数注释
	 * @param boo            是否必填
	 * @return
	 * @throws Exception
	 */
	public static Integer integer(HttpServletRequest request, String parameterName, String parameterNotes, boolean boo)
			throws Exception {
		Integer integer = null;
		try {
			try {
				integer = Integer.parseInt(request.getParameter(parameterName));
				if (boo && integer == null) {
					throw new NullPointerException();
				}
				return integer;
			} catch (NullPointerException e) {
				throw new Exception("'" + parameterNotes + "'不能为空");
			} catch (NumberFormatException e) {
				throw new Exception("'" + parameterNotes + "'参数类型有误,应该是数字");
			}
		} catch (Exception e) {
			if (boo) {
				throw new Exception(e.getMessage());
			} else {
				return null;
			}
		}
	}

	/**
	 * 
	 * @param request
	 * @param parameterName  参数名称
	 * @param parameterNotes 参数注释
	 * @param boo            是否必填
	 * @return
	 * @throws Exception
	 */
	public static String string(HttpServletRequest request, String parameterName, String parameterNotes, boolean boo)
			throws Exception {
		String string = null;
		try {
			try {
//				if (request.getMethod().equals("GET")) {
//					string = new String(request.getParameter(parameterName).getBytes("ISO-8859-1"), "UTF-8");
//				} else {
//					request.setCharacterEncoding("utf-8");
					string = request.getParameter(parameterName);
//				}
				string = string.trim();
				if (boo) {
					if (string == null || "".equals(string)) {
						throw new NullPointerException();
					}
				}
				return string;
			} catch (NullPointerException e) {
				throw new Exception("'" + parameterNotes + "'不能为空");
			}
		} catch (Exception e) {
			if (boo) {
				throw new Exception(e.getMessage());
			} else {
				return null;
			}
		}
	}

	/**
	 * 
	 * @param request
	 * @param parameterName  参数名称
	 * @param parameterNotes 参数注释
	 * @param boo            是否必填
	 * @return
	 * @throws Exception
	 */
	public static double Double(HttpServletRequest request, String parameterName, String parameterNotes, boolean boo)
			throws Exception {
		double number = 0.00;
		try {
			try {
				number = Double.valueOf(request.getParameter(parameterName).toString());
				System.out.println(number);
				if (boo) {
					if (number == 0) {
						throw new NullPointerException();
					}
				}
				return number;
			} catch (NullPointerException e) {
				throw new Exception("'" + parameterNotes + "'不能为空");
			} catch (NumberFormatException e) {
				throw new Exception("'" + parameterNotes + "'参数类型有误");
			}
		} catch (Exception e) {
			if (boo) {
				throw new Exception(e.getMessage());
			} else {
				return 0.00;
			}
		}
	}

}

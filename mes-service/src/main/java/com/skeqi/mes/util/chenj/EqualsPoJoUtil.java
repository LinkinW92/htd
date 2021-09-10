package com.skeqi.mes.util.chenj;

import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 非空判断工具类
 *
 * @author YinP
 */
public class EqualsPoJoUtil {

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

    public static Integer pageNum(Integer pageNum) {

        try {
            if (pageNum <= 0) {
                pageNum = 1;
            }
            return pageNum;
        } catch (Exception e) {
            return 1;
        }
    }

    public static Integer pageSize(Integer pageSize) {
        try {
            if (pageSize <= 0) {
                pageSize = 1;
            }
            return pageSize;
        } catch (Exception e) {
            return 10;
        }
    }

    /**
     * @param parameterName  参数名称
     * @param parameterNotes 参数注释
     * @param fliZero        是否校验参数 '0'
     * @return
     * @throws Exception
     */
    public static Integer integer(String parameterName, String parameterNotes, boolean fliZero)
            throws Exception {
        Integer integer = null;
        try {
            try {
                integer = Integer.parseInt(parameterName);
                if (integer == null) {
                    throw new NullPointerException();
                } else if (fliZero) {
                    // 参数为 '0'
                    if (integer == 0) {
                        throw new NullPointerException();
                    }

                }
                return integer;
            } catch (NullPointerException e) {
                throw new Exception("'" + parameterNotes + "'不能为空");
            } catch (NumberFormatException e) {
                throw new Exception("'" + parameterNotes + "'参数类型有误,应该是数字");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    /**
     * @param parameterName  参数名称
     * @param parameterNotes 参数注释
     * @return
     * @throws Exception
     */
    public static String string(String parameterName, String parameterNotes)
            throws Exception {
        try {
            try {
                if (parameterName == null || "".equals(parameterName)) {
                    throw new NullPointerException();
                }
                return parameterName;
            } catch (NullPointerException e) {
                throw new Exception("'" + parameterNotes + "'不能为空");
            }
        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    /**
     * @param parameterName  参数名称
     * @param parameterNotes 参数注释
     * @return
     * @throws Exception
     */
    public static List<Object> collection(List<Object> parameterName, String parameterNotes)
            throws Exception {
        try {
            try {
                if (CollectionUtils.isEmpty(parameterName)) {
                    throw new NullPointerException();
                }
                return parameterName;
            } catch (NullPointerException e) {
                throw new Exception("'" + parameterNotes + "'不能为空");
            }
        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    /**
     * @param parameterName  参数名称
     * @param parameterNotes 参数注释
     * @return
     * @throws Exception
     */
    public static String date(String parameterName, String parameterNotes)
            throws Exception {
        try {
            try {
                if (parameterName == null || "".equals(parameterName)) {
                    throw new NullPointerException();
                }
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parameterName);

                return parameterName;
            } catch (ParseException ignored) {
                throw new Exception("'" + parameterNotes + "'格式错误");
            } catch (NullPointerException e) {
                throw new Exception("'" + parameterNotes + "'不能为空");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    /**
     * @param parameterName  参数名称
     * @param parameterNotes 参数注释
     * @return
     * @throws Exception
     */
    public static String dateYmd(String parameterName, String parameterNotes)
            throws Exception {
        try {
            try {
                if (parameterName == null || "".equals(parameterName)) {
                    throw new NullPointerException();
                }
                new SimpleDateFormat("yyyy-MM-dd").parse(parameterName);

                return parameterName;
            } catch (ParseException ignored) {
                throw new Exception("'" + parameterNotes + "'格式错误");
            } catch (NullPointerException e) {
                throw new Exception("'" + parameterNotes + "'不能为空");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * @param parameterName  参数名称
     * @param parameterNotes 参数注释
     * @return
     * @throws Exception
     */
    public static double Double(String parameterName, String parameterNotes)
            throws Exception {
        double number = 0.00;
        try {
            try {
                number = Double.valueOf(parameterName);
                if (number == 0) {
                    throw new NullPointerException();
                }
                return number;
            } catch (NullPointerException e) {
                throw new Exception("'" + parameterNotes + "'不能为空");
            } catch (NumberFormatException e) {
                throw new Exception("'" + parameterNotes + "'参数类型有误");
            }
        } catch (Exception e) {
            if (true) {
                throw new Exception(e.getMessage());
            } else {
                return 0.00;
            }
        }
    }

}

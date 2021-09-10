package com.skeqi.mes.util.chenj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ChenJ
 * @date 2021/6/18
 * @Classname formatUtils
 * @Description 格式化工具类
 */
public class FormatUtils {

    /**
     * 判断是否是电话
     *
     * @param phone
     * @return
     */

    public static boolean isPhone(String phone) throws Exception {
        Pattern phonePattern = Pattern.compile("^1\\d{10}$");
        Matcher matcher = phonePattern.matcher(phone);
        if (matcher.find()) {
            return true;
        } else {
            throw new Exception("请输入正确的手机号");
        }
    }

    /**
     * 判断是否是Email
     *
     * @return
     */

    public static boolean isEmail(String email) throws Exception {
        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(email);
        if (matcher.find()) {
            return true;
        } else {
            throw new Exception("请输入正确的邮箱地址");
        }


    }


    /**
     * 校验银行账号
     */
    public static boolean checkBankCard(String bankCard) throws Exception {
        if (bankCard.length() < 15 || bankCard.length() > 19) {
            throw new Exception("银行账号格式错误");
//            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if (bit == 'N') {
            throw new Exception("请输入正确的银行账号");
//            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeBankCard
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeBankCard) {
        if (nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        // lumSum
        int lamSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            lamSum += k;
        }
        return (lamSum % 10 == 0) ? '0' : (char) ((10 - lamSum % 10) + '0');
    }

    /**
     * 校验是否包含中文
     *
     * @param parameterName  参数名称
     * @param parameterNotes 参数注释
     * @return
     */
    public static boolean checkCountName(String parameterName, String parameterNotes) throws Exception {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(parameterName);
        if (m.find()) {
            throw new Exception("'" + parameterNotes + "'格式错误");
        } else {
            return true;
        }
    }

    /**
     * 校验是否不包含中文和包含特殊字符  // 通过校验：包含中文，不包含特殊字符即可
     *
     * @param parameterName  参数名称
     * @param parameterNotes 参数注释
     * @return
     */
    public static boolean checkNoCountNameNovalidateLegalString(String parameterName, String parameterNotes) throws Exception {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(parameterName);
        if (m.find()) {

            String illegal = "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆";

            char isLegalChar = 't';

            L1:
            for (int i = 0; i < parameterName.length(); i++) {
                for (int j = 0; j < illegal.length(); j++) {
                    if (parameterName.charAt(i) == illegal.charAt(j)) {
                        isLegalChar = parameterName.charAt(i);

                        break L1;

                    }
                }
            }
            if (isLegalChar == 't') {
                return true;
            } else {
                throw new Exception("'" + parameterNotes + "'格式错误");
            }
        } else {

            throw new Exception("'" + parameterNotes + "'格式错误");
        }
    }


    /**
     * 校验是否包含非法字符
     *
     * @param parameterName  参数名称
     * @param parameterNotes 参数注释
     * @return
     */
    public static boolean validateLegalString(String parameterName, String parameterNotes) throws Exception {


        String illegal = "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆";

        char isLegalChar = 't';

        L1:
        for (int i = 0; i < parameterName.length(); i++) {
            for (int j = 0; j < illegal.length(); j++) {
                if (parameterName.charAt(i) == illegal.charAt(j)) {
                    isLegalChar = parameterName.charAt(i);

                    break L1;

                }
            }
        }
        if (isLegalChar == 't') {
            return true;
        } else {
            throw new Exception("'" + parameterNotes + "'格式错误");
        }
    }

    /**
     * 校验是否包含中文和包含非法字符  // 通过校验：不包含中文，不包含特殊字符即可
     */
    public static boolean validateLegalStringORCheckCountName(String parameterName, String parameterNotes) throws Exception {

        String illegal = "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆";

        char isLegalChar = 't';

        L1:
        for (int i = 0; i < parameterName.length(); i++) {
            for (int j = 0; j < illegal.length(); j++) {
                if (parameterName.charAt(i) == illegal.charAt(j)) {
                    isLegalChar = parameterName.charAt(i);

                    break L1;

                }
            }
        }
        if (isLegalChar == 't') {
            // 校验是否包含中文
            Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher m = p.matcher(parameterName);
            if (m.find()) {
                throw new Exception("'" + parameterNotes + "'格式错误");
            } else {
                return true;
            }

        } else {
            throw new Exception("'" + parameterNotes + "'格式错误");
        }
    }


    public static void main(String[] args) {

    }


}

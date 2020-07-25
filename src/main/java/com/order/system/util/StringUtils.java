package com.order.system.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : str.toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * 获得i18n字符串
     */
//	public static String getMessage(String code, Object[] args) {
//		LocaleResolver localLocaleResolver = (LocaleResolver) SpringContextHolder.getBean(LocaleResolver.class);
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
//		Locale localLocale = localLocaleResolver.resolveLocale(request);
//		return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
//	}

    /**
     * 获得用户远程地址
     */
//	public static String getRemoteAddr(HttpServletRequest request){
//		String remoteAddr = request.getHeader("X-Real-IP");
//        if (isNotBlank(remoteAddr)) {
//        	remoteAddr = request.getHeader("X-Forwarded-For");
//        }else if (isNotBlank(remoteAddr)) {
//        	remoteAddr = request.getHeader("Proxy-Client-IP");
//        }else if (isNotBlank(remoteAddr)) {
//        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
//        }
//        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
//	}

    /**
     * 剪切文本。如果进行了剪切，则在文本后加上"..."
     *
     * @param s   剪切对象。
     * @param len 编码小于256的作为一个字符，大于256的作为两个字符。
     * @return
     */
    public static CharSequence textCut(String s, Integer len, String append) {
        if (s == null) {
            return null;
        }
        int slen = s.length();
        if (slen <= len) {
            return s;
        }
        // 最大计数（如果全是英文）
        int maxCount = len * 2;
        int count = 0;
        int i = 0;
        for (; count < maxCount && i < slen; i++) {
            if (s.codePointAt(i) < 256) {
                count++;
            } else {
                count += 2;
            }
        }
        if (i < slen) {
            if (count > maxCount) {
                i--;
            }
            if (!StringUtils.isBlank(append)) {
                if (s.codePointAt(i - 1) < 256) {
                    i -= 2;
                } else {
                    i--;
                }
                return s.substring(0, i) + append;
            } else {
                return s.substring(0, i);
            }
        } else {
            return s;
        }
    }

    public static String toString(Object[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public final static String shortRegionCode(String regionCode) {
        if (regionCode == null) {
            return "";
        }
        while (regionCode.endsWith("00")) {
            regionCode = regionCode.substring(0, regionCode.length() - 2);
        }
        return regionCode;
    }


    public static boolean checkCellphone(String cellphone) {
        //String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18\\d)|(177))\\d{8}$";
        String regex = "/^1[3|4|5|7|8][0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellphone);
        return matcher.matches();
    }

    public static boolean checkEmail(String email) {
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    //18
    public static String hideIdCard(String idNumber) {
        return idNumber.substring(0, 10).concat("******").concat(idNumber.substring(16));
    }

    public static boolean isIpv4(String ipv4) {
        if (StringUtils.isEmpty(ipv4)) {
            return false;// 字符串为空或者空串
        }
        // 因为java doc里已经说明, split的参数是reg, 即正则表达式, 如果用"|"分割, 则需使用"\\|"
        String[] parts = ipv4.split("\\.");
        if (parts == null || parts.length != 4) {
            return false;// 分割开的数组根本就不是4个数字
        }
        for (int i = 0; i < parts.length; i++) {
            try {
                int n = Integer.parseInt(parts[i]);
                if (n < 0 || n > 255) {
                    return false;// 数字不在正确范围内
                }
            } catch (NumberFormatException e) {
                return false;// 转换数字不正确
            }
        }
        return true;
    }

    /**
     * 隱藏手機號碼
     *
     * @param phone
     * @return
     */
    public static String cryptPhone(String phone) {
        if (StringUtils.isEmpty(phone) || phone.length() != 11) {
            return null;
        }
        StringBuffer phoneBuffer = new StringBuffer();
        phoneBuffer.append(phone.substring(0, 3)).append("****").append(phone.substring(phone.length() - 4));
        return phoneBuffer.toString();
    }

    public static String cryptEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        int index = email.indexOf("@");
        if (index <= 4) {
            return email;
        } else {
            StringBuffer emailBuffer = new StringBuffer();
            emailBuffer.append(email.substring(0, 4)).append("***").append(email.substring(index));
            return emailBuffer.toString();
        }
    }

    public static String cryptIdCard(String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return null;
        }
        StringBuffer emailBuffer = new StringBuffer();
        emailBuffer.append(idCard.substring(0, 4)).append("***").append(idCard.substring(idCard.length() - 4));
        return emailBuffer.toString();
    }

    public static String cryptLoginName(String loginName) {
        if (StringUtils.isEmpty(loginName)) {
            return null;
        }
        StringBuffer emailBuffer = new StringBuffer();
        emailBuffer.append(loginName.substring(0, 2)).append("***").append(loginName.substring(loginName.length() - 2));
        return emailBuffer.toString();
    }

    /**
     * 身份证含有字母'x'的，转为大写
     */
    public static String upperIdCard(String idcard) {
        if (StringUtils.isEmpty(idcard))
            return null;
        if (idcard.endsWith("x"))
            idcard = idcard.substring(0, idcard.length() - 1) + "X";
        return idcard;
    }


    /**
     * 判断字符串是否是整数
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否是数字 包括整数和小数
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");//这个是对的
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 将字符串数组的每个元素加上单引号拼成字符串，并以“,”隔开<br/>
     * 可用于构造sql参数
     */
    public static String jointParam(String[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            str.append("'" + array[i] + "'");
            if (i < array.length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }

    /**
     * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param url url地址
     * @return url请求参数部分
     */
    public static Map<String, String> urlAnalyze(String url) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        String strUrlParam = truncateUrl(url);
        if (strUrlParam == null) {
            return mapRequest;
        }
        // 每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            // 解析出键值
            if (arrSplitEqual.length > 1) {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param url url地址
     * @return url请求参数部分
     */
    private static String truncateUrl(String url) {
        String strAllParam = null;
        String[] arrSplit = null;

        url = url.trim();

        arrSplit = url.split("[?]");
        if (url.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }

    /**
     * 生成相应位数的验证码
     *
     * @param digit 位数
     */
    public static String getRandomCode(int digit) {
        char[] codeSeq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);//random.nextInt(10));
            s.append(r);
        }
        return s.toString();
    }
}
